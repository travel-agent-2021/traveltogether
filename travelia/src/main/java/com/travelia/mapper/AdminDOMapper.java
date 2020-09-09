package com.travelia.mapper;

import com.travelia.entity.AdminDO;

public interface AdminDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_info
     *
     * @mbg.generated Wed Sep 09 09:04:12 CST 2020
     */
    int deleteByPrimaryKey(Integer adminId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_info
     *
     * @mbg.generated Wed Sep 09 09:04:12 CST 2020
     */
    int insert(AdminDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_info
     *
     * @mbg.generated Wed Sep 09 09:04:12 CST 2020
     */
    int insertSelective(AdminDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_info
     *
     * @mbg.generated Wed Sep 09 09:04:12 CST 2020
     */
    AdminDO selectByPrimaryKey(Integer adminId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_info
     *
     * @mbg.generated Wed Sep 09 09:04:12 CST 2020
     */
    int updateByPrimaryKeySelective(AdminDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_info
     *
     * @mbg.generated Wed Sep 09 09:04:12 CST 2020
     */
    int updateByPrimaryKey(AdminDO record);

    /**
     * 根据管理员账号搜索
     * @param adminAccount
     * @return
     */
    AdminDO selectByAdminAccount(String adminAccount);
}