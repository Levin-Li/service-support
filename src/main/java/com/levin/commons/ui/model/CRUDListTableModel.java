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
 * {@link CRUD.ListTable} 的模型对象。
 */
@Data
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@Schema(title = "列表", description = "通常注解在控制器的列表方法上")
public class CRUDListTableModel implements CRUD.ListTable {

    @Schema(title = "类名", description = "注解所在类的全名，用于全局定位")
    String className = "";

    @Schema(title = "方法ID", description = "完整的泛型方法签名")
    String methodId = "";

    @Schema(title = "方法名", description = "注解所在方法名，用于全局定位")
    String methodName = "";

    @Schema(title = "别名", description = "用于全局标识的别名")
    String alias = "";

    @Schema(title = "列表名称", description = "用于关联CURD的操作")
    String name = "default";

    @Schema(title = "标题", description = "通常用于在一个CURD页面中有多个列表时")
    String title = "";

    @Schema(title = "列表关联的实体类", description = "也可能不是具体的一个实体")
    Class<?> refEntityClass = Void.class;

    @Schema(title = "显示条件", description = "Js脚本")
    String visibleOn = "";

    @Schema(title = "样式", description = "样式")
    String style = "";

    String desc = "";

    @Schema(title = "操作列表", description = "归属本列表的操作")
    List<CRUDOpModel> opList = new ArrayList<>();

    @Override
    public Class<? extends Annotation> annotationType() {
        return CRUD.ListTable.class;
    }
}
