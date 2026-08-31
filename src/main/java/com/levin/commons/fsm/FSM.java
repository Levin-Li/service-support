package com.levin.commons.fsm;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

/**
 * @author lilw
 */
@Schema(title = "有限状态机", description = "实现类建议是一个枚举类")
public interface FSM<EVENT> extends Serializable {

    /**
     * 获取有限的状态列表
     *
     * @return
     */
    List<FsmState<EVENT>> states();

    /**
     * 指定的状态 可以触发的事件名称列表
     *
     * @param fsmState
     * @return
     */
    default List<String> canFireEventNames(FsmState<EVENT> fsmState) {
        return FSMHelper.canFireEventNames(fsmState);
    }

    /**
     * 指定的状态 可以触发的事件列表
     *
     * @param fsmState
     * @return
     */
    default List<EVENT> canFireEvents(FsmState<EVENT> fsmState) {
        return FSMHelper.canFireEvents(fsmState);
    }
}
