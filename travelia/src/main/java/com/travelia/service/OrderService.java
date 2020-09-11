package com.travelia.service;


import com.travelia.service.model.OrderModel;

import java.util.List;

public interface OrderService {

    OrderModel getOrderById(Integer id);

    List<OrderModel> getAllOrders();


    int deleteOrder(Integer orderId);


}
