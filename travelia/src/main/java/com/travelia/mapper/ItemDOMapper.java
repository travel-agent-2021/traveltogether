package com.travelia.mapper;

import com.travelia.entity.ItemDO;

public interface ItemDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    int deleteByPrimaryKey(Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    int insert(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    int insertSelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    ItemDO selectByPrimaryKey(Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    int updateByPrimaryKeySelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated Tue Sep 08 10:22:17 CST 2020
     */
    int updateByPrimaryKey(ItemDO record);
}