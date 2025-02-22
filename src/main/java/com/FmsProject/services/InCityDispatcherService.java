package com.FmsProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.FmsProject.mappers.InCityDispatcherMapper;
import com.FmsProject.mappers.InCityRequestMapper;
import com.FmsProject.mappers.OffTimeDispatcherMapper;
import com.FmsProject.models.DriverModel;
import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.VehicleModel;
import com.FmsProject.models.VehicleTypesModel;

@Service
public class InCityDispatcherService {
	@Autowired
	private InCityDispatcherMapper maper;

	@Autowired
	private InCityRequestMapper incitymapper;

	@Autowired
	private OffTimeDispatcherMapper mapper;

	public InCityDispatcherModel saveIncityRequestService(InCityDispatcherModel model) {
		maper.saveIncityDispatcherMapper(model);
		maper.vehiclestatus(model.getPlateNumber());
		incitymapper.approveRequest(model.getApprovedBy(), model.getApprovedDate(), model.getRequesterId(),
				model.getRemark());
		mapper.changeDriverAvailability(model.getDriverName());

		return model;
	}

	public List<InCityDispatcherModel> viewIncityDispatchService() {
		return maper.viewIncityDispatchMapper();
	}

	public Optional<InCityDispatcherModel> selectRequestById(Integer id) {
		return maper.selectRequestById(id);
	}

	public void rejectRequest(InCityDispatcherModel req) {
		maper.rejectReuest(req);

	}

	public Optional<InCityDispatcherModel> selectDispatcherById(Integer id) {
		return maper.selectDispatcherById(id);
	}

	public List<VehicleModel> getvehiclebystatus(String type) {
		Integer id = maper.getVehicleTypeId(type);
		return maper.getvehiclebystatus(id);
	}

	public List<DriverModel> getDriverName() {
		return maper.getDriverName();
	}

	public Optional<VehicleModel> getDepartureKm(String vehiclePlateNo) {
		return maper.getDepartureKm(vehiclePlateNo);
	}

	public Optional<InCityDispatcherModel> selectDispatchedPlateNo(Integer id) {
		return maper.selectDispatchedPlateNo(id);
	}

	public Optional<DriverModel> getDriverPhoneNumber(String plateNo) {
		return maper.getDriverPhoneNumber(plateNo);
	}

	public void modifyDispatchedReturnKm(InCityDispatcherModel model) {
		maper.modifyDispatchedReturnKm(model);
		maper.modifyVehicleStatus(model.getPlateNumber(), model.getReturnKm());
		maper.returnDriverName(model.getDriverName());
	}

	public List<VehicleTypesModel> getVehicleTypes() {
		return maper.getVehicleTypes();
	}

	public void setDriveOff(String name) {
		maper.setDriveOff(name);

	}

}
