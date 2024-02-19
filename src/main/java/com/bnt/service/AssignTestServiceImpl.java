package com.bnt.service;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bnt.ResponseDTO.QuestionsTest;
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

	    ResponseEntity<QuestionsTest[]> responseEntity = restTemplate.exchange(
	            "http://localhost:8080/addQuestionsTest/" + testId,
	            HttpMethod.GET,
	            null,
	            QuestionsTest[].class
	    );

	    if (responseEntity.getStatusCode() != HttpStatus.OK) {
	        throw new RuntimeException("Failed to retrieve questions for test with ID " + testId);
	    }

	    QuestionsTest[] tests = responseEntity.getBody();
	    if (tests == null || tests.length == 0) {
	        throw new RuntimeException("No questions found for test with ID " + testId);
	    }

	    // For simplicity, just take the first QuestionsTest object
	    QuestionsTest test = tests[0];

	    AssignTest assignTest = new AssignTest();
	    assignTest.setEmployeeId(employee.getEmployeeId());
	    assignTest.setTestId(test.getTestId());
	    assignTest.setQuestionId(test.getQuestionId());
	    assignTest.setContent(test.getContent());
	    assignTest.setOption1(test.getOption1());
	    assignTest.setOption2(test.getOption2());
	    assignTest.setOption3(test.getOption3());
	    assignTest.setOption4(test.getOption4());
	    assignTest.setMarks(test.getMarks());

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
		response.setAssignTests(test);

		return response;
	}

	
}
