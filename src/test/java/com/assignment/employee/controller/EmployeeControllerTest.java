package com.assignment.employee.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
	
	EmployeeController controller;

	@Before
	public void setUp() throws Exception {
		controller = new EmployeeController();
	}

	@Test
	public void testHome() {
		assertEquals("index", controller.home(new ModelMap()));
	}

	@Test
	public void testPartialHandler() {
		String page = "helloEmployee";
		String expected = controller.partialHandler(page);
		assertEquals(page, expected);
		//fail("Not yet implemented");
	}

}
