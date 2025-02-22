package com.FmsProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.DispatcherReportMapper;
import com.FmsProject.models.DispatcherModel;
import com.FmsProject.models.DispatcherReportModel;
import com.FmsProject.models.InCityDispatcherModel;
import com.FmsProject.models.OffTimeDispatcherModel;

@Service
public class DispatcherReportService {
	@Autowired
	private DispatcherReportMapper _mapper;

	public List<DispatcherModel> dispatcherFieldReport(DispatcherReportModel report) {
		 List<DispatcherModel> response = null;
	        if (report.getFieldfromDate() != null || report.getFieldtoDate() != null) {
	            response = _mapper.dispatcherFieldReport(report);
	        } 
	        return response;
	}

	public List<InCityDispatcherModel> dispatcherIncityReport(DispatcherReportModel report) {
		 List<InCityDispatcherModel> response = null;
	        if (report.getIncityFromDate() != null || report.getIncityToDate() != null) {
	            response = _mapper.dispatcherIncityReport(report);
	        } 
	        return response;
	}

	public List<OffTimeDispatcherModel> dispatcherOfftimeReport(DispatcherReportModel report) {
		 List<OffTimeDispatcherModel> response = null;
		 if (report.getOfftimeFromDate() != null || report.getOfftimeToDate() != null) {
	            response = _mapper.dispatcherOfftimeReport(report);
	        } 
	        return response;
	}

	public List<DispatcherModel> getAllFieldDispatch() {
		return _mapper.getAllFieldDispatch();
	}

	public List<InCityDispatcherModel> getAllIncityDispatch() {
		return _mapper.getAllIncityDispatch();
	}

	public List<OffTimeDispatcherModel> getAllOfftimeDispatch() {
		return _mapper.getAllOfftimeDispatch();
	}


}
