package com.FmsProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.DispatcherMapper;
import com.FmsProject.mappers.DriverMapper;
import com.FmsProject.mappers.FieldRequestMapper;
import com.FmsProject.mappers.OffTimeDispatcherMapper;
import com.FmsProject.models.DispatcherModel;
import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.FuelDetailDto;
import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.OffTimeDispatcherModel;
import com.FmsProject.models.VehicleModel;
import com.FmsProject.models.VehicleTypesModel;

@Service
public class DispatcherService {

	@Autowired
	private DispatcherMapper _dispatcherMapper;

	@Autowired
	private DriverMapper _driver;
	FieldRequestModel model = new FieldRequestModel();

	@Autowired
	private FieldRequestMapper _field;

	@Autowired
	private OffTimeDispatcherMapper mapper;

	public List<DispatcherModel> getRequsition() {
		return _dispatcherMapper.getRequisition();
	}

	public DispatcherModel approveRequests(DispatcherModel req) {
		// Integer vtype = Integer.parseInt(req.getVehicleType());
		// req.setVehicleType(vtype);
		_dispatcherMapper.approveRequests(req);
		// _driver.updateDriverStatus(req.getPlateNo());
		_driver.updateVehicleStatus(req.getPlateNo());
		_field.updateRequesterStatus(req.getRequesterID());
		// _field.updateApprover(req.getApprovedBy(), req.getApprovedDate(),
		// req.getRequesterID());
		mapper.changeDriverAvailability(req.getDriverName());
		return req;

	}

	public Optional<DispatcherModel> getRequestById(Integer id) {
		return _dispatcherMapper.getRequestById(id);
	}

	public Optional<DispatcherModel> getDispatcherByRequesterID(Integer reqid) {
		return _dispatcherMapper.getDispatcherByRequesterID(reqid);
	}

	// public List<DispatcherModel> getDispatcherByRequestID() {
	// return _dispatcherMapper.getDispatcherByRequesterID();
	// }
	public List<DispatcherModel> getAllAprovedRequest() {
		return _dispatcherMapper.getAllAprovedRequest();
	}

	public void updateApprovedRequest(DispatcherModel driv) {
		// System.out.println(driv);
		_dispatcherMapper.updateApprovedRequest(driv);
		_dispatcherMapper.updateVehicleStatus(driv.getKmReadingOnReturn(), driv.getPlateNo());
		mapper.updateDriver(driv.getDriverName());

	}

	public List<VehicleTypesModel> getAllVehicleTypes() {
		return _dispatcherMapper.getAllVehicleTypes();
	}

	public List<VehicleModel> getVehicleByType(Integer vtypes) {
		return _dispatcherMapper.getVehicleByType(vtypes);
	}

	public Optional<VehicleTypesModel> getVehicleTypeById(Integer id) {
		return _dispatcherMapper.getVehicleTypeById(id);
	}

	public Optional<DispatcherModel> getDispatchedByDriverName(String driverName) {
		return _dispatcherMapper.getDispatchedByDriverName(driverName);
	}

	public Optional<InCityDispatcherModel> getIncityDispatchedByDriverName(String driverName) {
		return _dispatcherMapper.getIncityDispatchedByDriverName(driverName);
	}

	public Optional<OffTimeDispatcherModel> getOfftimeDispatchedByDriverName(String driverName) {
		return _dispatcherMapper.getOfftimeDispatchedByDriverName(driverName);
	}

	public Optional<FuelDetailDto> getFuelTypeById(Integer id) {
		return _dispatcherMapper.getFuelTypeById(id);
	}
}
