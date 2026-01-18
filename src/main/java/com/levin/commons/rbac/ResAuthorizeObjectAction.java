package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

/**
 * 资源访问验证
 * <p>
 * 可以注解在类上，表示应用在所有的方法上。
 * <p>
 * 在调用方法前验证方法是否指定的资源授权。
 * <p>
 * 该注解出于简单考虑
 * <p>
 * <p>
 * 复杂的判断建议使用表达式
 * <p>
 * <p>
 * 注意，可以设置空格覆盖类定义
 *
 * @author llw
 */

@Data
@Accessors(fluent = true, chain = true)
@GenNameConstant
public class ResAuthorizeObjectAction extends ResConditionActionObject implements ResAuthorize {

    /// ////////////////////////////////////////////////////////////
    @Schema(title = "资源域")
    String domain = "";

    @Schema(title = "类型")
    String type = "";

    @Schema(title = "资源")
    String res = "";

    @Override
    public Class<? extends Annotation> annotationType() {
        return ResAuthorize.class;
    }
}
