package com.travelia.service.model;

import java.math.BigDecimal;

/**
 * 订单模型
 */
public class OrderModel {

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 创建日期
     */
    private String orderCreateDate;


    /**
     * 订单总价
     */
    private BigDecimal orderPrice;

    /**
     * 订单状态
     * 0：未付款
     * 1：已付款未完成
     * 2：已付款已完成
     * 3：订单被取消
     */
    private Integer orderStatus;

    /**
     * 订单用户名称
     */
    private String username;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 商品ID
     */
    private Integer itemId;
    /**
     * 经销商ID
     */
    private Integer agencyId;
    /**
     * 订单所属旅行社名称
     */
    private Integer agencyTitle;

    /**
     * 旅游人数
     */
    private Integer orderTravelers;

    /**
     * 其他补充信息
     */
    private String orderDetail;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    /**
     * getters and setters
     */


    public String getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(String orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Integer getOrderTravelers() {
        return orderTravelers;
    }

    public void setOrderTravelers(Integer orderTravelers) {
        this.orderTravelers = orderTravelers;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAgencyTitle() {
        return agencyTitle;
    }

    public void setAgencyTitle(Integer agencyTitle) {
        this.agencyTitle = agencyTitle;
    }
}
