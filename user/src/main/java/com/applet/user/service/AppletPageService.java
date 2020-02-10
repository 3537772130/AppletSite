package com.applet.user.service;

import com.applet.common.util.Arith;
import com.applet.user.entity.*;
import com.applet.user.entity.page.ContentInfo;
import com.applet.user.entity.page.ElementInfo;
import com.applet.user.entity.page.PageContent;
import com.applet.user.mapper.*;
import com.applet.common.util.NullUtil;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: AppletSite
 * @description: 管理小程序页面服务类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-12-09 11:13
 **/
@SuppressWarnings("ALL")
@Service
public class AppletPageService {
    private final static Logger log = LoggerFactory.getLogger(AppletPageService.class);
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
     * @param appletId
     * @param userId
     * @param goodsName
     * @return
     */
    public List<Map> selectGoodsInfoList(Integer appletId, Integer userId, String goodsName) {
        String sql = "SELECT id,type_id AS typeId,goods_name AS name,cover_src AS icon FROM view_goods_info " +
                "WHERE applet_id = " + appletId + " AND user_id = " + userId + " AND goods_status = 1";
        if (NullUtil.isNotNullOrEmpty(goodsName)){
            sql +=  " AND goods_name LIKE '%" + goodsName + "%'";
        }
        sql +=  " ORDER BY goods_index ASC;";
        return commonMapper.selectListMap(sql);
    }

    /**
     * 查询测试商品详情列表
     * @param appletId
     * @param userId
     * @param goodsName
     * @return
     */
    public List<Map> selectGoodsDetailsList(Integer appletId, Integer userId, String goodsName) {
        String sql = "SELECT id,type_id AS typeId,goods_name AS name,cover_src AS icon,min_price AS minPrice,max_price AS maxPrice,discount AS discount" +
                " FROM view_goods_info WHERE applet_id = " + appletId + " AND user_id = " + userId + " AND goods_status = 1";
        if (NullUtil.isNotNullOrEmpty(goodsName)){
            sql +=  " AND goods_name LIKE '%" + goodsName + "%'";
        }
        sql +=  " ORDER BY goods_index ASC;";
        return commonMapper.selectListMap(sql);
    }

    /**
     * 查询测试商品折扣列表
     * @param appletId
     * @param userId
     * @param goodsName
     * @return
     */
    public List<Map> selectGoodsDiscountList(Integer appletId, Integer userId, String goodsName) {
        String sql = "SELECT id,goods_name AS name,cover_src AS icon,CONVERT(((min_price*discount)/100),decimal(10,2)) AS minPrice,min_price AS maxPrice,discount AS discount" +
                " FROM view_goods_info WHERE applet_id = " + appletId + " AND user_id = " + userId + "  AND discount <> 100";
        if (NullUtil.isNotNullOrEmpty(goodsName)){
            sql +=  " AND goods_name LIKE '%" + goodsName + "%'";
        }
        sql +=  " ORDER BY goods_index ASC;";
        return commonMapper.selectListMap(sql);
    }

    /**
     * 查询测试商品类型列表
     * @param appletId
     * @param userId
     * @param typeName
     * @return
     */
    public List<Map> selectGoodsTypeList(Integer appletId, Integer userId, String typeName) {
        String sql = "SELECT id,type_name AS name,type_logo AS icon FROM goods_type " +
                "WHERE applet_id = " + appletId + " AND user_id = " + userId + " AND type_status = 1";
        if (NullUtil.isNotNullOrEmpty(typeName)){
            sql +=  " AND type_name LIKE '%" + typeName + "%'";
        }
        sql +=  " ORDER BY type_index ASC;";
        return commonMapper.selectListMap(sql);
    }

    /**
     * 修改商品或类型信息，更新页面配置内容
     *
     * @param appletId
     * @param userId
     * @param type
     * @param goods
     * @throws Exception
     */
    @Async
    public void updatePageContext(Integer appletId, Integer userId, GoodsType type, ViewGoodsInfo goods) {
        try {
            List<ViewAppletPageContent> contentList = selectAppletPageContent(userId, appletId);
            for (ViewAppletPageContent content : contentList) {
                if (NullUtil.isNotNullOrEmpty(content.getContentJson())) {
                    boolean bool = false;
                    String json = "{\"contentList\":" + content.getContentJson() + "}";
                    JSONObject object = JSONObject.fromObject(json);
                    Map<String, Class> map = new HashMap<>();
                    map.put("contentList", ContentInfo.class);
                    map.put("list", ElementInfo.class);
                    PageContent record = (PageContent) JSONObject.toBean(object, PageContent.class, map);
                    List<ContentInfo> list = record.getContentList();
                    for (int k = 0; k < list.size(); k++) {
                        if (NullUtil.isNotNullOrEmpty(list.get(k).getList())) {
                            List<ElementInfo> list1 = list.get(k).getList();
                            for (int i = 0; i < list1.size(); i++) {
                                ElementInfo note = list1.get(i);
                                if (null != goods && NullUtil.isNotNullOrEmpty(note.getGoodsId()) && goods.getId().intValue() == note.getGoodsId().intValue()) {
                                    record.getContentList().get(k).getList().get(i).setName(goods.getGoodsName());
                                    record.getContentList().get(k).getList().get(i).setMinPrice(goods.getMinPrice());
                                    record.getContentList().get(k).getList().get(i).setMaxPrice(goods.getMaxPrice());
                                    record.getContentList().get(k).getList().get(i).setDiscount(goods.getDiscount());
                                    bool = true;
                                } else if (null != type && NullUtil.isNotNullOrEmpty(note.getTypeId()) && type.getId().intValue() == note.getTypeId().intValue()) {
                                    record.getContentList().get(k).getList().get(i).setName(type.getTypeName());
                                    bool = true;
                                }
                                continue;
                            }
                        }
                    }

                    if (bool) {
                        object = JSONObject.fromObject(record);
                        json = object.toString();
                        json = json.substring(15, json.length());
                        json = json.substring(0, json.length() - 1);
                        AppletPageContent content1 = new AppletPageContent();
                        content1.setId(content.getId());
                        content1.setContentJson(json);
                        content1.setUpdateTime(new Date());
                        appletPageContentMapper.updateByPrimaryKeySelective(content1);
                    }
                }
            }
        } catch (Exception e) {
            log.error("修改商品或类型信息，更新页面配置内容出错{}", e);
        }
    }
}
