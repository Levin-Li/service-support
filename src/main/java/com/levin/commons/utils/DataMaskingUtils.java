package com.levin.commons.utils;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;

public abstract class DataMaskingUtils {

    /**
     * 简单的数据编码
     *
     * @return
     */
    public static String simpleMergeEncode(String key, String data) {

        if (StrUtil.isBlank(key) || StrUtil.isBlank(data)) {
            return data;
        }

        StringBuilder result = new StringBuilder();

        String maxStr = data.length() > key.length() ? data : key;

        int i = 0, end = Math.min(data.length(), key.length());

        while (i < end) {

            int mode = i % 11;
            //偶数用加，奇数用减
            mode = (i % 2) == 0 ? mode : -mode;

            result.append(add(data.charAt(i), mode));
            result.append(add(key.charAt(i), mode));
            i++;
        }

        while (i < maxStr.length()) {

            int mode = i % 7;
            //偶数用加，奇数用减
            mode = (i % 2) == 0 ? mode : -mode;

            result.append(add(maxStr.charAt(i), mode));
            i++;
        }

        return Base64Utils.encodeToUrlSafeString(result.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 用字符加减实现
     *
     * @param a
     * @param b
     * @return
     */
    private static char add(char a, int b) {
        return (b < 0 && a < Math.abs(b)) ? a : (char) (a + b);
    }

    /**
     * 数据解码
     *
     * @param key
     * @param data
     * @return
     */
    public static String simpleMergeDecode(String key, String data) {

        if (StrUtil.isBlank(key) || StrUtil.isBlank(data)) {
            return data;
        }

        data = new String(Base64Utils.decodeFromUrlSafeString(data), StandardCharsets.UTF_8);

        StringBuilder result = new StringBuilder();

        int dataLen = data.length() - key.length();

        Assert.isTrue(dataLen > 0, "解码失败0");

        int end = Math.min(dataLen, key.length()) * 2;

        int i = 0;
        while (i < end) {
            result.append(data.charAt(i));
            i += 2;
        }

        end = end / 2;

        if (dataLen > key.length()) {
            while (i < data.length()) {
                result.append(data.charAt(i));
                i++;
            }
        }

        data = result.toString();

        result = new StringBuilder();

        for (i = 0; i < data.length(); i++) {

            int mode = i % (i < end ? 11 : 7);

            //偶数用加，奇数用减
            mode = (i % 2) == 0 ? -mode : mode;

            result.append(add(data.charAt(i), mode));
        }

        return result.toString();
    }

}
