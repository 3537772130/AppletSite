package com.applet.user.service;

import com.applet.common.entity.AppletMaterielInfo;
import com.applet.common.entity.AppletMaterielInfoExample;
import com.applet.common.entity.UserGoodsSpreadImage;
import com.applet.common.entity.UserGoodsSpreadImageExample;
import com.applet.common.mapper.AppletMaterielInfoMapper;
import com.applet.common.mapper.CommonMapper;
import com.applet.common.mapper.UserGoodsSpreadImageMapper;
import com.applet.common.util.NullUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/5/7
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@SuppressWarnings("ALL")
@Slf4j
@Service
public class UserSpreadService {
    @Autowired
    private AppletMaterielInfoMapper appletMaterielInfoMapper;
    @Autowired
    private UserGoodsSpreadImageMapper userGoodsSpreadImageMapper;
    @Autowired
    private CommonMapper commonMapper;

    private List<AppletMaterielInfo> selectAppletMaterielList(Integer id, Integer materielType, Integer appletId, Integer userId) {
        AppletMaterielInfoExample example = new AppletMaterielInfoExample();
        example.setOrderByClause("materiel_type asc");
        AppletMaterielInfoExample.Criteria c = example.createCriteria().andUserIdEqualTo(userId).andMaterielStatusEqualTo(true);
        if (NullUtil.isNotNullOrEmpty(id)) {
            c.andIdEqualTo(id);
        }
        if (NullUtil.isNotNullOrEmpty(appletId)) {
            c.andAppletIdEqualTo(appletId);
        }
        if (NullUtil.isNotNullOrEmpty(materielType)) {
            c.andMaterielTypeEqualTo(materielType);
        }
        return appletMaterielInfoMapper.selectByExample(example);
    }

    /**
     * 查询小程序物料集合
     *
     * @param appletId
     * @param userId
     * @return
     */
    public List<AppletMaterielInfo> selectAppletMaterielList(Integer appletId, Integer userId) {
        return selectAppletMaterielList(null, null, appletId, userId);
    }

    /**
     * 查询小程序物料信息
     *
     * @param id
     * @param appletId
     * @param userId
     * @return
     */
    public AppletMaterielInfo selectAppletMaterielInfo(Integer id, Integer appletId, Integer userId) {
        List<AppletMaterielInfo> list = selectAppletMaterielList(id, null, appletId, userId);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询小程序物料信息
     *
     * @param id
     * @param userId
     * @return
     */
    public AppletMaterielInfo selectAppletMaterielInfo(Integer id, Integer userId) {
        List<AppletMaterielInfo> list = selectAppletMaterielList(id, null, null, userId);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    private List<UserGoodsSpreadImage> selectUserGoodsSpreadImageList(Integer id, Integer goodsId, Integer userId) {
        UserGoodsSpreadImageExample example = new UserGoodsSpreadImageExample();
        UserGoodsSpreadImageExample.Criteria c = example.createCriteria()
                .andUserIdEqualTo(userId)
                .andSpreadStatusEqualTo(true);
        if (NullUtil.isNotNullOrEmpty(goodsId)){
            c.andGoodsIdEqualTo(goodsId);
        }
        if (NullUtil.isNotNullOrEmpty(id)) {
            c.andIdEqualTo(id);
        }
        return userGoodsSpreadImageMapper.selectByExample(example);
    }

    /**
     * 查询用户商品推广图片集合
     *
     * @param goodsId
     * @param userId
     * @return
     */
    public List<UserGoodsSpreadImage> selectUserGoodsSpreadImageList(Integer goodsId, Integer userId) {
        return selectUserGoodsSpreadImageList(null, goodsId, userId);
    }

    /**
     * 查询用户商品推广图片
     *
     * @param id
     * @param goodsId
     * @param userId
     * @return
     */
    public UserGoodsSpreadImage selectUserGoodsSpreadImage(Integer id, Integer goodsId, Integer userId) {
        List<UserGoodsSpreadImage> list = selectUserGoodsSpreadImageList(id, goodsId, userId);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    public UserGoodsSpreadImage selectUserGoodsSpreadImage(Integer id, Integer userId) {
        return selectUserGoodsSpreadImage(id, null, userId);
    }

    /**
     * 初始化小程序物料信息
     * @param appletId
     * @param userId
     */
//    @Transactional(rollbackFor = Exception.class)
//    public void saveAppletMaterielInfo(Integer appletId, Integer userId) {
//        String sql = "INSERT INTO applet_materiel_info (user_id, applet_id, materiel_type)\n" +
//                "VALUES(" + userId + ", " + appletId + ", 1)," +
//                "(" + userId + ", " + appletId + ", 2)," +
//                "(" + userId + ", " + appletId + ", 3)," +
//                "(" + userId + ", " + appletId + ", 4)";
//        commonMapper.insertBatch(sql);
//    }

    /**
     * 更新小程序物料信息
     *
     * @param info
     */
    public void saveAppletMaterielInfo(AppletMaterielInfo info) {
        info.setUpdateTime(new Date());
        info.setMaterielStatus(true);
        appletMaterielInfoMapper.insertSelective(info);
    }

    /**
     * 删除小程序物料信息
     *
     * @param id
     * @param userId
     */
    public void deleteAppletMaterielInfo(Integer id, Integer userId) {
        AppletMaterielInfoExample example = new AppletMaterielInfoExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
        AppletMaterielInfo info = new AppletMaterielInfo();
        info.setMaterielStatus(false);
        appletMaterielInfoMapper.updateByExampleSelective(info, example);
    }

    /**
     * 更新商品推广图片信息
     *
     * @param image
     */
    public void updateUserGoodsSpreadImage(UserGoodsSpreadImage image) {
        image.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(image.getId())) {
            userGoodsSpreadImageMapper.updateByPrimaryKeySelective(image);
        } else {
            image.setSpreadStatus(true);
            userGoodsSpreadImageMapper.insertSelective(image);
        }
    }

    /**
     * 删除商品推广图片
     *
     * @param id
     * @param userId
     * @param goodsId
     */
    public void deletaUserGoodsSpreadImage(Integer id, Integer userId, Integer goodsId) {
        UserGoodsSpreadImage image = new UserGoodsSpreadImage();
        image.setSpreadStatus(false);
        UserGoodsSpreadImageExample example = new UserGoodsSpreadImageExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId);
        userGoodsSpreadImageMapper.updateByExampleSelective(image, example);
    }
}
