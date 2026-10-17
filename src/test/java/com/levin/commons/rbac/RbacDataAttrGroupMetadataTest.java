package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class RbacDataAttrGroupMetadataTest {
    @RbacDataAttrGroup(name = "finance", attrList = {"amount", "amount"}, confidentialLevel = 100)
    @RbacDataAttrGroup(name = "contact", attrList = "phone")
    static class Parent {
        @RbacDataAttrGroup(name = "finance", confidentialLevel = 200, remark = "amount")
        @RbacDataAttrGroup(name = "audit")
        int amount;
        String phone;
    }

    static class Child extends Parent {
        @RbacDataAttrGroup(name = "finance", remark = "rate") int rate;
        @RbacDataAttrGroup @Schema(title = "Account") String account;
        @Schema(title = "Enabled") boolean enabled;
        @RbacDataAttrGroup public boolean isEnabled() { return enabled; }
        @RbacDataAttrGroup(name = "contact") public String getPhone() { return phone; }
    }

    @Test void mergesRepeatedTypeFieldGetterAndInheritedDeclarations() {
        var metadata = RbacDataAttrGroupMetadata.forType(Child.class);
        assertEquals(Set.of("finance", "contact", "audit", "Account", "Enabled"), metadata.getGroups().keySet());
        var finance = metadata.getGroups().get("finance");
        assertEquals(Set.of("amount", "rate"), finance.getAttributes());
        assertEquals(200, finance.getConfidentialLevel());
        assertTrue(finance.getRemark().contains("amount"));
        assertTrue(finance.getRemark().contains("rate"));
        assertEquals(2, metadata.getGroupsForAttribute("amount").size());
        assertEquals(Set.of("phone"), metadata.getGroups().get("contact").getAttributes());
        assertTrue(metadata.getGroupsForAttribute("missing").isEmpty());
    }

    @Test void cachesPositiveAndNegativeResultsAndKeepsThemImmutable() {
        var metadata = RbacDataAttrGroupMetadata.forType(Child.class);
        assertSame(metadata, RbacDataAttrGroupMetadata.forType(Child.class));
        var empty = RbacDataAttrGroupMetadata.forType(String.class);
        assertSame(empty, RbacDataAttrGroupMetadata.forType(String.class));
        assertTrue(empty.getGroups().isEmpty());
        assertThrows(UnsupportedOperationException.class, () -> metadata.getGroups().clear());
        assertThrows(UnsupportedOperationException.class, () -> metadata.getGroups().get("finance").getAttributes().clear());
        assertThrows(UnsupportedOperationException.class, () -> metadata.getGroupsForAttribute("amount").clear());
    }

    static class ConcurrentType { @RbacDataAttrGroup(name = "concurrent") int field; }

    @Test void concurrentFirstAccessReusesOneResult() throws Exception {
        var executor = Executors.newFixedThreadPool(12);
        try {
            List<java.util.concurrent.Callable<RbacDataAttrGroupMetadata>> tasks = new ArrayList<>();
            for (int i = 0; i < 100; i++) tasks.add(() -> RbacDataAttrGroupMetadata.forType(ConcurrentType.class));
            var futures = executor.invokeAll(tasks);
            var first = futures.get(0).get();
            for (var future : futures) assertSame(first, future.get());
        } finally { executor.shutdownNow(); }
    }

    interface Contract {
        @RbacDataAttrGroup @Schema(title = "Computed") String getComputed();
    }
    static class Implementation implements Contract {
        public String getComputed() { return ""; }
    }

    @Test void inheritsInterfaceGetterSchema() {
        assertEquals(Set.of("computed"), RbacDataAttrGroupMetadata.forType(Implementation.class)
                .getGroups().get("Computed").getAttributes());
    }

    @RbacDataAttrGroup(attrList = "amount") static class MissingName { int amount; }
    @RbacDataAttrGroup(name = "bad") static class MissingMembers { int amount; }
    @RbacDataAttrGroup(name = "bad", attrList = "missing") static class MissingProperty { int amount; }
    static class MissingTitle { @RbacDataAttrGroup int amount; }
    static class InvalidMethod { @RbacDataAttrGroup(name = "bad") void update(int value) {} }

    @Test void rejectsInvalidDeclarationsAndCachesFailure() {
        for (Class<?> type : List.of(MissingName.class, MissingMembers.class, MissingProperty.class,
                MissingTitle.class, InvalidMethod.class)) {
            var first = assertThrows(IllegalArgumentException.class, () -> RbacDataAttrGroupMetadata.forType(type));
            assertSame(first, assertThrows(IllegalArgumentException.class, () -> RbacDataAttrGroupMetadata.forType(type)));
        }
    }

    static class Envelope { Child payload; }
    @Test void emptyEnvelopeDoesNotClassifyItsChildAsUnrestricted() {
        assertTrue(RbacDataAttrGroupMetadata.forType(Envelope.class).getGroups().isEmpty());
        assertFalse(RbacDataAttrGroupMetadata.forType(Child.class).getGroups().isEmpty());
    }

    static class BooleanPrefix {
        @Schema(title = "启用") private boolean isActive;
        @RbacDataAttrGroup public boolean isActive() { return isActive; }
    }
    static class BooleanPrefixChild extends BooleanPrefix {}
    static class BothBooleanNames {
        @Schema(title = "普通启用") boolean active;
        @Schema(title = "前缀启用") boolean isActive;
        @RbacDataAttrGroup public boolean isActive() { return active; }
    }
    static class OrdinaryGetter {
        @Schema(title = "原始名称") boolean isActive;
        @RbacDataAttrGroup @Schema(title = "普通 getter") public boolean getActive() { return isActive; }
    }

    @Test void booleanGetterResolvesSourceFieldAndPreservesOrdinaryPropertyPriority() {
        for (Class<?> type : List.of(BooleanPrefix.class, BooleanPrefixChild.class)) {
            var metadata = RbacDataAttrGroupMetadata.forType(type);
            assertEquals(Set.of("isActive"), metadata.getGroups().get("启用").getAttributes());
            assertEquals(1, metadata.getGroupsForAttribute("isActive").size());
            assertTrue(metadata.getGroupsForAttribute("active").isEmpty());
        }
        var both = RbacDataAttrGroupMetadata.forType(BothBooleanNames.class);
        assertEquals(Set.of("active"), both.getGroups().get("普通启用").getAttributes());
        assertTrue(both.getGroupsForAttribute("isActive").isEmpty());
        var ordinary = RbacDataAttrGroupMetadata.forType(OrdinaryGetter.class);
        assertEquals(Set.of("active"), ordinary.getGroups().get("普通 getter").getAttributes());
    }

}
