package com.assignment.employee.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5815327415583005733L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;

	@Column(name="AGE", nullable=false)
	private Integer age;

	@Column(name="SALARY", nullable=false)
	private double salary;
	
	@Column(name="PREV_ORG", nullable=false)
	private String previousOrganization;
	
	@Column(name="CITY", nullable=false)
	private String city;
	
	@Column(name="STATE", nullable=false)
	private String state;
	

	public String getPreviousOrganization() {
		return previousOrganization;
	}

	public void setPreviousOrganization(String previousOrganization) {
		this.previousOrganization = previousOrganization;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Employee employee = (Employee) o;

		if (Double.compare(employee.salary, salary) != 0) return false;
		if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
		if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
		return age != null ? age.equals(employee.age) : employee.age == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (age != null ? age.hashCode() : 0);
		temp = Double.doubleToLongBits(salary);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age
				+ ", salary=" + salary + "]";
	}


}
