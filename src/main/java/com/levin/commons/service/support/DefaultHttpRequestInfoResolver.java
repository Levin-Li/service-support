package com.levin.commons.service.support;

import com.levin.commons.utils.IPAddrUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class DefaultHttpRequestInfoResolver implements VariableResolver {

    @Resource
    HttpServletRequest request;

    @Resource
    HttpServletResponse response;

    @Override
    public <T> ValueHolder<T> resolve(String name, T defaultValue, boolean throwEx, Class<?>... types) throws VariableNotFoundException {

        String value = null;

//            request.getRequestURL() 返回全路径
//            request.getRequestURI() 返回除去host（域名或者ip）部分的路径
//            request.getContextPath() 返回工程名部分，如果工程映射为/，此处返回则为空
//            request.getServletPath() 返回除去host和工程名部分的路径

//            request.getRequestURL() http://localhost:8080/jqueryLearn/resources/request.jsp
//            request.getRequestURI() /jqueryLearn/resources/request.jsp
//            request.getContextPath()/jqueryLearn
//            request.getServletPath()/resources/request.jsp

        if (InjectConsts.IP_ADDR.equalsIgnoreCase(name)) {

            value = IPAddrUtils.try2GetUserRealIPAddr(request);

        } else if (InjectConsts.URL_SERVERNAME.equalsIgnoreCase(name)) {

            value = request.getServerName();

        } else if (InjectConsts.USER_AGENT.equalsIgnoreCase(name)) {

            value = request.getHeader("user-agent");

        } else if (InjectConsts.URL.equalsIgnoreCase(name)) {

            value = request.getRequestURL().toString();

        } else if (InjectConsts.URL_SCHEME.equalsIgnoreCase(name)) {

            value = request.getScheme();

        } else {
            return ValueHolder.notValue();
        }

        return new ValueHolder()
                .setValue(value)
                .setHasValue(true);
    }

}
