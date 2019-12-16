package com.applet.user.service;

import com.applet.user.entity.*;
import com.applet.user.mapper.*;
import com.applet.user.util.NullUtil;
import com.applet.user.util.Page;
import com.applet.user.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 小程序信息服务类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-19 09:38
 **/
@Service
public class AppletService {
    @Autowired
    private AppletTypeMapper appletTypeMapper;
    @Autowired
    private AppletInfoMapper appletInfoMapper;
    @Autowired
    private ViewAppletInfoMapper viewAppletInfoMapper;
    @Autowired
    private AppletAuditMapper appletAuditMapper;
    @Autowired
    private ViewAppletAuditListMapper viewAppletAuditListMapper;
    @Autowired
    private CommonMapper commonMapper;

    /**
     * 查询小程序服务类型集合
     *
     * @return
     */
    public List<AppletType> selectAppletTypeList(Boolean status) {
        AppletTypeExample example = new AppletTypeExample();
        if (NullUtil.isNotNullOrEmpty(status)) {
            example.createCriteria().andTypeStatusEqualTo(status);
        }
        return appletTypeMapper.selectByExample(example);
    }


    /**
     * 查询小程序信息
     *
     * @param id
     * @return
     */
    public AppletInfo selectAppletInfoById(Integer id) {
        return appletInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询小程序信息
     *
     * @param id
     * @param userId
     * @return
     */
    public AppletInfo selectAppletInfo(Integer id, Integer userId) {
        AppletInfoExample example = new AppletInfoExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
        List<AppletInfo> list = appletInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询小程序信息
     *
     * @param id
     * @param userId
     * @return
     */
    public ViewAppletInfo selectViewAppletInfo(Integer id, Integer userId) {
        ViewAppletInfoExample example = new ViewAppletInfoExample();
        ViewAppletInfoExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(id);
        if (NullUtil.isNotNullOrEmpty(userId)) {
            c.andUserIdEqualTo(userId);
        }
        List<ViewAppletInfo> list = viewAppletInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 管理员分页查询小程序列表
     *
     * @param info
     * @param page
     * @return
     */
    public Page selectAppletInfoToPage(ViewAppletInfo info, Page page) {
        ViewAppletInfoExample example = new ViewAppletInfoExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        ViewAppletInfoExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(info.getAppletCode())) {
            c.andAppletCodeLike("%" + info.getAppletCode() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(info.getAppletName())) {
            c.andAppletNameLike("%" + info.getAppletName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(info.getTypeId())) {
            c.andTypeIdEqualTo(info.getTypeId());
        }
        if (NullUtil.isNotNullOrEmpty(info.getMobile())) {
            c.andMobileLike(info.getMobile() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(info.getProvince())) {
            c.andProvinceEqualTo(info.getProvince());
            if (NullUtil.isNotNullOrEmpty(info.getCity())) {
                c.andCityEqualTo(info.getCity());
                if (NullUtil.isNotNullOrEmpty(info.getCounty())) {
                    c.andCountyEqualTo(info.getCounty());
                }
            }
        }
        if (NullUtil.isNotNullOrEmpty(info.getIfRetail())) {
            c.andIfRetailEqualTo(info.getIfRetail());
        }
        if (NullUtil.isNotNullOrEmpty(info.getIfSelling())) {
            c.andIfSellingEqualTo(info.getIfSelling());
        }
        if (NullUtil.isNotNullOrEmpty(info.getStatus())) {
            c.andStatusEqualTo(info.getStatus());
        } else {
            c.andStatusNotEqualTo(0);
        }
        long count = viewAppletInfoMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewAppletInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 更新小程序信息
     *
     * @param info
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveAppletInfo(AppletInfo info) {
        info.setUpdateTime(new Date());
        if (NullUtil.isNullOrEmpty(info.getId())) {
            info.setStatus(0);
            info.setAppletCode("AC" + RandomUtil.getTimeStamp());
            info.setIfSelling(false);
            appletInfoMapper.insertSelective(info);
        } else {

            this.updateAppletInfo(info);
        }

        // 添加审核记录
        AppletAudit audit = new AppletAudit();
        audit.setAppletId(info.getId());
        audit.setAppletCode(info.getAppletCode());
        audit.setResult(0);
        audit.setRemark("提交信息");
        audit.setAuditTime(new Date());
        appletAuditMapper.insertSelective(audit);
    }

    /**
     * 更新小程序信息
     *
     * @param info
     */
    public int updateAppletInfo(AppletInfo info) {
        return appletInfoMapper.updateByPrimaryKeySelective(info);
    }

    /**
     * 查询小程序审核列表
     *
     * @param record
     * @param page
     * @return
     */
    public Page selectAppletAuditToPage(ViewAppletAuditList record, Page page) {
        ViewAppletAuditListExample example = new ViewAppletAuditListExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        ViewAppletAuditListExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(record.getUserId())) {
            c.andUserIdEqualTo(record.getUserId());
        }
        if (NullUtil.isNotNullOrEmpty(record.getAppletCode())) {
            c.andAppletCodeLike("%" + record.getAppletCode() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(record.getAppletName())) {
            c.andAppletNameLike("%" + record.getAppletName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(record.getMobile())) {
            c.andMobileLike(record.getMobile() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(record.getProvince())) {
            c.andProvinceEqualTo(record.getProvince());
            if (NullUtil.isNotNullOrEmpty(record.getCity())) {
                c.andCityEqualTo(record.getCity());
                if (NullUtil.isNotNullOrEmpty(record.getCounty())) {
                    c.andCountyEqualTo(record.getCounty());
                }
            }
        }
        if (NullUtil.isNotNullOrEmpty(record.getIfRetail())) {
            c.andIfRetailEqualTo(record.getIfRetail());
        }
        if (NullUtil.isNotNullOrEmpty(record.getIfSelling())) {
            c.andIfSellingEqualTo(record.getIfSelling());
        }
        if (NullUtil.isNotNullOrEmpty(record.getStatus())) {
            c.andStatusEqualTo(record.getStatus());
        }
        if (NullUtil.isNotNullOrEmpty(record.getAuditorUserName())) {
            c.andAuditorUserNameLike(record.getAuditorUserName());
        }
        if (NullUtil.isNotNullOrEmpty(record.getAuditResult())) {
            c.andAuditResultEqualTo(record.getAuditResult());
        }
        if (NullUtil.isNotNullOrEmpty(record.getStatus())) {
            c.andStatusEqualTo(record.getStatus());
        }
        long count = viewAppletAuditListMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewAppletAuditListMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 更新小程序营业状态
     * @param id
     * @param userId
     * @param ifSelling
     * @throws Exception
     */
    public void updateAppletSelling(Integer id, Integer userId, boolean ifSelling) throws Exception {
        String sql = "UPDATE applet_info SET if_selling = " + (ifSelling ? 0 : 1) + " WHERE id = " + id + " AND user_id = " + userId + " AND `status` = 1;";
        int num = commonMapper.updateBatch(sql);
        if (num < 1) {
            throw new Exception();
        }
    }
}
