package com.travelia.service.model;

import java.math.BigDecimal;
import java.util.List;

/*
* 商品模型
* */
public class ItemModel {

    /**
     * 商品ID
     */
    private Integer itemId;

    /**
     * 商品名
     */
    private String itemName;

    /**
     * 商品创建日期
     */
    private String itemCreateDate;

    /**
     * 商品图片
     */
    private List<String> itemImageSources;

    /**
     *价格
     */
    private BigDecimal itemPrice;

    /**
     * 旅游时长
     */
    private Integer duration;

    /**
     * 包含城市
     */
    private List<CityModel> cityModels;

    /**
     * 最少游客数
     */
    private Integer min_tourists;

    /**
     * 最多游客数
     */
    private Integer max_tourists;

    /**
     * 旅行社ID
     */
    private Integer agencyId;

    /**
     * 旅行社名称
     */
    private String agencyTitle;

    /**
     * 旅行社地址
     */
    private String agencyAddress;

    /**
     * 旅行社电话
     */
    private String agencyTelephone;

    /**
     * 商品描述信息
     */
    private String itemDetail;

    /**
     * 商品总下单次数
     */
    private Integer totalOrderTimes;


    /**
     * getters and setters
     */
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCreateDate() {
        return itemCreateDate;
    }

    public void setItemCreateDate(String itemCreateDate) {
        this.itemCreateDate = itemCreateDate;
    }

    public List<String> getItemImageSources() {
        return itemImageSources;
    }

    public void setItemImageSources(List<String> itemImageSources) {
        this.itemImageSources = itemImageSources;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<CityModel> getCityModels() {
        return cityModels;
    }

    public void setCityModels(List<CityModel> cityModels) {
        this.cityModels = cityModels;
    }

    public Integer getMin_tourists() {
        return min_tourists;
    }

    public void setMin_tourists(Integer min_tourists) {
        this.min_tourists = min_tourists;
    }

    public Integer getMax_tourists() {
        return max_tourists;
    }

    public void setMax_tourists(Integer max_tourists) {
        this.max_tourists = max_tourists;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyTitle() {
        return agencyTitle;
    }

    public void setAgencyTitle(String agencyTitle) {
        this.agencyTitle = agencyTitle;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }

    public String getAgencyTelephone() {
        return agencyTelephone;
    }

    public void setAgencyTelephone(String agencyTelephone) {
        this.agencyTelephone = agencyTelephone;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public Integer getTotalOrderTimes() {
        return totalOrderTimes;
    }

    public void setTotalOrderTimes(Integer totalOrderTimes) {
        this.totalOrderTimes = totalOrderTimes;
    }
}