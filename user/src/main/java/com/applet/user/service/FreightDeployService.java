package com.applet.user.service;

import com.applet.common.excepion.BusinessException;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import com.applet.user.entity.AppletPageContent;
import com.applet.user.entity.FreightDeploy;
import com.applet.user.entity.FreightDeployExample;
import com.applet.user.mapper.CommonMapper;
import com.applet.user.mapper.FreightDeployMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private CommonMapper commonMapper;

    /**
     * 根据小程序ID查询运费配置信息
     * @param appletId 小程序ID
     * @param page 分页
     * @return
     */
    public Page selectFreightDeploysByAppletId(Integer appletId, Page page) {
        FreightDeployExample example = new FreightDeployExample();
        example.setPage(page);
        example.setOrderByClause("minimum");
        example.createCriteria().andAppletIdEqualTo(appletId).andStatusEqualTo(true);
        long count = freightDeployMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(freightDeployMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 根据ID查询运费配置信息
     * @param freightDeploy 运费配置
     * @return
     */
    public FreightDeploy selectFreightDeployById(FreightDeploy freightDeploy) {
        FreightDeployExample example = new FreightDeployExample();
        example.createCriteria().andIdEqualTo(freightDeploy.getId()).andStatusEqualTo(true);
        List<FreightDeploy> list = freightDeployMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 保存运费配置
     * @param freightDeploy 运费配置
     * @return
     */
    public Object saveFreightDeploy(FreightDeploy freightDeploy) {
        // 校验参数不能为空
        if (null == freightDeploy) {
            return AjaxResponse.error("参数错误");
        }
        if (NullUtil.isNullOrEmpty(freightDeploy.getAppletId())) {
            return AjaxResponse.error("缺少小程序ID");
        }
        if (NullUtil.isNullOrEmpty(freightDeploy.getMinimum())) {
            return AjaxResponse.error("缺少最小数");
        }
        if (NullUtil.isNullOrEmpty(freightDeploy.getMaximum())) {
            return AjaxResponse.error("缺少最大数");
        }
        if (NullUtil.isNullOrEmpty(freightDeploy.getFreight())) {
            return AjaxResponse.error("缺少运费配置");
        }
        if (freightDeploy.getMinimum() > freightDeploy.getMaximum()) {
            return AjaxResponse.error("最大数不能小于最小数");
        }
        if (freightDeploy.getMinimum() < 0 || freightDeploy.getMaximum() < 0) {
            return AjaxResponse.error("最小数、最大数不能小于0");
        }
        // 判断最小数、最大数是否已存在配置
        FreightDeployExample example = new FreightDeployExample();
        example.createCriteria()
                .andIdNotEqualTo(freightDeploy.getId())
                .andAppletIdEqualTo(freightDeploy.getAppletId())
                .andMinimumEqualTo(freightDeploy.getMinimum())
                .andMaximumEqualTo(freightDeploy.getMaximum())
                .andStatusEqualTo(true);
        long count = freightDeployMapper.countByExample(example);
        if (count > 0) {
            return AjaxResponse.error("最小数、最大数已配置运费");
        }
        // 判断最小数、最大数跟已配置的信息是否冲突（最小数、最大数不能存在已配置的最小数、最大数之间）
        example = new FreightDeployExample();
        example.createCriteria()
                .andIdNotEqualTo(freightDeploy.getId())
                .andAppletIdEqualTo(freightDeploy.getAppletId())
                .andMinimumBetween(freightDeploy.getMinimum(), freightDeploy.getMaximum())
                .andStatusEqualTo(true);
        example.or()
                .andIdNotEqualTo(freightDeploy.getId())
                .andAppletIdEqualTo(freightDeploy.getAppletId())
                .andMaximumBetween(freightDeploy.getMinimum(), freightDeploy.getMaximum())
                .andStatusEqualTo(true);
        count = freightDeployMapper.countByExample(example);
        if (count > 0) {
            return AjaxResponse.error("最小数、最大数和已配置的运费规则冲突");
        }
        // 根据运费配置ID是否为空决定新增或者修改
        if (NullUtil.isNullOrEmpty(freightDeploy.getId()) || freightDeploy.getId() == 0) {
            freightDeploy.setStatus(true);
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
        freightDeploy.setStatus(false);
        int num = freightDeployMapper.updateByPrimaryKeySelective(freightDeploy);
        if (num < 1) {
            return AjaxResponse.error("删除失败");
        }
        return AjaxResponse.success("删除成功");
    }

    /**
     * 计算运费
     * @param appletId 小程序ID
     * @param distance 距离（米）
     * @return
     */
    public Number countFreight(Integer appletId, Integer distance) throws Exception {
        if (NullUtil.isNullOrEmpty(appletId)) {
            throw new Exception("缺少小程序ID");
        }
        if (NullUtil.isNullOrEmpty(distance)) {
            throw new Exception("缺少配送距离");
        }
        FreightDeployExample example = new FreightDeployExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andMinimumLessThanOrEqualTo(distance).andMaximumGreaterThanOrEqualTo(distance);
        List<FreightDeploy> freightDeploys = freightDeployMapper.selectByExample(example);
        if (NullUtil.isNullOrEmpty(freightDeploys)) {
            throw new Exception("不在配送范围内");
        }
        return freightDeploys.get(0).getFreight();
    }

}
