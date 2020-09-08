package com.travelia.service.model;


import java.util.List;

/**
 * 管理员模型
 */
public class AdminModel {

    /**
     * 管理员ID
     */
    private Integer adminId;

    /**
     * 管理员账号
     */
    private String adminAccount;

    /**
     * 管理员密码
     */
    private String encryptPassword;

    /**
     * getters and setters
     */
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }
}
