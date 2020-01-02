package com.applet.common.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页 - bo
 *
 * @author 谭良忠
 * @date 2019/12/31 13:51
 */
@Data
@ApiModel("分页请求实体")
public class PageBo<T> implements Serializable {

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("页码大小")
    private Integer size = 20;

    @ApiModelProperty("查询参数")
    private T param;

    @ApiModelProperty(value = "多个参数排序", example = "{'createDate':'desc'}")
    private Map<String, String> sortableField = new HashMap<>();

    public long getOffset() {
        return (page - 1) * size;
    }
}