package com.applet.common.mapper;

import com.applet.common.entity.ViewCommentReplyRecord;
import com.applet.common.entity.ViewCommentReplyRecordExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewCommentReplyRecordMapper {
    long countByExample(ViewCommentReplyRecordExample example);

    List<ViewCommentReplyRecord> selectByExample(ViewCommentReplyRecordExample example);
}
