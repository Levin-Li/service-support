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
@Schema(title = "服务交互")
public interface Interaction
        extends Serializable {


    interface Action {

        enum Cmd implements EnumDesc {
            @Schema(title = "自动消失的提示")
            prompt,

            @Schema(title = "跳转到UI页面")
            jumpToPage,

            @Schema(title = "执行脚本")
            execScript,

            @Schema(title = "是否下一步")
            nextStep,

            @Schema(title = "访问接口")
            requestApi,
        }


        /**
         * 动作名称
         *
         * @return
         */
        @Schema(title = "名称")
        String getName();

        /**
         * 图标
         *
         * @return
         */
        @Schema(title = "图标")
        default String getIcon() {
            return null;
        }

        /**
         * 命令关键字
         *
         * @return
         */
        @Schema(title = "命令")
        Cmd getCmd();

        /**
         * 命令参数
         *
         * @return
         */
        @Schema(title = "命令参数")
        String getArgs();

    }

    /**
     * 提示框标题
     *
     * @return
     */
    @Schema(title = "提示框标题")
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
    @Schema(title = "提示等级，值越大表示越严重")
    default int getLevel() {
        return 0;
    }

    /**
     * 提示信息
     *
     * @return
     */
    @Schema(title = "提示信息")
    String getInfo();

    /**
     * 客户端应该以模态对话框的方式展示，等待用户做出选择并执行响应的动作。
     *
     * @return
     */
    @Schema(title = "动作列表，客户端应该以模态对话框的方式展示按钮，等待用户做出选择并执行响应的动作。")
    default List<Action> getActions() {
        return Collections.emptyList();
    }

}
