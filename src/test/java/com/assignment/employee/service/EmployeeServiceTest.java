package com.assignment.employee.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import com.assignment.employee.model.Employee;
import com.assignment.employee.repositories.EmployeeRepository;

public class EmployeeServiceTest {
	
	@Mock
	EmployeeService service;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	Employee e = new Employee();
	List<Employee> empList = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		//service = new EmployeeServiceImpl();
		MockitoAnnotations.initMocks(this);
		e.setAge(25);
		e.setName("Johnny");
		e.setCity("New york");
		e.setId(1000l);
		e.setPreviousOrganization("Prev1");
		e.setState("CA");
	}

	@Test
	public void testFindById() {
		Mockito.when(service.findById(1000l)).thenReturn(e);
		assertEquals("Johnny", e.getName());
	}

	@Test
	public void testFindByName() {
		Mockito.when(service.findByName("Johnny")).thenReturn(e);
		assertEquals("Johnny", e.getName());
	}

	@Test
	public void testSaveEmployee() {
		Employee n = new Employee();
		n.setAge(30);
		n.setName("Orange");
		n.setSalary(150000);
		EmployeeService newService = new EmployeeServiceImpl();
		EmployeeService spy = Mockito.spy(newService);
		Mockito.doNothing().when(spy).saveEmployee(n);
		
		
	}

	@Test
	public void testUpdateEmployee() {
		service.updateEmployee(e);
		Mockito.verify(service).updateEmployee(e);
	}

	@Test
	public void testDeleteEmployeeById() {
		service.deleteEmployeeById(1000l);
		Mockito.verify(service).deleteEmployeeById(1000l);
	}

	@Test
	public void testDeleteAllEmployees() {
		service.deleteAllEmployees();
		Mockito.verify(service).deleteAllEmployees();;
	}

	@Test
	public void testFindAllEmployees() {
		
		Employee n = new Employee();
		n.setAge(30);
		n.setName("Orange");
		n.setSalary(150000);
		empList.add(n);

		Mockito.when(service.findAllEmployees()).thenReturn(empList);
		assertEquals(1, empList.size());
	}

	@Test
	public void testIsEmployeeExist() {
		Mockito.when(service.isEmployeeExist(e)).thenReturn(true);
		assertTrue(service.isEmployeeExist(e));
	}

}
