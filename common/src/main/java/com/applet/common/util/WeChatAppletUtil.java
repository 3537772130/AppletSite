package com.applet.common.util;

import com.alibaba.fastjson.JSONObject;
import com.applet.common.entity.pay.WxUnifiedOrder;
import com.applet.common.util.encryption.Base64;
import com.applet.common.util.encryption.EncryptionUtil;
import com.applet.common.util.encryption.MD5Util;
import com.applet.common.util.encryption.RsaUtil;
import com.applet.common.util.http.HttpUtil;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.security.PrivateKey;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/9
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
public class WeChatAppletUtil {

    /**
     * 获取用户OPENID
     * @param loginCode
     * @return
     * @throws Exception
     */
    public static String getOpenId(String loginCode, String appId, String appSecret) throws Exception{
        appId = EncryptionUtil.decryptAppletRSA(appId);
        appSecret = EncryptionUtil.decryptAppletRSA(appSecret);
        //获取微信授权信息
        String result = toSendOutHttpRequest(loginCode, appId, appSecret);
        log.info("获取用户OPENID:\n{}",result);
        JSONObject obj = JSONObject.parseObject(result);
        if (obj.containsKey("errcode")) {
            log.error("code:{},换取sessionKey失败", obj.get("errmsg"));
            throw new Exception();
        }
        String openId = obj.get("openid").toString();
        return openId;
    }

    /**
     * 获取小程序AccessToken
     * @param appId
     * @param appSecret
     * @return
     * @throws Exception
     */
    public static String getAccessToken(String appId, String appSecret)throws Exception{
        appId = EncryptionUtil.decryptAppletRSA(appId);
        appSecret = EncryptionUtil.decryptAppletRSA(appSecret);
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=##APPID&secret=##APPSECRET";
        url = url.replace("##APPID", appId)
                .replace("##APPSECRET", appSecret);
        String context = HttpUtil.doGet(url);
        log.info("【获取小程序AccessToken返回报文】\n{}", context);
        JSONObject obj = JSONObject.parseObject(context);
        return obj.get("access_token").toString();
    }

    /**
     * 获取小程序二维码
     * @param appId
     * @param appSecret
     * @return
     * @throws Exception
     */
    public static Object getAppletQrCode(String appId, String appSecret)throws Exception{
        String accessToken = getAccessToken(appId, appSecret);
        String url = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=##ACCESS_TOKEN";
        url = url.replace("##ACCESS_TOKEN", accessToken);
        Object obj = HttpUtil.doPost(url);
        log.info("【获取小程序二维码】\n{}", obj);
        return obj;
    }

//    /**
//     * 解密小程序信息
//     * @param str
//     * @return
//     * @throws Exception
//     */
//    private static String getUserAppletDecrypt(String str) throws Exception{
//        byte[] bytes = Base64.decode(str);
//        PrivateKey pri = RsaUtil.getPriKey(RsaUtil.APPLET_MANAGE_PRIVATE_PATH, "RSA");
//        byte[] dStr = RsaUtil.decrypt(bytes, pri, 2048, 11,"RSA/ECB/PKCS1Padding");
//        return new String(dStr, "GBK");//GB2312
//    }

    /**
     * 发送小程序授权请求
     * @param code
     * @return
     */
    public static String toSendOutHttpRequest(String code, String appId, String appSecret) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        //发送POST请求
        String context = HttpUtil.doPost(url);
        log.info("【微信授权登录】\n{}", context);
        return context;
    }


    /**
     * 获取位置详情
     *
     * @param lat 经度
     * @param lon 纬度
     * @return
     */
    public static String getLocation(Double lat, Double lon) throws Exception {
        String url = "http://apis.map.qq.com/ws/geocoder/v1/?location=" + lat + "," + lon + "&key=IT3BZ-D5NHD-Z3E4I-HNVDW-CFXWS-F3BQZ" +
                "&get_poi=1&poi_options=address_format=short;radius=200;page_size=5;page_index=1;policy=1";
        log.info("【获取定位信息链接】\n" + url);
        //发送POST请求
        String context = HttpUtil.doGet(url);
//        log.info("【获取位置详情】{}",context);
        return context;
    }

    /**
     * 发起微信统一下单 - POST
     * @param xml
     * @return
     */
    public static String sendWeChantUnifiedOrderToPOST(String xml){
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String context = null;
        try {
            context = HttpUtil.doPost(url, xml, "UTF-8");
        } catch (Exception e) {
            log.error("第一次发起微信统一下单出错{}", e);
            log.info("尝试第二次发起微信统一下单...");
            url = "https://api2.mch.weixin.qq.com/pay/unifiedorder";
            context = HttpUtil.doPost(url, xml, "UTF-8");
        }
        log.info("【微信统一下单回执】\n{}", context);
        return context;
    }

    /**
     * 获取微信统一下单签名
     * 签名参数按照参数名ASCII码从小到大排序（字典序）
     * 拼接成键值对字符串（即key1=value1&key2=value2…）
     * 使用MD5对字符串进行加密，并转格式成大写字母
     * @param wo
     * @param key
     * @return
     */
    public static String getUnifiedSign(WxUnifiedOrder wo, String key) {
        Map map = convertToMap(wo);
        StringBuffer sbkey = new StringBuffer();
        // 所有参与传参的参数按照accsii排序（升序）
        SortedMap<String, String> sort = new TreeMap<String, String>(map);
        Set es = sort.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v)) {
                sbkey.append(k + "=" + v + "&");
            }
        }
        sbkey = sbkey.append("key=" + key);
        log.info("统一下单信息DATA字符串:{}", sbkey.toString());
        //MD5加密,结果转换为大写字符
        String sign = MD5Util.MD5(sbkey.toString()).toUpperCase();
        log.info("MD5加密值:{}", sign);
        return sign;
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

    /**
     * 转换时间格式
     *
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        if (date != null) {
            JDateTime time = new JDateTime(date);
            String separate1 = "-";
            if (time.getMonth() < 10) {
                separate1 += "0";
            }
            String separate2 = "-";
            if (time.getDay() < 10) {
                separate2 += "0";
            }
            return time.getYear() + separate1 + time.getMonth() + separate2 + time.getDay();
        }
        return "";
    }

    public static void main(String[] args) {
        try {
//            String result = toSendOutHttpRequest("0212NfG32YUvgK0AroH325FtG322NfGQ");
//            JSONObject obj = JSONObject.parseObject(result);
//            if (obj.containsKey("errcode")) {
//                System.out.print("code:{},换取sessionKey失败: " +  obj.get("errmsg"));
//                throw new Exception();
//            } else {
//                String openId = obj.get("openid").toString();
//                String session_key = obj.get("session_key").toString();
//                System.out.print("openId = " + openId + ",session_key = " + session_key);
//            }

            String str = getLocation(31.22964, 121.4744);
            System.out.print(str);
            JSONObject obj = JSONObject.parseObject(str);
            String status = obj.get("status").toString();
            if (status.equals("0")) {
                String result = obj.get("result").toString();
                obj = JSONObject.parseObject(result);
                String addressInfo = obj.get("ad_info").toString();
                obj = JSONObject.parseObject(addressInfo);
                System.out.print(obj.toString());
            } else {
                System.out.print("地图定位失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
