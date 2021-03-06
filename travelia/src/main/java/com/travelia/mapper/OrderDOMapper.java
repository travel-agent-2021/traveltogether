package com.travelia.mapper;

import com.travelia.entity.OrderDO;

import java.util.List;

public interface OrderDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed Sep 16 11:21:08 CST 2020
     */
    int deleteByPrimaryKey(Integer orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed Sep 16 11:21:08 CST 2020
     */
    int insert(OrderDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed Sep 16 11:21:08 CST 2020
     */
    int insertSelective(OrderDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed Sep 16 11:21:08 CST 2020
     */
    OrderDO selectByPrimaryKey(Integer orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed Sep 16 11:21:08 CST 2020
     */
    int updateByPrimaryKeySelective(OrderDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed Sep 16 11:21:08 CST 2020
     */
    int updateByPrimaryKey(OrderDO record);


    List<OrderDO> selectAllOrders();


    List<OrderDO> selectByAgencyId(Integer agencyId);


    List<OrderDO> selectByAgencyAccount(String agencyAccount);


    List<OrderDO> selectByUserId(Integer userId);


    int selectCountByCreateDate(String date,Integer agencyId);

    Integer selectPriceSumByCreateDate(String date,Integer agencyId);


    List<OrderDO> selectByOptions(Integer agencyId, Integer orderStatus);
}