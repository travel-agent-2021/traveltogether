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
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

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

    @RequestMapping(value = "/getOrdersByOptions", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOrdersByUserId(@RequestParam(name = "agencyId") Integer agencyId,
                                              @RequestParam(name = "orderStatus") Integer orderStatus) throws BusinessException {
        if (agencyId == -1) {
            agencyId = null;
        }
        if (orderStatus == -1) {
            orderStatus = null;
        }
        List<OrderModel> orderModelList = orderService.getOrdersByOptions(agencyId, orderStatus);
        if (orderModelList == null) {
            return CommonReturnType.create();
        }
        return CommonReturnType.create(orderModelList);
    }

    /**
     * 获取近7天销量数据
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getChartData", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getChartData() throws BusinessException {
        Map<String, Integer> data = new TreeMap<>();
        for (int i = 0; i < 7; i++) {
            String date = getBeforeDate(i);
            Integer count = orderService.getOrderCountsByDate(date);
            data.put(date.substring(5), count);
        }
        return CommonReturnType.create(data);
    }

    /**
     * 获取近5个月销量数据
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getMonthlyData", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getMonthlyData() throws BusinessException {
        Map<String, Integer> data = new TreeMap<>();
        for (int i = 0; i < 5; i++) {
            String date = getBeforeMonth(i);
            Integer count = orderService.getOrderCountsByDate(date);
            data.put(date, count);
        }
        return CommonReturnType.create(data);
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
        orderModel.setOrderStatus(0);
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
     * @param orderStatus
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/updateOrder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType updateOrder(@RequestParam(name = "orderId") Integer orderId,
                                       @RequestParam(name = "orderStatus") Integer orderStatus) throws BusinessException {

        if (orderId == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
//        if (itemName == null || itemName.equals("")) {
//            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入商品名称");
//        }


        OrderModel orderModel = orderService.getOrderByOrderId(orderId);
        if (orderModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR,"orderId未找到");
        }

        // 修改基本信息

        orderModel.setOrderStatus(orderStatus);

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
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "订单未找到");
        }
        if (orderModel.getOrderStatus() == 1) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "订单正在进行中，不可删除！");
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
