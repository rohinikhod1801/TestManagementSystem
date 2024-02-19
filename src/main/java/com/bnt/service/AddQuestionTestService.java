package com.bnt.service;

import java.util.List;

import com.bnt.entity.QuestionsTest;

public interface AddQuestionTestService {

	
	public QuestionsTest addQuestionsById(Long testId,Long questionId);

	public List<QuestionsTest> getEmployeeAssignTestList(long testId);
}
