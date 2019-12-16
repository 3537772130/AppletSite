package com.applet.user.service;

import com.applet.user.entity.*;
import com.applet.user.mapper.*;
import com.applet.user.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: AppletSite
 * @description: 管理小程序页面服务类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-12-09 11:13
 **/
@Service
public class AppletPageService {
    @Autowired
    private ViewAppletVersionMapper viewAppletVersionMapper;
    @Autowired
    private AppletPageMapper appletPageMapper;
    @Autowired
    private ViewAppletPageMapper viewAppletPageMapper;
    @Autowired
    private AppletPageElementTypeMapper appletPageElementTypeMapper;
    @Autowired
    private AppletPageElementMapper appletPageElementMapper;
    @Autowired
    private AppletPageContentMapper appletPageContentMapper;
    @Autowired
    private ViewAppletPageContentMapper viewAppletPageContentMapper;
    @Autowired
    private CommonMapper commonMapper;

    /**
     * 查询用户小程序版本信息
     *
     * @param userId
     * @param appletId
     * @return
     */
    public ViewAppletVersion selectAppletVersionInfo(Integer userId, Integer appletId) {
        ViewAppletVersionExample example = new ViewAppletVersionExample();
        example.createCriteria().andUserIdEqualTo(userId).andAppletIdEqualTo(appletId);
        List<ViewAppletVersion> list = viewAppletVersionMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询页面集合
     *
     * @param userId
     * @param appletId
     * @return
     */
    public List<ViewAppletPage> selectAppletPageList(Integer userId, Integer appletId) {
        ViewAppletPageExample example = new ViewAppletPageExample();
        example.setOrderByClause("page_id desc");
        example.createCriteria().andUserIdEqualTo(userId).andAppletIdEqualTo(appletId);
        return viewAppletPageMapper.selectByExample(example);
    }

    /**
     * 小程序页面信息详情
     *
     * @param userId
     * @param appletId
     * @param pageId
     * @return
     */
    public ViewAppletPage selectAppletPageById(Integer userId, Integer appletId, Integer pageId) {
        ViewAppletPageExample example = new ViewAppletPageExample();
        example.createCriteria().andUserIdEqualTo(userId).andAppletIdEqualTo(appletId).andPageIdEqualTo(pageId);
        List<ViewAppletPage> list = viewAppletPageMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询页面元素类型集合
     *
     * @param pageId
     * @return
     */
    public List<AppletPageElementType> selectElementTypeList(Integer pageId) {
        AppletPageElementTypeExample example = new AppletPageElementTypeExample();
        example.createCriteria().andPageIdEqualTo(pageId).andTypeStatusEqualTo(true);
        return appletPageElementTypeMapper.selectByExample(example);
    }

    /**
     * 查询页面元素集合
     *
     * @param pageId
     * @return
     */
    public List<AppletPageElement> selectElementList(Integer pageId) {
        AppletPageElementExample example = new AppletPageElementExample();
        example.setOrderByClause("element_index asc");
        example.createCriteria().andPageIdEqualTo(pageId).andElementStatusEqualTo(true);
        return appletPageElementMapper.selectByExample(example);
    }

    /**
     * 获取小程序页面内容集合
     *
     * @param userId
     * @param appletId
     * @return
     */
    public List<ViewAppletPageContent> selectAppletPageContent(Integer userId, Integer appletId) {
        ViewAppletPageContentExample example = new ViewAppletPageContentExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andUserIdEqualTo(userId);
        return viewAppletPageContentMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 获取小程序页面内容
     *
     * @param userId
     * @param appletId
     * @param pageId
     * @return
     */
    public ViewAppletPageContent selectAppletPageContent(Integer userId, Integer appletId, Integer pageId) {
        ViewAppletPageContentExample example = new ViewAppletPageContentExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andUserIdEqualTo(userId).andPageIdEqualTo(pageId);
        List<ViewAppletPageContent> list = viewAppletPageContentMapper.selectByExampleWithBLOBs(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 获取小程序页面默认内容
     *
     * @param pageId
     * @return
     */
    public AppletPageContent selectAppletPageContent(Integer pageId) {
        AppletPageContentExample example = new AppletPageContentExample();
        example.createCriteria().andAppletIdEqualTo(0).andPageIdEqualTo(pageId);
        List<AppletPageContent> list = appletPageContentMapper.selectByExampleWithBLOBs(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新页面元素内容
     *
     * @param content
     */
    public void updateAppletPageContent(AppletPageContent content) {
        content.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(content.getId())) {
            appletPageContentMapper.updateByPrimaryKeySelective(content);
        } else {
            appletPageContentMapper.insertSelective(content);
        }
    }

    /**
     * 查询测试商品列表
     *
     * @param userId
     * @return
     */
    public List<Map> selectGoodsInfoList(Integer userId, String goodsName) {
        String sql = "SELECT id,type_id AS typeId,goods_name AS name,cover_src AS icon FROM goods_info WHERE user_id = " + userId + " AND `status` = 1";
        if (NullUtil.isNotNullOrEmpty(goodsName)) {
            sql += " AND goods_name LIKE '%" + goodsName + "%'";
        }
        sql += " ORDER BY goods_index ASC;";
        return commonMapper.selectListMap(sql);
    }

    /**
     * 查询测试商品详情列表
     *
     * @param userId
     * @return
     */
    public List<Map> selectGoodsDetailsList(Integer userId, String goodsName) {
        String sql = "SELECT id,type_id AS typeId,goods_name AS name,cover_src AS icon,min_price AS minPrice,max_price AS maxPrice FROM goods_info WHERE user_id = " + userId + " AND `status` = 1";
        if (NullUtil.isNotNullOrEmpty(goodsName)) {
            sql += " AND goods_name LIKE '%" + goodsName + "%'";
        }
        sql += " ORDER BY goods_index ASC;";
        return commonMapper.selectListMap(sql);
    }

    /**
     * 查询测试商品折扣列表
     *
     * @param userId
     * @return
     */
    public List<Map> selectGoodsDiscountList(Integer userId, String goodsName) {
        String sql = "SELECT id,goods_name AS name,specs_src AS icon,actual_price AS minPrice,sell_price AS maxPrice FROM view_goods_specs_summary WHERE user_id = " + userId;
        if (NullUtil.isNotNullOrEmpty(goodsName)) {
            sql += " AND goods_name LIKE '%" + goodsName + "%'";
        }
        sql += " ORDER BY goods_index ASC;";
        return commonMapper.selectListMap(sql);
    }

    /**
     * 查询测试商品类型列表
     *
     * @param userId
     * @return
     */
    public List<Map> selectGoodsTypeList(Integer userId, String typeName) {
        String sql = "SELECT id,type_name AS name,type_logo AS icon FROM goods_type WHERE user_id = " + userId + " AND type_status = 1";
        if (NullUtil.isNotNullOrEmpty(typeName)) {
            sql += " AND type_name LIKE '%" + typeName + "%'";
        }
        sql += " ORDER BY type_index ASC;";
        return commonMapper.selectListMap(sql);
    }
}
