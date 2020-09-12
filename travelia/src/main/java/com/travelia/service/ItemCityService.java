package com.travelia.service;

import com.travelia.error.BusinessException;
import com.travelia.service.model.CityModel;

import java.util.List;

public interface ItemCityService {

    int addItemCityDOKey(Integer itemId, Integer cityId);

    int deleteByItemId(Integer itemId);

    int addItemCityDOKeys(Integer itemId, List<CityModel> cityModelList) throws BusinessException;

}
