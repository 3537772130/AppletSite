package com.applet.user.controller;

import com.applet.common.entity.*;
import com.applet.common.entity.other.CheckResult;
import com.applet.common.entity.other.GoodsSpreadImage;
import com.applet.common.util.*;
import com.applet.common.util.file.ImageMergeUtil;
import com.applet.common.util.qiniu.QiNiuUtil;
import com.applet.user.config.annotation.SessionScope;
import com.applet.user.service.AppletService;
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
    @Autowired
    private AppletService appletService;

    /**
     * 加载小程序物料信息集合
     *
     * @param user
     * @param appletId
     * @return
     */
    @RequestMapping(value = "loadAppletMaterielImageList")
    public Object loadAppletMaterielImageList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer appletId) {
        List<AppletMaterielInfo> materielList = userSpreadService.selectAppletMaterielList(appletId, user.getId());
        Map map = new HashMap();
        map.put("materielList", materielList);
        map.put("typeList", Constants.MATERIEL_TYPE_MAP);
        return AjaxResponse.success(map);
    }

    /**
     * 上传小程序物料图片
     *
     * @param user
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "uploadAppletMaterielImage")
    public Object uploadAppletMaterielImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user,
                                            @RequestParam("materielImage") MultipartFile multipartFile,
                                            Integer appletId, Integer materielType) {
        try {
            if (NullUtil.isNullOrEmpty(appletId) || NullUtil.isNullOrEmpty(Constants.MATERIEL_TYPE_MAP.get(materielType))) {
                return AjaxResponse.error("参数错误");
            }
            AppletInfo appletInfo = appletService.selectAppletInfo(appletId, user.getId());
            if (null == appletInfo) {
                return AjaxResponse.error("信息不符");
            }
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            AppletMaterielInfo info = new AppletMaterielInfo();
            info.setUserId(user.getId());
            info.setAppletId(appletInfo.getId());
            info.setMaterielImage("/api/public/U" + user.getId() + "-AM" + RandomUtil.getTimeStamp());
            info.setMaterielType(materielType);
            QiNiuUtil.uploadFile(multipartFile, info.getMaterielImage());
            userSpreadService.saveAppletMaterielInfo(info);
            return AjaxResponse.success("上传成功");
        } catch (Exception e) {
            log.error("上传小程序物料图片出错{}", e);
        }
        return AjaxResponse.error("上传失败");
    }

    /**
     * 删除小程序物料
     * @param user
     * @param materielId
     * @return
     */
    @RequestMapping(value = "delAppletMaterielImage")
    public Object delAppletMaterielImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer materielId) {
        try {
            AppletMaterielInfo info = userSpreadService.selectAppletMaterielInfo(materielId, user.getId());
            if (null == info) {
                return AjaxResponse.error("信息不符");
            }
            userSpreadService.deleteAppletMaterielInfo(info.getId(), info.getUserId());
            return AjaxResponse.success("删除成功");
        } catch (Exception e) {
            log.error("删除图片信息出错{}", e);
        }
        return AjaxResponse.error("删除失败");
    }

    /**
     * 加载商品推广图片集合
     *
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "loadGoodsSpreadImageList")
    public Object loadGoodsSpreadImageList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId) {
        List<UserGoodsSpreadImage> list = userSpreadService.selectUserGoodsSpreadImageList(goodsId, user.getId());
        return AjaxResponse.success(list);
    }

    /**
     * 删除商品推广图片
     *
     * @param user
     * @param goodsId
     * @param imageId
     * @return
     */
    @RequestMapping(value = "delGoodsSpreadImage")
    public Object delGoodsSpreadImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId, Integer imageId) {
        try {
            UserGoodsSpreadImage image = userSpreadService.selectUserGoodsSpreadImage(imageId, goodsId, user.getId());
            if (null != image) {
                userSpreadService.deletaUserGoodsSpreadImage(imageId, user.getId(), goodsId);
                QiNiuUtil.deleteFile(image.getSpreadImage());
                return AjaxResponse.success("操作成功");
            }
        } catch (Exception e) {
            log.error("是商品推广图片出错{}", e);
        }
        return AjaxResponse.error("操作失败");
    }

    /**
     * 加载制作商品推广图片
     *
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "loadMakeGoodsSpreadImage")
    public Object loadMakeGoodsSpreadImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId) {
        ViewGoodsInfo goodsInfo = userGoodsService.selectViewGoodsInfo(goodsId, user.getId());
        if (null == goodsInfo) {
            return AjaxResponse.error("信息不符");
        }
        List<AppletMaterielInfo> materielList = userSpreadService.selectAppletMaterielList(goodsInfo.getAppletId(), user.getId());
        List<ViewGoodsFile> goodsFiles = userGoodsService.selectFileList(goodsInfo.getId(), user.getId(), 1);
        Map map = new HashMap();
        map.put("materielList", materielList);
        map.put("goodsFileList", goodsFiles);
        return AjaxResponse.success(map);
    }

    /**
     * 制作商品推广图片
     *
     * @param user
     * @param image
     * @return
     */
    @RequestMapping(value = "makeGoodsSpreadImage")
    public Object makeGoodsSpreadImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, GoodsSpreadImage image) {
        try {
            if (NullUtil.isNullOrEmpty(image.getGoodsFileId()) || NullUtil.isNullOrEmpty(image.getMaterielId())) {
                return AjaxResponse.error("参数缺失(1)");
            }
            // 校验信息
            ViewGoodsFile goodsFile = userGoodsService.selectFileInfo(image.getGoodsFileId(), user.getId());
            AppletMaterielInfo materielInfo = userSpreadService.selectAppletMaterielInfo(image.getMaterielId(), user.getId());
            if (null == goodsFile || null == materielInfo) {
                return AjaxResponse.error("信息不符");
            }
            List<Double> positionList = null;
            if (materielInfo.getMaterielType().intValue() == 3 || materielInfo.getMaterielType().intValue() == 4) {
                // 水印图片，设置水印坐标
                if (NullUtil.isNullOrEmpty(image.getStartX()) || NullUtil.isNullOrEmpty(image.getStartY())
                        || NullUtil.isNullOrEmpty(image.getSizeX()) || NullUtil.isNullOrEmpty(image.getSizeY())) {
                    return AjaxResponse.error("参数缺失(2)");
                }
                positionList = image.setGoodsSpreadImage();
                if (NullUtil.isNullOrEmpty(positionList)) {
                    return AjaxResponse.error("图片溢出");
                }

            }
            String fileKey = "/api/public/U" + user.getId() + "-GS" + RandomUtil.getTimeStamp();
            ImageMergeUtil.mergeGoodsImageToAppletMateriel(fileKey, goodsFile.getFileSrc(), materielInfo.getMaterielImage(), materielInfo.getMaterielType(), positionList);

            UserGoodsSpreadImage spreadImage = new UserGoodsSpreadImage();
            spreadImage.setUserId(user.getId());
            spreadImage.setAppletId(materielInfo.getAppletId());
            spreadImage.setGoodsId(goodsFile.getGoodsId());
            spreadImage.setSpreadImage(fileKey);
            userSpreadService.updateUserGoodsSpreadImage(spreadImage);
            return AjaxResponse.success("保存成功");
        } catch (Exception e) {
            log.error("合并商品推广图片出错{}", e);
        }
        return AjaxResponse.error("保存失败");
    }

}
