package com.applet.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应实体
 *
 * @author 谭良忠
 * @date 2019/12/31 13:51
 */
@Data
@ApiModel("分页响应实体")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageVo<T> implements Serializable {

    @ApiModelProperty("当前页数")
    private Integer page;

    @ApiModelProperty("每页条数")
    private Integer size;

    @ApiModelProperty("总数")
    private Long total;

    @ApiModelProperty("总页数")
    private Integer totalPages;

    @ApiModelProperty("数据 {@link List}")
    private List<T> datas;


}
