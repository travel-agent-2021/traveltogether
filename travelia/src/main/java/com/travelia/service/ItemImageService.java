package com.travelia.service;

import com.travelia.error.BusinessException;

import java.util.List;

public interface ItemImageService {

    int deleteByItemId(Integer itemId);

    int addByItemId(Integer itemId,List<String > itemImageSources);

}
