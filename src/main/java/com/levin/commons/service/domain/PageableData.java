package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 可分页数据
 *
 * @param <T>
 */
public interface PageableData<T> {

    @Schema(description = "总记录数")
    Long getTotals();

    @Schema(description = "页面编号")
    Integer getPageIndex();

    @Schema(description = "分页大小")
    Integer getPageSize();

    @Schema(description = "是否有下一页")
    boolean hasMore();

    @Schema(description = "分页标记，当 has_more 为 true 时，会同时返回新的 page_token，否则不返回 page_token")
    default String getPageToken() {
        return null;
    }

    @Schema(description = "数据项列表")
    List<T> getItems();

    T getFirst();

    boolean isEmpty();

}
