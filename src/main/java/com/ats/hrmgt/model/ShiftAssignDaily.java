package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_shift_timming")
public class ShiftAssignDaily {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id ;
	
	@Column(name="emp_id")
	private int empId ;
	  
	@Column(name="shift_id")
	private int shiftId ;
	
	@Column(name="shift_date")
	private String shiftDate;
	
	@Column(name="month")
	private int month;
	
	@Column(name="year")
	private int year;
	  
	@Column(name="extra1")
	private int extra1; 
	
	@Column(name="extra2")
	private int extra2; 
	
	@Column(name="var1")
	private String var1; 
	 
	@Column(name="var2")
	private String var2 ;
	
	@Column(name="emp_code")
	private String empCode;

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

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(String shiftDate) {
		this.shiftDate = shiftDate;
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

	public int getExtra1() {
		return extra1;
	}

	public void setExtra1(int extra1) {
		this.extra1 = extra1;
	}

	public int getExtra2() {
		return extra2;
	}

	public void setExtra2(int extra2) {
		this.extra2 = extra2;
	}

	public String getVar1() {
		return var1;
	}

	public void setVar1(String var1) {
		this.var1 = var1;
	}

	public String getVar2() {
		return var2;
	}

	public void setVar2(String var2) {
		this.var2 = var2;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	@Override
	public String toString() {
		return "ShiftAssignDaily [id=" + id + ", empId=" + empId + ", shiftId=" + shiftId + ", shiftDate=" + shiftDate
				+ ", month=" + month + ", year=" + year + ", extra1=" + extra1 + ", extra2=" + extra2 + ", var1=" + var1
				+ ", var2=" + var2 + ", empCode=" + empCode + "]";
	} 
	
	

}
