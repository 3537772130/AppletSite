package com.applet.common.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 谭良忠
 * @date 2020/1/2 10:31
 */
public interface BaseMapper<T> {

    /**
     * 取得检索分页信息
     *
     * @param params
     * @param offset
     * @param limit
     * @return 实体列表
     */
    List<T> selectSearchData(@Param("params") Map<String,Object> params, @Param("offset") Long offset, @Param("limit") Integer limit);

    /**
     * 取得检索信息条数
     *
     * @param params
     * @return
     */
    Long selectSearchCount(@Param("params") Map<String,Object> params);

}
