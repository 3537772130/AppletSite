package com.applet.manage.controller;

import com.applet.common.entity.*;
import com.applet.manage.service.MenuService;
import com.applet.common.util.AjaxResponse;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import jodd.datetime.JDateTime;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 后台管理菜单控制类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-02 10:13
 **/
@RestController
@RequestMapping(value = "/api/manage/menu/")
public class ManageMenuController {
    private static final Logger log = LoggerFactory.getLogger(ManageMenuController.class);
    @Autowired
    private MenuService menuService;

    /**
     * 加载菜单列表
     *
     * @return
     */
    @RequestMapping(value = "loadMenuList")
    public Object loadMenuList() {
        try {
            List<Map> mapList = getMenuList();
            return AjaxResponse.success(new JSONArray(mapList).toString());
        } catch (Exception e) {
            log.error("加载菜单列表出错{}", e);
            return AjaxResponse.error("加载菜单列表出错");
        }
    }

    /**
     * 获取菜单列表(格式化)
     *
     * @return
     * @throws Exception
     */
    public List<Map> getMenuList() throws Exception {
        List<MenuInfo> thirdList = menuService.selectMenuListByThird();
        List<MenuInfo> secondList = menuService.selectMenuListBySecond();
        List<MenuInfo> firstList = menuService.selectMenuListByFirst();
        // 格式化菜单
        List<Map> mapList1 = new ArrayList<>();
        for (MenuInfo record1 : secondList) {
            Map map1 = new HashMap();
            map1.put("id", record1.getId());
            map1.put("label", record1.getMenuName());
            map1.put("icon", record1.getMenuIcon());
            map1.put("logo", record1.getMenuLogo());
            map1.put("parentId", record1.getParentId());
            map1.put("index", record1.getMenuIndex());
            map1.put("level", record1.getMenuLevel());
            map1.put("updateTime", new JDateTime(record1.getUpdateTime()).toString(Constants.DATE_TIME_JDK));
            map1.put("status", record1.getStatus());
            List<Map> mapList2 = new ArrayList<>();
            for (MenuInfo record2 : thirdList) {
                if (record2.getParentId().intValue() == record1.getId().intValue()) {
                    Map map2 = new HashMap();
                    map2.put("id", record2.getId());
                    map2.put("label", record2.getMenuName());
                    map2.put("logo", record2.getMenuLogo());
                    map2.put("path", record2.getMenuPath());
                    map2.put("parentId", record2.getParentId());
                    map2.put("index", record2.getMenuIndex());
                    map2.put("level", record2.getMenuLevel());
                    map2.put("updateTime", new JDateTime(record2.getUpdateTime()).toString(Constants.DATE_TIME_JDK));
                    map2.put("status", record2.getStatus());
                    mapList2.add(map2);
                }
            }
            map1.put("children", mapList2);
            mapList1.add(map1);
        }
        List<Map> mapList2 = new ArrayList<>();
        for (MenuInfo record : firstList) {
            Map map1 = new HashMap();
            map1.put("id", record.getId());
            map1.put("label", record.getMenuName());
            map1.put("logo", record.getMenuLogo());
            map1.put("parentId", 0);
            map1.put("index", record.getMenuIndex());
            map1.put("level", record.getMenuLevel());
            map1.put("updateTime", new JDateTime(record.getUpdateTime()).toString(Constants.DATE_TIME_JDK));
            map1.put("status", record.getStatus());
            List<Map> mapList3 = new ArrayList<>();
            for (Map map2 : mapList1) {
                if (map2.get("parentId").toString().equals(record.getId().toString())) {
                    mapList3.add(map2);
                }
            }
            map1.put("children", mapList3);
            mapList2.add(map1);
        }
        Map map = new HashMap();
        map.put("id", "0");
        map.put("label", "根目录");
        map.put("logo", "root");
        map.put("level", 0);
        map.put("children", mapList2);
        List<Map> mapList = new ArrayList<>();
        mapList.add(map);
        return mapList;
    }

    /**
     * 加载菜单信息
     *
     * @param id
     * @param parentId
     * @return
     */
    @RequestMapping(value = "loadMenuDetails")
    public Object loadMenuDetails(Integer id, Integer parentId) {
        Map maps = new HashMap();
        List<Map> parentList = new ArrayList<>();
        if (parentId.intValue() == 0) {
            Map map = new HashMap();
            map.put("id", 0);
            map.put("name", "根目录");
            parentList.add(map);
            maps.put("parentInfo", map);
            maps.put("level", 1);
        } else {
            MenuInfo menuInfo = menuService.selectMenuInfo(parentId);
            maps.put("parentInfo", menuInfo);
            maps.put("level", menuInfo.getMenuLevel() + 1);
            List<MenuInfo> list = menuService.selectMenuList(menuInfo.getMenuLevel(), true);
            for (MenuInfo info : list) {
                Map map = new HashMap();
                map.put("id", info.getId());
                map.put("name", info.getMenuName());
                parentList.add(map);
            }
        }
        maps.put("parentList", parentList);
        if (NullUtil.isNotNullOrEmpty(id) && id.intValue() != 0) {
            MenuInfo menuInfo = menuService.selectMenuInfo(id);
            if (NullUtil.isNullOrEmpty(menuInfo.getParentId())) {
                menuInfo.setParentId(0);
            }
            maps.put("info", menuInfo);
            maps.put("level", menuInfo.getMenuLevel());
            return AjaxResponse.success(maps);
        }
        return AjaxResponse.msg("-1", maps);
    }

    /**
     * 更新菜单信息
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "updateMenuInfo")
    public Object updateMenuInfo(MenuInfo info) {
        try {
            if (null == info) {
                return AjaxResponse.error("参数错误");
            }
            if (info.getParentId().intValue() == 0) {
                info.setParentId(null);
            }
            menuService.updateMenuInfo(info);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("提交菜单信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }


    /**
     * 加载角色菜单列表
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "loadRoleMenuList")
    public Object loadRoleMenuList(Integer roleId) {
        try {
            List<Integer> idList = menuService.selectRoleMenuIdToList(roleId);
            List<Map> menuList = getMenuList();
            Map map = new HashMap();
            map.put("idList", idList);
            map.put("menuList", new JSONArray(menuList).toString());
            return AjaxResponse.success(map);
        } catch (Exception e) {
            log.error("加载菜单列表出错{}", e);
            return AjaxResponse.error("加载菜单列表出错");
        }

    }

    /**
     * 更新角色菜单权限
     *
     * @param roleId
     * @param idJson
     * @return
     */
    @RequestMapping(value = "updateRoleMenuAuth")
    public Object updateRoleMenuAuth(Integer roleId, String idJson) {
        try {
            if (NullUtil.isNullOrEmpty(roleId) || NullUtil.isNullOrEmpty(idJson)) {
                return AjaxResponse.error("参数错误");
            }
            List<Integer> idList = com.alibaba.fastjson.JSONArray.parseArray(idJson, Integer.class);
            menuService.updateRoleMenu(roleId, idList);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("更新角色菜单权限出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }
}
