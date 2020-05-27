package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.entity.page.GoodsClassify;
import com.applet.common.mapper.AppletAdvertRelationMapper;
import com.applet.common.mapper.UserAppletRecommendGoodsMapper;
import com.applet.common.mapper.ViewAppletPageContentMapper;
import com.applet.common.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/10
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 * Description: 小程序页面服务类
 */
@SuppressWarnings("ALL")
@Service
public class AppletPageService {
    @Autowired
    private ViewAppletPageContentMapper viewAppletPageContentMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private UserAppletRecommendGoodsMapper userAppletRecommendGoodsMapper;
    @Autowired
    private AppletAdvertRelationMapper appletAdvertRelationMapper;

    /**
     * 查询小程序页面配置信息
     *
     * @param appletId
     * @param pageLogo
     * @return
     */
    public ViewAppletPageContent selectViewAppletPageContent(Integer appletId, String pageLogo) {
        ViewAppletPageContentExample example = new ViewAppletPageContentExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andPageLogoEqualTo(pageLogo);
        List<ViewAppletPageContent> list = viewAppletPageContentMapper.selectByExampleWithBLOBs(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 加载商品分类界面信息
     *
     * @param appletId
     * @param appletCode
     */
    public GoodsClassify selectGoodsClassify(Integer appletId) {
        List<ViewGoodsType> typeList = goodsService.selectGoodsTypeList(appletId);
        GoodsClassify gc = null;
        if (NullUtil.isNotNullOrEmpty(typeList)){
            List<Integer> typeIdList = new ArrayList<>();
            for (ViewGoodsType type : typeList) {
                typeIdList.add(type.getId());
            }
            gc = new GoodsClassify();
            gc.setTypeList(typeList);
            gc.setInfoList(goodsService.selectGoodsInfoList(appletId, typeIdList));
            gc.setCouponList(userCouponService.selectCouponList(appletId));
        }
        return gc;
    }

    /**
     * 查询页面推广信息
     *
     * @param appletType
     * @param pageLogo
     * @return
     */
    public List<AppletAdvertRelation> selectAppletAdvertRelationByPage(Integer appletType, String pageLogo) {
        AppletAdvertRelationExample example = new AppletAdvertRelationExample();
        AppletAdvertRelationExample.Criteria c = example.createCriteria()
                .andAppletTypeIdEqualTo(appletType)
                .andPageLogoEqualTo(pageLogo)
                .andStartTimeLessThanOrEqualTo(new Date())
                .andExpireTimeGreaterThanOrEqualTo(new Date())
                .andRelationStatusEqualTo(true);
        List<AppletAdvertRelation> list = appletAdvertRelationMapper.selectByExample(example);
        if (NullUtil.isNullOrEmpty(list)) {
            example = new AppletAdvertRelationExample();
            c = example.createCriteria()
                    .andAppletTypeIdEqualTo(appletType)
                    .andPageLogoEqualTo(pageLogo)
                    .andIsDefaultEqualTo(true)
                    .andRelationStatusEqualTo(true);
            return appletAdvertRelationMapper.selectByExample(example);
        }
        return list;
    }

    /**
     * 查询小程序推荐商品
     * @param appletId
     * @return
     */
    public List<UserAppletRecommendGoods> selectUserAppletRecommendGoodsList(Integer appletId) {
        UserAppletRecommendGoodsExample example = new UserAppletRecommendGoodsExample();
        example.setOrderByClause("update_time desc");
        example.createCriteria()
                .andAppletIdEqualTo(appletId)
                .andStartTimeLessThanOrEqualTo(new Date())
                .andExpireTimeGreaterThanOrEqualTo(new Date())
                .andRecommendStatusEqualTo(true);
        return userAppletRecommendGoodsMapper.selectByExample(example);
    }
}
