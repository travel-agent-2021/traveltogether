package com.travelia.service;

import com.travelia.entity.ItemDO;
import com.travelia.error.BusinessException;
import com.travelia.service.model.CityModel;
import com.travelia.service.model.ItemModel;

import java.util.List;

public interface ItemService {

    ItemModel getItemByItemId(Integer itemId);

    List<ItemModel> getAllItems();

    List<ItemModel> getCheckedItems();

    List<ItemModel> getItemsByAgencyId(Integer agencyId) throws BusinessException;

    List<ItemModel> getItemsOrderByTotalOrderTimesDESC(Integer agencyId) throws BusinessException;

    List<ItemModel> getItemsOrderByCreateDateDESC() throws BusinessException;

    List<ItemModel> getRelatedItems(ItemModel itemModel) throws BusinessException;

    List<ItemModel> getItemsByOptions(Integer checkStatus, Integer agencyId) throws BusinessException;

    List<ItemModel> searchItemsByKeyword(String keyword) throws BusinessException;

    int insertItem(ItemModel itemModel) throws BusinessException;

    int deleteItem(ItemModel itemModel) throws BusinessException;

    int updateItemById(ItemModel itemModel) throws BusinessException;

    List<ItemModel> getItemsByClickTimesDESC(Integer agencyId) throws BusinessException;

}
