package com.applet.user.entity.page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/20
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class ContentInfo {
    private String id;
    private String name;
    private String title;
    private String describe;
    private List<ElementInfo> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<ElementInfo> getList() {
        return list;
    }

    public void setList(List<ElementInfo> list) {
        this.list = list;
    }
}
