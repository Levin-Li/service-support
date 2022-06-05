package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * ${name}
 *
 * ${ui.description!''}
 *
 * @author auto gen by service-support at ${.now}
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "${ui.description!name}")
public @interface ${name} {
///////////////////////////////////////////
${ui.getEnumDefines()}
////////////////////////////////////////////

  /**
   * Any Of
   * ${ui.anyOf!}
   */

<#if ui.hasConsts() >
    String[] consts = { ${ui.getConsts()} };
</#if>

//////////////////////////////////////////////
<#if ui.isPrimitive() && !ui.hasValueKey() >
   /**
    *
    */
   ${ui.getTypeInfo()} value() ${ui.defaultValue()};
</#if>

<#list ui.properties?keys as porpsName>
<#assign props=ui.properties[porpsName]>
    /**
     * ${ui.descriptions[porpsName]!porpsName}
     *
     * 参考定义: ${props.ref!}
     *
     * @see ${props.refType?cap_first}
     */
    @Schema(description = "${ui.descriptions[porpsName]!porpsName}")
    ${props.getTypeInfo()} ${porpsName}() ${props.defaultValue()};

</#list>
}
