package com.bnt.response;

import java.util.List;

import com.bnt.entity.AssignTest;

public class EmployeeResponse {

	private long employeeId;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private List<AssignTest> assignTests;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AssignTest> getAssignTests() {
		return assignTests;
	}

	public void setAssignTests(List<AssignTest> assignTests) {
		this.assignTests = assignTests;
	}

}
