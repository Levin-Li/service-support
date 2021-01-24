package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

/**
 * 服务响应
 * <p>
 * 可以继承
 *
 * @param <T>
 * @author lilw
 */

@Schema(description = "服务响应")
public interface ServiceResp<T>
        extends Serializable {

    /**
     * 获取响应码，非零表示有错误或异常
     *
     * @return
     */
    int getCode();

    /**
     * 获取信息
     *
     * @return
     */
    String getMsg();

    /**
     * 获取详细信息
     *
     * @return
     */
    String getDetailMsg();


    /**
     * 服务交互
     *
     * @return
     */
    List<Interaction> getInteractions();

    /**
     * 业务数据
     *
     * @return
     */
    T getData();
}
