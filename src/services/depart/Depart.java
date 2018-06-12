package services.depart;

import java.util.List;

import persistences.depart.DepartDAO;
import dto.department.DepartDTO;

public class Depart {
	
	public List<DepartDTO> getAllDeparts(){
		List<DepartDTO> allDeparts = null;
		
		allDeparts = new DepartDAO().getAllDeparts();
		
		return allDeparts;
	}
	public List<DepartDTO> getDepartByIds(DepartDTO departDTO){
		List<DepartDTO> DepartByIds = null;
		
		DepartByIds = new DepartDAO().getAllDepart(departDTO);
		
		return DepartByIds;
	}
	public boolean getDepart(DepartDTO departDTO) {
		boolean saveSuccessful = false;

		saveSuccessful = new DepartDAO().getDeparts(departDTO);
		return saveSuccessful;

	}
	public boolean updateDepart(DepartDTO departDTO) {
		boolean updateSuccessful = false;

		updateSuccessful = new DepartDAO().getupdateDepart(departDTO);
		return updateSuccessful;

	}
	public boolean deleteDepart(DepartDTO departDTO) {
		boolean result = false;
		result = new DepartDAO().deleteDeparts(departDTO);
		return result;
	}

	
	public DepartDTO getDepartById(String id){
		DepartDTO depart = null;
		
		depart = new DepartDAO().getDepartById(id);
		return depart;
	}
	public DepartDTO getUnit_idByname(String name){
		DepartDTO depart = null;
		
		depart = new DepartDAO().Unit_idByname(name);
		return depart;
	}

}
