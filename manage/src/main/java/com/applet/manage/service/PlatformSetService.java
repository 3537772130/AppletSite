package com.applet.manage.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.AppletAdvertRelationMapper;
import com.applet.common.mapper.CommonMapper;
import com.applet.common.mapper.SystemNoticeMapper;
import com.applet.common.mapper.ViewAppletAdvertRelationMapper;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/3/31
 * Time: 21:58
 * To change this template use File | Settings | File Templates.
 * Description: 平台设置服务类
 */
@SuppressWarnings("ALL")
@Service
public class PlatformSetService {
    @Autowired
    private SystemNoticeMapper systemNoticeMapper;
    @Autowired
    private AppletAdvertRelationMapper appletAdvertRelationMapper;
    @Autowired
    private ViewAppletAdvertRelationMapper viewAppletAdvertRelationMapper;
    @Autowired
    private CommonMapper commonMapper;

    /**
     * 分页系统通知消息
     *
     * @param title
     * @param type
     * @param status
     * @param page
     * @return
     */
    public Page selectSystemNoticeByPage(String title, Integer type, Integer status, Page page) {
        SystemNoticeExample example = new SystemNoticeExample();
        example.setPage(page);
        example.setOrderByClause("create_time desc");
        SystemNoticeExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(title)) {
            c.andNoticeTitleLike("%" + title + "%");
        }
        if (NullUtil.isNotNullOrEmpty(type)) {
            c.andNoticeTypeEqualTo(type);
        }
        if (NullUtil.isNotNullOrEmpty(status)) {
            c.andNoticeStatusEqualTo(status.intValue() == 1);
        }
        long count = systemNoticeMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(systemNoticeMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询系统通知消息详情
     * @param id
     * @return
     */
    public SystemNotice selectSystemNoticeinfo(Integer id) {
        return systemNoticeMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新系统通信消息
     *
     * @param notice
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSystemNotice(SystemNotice notice) {
        notice.setCreateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(notice.getId())) {
            notice.setNoticeStatus(false);
            systemNoticeMapper.updateByPrimaryKeySelective(notice);
        } else {
            systemNoticeMapper.insertSelective(notice);
            if (notice.getNoticeType().intValue() == Constants.SYSTEM_NOTICE_TYPE_PLAIN.intValue()) {
                // 添加普通推送通知，则为全平台用户添加一条未读消息提醒（批量插入）
                String sql = "INSERT INTO user_remind_record (relation_id,relation_user_id,relation_type,relation_time,relation_status) " +
                        "SELECT " + notice.getId() + " as relation_id,u.id as relation_user_id," +
                        Constants.RELATION_TYPE_SYSTEM + " as relation_type," +
                        "current_timestamp() as relation_time, true as relation_status  " +
                        "FROM user_info as u ORDER BY u.id asc";
                commonMapper.insertBatch(sql);
            } else if (notice.getNoticeType().intValue() == Constants.SYSTEM_NOTICE_TYPE_PUBLIC.intValue()) {
                // 系统公告同时只能存在一条有效消息
                SystemNoticeExample example = new SystemNoticeExample();
                example.createCriteria().andIdNotEqualTo(notice.getId())
                        .andNoticeTypeEqualTo(Constants.SYSTEM_NOTICE_TYPE_PUBLIC)
                        .andNoticeStatusEqualTo(true);
                SystemNotice newNotice = new SystemNotice();
//                newNotice.setCreateTime(new Date());
                newNotice.setNoticeStatus(false);
                systemNoticeMapper.updateByExampleSelective(newNotice, example);
            }
        }
    }

    /**
     * 分页查询封面广告关联信息
     * @param relationWebsite
     * @param relationType
     * @param relationName
     * @param relationStatus
     * @param page
     * @return
     */
    public Page selectAppletAdvertRelationByPage(AppletAdvertRelation relation, Page page){
        // 异步更新小程序推广关联状态
        updateAppletAdvertRelationStatus();

        ViewAppletAdvertRelationExample example = new ViewAppletAdvertRelationExample();
        example.setPage(page);
        example.setOrderByClause("expire_time desc");
        ViewAppletAdvertRelationExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(relation.getAppletTypeId())){
            c.andAppletTypeIdEqualTo(relation.getAppletTypeId());
        }
        if (NullUtil.isNotNullOrEmpty(relation.getPageLogo())){
            c.andPageLogoEqualTo(relation.getPageLogo());
        }
        if (NullUtil.isNotNullOrEmpty(relation.getRelationWebsite())){
            c.andRelationWebsiteLike("%" + relation.getRelationWebsite() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(relation.getRelationType())){
            c.andRelationTypeEqualTo(relation.getRelationType());
        }
        if (NullUtil.isNotNullOrEmpty(relation.getRelationName())){
            c.andRelationNameLike("%"+ relation.getRelationName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(relation.getRelationStatus())){
            c.andRelationStatusEqualTo(relation.getRelationStatus());
        }
        long count = viewAppletAdvertRelationMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewAppletAdvertRelationMapper.selectByExample(example));
        }
        return page;
    }

    @Async
    private void updateAppletAdvertRelationStatus(){
        AppletAdvertRelation relation = new AppletAdvertRelation();
        relation.setRelationStatus(false);
        AppletAdvertRelationExample example = new AppletAdvertRelationExample();
        example.createCriteria().andExpireTimeLessThan(new Date()).andRelationStatusEqualTo(true);
        appletAdvertRelationMapper.updateByExampleSelective(relation, example);
    }

    /**
     * 查询封面广告关联信息
     * @param id
     * @return
     */
    public AppletAdvertRelation selectAppletAdvertRelationById(Integer id){
        return appletAdvertRelationMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询广告位置已安排到的截止日期
     * @param appletTypeId
     * @param pageLogo
     * @return
     */
    public Date selectAppletAdvertRelationByLastExpireTime(Integer appletTypeId, String pageLogo){
        Page page = new Page(0, 1);
        AppletAdvertRelationExample example = new AppletAdvertRelationExample();
        example.setPage(page);
        example.setOrderByClause("expire_time desc");
        example.createCriteria()
                .andAppletTypeIdEqualTo(appletTypeId)
                .andPageLogoEqualTo(pageLogo)
                .andRelationStatusEqualTo(true);
        List<AppletAdvertRelation> list = appletAdvertRelationMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            return list.get(0).getExpireTime();
        }
        return null;
    }

    /**
     * 校验是否存在时间冲突的记录
     * @param appletTypeId
     * @param pageLogo
     * @param startTime
     * @return
     */
    public Boolean checkAppletAdvertRelation(Integer appletTypeId, String pageLogo, Date startTime){
        AppletAdvertRelationExample example = new AppletAdvertRelationExample();
        example.createCriteria()
                .andAppletTypeIdEqualTo(appletTypeId)
                .andPageLogoEqualTo(pageLogo)
                .andExpireTimeGreaterThan(startTime)
                .andRelationStatusEqualTo(true);
        return appletAdvertRelationMapper.countByExample(example) > 0;
    }

    /**
     * 更新封面广告关联信息
     * @param relation
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateAppletAdvertRelation(AppletAdvertRelation relation){
        relation.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(relation.getId())){
            appletAdvertRelationMapper.updateByPrimaryKeySelective(relation);
        } else {
            appletAdvertRelationMapper.insertSelective(relation);
        }
        if (relation.getRelationType().intValue() == 1 && relation.getIsDefault() && relation.getRelationStatus()){
            // 内部推广默认项只允许在同类型小程序中存在一条有效记录，在其他记录失效、过期后将使用此条记录
            AppletAdvertRelationExample example = new AppletAdvertRelationExample();
            example.createCriteria().andIdNotEqualTo(relation.getId()).andIsDefaultEqualTo(true).andRelationStatusEqualTo(true);
            AppletAdvertRelation record = new AppletAdvertRelation();
            record.setUpdateTime(new Date());
            record.setIsDefault(false);
            appletAdvertRelationMapper.updateByExample(record, example);
        }
    }
}
