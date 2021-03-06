package com.applet.user.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.applet.common.entity.other.CheckResult;
import com.applet.user.config.annotation.SessionScope;
import com.applet.common.entity.*;
import com.applet.user.service.AppletService;
import com.applet.user.service.UserGoodsService;
import com.applet.common.util.*;
import com.applet.common.util.qiniu.QiNiuUtil;
import jodd.datetime.JDateTime;
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
    private UserGoodsService userGoodsService;
    @Autowired
    private AppletService appletService;

    /**
     * 上传商品图片文件
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "uploadGoodsImage")
    public Object uploadGoodsImage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user,
                                   @RequestParam("image") MultipartFile multipartFile, String oldSrc) {
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            String fileKey = "/api/public/U" + user.getId() + "-GT" + RandomUtil.getTimeStamp();
            if (NullUtil.isNotNullOrEmpty(oldSrc)){
                fileKey = oldSrc;
                QiNiuUtil.deleteFile(fileKey);
            }
            QiNiuUtil.uploadFile(multipartFile, fileKey);
            return AjaxResponse.success(fileKey);
        } catch (Exception e) {
            log.error("上传头像出错{}", e);
            return AjaxResponse.error("上传失败");
        }
    }

    /**
     * 加载商品类型列表
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "loadTypePage")
    public Object loadTypePage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user) {
        int count = userGoodsService.selectGoodsTypeCount(user.getId(), null);
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
        if (NullUtil.isNotNullOrEmpty(appletId)) {
            page = userGoodsService.selectTypePage(appletId, user.getId(), name, status, page);
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
        GoodsType goodsType = userGoodsService.selectGoodsType(id, user.getId());
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
            if (NullUtil.isNullOrEmpty(goodsType.getAppletId())) {
                return AjaxResponse.error("参数丢失");
            }
            if (NullUtil.isNullOrEmpty(goodsType.getTypeLogo())) {
                return AjaxResponse.error("类类型图标不能为空");
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
                int count = userGoodsService.selectGoodsTypeCount(user.getId(), goodsType.getAppletId());
                if (count >= Constants.GOODS_TYPE_COUNT) {
                    return AjaxResponse.error("类型创建数量已达上限");
                }
                goodsType.setTypeIndex(count);
            }
            goodsType.setUserId(user.getId());
            userGoodsService.updateGoodsType(goodsType);
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
    public Object updateGoodsTypeIndex(@SessionScope(Constants.VUE_USER_INFO) UserInfo user,
                                       Integer typeId, String sort, Integer appletId) {
        try {
            if (NullUtil.isNullOrEmpty(typeId) || NullUtil.isNullOrEmpty(sort) || NullUtil.isNullOrEmpty(appletId)) {
                return AjaxResponse.error("参数错误");
            }
            int count = userGoodsService.selectGoodsTypeCount(user.getId(), appletId);
            if (count > 1) {
                GoodsType type = userGoodsService.selectGoodsType(typeId, user.getId());
                if (null == type) {
                    return AjaxResponse.error("未找到相关记录");
                }
                Integer num = null;
                if (sort.equals("top") && type.getTypeIndex() - 1 >= 0) {
                    num = -1;
                } else if (sort.equals("bot") && type.getTypeIndex() + 1 < count) {
                    num = 1;
                } else {
                    return AjaxResponse.success("参数错误");
                }
                userGoodsService.updateGoodsTypeIndex(type, num);
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
        List<GoodsType> list = userGoodsService.selectTypeList(appletId, user.getId());
        if (NullUtil.isNullOrEmpty(list)) {
            return AjaxResponse.error("未找到相关记录");
        }
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
        goods.setUserId(user.getId());
        Page page = PageUtil.initPage(request);
        page = userGoodsService.selectInfoPage(goods, page);
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
        List<GoodsType> list = userGoodsService.selectTypeList(user.getId());
        map.put("typeList", list);
        if (NullUtil.isNotNullOrEmpty(id) && id.intValue() != 0) {
            GoodsInfo goods = userGoodsService.selectGoodsInfo(id, user.getId());
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
            GoodsType type = userGoodsService.selectGoodsType(goods.getTypeId(), user.getId());
            if (null == type) {
                return AjaxResponse.error("商品类型选择错误");
            }
            if (NullUtil.isNullOrEmpty(goods.getDiscount())) {
                return AjaxResponse.error("折扣不能为空");
            }
            if (goods.getDiscount().intValue() < 1 || goods.getDiscount().intValue() > 100) {
                return AjaxResponse.error("折扣只能为1-100");
            }
            if (NullUtil.isNotNullOrEmpty(goods.getDescribeStr()) && goods.getDescribeStr().getBytes().length > 300) {
                return AjaxResponse.error("描述输入过长");
            }
            goods.setUserId(user.getId());
            goods.setMinPrice(null);
            goods.setMaxPrice(null);
            try {
                userGoodsService.updateGoodsInfo(goods);
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
            int count = userGoodsService.selectGoodsCount(typeId, user.getId());
            if (count > 1) {
                GoodsInfo goods = userGoodsService.selectGoodsInfo(goodsId, typeId, user.getId());
                if (null == goods) {
                    return AjaxResponse.error("未找到相关记录");
                }
                Integer num = null;
                if (sort.equals("top") && goods.getGoodsIndex().intValue() - 1 >= 0) {
                    num = -1;
                } else if (sort.equals("bot") && goods.getGoodsIndex().intValue() + 1 < count) {
                    num = 1;
                } else {
                    return AjaxResponse.success("参数错误");
                }
                userGoodsService.updateGoodsIndex(goods, num);
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
        ViewGoodsInfo goods = userGoodsService.selectViewGoodsInfo(goodsId, user.getId());
        try {
            if (goods.getGoodsStatus().intValue() == 0) {
                GoodsType type = userGoodsService.selectGoodsType(goods.getTypeId(), user.getId());
                if (!type.getTypeStatus()) {
                    return AjaxResponse.error("发布失败：该商品的类型已禁用");
                }
                int fileCount = userGoodsService.selectFileCount(goodsId, user.getId());
                if (fileCount <= 0) {
                    return AjaxResponse.error("发布失败：请提交文件(至少一张图片或短视频)");
                }
                int specsCount = userGoodsService.selectSpecsCount(goodsId, user.getId());
                if (specsCount <= 0) {
                    return AjaxResponse.error("发布失败：请设置至少一条正常规格)");
                }
            }
            userGoodsService.updateGoodsStatus(goods);
            return AjaxResponse.success(goods.getGoodsStatus().intValue() == 1 ? "下架成功" : "发布成功");
        } catch (Exception e) {
            log.error("更新商品状态出错{}", e);
            return AjaxResponse.error(goods.getGoodsStatus().intValue() == 1 ? "下架失败" : "发布失败");
        }
    }

    /**
     * 删除商品信息
     *
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "deleteGoodsInfo")
    public Object deleteGoodsInfo(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId) {
        try {
            if (NullUtil.isNullOrEmpty(goodsId)) {
                return AjaxResponse.error("参数错误");
            }
            ViewGoodsInfo goods = userGoodsService.selectViewGoodsInfo(goodsId, user.getId());
            if (null != goods) {
                userGoodsService.updateGoodsStatus(goodsId);
                return AjaxResponse.success("删除成功");
            }
        } catch (Exception e) {
            log.error("删除商品信息出错{}", e);
        }
        return AjaxResponse.error("删除失败");
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
        List<ViewGoodsFile> list = userGoodsService.selectFileList(goodsId, user.getId(), null);
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
            GoodsInfo goodsInfo = userGoodsService.selectGoodsInfo(goodsId, user.getId());
            if (null == goodsInfo) {
                return AjaxResponse.error("信息不符");
            }
            if (goodsInfo.getStatus() != 0) {
                return AjaxResponse.error("未下架商品禁止上传图片");
            }
            ViewGoodsFile record = userGoodsService.selectFileInfo(fileId, goodsId, user.getId());
            if (null == record) {
                return AjaxResponse.error("未找到相关记录");
            }
            String fileSrc = "/api/public/GI-" + RandomUtil.getTimeStamp();
            QiNiuUtil.uploadFile(multipartFile, fileSrc);
            userGoodsService.updateGoodsFile(fileId, fileSrc, true);
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
            GoodsInfo goodsInfo = userGoodsService.selectGoodsInfo(goodsId, user.getId());
            if (null == goodsInfo) {
                return AjaxResponse.error("信息不符");
            }
            if (goodsInfo.getStatus() != 0) {
                return AjaxResponse.error("未下架商品禁止上传视频");
            }
            ViewGoodsFile record = userGoodsService.selectFileInfo(fileId, goodsId, user.getId());
            if (null == record) {
                return AjaxResponse.error("未找到相关记录");
            }
            String fileSrc = "/api/video/GV-" + RandomUtil.getTimeStamp();
            QiNiuUtil.uploadFile(multipartFile, fileSrc);
            userGoodsService.updateGoodsFile(fileId, fileSrc, true);
            if (NullUtil.isNotNullOrEmpty(record.getFileSrc())) {
                QiNiuUtil.deleteFile(record.getFileSrc());
            }
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
            ViewGoodsFile record = userGoodsService.selectFileInfo(fileId, goodsId, user.getId());
            if (null == record) {
                return AjaxResponse.error("未找到相关记录");
            }
            QiNiuUtil.deleteFile(record.getFileSrc());
            userGoodsService.updateGoodsFile(fileId, null, false);
//            userGoodsService.checkGoodsValue(goodsId, user.getId());
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
        int count = userGoodsService.selectSpecsCount(goodsId, user.getId());
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
        specs.setUserId(user.getId());
        Page page = PageUtil.initPage(request);
        page = userGoodsService.selectSpecsList(specs, page);
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
            GoodsInfo goodsInfo = userGoodsService.selectGoodsInfo(goodsId, user.getId());
            ViewGoodsSpecs record = userGoodsService.selectViewSpecsInfo(specsId, goodsId, user.getId());
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
            GoodsInfo goodsInfo = userGoodsService.selectGoodsInfo(specs.getGoodsId(), user.getId());
            if (null == goodsInfo) {
                return AjaxResponse.error("信息不符");
            }
            if (goodsInfo.getStatus() != 0) {
                return AjaxResponse.error("未下架商品禁止修改信息");
            }
            if (NullUtil.isNullOrEmpty(specs.getSpecsSrc())) {
                return AjaxResponse.error("请上传规格图片");
            }
            if (NullUtil.isNotNullOrEmpty(specs.getSpecsName()) && specs.getSpecsName().length() > 100) {
                return AjaxResponse.error("规格名称长度过长");
            }
            if (NullUtil.isNullOrEmpty(specs.getSpecsTypeList())) {
                return AjaxResponse.error("规格类型集不能为空");
            }
            specs.setSpecsTypeList(specs.getSpecsTypeList().trim());
            int specsTypeFirstIndex = specs.getSpecsTypeList().indexOf(";");
            if (specsTypeFirstIndex == 0){
                specs.setSpecsTypeList(specs.getSpecsTypeList().substring(1, specs.getSpecsTypeList().length()));
            }
            int specsTypeLastIndex = specs.getSpecsTypeList().lastIndexOf(";");
            if (specsTypeLastIndex == (specs.getSpecsTypeList().length() - 1)){
                specs.setSpecsTypeList(specs.getSpecsTypeList().substring(0, specsTypeLastIndex));
            }
            if (NullUtil.isNotNullOrEmpty(specs.getSpecsTypeList()) && specs.getSpecsTypeList().getBytes().length > 660) {
                return AjaxResponse.error("规格类型集长度过长");
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
            if (NullUtil.isNotNullOrEmpty(specs.getDescribeStr())) {
                specs.setDescribeStr(specs.getDescribeStr().trim());
                if (specs.getDescribeStr().getBytes().length > 660) {
                    return AjaxResponse.error("规格描述长度过长");
                }
            }
            if (NullUtil.isNullOrEmpty(specs.getId())) {
                int count = userGoodsService.selectGoodsSpecsCount(specs.getGoodsId(), user.getId());
                if (count >= Constants.GOODS_SPECS_COUNT) {
                    return AjaxResponse.error("商品规格创建数量已达上限");
                }
            }
            boolean bool = (NullUtil.isNullOrEmpty(specs.getId()) && !specs.getSpecsStatus()) ? false : true;
            String oldSrc = null;
            if (NullUtil.isNotNullOrEmpty(specs.getId()) && NullUtil.isNotNullOrEmpty(specs.getSpecsSrc())) {
                ViewGoodsSpecs record = userGoodsService.selectViewSpecsInfo(specs.getId(), specs.getGoodsId(), user.getId());
                if (null != record && !specs.getSpecsSrc().equals(record.getSpecsSrc())) {
                    oldSrc = record.getSpecsSrc();
                }
            }
            if (NullUtil.isNotNullOrEmpty(oldSrc)) {
                QiNiuUtil.deleteFile(oldSrc);
            }
            userGoodsService.updateGoodsSpecs(specs, user.getId());
            if (bool) {
                userGoodsService.checkGoodsValueAndCart(user.getId(), specs);
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
            int count = userGoodsService.selectGoodsSpecsCount(goodsId, user.getId());
            if (count > 1) {
                ViewGoodsSpecs specs = userGoodsService.selectViewSpecsInfo(specsId, goodsId, user.getId());
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
                userGoodsService.updateGoodsSpecsIndex(specs, num);
            }
            return AjaxResponse.success();
        } catch (Exception e) {
            log.error("更新商品排序出错{}", e);
            return AjaxResponse.error("操作失败");
        }
    }

    /**
     * 删除商品规格
     * @param user
     * @param goodsId
     * @param specsId
     * @return
     */
    @RequestMapping(value = "deleteGoodsSpecs")
    public Object deleteGoodsSpecs(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer goodsId, Integer specsId){
        try {
            GoodsInfo goodsInfo = userGoodsService.selectGoodsInfo(goodsId, user.getId());
            if (null == goodsInfo) {
                return AjaxResponse.error("信息不符");
            }
            if (goodsInfo.getStatus() != 0) {
                return AjaxResponse.error("未下架商品禁止修改信息");
            }
            GoodsSpecs specs = userGoodsService.selectSpecsInfo(specsId, goodsId);
            if (null == specs) {
                return AjaxResponse.error("未找到相关记录");
            }
            userGoodsService.deleteGoodsSpecs(specs.getId());
            specs.setSpecsStatus(false);
            userGoodsService.checkGoodsValueAndCart(user.getId(), specs);
            return AjaxResponse.success("删除成功");
        } catch (Exception e) {
            log.error("删除商品规格出错{}", e);
        }
        return AjaxResponse.error("删除失败");
    }

    /**
     * 分享查询用户小程序推荐商品
     *
     * @param user
     * @param rg
     * @param request
     * @return
     */
    @RequestMapping(value = "queryUserAppletRecommendGoodsByPage")
    public Object queryUserAppletRecommendGoodsByPage(@SessionScope(Constants.VUE_USER_INFO) UserInfo user,
                                                      ViewUserAppletRecommendGoods rg, HttpServletRequest request) {
        rg.setUserId(user.getId());
        Page page = PageUtil.initPage(request);
        page = userGoodsService.selectUserAppletRecommendGoodsByPage(rg, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载用户小程序推荐商品详情
     *
     * @param user
     * @param id
     * @return
     */
    @RequestMapping(value = "loadUserAppletRecommendGoodsDetails")
    public Object loadUserAppletRecommendGoodsDetails(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer id) {
        UserAppletRecommendGoods rg = userGoodsService.selectUserAppletRecommendGoods(id, user.getId());
        if (null != rg) {
            ViewGoodsInfo goodsInfo = userGoodsService.selectViewGoodsInfo(rg.getGoodsId(), user.getId());
            Map map = new HashMap();
            map.put("info", rg);
            map.put("goods", goodsInfo);
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 更新小程序推荐商品信息
     *
     * @param user
     * @param rg
     * @return
     */
    @RequestMapping(value = "updateUserAppletRecommendGoods")
    public Object updateUserAppletRecommendGoods(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, UserAppletRecommendGoods rg) {
        try {
            if (null == rg) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(rg.getRecommendImg())) {
                return AjaxResponse.error("请上传推荐图片");
            }
            if (NullUtil.isNullOrEmpty(rg.getAppletId())) {
                return AjaxResponse.error("请选择小程序");
            }
            AppletInfo appletInfo = appletService.selectAppletInfo(rg.getAppletId(), user.getId());
            if (null == appletInfo) {
                return AjaxResponse.error("未找到相关小程序");
            }
            if (NullUtil.isNullOrEmpty(rg.getGoodsId())) {
                return AjaxResponse.error("请选择关联商品");
            }
            ViewGoodsInfo goodsInfo = userGoodsService.selectViewGoodsInfo(rg.getGoodsId(), user.getId());
            if (null == goodsInfo) {
                return AjaxResponse.error("未找到相关商品");
            }
            if (appletInfo.getId().intValue() != goodsInfo.getAppletId()) {
                return AjaxResponse.error("小程序与商品信息不匹配");
            }
            if (NullUtil.isNullOrEmpty(rg.getStartTime())) {
                return AjaxResponse.error("请选择生效日期");
            }
            if (NullUtil.isNullOrEmpty(rg.getExpireTime())) {
                return AjaxResponse.error("请选择截止日期");
            }
            JDateTime start = new JDateTime(rg.getStartTime());
            start.setHour(0).setMinute(0).setSecond(0);
            JDateTime expire = new JDateTime(rg.getExpireTime());
            expire.setHour(23).setMinute(59).setSecond(59);
            if (expire.compareDateTo(start) != 1) {
                return AjaxResponse.error("日期选择错误");
            }
            rg.setStartTime(start.convertToDate());
            rg.setExpireTime(expire.convertToDate());
            rg.setUserId(user.getId());
            userGoodsService.updateUserAppletRecommendGoods(rg);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("提交小程序推荐商品信息出错{}", e);
        }
        return AjaxResponse.error("提交失败");
    }

    /**
     * 加载小程序分类列表 - 排序
     *
     * @param user
     * @param appletId
     * @return
     */
    @RequestMapping(value = "loadGoodsTypeDraggableList")
    public Object loadGoodsTypeDraggableList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer appletId) {
        AppletInfo appletInfo = appletService.selectAppletInfo(appletId, user.getId());
        if (null == appletInfo) {
            return AjaxResponse.error("信息不符");
        }
        List<Map> list = userGoodsService.selectTypeDraggableList(user.getId(), appletId);
        return AjaxResponse.success(list);
    }

    /**
     * 批量更新商品类型 - 排序
     *
     * @param user
     * @param appletId
     * @param json
     * @return
     */
    @RequestMapping(value = "updateGoodsTypeIndexList")
    public Object updateGoodsTypeIndexList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer appletId,
                                           String json) {
        try {
            if (NullUtil.isNullOrEmpty(appletId) || NullUtil.isNullOrEmpty(json)) {
                return AjaxResponse.error("参数错误");
            }
            AppletInfo appletInfo = appletService.selectAppletInfo(appletId, user.getId());
            if (null == appletInfo) {
                return AjaxResponse.error("信息不符");
            }
            JSONArray arrayList = JSONArray.parseArray(json);
            List<GoodsType> list = JSONObject.parseArray(arrayList.toJSONString(), GoodsType.class);
            userGoodsService.updateGoodsTypeIndexs(user.getId(), appletId, list);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("批量更新商品分类排序出错{}", e);
        }
        return AjaxResponse.error("提交失败");
    }


    /**
     * 加载小程序分类下的商品列表 - 排序
     *
     * @param user
     * @param appletId
     * @param typeId
     * @return
     */
    @RequestMapping(value = "loadGoodsDraggableList")
    public Object loadGoodsDraggableList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer appletId, Integer typeId) {
        GoodsType goodsType = userGoodsService.selectGoodsType(typeId, appletId, user.getId());
        if (null == goodsType) {
            return AjaxResponse.error("信息不符");
        }
        List<Map> list = userGoodsService.selectGoodsDraggableList(user.getId(), appletId, typeId);
        return AjaxResponse.success(list);
    }

    /**
     * 批量更新商品 - 排序
     *
     * @param user
     * @param typeId
     * @param json
     * @return
     */
    @RequestMapping(value = "updateGoodsIndexList")
    public Object updateGoodsIndexList(@SessionScope(Constants.VUE_USER_INFO) UserInfo user, Integer typeId,
                                       String json) {
        try {
            if (NullUtil.isNullOrEmpty(typeId) || NullUtil.isNullOrEmpty(json)) {
                return AjaxResponse.error("参数错误");
            }
            GoodsType goodsType = userGoodsService.selectGoodsType(typeId, user.getId());
            if (null == goodsType) {
                return AjaxResponse.error("信息不符");
            }
            JSONArray arrayList = JSONArray.parseArray(json);
            List<GoodsInfo> list = JSONObject.parseArray(arrayList.toJSONString(), GoodsInfo.class);
            userGoodsService.updateGoodsIndexs(user.getId(), typeId, list);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("批量更新商品排序出错{}", e);
        }
        return AjaxResponse.error("提交失败");
    }

}
