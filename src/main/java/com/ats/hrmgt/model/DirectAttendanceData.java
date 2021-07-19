package com.ats.hrmgt.model;

import java.util.List;

public class DirectAttendanceData {

	private List<DirectUploadAttendace> list;
	private List<String> empCodes;
	private int month;
	private int year;

	public List<DirectUploadAttendace> getList() {
		return list;
	}

	public void setList(List<DirectUploadAttendace> list) {
		this.list = list;
	}

	public List<String> getEmpCodes() {
		return empCodes;
	}

	public void setEmpCodes(List<String> empCodes) {
		this.empCodes = empCodes;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "DirectAttendanceData [list=" + list + ", empCodes=" + empCodes + ", month=" + month + ", year=" + year
				+ "]";
	}

}
