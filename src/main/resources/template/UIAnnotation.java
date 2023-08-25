package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ${name}
 *
 * ${ui.description!''}
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at ${.now}
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "${ui.description!ref!name}")
public @interface ${name} {
///////////////////////////////////////////
${ui.getEnumDefines()}
////////////////////////////////////////////

  /**
   * Any Of
   * ${ui.anyOf!}
   *
   *
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
     * ${props.anyOf!}
     *
     * ${props.getRefTypeAnyOf()!}
     *
     * @see ${props.refType?cap_first}
     */
    ${props.getConsts('@Const({','})')}
    @Schema(title = "${ui.descriptions[porpsName]!props.description!porpsName}")
    ${props.getTypeInfo()} ${porpsName}() ${props.defaultValue()};

</#list>
}
