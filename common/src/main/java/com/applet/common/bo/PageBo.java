package com.applet.common.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "起始页为空")
    @Min(value = 1, message = "起始页最小1")
    @ApiModelProperty(value = "当前页数", required = true, example = "1")
    private Integer page;

    @NotNull(message = "页大小为空")
    @Min(value = 1, message = "页大小最小1")
    @Max(value = 100, message = "页大小最大100")
    @ApiModelProperty(value = "每页显示数", required = true, example = "10")
    private Integer size;

    @ApiModelProperty("查询参数")
    private T param;

    @ApiModelProperty(value = "多个参数排序", example = "{'createDate':'desc'}")
    private Map<String, String> sortableField = new HashMap<>();

    @ApiModelProperty(value = "offset",hidden = true)
    private long offset;

    public long getOffset() {
        return (page - 1) * size;
    }
}