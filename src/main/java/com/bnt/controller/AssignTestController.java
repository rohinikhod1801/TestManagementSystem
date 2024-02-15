package com.bnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.entity.Employee;
import com.bnt.service.EmployeeService;

@RestController
@RequestMapping("/tests")
public class AssignTestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/{employeeId}/{testId}")
	public ResponseEntity<Employee> takeTest(@PathVariable Long employeeId, @PathVariable Long testId) {
		Employee employee=employeeService.takeTest(employeeId, testId);
		return ResponseEntity.ok(employee);
	}
	
	
	@GetMapping("/employees/{employeeId}")
	private ResponseEntity<Employee> getEmployeeDetails(@PathVariable("employeeId") long employeeId) {
		Employee employee = employeeService.getEmployeeBy(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

}
