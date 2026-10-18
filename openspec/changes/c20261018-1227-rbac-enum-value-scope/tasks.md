# 实施任务

- [x] 先编写继承、错误配置、不可变、并发缓存测试。
- [x] 实现注解及元数据解析器。
- [x] 更新随包手册和入口，运行相关及回归测试。

验证：JDK 25，`mvn -U -q test -P '!01-跳过测试'`，{'tests': 303, 'failures': 0, 'errors': 0, 'skipped': 0}；新元数据6项、既有属性分组8项均通过。红测日志 `/tmp/rbac-enum-support-red.log`，回归日志 `/tmp/rbac-enum-support-all-tests.log`。未发布、安装、提交或推送。

本地 `package -DskipTests` 通过；JAR 中新注解、元数据类、README 和 manual 新章节存在。构建日志 `/tmp/rbac-enum-support-package.log`。经负责人扩大所有权后，仅在现有根文档资源 includes 增加 AGENTS.md；重新 package 通过，确认 AGENTS、README、manual 与新增章节均入包，AGENTS 中保留直接手册入口。

- [x] 补齐 JPA STRING 枚举映射及转换器拒绝校验，先红后绿测试并全包回归。

映射增强验证：新增元数据9项与属性分组8项定向通过；全包{'tests': 306, 'failures': 0, 'errors': 0, 'skipped': 0}。日志 `/tmp/rbac-enum-support-mapping-red.log`、`/tmp/rbac-enum-support-mapping-green.log`、`/tmp/rbac-enum-support-mapping-all-tests.log`。

- [x] 收紧 JPA FIELD 访问与字段直接 STRING 映射，覆盖 PROPERTY/混合/getter-only 拒绝。

FIELD 收紧：定向18项通过，全包{'tests': 307, 'failures': 0, 'errors': 0, 'skipped': 0}。首次全包仅既有组织树2秒性能阈值超时，未改实现或测试，完整重跑通过。保留日志 `/tmp/rbac-enum-support-field-all-tests.log` 与 `...-retry.log`；新元数据10项通过。
