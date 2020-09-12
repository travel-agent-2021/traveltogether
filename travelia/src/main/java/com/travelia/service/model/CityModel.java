package com.travelia.service.model;

import java.util.Objects;

/**
 * 城市模型
 */
public class CityModel {

    /**
     * 城市编号
     */
    private Integer cityId;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 国内外
     * 0：国内
     * 1：国外
     */
    private Integer isDomestic;

    /**
     * 城市图片
     */
    private String cityImageSource;

    /**
     * getters and setters
     */
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(Integer isDomestic) {
        this.isDomestic = isDomestic;
    }

    public String getCityImageSource() {
        return cityImageSource;
    }

    public void setCityImageSource(String cityImageSource) {
        this.cityImageSource = cityImageSource;
    }

    /**
     * equals方法
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityModel cityModel = (CityModel) o;
        return cityId.equals(cityModel.cityId);
    }

    /**
     * hashcode
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }
}
