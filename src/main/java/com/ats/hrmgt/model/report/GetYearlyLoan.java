package com.ats.hrmgt.model.report;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetYearlyLoan {
	
	@Id
	private String uniqueId;
	
	private int empId;

	private String empName;

	private String empCode;
	private int month;

	private int year;

	private int  loanAmt;
	
	private int currentTotpaid;

	private int currentOutstanding;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(int loanAmt) {
		this.loanAmt = loanAmt;
	}

	public int getCurrentTotpaid() {
		return currentTotpaid;
	}

	public void setCurrentTotpaid(int currentTotpaid) {
		this.currentTotpaid = currentTotpaid;
	}

	public int getCurrentOutstanding() {
		return currentOutstanding;
	}

	public void setCurrentOutstanding(int currentOutstanding) {
		this.currentOutstanding = currentOutstanding;
	}

	@Override
	public String toString() {
		return "GetYearlyLoan [uniqueId=" + uniqueId + ", empId=" + empId + ", empName=" + empName + ", empCode="
				+ empCode + ", month=" + month + ", year=" + year + ", loanAmt=" + loanAmt + ", currentTotpaid="
				+ currentTotpaid + ", currentOutstanding=" + currentOutstanding + "]";
	}
	
	
	
	


}
