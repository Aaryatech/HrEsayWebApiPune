package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class EmpListForHolidayApprove {

	@Id
	private int id;
	private int empId;
	private int delStatus;
	private int holidayId;
	private int status;
	private Date holidate;
	private String remark;
	private String empCode;
	private String empName;
	private String holidayName;

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

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

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

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getHolidate() {
		return holidate;
	}

	public void setHolidate(Date holidate) {
		this.holidate = holidate;
	}

	@Override
	public String toString() {
		return "EmpListForHolidayApprove [id=" + id + ", empId=" + empId + ", delStatus=" + delStatus + ", holidayId="
				+ holidayId + ", status=" + status + ", holidate=" + holidate + ", remark=" + remark + ", empCode="
				+ empCode + ", empName=" + empName + ", holidayName=" + holidayName + "]";
	}

}
