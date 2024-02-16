package com.bnt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.entity.AssignTest;

@Repository
public interface TestRepository extends JpaRepository<AssignTest, Long> {

	//@Query(value = "SELECT a.assignTestId ,a.testId, a.employeeId FROM test3.assigntest a WHERE a.employeeId = :employeeId", nativeQuery = true)
    List<AssignTest> findByEmployeeId(long employeeId);
}

