package com.travelia.mapper;

import com.travelia.entity.ItemDO;

import java.util.List;

public interface ItemDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    int deleteByPrimaryKey(Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    int insert(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    int insertSelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    ItemDO selectByPrimaryKey(Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    int updateByPrimaryKeySelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Wed Sep 16 14:53:48 CST 2020
     */
    int updateByPrimaryKey(ItemDO record);

    /**
     * 查询所有商品
     * @return
     */
    List<ItemDO> selectAllItems();


    /**
     * 查询审核通过的商品
     * @return
     */
    List<ItemDO> selectCheckedItems();

    /**
     * 根据AgencyId查询商品
     * @param agencyId
     * @return
     */
    List<ItemDO> selectByAgencyId(Integer agencyId);

    /**
     * 将商品按商品下单次数降序排列
     * @return
     */
    List<ItemDO> selectAllByOrderTimesDESC(Integer agencyId);

    /**
     * 将商品按时间降序排列
     * @return
     */
    List<ItemDO> selectAllByCreateDateDESC();

    /**
     * 查询所有包含itemName的商品
     * @param itemName
     * @return
     */
    List<ItemDO> selectLikeItemName(String itemName);

    /**
     *
     * @param checkStatus
     * @param agencyId
     * @return
     */
    List<ItemDO> selectByOptions(Integer checkStatus, Integer agencyId);

    /**
     *
     * @param agencyId
     * @return
     */
    List<ItemDO> selectAllByClickTimesDESC(Integer agencyId);
}