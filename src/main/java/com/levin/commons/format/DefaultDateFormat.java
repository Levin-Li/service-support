package com.levin.commons.format;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

/**
 * 日期格式化类
 * <p>
 * 调用hutool解析类，方便 json参数上传转换
 */
public class DefaultDateFormat extends StdDateFormat {
    @Override
    protected Date _parseDate(String dateStr, ParsePosition pos) throws ParseException {

        Date date = super._parseDate(dateStr, pos);

        if (date == null) {
            return DateUtil.parse(dateStr).toJdkDate();
        }
        return null;
    }
}
