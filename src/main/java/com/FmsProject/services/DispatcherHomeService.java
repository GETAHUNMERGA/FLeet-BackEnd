package com.FmsProject.services;

import com.FmsProject.mappers.DispatcherHomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispatcherHomeService {
    @Autowired
    private DispatcherHomeMapper dispatcherMapper;

    public int getNumOfIncity() {
        return dispatcherMapper.getNumOfIncity();
    }

    public int getNumOfOfftime() {
        return dispatcherMapper.getNumOfOfftime();
    }

    public int getNumOfField() {
        return dispatcherMapper.getNumOfField();
    }

    public int getNumOfPooledVehicle() {
        return dispatcherMapper.getNumOfPooledVehicle();
    }

    public int getNumOfMaintenanceVehicle() {
        return dispatcherMapper.getNumOfMaintenanceVehicle();
    }

    public int getNumOfDisposedVehicle() {
        return dispatcherMapper.getNumOfDisposedVehicle();
    }

    public int getNumOfDispatchedVehicle() {
        return dispatcherMapper.getNumOfDispatchedVehicle();
    }
}
