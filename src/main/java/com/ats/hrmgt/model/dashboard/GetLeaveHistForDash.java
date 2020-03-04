package com.ats.hrmgt.model.dashboard;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetLeaveHistForDash {
	
	@Id
	
	private int leaveId;
	
	
	
	private Date leaveFromdt;
	
	private Date leaveTodt ;
	
	private float leaveNumDays;
	
	
	private String leaveEmpReason;
	
	private int exInt1;
	
	
	private String lvTitleShort;


	public int getLeaveId() {
		return leaveId;
	}


	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")

	public Date getLeaveFromdt() {
		return leaveFromdt;
	}


	public void setLeaveFromdt(Date leaveFromdt) {
		this.leaveFromdt = leaveFromdt;
	}

	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")

	public Date getLeaveTodt() {
		return leaveTodt;
	}


	public void setLeaveTodt(Date leaveTodt) {
		this.leaveTodt = leaveTodt;
	}


	public float getLeaveNumDays() {
		return leaveNumDays;
	}


	public void setLeaveNumDays(float leaveNumDays) {
		this.leaveNumDays = leaveNumDays;
	}


	public String getLeaveEmpReason() {
		return leaveEmpReason;
	}


	public void setLeaveEmpReason(String leaveEmpReason) {
		this.leaveEmpReason = leaveEmpReason;
	}


	public int getExInt1() {
		return exInt1;
	}


	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}


	public String getLvTitleShort() {
		return lvTitleShort;
	}


	public void setLvTitleShort(String lvTitleShort) {
		this.lvTitleShort = lvTitleShort;
	}


	@Override
	public String toString() {
		return "GetLeaveHistForDash [leaveId=" + leaveId + ", leaveFromdt=" + leaveFromdt + ", leaveTodt=" + leaveTodt
				+ ", leaveNumDays=" + leaveNumDays + ", leaveEmpReason=" + leaveEmpReason + ", exInt1=" + exInt1
				+ ", lvTitleShort=" + lvTitleShort + "]";
	}
	
	
	

}
