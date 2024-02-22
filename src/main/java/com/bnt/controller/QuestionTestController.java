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
		
	@PostMapping("/{test_id}/{question_id}")
	public ResponseEntity<QuestionsTest> addQuestionToTest(@PathVariable("test_id") Long test_id,
			@PathVariable("question_id") Long question_id) {

		QuestionsTest updatedTest = questionService.addQuestionsById(test_id, question_id);
		return new ResponseEntity<>(updatedTest, HttpStatus.OK);

	}
	
	@GetMapping
	public List<QuestionsTest> getAllQuestionsTest() {
		return questionService.getQuestionsTestList();
	}
	
}
