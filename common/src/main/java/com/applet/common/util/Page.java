package com.applet.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data
@ApiModel("分页对象")
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Page<T> implements Serializable {

    @ApiModelProperty("总数")
    private long totalCount;

    @ApiModelProperty("当前页码")
    private long page;

    @ApiModelProperty("页面大小")
    private long pageSize;

    @ApiModelProperty("数据源")
    private Collection<T> dataSource;

    @ApiModelProperty("开始行")
    private long rowStart;

    @ApiModelProperty("总页数")
    private long totalPage;

    public Page(long page, long size, long totalCount) {
        this.page = page;
        this.pageSize = size;
        this.totalCount = totalCount;
    }

    public Page(long page, long pageSize) {
        if (pageSize <= 0) {
            this.pageSize = 10;
        } else {
            this.pageSize = pageSize;
        }
        if (page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
        this.rowStart = (this.page - 1) * this.pageSize;
    }

    public long getTotalPage() {
        if (totalCount % pageSize == 0) {
            this.totalPage = totalCount / pageSize;
        } else {
            this.totalPage = totalCount / pageSize + 1;
        }
        return totalPage;
    }

}
