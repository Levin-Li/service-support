package com.levin.commons.enums;

/**
 * 可操作描述
 *
 * @author sean
 */

public enum DescOperable {

    // 可自动装载（查询使用）
    LOADABLE,
    // 可编辑的（列表使用）
    EDITABLE,
    // 切换参数（查询使用）
    TABABLE,
    // 固定查询参数（查询使用）
    FIXABLE,
    // 不可变值
    IMMUTABLE,
    // 自动裁剪（包括去除头尾空格和按最长允许长度裁剪）
    CUTABLE,
    // 转小写
    TO_LOWER_CASE,
    // 转大写
    TO_UPPER_CASE,
    // 唯一检查
    CHECK_UK,
    // 隐藏表单
    HIDDEN;

    public static boolean exists(DescOperable descOperable, DescOperable[] descOperables) {
        if (descOperable != null && descOperables != null && descOperables.length > 0) {
            for (DescOperable operable : descOperables) {
                if (operable.equals(descOperable)) {
                    return true;
                }
            }
        }
        return false;
    }
}
