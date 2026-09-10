import csv
import json
import math
import pathlib
import statistics

OUT = pathlib.Path(__file__).resolve().parent
rows = []
for version in ('baseline', 'current'):
    for fork in (1, 2):
        log = (OUT / f'{version}-fork{fork}.log').read_text()
        assert 'BUILD SUCCESS' in log, (version, fork, 'incomplete benchmark')
        for line in log.splitlines():
            if not line.startswith('BENCH,'): continue
            _, label, fork_id, case, iteration, ns, allocated, count, tenants, orgs = line.split(',')
            rows.append(dict(version=label, fork=int(fork_id), case=case, iteration=int(iteration),
                             milliseconds=int(ns)/1e6, allocated_mib=int(allocated)/1048576,
                             result_count=int(count), tenant_loads=int(tenants), org_loads=int(orgs)))
with (OUT / 'samples.csv').open('w') as f:
    writer = csv.DictWriter(f, fieldnames=list(rows[0]))
    writer.writeheader(); writer.writerows(rows)
stats = {}
cases = ('tree', 'allow_subtree', 'deny_all', 'deny_leaf', 'path_1', 'path_20')
for case in cases:
    stats[case] = {}
    for version in ('baseline', 'current'):
        data = [r for r in rows if r['case'] == case and r['version'] == version and r['iteration'] >= 0]
        assert len(data) == 10, (case, version, len(data))
        durations = sorted(r['milliseconds'] for r in data)
        stats[case][version] = dict(median_ms=statistics.median(durations), min_ms=min(durations),
                                   max_ms=max(durations), p90_ms=durations[math.ceil(.9*len(durations))-1],
                                   median_allocated_mib=statistics.median(r['allocated_mib'] for r in data),
                                   fork_medians_ms=[statistics.median(r['milliseconds'] for r in data if r['fork']==f) for f in (1,2)],
                                   result_counts=sorted(set(r['result_count'] for r in data)),
                                   tenant_loads=sorted(set(r['tenant_loads'] for r in data)),
                                   org_loads=sorted(set(r['org_loads'] for r in data)))
    old, new = stats[case]['baseline'], stats[case]['current']
    assert old['result_counts'] == new['result_counts']
    stats[case]['change_percent'] = (new['median_ms']/old['median_ms']-1)*100
    stats[case]['speedup'] = old['median_ms']/new['median_ms']
    stats[case]['allocation_change_percent'] = (new['median_allocated_mib']/old['median_allocated_mib']-1)*100
(OUT / 'results.json').write_text(json.dumps(stats, indent=2))
labels = {'tree':'组织树装配', 'allow_subtree':'允许 ROOT 全部后代', 'deny_all':'拒绝全部根组织及后代',
          'deny_leaf':'允许全部，排除一个叶节点', 'path_1':'1 条不命中 ID 路径规则', 'path_20':'20 条不命中 ID 路径规则'}
meta = json.loads((OUT / 'metadata.json').read_text())
lines = ['# DataScope 原版与新版性能对比', '',
         f"原版：Git `{meta['baseline']}`。新版：本次工作区隔离快照。", '',
         '相同本机、Maven 3.9.15、JDK 25.0.2、Java 17 编译目标、G1 GC、固定 1 GiB 初始/最大堆。',
         '两版分别运行在独立 JVM，串行执行，第二轮颠倒版本和场景顺序。每场景每 JVM 预热 2 次、计时 5 次；下表为 2 个 JVM 共 10 次计时的中位数。',
         '数据固定为 50,000 节点、100 层、单租户 T1、非管理员用户；沿用项目原有树形生成函数。数据准备、结果断言和节点计数不计入耗时。每次执行均验证结果数量以及未修改原始树。', '',
         '| 场景 | 原版 ms | 新版 ms | 耗时变化 | 原版/新版 | 返回数量 |', '|---|---:|---:|---:|---:|---:|']
for case in cases:
    v=stats[case]; o=v['baseline']; n=v['current']
    lines.append(f"| {labels[case]} | {o['median_ms']:.2f} | {n['median_ms']:.2f} | {v['change_percent']:+.1f}% | {v['speedup']:.2f}× | {n['result_counts'][0]} |")
lines += ['', '负的耗时变化表示新版更快。倍率表示原版耗时÷新版耗时；小于 1 表示新版更慢。', '',
          '## 内存分配', '',
          'ThreadMXBean 统计计时线程每次操作分配的字节数，中位数如下。此项是累计分配量，不是峰值堆占用，也不包含其他线程分配。', '',
          '| 场景 | 原版 MiB/次 | 新版 MiB/次 | 分配变化 |', '|---|---:|---:|---:|']
for case in cases:
    v=stats[case]
    lines.append(f"| {labels[case]} | {v['baseline']['median_allocated_mib']:.2f} | {v['current']['median_allocated_mib']:.2f} | {v['allocation_change_percent']:+.1f}% |")
lines += ['', '## 解释与边界', '',
          '- 全部六个场景的组织结果数量相同，可对比此处实际组织列表操作。原版拒绝组织影响租户列表的语义差异不计入本表；实测旧组织路径会补入默认租户，两版排除单叶均返回 49,999 条。',
          '- 原版与新版的租户/组织加载次数已记录在原始样本中。这是内存 fixture 基准，没有模拟真实数据库、网络、生产并发或其他租户分布。',
          '- 新单点 canAccessOrg 没有旧版同签名接口，未混入对比。',
          '- 使用轻量重复基准而非 JMH；毫秒级小差距可能受到 JIT、GC、调度和运行顺序影响，不应据此推断生产 QPS。完整范围与每 JVM 中位数见 results.json。',
          '- 此次不修改生产实现；构建和运行都在临时隔离快照完成。', '',
          '## 复现与原始数据', '',
          '- `run.py`：提取 Git 原版和当前 src/main；保留各自测试 fixture，只替换测试方法为同一基准。',
          '- `summarize.py`：汇总样本并校验每场景 10 次测量及返回数量一致。',
          '- `samples.csv`：含预热和测量样本。', '- `results.json`：统计结果及分 JVM 中位数。',
          '- `baseline-fork1.log`、`baseline-fork2.log`、`current-fork1.log`、`current-fork2.log`：Maven 和原始执行日志。',
          '隔离目录位置见本次生成的 metadata.json。', '']
(OUT / 'report.md').write_text('\n'.join(lines))
print(json.dumps(stats, ensure_ascii=False, indent=2))
