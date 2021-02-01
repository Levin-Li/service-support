package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * 服务交互
 * <p>
 * 标准的用户互动处理
 * <p>
 * 用于业务变化时，强制提醒用户，以解决业务上可能存在的纠纷
 *
 * @author lilw
 */
@Schema(description = "服务交互")
public interface Interaction
        extends Serializable {

    /**
     * 提示框标题
     *
     * @return
     */
    default String getTitle() {
        return "";
    }

    /**
     * 提示等级
     * 越高表示越严重
     * <p>
     * 用于处理提示框的图标，或是标题的颜色
     * <p>
     * 0 通常为 toast 信息
     *
     * @return
     */
    default int getLevel() {
        return 0;
    }

    /**
     * 提示信息
     * 如果为空，则忽略这调提示。
     *
     * @return
     */
    String getInfo();


    /**
     * 返回的 map 中 key 为动作显示名称，通常显示在按钮上，value 为动作。
     * <p>
     * 客户端应该以模态对话框的方式展示，等待用户做出选择。
     * <p>
     * 动作可以是跳转到指定页面， 也可以是访问指定的Api接口
     * 动作有3种前缀：
     * ui: page1 跳转到指定页面
     * api: http://xxx 访问 API 接口，接口必须返回一个标准的 ServiceResp 对象
     * go: true/false ,true 表示按钮按下后，继续业务，false 表示中断业务
     * script: 执行 javascript 脚本
     * <p>
     * <p>
     * 如果动作为 null 或是 empty ，则为自动消失的提示，不需要用户点击动作。
     *
     * @return
     */
    default Map<String /* action 显示名称 */, String /* 具体动作 */> getActions() {
        return Collections.emptyMap();
    }

}
