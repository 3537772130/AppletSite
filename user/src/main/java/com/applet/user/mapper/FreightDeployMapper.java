package com.applet.user.mapper;

import com.applet.user.entity.FreightDeploy;
import com.applet.user.entity.FreightDeployExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FreightDeployMapper {
    long countByExample(FreightDeployExample example);

    int deleteByExample(FreightDeployExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FreightDeploy record);

    int insertSelective(FreightDeploy record);

    List<FreightDeploy> selectByExample(FreightDeployExample example);

    FreightDeploy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FreightDeploy record, @Param("example") FreightDeployExample example);

    int updateByExample(@Param("record") FreightDeploy record, @Param("example") FreightDeployExample example);

    int updateByPrimaryKeySelective(FreightDeploy record);

    int updateByPrimaryKey(FreightDeploy record);
}