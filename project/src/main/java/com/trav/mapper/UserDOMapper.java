package com.trav.mapper;

import com.trav.dao.UserDO;

public interface UserDOMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(UserDO record);


    int insertSelective(UserDO record);


    UserDO selectByPrimaryKey(Integer id);

    UserDO selectByUsername(String username);

    int updateByPrimaryKeySelective(UserDO record);


    int updateByPrimaryKey(UserDO record);
}