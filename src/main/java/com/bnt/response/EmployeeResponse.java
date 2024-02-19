package com.bnt.response;

import java.util.List;

import com.bnt.entity.AssignTest;

public class EmployeeResponse {

	private long employeeId;

	private List<AssignTest> assignTests;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	
	public List<AssignTest> getAssignTests() {
		return assignTests;
	}

	public void setAssignTests(List<AssignTest> assignTests) {
		this.assignTests = assignTests;
	}

}
