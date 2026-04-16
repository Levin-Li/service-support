package com.levin.commons.ui.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.ui.annotation.FormLayout;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

/**
 * {@link FormLayout} 的模型对象。
 */
@Data
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
public class FormLayoutModel implements FormLayout {

    @Schema(title = "类名", description = "注解所在类的全名，用于全局定位")
    String className = "";

    @Schema(title = "别名", description = "用于全局标识的别名")
    String alias = "";

    @Schema(title = "标题")
    String title = "";

    @Schema(title = "本布局在父布局中的占位顺序", description = "默认为-1，表示默认")
    int order = -1;

    @Schema(title = "布局项栅格占位", description = "默认为-1，表示使用布局默认值")
    int span = -1;

    @Schema(title = "列数", description = "默认为-1, 表示自动处理, 建议总字段数除7行,得出列数,但是不能超过父分组的列数")
    int columns = -1;

    @Schema(title = "最大列数", description = "默认为4,超过4列不方便")
    int maxColumns = 4;

    @Schema(title = "展示分类", description = "通常用于表单项过多时, 展示基本部分还是全部, 比如高级搜索,简单搜索, 基本信息, 更多信息等场景")
    String[] showCategories = {"更多"};

    @Schema(title = "分组", description = "分组关联表单项, 通过布局分组实现布局嵌套，通过点分割,如base.addr,标识这个布局是嵌套在base组中")
    String group = "";

    FormLayout.Type type = FormLayout.Type.vbox;

    @Schema(title = "布局参数", description = "json格式, 具体有布局类型解析和使用")
    String layoutParams = "";

    @Schema(title = "显示条件", description = "前端Js表达式，返回true时布局显示")
    String visibleOn = "";

    @Schema(title = "样式")
    String style = "";

    @Override
    public Class<? extends Annotation> annotationType() {
        return FormLayout.class;
    }
}
