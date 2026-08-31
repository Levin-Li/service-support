package com.levin.commons.fsm;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FSMHelperTest {

    @Test
    void shouldCreateEventAndExposeEventSource() {
        final FsmEvent event = FSMHelper.newFsmEvent("ACCEPT", FsmEventSource.System, "系统受理");

        assertEquals("ACCEPT", event.name());
        assertEquals("系统受理", event.description());
        assertEquals(FsmEventSource.System, event.source());
        assertEquals(FsmEventSource.User, FSMHelper.newUserFsmEvent("SUBMIT").source());
        assertThrows(IllegalArgumentException.class, () -> FSMHelper.newUserFsmEvent("  "));
        assertThrows(NullPointerException.class, () -> FSMHelper.newFsmEvent("SUBMIT", null, "提交"));
    }

    @Test
    void shouldReturnEventsForRulesThatStartFromCurrentState() {
        final TestState draft = new TestState("DRAFT");
        final TestState submitted = new TestState("SUBMITTED");
        final FsmState<TestEvent> sourceState = draft;
        final FsmState<TestEvent> targetState = submitted;

        draft.transitionRules = List.of(FSMHelper.newFsmStateTransitionRule(
                sourceState, TestEvent.SUBMIT, targetState));

        assertEquals(List.of(TestEvent.SUBMIT), FSMHelper.canFireEvents(draft));
        assertEquals(List.of("SUBMIT"), FSMHelper.canFireEventNames(draft));
        assertEquals(List.of(TestEvent.SUBMIT), draft.canFireEvents());
        assertTrue(draft.canFireEvent("SUBMIT"));
        assertFalse(draft.canFireEvent("ACCEPT"));
        assertEquals(submitted, draft.fireEvent("SUBMIT"));
        assertThrows(IllegalStateException.class, () -> submitted.fireEvent("SUBMIT"));

        final FSM<TestEvent> fsm = () -> List.of(draft, submitted);
        assertEquals(List.of(draft, submitted), fsm.allStates());
        assertTrue(FSMHelper.canFireEvents(submitted).isEmpty());
    }

    @Test
    void shouldMatchEquivalentStateNamesAndRejectMismatchedSourceRules() {
        final TestState draft = new TestState("DRAFT");
        final TestState sameNameDraft = new TestState("DRAFT");
        final TestState submitted = new TestState("SUBMITTED");

        draft.transitionRules = List.of(FSMHelper.newFsmStateTransitionRule(
                sameNameDraft, TestEvent.SUBMIT, submitted));
        assertEquals(List.of(TestEvent.SUBMIT), draft.canFireEvents(),
                "同名状态对象应按状态名称视为同一个源状态");
        assertEquals(submitted, draft.fireEvent("SUBMIT"));

        draft.transitionRules = List.of(FSMHelper.newFsmStateTransitionRule(
                submitted, TestEvent.SUBMIT, draft));
        assertTrue(draft.canFireEvents().isEmpty(), "源状态不匹配时不应暴露事件");
        assertThrows(IllegalStateException.class, () -> draft.fireEvent("SUBMIT"),
                "源状态不匹配时不应实际触发事件");
    }

    @Test
    void shouldRejectIncompleteTransitionRules() {
        final TestState draft = new TestState("DRAFT");
        final TestState submitted = new TestState("SUBMITTED");

        assertThrows(NullPointerException.class,
                () -> FSMHelper.newFsmStateTransitionRule(null, TestEvent.SUBMIT, submitted));
        assertThrows(NullPointerException.class,
                () -> FSMHelper.newFsmStateTransitionRule(draft, null, submitted));
        assertThrows(NullPointerException.class,
                () -> FSMHelper.newFsmStateTransitionRule(draft, TestEvent.SUBMIT, null));
    }

    enum TestEvent implements FsmEvent {
        SUBMIT
    }

    static class TestState implements FsmState<TestEvent> {
        private final String name;
        private List<? extends FsmStateTransitionRule<TestEvent, FsmState<TestEvent>>> transitionRules = List.of();

        TestState(String name) {
            this.name = name;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public List<? extends FsmStateTransitionRule<TestEvent, FsmState<TestEvent>>> transitionRules() {
            return transitionRules;
        }
    }
}
