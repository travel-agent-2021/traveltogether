package com.travelia.service.Impl;

import com.travelia.entity.AdminDO;
import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.mapper.AdminDOMapper;
import com.travelia.service.AdminService;
import com.travelia.service.model.AdminModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDOMapper adminDOMapper;


    @Override
    public AdminModel getAdminByAccount(String adminAccount) {
       AdminDO adminDO = adminDOMapper.selectByAdminAccount(adminAccount);
       return convertFromDO2Model(adminDO);
    }

    @Override
    public AdminModel validateLogin(String adminAccount, String encryptPassword) throws BusinessException {
        AdminModel adminModel = getAdminByAccount(adminAccount);
        if (adminModel == null) {
           throw new BusinessException(BusinessError.ADMIN_LOGIN_FAIL);
        }
        if (adminModel.getAdminAccount().equals(adminAccount)
                && adminModel.getEncryptPassword().equals(encryptPassword)) {
            return adminModel;
        }
        return null;
    }

    private AdminModel convertFromDO2Model(AdminDO adminDO) {
        if (adminDO == null) {
            return null;
        }
        AdminModel adminModel = new AdminModel();
        BeanUtils.copyProperties(adminDO, adminModel);
        return adminModel;
    }

}
