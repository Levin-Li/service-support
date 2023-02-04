package com.levin.commons.utils;

import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * 异常处理工具类
 * 模块名称：
 * 功能说明：
 */

public abstract class ExceptionUtils {

    //识别中文
    static final Pattern zhCn = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 尝试获取描述中的中文部分，如果没有则放回原值
     * <p>
     * 描述的格式为：  XXX:YYY，其中XXX或是YYY都可以有中文，优先返回XXX
     *
     * @return
     */
    public static String getZhDesc(String tempDesc) {

        if (!StringUtils.hasText(tempDesc))
            return tempDesc;

        tempDesc = tempDesc.replace("\n", " ")
                .replace("\r", " ")
                .replace('"',' ');

        //尝试识别中文注释
        int idx = tempDesc.indexOf(':');

        if (idx < 0) {
            idx = tempDesc.indexOf('：');
        }

        if (idx < 0) {
            return tempDesc;
        }

        //分割出冒号前的内容
        String result = tempDesc.substring(0, idx);

        //如果不包含中文
        if (!zhCn.matcher(result).find()) {
            //分割出冒号后的内容
            tempDesc = tempDesc.substring(idx + 1);

            //如果包含中文
            if (zhCn.matcher(tempDesc).find()) {
                result = tempDesc;
            }
        }

        return StringUtils.trimAllWhitespace(result);
    }

    /**
     * 返回调用这个方法的堆栈追踪信息
     *
     * @return
     */
    public static StackTraceElement getInvokeThisMethodStackTrace() {
        return new Exception().getStackTrace()[1];
    }

    /**
     * 返回调用这个方法的堆栈追踪信息
     *
     * @return
     */
    public static String getInvokeMethodName() {
        return new Exception().getStackTrace()[1].getMethodName();
    }


    public static String getPrintInfo(Throwable e) {
        ByteArrayOutputStream tempOut = new ByteArrayOutputStream(256);
        e.printStackTrace(new PrintStream(tempOut));
        return new String(tempOut.toByteArray());
    }

    /**
     * 获取异常的全部消息
     *
     * @param e
     * @param delim
     * @param excludeTypes 忽略的异常信息
     * @return
     */
    public static String getAllCauseInfo(Throwable e, String delim, Class<? extends Throwable>... excludeTypes) {

        StringBuilder sb = new StringBuilder();

        if (delim == null)
            delim = "";

        while (e != null) {

            boolean isAppend = true;

            if (excludeTypes != null) {
                for (Class<? extends Throwable> exType : excludeTypes) {
                    if (exType.isInstance(e)) {
                        isAppend = false;
                        break;
                    }
                }
            }

            if (isAppend)
                sb.append("[" + e + "]");

            e = e.getCause();

            if (isAppend && e != null)
                sb.append(delim);
        }

        return sb.toString();
    }

    /**
     * 获取根异常的消息
     *
     * @param ex
     * @return
     */
    public static Throwable getRootCause(Throwable ex) {

        Throwable result = ex;

        while (ex != null) {
            result = ex;
            ex = ex.getCause();
        }

        return result;
    }


    public static <EX extends Throwable> EX getCauseByStartsWith(Throwable ex, String... startsWithClassNames) {
        while (ex != null) {
            for (String startsWithClassName : startsWithClassNames) {
                if (ex.getClass().getName().startsWith(startsWithClassName))
                    return (EX) ex;
            }
            ex = ex.getCause();
        }
        return null;
    }


    public static <EX extends Throwable> EX getCauseByTypes(Throwable e, Class<? extends Throwable>... exTypes) {
        while (e != null) {
            for (Class exType : exTypes) {
                if (exType.isInstance(e))
                    return (EX) e;
            }
            e = e.getCause();
        }
        return null;
    }

    /**
     * 获取根异常的消息
     *
     * @param e
     * @return
     */
    public static String getRootCauseInfo(Throwable e) {
        return getRootCause(e).getLocalizedMessage();
    }


    public static void main(String[] args) {

        System.out.println(getZhDesc("类型: 1 创建  2 完成支付变为审核中  3 审核中变为加热\n4 审核中变为审核不通过 5 修改自动关停配置 (关闭、启用、修改关停条件) 6 手动关停\n7 自动关停 8 加热中变为退款中 \r 9 状态变为 已完成或已结束"));


    }

}
