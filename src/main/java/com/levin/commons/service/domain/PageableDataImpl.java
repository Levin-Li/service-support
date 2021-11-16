package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.beans.Transient;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)
@Schema(description = "分页对象")
@FieldNameConstants
public class PageableDataImpl<T> implements PageableData<T> {

    Long totals;
    Integer pageIndex;
    Integer pageSize;
    List<T> items;
    String pageToken;

//    @Setter
//    boolean hasMore;

    public boolean isHasMore() {
        return (pageSize != null && items != null && items.size() >= pageSize);
    }

    @Override
    public boolean hasMore() {
        return isHasMore();
    }

    @Override
    @Transient
    public T getFirst() {
        return isEmpty() ? null : items.get(0);
    }

    @Override
    public boolean isEmpty() {
        return items == null || items.size() == 0;
    }

}
