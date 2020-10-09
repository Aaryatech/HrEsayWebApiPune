package com.ats.hrmgt.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeductionAndLoanAMT {
	
	@Id
	private String id;
	private int empId; 
	private float amt;
	private String monthYear;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public float getAmt() {
		return amt;
	}
	public void setAmt(float amt) {
		this.amt = amt;
	}
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	@Override
	public String toString() {
		return "DeductionAndLoanAMT [id=" + id + ", empId=" + empId + ", amt=" + amt + ", monthYear=" + monthYear + "]";
	}
	
	

}
