package com.levin.commons.processor.mapstruct;

import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

/**
 * 优先使用实际字段名，兼容 Lombok basic 和 beanspec 两种访问器命名。
 * 例如字段 eSigningProcessSnapshotList 的 getESigningProcessSnapshotList 和
 * geteSigningProcessSnapshotList 都识别为 eSigningProcessSnapshotList。
 * 精确匹配字段优先，避免把 URL 等真实大写字段改成 uRL；找不到字段时沿用 JavaBeans 规则。
 * 本策略通过 MapStruct SPI 在编译期读取类型信息，不修改字段或访问器，不使用运行时反射。
 */
public class FieldAwareAccessorNamingStrategy extends DefaultAccessorNamingStrategy {

    @Override
    public String getPropertyName(ExecutableElement method) {
        String property = super.getPropertyName(method);
        TypeElement owner = (TypeElement) method.getEnclosingElement();
        if (property.isEmpty() || hasField(owner, property)) {
            return property;
        }

        String methodName = method.getSimpleName().toString();
        String stem = property;
        if ((methodName.startsWith("get") || methodName.startsWith("set")) && methodName.length() > 3) {
            stem = methodName.substring(3);
        } else if (methodName.startsWith("is") && methodName.length() > 2) {
            stem = methodName.substring(2);
        }
        // beanspec 的 seteSigning... 可能返回 this；MapStruct 默认将它视为无前缀的 fluent setter。
        // 仍按真实字段匹配，才能让链式 setter 和 getter 识别为同一属性。
        if (hasField(owner, stem)) {
            return stem;
        }
        String candidate = Character.toLowerCase(stem.charAt(0)) + stem.substring(1);
        return hasField(owner, candidate) ? candidate : property;
    }

    private boolean hasField(TypeElement type, String name) {
        while (type != null) {
            for (VariableElement field : ElementFilter.fieldsIn(type.getEnclosedElements())) {
                if (!field.getModifiers().contains(Modifier.STATIC)
                        && field.getSimpleName().contentEquals(name)) {
                    return true;
                }
            }
            TypeMirror parent = type.getSuperclass();
            type = parent instanceof DeclaredType ? (TypeElement) ((DeclaredType) parent).asElement() : null;
        }
        return false;
    }
}
