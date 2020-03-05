package com.ats.hrmgt.model.dashboard;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
 public class PerformanceProdDash {
	
	
	@Id
	private String uniKey;
	
	
	private double prodDays;
	
	private double prodAmt;

	public String getUniKey() {
		return uniKey;
	}

	public void setUniKey(String uniKey) {
		this.uniKey = uniKey;
	}

	public double getProdDays() {
		return prodDays;
	}

	public void setProdDays(double prodDays) {
		this.prodDays = prodDays;
	}

	public double getProdAmt() {
		return prodAmt;
	}

	public void setProdAmt(double prodAmt) {
		this.prodAmt = prodAmt;
	}

	@Override
	public String toString() {
		return "PerformanceProdDash [uniKey=" + uniKey + ", prodDays=" + prodDays + ", prodAmt=" + prodAmt + "]";
	}

	
	
	

}
