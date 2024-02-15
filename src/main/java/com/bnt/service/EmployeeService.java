package com.bnt.service;

import java.util.List;

import com.bnt.entity.Employee;

public interface EmployeeService {
	
	public Employee register(Employee employee);
    public Employee login(String email, String password);
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(long employeeId);
    public Employee updateEmployee(Employee employeeDetails);
    public Employee deleteEmployee(long employeeId);
	//Employee getEmployeeById(long employeeId, long testId);
    public Employee getEmployeeBy(long id);
    public Employee takeTest(Long employeeId, Long testId);
	
}
