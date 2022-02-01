package com.levin.commons.service.support;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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

    String type;

    String refType = "";

    List<String> enums;

    Object items;

    String ref;

    boolean isProps;

    String description;


    public String getTypeInfo() {

        if (type.equalsIgnoreCase("String")) {
            return "String";
        } else if (type.endsWith("[]")) {
            return type.toLowerCase().startsWith("string") ? "String[]" : type;
        } else if (type.equalsIgnoreCase("Number")) {
            return "double";
        } else if (type.equalsIgnoreCase("Boolean")) {
            return "boolean";
        } else
            return Character.toUpperCase(type.charAt(0)) + type.substring(1);
    }

    public String defaultValue() {

        if (type.equalsIgnoreCase("String")) {
            return "default \"\"";
        } else if (type.endsWith("[]")) {
            return "default {}";
        } else if (type.equalsIgnoreCase("Number")) {
            return "default 0";
        } else if (type.equalsIgnoreCase("Boolean")) {
            return "default false";
        } else
            return "";
    }

    final Map<String, DataType> properties = new LinkedHashMap<>();

    final Map<String, String> descriptions = new LinkedHashMap<>();

    public String toString() {
        if (!isProps) {
            return name + " : " + type + ":" + properties.values();
        } else {
            return name + ":" + type;
        }
    }
}
