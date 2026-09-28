# RBAC 角色命名迁移说明：Platform / Tenant

## 适用范围

本次变更统一 `RbacRoleInfo` 的角色命名：平台级角色使用 `Platform`，租户级角色使用 `Tenant`。这是一次**包含角色编码变更的破坏性迁移**，下游应用需要同时更新 Java 常量引用和持久化/配置中的角色编码字符串。

`sa` 仍是顶级超级管理员的登录名，不是角色编码；不要把账号名改为 `platform-sa`。

## Java 常量与角色编码对照

| 旧 Java 常量 | 新 Java 常量 | 旧角色编码 | 新角色编码 | 是否需要迁移字符串 |
| --- | --- | --- | --- | --- |
| `RbacRoleInfo.SA_ROLE` | `RbacRoleInfo.PLATFORM_SA` | `R_SA` | `R_PLATFORM_SA` | 是 |
| `RbacRoleInfo.ADMIN_ROLE` | `RbacRoleInfo.TENANT_ADMIN` | `R_ADMIN` | `R_ADMIN` | 否，仅修改 Java 引用 |
| `RbacRoleInfo.ORG_ADMIN_ROLE` | `RbacRoleInfo.TENANT_ORG_ADMIN` | `R_ORG_ADMIN` | `R_ORG_ADMIN` | 否，仅修改 Java 引用 |

平台管理员继续使用 `RbacRoleInfo.PLATFORM_ADMIN`，角色编码仍为 `R_PLATFORM_ADMIN`，本次没有改变其编码。

## 行为影响

`R_PLATFORM_SA` 现在是平台超级管理员的唯一角色编码。以下能力均以该编码判断：

- `RbacUserInfo.isSuperAdmin()`；
- 顶级超级管理员判定（登录名为 `sa` 且拥有 `R_PLATFORM_SA`）；
- 全局租户/组织范围快捷路径；
- 超级管理员角色分配与层级管理；
- `@DataMasking` 的默认免脱敏授权。

因此，仅保留旧编码 `R_SA` 的用户在升级后不再被识别为超级管理员；其全局范围、角色管理及免脱敏权限都会失效。库没有保留旧编码别名或双轨识别，避免两个角色码并存时产生不可审计的授权语义。

## 下游迁移步骤

1. 在数据库、初始化脚本、权限中心配置、缓存预置数据、消息载荷和序列化快照中，将角色编码 `R_SA` 精确替换为 `R_PLATFORM_SA`。
2. 同时更新角色定义记录与所有用户—角色关联记录；如果角色编码也出现在互斥/共存角色列表、角色分配前置条件或权限表达式中，也一并更新。
3. 更新所有 Java 源码：`SA_ROLE` 改为 `PLATFORM_SA`，`ADMIN_ROLE` 改为 `TENANT_ADMIN`，`ORG_ADMIN_ROLE` 改为 `TENANT_ORG_ADMIN`。
4. 更新 `@ResAuthorize(anyRoles = ...)`、`@DataMasking(noMaskingAuthorize = ...)`、单元测试夹具及任何直接比较角色编码的代码。
5. 在切换应用版本前，使用真实角色目录和用户关联数据验证：平台超级管理员、顶级 `sa`、租户管理员、组织管理员与普通平台管理员的授权结果符合预期。

建议将角色定义和用户—角色关联放在同一个事务或同一个发布窗口中完成，避免应用升级后短暂出现用户仍持有 `R_SA`、但新版本只识别 `R_PLATFORM_SA` 的权限丢失。

## 验收检查

迁移后应满足：

```text
git grep -n 'R_SA'
```

对业务仓库只应允许出现非角色含义的名称（例如 `R_SALES`）；不应存在独立的角色码 `R_SA`。运行时还应确认：

- `isSuperAdmin()` 对拥有 `R_PLATFORM_SA` 的平台用户返回 `true`；
- 登录名 `sa` 加 `R_PLATFORM_SA` 时，`isTopSuperAdmin()` 返回 `true`；
- `R_ADMIN` 与 `R_ORG_ADMIN` 的既有租户/组织管理员行为未改变；
- `R_PLATFORM_ADMIN` 的显式范围和机密等级约束未改变。

## 不兼容性摘要

- 旧 Java 常量已删除，直接使用它们的下游项目会编译失败。
- `R_SA` 不再被框架视为超级管理员角色。
- 没有运行时自动转换、兼容别名或回退逻辑；迁移必须由下游系统显式完成。
