package com.travelia.service.Impl;

import com.travelia.entity.AgencyDO;
import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.mapper.AgencyDOMapper;
import com.travelia.service.AgencyService;
import com.travelia.service.model.AgencyModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyDOMapper agencyDOMapper;

    @Override
    public AgencyModel getAgencyById(Integer id) {
        AgencyDO agencyDO = agencyDOMapper.selectByPrimaryKey(id);
        if (agencyDO == null) {
            return null;
        }
        return convertFromAgencyDO2Model(agencyDO);
    }

    @Override
    public List<AgencyModel> getAllAgencies() {
        List<AgencyDO> agencyDOS = agencyDOMapper.selectAllAgencies();
        if (agencyDOS == null) {
            return null;
        }
        List<AgencyModel> agencyModels = new ArrayList<>();
        for (AgencyDO agencyDO: agencyDOS) {
            agencyModels.add(convertFromAgencyDO2Model(agencyDO));
        }
        return agencyModels;
    }

    /**
     * 删除经销商
     * @param agencyId
     * @return
     */
    @Override
    public int deleteAgency(Integer agencyId) {
        if (agencyId == null) {
            return -1;
        }
        int flag = agencyDOMapper.deleteByPrimaryKey(agencyId);
        return flag;
    }

    /**
     * 更新经销商信息
     * @param agencyModel
     * @return
     * @throws BusinessException
     */
    @Override
    public int updateAgency(AgencyModel agencyModel) throws BusinessException {
        if (agencyModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "更新失败");
        }
        int flag = agencyDOMapper.updateByPrimaryKeySelective(convertFromAgencyModel2DO(agencyModel));
        return flag;
    }

    /**
     * 按用户名查询经销商，返回多个经销商
     * @param agencyAccount
     * @return
     */
    @Override
    public List<AgencyModel> getAgenciesLikeAgencyAccount(String agencyAccount) {
        if (agencyAccount == null || agencyAccount.equals("")) {
            return getAllAgencies();
        }
        List<AgencyDO> agencyDOList = agencyDOMapper.selectLikeAgencyAccount("%" + agencyAccount + "%");
        if (agencyDOList == null) {
            return null;
        }
        List<AgencyModel> agencyModelList = new ArrayList<>();
        for (AgencyDO agencyDO: agencyDOList) {
            agencyModelList.add(convertFromAgencyDO2Model(agencyDO));
        }
        return agencyModelList;
    }


    /**
     * 登录校验
     * @param agencyAccount
     * @param encryptPassword
     * @return
     * @throws BusinessException
     */
    @Override
    public AgencyModel validLogin(String agencyAccount, String encryptPassword) throws BusinessException {
        AgencyModel agencyModel = getAgencyByAgencyAccount(agencyAccount);
        if (agencyModel == null) {
            throw new BusinessException(BusinessError.ADMIN_LOGIN_FAIL);
        }
        if (agencyModel.getEncryptPassword().equals(encryptPassword)) {
            return agencyModel;
        }
        return null;
    }

    /**
     * 根据经销商手机号查询经销商
     * @param agencyAccount
     * @return
     * @throws BusinessException
     */
    public AgencyModel getAgencyByAgencyAccount(String agencyAccount) throws BusinessException {
        AgencyDO agencyDO = agencyDOMapper.selectByAgencyAccount(agencyAccount);
        if (agencyDO == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "未找到信息！");
        }
        return convertFromAgencyDO2Model(agencyDO);
    }

    /**
     * 添加经销商信息
     * @param agencyModel
     * @return
     * @throws BusinessException
     */
    @Override
    public int addAgency(AgencyModel agencyModel) throws BusinessException {
        if (agencyModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR);
        }
        AgencyDO agencyDO = convertFromAgencyModel2DO(agencyModel);

        try {
            agencyDOMapper.insertSelective(agencyDO);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "手机号已被注册");
        }
        return 0;
    }
    private AgencyModel convertFromAgencyDO2Model(AgencyDO agencyDO) {
        if (agencyDO == null) {
            return null;
        }
        AgencyModel agencyModel = new AgencyModel();
        BeanUtils.copyProperties(agencyDO, agencyModel);
        return agencyModel;
    }
    /**
     * 用户model转化为dataObject
     * @param agencyModel
     * @return
     */
    private AgencyDO convertFromAgencyModel2DO(AgencyModel agencyModel) {
        if (agencyModel == null) {
            return null;
        }
        AgencyDO agencyDO = new AgencyDO();
        BeanUtils.copyProperties(agencyModel, agencyDO);
        return agencyDO;
    }
}
