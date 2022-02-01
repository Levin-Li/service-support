package com.levin.commons.service.support;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.levin.commons.utils.MapUtils;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

class SchemaTest {

    @SneakyThrows
    @Test
    void get() {

        Gson gson = new Gson();

        String schema = "Schema";

        JsonReader jsonReader = gson.newJsonReader(new FileReader("schema.json"));

        JsonObject root = (JsonObject) JsonParser.parseReader(jsonReader);

        JsonObject definitions = root.getAsJsonObject("definitions");

        Map<String, DataType> dataTypes = new LinkedHashMap<>();

        Map<String, JsonObject> comTypes = new LinkedHashMap<>();

        // parse(definitions, dataTypes);

        definitions.entrySet().stream()
//                .filter(item -> item.getKey().contains(schema))
                .forEach(item -> {
                            String name = item.getKey().replace(schema, "");
                            comTypes.put(name, (JsonObject) item.getValue());
                        }
                );


        parse(false, comTypes, dataTypes, null);

        File dir = new File("src/main/java/com/levin/commons/ui/annotation/amis");

        dir.mkdirs();

        for (Map.Entry<String, DataType> entry : dataTypes.entrySet()) {

            String name = entry.getKey();

            Map<String, Object> params = MapUtils
                    .putFirst("ui", entry.getValue())
                    .put("name", name)
                    .build();

            Writer hWriter = new OutputStreamWriter(new FileOutputStream(new File(dir, name + ".java")), "utf-8");

            System.out.println("开始生成 " + name);

            try {
                getTemplate("UIAnnotation.java").process(params, hWriter);
            } finally {
                hWriter.close();
            }

        }

    }


    private Map<String, DataType> parse(boolean isProps, Map<String, JsonObject> definitions, Map<String, DataType> dataTypes, Map<String, DataType> props) {

        String schema = "Schema";

        definitions.entrySet().stream().forEach(item -> {

            String name = item.getKey();

            if (name.contains(schema)) {
                name = name.replace(schema, "");
            }

            if (name.equals("char")) {
                name = "_" + name;
            }

            if(name.contains("-") ){
                return;
            }

            JsonObject value = item.getValue();

            String type = "";

            JsonArray anEnum = value.getAsJsonArray("enum");
            JsonElement typeDesc = value.get("type");
            JsonElement ref = value.get("$ref");

            JsonElement anyOf = value.get("anyOf");

            JsonElement description = value.get(DataType.Fields.description);
            JsonObject properties = value.getAsJsonObject(DataType.Fields.properties);

            StringBuilder addInfo = new StringBuilder();

            boolean isEnum = false;

            DataType dataType = new DataType()
                    .setProps(isProps);

            if (typeDesc != null
                    && typeDesc.isJsonPrimitive()) {
                type = typeDesc.toString();
                if (anEnum != null) {
                    type = "string";
                    isEnum = true;
                    addInfo.append("枚举值：" + anEnum.toString());
                }
            }

            if (ref != null) {
                if (ref.isJsonPrimitive()) {

                    type = ref.getAsString();

                    if (type.contains(DataType.TYPE_PREFIX)) {
                        type = type.replace(DataType.TYPE_PREFIX, "");
                    } else if (type.contains(DataType.TYPE_PREFIX_2)) {
                        type = type.replace(DataType.TYPE_PREFIX_2, "");
                    } else {
                        throw new RuntimeException(type);
                    }

                    type = type.replace(schema, "");

                } else {
                    System.err.println(" " + ref);
                }
            }

            if (!StringUtils.hasText(type) && anyOf != null) {
                //默认取第一个类型
                JsonElement element = anyOf.getAsJsonArray().get(0).getAsJsonObject().get(DataType.Fields.type);

                if (element != null) {
                    type = element.getAsString();
                }
            }

            if (!StringUtils.hasText(type)
                    && typeDesc != null
                    && typeDesc.isJsonArray()) {
                type = typeDesc.getAsJsonArray().toString().contains("string") ? "string" : typeDesc.getAsJsonArray().get(0).getAsString();
            }

            if (type != null) {
                type = type.replace("\"", "");
            }

            if (!StringUtils.hasText(type)) {
                type = "string";
            } else if (type.trim().equalsIgnoreCase("array")) {
                type = "string[]";
            } else if (dataTypes.containsKey(type)) {

                while (dataTypes.containsKey(type)) {

                    DataType refDataType = dataTypes.get(type);

                    if (refDataType == null) {
                        break;
                    }

                    type = refDataType.type;

                    if (refDataType.isPrimitive) {
                        dataType.refType = refDataType.type;
                        break;
                    }
                }

            } else {
                System.out.println(item.getKey() + " --- type --- : " + type);
            }

            type = type.replace("\"", "");

            if (type.equalsIgnoreCase("object")) {
                type = "String";
            }

            dataType.setProps(isProps)
                    .setType(type)
                    .setRef(ref != null ? ref.toString() : value.toString())
                    .setEnum(isEnum);

            dataType.isPrimitive = Arrays.asList("string", "boolean", "number").stream().anyMatch(type::equalsIgnoreCase);

            if (properties != null) {

                Map<String, JsonObject> map = new LinkedHashMap<>();

                properties.entrySet().forEach(it -> {
                    map.put(it.getKey(), (JsonObject) it.getValue());

                    JsonElement jsonElement = it.getValue().getAsJsonObject().get(DataType.Fields.description);

                    if (jsonElement != null) {
                        dataType.descriptions.put(it.getKey(), replace(jsonElement.getAsString()));
                    }

                });

                try {
                    dataType.properties.putAll(parse(true, map, dataTypes, new LinkedHashMap<>()));
                } catch (Exception exception) {
                    System.out.println("parse " + name);
                    exception.printStackTrace();
                }
            }

            if (description != null) {
                dataType.setDescription(replace(description.toString() + addInfo));
            }

            if (StringUtils.hasText(type)
                    && !name.equalsIgnoreCase("$ref")) {

                if (isProps) {
                    props.put(name, dataType);
                } else {
                    dataTypes.put(name, dataType);
                }

                dataType.setName(name);

                if (type.equalsIgnoreCase("Expression")) {
                    System.out.println(dataType);
                }

                if (!isProps) {
                }
            } else {
                if (!isProps)
                    System.out.println("unknown item " + item);
            }
        });

        return isProps ? props : dataTypes;
    }

    private static String replace(String info) {
        return info.replace("\\", "\"")
                 .replace("\n", "\\n")
                .replace("\"", "\\\"")
                .replace("\r", "\\n");
    }


    private static Template getTemplate(String templatePath) throws IOException {

        //freemark 模板路径只支持正斜杠
        templatePath = templatePath.replace("\\", "/").replace("//", "/");

        //创建一个合适的Configuration对象
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        DefaultObjectWrapper objectWrapper = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28).build();
        configuration.setObjectWrapper(objectWrapper);

        //这个一定要设置，不然在生成的页面中 会乱码
        configuration.setDefaultEncoding("UTF-8");

        //支持从jar中加载模板
        configuration.setClassForTemplateLoading(ContextHolder.class, "/");
        //获取页面模版。
        return configuration.getTemplate(MessageFormat.format("/template/{0}", templatePath));
    }

}