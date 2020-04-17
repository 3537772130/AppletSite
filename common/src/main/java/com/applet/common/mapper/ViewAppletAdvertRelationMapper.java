package com.applet.common.mapper;

import com.applet.common.entity.ViewAppletAdvertRelation;
import com.applet.common.entity.ViewAppletAdvertRelationExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppletAdvertRelationMapper {
    long countByExample(ViewAppletAdvertRelationExample example);

    List<ViewAppletAdvertRelation> selectByExample(ViewAppletAdvertRelationExample example);
}
