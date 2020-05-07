package com.applet.manage.controller;

import com.applet.common.entity.AppletAdvertRelation;
import com.applet.common.entity.other.CheckResult;
import com.applet.common.entity.SystemNotice;
import com.applet.common.util.*;
import com.applet.common.util.qiniu.QiNiuUtil;
import com.applet.manage.service.PlatformSetService;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     * 上传平台图片
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "uploadImage")
    public Object uploadManagerAvatar(@RequestParam("image") MultipartFile multipartFile) {
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            String fileKey = "/api/public/platform/" + RandomUtil.getTimeStamp();
            QiNiuUtil.uploadFile(multipartFile, fileKey);
            return AjaxResponse.success(fileKey);
        } catch (Exception e) {
            log.error("管理员上传头像出错{}", e);
            return AjaxResponse.error("上传失败");
        }
    }

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

    /**
     * 分页加载小程序广告位关联记录
     * @param relation
     * @param request
     * @return
     */
    @RequestMapping(value = "loadAppletAdvertRelationByPage")
    public Object loadAppletAdvertRelationByPage(AppletAdvertRelation relation, HttpServletRequest request){
        Page page = PageUtil.initPage(request);
        page = platformSetService.selectAppletAdvertRelationByPage(relation, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载小程序广告位关联详情
     * @param id
     * @return
     */
    @RequestMapping(value = "loadAppletAdvertRelationDetails")
    public Object loadAppletAdvertRelationDetails(Integer id){
        if (NullUtil.isNullOrEmpty(id)){
            return AjaxResponse.error("参数错误");
        }
        return AjaxResponse.success(platformSetService.selectAppletAdvertRelationById(id));
    }

    /**
     * 查询广告位已安排到的最后截止日期
     * @param appletTypeId
     * @param pageLogo
     * @return
     */
    @RequestMapping(value = "queryAppletAdvertRelationByLastExpireTime")
    public Object queryAppletAdvertRelationByLastExpireTime(Integer appletTypeId,  String pageLogo){
        Date lastTime = platformSetService.selectAppletAdvertRelationByLastExpireTime(appletTypeId, pageLogo);
        if (NullUtil.isNullOrEmpty(lastTime)){
            return AjaxResponse.error("");
        }
        JDateTime time = new JDateTime(lastTime);
        JDateTime nowTime = new JDateTime(new Date());
        nowTime.setHour(0).setMinute(0).setSecond(0);
        if (time.compareDateTo(nowTime) < 0){
            time = nowTime;
        }
        Map map = new HashMap<>();
        map.put("startDate", time.toString(Constants.DATE_YMD_JDK));
        map.put("expireTime", time.addDay(1).toString(Constants.DATE_YMD_JDK));
        return AjaxResponse.success(map);
    }

    /**
     * 更新小程序广告位关系记录
     * @param relation
     * @return
     */
    @RequestMapping(value = "updateAppletAdvertRelation", method = RequestMethod.POST)
    public Object updateAppletAdvertRelation(AppletAdvertRelation relation){
        try {
            if (null == relation){
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(relation.getAppletTypeId())){
                return AjaxResponse.error("请选择小程序类型");
            }
            if (NullUtil.isNullOrEmpty(relation.getPageLogo())){
                return AjaxResponse.error("请选择页面类型");
            }
            if (NullUtil.isNullOrEmpty(relation.getRelationImage())){
                return AjaxResponse.error("请上传广告图片");
            }
            if (NullUtil.isNotNullOrEmpty(relation.getRelationWebsite()) && relation.getRelationWebsite().getBytes().length > 100){
                return AjaxResponse.error("关联网址输入长度0-100个字符");
            }
            if (relation.getRelationType().intValue() == 2){
                // 外部广告是否默认只能为false
                relation.setIsDefault(false);
                if (NullUtil.isNullOrEmpty(relation.getRelationName())){
                    return AjaxResponse.error("请输入关联对象");
                }
            } else {
                // 平台推广
                relation.setRelationName("平台推广");
            }
            if (NullUtil.isNullOrEmpty(relation.getStartTime())){
                return AjaxResponse.error("请选择开始日期");
            }
            JDateTime nowTime = new JDateTime(new Date());
            nowTime.setHour(0).setMinute(0).setSecond(0);
            JDateTime startTime = new JDateTime(relation.getStartTime());
            if (startTime.convertToDate().getTime() >= nowTime.convertToDate().getTime()){
                return AjaxResponse.error("开始日期段必须大于等于当前日期");
            }
            startTime.setHour(0).setMinute(0).setSecond(0);
            if (relation.getRelationType().intValue() != 1){
                if (platformSetService.checkAppletAdvertRelation(relation.getAppletTypeId(), relation.getPageLogo(), relation.getStartTime())){
                    return AjaxResponse.error("开始日期段已存在安排");
                }
            }
            relation.setStartTime(startTime.convertToDate());
            if (NullUtil.isNullOrEmpty(relation.getExpireTime())){
                return AjaxResponse.error("请选择截止日期");
            }
            JDateTime expireTime = new JDateTime(relation.getExpireTime());
            expireTime.setHour(23).setMinute(59).setSecond(59);
            if (expireTime.convertToDate().getTime() <= startTime.convertToDate().getTime()){
                return AjaxResponse.error("截止日期段必须大于开始日期");
            }
            relation.setExpireTime(expireTime.convertToDate());
            platformSetService.updateAppletAdvertRelation(relation);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("提交小程序广告位安排出错", e);
        }
        return AjaxResponse.error("提交失败");
    }
}
