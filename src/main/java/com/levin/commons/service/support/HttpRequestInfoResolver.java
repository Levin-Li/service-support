package com.levin.commons.service.support;

import cn.hutool.core.util.StrUtil;
import com.levin.commons.utils.IPAddrUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


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

        } else if (name.startsWith(InjectConst.HTTP_HEADER_PREFIX)) {

            value = request.getHeader(name.substring(InjectConst.HTTP_HEADER_PREFIX.length()));

            if (value == null) {
                return ValueHolder.notValue(name);
            }

        } else if (name.startsWith(InjectConst.HTTP_PARAM_PREFIX)) {

            value = request.getParameter(name.substring(InjectConst.HTTP_PARAM_PREFIX.length()));

            if (value == null) {
                return ValueHolder.notValue(name);
            }

        } else {
            return ValueHolder.notValue(name);
        }

        ValueHolder valueHolder = new ValueHolder()
                .setValue(value)
                .setHasValue(!isRequireNotNull || value != null);

        return valueHolder;
    }

}
