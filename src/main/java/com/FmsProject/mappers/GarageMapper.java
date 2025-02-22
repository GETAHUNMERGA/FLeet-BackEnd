package com.FmsProject.mappers;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.FmsProject.models.GarageModel;

public interface GarageMapper {

	@Insert("insert into tbGarage(name,location,phone) values("+"#{name},#{location},#{phone})")
	void registerGarage(GarageModel mod);

	@Select("select * from tbGarage where phone=#{phone}")
	Optional<GarageModel> checkGaragePhone(String phone);

	@Select("select * from tbGarage")
	List<GarageModel> getGarage();

	@Select("select * from tbGarage where id =#{id}")
	Optional<GarageModel> selectGarageById(Integer id);
	
	@Update("update tbGarage set name=#{name},location=#{location},phone=#{phone} where id=#{id}")
	void editGarage(GarageModel req);

	@Delete("delete from tbGarage where id =#{id}")
	void disposeGarage(Long id);
}
