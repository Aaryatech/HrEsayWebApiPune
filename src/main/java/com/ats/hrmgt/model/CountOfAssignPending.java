package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CountOfAssignPending {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private String id;
	
	@Column(name="emp_type_count")
	private int empTypeCount;
	
	@Column(name="shift_count")
	private int shiftCount;
	
	@Column(name="location_count")
	private int locationCount ;
	
	@Column(name="holiday_count")
	private int holidayCount;
	
	@Column(name="weekend_count")
	private int weekendCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEmpTypeCount() {
		return empTypeCount;
	}

	public void setEmpTypeCount(int empTypeCount) {
		this.empTypeCount = empTypeCount;
	}

	public int getShiftCount() {
		return shiftCount;
	}

	public void setShiftCount(int shiftCount) {
		this.shiftCount = shiftCount;
	}

	public int getLocationCount() {
		return locationCount;
	}

	public void setLocationCount(int locationCount) {
		this.locationCount = locationCount;
	}

	public int getHolidayCount() {
		return holidayCount;
	}

	public void setHolidayCount(int holidayCount) {
		this.holidayCount = holidayCount;
	}

	public int getWeekendCount() {
		return weekendCount;
	}

	public void setWeekendCount(int weekendCount) {
		this.weekendCount = weekendCount;
	}

	@Override
	public String toString() {
		return "CountOfAssignPending [id=" + id + ", empTypeCount=" + empTypeCount + ", shiftCount=" + shiftCount
				+ ", locationCount=" + locationCount + ", holidayCount=" + holidayCount + ", weekendCount="
				+ weekendCount + "]";
	} 
	
	
	

}
