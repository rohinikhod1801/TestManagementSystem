package com.bnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.entity.QuestionsTest;
import com.bnt.service.AddQuestionTestService;

@RestController
@RequestMapping("/addQuestionsTest")
public class QuestionTestController {


	@Autowired
	private AddQuestionTestService questionService;		
		
	@PostMapping("/{testId}/{questionId}")
	public ResponseEntity<QuestionsTest> addQuestionToTest(@PathVariable("testId") Long testId,
			@PathVariable("questionId") Long questionId) {

		QuestionsTest updatedTest = questionService.addQuestionsById(testId, questionId);
		return new ResponseEntity<>(updatedTest, HttpStatus.OK);

	}
	
	@GetMapping("/{testId}")
	private ResponseEntity<List<QuestionsTest>> getQuestionsByTestId(@PathVariable("testId") long testId) {
		List<QuestionsTest> test = questionService.getEmployeeAssignTestList(testId);
		return ResponseEntity.status(HttpStatus.OK).body(test);
	}

}
