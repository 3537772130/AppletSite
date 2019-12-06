package com.applet.manage.controller;

import com.applet.manage.config.annotation.SessionScope;
import com.applet.manage.entity.*;
import com.applet.manage.service.AppletPageService;
import com.applet.manage.service.AppletService;
import com.applet.manage.util.*;
import com.applet.manage.util.file.FileUtil;
import com.applet.manage.util.qiniu.QiNiuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @RequestMapping(value = "loadAppletPageList")
    public Object loadAppletPageList(Integer fileId) {
        if (NullUtil.isNotNullOrEmpty(fileId)){
            AppletFile file = appletService.selectAppletFileById(fileId);
            List<AppletFile> fileList = appletService.selectAppletFileList(file.getId(), file.getTypeId());
            Map map = new HashMap();
            map.put("fileTypeId", file.getTypeId());
            map.put("fileList", fileList);
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("参数错误");
    }

    /**
     * 分页查询页面
     * @param appletPage
     * @param request
     * @return
     */
    @RequestMapping(value = "queryAppletPage")
    public Object queryAppletPage(AppletPage appletPage, HttpServletRequest request){
        Page page = PageUtil.initPage(request);
        page = appletPageService.selectAppletPage(appletPage, page);
        return AjaxResponse.success(page);
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
     * 页面元素类型分页查询
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "queryElementTypePage")
    public Object queryElementTypePage(AppletPageElementType type, HttpServletRequest request){
        Page page = PageUtil.initPage(request);
        page = appletPageService.selectElementTypePage(type, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载页面元素类型
     * @param id
     * @param pageId
     * @return
     */
    @RequestMapping(value = "loadElementType")
    public Object loadElementType(Integer id, Integer pageId){
        AppletPageElementType type = appletPageService.selectElementType(id, pageId);
        if (null == type){
            return AjaxResponse.error("未找到相关记录");
        }
        return AjaxResponse.success(type);
    }

    /**
     * 更新页面元素类型
     * @param type
     * @return
     */
    @RequestMapping(value = "updateElementType")
    public Object updateElementType(AppletPageElementType type){
        try {
            if (null == type){
                return AjaxResponse.error("参数有误");
            }
            if (NullUtil.isNullOrEmpty(type.getPageId())){
                return AjaxResponse.error("参数丢失");
            }
            if (NullUtil.isNullOrEmpty(type.getTypeName())){
                return AjaxResponse.error("类型名称不能为空");
            }
            if (type.getTypeName().trim().length() > 30){
                return AjaxResponse.error("类型名称长度过长");
            }
            if (NullUtil.isNullOrEmpty(type.getId())){
                int count = appletPageService.countElementTypeByPageId(type.getPageId());
                type.setTypeIndex(count + 1);
            }
            appletPageService.updateElementType(type);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("更新页面元素信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 更新页面元素类型排序
     * @param typeId
     * @param pageId
     * @param sort
     * @return
     */
    @RequestMapping(value = "updateElementTypeIndex")
    public Object updateElementTypeIndex(Integer typeId, Integer pageId, String sort) {
        try {
            if (NullUtil.isNullOrEmpty(typeId) || NullUtil.isNullOrEmpty(sort)) {
                return AjaxResponse.error("参数错误");
            }
            int count = appletPageService.countElementTypeByPageId(pageId);
            if (count > 1) {
                AppletPageElementType type = appletPageService.selectElementType(typeId, pageId);
                if (null == type) {
                    return AjaxResponse.error("未找到相关记录");
                }
                Integer num = null;
                if (sort.equals("top") && type.getTypeIndex() - 1 > 0) {
                    num = -1;
                } else if (sort.equals("bot") && type.getTypeIndex() + 1 <= count) {
                    num = 1;
                } else {
                    return AjaxResponse.success("参数错误");
                }
                appletPageService.updateElementTypeIndex(type, num);
            }
            return AjaxResponse.success();
        } catch (Exception e) {
            log.error("更新页面元素类型排序出错{}", e);
            return AjaxResponse.error("操作失败");
        }
    }

    /**
     * 加载页面元素列表信息
     * @param pageId
     * @return
     */
    @RequestMapping(value = "loadAppletPageElementPage")
    public Object loadAppletPageElementPage(Integer pageId){
        List<AppletPageElementType> list = appletPageService.selectElementTypeList(pageId);
        return AjaxResponse.success(list);
    }

    /**
     * 分页查询页面元素列表
     *
     * @param element
     * @param request
     * @return
     */
    @RequestMapping(value = "queryAppletPageElementPage")
    public Object queryAppletPageElementPage(ViewAppletPageElement element, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = appletPageService.selectElementPage(element, page);
        return AjaxResponse.success(page);
    }

    /**
     * 更新页面元素排序
     * @param elementId
     * @param pageId
     * @param sort
     * @return
     */
    @RequestMapping(value = "updateElementIndex")
    public Object updateElementIndex(Integer elementId, Integer pageId, String sort) {
        try {
            if (NullUtil.isNullOrEmpty(elementId) || NullUtil.isNullOrEmpty(sort)) {
                return AjaxResponse.error("参数错误");
            }
            int count = appletPageService.countElementByPageId(pageId);
            if (count > 1) {
                AppletPageElement element = appletPageService.selectElementById(elementId, pageId);
                if (null == element) {
                    return AjaxResponse.error("未找到相关记录");
                }
                Integer num = null;
                if (sort.equals("top") && element.getElementIndex() - 1 > 0) {
                    num = -1;
                } else if (sort.equals("bot") && element.getElementIndex() + 1 <= count) {
                    num = 1;
                } else {
                    return AjaxResponse.success("参数错误");
                }
                appletPageService.updateElementIndex(element, num);
            }
            return AjaxResponse.success();
        } catch (Exception e) {
            log.error("更新页面元素类型排序出错{}", e);
            return AjaxResponse.error("操作失败");
        }
    }

    /**
     * 加载页面元素信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "loadAppletPageElement")
    public Object loadAppletPageElement(Integer id, Integer pageId) {
        Map map = new HashMap();
        map.put("typeList", appletPageService.selectElementTypeList(pageId));
        if (NullUtil.isNotNullOrEmpty(id) && id.intValue() != 0){
            AppletPageElement element = appletPageService.selectElementById(id, pageId);
            if (null != element){
                map.put("info", element);
                return AjaxResponse.success(map);
            }
        }
        return AjaxResponse.msg("-1", map);
    }

    /**
     * 更新页面元素信息
     *
     * @param element
     * @return
     */
    @RequestMapping(value = "updateAppletPageElement")
    public Object updateAppletPageElement(AppletPageElement element) {
        try {
            if (null == element) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(element.getPageId())) {
                return AjaxResponse.error("参数丢失");
            }
            if (NullUtil.isNullOrEmpty(element.getElementLogo())) {
                return AjaxResponse.error("元素标识不能为空");
            }
            if (element.getElementLogo().trim().length() > 30) {
                return AjaxResponse.error("元素标识长度过长");
            } else {
                element.setElementLogo(element.getElementLogo().trim());
            }
            if (NullUtil.isNullOrEmpty(element.getElementName())) {
                return AjaxResponse.error("元素名称不能为空");
            }
            if (element.getElementName().trim().getBytes().length > 36) {
                return AjaxResponse.error("元素名称长度过长");
            } else {
                element.setElementName(element.getElementName().trim());
            }
            if (NullUtil.isNullOrEmpty(element.getTypeId())){
                return AjaxResponse.error("元素类型不能为空");
            }
            if (NullUtil.isNullOrEmpty(element.getId())){
                int count = appletPageService.countElementByPageId(element.getPageId());
                element.setElementIndex(count + 1);
            }
            appletPageService.updateElement(element);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("更新小程序页面元素及默认内容出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 加载页面默认信息
     * @param fileId
     * @return
     */
    @RequestMapping(value = "loadPageDefault")
    public Object loadPageDefault(Integer fileId){
        List<AppletPage> list = appletPageService.selectAppletPageList(fileId);
        if (NullUtil.isNotNullOrEmpty(list)){
            return AjaxResponse.success(list);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 加载页面元素
     * @param pageId
     * @return
     */
    @RequestMapping(value = "loadPageElement")
    public Object loadPageElement(Integer pageId){
        List<AppletPageElementType> list1 = appletPageService.selectElementTypeList(pageId);
        List<AppletPageElement> list2 = appletPageService.selectElementList(pageId);
        List<Map> mapList = new ArrayList<>();
        for (AppletPageElementType type: list1) {
            Map map1 = new HashMap();
            map1.put("id", type.getId());
            map1.put("name", type.getTypeName());
            List<Map> list3 = new ArrayList<>();
            for (AppletPageElement element:list2) {
                if (element.getTypeId() == type.getId()){
                    Map map2 = new HashMap();
                    map2.put("id", element.getId());
                    map2.put("logo", element.getElementLogo());
                    map2.put("name", element.getElementName());
                    list3.add(map2);
                }
            }
            map1.put("list", list3);
            mapList.add(map1);
        }
        AppletPageContent content = appletPageService.selectAppletPageContent(pageId);
        Map map = new HashMap();
        map.put("typeList" , mapList);
        if (null != content && NullUtil.isNotNullOrEmpty(content.getContentJson())){
            map.put("contentJson", content.getContentJson());
            return AjaxResponse.success(map);
        } else {
            return AjaxResponse.msg("-1", map);
        }
    }


    /**
     * 保存页面配置
     * @param pageId
     * @param json
     * @return
     */
    @RequestMapping(value = "savePageContent")
    public Object savePageContent(Integer pageId, String json){
        try {
            AppletPageContent content = appletPageService.selectAppletPageContent(pageId);
            if (null == content){
                AppletPage page = appletPageService.selectAppletPageById(pageId);
                if (null == page){
                    return AjaxResponse.error("未找到相关记录");
                }
                content = new AppletPageContent();
                content.setAppletId(0);
                content.setPageId(page.getId());
                content.setFileId(page.getFileId());
            }
            content.setContentJson(json);
            appletPageService.updateAppletPageContent(content);
            return AjaxResponse.success("保存配置成功");
        } catch (Exception e) {
            log.error("保存页面配置出错{}", e);
            return AjaxResponse.error("保存配置失败");
        }
    }

    /**
     * 上传小程序页面图片
     *
     * @param manager
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "uploadAppletPageImage")
    public Object uploadAppletPageImage(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo manager,
                                        @RequestParam("image") MultipartFile multipartFile,
                                        Integer pIndex, Integer index, String icon) {
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            String fileKey = "/api/public/PAGE-IMG-" + RandomUtil.getTimeStamp();
            QiNiuUtil.uploadFile(multipartFile, fileKey);
            if (NullUtil.isNotNullOrEmpty(icon)){
                QiNiuUtil.deleteFile(icon);
            }
            Map map = new HashMap();
            map.put("pIndex", pIndex);
            map.put("index", index);
            map.put("icon", fileKey);
            return AjaxResponse.success(map);
        } catch (Exception e) {
            log.error("上传小程序页面图片出错{}", e);
            return AjaxResponse.error("上传失败");
        }
    }

    /**
     * 删除小程序页面图片
     * @param manager
     * @param icon
     */
    @RequestMapping(value = "deleteAppletPageImage")
    public void deleteAppletPageImage(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo manager, String icon){
        if (NullUtil.isNotNullOrEmpty(icon)){
            QiNiuUtil.deleteFile(icon);
        }
    }

    /**
     * 查询测试商品列表
     * @param manager
     * @return
     */
    @RequestMapping(value = "queryGoodsInfoList")
    public Object queryGoodsInfoList(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo manager, String name){
        Map map = new HashMap();
        map.put("goodsList", appletPageService.selectGoodsInfoList(1, name));
        map.put("typeList", appletPageService.selectGoodsTypeList(1, null));
        return AjaxResponse.success(map);
    }

    /**
     * 查询测试商品详情列表
     * @param manager
     * @return
     */
    @RequestMapping(value = "queryGoodsDetailsList")
    public Object queryGoodsDetailsList(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo manager, String name){
        Map map = new HashMap();
        map.put("goodsList", appletPageService.selectGoodsDetailsList(1, name));
        map.put("typeList", appletPageService.selectGoodsTypeList(1, null));
        return AjaxResponse.success(map);
    }

    /**
     * 查询测试商品折扣列表
     * @param manager
     * @return
     */
    @RequestMapping(value = "queryGoodsDiscountList")
    public Object queryGoodsDiscountList(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo manager, String name){
        Map map = new HashMap();
        map.put("goodsList", appletPageService.selectGoodsDiscountList(1, name));
        return AjaxResponse.success(map);
    }

    /**
     * 查询测试商品类型列表
     * @param manager
     * @return
     */
    @RequestMapping(value = "queryGoodsTypeList")
    public Object queryGoodsTypeList(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo manager, String name){
        Map map = new HashMap();
        map.put("typeList", appletPageService.selectGoodsTypeList(1, name));
        return AjaxResponse.success(map);
    }
}
