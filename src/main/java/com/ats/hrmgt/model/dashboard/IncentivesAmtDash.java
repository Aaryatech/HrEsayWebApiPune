package com.ats.hrmgt.model.dashboard;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IncentivesAmtDash {

	@Id
 	private String uniKey;
	private double perfIncentive;

	private double prodIncentive;

	public String getUniKey() {
		return uniKey;
	}

	public void setUniKey(String uniKey) {
		this.uniKey = uniKey;
	}

	public double getPerfIncentive() {
		return perfIncentive;
	}

	public void setPerfIncentive(double perfIncentive) {
		this.perfIncentive = perfIncentive;
	}

	public double getProdIncentive() {
		return prodIncentive;
	}

	public void setProdIncentive(double prodIncentive) {
		this.prodIncentive = prodIncentive;
	}

	@Override
	public String toString() {
		return "IncentivesAmtDash [uniKey=" + uniKey + ", perfIncentive=" + perfIncentive + ", prodIncentive="
				+ prodIncentive + "]";
	}
	
	
	

}
