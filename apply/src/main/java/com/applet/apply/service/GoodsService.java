package com.applet.apply.service;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.*;
import com.applet.apply.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/12
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class GoodsService {
    @Autowired
    private ViewGoodsInfoMapper viewGoodsInfoMapper;
    @Autowired
    private ViewGoodsTypeMapper viewGoodsTypeMapper;
    @Autowired
    private ViewGoodsFileMapper viewGoodsFileMapper;
    @Autowired
    private ViewGoodsSpecsMapper viewGoodsSpecsMapper;
    @Autowired
    private CommonMapper commonMapper;

    /**
     * 查询小程序商品类型集合
     * @param appletId
     * @return
     */
    public List<ViewGoodsType> selectGoodsTypeList(Integer appletId) {
        ViewGoodsTypeExample example = new ViewGoodsTypeExample();
        example.setOrderByClause("type_index asc");
        example.createCriteria().andAppletIdEqualTo(appletId).andTypeStatusEqualTo(true);
        return viewGoodsTypeMapper.selectByExample(example);
    }

    /**
     * 查询小程序商品集合
     * @param appletId
     * @param typeIdList
     * @return
     */
    public List<ViewGoodsInfo> selectGoodsInfoList(Integer appletId, List<Integer> typeIdList) {
        ViewGoodsInfoExample example = new ViewGoodsInfoExample();
        example.setOrderByClause("goods_index asc");
        example.createCriteria().andAppletIdEqualTo(appletId).andTypeIdIn(typeIdList).andGoodsStatusEqualTo(1);
        return viewGoodsInfoMapper.selectByExample(example);
    }

    /**
     * 查询商品信息
     * @param appletId
     * @param goodsId
     * @return
     */
    public ViewGoodsInfo selectGoodsInfo(Integer appletId, Integer goodsId){
        ViewGoodsInfoExample example = new ViewGoodsInfoExample();
        example.createCriteria().andIdEqualTo(goodsId).andAppletIdEqualTo(appletId).andGoodsStatusEqualTo(1);
        List<ViewGoodsInfo> list = viewGoodsInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询商品文件信息集合
     * @param goodsId
     * @return
     */
    public List<ViewGoodsFile> selectGoodsFileList(Integer goodsId){
        ViewGoodsFileExample example = new ViewGoodsFileExample();
        example.setOrderByClause("id asc");
        example.createCriteria().andGoodsIdEqualTo(goodsId).andFileSrcIsNotNull();
        return viewGoodsFileMapper.selectByExample(example);
    }

    /**
     * 查询商品规格信息集合
     * @param goodsId
     * @return
     */
    public List<ViewGoodsSpecs> selectGoodsSpecsList (Integer goodsId){
        ViewGoodsSpecsExample example = new ViewGoodsSpecsExample();
        example.setOrderByClause("specs_index asc");
        example.createCriteria().andGoodsIdEqualTo(goodsId).andSpecsStatusEqualTo(true);
        return viewGoodsSpecsMapper.selectByExample(example);
    }
}
