package com.bnt.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.bnt.entity.AssignTest;
import com.bnt.entity.AssignTestResponse;
import com.bnt.entity.TestsDTO;
import com.bnt.exception.EmployeeNotFoundException;
import com.bnt.exception.TestIdNotExistException;
import com.bnt.repository.EmployeeRepository;
import com.bnt.repository.TestRepository;

@RestController
@RequestMapping("/assignTests")
public class TestAssignController {

	private static final Logger logger = LoggerFactory.getLogger(TestAssignController.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TestRepository assignTestRepository;

	private final WebClient webClient;

	public TestAssignController(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
	}

	@PostMapping("/{employeeId}/{test_id}")
	public ResponseEntity<AssignTest> assignTest(@PathVariable("test_id") Long test_id,
			@PathVariable("employeeId") Long employeeId) {
		AssignTest assignTest = new AssignTest();
		try {

			if (employeeRepository.existsById(employeeId)) {

				assignTest.setEmployeeId(employeeId);
				assignTest.setTestId(test_id);
				logger.info("Getting employee by ID: {}", assignTest.toString());
				assignTestRepository.save(assignTest);
			}

		} catch (EmployeeNotFoundException ex) {
			logger.error("Employee not found with ID: {}", employeeId, ex);
			throw new EmployeeNotFoundException("Error occurred while getById employee" + ex);
		}
		return ResponseEntity.status(HttpStatus.OK).body(assignTest);
	}

	@GetMapping("/call-server/{employeeId}")
	public AssignTestResponse callServer(@PathVariable("employeeId") Long employeeId) {
		AssignTestResponse response = new AssignTestResponse();
		List<TestsDTO> testDto = new ArrayList<>();
		try {			
			List<AssignTest> assignTest = assignTestRepository.findByEmployeeId(employeeId);
			for (AssignTest test : assignTest) {
				TestsDTO td = webClient.get().uri("/tests/{test_id}", test.getTestId()).retrieve()
						.bodyToMono(TestsDTO.class).block();
				testDto.add(td);
			}
			response.setEmployeeId(employeeId);
			response.setTests(testDto);
		} catch (TestIdNotExistException ex) {
			logger.error("Tests not found with ID: {}", testDto, ex);
			throw new TestIdNotExistException("Error occurred while getById Tests" + ex);
		}
		return response;
	}

}
