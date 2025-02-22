package com.FmsProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.DriverMapper;
import com.FmsProject.models.DriverModel;
import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.VehicleModel;

@Service
public class DriverService {
	
	@Autowired
	private DriverMapper _driverMapper;

	public List<DriverModel> getDrivers() {
		return _driverMapper.getDrivers();
	}

	public DriverModel saveDrivers(DriverModel driv) {
		//System.out.println(driv);
	   _driverMapper.saveDrivers(driv);
	   _driverMapper.updateActiveVehicle(driv.getPlateNo());
	   return driv;
	   
	}

	public Optional<DriverModel> getDriverById(Integer id) {
		return _driverMapper.getDriverById(id);
	}

	public void deleteDriver(Long id) {
		_driverMapper.deleteDriver(id);
	}

	public void updateDriver(DriverModel driv) {
	  _driverMapper.updateDriver(driv);
	}

	public Optional<VehicleModel> getVehicleByPlateNo(String plateNo) {
		return _driverMapper.getVehicleByPlateNo(plateNo);
	}

	public void updateDriverVehicleId(DriverModel vid) {
		_driverMapper.updateDriverVehicleId(vid);
	}

	public List<VehicleModel> getVehicleByStatus() {
		return _driverMapper.getVehicleByStatus();
	}

	public Optional<DriverModel> getDriverByPlateNo(String plateNo) {
		return _driverMapper.getDriverByPlateNo(plateNo);
	}
//
//	public void updateVehicleStatus(VehicleModel vid) {
//		_driverMapper.updateVehicleStatus(vid);
//	}
//
//	public void updateDriverStatus(DriverModel driver) {
//		_driverMapper.updateDriverStatus(driver);
//	}

	public List<DriverModel> getDriverByStatus() {
		return _driverMapper.getDriverByStatus();
	}

	public List<VehicleModel> getActiveVehicle() {
		return _driverMapper.getActiveVehicle();
	}

	public Optional<DriverModel> getDriverByPhone(String phone) {
		return _driverMapper.getDriverByPhone(phone);
	}

	public Optional<DriverModel> getDriverByFullname(String fullname) {
		return _driverMapper.getDriverByFullname(fullname);
	}

	public void disableDriver(DriverModel driv) {
//		System.out.println(driv);
	_driverMapper.disableDriver(driv);
	}

	public void enableDriver(DriverModel driv) {
	_driverMapper.enableDriver(driv);
	}

//	public void updateActiveVehicle(VehicleModel vid) {
//		_driverMapper.updateActiveVehicle(vid);
//	}

}
