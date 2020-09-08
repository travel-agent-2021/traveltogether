package com.travelia.service.Impl;

import com.travelia.entity.UserDO;
import com.travelia.mapper.UserDOMapper;
import com.travelia.service.UserService;
import com.travelia.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserModel convertFromDO2Model(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        return userModel;
    }


}
