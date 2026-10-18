package com.levin.commons.rbac;

import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Convert;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Id;
import jakarta.persistence.EmbeddedId;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 枚举值范围的静态元数据。按编译类型惰性解析，成功、无注解和配置失败都缓存。
 * 不缓存用户或角色，不递归遍历关联实体，也不执行数据库条件或权限判断。
 */
public final class RbacDataAttrMetadata {
    private static final ClassValue<Holder> CACHE = new ClassValue<>() {
        @Override protected Holder computeValue(Class<?> type) { return new Holder(); }
    };
    private final List<Attribute> attributes;

    private RbacDataAttrMetadata(List<Attribute> attributes) { this.attributes = List.copyOf(attributes); }

    /** 每个类型只解析一次，包括明确拒绝的配置；ClassValue 避免阻止类卸载。 */
    public static RbacDataAttrMetadata forType(Class<?> type) {
        Objects.requireNonNull(type, "类型不能为空");
        Holder holder = CACHE.get(type);
        synchronized (holder) {
            if (!holder.resolved) {
                try { holder.metadata = parse(type); }
                catch (RuntimeException failure) { holder.failure = failure; }
                holder.resolved = true;
            }
            if (holder.failure != null) throw holder.failure;
            return holder.metadata;
        }
    }

    public List<Attribute> getAttributes() { return attributes; }
    public boolean isEmpty() { return attributes.isEmpty(); }

    private static RbacDataAttrMetadata parse(Class<?> type) {
        List<Class<?>> hierarchy = new ArrayList<>();
        for (Class<?> current = type; current != null && current != Object.class; current = current.getSuperclass()) {
            hierarchy.add(0, current);
        }
        Map<String, List<Field>> fields = new LinkedHashMap<>();
        for (Class<?> current : hierarchy) {
            for (Field field : current.getDeclaredFields()) {
                fields.computeIfAbsent(field.getName(), key -> new ArrayList<>()).add(field);
            }
        }
        List<Attribute> result = new ArrayList<>();
        for (List<Field> declarations : fields.values()) {
            for (Field field : declarations) {
                RbacDataAttr annotation = field.getDeclaredAnnotation(RbacDataAttr.class);
                if (annotation == null) continue;
                if (declarations.size() != 1) throw invalid(type, field, "父子类字段遮蔽导致持久属性含义不明确");
                if (Modifier.isStatic(field.getModifiers()) || field.isSynthetic()) throw invalid(type, field, "不支持静态或编译器生成字段");
                if (Modifier.isTransient(field.getModifiers()) || field.isAnnotationPresent(Transient.class)
                        || hasTransientGetter(hierarchy, field)) throw invalid(type, field, "不支持非持久字段");
                if (!field.getType().isEnum()) throw invalid(type, field, "只支持枚举类型字段");
                if (hierarchy.stream().anyMatch(current -> current.isAnnotationPresent(Entity.class))) {
                    validateJpaMapping(type, hierarchy, field);
                }
                Schema schema = field.getDeclaredAnnotation(Schema.class);
                String title = firstText(annotation.name(), schema == null ? "" : schema.title(), field.getName());
                Class<? extends Enum<?>> enumType = enumType(field.getType());
                List<Value> values = new ArrayList<>();
                for (Enum<?> value : enumType.getEnumConstants()) {
                    try {
                        Schema valueSchema = enumType.getField(value.name()).getDeclaredAnnotation(Schema.class);
                        String valueTitle = valueSchema == null ? "" : valueSchema.title();
                        if (valueTitle.isBlank() && value instanceof EnumDesc desc) valueTitle = desc.getDesc();
                        values.add(new Value(value.name(), firstText(valueTitle, value.name())));
                    } catch (NoSuchFieldException impossible) {
                        throw invalid(type, field, "无法解析枚举常量：" + value.name());
                    }
                }
                result.add(new Attribute(field.getName(), title, annotation.remark(), enumType, values));
            }
        }
        return new RbacDataAttrMetadata(result);
    }

    /** 显式 STRING 映射排除 ordinal 和 autoApply 转换器；任何启用的自定义转换都拒绝。 */
    private static void validateJpaMapping(Class<?> type, List<Class<?>> hierarchy, Field field) {
        // 不从 getter 猜测映射：FIELD 模式下 getter 的 STRING 声明不会改变字段的 ordinal 映射。
        for (Class<?> current : hierarchy) {
            Access access = current.getDeclaredAnnotation(Access.class);
            if (access != null && access.value() != AccessType.FIELD) throw invalid(type, field, "本期只支持 JPA FIELD 访问，拒绝 PROPERTY 或混合访问");
            for (Field member : current.getDeclaredFields()) {
                Access memberAccess = member.getDeclaredAnnotation(Access.class);
                if (memberAccess != null && memberAccess.value() != AccessType.FIELD) throw invalid(type, field, "本期只支持 JPA FIELD 访问，拒绝字段 PROPERTY 访问");
            }
            for (Method method : current.getDeclaredMethods()) {
                Access memberAccess = method.getDeclaredAnnotation(Access.class);
                if ((memberAccess != null && memberAccess.value() != AccessType.FIELD)
                        || method.isAnnotationPresent(Id.class) || method.isAnnotationPresent(EmbeddedId.class)) {
                    throw invalid(type, field, "本期只支持 JPA FIELD 访问，拒绝 getter 主键或 PROPERTY 访问");
                }
            }
        }
        Enumerated direct = field.getDeclaredAnnotation(Enumerated.class);
        for (Convert convert : field.getDeclaredAnnotationsByType(Convert.class)) {
            if (!convert.disableConversion()) throw invalid(type, field, "不支持启用的 @Convert 映射");
        }
        String getter = "get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
        for (Class<?> current : hierarchy) {
            for (Convert convert : current.getDeclaredAnnotationsByType(Convert.class)) {
                if (!convert.disableConversion() && (convert.attributeName().isBlank()
                        || convert.attributeName().equals(field.getName()))) {
                    throw invalid(type, field, "不支持类级 @Convert/@Converts 映射");
                }
            }
            for (Method method : current.getDeclaredMethods()) {
                if (!method.getName().equals(getter) || method.getParameterCount() != 0
                        || Modifier.isStatic(method.getModifiers()) || method.getReturnType() != field.getType()) continue;
                for (Convert convert : method.getDeclaredAnnotationsByType(Convert.class)) {
                    if (!convert.disableConversion()) throw invalid(type, field, "不支持 getter 上启用的 @Convert 映射");
                }
            }
        }
        if (direct == null || direct.value() != EnumType.STRING) {
            throw invalid(type, field, "JPA FIELD 访问的受控字段必须直接声明 @Enumerated(EnumType.STRING)");
        }
    }

    private static boolean hasTransientGetter(List<Class<?>> hierarchy, Field field) {
        String getter = "get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
        for (Class<?> current : hierarchy) {
            for (Method method : current.getDeclaredMethods()) {
                if (method.getName().equals(getter) && method.getParameterCount() == 0
                        && !Modifier.isStatic(method.getModifiers()) && method.isAnnotationPresent(Transient.class)) return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Enum<?>> enumType(Class<?> type) { return (Class<? extends Enum<?>>) type; }

    private static String firstText(String... values) {
        for (String value : values) if (value != null && !value.isBlank()) return value;
        return "";
    }
    private static IllegalArgumentException invalid(Class<?> type, Field field, String reason) {
        return new IllegalArgumentException("RbacDataAttr 配置错误：" + type.getName() + "." + field.getName() + "，" + reason);
    }
    private static final class Holder {
        boolean resolved;
        RbacDataAttrMetadata metadata;
        RuntimeException failure;
    }

    /** 字段标识始终使用 Java 属性名；所有集合不可变。 */
    public static final class Attribute {
        private final String name;
        private final String title;
        private final String remark;
        private final Class<? extends Enum<?>> enumType;
        private final List<Value> values;
        private Attribute(String name, String title, String remark, Class<? extends Enum<?>> enumType, List<Value> values) {
            this.name = name; this.title = title; this.remark = remark; this.enumType = enumType; this.values = List.copyOf(values);
        }
        public String getName() { return name; }
        public String getTitle() { return title; }
        public String getRemark() { return remark; }
        public Class<? extends Enum<?>> getEnumType() { return enumType; }
        public List<Value> getValues() { return values; }
    }
    /** 授权协议使用枚举 name，不使用 ordinal、toString 或自定义数值 code。 */
    public static final class Value {
        private final String name;
        private final String title;
        private Value(String name, String title) { this.name = name; this.title = title; }
        public String getName() { return name; }
        public String getTitle() { return title; }
    }
}
