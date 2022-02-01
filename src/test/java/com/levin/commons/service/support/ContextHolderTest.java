package com.levin.commons.service.support;

import org.junit.jupiter.api.Test;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

class ContextHolderTest {


    static class TestObj {

                Integer[] index;
//        List<Integer> index;

    }

    @Test
    void get() {

        PrimitiveArrayJsonConverter converter = new PrimitiveArrayJsonConverter();

        Field field = ReflectionUtils.findField(TestObj.class, "index");

        Object convert = converter.convert("[\"1\",\"2\",\"3\",\"4\"]", new TypeDescriptor(ResolvableType.forClass(String.class), String.class, new Annotation[]{}), new TypeDescriptor(field));

        System.out.println(convert);

    }

}