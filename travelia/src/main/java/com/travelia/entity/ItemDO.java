package com.travelia.entity;

import java.math.BigDecimal;

public class ItemDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.item_id
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private Integer itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.item_name
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private String itemName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.agency_id
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private Integer agencyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.item_create_date
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private String itemCreateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.item_price
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private BigDecimal itemPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.duration
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private Integer duration;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.min_tourists
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private Integer minTourists;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.max_tourists
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private Integer maxTourists;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.item_detail
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private String itemDetail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.total_order_times
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private Integer totalOrderTimes;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.check_status
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private Integer checkStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.total_click_times
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    private Integer totalClickTimes;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.item_id
     *
     * @return the value of item_info.item_id
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.item_id
     *
     * @param itemId the value for item_info.item_id
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.item_name
     *
     * @return the value of item_info.item_name
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.item_name
     *
     * @param itemName the value for item_info.item_name
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.agency_id
     *
     * @return the value of item_info.agency_id
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public Integer getAgencyId() {
        return agencyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.agency_id
     *
     * @param agencyId the value for item_info.agency_id
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.item_create_date
     *
     * @return the value of item_info.item_create_date
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public String getItemCreateDate() {
        return itemCreateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.item_create_date
     *
     * @param itemCreateDate the value for item_info.item_create_date
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setItemCreateDate(String itemCreateDate) {
        this.itemCreateDate = itemCreateDate == null ? null : itemCreateDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.item_price
     *
     * @return the value of item_info.item_price
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.item_price
     *
     * @param itemPrice the value for item_info.item_price
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.duration
     *
     * @return the value of item_info.duration
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.duration
     *
     * @param duration the value for item_info.duration
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.min_tourists
     *
     * @return the value of item_info.min_tourists
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public Integer getMinTourists() {
        return minTourists;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.min_tourists
     *
     * @param minTourists the value for item_info.min_tourists
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setMinTourists(Integer minTourists) {
        this.minTourists = minTourists;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.max_tourists
     *
     * @return the value of item_info.max_tourists
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public Integer getMaxTourists() {
        return maxTourists;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.max_tourists
     *
     * @param maxTourists the value for item_info.max_tourists
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setMaxTourists(Integer maxTourists) {
        this.maxTourists = maxTourists;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.item_detail
     *
     * @return the value of item_info.item_detail
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public String getItemDetail() {
        return itemDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.item_detail
     *
     * @param itemDetail the value for item_info.item_detail
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail == null ? null : itemDetail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.total_order_times
     *
     * @return the value of item_info.total_order_times
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public Integer getTotalOrderTimes() {
        return totalOrderTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.total_order_times
     *
     * @param totalOrderTimes the value for item_info.total_order_times
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setTotalOrderTimes(Integer totalOrderTimes) {
        this.totalOrderTimes = totalOrderTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.check_status
     *
     * @return the value of item_info.check_status
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public Integer getCheckStatus() {
        return checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.check_status
     *
     * @param checkStatus the value for item_info.check_status
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.total_click_times
     *
     * @return the value of item_info.total_click_times
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public Integer getTotalClickTimes() {
        return totalClickTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.total_click_times
     *
     * @param totalClickTimes the value for item_info.total_click_times
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    public void setTotalClickTimes(Integer totalClickTimes) {
        this.totalClickTimes = totalClickTimes;
    }
}