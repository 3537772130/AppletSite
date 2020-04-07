package com.applet.manage.controller;

import com.applet.common.entity.SystemNotice;
import com.applet.common.util.*;
import com.applet.manage.config.annotation.CancelAuthentication;
import com.applet.manage.service.PlatformSetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/3/31
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 * Description: 平台设置控制类
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/manage/platformSet/")
public class PlatformSetController {
    @Autowired
    private PlatformSetService platformSetService;

    /**
     * 加载系统通知消息
     * @param title
     * @param type
     * @param status
     * @param request
     * @return
     */
    @RequestMapping(value = "loadSystemNoticeByPage")
    public Object loadSystemNoticeByPage(String title, Integer type, Integer status, HttpServletRequest request){
        Page page = PageUtil.initPage(request);
        page = platformSetService.selectSystemNoticeByPage(title, type, status, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载系统通知消息详情
     * @param id
     * @return
     */
    @RequestMapping(value = "loadSystemNoticeDetails")
    public Object loadSystemNoticeDetails(Integer id){
        if (NullUtil.isNullOrEmpty(id)){
            return AjaxResponse.error("未找到相关记录");
        }
        return AjaxResponse.success(platformSetService.selectSystemNoticeinfo(id));
    }

    /**
     * 更新系统通知消息
     * @param notice
     * @return
     */
    @RequestMapping(value = "updateSystemNotice")
    public Object updateSystemNotice(SystemNotice notice){
        try {
            if (null == notice){
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNotNullOrEmpty(notice.getId())){
                SystemNotice newNotice = new SystemNotice();
                newNotice.setId(notice.getId());
                newNotice.setNoticeStatus(notice.getNoticeStatus());
                platformSetService.updateSystemNotice(newNotice);
            } else {
                if (NullUtil.isNullOrEmpty(notice.getNoticeTitle())){
                    return AjaxResponse.error("通知标题不能为空");
                }
                if (notice.getNoticeTitle().getBytes().length > 50*3){
                    return AjaxResponse.error("通知标题过长，1-50个字符");
                }
                if (NullUtil.isNullOrEmpty(notice.getNoticeContent())){
                    return AjaxResponse.error("通知内容不能为空");
                }
                if (notice.getNoticeContent().getBytes().length > 2000*3){
                    return AjaxResponse.error("通知内容过长，1-2000个字符");
                }
                String type = Constants.SYSTEM_NOTICE_TYPE_MAP.get(notice.getNoticeType());
                if (NullUtil.isNullOrEmpty(type)){
                    return AjaxResponse.error("通知类型错误");
                }
                platformSetService.updateSystemNotice(notice);
            }
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("更新系统通知状态出错{}", e);
        }
        return AjaxResponse.error("提交失败");
    }

}
