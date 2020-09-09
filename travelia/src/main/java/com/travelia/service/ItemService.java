package com.travelia.service;

import com.travelia.entity.ItemDO;
import com.travelia.error.BusinessException;
import com.travelia.service.model.ItemModel;

import java.util.List;

public interface ItemService {

    ItemModel getItemByItemId(Integer itemId);

    List<ItemModel> getAllItems();

    List<ItemModel> getItemsByAgencyId(Integer agencyId);

    int insertItem(ItemModel itemModel) throws BusinessException;

    int deleteItem(ItemModel itemModel) throws BusinessException;

    int updateItemById(ItemModel itemModel) throws BusinessException;

}
