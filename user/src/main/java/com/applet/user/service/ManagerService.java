package com.applet.user.service;

import com.applet.user.entity.*;
import com.applet.user.mapper.*;
import com.applet.user.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 查询管理员信息
     *
     * @param extensionCode
     * @return
     */
    public ManagerInfo selectManagerInfo(String extensionCode) {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        ManagerInfoExample example = new ManagerInfoExample();
        example.createCriteria().andExtensionCodeEqualTo(extensionCode).andRoleIdIn(list).andStatusEqualTo(true);
        List<ManagerInfo> list1 = managerInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list1) ? list1.get(0) : null;
    }
}
