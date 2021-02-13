package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LiveThumbData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;

	@Column(name = "emp_code")
	private String empCode;

	@Column(name = "count_thumb")
	private int countThumb;

	@Column(name = "in_id")
	private int inId;

	@Column(name = "in_punch_time")
	private String inPunchTime;

	@Column(name = "in_date")
	private String inDate;

	@Column(name = "in_time")
	private String inTime;

	@Column(name = "out_id")
	private int outId;

	@Column(name = "out_punch_time")
	private String outPunch_time;

	@Column(name = "out_date")
	private String outDate;

	@Column(name = "out_time")
	private String outTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public int getInId() {
		return inId;
	}

	public void setInId(int inId) {
		this.inId = inId;
	}

	public String getInPunchTime() {
		return inPunchTime;
	}

	public void setInPunchTime(String inPunchTime) {
		this.inPunchTime = inPunchTime;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public int getOutId() {
		return outId;
	}

	public void setOutId(int outId) {
		this.outId = outId;
	}

	public String getOutPunch_time() {
		return outPunch_time;
	}

	public void setOutPunch_time(String outPunch_time) {
		this.outPunch_time = outPunch_time;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public int getCountThumb() {
		return countThumb;
	}

	public void setCountThumb(int countThumb) {
		this.countThumb = countThumb;
	}

	@Override
	public String toString() {
		return "LiveThumbData [id=" + id + ", empCode=" + empCode + ", countThumb=" + countThumb + ", inId=" + inId
				+ ", inPunchTime=" + inPunchTime + ", inDate=" + inDate + ", inTime=" + inTime + ", outId=" + outId
				+ ", outPunch_time=" + outPunch_time + ", outDate=" + outDate + ", outTime=" + outTime + "]";
	}

}
