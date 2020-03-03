package com.ats.hrmgt.model.dashboard;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetNewHiresDash {
	
	
	@Id
	private String uniKey;
	
	
	private int  maleEmp;
	
	
	private int  femaleEmp;
	
	
	private int  othEmp;


	public String getUniKey() {
		return uniKey;
	}


	public void setUniKey(String uniKey) {
		this.uniKey = uniKey;
	}


	public int getMaleEmp() {
		return maleEmp;
	}


	public void setMaleEmp(int maleEmp) {
		this.maleEmp = maleEmp;
	}


	public int getFemaleEmp() {
		return femaleEmp;
	}


	public void setFemaleEmp(int femaleEmp) {
		this.femaleEmp = femaleEmp;
	}


	public int getOthEmp() {
		return othEmp;
	}


	public void setOthEmp(int othEmp) {
		this.othEmp = othEmp;
	}


	@Override
	public String toString() {
		return "GetNewHiresDash [uniKey=" + uniKey + ", maleEmp=" + maleEmp + ", femaleEmp=" + femaleEmp + ", othEmp="
				+ othEmp + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
