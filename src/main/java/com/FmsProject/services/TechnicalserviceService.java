package com.FmsProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.TechnicalServiceMapper;
import com.FmsProject.models.GarageModel;
import com.FmsProject.models.MaintenanceRequestModel;

@Service
public class TechnicalserviceService {
	@Autowired
	private TechnicalServiceMapper _mapper;

	public List<MaintenanceRequestModel> getAllMaintenenceRequest() {
		return _mapper.getAllMaintenenceRequest();
	}

	public void assignGarage(MaintenanceRequestModel req) {
		// System.out.println(req);
		_mapper.assignGarage(req);
	}

	public List<GarageModel> getGarage() {
		return _mapper.getGarage();
	}

	public List<MaintenanceRequestModel> getMaintenanceCompletedVehicle() {
		return _mapper.getMaintenanceCompletedVehicle();
	}

	public int getNoOfAuthorizedrequest() {
		return _mapper.getNoOfAuthorizedrequest();
	}

	public int getNoOfUnderMaintenance() {
		return _mapper.getNoOfUnderMaintenance();
	}

	public int getNoOfGarage() {
		return _mapper.getNoOfGarage();
	}

	public MaintenanceRequestModel getMaintenanceRequest(Integer maintId) {
		MaintenanceRequestModel response = _mapper.getMaintenanceRequest(maintId);
		String maintenanceType = _mapper.getMaintenanceType(maintId);
		response.setMaintenanceType(maintenanceType);
		// if (response.getMaintenanceType() != null) {
		// Integer mainType = Integer.parseInt(response.getMaintenanceType());
		// String maintenanceType = _mapper.getMaintenanceType(mainType);
		// response.setMaintenanceType(maintenanceType);
		// }
		return response;
	}

}
