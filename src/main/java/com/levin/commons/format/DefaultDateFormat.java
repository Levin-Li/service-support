package com.levin.commons.format;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

/**
 * 日期格式化类
 * <p>
 * 自动解析多种样式
 *
 * <p>
 * 调用hutool解析类，方便 json参数上传转换
 */

public class DefaultDateFormat extends DateFormat {

    final String format;

    public DefaultDateFormat() {
        this(null);
    }

    public DefaultDateFormat(String format) {

        if (StrUtil.isBlank(format)) {
            format = DatePattern.NORM_DATETIME_PATTERN;
        }

        this.format = format;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {

        toAppendTo.append(DateUtil.format(date, format));

        return toAppendTo;
    }

    @Override
    public Date parse(String source, ParsePosition pos) {

        Date result = null;

        DateTime parse = DateUtil.parse(source);

        if (parse != null) {
            result = parse.toJdkDate();
        }

        //非常重要，标记为解析完成
        pos.setIndex(source.length());

        return result;
    }

}
