# service-support 项目用户手册

## 1. 项目简介

`service-support` 是一个面向 Spring / Spring Boot 生态的通用支撑库。  
它不是单一功能组件，而是一组可组合的基础能力，核心目标是：

- 减少业务项目里的重复样板代码
- 统一权限、数据范围、变量注入、代理注册等横切逻辑
- 为多租户、组织树、机密数据分级等场景提供默认模型

从当前代码结构看，项目主要覆盖以下能力：

- 编译期名称常量生成
- 基于注解的代理 Bean 自动注册
- 插件机制
- 变量解析与字段注入
- Spring MVC 参数转换增强
- RBAC 权限模型与授权引擎
- 常用工具类与基础领域接口

如果你的项目是一个中后台系统，并且同时存在：

- 多租户
- 用户、角色、权限
- 组织树
- 数据密级
- 控制器/服务方法级授权

那么这个项目最值得重点接入的部分通常就是 RBAC 模块。

---

## 2. 适用环境

### 2.1 当前构建配置

根据当前 [pom.xml](/Users/lilw/IdeaProjects/service-support/pom.xml)：

- `groupId`: `com.levin.commons`
- `artifactId`: `service-support`
- `version`: `2.0.0-SNAPSHOT`
- `Java`: `17`
- `Spring Boot BOM`: `4.0.5`

### 2.2 依赖特点

这个项目中有不少依赖使用了 `provided`，例如：

- Spring Core / AOP / Web / MVC
- Groovy
- SpEL
- Jakarta Validation / Servlet / Persistence
- Swagger Annotations
- Fastjson / Fastjson2
- Hutool

这意味着：

1. 编译本库时需要这些依赖
2. 在你的业务项目运行时，也要按实际使用的功能自行补齐依赖

如果你只用其中一部分能力，比如只用 RBAC，不一定需要把全部生态依赖都带上，但 RBAC 至少通常会依赖：

- `spring-core`
- `spring-expression`
- `spring-aop`
- `hutool`
- `groovy`

---

## 3. 快速开始

### 3.1 Maven 引入

```xml
<dependency>
    <groupId>com.levin.commons</groupId>
    <artifactId>service-support</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</dependency>
```

如果你使用 JitPack，则参考 [README.md](/Users/lilw/IdeaProjects/service-support/README.md) 的仓库配置。

### 3.2 推荐接入顺序

建议按风险从低到高分阶段接入：

1. 编译期常量生成
2. Spring MVC 参数转换
3. 常用工具类
4. 变量注入
5. 代理 Bean 注册
6. 插件机制
7. RBAC

如果你的项目已经有权限体系，建议先单独接入 RBAC，再逐步决定是否复用代理、插件和变量注入这几块。

---

## 4. 模块总览

当前源码包结构大致如下：

- `com.levin.commons.processor`
  注解处理器，负责生成 `E_` 常量类

- `com.levin.commons.service.proxy`
  代理 Bean 扫描和注册

- `com.levin.commons.plugin`
  插件接口与插件管理器

- `com.levin.commons.service.support`
  变量解析、Spring MVC 转换、上下文工具等支撑能力

- `com.levin.commons.rbac`
  RBAC 核心模型、授权服务、数据范围和权限匹配逻辑

- `com.levin.commons.dao.domain`
  一组基础领域接口，例如：
  `ConfidentialObject`、`MultiTenantObject`、`ProxyWrapperObject`

- `com.levin.commons.utils`
  通用工具类

下面会先简单介绍全项目常见能力，再把 RBAC 单独展开。

---

## 5. 常用能力速览

### 5.1 编译期常量生成

项目包含注解处理器，可以在编译期为实体、接口或注解生成 `E_` 前缀的名称常量类。

适合解决的问题：

- 避免手写字段名字符串
- 在查询构造、表达式拼接、列名引用中减少硬编码

典型使用方式：

- JPA `@Entity`
- `@MappedSuperclass`
- `@GenNameConstant`

---

### 5.2 代理 Bean 自动注册

适合“接口 + 统一拦截处理”的场景，例如：

- 远程调用客户端
- 统一权限代理
- 注解驱动的服务注册

关键注解：

- `@ProxyBeanScan`
- `@ProxyBeanScans`
- `@EnableProxyBean`

支持三类拦截器：

- `InvocationHandler`
- `org.aopalliance.intercept.MethodInterceptor`
- `org.springframework.cglib.proxy.MethodInterceptor`

---

### 5.3 插件机制

插件模块适合做“运行时发现 + 注册 + 分发事件”的扩展点。

关键接口：

- `Plugin`
- `PluginManager`
- `PluginConfigurer`

如果你的系统有“模块化扩展”、“应用市场”、“按插件加载菜单和数据资源”的需求，这一块会比较有价值。

---

### 5.4 变量解析与字段注入

核心能力是把请求上下文、变量上下文或脚本表达式结果注入到对象字段。

关键接口/注解：

- `@InjectVar`
- `VariableResolver`
- `VariableInjector`

支持的表达式前缀：

- `#!spel:`
- `#!groovy:`

适合：

- 请求对象补齐上下文参数
- 从上下文自动填用户、租户、组织等信息
- 简单规则表达式注入

---

### 5.5 Spring MVC 参数转换增强

项目提供了若干自动配置，用于增强 MVC 参数解析。

当前主要包括：

- 日期字符串转 `Date`
- 枚举字符串/数字转换
- JSON 字符串转 `JSONObject` / `JsonElement` / `Map`

如果你的项目接口层经常接收字符串日期、字符串枚举或 JSON 文本参数，这些自动配置可以省掉不少手写转换器。

---

## 6. RBAC 使用总览

RBAC 是这个项目最值得重点理解的模块。  
它不是简单的“用户-角色-权限”三层模型，而是把下面几件事合在了一起：

- 用户、角色、权限
- 多租户
- 组织树
- 数据范围
- 机密级别
- 方法级授权

### 6.1 RBAC 解决什么问题

它默认解决的是下面这一类业务：

1. 不同租户之间要隔离
2. 同一租户内部还要按组织树授权
3. 某些用户是全局管理用户
4. 即使是全局管理用户，也可能仍然受机密数据级别限制
5. 角色不仅带权限，也可以带数据范围
6. 用户本身也可以直接声明更细的数据范围

如果你的系统只有“用户拥有若干菜单权限”这么简单，其实不一定需要把整个 RBAC 模块都接满。  
但如果你已经遇到“跨租户管理 + 组织树 + 数据密级”的组合问题，这套默认模型会很有帮助。

---

## 7. RBAC 核心对象

### 7.1 用户

接口：`RbacUserInfo`

用户对象同时具备：

- 基本身份信息
- 租户归属
- 组织归属
- 角色列表
- 数据范围

也就是说，用户不只是“挂角色”，还可以直接声明自己的 `DataScope`。  
这也是为什么用户本身的数据范围优先于角色数据范围。

常见辅助方法：

- `isTopSuperAdmin()`
- `isSuperAdmin()`
- `isSaasAdmin()`
- `isTenantAdmin()`

其中顶级超管的判定规则是：

- 登录名为 `sa`
- 同时具备超级管理员角色

### 7.2 角色

接口：`RbacRoleInfo`

角色对象有两层不同语义：

1. 角色对象本身是一个机密对象
2. 角色还可以声明它授予用户的“可访问数据密级”

这两个概念不要混在一起。

换句话说：

- `ConfidentialObject.getConfidentialLevel()`
  是“这个角色对象本身有多敏感”

- `DataScope.getConfidentialDataAccessLevel()`
  是“拿到这个角色后，用户最多能看多高密级的数据”

这两者在建模上是不同字段，不应该互相代替。

### 7.3 权限

权限表达式采用：

`domain:type:res:action`

支持：

- `*` 通配
- `|` 或关系
- 角色前缀约定：`R_`

### 7.4 租户

接口：`RbacTenantInfo`

租户对象主要用于：

- 多租户隔离
- 作为组织、角色、用户的归属域
- 参与机密对象判断

### 7.5 组织

接口：`RbacOrgInfo`

组织对象默认被当成树节点使用。  
在当前 RBAC 默认实现中，组织访问控制是整个数据范围的核心部分。

---

## 8. RBAC 核心服务与职责

### 8.1 `RbacBaseUserService`

负责用户层面的基础语义：

- `loadUser(...)`
- `getUserConfidentialDataAccessLevel(...)`
- `canAccessConfidentialDataByUser(...)`
- `canAdminUser(...)`

### 8.2 `RbacBaseService`

这是整个 RBAC 数据装载和默认授权逻辑的核心入口。  
你通常需要自己实现它的抽象加载方法，然后复用它的大量默认方法。

你至少要实现：

- `loadAllTenantList(...)`
- `loadTenant(...)`
- `loadOrg(...)`
- `loadTenantOrgList(...)`
- `loadRole(...)`
- `loadTenantRoleList(...)`

强约束：

- `loadTenantOrgList(...)` 应返回只读对象

原因是默认树装配实现会复制节点，而不是直接修改原始组织对象。

### 8.3 `AbstractRbacAuthorizeService` / `RbacAuthorizeService`

这两个类负责把 RBAC 语义落到“方法授权”和“资源授权”上。

你可以把它们理解成：

- `RbacBaseService` 负责“算出你拥有什么”
- `RbacAuthorizeService` 负责“判断你能不能做这件事”

---

## 9. RBAC 最小接入方案

建议按下面步骤做：

### 第一步：准备领域模型

至少准备这几类对象，并实现对应接口：

- 用户：`RbacUserInfo`
- 角色：`RbacRoleInfo`
- 组织：`RbacOrgInfo`
- 租户：`RbacTenantInfo`

### 第二步：实现认证上下文

实现 `RbacAuthService`，保证系统随时能拿到当前操作用户。

### 第三步：实现 RBAC 数据装载

实现 `RbacBaseService` 的抽象加载方法。

最小示意：

```java
@Service
public class DemoRbacService implements RbacBaseService, RbacAuthService {

    @Override
    public Collection<MyTenant> loadAllTenantList(boolean onlyLoadEffectTenant) {
        return tenantRepository.findAll();
    }

    @Override
    public MyTenant loadTenant(Serializable tenantPrincipal) {
        return tenantRepository.findById(tenantPrincipal.toString()).orElse(null);
    }

    @Override
    public MyOrg loadOrg(Serializable orgPrincipal) {
        return orgRepository.findById(orgPrincipal.toString()).orElse(null);
    }

    @Override
    public Collection<MyOrg> loadTenantOrgList(Serializable tenantId, boolean onlyLoadEffectOrg) {
        return readonlyWrap(orgRepository.findByTenantId(tenantId));
    }

    @Override
    public MyRole loadRole(Serializable rolePrincipal) {
        return roleRepository.findByCode(rolePrincipal.toString()).orElse(null);
    }

    @Override
    public Collection<MyRole> loadTenantRoleList(Serializable tenantId, boolean onlyLoadEffectRole) {
        return roleRepository.findByTenantIdOrPublic(tenantId);
    }
}
```

### 第四步：给业务接口加授权注解

```java
@ResAuthorize(domain = "sys", type = "user", res = "*", action = "query")
@GetMapping("/users")
public List<UserDto> queryUser() {
    ...
}
```

### 第五步：接入组织、角色、租户可访问列表

你最常会直接用到的方法是：

- `loadUserAccessibleTenantList(...)`
- `loadUserAccessibleOrgList(...)`
- `loadUserOrgList(...)`
- `loadUserOwnerRoleList(...)`
- `loadUserAccessibleRoleList(...)`
- `loadUserRoleCodeList(...)`
- `loadUserPermissionExprList(...)`

---

## 10. 数据范围模型

### 10.1 `DataScope`

`DataScope` 同时描述两件事：

1. 组织范围
2. 机密数据访问级别

对于用户和角色来说，都可以声明 `DataScope`。

### 10.2 用户数据范围优先级

默认规则是：

1. 用户自己声明的组织范围优先
2. 只有用户未声明组织范围时，才回退到角色组织范围
3. 用户自己的数据密级优先
4. 用户未声明时，从角色中取最大数据密级

这也是当前默认实现中 `getUserDataScope(...)` 的核心语义。

### 10.3 性能注意

`getUserDataScope(...)` 相对较重。  
它会涉及用户、角色和范围合并，所以：

- 不要在循环里反复调用
- 在一条调用链里应尽量只取一次

同样，`getUserConfidentialDataAccessLevel(...)` 也不适合放在循环里频繁调用。

---

## 11. 组织范围 `OrgScope`

`OrgScope` 是 RBAC 模块中最核心、也最容易理解错的接口之一。

关键字段：

- `tenantExpression`
- `orgId`
- `isAllow`
- `expressionType`
- `orgScopeExpression`

它的含义可以拆成两步：

1. 这条规则先决定作用于哪些租户
2. 再在这些租户内，从某个组织起点开始匹配组织范围

### 11.1 `tenantExpression`

当前默认语义：

- `""`
  无租户

- `OrgScope.DEFAULT_TENANT`
  当前用户默认租户

- `OrgScope.ALL_TENANT`
  所有租户

- 其他表达式
  交给租户匹配逻辑

这里有一个非常重要的约定：

**无租户 = 公共组织**

也就是说：

- `loadTenantOrgList(null, ...)`
  加载的是公共组织

- `tenantExpression == ""`
  匹配的是公共组织

- 对没有租户归属的用户，`DEFAULT_TENANT` 也会落到公共组织

### 11.2 `orgId`

`orgId` 有三种常见取值：

- `OrgScope.ALL_ROOT_ORG`
  所有根组织

- `OrgScope.USER_ORG`
  用户所在组织

- 普通组织 ID
  表示指定组织节点

### 11.3 allow / deny

当前默认语义非常明确：

最终可访问组织 = allow 集合 - deny 集合

也就是说：

- 必须先命中 allow
- 同时不能命中 deny

并且有两个优先短路：

- 任何一条规则命中 `isDenyAllOrg()`，直接返回空结果
- 只要存在 `isAllowAllOrg()` 且没有任何 deny，直接返回全部候选组织

---

## 12. 组织匹配表达式

### 12.1 标准范围

`OrgScope.Scope` 支持：

- `OnlySelf`
- `OnlyDirectChild`
- `SelfAndDirectChild`
- `All`
- `Custom`

标准范围会优先走树结构快路径，不需要逐节点做复杂表达式匹配。

### 12.2 自定义表达式类型

当 `Scope = Custom` 时，支持：

- `IdAntPath`
- `NameAntPath`
- `Groovy`
- `SpringEL`

### 12.3 相对路径，不是绝对路径

`IdAntPath` 和 `NameAntPath` 都是相对路径。

路径起点不是整棵树根节点，而是：

`OrgScope.getOrgId()`

例如：

- scope 根组织 = `A`
- 目标组织链路 = `A -> A2 -> A21`

那么：

- 相对 ID 路径 = `/A2/A21/`
- 相对名称路径 = `/二级部门/三级部门/`

不是 `/A/A2/A21/`

### 12.4 Groovy / SpEL 上下文

当前自定义表达式默认可用上下文变量：

- `user`
- `org`
- `rootOrg`
- `scope`
- `relativeIdPath`
- `relativeNamePath`

因此你既可以写：

- `#user.loginName == 'admin'`
- `#relativeNamePath matches '.*/财务部/.*'`

也可以在 Groovy 中直接使用：

- `user`
- `org`

---

## 13. 组织列表与组织树

### 13.1 扁平列表

`loadUserAccessibleOrgList(...)` 返回的是扁平组织列表。  
它适合：

- 做是否可访问判断
- 做导出、批量处理
- 给查询条件下拉框提供选项

### 13.2 树形结果

如果你需要树结构，可以使用：

- `assembleOrgTree(orgList, rootIdList...)`
- `assembleOrgTree(orgList, buildNodePath, rootIdList...)`
- `loadUserOrgList(userPrincipal, true, rootIdList...)`

### 13.3 树装配的关键行为

当前默认实现有几条很重要的约束：

- 返回的是复制后的组织对象
- 不会直接修改原始组织对象
- 原始对象可以是只读代理对象
- 装树前会做代理脱壳
- 可以通过 `buildNodePath=false` 跳过 `nodePath` 构建

### 13.4 循环父链保护

如果组织节点在向上追溯父节点时出现了环，例如：

- A 的父节点是 B
- B 的父节点又回到了 A

当前默认实现会直接抛异常，而不是递归卡死。  
这是刻意设计的保护行为，目的是尽早暴露错误的组织数据。

---

## 14. 超管、SaaS 管理员与租户管理员

### 14.1 顶级超级管理员

`isTopSuperAdmin()` 的语义是：

- 可以无条件跳过所有范围判断
- 直接返回最大结果
- 不受数据密级限制

### 14.2 普通超级管理员 / SaaS 管理员

`isSuperAdmin()` 和 `isSaasAdmin()` 的语义是：

- 不再走 `DataScope`
- 可以直接使用最大候选结果
- 但仍然要经过对象自身机密级别过滤

这点非常关键。  
它们不是顶级超管，不能无条件越过数据密级约束。

### 14.3 租户管理员

`isTenantAdmin()` 不是全局豁免身份。  
默认仍然属于常规 RBAC 用户，需要参与租户、组织、角色和数据范围判断。

---

## 15. 角色列表的两种视角

这个模块里，角色列表有两种常见读取方式：

### 15.1 用户拥有的角色

方法：

- `loadUserOwnerRoleList(...)`

这是“用户实际拥有并生效的角色”，用于：

- 算权限表达式
- 算数据范围
- 算用户最终可访问的数据密级

### 15.2 当前可见的角色

方法：

- `loadUserAccessibleRoleList(...)`

这是“当前操作者有权查看到的角色对象”。  
它会额外经过角色对象自身的机密级别过滤。

### 15.3 不要把两者混用

如果你用“可见角色列表”反推用户真实权限，很容易导致：

- 权限丢失
- 逻辑递归
- 结果与实际授权不一致

正确做法是：

- 内部计算权限，使用 `loadUserOwnerRoleList(...)`
- 对外展示角色列表，使用 `loadUserAccessibleRoleList(...)`

---

## 16. 方法授权

项目提供了基于注解的方法授权能力。

主要入口：

- `@ResAuthorize`
- `AbstractRbacAuthorizeService`
- `RbacAuthorizeService`

常见流程可以理解为：

1. 解析当前用户
2. 顶级超管直接放行
3. 校验目标资源或目标动作要求的机密级别
4. 对超级管理员 / SaaS 管理员走快捷分支
5. 普通用户走权限表达式和组织范围判断

如果你要在控制器层做声明式授权，通常会从 `@ResAuthorize` 开始。

---

## 17. 性能设计建议

RBAC 默认实现已经做了一些性能优化，例如：

- `allow all` / `deny all` 优先短路
- 标准组织范围优先走树索引
- 自定义表达式做缓存
- SpEL / Groovy 编译缓存
- 树装配支持关闭 `nodePath` 构建

但在业务接入层，仍然建议注意下面几点：

### 17.1 避免在循环中重复计算

不要在循环中不断调用：

- `getUserDataScope(...)`
- `getUserConfidentialDataAccessLevel(...)`

它们都不是零成本方法。

### 17.2 组织尽量只在需要时装树

多数授权场景只需要扁平组织集合。  
真正需要展示树时再调用 `assembleOrgTree(...)`。

### 17.3 组织量大时优先在数据层预裁剪

如果你的租户数和组织数都很大，不要总是：

1. 先全量加载
2. 再在内存里做过滤

更好的做法是业务层尽量先按租户、状态、根节点做预裁剪。

### 17.4 `loadTenantOrgList(...)` 返回只读对象

这不是文档习惯，而是默认实现的设计约束。  
因为树装配会复制节点，不应该去修改源对象。

---

## 18. 常见接入误区

### 误区一：角色对象机密级别和角色授予的数据密级是同一个字段

不是。  
这两个语义必须分开。

### 误区二：`IdAntPath` / `NameAntPath` 是绝对路径

不是。  
它们是相对于 `OrgScope.getOrgId()` 的相对路径。

### 误区三：无租户和公共组织不是一回事

在当前默认实现里，它们是一回事。

### 误区四：SuperAdmin 和 TopSuperAdmin 一样

不是。  
只有 TopSuperAdmin 能无条件越过全部检查。

### 误区五：用户自己的数据范围和角色数据范围会自动合并

当前默认策略不是“简单合并优先并集”，而是：

- 用户自己的组织范围优先
- 用户没有组织范围时才回退角色

### 误区六：用户可见角色列表就是用户生效角色列表

不是。  
这两个列表的用途完全不同。

---

## 19. 推荐的落地实践

如果你准备把这套 RBAC 真正用到业务里，我建议这样落地：

1. 先把用户、角色、租户、组织四个基础对象建模清楚
2. 先明确“无租户是否等于公共组织”
3. 再明确角色对象机密级别和角色数据密级的区别
4. 优先把 `RbacBaseService` 的数据加载方法实现稳定
5. 先跑通用户组织可见性，再接方法授权
6. 最后再上 Groovy / SpEL 等自定义范围表达式

这样能避免一开始就把最复杂的表达式能力和最复杂的组织范围混在一起调试。

---

## 20. 手册索引

如果你是第一次接触这个项目，建议按下面顺序阅读：

1. 本文第 1 到 5 节，先了解项目全貌
2. 第 6 到 10 节，先理解 RBAC 的核心对象和服务职责
3. 第 11 到 15 节，理解 `OrgScope`、组织树和角色语义
4. 第 16 到 18 节，理解授权入口、性能注意事项和常见误区
5. 最后回到源码查看：
   [RbacBaseService.java](/Users/lilw/IdeaProjects/service-support/src/main/java/com/levin/commons/rbac/RbacBaseService.java)
   [RbacAuthorizeService.java](/Users/lilw/IdeaProjects/service-support/src/main/java/com/levin/commons/rbac/RbacAuthorizeService.java)
   [AbstractRbacAuthorizeService.java](/Users/lilw/IdeaProjects/service-support/src/main/java/com/levin/commons/rbac/AbstractRbacAuthorizeService.java)
   [OrgScope.java](/Users/lilw/IdeaProjects/service-support/src/main/java/com/levin/commons/rbac/OrgScope.java)
   [RbacUserInfo.java](/Users/lilw/IdeaProjects/service-support/src/main/java/com/levin/commons/rbac/RbacUserInfo.java)
   [RbacRoleInfo.java](/Users/lilw/IdeaProjects/service-support/src/main/java/com/levin/commons/rbac/RbacRoleInfo.java)
