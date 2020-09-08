package com.travelia.service;

import com.travelia.error.BusinessException;
import com.travelia.service.model.AdminModel;

public interface AdminService {

    AdminModel getAdminByAccount(String adminAccount);

    AdminModel validateLogin(String adminAccount, String encryptPassword) throws BusinessException;

}
