package com.travelia.entity;

public class GroupDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_info.group_id
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    private Integer groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_info.start_date
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    private String startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_info.end_date
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    private String endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_info.route_id
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    private Integer routeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_info.current_amount
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    private Integer currentAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_info.state
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    private Integer state;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_info.group_id
     *
     * @return the value of group_info.group_id
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_info.group_id
     *
     * @param groupId the value for group_info.group_id
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_info.start_date
     *
     * @return the value of group_info.start_date
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_info.start_date
     *
     * @param startDate the value for group_info.start_date
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_info.end_date
     *
     * @return the value of group_info.end_date
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_info.end_date
     *
     * @param endDate the value for group_info.end_date
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_info.route_id
     *
     * @return the value of group_info.route_id
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public Integer getRouteId() {
        return routeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_info.route_id
     *
     * @param routeId the value for group_info.route_id
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_info.current_amount
     *
     * @return the value of group_info.current_amount
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public Integer getCurrentAmount() {
        return currentAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_info.current_amount
     *
     * @param currentAmount the value for group_info.current_amount
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public void setCurrentAmount(Integer currentAmount) {
        this.currentAmount = currentAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_info.state
     *
     * @return the value of group_info.state
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_info.state
     *
     * @param state the value for group_info.state
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    public void setState(Integer state) {
        this.state = state;
    }
}