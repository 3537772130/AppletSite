package com.applet.manage.service;

import com.applet.manage.entity.*;
import com.applet.manage.mapper.*;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import com.applet.common.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    private ViewAppletTypeMapper viewAppletTypeMapper;
    @Autowired
    private AppletInfoMapper appletInfoMapper;
    @Autowired
    private ViewAppletInfoMapper viewAppletInfoMapper;
    @Autowired
    private AppletAuditMapper appletAuditMapper;
    @Autowired
    private ViewAppletAuditMapper viewAppletAuditMapper;
    @Autowired
    private ViewAppletAuditListMapper viewAppletAuditListMapper;
    @Autowired
    private AppletFileMapper appletFileMapper;
    @Autowired
    private ViewAppletFileMapper viewAppletFileMapper;
    @Autowired
    private AppletVersionMapper appletVersionMapper;
    @Autowired
    private ViewAppletVersionMapper viewAppletVersionMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

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
     * 分页查询小程序服务类型列表
     *
     * @param type
     * @param page
     * @return
     */
    public Page selectAppletTypePage(AppletType type, Page page) {
        ViewAppletTypeExample example = new ViewAppletTypeExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        ViewAppletTypeExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(type.getTypeName())) {
            c.andTypeNameLike("%" + type.getTypeName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(type.getTypeStatus())) {
            c.andTypeStatusEqualTo(type.getTypeStatus());
        }
        long count = viewAppletTypeMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewAppletTypeMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询小程序服务类型信息
     *
     * @param id
     * @return
     */
    public AppletType selectAppletTypeById(Integer id) {
        return appletTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新小程序服务类型信息
     *
     * @param record
     */
    public void updateAppletType(AppletType record) {
        record.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(record.getId())) {
            appletTypeMapper.updateByPrimaryKeySelective(record);
        } else {
            appletTypeMapper.insertSelective(record);
        }
    }


    /**
     * 查询小程序信息
     *
     * @param id
     * @return
     */
    public ViewAppletInfo selectAppletInfo(Integer id) {
        return selectAppletInfo(id, null);
    }

    /**
     * 查询小程序信息
     *
     * @param id
     * @param userId
     * @return
     */
    public ViewAppletInfo selectAppletInfo(Integer id, Integer userId) {
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
    public int updateAppletInfo(AppletInfo info) {
        return appletInfoMapper.updateByPrimaryKeySelective(info);
    }

    /**
     * 查询小程序审核记录
     *
     * @param appletId
     * @return
     */
    public List<ViewAppletAudit> selectAppletAuditList(Integer appletId) {
        ViewAppletAuditExample example = new ViewAppletAuditExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andAppletIdEqualTo(appletId);
        return viewAppletAuditMapper.selectByExample(example);
    }

    /**
     * 查询小程序最新审核记录
     *
     * @param appletId
     * @return
     */
    public ViewAppletAudit selectAppletNewAudit(Integer appletId) {
        Page page = new Page(0, 1);
        ViewAppletAuditExample example = new ViewAppletAuditExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        example.createCriteria().andAppletIdEqualTo(appletId);
        List<ViewAppletAudit> list = viewAppletAuditMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
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
     * 查询小程序审核信息
     *
     * @param id
     * @return
     */
    public ViewAppletAuditList selectAppletAuditDetails(Integer id) {
        ViewAppletAuditListExample example = new ViewAppletAuditListExample();
        example.createCriteria().andIdEqualTo(id);
        List<ViewAppletAuditList> list = viewAppletAuditListMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 添加小程序审核信息
     *
     * @param appletAudit
     */
    @Transactional(rollbackFor = Exception.class)
    public void addAppletAudit(AppletAudit appletAudit) {
        appletAudit.setAuditTime(new Date());
        appletAuditMapper.insertSelective(appletAudit);

        if (appletAudit.getResult().intValue() != 1) {
            AppletInfo applet = new AppletInfo();
            applet.setId(appletAudit.getAppletId());
            applet.setStatus(appletAudit.getResult().intValue() == 2 ? 1 : -1);
            appletInfoMapper.updateByPrimaryKeySelective(applet);
        }

        if (appletAudit.getResult().intValue() == 2) {
            AppletInfo info = appletInfoMapper.selectByPrimaryKey(appletAudit.getAppletId());
            // 更新用户身份
            UserInfo user = new UserInfo();
            user.setId(info.getUserId());
            user.setIsDealer(true);
            userInfoMapper.updateByPrimaryKeySelective(user);
            // 初始化小程序版本信息
            AppletVersion version = new AppletVersion();
            version.setAppletId(info.getId());
            version.setTypeId(info.getTypeId());
            this.updateAppletVersion(version);
        }
    }

    /**
     * 分页查询小程序版本文件列表
     *
     * @param file
     * @param page
     * @return
     */
    public Page selectAppletFilePage(AppletFile file, Page page) {
        ViewAppletFileExample example = new ViewAppletFileExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        ViewAppletFileExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(file.getVersionNumber())) {
            c.andVersionNumberLike("%" + file.getVersionNumber() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(file.getTypeId())) {
            c.andTypeIdEqualTo(file.getTypeId());
        }
        if (NullUtil.isNotNullOrEmpty(file.getFileStatus())) {
            c.andFileStatusEqualTo(file.getFileStatus());
        }
        long count = viewAppletFileMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewAppletFileMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询小程序版本文件信息
     *
     * @param id
     * @param typeId
     * @return
     */
    public AppletFile selectAppletFile(Integer id, Integer typeId) {
        AppletFileExample example = new AppletFileExample();
        example.createCriteria().andIdEqualTo(id).andTypeIdEqualTo(typeId);
        List<AppletFile> list = appletFileMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 根据类型编号查询小程序版本文件信息集合
     *
     * @param typeId
     * @return
     */
    public List<AppletFile> selectAppletFile(Integer typeId) {
        AppletFileExample example = new AppletFileExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andTypeIdEqualTo(typeId).andFileStatusEqualTo(true);
        return appletFileMapper.selectByExample(example);
    }

    /**
     * 查询服务类型其他可继承版本集合
     *
     * @param id
     * @param typeId
     * @return
     */
    public List<AppletFile> selectAppletFileList(Integer id, Integer typeId) {
        AppletFileExample example = new AppletFileExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andIdNotEqualTo(id).andTypeIdEqualTo(typeId).andFileStatusEqualTo(true);
        return appletFileMapper.selectByExample(example);
    }

    /**
     * 查询小程序版本文件信息
     *
     * @param id
     * @return
     */
    public AppletFile selectAppletFileById(Integer id) {
        return appletFileMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新小程序版本文件信息
     *
     * @param file
     */
    public void updateAppletFile(AppletFile file) {
        file.setUpdateTime(new Date());
        if (NullUtil.isNullOrEmpty(file.getFilePath())) {
            file.setFileStatus(false);
        }
        if (NullUtil.isNotNullOrEmpty(file.getId())) {
            appletFileMapper.updateByPrimaryKeySelective(file);
        } else {
            appletFileMapper.insertSelective(file);
        }
    }


    /**
     * 分页查询小程序版本信息列表
     *
     * @param version
     * @param page
     * @return
     */
    public Page selectAppletVersionPage(ViewAppletVersion version, Page page) {
        ViewAppletVersionExample example = new ViewAppletVersionExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        ViewAppletVersionExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(version.getAppletCode())) {
            c.andAppletCodeLike(version.getAppletCode() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(version.getAppletName())) {
            c.andAppletNameLike("%" + version.getAppletName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(version.getTypeId())) {
            c.andTypeIdEqualTo(version.getTypeId());
        }
        if (NullUtil.isNotNullOrEmpty(version.getVersionNumber())) {
            c.andVersionNumberEqualTo(version.getVersionNumber());
        }
        long count = viewAppletVersionMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewAppletVersionMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询小程序版本信息
     *
     * @param id
     * @return
     */
    public ViewAppletVersion selectAppletVersion(Integer id) {
        ViewAppletVersionExample example = new ViewAppletVersionExample();
        example.createCriteria().andIdEqualTo(id);
        List<ViewAppletVersion> list = viewAppletVersionMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新小程序版本信息
     *
     * @param record
     */
    public void updateAppletVersion(AppletVersion record) {
        record.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(record.getId())) {
            appletVersionMapper.updateByPrimaryKeySelective(record);
        } else {
            appletVersionMapper.insertSelective(record);
        }
    }
}
