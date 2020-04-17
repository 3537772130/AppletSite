package com.applet.common.util;

import jodd.datetime.JDateTime;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @program: SpringBootDemo
 * @description: 随机数生成工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-19 17:17
 **/
public class RandomUtil {

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    /**
     * 生成32位随机字符串
     * @return
     */
    public static String getRandomStr32() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     */
    public static String getSecondTimestamp(Date date) throws Exception{
        if (null == date) {
            throw new Exception();
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp).toString();
    }

    /**
     * yyyyMMddHHmmss+6位不重复随机数或序列，不能重复
     * @return
     */
    public static String getTimeStamp() {
        JDateTime now = new JDateTime();
        String str6 = ((int) ((Math.random() * 9 + 1) * 100000)) + "";
        return now.toString(Constants.DEFAULT_DATE_FORMAT_STAMP) + str6;
    }

    public static String getOrderTime() {
        JDateTime now = new JDateTime();
        return now.toString(Constants.DEFAULT_DATE_FORMAT_STAMP);
    }

    public static String orderEffTime() {
        JDateTime now = new JDateTime();
        return now.addMinute(15).toString(Constants.DEFAULT_DATE_FORMAT_STAMP);
    }

    public static Double getMshRdmRatio() {
        Random random = new Random();
        return Arith.round(0.3 + random.nextDouble() * 0.1, 2);
    }

    public static String getAgentOrder() {
        return "AP" + System.currentTimeMillis();
    }

    public static String getRemitBatchNo(String type) {
        return getOrderTime() + type;
    }

    /**
     * 生成既定长度的纯数字字符串
     *
     * @param length
     * @return
     */
    public static String getRandomStr(Integer length) {
        Random random = new Random();
        String str = "";
        for (int i = 0; i < length; i++) {
            str += random.nextInt(10);
        }
        return str;
    }

    public static String getWangSN() {
        return "PM" + new JDateTime().toString(Constants.DEFAULT_DATE_FORMAT_STAMP) + getRandomStr(6);
    }

    /**
     * 生成订单号
     * @param managerId
     * @return
     */
    public static String getMerNo(Integer managerId) {
        return managerId + "-" + new JDateTime().toString(Constants.DEFAULT_DATE_FORMAT_STAMP);
    }

    /**
     * 高汇通子商户号 机构上送开头1位必须位7，长度共计15位，最后四位为“0000”代表非，
     * 1、非实体商户（个人小微企业），商户编码采用店主手机号；
     * 2、 实体商户的商户编码由下游机构自行定义商户编码上送； 15 位机构号+15 位商户号，连锁商户的商户编码如下：
     * ____________________________________________________
     * |位 |1|         位02-11       |        位12-位15   |
     * |值 |8| 法人代表手机号后10位   |       连锁商户号   |
     * |值 |7| 下游自定义商遍         |       连锁商户号   |
     * ----------------------------------------------------
     * 说明：
     * 1、 非连锁商户，连锁商户号为“0000”；
     * 2、 连锁商户号如果不为“0000”，说明是连锁商户，要上送上级
     * 商户号字段“inviteMerNo” ；只支持连锁商户的父子关系；
     *
     * @param shopId
     * @return
     */
    public static String getGhtMerNo(Integer shopId) {
        String merNo = "7" + shopId + getRandomStr(15);
        return merNo.substring(0, 11) + "0000";
    }

    public static String getQyfCustomerNo(Integer applyId) {
        String merNo = "Q" + applyId + "000000000000000";
        return merNo.substring(0, 12);
    }

    public static String getSmsCode() {
        Random r = new Random();
        String smsCode = "";
        int x = r.nextInt(999999);
        if (x > 100000) {
            smsCode = x + "";
        } else {
            x = x + 100000;
            smsCode = x + "";
        }
        return smsCode;
    }

    /**
     * 随机指定范围内N个不重复的数
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n 随机数个数
     */
    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }




    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getMshRdmRatio());
        }
    }
}
