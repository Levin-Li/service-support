package com.levin.commons.fsm;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 有限状态机的流转与查询工具。
 *
 * @author lilw
 */
@Schema(title = "有限状态机", description = "通过事件驱动状态按预定义规则流转")
public final class FSMHelper {

    private FSMHelper() {
    }

    /**
     * 创建事件
     *
     * @param name
     * @return
     */
    public static FsmEvent newUserFsmEvent(String name) {
        return newFsmEvent(name, FsmEventSource.User, null);
    }

    /**
     * @param name
     * @param source
     * @param description
     * @return
     */
    public static FsmEvent newFsmEvent(String name, FsmEventSource source, String description) {

        return new SimpleFsmEvent(
                requireNonBlank(name, "event name is blank"),
                requireNonBlank(source, "event source is null"),
                description
        );

    }

    /**
     * 创建新规则
     *
     * @param sourceState
     * @param event
     * @param targetState
     * @param fireConditions
     * @param <EVENT>
     * @param <STATE>
     * @return
     */
    public static <EVENT, STATE> FsmStateTransitionRule<EVENT, STATE> newFsmStateTransitionRule(STATE sourceState, EVENT event, STATE targetState, Predicate<STATE>... fireConditions) {

        return new SimpleFsmStateTransitionRule<EVENT, STATE>(

                requireNonBlank(sourceState, "sourceState is blank"),

                requireNonBlank(event, "event is blank"),

                //多个条件合并
                (fireConditions == null || Stream.of(fireConditions).allMatch(Objects::isNull)) ?
                        (state) -> true
                        : (state) -> Stream.of(fireConditions).filter(Objects::nonNull).allMatch(statePredicate -> statePredicate.test(state)),

                requireNonBlank(targetState, "targetState is blank")
        );
    }

    /**
     * 是否能触发事件
     *
     * @param fsmState
     * @param <EVENT>
     * @return
     */
    public static <EVENT> List<String> canFireEventNames(FsmState<EVENT> fsmState) {
        return canFireEvents(fsmState).stream().map(FSMHelper::toStringValue).collect(Collectors.toUnmodifiableList());
    }

    /**
     * 指定的状态能够触发的事件列表，可用于前端展示
     *
     * @param fsmState
     * @param <EVENT>
     * @return
     */
    public static <EVENT> List<EVENT> canFireEvents(FsmState<EVENT> fsmState) {

        List<? extends FsmStateTransitionRule<EVENT, FsmState<EVENT>>> transitionRules = fsmState.transitionRules();

        if (transitionRules == null
                || transitionRules.isEmpty()) {
            return Collections.emptyList();
        }

        return transitionRules.stream()

                //源相等
                .filter(rule -> rule.sourceState() == null || isValueEquals(rule.sourceState(), fsmState))

                //

                .filter(rule -> rule.fireCondition() == null || rule.fireCondition().test(fsmState))


                //取事件
                .map(rule -> rule.event())

                .collect(Collectors.toUnmodifiableList());

    }

    /**
     * 特别方法，用于比较 2个状态值 或是 2个事件是否相等
     *
     * @param value1
     * @param value2
     * @return
     */
    private static boolean isValueEquals(Object value1, Object value2) {

        if (value1 == value2) {
            return true;
        }

        if (value1 == null || value2 == null) {
            return false;
        }

        value1 = toStringValue(value1);
        value2 = toStringValue(value2);

        if (value1 == value2) {
            return true;
        }

        if (value1 == null || value2 == null) {
            return false;
        }

        return value1.equals(value2);
    }


    /**
     * 特定功能， 为了匹配 字符串和对象的比较
     *
     * @param value
     * @return
     */
    private static String toStringValue(Object value) {

        if (value == null) {
            return null;
        }

        if (value instanceof FsmState<?>) {
            return ((FsmState<?>) value).name().trim();
        } else if (value instanceof FsmEvent) {
            return ((FsmEvent) value).name().trim();
        } else {
            return value.toString().trim();
        }

    }


    private static <T> T requireNonBlank(T value, String errInfo) {

        Objects.requireNonNull(value, errInfo);

        if ((value instanceof CharSequence) && value.toString().isBlank()) {
            throw new IllegalArgumentException(errInfo);
        }

        return value;
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////

    private record SimpleFsmEvent(String name, FsmEventSource source, String description) implements FsmEvent {
    }

    private record SimpleFsmStateTransitionRule<EVENT, STATE>(STATE sourceState, EVENT event,
                                                              Predicate<STATE> fireCondition,
                                                              STATE targetState) implements FsmStateTransitionRule<EVENT, STATE> {
    }


}
