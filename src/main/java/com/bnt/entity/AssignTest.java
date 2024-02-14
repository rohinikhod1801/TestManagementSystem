package com.bnt.entity;

public class AssignTest {
	private Long employeeId;
	private Long testId;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public AssignTest() {
		super();
	}

	public AssignTest(Long employeeId, Long testId) {
		super();
		this.employeeId = employeeId;
		this.testId = testId;
	}

}
