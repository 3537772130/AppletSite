package com.applet.manage.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: SpringBootDemo
 * @description: 菜单服务类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-02 10:15
 **/
@SuppressWarnings("ALL")
@Service
@Component
public class MenuService implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(MenuService.class);
    @Autowired
    private MenuInfoMapper menuInfoMapper;
    @Autowired
    private ManagerRoleMenuMapper managerRoleMenuMapper;
    @Autowired
    private ViewRoleMenuFirstMapper viewRoleMenuFirstMapper;
    @Autowired
    private ViewRoleMenuSecondMapper viewRoleMenuSecondMapper;
    @Autowired
    private ViewRoleMenuThirdMapper viewRoleMenuThirdMapper;
    @Autowired
    private ManagerService managerService;

    /**
     * 打包所有角色的权限信息
     *
     * @param args
     */
    @Override
    public void run(ApplicationArguments args) {
        this.getReadyRole(null);
    }

    /**
     * 准备好角色信息
     *
     * @param roleId
     */
    @Async("taskExecutor")
    public void getReadyRole(Integer roleId) {
        log.info("----------开始准备角色权限信息，进行打包封装处理----------");
        if (NullUtil.isNullOrEmpty(roleId)) {
            List<ManagerRole> roleList = managerService.selectManagerRoleList();
            for (ManagerRole role : roleList) {
                Map map = this.getRoleAuthMap(role.getId());
                Constants.MANAGER_ROLE_AUTH_MAP.put(role.getId(), map);
            }
        } else {
            Map map = this.getRoleAuthMap(roleId);
            Constants.MANAGER_ROLE_AUTH_MAP.put(roleId, map);
        }

        List<MenuInfo> list = this.selectMenuListByThird();
        for (MenuInfo record : list) {
            Constants.MANAGER_ROLE_AUTH_LOGO_MAP.put(record.getMenuLogo(), record.getMenuLogo());
        }

        log.info("--------------角色权限信息，打包封装处理完毕--------------");
    }

    /**
     * 准备角色权限信息，封装成Map集合
     *
     * @param roleId
     * @return
     */
    public Map getRoleAuthMap(Integer roleId) {
        List<ViewRoleMenuThird> list = selectMenuListByThird(roleId);
        if (NullUtil.isNullOrEmpty(list)) {
            return null;
        }
        Map<String, String> map = new HashMap();
        for (ViewRoleMenuThird record : list) {
            map.put(record.getMenuLogo(), record.getMenuLogo());
        }
        return map;
    }

    /**
     * 查询菜单信息
     *
     * @param id
     * @return
     */
    public MenuInfo selectMenuInfo(Integer id) {
        return menuInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询菜单的子菜单
     *
     * @param parentId
     * @return
     */
    public List<MenuInfo> selectMenuChildrenList(Integer parentId) {
        MenuInfoExample example = new MenuInfoExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        return menuInfoMapper.selectByExample(example);
    }

    /**
     * 查询菜单集合
     *
     * @param level
     * @return
     */
    public List<MenuInfo> selectMenuList(Integer level, Boolean status) {
        MenuInfoExample example = new MenuInfoExample();
        example.setOrderByClause("menu_index asc");
        MenuInfoExample.Criteria c = example.createCriteria().andMenuLevelEqualTo(level);
        if (NullUtil.isNotNullOrEmpty(status)) {
            c.andStatusEqualTo(status);
        }
        return menuInfoMapper.selectByExample(example);
    }

    /**
     * 查询一级菜单集合
     *
     * @return
     */
    public List<MenuInfo> selectMenuListByFirst() {
        return selectMenuList(1, null);
    }

    /**
     * 查询二级菜单集合
     *
     * @return
     */
    public List<MenuInfo> selectMenuListBySecond() {
        return selectMenuList(2, null);
    }

    /**
     * 查询三级菜单集合
     *
     * @return
     */
    public List<MenuInfo> selectMenuListByThird() {
        return selectMenuList(3, null);
    }

    /**
     * 查询角色一级菜单集合
     *
     * @param roleId
     * @return
     */
    public List<ViewRoleMenuFirst> selectMenuListByFirst(Integer roleId) {
        ViewRoleMenuFirstExample example = new ViewRoleMenuFirstExample();
        example.setOrderByClause("menu_index asc");
        example.createCriteria().andRoleIdEqualTo(roleId);
        return viewRoleMenuFirstMapper.selectByExample(example);
    }

    /**
     * 查询角色二级菜单集合
     *
     * @param roleId
     * @return
     */
    public List<ViewRoleMenuSecond> selectMenuListBySecond(Integer roleId) {
        ViewRoleMenuSecondExample example = new ViewRoleMenuSecondExample();
        example.setOrderByClause("menu_index asc");
        example.createCriteria().andRoleIdEqualTo(roleId);
        return viewRoleMenuSecondMapper.selectByExample(example);
    }

    /**
     * 查询角色三级菜单集合
     *
     * @param roleId
     * @return
     */
    public List<ViewRoleMenuThird> selectMenuListByThird(Integer roleId) {
        ViewRoleMenuThirdExample example = new ViewRoleMenuThirdExample();
        example.setOrderByClause("menu_index asc");
        ViewRoleMenuThirdExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(roleId)) {
            c.andRoleIdEqualTo(roleId);
        }
        return viewRoleMenuThirdMapper.selectByExample(example);
    }

    /**
     * 根据标识查询授权菜单
     *
     * @param logo
     * @return
     */
    public List<ViewRoleMenuThird> selectRoleMenuInfo(String logo) {
        ViewRoleMenuThirdExample example = new ViewRoleMenuThirdExample();
        example.createCriteria().andMenuLogoEqualTo(logo);
        return viewRoleMenuThirdMapper.selectByExample(example);
    }

    /**
     * 更新菜单
     *
     * @param menuInfo
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateMenuInfo(MenuInfo menuInfo) {
        menuInfo.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(menuInfo.getId())) {
            MenuInfo record = menuInfoMapper.selectByPrimaryKey(menuInfo.getId());
            menuInfoMapper.updateByPrimaryKeySelective(menuInfo);
            // 当菜单状态发生改变
            if ((record.getStatus() == true && menuInfo.getStatus() == false) ||
                    (record.getStatus() == false && menuInfo.getStatus() == true)) {
                // 非三级菜单，则更新菜单下所有子菜单状态
                List<Integer> idList = new ArrayList<>();
                idList.add(menuInfo.getId());
                if (record.getMenuLevel().intValue() != 3) { // 获取所有子级的ID
                    List<MenuInfo> list = this.selectMenuChildrenList(record.getId());
                    // 二级菜单，下属只有一级
                    for (MenuInfo info : list) {
                        idList.add(info.getId());
                        // 一级菜单，下属有俩级
                        if (record.getMenuLevel().intValue() == 1) {
                            List<MenuInfo> list1 = this.selectMenuChildrenList(info.getId());
                            for (MenuInfo info1 : list1) {
                                idList.add(info1.getId());
                            }
                        }
                    }
                } else if (menuInfo.getStatus() == true) { // 获取所有父级的ID
                    MenuInfo record1 = this.selectMenuInfo(record.getParentId());
                    idList.add(record1.getId());
                    MenuInfo record2 = this.selectMenuInfo(record1.getParentId());
                    idList.add(record2.getId());
                }
                // 批量更新菜单状态
                if (!(record.getMenuLevel().intValue() == 3 && menuInfo.getStatus() == false)) {
                    MenuInfoExample example = new MenuInfoExample();
                    example.createCriteria().andIdIn(idList);
                    MenuInfo m = new MenuInfo();
                    m.setStatus(menuInfo.getStatus());
                    menuInfoMapper.updateByExampleSelective(m, example);
                }

                // 更新菜单权限打包的缓存
                this.getReadyRole(null);
            }
        } else {
            menuInfoMapper.insertSelective(menuInfo);
            if (menuInfo.getMenuLevel().intValue() == 3) {
                ManagerRoleMenu record = new ManagerRoleMenu();
                record.setRoleId(1);
                record.setMenuId(menuInfo.getId());
                record.setUpdateTime(new Date());
                record.setStatus(true);
                managerRoleMenuMapper.insertSelective(record);
                // 更新菜单权限打包的缓存
                this.getReadyRole(record.getRoleId());
            }
        }
    }

    /**
     * 查询角色拥有权限的菜单ID集合
     *
     * @param roleId
     * @return
     */
    public List<Integer> selectRoleMenuIdToList(Integer roleId) {
        ManagerRoleMenuExample example = new ManagerRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andStatusEqualTo(true);
        List<ManagerRoleMenu> list = managerRoleMenuMapper.selectByExample(example);
        List<Integer> list1 = new ArrayList<>();
        for (ManagerRoleMenu record : list) {
            list1.add(record.getMenuId());
        }
        return list1;
    }

    /**
     * 更新角色菜单
     *
     * @param roleId
     * @param idList
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleMenu(Integer roleId, List<Integer> idList) {
        // 刷选未选择过的权限，添加到角色菜单权限表
        ManagerRoleMenuExample example = new ManagerRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<ManagerRoleMenu> list = managerRoleMenuMapper.selectByExample(example);
        List<Integer> idList1 = new ArrayList<>();
        for (Integer menuId : idList) {
            boolean bool = true;
            for (ManagerRoleMenu record : list) {
                if (menuId.intValue() == record.getMenuId().intValue()) {
                    bool = false;
                    break;
                }
            }
            if (bool) {
                idList1.add(menuId);
            }
        }
        for (Integer menuId : idList1) {
            ManagerRoleMenu roleMenu = new ManagerRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setUpdateTime(new Date());
            roleMenu.setStatus(true);
            managerRoleMenuMapper.insertSelective(roleMenu);
        }
        // 已选择的菜单全部开启权限
        example = new ManagerRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andMenuIdIn(idList);
        ManagerRoleMenu record = new ManagerRoleMenu();
        record.setStatus(true);
        managerRoleMenuMapper.updateByExampleSelective(record, example);
        // 未选择的菜单全部关闭权限
        example = new ManagerRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andMenuIdNotIn(idList);
        record = new ManagerRoleMenu();
        record.setStatus(false);
        managerRoleMenuMapper.updateByExampleSelective(record, example);

        // 更新
        this.getReadyRole(roleId);
    }
}
