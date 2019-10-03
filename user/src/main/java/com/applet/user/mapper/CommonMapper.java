package com.applet.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhh on 2017/5/11.
 */
@Mapper
public interface CommonMapper {

    public Map selectSingleLine(String sqlStr);

    public List<Map> selectListMap(String sqlStr);

    public Integer selectCount(String sql);

    public Integer insertBatch(String sql);

    public Integer updateBatch(String sql);

    Object selectSingleValue(String sqlStr);
}
