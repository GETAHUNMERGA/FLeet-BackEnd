package com.FmsProject.services;

import com.FmsProject.mappers.VehicleMapper;
import com.FmsProject.models.FuelDetailDto;
import com.FmsProject.models.MaintenanceTypeModel;
import com.FmsProject.models.VehicleModel;
import com.FmsProject.models.VehicleTypesModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class VehicleService {

    @Autowired
    private VehicleMapper mapper;

    public void addVehicle(VehicleModel vehicleModel) {
        Integer id = mapper.getVehicleTypeId(vehicleModel.getVehicleTypeString());
        vehicleModel.setVehicleType(id);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String d = dateFormat.format(date);
        try {
            vehicleModel.setCreatedDate(dateFormat.parse(d));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (Objects.equals(vehicleModel.getCustodian(), "General Technical and Security Service Directorate")
                && Objects.equals(vehicleModel.getIsStandBy(), "no")) {
            vehicleModel.setStatus("Pool");
        } else if (Objects.equals(vehicleModel.getCustodian(), "General Technical and Security Service Directorate")
                && Objects.equals(vehicleModel.getIsStandBy(), "yes")) {

            vehicleModel.setStatus("StandBy");
        } else {
            vehicleModel.setStatus("Assigned to Respective");
        }
        mapper.addVehicle(vehicleModel);
    }

    public List<VehicleModel> getAllVehicles() {
        return mapper.getAllVehicles();
    }

    public void updateVehicle(VehicleModel vehicleModel) {
        Integer id = mapper.getVehicleTypeId(vehicleModel.getVehicleTypeString());
        vehicleModel.setVehicleType(id);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String d = dateFormat.format(date);
        try {
            vehicleModel.setCreatedDate(dateFormat.parse(d));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
         * #1 condition checks if custodian general and not standby, assigned to
         * respective, not dispatched, not maintenance
         * it will put vehicle in the pool
         * #2 if directorate general and standby status selected vehicle status will be
         * changed to StandBy
         * #3 if directorate not general and status where pool status will be changed to
         * assigned to respective
         */
        if (Objects.equals(vehicleModel.getCustodian(), "General Technical and Security Service Directorate")
                && (Objects.equals(vehicleModel.getIsStandBy(), "no")
                        || Objects.equals(vehicleModel.getStatus(), "Assigned to Respective"))
                && !Objects.equals(vehicleModel.getStatus(), "Dispatched")
                && !Objects.equals(vehicleModel.getStatus(), "Maintenance")) {
            vehicleModel.setStatus("Pool");
        } else if (Objects.equals(vehicleModel.getCustodian(), "General Technical and Security Service Directorate")
                && Objects.equals(vehicleModel.getIsStandBy(), "yes")) {
            vehicleModel.setStatus("StandBy");
        } else if (!Objects.equals(vehicleModel.getCustodian(), "General Technical and Security Service Directorate")
                && Objects.equals(vehicleModel.getStatus(), "Pool")) {
            vehicleModel.setStatus("Assigned to Respective");
        }
        mapper.updateVehicle(vehicleModel);
    }

    public void disposeVehicle(int id) {
        mapper.disposeVehicle(id);
    }

    public List<VehicleModel> getVehiclesOnPool(String vtype) {
        return mapper.getVehiclesOnPool(vtype);
    }

    public boolean vehicleAlreadyExists(String plateNo) {
        return mapper.vehicleAlreadyExists(plateNo);
    }

    public int getNoOfServiceMillageDue() {
        return mapper.getNoOfServiceMillageDue();
    }

    public int getNoOfTyreMillageDue() {
        return mapper.getNoOfTyreMillageDue();
    }

    public int getNoOfInsuranceDue() {
        return mapper.getNoOfInsuranceDue();
    }

    public List<VehicleModel> getServiceMillageDue() {
        return mapper.getServiceMillageDue();
    }

    public List<VehicleModel> getTyresMillageDue() {
        return mapper.getTyresMillageDue();
    }

    public List<VehicleModel> getInsuranceMillageDue() {
        return mapper.getInsuranceMillageDue();
    }

    public List<MaintenanceTypeModel> getServiceMaintenanceTypes() {
        return mapper.getServiceMaintenanceTypes();
    }

    public List<MaintenanceTypeModel> getTyresMaintenanceTypes() {
        return mapper.getTyresMaintenanceTypes();
    }

    public List<VehicleModel> getCustodianVehicle(String director) {
        return mapper.getCustodianVehicle(director);
    }

    public List<VehicleTypesModel> getVehicleTypes() {
        return mapper.getVehicleTypes();
    }

    public List<FuelDetailDto> getFuelTypes() {
        return mapper.getFuelTypes();
    }

    public int getNoOfftimeReq() {
        return mapper.getNoOfftimeReq();
    }

    public int getNoFieldReq() {
        return mapper.getNoFieldReq();
    }
}
