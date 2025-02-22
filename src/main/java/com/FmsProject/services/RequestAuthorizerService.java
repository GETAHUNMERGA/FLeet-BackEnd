package com.FmsProject.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.FmsProject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.RequestAuthorizerMapper;

@Service
public class RequestAuthorizerService {
	@Autowired
	RequestAuthorizerMapper map;

	public List<OffTimeRequestModel> getOfftimeRequests(String directorate) {
		// TODO Auto-generated method stub
		int directorId = map.getDirectorId(directorate);
		// System.out.println(directorId+""+directorate);
		return map.getOfftimeRequests(directorId);
	}

	public OffTimeRequestModel authorizeOfftimeRequests(OffTimeRequestModel offtime) {
		Date date = new Date();
		offtime.setAuthorizedDate(date);
		map.authorizeOfftimeRequests(offtime);
		return offtime;
	}

	public OffTimeRequestModel rejectOfftimeRequests(OffTimeRequestModel offtime) {
		// TODO Auto-generated method stub
		Date date = new Date();
		offtime.setAuthorizedDate(date);
		map.rejectOfftimeRequests(offtime);
		return offtime;
	}

	public List<InCityRequestModel> getInCityRequests(String directorate) {
		// TODO Auto-generated method stub
		return map.getInCityRequests(directorate);
	}

	public InCityRequestModel authorizeInCityRequests(InCityRequestModel incity) {
		// TODO Auto-generated method stub
		Date date = new Date();
		incity.setAuthorizedDate(date);
		map.authorizeInCityRequests(incity);
		return incity;
	}

	public InCityRequestModel rejectIncityRequests(InCityRequestModel incity) {
		// TODO Auto-generated method stub
		Date date = new Date();
		incity.setAuthorizedDate(date);
		map.rejectIncityRequests(incity);
		return incity;
	}

	public List<FieldRequestModel> getFieldRequests(String directorate) {
		// TODO Auto-generated method stub
		return map.getFieldRequests(directorate);
	}

	public FieldRequestModel authorizeFieldRequests(FieldRequestModel field) {
		// TODO Auto-generated method stub
		Date date = new Date();
		field.setAuthorizedDate(date);
		map.authorizeFieldRequests(field);
		return field;
	}

	public FieldRequestModel rejectFieldRequests(FieldRequestModel field) {
		// TODO Auto-generated method stub
		Date date = new Date();
		field.setAuthorizedDate(date);
		map.rejectFieldRequests(field);
		return field;
	}

	public List<OffTimeRequestModel> getOfftimeReport(AuthReqReport reqReport) {
		List<OffTimeRequestModel> response = null;
		int directorateId = map.getDirectorId(reqReport.getDirectorate());
		reqReport.setDirectorate(String.valueOf(directorateId));
		if (reqReport.getFrom() == null || reqReport.getTo() == null) {
			if (reqReport.getListing().equals("Authorized")) {
				response = map.getAuthorizedOfftimeRequests(reqReport);
			} else if (reqReport.getListing().equals("Non-Authorized")) {
				response = map.getNonAuthorizedOfftimeRequests(reqReport);
			}

		} else {
			if (reqReport.getListing().equals("Authorized")) {

				response = map.getAuthorizedOfftimeRequestsRange(reqReport);

			} else if (reqReport.getListing().equals("Non-Authorized")) {

				response = map.getNonAuthorizedOfftimeRequestsRange(reqReport);

			}
		}
		return response;
	}

	public List<InCityRequestModel> getIncityReport(AuthReqReport reqReport) {
		List<InCityRequestModel> response = null;
		if (reqReport.getFrom() == null || reqReport.getTo() == null) {
			if (reqReport.getListing().equals("Authorized")) {
				response = map.getAuthorizedIncityRequests(reqReport);

			} else if (reqReport.getListing().equals("Non-Authorized")) {
				response = map.getNonAuthorizedIncityRequests(reqReport);
			}

		} else {
			if (reqReport.getListing().equals("Authorized")) {

				response = map.getAuthorizedIncityRequestsRange(reqReport);

			} else if (reqReport.getListing().equals("Non-Authorized")) {

				response = map.getNonAuthorizedIncityRequestsRange(reqReport);

			}
		}
		return response;
	}

	public List<FieldRequestModel> getFieldReport(AuthReqReport reqReport) {

		List<FieldRequestModel> response = null;
		if (reqReport.getFrom() == null || reqReport.getTo() == null) {
			if (reqReport.getListing().equals("Authorized")) {
				response = map.getAuthorizedFieldRequests(reqReport);
			} else if (reqReport.getListing().equals("Non-Authorized")) {
				response = map.getNonAuthorizedFieldRequests(reqReport);
			}

		} else {
			if (reqReport.getListing().equals("Authorized")) {

				response = map.getAuthorizedFieldRequestsRange(reqReport);

			} else if (reqReport.getListing().equals("Non-Authorized")) {

				response = map.getNonAuthorizedFieldRequestsRange(reqReport);

			}
		}
		return response;
	}

	public List<UserModel> getAllUsers(String directorate) {
		return map.getAllUsers(directorate);
	}

	public UserModel delegateAuthorizer(UserModel user) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String d = dateFormat.format(date);
		try {
			user.setCreatedDate(dateFormat.parse(d));
			user.setStatus("Active");
			map.delegateAuthorizer(user);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.recordDelegationHistory(user);
		return user;
	}

	public UserModel deactivateDelegation(UserModel user) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String d = dateFormat.format(date);
		try {
			user.setCreatedDate(dateFormat.parse(d));
			user.setStatus("Deactivated");
			map.delegateAuthorizer(user);
			map.deactivateDelegation(user);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.recordDelegationHistory(user);
		return user;
	}

	public List<UserModel> getMechanicUsers() {
		return map.getMechanicUsers();
	}

	public List<UserModel> getDispatcherUsers() {
		return map.getDispatcherUsers();
	}
}
