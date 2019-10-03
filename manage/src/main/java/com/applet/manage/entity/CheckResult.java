package com.applet.manage.entity;

/**
 * @program: SpringBootDemo
 * @description: 检查结果
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-05 09:25
 **/
public class CheckResult {

    public Boolean bool;

    public String msg;

    public CheckResult() {
        this.bool = true;
        this.msg = "";
    }

    public CheckResult(String result) {
        this.bool = false;
        this.msg = result;
    }

    public CheckResult(boolean bool, String result) {
        this.bool = bool;
        this.msg = result;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
