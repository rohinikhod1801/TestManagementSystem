package com.bnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.entity.Questions;
import com.bnt.entity.QuestionsTest;
import com.bnt.entity.TestResponse;
import com.bnt.entity.Tests;
import com.bnt.exception.DuplicateQuestionsException;
import com.bnt.repository.QuestionRepository;
import com.bnt.repository.QuestionTestRepository;
import com.bnt.repository.TestRepository;

@Service
public class AddQuestionTestServiceImpl implements AddQuestionTestService{
	
	@Autowired
	private TestRepository testRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	QuestionTestRepository questionTestRepository;

	@Override
	public QuestionsTest addQuestionsById(Long testId, Long questionId) {

		Questions question = questionRepository.findById(questionId).orElse(null);
		
		Tests test = testRepository.findById(testId).orElse(null);
		
		QuestionsTest questionTest =new QuestionsTest();
		questionTest.setTestId(test.getTestId());
		questionTest.setQuestionId(question.getQuestionId());
		questionTest.setContent(question.getContent());
		questionTest.setOption1(question.getOption1());
		questionTest.setOption2(question.getOption2());
		questionTest.setOption3(question.getOption3());
		questionTest.setOption4(question.getOption4());		
		questionTest.setMarks(question.getMarks());
		
		return questionTestRepository.save(questionTest);
	}

	@Override
	public List<QuestionsTest> getEmployeeAssignTestList(long testId) {
		Tests test = testRepository.findById(testId).orElse(null);
		if (test == null) {
			throw new RuntimeException("Employee with ID " + test + " not found.");
		}
		
		return questionTestRepository.findByTestId(testId);
	}


}
