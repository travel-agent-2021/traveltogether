package com.travelia.service;

import com.travelia.error.BusinessException;
import com.travelia.service.model.UserModel;

import java.util.List;

public interface UserService {

    List<UserModel> getAllUsers();

    UserModel validateLogin(String username, String encryptPassword) throws BusinessException;

    int addUser(UserModel userModel) throws BusinessException;

    UserModel getUserByUserId(Integer userId);

    UserModel getUserByTelephone(String telephone) throws BusinessException;

    int deleteUser(Integer userId);

    int updateUser(UserModel userModel) throws BusinessException;

    List<UserModel> getUsersLikeUsername(String username);

    Integer getCountByAge(Integer minAge, Integer maxAge);
}
