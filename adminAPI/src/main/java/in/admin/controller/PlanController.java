package in.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.admin.entity.Plan;
import in.admin.service.PlanService;

@RestController
public class PlanController {
	@Autowired
	private PlanService planService;

	@PostMapping("plan")
	public ResponseEntity<?> createPlan(@RequestBody Plan plan)
	{
		boolean b=planService.savePlan(plan);
		if(b)
		{
			String msg="Plan  is created successfully";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}else {
			String msg="Plan is not created";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
	}
	@GetMapping("/plans")
	public ResponseEntity<?> getAllPlan()
	{
		List<Plan>planList=planService.getAlldata();
		if(!planList.isEmpty())
		{
			return new ResponseEntity<>(planList,HttpStatus.OK);
		}else {
			String msg="Plans are not available";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
	}
	@PutMapping("/plan/{id}")
	public ResponseEntity<?> updatePlain(@PathVariable("id") Integer id,@RequestBody Plan plan)
	{
		plan.setId(id);
		boolean b=planService.updateData(plan);
		if(b)
		{
			String msg="Plan id updated";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}else {
			String msg="Plan Record is not founded";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
	}
	@PatchMapping("/plan/{id}")
	public ResponseEntity<?> changeActionById(@PathVariable("id") Integer id,@RequestBody String action)
	{
		System.out.println(action);
		Plan plan= new Plan();
		plan.setId(id);
		plan.setAction(action);
		boolean b=planService.updateAction(plan);
		if(b)
		{
			String msg="Action is updated";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}else
		{
			String msg="Plan is not founded";
			System.out.println(b);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		
	}
}
