package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_optional_holiday")
public class OptionalHoliday {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id ;
	
	@Column(name="emp_id")
	private int empId ;
	  
	@Column(name="holidate")
	private String holidate ;
	
	@Column(name="holiday_id")
	private int holidayId;
	
	@Column(name="status")
	private int status;
	
	@Column(name="remark")
	private String remark;
	  
	@Column(name="extra_int1")
	private int extraInt1; 
	
	@Column(name="extra_int2")
	private int extraInt2; 
	
	@Column(name="del_status")
	private int delStatus; 
	
	@Column(name="varchar1")
	private String varchar1;
	
	@Column(name="varchar2")
	private String varchar2;
	
	@Column(name="year_id")
	private int yearId; 

	@Column(name="request_by")
	private int requestBy; 
	
	@Column(name="request_datetime")
	private String requestDatetime;
	
	@Column(name="approved_by")
	private int approvedBy; 
	
	@Column(name="approved_daetime")
	private String approvedDaetime;

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

	public String getHolidate() {
		return holidate;
	}

	public void setHolidate(String holidate) {
		this.holidate = holidate;
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

	public int getExtraInt1() {
		return extraInt1;
	}

	public void setExtraInt1(int extraInt1) {
		this.extraInt1 = extraInt1;
	}

	public int getExtraInt2() {
		return extraInt2;
	}

	public void setExtraInt2(int extraInt2) {
		this.extraInt2 = extraInt2;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getVarchar1() {
		return varchar1;
	}

	public void setVarchar1(String varchar1) {
		this.varchar1 = varchar1;
	}

	public String getVarchar2() {
		return varchar2;
	}

	public void setVarchar2(String varchar2) {
		this.varchar2 = varchar2;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public int getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(int requestBy) {
		this.requestBy = requestBy;
	}

	public String getRequestDatetime() {
		return requestDatetime;
	}

	public void setRequestDatetime(String requestDatetime) {
		this.requestDatetime = requestDatetime;
	}

	public int getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getApprovedDaetime() {
		return approvedDaetime;
	}

	public void setApprovedDaetime(String approvedDaetime) {
		this.approvedDaetime = approvedDaetime;
	}

	@Override
	public String toString() {
		return "OptionalHoliday [id=" + id + ", empId=" + empId + ", holidate=" + holidate + ", holidayId=" + holidayId
				+ ", status=" + status + ", remark=" + remark + ", extraInt1=" + extraInt1 + ", extraInt2=" + extraInt2
				+ ", delStatus=" + delStatus + ", varchar1=" + varchar1 + ", varchar2=" + varchar2 + ", yearId="
				+ yearId + ", requestBy=" + requestBy + ", requestDatetime=" + requestDatetime + ", approvedBy="
				+ approvedBy + ", approvedDaetime=" + approvedDaetime + "]";
	}
	
	
}
