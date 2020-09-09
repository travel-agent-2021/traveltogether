package com.travelia.service.Impl;

import com.travelia.entity.AgencyDO;
import com.travelia.mapper.AgencyDOMapper;
import com.travelia.service.AgencyService;
import com.travelia.service.model.AgencyModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        return convertFormAgencyDO2Model(agencyDO);
    }

    @Override
    public List<AgencyModel> getAllAgencies() {
        List<AgencyDO> agencyDOS = agencyDOMapper.selectAllAgencies();
        List<AgencyModel> agencyModels = new ArrayList<>();
        for (AgencyDO agencyDO: agencyDOS) {
            agencyModels.add(convertFormAgencyDO2Model(agencyDO));
        }
        return agencyModels;
    }

    private AgencyModel convertFormAgencyDO2Model(AgencyDO agencyDO) {
        if (agencyDO == null) {
            return null;
        }
        AgencyModel agencyModel = new AgencyModel();
        BeanUtils.copyProperties(agencyDO, agencyModel);
        return agencyModel;
    }

}
