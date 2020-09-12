package com.travelia.service.Impl;

import com.travelia.entity.ItemCityDOKey;
import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.mapper.ItemCityDOMapper;
import com.travelia.service.ItemCityService;
import com.travelia.service.model.CityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemCityServiceImpl implements ItemCityService {


    @Autowired
    private ItemCityDOMapper itemCityDOMapper;

    @Override
    @Transactional
    public int addItemCityDOKey(Integer itemId, Integer cityId) {
        if (cityId == null || itemId == null) {
            return -1;
        }
        ItemCityDOKey itemCityDOKey = new ItemCityDOKey();
        itemCityDOKey.setItemId(itemId);
        itemCityDOKey.setCityId(cityId);
        itemCityDOMapper.insertSelective(itemCityDOKey);
        return 0;
    }

    @Override
    @Transactional
    public int deleteByItemId(Integer itemId) {
        if (itemId == null) {
            return -1;
        }
        itemCityDOMapper.deleteByItemId(itemId);
        return 0;
    }

    @Override
    public int addItemCityDOKeys(Integer itemId, List<CityModel> cityModelList) throws BusinessException {
        if (itemId == null) {
            return -1;
        }
        if (cityModelList == null) {
            return -1;
        }
        for (CityModel cityModel: cityModelList) {
            addItemCityDOKey(itemId, cityModel.getCityId());
        }
        return 0;
    }


}
