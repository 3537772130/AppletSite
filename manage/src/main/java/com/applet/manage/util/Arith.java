package com.applet.manage.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 数字计算工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-19 17:28
 **/
public class Arith {

    /**
     *@param DEF_DIV_SCALE
     *定义默认除法运算精度,精度越高，运算结果越精确，但效率会有所降低
     */
    //private static final int DEF_DIV_SCALE =6000;

    /**
     * 功能：BigDecimal类型的加法运算。
     *
     * @param s1 被加数
     * @param s2 加数
     * @return 两个参数的和
     */
    public static double add(double s1, double s2) {
        BigDecimal b1 = new BigDecimal(Double.toString(s1));
        BigDecimal b2 = new BigDecimal(Double.toString(s2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 功能：BigDecimal类型的减法运算。
     *
     * @param s1 被减数
     * @param s2 减数
     * @return 两个参数的差
     */
    public static double sub(double s1, double s2) {
        BigDecimal b1 = new BigDecimal(Double.toString(s1));
        BigDecimal b2 = new BigDecimal(Double.toString(s2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 功能：BigDecimal类型的乘法运算。
     *
     * @param s1 被乘数
     * @param s2 乘数
     * @return 两个参数的积
     */
    public static double mul(double s1, double s2) {
        BigDecimal b1 = new BigDecimal(Double.toString(s1));
        BigDecimal b2 = new BigDecimal(Double.toString(s2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 功能：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param s1    被除数 (前面的)
     * @param s2    除数 	 (后面的)
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double s1, double s2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");
        }

        BigDecimal b1 = new BigDecimal(Double.toString(s1));
        BigDecimal b2 = new BigDecimal(Double.toString(s2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 向下取值
     * 功能：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param s1    被除数
     * @param s2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double divDown(double s1, double s2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");
        }

        BigDecimal b1 = new BigDecimal(Double.toString(s1));
        BigDecimal b2 = new BigDecimal(Double.toString(s2));
        return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * 功能：提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");

        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 功能：提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double roundDown(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");

        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        return b.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * 按月还款
     * excel 中rate()函数
     *
     * @param a   现值
     * @param b   年金
     * @param c   期数
     * @param cnt 循环次数
     * @param ina 精确到小数点后10位
     * @return
     */
    public static double excelRate(double a, double b, double c, int cnt, int ina) {
        double rate = 1, x, jd = 0.1, side = 0.1, i = 1;
        do {
            x = a / b - (Math.pow(1 + rate, c) - 1) / (Math.pow(rate + 1, c) * rate);
            if (x * side > 0) {
                side = -side;
                jd *= 10;
            }
            rate += side / jd;
        } while (i++ < cnt && Math.abs(x) >= 1 / Math.pow(10, ina));
        if (i > cnt) return Double.NaN;
        return rate;
    }


    /**
     * 每月付息，到期还本
     *
     * @param planTotal
     * @param putIn
     * @param time      月份
     *                  年平均收益率 = (预计到期本金收益和 / 计划投资金额) ^ (1 / 投资年限) - 1
     * @return
     */
    public static double rateTotal(double planTotal, double putIn, int time) {
        float year = time * 1.0f / 12;//月份转换成年

        double rate = Math.pow(planTotal / putIn, 1 / year) - 1;
        return rate;
    }

    /**
     * 转换成字符串，如果大于一万，则以万为单位
     *
     * @return
     */
    public static String doubleToString(double d) {
        if (d < 10000) {
            return String.format("%.2f", d);
        }

        return Math.round(d / 10000) + "万";
    }

    /**
     * 等额本息返回总利息,及每一期利息
     * @param limit 金额
     * @param monthRate 月息
     * @param month 期数/月
     * @return
     */
    public static Map getPrincipalAndInterestEqualsTotalInterest(double limit, double monthRate, int month) {
        List<Double> monthInterestList=new ArrayList<Double>();

        BigDecimal invest = new BigDecimal(limit); // 本金
        // 每月利息  = 剩余本金 x 贷款月利率
        BigDecimal monthInterest;
        BigDecimal capital = invest;
        BigDecimal tmpCapital = BigDecimal.ZERO;
        BigDecimal sumInterest = BigDecimal.ZERO;
        for (int i = 1; i < month + 1; i++) {
            capital = capital.subtract(tmpCapital);
            monthInterest = capital.multiply(new BigDecimal(monthRate)).setScale(2, BigDecimal
                    .ROUND_HALF_UP);
            tmpCapital = invest.multiply(new BigDecimal(monthRate * (Math.pow((1 + monthRate), i
                    - 1)))).divide(new BigDecimal(Math.pow(1 + monthRate, month) - 1), 2,
                    BigDecimal.ROUND_HALF_UP);
            System.out.println("第" + i + "月利息： " + monthInterest);

            sumInterest = sumInterest.add(monthInterest);
            monthInterestList.add(monthInterest.doubleValue());
        }
        System.out.println("利息总和：" + sumInterest);
        Map map=new HashMap();
        map.put("monthInterestList",monthInterestList);
        map.put("monthInterestSum",sumInterest);
        return map;
    }

    /**
     * 等额本息返回每期应还金额,及每一期本金
     * @param limit 金额
     * @param monthRate 月息
     * @param month 期数/月
     * @return
     */
    public static Map getPrincipalAndInterestEqualsMonthIncome(double limit, double monthRate, int month){
        List<Double> monthIncomeList=new ArrayList<Double>(month);
        List<Double> monthCapitalList=new ArrayList<Double>(month);

        BigDecimal invest = new BigDecimal(limit); // 本金
        monthRate = monthRate;
        month = month;
        double monthIncomeD=0d;
        // 每月本息金额  = (本金×月利率×(1＋月利率)＾还款月数)÷ ((1＋月利率)＾还款月数-1)
        BigDecimal monthIncome = invest.multiply(new BigDecimal(monthRate * Math.pow(1 +
                monthRate, month))).divide(new BigDecimal(Math.pow(1 + monthRate, month) - 1), 2,
                BigDecimal.ROUND_HALF_UP);
        monthIncomeD=monthIncome.doubleValue();
        System.out.println("每月本息金额 : " + monthIncome);
        System.out.println("---------------------------------------------------");
        // 每月本金 = 本金×月利率×(1+月利率)^(还款月序号-1)÷((1+月利率)^还款月数-1)
        BigDecimal monthCapital;
        BigDecimal sumCapital = BigDecimal.ZERO;
        for (int i = 1; i < month + 1; i++) {
            monthCapital = invest.multiply(new BigDecimal(monthRate * (Math.pow((1 + monthRate),
                    i - 1)))).divide(new BigDecimal(Math.pow(1 + monthRate, month) - 1), 2,
                    BigDecimal.ROUND_HALF_UP);
            System.out.println("第" + i + "月本金： " + monthCapital);
            sumCapital = sumCapital.add(monthCapital);

            monthIncomeList.add(monthIncomeD);
            monthCapitalList.add(monthCapital.doubleValue());
        }
        System.out.println("总本金:"+sumCapital);
        //判断计算是否有出入
        //若少+最后一期，多减第一期
        double differ=sub(sumCapital.doubleValue(),limit);
        //同步等额本息网上处理方式之一,多+在总利息,少-在总利息,明细不做更改
        Map map=new HashMap();
        map.put("monthIncomeList",monthIncomeList);
        map.put("monthCapitalList",monthCapitalList);
        map.put("differ",differ);
        return map;
    }
}
