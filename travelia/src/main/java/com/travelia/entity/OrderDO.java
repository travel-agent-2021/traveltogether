package com.travelia.entity;

import java.math.BigDecimal;

public class OrderDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_user_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private Integer orderUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_agency_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private Integer orderAgencyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.item_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private Integer itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_create_date
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private String orderCreateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_price
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private BigDecimal orderPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_status
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private Integer orderStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_detail
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private String orderDetail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_travelers
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private Integer orderTravelers;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_id
     *
     * @return the value of order_info.order_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_id
     *
     * @param orderId the value for order_info.order_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_user_id
     *
     * @return the value of order_info.order_user_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public Integer getOrderUserId() {
        return orderUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_user_id
     *
     * @param orderUserId the value for order_info.order_user_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_agency_id
     *
     * @return the value of order_info.order_agency_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public Integer getOrderAgencyId() {
        return orderAgencyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_agency_id
     *
     * @param orderAgencyId the value for order_info.order_agency_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setOrderAgencyId(Integer orderAgencyId) {
        this.orderAgencyId = orderAgencyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.item_id
     *
     * @return the value of order_info.item_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.item_id
     *
     * @param itemId the value for order_info.item_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_create_date
     *
     * @return the value of order_info.order_create_date
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public String getOrderCreateDate() {
        return orderCreateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_create_date
     *
     * @param orderCreateDate the value for order_info.order_create_date
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setOrderCreateDate(String orderCreateDate) {
        this.orderCreateDate = orderCreateDate == null ? null : orderCreateDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_price
     *
     * @return the value of order_info.order_price
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_price
     *
     * @param orderPrice the value for order_info.order_price
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_status
     *
     * @return the value of order_info.order_status
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_status
     *
     * @param orderStatus the value for order_info.order_status
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_detail
     *
     * @return the value of order_info.order_detail
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public String getOrderDetail() {
        return orderDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_detail
     *
     * @param orderDetail the value for order_info.order_detail
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail == null ? null : orderDetail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_travelers
     *
     * @return the value of order_info.order_travelers
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public Integer getOrderTravelers() {
        return orderTravelers;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_travelers
     *
     * @param orderTravelers the value for order_info.order_travelers
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setOrderTravelers(Integer orderTravelers) {
        this.orderTravelers = orderTravelers;
    }
}