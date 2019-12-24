package com.applet.common.util;

import com.alibaba.fastjson.JSONObject;
import com.applet.common.util.encryption.Base64;
import com.applet.common.util.encryption.RsaUtil;
import com.applet.common.util.file.PathUtil;
import com.applet.common.util.http.HttpUtil;
import jodd.datetime.JDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PrivateKey;
import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/9
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class WeChatAppletUtil {

    private static final Logger logger = LoggerFactory.getLogger(WeChatAppletUtil.class);

    /**
     * 获取用户OPENID
     * @param loginCode
     * @return
     * @throws Exception
     */
    public static String getOpenId(String loginCode, String appId, String appSecret) throws Exception{
        appId = getUserAppletDecrypt(appId);
        appSecret = getUserAppletDecrypt(appSecret);
        //获取微信授权信息
        String result = toSendOutHttpRequest(loginCode, appId, appSecret);
        logger.info("result:{}",result);
        JSONObject obj = JSONObject.parseObject(result);
        if (obj.containsKey("errcode")) {
            logger.error("code:{},换取sessionKey失败", obj.get("errmsg"));
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
        appId = getUserAppletDecrypt(appId);
        appSecret = getUserAppletDecrypt(appSecret);
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=##APPID&secret=##APPSECRET";
        url = url.replace("##APPID", appId)
                .replace("##APPSECRET", appSecret);
        String context = HttpUtil.doGet(url);
        logger.info("【获取小程序AccessToken返回报文】{}", context);
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
        logger.info("【获取小程序二维码】{}", obj);
        return obj;
    }

    /**
     * 解密小程序信息
     * @param str
     * @return
     * @throws Exception
     */
    private static String getUserAppletDecrypt(String str) throws Exception{
        byte[] bytes = Base64.decode(str);
        PrivateKey pri = RsaUtil.getPriKey(RsaUtil.APPLET_MANAGE_PRIVATE_PATH, "RSA");
        byte[] dStr = RsaUtil.decrypt(bytes, pri, 2048, 11,"RSA/ECB/PKCS1Padding");
        return new String(dStr, "GBK");//GB2312
    }

    /**
     * 发送小程序授权请求
     * @param code
     * @return
     */
    public static String toSendOutHttpRequest(String code, String appId, String appSecret) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        //发送POST请求
        String context = HttpUtil.doPost(url);
        logger.info("【微信授权登录】{}", context);
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
        logger.info("【获取定位信息链接】" + url);
        //发送POST请求
        String context = HttpUtil.doGet(url);
//        logger.info("【获取位置详情】{}",context);
        return context;
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


    /**
     * java生成随机数字和字母组合
     *
     * @param length [生成随机数的长度]
     * @return
     */
    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
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

    /**
     * 修改路径格式，适应微信小程序访问
     *
     * @param path
     * @return
     */
    public static String updatePath(String path) {
        String paths = "";
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == '\\') {
                paths += "/";
            } else {
                paths += c;
            }
        }
        return paths;
    }
}
