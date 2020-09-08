package com.travelia.service.model;

/**
 * 拼团信息模型
 */
public class GroupModel {

    /**
     * 拼团编号
     */
    private String groupId;

    /**
     * 拼团商品ID
     */
    private String itemId;

    /**
     * 目前拼团人数
     */
    private Integer groupCurrentTravelers;

    /**
     * 拼团创建日期
     */
    private String groupCreateDate;

    /**
     * 拼团结束日期
     */
    private String groupEndDate;

    /**
     * 拼团状态
     * 0: 人数未满
     * 1：拼团成功
     * 2：拼团失败
     */
    private Integer groupStatus;

    /**
     * 拼团需要人数
     */
    private Integer targetTravelers;

    /**
     * getters and setters
     */
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getGroupCurrentTravelers() {
        return groupCurrentTravelers;
    }

    public void setGroupCurrentTravelers(Integer groupCurrentTravelers) {
        this.groupCurrentTravelers = groupCurrentTravelers;
    }

    public String getGroupCreateDate() {
        return groupCreateDate;
    }

    public void setGroupCreateDate(String groupCreateDate) {
        this.groupCreateDate = groupCreateDate;
    }

    public String getGroupEndDate() {
        return groupEndDate;
    }

    public void setGroupEndDate(String groupEndDate) {
        this.groupEndDate = groupEndDate;
    }

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Integer getTargetTravelers() {
        return targetTravelers;
    }

    public void setTargetTravelers(Integer targetTravelers) {
        this.targetTravelers = targetTravelers;
    }
}
