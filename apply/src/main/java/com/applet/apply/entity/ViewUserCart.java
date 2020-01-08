package com.applet.apply.entity;

import java.io.Serializable;
import java.util.Date;

public class ViewUserCart implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.wx_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private Integer wxId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.applet_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private Integer appletId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.goods_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private Integer goodsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.goods_name
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private String goodsName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.discount
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private Integer discount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.if_discount
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private Boolean ifDiscount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.describe_str
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private String describeStr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.specs_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private Integer specsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.specs_src
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private String specsSrc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.specs_text
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private String specsText;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.sell_price
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private Double sellPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.amount
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private Integer amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_user_cart.update_time
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.id
     *
     * @return the value of view_user_cart.id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.id
     *
     * @param id the value for view_user_cart.id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.wx_id
     *
     * @return the value of view_user_cart.wx_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public Integer getWxId() {
        return wxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.wx_id
     *
     * @param wxId the value for view_user_cart.wx_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setWxId(Integer wxId) {
        this.wxId = wxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.applet_id
     *
     * @return the value of view_user_cart.applet_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public Integer getAppletId() {
        return appletId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.applet_id
     *
     * @param appletId the value for view_user_cart.applet_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setAppletId(Integer appletId) {
        this.appletId = appletId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.goods_id
     *
     * @return the value of view_user_cart.goods_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.goods_id
     *
     * @param goodsId the value for view_user_cart.goods_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.goods_name
     *
     * @return the value of view_user_cart.goods_name
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.goods_name
     *
     * @param goodsName the value for view_user_cart.goods_name
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.discount
     *
     * @return the value of view_user_cart.discount
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.discount
     *
     * @param discount the value for view_user_cart.discount
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.if_discount
     *
     * @return the value of view_user_cart.if_discount
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public Boolean getIfDiscount() {
        return ifDiscount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.if_discount
     *
     * @param ifDiscount the value for view_user_cart.if_discount
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setIfDiscount(Boolean ifDiscount) {
        this.ifDiscount = ifDiscount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.describe_str
     *
     * @return the value of view_user_cart.describe_str
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public String getDescribeStr() {
        return describeStr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.describe_str
     *
     * @param describeStr the value for view_user_cart.describe_str
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setDescribeStr(String describeStr) {
        this.describeStr = describeStr == null ? null : describeStr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.specs_id
     *
     * @return the value of view_user_cart.specs_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public Integer getSpecsId() {
        return specsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.specs_id
     *
     * @param specsId the value for view_user_cart.specs_id
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setSpecsId(Integer specsId) {
        this.specsId = specsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.specs_src
     *
     * @return the value of view_user_cart.specs_src
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public String getSpecsSrc() {
        return specsSrc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.specs_src
     *
     * @param specsSrc the value for view_user_cart.specs_src
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setSpecsSrc(String specsSrc) {
        this.specsSrc = specsSrc == null ? null : specsSrc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.specs_text
     *
     * @return the value of view_user_cart.specs_text
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public String getSpecsText() {
        return specsText;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.specs_text
     *
     * @param specsText the value for view_user_cart.specs_text
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setSpecsText(String specsText) {
        this.specsText = specsText == null ? null : specsText.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.sell_price
     *
     * @return the value of view_user_cart.sell_price
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public Double getSellPrice() {
        return sellPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.sell_price
     *
     * @param sellPrice the value for view_user_cart.sell_price
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.amount
     *
     * @return the value of view_user_cart.amount
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.amount
     *
     * @param amount the value for view_user_cart.amount
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_user_cart.update_time
     *
     * @return the value of view_user_cart.update_time
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_user_cart.update_time
     *
     * @param updateTime the value for view_user_cart.update_time
     *
     * @mbg.generated Sun Jan 05 15:05:03 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
