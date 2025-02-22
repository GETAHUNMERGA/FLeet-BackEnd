package com.FmsProject.services;

import com.FmsProject.mappers.OffTimeRequestMapper;
import com.FmsProject.models.OffTimeRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffTimeRequestService {
    @Autowired
    private OffTimeRequestMapper map;

    public void saveofftimeRequestService(OffTimeRequestModel model) {
        String mt = String.valueOf(model.getMorningTime());
        String nt = String.valueOf(model.getNightTime());
        String vFrom = String.valueOf(model.getVehicleNeededFrom());
        String vTo = String.valueOf(model.getVehicleNeededTo());
        String rTime = String.valueOf(model.getReturnTime());
        model.setMorningTime(mt);
        model.setNightTime(nt);
        model.setVehicleNeededFrom(vFrom);
        model.setVehicleNeededTo(vTo);
        model.setReturnTime(rTime);
        map.saveOffTimeRequestMapper(model);
    }

    public int getUserId(String reqBy) {

        return map.getUserId(reqBy);
    }

    public int getDirId(String dirId) {
        return map.getDirId(dirId);
    }

    public List<OffTimeRequestModel> getAllRequests(String username) {
        return map.getAllRequests(username);
    }

    public OffTimeRequestModel getRequestById(String id) {
        return map.getRequestById(id);
    }

    public void updateofftimeRequestService(OffTimeRequestModel model) {

        map.updateOffTimeRequestMapper(model);
    }

    public void cancelOffTimeRequestService(Long id) {
        map.cancelOffTimeRequestMapper(id);
    }
}
