package com.travelia.service;

import com.travelia.error.BusinessException;
import com.travelia.service.model.UserModel;

import java.util.List;

public interface UserService {

    List<UserModel> getAllUsers();

    UserModel validLogin(String username, String encryptPassword) throws BusinessException;

    int addUser(UserModel userModel) throws BusinessException;

    UserModel getUserByUsername(String username);

}
