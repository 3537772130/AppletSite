package com.applet.manage.controller;

import com.applet.manage.entity.*;
import com.applet.manage.service.AppletPageService;
import com.applet.manage.service.AppletService;
import com.applet.manage.util.AjaxResponse;
import com.applet.manage.util.NullUtil;
import com.applet.manage.util.Page;
import com.applet.manage.util.PageUtil;
import com.applet.manage.util.file.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 管理小程序页面控制类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-25 14:17
 **/
@RestController
@RequestMapping(value = "/api/manage/applet/page/")
public class ManageAppletPageController {
    private static final Logger log = LoggerFactory.getLogger(ManageAppletPageController.class);
    @Autowired
    private AppletService appletService;
    @Autowired
    private AppletPageService appletPageService;

    /**
     * 查询小程序文件页面列表
     *
     * @param fileId
     * @return
     */
    @RequestMapping(value = "queryAppletPageList")
    public Object queryAppletPageList(Integer fileId) {
        AppletFile file = appletService.selectAppletFileById(fileId);
        List<AppletFile> fileList = appletService.selectAppletFileList(file.getId(), file.getTypeId());
        List<AppletPage> pageList = appletPageService.selectAppletPageList(fileId);
        Map map = new HashMap<>();
        map.put("fileList", fileList);
        map.put("pageList", pageList);
        return AjaxResponse.success(map);
    }

    /**
     * 加载页面信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "loadAppletPage")
    public Object loadAppletPage(Integer id) {
        AppletPage page = appletPageService.selectAppletPageById(id);
        return AjaxResponse.success(page);
    }

    /**
     * 更新页面信息
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "updateAppletPage")
    public Object updateAppletPage(AppletPage page) {
        try {
            if (null == page) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(page.getFileId())) {
                return AjaxResponse.error("参数丢失");
            }
            if (NullUtil.isNullOrEmpty(page.getPageLogo())) {
                return AjaxResponse.error("页面标识不能为空");
            }
            if (page.getPageLogo().trim().length() > 30) {
                return AjaxResponse.error("页面标识长度过长");
            } else {
                page.setPageLogo(page.getPageLogo().trim().toUpperCase());
            }
            if (NullUtil.isNullOrEmpty(page.getPageName())) {
                return AjaxResponse.error("页面名称不能为空");
            }
            if (page.getPageName().trim().length() > 30) {
                return AjaxResponse.error("页面名称长度过长");
            } else {
                page.setPageName(page.getPageName().trim());
            }
            appletPageService.updateAppletPage(page);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("更新页面信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 分页查询页面元素列表
     *
     * @param element
     * @param request
     * @return
     */
    @RequestMapping(value = "queryAppletPageElementDefaultPage")
    public Object queryAppletPageElementDefaultPage(ViewAppletPageElementDefault element, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = appletPageService.selectAppletPageElementPage(element, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载页面元素信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "loadAppletPageElementDefault")
    public Object loadAppletPageElementDefault(Integer id) {
        ViewAppletPageElementDefault elementDefault = appletPageService.selectAppletPageElementById(id);
        return AjaxResponse.success(elementDefault);
    }

    /**
     * 更新页面元素信息
     *
     * @param elementDefault
     * @return
     */
    @RequestMapping(value = "updateAppletPageElementDefault")
    public Object updateAppletPageElementDefault(ViewAppletPageElementDefault elementDefault) {
        try {
            if (null == elementDefault) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(elementDefault.getPageId())) {
                return AjaxResponse.error("参数丢失");
            }
            if (NullUtil.isNullOrEmpty(elementDefault.getElementLogo())) {
                return AjaxResponse.error("元素标识不能为空");
            }
            if (elementDefault.getElementLogo().trim().length() > 30) {
                return AjaxResponse.error("元素标识长度过长");
            } else {
                elementDefault.setElementLogo(elementDefault.getElementLogo().trim().toUpperCase());
            }
            if (NullUtil.isNullOrEmpty(elementDefault.getElementName())) {
                return AjaxResponse.error("元素名称不能为空");
            }
            if (elementDefault.getElementName().trim().length() > 30) {
                return AjaxResponse.error("元素名称长度过长");
            } else {
                elementDefault.setElementName(elementDefault.getElementName().trim());
            }
            boolean bool = false;
            if (NullUtil.isNullOrEmpty(elementDefault.getElementIcon())) {
                return AjaxResponse.error("请上传元素图标");
            } else if (NullUtil.isNotNullOrEmpty(elementDefault.getId())) {
                ViewAppletPageElementDefault oldInfo = appletPageService.selectAppletPageElementById(elementDefault.getId());
                elementDefault = this.setPageElementIcon(oldInfo.getElementIcon(), elementDefault);
            } else {
                bool = true;
            }
            appletPageService.updateAppletPageElementDefault(elementDefault);
            if (bool) {
                AppletPageElement element = new AppletPageElement();
                element.setId(elementDefault.getId());
                elementDefault = this.setPageElementIcon(elementDefault.getElementIcon(), elementDefault);
                appletPageService.updateAppletPageElement(element);
            }
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("更新小程序页面元素及默认内容出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    public ViewAppletPageElementDefault setPageElementIcon(String oldElementIcon, ViewAppletPageElementDefault newInfo) {
        // 更新页面元素图标地址
        newInfo.setElementIcon(newInfo.getElementIcon().replace("api\\", ""));
        String newPath = FileUtil.copyPageElementIcon(newInfo.getId(), newInfo.getElementIcon(), oldElementIcon);
        newInfo.setElementIcon(newPath);
        return newInfo;
    }
}
