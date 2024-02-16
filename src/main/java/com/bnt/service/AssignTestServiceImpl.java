package com.bnt.service;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bnt.ResponseDTO.Tests;
import com.bnt.entity.AssignTest;
import com.bnt.entity.Employee;
import com.bnt.repository.EmployeeRepository;
import com.bnt.repository.TestRepository;
import com.bnt.response.EmployeeResponse;

@Service
public class AssignTestServiceImpl implements AssignTestService {
	
	private EmployeeRepository employeeRepository;

	private TestRepository assignTestRepository;

	private RestTemplate restTemplate;

	public AssignTestServiceImpl(EmployeeRepository employeeRepository, TestRepository assignTestRepository,
			RestTemplate restTemplate) {
		super();
		this.assignTestRepository = assignTestRepository;
		this.restTemplate = restTemplate;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public AssignTest takeTest(Long employeeId, Long testId) {
		Employee employee = employeeRepository.findById(employeeId).orElse(null);

		if (employee == null) {
			throw new RuntimeException("Employee with ID " + employeeId + " not found.");
		}

		Tests test = restTemplate.exchange("http://localhost:8080/tests/" + testId, HttpMethod.GET, null, Tests.class)
				.getBody();

		if (test == null) {
			throw new RuntimeException("Test with ID " + testId + " not found.");
		}

		AssignTest assignTest = new AssignTest();
		assignTest.setEmployeeId(employee.getEmployeeId());
		assignTest.setTestId(test.getTestId());

		return assignTestRepository.save(assignTest);
	}
	
	@Override
	public EmployeeResponse getEmployeeAssignTestList(long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElse(null);
		if (employee == null) {
			throw new RuntimeException("Employee with ID " + employeeId + " not found.");
		}
		List<AssignTest> test = assignTestRepository.findByEmployeeId(employeeId);

		EmployeeResponse response = new EmployeeResponse();
		response.setEmployeeId(employee.getEmployeeId());
		response.setFirstName(employee.getFirstName());
		response.setLastName(employee.getLastName());
		response.setEmail(employee.getEmail());
		response.setPassword(employee.getPassword());
		response.setAssignTests(test);

		return response;
	}

	
}
