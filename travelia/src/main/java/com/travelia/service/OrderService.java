package com.travelia.service;

import com.travelia.error.BusinessException;
import com.travelia.service.model.OrderModel;

import java.util.List;

public interface OrderService {

    OrderModel getOrderByOrderId(Integer orderId);

    List<OrderModel> getAllOrders();

    List<OrderModel> getOrdersByAgencyId(Integer agencyId) throws BusinessException;

    List<OrderModel> getOrdersByAgencyAccount(String agencyAccount) throws BusinessException;

    List<OrderModel> getOrdersByUserId(Integer userId) throws BusinessException;

    List<OrderModel> getOrdersByOptions(Integer agencyId, Integer orderStatus) throws BusinessException;

    int insertOrder(OrderModel orderModel) throws BusinessException;

    int deleteOrder(OrderModel orderModel) throws BusinessException;

    int updateOrderById(OrderModel orderModel) throws BusinessException;

    int getOrderCountsByDate(String date,Integer agencyId);


}
