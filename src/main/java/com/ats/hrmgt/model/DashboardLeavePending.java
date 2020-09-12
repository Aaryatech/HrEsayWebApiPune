package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class DashboardLeavePending {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "leave_id")
	private int leaveId;

	@Column(name = "emp_id")
	private int empId;

	@Column(name = "lv_type_id")
	private int lvTypeId;

	@Column(name = "leave_fromdt")
	private Date leaveFromdt;
	
	@Column(name = "leave_todt")
	private Date leaveTodt;
	
	@Column(name = "leave_num_days")
	private int leaveNumDays; 
	
	@Column(name = "emp_name")
	private String empName; 
	
	@Column(name = "initial_auth_name")
	private String initialAuthName; 
	
	@Column(name = "final_auth_name")
	private String finalAuthName; 
	
	@Column(name = "leave_title")
	private String leaveTitle;
 
	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getLvTypeId() {
		return lvTypeId;
	}

	public void setLvTypeId(int lvTypeId) {
		this.lvTypeId = lvTypeId;
	}
	@JsonFormat(locale = "English", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getLeaveFromdt() {
		return leaveFromdt;
	}

	public void setLeaveFromdt(Date leaveFromdt) {
		this.leaveFromdt = leaveFromdt;
	}
	@JsonFormat(locale = "English", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getLeaveTodt() {
		return leaveTodt;
	}

	public void setLeaveTodt(Date leaveTodt) {
		this.leaveTodt = leaveTodt;
	}

	public int getLeaveNumDays() {
		return leaveNumDays;
	}

	public void setLeaveNumDays(int leaveNumDays) {
		this.leaveNumDays = leaveNumDays;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getInitialAuthName() {
		return initialAuthName;
	}

	public void setInitialAuthName(String initialAuthName) {
		this.initialAuthName = initialAuthName;
	}

	public String getFinalAuthName() {
		return finalAuthName;
	}

	public void setFinalAuthName(String finalAuthName) {
		this.finalAuthName = finalAuthName;
	}

	public String getLeaveTitle() {
		return leaveTitle;
	}

	public void setLeaveTitle(String leaveTitle) {
		this.leaveTitle = leaveTitle;
	}

	@Override
	public String toString() {
		return "DashboardLeavePending [leaveId=" + leaveId + ", empId=" + empId + ", lvTypeId=" + lvTypeId
				+ ", leaveFromdt=" + leaveFromdt + ", leaveTodt=" + leaveTodt + ", leaveNumDays=" + leaveNumDays
				+ ", empName=" + empName + ", initialAuthName=" + initialAuthName + ", finalAuthName=" + finalAuthName
				+ ", leaveTitle=" + leaveTitle + "]";
	} 
	
	

}
