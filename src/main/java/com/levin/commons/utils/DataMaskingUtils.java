package com.levin.commons.utils;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

public abstract class DataMaskingUtils {

    private static final int LZW_MAX_TABLE_SIZE = 4096;

    /**
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

        return result.toString();
    }

    private static char add(char a, int b) {
        return (b < 0 && a < Math.abs(b)) ? a : (char) (a + b);
    }

    /**
     * 合并解码
     *
     * @param key
     * @param data
     * @return
     */
    public static String simpleMergeDecode(String key, String data) {

        if (StrUtil.isBlank(key) || StrUtil.isBlank(data)) {
            return data;
        }

        StringBuilder result = new StringBuilder();

        int dataLen = data.length() - key.length();

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


    /**
     * LZW压缩（Lempel-Ziv-Welch Compression）
     *
     * @param input
     * @return
     */ // LZW压缩方法
    public static String compress(String input) {
        Map<String, Integer> dictionary = new HashMap<>();
        int dictSize = 256;
        for (int i = 0; i < 256; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }

        StringBuilder w = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            String wc = w.toString() + c;
            if (dictionary.containsKey(wc)) {
                w = new StringBuilder(wc);
            } else {
                result.append(dictionary.get(w.toString()));
                dictionary.put(wc, dictSize++);
                w = new StringBuilder(String.valueOf(c));
            }
        }

        if (w.length() > 0) {
            result.append(dictionary.get(w.toString()));
        }

        return result.toString();
    }

    // LZW解压缩方法
    public static String decompress(String compressed) {
        Map<Integer, String> dictionary = new HashMap<>();
        int dictSize = 256;
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, String.valueOf((char) i));
        }

        String[] codes = compressed.split("(?<=\\G.{1})");
        StringBuilder w = new StringBuilder(dictionary.get(Integer.parseInt(codes[0])));
        StringBuilder result = new StringBuilder(w);

        for (int i = 1; i < codes.length; i++) {
            int current = Integer.parseInt(codes[i]);
            String entry;
            if (dictionary.containsKey(current)) {
                entry = dictionary.get(current);
            } else if (current == dictSize) {
                entry = w.toString() + w.charAt(0);
            } else {
                throw new IllegalArgumentException("Bad compressed k: " + current);
            }

            result.append(entry);

            dictionary.put(dictSize++, w.toString() + entry.charAt(0));
            w = new StringBuilder(entry);
        }

        return result.toString();
    }


    /**
     * 运行长度编码（Run-Length Encoding, RLE）
     * RLE是一种简单的压缩算法，通过将连续的相同字符替换为一个字符和其出现次数来减少数据长度。适用于包含大量重复字符的文件，如简单位图、ASCII文本文件等
     *
     * @param input
     * @return
     */
    public static String rleCompress(String input) {

        StringBuilder compressed = new StringBuilder();

        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                count++;
            } else {
                compressed.append(input.charAt(i - 1)).append(count);
                count = 1;
            }
        }

        compressed.append(input.charAt(input.length() - 1)).append(count);

        return compressed.toString();

    }

    public static String rleDecompress(String input) {

        StringBuilder decompressed = new StringBuilder();

        for (int i = 0; i < input.length(); i += 2) {
            char character = input.charAt(i);
            int count = Integer.parseInt(String.valueOf(input.charAt(i + 1)));
            for (int j = 0; j < count; j++) {
                decompressed.append(character);
            }
        }

        return decompressed.toString();
    }

}
