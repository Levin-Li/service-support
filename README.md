# service-support

`service-support` 是一个面向 Spring / Spring Boot 生态的通用支撑库，包含：

- RBAC 权限模型与授权引擎
- 领域访问边界、租户/组织数据范围与机密级别支持
- 代理 Bean 自动注册
- 插件机制
- 变量注入与表达式解析
- Spring MVC 参数转换增强
- 常用工具类与基础领域接口

项目完整使用手册请查看：

- [manual.md](manual.md)

如果你主要关注 RBAC，建议直接从 `manual.md` 中的 RBAC 章节开始阅读。

当前 `DataScope` 使用六个允许/拒绝集合：用户字段非 `null` 就替代角色配置（包括空集合），只有 `null` 才继承生效角色。领域是现有可访问查询与权限校验的前置门槛，空 `domainId` 跳过自身领域检查，原始加载接口保持不变。仅平台用户可以跨租户；组织规则采用 `起点组织|匹配模式` 字符串。

- [快速接入与数据范围示例](使用说明.md)
- [数据范围与继承规则](manual.md#19-数据范围-datascope)
- [旧协议迁移说明](manual.md#203-从旧协议升级)
- [RBAC 角色命名迁移说明](docs/rbac-role-naming-migration.md)

## MapStruct 字段名兼容策略

`service-support` 内置 `com.levin.commons.processor.mapstruct.FieldAwareAccessorNamingStrategy`，通过 `META-INF/services/org.mapstruct.ap.spi.AccessorNamingStrategy` 注册。策略在 MapStruct 编译期优先匹配源、目标类型的实际实例字段（包含父类字段），找不到对应字段时沿用 JavaBeans 默认规则，不修改字段或 getter/setter。

对于字段 `eSigningProcessSnapshotList`，`getESigningProcessSnapshotList()` 和 `geteSigningProcessSnapshotList()` 都识别为原字段名；同时保留真实的 `URL` 大写字段、普通属性和无字段的计算属性。链式 `seteSigning...` setter 也按实际字段匹配。生成的 Mapper 可以继续使用 `@Mapping(target = "eSigningProcessSnapshotList", ...)`。

SPI 必须在注解处理器类路径中。未配置独立 `annotationProcessorPaths`、使用普通编译类路径进行注解处理的项目，在编译依赖中包含新版 `service-support` 即可被 MapStruct 自动发现。JDK 23+ 还需要显式启用现有注解处理（例如 Maven 的 `<proc>full</proc>`）。

如果项目已配置 `annotationProcessorPaths`，在现有列表中追加 `service-support`，并保留已有 MapStruct、Lombok 等处理器：

```xml
<path>
    <groupId>com.levin.commons</groupId>
    <artifactId>service-support</artifactId>
    <version>${levin.service-support.version}</version>
</path>
```

这是编译期策略，会影响同一次编译中的 MapStruct Mapper；只在运行时添加依赖不会启用它。MapStruct 处理器依赖在 `service-support` 中设为 `provided`，不会作为普通运行依赖传递给使用方。

## API 数据属性权限分组

`RbacDataAttrGroup` 及 `RbacDataAttrGroupMetadata` 的声明规则、重复分组合并、按需正负缓存与 API 集成边界见[数据属性分组元数据](manual.md#数据属性分组元数据)。该手册随构件放入 `META-INF/service-support-docs/manual.md`。

## 枚举字段值范围

新增 `RbacDataAttr` 与不可变 `RbacDataAttrMetadata` 的声明、缓存和安全集成边界见[枚举值范围元数据](manual.md#枚举值范围元数据)。使用手册随包交付；本包不直接执行用户授权或 SQL 条件。
