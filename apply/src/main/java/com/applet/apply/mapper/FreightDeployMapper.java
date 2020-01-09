package com.applet.apply.mapper;

import com.applet.apply.entity.FreightDeploy;
import com.applet.apply.entity.FreightDeployExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreightDeployMapper {
    long countByExample(FreightDeployExample example);

    List<FreightDeploy> selectByExample(FreightDeployExample example);

    FreightDeploy selectByPrimaryKey(Integer id);
}
