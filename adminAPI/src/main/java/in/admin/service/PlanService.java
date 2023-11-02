package in.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.admin.entity.Plan;
@Service
public interface PlanService {
	public boolean savePlan(Plan plan);
	public List<Plan> getAlldata();
	public boolean updateData(Plan plan);
	public boolean updateAction(Plan plan);

}
