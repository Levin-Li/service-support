package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@NoArgsConstructor
//@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)
@Schema(description = "服务响应对象")
@FieldNameConstants
public class PageableDataImpl<T> implements PageableData<T> {

    Long totals;
    Integer pageIndex;
    Integer pageSize;
    List<T> items;
    String pageToken;
    boolean hasMore;

    @Override
    public boolean hasMore() {
        return hasMore;
    }
}
