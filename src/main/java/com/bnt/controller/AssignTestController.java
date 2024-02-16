package com.bnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.entity.AssignTest;
import com.bnt.response.EmployeeResponse;
import com.bnt.service.AssignTestService;

@RestController
@RequestMapping("/tests")
public class AssignTestController {
	
	@Autowired
	private AssignTestService assignService;
	
	
	
	@PostMapping("/{employeeId}/{testId}")
	public ResponseEntity<AssignTest> takeTest(@PathVariable Long employeeId, @PathVariable Long testId) {
		AssignTest employee=assignService.takeTest(employeeId, testId);
		return ResponseEntity.ok(employee);
	}
	
	
	@GetMapping("/{employeeId}")
	private ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("employeeId") long employeeId) {
		EmployeeResponse employee = assignService.getEmployeeAssignTestList(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

}
