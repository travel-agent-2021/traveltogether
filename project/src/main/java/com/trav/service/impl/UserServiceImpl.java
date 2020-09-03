package com.trav.service.impl;

import com.trav.dao.UserDO;
import com.trav.error.BusinessError;
import com.trav.error.BusinessException;
import com.trav.mapper.UserDOMapper;
import com.trav.service.UserService;
import com.trav.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    public UserModel getUserByUsername(String username) {
        UserDO userDO = userDOMapper.selectByUsername(username);
        if (userDO == null) {
            return null;
        }
        UserModel userModel = convertFromDO2Model(userDO);
        return userModel;
    }

    @Override
    @Transactional
    public void insertUser(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if (StringUtils.isEmpty(userModel.getUsername())) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserDO userDO = new UserDO();
        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException ex) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "用户名已经被注册！");
        }
        userModel.setId(userDO.getId());
    }

    @Override
    public UserModel validateLogin(String username, String encryptPassword) throws BusinessException {
        UserModel userModel = getUserByUsername(username);
        if (userModel == null) {
            throw new BusinessException(BusinessError.USER_LOGIN_FAIL);
        }
        if (userModel.getUsername().equals(username) &&
            userModel.getEncryptPassword().equals(encryptPassword)) {
            return userModel;
        }
        return null;
    }


    private UserModel convertFromDO2Model(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        return userModel;
     }

    private UserDO convertFromModel2DO(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }
}
