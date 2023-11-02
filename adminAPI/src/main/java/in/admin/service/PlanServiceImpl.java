package in.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.admin.entity.Plan;
import in.admin.repository.PlanRepository;
@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanRepository planRepository;

	@Override
	public boolean savePlan(Plan plan) {
		Integer id=planRepository.save(plan).getId();
		if(id!=null)
		{
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<Plan> getAlldata() {
	
		return 	planRepository.findAll();
	}

	@Override
	public boolean updateData(Plan plan) {
		boolean b=planRepository.existsById(plan.getId());
		if(b)
		{
			planRepository.save(plan);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateAction(Plan plan) {
		
		boolean b=planRepository.existsById(plan.getId());
		if(b)
		{
			planRepository.updateActionById(plan.getAction(), plan.getId());
			return true;
		}else {
			System.out.println(plan.getId());
			return false;
		}
	}

}
