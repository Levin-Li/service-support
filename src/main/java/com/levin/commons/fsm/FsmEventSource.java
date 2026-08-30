package com.levin.commons.fsm;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 状态机事件的发起来源。
 * @author lilw
 */
@Schema(title = "状态机事件来源")
public enum FsmEventSource {

    @Schema(title = "用户", description = "由用户在界面上发起，应作为可操作项呈现")
    User,

    @Schema(title = "管理", description = "由管理员或运维人员执行的管理操作发起，可按权限决定是否呈现")
    Admin,

    @Schema(title = "系统", description = "由系统流程自动发起，不应作为用户操作呈现")
    System,

    @Schema(title = "定时", description = "由定时任务、延迟任务或超时机制自动发起，不应作为用户操作呈现")
    Timer,

    @Schema(title = "外部", description = "由第三方回调、开放接口或外部系统同步发起，不应作为用户操作呈现")
    External,

    @Schema(title = "消息", description = "由消息队列、事件总线或异步消费者发起，不应作为用户操作呈现")
    Message,

    @Schema(title = "其他", description = "无法归入上述来源的事件")
    Other
}
