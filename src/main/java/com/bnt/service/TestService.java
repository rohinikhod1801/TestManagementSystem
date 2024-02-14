package com.bnt.service;

import java.util.List;

import com.bnt.entity.TestResponse;
import com.bnt.entity.Tests;

public interface TestService {
	
	public Tests addTest(Tests test);

	public List<TestResponse> getAllTest();

	public Tests getTestById(Long testId);

	public Tests updateTest(Tests test);

	public void deleteTest(Long testId);
}
