package com.travelia.controller;


import com.travelia.error.BusinessException;
import com.travelia.response.CommonReturnType;
import com.travelia.service.OrderService;
import com.travelia.service.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/order")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取订单列表
     * @return 所有订单model
     * @throws BusinessException
     */
    @RequestMapping(value = "/getAllOrders", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getAllOrders() throws BusinessException {
        List<OrderModel> orders =  orderService.getAllOrders();
        System.out.println(orders.get(0).getOrderId()+orders.get(0).getOrderCreateDate());
        return CommonReturnType.create(orders, "success");
    }

    /**
     * 根据经订单ID获取订单
     * @return 所有订单model
     * @throws BusinessException
     */
    @RequestMapping(value = "/getOrderById", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getOrderById(@RequestParam(name = "orderId") Integer orderId) throws BusinessException {
        OrderModel orderModel = orderService.getOrderById(orderId);
        System.out.println(orderId);
        return CommonReturnType.create(orderModel, "success");
    }



    @RequestMapping(value = "/deleteOrder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType deleteOrder(@RequestParam(name = "orderId") Integer orderId) {
        orderService.deleteOrder(orderId);
        return CommonReturnType.create();
    }



}



