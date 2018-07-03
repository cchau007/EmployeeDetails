package com.assignment.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.assignment.employee.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.assignment.employee"})
@ComponentScan(" com.assignment.employee")
public class RunUsersApp {

	public static void main(String[] args) {
		SpringApplication.run(RunUsersApp.class, args);
	}
}
