package com.levin.commons.fsm;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.service.domain.Castable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * @author lilw
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(title = "状态转移规则")
public interface FsmStateTransitionRule<EVENT extends FsmEvent, STATE extends FsmState<EVENT>> extends Castable, Serializable {

    @NotNull
    @Schema(title = "源状态", description = "可为空, 有些初始态是空")
    STATE sourceState();

    @NotNull
    @Schema(title = "事件", description = "触发事件,不能为空 ")
    EVENT event();

    @Schema(title = "目标状态", description = "不能为空")
    STATE targetState();

}
