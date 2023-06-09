package com.levin.commons.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public abstract class LangUtils {

    //识别中文
    public static final Pattern zhCn = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 是否中文
     *
     * @param c
     * @return
     */
    public static boolean isZh(char c) {
        return c >= '\u4e00' && c <= '\u9fa5';
    }

    /**
     * 分割标题和描述
     *
     * @param desc
     * @return
     */
    public static String[] splitDesc(String desc) {

        desc = StringUtils.trimAllWhitespace(desc);

        String[] result = new String[]{desc, ""};

        int i = 0;
        for (; i < desc.length(); i++) {
            char c = desc.charAt(i);
            if (Character.isLetterOrDigit(c) || isZh(c)) {
            } else {
                break;
            }
        }

        if (i >= (desc.length() - 1)) {
            return result;
        }

        //1 2 3 中
        result[0] = desc.substring(0, i).trim();

        result[1] = StringUtils.trimAllWhitespace(desc.substring(i + 1))
                .replace("\n", " ")
                .replace("\r", " ")
                .replace('"', '\'');

        return result;
    }


    public static void main(String[] args) {

        String[] desc = splitDesc("标q题1 买书刚刚");
        desc = splitDesc("a标题1: 买书\n刚刚");
        desc = splitDesc("标题1 ");
        desc = splitDesc("标题1\n 回车");
        desc = splitDesc("a标题1，买书刚刚");

    }
}