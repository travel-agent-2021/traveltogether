package com.travelia.service.Impl;

import com.travelia.entity.CityDO;
import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.mapper.CityDOMapper;
import com.travelia.service.CityService;
import com.travelia.service.model.CityModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDOMapper cityDOMapper;

    /**
     * 返回所有城市信息
     * @return List<CityModel>
     * @throws BusinessException
     */
    @Override
    public List<CityModel> getAllCities() throws BusinessException {
        List<CityDO> cityDOList = cityDOMapper.selectAllCities();
        if (cityDOList == null) {
            throw new BusinessException(BusinessError.CITY_NOT_FOUND);
        }
        List<CityModel> cityModelList = new ArrayList<>();
        for (CityDO cityDO: cityDOList) {
            cityModelList.add(convertFromCityDO2Model(cityDO));
        }
        return cityModelList;
    }

    private CityModel convertFromCityDO2Model(CityDO cityDO) {
        if (cityDO == null) {
            return null;
        }
        CityModel cityModel = new CityModel();
        BeanUtils.copyProperties(cityDO, cityModel);
        return cityModel;
    }


}
