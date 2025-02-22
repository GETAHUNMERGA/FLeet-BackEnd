package com.FmsProject.mappers;

import org.apache.ibatis.annotations.Select;

public interface DispatcherHomeMapper {

    @Select("select count(id) from incityrequest where status = 'Authorized'")
    int getNumOfIncity();

    @Select("select count(id) from tbOffTimeRequisition where status = 'Checked'")
    int getNumOfOfftime();

    @Select("select count(id) from fieldRequest where status = 'Checked'")
    int getNumOfField();

    @Select("select count(id) from tbVehicle where status = 'Pool'")
    int getNumOfPooledVehicle();

    @Select("select count(id) from tbVehicle where status = 'Maintenance'")
    int getNumOfMaintenanceVehicle();

    @Select("select count(id) from tbVehicle where status = 'Disposed'")
    int getNumOfDisposedVehicle();

    @Select("select count(id) from tbVehicle where status = 'Dispatched'")
    int getNumOfDispatchedVehicle();
}
