package com.bnt.controller;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bnt.repository.EmployeeRepository;
import com.bnt.repository.TestRepository;

@SpringBootTest
class TestAssignControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private TestRepository assignTestRepository;

    @InjectMocks
    private TestAssignController testAssignController;

    
}
