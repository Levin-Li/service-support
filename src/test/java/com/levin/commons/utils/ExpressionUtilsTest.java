package com.levin.commons.utils;

import com.levin.commons.service.support.InjectConsts;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static com.levin.commons.utils.ExceptionUtils.getZhDesc;
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

        System.out.println(getZhDesc("类型: 1 创建  2 完成支付变为审核中  3 审核中变为加热\n4 审核中变为审核不通过 5 修改自动关停配置 (关闭、启用、修改关停条件) 6 手动关停\n7 自动关停 8 加热中变为退款中 \r 9 状态变为 已完成或已结束"));


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
