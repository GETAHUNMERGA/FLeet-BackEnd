package com.FmsProject.services;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.FieldRequestMapper;
import com.FmsProject.models.DispatcherModel;
import com.FmsProject.models.FieldRequestModel;
import com.FmsProject.models.VehicleModel;

@Service
public class FieldRequestService {

	@Autowired
	private FieldRequestMapper _fieldMapper;

	public List<FieldRequestModel> getRequsition() {
		return _fieldMapper.getRequisition();
	}

	public FieldRequestModel sendRequisition(FieldRequestModel req) {
		_fieldMapper.sendRequest(req);
		return req;
	}

	public Long requisitionCount() {
		return _fieldMapper.requestCount();
	}

	public Optional<FieldRequestModel> selectRequestById(Integer id) {
		return _fieldMapper.selectRequestById(id);
	}

	public void deleteRequest(Long id) {
		_fieldMapper.deleteRequest(id);
	}

	public void updateRequest(FieldRequestModel req) {
		_fieldMapper.updateRequest(req);
	}

	public void approveRequest(FieldRequestModel req) {
		_fieldMapper.approveRequest(req);
	}

	public void rejectRequest(FieldRequestModel req) {
		// System.out.println(req);
		_fieldMapper.rejectRequest(req);
		// _fieldMapper.updateRejecter(req.getRejectedBy(), req.getId());
		// _fieldMapper.updateRejecter(approver, dateApproved, reqID);
	}

	public List<FieldRequestModel> getRequsitionDispatch() {
		// TODO Auto-generated method stub
		return _fieldMapper.getRequisitionForDispatch();
	}

	public void addRejecter(FieldRequestModel rejecter) {
		// System.out.println("your data is rejected by " + rejecter);
		_fieldMapper.addRejecter(rejecter);
	}

	public List<FieldRequestModel> getAllApprovedRequest() {
		return _fieldMapper.getAllApprovedRequest();
	}

	public List<VehicleModel> getDispatchedVehicle() {
		return _fieldMapper.getDispatchedVehicle();
	}

	public List<FieldRequestModel> getmyRequsition(String username) {
		return _fieldMapper.getmyRequsition(username);
	}

}
