package com.bnt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bnt.entity.AssignTest;
import com.bnt.entity.Employee;
import com.bnt.exception.DuplicateEmployeeException;
import com.bnt.exception.EmployeeNotFoundException;
import com.bnt.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;	
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
    private RestTemplate restTemplate;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee register(Employee employee) {
		if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
			throw new DuplicateEmployeeException("Employee with email '" + employee.getEmail() + "' already exists");
		}
		return employeeRepository.save(employee);
	}

	@Override
	public Employee login(String email, String password) {

		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + employeeId));

		Employee getEmployee = new Employee();
		getEmployee.setEmployeeId(employee.getEmployeeId());
		getEmployee.setFirstName(employee.getFirstName());
		getEmployee.setLastName(employee.getLastName());
		getEmployee.setEmail(employee.getEmail());
		getEmployee.setPassword(employee.getPassword());
		//getEmployee.setAssignTests(employee.getAssignTests());
		// getEmployee.setTests(employee.getTests());
		return getEmployee;
	}

	@Override
	public Employee updateEmployee(Employee employeeDetails) {
		Employee employee = employeeRepository.findById(employeeDetails.getEmployeeId()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeDetails.getEmployeeId()));

		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		employee.setPassword(employeeDetails.getPassword());
		//employee.setAssignTests(employeeDetails.getAssignTests());
		return employeeRepository.save(employee);
	}

	@Override
	public Employee deleteEmployee(long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));
		employeeRepository.delete(employee);
		return employee;
	}

	
	public Employee getEmployeeBy(long id) {
		
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
           
            Employee savedEmployee = employeeRepository.save(employee);

            Employee employeeResponse = mapper.map(savedEmployee, Employee.class);

            AssignTest addressResponse = restTemplate.getForObject("http://localhost:8082/address/{id}", AssignTest.class, id);
            List<AssignTest> update=new ArrayList<>();
            update.add(addressResponse);
            employeeResponse.setAssignTests(update);

            return employeeResponse;
    }
		return null;
	
}
/*	
public Employee takeTest(Long employeeId, Long testId) {

	Employee employee = getEmployeeById(employeeId);

	AssignTest test = restTemplate
			.exchange("http://localhost:8080/tests/" + testId, HttpMethod.GET, null, AssignTest.class).getBody();

	List<AssignTest> assign = new ArrayList<>();
	assign.add(test);
	Employee employees = new Employee();
	employees.setEmployeeId(employee.getEmployeeId());
	employees.setFirstName(employee.getFirstName());
	employees.setLastName(employee.getLastName());
	employees.setEmail(employee.getEmail());
	employees.setPassword(employee.getPassword());
	employees.setAssignTests(assign);

	//Employee emp = updateEmployee(employees);
	return  employeeRepository.save(employee);
}*/
public Employee takeTest(Long employeeId, Long testId) {
	Employee employee = employeeRepository.findById(employeeId).orElse(null);

    if (employee == null) {
        throw new RuntimeException("Employee with ID " + employeeId + " not found.");
    }

    AssignTest test = restTemplate.exchange("http://localhost:8080/tests/" + testId, HttpMethod.GET, null, AssignTest.class).getBody();

    if (test == null) {
        throw new RuntimeException("Test with ID " + testId + " not found.");
    }

    if (employee.getAssignTests() == null) {
        employee.setAssignTests(new ArrayList<>());
    }
    
    employee.getAssignTests().add(test);

    return employeeRepository.save(employee);
}
}


