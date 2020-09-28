package com.ats.hrmgt.model.dashboard;

import java.util.Date;
 
import javax.persistence.Entity; 
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetBirthDaysForDash {
	@Id
	private String uuid;
	private int empId;

	private String name;

	private Date dob;

	private String empCode;

	private int age;
	private String dateMonth;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

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

	@JsonFormat(locale = "English", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDateMonth() {
		return dateMonth;
	}

	public void setDateMonth(String dateMonth) {
		this.dateMonth = dateMonth;
	}

	@Override
	public String toString() {
		return "GetBirthDaysForDash [uuid=" + uuid + ", empId=" + empId + ", name=" + name + ", dob=" + dob
				+ ", empCode=" + empCode + ", age=" + age + ", dateMonth=" + dateMonth + "]";
	}

}
