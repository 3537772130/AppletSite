package com.applet.common.mapper;

import com.applet.common.entity.ViewUserComment;
import com.applet.common.entity.ViewUserCommentExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewUserCommentMapper {
    long countByExample(ViewUserCommentExample example);

    List<ViewUserComment> selectByExample(ViewUserCommentExample example);
}
