package com.travelia.service;

import com.travelia.service.model.AgencyModel;
import com.travelia.service.model.ItemModel;

import java.util.List;

public interface AgencyService {

    AgencyModel getAgencyById(Integer id);

    List<AgencyModel> getAllAgencies();

}
