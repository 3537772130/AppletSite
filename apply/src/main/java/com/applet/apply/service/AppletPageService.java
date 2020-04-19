package com.applet.apply.service;

import com.applet.common.entity.ViewAppletPageContent;
import com.applet.common.entity.ViewAppletPageContentExample;
import com.applet.common.entity.ViewGoodsType;
import com.applet.common.entity.page.GoodsClassify;
import com.applet.common.mapper.ViewAppletPageContentMapper;
import com.applet.common.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private RedisService redisService;

    /**
     * 查询小程序页面配置信息
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
     * 预加载商品分类界面信息
     * @param appletId
     * @param appletCode
     */
    @Async
    public void loadGoodsClassify(Integer appletId, String appletCode){
        List<ViewGoodsType> typeList = goodsService.selectGoodsTypeList(appletId);
        List<Integer> typeIdList = new ArrayList<>();
        for (ViewGoodsType type : typeList) {
            typeIdList.add(type.getId());
        }
        GoodsClassify gc = new GoodsClassify();
        gc.setTypeList(typeList);
        gc.setInfoList(goodsService.selectGoodsInfoList(appletId, typeIdList));
        gc.setCouponList(userCouponService.selectCouponList(appletId));
        redisService.setValue(appletCode + "_CLASSIFY", gc);
    }
}
