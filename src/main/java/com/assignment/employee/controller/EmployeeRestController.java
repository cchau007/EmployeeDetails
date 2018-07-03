package com.assignment.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.assignment.employee.model.Employee;
import com.assignment.employee.service.EmployeeService;
import com.assignment.employee.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	public static final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> listAllEmployees() {
		List<Employee> employees = employeeService.findAllEmployees();
		if (employees.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployee(@PathVariable("id") long id) {
		logger.info("Fetching Employee with id {}", id);
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			logger.error("Employee with id {} not found.", id);
			CustomErrorType customErrorType = new CustomErrorType("Employee with id " + id + " is unavailable");
			return new ResponseEntity(customErrorType, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/", method = RequestMethod.POST)
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
		logger.info("New Employee : {}", employee);

		if (employeeService.isEmployeeExist(employee)) {
			logger.error("Unable to create. A Employee with name {} already exist", employee.getName());
			return new ResponseEntity(
					new CustomErrorType(
							"Error occurred in creating emp with name " + employee.getName() + " already exists."),
					HttpStatus.CONFLICT);
		}
		employeeService.saveEmployee(employee);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/employee/{id}").buildAndExpand(employee.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		logger.info("Updating Employee with id {}", id);

		Employee currentEmployee = employeeService.findById(id);

		if (currentEmployee == null) {
			logger.error("Unable to update. Employee with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Employee with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentEmployee.setName(employee.getName());
		currentEmployee.setAge(employee.getAge());
		currentEmployee.setSalary(employee.getSalary());

		employeeService.updateEmployee(currentEmployee);
		return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Employee with id {}", id);

		Employee employee = employeeService.findById(id);
		if (employee == null) {
			logger.error("Unable to delete. Employee with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Error occurred in deleging Employee " + id),
					HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/employee/", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteAllEmployees() {
		logger.info("Deleting All Employees");

		employeeService.findAllEmployees();
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

}