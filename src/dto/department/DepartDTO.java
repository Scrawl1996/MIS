package dto.department;

/**
 * 
 * @author 14713 一种设计模式之间传输数据的软件应用系统 数据传输目标往往是数据访问对象从而从数据库中表检索数据
 */
public class DepartDTO {
	private String unit_id;
	private String up_unit_id;
	private String unit_class;
	private String unit_name;
	private String address;
	private String telephone;
	private String contact_person;
	private String email;
	private String header;
	private String remark;

	public DepartDTO() {
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getUp_unit_id() {
		return up_unit_id;
	}

	public void setUp_unit_id(String up_unit_id) {
		this.up_unit_id = up_unit_id;
	}

	public String getUnit_class() {
		return unit_class;
	}

	public void setUnit_class(String unit_class) {
		this.unit_class = unit_class;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
