package com.levin.commons.utils;

import com.levin.commons.service.support.InjectConsts;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionUtilsTest {

    @Test
    void evalGroovy() {

        Object value1 = "ORG123";
        Object value = ExpressionUtils.evalGroovy( "" + InjectConsts.ORG,
                null, MapUtils.put(InjectConsts.ORG, value1).build());

        Assert.isTrue(value == value1, "");
    }

    @Test
    void testEvalGroovy() {


    }

    @Test
    void evalSpEL() {

        Object value1 = "ORG123";

        Object value = ExpressionUtils.evalSpEL(null, "#" + InjectConsts.ORG,
                null, MapUtils.put(InjectConsts.ORG, value1).build());

        Assert.isTrue(value == value1, "");

    }

    @Test
    void testEvalSpEL() {
    }
}