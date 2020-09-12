package com.travelia.service.Impl;

import com.travelia.entity.*;
import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.mapper.*;
import com.travelia.service.ItemService;
import com.travelia.service.model.CityModel;
import com.travelia.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private CityDOMapper cityDOMapper;

    @Autowired
    private AgencyDOMapper agencyDOMapper;

    @Autowired
    private ItemCityDOMapper itemCityDOMapper;

    @Autowired
    private ItemImageDOMapper itemImageDOMapper;

    /**
     * 根据商品Id查询商品
     * @return
     */
    @Override
    public ItemModel getItemByItemId(Integer itemId) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(itemId);
        if (itemDO != null) {
            AgencyDO agencyDO = agencyDOMapper.selectByPrimaryKey(itemDO.getAgencyId());
            return convertFromItemDO2Model(itemDO, agencyDO);
        }
        return null;
    }

    /**
     * 查询所有商品
     * @return
     */
    @Override
    public List<ItemModel> getAllItems() {
        List<ItemDO> itemDOList = itemDOMapper.selectAllItems();
        List<ItemModel> itemModels = new ArrayList<>();
        for (ItemDO itemDO : itemDOList) {
            AgencyDO agencyDO = agencyDOMapper.selectByPrimaryKey(itemDO.getAgencyId());
            itemModels.add(convertFromItemDO2Model(itemDO, agencyDO));
        }
        return itemModels;
    }


    /**
     * 根据旅行社Id查询旅行社所有商品
     * @param agencyId
     * @return
     */
    @Override
    public List<ItemModel> getItemsByAgencyId(Integer agencyId) throws BusinessException {
        List<ItemDO> itemDOS = itemDOMapper.selectByAgencyId(agencyId);
        if (itemDOS == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        AgencyDO agencyDO =  agencyDOMapper.selectByPrimaryKey(agencyId);
        List<ItemModel> itemModels = new ArrayList<>();
        for (ItemDO itemDO: itemDOS) {
            itemModels.add(convertFromItemDO2Model(itemDO, agencyDO));
        }
        return itemModels;
    }

    /**
     * 得到商品列表按下单次数降序排列
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ItemModel> getItemsOrderByTotalOrderTimesDESC() throws BusinessException {
        List<ItemDO> itemDOList = itemDOMapper.selectAllByOrderTimesDESC();
        if (itemDOList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        List<ItemModel> itemModels = new ArrayList<>();
        int num = Math.min(itemDOList.size(), 6);
        for (int i = 0; i < num; i++) {
            ItemDO itemDO = itemDOList.get(i);
            AgencyDO agencyDO = agencyDOMapper.selectByPrimaryKey(itemDO.getAgencyId());
            itemModels.add(convertFromItemDO2Model(itemDO, agencyDO));
        }
        return itemModels;
    }

    /**
     * 商品按日期降序排列
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ItemModel> getItemsOrderByCreateDateDESC() throws BusinessException {
        List<ItemDO> itemDOList = itemDOMapper.selectAllByCreateDateDESC();
        if (itemDOList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        List<ItemModel> itemModels = new ArrayList<>();
        int num = Math.min(itemDOList.size(), 7);
        for (int i = 0; i < num; i++) {
            ItemDO itemDO = itemDOList.get(i);
            AgencyDO agencyDO = agencyDOMapper.selectByPrimaryKey(itemDO.getAgencyId());
            itemModels.add(convertFromItemDO2Model(itemDO, agencyDO));
        }
        return itemModels;
    }

    /**
     * 获取某商品相关商品
     * @param itemModel
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ItemModel> getRelatedItems(ItemModel itemModel) throws BusinessException {
        if (itemModel == null) {
            return null;
        }

        // 获取商品包含城市
        List<CityModel> containedCities = itemModel.getCityModels();
        if (containedCities == null || containedCities.size() == 0) {
            return null;
        }

        // 获取所有商品
        List<ItemModel> itemModelList = getAllItems();
        if (itemModelList == null || itemModelList.size() == 0) {
            return null;
        }

        List<ItemModel> relatedItems = new ArrayList<>();
        // 对比商品城市信息获取相关信息
        for (ItemModel item : itemModelList) {
            List<CityModel> cities = item.getCityModels();
            if (cities != null && cities.size() != 0) {
                for (CityModel city: containedCities) {
                    if (cities.contains(city) && !item.getItemId().equals(itemModel.getItemId())) {
                        relatedItems.add(item);
                    }
                }
            }
        }

        // 添加至多三条相关商品信息
        if (relatedItems.size() < 3) {
            return relatedItems;
        } else {
            return relatedItems.subList(0, 3);
        }
    }


    /**
     * 根据商品名搜索商品
     * @param itemName
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ItemModel> searchItemsByItemName(String itemName) throws BusinessException {
        List<ItemDO> itemDOList = itemDOMapper.selectLikeItemName(itemName);
        if (itemDOList == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }

        List<ItemModel> itemModels = new ArrayList<>();
        for (ItemDO itemDO: itemDOList) {
            AgencyDO agencyDO = agencyDOMapper.selectByPrimaryKey(itemDO.getAgencyId());
            itemModels.add(convertFromItemDO2Model(itemDO, agencyDO));
        }
        return itemModels;
    }

    /**
     * 添加商品信息
     * @param itemModel
     * @return
     */
    @Override
    @Transactional
    public int insertItem(ItemModel itemModel) throws BusinessException {
        if (itemModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if (itemModel.getItemName() == null || itemModel.getItemName().equals("") )  {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ItemDO itemDO = convertFormItemModel2DO(itemModel);
        itemDOMapper.insertSelective(itemDO);

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
     * @param itemModel
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public int deleteItem(ItemModel itemModel) throws BusinessException {
        if (itemModel.getItemId() == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }
        itemDOMapper.deleteByPrimaryKey(itemModel.getItemId());
        // 删除商品相关图片信息
        itemImageDOMapper.deleteByItemId(itemModel.getItemId());
        // 删除商品相关城市信息
        itemCityDOMapper.deleteByItemId(itemModel.getItemId());
        return 0;
    }

    /**
     * 更新商品信息
     * @param itemModel
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public int updateItemById(ItemModel itemModel) throws BusinessException {
        if (itemModel == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        if (itemModel.getItemId() == null) {
            throw new BusinessException(BusinessError.ITEM_NOT_FOUND);
        }
        ItemDO itemDO = convertFormItemModel2DO(itemModel);
        int flag = itemDOMapper.updateByPrimaryKeySelective(itemDO);

        return flag;
    }

    /**
     * ItemDO转化为ItemModel，包括商品、旅行社信息、城市、图片信息
     * @param itemDO
     * @param agencyDO
     * @return
     */
    private ItemModel convertFromItemDO2Model(ItemDO itemDO, AgencyDO agencyDO) {
        if (itemDO == null) {
            return null;
        }
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);

        // 旅行社信息
        if (agencyDO != null) {
            BeanUtils.copyProperties(agencyDO, itemModel);
        }

        // 城市信息
        List<ItemCityDOKey> itemCityDOKeyList = itemCityDOMapper.selectByItemId(itemModel.getItemId());
        List<CityModel> cities = new ArrayList<>();
        if (itemCityDOKeyList != null) {
            for (ItemCityDOKey itemCityDOKey : itemCityDOKeyList) {
                CityDO cityDO = cityDOMapper.selectByPrimaryKey(itemCityDOKey.getCityId());
                if (cityDO != null) {
                    cities.add(convertFromCityDO2Model(cityDO));
                }
            }
        }
        itemModel.setCityModels(cities);

        // 图片信息
        List<ItemImageDO> itemImageDOS = itemImageDOMapper.selectByItemId(itemModel.getItemId());
        List<String> itemImages = new ArrayList<>();
        if (itemImageDOS != null) {
            for (ItemImageDO itemImageDO: itemImageDOS) {
                String imageSrc = itemImageDO.getItemImageSource();
                itemImages.add(imageSrc);
            }
        }
        itemModel.setItemImageSources(itemImages);
        return itemModel;
    }

    /**
     * CityDO转化为CityModel
     * @param cityDO
     * @return CityModel
     */
    private CityModel convertFromCityDO2Model(CityDO cityDO) {
        if (cityDO == null) {
            return null;
        }
        CityModel cityModel = new CityModel();
        BeanUtils.copyProperties(cityDO, cityModel);
        return cityModel;
    }

    /**
     * ItemDO转化为ItemModel
     * @param itemModel
     * @return
     */
    private ItemDO convertFormItemModel2DO(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        // 添加商品信息
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        return itemDO;
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
