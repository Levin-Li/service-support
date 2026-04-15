package com.levin.commons.ui.model;

import com.levin.commons.ui.annotation.CRUD;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

/**
 * {@link CRUD.Op} 的模型对象。
 */
@Data
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
@Schema(title = "操作", description = "通常注解在控制器方法上")
public class CRUDOpModel implements CRUD.Op {

    @Schema(title = "类名", description = "注解所在的类全名，用于全局定位")
    String className = "";

    @Schema(title = "方法名", description = "注解所在方法名，用于全局定位")
    String methodName = "";

    @Schema(title = "别名", description = "用于全局标识的别名")
    String alias = "";

    @Schema(title = "操作名称")
    String name = "";

    @Schema(title = "操作按钮的显示名称")
    String label = "";

    @Schema(title = "操作按钮的图标", description = "可以是图标的链接,也可以是fontawesome的字体图标,目前支持V4版本,https://fontawesome.com/v4/icons/")
    String icon = "";

    @Schema(title = "操作确认弹窗提示内容", description = "操作前确认提示内容, None,作为特殊关键字,表示无需确认")
    String confirmText = "";

    @Schema(title = "操作确认弹窗标题")
    String confirmTitle = "";

    @Schema(title = "操作等级")
    CRUD.Level level = CRUD.Level.Auto;

    @Schema(title = "操作动作")
    CRUD.Action action = CRUD.Action.Auto;

    @Schema(title = "视图容器类型", description = "对操作是视图时有效")
    CRUD.ViewContainerType viewContainerType = CRUD.ViewContainerType.Auto;

    @Schema(title = "操作使用的数据", description = "一般情况下使用当前记录的数据")
    String actionData = "";

    @Schema(title = "操作按钮的显示条件", description = "js脚本, 需要支持 _user 变量, 如果是关联单条记录的操作")
    String visibleOn = "";

    @Schema(title = "操作成功后的动作")
    CRUD.Action successAction = CRUD.Action.Auto;

    @Schema(title = "操作失败后的动作")
    CRUD.Action failAction = CRUD.Action.Auto;

    @Schema(title = "操作后动作使用的数据", description = "一般情况下使用Api调用结果的数据")
    String resultActionData = "";

    @Schema(title = "操作关联的目标类型")
    CRUD.OpRefTargetType opRefTargetType = CRUD.OpRefTargetType.SingleRow;

    @Schema(title = "操作关联的名字", description = "如果为空，则表示是页面的操作")
    String opRefTargetName = "default";

    String desc = "";

    @Schema(title = "关联表单", description = "操作关联的表单定义")
    FormModel form;

    @Override
    public Class<? extends Annotation> annotationType() {
        return CRUD.Op.class;
    }
}
