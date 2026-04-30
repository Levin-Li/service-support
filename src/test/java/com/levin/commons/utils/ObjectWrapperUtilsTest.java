package com.levin.commons.utils;

import com.levin.commons.dao.domain.ProxyWrapperObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class ObjectWrapperUtilsTest {

    @Test
    void wrapper2ReadonlyShouldExposeLatestValueAndOriginalObject() {

        DemoBean original = new DemoBean();
        original.setName("before");
        original.setTags(new ArrayList<>(List.of("A")));

        DemoBean readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertTrue(readonly instanceof ProxyWrapperObject);
        Assertions.assertSame(original, ((ProxyWrapperObject) readonly).getOriginalObject());
        Assertions.assertEquals("before", readonly.getName());
        Assertions.assertEquals(List.of("A"), readonly.getTags());

        original.setName("after");
        original.getTags().add("B");

        Assertions.assertEquals("after", readonly.getName());
        Assertions.assertEquals(List.of("A", "B"), readonly.getTags());
    }

    @Test
    void wrapper2ReadonlyShouldBlockJavaBeanSetterMutation() {

        DemoBean readonly = ObjectWrapperUtils.wrapper2Readonly(new DemoBean());

        Assertions.assertThrows(UnsupportedOperationException.class, () -> readonly.setName("new-name"));
    }

    @Test
    void wrapper2ReadonlyShouldFreezeCollectionProperties() {

        DemoBean original = new DemoBean();
        original.setTags(new ArrayList<>(List.of("A")));

        DemoBean readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> readonly.getTags().add("B"));

        original.getTags().add("B");
        Assertions.assertEquals(List.of("A", "B"), readonly.getTags());
    }

    @Test
    void wrapper2ReadonlyShouldNotFreezeConcreteCollectionReturnTypes() {

        ConcreteCollectionBean original = new ConcreteCollectionBean();
        original.tags.add("A");

        ConcreteCollectionBean readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertDoesNotThrow(() -> readonly.getTags().add("B"));
        Assertions.assertEquals(List.of("A", "B"), original.getTags());
    }

    @Test
    void wrapper2ReadonlyShouldFreezeMapPropertiesOnlyWhenReturnTypeIsMapInterface() {

        MapBean original = new MapBean();
        original.values.put("A", 1);

        MapBean readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> readonly.getValues().put("B", 2));
        original.values.put("B", 2);
        Assertions.assertEquals(2, readonly.getValues().get("B"));
    }

    @Test
    void wrapper2ReadonlyShouldNotFreezeConcreteMapReturnTypes() {

        ConcreteMapBean original = new ConcreteMapBean();
        original.values.put("A", 1);

        ConcreteMapBean readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertDoesNotThrow(() -> readonly.getValues().put("B", 2));
        Assertions.assertEquals(2, original.getValues().get("B"));
    }

    @Test
    void wrapper2ReadonlyShouldNotFreezeGenericCollectionTypeVariables() {

        GenericCollectionBean<ArrayList<String>> original = new GenericCollectionBean<>(new ArrayList<>(List.of("A")));

        GenericCollectionBean<ArrayList<String>> readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertDoesNotThrow(() -> readonly.getTags().add("B"));
        Assertions.assertEquals(List.of("A", "B"), original.getTags());
    }

    @Test
    void wrapper2ReadonlyShouldFreezeGenericCollectionTypeVariablesResolvedByProxyTargetClass() {

        GenericListCollectionBean original = new GenericListCollectionBean(new ArrayList<>(List.of("A")));

        GenericListCollectionBean readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> readonly.getTags().add("B"));
        original.getTags().add("B");
        Assertions.assertEquals(List.of("A", "B"), readonly.getTags());
    }

    @Test
    void wrapper2ReadonlyShouldNotFreezeCollectionReturnTypesWithUnresolvedGenericArguments() {

        GenericElementListBean<String> original = new GenericElementListBean<>(new ArrayList<>(List.of("A")));

        GenericElementListBean<String> readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertDoesNotThrow(() -> readonly.getTags().add("B"));
        Assertions.assertEquals(List.of("A", "B"), original.getTags());
    }

    @Test
    void wrapper2ReadonlyShouldFreezeCollectionReturnTypesWithGenericArgumentsResolvedByProxyTargetClass() {

        StringElementListBean original = new StringElementListBean(new ArrayList<>(List.of("A")));

        StringElementListBean readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> readonly.getTags().add("B"));
        original.getTags().add("B");
        Assertions.assertEquals(List.of("A", "B"), readonly.getTags());
    }

    @Test
    void wrapper2ReadonlyShouldNotFreezeGenericMapTypeVariables() {

        HashMap<String, Integer> values = new LinkedHashMap<>();
        values.put("A", 1);
        GenericMapBean<HashMap<String, Integer>> original = new GenericMapBean<>(values);

        GenericMapBean<HashMap<String, Integer>> readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertDoesNotThrow(() -> readonly.getValues().put("B", 2));
        Assertions.assertEquals(2, original.getValues().get("B"));
    }

    @Test
    void wrapper2ReadonlyShouldFreezeGenericMapTypeVariablesResolvedByProxyTargetClass() {

        GenericInterfaceMapBean original = new GenericInterfaceMapBean(new LinkedHashMap<>(Map.of("A", 1)));

        GenericInterfaceMapBean readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> readonly.getValues().put("B", 2));
        original.getValues().put("B", 2);
        Assertions.assertEquals(2, readonly.getValues().get("B"));
    }

    @Test
    void wrapper2ReadonlyShouldNotFreezeMapReturnTypesWithUnresolvedGenericArguments() {

        GenericValueMapBean<Integer> original = new GenericValueMapBean<>(new LinkedHashMap<>(Map.of("A", 1)));

        GenericValueMapBean<Integer> readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertDoesNotThrow(() -> readonly.getValues().put("B", 2));
        Assertions.assertEquals(2, original.getValues().get("B"));
    }

    @Test
    void wrapper2ReadonlyShouldFreezeMapReturnTypesWithGenericArgumentsResolvedByProxyTargetClass() {

        IntegerValueMapBean original = new IntegerValueMapBean(new LinkedHashMap<>(Map.of("A", 1)));

        IntegerValueMapBean readonly = ObjectWrapperUtils.wrapper2Readonly(original);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> readonly.getValues().put("B", 2));
        original.getValues().put("B", 2);
        Assertions.assertEquals(2, readonly.getValues().get("B"));
    }

    @Test
    void wrapperByProxyShouldDelegateToOriginalObject() {

        DemoBean original = new DemoBean();
        original.setName("before");

        DemoBean proxy = ObjectWrapperUtils.wrapperByProxy(original, null);

        Assertions.assertTrue(proxy instanceof ProxyWrapperObject);
        Assertions.assertSame(original, ((ProxyWrapperObject) proxy).getOriginalObject());
        Assertions.assertEquals("before", proxy.getName());

        proxy.setName("after");

        Assertions.assertEquals("after", original.getName());
    }

    public static class DemoBean {

        private String name;

        private List<String> tags = new ArrayList<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public void renameBySetter(String name) {
            setName(name);
        }
    }

    public static class ConcreteCollectionBean {

        private final ArrayList<String> tags = new ArrayList<>();

        public ArrayList<String> getTags() {
            return tags;
        }
    }

    public static class MapBean {

        private final Map<String, Integer> values = new LinkedHashMap<>();

        public Map<String, Integer> getValues() {
            return values;
        }
    }

    public static class ConcreteMapBean {

        private final HashMap<String, Integer> values = new LinkedHashMap<>();

        public HashMap<String, Integer> getValues() {
            return values;
        }
    }

    public static class GenericCollectionBean<T extends List<String>> {

        private T tags;

        public GenericCollectionBean() {
        }

        public GenericCollectionBean(T tags) {
            this.tags = tags;
        }

        public T getTags() {
            return tags;
        }
    }

    public static class GenericListCollectionBean extends GenericCollectionBean<List<String>> {

        public GenericListCollectionBean() {
        }

        public GenericListCollectionBean(List<String> tags) {
            super(tags);
        }
    }

    public static class GenericElementListBean<T> {

        private List<T> tags;

        public GenericElementListBean() {
        }

        public GenericElementListBean(List<T> tags) {
            this.tags = tags;
        }

        public List<T> getTags() {
            return tags;
        }
    }

    public static class StringElementListBean extends GenericElementListBean<String> {

        public StringElementListBean() {
        }

        public StringElementListBean(List<String> tags) {
            super(tags);
        }
    }

    public static class GenericMapBean<T extends Map<String, Integer>> {

        private T values;

        public GenericMapBean() {
        }

        public GenericMapBean(T values) {
            this.values = values;
        }

        public T getValues() {
            return values;
        }
    }

    public static class GenericInterfaceMapBean extends GenericMapBean<Map<String, Integer>> {

        public GenericInterfaceMapBean() {
        }

        public GenericInterfaceMapBean(Map<String, Integer> values) {
            super(values);
        }
    }

    public static class GenericValueMapBean<T> {

        private Map<String, T> values;

        public GenericValueMapBean() {
        }

        public GenericValueMapBean(Map<String, T> values) {
            this.values = values;
        }

        public Map<String, T> getValues() {
            return values;
        }
    }

    public static class IntegerValueMapBean extends GenericValueMapBean<Integer> {

        public IntegerValueMapBean() {
        }

        public IntegerValueMapBean(Map<String, Integer> values) {
            super(values);
        }
    }
}
