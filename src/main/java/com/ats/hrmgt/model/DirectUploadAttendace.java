package com.ats.hrmgt.model;

public class DirectUploadAttendace {

	private String empCode;
	private String empName;
	private float workingDays;
	private float presentDays;
	private float weeklyOff;
	private float paidHoliday;
	private float paidLeave;
	private float unpaidLeave;
	private int absentDays;
	private float payableDays;
	private float totalLateMins;
	private int totalLateDays;
	private float totalLateDaysDeduct;
	private float totalWorkingHrs;
	private float holidaypresent;
	private float weeklyOffPresent;
	private float nightshiftdays;
	private float weeklyOffHolidayOffPresent;
	private float otHours;
	
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public float getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(float workingDays) {
		this.workingDays = workingDays;
	}
	public float getPresentDays() {
		return presentDays;
	}
	public void setPresentDays(float presentDays) {
		this.presentDays = presentDays;
	}
	public float getWeeklyOff() {
		return weeklyOff;
	}
	public void setWeeklyOff(float weeklyOff) {
		this.weeklyOff = weeklyOff;
	}
	public float getPaidHoliday() {
		return paidHoliday;
	}
	public void setPaidHoliday(float paidHoliday) {
		this.paidHoliday = paidHoliday;
	}
	public float getPaidLeave() {
		return paidLeave;
	}
	public void setPaidLeave(float paidLeave) {
		this.paidLeave = paidLeave;
	}
	public float getUnpaidLeave() {
		return unpaidLeave;
	}
	public void setUnpaidLeave(float unpaidLeave) {
		this.unpaidLeave = unpaidLeave;
	}
	public int getAbsentDays() {
		return absentDays;
	}
	public void setAbsentDays(int absentDays) {
		this.absentDays = absentDays;
	}
	public float getPayableDays() {
		return payableDays;
	}
	public void setPayableDays(float payableDays) {
		this.payableDays = payableDays;
	}
	public float getTotalLateMins() {
		return totalLateMins;
	}
	public void setTotalLateMins(float totalLateMins) {
		this.totalLateMins = totalLateMins;
	}
	public int getTotalLateDays() {
		return totalLateDays;
	}
	public void setTotalLateDays(int totalLateDays) {
		this.totalLateDays = totalLateDays;
	}
	public float getTotalLateDaysDeduct() {
		return totalLateDaysDeduct;
	}
	public void setTotalLateDaysDeduct(float totalLateDaysDeduct) {
		this.totalLateDaysDeduct = totalLateDaysDeduct;
	}
	public float getTotalWorkingHrs() {
		return totalWorkingHrs;
	}
	public void setTotalWorkingHrs(float totalWorkingHrs) {
		this.totalWorkingHrs = totalWorkingHrs;
	}
	public float getHolidaypresent() {
		return holidaypresent;
	}
	public void setHolidaypresent(float holidaypresent) {
		this.holidaypresent = holidaypresent;
	}
	public float getWeeklyOffPresent() {
		return weeklyOffPresent;
	}
	public void setWeeklyOffPresent(float weeklyOffPresent) {
		this.weeklyOffPresent = weeklyOffPresent;
	}
	public float getNightshiftdays() {
		return nightshiftdays;
	}
	public void setNightshiftdays(float nightshiftdays) {
		this.nightshiftdays = nightshiftdays;
	}
	public float getWeeklyOffHolidayOffPresent() {
		return weeklyOffHolidayOffPresent;
	}
	public void setWeeklyOffHolidayOffPresent(float weeklyOffHolidayOffPresent) {
		this.weeklyOffHolidayOffPresent = weeklyOffHolidayOffPresent;
	}
	public float getOtHours() {
		return otHours;
	}
	public void setOtHours(float otHours) {
		this.otHours = otHours;
	}
	@Override
	public String toString() {
		return "DirectUploadAttendace [empCode=" + empCode + ", empName=" + empName + ", workingDays=" + workingDays
				+ ", presentDays=" + presentDays + ", weeklyOff=" + weeklyOff + ", paidHoliday=" + paidHoliday
				+ ", paidLeave=" + paidLeave + ", unpaidLeave=" + unpaidLeave + ", absentDays=" + absentDays
				+ ", payableDays=" + payableDays + ", totalLateMins=" + totalLateMins + ", totalLateDays="
				+ totalLateDays + ", totalLateDaysDeduct=" + totalLateDaysDeduct + ", totalWorkingHrs="
				+ totalWorkingHrs + ", holidaypresent=" + holidaypresent + ", weeklyOffPresent=" + weeklyOffPresent
				+ ", nightshiftdays=" + nightshiftdays + ", weeklyOffHolidayOffPresent=" + weeklyOffHolidayOffPresent
				+ ", otHours=" + otHours + "]";
	}
	
	

}
