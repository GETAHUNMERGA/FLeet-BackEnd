package com.FmsProject.services;

import com.FmsProject.mappers.OffTimeDispatcherMapper;
import com.FmsProject.mappers.VehicleMapper;
import com.FmsProject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OffTimeDispatcherService {

    @Autowired
    private OffTimeDispatcherMapper mapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    public List<OffTimeRequestModel> getAuthorizedRequests() {
        return mapper.getAuthorizedRequests();
    }

    public List<OffTimeRequestModel> getDispatchedRequests() {
        return mapper.getDispatchedRequests();
    }

    @Transactional
    public void dispatcherApprove(OffTimeDispatcherModel offTimeDispatcherModel) {
        mapper.dispatcherApprove(offTimeDispatcherModel);
        String plateNo = offTimeDispatcherModel.getPlateNo();

        mapper.approveInRequest(offTimeDispatcherModel);
        vehicleMapper.dispatchVehicle(plateNo);
        mapper.changeDriverAvailability(offTimeDispatcherModel.getDriverName());
    }

    public String getDriverName(String plateNo) {

        return mapper.getDriverName(plateNo);
    }

    public void dispatcherReject(Integer reqId, String reason, String approvedBy, String approvedDate) {
        mapper.dispatcherReject(reqId, reason, approvedBy, approvedDate);
    }

    public double getDepartureKm(String plateNo) {
        return mapper.getDepartureKm(plateNo);
    }

    public OffTimeDispatcherModel getDispatchedById(String id) {
        return mapper.getDispatchedById(id);
    }

    @Transactional
    public void updateDispatched(OffTimeDispatcherModel offTimeDispatcherModel) {
        mapper.updateDispatched(offTimeDispatcherModel);
        // mapper.updateRequest(offTimeDispatcherModel.getReqId());
        vehicleMapper.updateLastMileage(offTimeDispatcherModel.getPlateNo(), offTimeDispatcherModel.getKmOnRet());
        mapper.updateDriver(offTimeDispatcherModel.getDriverName());
    }

    public OfftimePrintDto getDispatchedPrintOut(String id) {
        return mapper.getDispatchedPrintOut(id);
    }

    public List<DriverDetailDto> getDriverDetail() {
        return mapper.getDriverDetail();
    }

    public Integer driverAvailable(String driverName) {
        return mapper.driverAvailable(driverName);
    }

    public OffTimeRequestModel getRequestById(String id) {
        return mapper.getRequestById(id);
    }

    public List<FuelDetailDto> getFuelDetail() {
        return mapper.getFuelDetail();
    }

    public void updateFuelPrice(FuelDetailDto fuelDetail) {
        mapper.updateFuelPrice(fuelDetail);
    }
}
