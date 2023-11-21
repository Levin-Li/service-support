package com.levin.commons.service.support;


import com.levin.commons.service.domain.InjectVar;

/**
 * 推荐的常量名称
 */
public interface InjectConst {

    String IS_WEB_CONTEXT = "isWebContext";

    String IS_SUPER_ADMIN = "isSuperAdmin";

    String IS_TENANT_ADMIN = "isTenantAdmin";

    String NOT_SUPER_ADMIN = " !(#" + IS_SUPER_ADMIN + ") ";

    String NOT_TENANT_ADMIN = " !(#" + IS_TENANT_ADMIN + ") ";

    String SPEL_NOT_SUPER_ADMIN_AND_NOT_TENANT_ADMIN = InjectVar.SPEL_PREFIX + NOT_SUPER_ADMIN + " && " + NOT_TENANT_ADMIN;

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
     * 组织ID列表
     */
    String ORG_ID_LIST = "orgIdList";

    /**
     * 组织路径
     */
    String ORG_PATH = "orgPath";

    /**
     * 组织名称
     */
    String ORG_NAME = "orgName";

    /**
     * 用户的角色列表
     */
    String ROLE_LIST = "roleList";

    /**
     * 权限列表
     */
    String PERMISSION_LIST = "permissionList";

    //////////////////////////////////////////////////////////////

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

    /**
     * 租户名称
     */
    String TENANT_NAME = "tenantName";


    /**
     * 行政区域编码
     */
    String AREA_CODE = "areaCode";

    /**
     * 城市名称
     */
    String CITY_NAME = "cityName";

    ///////////////////////////////////////////////////////////////

    /**
     * 发生时间
     */
    String OCCUR_TIME = "occurTime";

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
