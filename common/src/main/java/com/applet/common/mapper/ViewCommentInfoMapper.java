package com.applet.common.mapper;

import com.applet.common.entity.ViewCommentInfo;
import com.applet.common.entity.ViewCommentInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewCommentInfoMapper {
    long countByExample(ViewCommentInfoExample example);

    List<ViewCommentInfo> selectByExample(ViewCommentInfoExample example);
}
