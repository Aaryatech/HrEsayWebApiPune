package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class GetAccessibleLocation {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private int empId ;
	
	@Column(name="accessible_loc")
	private String accessibleLoc ;
	  
	@Column(name="present_loc ")
	private int presentLoc ;
	
	@Transient
	private boolean isError;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getAccessibleLoc() {
		return accessibleLoc;
	}

	public void setAccessibleLoc(String accessibleLoc) {
		this.accessibleLoc = accessibleLoc;
	}

	public int getPresentLoc() {
		return presentLoc;
	}

	public void setPresentLoc(int presentLoc) {
		this.presentLoc = presentLoc;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	@Override
	public String toString() {
		return "GetAccessibleLocation [empId=" + empId + ", accessibleLoc=" + accessibleLoc + ", presentLoc="
				+ presentLoc + ", isError=" + isError + "]";
	}
	
	
	

}
