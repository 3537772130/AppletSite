package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import com.applet.common.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@SuppressWarnings("ALL")
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
    private ViewGoodsSellCountMapper viewGoodsSellCountMapper;
    @Autowired
    private CommonMapper commonMapper;

    /**
     * 查询小程序商品类型集合
     *
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
     *
     * @param appletId
     * @param typeIdList
     * @return
     */
    public List<ViewGoodsInfo> selectGoodsInfoList(Integer appletId, List<Integer> typeIdList) {
        ViewGoodsInfoExample example = new ViewGoodsInfoExample();
        example.setOrderByClause("goods_index asc");
        example.createCriteria().andAppletIdEqualTo(appletId).andTypeIdIn(typeIdList).andGoodsStatusNotEqualTo(-1);
        return viewGoodsInfoMapper.selectByExample(example);
    }

    /**
     * 查询商品信息
     *
     * @param appletId
     * @param goodsId
     * @return
     */
    public ViewGoodsInfo selectGoodsInfo(Integer appletId, Integer goodsId) {
        ViewGoodsInfoExample example = new ViewGoodsInfoExample();
        example.createCriteria().andIdEqualTo(goodsId).andAppletIdEqualTo(appletId);
        List<ViewGoodsInfo> list = viewGoodsInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询商品信息
     *
     * @param appletId
     * @param goodsName
     * @return
     */
    public List<ViewGoodsInfo> selectGoodsList(Integer appletId, String goodsName, Page page) {
        ViewGoodsInfoExample example = new ViewGoodsInfoExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        example.createCriteria()
                .andAppletIdEqualTo(appletId)
                .andGoodsNameLike("%" + goodsName + "%")
                .andGoodsStatusEqualTo(1);
        return viewGoodsInfoMapper.selectByExample(example);
    }

    /**
     * 查询商品文件信息集合
     *
     * @param goodsId
     * @return
     */
    public List<ViewGoodsFile> selectGoodsFileList(Integer goodsId) {
        ViewGoodsFileExample example = new ViewGoodsFileExample();
        example.setOrderByClause("id asc");
        example.createCriteria().andGoodsIdEqualTo(goodsId).andFileSrcIsNotNull();
        return viewGoodsFileMapper.selectByExample(example);
    }

    /**
     * 查询商品规格信息集合
     *
     * @param goodsId
     * @return
     */
    public List<ViewGoodsSpecs> selectGoodsSpecsList(Integer goodsId) {
        ViewGoodsSpecsExample example = new ViewGoodsSpecsExample();
        example.setOrderByClause("specs_index asc");
        example.createCriteria().andGoodsIdEqualTo(goodsId).andSpecsStatusEqualTo(true);
        return viewGoodsSpecsMapper.selectByExample(example);
    }

    /**
     * 查询当前商品外的其他推荐商品
     * 随机抽取1-10个
     * 并虚增出售id个数，最大id + 99
     * @param id       当前商品ID
     * @param name      商品名称
     * @param appletId 小程序ID
     * @param userId   小程序所属用户ID
     * @return
     */
    public List<ViewGoodsSellCount> selectGoodsSellCountList(Integer id, String name, Integer appletId, Integer userId) {
        ViewGoodsSellCountExample example = new ViewGoodsSellCountExample();
        ViewGoodsSellCountExample.Criteria c = example.createCriteria().andAppletIdEqualTo(appletId).andUserIdEqualTo(userId);
        if (NullUtil.isNotNullOrEmpty(id)){
            c.andIdNotEqualTo(id);
        }
        if (NullUtil.isNotNullOrEmpty(name)){
            c.andGoodsNameNotLike("%" + name + "%");
        }
        List<ViewGoodsSellCount> list = viewGoodsSellCountMapper.selectByExample(example);
        List<ViewGoodsSellCount> list1 = new ArrayList<>();
        if (NullUtil.isNotNullOrEmpty(list)) {
            if (list.size() <= 10) {
                for (ViewGoodsSellCount record : list) {
                    record.setSellCount((long) record.getSellCount().intValue() + (record.getId().intValue() > 99 ? record.getId().intValue() - 99 : record.getId().intValue()));
                }
                return list;
            } else {
                int[] indexList = RandomUtil.randomCommon(0, list.size()-1, 10);
                for (int index : indexList) {
                    ViewGoodsSellCount record = list.get(index);
                    record.setSellCount((long) record.getSellCount().intValue() + (record.getId().intValue() > 99 ? record.getId().intValue() - 99 : record.getId().intValue()));
                    list1.add(list.get(index));
                }
            }
        }
        return list1;
    }

    public List<ViewGoodsSellCount> selectGoodsSellCountList(Integer id, Integer appletId, Integer userId){
        return selectGoodsSellCountList(id, null, appletId, userId);
    }

    public List<ViewGoodsSellCount> selectGoodsSellCountList( String name, Integer appletId, Integer userId){
        return selectGoodsSellCountList(null, name, appletId, userId);
    }

    public List<ViewGoodsSellCount> selectGoodsSellCountList( Integer appletId, Integer userId){
        return selectGoodsSellCountList(null, null, appletId, userId);
    }
}
