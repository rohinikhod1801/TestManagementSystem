package com.bnt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.entity.AssignTest;
import com.bnt.entity.Employee;
import com.bnt.entity.Tests;
import com.bnt.service.EmployeeService;
import com.bnt.service.TestService;

@RestController
public class TestAssignController {
	
	private final EmployeeService employeeService;
    private final TestService testService;
 
    public TestAssignController(EmployeeService employeeService, TestService testService) {
        this.employeeService = employeeService;
        this.testService = testService;
    }
	
	
    @PostMapping("/assign-test")
    public ResponseEntity<String> assignTestToEmployee(@RequestBody AssignTest requestDTO) {
      
        Employee employee = employeeService.getEmployeeById(requestDTO.getEmployeeId());
        Tests test = testService.getTestById(requestDTO.getTestId());

        employee.getAssignedTests().add(test);
 
        employeeService.updateEmployee(employee);
 
        return ResponseEntity.ok("Test assigned to employee successfully");
    }
}

	
