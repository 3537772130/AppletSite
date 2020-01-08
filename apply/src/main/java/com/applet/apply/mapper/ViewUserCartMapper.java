package com.applet.apply.mapper;

import com.applet.apply.entity.ViewUserCart;

import java.util.List;

/**
 * 用户购物车
 *
 * @author liangzhong.tan
 * @date 2019-12-31 10:11:00
 */
public interface ViewUserCartMapper {

    List<ViewUserCart> findByIds(List<Integer> ids);

}
