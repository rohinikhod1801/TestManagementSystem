package com.bnt.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "assignTest")
public class AssignTest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long testId;

	@Column(name = "title")
	private String title;

	@ManyToMany
	@JoinTable(name = "employee_test", joinColumns = @JoinColumn(name = "test_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private List<Employee> employees;

	public AssignTest() {
		super();
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	
}
