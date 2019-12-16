package com.applet.apply.service;

import com.applet.apply.entity.RegionInfo;
import com.applet.apply.entity.RegionInfoExample;
import com.applet.apply.mapper.RegionInfoMapper;
import com.applet.apply.util.Constants;
import com.applet.apply.util.NullUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 地域位置服务类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-07-29 10:57
 **/
@Service
@Component
public class RegionService implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(RegionService.class);
    @Autowired
    private RegionInfoMapper regionInfoMapper;

    private final Integer PROVINCE_LEVEL = 1;
    private final Integer CITY_LEVEL = 2;
    private final Integer COUNTY_LEVEL = 3;

    /**
     * 项目启动后自动初始化地域信息
     *
     * @param args
     */
    @Override
    public void run(ApplicationArguments args) {
        log.info("--------------开始初始化地域信息--------------");
        // 获取各级别地域信息集合
        List<RegionInfo> provinceList = selectProvinceList();
        List<RegionInfo> cityList = selectCityList();
        List<RegionInfo> countyList = selectCountyList();

        List<Map> idList = new ArrayList<>();
        List<Map> nameList = new ArrayList<>();
        List<Map> list2 = new ArrayList<>();
        List<Map> list22 = new ArrayList<>();
        try {
            // 将区县归入到对应的城市下
            for (int i = 0; i < cityList.size(); i++) {
                RegionInfo record2 = cityList.get(i);
                Map map2 = new HashMap();
                map2.put("parentId", record2.getParentId());
                map2.put("value", record2.getId());
                map2.put("label", record2.getAreaName());
                List<Map> list3 = new ArrayList<>();
                Map map22 = new HashMap();
                map22.put("parentId", record2.getParentId());
                map22.put("value", record2.getAreaName());
                map22.put("label", record2.getAreaName());
                List<Map> list33 = new ArrayList<>();
                for (int k = 0; k < countyList.size(); k++) {
                    RegionInfo record3 = countyList.get(k);
                    if (record2.getId().intValue() == record3.getParentId().intValue()) {
                        Map map3 = new HashMap();
                        map3.put("value", record3.getId());
                        map3.put("label", record3.getAreaName());
                        list3.add(map3);
                        Map map33 = new HashMap();
                        map33.put("value", record3.getAreaName());
                        map33.put("label", record3.getAreaName());
                        list33.add(map33);
                    }
                    continue;
                }
                map2.put("children", list3);
                list2.add(map2);
                map22.put("children", list33);
                list22.add(map22);
            }

            // 将城市归入到对应的省份下
            for (int i = 0; i < provinceList.size(); i++) {
                RegionInfo record1 = provinceList.get(i);
                Map map1 = new HashMap();
                map1.put("value", record1.getId());
                map1.put("label", record1.getAreaName());
                List<Map> list0 = new ArrayList<>();
                Map map11 = new HashMap();
                map11.put("value", record1.getAreaName());
                map11.put("label", record1.getAreaName());
                List<Map> list00 = new ArrayList<>();
                for (int k = 0; k < list2.size(); k++) {
                    Map map4 = list2.get(k);
                    Map map44 = list22.get(k);
                    if (map4.get("parentId").toString().equals(record1.getId().toString())) {
                        list0.add(map4);
                        list00.add(map44);
                    }
                    continue;
                }
                map1.put("children", list0);
                idList.add(map1);
                map11.put("children", list00);
                nameList.add(map11);
            }

            // 移除城市的父级id项，并保存地域信息集合
            for (int i = 0; i < idList.size(); i++) {
                Map map1 = idList.get(i);
                List<Map> mapList1 = (List<Map>) map1.get("children");
                Map map2 = nameList.get(i);
                List<Map> mapList2 = (List<Map>) map2.get("children");
                for (int k = 0; k < mapList1.size(); k++) {
                    mapList1.get(k).remove("parentId");
                    mapList2.get(k).remove("parentId");
                }
                Constants.REGION_MAP_TO_ID.add(map1);
                Constants.REGION_MAP_TO_NAME.add(map2);
            }
            log.info("--------------初始化地域信息完毕--------------");
        } catch (Exception e) {
            log.error("初始化地域信息JSON出错{}", e);
        }
    }

    /**
     * 查询省份信息集合
     *
     * @return
     */
    public List<RegionInfo> selectProvinceList() {
        return selectRegionList(null, PROVINCE_LEVEL);
    }

    /**
     * 查询城市信息集合
     *
     * @return
     */
    public List<RegionInfo> selectCityList() {
        return selectRegionList(null, CITY_LEVEL);
    }

    /**
     * 查询地区信息集合
     *
     * @return
     */
    public List<RegionInfo> selectCountyList() {
        return selectRegionList(null, COUNTY_LEVEL);
    }

    /**
     * 查询城市、区县信息集合
     *
     * @param parentId
     * @return
     */
    public List<RegionInfo> selectRegionList(Integer parentId, Integer level) {
        RegionInfoExample example = new RegionInfoExample();
        RegionInfoExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(parentId)) {
            c.andParentIdEqualTo(parentId).andIdNotEqualTo(parentId);
        }
        if (NullUtil.isNotNullOrEmpty(level)) {
            c.andLevelEqualTo(level);
        }
        List<RegionInfo> list = regionInfoMapper.selectByExample(example);
        return list;
    }

    /**
     * 根据父级名称，查询下级地域集合
     *
     * @param parentName 父级地域名称
     * @return
     */
    public List<RegionInfo> selectRegionInfo(String parentName) {
        RegionInfoExample example = new RegionInfoExample();
        example.createCriteria().andAreaNameEqualTo(parentName);
        RegionInfo info = regionInfoMapper.selectByExample(example).get(0);
        return selectRegionList(info.getId(), null);
    }
}
