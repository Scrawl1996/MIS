package forms;

import dto.department.DepartDTO;

public class KeyValue {
	private String name;
	private DepartDTO depart;
	
	public KeyValue(String name,DepartDTO depart){
		this.name = name;
		this.depart = depart;
	}
	
	public String toString(){
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DepartDTO getDepart() {
		return depart;
	}

	public void setDepart(DepartDTO depart) {
		this.depart = depart;
	}
	
	
}
