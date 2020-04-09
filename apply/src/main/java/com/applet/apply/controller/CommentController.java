package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.service.CommentService;
import com.applet.apply.service.UserService;
import com.applet.common.entity.*;
import com.applet.common.util.*;
import com.applet.common.util.qiniu.QiNiuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/3/17
 * Time: 18:08
 * To change this template use File | Settings | File Templates.
 * Description: 评论信息控制层
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/applet/comment/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    /**
     * 上传评论图片
     * @param multipartFile
     * @param oldPath
     * @return
     */
    @RequestMapping(value = "uploadCommentImage")
    public Object uploadCommentImage(@RequestParam("commentImage") MultipartFile multipartFile, String oldPath) {
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            if (NullUtil.isNullOrEmpty(oldPath)) {
                oldPath ="/api/image/COMMENT-" + RandomUtil.getTimeStamp();
            }
            QiNiuUtil.uploadFile(multipartFile, oldPath);
            return AjaxResponse.success(oldPath);
        } catch (Exception e) {
            log.error("小程序上传用户头像出错{}", e);
            return AjaxResponse.error("上传失败");
        }
    }

    /**
     * 发表评论
     * @param weChantInfo
     * @param info
     * @return
     */
    @RequestMapping(value = "publishCommentInfo")
    public Object publishCommentInfo(@SessionScope("appletInfo") ViewAppletInfo appletInfo, @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, CommentInfo info){
        try {
            if (NullUtil.isNullOrEmpty(info.getCommentContent())){
                return AjaxResponse.error("请输入评论内容");
            }
            if (info.getCommentContent().getBytes().length > 200 * 3){
                return AjaxResponse.error("评论内容过长");
            }
            if (commentService.selectOrderGoodsCommentListByIf(weChantInfo.getUserId(), info.getOrderId(), info.getGoodsId())){
                info.setCommentUserId(weChantInfo.getUserId());
                commentService.updateCommentInfo(info, appletInfo.getUserId());
                return AjaxResponse.success("发表评论成功");
            }
            return AjaxResponse.error("已发表过评论啦");
        } catch (Exception e) {
            log.error("发表评论出错{}", e);
        }
        return AjaxResponse.error("发表失败");
    }

    /**
     * 分页查询商品评论列表
     * @param id
     * @param request
     * @return
     */
    @CancelAuth
    @RequestMapping(value = "queryCommentListByPage")
    public Object queryCommentListByPage(Integer id, HttpServletRequest request){
        Page page = PageUtil.initPage(request);
        page = commentService.selectCommentListByPage(id, page);
        return AjaxResponse.success(page);
    }

    /**
     * 删除评论信息
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteCommentInfo")
    public Object deleteCommentInfo (@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id){
        try {
            if (NullUtil.isNullOrEmpty(id)){
                return AjaxResponse.error("参数缺失");
            }
            CommentInfo info = commentService.selectCommentInfo(id);
            if (weChantInfo.getUserId().intValue() == info.getCommentUserId().intValue()){
                info = new CommentInfo();
                info.setId(id);
                info.setCommentUserId(weChantInfo.getUserId());
                commentService.updateCommentInfo(info, null);
                return AjaxResponse.success("删除成功");
            }
            return AjaxResponse.error("没有权限");
        } catch (Exception e) {
            log.error("删除评论信息出错{}", e);
        }
        return AjaxResponse.error("删除失败");
    }

    /**
     * 查询评论回复记录（用户未登陆）
     * @param commentId
     * @return
     */
    @CancelAuth
    @RequestMapping(value = "queryReplyRecord")
    public Object queryReplyRecord(Integer commentId) {
        if (NullUtil.isNotNullOrEmpty(commentId)){
            ViewCommentInfo info = commentService.selectViewCommentInfo(commentId);
            List<ViewCommentReplyRecord> list = commentService.selectCommentReplyRecordByList(commentId);
            Map map = new HashMap();
            map.put("info", info);
            map.put("list", list);
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 查询评论回复记录(用户已登录)
     * @param weChantInfo
     * @param commentId
     * @return
     */
    @RequestMapping(value = "queryReplyRecordByUser")
    public Object queryReplyRecordByUser(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer commentId) {
        if (NullUtil.isNotNullOrEmpty(commentId)){
            ViewCommentInfo info = commentService.selectViewCommentInfo(commentId);
            List<ViewCommentReplyRecord> list = commentService.selectCommentReplyRecordByList(commentId);
            try {
                if (null != weChantInfo && NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
                    // 更新提醒状态
                    userService.updateUserRemindRecord(commentId, weChantInfo.getUserId(), Constants.RELATION_TYPE_COMMENT, -1);
                }
            } catch (Exception e) {
                log.error("更新用户消息提醒记录状态出错{}", e);
            }
            Map map = new HashMap();
            map.put("info", info);
            map.put("list", list);
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 回复评论信息
     * @param weChantInfo
     * @param commentId
     * @param aimUserId
     * @param replyContent
     * @return
     */
    @RequestMapping(value = "replyCommentInfo")
    public Object replyCommentInfo(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer commentId, Integer aimUserId, String replyContent){
        try {
            if (NullUtil.isNullOrEmpty(commentId)){
                return AjaxResponse.error("参数缺失");
            }
            if (NullUtil.isNullOrEmpty(replyContent)){
                return AjaxResponse.error("回复内容不能为空");
            }
            if (replyContent.getBytes().length > 200 * 3){
                return AjaxResponse.error("回复内容过长");
            }
            CommentReplyRecord record = new CommentReplyRecord();
            record.setCommentId(commentId);
            record.setAimUserId(aimUserId);
            record.setReplyContent(replyContent);
            if (commentService.checkCommentReplyQualifications(record.getCommentId(), record.getAimUserId())){
                record.setReplyUserId(weChantInfo.getUserId());
                record = commentService.updateCommentReplyRecord(record);
                return AjaxResponse.success(commentService.selectCommentReplyRecordById(record.getId()));
            }
            return AjaxResponse.error("回复异常");
        } catch (Exception e) {
            log.error("回复评论出错{}", e);
        }
        return AjaxResponse.error("回复失败");
    }

    /**
     * 删除评论回复记录
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteCommentReplyRecord")
    public Object deleteCommentReplyRecord(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id){
        try {
            if (NullUtil.isNullOrEmpty(id)){
                return AjaxResponse.error("参数缺失");
            }
            CommentReplyRecord record = new CommentReplyRecord();
            record.setId(id);
            record.setAimUserId(weChantInfo.getUserId());
            commentService.updateCommentReplyRecord(record);
            return AjaxResponse.success("删除成功");
        } catch (Exception e) {
            log.error("删除评论信息出错{}", e);
        }
        return AjaxResponse.error("删除失败");
    }


//    public Object loadCommentListByPage(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo){
//
//    }
}
