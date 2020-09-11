package com.travelia.service.Impl;

import com.travelia.entity.AgencyDO;
import com.travelia.entity.OrderDO;
import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.mapper.OrderDOMapper;
import com.travelia.service.OrderService;
import com.travelia.service.model.AgencyModel;
import com.travelia.service.model.OrderModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Override
    public OrderModel getOrderById(Integer id) {
        OrderDO orderDO = orderDOMapper.selectByPrimaryKey(id);
        if (orderDO == null) {
            return null;
        }
        return convertFromOrderDO2Model(orderDO);
    }

    @Override
    public List<OrderModel> getAllOrders() {
        List<OrderDO> orderDOS = orderDOMapper.selectAllOrders();
        if (orderDOS == null) {
            return null;
        }
        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderDO orderDO: orderDOS) {
            orderModels.add(convertFromOrderDO2Model(orderDO));
        }
        return orderModels;
    }

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    @Override
    public int deleteOrder(Integer orderId) {
        if (orderId == null) {
            return -1;
        }
        int flag = orderDOMapper.deleteByPrimaryKey(orderId);
        return flag;
    }



    /**
     * 用户model转化为dataObject
     * @param orderModel
     * @return
     */
    private OrderDO convertFromOrderModel2DO(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }

    private OrderModel convertFromOrderDO2Model(OrderDO orderDO) {
        if (orderDO == null) {
            return null;
        }
        OrderModel orderModel = new OrderModel();
        BeanUtils.copyProperties(orderDO, orderModel);
        return orderModel;
    }

}
