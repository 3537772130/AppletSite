package com.applet.manage.service;

import com.applet.manage.entity.*;
import com.applet.manage.mapper.*;
import com.applet.manage.util.NullUtil;
import com.applet.manage.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: SpringBootDemo
 * @description: 小程序页面信息服务类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-25 11:07
 **/
@Service
public class AppletPageService {
    @Autowired
    private AppletPageMapper appletPageMapper;
    @Autowired
    private AppletPageElementMapper appletPageElementMapper;
    @Autowired
    private AppletPageElementTypeMapper appletPageElementTypeMapper;
    @Autowired
    private AppletPageElementContentMapper appletPageElementContentMapper;
    @Autowired
    private ViewAppletPageElementContentMapper viewAppletPageElementContentMapper;
    @Autowired
    private ViewAppletPageElementDefaultMapper viewAppletPageElementDefaultMapper;

    /**
     * 分页查询页面
     * @param appletPage
     * @param page
     * @return
     */
    public Page selectAppletPage(AppletPage appletPage, Page page){
        AppletPageExample example = new AppletPageExample();
        example.setOrderByClause("id desc");
        AppletPageExample.Criteria c = example.createCriteria().andFileIdEqualTo(appletPage.getFileId());
        if (NullUtil.isNotNullOrEmpty(appletPage.getPageName())){
            c.andPageNameLike("%" + appletPage.getPageName() + "%");
        }
        long count = appletPageMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(appletPageMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询页面集合
     *
     * @param fileId
     * @return
     */
    public List<AppletPage> selectAppletPageList(Integer fileId) {
        AppletPageExample example = new AppletPageExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andFileIdEqualTo(fileId).andPageStatusEqualTo(true);
        return appletPageMapper.selectByExample(example);
    }

    /**
     * 现场页面信息详情
     *
     * @param id
     * @return
     */
    public AppletPage selectAppletPageById(Integer id) {
        return appletPageMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新页面信息
     *
     * @param page
     */
    public void updateAppletPage(AppletPage page) {
        page.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(page.getId())) {
            appletPageMapper.updateByPrimaryKeySelective(page);
        } else {
            appletPageMapper.insertSelective(page);
        }
    }

    /**
     * 分页查询页面元素类型
     * @param type
     * @param page
     * @return
     */
    public Page selectElementTypePage(AppletPageElementType type, Page page) {
        AppletPageElementTypeExample example = new AppletPageElementTypeExample();
        example.setPage(page);
        example.setOrderByClause("type_index asc");
        AppletPageElementTypeExample.Criteria c = example.createCriteria();
        c.andPageIdEqualTo(type.getPageId());
        if (NullUtil.isNotNullOrEmpty(type.getTypeName())) {
            c.andTypeNameLike("%" + type.getTypeName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(type.getTypeStatus())) {
            c.andTypeStatusEqualTo(type.getTypeStatus());
        }
        long count = appletPageElementTypeMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(appletPageElementTypeMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询页面元素类型总数
     * @param pageId
     * @return
     */
    public int countElementTypeByPageId(Integer pageId) {
        AppletPageElementTypeExample example = new AppletPageElementTypeExample();
        example.createCriteria().andPageIdEqualTo(pageId);
        return (int)appletPageElementTypeMapper.countByExample(example);
    }

    /**
     * 查询页面元素类型集合
     * @param pageId
     * @return
     */
    public List<AppletPageElementType> selectElementTypeList(Integer pageId){
        AppletPageElementTypeExample example = new AppletPageElementTypeExample();
        example.createCriteria().andPageIdEqualTo(pageId).andTypeStatusEqualTo(true);
        return appletPageElementTypeMapper.selectByExample(example);
    }

    /**
     * 查询页面元素类型信息
     * @param id
     * @param pageId
     * @return
     */
    public AppletPageElementType selectElementType(Integer id, Integer pageId) {
        AppletPageElementTypeExample example = new AppletPageElementTypeExample();
        example.createCriteria().andIdEqualTo(id).andPageIdEqualTo(pageId);
        List<AppletPageElementType> list = appletPageElementTypeMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新页面元素类型
     * @param type
     */
    public void updateElementType(AppletPageElementType type){
        if (NullUtil.isNotNullOrEmpty(type.getId())){
            appletPageElementTypeMapper.updateByPrimaryKeySelective(type);
        } else {
            appletPageElementTypeMapper.insertSelective(type);
        }
    }

    /**
     * 页面元素类型排序
     * @param type
     * @param num
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateElementTypeIndex(AppletPageElementType type, Integer num) {
        AppletPageElementType record1 = new AppletPageElementType();
        record1.setTypeIndex(type.getTypeIndex());
        AppletPageElementTypeExample example = new AppletPageElementTypeExample();
        example.createCriteria().andPageIdEqualTo(type.getPageId()).andTypeIndexEqualTo(type.getTypeIndex() + num);
        appletPageElementTypeMapper.updateByExampleSelective(record1, example);

        AppletPageElementType record2 = new AppletPageElementType();
        record2.setId(type.getId());
        record2.setTypeIndex(type.getTypeIndex() + num);
        appletPageElementTypeMapper.updateByPrimaryKeySelective(record2);
    }


    /**
     * 分页查询页面元素
     *
     * @param element
     * @param page
     * @return
     */
    public Page selectElementPage(AppletPageElement element, Page page) {
        AppletPageElementExample example = new AppletPageElementExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        AppletPageElementExample.Criteria c = example.createCriteria().andPageIdEqualTo(element.getPageId());
        if (NullUtil.isNotNullOrEmpty(element.getElementLogo())) {
            c.andElementLogoLike(element.getElementLogo() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(element.getElementName())) {
            c.andElementNameLike("%" + element.getElementName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(element.getTypeId())){
            c.andTypeIdEqualTo(element.getTypeId());
        }
        if (NullUtil.isNotNullOrEmpty(element.getElementStatus())) {
            c.andElementStatusEqualTo(element.getElementStatus());
        }
        long count = appletPageElementMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(appletPageElementMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询页面元素集合
     * @param pageId
     * @return
     */
    public List<AppletPageElement> selectElementList(Integer pageId){
        AppletPageElementExample example = new AppletPageElementExample();
        example.createCriteria().andPageIdEqualTo(pageId).andElementStatusEqualTo(true);
        return appletPageElementMapper.selectByExample(example);
    }

    /**
     * 查询页面元素详情
     *
     * @param id
     * @return
     */
    public AppletPageElement selectElementById(Integer id, Integer pageId) {
        AppletPageElementExample example = new AppletPageElementExample();
        example.createCriteria().andIdEqualTo(id).andPageIdEqualTo(pageId);
        List<AppletPageElement> list = appletPageElementMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新页面元素
     *
     * @param element
     */
    public void updateElement(AppletPageElement element) {
        element.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(element.getId())) {
            appletPageElementMapper.updateByPrimaryKeySelective(element);
        } else {
            appletPageElementMapper.insertSelective(element);
        }
    }

    /**
     * 更新页面元素内容
     *
     * @param content
     */
    public void updateAppletPageElementContent(AppletPageElementContent content) {
        content.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(content.getId())) {
            appletPageElementContentMapper.updateByPrimaryKeySelective(content);
        } else {
            appletPageElementContentMapper.insertSelective(content);
        }
    }

}
