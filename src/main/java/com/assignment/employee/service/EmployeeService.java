package com.assignment.employee.service;


import java.util.List;

import com.assignment.employee.model.Employee;

public interface EmployeeService {
	
	Employee findById(Long id);

	Employee findByName(String name);

	void saveEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployeeById(Long id);

	void deleteAllEmployees();

	List<Employee> findAllEmployees();

	boolean isEmployeeExist(Employee employee);
}