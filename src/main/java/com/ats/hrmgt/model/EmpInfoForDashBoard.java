package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmpInfoForDashBoard {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private int empId ;
	
	@Column(name="emp_code")
	private String empCode ;
	
	@Column(name="emp_name")
	private String empName;
	
	@Column(name="designation_name")
	private String designationName;
	
	@Column(name="department_name")
	private String departmentName;
	
	@Column(name="contact_no")
	private String contactNo;

	@Column(name="profile_pic")
	private String profilePic;
	
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	@Override
	public String toString() {
		return "EmpInfoForDashBoard [empId=" + empId + ", empCode=" + empCode + ", empName=" + empName
				+ ", designationName=" + designationName + ", departmentName=" + departmentName + ", contactNo="
				+ contactNo + ", profilePic=" + profilePic + "]";
	}
	
	

}
