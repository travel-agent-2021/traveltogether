package com.travelia.service.model;

import java.util.List;

/**
 * 旅行社信息模型
 */
public class AgencyModel {

    /**
     * 旅行社ID
     */
    private Integer agencyId;

    /**
     * 旅行社账号
     */
    private String agencyAccount;

    /**
     * 旅行社账号
     */
    private String encryptPassword;

    /**
     * 旅行社名称
     */
    private String agencyTitle;

    /**
     * 旅行社图片
     */
    private String agencyImageSource;

    /**
     * 旅行社联系电话
     */
    private String agencyTelephone;

    /**
     * 旅行社联系地址
     */
    private String agencyAddress;

    /**
     * 旅行社email
     */
    private String agencyEmail;


    /**
     * getters and setters
     */
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyAccount() {
        return agencyAccount;
    }

    public void setAgencyAccount(String agencyAccount) {
        this.agencyAccount = agencyAccount;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    public String getAgencyTitle() {
        return agencyTitle;
    }

    public void setAgencyTitle(String agencyTitle) {
        this.agencyTitle = agencyTitle;
    }

    public String getAgencyImageSource() {
        return agencyImageSource;
    }

    public void setAgencyImageSource(String agencyImageSource) {
        this.agencyImageSource = agencyImageSource;
    }

    public String getAgencyTelephone() {
        return agencyTelephone;
    }

    public void setAgencyTelephone(String agencyTelephone) {
        this.agencyTelephone = agencyTelephone;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }

    public String getAgencyEmail() {
        return agencyEmail;
    }

    public void setAgencyEmail(String agencyEmail) {
        this.agencyEmail = agencyEmail;
    }

}
