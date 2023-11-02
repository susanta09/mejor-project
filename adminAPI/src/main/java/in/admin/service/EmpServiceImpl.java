package in.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.admin.emailHandl.EmailUtils;
import in.admin.entity.Employee;
import in.admin.repository.EmpRepository;
@Service
public class EmpServiceImpl implements EmpService{
	@Autowired
	private EmpRepository empRepository;
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean saveEmp(Employee employee) {
		String email=employee.getEmail();
		Integer id=empRepository.save(employee).getId();
		if(id!=null)
		{
			emailUtils.sendMail(email);
			return true;
		}else {
			return false;
		}	
	}
	
	public List<Employee> getAlldata()
	{
		List<Employee> el=empRepository.findAll();
		return el;
	}
	
	@Override
	public boolean updateData(Employee employee) {
		
		boolean b=empRepository.existsById(employee.getId());
		if(b)
		{
			empRepository.save(employee);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean updateAction(Employee employee) {
		
		System.out.println(employee);
		boolean b=empRepository.existsById(employee.getId());
		System.out.println(b);
		if(b)
		{
			empRepository.updateActionById(employee.getAction(),employee.getId());
			return true;
		}
		else {
			return false;
		}
	}
	
	
}
