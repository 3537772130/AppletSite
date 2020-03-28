package com.applet.common.mapper;

import com.applet.common.entity.CommentInfo;
import com.applet.common.entity.CommentInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentInfoMapper {
    long countByExample(CommentInfoExample example);

    int deleteByExample(CommentInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentInfo record);

    int insertSelective(CommentInfo record);

    List<CommentInfo> selectByExample(CommentInfoExample example);

    CommentInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentInfo record, @Param("example") CommentInfoExample example);

    int updateByExample(@Param("record") CommentInfo record, @Param("example") CommentInfoExample example);

    int updateByPrimaryKeySelective(CommentInfo record);

    int updateByPrimaryKey(CommentInfo record);
}
