package com.applet.user.controller;

import com.applet.common.entity.CheckResult;
import com.applet.user.config.annotation.SessionScope;
import com.applet.user.entity.*;
import com.applet.user.service.GoodsService;
import com.applet.common.util.*;
import com.applet.common.util.file.FileUtil;
import com.applet.common.util.qiniu.QiNiuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 用户商品控制类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-16 17:48
 **/
@RestController
@RequestMapping(value = "/api/user/goods/")
@Component
public class UserGoodsController {
    private static final Logger log = LoggerFactory.getLogger(UserGoodsController.class);
    @Autowired
    private GoodsService goodsService;

    /**
     * 上传商品图片文件
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "uploadGoodsImage")
    public Object uploadGoodsImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user,
                                   @RequestParam("image") MultipartFile multipartFile) {
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            String fileKey = "/api/public/U" + user.getId() + "-GT" + RandomUtil.getTimeStamp();
            QiNiuUtil.uploadFile(multipartFile, fileKey);
            return AjaxResponse.success(fileKey);
        } catch (Exception e) {
            log.error("上传头像出错{}", e);
            return AjaxResponse.error("上传失败");
        }
    }

    /**
     * 转移存储空间文件
     *
     * @param oldKey
     * @param newKey
     * @throws Exception
     */
    @Async
    public void removeFile(String oldKey, String newKey) throws Exception {
        QiNiuUtil.removeFile(oldKey, newKey);
    }

    /**
     * 加载商品类型列表
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "loadTypePage")
    public Object loadTypePage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user) {
        int count = goodsService.selectGoodsTypeCount(user.getId());
        if (count >= Constants.GOODS_TYPE_COUNT) {
            return AjaxResponse.error("类型创建数量已达上限");
        }
        return AjaxResponse.success();
    }

    /**
     * 分页查询用户商品分类列表
     *
     * @param user
     * @param name
     * @param request
     * @return
     */
    @RequestMapping(value = "queryTypePage")
    public Object queryTypePage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer appletId, String name, Integer status, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        if (NullUtil.isNotNullOrEmpty(appletId)){
            page = goodsService.selectTypePage(appletId, user.getId(), name, status, page);
        }
        return AjaxResponse.success(page);
    }

    /**
     * 加载商品类型信息
     *
     * @param user
     * @param id
     * @return
     */
    @RequestMapping(value = "loadGoodsType")
    public Object loadGoodsType(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer id) {
        GoodsType goodsType = goodsService.selectGoodsType(id, user.getId());
        if (null == goodsType) {
            return AjaxResponse.error("未找到相关信息");
        }
        goodsType.setTypeLogo(goodsType.getTypeLogo());
        return AjaxResponse.success(goodsType);
    }

    /**
     * 更新商品类型信息
     *
     * @param user
     * @param goodsType
     * @return
     */
    @RequestMapping(value = "updateGoodsType")
    public Object updateGoodsType(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, GoodsType goodsType) {
        try {
            if (null == goodsType) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(goodsType.getAppletId())){
                return AjaxResponse.error("参数丢失");
            }
            if (NullUtil.isNullOrEmpty(goodsType.getTypeName())) {
                return AjaxResponse.error("类型名称不能为空");
            }
            if (goodsType.getTypeName().trim().length() > 30) {
                return AjaxResponse.error("类型名称过长");
            } else {
                goodsType.setTypeName(goodsType.getTypeName().trim());
            }
            if (NullUtil.isNullOrEmpty(goodsType.getId())) {
                int count = goodsService.selectGoodsTypeCount(user.getId());
                if (count >= Constants.GOODS_TYPE_COUNT) {
                    return AjaxResponse.error("类型创建数量已达上限");
                }
                goodsType.setTypeIndex(count + 1);
            }
            goodsType.setUserId(user.getId());
            String typeLogo = "/api/image/GTLOGO-" + RandomUtil.getTimeStamp();
            if (NullUtil.isNotNullOrEmpty(goodsType.getId())) {
                GoodsType record = goodsService.selectGoodsType(goodsType.getId(), user.getId());
                if (null == record) {
                    return AjaxResponse.error("信息不符");
                }
                if (NullUtil.isNotNullOrEmpty(record.getTypeLogo())) {
                    typeLogo = goodsType.getTypeLogo().equals(record.getTypeLogo()) ? null : record.getTypeLogo();
                }
            }
            if (NullUtil.isNotNullOrEmpty(typeLogo)) {
                this.removeFile(goodsType.getTypeLogo(), typeLogo);
                goodsType.setTypeLogo(typeLogo);
            }
            goodsService.updateGoodsType(goodsType);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("更新商品类型信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 更新商品类型排序
     *
     * @param user
     * @param typeId
     * @param sort
     * @return
     */
    @RequestMapping(value = "updateGoodsTypeIndex")
    public Object updateGoodsTypeIndex(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer typeId, String sort) {
        try {
            if (NullUtil.isNullOrEmpty(typeId) || NullUtil.isNullOrEmpty(sort)) {
                return AjaxResponse.error("参数错误");
            }
            int count = goodsService.selectGoodsTypeCount(user.getId());
            if (count > 1) {
                GoodsType type = goodsService.selectGoodsType(typeId, user.getId());
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
                goodsService.updateGoodsTypeIndex(type, num);
            }
            return AjaxResponse.success();
        } catch (Exception e) {
            log.error("更新商品类型排序出错{}", e);
            return AjaxResponse.error("操作失败");
        }
    }


    /**
     * 查询商品类型集合
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "queryTypeList")
    public Object queryTypeList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer appletId) {
        List<GoodsType> list = goodsService.selectTypeList(appletId, user.getId());
        return AjaxResponse.success(list);
    }

    /**
     * 分页查询用户商品列表
     *
     * @param user
     * @param goods
     * @param request
     * @return
     */
    @RequestMapping(value = "queryInfoPage")
    public Object queryInfoToPage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, ViewGoodsInfo goods, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        goods.setUserId(user.getId());
        page = goodsService.selectInfoPage(goods, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载商品信息
     *
     * @param user
     * @param id
     * @return
     */
    @RequestMapping(value = "loadGoodsInfo")
    public Object loadGoodsInfo(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer id) {
        Map map = new HashMap();
        List<GoodsType> list = goodsService.selectTypeList(user.getId());
        map.put("typeList", list);
        if (NullUtil.isNotNullOrEmpty(id) && id.intValue() != 0) {
            GoodsInfo goods = goodsService.selectGoodsInfo(id, user.getId());
            goods.setCoverSrc(goods.getCoverSrc());
            map.put("goods", goods);
            return AjaxResponse.success(map);
        }
        return AjaxResponse.msg("-1", map);
    }

    /**
     * 更新商品信息
     *
     * @param user
     * @param goods
     * @return
     */
    @RequestMapping(value = "updateGoodsInfo")
    public Object updateGoodsInfo(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, GoodsInfo goods) {
        try {
            if (null == goods) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(goods.getCoverSrc())) {
                return AjaxResponse.error("请上传商品封面图片");
            }
            if (NullUtil.isNullOrEmpty(goods.getGoodsName())) {
                return AjaxResponse.error("商品名称不能为空");
            }
            if (goods.getGoodsName().trim().length() > 150) {
                return AjaxResponse.error("商品名称输入过长");
            } else {
                goods.setGoodsName(goods.getGoodsName().trim());
            }
            if (NullUtil.isNullOrEmpty(goods.getTypeId())) {
                return AjaxResponse.error("请选择商品类型");
            }
            GoodsType type = goodsService.selectGoodsType(goods.getTypeId(), user.getId());
            if (null == type) {
                return AjaxResponse.error("商品类型选择错误");
            }
            if (NullUtil.isNullOrEmpty(goods.getDiscount())){
                return AjaxResponse.error("折扣不能为空");
            }
            if (goods.getDiscount().intValue() < 1 || goods.getDiscount().intValue() > 100){
                return AjaxResponse.error("折扣只能为1-100");
            }
            if (NullUtil.isNotNullOrEmpty(goods.getDescribeStr()) && goods.getDescribeStr().getBytes().length > 300){
                return AjaxResponse.error("描述输入过长");
            }
            String coverSrc = "/api/image/GC-" + RandomUtil.getTimeStamp();
            if (NullUtil.isNotNullOrEmpty(goods.getId())) {
                GoodsInfo record = goodsService.selectGoodsInfo(goods.getId(), user.getId());
                if (null == record){
                    return AjaxResponse.error("信息不符");
                }
                if (record.getStatus()){
                    return AjaxResponse.error("未下架商品禁止修改信息");
                }
                if (NullUtil.isNotNullOrEmpty(goods.getCoverSrc())) {
                    coverSrc = goods.getCoverSrc().equals(record.getCoverSrc()) ? null : record.getCoverSrc();
                }
            }
            if (NullUtil.isNotNullOrEmpty(coverSrc)) {
                QiNiuUtil.removeFile(goods.getCoverSrc(), coverSrc);
                goods.setCoverSrc(coverSrc);
            }

            goods.setUserId(user.getId());
            goods.setMinPrice(null);
            goods.setMaxPrice(null);
            try {
                goodsService.updateGoodsInfo(goods);
            } catch (SQLIntegrityConstraintViolationException e) {
                log.error("更新商品信息出错{}", e);
                return AjaxResponse.error("提交失败（商品名称已存在！）");
            }
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("更新商品信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    public GoodsInfo setGoodsCoverSrc(String oldCoverSrc, GoodsInfo newInfo) {
        // 更新封面图地址
        newInfo.setCoverSrc(newInfo.getCoverSrc().replace("api/", ""));
        String newPath = FileUtil.copyGoodsCoverSrc(newInfo.getId(), newInfo.getCoverSrc(), oldCoverSrc);
        newInfo.setCoverSrc(newPath);
        return newInfo;
    }

    /**
     * 更新商品排序
     *
     * @param user
     * @param goodsId
     * @param sort
     * @return
     */
    @RequestMapping(value = "updateGoodsIndex")
    public Object updateGoodsIndex(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId,
                                   Integer typeId, String sort) {
        try {
            if (NullUtil.isNullOrEmpty(goodsId) || NullUtil.isNullOrEmpty(typeId) || NullUtil.isNullOrEmpty(sort)) {
                return AjaxResponse.error("参数错误");
            }
            int count = goodsService.selectGoodsCount(typeId, user.getId());
            if (count > 1) {
                GoodsInfo goods = goodsService.selectGoodsInfo(goodsId, typeId, user.getId());
                if (null == goods) {
                    return AjaxResponse.error("未找到相关记录");
                }
                Integer num = null;
                if (sort.equals("top") && goods.getGoodsIndex().intValue() - 1 > 0) {
                    num = -1;
                } else if (sort.equals("bot") && goods.getGoodsIndex().intValue() + 1 <= count) {
                    num = 1;
                } else {
                    return AjaxResponse.success("参数错误");
                }
                goodsService.updateGoodsIndex(goods, num);
            }
            return AjaxResponse.success();
        } catch (Exception e) {
            log.error("更新商品排序出错{}", e);
            return AjaxResponse.error("操作失败");
        }
    }

    /**
     * 更新商品状态
     *
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "updateGoodsStatus")
    public Object updateGoodsStatus(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId) {
        if (NullUtil.isNullOrEmpty(goodsId)) {
            return AjaxResponse.error("参数错误");
        }
        ViewGoodsInfo goods = goodsService.selectViewGoodsInfo(goodsId, user.getId());
        try {
            if (goods.getGoodsStatus().intValue() == 0) {
                GoodsType type = goodsService.selectGoodsType(goods.getTypeId(), user.getId());
                if (!type.getTypeStatus()) {
                    return AjaxResponse.error("发布失败：该商品的类型已禁用");
                }
                int fileCount = goodsService.selectFileCount(goodsId, user.getId());
                if (fileCount <= 0) {
                    return AjaxResponse.error("发布失败：请提交文件(至少一张图片或短视频)");
                }
                int specsCount = goodsService.selectSpecsCount(goodsId, user.getId());
                if (specsCount <= 0) {
                    return AjaxResponse.error("发布失败：请设置至少一条正常规格)");
                }
            }
            goodsService.updateGoodsStatus(goods);
            return AjaxResponse.success(goods.getGoodsStatus().intValue() == 1 ? "下架成功" : "发布成功");
        } catch (Exception e) {
            log.error("更新商品状态出错{}", e);
            return AjaxResponse.error(goods.getGoodsStatus().intValue() == 1 ? "下架失败" : "发布失败");
        }
    }

    /**
     * 查询商品文件集合
     *
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "queryFileList")
    public Object queryFileList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId) {
        List<ViewGoodsFile> list = goodsService.selectFileList(goodsId, user.getId());
        if (NullUtil.isNullOrEmpty(list)) {
            return AjaxResponse.error("未找到相关记录");
        }
        return AjaxResponse.success(list);
    }

    /**
     * 上传商品图片文件
     *
     * @param user
     * @param goodsId
     * @param fileId
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "uploadGoodsFileImg")
    public Object uploadGoodsFileImg(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId, Integer fileId,
                                     @RequestParam("goodsFile") MultipartFile multipartFile) {
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            if (NullUtil.isNullOrEmpty(goodsId) || NullUtil.isNullOrEmpty(fileId)) {
                return AjaxResponse.error("参数错误");
            }
            GoodsInfo goodsInfo = goodsService.selectGoodsInfo(goodsId, user.getId());
            if (null == goodsInfo){
                return AjaxResponse.error("信息不符");
            }
            if (goodsInfo.getStatus()){
                return AjaxResponse.error("未下架商品禁止上传图片");
            }
            ViewGoodsFile record = goodsService.selectFileInfo(fileId, goodsId, user.getId());
            if (null == record) {
                return AjaxResponse.error("未找到相关记录");
            }
            String fileSrc = "/api/image/GI-" + RandomUtil.getTimeStamp();
            QiNiuUtil.uploadFile(multipartFile, fileSrc);
            goodsService.updateGoodsFile(fileId, fileSrc, true);
            Map map = new HashMap();
            map.put("src", fileSrc);
            map.put("id", fileId);
            return AjaxResponse.success(map);
        } catch (Exception e) {
            log.error("上传头像出错{}", e);
            return AjaxResponse.success("上传失败");
        }
    }

    /**
     * 上传商品视频文件
     *
     * @param user
     * @param goodsId
     * @param fileId
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "uploadGoodsFileVideo")
    public Object uploadGoodsFileVideo(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId, Integer fileId,
                                       @RequestParam("goodsFile") MultipartFile multipartFile) {
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkVideoFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            if (NullUtil.isNullOrEmpty(goodsId) || NullUtil.isNullOrEmpty(fileId)) {
                return AjaxResponse.error("参数错误");
            }
            GoodsInfo goodsInfo = goodsService.selectGoodsInfo(goodsId, user.getId());
            if (null == goodsInfo){
                return AjaxResponse.error("信息不符");
            }
            if (goodsInfo.getStatus()){
                return AjaxResponse.error("未下架商品禁止上传视频");
            }
            ViewGoodsFile record = goodsService.selectFileInfo(fileId, goodsId, user.getId());
            if (null == record) {
                return AjaxResponse.error("未找到相关记录");
            }
            String fileSrc = NullUtil.isNotNullOrEmpty(record.getFileSrc()) ?
                    record.getFileSrc() : "/api/video/GV-" + RandomUtil.getTimeStamp();
            QiNiuUtil.uploadFile(multipartFile, fileSrc);
            goodsService.updateGoodsFile(fileId, fileSrc, true);
            Map map = new HashMap();
            map.put("src", fileSrc + "?token=" + RandomUtil.getTimeStamp());
            map.put("id", fileId);
            return AjaxResponse.success(map);
        } catch (Exception e) {
            log.error("上传头像出错{}", e);
            return AjaxResponse.error("上传失败");
        }
    }

    /**
     * 删除商品文件
     *
     * @param user
     * @param goodsId
     * @param fileId
     * @return
     */
    @RequestMapping(value = "deleteGoodsFile")
    public Object deleteGoodsFile(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId, Integer fileId) {
        try {
            if (NullUtil.isNullOrEmpty(goodsId) || NullUtil.isNullOrEmpty(fileId)) {
                return AjaxResponse.error("参数错误");
            }
            ViewGoodsFile record = goodsService.selectFileInfo(fileId, goodsId, user.getId());
            if (null == record) {
                return AjaxResponse.error("未找到相关记录");
            }
            goodsService.updateGoodsFile(fileId, null, false);
            goodsService.checkGoodsValue(goodsId, user.getId(), false);
            return AjaxResponse.success();
        } catch (Exception e) {
            log.error("删除商品文件出错{}", e);
            return AjaxResponse.error("删除失败");
        }
    }

    /**
     * 加载商品规格列表
     *
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "loadSpecsPage")
    public Object loadSpecsPage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId) {
        int count = goodsService.selectSpecsCount(goodsId, user.getId());
        if (count >= Constants.GOODS_SPECS_COUNT) {
            return AjaxResponse.msg("-1", goodsId);
        }
        return AjaxResponse.success(goodsId);
    }

    /**
     * 查询商品规格集合
     *
     * @param user
     * @param specs
     * @return
     */
    @RequestMapping(value = "querySpecsPage")
    public Object querySpecsPage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, ViewGoodsSpecs specs, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        specs.setUserId(user.getId());
        page = goodsService.selectSpecsList(specs, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载商品规格
     *
     * @param user
     * @param goodsId
     * @param specsId
     * @return
     */
    @RequestMapping(value = "loadGoodsSpecs")
    public Object loadGoodsSpecs(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId, Integer specsId) {
        if (NullUtil.isNotNullOrEmpty(specsId) && specsId.intValue() != 0) {
            GoodsInfo goodsInfo = goodsService.selectGoodsInfo(goodsId, user.getId());
            ViewGoodsSpecs record = goodsService.selectSpecsInfo(specsId, goodsId, user.getId());
            if (null != record) {
                Map map = new HashMap();
                map.put("discount", goodsInfo.getDiscount());
                map.put("specs", record);
                record.setSpecsSrc(record.getSpecsSrc());
                return AjaxResponse.success(map);
            }
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 更新商品规格
     *
     * @param user
     * @param specs
     * @return
     */
    @RequestMapping(value = "updateGoodsSpecs")
    public Object updateGoodsSpecs(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, GoodsSpecs specs) {
        try {
            if (null == specs) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(specs.getGoodsId())) {
                return AjaxResponse.error("参数丢失");
            }
            GoodsInfo goodsInfo = goodsService.selectGoodsInfo(specs.getGoodsId(), user.getId());
            if (null == goodsInfo){
                return AjaxResponse.error("信息不符");
            }
            if (goodsInfo.getStatus()){
                return AjaxResponse.error("未下架商品禁止修改信息");
            }
            if (NullUtil.isNotNullOrEmpty(specs.getSpecsText()) && specs.getSpecsText().length() > 100) {
                return AjaxResponse.error("商品规格长度过长");
            }
            if (NullUtil.isNullOrEmpty(specs.getSellPrice())) {
                return AjaxResponse.error("出售价格不能为空");
            }
            if (specs.getSellPrice().doubleValue() <= 0.00d) {
                return AjaxResponse.error("出售价格不能低于0.01");
            }
            if (specs.getSellPrice().doubleValue() > 99999.99d) {
                return AjaxResponse.error("出售价格不能高于99999.99");
            }
            if (NullUtil.isNotNullOrEmpty(specs.getDescribeStr()) && specs.getDescribeStr().length() > 500) {
                return AjaxResponse.error("折扣描述长度过长");
            }
            if (NullUtil.isNullOrEmpty(specs.getId())) {
                int count = goodsService.selectGoodsSpecsCount(specs.getGoodsId(), user.getId());
                if (count >= Constants.GOODS_SPECS_COUNT) {
                    return AjaxResponse.error("商品规格创建数量已达上限");
                }
            }
            boolean bool = (NullUtil.isNullOrEmpty(specs.getId()) && !specs.getSpecsStatus()) ? false : true;
            String specsSrc = "/api/image/GS-" + RandomUtil.getTimeStamp();
            if (NullUtil.isNotNullOrEmpty(specs.getId()) && NullUtil.isNotNullOrEmpty(specs.getSpecsSrc())) {
                ViewGoodsSpecs record = goodsService.selectSpecsInfo(specs.getId(), specs.getGoodsId(), user.getId());
                if (null == record) {
                    return AjaxResponse.error("信息不符");
                }
                specsSrc = specs.getSpecsSrc().equals(record.getSpecsSrc()) ? null : record.getSpecsSrc();
            }
            if (NullUtil.isNotNullOrEmpty(specsSrc)) {
                this.removeFile(specs.getSpecsSrc(), specsSrc);
                specs.setSpecsSrc(specsSrc);
            }
            goodsService.updateGoodsSpecs(specs, user.getId());
            if (bool) {
                goodsService.checkGoodsValue(specs.getGoodsId(), user.getId(), true);
            }
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("跟新商品规格出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 更新商品规格排序
     *
     * @param user
     * @param goodsId
     * @param specsId
     * @param sort
     * @return
     */
    @RequestMapping(value = "updateGoodsSpecsIndex")
    public Object updateGoodsSpecsIndex(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId,
                                        Integer specsId, String sort) {
        try {
            if (NullUtil.isNullOrEmpty(goodsId) || NullUtil.isNullOrEmpty(specsId) || NullUtil.isNullOrEmpty(sort)) {
                return AjaxResponse.error("参数错误");
            }
            int count = goodsService.selectGoodsSpecsCount(goodsId, user.getId());
            if (count > 1) {
                ViewGoodsSpecs specs = goodsService.selectSpecsInfo(specsId, goodsId, user.getId());
                if (null == specs) {
                    return AjaxResponse.error("未找到相关记录");
                }
                Integer num = null;
                if (sort.equals("top") && specs.getSpecsIndex().intValue() - 1 > 0) {
                    num = -1;
                } else if (sort.equals("bot") && specs.getSpecsIndex() + 1 <= count) {
                    num = 1;
                } else {
                    return AjaxResponse.success("参数错误");
                }
                goodsService.updateGoodsSpecsIndex(specs, num);
            }
            return AjaxResponse.success();
        } catch (Exception e) {
            log.error("更新商品排序出错{}", e);
            return AjaxResponse.error("操作失败");
        }
    }

    /**
     * 查询摇号小程序Map信息集合
     * @param user
     * @return
     */
    @RequestMapping(value = "queryAppletToMap")
    public Object queryAppletToMap(@SessionScope(Constants.VUE_USER_INFO) UserInfo user){
        List<Map> list = goodsService.selectAppletToMap(user.getId());
        if (NullUtil.isNullOrEmpty(list)) {
            return AjaxResponse.error("未找到相关记录");
        }
        return AjaxResponse.success(list);
    }
}
