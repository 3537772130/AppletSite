package com.applet.manage.service;

import com.applet.manage.entity.*;
import com.applet.manage.mapper.*;
import com.applet.manage.util.NullUtil;
import com.applet.manage.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private AppletPageElementContentMapper appletPageElementContentMapper;
    @Autowired
    private ViewAppletPageElementContentMapper viewAppletPageElementContentMapper;
    @Autowired
    private ViewAppletPageElementDefaultMapper viewAppletPageElementDefaultMapper;

    /**
     * 查询页面集合
     *
     * @param fileId
     * @return
     */
    public List<AppletPage> selectAppletPageList(Integer fileId) {
        AppletPageExample example = new AppletPageExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andFileIdEqualTo(fileId);
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
     * 分页查询页面元素
     *
     * @param element
     * @param page
     * @return
     */
    public Page selectAppletPageElementPage(ViewAppletPageElementDefault element, Page page) {
        ViewAppletPageElementDefaultExample example = new ViewAppletPageElementDefaultExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        ViewAppletPageElementDefaultExample.Criteria c = example.createCriteria().andPageIdEqualTo(element.getPageId());
        if (NullUtil.isNotNullOrEmpty(element.getElementLogo())) {
            c.andElementLogoLike(element.getElementLogo() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(element.getElementName())) {
            c.andElementNameLike("%" + element.getElementName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(element.getElementStatus())) {
            c.andElementStatusEqualTo(element.getElementStatus());
        }
        long count = viewAppletPageElementDefaultMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewAppletPageElementDefaultMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询页面元素详情
     *
     * @param id
     * @return
     */
    public ViewAppletPageElementDefault selectAppletPageElementById(Integer id) {
        ViewAppletPageElementDefaultExample example = new ViewAppletPageElementDefaultExample();
        example.createCriteria().andIdEqualTo(id);
        List<ViewAppletPageElementDefault> list = viewAppletPageElementDefaultMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新页面元素及默认内容
     *
     * @param elementDefault
     */
    public void updateAppletPageElementDefault(ViewAppletPageElementDefault elementDefault) {
        AppletPageElement element = new AppletPageElement();
        element.setId(elementDefault.getId());
        element.setPageId(elementDefault.getPageId());
        element.setElementIcon(elementDefault.getElementIcon());
        element.setElementLogo(elementDefault.getElementLogo());
        element.setElementName(elementDefault.getElementName());
        element.setElementStatus(elementDefault.getElementStatus());
        this.updateAppletPageElement(element);

        AppletPage page = this.selectAppletPageById(element.getPageId());
        AppletPageElementContent content = new AppletPageElementContent();
        content.setId(elementDefault.getContentId());
        content.setAppletId(0);
        content.setFileId(page.getFileId());
        content.setPageId(page.getId());
        content.setElementId(element.getId());
        content.setElementJson(elementDefault.getElementJson());
        this.updateAppletPageElementContent(content);
    }

    /**
     * 更新页面元素
     *
     * @param element
     */
    public void updateAppletPageElement(AppletPageElement element) {
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
