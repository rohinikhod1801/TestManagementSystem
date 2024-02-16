package com.bnt.service;

import com.bnt.entity.AssignTest;
import com.bnt.response.EmployeeResponse;

public interface AssignTestService {

	public EmployeeResponse getEmployeeAssignTestList(long employeeId);

	public AssignTest takeTest(Long employeeId, Long testId);
}
