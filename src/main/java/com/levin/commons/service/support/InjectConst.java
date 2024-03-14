package com.levin.commons.service.support;

/**
 * 推荐的常量名称
 */
public interface InjectConst {

    String IS_WEB_CONTEXT = "isWebContext";

    String IS_SUPER_ADMIN = "isSuperAdmin";

    String IS_TENANT_ADMIN = "isTenantAdmin";

    String IS_ALL_ORG_SCOPE = "isAllOrgScope";

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
     * 模块ID
     */
    String MODULE_ID = "moduleId";

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
     * 客户端 IP 地址 城市
     */
    String IP_ADDR_CITY = "ipAddrCity";

    /**
     * 客户端 user agent
     */
    String USER_AGENT = "userAgent";

    /**
     * URL 包含参数
     */
    String URL = "url";

    /**
     * URL 路径，只是路径，不包含域名和参数
     */
    String URL_PATH = "urlPath";

    /**
     * http or https  协议
     */
    String URL_SCHEME = "urlScheme";

    /**
     * 服务器名称，通常是域名
     */
    String DOMAIN = "domain";

}
