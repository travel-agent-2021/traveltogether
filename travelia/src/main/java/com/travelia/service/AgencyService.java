package com.travelia.service;

import com.travelia.error.BusinessException;
import com.travelia.service.model.AgencyModel;
import com.travelia.service.model.ItemModel;


import java.util.List;

public interface AgencyService {

    AgencyModel getAgencyById(Integer id);

    List<AgencyModel> getAllAgencies();

    AgencyModel validLogin(String agencyAccount, String encryptPassword) throws BusinessException;

    int addAgency(AgencyModel agencyModel) throws BusinessException;

    AgencyModel getAgencyByAgencyAccount(String agencyAccount) throws BusinessException;

    int deleteAgency(Integer agencyId);

    int updateAgency(AgencyModel agencyModel) throws BusinessException;

    List<AgencyModel> getAgenciesLikeAgencyAccount(String agencyAccount);
}
