# service-support 使用手册

本文基于当前源码、`README.md`、`使用说明.md`、模块 README 和测试用例整理，是 `service-support` 的主使用手册。

`service-support` 是一个面向 Spring / Spring Boot 生态的通用支撑库。它不是单一工具类集合，而是一组围绕中后台业务系统沉淀出来的基础能力，重点覆盖 RBAC、多租户、组织树、数据范围、代理注册、插件扩展、变量注入、MVC 参数转换和常用工具。

## 1. 项目定位

这个库适合下面这类项目：

- Spring 或 Spring Boot 中后台系统
- 存在用户、角色、权限、租户、组织树等基础模型
- 需要统一处理数据范围、机密级别、接口授权
- 需要把一些基础横切能力下沉为公共库
- 需要少量运行时扩展点，例如插件、代理客户端、变量注入

不建议一开始把全部模块一次性接入。更稳妥的方式是先接低耦合能力，例如工具类、字段常量生成、MVC 参数转换，再接代理、变量注入、插件和 RBAC。

## 2. 当前版本与环境

当前 `pom.xml` 中的坐标和构建配置：

```xml
<groupId>com.levin.commons</groupId>
<artifactId>service-support</artifactId>
<version>2.0.0-SNAPSHOT</version>
<maven.compiler.release>17</maven.compiler.release>
```

依赖管理使用 Spring Boot BOM：

```xml
<spring-boot.version>4.0.5</spring-boot.version>
```

### 2.1 JDK 建议

项目当前源码目标版本是 Java 17。

本地验证时建议使用 JDK 17 或 JDK 21。当前机器上使用 JDK 21 可以通过 `ObjectWrapperUtilsTest`。使用 JDK 25 时，Lombok/注解处理链路容易出现大量生成方法缺失类编译错误，建议不要用 JDK 25 作为日常构建 JDK。

示例：

```bash
JAVA_HOME=/Users/lilw/Library/Java/JavaVirtualMachines/corretto-21.0.5/Contents/Home \
  mvn clean package
```

项目默认 profile 会跳过测试。如果要运行测试，需要关闭默认跳过测试 profile：

```bash
JAVA_HOME=/Users/lilw/Library/Java/JavaVirtualMachines/corretto-21.0.5/Contents/Home \
  mvn clean -P '!01-跳过测试' test
```

定向运行某个测试：

```bash
JAVA_HOME=/Users/lilw/Library/Java/JavaVirtualMachines/corretto-21.0.5/Contents/Home \
  mvn clean -P '!01-跳过测试' -Dtest=ObjectWrapperUtilsTest test
```

### 2.2 provided 依赖

本项目大量依赖使用 `provided`，包括 Spring、Groovy、SpEL、Servlet、Validation、Swagger、Hutool、Fastjson、Gson、Redisson、JWT 等。

这意味着：

- 编译本库时需要这些依赖。
- 业务项目运行时要按实际使用的模块补齐依赖。
- 只使用工具类时，不一定要引入所有运行时依赖。
- 使用 RBAC、MVC 自动转换、变量注入、插件机制时，需要确保 Spring 相关依赖齐全。

## 3. 引入方式

### 3.1 使用当前 Maven 坐标

如果你发布到自己的 Maven 仓库，可以直接使用当前坐标：

```xml
<dependency>
    <groupId>com.levin.commons</groupId>
    <artifactId>service-support</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</dependency>
```

### 3.2 使用 JitPack

仓库中已有 JitPack 入口。按 JitPack 方式使用时，坐标通常是：

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://www.jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.Levin-Li</groupId>
    <artifactId>service-support</artifactId>
    <version>选择实际发布版本</version>
</dependency>
```

## 4. 自动配置

项目同时兼容旧版 `spring.factories` 和新版 `AutoConfiguration.imports`。

自动配置入口：

- `META-INF/spring.factories`
- `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`

当前自动配置类：

- `com.levin.commons.plugin.support.PluginConfiguration`
- `com.levin.commons.service.support.SpringContextHolder`
- `com.levin.commons.service.support.VariableResolverConfiguration`
- `com.levin.commons.service.support.DefaultSpringMvcDateFormatterConfiguration`
- `com.levin.commons.service.support.DefaultSpringMvcEnumFormatterConfiguration`
- `com.levin.commons.service.support.DefaultSpringMvcJsonDeserializerConfiguration`

这些配置类配合项目内的条件注解使用。业务项目不想启用某项自动配置时，可以按类名配置为 `disable`：

```properties
com.levin.commons.plugin.support.PluginConfiguration=disable
com.levin.commons.service.support.VariableResolverConfiguration=disable
com.levin.commons.service.support.DefaultSpringMvcDateFormatterConfiguration=disable
com.levin.commons.service.support.DefaultSpringMvcEnumFormatterConfiguration=disable
com.levin.commons.service.support.DefaultSpringMvcJsonDeserializerConfiguration=disable
```

## 5. 包结构速览

主要包结构：

- `com.levin.commons.annotation`
  通用注解，例如 `@GenNameConstant`、`@EnvValue`。

- `com.levin.commons.conditional`
  条件装配支持，主要服务于自动配置开关。

- `com.levin.commons.dao.domain`
  基础领域接口，例如 `EntityObject`、`TreeObject`、`MultiTenantObject`、`ConfidentialObject`、`ProxyWrapperObject`。

- `com.levin.commons.processor`
  编译期注解处理器，生成 `E_` 名称常量类。

- `com.levin.commons.service.proxy`
  注解扫描和代理 Bean 注册。

- `com.levin.commons.plugin`
  插件接口、插件管理器和资源加载扩展。

- `com.levin.commons.service.support`
  变量解析、字段注入、MVC 转换器、上下文、缓存、事件、异步任务等支撑能力。

- `com.levin.commons.rbac`
  RBAC、多租户、组织树、数据范围、机密级别、授权判断。

- `com.levin.commons.ui`
  UI 注解模型和 AMIS 辅助能力。

- `com.levin.commons.utils`
  签名、JWT、表达式、脱敏、对象包装、路径匹配、锁、异常、Bean 复制等工具。

## 6. 推荐接入顺序

推荐顺序如下：

1. 构建项目，确认 JDK 和 Maven 环境可用。
2. 引入基础工具类和领域接口。
3. 接入字段名/方法名常量生成。
4. 按需启用 Spring MVC 参数转换。
5. 接入变量注入。
6. 接入代理 Bean 注册。
7. 接入插件机制。
8. 最后接入 RBAC。

RBAC 是本库最重的模块，建议在业务模型、租户语义、组织语义明确后再接。

## 7. 编译期名称常量生成

处理器：

- `JpaEntityClassProcessor`
- `GenNameConstantProcessor`

注册文件：

- `src/main/processor/META-INF/services/javax.annotation.processing.Processor`

适合解决的问题：

- 字段名、方法名、表名字符串硬编码
- 查询条件、表达式、导出字段等场景需要稳定常量
- 注解、接口或领域对象需要生成 `E_` 前缀常量类

典型触发方式：

- JPA `@Entity`
- JPA `@MappedSuperclass`
- 项目注解 `@GenNameConstant`

注意事项：

- 生成逻辑依赖编译期注解处理。
- IDE 里要打开 annotation processing。
- 如果编译阶段出现生成类、`Fields`、getter/setter 不存在等错误，先检查 JDK 版本和 Lombok 注解处理。

## 8. 代理 Bean 自动注册

核心注解：

- `@ProxyBeanScan`
- `@ProxyBeanScans`
- `@EnableProxyBean`

核心类：

- `ProxyBeanScanAndRegistrar`
- `ProxyFactoryBean`

### 8.1 使用场景

适合用在“接口或类被某个注解标记，然后统一注册为代理 Bean”的场景，例如：

- API client
- RPC client
- SDK facade
- 统一拦截的服务接口
- 注解驱动的本地代理

### 8.2 代理处理器类型

`@ProxyBeanScan.invocationHandlerClass` 支持三类处理器：

- `java.lang.reflect.InvocationHandler`
- `org.aopalliance.intercept.MethodInterceptor`
- `org.springframework.cglib.proxy.MethodInterceptor`

### 8.3 示例

定义标记注解：

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiClient {
}
```

扫描并注册：

```java
@Configuration
@ProxyBeanScan(
        scanType = ApiClient.class,
        invocationHandlerClass = MyInvocationHandler.class,
        basePackages = "com.demo.client"
)
@EnableProxyBean(registerTypes = ApiClient.class)
public class ProxyClientConfiguration {
}
```

要点：

- `onlyScan=true` 只扫描不注册 Bean。
- `lazyInit=true` 默认延迟初始化。
- `basePackageClasses` 比字符串包名更安全。
- `@EnableProxyBean.registerTypes` 为空时默认启用所有已扫描类型。

## 9. 插件机制

核心接口：

- `Plugin`
- `PluginManager`
- `PluginConfigurer`
- `PluginManagerAware`
- `ResLoader`

默认实现：

- `PluginManagerImpl`
- `PluginConfiguration`

### 9.1 插件对象

插件需要实现 `Plugin`，关键方法包括：

- `getPackageName()`：插件包名，要求全局唯一。
- `getVersion()`：插件版本。
- `getAuthor()`：作者信息。
- `getType()`：插件类型，可选。
- `getMenuList()`：插件菜单，可选。
- `getResLoader()`：资源加载器，可选。
- `onEvent(Object... events)`：接收插件事件。
- `destroy()`：卸载或销毁时释放资源。

### 9.2 插件管理器

`PluginManager` 提供：

- `installPlugin(plugin, isOverrideExists)`
- `uninstallPlugin(pluginId)`
- `getInstalledPlugin(pluginId)`
- `getInstalledPlugins()`
- `sendEvent(pluginId, events...)`

启动后，`PluginConfiguration` 会收集容器里的 `Plugin` Bean 并安装到默认 `PluginManager`。

### 9.3 使用建议

插件机制适合作为业务扩展点，不适合替代 Spring Bean 生命周期本身。

建议插件只暴露：

- 菜单
- 资源加载器
- 事件入口
- 小范围扩展能力

复杂服务仍建议作为普通 Spring Bean 管理，再由插件对象引用。

## 10. 变量解析与字段注入

核心注解：

- `@InjectVar`

核心接口/实现：

- `VariableResolver`
- `VariableResolverManager`
- `DefaultVariableResolverManager`
- `VariableResolverConfigurer`
- `VariableInjector`
- `SimpleVariableInjector`
- `HttpRequestInfoResolver`

### 10.1 `@InjectVar` 的作用

`@InjectVar` 把上下文中的变量、SpEL 表达式或 Groovy 表达式结果注入到对象字段中。

示例：

```java
public class DemoRequest {

    @InjectVar("userId")
    private String userId;

    @InjectVar(value = "#!spel:#root['tenantId']", isRequired = "true")
    private String tenantId;
}
```

### 10.2 表达式前缀

支持：

- `#!spel:`：Spring Expression Language
- `#!groovy:`：Groovy

### 10.3 常用字段

常量集中在 `InjectConst`，例如：

- `user`
- `userId`
- `userName`
- `tenant`
- `tenantId`
- `org`
- `orgId`
- `orgIdList`
- `userRoleList`
- `userPermissionList`
- `confidentialDataAccessLevel`
- `isTopSuperAdmin`
- `isSuperAdmin`
- `isSaasAdmin`
- `isTenantAdmin`

### 10.4 覆盖与必填

`@InjectVar` 里两个配置很重要：

- `isOverride`：是否覆盖已有字段值，支持布尔文本、SpEL、Groovy。
- `isRequired`：变量是否必须存在且不为 `null`。

如果变量找不到且 `isRequired` 为 true，注入器应抛出异常。

## 11. Spring MVC 参数转换增强

自动配置类：

- `DefaultSpringMvcDateFormatterConfiguration`
- `DefaultSpringMvcEnumFormatterConfiguration`
- `DefaultSpringMvcJsonDeserializerConfiguration`

提供能力：

- 字符串转 `Date`
- 字符串/数字转枚举，尤其是实现 `EnumDesc` 的枚举
- JSON 字符串转 `fastjson2 JSONObject`
- JSON 字符串转 `fastjson JSONObject`
- JSON 字符串转 `gson JsonElement`
- JSON 字符串转 `Map`
- 数组 JSON 转基础数组

这类能力适合 Controller 层参数适配，不建议把复杂业务解析逻辑塞进转换器。

## 12. 对象代理与只读包装

核心类：

- `ObjectWrapperUtils`
- `ProxyWrapperObject`
- `MethodOverrideHandler`

### 12.1 普通代理

`wrapperByProxy(...)` 使用 CGLIB 创建代理对象。存在原始对象时，方法默认转调原始对象；不存在原始对象时，调用代理父类方法。

示例：

```java
DemoBean proxy = ObjectWrapperUtils.wrapperByProxy(original, methodOverrideHandler);
```

代理对象会实现 `ProxyWrapperObject`，可读取：

- `proxyTargetClass()`
- `getOriginalObject()`

### 12.2 只读代理

`wrapper2Readonly(...)` 用于把对象包装为只读代理。

示例：

```java
DemoBean readonly = ObjectWrapperUtils.wrapper2Readonly(original);
```

行为：

- 原始对象为 `null` 时返回 `null`。
- getter 默认读取原始对象的实时状态。
- JavaBean setter 会抛出 `UnsupportedOperationException`。
- `setXxx(...)` 风格方法也会抛出 `UnsupportedOperationException`。
- 可安全识别的 `List`、`Set`、`Map`、`SortedMap`、`NavigableMap` 等读方法返回只读视图。
- 对未能安全解析泛型或返回具体集合实现的读方法，保持原返回值，避免破坏方法契约。

动态只读示例：

```java
DemoBean proxy = ObjectWrapperUtils.wrapper2Readonly(original, () -> isReadonlyMode());
```

### 12.3 只读包装与深拷贝的取舍

只读包装适合“防止调用方通过返回对象修改数据”的场景，成本通常低于深拷贝，并保持 live view。

深拷贝适合“后续流程必须修改对象，但不能污染源对象”的场景，例如组织树装配时需要重置 `children` 或回填 `nodePath`。

RBAC 的组织树默认实现会复制组织节点，避免污染输入对象；`ObjectWrapperUtils` 的职责仍然是只读包装和代理，不承担通用深拷贝。

## 13. 常用工具

### 13.1 签名

相关类：

- `SignUtils`
- `@Sign`
- `SignReq`
- `DefaultSignatureReq`

适合请求签名、字段签名、验签。

### 13.2 数据脱敏

相关类：

- `@DataMasking`
- `DataMasker`
- `DefaultDataMasker`
- `AccountDataMasker`
- `DataMaskingUtils`

适合统一处理账号、手机号、邮箱等敏感字段展示。

### 13.3 分布式锁

相关类：

- `RedissonLockUtils`
- `Locker`

适合基于 Redisson `RLock` 包装临界区执行。

### 13.4 表达式与路径匹配

相关类：

- `ExpressionUtils`
- `PathPatternUtils`
- `SimpleTypeParser`

RBAC 的组织路径、租户匹配和注入表达式会间接用到这些能力。

### 13.5 事件与异步处理

相关类：

- `EventBus`
- `SimpleEventBus`
- `AsyncHandler`
- `AbstractDistributionJob`

适合轻量级本地事件分发和后台队列处理。复杂消息系统仍建议使用专业 MQ。

## 14. UI 与 AMIS 辅助

相关包：

- `com.levin.commons.ui.annotation`
- `com.levin.commons.ui.model`
- `com.levin.commons.ui.utils`
- `com.levin.commons.amis`

主要能力：

- 用 `@CRUD`、`@Form`、`@FormItem`、`@Options` 等注解描述 UI 元数据。
- `ModelUtils` 将注解转换为模型对象。
- `SchemaCodeGen` 基于 AMIS schema 模板生成注解或枚举。

这一块更适合内部平台、低代码、表单/列表元数据生成场景。

## 15. RBAC 总览

RBAC 是本库最核心、也最复杂的模块。

它不是简单的“用户-角色-权限”三层模型，而是把下面几件事组合在一起：

- 资源权限
- 用户角色
- 多租户
- 组织树
- 数据范围
- 机密数据级别
- 方法授权
- 菜单和资源扫描

如果你的系统只需要简单菜单权限，可以只使用其中的资源表达式和角色权限。若系统存在多租户、组织树和数据密级，才建议完整接入 RBAC。

## 16. RBAC 核心对象

### 16.1 用户 `RbacUserInfo`

用户对象通常包含：

- 用户 ID
- 登录名
- 用户类型
- 租户 ID
- 组织 ID
- 角色编码列表
- 用户自己的 `DataScope`

常见身份判断：

- `isPlatformUser()`：无所属租户的平台用户。
- `isTenantUser()`：具有具体租户 ID 的租户用户。
- `isTopSuperAdmin()`
- `isSuperAdmin()`
- `isSaasAdmin()`
- `isTenantAdmin()`

`isSaasUser()` 已废弃，保留为 `isPlatformUser()` 的兼容别名；新代码应使用更准确的平台/租户用户名称。

顶级超管的默认语义较强：可以跳过大多数范围判断。普通超级管理员和 SaaS 管理员不等同于顶级超管。

### 16.2 角色 `RbacRoleInfo`

角色对象同时承担两层语义：

- 角色对象自身是一个可被机密级别过滤的对象。
- 角色可以授予用户数据范围和机密数据访问级别。

不要把 `ConfidentialObject.getConfidentialLevel()` 和 `DataScope.getConfidentialDataAccessLevel()` 混在一起。

角色还支持：

- 权限表达式列表
- 互斥角色表达式
- 共存角色表达式
- 分配前置条件

`RbacAuthorizeService` 提供：

- `findExclusiveRolePair(...)`
- `findMissingCoexistRolePair(...)`
- `checkRoleAssignment(...)`

### 16.3 租户 `RbacTenantInfo`

租户对象用于：

- 多租户隔离
- 组织归属
- 角色归属
- 用户归属
- 机密级别过滤

### 16.4 组织 `RbacOrgInfo`

组织对象继承多租户对象和树对象语义。它是数据范围计算的核心。

组织树装配默认会复制节点，不修改源对象。

### 16.5 资源 `Res`

资源描述通常包含：

- `domain`
- `type`
- `id`
- `icon`
- `actionList`
- `alwaysShow`

资源可以来自注解扫描，也可以来自业务表或插件。

### 16.6 权限 `Permission`

权限表达式由四段组成：

```text
domain:type:res:action
```

支持：

- `*` 通配
- `|` 或关系
- 角色前缀约定 `R_`

### 16.7 重点：空资源 ID（`::`）的权限匹配规则

> **资源 ID 为空不等于“任意资源 ID”。** 当请求权限的第三段为空时，必须由拥有权限中的通配模式显式匹配；具体资源 ID 不会被放宽匹配。

权限检查按 `domain:type:res:action` 四段逐段进行。`res` 为空时会形成连续的 `::`，例如：

```text
com.levin.oak.base:系统数据-角色::查询列表
```

此规则仅适用于权限表达式的分段匹配。用户类型、角色等通用文本匹配仍然遵循“待匹配值为空即不匹配”的安全规则。

| 拥有权限表达式 | 与上例请求是否匹配 | 原因 |
| --- | --- | --- |
| `*` | 是 | 单独的 `*` 会覆盖权限表达式的全部分段。 |
| `*:系统数据-*:*:*` | 是 | 第三段 `*` 可以匹配空资源 ID。 |
| `com.levin.oak.base:系统数据-角色:*` | 是 | 末尾 `*` 会复用于后续省略的分段，因此同时覆盖空资源 ID 和操作。 |
| `*:系统数据-*:role-42:查询列表` | 否 | `role-42` 是具体资源 ID，不能匹配空值。 |
| `*:系统数据-*:role-42|*:查询列表` | 是 | 第三段的备选表达式包含 `*`。 |
| `*:系统数据-*:role-42|role-43:查询列表` | 否 | 第三段没有可匹配空值的通配模式。 |

因此，若某个操作不绑定具体资源 ID、需要覆盖该资源类型下的所有资源 ID，应在拥有权限的 `res` 段明确写 `*`；不要把该段留空来表示通配。该语义由 `AbstractRbacAuthorizeService.simpleMatch(...)` 保证，并由 RBAC 回归测试覆盖。

## 17. 方法授权

核心注解：

- `@ResAuthorize`
- `@ResConditionAction`

核心服务：

- `RbacAuthorizeService`
- `RbacBaseAuthorizeService`
- `AbstractRbacAuthorizeService`

示例：

```java
@ResAuthorize(domain = "sys", type = "user", res = "*", action = "query")
@GetMapping("/users")
public List<UserDto> queryUsers() {
    return userService.queryUsers();
}
```

`@ResAuthorize` 支持：

- `ignored`：忽略授权检查。
- `onlyRequireAuthenticated`：只要求认证。
- `anyUserTypes`：任意用户类型满足即可。
- `confidentialLevel`：要求用户可访问密级大于等于该值；默认 `ConfidentialLevel.PLATFORM_PUBLIC_CODE`，不限制访问者的机密数据访问级别。
- `isAndMode`：角色、权限、表达式是否都必须满足。
- `anyRoles`：任意角色满足即可。
- `verifyExpression`：SpEL 校验表达式。

类和方法都可以标注。方法级配置可覆盖类级配置。

`PLATFORM_PUBLIC` 只跳过机密数据访问级别比较，不跳过资源权限、角色、用户类型或认证判断；需要完全跳过授权时仍应明确使用 `ignored = true`。

### 17.1 权限验证整体流程

授权服务有两类入口，先区分入口再理解结果：

1. **资源/方法操作入口**：`isAuthorized(principal, domain, type, res, action)`，或传入 `@ResAuthorize`。注解会先转换成 `ResConditionAction`，然后按操作配置执行完整验证链。
2. **纯权限表达式入口**：`isAuthorized(principal, requirePermissionList, ...)`。用于一次检查一条或多条权限/角色表达式，支持“全部满足”或“任一满足”。空的需求列表会直接通过。

资源/方法操作入口的实际顺序如下：

1. 解析 `principal` 为 `RbacUserInfo`；用户不存在即拒绝。
2. TopSuperAdmin 直接通过。
3. `ignored` 或 `onlyRequireAuthenticated` 直接通过后续资源条件；公共入口仍要求能够解析出已认证用户。
4. 检查 `confidentialLevel`。`PLATFORM_PUBLIC` 不限制机密数据访问级别；其他级别要求用户访问级别大于等于要求值。
5. 普通 SuperAdmin 在通过机密级别检查后直接通过；SaaSAdmin 和 TenantAdmin 不享有这一通用短路。
6. 若配置了 `anyUserTypes`，用户类型必须命中其中任一表达式；该条件始终是前置门槛，不受 `isAndMode` 影响。
7. 组装三类可选业务条件：
   - **权限条件**：将 `domain:type:res:action` 组装为权限表达式，并在用户拥有的权限中匹配。
   - **角色条件**：`anyRoles` 中任一角色表达式匹配用户拥有的角色即可。
   - **表达式条件**：执行 `verifyExpression`（SpEL），上下文包含 `user`、`action`、`resPrefix`、`userType`、`ownerRoleList` 和 `ownerPermissionList`，以及调用方提供的授权上下文。
8. 对第 7 步实际存在的条件进行组合：`isAndMode = false` 时任一条件为真即可；`isAndMode = true` 时全部条件必须为真。

> **配置注意：** 用户类型和机密级别是前置门槛，不参与第 8 步的 AND/OR 组合。若权限、角色和表达式三类条件都未配置，默认 OR 模式会拒绝（没有命中的条件）；AND 模式会通过空条件集合。因此业务操作应明确配置至少一种业务授权条件，或明确标注 `ignored` / `onlyRequireAuthenticated`。

纯权限表达式入口会先去除空表达式，并对每一项按下面顺序处理：TopSuperAdmin 通过；用户角色或权限列表直接命中则通过；角色表达式未直接命中时按精确角色处理；其余表达式若对应已登记资源操作，则加载该操作并执行上述完整条件链；找不到操作时拒绝并通过 `matchErrorConsumer` 报告原因。多条需求由 `isRequireAllPermission` 决定使用全量匹配还是任一匹配。

权限表达式的 `*`、`|`、空资源 ID（`::`）、单独 `*` 和末尾 `*` 的匹配规则见 [16.7 节](#167-重点空资源-id的权限匹配规则)。

## 18. RBAC 最小接入流程

### 第一步：实现领域模型

至少实现：

- `RbacUserInfo`
- `RbacRoleInfo`
- `RbacTenantInfo`
- `RbacOrgInfo`

如果只做简单权限，可先让组织和租户字段返回空值，但要清楚这意味着你没有启用完整数据范围语义。

### 第二步：实现用户服务

实现 `RbacBaseUserService` 的核心方法：

- `encryptUserPwd(...)`
- `loadUser(tenantId, account)`
- `loadUser(userPrincipal)`
- `auditUser(...)`
- `auditUserLogin(...)`

### 第三步：实现数据加载服务

实现 `RbacBaseService` 的核心加载方法：

- `loadAllTenantList(...)`
- `loadTenant(...)`
- `loadOrg(...)`
- `loadTenantOrgList(...)`
- `loadRole(...)`
- `loadTenantRoleList(...)`

最小示例：

```java
@Service
public class DemoRbacService implements RbacBaseService {

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
        return orgRepository.findByTenantId(tenantId).stream()
                .map(ObjectWrapperUtils::wrapper2Readonly)
                .toList();
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

`loadTenantOrgList(...)` 建议返回只读对象。默认组织树装配会复制节点，不应污染源组织对象。

### 第四步：实现授权服务

如果需要直接使用默认授权逻辑，可以继承或组合 `AbstractRbacAuthorizeService`，并保证 `getRbacBaseLoadService()` 能返回你的 `RbacBaseService` 实现。

### 第五步：在接口上声明授权

在 Controller 或 Service 方法上加 `@ResAuthorize`，再在拦截器、AOP 或框架入口调用 `RbacAuthorizeService` 做校验。

## 19. 数据范围 `DataScope`

`DataScope` 描述两个维度：

- 组织范围：`getOrgScopeList()`
- 机密数据访问级别：`getConfidentialDataAccessLevel()`

用户和角色都可以携带 `DataScope`。

默认合并规则：

- 用户自己声明组织范围时，优先使用用户范围。
- 用户未声明组织范围时，回退到角色范围。
- 用户自己声明机密数据访问级别时，优先使用用户值。
- 用户未声明时，从角色中取最大可访问密级。

性能注意：

- `getUserDataScope(...)` 不是零成本方法。
- 不要在循环中反复调用。
- 高频场景建议在业务层做调用链级缓存或 SQL 预聚合。

## 20. 组织范围 `OrgScope`

`OrgScope` 是 RBAC 数据范围的核心。

关键字段：

- `tenantMatchingExpression`
- `orgId`
- `isAllow`
- `orgScopeExpressionType`
- `orgScopeExpression`

计算可以理解为两步：

1. 先匹配租户。
2. 再在租户内匹配组织。

### 20.1 租户表达式

`tenantMatchingExpression` 常见取值：

- 空字符串：无租户，也就是公共组织。
- `OrgScope.DEFAULT_TENANT`：用户默认租户；无租户用户会落到公共组织。
- `OrgScope.ALL_TENANT`：所有租户。
- 普通文本：租户 ID 精确匹配。
- Spring PathPattern：例如 `tenant-*` 或 `/tenant-*`。
- Groovy：必须使用 `#!groovy:` 前缀。

租户 Groovy 上下文：

- `_tenant`
- `_user`
- `_scope`

普通租户用户默认只能应用自己租户内的规则。跨租户范围主要服务平台用户或 SaaS 管理员场景。

### 20.2 组织起点

`orgId` 常见取值：

- `OrgScope.ALL_ROOT_ORG`：所有根组织作为起点。
- `OrgScope.USER_ORG`：用户所在组织作为起点。
- 普通组织 ID：指定组织作为起点。

`ALL_ROOT_ORG` 是否表示所有根节点还是所有组织，取决于匹配模式：

- `ALL_ROOT_ORG + OnlySelf`：所有根组织。
- `ALL_ROOT_ORG + All`：所有组织。

### 20.3 allow / deny

默认结果：

```text
最终可访问组织 = allow 命中集合 - deny 命中集合
```

特殊短路：

- 命中 `isDenyAllOrg()`，直接返回空。
- 存在 `isAllowAllOrg()` 且没有 deny，直接返回全部候选组织。

### 20.4 标准匹配模式

`ScopeMatchingPattern` 支持：

- `OnlySelf`
- `OnlyDirectChild`
- `SelfAndDirectChild`
- `All`
- `Custom`

标准模式会走树结构快路径。只有 `Custom` 才进入自定义表达式。

### 20.5 自定义表达式

`ExpressionType` 支持：

- `IdPath`
- `NamePath`
- `Groovy`
- `SpringEL`

`IdPath` 和 `NamePath` 是相对路径，不是整棵树绝对路径。

例如组织链路是：

```text
A -> A2 -> A21
```

如果范围起点是 `A`，目标是 `A21`：

```text
相对 ID 路径: /A2/A21/
```

不是：

```text
/A/A2/A21/
```

自定义表达式上下文通常包含：

- `_user`
- `_org`
- `_rootOrg`
- `_scope`
- `_relativeIdPath`
- `_relativeNamePath`

## 21. 组织列表与组织树

常用方法：

- `loadUserAccessibleOrgList(...)`
- `loadUserOrgList(userPrincipal, assembleTree, rootIdList...)`
- `assembleOrgTree(orgList, rootIdList...)`
- `assembleOrgTree(orgList, buildNodePath, rootIdList...)`

### 21.1 扁平列表

`loadUserAccessibleOrgList(...)` 返回扁平列表，适合：

- 查询条件
- 批量校验
- 导出
- 是否可访问判断

### 21.2 树形列表

`assembleOrgTree(...)` 会把扁平组织列表组装为树。

关键行为：

- 返回复制后的组织节点。
- 不直接修改源对象。
- 支持源对象是 `ObjectWrapperUtils.wrapper2Readonly(...)` 返回的只读代理。
- 会在必要时对代理脱壳。
- 可以通过 `buildNodePath=false` 跳过 `nodePath` 构建。
- 发现父链循环时会抛异常，避免递归死循环。

### 21.3 性能建议

组织量大时，不要总是全量加载后内存过滤。

优先在数据层按下面条件预裁剪：

- 租户
- 启用状态
- 根节点
- 父节点
- 业务状态

业务实现明确知道组织对象类型时，建议覆盖 `copyOrgNodeForAssembleTree(...)`，用构造器或 mapper 复制必要字段，减少反射成本。

## 22. 角色与权限列表

角色列表有两个视角：

### 22.1 用户拥有的角色

方法：

- `loadUserOwnerRoleList(...)`

用途：

- 计算用户真实权限。
- 计算数据范围。
- 计算机密数据访问级别。

### 22.2 用户可见的角色

方法：

- `loadUserAccessibleRoleList(...)`

用途：

- UI 展示。
- 当前用户能看到哪些角色对象。

不要用“可见角色列表”反推用户真实权限。权限计算应使用“拥有角色列表”。

## 23. 超管语义

### 23.1 TopSuperAdmin

`isTopSuperAdmin()` 语义最强：

- 可跳过组织范围。
- 可跳过租户范围。
- 可跳过机密级别约束。
- 直接返回最大候选结果。

### 23.2 SuperAdmin / SaaSAdmin

`isSuperAdmin()` 和 `isSaasAdmin()` 可以走更宽的范围分支，但仍会经过对象自身机密级别过滤。

它们不是 `TopSuperAdmin`。

### 23.3 TenantAdmin

`isTenantAdmin()` 默认不是全局豁免身份。通常仍要参与租户、组织、角色和数据范围判断。

## 24. 资源扫描与菜单

相关类：

- `RbacUtils`
- `MenuItem`
- `SimpleMenu`
- `SimpleRes`
- `SimpleTreeRes`
- `ResPermission`

`RbacUtils` 可根据 Controller 上的 `@ResAuthorize` 和 Swagger `@Operation` 信息构造资源、菜单和权限描述。

建议：

- 权限表达式作为后端真实判断依据。
- 菜单仅作为 UI 展示结构。
- 菜单依赖权限，但不要把菜单当权限源。

## 25. RBAC 常见误区

### 25.1 把角色对象密级和角色授予的数据密级混为一谈

角色对象密级控制“谁能看到这个角色对象”。角色授予的数据密级控制“拥有这个角色后最多能访问多高密级数据”。

### 25.2 把 `IdPath` / `NamePath` 当绝对路径

它们是相对于 `OrgScope.getOrgId()` 的路径。

### 25.3 把无租户和公共组织拆开理解

当前默认实现里，无租户组织就是公共组织。

### 25.4 认为 SuperAdmin 等于 TopSuperAdmin

只有 TopSuperAdmin 能无条件跳过全部检查。

### 25.5 在循环中重复计算数据范围

`getUserDataScope(...)`、`getUserConfidentialDataAccessLevel(...)` 应尽量在调用链里取一次后复用。

### 25.6 让树装配污染源对象

默认树装配会复制节点。业务覆盖时也必须保证返回新对象，不能直接返回源对象。

## 26. 测试与验证

项目测试覆盖了以下重点：

- RBAC 权限、角色、组织范围和组织树
- 代理 Bean 扫描注册
- 变量解析
- Spring Cache 解析
- 事件总线
- 工具类
- 只读对象包装
- UI 模型转换

当前已验证过的命令：

```bash
JAVA_HOME=/Users/lilw/Library/Java/JavaVirtualMachines/corretto-21.0.5/Contents/Home \
  mvn -q clean -P '!01-跳过测试' -Dtest=ObjectWrapperUtilsTest test
```

`ObjectWrapperUtilsTest` 结果：

```text
tests=17, errors=0, failures=0, skipped=0
```

完整测试前建议：

- 使用 JDK 17 或 21。
- 先执行 `mvn clean`，避免旧的 annotation processor 服务文件留在 `target/classes`。
- 确认 IDE 和 Maven 的 JDK 一致。

## 27. 故障排查

### Q1：大量 `getXxx`、`setXxx`、`Fields` 或 `log` 找不到？

优先检查：

- 是否使用了过高 JDK，例如 JDK 25。
- Lombok 注解处理是否开启。
- Maven 和 IDE 使用的 JDK 是否一致。
- 是否需要 `clean` 清理旧的 `target`。

### Q2：编译时提示 `Processor not found`？

先执行：

```bash
mvn clean
```

项目会在打包阶段复制 processor 服务文件。如果旧服务文件已经在 `target/classes`，下一次增量编译可能提前尝试加载尚未编译完成的处理器。

### Q3：运行时报 `ClassNotFoundException`？

多数情况下是业务项目没有提供本库中 `provided` 依赖。按实际使用模块补齐依赖即可。

### Q4：自动配置不想启用？

使用第 4 节中的 `类全名=disable` 配置关闭对应自动配置。

### Q5：只读对象还能被原对象修改吗？

可以。`wrapper2Readonly(...)` 是 live view，只阻止通过代理对象执行 `setXxx(...)` 或修改可识别集合视图。原始对象自己仍然可变。

如果你需要快照隔离，应在业务层显式复制对象。

## 28. 推荐落地路线

一个实际项目可以按下面节奏接入：

1. 统一 JDK 和构建环境。
2. 引入依赖，只启用需要的自动配置。
3. 先使用工具类、领域接口、名称常量生成。
4. 接入 MVC 参数转换，降低 Controller 参数适配代码。
5. 接入变量注入，统一上下文变量写入请求对象。
6. 如有接口代理需求，再接 `@ProxyBeanScan`。
7. 如有模块扩展需求，再接插件机制。
8. 最后接 RBAC，先跑通用户、角色、租户、组织基础模型。
9. 先实现扁平组织可访问列表，再做组织树。
10. 最后启用方法授权、菜单扫描、自定义组织范围表达式。

## 29. 重点源码入口

建议第一次阅读源码时从这些文件开始：

- `src/main/java/com/levin/commons/rbac/RbacBaseService.java`
- `src/main/java/com/levin/commons/rbac/RbacAuthorizeService.java`
- `src/main/java/com/levin/commons/rbac/AbstractRbacAuthorizeService.java`
- `src/main/java/com/levin/commons/rbac/OrgScope.java`
- `src/main/java/com/levin/commons/rbac/DataScope.java`
- `src/main/java/com/levin/commons/rbac/RbacUserInfo.java`
- `src/main/java/com/levin/commons/rbac/RbacRoleInfo.java`
- `src/main/java/com/levin/commons/utils/ObjectWrapperUtils.java`
- `src/main/java/com/levin/commons/service/proxy/ProxyBeanScan.java`
- `src/main/java/com/levin/commons/service/domain/InjectVar.java`
- `src/main/java/com/levin/commons/plugin/Plugin.java`
- `src/main/java/com/levin/commons/plugin/PluginManager.java`
