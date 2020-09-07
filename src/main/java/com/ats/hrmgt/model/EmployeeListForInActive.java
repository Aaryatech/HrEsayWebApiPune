package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeeListForInActive {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private int empId ;
	
	@Column(name="emp_code")
	private String empCode ;
	
	@Column(name="notice_pay_amount")
	private int noticePayAmount;

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

	public int getNoticePayAmount() {
		return noticePayAmount;
	}

	public void setNoticePayAmount(int noticePayAmount) {
		this.noticePayAmount = noticePayAmount;
	}

	@Override
	public String toString() {
		return "EmployeeListForInActive [empId=" + empId + ", empCode=" + empCode + ", noticePayAmount="
				+ noticePayAmount + "]";
	} 
	
	

}
