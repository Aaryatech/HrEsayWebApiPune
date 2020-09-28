package com.ats.hrmgt.model.dashboard;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LeavePenDash {
	
	

	@Id
	private String uniKey;
	
	
	private int  newApp; // used for performance bonus
	
	private int finalPending;
	private int ohPending;
	
	public String getUniKey() {
		return uniKey;
	}

	public void setUniKey(String uniKey) {
		this.uniKey = uniKey;
	}

	public int getNewApp() {
		return newApp;
	}

	public void setNewApp(int newApp) {
		this.newApp = newApp;
	}

	public int getFinalPending() {
		return finalPending;
	}

	public void setFinalPending(int finalPending) {
		this.finalPending = finalPending;
	}

	public int getOhPending() {
		return ohPending;
	}

	public void setOhPending(int ohPending) {
		this.ohPending = ohPending;
	}

	@Override
	public String toString() {
		return "LeavePenDash [uniKey=" + uniKey + ", newApp=" + newApp + ", finalPending=" + finalPending
				+ ", ohPending=" + ohPending + "]";
	}
	
	
	
	
	
	

}
