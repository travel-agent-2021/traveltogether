package com.trav.service.model;

import java.math.BigDecimal;

public class RouteModel {

    private Integer routeId;

    private String routeTitle;

    private String imageSrc;

    private String cityName;

    private Integer duration;

    private String transportation;

    private BigDecimal price;

    private Integer priceDetailId;

    private String startDate;

    private String endDate;

    private Integer touristMin;

    private Integer touristMax;

    private Integer agencyId;

    private String detail;

    private String agencyName;

    private String telephone;

    private String location;

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteTitle() {
        return routeTitle;
    }

    public void setRouteTitle(String routeTitle) {
        this.routeTitle = routeTitle;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPriceDetailId() {
        return priceDetailId;
    }

    public void setPriceDetailId(Integer priceDetailId) {
        this.priceDetailId = priceDetailId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getTouristMin() {
        return touristMin;
    }

    public void setTouristMin(Integer touristMin) {
        this.touristMin = touristMin;
    }

    public Integer getTouristMax() {
        return touristMax;
    }

    public void setTouristMax(Integer touristMax) {
        this.touristMax = touristMax;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
