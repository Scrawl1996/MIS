package persistences.trainingplan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.CommUtil;
import util.DBUtil;
import dto.Trainingplan.TrainingPlanDTO;

public class TrainingPlanDAO {
	private DBUtil dbUtil;
	private Connection con;

	public TrainingPlanDAO() {
		dbUtil = new DBUtil();
		con = dbUtil.getConnection();
	}

	public boolean saveTrains(TrainingPlanDTO trainingPlanDTO) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "INSERT INTO train_plan_info (TRAIN_PLAN_ID,TRAIN_PLAN_NAME,TRAIN_PLAN_YEAR,START_TIME,END_TIME,TRAIN_PLAN_TYPE,IS_FINISH,ZY_NAME,TRAIN_PURPOSE,TRAIN_CONTENT,TEACHER,CLASS_COUNT)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		String train_planid = CommUtil.getId();

		try {
			insertRows = qr.update(con, sql, train_planid,
					trainingPlanDTO.getTrain_plan_name(),
					trainingPlanDTO.getTrain_plan_year(),
					trainingPlanDTO.getStart_time(),
					trainingPlanDTO.getEnd_time(),
					trainingPlanDTO.getTrain_plan_type(),
					trainingPlanDTO.getIs_finish(),
			        trainingPlanDTO.getZy_name(),
			        trainingPlanDTO.getTrain_purpose(),
			        trainingPlanDTO.getTrain_content(),
			        trainingPlanDTO.getTeacher(),
			        trainingPlanDTO.getClass_count());

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
		return result;
	}

	public List<TrainingPlanDTO> getAllTrainingPlan() {
		QueryRunner qr = new QueryRunner();
		List<TrainingPlanDTO> allTrainingPlan = null;

		String sql = "select TRAIN_PLAN_ID,TRAIN_PLAN_YEAR,TRAIN_PLAN_NAME,TRAIN_PLAN_TYPE,ZY_NAME,CREATE_TIME,IS_FINISH from train_plan_info";
		try {
			allTrainingPlan = qr.query(con, sql, new BeanListHandler<TrainingPlanDTO>(
					TrainingPlanDTO.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return allTrainingPlan;
	}

	public List<TrainingPlanDTO> getBaseTrainingPlan(TrainingPlanDTO trainingPlanDTO) {
		QueryRunner qr = new QueryRunner();
		List<TrainingPlanDTO> baseTrainingPlan = null;

		String sql = "select TRAIN_PLAN_YEAR,TRAIN_PLAN_NAME,START_TIME,END_TIME,TRAIN_PLAN_ID,ZY_NAME,TRAIN_PURPOSE,TRAIN_CONTENT,CLASS_COUNT,TEACHER from train_plan_info where TRAIN_PLAN_ID='"+trainingPlanDTO.getTrain_plan_id()+"'";
		try {
			baseTrainingPlan = qr.query(con, sql, new BeanListHandler<TrainingPlanDTO>(
					TrainingPlanDTO.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return baseTrainingPlan;
	}

	public boolean saveTrainPlan(String train_plan_id, String valueAt) {
		boolean result = false;
		QueryRunner qr = new QueryRunner();
		int insertRows = 0;

		String sql = "UPDATE t_base_user_info set TRAIN_PLAN_ID='"+ train_plan_id+"' where USER_ID='"+valueAt+"'";

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
}
