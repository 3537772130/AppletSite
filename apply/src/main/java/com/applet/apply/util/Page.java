package com.applet.apply.util;

import java.util.Collection;

/**
 * @program: SpringBootDemo
 * @description: 分页
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-18 17:22
 **/
public class Page {
    private long totalCount;//总数
    private long page;//当前页码
    private long pageSize;//页面大小
    private Collection dataSource;//数据源
    private long rowStart;
    private long totalPage;

    public Page(long page, long pageSize) {
        if (pageSize <= 0) {
            this.pageSize = 10;
        } else {
            this.pageSize = pageSize;
        }
        if (page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
        this.rowStart = (this.page - 1) * this.pageSize;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalPage() {
        if (totalCount % pageSize == 0) {
            this.totalPage = totalCount / pageSize;
        } else {
            this.totalPage = totalCount / pageSize + 1;
        }
        return totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public Collection getDataSource() {
        return dataSource;
    }

    public void setDataSource(Collection dataSource) {
        this.dataSource = dataSource;
    }

    public long getRowStart() {
        return rowStart;
    }

    public void setRowStart(long rowStart) {
        this.rowStart = rowStart;
    }
}
