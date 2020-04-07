package com.applet.manage.service;

import com.applet.common.entity.SystemNotice;
import com.applet.common.entity.SystemNoticeExample;
import com.applet.common.mapper.CommonMapper;
import com.applet.common.mapper.SystemNoticeMapper;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
}
