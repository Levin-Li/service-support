package com.levin.commons.service.support;

import com.levin.commons.utils.IPAddrUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;


public class HttpRequestInfoResolver implements VariableResolver {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    @Override
    public <T> ValueHolder<T> resolve(String name, T defaultValue, boolean throwEx, boolean isRequireNotNull, Type... types) throws VariableNotFoundException {

        String value = null;

//            request.getRequestURL() 返回全路径
//            request.getRequestURI() 返回除去host（域名或者ip）部分的路径
//            request.getContextPath() 返回工程名部分，如果工程映射为/，此处返回则为空
//            request.getServletPath() 返回除去host和工程名部分的路径

//            request.getRequestURL() http://localhost:8080/jqueryLearn/resources/request.jsp
//            request.getRequestURI() /jqueryLearn/resources/request.jsp
//            request.getContextPath()/jqueryLearn
//            request.getServletPath()/resources/request.jsp

        if (InjectConst.IP_ADDR.equalsIgnoreCase(name)) {

            value = IPAddrUtils.try2GetUserRealIPAddr(request, false);

        } else if (InjectConst.IP_ADDR_CITY.equalsIgnoreCase(name)) {

            value = IPAddrUtils.searchIpRegion(IPAddrUtils.try2GetUserRealIPAddr(request, false));

        } else if (InjectConst.DOMAIN.equalsIgnoreCase(name)) {

            value = request.getServerName();

        } else if (InjectConst.USER_AGENT.equalsIgnoreCase(name)) {

            value = request.getHeader("user-agent");

        } else if (InjectConst.URL.equalsIgnoreCase(name)) {

            value = request.getRequestURL().toString();

        } else if (InjectConst.URL_PATH.equalsIgnoreCase(name)) {

            value = request.getRequestURI();

        } else if (InjectConst.URL_SCHEME.equalsIgnoreCase(name)) {

            value = request.getScheme();

        } else {
            return ValueHolder.notValue(name);
        }

        return new ValueHolder()
                .setValue(value)
                .setHasValue(!isRequireNotNull || value != null);
    }

}
