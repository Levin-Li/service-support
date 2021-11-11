package com.levin.commons.service.support;


/**
 * 推荐的常量名称
 */
public interface InjectConsts {

    /**
     * 用户相关
     */
    String USER = "user";
    String USER_ID = "user.id";
    String USER_NAME = "user.name";
    String USER_LOGIN_NAME = "user.loginName";
    String USER_PHONE = "user.phone";
    String USER_EMAIL = "user.email";

    /**
     * 第三方
     */
    String WX_OPEN_ID = "openId";


    /**
     * 租户
     */
    String TENANT = "tenant";


    /**
     * 租户ID
     */
    String TENANT_ID = "tenant.id";

    /**
     * 组织对象
     */
    String ORG = "org";

    /**
     * 组织ID
     */
    String ORG_ID = "org.id";

    /**
     * 组织路径
     */
    String ORG_PATH = "org.path";

    /**
     * 组织名称
     */
    String ORG_NAME = "org.name";

    /**
     * 行政区域编码
     */
    String AREA_CODE = "areaCode";

    /**
     * 城市名称
     */
    String CITY_NAME = "cityName";

    /**
     * 客户端 IP 地址
     */
    String IP_ADDR = "ipAddr";

    /**
     * 客户端 user agent
     */
    String USER_AGENT = "userAgent";

    /**
     * 域名或是服务器
     */
    String URL = "url";

    /**
     * http or https  协议
     */
    String URL_SCHEME = "url.scheme";

    /**
     * 服务器名称，通常是域名
     */
    String URL_SERVERNAME = "url.serverName";

}
