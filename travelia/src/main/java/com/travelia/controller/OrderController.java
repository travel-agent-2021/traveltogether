package com.travelia.controller;


import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.response.CommonReturnType;
import com.travelia.service.CityService;
import com.travelia.service.ItemCityService;
import com.travelia.service.ItemService;
import com.travelia.service.OrderService;
import com.travelia.service.model.CityModel;
import com.travelia.service.model.ItemModel;
import com.travelia.service.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    /**
     * 根据OrderId获取订单信息
     * @param orderId
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getOrderById", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOrderById(@RequestParam(name = "orderId") Integer orderId) throws BusinessException {
        OrderModel orderModel = orderService.getOrderByOrderId(orderId);
        if (orderModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR,"orderId未找到");
        }
        return CommonReturnType.create(orderModel);
    }

    /**
     * 获取所有订单信息
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getAllOrders", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getAllOrders() throws BusinessException {
        List<OrderModel> orderModelList = orderService.getAllOrders();
        if (orderModelList == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR,"orderId未找到");
        }
        return CommonReturnType.create(orderModelList);
    }

    /**
     * 按经销商账户获取订单信息
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getOrdersByAgencyAccount", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getOrdersByAgencyAccount(@RequestParam(name = "agencyId") Integer agencyId) throws BusinessException {
        List<OrderModel> orderModelList = orderService.getOrdersByAgencyId(agencyId);
        if (orderModelList == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR,"orderId未找到");
        }
        return CommonReturnType.create(orderModelList);
    }

    /**
     * 按用户ID获取订单信息
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getOrdersByUserId", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOrdersByUserId(@RequestParam(name = "userId") Integer userId) throws BusinessException {
        List<OrderModel> orderModelList = orderService.getOrdersByUserId(userId);
        if (orderModelList == null) {
            return CommonReturnType.create();
        }
        return CommonReturnType.create(orderModelList);
    }


    /**
     * 添加订单信息
     * @param userId
     * @param itemId
     * @param orderPrice
     * @param orderDetail
     * @param orderTravelers
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/addOrder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType addOrder(@RequestParam(name = "userId") Integer userId,
                                     @RequestParam(name = "itemId") Integer itemId,
                                     @RequestParam(name = "orderPrice") BigDecimal orderPrice,
                                     @RequestParam(name = "orderDetail") String orderDetail,
                                     @RequestParam(name = "orderTravelers") Integer orderTravelers) throws BusinessException {

        if (orderPrice == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入订单价格");
        }
        if (itemId == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入旅行社");
        }


        OrderModel orderModel = new OrderModel();
        orderModel.setOrderId(generateOrderId());
        orderModel.setUserId(userId);
        ItemModel itemModel = itemService.getItemByItemId(itemId);
        if (itemModel != null) {
            orderModel.setAgencyId(itemModel.getAgencyId());
            itemModel.setTotalOrderTimes(itemModel.getTotalOrderTimes() + 1);
            itemService.updateItemById(itemModel);
        }
        orderModel.setItemId(itemId);
        orderModel.setOrderCreateDate(getNowDate("yyyy-MM-dd"));
        orderModel.setOrderPrice(orderPrice);
        orderModel.setOrderStatus(1);
        orderModel.setOrderDetail(orderDetail);
        orderModel.setOrderTravelers(orderTravelers);
        // 待修改
        //orderModel.setOrderImageSources(null);

        orderService.insertOrder(orderModel);

        return CommonReturnType.create();
    }

    /**
     * 修改订单信息
     * @param orderId
     * @param userId
     * @param agencyId
     * @param itemId
     * @param orderCreateDate
     * @param orderPrice
     * @param orderStatus
     * @param orderDetail
     * @param orderTravelers
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/updateOrder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType updateOrder(@RequestParam(name = "orderId") Integer orderId,
                                       @RequestParam(name = "userId") Integer userId,
                                       @RequestParam(name = "agencyId") Integer agencyId,
                                       @RequestParam(name = "itemId") Integer itemId,
                                       @RequestParam(name = "orderCreateDate") String orderCreateDate,
                                       @RequestParam(name = "orderPrice") BigDecimal orderPrice,
                                       @RequestParam(name = "orderStatus") Integer orderStatus,
                                       @RequestParam(name = "orderDetail") String orderDetail,
                                       @RequestParam(name = "orderTravelers") Integer orderTravelers) throws BusinessException {

        if (orderId == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
//        if (itemName == null || itemName.equals("")) {
//            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入商品名称");
//        }
        if (orderPrice == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入订单价格");
        }
        if (agencyId == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入旅行社ID");
        }

        OrderModel orderModel = orderService.getOrderByOrderId(orderId);
        if (orderModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR,"orderId未找到");
        }

        // 修改基本信息
        orderModel.setOrderId(generateOrderId());
        orderModel.setUserId(userId);
        orderModel.setAgencyId(agencyId);
        orderModel.setItemId(itemId);
        orderModel.setOrderCreateDate(orderCreateDate);
        orderModel.setOrderPrice(orderPrice);
        orderModel.setOrderStatus(orderStatus);
        orderModel.setOrderDetail(orderDetail);
        orderModel.setOrderTravelers(orderTravelers);

        // to do 修改图片信息

        orderService.updateOrderById(orderModel);
        return CommonReturnType.create();
    }

    /**
     * 删除商品信息
     * @param orderId
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/deleteOrder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType deleteOrder(@RequestParam(name = "orderId") Integer orderId) throws BusinessException {
        OrderModel orderModel = orderService.getOrderByOrderId(orderId);
        if (orderModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR,"orderId未找到");
        }
        orderService.deleteOrder(orderModel);
        return CommonReturnType.create();
    }

    /**
     * 生成随机itemId
     * @return
     */
    private Integer generateOrderId () {
        Integer id = generateRandomId();
        if (orderService.getOrderByOrderId(id) != null) {
            id = generateRandomId();
        }
        return id;
    }

}
