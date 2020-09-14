package com.travelia.service.Impl;

import com.travelia.entity.ItemImageDO;
import com.travelia.mapper.ItemImageDOMapper;
import com.travelia.service.ItemImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ItemImageServiceImpl implements ItemImageService {
    @Autowired
    private ItemImageDOMapper itemImageDOMapper;


    @Override
    public int deleteByItemId(Integer itemId) {
        if (itemId == null) {
            return -1;
        }
        itemImageDOMapper.deleteByItemId(itemId);
        return 0;
    }

    @Override
    public int addByItemId(Integer itemId, List<String> itemImageSources) {
        if (itemId == null ){
            return -1;
        }
        if (itemImageSources == null){
            return -1;
        }
        for (String itemImage: itemImageSources) {
            ItemImageDO itemImageDO = new ItemImageDO();
            itemImageDO.setItemImageSource(itemImage);
            itemImageDO.setItemId(itemId);
            itemImageDOMapper.insertSelective(itemImageDO);
        }


        return 0;
    }


}
