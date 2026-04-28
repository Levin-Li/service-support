package com.levin.commons.ui.annotation;

import com.levin.commons.annotation.GenNameConstant;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;


/**
 * @author lilw
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "文件资源")
public @interface FileRes {

    @Schema(title = "类型")
    @GenNameConstant
    enum Type implements EnumDesc {

        @Schema(title = "Html")
        Html,

        @Schema(title = "Css")
        Css,

        @Schema(title = "Js")
        Js,

        @Schema(title = "Markdown")
        Markdown,

        @Schema(title = "Word")
        Word,

        @Schema(title = "Excel")
        Excel,

        @Schema(title = "PowerPoint")
        Ppt,

        @Schema(title = "Pdf")
        Pdf,

        @Schema(title = "Xml")
        Xml,

        @Schema(title = "文本")
        Text,

        @Schema(title = "Yaml")
        Yaml,

        @Schema(title = "Json")
        Json,

        @Schema(title = "JsonSchema")
        JsonSchema,

        @Schema(title = "图片")
        Image,

        @Schema(title = "视频")
        Video,

        @Schema(title = "音频")
        Audio,

        @Schema(title = "文件", description = "默认文件")
        File,
        ;

        @Override
        public String toString() {
            return nameAndDesc();
        }
    }

    Type type();

}
