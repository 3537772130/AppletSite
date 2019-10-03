package com.applet.user.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: SpringBootDemo
 * @description: 分页工具
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-18 17:23
 **/
public class PageUtil {
    /**
     * 分页参数自动封装
     * @param request
     * @return
     */
    public static Page initPage(HttpServletRequest request) {

        String cur = request.getParameter("page");
        if(NullUtil.isNullOrEmpty(cur)){
            cur = request.getParameter("curPage");
        }
        String size = request.getParameter("pageSize");
        cur =  StringUtils.isNullOrEmpty(cur) ? null : cur;
        size = StringUtils.isNullOrEmpty(size) ? null : size;
        int curPage = (cur == null ? 1 : Integer.parseInt(cur));
        int pageSize = (size == null ? 10: Integer.parseInt(size));
        return new Page(curPage, pageSize);
    }

    /**
     * 分页参数自动封装
     * @return
     */
    public static Page initPage() {
        int curPage = 1;
        int pageSize = 10;
        return new Page(curPage, pageSize);
    }
}
