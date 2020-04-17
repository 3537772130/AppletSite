package com.applet.apply.service;

import com.alibaba.fastjson.JSONObject;
import com.applet.common.entity.OrderInfo;
import com.applet.common.entity.OrderRequestRecord;
import com.applet.common.entity.pay.WxUnifiedOrder;
import com.applet.common.util.Arith;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import com.applet.common.util.RandomUtil;
import com.applet.common.util.encryption.MD5Util;
import com.applet.common.util.http.HttpUtil;
import com.applet.common.util.http.IpUtil;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/4/16
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
@Service
public class WeChantPayService {
    @Autowired
    private UserOrderService userOrderService;

    // 微信统一下单参考文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
    // 签名类型
    private static final String SIGN_TYPE = "MD5";
    // 微信统一下单接口
    private static final String UNIFIED_NURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 微信统一下单
     * @param appletCode
     * @param appletName
     * @param appid
     * @param mchId
     * @param payKey
     * @param openId
     * @param orderId
     * @param orderNo
     * @param totalFee
     * @param tradeType
     * @param goodsId
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String sendWeChantUnifiedOrder(String appletCode, String appletName, String appid, String mchId,
                                                 String payKey, String openId, Integer orderId, String orderNo, Double totalFee,
                                                 String tradeType, String goodsId, HttpServletRequest request) {
        // 设置微信统一下单信息
        WxUnifiedOrder wx = new WxUnifiedOrder();
        wx.setAppid(appid);
        wx.setMch_id(mchId);
        wx.setNonce_str(RandomUtil.getRandomStr32());
        wx.setDevice_info(appletCode);
        wx.setSign_type(SIGN_TYPE);
        wx.setBody(orderNo + "-订单支付");
        wx.setDetail(null);
        wx.setAttach(appletName);
        wx.setOut_trade_no(orderNo);
        wx.setFee_type("CNY");
        wx.setTotal_fee((int) Arith.mul(totalFee, 100.0d));
        wx.setSpbill_create_ip(IpUtil.getForIp(request));
        JDateTime time = new JDateTime(new Date());
        wx.setTime_start(time.toString(Constants.DEFAULT_DATE_FORMAT_STAMP));
        wx.setTime_expire(time.addHour(2).toString(Constants.DEFAULT_DATE_FORMAT_STAMP));
        wx.setGoods_tag(null);
        wx.setNotify_url(getNotifyUrl(tradeType));
        wx.setTrade_type(tradeType);
        wx.setProduct_id(tradeType.equals(Constants.TRADE_TYPE_NATIVE) ? goodsId : null);
        wx.setLimit_pay(null);
        wx.setOpenid(openId);
        wx.setReceipt(null);
        wx.setScene_info(null);
        // 按照参数名ASCII码从小到大排序，拼接成键值对字符串（即key1=value1&key2=value2…）
        Map map = convertToMap(wx);
        SortedMap<String, String> sort = new TreeMap<String, String>(map);
        wx.setSign(getUnifiedSign(sort, payKey));
        String xml = getXml(wx);
        String prepayId = null;
        Boolean bool = false;
        // 发送微信统一下单请求
        String result = HttpUtil.doPost(UNIFIED_NURL, xml, "UTF-8");
        OrderRequestRecord record = new OrderRequestRecord();
        record.setRequestMsg(result);
        JSONObject obj = JSONObject.parseObject(result);
        record.setRequestType("WX_" + tradeType);
        if (obj.get("return_code").toString().equals("SUCCESS")) {
            record.setResultCode(obj.get("result_code").toString());
            if (record.getResultCode().equals("SUCCESS")) {
                bool = true;
            } else {
                record.setErrCode(obj.get("err_code").toString());
                record.setErrCodeDes(obj.get("err_code_des").toString());
                bool = record.getErrCode().equals("NOTENOUGH");
            }
        }
        prepayId = obj.get("err_code_des").toString();
        // 添加订单支付请求记录
        userOrderService.addOrderRequestRecord(record);
        if (NullUtil.isNotNullOrEmpty(orderId)){
            // 更新订单信息
            OrderInfo orderInfo = userOrderService.selectOrderInfoByPayRelationId(prepayId, record.getRequestType());
            userOrderService.updateOrderInfo(orderInfo.getId(), prepayId, record.getRequestType(), bool);
        }
        return bool ? prepayId : null;
    }


    /**
     * 获取微信统一下单签名
     * @param parameters
     * @param key
     * @return
     */
    private static String getUnifiedSign(SortedMap<String, String> parameters, String key) {
        StringBuffer sb = new StringBuffer();
        StringBuffer sbkey = new StringBuffer();
        Set es = parameters.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
                sbkey.append(k + "=" + v + "&");
            }
        }
        System.out.println("统一下单信息DATA字符串:" + sb.toString());
        sbkey = sbkey.append("key=" + key);
        System.out.println("字符串:" + sbkey.toString());
        //MD5加密,结果转换为大写字符
        String sign = MD5Util.MD5(sbkey.toString()).toUpperCase();
        System.out.println("MD5加密值:" + sign);
        return sb.toString() + "sign=" + sign;
    }

    /**
     * 获取支付回调地址
     * @param tradeType
     * @return
     */
    private static String getNotifyUrl(String tradeType) {
        String notify_url = NullUtil.getWebSite() + "/api/user/payBack";
        if (tradeType.equals(Constants.TRADE_TYPE_JSAPI)) {
            notify_url = NullUtil.getWebSite() + "/api/applet/payBack";
        } else if (tradeType.equals(Constants.TRADE_TYPE_APP)) {
            notify_url = NullUtil.getWebSite() + "/api/app/payBack";
        }
        return notify_url;
    }


    /**
     * java对象转XML
     * @param obj
     * @return
     */
    private static String getXml(Object obj) {
        Map map = convertToMap(obj);
//        SortedMap<String, String> parameters = new TreeMap<String, String>(map);
        StringBuffer str = new StringBuffer();
        str.append("<xml>");
//        Set es = parameters.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        Set es = map.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v)) {
                str.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        str.append("</xml>");
        log.info("微信统一下单请求参数XML字符串：", str.toString());
        return str.toString();
    }

    /**
     * Object对象转Map集合
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> convertToMap(Object obj) {
        try {
            if (obj instanceof Map) {
                return (Map) obj;
            }
            Map<String, Object> returnMap = BeanUtils.describe(obj);
            returnMap.remove("class");
            return returnMap;
        } catch (IllegalAccessException e1) {
            e1.getMessage();
        } catch (InvocationTargetException e2) {
            e2.getMessage();
        } catch (NoSuchMethodException e3) {
            e3.getMessage();
        }
        return new HashMap();
    }
}
