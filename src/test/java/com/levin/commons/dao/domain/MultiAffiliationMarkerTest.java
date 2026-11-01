package com.levin.commons.dao.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class MultiAffiliationMarkerTest {

    static class MultiAffiliationEntity implements MultiOrgObject, MultiOwnerObject {
    }

    @Test
    void markersRequireNoSingleAffiliationContract() {
        MultiAffiliationEntity entity = new MultiAffiliationEntity();

        assertInstanceOf(MultiOrgObject.class, entity);
        assertInstanceOf(MultiOwnerObject.class, entity);
        assertEquals(0, MultiOrgObject.class.getDeclaredMethods().length);
        assertEquals(0, MultiOwnerObject.class.getDeclaredMethods().length);
        assertEquals(0, MultiOrgObject.class.getInterfaces().length);
        assertEquals(0, MultiOwnerObject.class.getInterfaces().length);
        assertFalse(OrganizedObject.class.isAssignableFrom(MultiOrgObject.class));
        assertFalse(PersonalObject.class.isAssignableFrom(MultiOwnerObject.class));
    }
}
