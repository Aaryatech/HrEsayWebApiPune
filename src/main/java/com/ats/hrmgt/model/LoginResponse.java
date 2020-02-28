package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class LoginResponse {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private int empId ;
	
	@Column(name="emp_code")
	private String empCode ;
	  
	@Column(name="first_name ")
	private String firstName ;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="mother_name")
	private String motherName;
	  
	@Column(name="email_id")
	private String emailId; 
	
	@Column(name="user_id")
	private int userId; 
	
	@Column(name="loc_id")
	private String locationIds; 
	
	@Column(name="user_pwd")
	private String userPwd; 
	
	@Column(name="design_type")
	private int designType; 
	
	@Column(name="hod_dept_ids")
	private String hodDeptIds; 
	
	
	
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Transient
	private boolean isError;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean getIsError() {
		return isError;
	}

	public void setIsError(boolean isError) {
		this.isError = isError;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	public int getDesignType() {
		return designType;
	}

	public void setDesignType(int designType) {
		this.designType = designType;
	}

	public String getHodDeptIds() {
		return hodDeptIds;
	}

	public void setHodDeptIds(String hodDeptIds) {
		this.hodDeptIds = hodDeptIds;
	}

	@Override
	public String toString() {
		return "LoginResponse [empId=" + empId + ", empCode=" + empCode + ", firstName=" + firstName + ", middleName="
				+ middleName + ", surname=" + surname + ", motherName=" + motherName + ", emailId=" + emailId
				+ ", userId=" + userId + ", locationIds=" + locationIds + ", userPwd=" + userPwd + ", designType="
				+ designType + ", hodDeptIds=" + hodDeptIds + ", isError=" + isError + "]";
	}

	 

}
