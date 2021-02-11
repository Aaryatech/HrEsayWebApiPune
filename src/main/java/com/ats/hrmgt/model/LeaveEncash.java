package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_encash")
public class LeaveEncash {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "emp_id")
	private int empId;

	@Column(name = "lv_type_id")
	private int lvTypeId;

	@Column(name = "month")
	private int month;

	@Column(name = "year")
	private int year;

	@Column(name = "is_freeze")
	private int isFreeze;

	@Column(name = "leave_count")
	private float leaveCount;

	@Column(name = "total_amt")
	private float totalAmt;

	@Column(name = "per_day_amt")
	private float perDayAmt;

	@Column(name = "ex_int1")
	private int exInt1;

	@Column(name = "ex_int2")
	private int exInt2;

	@Column(name = "ex_varchar1")
	private String exVarchar1;

	@Column(name = "ex_varchar2")
	private String exVarchar2;

	@Column(name = "year_id")
	private int yearId;

	@Column(name = "del_status")
	private int delStatus;

	@Column(name = "remark")
	private String remark;

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

	public int getLvTypeId() {
		return lvTypeId;
	}

	public void setLvTypeId(int lvTypeId) {
		this.lvTypeId = lvTypeId;
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

	public int getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(int isFreeze) {
		this.isFreeze = isFreeze;
	}

	public float getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(float leaveCount) {
		this.leaveCount = leaveCount;
	}

	public float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}

	public float getPerDayAmt() {
		return perDayAmt;
	}

	public void setPerDayAmt(float perDayAmt) {
		this.perDayAmt = perDayAmt;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVarchar1() {
		return exVarchar1;
	}

	public void setExVarchar1(String exVarchar1) {
		this.exVarchar1 = exVarchar1;
	}

	public String getExVarchar2() {
		return exVarchar2;
	}

	public void setExVarchar2(String exVarchar2) {
		this.exVarchar2 = exVarchar2;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "LeaveEncash [id=" + id + ", empId=" + empId + ", lvTypeId=" + lvTypeId + ", month=" + month + ", year="
				+ year + ", isFreeze=" + isFreeze + ", leaveCount=" + leaveCount + ", totalAmt=" + totalAmt
				+ ", perDayAmt=" + perDayAmt + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVarchar1="
				+ exVarchar1 + ", exVarchar2=" + exVarchar2 + ", yearId=" + yearId + ", delStatus=" + delStatus
				+ ", remark=" + remark + "]";
	}

}
