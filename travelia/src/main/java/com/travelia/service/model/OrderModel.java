package com.travelia.service.model;

import java.math.BigDecimal;

/**
 * 订单模型
 */
public class OrderModel {

    /**
     * 订单ID
     */
    private String orderId;

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
     * 订单用户ID
     */
    private String orderUserName;

    /**
     * 订单所属旅行社ID
     */
    private Integer orderAgencyTitle;

    /**
     * 旅游人数
     */
    private Integer orderTravelers;

    /**
     * 其他补充信息
     */
    private String orderDetail;

    /**
     * getters and setters
     */
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public Integer getOrderAgencyTitle() {
        return orderAgencyTitle;
    }

    public void setOrderAgencyTitle(Integer orderAgencyTitle) {
        this.orderAgencyTitle = orderAgencyTitle;
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

}
