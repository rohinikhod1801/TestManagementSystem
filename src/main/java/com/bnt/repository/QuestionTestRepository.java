package com.bnt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.entity.QuestionsTest;

@Repository
public interface QuestionTestRepository  extends JpaRepository<QuestionsTest, Long>{
	
	List<QuestionsTest> findByTestId(long testId);
}
