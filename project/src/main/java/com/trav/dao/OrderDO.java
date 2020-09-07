package com.trav.dao;

import java.math.BigDecimal;

public class OrderDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_id
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    private String orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.username
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.telephone
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    private String telephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.tourist_num
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    private Integer touristNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.agency_id
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    private Integer agencyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.total_cost
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    private BigDecimal totalCost;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.group_id
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    private Integer groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.state
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    private Integer state;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_id
     *
     * @return the value of order_info.order_id
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_id
     *
     * @param orderId the value for order_info.order_id
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.username
     *
     * @return the value of order_info.username
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.username
     *
     * @param username the value for order_info.username
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.telephone
     *
     * @return the value of order_info.telephone
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.telephone
     *
     * @param telephone the value for order_info.telephone
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.tourist_num
     *
     * @return the value of order_info.tourist_num
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public Integer getTouristNum() {
        return touristNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.tourist_num
     *
     * @param touristNum the value for order_info.tourist_num
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public void setTouristNum(Integer touristNum) {
        this.touristNum = touristNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.agency_id
     *
     * @return the value of order_info.agency_id
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public Integer getAgencyId() {
        return agencyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.agency_id
     *
     * @param agencyId the value for order_info.agency_id
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.total_cost
     *
     * @return the value of order_info.total_cost
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.total_cost
     *
     * @param totalCost the value for order_info.total_cost
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.group_id
     *
     * @return the value of order_info.group_id
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.group_id
     *
     * @param groupId the value for order_info.group_id
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.state
     *
     * @return the value of order_info.state
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.state
     *
     * @param state the value for order_info.state
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    public void setState(Integer state) {
        this.state = state;
    }
}