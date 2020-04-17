package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/31
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 * Description: 用户购物车服务类
 */
@SuppressWarnings("ALL")
@Service
public class UserCartService {
    @Autowired
    private UserCartMapper userCartMapper;
    @Autowired
    private ViewUserCartMapper viewUserCartMapper;
    @Autowired
    private UserOrderService userOrderService;

    /**
     * 添加购物车信息
     * @param cart
     */
    public void addUserCart(UserCart cart){
        cart.setUpdateTime(new Date());
        cart.setStatus(true);
        if (NullUtil.isNotNullOrEmpty(cart.getId())){
            userCartMapper.updateByPrimaryKeySelective(cart);
        } else {
            userCartMapper.insertSelective(cart);
        }
    }

    /**
     * 查询购物车记录信息
     * @param wxId
     * @param appletId
     * @param id
     * @return
     */
    public ViewUserCart selectUserCartById(Integer wxId, Integer appletId, Integer id){
        ViewUserCartExample example = new ViewUserCartExample();
        example.setOrderByClause("update_time desc");
        example.createCriteria().andWxIdEqualTo(wxId).andAppletIdEqualTo(appletId).andIdEqualTo(id);
        List<ViewUserCart> list = viewUserCartMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 根据商品规格查询购物车信息
     * @param wxId
     * @param appletId
     * @param specsId
     * @return
     */
    public ViewUserCart selectUserCartInfo(Integer wxId, Integer appletId, Integer specsId){
        ViewUserCartExample example = new ViewUserCartExample();
        example.setOrderByClause("update_time desc");
        example.createCriteria().andWxIdEqualTo(wxId).andAppletIdEqualTo(appletId).andSpecsIdEqualTo(specsId);
        List<ViewUserCart> list = viewUserCartMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询购物车信息集合
     * @param wxId
     * @param appletId
     * @return
     */
    public List<ViewUserCart> selectUserCartList(Integer wxId, Integer appletId){
        ViewUserCartExample example = new ViewUserCartExample();
        example.setOrderByClause("update_time desc");
        example.createCriteria().andWxIdEqualTo(wxId).andAppletIdEqualTo(appletId);
        return viewUserCartMapper.selectByExample(example);
    }

    /**
     * 查询购物车信息集合
     * @param wxId
     * @param appletId
     * @return
     */
    public List<ViewUserCart> selectUserCartList(List<Integer> idList, Integer appletId, Integer wxId){
        ViewUserCartExample example = new ViewUserCartExample();
        example.setOrderByClause("update_time desc");
        example.createCriteria().andIdIn(idList).andAppletIdEqualTo(appletId).andWxIdEqualTo(wxId);
        return viewUserCartMapper.selectByExample(example);
    }

    /**
     * 更新购物车商品数量
     * @param id
     * @param amount
     */
    public int updateCartGoodsAmount(Integer id, Integer wxId, Integer appletId, Integer amount){
        UserCartExample example = new UserCartExample();
        example.createCriteria().andIdEqualTo(id).andWxIdEqualTo(wxId).andAppletIdEqualTo(appletId).andStatusEqualTo(true);
        UserCart cart = new UserCart();
        cart.setAmount(amount);
        return userCartMapper.updateByExampleSelective(cart, example);
    }

    /**
     * 更新购物车商品状态
     * @param id
     */
    public int updateCartStatus(Integer id, Integer wxId, Integer appletId){
        UserCartExample example = new UserCartExample();
        example.createCriteria().andIdEqualTo(id).andWxIdEqualTo(wxId).andAppletIdEqualTo(appletId).andStatusEqualTo(true);
        UserCart cart = new UserCart();
        cart.setStatus(false);
        return userCartMapper.updateByExampleSelective(cart, example);
    }

    /**
     * 查询选中的购物车商品信息
     * @param wxId
     * @param appletId
     * @param idList
     * @return
     */
    public List<ViewUserCart> selectUserCartList(Integer wxId, Integer appletId, List<Integer> idList){
        ViewUserCartExample example = new ViewUserCartExample();
        example.setOrderByClause("update_time desc");
        example.createCriteria().andWxIdEqualTo(wxId).andAppletIdEqualTo(appletId).andIdIn(idList);
        return viewUserCartMapper.selectByExample(example);
    }

    /**
     * 更新购物车信息状态
     * @param orderId
     * @param appletId
     * @param wxId
     * @param specsIdList
     */
    public void updateUserCartStatus(Integer orderId, Integer appletId, Integer wxId, List<Integer> specsIdList){
        UserCartExample example = new UserCartExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andWxIdEqualTo(wxId).andSpecsIdIn(specsIdList);
        UserCart record = new UserCart();
        record.setStatus(false);
        userCartMapper.updateByExampleSelective(record, example);
        userOrderService.addOrderSeeRecord(orderId);
    }
}
