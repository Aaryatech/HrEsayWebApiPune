package com.ats.hrmgt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CountData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private int present;
	private int absent;
	private int leavecount;
	private int latemark;
	private int holiday;
	private int weeklyoff;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPresent() {
		return present;
	}
	public void setPresent(int present) {
		this.present = present;
	}
	public int getAbsent() {
		return absent;
	}
	public void setAbsent(int absent) {
		this.absent = absent;
	}
	public int getLeavecount() {
		return leavecount;
	}
	public void setLeavecount(int leavecount) {
		this.leavecount = leavecount;
	}
	public int getLatemark() {
		return latemark;
	}
	public void setLatemark(int latemark) {
		this.latemark = latemark;
	}
	public int getHoliday() {
		return holiday;
	}
	public void setHoliday(int holiday) {
		this.holiday = holiday;
	}
	public int getWeeklyoff() {
		return weeklyoff;
	}
	public void setWeeklyoff(int weeklyoff) {
		this.weeklyoff = weeklyoff;
	}
	@Override
	public String toString() {
		return "CountData [id=" + id + ", present=" + present + ", absent=" + absent + ", leavecount=" + leavecount
				+ ", latemark=" + latemark + ", holiday=" + holiday + ", weeklyoff=" + weeklyoff + "]";
	}
	
	
	
}
