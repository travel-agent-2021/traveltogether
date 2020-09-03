package com.trav.service;

import com.trav.error.BusinessException;
import com.trav.service.model.UserModel;

public interface UserService {

    UserModel getUserByUsername(String username);

    void insertUser(UserModel userModel) throws BusinessException;

    UserModel validateLogin(String username, String encryptPassword) throws BusinessException;

}
