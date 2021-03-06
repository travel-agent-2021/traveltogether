package com.travelia.mapper;

import com.travelia.entity.AgencyDO;
import com.travelia.entity.UserDO;

import java.util.List;

public interface AgencyDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agency_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    int deleteByPrimaryKey(Integer agencyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agency_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    int insert(AgencyDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agency_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    int insertSelective(AgencyDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agency_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    AgencyDO selectByPrimaryKey(Integer agencyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agency_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    int updateByPrimaryKeySelective(AgencyDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agency_info
     *
     * @mbg.generated Wed Sep 09 09:49:03 CST 2020
     */
    int updateByPrimaryKey(AgencyDO record);


    List<AgencyDO> selectAllAgencies();

    AgencyDO selectByAgencyAccount(String agencyAccount);

    AgencyDO selectByAgencyTelephone(String agencyTelephone);

    List<AgencyDO> selectLikeAgencyAccount(String agencyAccount);

    List<AgencyDO> selectLikeAccountTelephone(String agencyTelephone);
}