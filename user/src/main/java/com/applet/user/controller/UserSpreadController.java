package com.applet.user.controller;

import com.applet.common.entity.*;
import com.applet.common.entity.other.CheckResult;
import com.applet.common.entity.other.GoodsSpreadImage;
import com.applet.common.util.*;
import com.applet.common.util.file.ImageMergeUtil;
import com.applet.common.util.qiniu.QiNiuUtil;
import com.applet.user.config.annotation.SessionScope;
import com.applet.user.service.UserGoodsService;
import com.applet.user.service.UserSpreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/5/7
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/user/spread/")
public class UserSpreadController {
    @Autowired
    private UserSpreadService userSpreadService;
    @Autowired
    private UserGoodsService userGoodsService;

    /**
     * 加载小程序物料信息集合
     * @param user
     * @param appletId
     * @return
     */
    @RequestMapping(value = "loadAppletMaterielImageList")
    public Object loadAppletMaterielImageList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer appletId) {
        List<AppletMaterielInfo> materielInfoList = userSpreadService.selectAppletMaterielList(appletId, user.getId());
        return AjaxResponse.success(materielInfoList);
    }

    /**
     * 上传小程序物料图片
     * @param user
     * @param multipartFile
     * @param materielId
     * @return
     */
    @RequestMapping(value = "uploadAppletMaterielImage")
    public Object uploadAppletMaterielImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user,
                                            @RequestParam("appletLogo") MultipartFile multipartFile,
                                            Integer materielId) {
        try {
            if (NullUtil.isNullOrEmpty(materielId)) {
                return AjaxResponse.error("参数错误");
            }
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            AppletMaterielInfo info = userSpreadService.selectAppletMaterielInfo(materielId, user.getId());
            if (NullUtil.isNullOrEmpty(info.getMaterielImage())) {
                info.setMaterielImage("/api/public/U" + user.getId() + "-AM" + RandomUtil.getTimeStamp());
            }
            String fileKey = info.getMaterielImage();
            QiNiuUtil.uploadFile(multipartFile, fileKey);
            return AjaxResponse.success(fileKey);
        } catch (Exception e) {
            log.error("上传小程序物料图片出错{}", e);
        }
        return AjaxResponse.error("上传失败");
    }

    /**
     * 加载商品推广图片集合
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "loadGoodsSpreadImageList")
    public Object loadGoodsSpreadImageList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId){
        List<UserGoodsSpreadImage> list = userSpreadService.selectUserGoodsSpreadImageList(goodsId, user.getId());
        return AjaxResponse.success(list);
    }

    /**
     * 删除商品推广图片
     * @param user
     * @param goodsId
     * @param imageId
     * @return
     */
    @RequestMapping(value = "delGoodsSpreadImage")
    public Object delGoodsSpreadImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId, Integer imageId){
        try {
            userSpreadService.deletaUserGoodsSpreadImage(imageId, user.getId(), goodsId);
            return AjaxResponse.success("操作成功");
        } catch (Exception e) {
            log.error("是商品推广图片出错{}", e);
        }
        return AjaxResponse.error("操作失败");
    }

    /**
     * 加载制作商品推广图片
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "loadMakeGoodsSpreadImage")
    public Object loadMakeGoodsSpreadImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId){
        ViewGoodsInfo goodsInfo = userGoodsService.selectViewGoodsInfo(goodsId, user.getId());
        if (null == goodsInfo){
            return AjaxResponse.error("信息不符");
        }
        List<AppletMaterielInfo> materielList = userSpreadService.selectAppletMaterielList(goodsInfo.getAppletId(), user.getId());
        List<ViewGoodsFile> goodsFiles = userGoodsService.selectFileList(goodsInfo.getId(), user.getId());
        Map map = new HashMap();
        map.put("materielList", materielList);
        map.put("goodsFiles", goodsFiles);
        return AjaxResponse.success(map);
    }

    /**
     * 制作商品推广图片
     * @param user
     * @param image
     * @return
     */
    @RequestMapping(value = "makeGoodsSpreadImage")
    public Object makeGoodsSpreadImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, GoodsSpreadImage image) {
        try {
            if (NullUtil.isNullOrEmpty(image.getGoodsFileId()) || NullUtil.isNullOrEmpty(image.getMaterielId())){
                return AjaxResponse.error("参数缺失(1)");
            }
            ViewGoodsFile goodsFile = userGoodsService.selectFileInfo(image.getGoodsFileId(), user.getId());
            AppletMaterielInfo materielInfo = userSpreadService.selectAppletMaterielInfo(image.getMaterielId(), user.getId());
            if (null == goodsFile || null == materielInfo){
                return AjaxResponse.error("信息不符");
            }
            String oldImageSrc = null;
            UserGoodsSpreadImage spreadImage = null;
            if (NullUtil.isNotNullOrEmpty(image.getSpreadImageId())){
                spreadImage = userSpreadService.selectUserGoodsSpreadImage(image.getSpreadImageId(), goodsFile.getGoodsId(), user.getId());
                if (null == spreadImage){
                    return AjaxResponse.error("参数错误");
                }
                oldImageSrc = spreadImage.getSpreadImage();
            }
            List<Double> positionList = null;
            if (materielInfo.getMaterielType().intValue() == 3 || materielInfo.getMaterielType().intValue() == 4){
                // 水印图片，设置水印坐标
                if (NullUtil.isNullOrEmpty(image.getStartX()) || NullUtil.isNullOrEmpty(image.getStartY())
                        || NullUtil.isNullOrEmpty(image.getSizeX()) || NullUtil.isNullOrEmpty(image.getSizeY())){
                    return AjaxResponse.error("参数缺失(2)");
                }
                positionList = image.setGoodsSpreadImage();
                if (NullUtil.isNullOrEmpty(positionList)){
                    return AjaxResponse.error("图片溢出");
                }

            }
            String fileKey = "/api/public/U" + user.getId() + "-GS" + RandomUtil.getTimeStamp();
            ImageMergeUtil.mergeGoodsImageToAppletMateriel(fileKey, goodsFile.getFileSrc(), materielInfo.getMaterielImage(), materielInfo.getMaterielType(), positionList);
            if (null == spreadImage){
                spreadImage = new UserGoodsSpreadImage();
                spreadImage.setUserId(user.getId());
                spreadImage.setAppletId(materielInfo.getAppletId());
                spreadImage.setGoodsId(goodsFile.getGoodsId());
            }
            spreadImage.setSpreadImage(fileKey);
            userSpreadService.updateUserGoodsSpreadImage(spreadImage);
            if (NullUtil.isNotNullOrEmpty(oldImageSrc)){
                QiNiuUtil.deleteFile(oldImageSrc);
            }
            return AjaxResponse.success(spreadImage);
        } catch (Exception e) {
            log.error("合并商品推广图片出错{}", e);
        }
        return AjaxResponse.error("制作失败");
    }
}
