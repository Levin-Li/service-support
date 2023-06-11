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
     * <p>
     * 方法不放回 null值，只放回 空字符串
     *
     * @param desc
     * @return 分割结果数组， [0] 是标题，不为空 ，[1] 是描述，可为空
     */
    public static String[] splitDesc(String desc) {

        String[] result = new String[]{"", ""};

        if (!StringUtils.hasText(desc)) {
            return result;
        }

        desc = StringUtils.trimAllWhitespace(desc);

        int start = 0;

        while (!(Character.isLetterOrDigit(desc.charAt(start)) || isZh(desc.charAt(start)))) {
            start++;
        }

        //去除前面非文字字符
        if (start > 0) {
            desc = desc.substring(start);
        }

        result[0] = desc;

        for (start = 0; start < desc.length(); start++) {
            char c = desc.charAt(start);
            if (Character.isLetterOrDigit(c) || isZh(c)) {
            } else {
                break;
            }
        }

        if (start <= 0 || start >= (desc.length() - 1)) {
            return result;
        }

        //1 2 3 中
        result[0] = desc.substring(0, start).trim();

        result[1] = StringUtils.trimAllWhitespace(desc.substring(start + 1))
                .replace("\n", " ")
                .replace("\r", " ")
                .replace('"', '\'');

        return result;
    }
}
