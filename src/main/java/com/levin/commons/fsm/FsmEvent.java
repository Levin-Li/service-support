package com.levin.commons.fsm;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * @author lilw
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(title = "事件")
public interface FsmEvent extends Serializable {

    @Schema(title = "事件名称", description = "必须与 Transition.event() 使用的事件名一致；枚举实现通常直接使用枚举常量名")
    String name();

    @Schema(title = "事件描述")
    default String description() {
        return "";
    }

    @Schema(title = "事件来源", description = "用户事件可在界面上呈现为操作项；系统事件由系统自动发起")
    default FsmEventSource source() {
        return FsmEventSource.User;
    }
}
