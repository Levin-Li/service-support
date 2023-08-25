package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author llw
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)
@Schema(title = "服务交互对象-具体业务逻辑参考[Interaction]接口")
@FieldNameConstants
public class BizInteraction
        implements Interaction {

    private static final long serialVersionUID = -944707546677849710L;

    @Schema(title = "提示级别")
    int level;

    @Schema(title = "标题")
    String title;

    @Schema(title = "提示内容")
    @NotBlank
    String info;

    @Schema(title = "交互动作")
    List<Action> actions;

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class BizAction implements Interaction.Action {

        @Schema(title = "名称")
        String name;

        @Schema(title = "图标")
        String icon;

        @Schema(title = "命令")
        Interaction.Action.Cmd cmd;

        @Schema(title = "命令参数")
        String args;
    }

}
