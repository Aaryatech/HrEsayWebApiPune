package com.ats.hrmgt.model.dashboard;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeptWiseWeekoffDash {
	
	
	@Id 
	private int departId;
	
	
	private String nameSd;
	
	
	private int  empCount;


	public int getDepartId() {
		return departId;
	}


	public void setDepartId(int departId) {
		this.departId = departId;
	}


	public String getNameSd() {
		return nameSd;
	}


	public void setNameSd(String nameSd) {
		this.nameSd = nameSd;
	}


	public int getEmpCount() {
		return empCount;
	}


	public void setEmpCount(int empCount) {
		this.empCount = empCount;
	}


	@Override
	public String toString() {
		return "DeptWiseWeekoffDash [departId=" + departId + ", nameSd=" + nameSd + ", empCount=" + empCount + "]";
	}
	
	
	
	
	

}
