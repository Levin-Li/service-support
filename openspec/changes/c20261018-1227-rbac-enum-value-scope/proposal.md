# 枚举值范围元数据

根框架事实源：levin-framework-base/openspec/changes/c20261018-1227-rbac-enum-value-scope/。本包只提供字段声明和不可变元数据，不执行用户授权或 SQL 注入。新增仅适用于枚举持久字段的 RbacDataAttr；按类型缓存成功、空结果和失败，不缓存用户。

JPA 映射约束：实体（含继承）仅支持 FIELD 访问，受控枚举字段必须直接显式使用字段上的 `@Enumerated(STRING)`。拒绝 ORDINAL、缺少显式声明及启用的字段/getter/类级 @Convert/@Converts；普通非 JPA POJO 保持枚举类型校验。通过显式 STRING 避免自动转换器和数据库值不一致导致授权判断错误。

映射边界收紧：JPA 本期仅支持 FIELD 访问，受控字段必须直接声明 Enumerated(STRING)；拒绝 PROPERTY、混合访问和 getter-only STRING。getter @Id/@EmbeddedId、类/字段/getter @Access(PROPERTY) 均拒绝，不猜测有效映射。
