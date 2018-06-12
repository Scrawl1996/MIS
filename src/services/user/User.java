package services.user;

import java.util.List;
import java.util.Vector;

import dto.user.UserDTO;
import persistences.UserDAO;

public class User {
	private String user_name;
	private String password;

	public User(String name, String psw) {
		this.setUser_name(name);
		this.setPassword(psw);
	}

	public User() {

	}
	public List<UserDTO> getAllusers(){
		List<UserDTO> allUsers = null;
		
		allUsers = new UserDAO().getAllUsers();
		
		return allUsers;
	}
	public boolean findUser(String user_name, String password) {
		boolean result = false;

		result = new UserDAO().findUser(user_name, password);

		return result;
	}

	public boolean deleteUser(UserDTO userDTO) {
		boolean result = false;
		result = new UserDAO().deleteUser(userDTO);
		return result;
	}

	public Vector<Vector<Object>> getalterUsers(UserDTO userDTO) {
		List<UserDTO> alterUsers = null;
		alterUsers = new UserDAO().getalterUser(userDTO);
		return list4vector(alterUsers);
	}

	public Vector<Vector<Object>> getAllUsers() {
		List<UserDTO> allUsers = null;

		allUsers = new UserDAO().getAllUser();

		return list2vector(allUsers);
	}

	public Vector<Vector<Object>> getQueryUsers(UserDTO userDTO) {
		List<UserDTO> queryUsers = null;

		queryUsers = new UserDAO().getQueryUser(userDTO);

		return list3vector(queryUsers);
	}

	private Vector<Vector<Object>> list2vector(List<UserDTO> allUser) {

		Vector<Vector<Object>> result = new Vector<Vector<Object>>();

		for (UserDTO u : allUser) {
			Vector<Object> v = new Vector<Object>();
			v.add(u.getUser_id());
			v.add(u.getUnit_name());
			v.add(u.getName());
			v.add(u.getBirthday());
			v.add(u.getCard_id());
			v.add(u.getDuty());
			result.add(v);
		}

		return result;
	}

	private Vector<Vector<Object>> list4vector(List<UserDTO> alterUser) {

		Vector<Vector<Object>> result = new Vector<Vector<Object>>();

		for (UserDTO u : alterUser) {
			Vector<Object> v = new Vector<Object>();
			v.add(u.getUser_id());
			v.add(u.getName());
			v.add(u.getSex());
			v.add(u.getBirthday());
			v.add(u.getCard_id());
			v.add(u.getUnit_name());
			v.add(u.getTelephone());
			v.add(u.getDuty());
			v.add(u.getTecduty());

			result.add(v);
		}

		return result;
	}

	private Vector<Vector<Object>> list3vector(List<UserDTO> queryUser) {

		Vector<Vector<Object>> result = new Vector<Vector<Object>>();

		for (UserDTO u : queryUser) {
			Vector<Object> v = new Vector<Object>();
			v.add(u.getUser_id());
			v.add(u.getUnit_name());
			v.add(u.getName());
			v.add(u.getBirthday());
			v.add(u.getCard_id());
			v.add(u.getDuty());
			result.add(v);
		}

		return result;
	}

	public boolean saveUser(UserDTO user) {
		boolean saveSuccessful = false;

		saveSuccessful = new UserDAO().saveUser(user);
		return saveSuccessful;

	}

	public boolean updateUser(UserDTO userDTO) {
		boolean updateSuccessful = false;

		updateSuccessful = new UserDAO().updateUser(userDTO);
		return updateSuccessful;
	}

	public boolean nameExisted(String userName) {

		return (new UserDAO().nameExisted(userName));

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public List<UserDTO> getAllusersBydepart(String un_name) {
        List<UserDTO> allUsers = null;
		
		allUsers = new UserDAO().getAllUsersBydepart(un_name);
		
		return allUsers;
	}

	public void countStudent_sum(String tarin_plan_id, String userid) {
		new UserDAO().getcountStudent_sum(tarin_plan_id,userid);
	}
}
