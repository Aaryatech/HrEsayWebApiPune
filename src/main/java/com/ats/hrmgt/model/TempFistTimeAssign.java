package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_temp_assign_first_day_shift")
public class TempFistTimeAssign {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	  
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="extra1")
	private String extra1;
	
	@Column(name="extra2")
	private int extra2;
	
	@Column(name="shift_id")
	private int shiftId;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExtra1() {
		return extra1;
	}

	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	public int getExtra2() {
		return extra2;
	}

	public void setExtra2(int extra2) {
		this.extra2 = extra2;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	@Override
	public String toString() {
		return "TempFistTimeAssign [id=" + id + ", empId=" + empId + ", date=" + date + ", extra1=" + extra1
				+ ", extra2=" + extra2 + ", shiftId=" + shiftId + "]";
	}
	
	

}
