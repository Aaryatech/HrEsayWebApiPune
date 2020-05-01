package com.ats.hrmgt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class EmpWithShiftDetail {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private int empId ;
	
	@Column(name="emp_code")
	private String empCode ;
	
	@Column(name="name")
	private String name;
	
	@Column(name="group_id")
	private int groupId;
	
	@Column(name="group_type")
	private int groupType;
	
	@Column(name="shift_id")
	private int shiftId;
	
	@Column(name="location_id")
	private int locationId;
	
	@Column(name="holiday_category")
	private int holidayCategory;
	
	@Column(name="weekend_category")
	private int weekendCategory;
	
	
	@Transient
	private List<DateWiseProjection> dateprojectlist;
	
	@Transient
	List<EmpShiftAllocationDetail> shiftallocationDetailList;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getGroupType() {
		return groupType;
	}

	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public List<DateWiseProjection> getDateprojectlist() {
		return dateprojectlist;
	}

	public void setDateprojectlist(List<DateWiseProjection> dateprojectlist) {
		this.dateprojectlist = dateprojectlist;
	}

	 

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getHolidayCategory() {
		return holidayCategory;
	}

	public void setHolidayCategory(int holidayCategory) {
		this.holidayCategory = holidayCategory;
	}

	public int getWeekendCategory() {
		return weekendCategory;
	}

	public void setWeekendCategory(int weekendCategory) {
		this.weekendCategory = weekendCategory;
	}

	public List<EmpShiftAllocationDetail> getShiftallocationDetailList() {
		return shiftallocationDetailList;
	}

	public void setShiftallocationDetailList(List<EmpShiftAllocationDetail> shiftallocationDetailList) {
		this.shiftallocationDetailList = shiftallocationDetailList;
	}

	@Override
	public String toString() {
		return "EmpWithShiftDetail [empId=" + empId + ", empCode=" + empCode + ", name=" + name + ", groupId=" + groupId
				+ ", groupType=" + groupType + ", shiftId=" + shiftId + ", locationId=" + locationId
				+ ", holidayCategory=" + holidayCategory + ", weekendCategory=" + weekendCategory + ", dateprojectlist="
				+ dateprojectlist + ", shiftallocationDetailList=" + shiftallocationDetailList + "]";
	} 
	
	

}
