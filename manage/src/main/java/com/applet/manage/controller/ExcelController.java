package com.applet.manage.controller;

import com.applet.manage.entity.ViewAppletInfo;
import com.applet.manage.entity.ViewAppletInfoExample;
import com.applet.manage.service.ExcelService;
import com.applet.common.util.NullUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: SpringBootDemo
 * @description: Excel控制类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-12 11:23
 **/
@Controller
@RequestMapping(value = "/api/manage/excel/")
public class ExcelController {
    private static final Logger log = LoggerFactory.getLogger(ExcelController.class);
    @Autowired
    private ExcelService excelService;

    /**
     * 导出小程序列表
     *
     * @param info
     */
    @RequestMapping(value = "exportAppletList")
    public void exportAppletList(ViewAppletInfo info, HttpServletResponse response) {
        ViewAppletInfoExample example = new ViewAppletInfoExample();
        example.setOrderByClause("id desc");
        ViewAppletInfoExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(info.getAppletCode())) {
            c.andAppletCodeLike("%" + info.getAppletCode() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(info.getAppletName())) {
            c.andAppletNameLike("%" + info.getAppletName() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(info.getMobile())) {
            c.andMobileLike(info.getMobile() + "%");
        }
        if (NullUtil.isNotNullOrEmpty(info.getProvince())) {
            c.andProvinceEqualTo(info.getProvince());
            if (NullUtil.isNotNullOrEmpty(info.getCity())) {
                c.andCityEqualTo(info.getCity());
                if (NullUtil.isNotNullOrEmpty(info.getCounty())) {
                    c.andCountyEqualTo(info.getCounty());
                }
            }
        }
        if (NullUtil.isNotNullOrEmpty(info.getIfRetail())) {
            c.andIfRetailEqualTo(info.getIfRetail());
        }
        if (NullUtil.isNotNullOrEmpty(info.getIfSelling())) {
            c.andIfSellingEqualTo(info.getIfSelling());
        }
        if (NullUtil.isNotNullOrEmpty(info.getStatus())) {
            c.andStatusEqualTo(info.getStatus());
        } else {
            c.andStatusNotEqualTo(0);
        }
        try {
            excelService.exportAppletList(example, response);
        } catch (Exception e) {
            log.error("导出小程序列表出错{}", e);
        }
    }
}
