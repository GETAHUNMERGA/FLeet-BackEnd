package com.FmsProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.DispatcherPrintMapper;
import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.InCityRequestModel;
import com.FmsProject.models.OffTimeRequestModel;

@Service
public class DispatcherPrintService {

    @Autowired
    DispatcherPrintMapper mapper;

    public List<InCityRequestModel> getApprovedIncityRequest() {
        return mapper.getApprovedIncityRequest();
    }

    public List<OffTimeRequestModel> getApprovedOfftimeRequest() {
        return mapper.getApprovedOfftimeRequest();
    }

    public List<FieldRequestModel> getApprovedFieldRequest() {
        return mapper.getApprovedFieldRequest();
    }

}
