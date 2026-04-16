package com.levin.commons.ui.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.ui.annotation.CRUD;
import com.levin.commons.ui.annotation.Form;
import com.levin.commons.ui.annotation.FormItem;
import com.levin.commons.ui.annotation.FormLayout;
import com.levin.commons.ui.annotation.JsonSchemaEditor;
import com.levin.commons.ui.annotation.Options;
import com.levin.commons.ui.model.CRUDModel;
import com.levin.commons.ui.model.CRUDListTableModel;
import com.levin.commons.ui.model.CRUDOpModel;
import com.levin.commons.ui.model.FormActionModel;
import com.levin.commons.ui.model.FormItemModel;
import com.levin.commons.ui.model.FormLayoutModel;
import com.levin.commons.ui.model.FormModel;
import com.levin.commons.ui.model.JsonSchemaEditorModel;
import com.levin.commons.ui.model.OptionsModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Date;

public class ModelUtils {

    public static CRUDModel genModelByClass(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }

        CRUD crud = findClassAnnotation(clazz, CRUD.class);

        if (crud == null) {
            return null;
        }

        CRUDModel crudModel = new CRUDModel();
        copyAnnotationValues(crud, crudModel);

        Tag tag = findClassAnnotation(clazz, Tag.class);

        crudModel.className(clazz.getName());
        crudModel.alias(defaultText(crudModel.alias(), buildClassAlias(clazz, crud, tag)));
        crudModel.name(defaultText(crudModel.name(), tag != null ? tag.name() : "", clazz.getSimpleName()));
        crudModel.title(defaultText(crudModel.title(), tag != null ? tag.description() : ""));

        List<CRUDListTableModel> listTableModels = new ArrayList<>();
        List<CRUDOpModel> opModels = new ArrayList<>();

        for (Method method : getPublicMethods(clazz)) {

            CRUD.ListTable listTable = method.getAnnotation(CRUD.ListTable.class);
            if (listTable != null) {
                listTableModels.add(buildListTableModel(clazz, method, listTable));
            }

            CRUD.Op op = method.getAnnotation(CRUD.Op.class);
            if (op != null) {
                opModels.add(buildOpModel(clazz, method, op));
            }
        }

        linkOpsToListTables(listTableModels, opModels);

        crudModel.listTableList(listTableModels);
        crudModel.opList(opModels);

        return crudModel;
    }

    private static CRUDListTableModel buildListTableModel(Class<?> controllerClass, Method method, CRUD.ListTable annotation) {

        CRUDListTableModel model = new CRUDListTableModel();
        copyAnnotationValues(annotation, model);

        Operation operation = method.getAnnotation(Operation.class);

        model.className(controllerClass.getName());
        model.methodId(method.toGenericString());
        model.methodName(method.getName());
        model.alias(defaultText(model.alias(), buildMethodAlias(controllerClass, method, "listTable")));

        if (!hasText(model.title()) && operation != null) {
            model.title(operation.summary());
        }

        if ((!hasText(model.name()) || "default".equals(model.name())) && !"list".equals(method.getName())) {
            model.name(method.getName());
        }

        if (!hasText(model.desc()) && operation != null) {
            model.desc(operation.description());
        }

        return model;
    }

    private static CRUDOpModel buildOpModel(Class<?> controllerClass, Method method, CRUD.Op annotation) {

        CRUDOpModel model = new CRUDOpModel();
        copyAnnotationValues(annotation, model);

        Operation operation = method.getAnnotation(Operation.class);

        model.className(controllerClass.getName());
        model.methodId(method.toGenericString());
        model.methodName(method.getName());
        model.alias(defaultText(model.alias(), buildMethodAlias(controllerClass, method, "op")));
        model.name(defaultText(model.name(), method.getName()));

        if (!hasText(model.label()) && operation != null) {
            model.label(operation.summary());
        }

        if (!hasText(model.label())) {
            model.label(method.getName());
        }

        if (!hasText(model.desc()) && operation != null) {
            model.desc(operation.description());
        }

        FormModel formModel = buildFormModel(controllerClass, method);
        if (formModel != null) {
            model.form(formModel);
        }

        return model;
    }

    private static void linkOpsToListTables(List<CRUDListTableModel> listTableModels, List<CRUDOpModel> opModels) {

        if (listTableModels.isEmpty() || opModels.isEmpty()) {
            return;
        }

        Map<String, List<CRUDOpModel>> groupedOps = new LinkedHashMap<>();
        for (CRUDOpModel opModel : opModels) {
            if (opModel == null || !hasText(opModel.opRefTargetName())) {
                continue;
            }

            if (opModel.opRefTargetType() == CRUD.OpRefTargetType.None
                    || opModel.opRefTargetType() == CRUD.OpRefTargetType.Other) {
                continue;
            }

            groupedOps.computeIfAbsent(opModel.opRefTargetName(), key -> new ArrayList<>()).add(opModel);
        }

        for (CRUDListTableModel listTableModel : listTableModels) {
            listTableModel.opList(new ArrayList<>(groupedOps.getOrDefault(listTableModel.name(), List.of())));
        }
    }

    private static FormModel buildFormModel(Class<?> controllerClass, Method method) {

        for (Parameter parameter : method.getParameters()) {
            Form form = parameter.getAnnotation(Form.class);
            if (form == null) {
                form = parameter.getType().getAnnotation(Form.class);
            }

            if (form == null) {
                continue;
            }

            Class<?> requestClass = parameter.getType();
            FormModel formModel = new FormModel();
            copyAnnotationValues(form, formModel);

            Schema schema = requestClass.getAnnotation(Schema.class);

            formModel.className(requestClass.getName());
            formModel.alias(defaultText(formModel.alias(), buildFormAlias(controllerClass, method, requestClass)));
            formModel.name(defaultText(formModel.name(), requestClass.getSimpleName()));

            if (!hasText(formModel.title()) && schema != null) {
                formModel.title(schema.title());
            }

            if (!hasText(formModel.desc()) && schema != null) {
                formModel.desc(schema.description());
            }

            formModel.layouts(buildLayouts(form.layouts(), requestClass));
            formModel.actions(buildActions(form.actions()));
            formModel.formItemList(buildFormItems(requestClass));

            return formModel;
        }

        return null;
    }

    private static FormLayoutModel[] buildLayouts(FormLayout[] layouts, Class<?> requestClass) {

        return Arrays.stream(layouts)
                .filter(Objects::nonNull)
                .map(item -> {
                    FormLayoutModel model = new FormLayoutModel();
                    copyAnnotationValues(item, model);
                    model.className(requestClass.getName());
                    model.alias(defaultText(model.alias(), buildLayoutAlias(requestClass, item)));
                    return model;
                })
                .toArray(FormLayoutModel[]::new);
    }

    private static FormActionModel[] buildActions(Form.Action[] actions) {

        return Arrays.stream(actions)
                .filter(Objects::nonNull)
                .map(item -> {
                    FormActionModel model = new FormActionModel();
                    copyAnnotationValues(item, model);
                    return model;
                })
                .toArray(FormActionModel[]::new);
    }

    private static List<FormItemModel> buildFormItems(Class<?> requestClass) {

        List<FormItemModel> items = new ArrayList<>();

        for (Field field : getAllFields(requestClass)) {

            if (Modifier.isStatic(field.getModifiers()) || field.isSynthetic()) {
                continue;
            }

            if (!shouldBuildFormItem(field)) {
                continue;
            }

            FormItemModel itemModel = new FormItemModel();
            FormItem formItem = field.getAnnotation(FormItem.class);
            Schema schema = field.getAnnotation(Schema.class);

            itemModel.fieldName(field.getName());
            itemModel.alias(buildFieldAlias(field));

            if (formItem != null) {
                copyAnnotationValues(formItem, itemModel);
            }

            itemModel.name(defaultText(itemModel.name(), field.getName()));

            if (schema != null) {
                if (!hasText(itemModel.placeholder())) {
                    itemModel.placeholder(schema.title());
                }

                if (!hasText(itemModel.desc())) {
                    itemModel.desc(schema.description());
                }

                if (!hasText(itemModel.defaultValue())) {
                    itemModel.defaultValue(schema.defaultValue());
                }
            }

            if (!hasText(itemModel.uiType())) {
                itemModel.uiType(inferUiType(field));
            }

            itemModel.options(buildOptions(field, formItem));

            JsonSchemaEditor editor = field.getAnnotation(JsonSchemaEditor.class);
            if (editor != null) {
                itemModel.jsonSchemaEditor(buildJsonSchemaEditorModel(field, editor));
            }

            items.add(itemModel);
        }

        items.sort(Comparator.comparingInt(item -> item.order() >= 0 ? item.order() : Integer.MAX_VALUE));

        return items;
    }

    private static OptionsModel[] buildOptions(Field field, FormItem formItem) {

        List<OptionsModel> optionsModels = new ArrayList<>();

        Options directOptions = field.getAnnotation(Options.class);
        if (directOptions != null) {
            optionsModels.add(buildOptionsModel(field, directOptions));
        }

        if (formItem != null) {
            for (Options options : formItem.options()) {
                if (options != null) {
                    optionsModels.add(buildOptionsModel(field, options));
                }
            }
        }

        if (optionsModels.isEmpty() && field.getType().isEnum()) {
            OptionsModel enumOptions = new OptionsModel();
            enumOptions.fieldName(field.getName());
            enumOptions.alias(buildFieldAlias(field) + ":options");
            enumOptions.name(field.getName());
            enumOptions.refTargetType(field.getType());
            optionsModels.add(enumOptions);
        }

        return optionsModels.toArray(new OptionsModel[0]);
    }

    private static OptionsModel buildOptionsModel(Field field, Options annotation) {

        OptionsModel model = new OptionsModel();
        copyAnnotationValues(annotation, model);

        model.fieldName(field.getName());
        model.alias(buildFieldAlias(field) + ":options");
        model.name(defaultText(model.name(), field.getName()));

        return model;
    }

    private static JsonSchemaEditorModel buildJsonSchemaEditorModel(Field field, JsonSchemaEditor annotation) {

        JsonSchemaEditorModel model = new JsonSchemaEditorModel();
        copyAnnotationValues(annotation, model);

        model.fieldName(field.getName());
        model.alias(buildFieldAlias(field) + ":jsonSchema");

        if (!hasText(model.title())) {
            Schema schema = field.getAnnotation(Schema.class);
            if (schema != null) {
                model.title(schema.title());
            }
        }

        return model;
    }

    private static boolean shouldBuildFormItem(Field field) {

        if (field.getAnnotation(JsonIgnore.class) != null || Modifier.isTransient(field.getModifiers())) {
            return false;
        }

        return field.getAnnotation(FormItem.class) != null
                || field.getAnnotation(Schema.class) != null
                || field.getAnnotation(Options.class) != null
                || field.getAnnotation(JsonSchemaEditor.class) != null;
    }

    private static <A extends Annotation> A findClassAnnotation(Class<?> clazz, Class<A> annotationClass) {

        for (Class<?> current = clazz; current != null && current != Object.class; current = current.getSuperclass()) {
            A annotation = current.getAnnotation(annotationClass);
            if (annotation != null) {
                return annotation;
            }
        }

        return null;
    }

    private static List<Method> getPublicMethods(Class<?> clazz) {

        return Arrays.stream(clazz.getMethods())
                .filter(method -> method.getDeclaringClass() != Object.class)
                .filter(method -> !method.isBridge() && !method.isSynthetic())
                .sorted(Comparator.comparing(Method::getName)
                        .thenComparingInt(Method::getParameterCount))
                .toList();
    }

    private static List<Field> getAllFields(Class<?> clazz) {

        List<Class<?>> hierarchy = new ArrayList<>();
        for (Class<?> current = clazz; current != null && current != Object.class; current = current.getSuperclass()) {
            hierarchy.add(0, current);
        }

        Map<String, Field> fieldMap = new LinkedHashMap<>();

        for (Class<?> current : hierarchy) {
            for (Field field : current.getDeclaredFields()) {
                fieldMap.put(field.getName(), field);
            }
        }

        return new ArrayList<>(fieldMap.values());
    }

    private static void copyAnnotationValues(Annotation annotation, Object target) {

        for (Method method : annotation.annotationType().getDeclaredMethods()) {
            if ("annotationType".equals(method.getName())) {
                continue;
            }

            try {
                Object value = method.invoke(annotation);
                invokeFluentSetter(target, method.getName(), value);
            } catch (IllegalAccessException | InvocationTargetException ignored) {
                // ignore bad values and keep defaults
            }
        }
    }

    private static void invokeFluentSetter(Object target, String name, Object value) {

        if (value == null) {
            return;
        }

        for (Method method : target.getClass().getMethods()) {
            if (!method.getName().equals(name) || method.getParameterCount() != 1) {
                continue;
            }

            Class<?> parameterType = wrap(method.getParameterTypes()[0]);
            Class<?> valueType = wrap(value.getClass());

            if (!parameterType.isAssignableFrom(valueType)) {
                continue;
            }

            try {
                method.invoke(target, value);
                return;
            } catch (IllegalAccessException | InvocationTargetException ignored) {
                return;
            }
        }
    }

    private static Class<?> wrap(Class<?> type) {

        if (!type.isPrimitive()) {
            return type;
        }

        if (type == int.class) {
            return Integer.class;
        }
        if (type == long.class) {
            return Long.class;
        }
        if (type == boolean.class) {
            return Boolean.class;
        }
        if (type == byte.class) {
            return Byte.class;
        }
        if (type == short.class) {
            return Short.class;
        }
        if (type == float.class) {
            return Float.class;
        }
        if (type == double.class) {
            return Double.class;
        }
        if (type == char.class) {
            return Character.class;
        }

        return type;
    }

    private static String buildClassAlias(Class<?> clazz, CRUD crud, Tag tag) {
        return defaultText(crud.name(), tag != null ? tag.name() : "", clazz.getSimpleName(), clazz.getName());
    }

    private static String buildMethodAlias(Class<?> controllerClass, Method method, String suffix) {
        return controllerClass.getName() + "#" + method.getName() + ":" + suffix;
    }

    private static String buildFormAlias(Class<?> controllerClass, Method method, Class<?> requestClass) {
        return controllerClass.getName() + "#" + method.getName() + ":" + requestClass.getName();
    }

    private static String buildLayoutAlias(Class<?> requestClass, FormLayout layout) {
        return requestClass.getName() + "#layout:" + defaultText(layout.group(), layout.title(), String.valueOf(layout.order()));
    }

    private static String buildFieldAlias(Field field) {
        return field.getDeclaringClass().getName() + "#" + field.getName();
    }

    private static String inferUiType(Field field) {

        Class<?> type = field.getType();

        if (type == boolean.class || type == Boolean.class) {
            return "switch";
        }

        if (Number.class.isAssignableFrom(type)
                || type == byte.class
                || type == short.class
                || type == int.class
                || type == long.class
                || type == float.class
                || type == double.class) {
            return "number";
        }

        if (type.isEnum()) {
            return "select";
        }

        if (Date.class.isAssignableFrom(type)
                || "java.time.LocalDate".equals(type.getName())
                || "java.time.LocalDateTime".equals(type.getName())
                || "java.time.LocalTime".equals(type.getName())) {
            return "date";
        }

        if (Collection.class.isAssignableFrom(type) || type.isArray()) {
            return "list";
        }

        if (Map.class.isAssignableFrom(type) || type.getName().contains("JSONObject")) {
            return "json";
        }

        if (type == String.class) {
            return "text";
        }

        return type.getSimpleName();
    }

    private static String defaultText(String... values) {

        if (values == null) {
            return "";
        }

        for (String value : values) {
            if (hasText(value)) {
                return value;
            }
        }

        return "";
    }

    private static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }

}
