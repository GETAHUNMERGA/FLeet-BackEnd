package com.FmsProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.InCityDispatcherMapper;
import com.FmsProject.mappers.InCityRequestMapper;
import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.InCityRequestModel;
import com.FmsProject.models.OffTimeRequestModel;

@Service
public class InCityRequestService {
	@Autowired
	private InCityRequestMapper map;

	public void saveIncityRequestService(InCityRequestModel model) {
		map.saveIncityRequestMapper(model);
	}

	public List<InCityRequestModel> viewIncityRequestService() {
		return map.viewIncityRequestMapper();
	}

	public Optional<InCityRequestModel> selectRequestById(Integer id) {
		return map.selectRequestById(id);
	}

	public void cancelIncityRequestService(Integer reqid) {
		map.cancelIncityRequestMapper(reqid);
	}

	public void updateIncityRequestService(InCityRequestModel model) {
		map.updateIncityRequestMapper(model);
	}

	public void rejectRequest(InCityRequestModel req, InCityDispatcherModel mod) {
		map.rejectRequest(req);
	}

	public void approveRequest(InCityDispatcherModel req) {
		map.approveRequest(req.getApprovedBy(), req.getApprovedDate(), req.getRequesterId(), req.getRemark());
	}

	public List<InCityRequestModel> viewIncityRequestByUserName(String name) {
		return map.incityrequestviewbyusername(name);
	}

	public List<InCityRequestModel> incityRequestApproved() {
		return map.incityRequestApproved();
	}

}
