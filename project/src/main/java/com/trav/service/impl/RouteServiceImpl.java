package com.trav.service.impl;

import com.trav.dao.AgencyDO;
import com.trav.dao.CityDO;
import com.trav.dao.RouteDO;
import com.trav.mapper.AgencyDOMapper;
import com.trav.mapper.CityDOMapper;
import com.trav.mapper.RouteDOMapper;
import com.trav.service.RouteService;
import com.trav.service.model.RouteModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteDOMapper routeDOMapper;

    @Autowired
    AgencyDOMapper agencyDOMapper;


    @Override
    public List<RouteModel> getAllRoute() {
        List<RouteDO> routes = routeDOMapper.selectAllRoutes();
        List<RouteModel> routeModels = new ArrayList<>();
        for (RouteDO routeDO: routes) {
            AgencyDO agencyDO = agencyDOMapper.selectByPrimaryKey(routeDO.getAgencyId());
            RouteModel routeModel = convertFromDO2Model(routeDO, agencyDO);
            routeModels.add(routeModel);
        }
        return routeModels;
    }

    @Override
    public RouteModel getRouteByCityName(String cityName) {
        return null;
    }


    private RouteModel convertFromDO2Model(RouteDO routeDO, AgencyDO agencyDO) {
        if (routeDO == null) {
            return null;
        }
        if (agencyDO == null) {
            return null;
        }
        RouteModel routeModel = new RouteModel();
        BeanUtils.copyProperties(routeDO, routeModel);
        BeanUtils.copyProperties(agencyDO, routeModel);
        return routeModel;
    }

}
