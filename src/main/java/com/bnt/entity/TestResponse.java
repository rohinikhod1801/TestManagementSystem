package com.bnt.entity;

import java.util.List;

public class TestResponse {
	
	private Long testId;
    private List<QuestionsTest> questionIds;
	   
	public Long getTestId() {
		return testId;
	}
	
	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public List<QuestionsTest> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<QuestionsTest> questionIds) {
		this.questionIds = questionIds;
	}
	
}
