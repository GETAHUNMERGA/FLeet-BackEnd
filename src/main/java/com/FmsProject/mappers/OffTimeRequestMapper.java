package com.FmsProject.mappers;

import com.FmsProject.models.OffTimeRequestModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OffTimeRequestMapper {

    @Insert("insert into tbOffTimeRequisition(directorate,reqBy,date,vehicleNeededFrom,vehicleNeededTo,nameOfPassengers,residentialArea,departureDate,morningTime,nightTime,returnDate,returnTime,km,reason,telephone,status,requestedFor,requestedTime) values("
            + "#{directorate},#{reqBy},#{date},#{vehicleNeededFrom},#{vehicleNeededTo},#{nameOfPassengers},#{residentialArea},#{departureDate},#{morningTime},#{nightTime},#{returnDate},#{returnTime},#{km},#{reason},#{telephone},'Unauthorized',#{requestedFor},CONVERT(TIME, GETDATE()))")
    void saveOffTimeRequestMapper(OffTimeRequestModel model);

    @Select("SELECT id FROM users where username = #{username}")
    int getUserId(String reqBy);

    @Select("SELECT id FROM tbDirectorate where directorate = #{dir}")
    int getDirId(String dir);

    // @Select("select * from tbOffTimeRequisition where reqBy = #{username} and
    // status != 'Authorized'")
    @Select("select * from tbOffTimeRequisition where reqBy = #{username} ORDER BY id desc")
    List<OffTimeRequestModel> getAllRequests(String username);

    @Select("select * from tbOffTimeRequisition where id = #{id}")
    OffTimeRequestModel getRequestById(String id);

    @Update("update tbOffTimeRequisition set vehicleNeededFrom=#{vehicleNeededFrom},vehicleNeededTo=#{vehicleNeededTo},nameOfPassengers=#{nameOfPassengers},residentialArea=#{residentialArea},departureDate=#{departureDate},morningTime=#{morningTime},nightTime=#{nightTime},returnDate=#{returnDate},returnTime=#{returnTime},km=#{km},reason=#{reason},telephone=#{telephone} where id=#{id}")
    void updateOffTimeRequestMapper(OffTimeRequestModel model);

    @Delete("delete from tbOffTimeRequisition where id =#{id}")
    void cancelOffTimeRequestMapper(Long id);
}
