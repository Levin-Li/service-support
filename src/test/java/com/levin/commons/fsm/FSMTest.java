package com.levin.commons.fsm;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FSMTest {

    @Test
    void shouldSupportEnumStateAsFsmState() {
        assertEquals(Arrays.asList(OrderState.Created, OrderState.Paid, OrderState.Closed),
                new ArrayList<>(OrderState.Created.allStates()),
                "枚举状态默认应能返回全部枚举常量");

        assertTrue(FSM.canTransit(OrderState.Created, OrderState.Paid, "pay"),
                "Created 应能通过 pay 流转到 Paid");
        assertEquals(OrderState.Paid, FSM.fireEvent(OrderState.Created, "pay"),
                "触发 pay 应返回 Paid");
        assertFalse(FSM.canTransit(OrderState.Closed, OrderState.Paid, "pay"),
                "Closed 不应再通过 pay 回到 Paid");
        assertEquals(new LinkedHashSet<>(Arrays.asList("pay", "close")), FSM.getEventNames(OrderState.Created),
                "Created 当前可触发 pay 和 close");
        assertEquals(new LinkedHashSet<>(Arrays.asList("create", "pay", "close")), FSM.getAllEventNames(OrderState.Created),
                "状态机全部事件应去重返回");
    }

    @Test
    void shouldGetAvailableEventNamesFromSingleEnumValue() {
        assertEquals(new LinkedHashSet<>(Collections.singletonList("close")), FSM.getEventNames(OrderState.Paid),
                "Paid 这个枚举值当前只能触发 close");
        assertTrue(FSM.canFireEvent(OrderState.Paid, "close"));
        assertFalse(FSM.canFireEvent(OrderState.Paid, "pay"));
    }

    @Test
    void shouldFilterAvailableEventNamesByExtraPredicates() {
        assertEquals(new LinkedHashSet<>(Collections.singletonList("archive")),
                FSM.getEventNames(TicketState.PendingReview, transition -> transition.to() == TicketState.Archived),
                "附加条件满足时才应返回当前可触发事件");
        assertTrue(FSM.getEventNames(TicketState.PendingReview, transition -> transition.to() == TicketState.Open).isEmpty(),
                "附加条件不满足时不应返回事件");
    }

    @Test
    void shouldExposeAllEventsFromStateContract() {
        assertEquals(new LinkedHashSet<>(Arrays.asList("create", "pay", "close")), eventNames(OrderState.Created.allEvents()),
                "State.allEvents 默认应从所有流转规则推导事件集合");
        assertEquals(new LinkedHashSet<>(Arrays.asList("create", "pay", "close")), eventNames(FSM.getAllEvents(OrderState.Created)),
                "FSM.getAllEvents 应返回 State 约定的所有事件集合");
    }

    @Test
    void shouldUseStateAllEventsAsCatalogWhenGettingAvailableEventNames() {
        DocumentState draft = new DocumentState("draft");
        DocumentState reviewed = new DocumentState("reviewed");
        DocumentState published = new DocumentState("published");
        List<DocumentState> states = Arrays.asList(draft, reviewed, published);

        draft.setAllStates(states);
        reviewed.setAllStates(states);
        published.setAllStates(states);
        reviewed.setTransitions(Collections.singletonList(FSM.transition("review", Collections.singletonList("draft"), reviewed)));
        published.setTransitions(Collections.singletonList(FSM.transition("publish", Collections.singletonList("reviewed"), published)));
        reviewed.setAllEvents(Collections.singletonList(FSM.Event.of("review")));

        assertTrue(FSM.canFireEvent(reviewed, "publish"),
                "流转规则本身仍允许 reviewed 通过 publish 进入 published");
        assertTrue(FSM.getEventNames(reviewed).isEmpty(),
                "getEventNames 应以 State.allEvents 作为事件目录，不暴露未声明事件");
    }

    @Test
    void shouldSupportNonEnumStateObjects() {
        DocumentState draft = new DocumentState("draft");
        DocumentState reviewed = new DocumentState("reviewed");
        DocumentState published = new DocumentState("published");
        List<DocumentState> states = Arrays.asList(draft, reviewed, published);

        draft.setAllStates(states);
        reviewed.setAllStates(states);
        published.setAllStates(states);
        reviewed.setTransitions(Collections.singletonList(FSM.transition("review", Collections.singletonList("draft"), reviewed)));
        published.setTransitions(Collections.singletonList(FSM.transition("publish", Collections.singletonList("reviewed"), published)));

        assertTrue(FSM.canTransit(draft, reviewed, "review"));
        assertEquals(reviewed, FSM.fireEvent(draft, "review"));
        assertTrue(FSM.canTransit(reviewed, published, "publish"));
        assertFalse(FSM.canTransit(draft, published, "publish"));
    }

    @Test
    void shouldSupportEmptySourceWildcardAndExtraTransitionPredicates() {
        assertTrue(FSM.canTransit(null, TicketState.Open, "create"),
                "from 集合包含 null 时，应允许从空状态创建");
        assertTrue(FSM.canTransit(TicketState.Open, TicketState.Done, "finish"),
                "from 为空时，应允许从任意状态进入目标状态");
        assertTrue(FSM.canTransit(TicketState.PendingReview, TicketState.Archived, "archive"),
                "? 通配符应能匹配单个字符状态名");
        assertFalse(FSM.canTransit(TicketState.Open, TicketState.Archived, "archive"),
                "from 条件不匹配时不应允许进入目标状态");
        assertTrue(FSM.canTransit(TicketState.PendingReview, TicketState.Escalated, "escalate"),
                "* 通配符应能匹配状态名前缀");
        assertTrue(FSM.canTransit(TicketState.PendingReview, TicketState.Archived, "archive",
                transition -> transition.to() == TicketState.Archived));
        assertFalse(FSM.canTransit(TicketState.PendingReview, TicketState.Archived, "archive",
                transition -> transition.to() == TicketState.Done),
                "额外 Predicate 不满足时应拒绝流转");
    }

    enum OrderState implements FSM.State<OrderState> {
        Created {
            @Override
            public Collection<? extends FSM.Transition> transitions() {
                return Collections.singletonList(FSM.transition("create", Collections.singletonList((String) null), Created));
            }
        },
        Paid {
            @Override
            public Collection<? extends FSM.Transition> transitions() {
                return Collections.singletonList(FSM.transition("pay", Collections.singletonList("Created"), Paid));
            }
        },
        Closed {
            @Override
            public Collection<? extends FSM.Transition> transitions() {
                return Arrays.asList(
                        FSM.transition("close", Collections.singletonList("Created"), Closed),
                        FSM.transition("close", Collections.singletonList("Paid"), Closed)
                );
            }
        }
    }

    enum TicketState implements FSM.State<TicketState> {
        Open {
            @Override
            public Collection<? extends FSM.Transition> transitions() {
                return Collections.singletonList(FSM.transition("create", Collections.singletonList((String) null), Open));
            }
        },
        PendingReview,
        Done {
            @Override
            public Collection<? extends FSM.Transition> transitions() {
                return Collections.singletonList(FSM.transition("finish", Collections.emptyList(), Done));
            }
        },
        Escalated {
            @Override
            public Collection<? extends FSM.Transition> transitions() {
                return Collections.singletonList(FSM.transition("escalate", Collections.singletonList("Pend*"), Escalated));
            }
        },
        Archived {
            @Override
            public Collection<? extends FSM.Transition> transitions() {
                return Collections.singletonList(FSM.transition("archive", Collections.singletonList("PendingRevie?"), Archived));
            }
        }
    }

    static class DocumentState implements FSM.State<DocumentState> {
        private final String name;
        private Collection<DocumentState> allStates = Collections.singletonList(this);
        private Collection<? extends FSM.Event> allEvents;
        private Collection<? extends FSM.Transition> transitions = Collections.emptyList();

        DocumentState(String name) {
            this.name = name;
        }

        void setAllStates(Collection<DocumentState> allStates) {
            this.allStates = allStates;
        }

        void setTransitions(Collection<? extends FSM.Transition> transitions) {
            this.transitions = transitions;
        }

        void setAllEvents(Collection<? extends FSM.Event> allEvents) {
            this.allEvents = allEvents;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public Collection<? extends FSM.Transition> transitions() {
            return transitions;
        }

        @Override
        public Collection<? extends DocumentState> allStates() {
            return allStates;
        }

        @Override
        public Collection<? extends FSM.Event> allEvents() {
            return allEvents == null ? FSM.State.super.allEvents() : allEvents;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof DocumentState)) {
                return false;
            }
            DocumentState that = (DocumentState) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    private static Collection<String> eventNames(Collection<? extends FSM.Event> events) {
        Collection<String> eventNames = new LinkedHashSet<>();
        for (FSM.Event event : events) {
            eventNames.add(event.name());
        }
        return eventNames;
    }
}
