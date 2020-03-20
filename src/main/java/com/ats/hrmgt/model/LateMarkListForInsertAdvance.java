package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LateMarkListForInsertAdvance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="gr_sal")
	private double grSal; 
	
	@Column(name="totlate_days")
	private float totlateDays;
	
	@Column(name="total_days_inmonth")
	private int totalDaysInmonth;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public double getGrSal() {
		return grSal;
	}

	public void setGrSal(double grSal) {
		this.grSal = grSal;
	}

	public float getTotlateDays() {
		return totlateDays;
	}

	public void setTotlateDays(float totlateDays) {
		this.totlateDays = totlateDays;
	}

	public int getTotalDaysInmonth() {
		return totalDaysInmonth;
	}

	public void setTotalDaysInmonth(int totalDaysInmonth) {
		this.totalDaysInmonth = totalDaysInmonth;
	}

	@Override
	public String toString() {
		return "LateMarkListForInsertAdvance [empId=" + empId + ", grSal=" + grSal + ", totlateDays=" + totlateDays
				+ ", totalDaysInmonth=" + totalDaysInmonth + "]";
	}
	
	

}
