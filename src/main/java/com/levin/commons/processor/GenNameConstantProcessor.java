package com.levin.commons.processor;

import com.levin.commons.annotation.GenNameConstant;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.*;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.*;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@SupportedAnnotationTypes({
        "com.levin.commons.annotation.GenNameConstant",})
//@SupportedSourceVersion(SourceVersion.RELEASE_6)
//@com.google.auto.service.AutoService(Processor.class)
public class GenNameConstantProcessor extends AbstractProcessor {

    final Map<String, Object> processedFiles = new ConcurrentHashMap<>();

    public static final String CLASS_NAME_PREFIX = "E_";


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

        if (className == null || className.trim().length() < 1)
            return "";

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

                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, getClass().getSimpleName() + " <<<" + newFullClassName + ">>> already processed.");

                continue;
            } else {

                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, getClass().getSimpleName() + " Processing class " + fullClassName + " --> " + newSimpleClassName);

            }

            boolean useExtends = genFieldNameConstant == null || genFieldNameConstant.extendsMode();

            TypeMirror superclass = typeElement.getSuperclass();

            final String newSuperFullClassName = newClassName((superclass != null && !(superclass instanceof NoType)) ? superclass.toString() : "");

            boolean isRootParent = newSuperFullClassName.trim().length() < 1
                    || newSuperFullClassName.startsWith("java.")
                    || newSuperFullClassName.startsWith("javax.")
                    || newSuperFullClassName.equals(Object.class.getName());

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

                    .append((useExtends && !isRootParent && newSuperFullClassName.trim().length() > 0) ? (" , " + newSuperFullClassName) : "")

                    .append(" {\n\n")
                    .append("    String PACKAGE_NAME = \"").append(packageName).append("\"; // 类包名 \n\n")
                    .append("    String CLASS_NAME = \"").append(fullClassName).append("\"; // 类全名 \n\n")
                    .append("    String SIMPLE_CLASS_NAME = \"").append(simpleClassName).append("\"; // 类短名称 \n\n")

            ;

            //注解类也是接口
            ElementKind eKind = typeElement.getKind();
            boolean isInterface = eKind == ElementKind.ANNOTATION_TYPE || eKind == ElementKind.INTERFACE;
            boolean isClass = eKind == ElementKind.CLASS;

            GenNameConstant.Type genType = genFieldNameConstant.genType();

            List<? extends Element> enclosedElements = (isRootParent || eKind == ElementKind.ANNOTATION_TYPE || useExtends) ? typeElement.getEnclosedElements() : elementUtils.getAllMembers(typeElement);

            final Map<String, String> fieldMap = new LinkedHashMap<>();

            enclosedElements.stream()
                    //只支持方法
                    .filter(e -> {

                        ElementKind kind = e.getKind();

                        GenNameConstant tmp = e.getAnnotation(GenNameConstant.class);

                        if (tmp != null && tmp.ignore()) {
                            return false;
                        }

                        Set<Modifier> modifiers = e.getModifiers();

                        //过滤静态字段
                        if (modifiers.contains(Modifier.STATIC)
                                || modifiers.contains(Modifier.TRANSIENT)
                                || modifiers.contains(Modifier.NATIVE)) {
                            return false;
                        }


                        if (genType == GenNameConstant.Type.BOTH) {
                            return true;
                        } else if (genType == GenNameConstant.Type.AUTO) {
                            return (isInterface && kind == ElementKind.METHOD) || (isClass && kind == ElementKind.FIELD);
                        } else if (genType == GenNameConstant.Type.METHOD) {
                            return kind == ElementKind.METHOD;
                        } else if (genType == GenNameConstant.Type.FIELD) {
                            return kind == ElementKind.FIELD;
                        } else {
                            return false;
                        }

                    })
                    .forEach(e -> {
                        Name name = e.getSimpleName();
                        if (!fieldMap.containsKey(name.toString())) {
                            codeBlock.append("    String ").append(name).append(" = \"").append(name).append("\";\n\n");
                            fieldMap.put(name.toString(), "");
                        }
                    });


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


}
