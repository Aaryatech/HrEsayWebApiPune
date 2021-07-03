package com.ats.hrmgt.model;

public class ClaimUpdatAmount {
	
	private int claimId;
	private int claimHeadId;
	private float amt;
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public float getAmt() {
		return amt;
	}
	public void setAmt(float amt) {
		this.amt = amt;
	}
	public int getClaimHeadId() {
		return claimHeadId;
	}
	public void setClaimHeadId(int claimHeadId) {
		this.claimHeadId = claimHeadId;
	}
	@Override
	public String toString() {
		return "ClaimUpdatAmount [claimId=" + claimId + ", claimHeadId=" + claimHeadId + ", amt=" + amt + "]";
	}

}
