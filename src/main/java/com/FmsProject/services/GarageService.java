package com.FmsProject.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.FmsProject.mappers.GarageMapper;
import com.FmsProject.models.GarageModel;

@Service
public class GarageService {

	@Autowired
	private GarageMapper map;
	
	public void registerGarage(GarageModel mod) {
		map.registerGarage(mod);
	}
	
	public Optional<GarageModel> checkGaragePhone(String phone) {
		return map.checkGaragePhone(phone);
	}
	
	public List<GarageModel> getGarage() {
		return map.getGarage();
	}
	
	public Optional<GarageModel> selectGarageById(Integer id) {
		return map.selectGarageById(id);
	}
	
	public void editGarage(GarageModel req) {
		map.editGarage(req);
	}

	public void disposeGarage(Long id) {
		map.disposeGarage(id);
	}

}
