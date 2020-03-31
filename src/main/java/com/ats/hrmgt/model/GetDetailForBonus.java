package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GetDetailForBonus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	private int empId;
	private float presentdays;
	private float weeklyoff;
	private float holiday;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public float getPresentdays() {
		return presentdays;
	}
	public void setPresentdays(float presentdays) {
		this.presentdays = presentdays;
	}
	public float getWeeklyoff() {
		return weeklyoff;
	}
	public void setWeeklyoff(float weeklyoff) {
		this.weeklyoff = weeklyoff;
	}
	public float getHoliday() {
		return holiday;
	}
	public void setHoliday(float holiday) {
		this.holiday = holiday;
	}
	@Override
	public String toString() {
		return "GetDetailForBonus [id=" + id + ", empId=" + empId + ", presentdays=" + presentdays + ", weeklyoff="
				+ weeklyoff + ", holiday=" + holiday + "]";
	}
	
	
	
	

}
