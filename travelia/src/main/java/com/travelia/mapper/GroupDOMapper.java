package com.travelia.mapper;

import com.travelia.entity.GroupDO;

public interface GroupDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    int deleteByPrimaryKey(Integer groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    int insert(GroupDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    int insertSelective(GroupDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    GroupDO selectByPrimaryKey(Integer groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    int updateByPrimaryKeySelective(GroupDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    int updateByPrimaryKey(GroupDO record);
}