package com.travelia.service.Impl;

import com.travelia.mapper.ItemImageDOMapper;
import com.travelia.service.ItemImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




}
