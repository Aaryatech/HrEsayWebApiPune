package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ProductionIncentiveList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;

	@Column(name = "emp_id")
	private int empId;

	@Column(name = "att_date")
	private Date attDate;

	@Column(name = "hrs")
	private int hrs;
	
	@Column(name = "tot_othr")
	private int totOthr;
	
	@Column(name = "show_hrs")
	private String showHrs;
	
	@Column(name = "att_status")
	private String attStatus;
	
	@Transient
	private double amt;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@JsonFormat(locale = "ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getAttDate() {
		return attDate;
	}

	public void setAttDate(Date attDate) {
		this.attDate = attDate;
	}

	public int getHrs() {
		return hrs;
	}

	public void setHrs(int hrs) {
		this.hrs = hrs;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public int getTotOthr() {
		return totOthr;
	}

	public void setTotOthr(int totOthr) {
		this.totOthr = totOthr;
	}

	public String getShowHrs() {
		return showHrs;
	}

	public void setShowHrs(String showHrs) {
		this.showHrs = showHrs;
	}

	public String getAttStatus() {
		return attStatus;
	}

	public void setAttStatus(String attStatus) {
		this.attStatus = attStatus;
	}

	@Override
	public String toString() {
		return "ProductionIncentiveList [id=" + id + ", empId=" + empId + ", attDate=" + attDate + ", hrs=" + hrs
				+ ", totOthr=" + totOthr + ", showHrs=" + showHrs + ", attStatus=" + attStatus + ", amt=" + amt + "]";
	}

}
