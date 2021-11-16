package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 服务交互
 * <p>
 * 标准的用户互动处理
 * <p>
 * 用于业务变化时调整原有的交互流程
 *
 * @author lilw
 */
@Schema(description = "服务交互")
public interface Interaction
        extends Serializable {


    interface Action {

        // 自动消失的提示
        String PROMPT_CMD = "prompt";

        //跳转到 UI 页面
        String UI_CMD = "ui";

        //执行脚本
        String SCRIPT_CMD = "script";

        //是否下一步
        String CONTINUE_CMD = "continue";

        //访问接口
        String API_CMD = "api";

        /**
         * 动作名称
         *
         * @return
         */
        @Schema(description = "名称")
        String getName();

        /**
         * 图标
         *
         * @return
         */
        @Schema(description = "图标")
        default String getIcon() {
            return null;
        }

        /**
         * 命令关键字
         *
         * @return
         */
        @Schema(description = "命令")
        String getCmd();

        /**
         * 命令参数
         *
         * @return
         */
        @Schema(description = "命令参数")
        String getArgs();

    }

    /**
     * 提示框标题
     *
     * @return
     */
    @Schema(description = "提示框标题")
    default String getTitle() {
        return "";
    }

    /**
     * 提示等级
     * 值越大表示越严重
     * <p>
     * 用于处理提示框的图标，或是标题的颜色
     * <p>
     * 0 通常为 toast 信息
     *
     * @return
     */
    @Schema(description = "提示等级，值越大表示越严重")
    default int getLevel() {
        return 0;
    }

    /**
     * 提示信息
     *
     * @return
     */
    @Schema(description = "提示信息")
    String getInfo();

    /**
     * 客户端应该以模态对话框的方式展示，等待用户做出选择并执行响应的动作。
     *
     * @return
     */
    @Schema(description = "动作列表，客户端应该以模态对话框的方式展示，等待用户做出选择并执行响应的动作。")
    default List<Action> getActions() {
        return Collections.emptyList();
    }

}
