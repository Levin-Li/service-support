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
<version>2.2.0-SNAPSHOT</version>
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
    <version>2.2.0-SNAPSHOT</version>
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

变量注入场景使用 `InjectConst.IS_PLATFORM_USER`（`isPlatformUser`）和 `InjectConst.IS_TENANT_USER`（`isTenantUser`）；`InjectConst.IS_SAAS_USER` 已废弃但保留原键值以兼容已有表达式。

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

领域目录使用 `RbacDomainInfo`，与 `RbacTenantInfo` 一样继承 `RbacCoreObject`，具有 ID、有效状态及可选机密级别。它表示业务领域或应用，本身不绑定租户；业务数据的 `DomainObject.getDomainId()` 对应该目录 ID。

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

### 17.2 资源权限、数据范围与机密等级的关系

三者是独立的授权门槛，不是一个方法自动完成的单一判断：

```text
请求一个带租户/组织目标的操作
  ├─ 资源动作授权：isAuthorized(..., ResConditionAction)
  │    ├─ 认证、TopSuperAdmin、ignored / onlyRequireAuthenticated
  │    ├─ 动作要求的 confidentialLevel
  │    └─ 用户类型 + 角色 + 权限表达式 + SpEL 条件
  └─ 数据目标授权：checkOrgAccessible(user, tenantId, parentId, orgId)
       ├─ 目标对象及所属租户的领域门槛（空领域跳过自身检查）
       ├─ 租户与组织非空领域一致性
       ├─ 租户边界
       ├─ DataScope 中的租户允许/拒绝集合
       ├─ 组织允许匹配集合减去拒绝匹配集合
       └─ SuperAdmin / SaaSAdmin 分支对目标租户、父组织、组织对象的机密级别检查
```

组织管理入口应同时执行动作授权与 `checkOrgAccessible` 校验。一般业务数据读取使用适用的 `canAccessTenant`、`canAccessDomain`、`canAccessOrg` 和机密级别判断，或等价的查询过滤；不要把带父节点、根节点管理约束的 `checkOrgAccessible` 当成通用读取校验。资源权限通过不表示可以访问任意组织，组织范围通过也不表示可以执行任意资源动作。

`DataScope` 的六个集合字段分别采用用户非 null 值（包括空集合），否则继承生效角色对应集合的并集。普通租户用户始终受自身租户边界约束；普通平台用户通过 `_ALL_`、`_NONE_`、指定租户或 `Groovy#` 配置授权租户。`_ALL_` 包含无租户数据，`_NONE_` 仅匹配无租户数据。

角色、租户和组织在参与默认授权计算前都会执行 `selfAudit()`；禁用、逻辑删除、过期或缺少 ID 的对象不会授予权限、扩大数据范围或作为可访问目标。

身份快捷路径只作用于对应的授权门槛：仅 `R_SA` SuperAdmin（包括顶级 `sa`）可跳过租户/组织范围规则，TopSuperAdmin 另可跳过机密等级。SaaSAdmin 与普通平台用户必须按授权范围访问，服从用户覆盖、角色继承及拒绝规则；有授权可以跨组织，不增加所属组织或组织树的硬限制。普通 SuperAdmin / SaaSAdmin 的既有列表及管理入口均保留对象密级检查。身份边界和显式目标的存在性、状态检查先于快捷返回；TenantAdmin 的全部组织权限只限于已通过租户资格判断的自身租户。领域判断没有管理员自动放行分支，TopSuperAdmin 也必须通过目标对象的非空领域检查。

> **实现注意：** 当前普通用户的 `checkOrgAccessible(...)` 分支以租户边界和已计算的组织范围集合判断目标组织；若业务要求普通用户也必须逐一校验目标租户、父组织或组织对象的 `confidentialLevel`，应在业务入口补充该校验，或将其明确提升为 `RbacBaseService` 的统一策略。

## 18. RBAC 最小接入流程

### 第一步：实现领域模型

至少实现：

- `RbacUserInfo`
- `RbacRoleInfo`
- `RbacTenantInfo`
- `RbacOrgInfo`

若只使用动作/角色授权，可暂不接入数据范围查询，但仍须准确填写用户归属。用户租户 ID 为空会被识别为平台用户，不能用空租户 ID 表示“关闭租户权限”；组织 ID 为空表示无组织归属。范围集合返回 null 表示继承角色，返回空集合表示显式空授权，两者也不能混用。

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
- `loadAllDomainList(...)`
- `loadDomain(...)`
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
    public Collection<MyDomain> loadAllDomainList(boolean onlyLoadEffectDomain) {
        return domainRepository.findAll(onlyLoadEffectDomain);
    }

    @Override
    public MyDomain loadDomain(Serializable domainPrincipal) {
        return domainRepository.findById(domainPrincipal.toString()).orElse(null);
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

`DataScope` 分别声明租户、领域、组织的允许/拒绝集合，以及机密数据访问级别。
用户和角色均实现该接口。领域对应 `DomainObject.getDomainId()`，不是域名或资源权限表达式。

### 19.1 用户与角色的合并

六个 `Set<String>` 字段独立选择来源：

| 用户字段 | 有效配置 |
|---|---|
| `null` | 生效角色对应集合的并集 |
| 空集合 `[]` | 用户明确的空集合，替代角色配置 |
| 非空集合 | 用户配置，替代角色配置 |

不能用 `isEmpty()` 判断是否继承。接口默认返回的空集合以及默认租户集合也是已定义值；需要继承角色的用户实现必须返回 `null`，持久化适配不能提前把 `null` 变成空集合。

| 接口字段 | 默认返回值 | 对角色配置的影响 |
|---|---|---|
| `getTenantScopeList()` | `Set.of("_DEFAULT_")` | 替代角色租户允许集合 |
| 其余五个范围 getter | `Set.of()` | 分别替代角色对应集合 |
| `getConfidentialDataAccessLevel()` | `null` | 回退到生效角色的最高级别 |

集合字段本身不能是空字符串；原始存储中的空字符串须由适配层转换为未定义的 `null`。集合内部的 `null`、空字符串和空白规则属于配置错误，不能通过清理这些元素触发角色继承。

允许为空表示没有允许的目标；拒绝为空表示没有排除项。用户拒绝集合为空时，角色拒绝不会保留。选择各自来源后，每一维度计算：

```text
有效范围 = 允许规则匹配的目标并集 − 拒绝规则匹配的目标并集
```

这里减的是匹配结果，而不是规则字符串；允许全部组织、拒绝某部门时，两条不同字符串仍会发生权限扣除。

领域授权和其他权限分阶段计算，避免递归：先内部加载已分配且有效的候选角色，合并用户/角色的领域允许和拒绝集合；随后检查角色自身 `domainId`，只让领域可访问的角色贡献租户/组织范围、动作权限和授予密级。用户非 null 字段的替代规则保持不变。

机密级别沿用用户非 null 值优先，否则取领域可访问角色授予的最大值；顶级超级管理员保留最高访问级别。角色自身 `getConfidentialLevel()` 是该角色对象的可见性门槛，`getConfidentialDataAccessLevel()` 才是它授予用户的级别。用户密级计算和数据范围初始化不读取角色对象密级，也不调用可见角色列表；只有对外可见角色过滤才使用对象密级，避免 `角色可见性 → 用户密级 → 角色可见性` 递归。

### 19.2 租户范围与身份边界

租户范围使用 `getTenantScopeList()`、`getDeniedTenantScopeList()`：

| 编码 | 含义 |
|---|---|
| `_ALL_` | 所有数据，包括租户 ID 为空的数据 |
| `_DEFAULT_` | 用户所属租户；平台用户对应无租户 |
| `_NONE_` | 租户 ID 为空的数据 |
| `Groovy#脚本` | 使用 `_tenant`、`_user` 匹配租户 |
| 其他字符串 | 具体租户 ID，按精确值匹配 |

只有平台用户可以跨租户。租户用户即使配置其他租户、`_ALL_`、`_NONE_` 或 Groovy，也不能超出自身租户。平台身份仅提供跨租户资格；除 `R_SA` 超管外，包括 SaaSAdmin 在内均须匹配明确的租户和组织授权范围，不能直接获得全量访问。

租户列表只枚举真实租户对象。无租户数据应通过单点范围检查处理，不创建虚构租户。拒绝租户内某组织不会让该租户从可访问租户列表消失。

### 19.3 领域范围与业务接入

领域允许/拒绝集合使用具体领域 ID，或 `_ALL_`（所有领域值，包括空 ID）和 `_NONE_`（仅空 ID）。非空未知领域不会自动获得授权。

`loadAllDomainList(boolean)` 和 `loadDomain(Serializable)` 由业务服务实现，分别加载领域目录和指定领域对象；指定领域不存在时返回 null。`canAccessDomain` 先判断允许/拒绝集合，再加载领域，验证 ID 一致且通过 `selfAudit()`。禁用、过期、已逻辑删除或缺少 ID 的领域不会通过检查。

`loadUserAccessibleDomainList(user, onlyLoadEffectDomain)` 返回授权范围内的有效领域：先求允许减拒绝，结果为空时不读取目录；否则只批量加载一次，不逐个调用 `loadDomain`，并保留目录顺序。参数原样传给目录加载器，但用户可访问列表始终排除无效对象，与租户列表契约一致。领域规模大时可覆盖为按有效允许 ID 直接查询。

领域是租户之上的业务范围。`RbacTenantInfo`、`RbacOrgInfo`、`RbacUserInfo`、`RbacRoleInfo`、`MenuItem` 的 `domainId` 表示对象归属；用户/角色的领域集合表示访问授权，两者不能混用。

- 对象领域 ID 为空：跳过该对象自身的领域检查，其他权限仍生效。
- 对象领域 ID 非空：先通过领域允许/拒绝和目录有效性检查，再继续原有判断，所有管理员身份都不绕过领域。
- 组织和所属租户的非空领域必须一致；不一致时单点拒绝、可访问列表排除、管理校验抛出异常。
- 组织自身领域为空不能绕过所属租户的领域门槛。全组织快捷判断也不能忽略有领域限制或领域不一致的实际节点。

原始 `loadUser`、`loadTenant`、`loadOrg`、`loadRole`、各目录加载、父子节点原始查询以及菜单扫描仍只取数，原方法名和参数不变。领域过滤在已有用户上下文的可访问列表、权限判断与管理方法内部完成。无用户上下文的原始方法不能直接作为对用户返回数据的安全出口；没有引入隐式当前登录用户。

库内没有租户/组织创建、修改、迁移的持久化接口。业务写入入口也必须校验非空领域一致性，修改租户领域时须检查已有组织，不能仅依赖读取时拒绝异常数据。

`canAccessObjectDomain`、`filterByDomainAccess` 供现有业务流程复用对象级领域检查；对象空领域免过滤，而 `canAccessDomain(user, null)` 按 `_ALL_` / `_NONE_` 规则判断且不加载虚构领域目录。领域授权不自动授予跨租户资格，也不替代资源动作或机密级别检查。

`canAccessTenant`、`canAccessDomain`、`canAccessOrg` 是数据范围判断入口，不能代替资源动作授权或机密级别检查。业务数据查询需要显式接入适用维度的过滤；新增领域字段不会自动给任意 DAO 查询加条件。没有某维度的对象无需凭空检查该维度；具备组织/租户维度但 ID 为空的数据按对应 None 规则处理。

### 19.4 服务接口职责

| 方法 | 职责 |
|---|---|
| `getUserDataScope(user)` | 合并用户/生效角色并返回不可变快照；不能修改返回集合 |
| `canAccessTenant(user, tenantId)` | 检查租户领域、身份边界和租户范围；具体租户还须存在且有效 |
| `canAccessDomain(user, domainId)` | 判断领域允许/拒绝范围，并验证领域存在、ID 一致且有效 |
| `loadAllDomainList(onlyEffective)` / `loadDomain(id)` | 业务实现领域目录和指定领域加载 |
| `loadUserAccessibleDomainList(user, onlyEffective)` | 一次批量加载授权范围内的有效领域，空授权不加载 |
| `canAccessOrg(user, tenantId, orgId)` | 检查租户及组织领域、一致性和组织范围；空组织按 None 处理 |
| `canAccessAllOrg(user, tenantId)` | 检查指定租户内非空组织的完整范围覆盖 |
| `canAccessAllOrg(user)` | 租户用户检查自身租户；平台用户保留全局覆盖语义 |
| `loadUserAccessibleTenantList(user, onlyEffective)` | 枚举可访问的真实租户对象，不返回虚构的 None 租户 |
| `loadUserAccessibleOrgList(user, onlyEffective)` | 按租户隔离计算可访问的真实组织列表 |
| `checkOrgAccessible(user, tenantId, parentId, orgId)` | 领域和一致性校验先于管理快捷路径，保留父节点及根节点限制 |
| `canAdminUser(operator, targetUser)` | 目标用户及所属租户领域先于自我管理和管理员快捷路径 |
| `filterAccessibleMenuList(user, menus)` | 领域过滤与菜单/按钮动作授权，返回独立菜单树副本 |

普通平台用户的全局 `canAccessAllOrg(user)` 要求明确包含 `_ALL_`，组织允许包含 `_ALL_ROOT_|SelfAndAllChild` 且没有租户/组织拒绝规则，同时检查当前有效组织的实际覆盖情况；孤儿节点和未选中的独立环不能被当作已授权。全量判断是保守的明确授权判断，不是对任意复杂脚本做等价证明。

范围单点方法不执行通用的对象密级过滤；普通 SuperAdmin / SaaSAdmin 的列表接口保留密级过滤，所以单点范围为 true 不保证该对象会出现在密级过滤后的列表中。

### 19.5 角色分配的数据范围上限

`checkRoleAssignment` 在原有真实定义解析、领域、操作权限、授予密级、前置条件、互斥和共存校验后，调用 `checkRoleDataScopeAssignment`：每个角色独立声明的有效范围，以及采用目标用户六字段覆盖/继承后的最终有效范围，都不得超出操作者的当前有效范围。角色自身拒绝规则参与扣除；不能仅用另一个角色的拒绝或目标用户空覆盖遮住已经独立越权的角色。清空角色同样检查最终状态，避免移除拒绝角色后扩权。

此处比较分配时完整有效目录里的实际领域、租户和组织集合，不比较规则字符串是否相同。双方 `_DEFAULT_` 分别按自身租户/组织解释，目标用户的 None 公共范围也会检查。角色编码带来的全租户/全组织管理员能力参与上限检查；管理员不能绕过领域上限。授予及最终机密级别须同时满足操作者有效快照等级和公开机密等级检查，角色对象自身的可见密级不作为授予等级。

分配方法只做验证，不保存角色。调用方必须提供当前已授权的操作者上下文，以及拟保存的真实目标用户上下文。存在租户或组织 Groovy 范围时，目标对象的 `getRoleList()` 必须已经是 `finalRoles` 对应的最终编码集合，否则拒绝；不使用代理用户或分配前角色状态求值。这也适用于角色移除导致脚本从 false 变成 true 的情况。脚本应为可信、无副作用的判定表达式。

这是**当前有效目录快照**包含检查，不是对未来或任意脚本的静态证明。加载器必须返回完整、一致的有效目录；返回 null 被视为加载失败而拒绝，真正空目录应返回空集合。业务应在同一一致性边界内校验和保存，目录、组织归属、用户上下文或规则变化后必须重新校验。检查不缓存跨请求的授权结论；组织匹配按租户批量计算，不逐组织重复加载整树。

`isRoleAuthorized` 没有目标用户上下文，仍只表示角色权限检查，不能单独替代实际分配验证。

## 20. 组织范围 `DataScope.OrgScope`

组织允许/拒绝集合分别为 `getOrgScopeList()`、`getDeniedOrgScopeList()`，每项采用字符串：

```text
起点组织|匹配模式
```

### 20.1 起点与匹配模式

| 起点 | 含义 |
|---|---|
| `_DEFAULT_` | 用户归属组织；无归属时匹配无组织数据 |
| `_NONE_` | 组织 ID 为空的数据，忽略匹配模式 |
| `_ALL_ROOT_` | 当前目标租户的所有根组织，不包含无组织数据 |
| 具体组织 ID | 当前目标租户中的指定组织 |

| 模式 | 范围 |
|---|---|
| `Self` | 起点本身 |
| `DirectChild` | 直接子节点，不含起点 |
| `SelfAndDirectChild` | 起点及直接子节点 |
| `SelfAndAllChild` | 起点及全部后代 |
| `IdPath#表达式` | 相对起点的 ID 路径匹配 |
| `NamePath#表达式` | 相对起点的名称路径匹配 |
| `Groovy#脚本` | 组织脚本匹配，支持 `_org`、`_user` |

例如：

```json
{
  "tenantScopeList": ["tenant-a"],
  "deniedTenantScopeList": [],
  "orgScopeList": ["_ALL_ROOT_|SelfAndAllChild"],
  "deniedOrgScopeList": ["finance|SelfAndAllChild"]
}
```

含义是允许 tenant-a 的组织，但排除 finance 及全部下级。是否有业务操作权限仍需另行校验。

`_NONE_|Self` 只检查组织 ID 是否为空。格式仍要求 `|`，None 后的模式不参与匹配。解析只在第一个 `|` 处分割，因此 Groovy 中的 `||` 会完整保留。

### 20.2 路径与租户隔离

`IdPath#`、`NamePath#` 使用 Spring PathPattern。对于 `A -> A2 -> A21`，起点 A 到 A21 的相对 ID 路径是 `/A2/A21/`，不包含起点 A。

```text
A|IdPath#/*/*
```

表示在 A 子树中匹配二级节点。非尾斜杠表达式使用规范化路径；尾斜杠表达式保持 PathPattern 的尾斜杠语义。组织候选必须限定在目标租户和起点子树内，路径或脚本不得通过恒真条件扩展到兄弟组织树或其他租户。

### 20.3 从旧协议升级

旧顶层 `OrgScope`、`SimpleOrgScope`、`SimpleDataScope` 已移除，消费者需要迁移到新接口；有效用户范围通过 `getUserDataScope(...)` 获取。旧 `Collection<OrgScope>` getter 不能与新 `Set<String>` getter 仅凭返回类型重载兼容。

- `OnlySelf` → `Self`；`OnlyDirectChild` → `DirectChild`；`All` → `SelfAndAllChild`。
- `Custom + IdPath/NamePath/Groovy` → 对应的 `IdPath#/NamePath#/Groovy#` 字符串。
- 原 `isAllow` 改为放入允许集合或拒绝集合。
- `_USER_ORG_` → `_DEFAULT_`；`/*` 起点 → `_ALL_ROOT_`。
- 租户 `_DEFAULT_TENANT_` → `_DEFAULT_`；`#!groovy:` → `Groovy#`。
- 旧租户 `*` 映射为 `_ALL_`，包含无租户数据。

旧租户路径通配、组织 SpringEL 不再作为新范围协议支持。旧配置若对不同租户绑定不同组织策略，不能简单拆为两个并集，否则可能扩大授权；应逐项检查是否可等价表达。核心不自动双轨解析旧规则，迁移应保留原配置以便回退。

多数服务方法签名保留，旧 `mergeOrgScopeList` 已移除。新增的抽象 `loadAllDomainList(boolean)`、`loadDomain(Serializable)` 需要下游 `RbacBaseService` 实现类补齐；没有领域目录的业务可分别返回空集合和 null，显式表示没有可访问的领域。管理员组织查询现在也经过统一租户边界和租户内组织加载，不再调用 `loadMaxAccessibleOrgList` 的覆盖实现；依赖该扩展点优化的下游应改为覆盖 `loadTenantOrgList` 或 `loadUserAccessibleOrgList`。`canAccessAllOrg` 只表示非空组织范围完整覆盖，不能省略领域、租户、状态、无组织数据或机密级别条件。

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

范围计算采用以下短路顺序（保留管理员例外和身份边界）：

1. 空允许集合直接返回无权限，不执行拒绝脚本。
2. 租户保留编码和精确 ID 匹配先于 Groovy；拒绝命中后不执行允许脚本。
3. 真实租户全部拒绝时跳过租户枚举；无租户仍单独判断。拒绝 `_ALL_` 时不加载租户或组织候选。
4. 组织先计算拒绝结果。结构模式先于路径、Groovy；实际候选全部被拒绝时不再计算允许范围。
5. 部分组织被拒绝时，不对这些节点执行允许表达式；保留完整组织图，以免破坏后代的路径和祖先关系。
6. 单个组织检查只对目标节点执行允许和拒绝表达式，目标已被拒绝则立即返回。

`_ALL_ROOT_` 不包括孤儿节点或不属于根树的独立环，不能仅看到这个编码就认定所有数据都被拒绝；短路依据是实际候选覆盖结果。规则集合是匹配条件的并集，不承诺脚本求值顺序或求值次数；脚本应只返回判断结果，不依赖副作用。

性能回归保留五万节点、百层组织树的两秒上限，分别验证树装配、允许范围计算和全树拒绝；另用加载次数及抛错脚本验证不应执行的路径确实被跳过。字段定义和格式校验仍先执行，短路不等于接受非法配置。

本次本机 Surefire 单用例耗时（包含测试数据准备；不是生产延迟承诺）：

| 五万节点、百层场景 | 耗时 | 断言上限 |
|---|---:|---:|
| 组织树装配 | 0.264 秒 | 2 秒 |
| 允许范围计算 | 0.169 秒 | 2 秒 |
| 全树拒绝并跳过允许脚本 | 0.058 秒 | 2 秒 |
| 同一起点 20 条不命中 ID 路径规则 | 0.759 秒 | 2 秒 |
| 同领域五万组织批量过滤 | 0.101 秒，领域加载 1 次 | 2 秒 |

同时断言：全部租户和无租户均拒绝时，候选租户与组织加载次数均为 0；单点目标已拒绝时不执行任何允许脚本。

多规则路径匹配在单次计算内复用当前起点的子树、父路径和已解析的 `PathContainer`。ID 规则不读取名称；同起点名称规则增多不会重复生成整套名称路径。切换起点丢弃旧缓存，后续请求重新计算，组织更名、迁移或授权变更不会复用旧路径。缓存最多保留当前起点子树需要的路径，空间仍随节点数与路径长度增长，不是常量内存。

原版与新版的同环境重复测量、中位数、内存分配及原始样本见[性能对比报告](docs/benchmarks/datascope-2026-09-11/report.md)。

领域查询结果仅在一次最外层权限调用期间共享；嵌套调用复用同一计算上下文，最外层正常或异常退出都清理。服务实例之间隔离，返回的 DataScope 不携带查询缓存，即使业务复用同一范围快照，下次调用也重新核验领域状态。每批对象按领域 ID 复用目录校验，组织循环通过范围对象身份索引取得上下文，不反复计算大集合哈希。角色管理保留原 `isRoleAuthorized` 覆写扩展点，不能为了短路绕过业务自定义校验。

以上测试验证默认内存匹配算法，不代表数据库查询或生产并发吞吐。默认 `canAccessOrg` 仍加载目标租户的组织列表；高频业务可覆盖该接口或列表接口，使用有租户边界的索引查询/缓存，并保持六字段覆盖、拒绝优先及失效策略。不要直接缓存用户授权结果而不处理角色、组织和租户变化。




## 22. 角色与权限列表

角色列表有两个视角：

用户保存的是角色编码，不是某个角色对象 ID。角色的 `tenantId` 表示定义覆盖范围：目标租户的有效同码定义优先；没有有效本租户定义时，回退到租户 ID 为空的有效共享定义。其它租户的定义不参与选择。定义选择先于领域判断；已选中的本租户定义领域不可访问时，不再降级为共享定义来绕过限制。

角色定义的租户不是组织式的领域父对象。检查角色本身的 `domainId` 即可；目标用户及其租户的领域另行检查。普通租户操作人不能因为分配的是共享角色，就跨租户管理目标用户。

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

不要用“可见角色列表”反推用户真实权限。`loadUserOwnerRoleList` 过滤角色领域但不按角色对象密级筛选；可见角色列表再加对象密级过滤。内部领域授权初始化使用原始有效候选，不能再次调用这两个过滤出口。

### 22.3 角色分配必须校验实际定义

`checkRoleAssignment(operator, targetUser, finalRoles)` 参数不变，但传入角色对象仅提供编码：

1. 按目标用户租户批量加载角色目录，按编码去重并解析实际有效定义。
2. 任何编码找不到本租户或共享有效定义，立即拒绝。
3. 用实际定义检查领域、可分配权限、授予密级、分配前置条件、互斥和共存关系。
4. 共存角色的后续加载也按目标租户同码优先规则处理。

因此不能用低权限的共享角色对象，绕过目标租户中更高权限或更严格约束的同码定义。原始加载方法与 `isRoleAuthorized` 的覆写扩展点保持不变，覆写收到的是解析后的实际角色。

`isRoleAuthorized(operator, role)` 单独评估提供的角色定义，也支持创建尚未落库的角色；它没有目标用户参数，不负责解析目标用户最终使用哪份定义。实际分配前必须调用 `checkRoleAssignment`。分配校验使用选定定义的 `getPermissionList()`，不重新合并其它同码角色；目录加载器应提供完整权限、密级和约束字段。原始 `loadRolePermissionList` 的聚合查询不是实际定义的替代品。

保存用户时持久化经过校验的角色编码。角色定义更改或新增租户同码覆盖会影响已有用户，这是编码覆盖机制本身的行为，应由角色配置管理流程控制。

## 23. 超管语义

### 23.1 TopSuperAdmin

`isTopSuperAdmin()` 语义最强：

- 可跳过组织范围。
- 可跳过租户范围。
- 可跳过机密级别约束。
- 从有效租户/组织中获取最大候选结果，显式目标的存在性与状态检查仍保留。

### 23.2 SuperAdmin / SaaSAdmin

`isSuperAdmin()` 可走全局租户/组织范围快捷路径，但普通超管仍执行对象密级过滤。

`isSaasAdmin()` 不再享有自动全局数据范围：租户、组织、领域均须通过授权，拒绝规则生效，列表仍保留对象密级过滤。组织管理能力不被取消，但 `checkOrgAccessible` 对父组织和目标组织分别检查授权；创建无父、无现有目标的根节点时须具备 None 无组织范围授权。管理入口还会检查目标租户、父组织及目标组织密级。角色分配的范围预测也按此规则处理，不能把 `R_SAAS_ADMIN` 编码视为自动授予全局数据范围。

它们不是 `TopSuperAdmin`。

### 23.3 TenantAdmin

`isTenantAdmin()` 必须先通过自身租户的资格判断，随后组织范围可按本租户全部组织处理。它不能突破租户边界，也不自动取得领域权限或跨租户角色管理权限。

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

### 24.1 按用户过滤菜单

`RbacUtils.getMenuItemByController` 仍扫描并缓存完整菜单，不在全局缓存中保存某个用户的过滤结果。用户返回入口调用：

```java
List<SimpleMenu> visibleMenus = authorizeService.filterAccessibleMenuList(user, rawMenus);
```

输入为菜单根列表，子节点通过 `getChildren()` 提供。领域拒绝父菜单时不返回其子树，`alwaysShow` 只影响动作权限不足时的展示，不能绕过领域或禁用状态。按钮也按其授权列表过滤。

返回值是独立菜单副本，复制菜单接口字段、授权集合、按钮及父子结构；不修改共享缓存。自定义菜单子类的额外字段不在通用副本契约中。`SimpleMenu.domainId` 是领域归属，`domain` 保留为域名字段；扫描器当前用包名设置默认领域 ID，需要在领域目录中配置对应标识，不能把动作权限字符串中的 domain 自动当成目录记录。

## 25. RBAC 常见误区

### 25.1 把角色对象密级和角色授予的数据密级混为一谈

角色对象密级控制“谁能看到这个角色对象”。角色授予的数据密级控制“拥有这个角色后最多能访问多高密级数据”。

### 25.2 把 `IdPath` / `NamePath` 当绝对路径

它们是相对于 `DataScope.OrgScope.startOrg()` 的路径。

### 25.3 把无租户和公共组织拆开理解

当前默认实现里，无租户组织就是公共组织。

### 25.4 认为 SuperAdmin 等于 TopSuperAdmin

TopSuperAdmin 可跳过范围规则和密级限制，但显式目标的存在性、状态和租户身份边界检查仍保留；领域检查没有管理员自动放行。

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

本次完整验证命令（显式开启测试和注解处理）：

```bash
mvn -o -Dmaven.compiler.proc=full -Dmaven.test.skip=false -DskipTests=false clean verify
```

2026-09-11 使用 Maven 3.9.15 / JDK 25.0.2、编译目标 Java 17 验证：干净 Git 提交的发布构建运行 245 个测试，0 失败、0 错误、0 跳过；其中 RBAC 主回归 197 个、DataScope 协议测试 8 个。JAR 和源码包构建成功。本机离线依赖已缓存；首次构建未缓存时去掉 `-o`。工作区另有尚未跟踪的 `ModelUtilsTest`、`DataMaskingUtilsTest` 共 7 个本地测试，包含它们的工作区验证共 252 个通过；它们不属于干净检出的测试数量。

范围测试覆盖六字段的 null/空集合/非空集合覆盖、失效角色、快照隔离和 JSON 字段、租户与组织拒绝独立计算、平台身份边界、无归属数据、路径和脚本、同 ID 跨租户隔离、孤儿节点和组织环。领域加载另覆盖单次批量查询、拒绝短路、领域存在性/有效状态、错误 ID 和字段继承。外层门槛覆盖所有管理员、租户组织一致性、五万组织同领域只加载一次、角色对象密级回调无递归、菜单副本隔离、异常清理及覆写范围的约束一致性。角色分配另验证按目标租户编码解析、共享回退、实际定义约束、缺失定义拒绝以及共享角色不能跨目标用户租户。

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
- `src/main/java/com/levin/commons/rbac/DataScope.java`
- `src/main/java/com/levin/commons/rbac/RbacUserInfo.java`
- `src/main/java/com/levin/commons/rbac/RbacRoleInfo.java`
- `src/main/java/com/levin/commons/utils/ObjectWrapperUtils.java`
- `src/main/java/com/levin/commons/service/proxy/ProxyBeanScan.java`
- `src/main/java/com/levin/commons/service/domain/InjectVar.java`
- `src/main/java/com/levin/commons/plugin/Plugin.java`
- `src/main/java/com/levin/commons/plugin/PluginManager.java`
