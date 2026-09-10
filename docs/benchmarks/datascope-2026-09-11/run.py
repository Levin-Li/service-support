import io
import json
import pathlib
import shutil
import subprocess
import tarfile
import tempfile
import sys

ROOT = pathlib.Path(__file__).resolve().parents[3]
OUT = pathlib.Path(__file__).resolve().parent
BASE = subprocess.check_output(['git', 'rev-parse', sys.argv[1] if len(sys.argv) > 1 else 'd356897b86f64b631289c95f1d5f832527364514'], cwd=ROOT, text=True).strip()
TMP = pathlib.Path(tempfile.mkdtemp(prefix='datascope-comparison-'))
FIXTURE = 'src/test/java/com/levin/commons/rbac/RbacAuthorizeServiceRolePermissionTest.java'

def end_block(text, start):
    depth, i, state = 0, start, None
    while i < len(text):
        c = text[i]
        if state == 'line':
            if c == '\n': state = None
        elif state == 'comment':
            if text[i:i+2] == '*/': state = None; i += 1
        elif state in ('"', "'"):
            if c == '\\': i += 1
            elif c == state: state = None
        elif text[i:i+2] == '//': state = 'line'; i += 1
        elif text[i:i+2] == '/*': state = 'comment'; i += 1
        elif c in ('"', "'"): state = c
        elif c == '{': depth += 1
        elif c == '}':
            depth -= 1
            if depth == 0: return i + 1
        i += 1
    raise ValueError('unclosed Java block')

HARNESS = r'''
    @Test
    void compareImplementations() {
        String version = System.getProperty("bench.version");
        int fork = Integer.parseInt(System.getProperty("bench.fork"));
        List<TestOrg> data = largeLayeredOrgTree("ROOT", "T1", 50000, 100);
        var allocations = (com.sun.management.ThreadMXBean) java.lang.management.ManagementFactory.getThreadMXBean();
        allocations.setThreadAllocatedMemoryEnabled(true);
        long thread = Thread.currentThread().getId();
        String[] cases = {"tree", "allow_subtree", "deny_all", "deny_leaf", "path_1", "path_20"};
        // Second fresh JVM uses the reverse scenario order to reduce ordering bias.
        if (fork % 2 == 0) Collections.reverse(Arrays.asList(cases));
        for (String scenario : cases) {
            RULE_TYPE rules = new ArrayList<>();
            if (scenario.startsWith("path_")) {
                int n = Integer.parseInt(scenario.substring(5));
                for (int i = 0; i < n; i++) rules.add(customScope("ROOT", true, "/missing-" + i + "/**", PATH_MODE));
            } else {
                rules.add(scope("ROOT", true, ALL_MODE));
                if (scenario.equals("deny_all")) rules.add(scope(ALL_ROOT, false, ALL_MODE));
                if (scenario.equals("deny_leaf")) rules.add(scope("ROOT-49999", false, SELF_MODE));
            }
            TestRbacUser principal = new TestRbacUser("bench", "bench", "T1", "OPS", Collections.emptyList(), 5000, "ROOT", rules);
            AtomicInteger tenantLoads = new AtomicInteger();
            AtomicInteger orgLoads = new AtomicInteger();
            StubRbacBaseService service = new StubRbacBaseService(principal) {
                @Override public <T extends RbacTenantInfo> Collection<T> loadAllTenantList(boolean effective) {
                    tenantLoads.incrementAndGet();
                    return super.loadAllTenantList(effective);
                }
                @Override public <O extends RbacOrgInfo> List<O> loadTenantOrgList(Serializable tenant, boolean effective) {
                    orgLoads.incrementAndGet();
                    return super.loadTenantOrgList(tenant, effective);
                }
            };
            service.setTenantList(Collections.singletonList(new TestTenant("T1", "Tenant"))).setOrgList(data);
            java.util.function.Supplier<Collection<TestOrg>> operation = scenario.equals("tree")
                    ? () -> service.assembleOrgTree(data, true, "ROOT")
                    : () -> service.loadUserAccessibleOrgList(principal, true);
            int expected = scenario.equals("tree") || scenario.equals("allow_subtree") ? 50000
                    : scenario.equals("deny_leaf") ? 49999 : 0;
            for (int i = -2; i < 5; i++) {
                tenantLoads.set(0); orgLoads.set(0);
                long beforeBytes = allocations.getThreadAllocatedBytes(thread);
                long before = System.nanoTime();
                Collection<TestOrg> result = operation.get();
                long elapsed = System.nanoTime() - before;
                long allocated = allocations.getThreadAllocatedBytes(thread) - beforeBytes;
                int count = scenario.equals("tree") ? countTreeNodes(result) : result.size();
                assertEquals(expected, count, version + ":" + scenario);
                assertEquals(50000, data.size());
                assertTrue(data.get(0).getChildren().isEmpty(), "benchmark must not mutate input tree");
                System.out.printf(java.util.Locale.ROOT, "BENCH,%s,%d,%s,%d,%d,%d,%d,%d,%d%n",
                        version, fork, scenario, i, elapsed, allocated, count, tenantLoads.get(), orgLoads.get());
                System.out.flush();
            }
        }
    }
'''

OUT.joinpath('environment.txt').write_text(subprocess.check_output(['mvn', '-version'], cwd=ROOT, text=True))
OUT.joinpath('metadata.json').write_text(json.dumps({'baseline': BASE, 'snapshots': str(TMP),
    'warmups_per_case_per_fork': 2, 'samples_per_case_per_fork': 5,
    'forks': 2, 'java_options': '-Xms1g -Xmx1g -XX:+UseG1GC'}, indent=2))
for version in ['baseline', 'current']:
    dest = TMP / version
    dest.mkdir()
    if version == 'baseline':
        archive = subprocess.check_output(['git', 'archive', BASE, 'pom.xml', 'src/main'], cwd=ROOT)
        with tarfile.open(fileobj=io.BytesIO(archive)) as tf:
            for member in tf.getmembers():
                assert not pathlib.PurePosixPath(member.name).is_absolute()
                assert '..' not in pathlib.PurePosixPath(member.name).parts
                assert member.isfile() or member.isdir()
            tf.extractall(dest)
        text = subprocess.check_output(['git', 'show', BASE + ':' + FIXTURE], cwd=ROOT, text=True)
    else:
        shutil.copy2(ROOT / 'pom.xml', dest / 'pom.xml')
        shutil.copytree(ROOT / 'src/main', dest / 'src/main')
        text = (ROOT / FIXTURE).read_text()
    # Keep production and fixtures untouched; replace only test methods with this common harness.
    while '\n    @Test\n' in text:
        start = text.index('\n    @Test\n')
        body = text.index('{', start)
        text = text[:start] + text[end_block(text, body):]
    harness = HARNESS
    substitutions = {'RULE_TYPE': 'List<SimpleOrgScope>' if version == 'baseline' else 'List<ScopeGrant>',
        'PATH_MODE': 'OrgScope.ExpressionType.IdPath' if version == 'baseline' else 'DataScope.OrgMatchingMode.IdPath',
        'ALL_MODE': 'OrgScope.ScopeMatchingMode.All' if version == 'baseline' else 'DataScope.OrgMatchingMode.SelfAndAllChild',
        'SELF_MODE': 'OrgScope.ScopeMatchingMode.OnlySelf' if version == 'baseline' else 'DataScope.OrgMatchingMode.Self',
        'ALL_ROOT': 'OrgScope.ALL_ROOT_ORG' if version == 'baseline' else 'DataScope.StartOrg.AllRoot.getExpression()'}
    for key, value in substitutions.items(): harness = harness.replace(key, value)
    text = text[:text.rfind('}')] + harness + '\n}\n'
    text = text.replace('RbacAuthorizeServiceRolePermissionTest', 'RbacPerfComparisonTest')
    target = dest / FIXTURE.replace('RbacAuthorizeServiceRolePermissionTest', 'RbacPerfComparisonTest')
    target.parent.mkdir(parents=True)
    target.write_text(text)
    cmd = ['mvn', '-o', '-Dmaven.compiler.proc=full', '-Dmaven.test.skip=false', '-DskipTests=true', 'clean', 'test-compile']
    with (OUT / (version + '-build.log')).open('w') as log:
        subprocess.run(cmd, cwd=dest, stdout=log, stderr=subprocess.STDOUT, check=True)
    print('BUILT', version, flush=True)

# Sequential JVMs only; reverse version order for the second fork.
for fork, order in [(1, ['baseline', 'current']), (2, ['current', 'baseline'])]:
    for version in order:
        cmd = ['mvn', '-o', '-Dmaven.test.skip=false', '-DskipTests=false',
               '-Dtest=RbacPerfComparisonTest', '-DargLine=-Xms1g -Xmx1g -XX:+UseG1GC',
               '-Dbench.version=' + version, '-Dbench.fork=' + str(fork), 'surefire:test']
        print('RUNNING', version, fork, flush=True)
        with (OUT / (version + '-fork' + str(fork) + '.log')).open('w') as log:
            subprocess.run(cmd, cwd=TMP / version, stdout=log, stderr=subprocess.STDOUT, check=True)
        print('FINISHED', version, fork, flush=True)
print('DONE', str(OUT), flush=True)
