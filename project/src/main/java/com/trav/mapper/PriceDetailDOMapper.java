package com.trav.mapper;

import com.trav.dao.PriceDetailDO;

public interface PriceDetailDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table price_detail
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    int deleteByPrimaryKey(Integer priceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table price_detail
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    int insert(PriceDetailDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table price_detail
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    int insertSelective(PriceDetailDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table price_detail
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    PriceDetailDO selectByPrimaryKey(Integer priceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table price_detail
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    int updateByPrimaryKeySelective(PriceDetailDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table price_detail
     *
     * @mbg.generated Fri Sep 04 15:58:49 CST 2020
     */
    int updateByPrimaryKey(PriceDetailDO record);
}