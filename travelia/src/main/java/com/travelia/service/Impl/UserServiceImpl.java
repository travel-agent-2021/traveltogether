package com.travelia.service.Impl;

import com.travelia.entity.UserDO;
import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.mapper.UserDOMapper;
import com.travelia.service.UserService;
import com.travelia.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    public List<UserModel> getAllUsers() {
        List<UserDO> userDOS = userDOMapper.selectAllUsers();
        List<UserModel> userModels = new ArrayList<>();
        for (UserDO userDO: userDOS) {
            userModels.add(convertFromDO2Model(userDO));
        }
        return userModels;
    }

    @Override
    public UserModel getUserByUsername(String username) {
        UserDO userDO = userDOMapper.selectByUsername(username);
        return convertFromDO2Model(userDO);
    }


    @Override
    public UserModel validLogin(String username, String encryptPassword) throws BusinessException {
        UserModel userModel = getUserByUsername(username);
        if (userModel == null) {
            throw new BusinessException(BusinessError.ADMIN_LOGIN_FAIL);
        }
        if (userModel.getEncryptPassword().equals(encryptPassword)) {
            return userModel;
        }
        return null;
    }

    @Override
    public int addUser(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserDO userDO = convertFromModel2DO(userModel);

        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "用户名已被注册");
        }
        return 0;
    }


    


    private UserDO convertFromModel2DO(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }


    private UserModel convertFromDO2Model(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        return userModel;
    }


}
