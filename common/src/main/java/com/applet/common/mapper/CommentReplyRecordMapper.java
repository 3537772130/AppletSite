package com.applet.common.mapper;

import com.applet.common.entity.CommentReplyRecord;
import com.applet.common.entity.CommentReplyRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentReplyRecordMapper {
    long countByExample(CommentReplyRecordExample example);

    int deleteByExample(CommentReplyRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentReplyRecord record);

    int insertSelective(CommentReplyRecord record);

    List<CommentReplyRecord> selectByExample(CommentReplyRecordExample example);

    CommentReplyRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentReplyRecord record, @Param("example") CommentReplyRecordExample example);

    int updateByExample(@Param("record") CommentReplyRecord record, @Param("example") CommentReplyRecordExample example);

    int updateByPrimaryKeySelective(CommentReplyRecord record);

    int updateByPrimaryKey(CommentReplyRecord record);
}
