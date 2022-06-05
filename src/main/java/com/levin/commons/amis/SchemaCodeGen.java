package com.levin.commons.amis;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.levin.commons.service.support.ContextHolder;
import com.levin.commons.utils.MapUtils;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Predicate;

@Data
@Accessors(chain = true)
public class SchemaCodeGen {

    String outDir;

    String schemaFile;

    public static void main(String[] args) {
        new SchemaCodeGen().genCode();
    }

    @SneakyThrows
    public void genCode() {

        Gson gson = new Gson();

        String schema = "Schema";

        JsonReader jsonReader = gson.newJsonReader(new FileReader(StringUtils.hasText(schemaFile) ? schemaFile : "src/main/resources/json/schema/amis.schema.json"));

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

        final Predicate<String> isPrimitive = type -> Arrays.asList("string", "boolean", "number").stream().anyMatch(type::equalsIgnoreCase);


        Set<String> typeMapSet = new HashSet<>();

        //再处理一次类型
        dataTypes.entrySet().stream().forEachOrdered(item -> {

            DataType dataType = item.getValue();

            dataType.properties.values().stream().forEachOrdered(p -> {

                        String type = p.type;

                        StringBuilder info = new StringBuilder();

                        info.append(dataType.name).append(" --> ").append(type).append(p.isArray ? "[]" : "");

                        while (dataTypes.containsKey(type)
                                && dataTypes.get(type).properties.isEmpty()) {

                            DataType refDataType = dataTypes.get(type);

                            if (refDataType == null) {
                                break;
                            }

                            type = refDataType.type;

                            if (refDataType.isArray) {
                                p.setArray(true);
                            }

                            info.append(" --> ").append(type).append(p.isArray ? "[]" : "");

                            if (isPrimitive.test(type)) {
                                dataType.refType = refDataType.type;
                                break;
                            }
                        }

                        if (!p.type.equals(type)) {
                            typeMapSet.add(info.toString());
                        }

                        p.type = type;
                        p.isPrimitive = isPrimitive.test(type);

                        if (dataType.name.equals(p.type)) {
                            //如果是嵌套对象
                            p.type = "String";
                        }
                    }
            );

        });

        //打印类型转换
        typeMapSet.forEach(txt -> System.out.println(txt));

        File dir = new File(StringUtils.hasText(outDir) ? outDir : "src/main/java/com/levin/commons/ui/annotation/amis");

        dir.mkdirs();

        System.out.println("输出路径：" + dir);

        for (Map.Entry<String, DataType> entry : dataTypes.entrySet()) {

            String name = entry.getKey();

            DataType dataType = entry.getValue();

            Map<String, Object> params = MapUtils
                    .putFirst("ui", dataType)
                    .put("name", name)
                    .build();

            Writer hWriter = new OutputStreamWriter(new FileOutputStream(new File(dir, name.replace('.', '_') + ".java")), "utf-8");

            System.out.println("开始生成 " + name);

            try {
                if (dataType.isEnum) {
                    getTemplate("UIEnum.java").process(params, hWriter);
                } else {
                    getTemplate("UIAnnotation.java").process(params, hWriter);
                }
            } finally {
                hWriter.close();
            }

        }

    }

    private List<String> toList(JsonArray array) {

        List<String> list = new ArrayList<>();

        Iterator<JsonElement> iterator = array.iterator();

        while (iterator.hasNext()) {
            list.add(iterator.next().getAsString());
        }

        return list;
    }


    private Map<String, DataType> parse(boolean isProps, Map<String, JsonObject> definitions, Map<String, DataType> dataTypes, Map<String, DataType> props) {

        String schema = "Schema";

        Predicate<String> isPrimitive = type -> Arrays.asList("string", "boolean", "number").stream().anyMatch(type::equalsIgnoreCase);

        definitions.entrySet().stream().forEach(item -> {

            String name = item.getKey();

            if (name.contains(schema)) {
                name = name.replace(schema, "");
            }

            name = DataType.replace(name);

            if (name.contains("-")) {
                return;
            }

            JsonObject value = item.getValue();

            String type = "";

            JsonElement typeDesc = value.get("type");
            JsonArray anEnum = value.getAsJsonArray("enum");
            JsonElement ref = value.get("$ref");

            boolean isArray = typeDesc != null && typeDesc.isJsonPrimitive() && typeDesc.getAsString().contentEquals("array");

            if (isArray) {
                JsonObject items = value.getAsJsonObject("items");
                ref = items.get("$ref");
                if (items.has("type")) {
                    typeDesc = items.get("type");
                }
                if (items.has("enum")) {
                    anEnum = items.getAsJsonArray("enum");
                }
            }

            JsonElement anyOf = value.get("anyOf");


            JsonElement description = value.get(DataType.Fields.description);
            JsonObject properties = value.getAsJsonObject(DataType.Fields.properties);

            StringBuilder addInfo = new StringBuilder();

            boolean isEnum = false;

            DataType dataType = new DataType()
                    .setArray(isArray)
                    .setAnyOf(anyOf)
                    .setProps(isProps);

            if (typeDesc != null
                    && typeDesc.isJsonPrimitive()) {
                type = typeDesc.getAsString();
                if (anEnum != null) {
                    type = "string";
                    isEnum = true;
                    addInfo.append("枚举值：" + anEnum.toString());
                    dataType.enums = toList(anEnum);
                }
            }

            if (ref != null) {
                if (ref.isJsonPrimitive()) {
                    type = getType(schema, ref);
                } else {
                    System.err.println(" " + ref);
                }
            }

            if (anyOf != null) {

                List<String> tmp = new ArrayList<>(5);

                for (JsonElement ele : anyOf.getAsJsonArray()) {
                    JsonElement aConst = ((JsonObject) ele).get("const");
                    if (aConst != null) {
                        tmp.add(aConst.getAsString());
                    }
                }

                if (!tmp.isEmpty()) {
                    dataType.consts = tmp;
                    System.out.println(name + " 常量 consts：" + tmp);
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
            }

            type = type.replace("\"", "");

            if (type.equalsIgnoreCase("object")) {
                type = "String";
            } else if (type.contentEquals("array")) {
                dataType.setArray(isArray = true);
                type = "String";
            }

            dataType.setProps(isProps)
                    .setType(type)
                    .setRef(ref != null ? ref : value)
                    .setEnum(isEnum);

            dataType.isPrimitive = isPrimitive.test(type);

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

                if (!isProps) {
                }
            } else {
                if (!isProps) {
                    System.out.println("unknown item " + item);
                }
            }
        });

        return isProps ? props : dataTypes;

    }

    private String getType(String schema, JsonElement ref) {

        String type;

        type = ref.getAsString();

        if (type.contains(DataType.TYPE_PREFIX)) {
            type = type.replace(DataType.TYPE_PREFIX, "");
        } else if (type.contains(DataType.TYPE_PREFIX_2)) {
            type = type.replace(DataType.TYPE_PREFIX_2, "");
        } else {
            throw new RuntimeException(type);
        }

        type = type.replace(schema, "");

        return type;
    }

    private static String replace(String info) {
        return info.replace("\\", "")
                .replace("\n", "\\n")
//                .replace("\"", "\\\"")
                .replace("\"", "")
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