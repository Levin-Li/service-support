package com.levin.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lilw on 2016/10/10.
 */
@Slf4j
public class IPAddrUtils {

    private static List<String> ipHeadNameList = new ArrayList<>(16);

    static {

        ipHeadNameList.addAll(Arrays.asList(
                "x-real-ip",
                "X-Real-IP",
                "x-forwarded-for",
                "X-Forwarded-For",
                "REMOTE-HOST",
                "proxy-client-ip",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"
        ));

    }

    private static Searcher searcher = null;

    private static final Object lock = new Object();

    /**
     * 查找IP的地区
     * 格式：国家|区域|省份|城市|ISP，缺省的地域信息默认是0。 region 信息支持完全自定义
     *
     * @param ip
     * @return 地区
     */
    public static String searchIpRegion(String ip) {
        try {
            //优化同步处理的性能
            if (searcher == null) {
                synchronized (lock) {
                    //优化同步处理的性能
                    if (searcher == null) {
                        //立刻处理防止
                        String fn = "ip2region.xdb";

                        File file = null;

                        if ((file = new File(fn)).exists()) {
                        } else if ((file = new File("config", fn)).exists()) {

                        } else if ((file = new File("resources", fn)).exists()) {

                        } else {
                            file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fn);
                        }

                        if (file != null) {
                            searcher = Searcher.newWithBuffer(Files.readAllBytes(file.toPath()));
                        }
                    }
                }
            }

            return searcher != null ? searcher.search(ip) : null;

        } catch (Exception e) {
            log.warn("查询IP地址[" + ip + "]地区错误," + ExceptionUtils.getAllCauseInfo(e, "->"));
            return null;
        }
    }


    /**
     * @param insertToFirst
     * @param ipHeadNames
     */
    public static void addIPAddrHeadNames(boolean insertToFirst, String... ipHeadNames) {

        List<String> tmpList = Arrays.asList(ipHeadNames);

        if (insertToFirst) {
            ipHeadNameList.addAll(0, tmpList);
        } else {
            ipHeadNameList.addAll(tmpList);
        }

    }

    public static void setIPAddrHeadNames(String... ipHeadNames) {

        ipHeadNameList.clear();

        ipHeadNameList.addAll(Arrays.asList(ipHeadNames));

    }

    public static boolean isValidIP(String ip) {
        return isIPV6(ip) || isIPV4(ip);
    }

    public static boolean isIPV6(String ip) {

        //        IPv6的地址长度为128位，是IPv4地址长度的4倍。于是IPv4点分十进制格式不再适用，采用十六进制表示。IPv6有3种表示方法。
        //        一、冒分十六进制表示法
        //　　格式为X:X:X:X:X:X:X:X，其中每个X表示地址中的16b，以十六进制表示，例如：
        //　　ABCD:EF01:2345:6789:ABCD:EF01:2345:6789
        //　　这种表示法中，每个X的前导0是可以省略的，例如：
        //　　2001:0DB8:0000:0023:0008:0800:200C:417A→ 2001:DB8:0:23:8:800:200C:417A
        //        二、0位压缩表示法
        //　　在某些情况下，一个IPv6地址中间可能包含很长的一段0，可以把连续的一段0压缩为“::”。但为保证地址解析的唯一性，地址中”::”只能出现一次，例如：
        //　　FF01:0:0:0:0:0:0:1101 → FF01::1101
        //　　0:0:0:0:0:0:0:1 → ::1
        //　　0:0:0:0:0:0:0:0 → ::
        //        三、内嵌IPv4地址表示法
        //　　为了实现IPv4-IPv6互通，IPv4地址会嵌入IPv6地址中，此时地址常表示为：X:X:X:X:X:X:d.d.d.d，前96b采用冒分十六进制表示，而最后32b地址则使用IPv4的点分十进制表示，例如::192.168.0.1与::FFFF:192.168.0.1就是两个典型的例子，注意在前96b中，压缩0位的方法依旧适用
        //

        if (ip == null || !ip.contains(":")) {
            return false;
        }


        //0:0:0:0:0:0:0:0 → ::
        if (ip.equals("::")) {
            return true;
        }


        //0位压缩表示法
        if (ip.contains("::")) {

            String[] sections = ip.split(":");

            for (String section : sections) {
                try {

                    if (section.length() == 0)
                        continue;

                    if (section.contains(".") && isIPV4(section))
                        continue;

                    if (Integer.parseInt(section, 16) < 0)
                        return false;

                } catch (Exception e) {
                    return false;
                }

            }

            return true;
        }


        //三、内嵌IPv4地址表示法
        //为了实现IPv4-IPv6互通，IPv4地址会嵌入IPv6地址中，此时地址常表示为：X:X:X:X:X:X:d.d.d.d，前96b采用冒分十六进制表示，而最后32b地址则使用IPv4的点分十进制表示，例如::192.168.0.1与::FFFF:192.168.0.1就是两个典型的例子，注意在前96b中，压缩0位的方法依旧适用'
        String[] sections = ip.split(":");

        boolean hasIPV4 = ip.contains(".");

        if ((hasIPV4 && sections.length != 7)
                || sections.length != 8)
            return false;


        for (String section : sections) {
            try {

                if (section.contains(".") && isIPV4(section))
                    continue;

                if (Integer.parseInt(section, 16) < 0)
                    return false;

            } catch (Exception e) {
                return false;
            }

        }

        return true;

    }


    public static boolean isIPV4(String ip) {

        if (ip == null || !ip.contains(".")) {
            return false;
        }

        String[] sections = ip.split("\\.");

        if (sections.length != 4) {
            return false;
        }

        for (String section : sections) {
            try {
                if (Integer.parseInt(section) < 0) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }

        return true;


    }


    /**
     * 获取真实IP地址
     *
     * @param request
     * @return
     */
    public static String try2GetUserRealIPAddr(HttpServletRequest request) {
        return try2GetUserRealIPAddr(request, true);
    }

    /**
     * @param request
     * @param isVerify 是否校验IP地址
     * @return
     */
    public static String try2GetUserRealIPAddr(HttpServletRequest request, boolean isVerify) {

        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        for (String headName : ipHeadNameList) {

            String ip = request.getHeader(headName);

            if (ip == null || ip.trim().length() == 0) {
                continue;
            }

            ip = ip.trim();

            String[] sections = ip.split(",");

            for (String section : sections) {
                if (!isVerify || isValidIP(section)) {
                    return section;
                }
            }

        }

        return request.getRemoteAddr();
    }

    public static void main(String[] args) {
        System.out.println("IPV4:" + isValidIP("127.0.0.1"));
        System.out.println("IPV6:" + isValidIP("120.235.173.83"));

    }
}
