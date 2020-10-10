package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class DailyAttendaceReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private String id;
	 
	@Column(name="emp_code")
	private String empCode;
	
	@Column(name="emp_name")
	private String empName ;
	
	@Column(name="att_date")
	private Date attDate;
	
	@Column(name="att_status")
	private String attStatus;
	
	@Column(name="working_hrs")
	private String workingHrs;
	
	@Column(name="in_time")
	private String inTime;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="current_shiftid")
	private int currentShiftid;
	
	@Column(name="late_min")
	private int lateMin;
	
	@Column(name="current_shiftname")
	private String currentShiftname;
	
	@Column(name="out_time")
	private String outTime;
	
	@Column(name="atts_sd_show")
	private String attsSdShow;
	
	@Column(name="ot_hr")
	private String otHr;
	
	@Column(name="name")
	private String name;

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

	public String getWorkingHrs() {
		return workingHrs;
	}

	public void setWorkingHrs(String workingHrs) {
		this.workingHrs = workingHrs;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getCurrentShiftid() {
		return currentShiftid;
	}

	public void setCurrentShiftid(int currentShiftid) {
		this.currentShiftid = currentShiftid;
	}

	public int getLateMin() {
		return lateMin;
	}

	public void setLateMin(int lateMin) {
		this.lateMin = lateMin;
	}

	public String getCurrentShiftname() {
		return currentShiftname;
	}

	public void setCurrentShiftname(String currentShiftname) {
		this.currentShiftname = currentShiftname;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getAttsSdShow() {
		return attsSdShow;
	}

	public void setAttsSdShow(String attsSdShow) {
		this.attsSdShow = attsSdShow;
	}

	public String getOtHr() {
		return otHr;
	}

	public void setOtHr(String otHr) {
		this.otHr = otHr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DailyAttendaceReport [id=" + id + ", empCode=" + empCode + ", empName=" + empName + ", attDate="
				+ attDate + ", attStatus=" + attStatus + ", workingHrs=" + workingHrs + ", inTime=" + inTime
				+ ", empId=" + empId + ", currentShiftid=" + currentShiftid + ", lateMin=" + lateMin
				+ ", currentShiftname=" + currentShiftname + ", outTime=" + outTime + ", attsSdShow=" + attsSdShow
				+ ", otHr=" + otHr + ", name=" + name + "]";
	}
	
	
	

}
