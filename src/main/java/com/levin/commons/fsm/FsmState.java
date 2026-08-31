package com.levin.commons.fsm;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

/**
 * @author lilw
 */
@Schema(title = "状态", description = "")
public interface FsmState<EVENT extends FsmEvent> extends Serializable {

    @Schema(title = "状态名称")
    String name();

    @Schema(title = "以当前状态为源状态的流转规则集合", description = "")
    List<? extends FsmStateTransitionRule<EVENT, ? extends FsmState<EVENT>>> transitionRules();

    @Schema(title = "是否能触发指定事件")
    default boolean canFireEvent(String eventName) {
        return canFireEventNames().contains(eventName);
    }

    @Schema(title = "获取当前状态能触发的事件名称列表")
    default List<String> canFireEventNames() {
        return FSMHelper.canFireEventNames(this);
    }

    @Schema(title = "获取当前状态能触发的事件列表")
    default List<EVENT> canFireEvents() {
        return FSMHelper.canFireEvents(this);
    }

}
