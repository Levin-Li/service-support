package com.levin.commons.ui.annotation;

import com.levin.commons.annotation.GenNameConstant;
import com.levin.commons.service.domain.EnumDesc;

/**
 * 代码类型
 *
 * 目前这些类型，都是基于 amis 的代码编辑器
 *
 */

@GenNameConstant
public enum CodeType implements EnumDesc {
    BAT,
    C,
    COFFEESCRIPT,
    CPP,
    CSHARP,
    CSS,
    DOCKERFILE,
    FSHARP,
    GO,
    HANDLEBARS,
    HTML,
    INI,
    JAVA,
    JAVASCRIPT,
    JSON,
    LESS,
    LUA,
    MARKDOWN,
    MSDAX,
    OBJECTIVE_C,
    PHP,
    PLAINTEXT,
    POSTIATS,
    POWERSHELL,
    PUG,
    PYTHON,
    R,
    RAZOR,
    RUBY,
    SB,
    SCSS,
    SOL,
    SHELL,
    SQL,
    SWIFT,
    TYPESCRIPT,
    VB,
    XML,
    YAML;

    // 如果需要为每个枚举常量添加额外的属性或方法，可以在这里继续添加

    /**
     * 枚举常量的别名
     *
     * @return
     */
    public String getAlias() {
        return name().toLowerCase().replace('_', '-');
    }

}