package com.levin.commons.fsm;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author lilw
 */
@Schema(title = "有限状态机", description = "实现类建议是一个枚举类")
public interface FSM<EVENT extends FsmEvent> {

    /**
     * 获取有限的状态列表
     *
     * @return
     */
    List<? extends FsmState<EVENT>> allStates();

}
