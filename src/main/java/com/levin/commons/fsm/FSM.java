package com.levin.commons.fsm;

import com.levin.commons.utils.PathPatternUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Predicate;


/**
 * 有限状态机 FSM（Finite State Machine）
 * <p>
 * 有限个状态
 * 同一时间只处于一个状态
 * 通过「事件 / 触发条件」→ 切换到下一个状态
 * 状态流转固定、可控、不混乱
 * <p>
 * <p>
 * State 状态（有限，枚举类和普通类都可以实现）
 * Event 事件（触发动作）
 * Transition 流转规则（A 状态 + 某事件 → 变成 B 状态）
 *
 * @author lilw
 */
@Schema(title = "有限状态机", description = "有限状态机 FSM（Finite State Machine）;" +
        "1 有限个状态;" +
        "2 同一时间只处于一个状态;" +
        "3 通过「事件 / 触发条件」→ 切换到下一个状态;" +
        "4 状态流转固定、可控、不混乱;;" +
        "State 状态（有限）;" +
        "Event 事件（触发动作）;" +
        "Transition 流转规则（A 状态 + 某事件 → 变成 B 状态）;")
public interface FSM {

    @Schema(title = "事件")
    interface Event {

        @Schema(title = "事件名称")
        String name();

        @Schema(title = "事件描述")
        default String description() {
            return "";
        }
    }

    @Schema(title = "状态", description = "枚举类实现这个接口")
    interface State<S extends State<S>> {

        @Schema(title = "状态名称")
        String name();

        @Schema(title = "流转规则集合", description = "进入当前状态的流转规则集合")
        default Collection<? extends Transition> transitions() {
            return Collections.emptyList();
        }

        @Schema(title = "所有状态集合", description = "状态集合")
        default Collection<? extends S> allStates() {
            if (this instanceof Enum<?>) {
                return Arrays.asList(((Class<S>) ((Enum<?>) this).getDeclaringClass()).getEnumConstants());
            }
            return Collections.singletonList((S) this);
        }

        @Schema(title = "所有事件集合", description = "所有事件集合")
        default Collection<? extends Event> allEvents() {
            return Collections.emptyList();
        }

    }

    @Schema(title = "流转规则")
    interface Transition {

        @Schema(title = "事件名称")
        String eventName();

        @Schema(title = "源状态集合", description = "允许从这些状态流转到当前态, 从业务上更好理解,如为null或是空,则表示无限制,如果集合中的元素为null也是有意义,表示可以从空状态扭转到当前态;支持*?通配符")
        default Collection<String> from() {
            return Collections.emptyList();
        }
    }

    @Schema(title = "流转规则", description = "带目标状态的流转规则")
    interface TransitionX<S extends State<S>> extends Transition {
        @Schema(title = "目标状态名称")
        S to();
    }

    @Schema(title = "所有规则集合", description = "所有规则集合")
    static <S extends State<S>> Collection<? extends TransitionX<S>> getAllTransitions(S state) {

        if (state == null) {
            return Collections.emptyList();
        }

        final Collection<? extends S> allStates = state.allStates();
        if (allStates == null || allStates.isEmpty()) {
            return Collections.emptyList();
        }

        final Collection<TransitionX<S>> result = new ArrayList<>();
        for (S targetState : allStates) {
            if (targetState == null || targetState.transitions() == null) {
                continue;
            }

            for (Transition transition : targetState.transitions()) {
                if (transition != null) {
                    result.add(toTransitionX(targetState, transition));
                }
            }
        }

        return result;
    }

    @Schema(title = "是否可以流转", description = "是否可以通过eventName从sourceState流转到targetState")
    static <S extends State<S>> boolean canTransit(S sourceState, S targetState, String eventName, @Nullable Predicate<TransitionX<S>>... exTransitPredicates) {

        return targetState != null
                && getAllTransitions(targetState).stream()
                .filter(transition -> Objects.equals(transition.to(), targetState))
                .anyMatch(transition -> isMatched(sourceState, eventName, transition, exTransitPredicates));

    }

    @Schema(title = "是否可以触发事件", description = "是否可以通过eventName")
    static <S extends State<S>> boolean canFireEvent(S state, String eventName, @Nullable Predicate<TransitionX<S>>... exTransitPredicates) {

        return state != null
                && getAllTransitions(state).stream()
                .anyMatch(transition -> isMatched(state, eventName, transition, exTransitPredicates));
    }

    @Schema(title = "触发事件", description = "返回新状态")
    static <S extends State<S>> S fireEvent(S state, String eventName, @Nullable Predicate<TransitionX<S>>... exTransitPredicates) {

        return state == null ? null :
                getAllTransitions(state).stream()
                        .filter(transition -> isMatched(state, eventName, transition, exTransitPredicates))
                        .map(TransitionX::to)
                        .findFirst()
                        .orElse(null);
    }

    @Schema(title = "获取当前状态可以触发的事件", description = "")
    static <S extends State<S>> Collection<String> getEventNames(S state) {

        if (state == null) {
            return Collections.emptyList();
        }

        final Collection<String> result = new java.util.LinkedHashSet<>();

        getAllTransitions(state).stream()
                .filter(transition -> isMatchedFrom(state, transition))
                .map(Transition::eventName)
                .filter(Objects::nonNull)
                .forEach(result::add);

        return result;
    }

    @Schema(title = "获取所有可以触发的事件", description = "")
    static <S extends State<S>> Collection<String> getAllEventNames(S state) {

        if (state == null) {
            return Collections.emptyList();
        }

        final Collection<String> result = new java.util.LinkedHashSet<>();

        getAllTransitions(state).stream()
                .map(Transition::eventName)
                .filter(Objects::nonNull)
                .forEach(result::add);


        return result;
    }

    static <S extends State<S>> TransitionX<S> transition(String eventName, Collection<String> from, S to) {
        return new TransitionX<>() {
            @Override
            public String eventName() {
                return eventName;
            }

            @Override
            public Collection<String> from() {
                return from == null ? Collections.emptyList() : from;
            }

            @Override
            public S to() {
                return to;
            }
        };
    }

    private static <S extends State<S>> TransitionX<S> toTransitionX(S targetState, Transition transition) {

        if (transition instanceof TransitionX<?>) {
            return (TransitionX<S>) transition;
        }

        return transition(transition.eventName(), transition.from(), targetState);
    }

    private static <S extends State<S>> boolean isMatched(S sourceState, String eventName, TransitionX<S> transition, Predicate<TransitionX<S>>... exTransitPredicates) {
        return transition != null
                && Objects.equals(transition.eventName(), eventName)
                && isMatchedFrom(sourceState, transition)
                && isMatchedExPredicates(transition, exTransitPredicates);
    }

    private static <S extends State<S>> boolean isMatchedFrom(S sourceState, Transition transition) {

        if (transition == null) {
            return false;
        }

        final Collection<String> from = transition.from();

        if (from == null || from.isEmpty()) {
            return true;
        }

        for (String fromStateName : from) {
            if (fromStateName == null) {
                if (sourceState == null) {
                    return true;
                }
                continue;
            }

            if (sourceState != null && simpleStateNameMatch(fromStateName, sourceState.name())) {
                return true;
            }
        }

        return false;
    }

    private static <S extends State<S>> boolean isMatchedExPredicates(TransitionX<S> transition, Predicate<TransitionX<S>>... exTransitPredicates) {

        if (exTransitPredicates == null || exTransitPredicates.length == 0) {
            return true;
        }

        for (Predicate<TransitionX<S>> predicate : exTransitPredicates) {
            if (predicate != null && !predicate.test(transition)) {
                return false;
            }
        }

        return true;
    }

    private static boolean simpleStateNameMatch(String pattern, String stateName) {
        if (pattern == null || stateName == null) {
            return Objects.equals(pattern, stateName);
        }

        if (pattern.indexOf('*') < 0 && pattern.indexOf('?') < 0) {
            return Objects.equals(pattern, stateName);
        }

        return PathPatternUtils.matchName(pattern, stateName);
    }

}
