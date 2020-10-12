package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LwfChallanData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;

	@Column(name = "emp_count")
	private int empCount;

	@Column(name = "count_lwf")
	private int countLwf;

	@Column(name = "lwf_no_count")
	private int lwfNoCount;

	@Column(name = "mlwf")
	private float mlwf;

	@Column(name = "employer_mlwf")
	private float employerMlwf;
	
	@Column(name = "location_id")
	private int locationId;
	
	@Column(name = "month")
	private int month;
	
	@Column(name = "employer_value")
	private float employerValue;

	@Column(name = "employee_value")
	private float employeeValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEmpCount() {
		return empCount;
	}

	public void setEmpCount(int empCount) {
		this.empCount = empCount;
	}

	public int getCountLwf() {
		return countLwf;
	}

	public void setCountLwf(int countLwf) {
		this.countLwf = countLwf;
	}

	public int getLwfNoCount() {
		return lwfNoCount;
	}

	public void setLwfNoCount(int lwfNoCount) {
		this.lwfNoCount = lwfNoCount;
	}

	public float getMlwf() {
		return mlwf;
	}

	public void setMlwf(float mlwf) {
		this.mlwf = mlwf;
	}

	public float getEmployerMlwf() {
		return employerMlwf;
	}

	public void setEmployerMlwf(float employerMlwf) {
		this.employerMlwf = employerMlwf;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public float getEmployerValue() {
		return employerValue;
	}

	public void setEmployerValue(float employerValue) {
		this.employerValue = employerValue;
	}

	public float getEmployeeValue() {
		return employeeValue;
	}

	public void setEmployeeValue(float employeeValue) {
		this.employeeValue = employeeValue;
	}

	@Override
	public String toString() {
		return "LwfChallanData [id=" + id + ", empCount=" + empCount + ", countLwf=" + countLwf + ", lwfNoCount="
				+ lwfNoCount + ", mlwf=" + mlwf + ", employerMlwf=" + employerMlwf + ", locationId=" + locationId
				+ ", month=" + month + ", employerValue=" + employerValue + ", employeeValue=" + employeeValue + "]";
	}
	
	

}
