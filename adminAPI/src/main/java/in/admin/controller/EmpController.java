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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.admin.entity.Employee;
import in.admin.service.EmpService;
import jakarta.websocket.server.PathParam;

@RestController
public class EmpController {
	@Autowired
	private EmpService empService;
	@PostMapping("/emp")
	public ResponseEntity<?> collectData(@RequestBody Employee employee)
	{
		System.out.println(employee);
		Boolean b=empService.saveEmp(employee);
		if (b) {		
			String msg="Data successfully inserted";
			return new ResponseEntity<>(msg,HttpStatus.OK);
			
		}else {
			String msg="Please check ,Something is wrong";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
	}
	@GetMapping("/emps")
	public ResponseEntity<?> getEmpData()
	{
		List<Employee>el=empService.getAlldata();
		if (!el.isEmpty()) {
			return new ResponseEntity<>(el,HttpStatus.OK);
		}
		else {
			String msg="record is not avalable";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
	}
	@PutMapping("/emp")
	public ResponseEntity<?> updateEmpData(@RequestBody Employee employee)
	{
		boolean b=empService.updateData(employee);
		if(b)
		{
			String msg="Update is successful";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}else
		{
			String msg="record is not found";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
	}
	@PatchMapping("/emp/{id}")
	public ResponseEntity<?> updateAction(@PathVariable("id") Integer id,@RequestBody String action)
	{
		Employee employee =new Employee();
		System.out.println(id);
		System.out.println(employee.getAction());
		employee.setId(id);
		employee.setId(id);
		boolean b=empService.updateAction(employee);
		if(b)
		{
			
			return new ResponseEntity<>(employee.getAction(),HttpStatus.OK);
		}
		else {
			String msg="Record is not found";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		
	}
}
