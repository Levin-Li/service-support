package com.levin.commons.amis;

import com.google.gson.JsonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Data
@Accessors(chain = true)
@FieldNameConstants
@EqualsAndHashCode(of = "name")
public class DataType {

    public static final String TYPE_PREFIX = "#/definitions/Schema";
    public static final String TYPE_PREFIX_2 = "#/definitions/";

    String name;

    boolean isPrimitive;

    boolean isEnum;

    boolean isArray;

    String type;

    String refType = "";

    List<String> enums;

    Object items;

    Object ref;

    Object anyOf;

    List<String> consts;

    public boolean hasConsts() {
        return consts != null && !consts.isEmpty();
    }

    public String getConsts() {
        return consts.stream().collect(Collectors.joining("\", \"", "\"", "\""));
    }

    boolean isProps;

    String description;

    public boolean hasValueKey() {
        return properties.containsKey("value");
    }

    public String getEnums() {

        StringBuilder builder = new StringBuilder();

        String HEAD_LINE = "\n\t\t";

        enums.stream()
                .filter(StringUtils::hasText)
                .filter(txt -> Character.isLetter(txt.charAt(0)))
                .map(DataType::replaceKeywords)
                .forEach(e -> builder.append(HEAD_LINE).append(e.replace('-', '_')).append(","));

        return builder.toString();
    }


    public static String replaceKeywords(String txt) {

        List<String> aa = Arrays.asList("char", "boolean", "double", "float", "int", "default");

        return (aa.contains(txt)) ? "__" + txt : txt;
    }


    public String getEnumDefines() {

        StringBuilder builder = new StringBuilder();

        String HEAD_LINE = "\n\t\t";
        String HEAD_LINE1 = "\n\t";

        properties.values().stream()
                .filter(DataType::isEnum)
                .filter(p -> p.enums != null && p.enums.size() > 0)
                .forEach(p -> {

                    builder.append(HEAD_LINE1).append("//").append(descriptions.get(p.name));

                    builder.append(HEAD_LINE1).append("enum ").append(capFirst(p.isArray() ? getSingular(p.name) : p.name)).append("{");

                    p.enums.stream()
                            .filter(StringUtils::hasText)
                            .filter(txt -> Character.isLetter(txt.charAt(0)))
                            .map(DataType::replaceKeywords)
                            .forEach(e -> builder.append(HEAD_LINE).append(e.replace('-', '_')).append(","));

                    builder.append(HEAD_LINE).append(";\n")
                            .append(HEAD_LINE).append("@Override")
                            .append(HEAD_LINE).append("public String toString() { return super.toString().replace('_', '-'); }");

                    builder.append(HEAD_LINE1).append("}\n");

                });

        if (builder.length() > 0) {
            // System.out.println(builder);
        }

        return builder.toString();
    }

    public static String capFirst(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    public String getTypeInfo() {
        return isArray() ? (getSingular(_getTypeInfo()) + "[]") : _getTypeInfo();
    }

    public String _getTypeInfo() {
        if (isEnum()) {
            return capFirst(name);
        } else if (type.equalsIgnoreCase("String")) {
            return "String";
        } else if (type.equalsIgnoreCase("Number")) {
            return "double";
        } else if (type.equalsIgnoreCase("Boolean")) {
            return "boolean";
        } else {

            String type = replace(Character.toUpperCase(this.type.charAt(0)) + this.type.substring(1));

            if (type.startsWith("Property_") || type.startsWith("Moment_")) {
                return "String";
            }

            return type;
        }
    }

    public static String replace(String name) {

        name = name.replace('.', '_');

        int tmpIdx = name.indexOf('<');

        //如果有泛型，要去除泛型
        if (tmpIdx != -1) {
            name = name.substring(0, tmpIdx);
        }

        tmpIdx = name.indexOf("%3C");

        //如果有泛型，要去除泛型
        if (tmpIdx != -1) {
            name = name.substring(0, tmpIdx);
        }

        return replaceKeywords(name);
    }


    public String defaultValue() {

        if (type.equalsIgnoreCase("String")) {

            if (ref instanceof JsonObject && ((JsonObject) ref).has("const")) {
                return "default " + ((JsonObject) ref).get("const") + "";
            }

            if (isEnum) {
                return "";
            }

            //默认值是 \t 表示 不需要出现的值
            return "default \"\t\"";

        } else if (type.endsWith("[]")) {
            return "default {}";
        } else if (type.equalsIgnoreCase("Number")) {
            return "default 0";
        } else if (type.equalsIgnoreCase("Boolean")) {
            return "default false";
        } else {
            return "";
        }
    }

    /**
     * 获取单词的单数形式
     *
     * @param words
     * @return
     */
    public static String getSingular(String words) {
        return Inflector.getInstance().singularize(words);
    }

    final Map<String, DataType> properties = new LinkedHashMap<>();

    final Map<String, String> descriptions = new LinkedHashMap<>();

    @Override
    public String toString() {
        if (!isProps) {
            return name + " : " + type + ":" + properties.values();
        } else {
            return name + ":" + type;
        }
    }
}
