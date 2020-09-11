package com.travelia.service;

import com.travelia.error.BusinessException;
import com.travelia.service.model.CityModel;

import java.util.List;

public interface CityService {

    List<CityModel> getAllCities() throws BusinessException;

}
