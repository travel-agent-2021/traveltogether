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

    /**
     * 获取所有用户列表
     * @return
     */
    @Override
    public List<UserModel> getAllUsers() {
        List<UserDO> userDOS = userDOMapper.selectAllUsers();
        if (userDOS == null) {
            return null;
        }
        List<UserModel> userModels = new ArrayList<>();
        for (UserDO userDO: userDOS) {
            userModels.add(convertFromDO2Model(userDO));
        }
        return userModels;
    }

    /**
     * 按ID查找用户
     * @param userId
     * @return UserModel
     */
    @Override
    public UserModel getUserByUserId(Integer userId) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(userId);
        return convertFromDO2Model(userDO);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Override
    public int deleteUser(Integer userId) {
        if (userId == null) {
            return -1;
        }
        int flag = userDOMapper.deleteByPrimaryKey(userId);
        return flag;
    }

    /**
     * 更新用户信息
     * @param userModel
     * @return
     * @throws BusinessException
     */
    @Override
    public int updateUser(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "更新失败");
        }
        int flag = userDOMapper.updateByPrimaryKeySelective(convertFromModel2DO(userModel));
        return flag;
    }

    /**
     * 按用户名查询用户，返回多个用户
     * @param username
     * @return
     */
    @Override
    public List<UserModel> getUsersLikeUsername(String username) {
        if (username == null || username.equals("")) {
            return getAllUsers();
        }
        List<UserDO> userDOList = userDOMapper.selectLikeUsername("%" + username + "%");
        if (userDOList == null) {
            return null;
        }
        List<UserModel> userModelList = new ArrayList<>();
        for (UserDO userDO: userDOList) {
            userModelList.add(convertFromDO2Model(userDO));
        }
        return userModelList;
    }


    /**
     * 登录校验
     * @param telephone
     * @param encryptPassword
     * @return
     * @throws BusinessException
     */
    @Override
    public UserModel validLogin(String telephone, String encryptPassword) throws BusinessException {
        UserModel userModel = getUserByTelephone(telephone);
        if (userModel == null) {
            throw new BusinessException(BusinessError.ADMIN_LOGIN_FAIL);
        }
        if (userModel.getEncryptPassword().equals(encryptPassword)) {
            return userModel;
        }
        return null;
    }

    /**
     * 根据用户手机号查询用户
     * @param telephone
     * @return
     * @throws BusinessException
     */
    public UserModel getUserByTelephone(String telephone) throws BusinessException {
        UserDO userDO = userDOMapper.selectByUserTelephone(telephone);
        if (userDO == null) {
            throw new BusinessException(BusinessError.USER_NOT_FOUND);
        }
        return convertFromDO2Model(userDO);
    }

    /**
     * 添加用户信息
     * @param userModel
     * @return
     * @throws BusinessException
     */
    @Override
    public int addUser(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserDO userDO = convertFromModel2DO(userModel);

        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "手机号已被注册");
        }
        return 0;
    }



    /**
     * 用户model转化为dataObject
     * @param userModel
     * @return
     */
    private UserDO convertFromModel2DO(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

    /**
     * 用户dataObject转化为model
     * @param userDO
     * @return
     */
    private UserModel convertFromDO2Model(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        return userModel;
    }


}
