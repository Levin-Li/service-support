"""Reuse the existing isolated benchmark with both sides on the current scope protocol."""
import pathlib
import sys
import tempfile
import argparse
import json

parser = argparse.ArgumentParser(description=__doc__)
parser.add_argument("--after-cache-key-change", action="store_true")
parser.add_argument("--cases", nargs="+", choices=["path_1", "path_20"], default=["path_1", "path_20"])
parser.add_argument("--warmups", type=int, default=8)
parser.add_argument("--samples", type=int, default=12)
args = parser.parse_args()
if args.warmups < 0 or args.samples < 1:
    parser.error("warmups must be nonnegative and samples must be positive")

source = pathlib.Path(__file__).resolve().parents[1] / "datascope-2026-09-11/run.py"
output = pathlib.Path(tempfile.mkdtemp(prefix="org-path-key-confirm-"))
code = source.read_text()
code = code.replace("OUT = pathlib.Path(__file__).resolve().parent", f"OUT = pathlib.Path({str(output)!r})")
# Only the protocol-dependent harness substitutions use this conditional expression.
code = code.replace("if version == 'baseline' else", "if False else")
code = code.replace('{"tree", "allow_subtree", "deny_all", "deny_leaf", "path_1", "path_20"}',
                    "{" + ", ".join(json.dumps(case) for case in args.cases) + "}")
code = code.replace("i = -2; i < 5", f"i = -{args.warmups}; i < {args.samples}")
code = code.replace("'warmups_per_case_per_fork': 2", f"'warmups_per_case_per_fork': {args.warmups}")
code = code.replace("'samples_per_case_per_fork': 5", f"'samples_per_case_per_fork': {args.samples}")
if args.after_cache_key_change:
    # Step 2 compares against HEAD plus step 1's unchanged OrgScopePaths implementation.
    marker = "    cmd = ['mvn', '-o', '-Dmaven.compiler.proc=full'"
    assert marker in code
    code = code.replace(marker,
            "    if version == 'baseline':\n"
            "        shutil.copy2(ROOT / 'src/main/java/com/levin/commons/rbac/OrgScopePaths.java', "
            "dest / 'src/main/java/com/levin/commons/rbac/OrgScopePaths.java')\n" + marker)
    code = code.replace("'baseline': BASE", "'baseline': BASE, 'baseline_overlay': 'working-tree OrgScopePaths.java'")
sys.argv = [str(source), "5f5a8e89576722c8a187e33c94d70ddcccf1e23f"]
print("OUTPUT", output, flush=True)
exec(compile(code, str(source), "exec"), {"__file__": str(source), "__name__": "__main__"})
