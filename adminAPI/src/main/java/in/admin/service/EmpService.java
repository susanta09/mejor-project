package in.admin.service;

import java.util.List;

import in.admin.entity.Employee;

public interface EmpService {
	public boolean saveEmp(Employee employee);
	public List<Employee> getAlldata();
	public boolean updateData(Employee employee);
	public boolean updateAction(Employee employee);
}
