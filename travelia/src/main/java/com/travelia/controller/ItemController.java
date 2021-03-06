package com.travelia.controller;


import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.response.CommonReturnType;
import com.travelia.service.CityService;
import com.travelia.service.ItemCityService;
import com.travelia.service.ItemImageService;
import com.travelia.service.ItemService;
import com.travelia.service.model.CityModel;
import com.travelia.service.model.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;


@Controller
@RequestMapping("/item")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ItemCityService itemCityService;

    @Autowired
    private ItemImageService itemImageService;

    /**
     * 根据ItemId获取商品信息
     * @param itemId
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getItemById", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getItemById(@RequestParam(name = "itemId") Integer itemId) throws BusinessException {
        ItemModel itemModel = itemService.getItemByItemId(itemId);

        if (itemModel == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        itemModel.setTotalClickTimes(itemModel.getTotalClickTimes() + 1);
        itemService.updateItemById(itemModel);
        return CommonReturnType.create(itemModel);
    }

    /**
     * 根据AgencyId获取商品信息
     * @param agencyId
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getItemByAgencyId", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getItemByAgencyId(@RequestParam(name = "agencyId") Integer agencyId) throws BusinessException {
        List<ItemModel> itemModel = itemService.getItemsByAgencyId(agencyId);
        if (itemModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }
        return CommonReturnType.create(itemModel);
    }

    /**
     * 获取所有商品信息
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getAllItems", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getAllItems() throws BusinessException {
        List<ItemModel> itemModelList = itemService.getAllItems();
        if (itemModelList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        return CommonReturnType.create(itemModelList);
    }

    @RequestMapping(value = "/getCheckedItems", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getCheckedItems() throws BusinessException {
        List<ItemModel> itemModelList = itemService.getCheckedItems();
        if (itemModelList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        return CommonReturnType.create(itemModelList);
    }

    /**
     * 获取热门商品信息
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getHottestItems", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getHottestItems(@RequestParam(name = "agencyId") Integer agencyId) throws BusinessException {
        List<ItemModel> itemModelList = itemService.getItemsOrderByTotalOrderTimesDESC(agencyId);
        if (itemModelList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        return CommonReturnType.create(itemModelList);
    }

    /**
     * 获取点击量
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getClickTimes", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getClickTimes(@RequestParam(name = "agencyId") Integer agencyId) throws BusinessException {
        List<ItemModel> itemModelList = itemService.getItemsByClickTimesDESC(agencyId);
        if (itemModelList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        Map<String, Integer> chartData = new TreeMap<>();
        for (ItemModel itemModel: itemModelList) {
            chartData.put(itemModel.getItemName(), itemModel.getTotalClickTimes());
        }

        return CommonReturnType.create(sortMap(chartData));
    }

    /**
     * 获取点击量
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType test() throws BusinessException {
        List<ItemModel> itemModelList = itemService.getItemsByClickTimesDESC(2);
        if (itemModelList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        Map<String, Integer> chartData = new TreeMap<>();
        for (ItemModel itemModel: itemModelList) {
            chartData.put(itemModel.getItemName(), itemModel.getTotalClickTimes());
        }

        return CommonReturnType.create(sortMap(chartData));
    }

    /**
     * 获取点击量
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getBestsellers", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getBestsellers(@RequestParam(name = "agencyId") Integer agencyId) throws BusinessException {
        List<ItemModel> itemModelList = itemService.getItemsOrderByTotalOrderTimesDESC(agencyId);
        if (itemModelList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        Map<String, Integer> chartData = new TreeMap<>();
        for (ItemModel itemModel: itemModelList) {
            chartData.put(itemModel.getItemName(), itemModel.getTotalOrderTimes());
        }
        return CommonReturnType.create(sortMap(chartData));
    }

    /**
     * 获取最新商品信息
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getLatestItems", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getLatestItems() throws BusinessException {
        List<ItemModel> itemModelList = itemService.getItemsOrderByCreateDateDESC();
        if (itemModelList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        return CommonReturnType.create(itemModelList);
    }

    /**
     * 获取相关商品信息
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getRelatedItems", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getRelatedItems(@RequestParam(name = "itemId") Integer itemId) throws BusinessException {
        ItemModel itemModel = itemService.getItemByItemId(itemId);
        if (itemId == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        List<ItemModel> relatedItems = itemService.getRelatedItems(itemModel);
        if (relatedItems == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        return CommonReturnType.create(relatedItems);
    }

    /**
     * 根据itemName搜索item
     * @param itemName
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/searchItemsByItemName", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType searchItemsByItemName(@RequestParam(name = "itemName") String itemName) throws BusinessException {
        List<ItemModel> itemModelList;
        if (itemName == null || itemName.equals("")) {
            itemModelList = itemService.getCheckedItems();
        } else {
            itemModelList = itemService.searchItemsByKeyword(itemName);
        }
        if (itemModelList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        return CommonReturnType.create(itemModelList);
    }

    /**
     * 筛选商品
     * @param checkStatus
     * @param agencyId
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getItemsByOptions", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getItemsByOptions(@RequestParam(name = "checkStatus") Integer checkStatus,
                                              @RequestParam(name = "agencyId") Integer agencyId) throws BusinessException {
        if (checkStatus == -1) {
            checkStatus = null;
        }
        if (agencyId == -1) {
            agencyId = null;
        }
        List<ItemModel> itemModelList = itemService.getItemsByOptions(checkStatus, agencyId);
        return CommonReturnType.create(itemModelList);
    }

    /**
     * 添加商品信息
     * @param itemName
     * @param itemPrice
     * @param agencyId
     * @param duration
     * @param minTourists
     * @param maxTourists
     * @param itemDetail
     * @param itemImageSources
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/addItem", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType addItem(@RequestParam(name = "itemName") String itemName,
                                    @RequestParam(name = "itemPrice") BigDecimal itemPrice,
                                    @RequestParam(name = "agencyId") Integer agencyId,
                                    @RequestParam(name = "checkStatus") Integer checkStatus,
                                    @RequestParam(name = "duration", required = false) Integer duration,
                                    @RequestParam(name = "minTourists", required = false) Integer minTourists,
                                    @RequestParam(name = "maxTourists", required = false) Integer maxTourists,
                                    @RequestParam(name = "itemDetail", required = false) String itemDetail,
                                    @RequestParam(name = "itemImageSources", required = false) String itemImageSources) throws BusinessException {

        if (itemName == null || itemName.equals("")) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入商品名称");
        }
        if (itemPrice == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入商品价格");
        }
        if (agencyId == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入旅行社");
        }
        System.out.println("iir"+itemImageSources);
        List<String> imageRe = new ArrayList<>();
        imageRe.add(itemImageSources);


        ItemModel itemModel = new ItemModel();
        itemModel.setItemId(generateItemId());
        itemModel.setItemName(itemName);
        itemModel.setDuration(duration);
        itemModel.setMaxTourists(maxTourists);
        itemModel.setMinTourists(minTourists);
        itemModel.setItemPrice(itemPrice);
        itemModel.setItemDetail(itemDetail);
        itemModel.setAgencyId(agencyId);
        itemModel.setCheckStatus(checkStatus);
        itemModel.setTotalOrderTimes(0);
        itemModel.setItemCreateDate(getNowDate("yyyy-MM-dd"));
        itemModel.setTotalClickTimes(0);
        // 待修改
        //itemModel.setItemImageSources(null);

        itemService.insertItem(itemModel);

        // 根据商品名设置城市信息
        Integer itemId = itemModel.getItemId();
        itemModel.setCityModels(setCityList(itemName));
        itemCityService.addItemCityDOKeys(itemId, itemModel.getCityModels());

        itemImageService.addByItemId(itemId,imageRe);

        return CommonReturnType.create();
    }


    /**
     * 修改商品信息
     * @param itemId
     * @param itemName
     * @param itemPrice
     * @param agencyId
     * @param duration
     * @param minTourists
     * @param maxTourists
     * @param itemDetail
     * @param itemImageSources
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/updateItem", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType updateItem(@RequestParam(name = "itemId") Integer itemId,
                                       @RequestParam(name = "itemName") String itemName,
                                       @RequestParam(name = "checkStatus") Integer checkStatus,
                                       @RequestParam(name = "itemPrice") BigDecimal itemPrice,
                                       @RequestParam(name = "agencyId") Integer agencyId,
                                       @RequestParam(name = "duration", required = false) Integer duration,
                                       @RequestParam(name = "minTourists", required = false) Integer minTourists,
                                       @RequestParam(name = "maxTourists", required = false) Integer maxTourists,
                                       @RequestParam(name = "itemDetail", required = false) String itemDetail,
                                       @RequestParam(name = "itemImageSources", required = false) String itemImageSources) throws BusinessException {

        if (itemId == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        if (itemName == null || itemName.equals("")) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入商品名称");
        }
        if (itemPrice == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入商品价格");
        }
        if (agencyId == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入旅行社ID");
        }

        ItemModel itemModel = itemService.getItemByItemId(itemId);
        if (itemModel == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }

        // 修改城市信息
        itemCityService.deleteByItemId(itemId);
        itemCityService.addItemCityDOKeys(itemId, setCityList(itemName));
        List<String> imageRe = new ArrayList<>();
        imageRe.add(itemImageSources);
        itemImageService.deleteByItemId(itemId);
        itemImageService.addByItemId(itemId,imageRe);

        // 修改基本信息
        itemModel.setItemName(itemName);
        itemModel.setDuration(duration);
        itemModel.setMaxTourists(maxTourists);
        itemModel.setMinTourists(minTourists);
        itemModel.setAgencyId(agencyId);
        itemModel.setItemPrice(itemPrice);
        itemModel.setCheckStatus(checkStatus);
        itemModel.setItemDetail(itemDetail);
        itemModel.setItemImageSources(imageRe);
        itemModel.setItemCreateDate(getNowDate("yyyy-MM-dd"));

        // to do 修改图片信息

        itemService.updateItemById(itemModel);
        return CommonReturnType.create();
    }

    /**
     * 删除商品信息
     * @param itemId
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/deleteItem", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType deleteItem(@RequestParam(name = "itemId") Integer itemId) throws BusinessException {
        ItemModel itemModel = itemService.getItemByItemId(itemId);
        if (itemModel == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        itemService.deleteItem(itemModel);
        return CommonReturnType.create();
    }

    /**
     * 审核商品信息
     * @param itemId
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/checkItem", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType checkItem(@RequestParam(name = "itemId") Integer itemId,
                                      @RequestParam(name = "checkStatus") Integer checkStatus) throws BusinessException {
        ItemModel itemModel = itemService.getItemByItemId(itemId);
        if (itemModel == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        itemModel.setCheckStatus(checkStatus);
        itemService.updateItemById(itemModel);
        return CommonReturnType.create();
    }

    /**
     * 根据item名称得到城市列表
     * @param itemName
     * @return List<CityModel>
     */
    private List<CityModel> setCityList(String itemName) throws BusinessException {
        if (itemName == null || itemName.equals("")) {
            return null;
        }
        List<CityModel> list = new ArrayList<>();
        List<CityModel> allCities = cityService.getAllCities();
        for (CityModel cityModel: allCities) {
            if (itemName.contains(cityModel.getCityName())) {
                list.add(cityModel);
            }
        }
        return list;
    }

    /**
     * 生成随机itemId
     * @return
     */
    private Integer generateItemId () {
        Integer id = generateRandomId();
        if (itemService.getItemByItemId(id) != null) {
            id = generateRandomId();
        }
        return id;
    }

}
