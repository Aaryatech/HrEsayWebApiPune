package com.ats.hrmgt.model;

import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class EmpDetailForLetters {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_id")
	private int empId;

	@Column(name = "emp_code")
	private String empCode;

	@Column(name = "first_name")
	private String firsName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "surname")
	private String surname;

	@Column(name = "dept_name")
	private String deptName;

	@Column(name = "emp_desgn")
	private String empDesgn;

	@Column(name = "loc_name")
	private String locName;

	@Column(name = "org_name")
	private String orgName;

	@Column(name = "sub_comp_name")
	private String subCompName;

	@Column(name = "cmp_joining_date")
	private Date cmpJoiningDate;

	@Column(name = "cmp_leaving_date")
	private Date cmpLeavingDate;

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

	public String getFirsName() {
		return firsName;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEmpDesgn() {
		return empDesgn;
	}

	public void setEmpDesgn(String empDesgn) {
		this.empDesgn = empDesgn;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSubCompName() {
		return subCompName;
	}

	public void setSubCompName(String subCompName) {
		this.subCompName = subCompName;
	}

	@JsonFormat(locale = "ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MMM-yyyy")
	public Date getCmpJoiningDate() {
		return cmpJoiningDate;
	}

	public void setCmpJoiningDate(Date cmpJoiningDate) {
		this.cmpJoiningDate = cmpJoiningDate;
	}

	@JsonFormat(locale = "ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MMM-yyyy")
	public Date getCmpLeavingDate() {
		return cmpLeavingDate;
	}

	public void setCmpLeavingDate(Date cmpLeavingDate) {
		this.cmpLeavingDate = cmpLeavingDate;
	}

	@Override
	public String toString() {
		return "EmpDetailForLetters [empId=" + empId + ", empCode=" + empCode + ", firsName=" + firsName
				+ ", middleName=" + middleName + ", surname=" + surname + ", deptName=" + deptName + ", empDesgn="
				+ empDesgn + ", locName=" + locName + ", orgName=" + orgName + ", subCompName=" + subCompName
				+ ", cmpJoiningDate=" + cmpJoiningDate + ", cmpLeavingDate=" + cmpLeavingDate + "]";
	}

}
