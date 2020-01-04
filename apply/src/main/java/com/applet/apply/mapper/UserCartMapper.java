package com.applet.apply.mapper;

import com.applet.apply.entity.UserCart;
import com.applet.apply.entity.UserCartExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserCartMapper {
    long countByExample(UserCartExample example);

    int deleteByExample(UserCartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCart record);

    int insertSelective(UserCart record);

    List<UserCart> selectByExample(UserCartExample example);

    UserCart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCart record, @Param("example") UserCartExample example);

    int updateByExample(@Param("record") UserCart record, @Param("example") UserCartExample example);

    int updateByPrimaryKeySelective(UserCart record);

    int updateByPrimaryKey(UserCart record);
}
