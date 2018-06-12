package persistences.depart;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.DBUtil;
import dto.department.DepartDTO;


public class DepartDAO {
	private DBUtil dbUtil;
	private Connection conn;
	
	
	public DepartDAO(){
		dbUtil = new DBUtil();
		conn = dbUtil.getConnection();
	}
	public DepartDTO Unit_idByname(String name){
		DepartDTO depart = new DepartDTO();
		QueryRunner qr = new QueryRunner();
		String sql = "select UNIT_ID from t_base_unit_info where unit_name = ?";
		
		try {
			depart = qr.query(conn, sql, new BeanHandler<DepartDTO>(DepartDTO.class),name);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return depart;
	}
	public boolean deleteDeparts(DepartDTO departDTO) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "delete from t_base_unit_info  where UNIT_ID='"+ departDTO.getUnit_id()+"'";
		//String userId = CommUtil.getId();
		try {
			insertRows = qr.update(conn, sql);

		//	System.out.println("*******" + insertRows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = (insertRows == 1) ? true : false;
		//System.out.println("*******" + result);
		return result;
	}
	public boolean getDeparts(DepartDTO departDTO) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "insert into t_base_unit_info (UNIT_ID,UP_UNIT_ID,UNIT_NAME,HEADER,remark) values(?,?,?,?,?)";

		try {
			insertRows = qr.update(conn, sql,departDTO.getUnit_id(),departDTO.getUp_unit_id(),departDTO.getUnit_name(),departDTO.getHeader(),departDTO.getRemark());

			//System.out.println("*******" + insertRows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = (insertRows == 1) ? true : false;
		//System.out.println("*******" + result);
		return result;
	}
	public List<DepartDTO> getAllDepart(DepartDTO departDTO){
		List<DepartDTO> allDeparts =  new ArrayList<DepartDTO>();
		String sql = "select UNIT_ID,UP_UNIT_ID,UNIT_NAME,HEADER,remark from t_base_unit_info where UNIT_ID='"+ departDTO.getUnit_id()+"'";
		QueryRunner qr = new QueryRunner();
		
		try {
			allDeparts = qr.query(conn, sql, new BeanListHandler<DepartDTO>(DepartDTO.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return allDeparts;
	}
	public List<DepartDTO> getAllDeparts(){
		List<DepartDTO> allDeparts =  new ArrayList<DepartDTO>();
		String sql = "select * from t_base_unit_info ";
		QueryRunner qr = new QueryRunner();
		
		try {
			allDeparts = qr.query(conn, sql, new BeanListHandler<DepartDTO>(DepartDTO.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return allDeparts;
	}
	
	public DepartDTO getDepartById(String id){
		DepartDTO depart = new DepartDTO();
		QueryRunner qr = new QueryRunner();
		String sql = "select * from t_base_unit_info where unit_id = ?";
		
		try {
			depart = qr.query(conn, sql, new BeanHandler<DepartDTO>(DepartDTO.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return depart;
	}
	public boolean getupdateDepart(DepartDTO departDTO) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "update t_base_unit_info set UNIT_ID='"+departDTO.getUnit_id()+"',UP_UNIT_ID='"+departDTO.getUp_unit_id()+"',UNIT_NAME='"+departDTO.getUnit_name()+"',HEADER='"+departDTO.getHeader()+"',remark='"+departDTO.getRemark()+"' where UNIT_ID='"+departDTO.getUnit_id()+"'";

		try {
			insertRows = qr.update(conn, sql);

			//System.out.println("*******" + insertRows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = (insertRows == 1) ? true : false;
		//System.out.println("*******" + result);
		return result;
	}

}
