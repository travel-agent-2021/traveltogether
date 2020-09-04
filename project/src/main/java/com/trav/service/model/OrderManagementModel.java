package com.trav.service.model;

import java.math.BigDecimal;

public class OrderManagementModel {

    private Integer id;//订单号

    private String username;//客户名

    private String dealerName;//商户名称

    private String review;//评论情况

    private BigDecimal totalCost;//订单金额

    private String telephone;//客户电话

    private String detail;//详细信息


    public java.lang.Integer getId() {
        return id;
    }

    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.String getDealerName() {
        return dealerName;
    }

    public void setDealerName(java.lang.String dealerName) {
        this.dealerName = dealerName;
    }

    public java.lang.String getReview() {
        return review;
    }

    public void setReview(java.lang.String review) {
        this.review = review;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public java.lang.String getTelephone() {
        return telephone;
    }

    public void setTelephone(java.lang.String telephone) {
        this.telephone = telephone;
    }

    public java.lang.String getDetail() {
        return detail;
    }

    public void setDetail(java.lang.String detail) {
        this.detail = detail;
    }
}
