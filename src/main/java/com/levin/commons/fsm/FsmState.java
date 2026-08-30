package com.levin.commons.fsm;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

/**
 * @author lilw
 */
@Schema(title = "状态", description = "")
public interface FsmState<EVENT, STATE> extends Serializable {

    @Schema(title = "状态名称")
    String name();

    @Schema(title = "以当前状态为源状态的流转规则集合", description = "")
    List<? extends FsmStateTransitionRule<EVENT, FsmState<EVENT, STATE>>> transitionRules();


    default List<EVENT> canFireEvents() {
        return FSMHelper.canFireEvents(this);
    }

//    @Schema(title = "以当前状态为目标状态的流转规则集合", description = "")
//    List<? extends FsmStateTransitionRule<EVENT, STATE>> toMeTransitionRules();

}
