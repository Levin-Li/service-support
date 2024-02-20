package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 可分页数据
 *
 * @param <T>
 */
public interface PageableData<T> {

    @Schema(title = "总记录数")
    Long getTotals();

    @Schema(title = "总记录数-兼容")
    default Long getTotal() {
        return getTotals();
    }

    @Schema(title = "页面编号")
    Integer getPageIndex();

    @Schema(title = "分页大小")
    Integer getPageSize();

    @Schema(title = "是否还有数据")
    boolean hasMore();

    @Schema(title = "是否有下一条")
    default boolean hasNext() {
        return hasMore();
    }

    @Schema(title = "分页标记，当 has_more 为 true 时，会同时返回新的 page_token，否则不返回 page_token")
    default String getPageToken() {
        return null;
    }

    @Schema(title = "数据项列表")
    List<T> getItems();

    T getFirst();

    boolean isEmpty();

}
