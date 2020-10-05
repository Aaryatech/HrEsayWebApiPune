package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetShiftDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "shiftname")
	private String shiftname;

	@Column(name = "fromtime")
	private Date fromtime;

	@Column(name = "totime")
	private Date totime;

	@Column(name = "changeable")
	private int changeable;

	@Column(name = "changewith")
	private int changewith;

	@Column(name = "company_id")
	private int companyId;

	@Column(name = "max_late_time_allowed")
	private int maxLateTimeAllowed;

	@Column(name = "shift_hr")
	private String shiftHr;

	@Column(name = "shift_halfday_hr")
	private String shiftHalfdayHr;

	@Column(name = "early_going_min")
	private int earlyGoingMin;

	@Column(name = "ot_calculated_time")
	private String otCalculatedTime;

	@Column(name = "ot_calculated_after_hr")
	private int otCalculatedAfterHr;

	@Column(name = "shift_ot_hour")
	private String shiftOtHour;

	@Column(name = "department_id")
	private int departmentId;

	@Column(name = "self_group_id")
	private int selfGroupId;

	@Column(name = "status")
	private int status;

	@Column(name = "location_id")
	private int locationId;

	@Column(name = "short_name")
	private String shortName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShiftname() {
		return shiftname;
	}

	public void setShiftname(String shiftname) {
		this.shiftname = shiftname;
	}

	@JsonFormat(locale = "English", timezone = "Asia/Kolkata", pattern = "HH:mm")
	public Date getFromtime() {
		return fromtime;
	}

	public void setFromtime(Date fromtime) {
		this.fromtime = fromtime;
	}

	@JsonFormat(locale = "English", timezone = "Asia/Kolkata", pattern = "HH:mm")
	public Date getTotime() {
		return totime;
	}

	public void setTotime(Date totime) {
		this.totime = totime;
	}

	public int getChangeable() {
		return changeable;
	}

	public void setChangeable(int changeable) {
		this.changeable = changeable;
	}

	public int getChangewith() {
		return changewith;
	}

	public void setChangewith(int changewith) {
		this.changewith = changewith;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getMaxLateTimeAllowed() {
		return maxLateTimeAllowed;
	}

	public void setMaxLateTimeAllowed(int maxLateTimeAllowed) {
		this.maxLateTimeAllowed = maxLateTimeAllowed;
	}

	public String getShiftHr() {
		return shiftHr;
	}

	public void setShiftHr(String shiftHr) {
		this.shiftHr = shiftHr;
	}

	public String getShiftHalfdayHr() {
		return shiftHalfdayHr;
	}

	public void setShiftHalfdayHr(String shiftHalfdayHr) {
		this.shiftHalfdayHr = shiftHalfdayHr;
	}

	public int getEarlyGoingMin() {
		return earlyGoingMin;
	}

	public void setEarlyGoingMin(int earlyGoingMin) {
		this.earlyGoingMin = earlyGoingMin;
	}

	public String getOtCalculatedTime() {
		return otCalculatedTime;
	}

	public void setOtCalculatedTime(String otCalculatedTime) {
		this.otCalculatedTime = otCalculatedTime;
	}

	public int getOtCalculatedAfterHr() {
		return otCalculatedAfterHr;
	}

	public void setOtCalculatedAfterHr(int otCalculatedAfterHr) {
		this.otCalculatedAfterHr = otCalculatedAfterHr;
	}

	public String getShiftOtHour() {
		return shiftOtHour;
	}

	public void setShiftOtHour(String shiftOtHour) {
		this.shiftOtHour = shiftOtHour;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getSelfGroupId() {
		return selfGroupId;
	}

	public void setSelfGroupId(int selfGroupId) {
		this.selfGroupId = selfGroupId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return "GetShiftDetail [id=" + id + ", shiftname=" + shiftname + ", fromtime=" + fromtime + ", totime=" + totime
				+ ", changeable=" + changeable + ", changewith=" + changewith + ", companyId=" + companyId
				+ ", maxLateTimeAllowed=" + maxLateTimeAllowed + ", shiftHr=" + shiftHr + ", shiftHalfdayHr="
				+ shiftHalfdayHr + ", earlyGoingMin=" + earlyGoingMin + ", otCalculatedTime=" + otCalculatedTime
				+ ", otCalculatedAfterHr=" + otCalculatedAfterHr + ", shiftOtHour=" + shiftOtHour + ", departmentId="
				+ departmentId + ", selfGroupId=" + selfGroupId + ", status=" + status + ", locationId=" + locationId
				+ ", shortName=" + shortName + "]";
	}

}
