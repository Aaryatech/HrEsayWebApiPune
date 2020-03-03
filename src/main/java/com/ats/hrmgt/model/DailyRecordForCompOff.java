package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyRecordForCompOff {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "att_date")
	private String attDate;

	@Column(name = "emp_id")
	private int empId;

	@Column(name="lv_sumup_id")
	private int lvSumupId ;
	
	@Column(name="att_status")
	private String attStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttDate() {
		return attDate;
	}

	public void setAttDate(String attDate) {
		this.attDate = attDate;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getLvSumupId() {
		return lvSumupId;
	}

	public void setLvSumupId(int lvSumupId) {
		this.lvSumupId = lvSumupId;
	}

	public String getAttStatus() {
		return attStatus;
	}

	public void setAttStatus(String attStatus) {
		this.attStatus = attStatus;
	}

	@Override
	public String toString() {
		return "DailyRecordForCompOff [id=" + id + ", attDate=" + attDate + ", empId=" + empId + ", lvSumupId="
				+ lvSumupId + ", attStatus=" + attStatus + "]";
	}
	
	
  
}
