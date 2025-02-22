package com.FmsProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FmsProject.mappers.MaintenanceCompletionMapper;
import com.FmsProject.models.MaintenanceCompletionModel;
import com.FmsProject.models.MaintenanceCostReportModel;
import com.FmsProject.models.MaintenanceRequestModel;

@Service
public class MaintenanceCompletionService {
	@Autowired
	private MaintenanceCompletionMapper _mapper;

	public MaintenanceCompletionModel completeMaintenance(MaintenanceCompletionModel comp) {
		// System.out.println(comp);
		_mapper.completeMaintenance(comp);
		_mapper.updateMaintenanceReqStatus(comp.getRequestId());
		return comp;
	}

	public List<MaintenanceCompletionModel> getMaintenanceCompletion() {
		return _mapper.getMaintenanceCompletion();
	}

	public void updateCompletion(MaintenanceCompletionModel ment) {
		// System.out.println(ment);
		if (ment.getDateFromGarage() == null) {
			_mapper.updateCompletionCost(ment);
		} else {
			_mapper.updateCompletion(ment);
		}
	}

	public Float maintenanceConstCount() {
		return _mapper.maintenanceConstCount();
	}

	public List<MaintenanceCompletionModel> costReport(MaintenanceCostReportModel report) {
		// System.out.println(report.getFromMonth().substring(5));
		// System.out.println(report.getFromMonth().substring(5));
		List<MaintenanceCompletionModel> response = null;
		try {
			if ((report.getFromMonth() != null || report.getToMonth() != null) && report.getPlateNo() == null) {
				response = _mapper.monthlyCostReport(report.getFromMonth().substring(5));
			} else if ((report.getFromMonth() != null || report.getToMonth() != null) && report.getPlateNo() != null) {
				response = _mapper.monthlyCostReportPlateNo(report.getFromMonth().substring(5), report.getPlateNo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public List<MaintenanceCompletionModel> yearlyMaintenanceCostReport(MaintenanceCostReportModel report) {

		List<MaintenanceCompletionModel> response = null;
		try {

			if ((report.getFromYear() != null || report.getToYear() != null) && report.getPlateNo() == null) {
				// response = _mapper.yearlyMaintenanceCostReport(report);
				response = _mapper.yearlyCostReport(report.getFromYear().substring(0, 4));
			} else if ((report.getFromYear() != null || report.getToYear() != null) && report.getPlateNo() != null) {
				// response = _mapper.yearlyMaintenanceCostReport(report);
				response = _mapper.yearlyCostReportPlateNo(report.getFromYear().substring(0, 4), report.getPlateNo());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public double totalMonthlyCost(MaintenanceCostReportModel report) {
		double maintenanceCost = 0;
		try {
			if (report.getPlateNo() == null) {
				maintenanceCost = _mapper.totalMonthlyCost(report.getFromMonth().substring(5));
			} else {
				maintenanceCost = _mapper.totalMonthlyCostPlateNo(report.getFromMonth().substring(5),
						report.getPlateNo());
			}
		} catch (Exception e) {
			// TODO: handle exception
			maintenanceCost = 0;
		}

		return maintenanceCost;

	}

	public double totalYearlyCost(MaintenanceCostReportModel report) {
		double maintenanceCost = 0;
		try {
			if (report.getPlateNo() == null) {
				maintenanceCost = _mapper.totalYearlyCost(report.getFromYear().substring(0, 4));
			} else {
				maintenanceCost = _mapper.totalYearlyCostPlateNo(report.getFromYear().substring(0, 4),
						report.getPlateNo());
			}
		} catch (Exception e) {
			// TODO: handle exception
			maintenanceCost = 0;
		}

		return maintenanceCost;
	}

	public Float byplateNocostReport(String string) {
		return _mapper.byplateNocostReport(string);
	}

	public Float totalplatecost(String string) {
		return _mapper.totalplatecost(string);
	}

}
