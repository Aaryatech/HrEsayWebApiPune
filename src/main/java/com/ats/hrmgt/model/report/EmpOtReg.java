package com.ats.hrmgt.model.report;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class EmpOtReg {
	@Id
	private String id;
	private int empId;
	private String empCode;
	private String empName;
	private String designation;
	private String otHr;
	private String month;
	private Date date;
	private float otMin;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getOtHr() {
		return otHr;
	}
	public void setOtHr(String otHr) {
		this.otHr = otHr;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getOtMin() {
		return otMin;
	}
	public void setOtMin(float otMin) {
		this.otMin = otMin;
	}
	@Override
	public String toString() {
		return "EmpOtReg [id=" + id + ", empId=" + empId + ", empCode=" + empCode + ", empName=" + empName
				+ ", designation=" + designation + ", otHr=" + otHr + ", month=" + month + ", date=" + date + ", otMin="
				+ otMin + "]";
	}
	
}
