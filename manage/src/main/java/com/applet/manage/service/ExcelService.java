package com.applet.manage.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.ViewAppletInfoMapper;
import com.applet.common.util.excel.DownloadUtil;
import com.applet.common.util.excel.ExcelUtil;
import com.applet.manage.util.excel.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: Excel服务类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-12 11:22
 **/
@SuppressWarnings("ALL")
@Service
public class ExcelService {

    @Autowired
    private ViewAppletInfoMapper viewAppletInfoMapper;

    /**
     * 导出小程序信息
     *
     * @param example
     * @param response
     * @throws Exception
     */
    public void exportAppletList(ViewAppletInfoExample example, HttpServletResponse response) throws Exception {
        List<ViewAppletInfo> list = viewAppletInfoMapper.selectByExample(example);
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        for (ViewAppletInfo record : list) {
            listMap.add(ConvertUtil.appletInfoToMap(record));
        }
        String[] cols = {"编码", "名称", "用户账号", "用户名称", "省份", "城市", "区县", "营业类型", "营业状态", "注册日期", "管理状态"};
        String[] keys = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String fileName = "小程序列表";
        try {
            ExcelUtil.createWorkBook(fileName, listMap, keys, cols).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DownloadUtil.download(response, fileName, os);
    }
}
