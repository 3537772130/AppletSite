package com.applet.common.entity.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/4/16
 * Time: 1:11
 * To change this template use File | Settings | File Templates.
 * Description: 微信统一下单信息
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxUnifiedOrder {
    // 公众账号ID
    @XmlElement(name = "appid")
    private String appid;
    // 商户号
    @XmlElement(name = "mch_id")
    private String mchId;
    // 设备号
    @XmlElement(name = "device_info")
    private String deviceInfo;
    // 随机字符串
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    // 签名
    @XmlElement(name = "sign")
    private String sign;
    // 签名类型
    @XmlElement(name = "sign_type")
    private String signType;
    // 商品描述
    @XmlElement(name = "body")
    private String body;
    // 商品详情
    @XmlElement(name = "detail")
    private String detail;
    // 附加数据
    @XmlElement(name = "attach")
    private String attach;
    // 商户订单号
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    // 标价币种
    @XmlElement(name = "fee_type")
    private String feeType;
    // 标价金额
    @XmlElement(name = "total_fee")
    private Integer totalFee;
    // 终端IP
    @XmlElement(name = "spbill_create_ip")
    private String spbillCreateIp;
    // 交易起始时间
    @XmlElement(name = "time_start")
    private String timeStart;
    // 交易结束时间
    @XmlElement(name = "time_expire")
    private String timeExpire;
    // 订单优惠标记
    @XmlElement(name = "goods_tag")
    private String goodsTag;
    // 通知地址
    @XmlElement(name = "notify_url")
    private String notifyUrl;
    // 交易类型
    @XmlElement(name = "trade_type")
    private String tradeType;
    // 商品ID
    @XmlElement(name = "product_id")
    private String productId;
    // 指定支付方式
    @XmlElement(name = "limit_pay")
    private String limitPay;
    // 用户标识
    @XmlElement(name = "openid")
    private String openid;
    // 电子发票入口开放标识
    @XmlElement(name = "receipt")
    private String receipt;
    // 场景信息
    @XmlElement(name = "scene_info")
    private String sceneInfo;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }
}
