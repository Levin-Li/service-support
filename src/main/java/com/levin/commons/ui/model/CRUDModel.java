package com.levin.commons.ui.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.ui.annotation.CRUD;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link CRUD} 的模型对象。
 */
@Data
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@Schema(title = "CRUD页面", description = "通常注解在控制器类上, 用于标识这个是一个CRUD页面, 一个页面中允许存在多个列表, 多个列表时使用tab样式展示")
public class CRUDModel implements CRUD {

    @Schema(title = "类名", description = "注解所在的类全名，用于全局定位")
    String className = "";

    @Schema(title = "别名", description = "用于全局标识的别名")
    String alias = "";

    @Schema(title = "名称", description = "默认取控制器@Tag注解")
    String name = "";

    @Schema(title = "标题")
    String title = "";

    @Schema(title = "样式", description = "样式")
    String style = "";

    @Schema(title = "描述")
    String desc = "";

    @Schema(title = "操作列表", description = "页面的操作")
    List<CRUDOpModel> opList = new ArrayList<>();

    @Schema(title = "列表集合", description = "当前CRUD页面包含的多个列表")
    List<CRUDListTableModel> listTableList = new ArrayList<>();

    @Override
    public Class<? extends Annotation> annotationType() {
        return CRUD.class;
    }
}
