package com.trav.controller;


import com.trav.response.CommonReturnType;
import com.trav.service.RouteService;
import com.trav.service.model.RouteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/route")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class RouteController extends BaseController {

    @Autowired
    private RouteService routeService;

    @RequestMapping(value="/index", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getRouteList() {
        List<RouteModel> routeModels =  routeService.getAllRoute();

        return CommonReturnType.create(routeModels, "success");
    }


}
