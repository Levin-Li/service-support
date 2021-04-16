package com.levin.commons.uiform.annotation;

public interface ISchemaForm {

    String schema(); // 通过 schema 渲染表单

    String formComponent(); // 全局注册 Form 渲染组件

    String formItemComponent(); // 全局注册 FormItem 渲染组件

    int labelCol(); // label 布局控制

    int wrapperCol(); // FormItem 布局控制

    String previewPlaceholder(); // 详情页的文本态占位符

    String prefix(); // 样式前缀

    boolean inline(); // 是否为内联表单

    int size(); // 单个 Item 的 size 自定义，优先级高于 Form 的 size, 并且当组件与 Item 一起使用时，组件自身设置 size 属性无效。

    String labelAlign(); // 标签的位置

    String className(); // 扩展 class

    String style(); // 自定义内联样式

    String component(); // 设置标签类型

    String value(); // 全局 value

    String defaultValue(); // 全局 defaultValue

    String initialValues(); // 全局 initialValues

    String actions(); // FormActions 实例

    String effects(); // IFormEffect 实例

    String form(); // 表单实例

    String onChange(); // 表单变化回调

    String onSubmit(); // form 内有 htmlType="submit" 或 actions.submit 时 触发

    String onReset(); // form 内有 或 actions.reset 时 触发

    String onValidateFailed(); // 校验失败时触发

    String children(); // 全局 value

    boolean useDirty(); // 是否使用脏检查，默认会走 immer 精确更新

    boolean editable(); // 是否可编辑

    boolean validateFirst(); // 是否走悲观校验，遇到第一个校验失败就停止后续校验

}
