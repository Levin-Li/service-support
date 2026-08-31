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

    /**
     * 可以触发的事件名称列表
     *
     * @return
     */
    default List<String> canFireEventNames() {
        return FSMHelper.canFireEventNames(this);
    }

    default List<EVENT> canFireEvents() {
        return FSMHelper.canFireEvents(this);
    }

}
