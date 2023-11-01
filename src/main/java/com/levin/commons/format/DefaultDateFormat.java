package com.levin.commons.format;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期格式化类
 * <p>
 * 自动解析多种样式
 *
 * <p>
 * 调用hutool解析类，方便 json参数上传转换
 */

public class DefaultDateFormat extends DateFormat implements Formatter<Date> {

    final String format;

    private Locale locale;

    public DefaultDateFormat() {
        this(null);
    }

    public DefaultDateFormat(Locale locale) {
        this(null, locale);
    }

    public DefaultDateFormat(String format, Locale locale) {

        if (StrUtil.isBlank(format)) {
            format = DatePattern.NORM_DATETIME_PATTERN;
        }

        this.locale = locale;

        this.format = format;

        this.initialize();
    }

    private void initialize() {

        if (this.locale == null) {
            this.locale = Locale.getDefault(Locale.Category.FORMAT);
        }

        if (calendar == null) {
            calendar = Calendar.getInstance(this.locale);
        }

        if (numberFormat == null) {
            numberFormat = NumberFormat.getIntegerInstance(this.locale);
        }
    }

    @Override
    public DefaultDateFormat clone() {
        return new DefaultDateFormat(this.format, this.locale);
    }

    @Override
    public Date parse(String date, Locale locale) throws ParseException {
        //忽略 locale
        return parse(date);
    }

    @Override
    public String print(Date date, Locale locale) {
        //忽略 locale
        return format(date);
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {

        toAppendTo.append(DateUtil.format(date, format));

        return toAppendTo;
    }

    /**
     * 解析顺序
     * <p>
     * yyyy-MM-dd HH:mm:ss
     * yyyy/MM/dd HH:mm:ss
     * yyyy.MM.dd HH:mm:ss
     * yyyy年MM月dd日 HH时mm分ss秒
     * yyyy-MM-dd
     * yyyy/MM/dd
     * yyyy.MM.dd
     * HH:mm:ss
     * HH时mm分ss秒
     * yyyy-MM-dd HH:mm
     * yyyy-MM-dd HH:mm:ss.SSS
     * yyyy-MM-dd HH:mm:ss.SSSSSS
     * yyyyMMddHHmmss
     * yyyyMMddHHmmssSSS
     * yyyyMMdd
     * EEE, dd MMM yyyy HH:mm:ss z
     * EEE MMM dd HH:mm:ss zzz yyyy
     * yyyy-MM-dd'T'HH:mm:ss'Z'
     * yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     * yyyy-MM-dd'T'HH:mm:ssZ
     * yyyy-MM-dd'T'HH:mm:ss.SSSZ
     *
     * @param source The date/time string to be parsed
     * @param pos    On input, the position at which to start parsing; on
     *               output, the position at which parsing terminated, or the
     *               start position if the parse failed.
     * @return
     */
    @Override
    public Date parse(String source, ParsePosition pos) {

        Date result = null;

        if (StrUtil.isBlank(source)) {
            return null;
        }

        //source = StringUtils.trimWhitespace(source);

        //如果都是数字
        if (source.trim().chars().allMatch(Character::isDigit)) {
            //时间戳（秒）
            //95640525954 秒
            //1695474929406
            //1695480398236
            // 无法区分时间戳的秒和毫秒，不支持处理
        }

        DateTime parse = DateUtil.parse(source);
        if (parse != null) {
            result = parse.toJdkDate();
        }

        //非常重要，标记为解析完成
        pos.setIndex(source.length());

        return result;
    }

}
