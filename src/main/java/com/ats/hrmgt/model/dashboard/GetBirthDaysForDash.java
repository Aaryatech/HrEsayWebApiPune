package com.ats.hrmgt.model.dashboard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GetBirthDaysForDash {
	@Id

	private int empId;

	private String name;

	private String dob;
	
	
	private String empCode;
	
	
	private int age;

	
	
	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "GetBirthDaysForDash [empId=" + empId + ", name=" + name + ", dob=" + dob + ", empCode=" + empCode
				+ ", age=" + age + "]";
	}

 
	
	

}