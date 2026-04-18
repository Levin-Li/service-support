package com.levin.commons.rbac;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.annotation.GenNameConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Collection;

@Schema(title = "数据范围")

@GenNameConstant
@Data
@Accessors(chain = true)
@ToString

@JsonAutoDetect(
        // 只序列化字段
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        // 忽略所有 getter
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        // 忽略 isXXX
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)

public class SimpleDataScope implements DataScope {

    @Schema(title = "组织范围")
    Collection<? extends OrgScope> orgScopeList;

    @Schema(title = "数据访问级别")
    Integer confidentialDataAccessLevel;

}
