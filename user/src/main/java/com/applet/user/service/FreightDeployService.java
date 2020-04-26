package com.applet.user.service;

import com.applet.common.util.AjaxResponse;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import com.applet.common.entity.FreightDeploy;
import com.applet.common.entity.FreightDeployExample;
import com.applet.common.mapper.FreightDeployMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: com.applet.user.service
 * @description: 小程序运费配置服务类
 * @author: hugh.liu
 * @create: 2020/1/2
 **/
@Service
public class FreightDeployService {

    @Resource
    private FreightDeployMapper freightDeployMapper;

    /**
     * 根据小程序ID查询运费配置信息
     * @param appletId 小程序ID
     * @param userId
     * @param page 分页
     * @return
     */
    public Page selectFreightDeploysByAppletId(Integer appletId, Integer userId, Page page) {
        FreightDeployExample example = new FreightDeployExample();
        example.setPage(page);
        example.setOrderByClause("minimum asc");
        example.createCriteria().andAppletIdEqualTo(appletId).andUserIdEqualTo(userId).andStatusNotEqualTo(-1);
        long count = freightDeployMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(freightDeployMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 根据ID查询运费配置信息
     * @param id
     * @param userId
     * @return
     */
    public FreightDeploy selectFreightDeployById(Integer id, Integer userId) {
        FreightDeployExample example = new FreightDeployExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
        List<FreightDeploy> list = freightDeployMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 保存运费配置
     * @param freightDeploy 运费配置
     * @return
     */
    public Object updateFreightDeploy(FreightDeploy freightDeploy) {
        freightDeploy.setUpdateTime(new Date());
        if (NullUtil.isNullOrEmpty(freightDeploy.getId())) {
            freightDeploy.setStatus(1);
            freightDeployMapper.insert(freightDeploy);
        } else {
            freightDeployMapper.updateByPrimaryKeySelective(freightDeploy);
        }
        return AjaxResponse.success("提交成功");
    }

    /**
     * 删除运费配置
     * @param freightDeploy 运费配置
     * @return
     */
    public Object deleteFreightDeploy(FreightDeploy freightDeploy) {
        freightDeploy.setStatus(-1);
        int num = freightDeployMapper.updateByPrimaryKeySelective(freightDeploy);
        if (num < 1) {
            return AjaxResponse.error("删除失败");
        }
        return AjaxResponse.success("删除成功");
    }
}
