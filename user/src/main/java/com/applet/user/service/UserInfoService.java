package com.applet.user.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.util.*;
import com.applet.common.util.encryption.EncryptionUtil;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: test  服务类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-14 14:52
 **/
@SuppressWarnings("ALL")
@Slf4j
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Autowired
    private ViewUserLoginLogNewestMapper viewUserLoginLogNewestMapper;
    @Autowired
    private CommonMapper commonMapper;

    /**
     * 查询web用户信息
     *
     * @param mobile
     * @return
     */
    public UserInfo selectUserInfoByMobile(String mobile) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询用户详情
     *
     * @param id
     * @return
     */
    public UserInfo selectUserInfoById(Integer id) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andIdEqualTo(id);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 添加/修改用户信息
     *
     * @param userInfo
     */
    @Transactional(rollbackFor = Exception.class)
    public UserInfo addOrUpdateUserInfo(UserInfo userInfo) {
        if (NullUtil.isNotNullOrEmpty(userInfo.getId())) {
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
        } else {
            userInfo.setCreateDate(new Date());
            userInfoMapper.insertSelective(userInfo);
        }
        return selectUserInfoById(userInfo.getId());
    }

    /**
     * 修改用户密码
     *
     * @param id
     * @param oldPass
     * @param newPass
     * @param request
     * @return
     */
    public Object updateUserInfoByPassword(Integer id, String oldPass, String newPass, HttpServletRequest request) {
        UserInfo userInfo = selectUserInfoById(id);
        String cipher = EncryptionUtil.encryptPasswordMD5(oldPass, userInfo.getEncrypted());
        if (!cipher.equals(userInfo.getPassword())) {
            return AjaxResponse.error("原密码输入错误");
        }
        cipher = EncryptionUtil.encryptPasswordMD5(newPass, userInfo.getEncrypted());
        userInfo.setPassword(cipher);
        addOrUpdateUserInfo(userInfo.getUserInfoToPassword(userInfo));
        request.getSession().removeAttribute(Constants.VUE_USER_INFO);
        return AjaxResponse.success("修改密码成功");
    }

    /**
     * 修改用户头像
     *
     * @param id
     * @param avatarUrl
     * @return
     */
    public String updateUserInfoByAvatarUrl(Integer id, String avatarUrl) {
        UserInfo info = new UserInfo();
        info.setId(id);
        info.setAvatarUrl(avatarUrl);
        userInfoMapper.updateByPrimaryKeySelective(info);
        return info.getAvatarUrl();
    }

    /**
     * 添加用户信息
     *
     * @param userInfo
     */
    public void saveUserInfo(UserInfo userInfo) {
        userInfo.setId(null);
        userInfo.setNickName(userInfo.getNickName().trim());
        userInfo.setEncrypted(RandomUtil.getRandomStr32());
        String cipher = EncryptionUtil.encryptPasswordMD5(userInfo.getPassword(), userInfo.getEncrypted());
        userInfo.setPassword(cipher);
        userInfo.setCreateDate(new Date());
        userInfoMapper.insertSelective(userInfo);
    }

    /**
     * 添加用户登录日志
     * @param id
     * @param request
     */
    @Async("taskExecutor")
    public void saveUserLoginLog(Integer id, String ipAddress, String cityCode) {
        // 查询用户上次登录情况
        ViewUserLoginLogNewestExample example = new ViewUserLoginLogNewestExample();
        example.createCriteria().andUserIdEqualTo(id);
        List<ViewUserLoginLogNewest> list = viewUserLoginLogNewestMapper.selectByExample(example);
        boolean bool = false;
        if (NullUtil.isNotNullOrEmpty(list)) {
            if (list.get(0).getIpAddress().equals(ipAddress)){
                // 与上次登录地址相同，检测是否为俩小时以内记录
                JDateTime time1 = new JDateTime(list.get(0).getLoginTime());
                JDateTime time2 = new JDateTime(new Date());
                // 为当天记录俩小时以内不添加记录，否则添加记录
                time1.addHour(2);
                bool = time2.getTimeInMillis() > time1.getTimeInMillis();
            } else {
                // 与上次登录地址不相同，允许添加记录
                bool = true;
            }
        } else {
            // 不存在登录记录，允许添加记录
            bool = true;
        }
        if (bool) {
            UserLoginLog record = new UserLoginLog();
            record.setUserId(id);
            record.setIpAddress(ipAddress);
            record.setLoginTime(new Date());
            Map map = selectRegionInfoByCityCode(cityCode);
            if (null != map) {
                record.setCountryId("156");
                record.setCountry("中国");
                record.setRegionId(map.get("provinceCode").toString());
                record.setRegion(map.get("province").toString());
                record.setCityId(map.get("cityCode").toString());
                record.setCity(map.get("city").toString());
                userLoginLogMapper.insertSelective(record);
            }
        }
    }

    /**
     * 查询城市信息
     * @param cityCode
     * @return
     */
    public Map selectRegionInfoByCityCode(String cityCode){
        String sql = "SELECT \n" +
                "r1.id as provinceCode,\n" +
                "r1.area_name as province,\n" +
                "r2.id as cityCode,\n" +
                "r2.area_name as city \n" +
                "FROM region_info as r2\n" +
                "INNER JOIN region_info as r1 on r1.id = r2.parent_id\n" +
                "WHERE r2.id = " + cityCode;
        return commonMapper.selectSingleLine(sql);
    }

    /**
     * 分页-查询用户登录记录
     *
     * @param log
     * @param page
     * @return
     */
    public Page selectUserLoginLogToPage(UserLoginLog log, String startDate, String endDate, Page page) {
        UserLoginLogExample example = new UserLoginLogExample();
        example.setPage(page);
        example.setOrderByClause("login_time desc");
        UserLoginLogExample.Criteria c = example.createCriteria();
        c.andUserIdEqualTo(log.getUserId());
        if (NullUtil.isNotNullOrEmpty(log.getRegion())) {
            c.andRegionEqualTo(log.getRegion());
        }
        if (NullUtil.isNotNullOrEmpty(log.getCity())) {
            c.andCityEqualTo(log.getCity());
        }
        if (NullUtil.isNotNullOrEmpty(log.getCounty())) {
            c.andCountyEqualTo(log.getCounty());
        }
        if (NullUtil.isNotNullOrEmpty(log.getArea())) {
            c.andAreaLike("%" + log.getArea() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(log.getIpAddress())) {
            c.andIpAddressLike("%" + log.getIpAddress() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(startDate)) {
            JDateTime time = new JDateTime(startDate);
            time.setHour(0).setMinute(0).setSecond(0);
            c.andLoginTimeGreaterThanOrEqualTo(time.convertToDate());
        }
        if (NullUtil.isNotNullOrEmpty(endDate)) {
            JDateTime time = new JDateTime(endDate);
            time.setHour(23).setMinute(59).setSecond(59);
            c.andLoginTimeLessThanOrEqualTo(time.convertToDate());
        }
        long count = userLoginLogMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(userLoginLogMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询用户近期几个月活跃度情况
     *
     * @param userId
     * @param monthNumber
     * @return
     */
    public List<Map> selectUserActivity(Integer userId, Integer monthNumber) {
        String sql = "SELECT log.user_id,log.login_time,COUNT(*) AS activity FROM ( " +
                "SELECT id,user_id,DATE_FORMAT(login_time,'%Y-%m') AS login_time " +
                "FROM user_login_log " +
                "WHERE user_id = " + userId + " " +
                "ORDER BY login_time ASC " +
                ") AS log " +
                "GROUP BY log.login_time ORDER BY log.login_time DESC LIMIT " + monthNumber;
        return commonMapper.selectListMap(sql);
    }

}
