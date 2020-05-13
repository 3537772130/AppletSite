package com.applet.user.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 商品服务类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-16 17:51
 **/
@SuppressWarnings("ALL")
@Service
public class UserGoodsService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private GoodsFileMapper goodsFileMapper;
    @Autowired
    private GoodsSpecsMapper goodsSpecsMapper;
    @Autowired
    private ViewGoodsTypeMapper viewGoodsTypeMapper;
    @Autowired
    private ViewGoodsInfoMapper viewGoodsInfoMapper;
    @Autowired
    private ViewGoodsFileMapper viewGoodsFileMapper;
    @Autowired
    private ViewGoodsSpecsMapper viewGoodsSpecsMapper;
    @Autowired
    private UserAppletRecommendGoodsMapper userAppletRecommendGoodsMapper;
    @Autowired
    private ViewUserAppletRecommendGoodsMapper viewUserAppletRecommendGoodsMapper;
    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private AppletPageService appletPageService;

    /**
     * 分页查询商品类型
     *
     * @param appletId
     * @param userId
     * @param name
     * @param page
     * @return
     */
    public Page selectTypePage(Integer appletId, Integer userId, String name, Integer status, Page page) {
        ViewGoodsTypeExample example = new ViewGoodsTypeExample();
        example.setPage(page);
        example.setOrderByClause("type_index asc");
        ViewGoodsTypeExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(appletId)) {
            c.andAppletIdEqualTo(appletId);
        }
        if (NullUtil.isNotNullOrEmpty(name)) {
            c.andTypeNameLike("%" + name + "%");
        }
        if (NullUtil.isNotNullOrEmpty(status)) {
            c.andTypeStatusEqualTo(status.intValue() == 1);
        }
        c.andUserIdEqualTo(userId);
        long count = viewGoodsTypeMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewGoodsTypeMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询商品类型总数
     *
     * @param userId
     * @param appletId
     * @return
     */
    public int selectGoodsTypeCount(Integer userId, Integer appletId) {
        GoodsTypeExample example = new GoodsTypeExample();
        example.createCriteria().andUserIdEqualTo(userId).andAppletIdEqualTo(appletId);
        return (int) goodsTypeMapper.countByExample(example);
    }

    /**
     * 查询商品类型信息
     *
     * @param id
     * @param appletId
     * @param userId
     * @return
     */
    public GoodsType selectGoodsType(Integer id, Integer appletId, Integer userId) {
        GoodsTypeExample example = new GoodsTypeExample();
        GoodsTypeExample.Criteria c = example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
        if (NullUtil.isNotNullOrEmpty(appletId)) {
            c.andAppletIdEqualTo(appletId);
        }
        List<GoodsType> list = goodsTypeMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    public GoodsType selectGoodsType(Integer id, Integer userId) {
        return selectGoodsType(id, null, userId);
    }

    /**
     * 更新商品类型信息
     *
     * @param record
     */
    public void updateGoodsType(GoodsType record) {
        if (NullUtil.isNotNullOrEmpty(record.getId())) {
            goodsTypeMapper.updateByPrimaryKeySelective(record);
        } else {
            goodsTypeMapper.insertSelective(record);
        }

        if (record.getTypeStatus()) {
            // 检查更新小程序页面配置信息
            appletPageService.updatePageContext(record.getAppletId(), record.getUserId(), record, null);
        }
    }

    /**
     * 更新商品类型排序
     *
     * @param type
     * @param num
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateGoodsTypeIndex(GoodsType type, Integer num) {
        GoodsType record1 = new GoodsType();
        record1.setTypeIndex(type.getTypeIndex());
        GoodsTypeExample example = new GoodsTypeExample();
        example.createCriteria().andUserIdEqualTo(type.getUserId()).andTypeIndexEqualTo(type.getTypeIndex() + num);
        goodsTypeMapper.updateByExampleSelective(record1, example);

        GoodsType record2 = new GoodsType();
        record2.setId(type.getId());
        record2.setTypeIndex(type.getTypeIndex() + num);
        goodsTypeMapper.updateByPrimaryKeySelective(record2);
    }

    /**
     * 查询正常的商品类型集合
     *
     * @param appletId
     * @param userId
     * @return
     */
    public List<GoodsType> selectTypeList(Integer appletId, Integer userId) {
        GoodsTypeExample example = new GoodsTypeExample();
        example.setOrderByClause("type_index asc");
        GoodsTypeExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(appletId)) {
            c.andAppletIdEqualTo(appletId);
        }
        c.andUserIdEqualTo(userId).andTypeStatusEqualTo(true);
        return goodsTypeMapper.selectByExample(example);
    }

    public List<GoodsType> selectTypeList(Integer userId) {
        return selectTypeList(null, userId);
    }

    /**
     * 分页查询商品
     *
     * @param goods
     * @param page
     * @return
     */
    public Page selectInfoPage(ViewGoodsInfo goods, Page page) {
        ViewGoodsInfoExample example = new ViewGoodsInfoExample();
        example.setPage(page);
        example.setOrderByClause("goods_index asc");
        ViewGoodsInfoExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(goods.getGoodsName())) {
            c.andGoodsNameLike("%" + goods.getGoodsName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(goods.getTypeId())) {
            c.andTypeIdEqualTo(goods.getTypeId());
        }
        if (NullUtil.isNotNullOrEmpty(goods.getGoodsStatus())) {
            c.andGoodsStatusEqualTo(goods.getGoodsStatus());
        }
        if (NullUtil.isNotNullOrEmpty(goods.getIfDiscount())) {
            c.andIfDiscountEqualTo(goods.getIfDiscount());
        }
        c.andUserIdEqualTo(goods.getUserId());
        long count = viewGoodsInfoMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewGoodsInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询商品信息
     *
     * @param id
     * @param userId
     * @return
     */
    public ViewGoodsInfo selectViewGoodsInfo(Integer id, Integer userId) {
        ViewGoodsInfoExample example = new ViewGoodsInfoExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
        List<ViewGoodsInfo> list = viewGoodsInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询商品信息
     *
     * @param id
     * @param userId
     * @return
     */
    public GoodsInfo selectGoodsInfo(Integer id, Integer typeId, Integer userId) {
        GoodsInfoExample example = new GoodsInfoExample();
        GoodsInfoExample.Criteria c = example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
        if (NullUtil.isNotNullOrEmpty(typeId)) {
            c.andTypeIdEqualTo(typeId);
        }
        List<GoodsInfo> list = goodsInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询商品信息
     *
     * @param id
     * @param userId
     * @return
     */
    public GoodsInfo selectGoodsInfo(Integer id, Integer userId) {
        return this.selectGoodsInfo(id, null, userId);
    }

    /**
     * 查询商品数量
     *
     * @param typeId
     * @param userId
     * @return
     */
    public int selectGoodsCount(Integer typeId, Integer userId) {
        GoodsInfoExample example = new GoodsInfoExample();
        example.createCriteria().andTypeIdEqualTo(typeId).andUserIdEqualTo(userId).andStatusNotEqualTo(-1);
        return (int) goodsInfoMapper.countByExample(example);
    }

    /**
     * 更新商品信息
     *
     * @param record
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateGoodsInfo(GoodsInfo record) throws SQLIntegrityConstraintViolationException {
        record.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(record.getId())) {
            record.setGoodsName(null);
            goodsInfoMapper.updateByPrimaryKeySelective(record);
        } else {
            int count = this.selectGoodsCount(record.getTypeId(), record.getUserId());
            record.setGoodsIndex(count);
            record.setStatus(0);
            try {
                goodsInfoMapper.insertSelective(record);
            } catch (Exception e) {
                throw new SQLIntegrityConstraintViolationException();
            }
            // 设置商品文件(每个商品只允许有1个视频文件，5个图片文件)
            GoodsFile file = new GoodsFile();
            file.setGoodsId(record.getId());
            file.setFileType(2);
            file.setFileStatus(false);
            goodsFileMapper.insertSelective(file);
            for (int i = 0; i < 5; i++) {
                file = new GoodsFile();
                file.setGoodsId(record.getId());
                file.setFileType(1);
                file.setFileStatus(false);
                goodsFileMapper.insertSelective(file);
            }
        }
    }

    /**
     * 更新商品排序
     *
     * @param goods
     * @param num
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateGoodsIndex(GoodsInfo goods, Integer num) {
        GoodsInfo record1 = new GoodsInfo();
        record1.setGoodsIndex(goods.getGoodsIndex());
        GoodsInfoExample example = new GoodsInfoExample();
        example.createCriteria().andUserIdEqualTo(goods.getUserId()).andTypeIdEqualTo(goods.getTypeId())
                .andGoodsIndexEqualTo(goods.getGoodsIndex() + num);
        goodsInfoMapper.updateByExampleSelective(record1, example);

        GoodsInfo record2 = new GoodsInfo();
        record2.setId(goods.getId());
        record2.setGoodsIndex(goods.getGoodsIndex() + num);
        goodsInfoMapper.updateByPrimaryKeySelective(record2);
    }

    /**
     * 更新商品状态
     *
     * @param goods
     */
    public void updateGoodsStatus(ViewGoodsInfo goods) {
        GoodsInfo record = new GoodsInfo();
        record.setId(goods.getId());
        record.setStatus(goods.getGoodsStatus() == 1 ? 0 : 1);
        goodsInfoMapper.updateByPrimaryKeySelective(record);
        if (goods.getGoodsStatus() == 0) {
            // 检查更新小程序页面配置信息
            appletPageService.updatePageContext(goods.getAppletId(), goods.getUserId(), null, goods);
        }
    }

    /**
     * 删除商品信息
     *
     * @param goodsId
     */
    public void updateGoodsStatus(Integer goodsId) {
        GoodsInfo record = new GoodsInfo();
        record.setId(goodsId);
        record.setStatus(-1);
        goodsInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 查询用户商品所有文件
     *
     * @param goodsId
     * @return
     */
    public List<ViewGoodsFile> selectFileList(Integer goodsId, Integer userId, Integer type) {
        ViewGoodsFileExample example = new ViewGoodsFileExample();
        example.setOrderByClause("id asc");
        ViewGoodsFileExample.Criteria c = example.createCriteria().andGoodsIdEqualTo(goodsId).andUserIdEqualTo(userId);
        if (NullUtil.isNotNullOrEmpty(type)) {
            c.andFileTypeEqualTo(type);
        }
        return viewGoodsFileMapper.selectByExample(example);
    }

    /**
     * 查询用户商品文件状态正常数量
     *
     * @param goodsId
     * @param userId
     * @return
     */
    public int selectFileCount(Integer goodsId, Integer userId) {
        ViewGoodsFileExample example = new ViewGoodsFileExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId).andUserIdEqualTo(userId).andFileStatusEqualTo(true);
        return (int) viewGoodsFileMapper.countByExample(example);
    }

    /**
     * 查询商品文件信息
     *
     * @param fileId
     * @param goodsId
     * @param userId
     * @return
     */
    public ViewGoodsFile selectFileInfo(Integer fileId, Integer goodsId, Integer userId) {
        ViewGoodsFileExample example = new ViewGoodsFileExample();
        ViewGoodsFileExample.Criteria c = example.createCriteria().andIdEqualTo(fileId).andUserIdEqualTo(userId);
        if (NullUtil.isNotNullOrEmpty(goodsId)) {
            c.andGoodsIdEqualTo(goodsId);
        }
        List<ViewGoodsFile> list = viewGoodsFileMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    public ViewGoodsFile selectFileInfo(Integer fileId, Integer userId) {
        return selectFileInfo(fileId, null, userId);
    }

    /**
     * 修改视频文件信息
     *
     * @param id
     * @param src
     * @param status
     */
    public void updateGoodsFile(Integer id, String src, Boolean status) {
        GoodsFile file = new GoodsFile();
        file.setId(id);
        file.setFileSrc(src);
        file.setFileStatus(status);
        goodsFileMapper.updateByPrimaryKeySelective(file);
    }

    /**
     * 查询用户商品所有规格
     *
     * @param specs
     * @return
     */
    public Page selectSpecsList(ViewGoodsSpecs specs, Page page) {
        ViewGoodsSpecsExample example = new ViewGoodsSpecsExample();
        example.setPage(page);
        example.setOrderByClause("specs_index asc");
        example.createCriteria().andGoodsIdEqualTo(specs.getGoodsId()).andUserIdEqualTo(specs.getUserId());
        long count = viewGoodsSpecsMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewGoodsSpecsMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询用户商品规格详情
     *
     * @param id
     * @param goodsId
     * @param userId
     * @return
     */
    public ViewGoodsSpecs selectSpecsInfo(Integer id, Integer goodsId, Integer userId) {
        ViewGoodsSpecsExample example = new ViewGoodsSpecsExample();
        example.createCriteria().andIdEqualTo(id).andGoodsIdEqualTo(goodsId).andUserIdEqualTo(userId);
        List<ViewGoodsSpecs> list = viewGoodsSpecsMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }


    /**
     * 更新商品规格信息
     *
     * @param specs
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateGoodsSpecs(GoodsSpecs specs, Integer userId) {
        if (NullUtil.isNullOrEmpty(specs.getId())) {
            int count = this.selectGoodsSpecsCount(specs.getGoodsId(), userId);
            specs.setSpecsIndex(count + 1);
            goodsSpecsMapper.insertSelective(specs);
        } else {
            goodsSpecsMapper.updateByPrimaryKeySelective(specs);
        }
    }

    /**
     * 更新商品规格排序
     *
     * @param specs
     * @param num
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateGoodsSpecsIndex(ViewGoodsSpecs specs, Integer num) {
        GoodsSpecs record1 = new GoodsSpecs();
        record1.setSpecsIndex(specs.getSpecsIndex());
        GoodsSpecsExample example = new GoodsSpecsExample();
        example.createCriteria().andGoodsIdEqualTo(specs.getGoodsId())
                .andSpecsIndexEqualTo(specs.getSpecsIndex() + num);
        goodsSpecsMapper.updateByExampleSelective(record1, example);

        GoodsSpecs record2 = new GoodsSpecs();
        record2.setId(specs.getId());
        record2.setSpecsIndex(specs.getSpecsIndex() + num);
        goodsSpecsMapper.updateByPrimaryKeySelective(record2);
    }

    /**
     * 查询用户商品规格状态正常数量
     *
     * @param goodsId
     * @param userId
     * @return
     */
    public int selectSpecsCount(Integer goodsId, Integer userId) {
        ViewGoodsSpecsExample example = new ViewGoodsSpecsExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId).andUserIdEqualTo(userId).andSpecsStatusEqualTo(true);
        return (int) viewGoodsSpecsMapper.countByExample(example);
    }

    /**
     * 查询用户商品规格数量
     *
     * @param goodsId
     * @param userId
     * @return
     */
    public int selectGoodsSpecsCount(Integer goodsId, Integer userId) {
        ViewGoodsSpecsExample example = new ViewGoodsSpecsExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId).andUserIdEqualTo(userId);
        return (int) viewGoodsSpecsMapper.countByExample(example);
    }

    /**
     * 检查商品信息 更新商品信息
     *
     * @param goodsId
     * @param userId
     */
    @Async("taskExecutor")
    @Transactional(rollbackFor = Exception.class)
    public void checkGoodsValue(Integer goodsId, Integer userId, boolean ifPrice) {
        GoodsInfo goods = new GoodsInfo();
        goods.setId(goodsId);
        if (ifPrice) {
            String sql = "SELECT goods_id,min(sell_price) as mix_price, max(sell_price) as max_price " +
                    "FROM goods_specs " +
                    "WHERE goods_id = " + goodsId + " " +
                    "GROUP BY goods_id";
            Map map = commonMapper.selectSingleLine(sql);
            goods.setMinPrice(Double.parseDouble(map.get("mix_price").toString()));
            goods.setMaxPrice(Double.parseDouble(map.get("max_price").toString()));
        }

        int fileCount = this.selectFileCount(goodsId, userId);
        int specsCount = this.selectSpecsCount(goodsId, userId);
        if (fileCount <= 0 || specsCount <= 0) {
            goods.setStatus(0);
        }
        goodsInfoMapper.updateByPrimaryKeySelective(goods);

//        ViewGoodsInfo goodsInfo = selectViewGoodsInfo(goodsId, userId);
//        appletPageService.updatePageContext(goodsInfo.getAppletId(), goodsInfo.getUserId(), null, goodsInfo);
    }

    /**
     * 分页查询用户小程序推荐商品
     *
     * @param appletId
     * @param goodsName
     * @param page
     * @return
     */
    public Page selectUserAppletRecommendGoodsByPage(ViewUserAppletRecommendGoods rg, Page page) {
        // 异步更新推荐商品状态
        updateupdateUserAppletRecommendGoodsStatus();

        ViewUserAppletRecommendGoodsExample example = new ViewUserAppletRecommendGoodsExample();
        example.setPage(page);
        example.setOrderByClause("update_time desc");
        ViewUserAppletRecommendGoodsExample.Criteria c = example.createCriteria().andUserIdEqualTo(rg.getUserId());
        if (NullUtil.isNotNullOrEmpty(rg.getAppletId())) {
            c.andAppletIdEqualTo(rg.getAppletId());
        }
        if (NullUtil.isNotNullOrEmpty(rg.getGoodsName())) {
            c.andGoodsNameLike("%" + rg.getGoodsName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(rg.getRecommendStatus())) {
            c.andRecommendStatusEqualTo(rg.getRecommendStatus());
        }
        long count = viewUserAppletRecommendGoodsMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewUserAppletRecommendGoodsMapper.selectByExample(example));
        }
        return page;
    }

    @Async("taskExecutor")
    private void updateupdateUserAppletRecommendGoodsStatus() {
        UserAppletRecommendGoods rg = new UserAppletRecommendGoods();
        rg.setRecommendStatus(false);
        UserAppletRecommendGoodsExample example = new UserAppletRecommendGoodsExample();
        example.createCriteria().andExpireTimeLessThan(new Date()).andRecommendStatusEqualTo(true);
        userAppletRecommendGoodsMapper.updateByExampleSelective(rg, example);
    }

    /**
     * 查询小程序推荐商品信息
     *
     * @param id
     * @param userId
     * @return
     */
    public UserAppletRecommendGoods selectUserAppletRecommendGoods(Integer id, Integer userId) {
        UserAppletRecommendGoodsExample example = new UserAppletRecommendGoodsExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
        List<UserAppletRecommendGoods> list = userAppletRecommendGoodsMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新用户小程序推荐商品信息
     *
     * @param rg
     */
    public void updateUserAppletRecommendGoods(UserAppletRecommendGoods rg) {
        rg.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(rg.getId())) {
            userAppletRecommendGoodsMapper.updateByPrimaryKeySelective(rg);
        } else {
            userAppletRecommendGoodsMapper.insertSelective(rg);
        }
    }

    /**
     * 宣传小程序分类列表 - 排序
     *
     * @param userId
     * @param appletId
     * @return
     */
    public List<Map> selectTypeDraggableList(Integer userId, Integer appletId) {
        String sql = "SELECT t.id AS id,t.type_logo AS typeLogo,t.type_name AS typeName" +
                " FROM goods_type AS t" +
                " WHERE t.user_id = " + userId +
                " AND t.applet_id = " + appletId;
        return commonMapper.selectListMap(sql);
    }

    /**
     * 批量更新商品分类 - 排序
     *
     * @param userId
     * @param appletId
     * @param list
     */
    public void updateGoodsTypeIndexs(Integer userId, Integer appletId, List<GoodsType> list) {
        String sql = "UPDATE goods_type" +
                " SET type_index = CASE id";
        String idStr = "";
        for (int i = 0; i < list.size(); i++) {
            sql += " WHEN " + list.get(i).getId() + " THEN " + i;
            idStr += list.get(i).getId() + ",";
        }
        idStr = idStr.substring(0, idStr.length() - 1);
        sql += " END WHERE id IN (" + idStr + ") AND applet_id = " + appletId + " AND user_id = " + userId;
        commonMapper.updateBatch(sql);
    }

    /**
     * 查询小程序分类下的商品列表 - 排序
     *
     * @param userId
     * @param appletId
     * @param typeId
     * @return
     */
    public List<Map> selectGoodsDraggableList(Integer userId, Integer appletId, Integer typeId) {
        String sql = "SELECT" +
                " g.id as id,g.cover_src as coverSrc,g.goods_name as goodsName" +
                " FROM" +
                " view_goods_info AS g" +
                " WHERE" +
                " g.user_id = " + userId +
                " AND g.applet_id = " + appletId +
                " AND g.type_id = " + typeId +
                " ORDER BY g.goods_index ASC";
        return commonMapper.selectListMap(sql);
    }

    /**
     * 批量更新商品 - 排序
     *
     * @param userId
     * @param typeId
     * @param list
     */
    public void updateGoodsIndexs(Integer userId, Integer typeId, List<GoodsInfo> list) {
        String sql = "UPDATE goods_info" +
                " SET goods_index = CASE id";
        String idStr = "";
        for (int i = 0; i < list.size(); i++) {
            sql += " WHEN " + list.get(i).getId() + " THEN " + i;
            idStr += list.get(i).getId() + ",";
        }
        idStr = idStr.substring(0, idStr.length() - 1);
        sql += " END WHERE id IN (" + idStr + ") AND type_id = " + typeId + " AND user_id = " + userId;
        commonMapper.updateBatch(sql);
    }
}
