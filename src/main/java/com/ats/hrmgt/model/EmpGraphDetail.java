package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmpGraphDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="month")
	private String month;
	
	@Column(name="late_min")
	private String lateMin;
	
	@Column(name="late_mark")
	private String lateMark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
 
	public String getLateMin() {
		return lateMin;
	}

	public void setLateMin(String lateMin) {
		this.lateMin = lateMin;
	}

	public String getLateMark() {
		return lateMark;
	}

	public void setLateMark(String lateMark) {
		this.lateMark = lateMark;
	}

	@Override
	public String toString() {
		return "EmpGraphDetail [id=" + id + ", empId=" + empId + ", month=" + month + ", lateMin=" + lateMin
				+ ", lateMark=" + lateMark + "]";
	}

	 
	
	
	 

}
