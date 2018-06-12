package persistences;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dto.user.UserDTO;
import util.CommUtil;
import util.DBUtil;

public class UserDAO {
	private DBUtil dbUtil;
	private Connection con;

	public UserDAO() {
		dbUtil = new DBUtil();
		con = dbUtil.getConnection();
	}

	public boolean findUser(String name, String psw) {
		boolean result = false;
		QueryRunner qr = new QueryRunner();
		UserDTO userDTO = null;

		String sql = "select * from t_base_user_info where user_name=? and password=?";

		try {
			userDTO = qr.query(con, sql,
					new BeanHandler<UserDTO>(UserDTO.class), name, psw);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (userDTO != null) {
			result = true;
		}
		return result;
	}

	public List<UserDTO> getAllUser() {
		QueryRunner qr = new QueryRunner();
		List<UserDTO> allUsers = null;

		String sql = "select user_id,depart.unit_name,NAME,birthday,card_id,duty from t_base_user_info userInfo,t_base_unit_info depart where userInfo.unit_id = depart.unit_id";
		try {
			allUsers = qr.query(con, sql, new BeanListHandler<UserDTO>(
					UserDTO.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return allUsers;

	}

	public boolean saveUser(UserDTO user) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "insert into t_base_user_info (USER_ID,NAME,SEX,BIRTHDAY,CARD_ID,UNIT_ID,TELEPHONE,DUTY,TECDUTY) values(?,?,?,?,?,?,?,?,?)";
		String userId = CommUtil.getId();

		try {
			insertRows = qr.update(con, sql, userId, user.getName(), user
					.getSex().trim(), user.getBirthday(), user.getCard_id(),
					user.getUnit_id(), user.getTelephone(), user.getDuty(),
					user.getTecduty());

			// System.out.println("*******" + insertRows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = (insertRows == 1) ? true : false;
		// System.out.println("*******" + result);
		return result;
	}

	public Boolean nameExisted(String userName) {
		boolean existed = false;
		UserDTO userDTO = null;

		QueryRunner qr = new QueryRunner();
		String sql = "select user_id,user_name,password from t_base_user_info where user_name = ?";
		try {
			userDTO = qr.query(con, sql,
					new BeanHandler<UserDTO>(UserDTO.class), userName);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (userDTO != null) {
			System.out.println("inputed name:" + userName + "****");
			System.out.println("query name:" + userDTO.getUser_name() + "****");
		}
		existed = userDTO == null ? false : true;
		return existed;
	}

	public List<UserDTO> getQueryUser(UserDTO userDTO) {
		QueryRunner qr = new QueryRunner();
		List<UserDTO> queryUsers = null;
		String sql = "select USER_ID,depart.unit_name,NAME,BIRTHDAY,CARD_ID,DUTY from t_base_user_info userInfo ,t_base_unit_info depart  where NAME='"
				+ userDTO.getName()
				+ "' and SEX='"
				+ userDTO.getSex()
				+ "' and userInfo.unit_id=depart.unit_id";
		try {
			queryUsers = qr.query(con, sql, new BeanListHandler<UserDTO>(
					UserDTO.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryUsers;

	}

	public boolean deleteUser(UserDTO userDTO) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "delete from t_base_user_info where USER_ID='"
				+ userDTO.getUser_id() + "'";
		// String userId = CommUtil.getId();
		try {
			insertRows = qr.update(con, sql);

			// System.out.println("*******" + insertRows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = (insertRows == 1) ? true : false;
		// System.out.println("*******" + result);
		return result;
	}

	public boolean updateUser(UserDTO userDTO) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "UPDATE t_base_user_info SET NAME='" + userDTO.getName()
				+ "', SEX='" + userDTO.getSex() + "', BIRTHDAY='"
				+ userDTO.getBirthday() + "', CARD_ID='" + userDTO.getCard_id()
				+ "', UNIT_ID='" + userDTO.getUnit_id() + "', TELEPHONE='"
				+ userDTO.getTelephone() + "', DUTY='" + userDTO.getDuty()
				+ "',TECDUTY='" + userDTO.getTecduty() + "' where USER_ID='"
				+ userDTO.getUser_id() + "'";
		// String userId = CommUtil.getId();
		try {
			insertRows = qr.update(con, sql);

			// System.out.println("*******" + insertRows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = (insertRows == 1) ? true : false;
		// System.out.println("*******" + result);
		return result;
	}

	public List<UserDTO> getalterUser(UserDTO userDTO) {
		QueryRunner qr = new QueryRunner();
		List<UserDTO> alterUsers = null;
		String sql = "select USER_ID,NAME,SEX,BIRTHDAY,CARD_ID,depart.unit_name,userInfo.TELEPHONE,userInfo.DUTY,userInfo.TECDUTY from t_base_user_info userInfo ,t_base_unit_info depart  where USER_ID='"
				+ userDTO.getUser_id() + "'and userInfo.unit_id=depart.unit_id";
		try {
			alterUsers = qr.query(con, sql, new BeanListHandler<UserDTO>(
					UserDTO.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/*
		 * System.out.println(alterUsers); if (alterUsers != null) {
		 * System.out.println("****"); }
		 */
		return alterUsers;

	}

	public List<UserDTO> getAllUsers() {
		List<UserDTO> allUsers = new ArrayList<UserDTO>();
		String sql = "select USER_ID,NAME,DUTY,depart.unit_name from t_base_user_info userInfo ,t_base_unit_info depart  where userInfo.unit_id=depart.unit_id";
		QueryRunner qr = new QueryRunner();

		try {
			allUsers = qr.query(con, sql, new BeanListHandler<UserDTO>(
					UserDTO.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return allUsers;
	}

	public List<UserDTO> getAllUsersBydepart(String un_name) {
		List<UserDTO> allUsersBydepart = new ArrayList<UserDTO>();
		String sql = "select USER_ID,NAME,DUTY,depart.unit_name from t_base_user_info userInfo ,t_base_unit_info depart  where UNIT_NAME='"
				+ un_name + "' and userInfo.unit_id=depart.unit_id";
		QueryRunner qr = new QueryRunner();

		try {
			allUsersBydepart = qr.query(con, sql, new BeanListHandler<UserDTO>(
					UserDTO.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return allUsersBydepart;
	}

	@SuppressWarnings("unused")
	public void getcountStudent_sum(String tarin_plan_id, String userid) {
		int insertRows = 0;
		String sql="update t_base_user_info set plan_number =(select count(USER_ID) from  t_base_user_info where train_plan_id='"+tarin_plan_id+"') where USER_ID='"+userid+"'";
		QueryRunner qr = new QueryRunner();
		try {
			 insertRows = qr.update(con, sql);
			// System.out.println("*******" + insertRows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
