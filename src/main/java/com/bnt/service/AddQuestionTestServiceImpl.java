package com.bnt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.entity.Questions;
import com.bnt.entity.QuestionsTest;
import com.bnt.entity.Tests;
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
		questionTest.setTests(test);
		questionTest.setQuestions(question);
		
		return questionTestRepository.save(questionTest);
	}
}
