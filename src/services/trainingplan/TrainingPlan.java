package services.trainingplan;

import java.util.List;
import java.util.Vector;

import persistences.trainingplan.TrainingPlanDAO;
import dto.Trainingplan.TrainingPlanDTO;

public class TrainingPlan {

	private int i = 1;

	public boolean saveTrain(TrainingPlanDTO trainingPlanDTO) {
		boolean saveSuccessful = false;

		saveSuccessful = new TrainingPlanDAO().saveTrains(trainingPlanDTO);
		return saveSuccessful;
	}

	public Vector<Vector<Object>> getBaseTrainingPlans(
			TrainingPlanDTO trainingPlanDTO) {
		List<TrainingPlanDTO> BaseTrainingPlan = null;

		BaseTrainingPlan = new TrainingPlanDAO()
				.getBaseTrainingPlan(trainingPlanDTO);

		return list3vector(BaseTrainingPlan);
	}

	private Vector<Vector<Object>> list3vector(
			List<TrainingPlanDTO> BaseTrainingPlan) {
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();

		for (TrainingPlanDTO t : BaseTrainingPlan) {
			Vector<Object> v = new Vector<Object>();
			v.add(t.getTrain_plan_year());
			v.add(t.getTrain_plan_name());
			v.add(t.getStart_time());
			v.add(t.getEnd_time());
			v.add(t.getTrain_plan_id());
			v.add(t.getZy_name());
			v.add(t.getTrain_purpose());
			v.add(t.getTrain_content());
			v.add(t.getClass_count());
			v.add(t.getTeacher());
			result.add(v);
		}

		return result;
	}

	public Vector<Vector<Object>> getAllTrainingPlans() {
		List<TrainingPlanDTO> allTrainingPlan = null;

		allTrainingPlan = new TrainingPlanDAO().getAllTrainingPlan();

		return list2vector(allTrainingPlan);
	}

	private Vector<Vector<Object>> list2vector(
			List<TrainingPlanDTO> allTrainingPlan) {
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();

		for (TrainingPlanDTO t : allTrainingPlan) {
			int count = 0;
			Vector<Object> v = new Vector<Object>();
			v.add(i++);
			v.add(t.getTrain_plan_id());
			v.add(t.getTrain_plan_year());
			v.add(t.getTrain_plan_name());
			v.add(t.getTrain_plan_type());
			v.add(t.getZy_name());
			v.add(t.getCreate_time());
			v.add(t.getIs_finish());
			v.add(count);
			result.add(v);
		}

		return result;
	}

	public boolean saveTrain_plan(String train_plan_id, String valueAt) {
		boolean result = false;
		result = new TrainingPlanDAO().saveTrainPlan(train_plan_id, valueAt);
		return result;

	}

}
