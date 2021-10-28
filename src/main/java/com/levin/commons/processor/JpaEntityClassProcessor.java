package com.levin.commons.processor;

import com.levin.commons.annotation.GenNameConstant;
import com.levin.commons.service.domain.Desc;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.persistence.*;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@SupportedAnnotationTypes({"javax.persistence.MappedSuperclass", "javax.persistence.Entity"})
//@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class JpaEntityClassProcessor extends AbstractProcessor {

    final Map<String, Object> processedFiles = new ConcurrentHashMap<>();

    public static final String CLASS_NAME_PREFIX = "E_";

    private static final char[] replaceChars = {
            '!', '@', '#', '%', '^', '&', '(', ')', '-', '+', '=', '~'
            , '{', '}', '[', ']', '|', '\\'
            , ':', ';', '"', '\'', ',', '.', '/', '<', '>', '?'
            , ' '
            , '，', '。', '：', '；', '！', '？', '（', '）', '“', '”'
    };


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (String typeName : this.getSupportedAnnotationTypes()) {

            Class<? extends Annotation> annoType = null;

            try {
                annoType = (Class<? extends Annotation>) Class.forName(typeName);

                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, getClass().getSimpleName() + " start process type [ " + typeName + " ]...");

                process(roundEnv, roundEnv.getElementsAnnotatedWith(annoType));

            } catch (ClassNotFoundException e) {

                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, getClass().getSimpleName() + "  can't found class " + typeName);
            }

        }


        return false;
    }


    private static boolean hasText(String txt) {
        return txt != null && txt.trim().length() > 0;
    }

    private static String newClassName(String className) {
        return newClassName(className, CLASS_NAME_PREFIX);
    }

    private static String newClassName(String className, String prefix) {

        if (className == null || className.trim().length() < 1) {
            return "";
        }

        //如果有泛型，忽略泛型
        if (className.contains("<")) {
            className = className.substring(0, className.indexOf('<'));
        }

        int lastIndexOf = className.lastIndexOf('.');


        if (lastIndexOf == -1) {
            return prefix + className;
        }

        return className.substring(0, lastIndexOf + 1) + prefix + className.substring(lastIndexOf + 1);
    }


    private void process(RoundEnvironment roundEnv, Set<? extends Element> elementList) {


        Elements elementUtils = this.processingEnv.getElementUtils();

        //Types typeUtils = this.processingEnv.getTypeUtils();


        for (Element element : elementList) {

            //只支持对类，接口，注解的处理，对字段不做处理
            if (!element.getKind().isClass() && !element.getKind().isInterface()) {
                continue;
            }

            TypeElement typeElement = (TypeElement) element;

            GenNameConstant genFieldNameConstant = typeElement.getAnnotation(GenNameConstant.class);


            if (genFieldNameConstant != null
                    && genFieldNameConstant.ignore()) {
                continue;
            }


            final String packageName = elementUtils.getPackageOf(typeElement).getQualifiedName().toString();
            // final String newPackageName = elementUtils.getPackageOf(typeElement).getQualifiedName().toString()+".gen";

            final String fullClassName = typeElement.getQualifiedName().toString();
            final String simpleClassName = typeElement.getSimpleName().toString();

            final String newSimpleClassName = CLASS_NAME_PREFIX + typeElement.getSimpleName().toString();

            final String newFullClassName = packageName + "." + newSimpleClassName;


            if (processedFiles.containsKey(newFullClassName)) {
                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING,
                        getClass().getSimpleName() + " <<<" + newFullClassName + ">>> already processed.");
                continue;
            } else {
                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                        getClass().getSimpleName() + " Processing class " + fullClassName + " --> " + newSimpleClassName);
            }

            boolean useExtends = (genFieldNameConstant == null || genFieldNameConstant.extendsMode());

            TypeMirror superclass = typeElement.getSuperclass();

            //父类是否是 JPA
            boolean isSuperClassJpaEntity = superclass.getAnnotation(MappedSuperclass.class) != null || superclass.getAnnotation(Entity.class) != null;

            //继承
            useExtends = useExtends && isSuperClassJpaEntity;


            final String newSuperFullClassName = newClassName((superclass != null && !(superclass instanceof NoType)) ? superclass.toString() : "");

            boolean hasParent = newSuperFullClassName.trim().length() > 0
                    && !newSuperFullClassName.startsWith("java.")
                    && !newSuperFullClassName.startsWith("javax.")
                    && !newSuperFullClassName.equals(Object.class.getName());


            final StringBuilder codeBlock = new StringBuilder();

//            boolean isAnnotation = typeElement.getKind() == ElementKind.ANNOTATION_TYPE;


            codeBlock
                    .append("\n")
                    .append("package ").append(packageName).append(";\n")
                    .append("\n\n")
                    .append("import java.util.*;\n")
                    .append("import java.io.*;\n")

                    .append("//Auto gen by ").append(getClass()).append("， date:").append(new Date()).append("\n")
                    .append("/**\n" + " * @author Levin Li\n" + " */\n")
                    .append("public interface ").append(newSimpleClassName)
                    .append(" extends Serializable ")

                    .append((useExtends && hasParent) ? (" , " + newSuperFullClassName) : "")

                    .append(" {\n\n")
                    .append("    final String PACKAGE_NAME = \"").append(packageName).append("\"; // 类包名 \n\n")

                    .append("    final String CLASS_NAME = \"").append(fullClassName).append("\"; // 类全名 \n\n")

                    .append("    final String SIMPLE_CLASS_NAME = \"").append(simpleClassName).append("\"; // 类短名称 \n\n")
                    .append("    final String CACHE_KEY_PREFIX  = \"\\\"CK_\" + CLASS_NAME + \"_\\\" + \"; // 缓存Key前缀 \n\n")

            //    String CACHE_KEY_PREFIX = "\"PK_" + E_FXMember.CLASS_NAME + "_\" + ";

            ;

            if (typeElement.getAnnotation(MappedSuperclass.class) != null
                    || typeElement.getAnnotation(Entity.class) != null) {
                codeBlock.append("    final String ALIAS   = \"").append(getAlias(simpleClassName)).append("\"; // 别名1 \n\n");
                codeBlock.append("    final String ALIAS_2 = \"").append(getAlias(simpleClassName) + "_2").append("\"; // 别名2 \n\n");
            }

            processAnnotation(typeElement, simpleClassName, codeBlock);

            processJpaEntity(typeElement, simpleClassName, useExtends, hasParent, codeBlock);

            codeBlock.append("\n}\n");

            try {

                JavaFileObject sourceFile = this.processingEnv.getFiler().createSourceFile(newFullClassName);

                Writer writer = sourceFile.openWriter();

                writer.write(codeBlock.toString());

                writer.flush();

                writer.close();

                processedFiles.put(newFullClassName, sourceFile);

            } catch (IOException e) {

                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, getClass().getSimpleName() + " Processing  " + fullClassName + " error, " + e.getLocalizedMessage());

                throw new RuntimeException(e);
            }

        }
    }

    private static String getAlias(String className) {
        return className.chars()
                .filter(c -> Character.isUpperCase((char) c))
                .mapToObj(c -> "" + (char) c)
                .collect(Collectors.joining())
                .toLowerCase();
    }

    /**
     * 为注解生成方法名常量值
     *
     * @param typeElement
     * @param simpleClassName
     * @param codeBlock
     */
    private void processAnnotation(TypeElement typeElement, String simpleClassName, StringBuilder codeBlock) {

        //只支持注解类型
        if (typeElement.getKind() != ElementKind.ANNOTATION_TYPE
                || typeElement.getAnnotation(GenNameConstant.class) == null) {
            return;
        }

        typeElement.getEnclosedElements().stream()
                //只支持方法
                .filter(e -> e.getKind() == ElementKind.METHOD)
                .forEach(e -> {
                    codeBlock.append("    final String ").append(e.getSimpleName()).append(" = \"").append(e.getSimpleName()).append("\";\n\n");

                });

    }


    private void processJpaEntity(TypeElement typeElement, String simpleClassName, boolean useExtends, boolean hasParent, StringBuilder codeBlock) {

        //只支持类
        if (typeElement.getKind() != ElementKind.CLASS) {
            return;
        }

        Elements elementUtils = this.processingEnv.getElementUtils();

        Table tableAnno = typeElement.getAnnotation(Table.class);
        Entity entityAnno = typeElement.getAnnotation(Entity.class);

        if (tableAnno != null) {
            String tabName = hasText(tableAnno.name()) ? tableAnno.name().trim() : simpleClassName;
            codeBlock.append("    final String TABLE_NAME = \"").append(tabName).append("\"; //  表名 \n\n");
        }

        String entityName = (entityAnno != null && hasText(entityAnno.name())) ? entityAnno.name() : simpleClassName;

        codeBlock.append("    final String ENTITY_NAME = \"").append(entityName).append("\"; //  实体名字 \n\n");

        codeBlock.append("    final String E_ENTITY_NAME = \"E$:").append(typeElement.getQualifiedName().toString()).append("\"; // 可替换的全名 \n\n");

        final List<String> uniqueFields = new ArrayList<>();


        final Map<String, String> fieldMap = new LinkedHashMap<>();


        for (Element subEle : (hasParent && useExtends) ? typeElement.getEnclosedElements() : elementUtils.getAllMembers(typeElement)) {


            GenNameConstant fieldAnno = subEle.getAnnotation(GenNameConstant.class);

            if (fieldAnno != null && fieldAnno.ignore()) {
                continue;
            }

            Set<Modifier> modifiers = subEle.getModifiers();


            if (subEle.getKind() != ElementKind.FIELD
                    || modifiers.contains(Modifier.STATIC)
                    || modifiers.contains(Modifier.TRANSIENT)
                    || modifiers.contains(Modifier.FINAL)) {
                continue;
            }


            final String fieldName = subEle.getSimpleName().toString();

            // this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, getClass().getSimpleName() + " Processing field " + fieldName);


            Desc desc = subEle.getAnnotation(Desc.class);

            String name = "";


            if (desc != null) {
                name = desc.name().trim();

                if (name.length() < 1) {
                    name = desc.value().trim();
                }

                if (name.length() > 0) {
                    //public static final
                    name = replaceText(name);
                    fieldMap.put(name, "\n    final String " + name + " = \"" + fieldName + "\"; //类字段描述 \n");
                }
            }

            if (!fieldName.equals(name)) {
                fieldMap.put(fieldName, "\n    final String " + fieldName + " = \"" + fieldName + "\"; //类字段名  \n");
            }

            String fieldTableColName = "T_" + fieldName;

            String tableColName = fieldName;

            if (subEle.getAnnotation(Column.class) != null) {

                if (hasText(subEle.getAnnotation(Column.class).name())) {
                    tableColName = subEle.getAnnotation(Column.class).name();
                }

            } else if (subEle.getAnnotation(JoinColumn.class) != null) {
                if (hasText(subEle.getAnnotation(JoinColumn.class).name())) {
                    tableColName = subEle.getAnnotation(JoinColumn.class).name();
                }
            }

            fieldMap.put(fieldTableColName, "\n    @Deprecated\n    final String " + fieldTableColName + "  = \"" + tableColName + "\"; //字段" + name + " 对应的数据库列名，建议使用 F_" + fieldName + " 替代\n");

            fieldMap.put("F_" + fieldName, "\n    final String F_" + fieldName + "  = \"F$:" + fieldName + "\"; //用于替换的名称，替换字段" + name + " 对应的数据库列名 \n");

            boolean isIdAttr = subEle.getAnnotation(Id.class) != null || subEle.getAnnotation(EmbeddedId.class) != null;

            if (isIdAttr) {
                //   codeBlock.append("\n    String ").append("PK_ID").append(" = \"").append(fieldName).append("\";\n");

                fieldMap.put("PK_ID", "\n    final String PK_ID = \"" + fieldName + "\"; //主键字段名 \n");
                fieldMap.put("F_PK_ID", "\n    final String F_PK_ID = \"F$:" + fieldName + "\"; //主键字段名 \n");
                fieldMap.put("T_PK_ID", "\n    final String T_PK_ID = \"" + tableColName + "\"; //主键字段对应的数据库列名 \n");

                uniqueFields.add(fieldName);
            }

            //@todo 实现组合的唯一约束
            if (isUniqueField(subEle)
                    && !uniqueFields.contains(fieldName)) {
                uniqueFields.add(fieldName);
            }

        }


        //写出字段
        for (String line : fieldMap.values()) {
            codeBlock.append(line);
        }


        if (uniqueFields.size() > 0) {

            codeBlock.append("\n    List<String> uniqueFields =  Collections.unmodifiableList(Arrays.asList(");

            int i = 0;
            for (String uniqueField : uniqueFields) {
                codeBlock.append(i++ > 0 ? "," : "")
                        .append("\"").append(uniqueField).append("\"");
            }

            codeBlock.append("));\n\n")
                    .append("    default boolean isUnique(String field) {\n")
                    .append("        return uniqueFields.contains(field);\n")
                    .append("    }\n");

        }
    }

    private String replaceText(String name) {
        for (char replaceChar : replaceChars) {
            name = name.replace(replaceChar, '_');
        }
        return name;
    }

    boolean isUniqueField(Element subEle) {

        return isUniqueField(subEle.getAnnotation(Column.class)
                , subEle.getAnnotation(JoinColumn.class)
                , subEle.getAnnotation(MapKeyJoinColumn.class)
                , subEle.getAnnotation(MapKeyColumn.class));

    }

    boolean isUniqueField(Annotation... annotations) {

        if (annotations != null) {

            for (Annotation annotation : annotations) {
                try {
                    if (annotation == null) {
                        continue;
                    }

                    boolean unique = (boolean) annotation.getClass().getDeclaredMethod("unique").invoke(annotation);
                    if (unique) {
                        return true;
                    }
                } catch (Exception e) {
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {

        System.out.println(newClassName(String.class.getName()));

    }

}
