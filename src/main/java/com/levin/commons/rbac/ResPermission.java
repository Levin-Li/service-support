package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;


/**
 * 资源许可
 */

@Data
@Accessors(chain = true)
@FieldNameConstants
@EqualsAndHashCode
public class ResPermission implements Permission, Serializable {

    @Schema(title = "资源域", description = "逗号隔开", required = true)
    protected String domain;

    @Schema(title = "资源类型", description = "逗号隔开", required = true)
    protected String type;

    @Schema(title = "资源列表", description = "逗号隔开", required = true)
    protected String res;

    @Schema(title = "资源操作列表", description = "逗号隔开", required = true)
    protected String action;

    /**
     * 解析
     *
     * @param expr
     * @return
     */
    public static ResPermission parse(String expr, String delimiter) {

        if (delimiter.contains(",") || delimiter.contains("*")) {
            throw new IllegalArgumentException("delimiter not allow contains comma(,) or asterisk(*)");
        }

        String[] txts = expr.split(delimiter);

        return new ResPermission()
                .setDomain(txts[0])
                .setType(txts[1])
                .setRes(txts[2])
                .setAction(txts[3]);
    }

    /**
     * @param expr
     * @return
     */
    public static ResPermission parse(String expr) {
        return parse(expr, DELIMITER);
    }

    /**
     * @param delimiter
     * @return
     */
    public String toString(String delimiter) {

        if (delimiter.contains(",") || delimiter.contains("*")) {
            throw new IllegalArgumentException("delimiter not allow contains comma(,) or asterisk(*)");
        }

        return String.join(delimiter, domain, type, res, action);

    }

    @Override
    public String toString() {
        return toString(DELIMITER);
    }

}
