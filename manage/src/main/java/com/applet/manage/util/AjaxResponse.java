package com.applet.manage.util;

import jodd.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: AJAX响应工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-19 09:54
 **/
public class AjaxResponse {
    public static Map<String, Object> success(Object data) {
        return msg("1", data);
    }

    public static Map<String, Object> success() {
        return success(null);
    }

    public static Map<String, Object> error(String errorMsg) {
        return msg("-1", errorMsg);
    }

    public static Map<String, Object> msg(String code, Object data) {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("code", code);
        if (data != null) {
            ret.put("data", data);
        } else {
            ret.put("data", "");
        }
        return ret;
    }

    public static Map<String, Object> Page(boolean success, int totalRows, int curPage, Object data) {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("success", success);
        ret.put("totalRows", totalRows);
        ret.put("curPage", curPage);
        ret.put("data", data);
        return ret;
    }

    public static Map<String, Object> valid(boolean success, String msg) {
        Map<String, Object> ret = new HashMap<String, Object>();
        if (success) {
            ret.put("ok", " ");
        } else {
            ret.put("error", StringUtil.toSafeString(msg));
        }
        return ret;
    }


    public static Map<String, Object> bsGrid(boolean success, int totalRows, int curPage, Object data) {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("success", success);
        ret.put("totalRows", totalRows);
        ret.put("curPage", curPage);
        ret.put("data", data);
        return ret;
    }

    public static Map<String,Object> dataTables(Page page){
        Map<String,Object> ret = new HashMap();
        if (null == page.getDataSource()) {
            ret.put("rows", new  String[0]);
        } else {
            ret.put("rows", page.getDataSource());
        }
        ret.put("total",page.getTotalCount());
        return ret;
    }
}
