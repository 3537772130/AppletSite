package com.applet.common.entity.pay;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/4/15
 * Time: 22:52
 * To change this template use File | Settings | File Templates.
 * Description: 小程序支付信息类
 */
public class WxAppletPay {
    public String appId;
    public String nonceStr;
    public String packageStr;
    public String signType;
    public String timeStamp;
    public String key;
    public String paySign;

    public WxAppletPay(){

    }

    public WxAppletPay(String appId, String nonceStr, String packageStr, String signType, String timeStamp, String key, String paySign){
        this.appId = appId;
        this.nonceStr = nonceStr;
        this.packageStr = packageStr;
        this.signType = signType;
        this.timeStamp = timeStamp;
        this.key = key;
        this.paySign = paySign;
    }
}
