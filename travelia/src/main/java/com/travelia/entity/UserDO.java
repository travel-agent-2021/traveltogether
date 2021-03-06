package com.travelia.entity;

public class UserDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.user_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.username
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.encrypt_password
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private String encryptPassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.age
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.gender
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private Integer gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.user_telephone
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private String userTelephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.user_email
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private String userEmail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.birthday
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private String birthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.user_image_source
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    private String userImageSource;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.user_id
     *
     * @return the value of user_info.user_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.user_id
     *
     * @param userId the value for user_info.user_id
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.username
     *
     * @return the value of user_info.username
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.username
     *
     * @param username the value for user_info.username
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.encrypt_password
     *
     * @return the value of user_info.encrypt_password
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public String getEncryptPassword() {
        return encryptPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.encrypt_password
     *
     * @param encryptPassword the value for user_info.encrypt_password
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword == null ? null : encryptPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.age
     *
     * @return the value of user_info.age
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.age
     *
     * @param age the value for user_info.age
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.gender
     *
     * @return the value of user_info.gender
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.gender
     *
     * @param gender the value for user_info.gender
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.user_telephone
     *
     * @return the value of user_info.user_telephone
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public String getUserTelephone() {
        return userTelephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.user_telephone
     *
     * @param userTelephone the value for user_info.user_telephone
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone == null ? null : userTelephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.user_email
     *
     * @return the value of user_info.user_email
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.user_email
     *
     * @param userEmail the value for user_info.user_email
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.birthday
     *
     * @return the value of user_info.birthday
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.birthday
     *
     * @param birthday the value for user_info.birthday
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.user_image_source
     *
     * @return the value of user_info.user_image_source
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public String getUserImageSource() {
        return userImageSource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.user_image_source
     *
     * @param userImageSource the value for user_info.user_image_source
     *
     * @mbg.generated Tue Sep 08 10:36:05 CST 2020
     */
    public void setUserImageSource(String userImageSource) {
        this.userImageSource = userImageSource == null ? null : userImageSource.trim();
    }
}