package com.trav.service;

import com.trav.service.model.RouteModel;

import java.util.List;

public interface RouteService {

    List<RouteModel> getAllRoute();

    RouteModel getRouteByCityName(String cityName);

}
