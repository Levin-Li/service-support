package com.levin.commons.fsm;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.service.domain.Castable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

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
    @Schema(title = "事件", description = "触发事件,不能为空")
    EVENT event();

    @Schema(title = "目标状态", description = "不能为空")
    STATE targetState();

    /**
     * 状态转移的表单定义，具有三种语义：
     * <ul>
     *     <li>{@code null}：展示原有的完整表单；</li>
     *     <li>空列表：无需提交表单，发起事件前应提示用户确认该操作；</li>
     *     <li>非空列表：仅展示并提交列表中指定的表单项。</li>
     * </ul>
     */
    @Schema(title = "表单列表", description = "三态：null 展示原有完整表单；空列表无需表单但发起事件前需用户确认；非空列表仅展示并提交指定表单项")
    default List<? extends FsmFormItem> formItemList() {
        return List.of();
    }

    static <EVENT extends FsmEvent, STATE extends FsmState<EVENT>> FsmStateTransitionRule<EVENT, STATE> of(STATE sourceState, EVENT event, STATE targetState, FsmFormItem... formItemList) {
        return FSMHelper.newFsmStateTransitionRule(sourceState, event, targetState, formItemList);
    }
}
