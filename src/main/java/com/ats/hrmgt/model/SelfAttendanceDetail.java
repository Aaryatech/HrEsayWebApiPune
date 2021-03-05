package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class SelfAttendanceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String empCode;
	private String empName;
	private Date attDate;
	private String attStatus;
	private String lvSumupId;
	private String workingHrs;
	private String outTime;
	private String inTime; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@JsonFormat(locale = "English", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getAttDate() {
		return attDate;
	}

	public void setAttDate(Date attDate) {
		this.attDate = attDate;
	}

	public String getAttStatus() {
		return attStatus;
	}

	public void setAttStatus(String attStatus) {
		this.attStatus = attStatus;
	}

	public String getLvSumupId() {
		return lvSumupId;
	}

	public void setLvSumupId(String lvSumupId) {
		this.lvSumupId = lvSumupId;
	}

	public String getWorkingHrs() {
		return workingHrs;
	}

	public void setWorkingHrs(String workingHrs) {
		this.workingHrs = workingHrs;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	@Override
	public String toString() {
		return "SelfAttendanceDetail [id=" + id + ", empCode=" + empCode + ", empName=" + empName + ", attDate="
				+ attDate + ", attStatus=" + attStatus + ", lvSumupId=" + lvSumupId + ", workingHrs=" + workingHrs
				+ ", outTime=" + outTime + ", inTime=" + inTime + "]";
	}

}
