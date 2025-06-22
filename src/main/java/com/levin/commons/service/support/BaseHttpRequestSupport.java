package com.levin.commons.service.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.*;
import com.alibaba.fastjson2.writer.ObjectWriterProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
public class BaseHttpRequestSupport {

    /**
     * 获取请求地址
     *
     * @return
     */
    protected String getBaseUrl() {
        return "";
    }


    /**
     * 初始化http请求
     *
     * @param httpRequest
     */
    protected void initHttpRequest(HttpRequest httpRequest) {
    }

    /**
     * Json解析配置
     *
     * @return
     */
    protected JSONReader.Feature[] getParseFeatures() {
        return new JSONReader.Feature[]{JSONReader.Feature.SupportSmartMatch};
    }

    protected int getConnectTimeoutMs() {
        return 30 * 1000;
    }

    protected boolean isUnderlineNaming() {
        return false;
    }

    public <T> T get(String title, String url, Object requestParam, Type responseType) {
        return get(title, url, requestParam, responseType, this::initHttpRequest);
    }

    public <T> T postJson(String title, String url, Object requestParam, Type responseType) {
        return postJson(title, url, requestParam, responseType, this::initHttpRequest);
    }

    public <T> T postMultipart(String title, String url, Object requestParam, Type responseType) {
        return postMultipart(title, url, responseType, requestParam, this::initHttpRequest);
    }

    public <T> T get(String title, String url, Object requestParam, Type responseType, Consumer<HttpRequest> requestConsumer) {
        return doHttpRequest(title, "get", url, ContentType.FORM_URLENCODED, isUnderlineNaming(), requestParam, responseType, requestConsumer);
    }

    public <T> T postForm(String title, String url, Object requestParam, Type responseType, Consumer<HttpRequest> requestConsumer) {
        return doHttpRequest(title, "post", url, ContentType.FORM_URLENCODED, isUnderlineNaming(), requestParam, responseType, requestConsumer);
    }

    public <T> T postJson(String title, String url, Object requestParam, Type responseType, Consumer<HttpRequest> requestConsumer) {
        return doHttpRequest(title, "post", url, ContentType.JSON, isUnderlineNaming(), requestParam, responseType, requestConsumer);
    }

    public <T> T postMultipart(String title, String url, Type responseType, Object requestParam, Consumer<HttpRequest> requestConsumer) {
        return doHttpRequest(title, "post", url, ContentType.MULTIPART, isUnderlineNaming(), requestParam, responseType, requestConsumer);
    }

    /**
     * POST请求
     *
     * @param title
     * @param url
     * @param responseType
     * @param requestParam
     * @param requestConsumer
     * @param <T>
     * @return
     */
    public <T> T doHttpRequest(String title, String httpMethod, String url, ContentType contentType, boolean isUnderlineNaming, Object requestParam, Type responseType, Consumer<HttpRequest> requestConsumer) {

        if (!url.toLowerCase().startsWith("https://")
                && !url.toLowerCase().startsWith("http://")) {
            url = getBaseUrl() + url;
        }

        HttpRequest httpRequest = null;

        if ("post".equalsIgnoreCase(httpMethod)) {
            httpRequest = HttpRequest.post(url);
        } else if ("get".equalsIgnoreCase(httpMethod)) {
            httpRequest = HttpRequest.get(url);
        } else if ("delete".equalsIgnoreCase(httpMethod)) {
            httpRequest = HttpRequest.delete(url);
        } else if ("put".equalsIgnoreCase(httpMethod)) {
            httpRequest = HttpRequest.put(url);
        } else if ("patch".equalsIgnoreCase(httpMethod)) {
            httpRequest = HttpRequest.patch(url);
        } else {
            httpRequest = HttpRequest.post(url);
        }

        if (contentType == null) {
            contentType = ContentType.JSON;
        }

        //设置
        httpRequest.setConnectionTimeout(getConnectTimeoutMs())
                .setFollowRedirects(true)
                .contentType(contentType.getValue());

        String showText = null;

        if (requestParam != null) {
            if (contentType == ContentType.JSON) {
                httpRequest.body(showText = toJsonStr(isUnderlineNaming, requestParam)); //
            } else if (contentType == ContentType.MULTIPART
                    || contentType == ContentType.FORM_URLENCODED) {

                httpRequest.form(filterValue(contentType, BeanUtil.beanToMap(requestParam, isUnderlineNaming, true)));

            } else if (contentType == ContentType.OCTET_STREAM) {
                httpRequest.body((byte[]) requestParam);
            } else {
                httpRequest.body(requestParam.toString());
            }
        }

        if (requestConsumer != null) {
            requestConsumer.accept(httpRequest);
        }

        if (showText == null) {
            showText = "" + httpRequest.form();
        }

        log.info(title + "-请求 URL：{}:{}, 请求头: {}, 请求参数：{}", httpMethod, httpRequest.getUrl(), httpRequest.headers(), sampleText(showText));

        HttpResponse response = httpRequest.execute();

        String respBody = response.body();

        log.info(title + "-响应 URL：{} status:{} 响应结果：{}", httpRequest.getUrl(), response.getStatus(), sampleText(respBody));

        if (responseType == null || responseType == String.class || responseType == CharSequence.class) {
            return (T) respBody;
        }

        return JSON.parseObject(respBody, responseType, getParseFeatures());
    }


    final JSONWriter.Context writeSnakeCaseContext = JSONFactory.createWriteContext(new ObjectWriterProvider(PropertyNamingStrategy.SnakeCase));

    protected String toJsonStr(Object requestParam) {
        return toJsonStr(isUnderlineNaming(), requestParam);
    }

    protected String toJsonStr(boolean isUnderlineNaming, Object requestParam) {
        return isUnderlineNaming ? JSON.toJSONString(requestParam, writeSnakeCaseContext) : JSON.toJSONString(requestParam);
    }

    protected boolean isToJsonStr(ContentType contentType, Object value) {

        if (value == null) {
            return false;
        }

        if (contentType == ContentType.MULTIPART
                || contentType == ContentType.FORM_URLENCODED) {
            return !BeanUtils.isSimpleProperty(value.getClass());
        }

        return false;
    }

    protected Map<String, Object> filterValue(ContentType contentType, Map<String, Object> bean) {

        bean.replaceAll((k, v) -> isToJsonStr(contentType, v) ? toJsonStr(v) : v);

        return bean;
    }

    protected String sampleText(String text) {
        return (text != null && text.length() > 2000) ? (text.substring(0, 500) + "..." + text.substring(text.length() - 500)) : text;
    }

}
