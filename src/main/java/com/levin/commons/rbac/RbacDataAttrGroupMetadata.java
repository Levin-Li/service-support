package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.media.Schema;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 按需解析并缓存不可变的属性权限分组元数据，缓存不包含用户授权状态。
 * 无注解类型的空结果同样缓存；不递归缓存嵌套子类型图，API 集成方应分别处理嵌套类型。
 */
public final class RbacDataAttrGroupMetadata {

    private static final ClassValue<Holder> CACHE = new ClassValue<>() {
        @Override protected Holder computeValue(Class<?> type) { return new Holder(); }
    };

    private final Map<String, Group> groups;
    private final Map<String, List<Group>> attributes;

    private RbacDataAttrGroupMetadata(Map<String, MutableGroup> declarations) {
        Map<String, Group> result = new LinkedHashMap<>();
        Map<String, List<Group>> byAttribute = new LinkedHashMap<>();
        declarations.forEach((name, source) -> {
            Group group = new Group(name, source);
            result.put(name, group);
            group.attributes.forEach(attribute -> byAttribute.computeIfAbsent(attribute, key -> new ArrayList<>()).add(group));
        });
        Map<String, List<Group>> frozen = new LinkedHashMap<>();
        byAttribute.forEach((name, values) -> frozen.put(name, List.copyOf(values)));
        groups = Collections.unmodifiableMap(result);
        attributes = Collections.unmodifiableMap(frozen);
    }

    /** 每个已编译类型只解析一次，空结果和配置异常同样缓存。 */
    public static RbacDataAttrGroupMetadata forType(Class<?> type) {
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

    /** 返回按名称索引的不可变分组集合。 */
    public Map<String, Group> getGroups() { return groups; }

    /** 返回属性所属的全部分组；未归组属性返回不可变空列表。 */
    public List<Group> getGroupsForAttribute(String attribute) {
        return attributes.getOrDefault(attribute, List.of());
    }

    private static RbacDataAttrGroupMetadata parse(Class<?> type) {
        Set<Class<?>> hierarchy = new LinkedHashSet<>();
        collectHierarchy(type, hierarchy);
        Map<String, Field> fields = new LinkedHashMap<>();
        Map<String, Method> getters = new LinkedHashMap<>();
        for (Class<?> current : hierarchy) {
            for (Field field : current.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers()) && !field.isSynthetic()) fields.put(field.getName(), field);
            }
            for (Method method : current.getDeclaredMethods()) {
                String attribute = getterAttribute(method);
                if (attribute != null) {
                    Schema schema = method.getAnnotation(Schema.class);
                    if (!getters.containsKey(attribute) || (schema != null && !schema.title().isBlank())) {
                        getters.put(attribute, method);
                    }
                }
            }
        }
        Set<String> properties = new LinkedHashSet<>(fields.keySet());
        properties.addAll(getters.keySet());
        Map<String, MutableGroup> declarations = new LinkedHashMap<>();
        for (Class<?> current : hierarchy) {
            for (RbacDataAttrGroup annotation : current.getDeclaredAnnotationsByType(RbacDataAttrGroup.class)) {
                add(type, declarations, annotation, null, fields, getters, properties);
            }
            for (Field field : current.getDeclaredFields()) {
                for (RbacDataAttrGroup annotation : field.getDeclaredAnnotationsByType(RbacDataAttrGroup.class)) {
                    if (Modifier.isStatic(field.getModifiers()) || field.isSynthetic()) throw invalid(type, "不支持静态字段或编译器生成字段：" + field.getName());
                    add(type, declarations, annotation, field.getName(), fields, getters, properties);
                }
            }
            for (Method method : current.getDeclaredMethods()) {
                for (RbacDataAttrGroup annotation : method.getDeclaredAnnotationsByType(RbacDataAttrGroup.class)) {
                    String attribute = getterAttribute(method);
                    if (attribute == null) throw invalid(type, "方法注解必须声明在 JavaBean getter 上：" + method.getName());
                    add(type, declarations, annotation, attribute, fields, getters, properties);
                }
            }
        }
        return new RbacDataAttrGroupMetadata(declarations);
    }

    private static void collectHierarchy(Class<?> type, Set<Class<?>> hierarchy) {
        if (type == null || type == Object.class || hierarchy.contains(type)) return;
        collectHierarchy(type.getSuperclass(), hierarchy);
        for (Class<?> contract : type.getInterfaces()) collectHierarchy(contract, hierarchy);
        hierarchy.add(type);
    }

    private static String getterAttribute(Method method) {
        if (Modifier.isStatic(method.getModifiers()) || method.isBridge() || method.isSynthetic()
                || method.getParameterCount() != 0 || method.getReturnType() == void.class) return null;
        String name = method.getName();
        if (name.startsWith("get") && name.length() > 3) return Introspector.decapitalize(name.substring(3));
        if (name.startsWith("is") && name.length() > 2 && method.getReturnType() == boolean.class) {
            return Introspector.decapitalize(name.substring(2));
        }
        return null;
    }

    private static void add(Class<?> type, Map<String, MutableGroup> groups, RbacDataAttrGroup annotation,
                            String attribute, Map<String, Field> fields, Map<String, Method> getters, Set<String> properties) {
        String name = annotation.name().trim();
        if (name.isEmpty() && attribute != null) {
            Field field = fields.get(attribute);
            Schema schema = field == null ? null : field.getAnnotation(Schema.class);
            if (schema == null || schema.title().isBlank()) {
                Method getter = getters.get(attribute);
                schema = getter == null ? null : getter.getAnnotation(Schema.class);
            }
            if (schema != null) name = schema.title().trim();
        }
        if (name.isEmpty()) throw invalid(type, "分组名称不能为空" + (attribute == null ? "（类级声明）" : "，属性：" + attribute));
        Set<String> members = new LinkedHashSet<>();
        if (attribute != null) members.add(attribute);
        for (String member : annotation.attrList()) {
            if (member == null || member.isBlank() || !properties.contains(member.trim())) throw invalid(type, "属性不存在或名称为空：" + member);
            members.add(member.trim());
        }
        if (members.isEmpty()) throw invalid(type, "类级分组必须填写 attrList：" + name);
        MutableGroup group = groups.computeIfAbsent(name, key -> new MutableGroup());
        group.attributes.addAll(members);
        group.level = Math.max(group.level, annotation.confidentialLevel());
        if (!annotation.remark().isBlank()) group.remarks.add(annotation.remark().trim());
    }

    private static IllegalArgumentException invalid(Class<?> type, String message) {
        return new IllegalArgumentException("类型 " + type.getName() + " 的 RbacDataAttrGroup 配置无效：" + message);
    }

    private static final class Holder {
        private boolean resolved;
        private RbacDataAttrGroupMetadata metadata;
        private RuntimeException failure;
    }

    private static final class MutableGroup {
        private final Set<String> attributes = new LinkedHashSet<>();
        private final Set<String> remarks = new LinkedHashSet<>();
        private int level = ConfidentialLevel.PLATFORM_PUBLIC_CODE;
    }

    /** 合并同名声明后的不可变分组定义。 */
    public static final class Group {
        private final String name;
        private final Set<String> attributes;
        private final int confidentialLevel;
        private final String remark;

        private Group(String name, MutableGroup source) {
            this.name = name;
            attributes = Collections.unmodifiableSet(new LinkedHashSet<>(source.attributes));
            confidentialLevel = source.level;
            remark = String.join("\n", source.remarks);
        }

        /** 返回稳定的分组名称及授权标识。 */
        public String getName() { return name; }
        /** 返回去重后的不可变属性集合。 */
        public Set<String> getAttributes() { return attributes; }
        /** 返回同名声明中最高的最低机密访问级别。 */
        public int getConfidentialLevel() { return confidentialLevel; }
        /** 返回去重合并后的备注，各项以换行分隔。 */
        public String getRemark() { return remark; }
    }
}
