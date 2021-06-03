package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//Sachin 05-09-2020

@Entity
public class DailyDaily {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="ot_hr")
	private String otHr;
	
	@Column(name="att_date")
	private String attDate;

	private String dayName;
	
	private String inTime;
	
	private String outTime;
	
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

	public String getOtHr() {
		return otHr;
	}

	public void setOtHr(String otHr) {
		this.otHr = otHr;
	}

	public String getAttDate() {
		return attDate;
	}

	public void setAttDate(String attDate) {
		this.attDate = attDate;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	@Override
	public String toString() {
		return "DailyDaily [id=" + id + ", empId=" + empId + ", otHr=" + otHr + ", attDate=" + attDate + ", dayName="
				+ dayName + ", inTime=" + inTime + ", outTime=" + outTime + "]";
	}
	
}
