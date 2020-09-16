package com.travelia.service.Impl;

import com.travelia.entity.*;
import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.mapper.*;
import com.travelia.service.ItemService;
import com.travelia.service.OrderService;
import com.travelia.service.UserService;
import com.travelia.service.model.CityModel;
import com.travelia.service.model.ItemModel;
import com.travelia.service.model.OrderModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private AgencyDOMapper agencyDOMapper;

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private ItemDOMapper itemDOMapper;


    /**
     * 根据订单Id查询订单
     * @return
     */
    @Override
    public OrderModel getOrderByOrderId(Integer orderId) {
        OrderDO orderDO = orderDOMapper.selectByPrimaryKey(orderId);
        if (orderDO != null) {
            AgencyDO agencyDO = agencyDOMapper.selectByPrimaryKey(orderDO.getAgencyId());
            return convertFromOrderDO2Model(orderDO, agencyDO);
        }
        return null;
    }

    /**
     * 查询所有订单
     * @return
     */
    @Override
    public List<OrderModel> getAllOrders() {
        List<OrderDO> orderDOList = orderDOMapper.selectAllOrders();
        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderDO orderDO : orderDOList) {
            AgencyDO agencyDO = agencyDOMapper.selectByPrimaryKey(orderDO.getAgencyId());
            orderModels.add(convertFromOrderDO2Model(orderDO, agencyDO));
        }
        return orderModels;
    }


    /**
     * 根据旅行社Id查询旅行社所有订单
     * @param agencyId
     * @return
     */
    @Override
    public List<OrderModel> getOrdersByAgencyId(Integer agencyId) throws BusinessException {
        List<OrderDO> orderDOS = orderDOMapper.selectByAgencyId(agencyId);
        if (orderDOS == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR,"orderId未找到");
        }
        AgencyDO agencyDO =  agencyDOMapper.selectByPrimaryKey(agencyId);
        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderDO orderDO: orderDOS) {
            orderModels.add(convertFromOrderDO2Model(orderDO, agencyDO));
        }
        return orderModels;
    }
    /**
     * 根据旅行社账号查询旅行社所有订单
     * @param agencyAccount
     * @return
     */
    @Override
    public List<OrderModel> getOrdersByAgencyAccount(String agencyAccount) throws BusinessException {
        List<OrderDO> orderDOS = orderDOMapper.selectByAgencyAccount(agencyAccount);
        if (orderDOS == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR,"orderId未找到");
        }
        AgencyDO agencyDO =  agencyDOMapper.selectByAgencyAccount(agencyAccount);
        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderDO orderDO: orderDOS) {
            orderModels.add(convertFromOrderDO2Model(orderDO, agencyDO));
        }
        return orderModels;
    }

    /**
     * 根据用户Id查询旅行社所有订单
     * @param userId
     * @return
     */
    @Override
    public List<OrderModel> getOrdersByUserId(Integer userId) throws BusinessException {
        List<OrderDO> orderDOS = orderDOMapper.selectByUserId(userId);
        if (orderDOS == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "orderId未找到");
        }

        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderDO orderDO: orderDOS) {
            AgencyDO agencyDO = agencyDOMapper.selectByPrimaryKey(orderDO.getAgencyId());
            OrderModel orderModel = convertFromOrderDO2Model(orderDO, agencyDO);
            orderModels.add(orderModel);
        }
        return orderModels;
    }

    @Override
    public List<OrderModel> getOrdersByOptions(Integer agencyId, Integer orderStatus) throws BusinessException {
        List<OrderDO> orderDOList = orderDOMapper.selectByOptions(agencyId, orderStatus);
        if (orderDOList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderDO orderDO: orderDOList) {
            AgencyDO agencyDO = agencyDOMapper.selectByPrimaryKey(orderDO.getAgencyId());
            orderModels.add(convertFromOrderDO2Model(orderDO, agencyDO));
        }
        return orderModels;
    }


    /**
     * 添加订单信息
     * @param orderModel
     * @return
     */
    @Override
    @Transactional
    public int insertOrder(OrderModel orderModel) throws BusinessException {
        if (orderModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if (orderModel.getOrderId() == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }

        OrderDO orderDO = convertFormOrderModel2DO(orderModel);
        orderDOMapper.insertSelective(orderDO);

        // 插入图片记录表
        /*List<String> images = itemModel.getItemImageSources();
        if (images != null) {
            for (String imageSrc: images) {
                if (imageSrc != null) {
                    ItemImageDO itemImageDO = new ItemImageDO();
                    itemImageDO.setItemId(itemModel.getItemId());
                    itemImageDO.setItemImageSource(imageSrc);
                    itemImageDOMapper.insertSelective(itemImageDO);
                }
            }
        }*/

        return 0;
    }

    /**
     * 删除商品信息
     * @param orderModel
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public int deleteOrder(OrderModel orderModel) throws BusinessException {
        if (orderModel.getOrderId() == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }
        orderDOMapper.deleteByPrimaryKey(orderModel.getOrderId());
        return 0;
    }

    /**
     * 更新订单信息
     * @param orderModel
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public int updateOrderById(OrderModel orderModel) throws BusinessException {
        if (orderModel == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        if (orderModel.getOrderId() == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        OrderDO orderDO = convertFormOrderModel2DO(orderModel);
        int flag = orderDOMapper.updateByPrimaryKeySelective(orderDO);

        return flag;
    }

    public int getOrderCountsByDate(String date) {
        if (date == null) {
            return 0;
        }
        return orderDOMapper.selectCountByCreateDate("%" + date + "%");
    }

    /**
     * OrderDO转化为OrderModel，包括商品、旅行社信息、城市、图片信息
     * @param orderDO
     * @param agencyDO
     * @return
     */
    private OrderModel convertFromOrderDO2Model(OrderDO orderDO, AgencyDO agencyDO) {
        if (orderDO == null) {
            return null;
        }
        OrderModel orderModel = new OrderModel();
        BeanUtils.copyProperties(orderDO, orderModel);

        // 旅行社信息
        if (agencyDO != null) {
            orderModel.setAgencyTitle(agencyDO.getAgencyTitle());
        }

        UserDO userDO = userDOMapper.selectByPrimaryKey(orderDO.getUserId());
        if (userDO != null) {
            orderModel.setUsername(userDO.getUsername());
        }
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(orderDO.getItemId());
        if (itemDO != null) {
            orderModel.setItemName(itemDO.getItemName());
        }

        return orderModel;
    }

    /**
     * OrderModel转化为OrderDO
     * @param orderModel
     * @return
     */
    private OrderDO convertFormOrderModel2DO(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        // 添加商品信息
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }

    private CityDO convertFromCityModel2DO(CityModel cityModel) {
        if (cityModel == null) {
            return null;
        }
        CityDO cityDO = new CityDO();
        BeanUtils.copyProperties(cityModel, cityDO);
        return cityDO;
    }


}
