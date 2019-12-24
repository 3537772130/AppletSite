package com.applet.manage.service;

import com.applet.manage.entity.*;
import com.applet.manage.mapper.*;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import com.applet.common.util.http.IpUtil;
import jodd.datetime.JDateTime;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @program: demo
 * @description: 后台管理员服务类
 * @author: zhouhuahu
 * @create: 2019-08-16 00:45
 **/
@Service
public class ManagerService {

    @Autowired
    private ManagerInfoMapper managerInfoMapper;
    @Autowired
    private ViewManagerInfoMapper viewManagerInfoMapper;
    @Autowired
    private ManagerRoleMapper managerRoleMapper;
    @Autowired
    private ManagerLogMapper managerLogMapper;
    @Autowired
    private ViewManagerLogMapper viewManagerLogMapper;

    /**
     * 查询管理员信息
     *
     * @param id
     * @return
     */
    public ManagerInfo selectManagerInfoById(Integer id) {
        return managerInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询管理员信息
     *
     * @param userName
     * @return
     */
    public ViewManagerInfo selectManagerInfoByUserName(String userName) {
        ViewManagerInfoExample example = new ViewManagerInfoExample();
        example.createCriteria().andUserNameEqualTo(userName);
        List<ViewManagerInfo> list = viewManagerInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询管理员信息（业务主管、业务员）
     *
     * @return
     */
    public List<Map> selectExtensionByMap() {
        ManagerInfoExample example = new ManagerInfoExample();
        List<Integer> roleIdList = new ArrayList<>();
        roleIdList.add(3);
        roleIdList.add(4);
        example.createCriteria().andRoleIdIn(roleIdList).andStatusEqualTo(true);
        List<ManagerInfo> list = managerInfoMapper.selectByExample(example);
        List<Map> list1 = new ArrayList<>();
        for (ManagerInfo info :
                list) {
            Map map = new HashMap();
            map.put("code", info.getExtensionCode());
            String name = info.getNickName();
//            name = name.length() < 3 ? name.substring(0, 1) + " *" : name.substring(0, 1) + " * " + name.substring(name.length() - 1, name.length());
            map.put("name", name);
            list1.add(map);
        }
        return list1;
    }

    /**
     * 查询管理员信息
     *
     * @param id
     * @return
     */
    public ViewManagerInfo selectViewManagerInfoById(Integer id) {
        ViewManagerInfoExample example = new ViewManagerInfoExample();
        example.createCriteria().andIdEqualTo(id);
        List<ViewManagerInfo> list = viewManagerInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * @param roleId
     * @return
     */
    public List<Map> selectManagerInfoByRoleId(Integer roleId) {
        ManagerInfoExample example = new ManagerInfoExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andStatusEqualTo(true);
        List<ManagerInfo> list = managerInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            List<Map> mapList = new ArrayList<>();
            for (ManagerInfo info : list) {
                Map map = new HashMap();
                map.put("id", info.getId());
                map.put("userName", info.getUserName());
                map.put("nickName", info.getNickName());
                mapList.add(map);
            }
            return mapList;
        }
        return null;
    }

    /**
     * 分页查询管理员信息集合
     *
     * @param info
     * @param page
     * @return
     */
    public Page selectManagerInfoToPage(ViewManagerInfo info, Page page) {
        ViewManagerInfoExample example = new ViewManagerInfoExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        ViewManagerInfoExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(info.getUserName())) {
            c.andUserNameEqualTo(info.getUserName());
        }
        if (NullUtil.isNotNullOrEmpty(info.getRoleId())) {
            c.andRoleIdEqualTo(info.getRoleId());
        }
        if (NullUtil.isNotNullOrEmpty(info.getMobile())) {
            c.andMobileLike(info.getMobile() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(info.getQqAccount())) {
            c.andQqAccountLike(info.getQqAccount() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(info.getWeChatAccount())) {
            c.andWeChatAccountLike(info.getWeChatAccount() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(info.getProvince())) {
            c.andProvinceEqualTo(info.getProvince());
        }
        if (NullUtil.isNotNullOrEmpty(info.getCity())) {
            c.andCityEqualTo(info.getCity());
        }
        if (NullUtil.isNotNullOrEmpty(info.getCounty())) {
            c.andCountyEqualTo(info.getCounty());
        }
        if (NullUtil.isNotNullOrEmpty(info.getParentId())) {
            c.andParentIdEqualTo(info.getParentId());
        }
        if (NullUtil.isNotNullOrEmpty(info.getStatus())) {
            c.andStatusEqualTo(info.getStatus());
        }
        c.andIdNotEqualTo(1);
        long count = viewManagerInfoMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewManagerInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 更新管理员信息
     * @param newInfo
     * @param operateId
     * @param request
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateManagerInfo(ManagerInfo newInfo, Integer operateId, HttpServletRequest request) {
        if (NullUtil.isNotNullOrEmpty(newInfo.getId())) {
            // 原管理员信息
            ManagerInfo oldInfo = managerInfoMapper.selectByPrimaryKey(newInfo.getId());
            // 修改后管理员信息
            managerInfoMapper.updateByPrimaryKeySelective(newInfo);
            // 添加修改日志
            ManagerLog log = new ManagerLog();
            log.setManagerId(newInfo.getId());
            log.setAfterJson(JSONObject.fromObject(oldInfo).toString());
            log.setBeforeJson(JSONObject.fromObject(oldInfo).toString());
            log.setOperatorId(operateId);
            log.setOperatorIp(IpUtil.getForIp(request));
            log.setOperateTime(new Date());
            managerLogMapper.insertSelective(log);
        } else {
            newInfo.setCreateDate(new Date());
            if (NullUtil.isNotNullOrEmpty(newInfo.getRoleId()) &&
                    (newInfo.getRoleId().intValue() == 3 || newInfo.getRoleId().intValue() == 4)) {
                JDateTime time = new JDateTime(new Date());
                newInfo.setExtensionCode(time.toString("MMDDhhmmss"));
            }
            managerInfoMapper.insertSelective(newInfo);
        }
    }


    /**
     * 查询管理员角色信息
     *
     * @param id
     * @return
     */
    public ManagerRole selectManagerRoleById(Integer id) {
        return managerRoleMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询角色Map集合
     *
     * @return
     */
    public List<Map> selectRoleToMap() {
        ManagerRoleExample example = new ManagerRoleExample();
        example.setOrderByClause("id asc");
        example.createCriteria().andStatusEqualTo(true);
        List<ManagerRole> list = managerRoleMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            List<Map> mapList = new ArrayList<>();
            for (ManagerRole role : list) {
                Map map = new HashMap();
                map.put("id", role.getId());
                map.put("name", role.getRoleName());
                mapList.add(map);
            }
            return mapList;
        }
        return null;
    }

    /**
     * 查询所有角色信息
     *
     * @return
     */
    public List<ManagerRole> selectManagerRoleList() {
        ManagerRoleExample example = new ManagerRoleExample();
        example.createCriteria().andStatusEqualTo(true);
        return managerRoleMapper.selectByExample(example);
    }

    /**
     * 分页查询管理员角色信息集合
     *
     * @param info
     * @param page
     * @return
     */
    public Page selectRoleToPage(ManagerRole info, Page page) {
        ManagerRoleExample example = new ManagerRoleExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        ManagerRoleExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(info.getRoleName())) {
            c.andRoleNameLike("%" + info.getRoleName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(info.getStatus())) {
            c.andStatusEqualTo(info.getStatus());
        }
        long count = managerRoleMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(managerRoleMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 更新管理员角色信息
     *
     * @param info
     */
    public void updateManagerRole(ManagerRole info) {
        info.setUpdateDate(new Date());
        if (NullUtil.isNotNullOrEmpty(info.getId())) {
            managerRoleMapper.updateByPrimaryKeySelective(info);
        } else {
            managerRoleMapper.insertSelective(info);
        }
    }
}
