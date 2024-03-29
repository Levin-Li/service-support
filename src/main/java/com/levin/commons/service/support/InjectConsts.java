package com.levin.commons.service.support;


/**
 * 推荐的常量名称
 */
public interface InjectConsts {

    String ACCESS_TOKEN = "accessToken";

    /**
     * 用户相关
     */
    String USER = "user";
    String USER_ID = "userId";
    String USER_NAME = "userName";
    String USER_LOGIN_NAME = "userLoginName";
    String USER_PHONE = "userPhone";
    String USER_EMAIL = "userEmail";

    //////////////////////////////////////////////////////////

    /**
     * 第三方
     */
    String WX_OPEN_ID = "openId";

    /**
     * 第三方
     */
    String WX_UNION_ID = "unionId";

    /**
     * 第三方
     */
    String ALI_USER_ID = "aliUserId";

    /**
     * 抖音
     */
    String DY_OPEN_ID = "dyOpenId";

    ///////////////////////////////////////////////////////
    /**
     * 租户
     */
    String TENANT = "tenant";

    /**
     * 租户ID
     */
    String TENANT_ID = "tenantId";

    String TENANT_NAME = "tenantName";

    ////////////////////////////////////////////////////////

    /**
     * 组织对象
     */
    String ORG = "org";

    /**
     * 组织ID
     */
    String ORG_ID = "orgId";

    /**
     * 组织路径
     */
    String ORG_PATH = "orgPath";

    /**
     * 组织名称
     */
    String ORG_NAME = "orgName";

    //////////////////////////////////////////////////////////////

    /**
     * 行政区域编码
     */
    String AREA_CODE = "areaCode";

    /**
     * 城市名称
     */
    String CITY_NAME = "cityName";

    ////////////////////////////////////////////////////////////////

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
    String URL_SCHEME = "urlScheme";

    /**
     * 服务器名称，通常是域名
     */
    String URL_SERVERNAME = "urlServerName";

}
