# 数据范围、机密等级及角色分配：需求验收清单

依据：`manual.md` 第 19–20、22.3 节，以及本次明确新增的角色分配上限要求。
新增要求：每个角色独立授予范围及最终用户有效范围不能超过操作者；按分配时完整有效目录逐项校验，后续目录、规则、用户上下文变化需重校验。

2026-09-14 身份规则更新：仅 R_SA 超管（含顶级 sa）享有全局租户/组织范围快捷路径。SaaSAdmin 与普通平台用户按明确授权访问，不能自动跨组织；有授权可跨组织树，不添加所属组织硬边界。SaaSAdmin 原有列表和管理密级过滤仍保留。R06/R09/R10 的 SaaSAdmin 预期已同步收紧。

## 验收口径

本表列出有限需求等价类及其组合，逐项定义预期允许/拒绝、结果集合或异常。预期掩码来自需求真值表，不调用被测判断函数生成；测试同时覆盖普通用户和管理员例外。
这些已列明的需求组合全部通过，不能将此解释为任意 Groovy 程序、任意外部实现及未来数据均已穷尽证明，也不能将其冒充整类字节码分支 100%。
目录加载器须提供完整一致快照，检查和保存须处于同一一致性边界。脚本为可信、无副作用判定；含范围脚本时目标真实对象必须已经携带最终角色列表，操作者仍为当前授权状态。

## 新增需求矩阵

以下方法均位于 `src/test/java/com/levin/commons/rbac/RbacAuthorizeServiceRolePermissionTest.java`。
`requirementMatrix` 前缀后的名称与测试源码一致。组合数在相应用例末尾断言；等级矩阵断言 34 个边界并遍历其笛卡尔积。

| ID | 需求及分支 | 方法名（省略 requirementMatrix 前缀） | 组合数 |
|---|---|---|---:|
| R01 | 六字段同时 null/空/非空；5 种用户等级；角色无领域/正向领域/交换领域，含仅部分角色能贡献权限 | AllSixFieldSourcesAndConfidentialOverrides | 10,935 |
| R02 | 租户允许×拒绝；All/Default/None/精确 ID/字面星号/脚本 true/false；null/空串/空白/有租户身份；用户/角色来源 | TenantMarkersAllowDenyAndUserRoleSources | 648 |
| R03 | 所有等级枚举值及相邻值、null、整数极值的授予×要求比较；公开值免读取、单次缓存、null 结果缓存 | ConfidentialThresholdsAndLazyEvaluation | 1,156 + 懒加载断言 |
| R04 | 六字段非法元素 null/空/空白、空 Groovy body、无效组织模式；用户与角色来源，显式空覆盖错误角色字段 | InvalidScopeElementsAndExplicitOverride | 44 |
| R05 | 组织允许×拒绝；14 种起点/模式，DEFAULT 有无组织、同/异租户、两种来源；逐节点与列表对照，含孤儿 | OrganizationModesDefaultsAndSetSubtraction | 3,136 |
| R06 | 7 种身份×6 种等级×3 种领域授权×16 种租户/组织允许拒绝组合；单点、列表和密级分别断言 | AdminDomainRangeAndConfidentialGates | 2,016 |
| R07 | 领域 All/None/精确 ID/字面星号/未知/无效目录；允许×拒绝×用户/角色来源；对象空域与直接空域不同 | DomainMarkersAndDirectoryValidity | 128 |
| R08 | 本地/共享角色有效、禁用、过期、删除、缺 ID、外租户；目录顺序、领域、用户等级；不可见角色不混用对象密级 | RoleFallbackVisibilityAndGrantedLevel | 432 + 3 个空/零等级组合 |
| R09 | 7 种身份×双方领域空/同/异×领域允许/拒绝；单点、列表、全量声明和管理入口一致性 | TenantAndOrganizationDomainConsistency | 1,008 |
| R10 | 租户显式 All、组织 AllRoot、空/不命中/命中拒绝、孤儿节点、7 种身份；保守全组织判断 | ConservativeAllOrganizationClaims | 378 |
| R11 | 分配：操作者允许×拒绝×角色允许×拒绝；两方 Default 归属、公共范围、动态真/假、跨租户限制 | RoleAssignmentTenantContainment | 16,384 |
| R12 | 分配：操作者组织允许×拒绝×角色允许×拒绝；双方 Default 有/无组织，子树、ID/名称路径、Groovy | RoleAssignmentOrgContainment | 26,244 |
| R13 | 分配：操作者领域允许×拒绝×角色允许×拒绝，含 None 与字面星号 | RoleAssignmentDomainContainment | 1,296 |

共 63,808 组上述有计数的需求组合（含 R08 的 3 个额外等级组合），另有下列错误路径和跨请求断言。

## 必须保留的异常、覆盖及生命周期断言

| ID | 需求 | 实际测试方法 |
|---|---|---|
| R14 | 接口默认集合是显式定义、协议固定值精确、组织解析格式/None 规则 | DataScopeContractTest 的 preservesExactProtocolValues、interfaceDefaultsAreExplicitDefinitionsRatherThanInheritance 及解析测试 |
| R15 | 空输入、未知主体、角色目录空元素/空编码/重复/不存在编码 | requirementScopeNullInputsAndEmptyResults |
| R16 | Groovy 仅 Boolean true 授权，null/数字/字符串不当 true，语法/运行错误拒绝，编译缓存不复用用户结论 | requirementScriptBooleanResultsExceptionsAndFreshUserContext |
| R17 | 静态拒绝、空允许或管理员快捷路径应按契约阻止无关脚本执行 | shouldSkipTenantAllowScriptWhenTenantIsStructurallyDenied、shouldMatchStructuralTenantDenialBeforeEarlierScript、shouldSkipTenantDenyScriptWhenAllowSetIsEmpty、shouldPreservePlatformAdministratorShortcutBeforeScopeScripts 等原回归 |
| R18 | 域目录状态/错误 ID/异常后重新求值；服务实例及用户间不串授权 | shouldRejectUnavailableDomainObjectsAtSingleAndListEntrypoints、shouldRejectDomainLoaderReturningWrongOrNullIdentity、shouldClearDomainEvaluationContextAfterLoaderFailure、shouldNotShareDomainMetadataBetweenServiceInstances、shouldRecheckDomainStatusWhenImplementationReusesDataScopeSnapshot |
| R19 | 快照不可变、用户/角色配置变化后重新计算 | shouldReturnScopeSnapshotWithoutMutatingSourceSets、shouldRecalculateDataScopeWithoutTransientCache、shouldRecalculateConfidentialLevelWithoutTransientCache |
| R20 | 路径和 Groovy 不越过起点/租户；循环和孤儿不能伪装全量授权；ID/名称、尾斜杠及根切换缓存隔离 | shouldKeepCustomPathPatternInsideConfiguredScopeRoot、shouldExcludeDisconnectedCyclesFromAllRootOrganizationGrant、shouldKeepInterleavedNamePathRulesRelativeToTheirOwnRoots、shouldRebuildNamePathsWhenSourceNamesOrParentRelationshipsChangeBetweenCalls、shouldKeepPathParsingLazyForSkippedCandidates、OrgScopePathsTest |
| R21 | 分配角色不能借目标空覆盖/另一拒绝角色遮盖独立越权；最终状态及清空角色也检查 | requirementRoleAssignmentChecksOrgSubsetAndIndividualRoles |
| R22 | 分配双方 Default 字符串相同也可能指不同租户/组织 | requirementRoleAssignmentDefaultMustUseTargetIdentity，加 R11/R12 矩阵 |
| R23 | 分配本身改变 hasRole 时不能用旧用户状态检查，新增/移除角色均验证最终真实上下文 | requirementRoleAssignmentScriptsNeedFinalConcreteUserState（含组织模式空白） |
| R24 | isRoleAuthorized 扩展放行不能越过公开机密等级上限；角色编码产生的管理员能力和 TopSA 极值也检查 | requirementRoleAssignmentMustHonorConfidentialOverride、requirementRoleAssignmentNativeAdminPrivilegesAreAlsoCapped、requirementRoleAssignmentInheritedLevelsAndQualifiedRoles |
| R25 | 必需目录 null 拒绝；无效/外租户节点过滤；大树按批次处理 | requirementRoleAssignmentRejectsUnavailableDirectories、requirementRoleAssignmentFiltersInvalidDirectoryObjects、requirementRoleAssignmentUsesBatchedOrganizationChecks |
| R26 | 实际分配使用目标租户真实定义；本地有效同码覆盖共享，权限/密级/领域/分配条件/互斥共存均不能借共享绕过 | shouldFallbackToSharedRoleWhenLocalDefinitionIsDisabledOrExpired、shouldRejectRequestedRoleWithoutAnEffectiveDefinitionForTargetTenant、shouldNotUseSharedRoleMetadataToBypassLocalPermissionOrConfidentialLevel、shouldNotUseSharedRoleToBypassLocalDomainOrAssignmentConditions、shouldUseOnlySelectedRolePermissionsWithoutAggregatingSameCodeDefinitions 等原分配回归 |
| R27 | SaaS 管理员无默认全量范围；明确跨树允许、拒绝、父/目标/None 管理门槛、角色继承与用户空覆盖；分配预测与实际权限一致 | shouldRequireExplicitScopesForSaasAdministratorOrganizationAccess、shouldApplyRoleInheritanceAndUserOverridesForSaasAdministrator、shouldNotPredictAutomaticGlobalScopeWhenAssigningSaasAdministrator，加 R06/R09/R10 |

注意 `A|IdPath#/*` 与 `A|IdPath#/*/` 在 Spring 根路径 `/` 上结果不同：前者可包含 A，后者不包含。矩阵分别定义了预期，不能把二者都简化成 DirectChild。

## 执行与证据

普通全量运行必须显式开启测试，仓库继承配置会使裸 `mvn test` 跳过：

```sh
mvn clean test -Dmaven.test.skip=false -DskipTests=false
```

本轮全量实际运行 287 个 JUnit 测试（矩阵循环算单个测试方法），失败/错误/跳过均为 0。
大树分配回归：50,000 节点、100 层，组织目录加载恰为 2 次（独立角色和最终范围），防止逐节点重载整树。

JaCoCo 使用已有 0.8.12 agent，仅插桩 `com.levin.commons.*`，避免对当前 JDK 25 类插桩不兼容；报告位于 `target/site/jacoco/`，分支计数摘要及被测源码 SHA-256 归档在本文同目录的 `coverage-summary.json`。

本机覆盖采集命令（需要本地已有该 agent）：

```sh
mvn -Dmaven.test.skip=false -DskipTests=false \
  "-DargLine=-javaagent:${HOME}/.m2/repository/org/jacoco/org.jacoco.agent/0.8.12/org.jacoco.agent-0.8.12-runtime.jar=destfile=target/scope-coverage.exec,append=false,includes=com.levin.commons.*" \
  test org.jacoco:jacoco-maven-plugin:0.8.12:report -Djacoco.dataFile=target/scope-coverage.exec
```

测试先在旧实现上证明新增租户越权及不同 Default 分配案例失败，再实现拒绝检查；新增矩阵不改既有测试预期。

核对重点：用户六字段合并 20/20、字段来源与格式 10/10、用户等级回退 4/4、等级比较 16/16、静态租户规则 22/22、无组织规则 10/10、组织规则匹配 42/42、组织单点入口 24/24 分支已命中。完整类仍包含未覆盖的其他辅助/防御性分支，不能报告整类 100%。

## 本轮变更边界

性能优化暂停在前两小步；本轮新增的生产行为仅为已明确授权的角色分配范围上限及其失败保护，并同步 Swagger Tag/Operation 和 manual 19.5。
本清单不证明下游 DAO 自动加权限条件、所有自定义服务覆写都安全、任意脚本可终止或未来目录永远包含；这些不在当前快照验收的有限输入范围内。目录/规则/用户变化重新校验是调用方必须履行的契约。
