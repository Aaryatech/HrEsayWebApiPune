package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TotalOT {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private String id;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="month")
	private String month;
	
	@Column(name="ot")
	private float ot;

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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public float getOt() {
		return ot;
	}

	public void setOt(float ot) {
		this.ot = ot;
	}

	@Override
	public String toString() {
		return "TotalOT [id=" + id + ", empId=" + empId + ", month=" + month + ", ot=" + ot + "]";
	} 
	
	

}
