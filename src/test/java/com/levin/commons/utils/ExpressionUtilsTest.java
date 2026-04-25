package com.levin.commons.utils;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

class ExpressionUtilsTest {

    @Test
    void testEvalGroovy() {

        List<Object> value = Collections.emptyList();

        Assert.isTrue(ExpressionUtils.isEmpty( value), " ExpressionUtils.isEmpty fail");

        Assert.isTrue(!ExpressionUtils.isNotEmpty( value), " ExpressionUtils.isNotEmpty fail");


        Assert.isTrue(ExpressionUtils.isEmpty( null), " ExpressionUtils.isEmpty fail");

        Assert.isTrue(!ExpressionUtils.isNotEmpty( null), " ExpressionUtils.isNotEmpty fail");
        
    }

    @Test
    void testEvalSpEL() {
    }
}
