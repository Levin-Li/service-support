package com.levin.commons.utils;

import com.levin.commons.service.domain.ClientAccessRequest;
import com.levin.commons.service.domain.Sign;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.DigestUtils;
import org.springframework.util.ReflectionUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.*;

public abstract class SignUtils {


    /**
     * 验证签名
     *
     * @param data
     * @param clientSecret
     * @param overrideConfigs
     * @return
     */
    public static boolean verifySign(ClientAccessRequest data, String clientSecret, Map<String, String>... overrideConfigs) {
        return sign(data, clientSecret, overrideConfigs).equals(data.getSign());
    }

    /**
     * 生成签名
     *
     * @param data
     * @param clientSecret
     * @return
     */
    public static String sign(ClientAccessRequest data, String clientSecret, Map<String, String>... overrideConfigs) {

        Map<String, String> signFields = getSignMap(data, clientSecret, overrideConfigs);

        String sign = md5Utf8Text(genUrlStr(signFields));

        data.setSign(sign);

        return sign;
    }


    public static Map<String, String> getSignMap(ClientAccessRequest data, String clientSecret, Map<String, String>... overrideConfigs) {

        Map<String, String> signFields = SignUtils.getAndSortSignFields(data);

        signFields.put("clientId", data.getClientId());
        signFields.put("clientSecret", clientSecret);
        signFields.put("timestamp", data.getTimestamp());
        signFields.put("channelCode", data.getChannelCode());

        if (overrideConfigs != null) {
            for (Map<String, String> overrideConfig : overrideConfigs) {
                signFields.putAll(overrideConfig);
            }
        }

        return signFields;
    }


    public static String md5Utf8Text(String data) {
        return md5(data, "utf-8");
    }

    public static String md5ISO8859_1Text(String data) {
        return md5(data, "ISO8859_1");
    }

    public static String md5(String data, String encoding) {
        try {
            return DigestUtils.md5DigestAsHex(data.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成 URL串
     *
     * @param data
     * @return
     */
    public static String genUrlStr(Map<String, String> data) {
        return genTextStr(data, true, "=", "&");
    }

    /**
     * 生成字符串
     *
     * @param data
     * @param needKey
     * @param keyValueDelim
     * @param itemDelim
     * @return
     */
    public static String genTextStr(Map<String, String> data, boolean needKey, String keyValueDelim, String itemDelim) {


        StringBuilder sb = new StringBuilder();


        boolean isFirst = true;

        for (Map.Entry<String, String> entry : data.entrySet()) {

            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(itemDelim);
            }

            if (needKey) {
                sb.append(entry.getKey()).append(keyValueDelim);
            }


            sb.append(entry.getValue());


        }

        return sb.toString();

    }


    public static Map<String, String> object2StringMap(Object data, int excludeModifiers) {
        return toStringMap(object2Map(data, excludeModifiers));
    }


    /**
     * @param stringObjectMap
     * @return
     */
    public static Map<String, String> toStringMap(Map<String, Object> stringObjectMap) {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
            map.put(entry.getKey(), "" + entry.getValue());
        }

        return map;
    }

    /**
     * 字段转 Map
     *
     * @param data
     * @param excludeModifiers
     * @return
     */
    public static Map<String, Object> object2Map(Object data, int excludeModifiers) {


        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        ReflectionUtils.doWithFields(data.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {

                if ((field.getModifiers() & excludeModifiers) != 0) {
                    return;
                }

                field.setAccessible(true);

                map.put(field.getName(), field.get(data));
            }
        });


        return map;
    }


    /**
     * 按顺序获取签名的字段及字段值
     *
     * @param data
     * @return
     */
    public static Map<String, String> getAndSortSignFields(Object data) {


        List<SortObj> objList = new ArrayList<>();

        ReflectionUtils.doWithFields(data.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {

                Sign sign = field.getAnnotation(Sign.class);

                if (sign == null) {
                    return;
                }

                field.setAccessible(true);

                SortObj obj = new SortObj().setName(field.getName()).setOrderValue(sign.order()).setValue(field.get(data));

                objList.add(obj);
            }
        });


        SortObj[] array = objList.toArray(new SortObj[objList.size()]);


        Arrays.sort(array);


        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        for (SortObj obj : array) {

            map.put(obj.name, "" + obj.value);
        }

        return map;

    }


    @Data
    @Accessors(chain = true)
    static class SortObj implements Comparable<SortObj> {

        String name;

        Object value;

        Comparable orderValue;

        @Override
        public int compareTo(SortObj o) {
            return orderValue.compareTo(o.orderValue);
        }

    }

}
