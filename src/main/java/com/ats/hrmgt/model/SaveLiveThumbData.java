package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HRRAWINOUTDATA")
public class SaveLiveThumbData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "EmpCode")
	private String EmpCode;

	@Column(name = "PunchDateTime")
	private String PunchDateTime;

	@Column(name = "LogDate")
	private String LogDate;

	@Column(name = "LogTime")
	private String LogTime;
	
	@Column(name = "Direction")
	private String Direction;
	
	@Column(name = "WorkCode")
	private String WorkCode;
	
	@Column(name = "DeviceName")
	private String DeviceName;
	
	@Column(name = "SerialNo")
	private String SerialNo;
	
	@Column(name = "VerMode")
	private String VerMode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmpCode() {
		return EmpCode;
	}
	public void setEmpCode(String empCode) {
		EmpCode = empCode;
	}
	public String getPunchDateTime() {
		return PunchDateTime;
	}
	public void setPunchDateTime(String punchDateTime) {
		PunchDateTime = punchDateTime;
	}
	public String getLogDate() {
		return LogDate;
	}
	public void setLogDate(String logDate) {
		LogDate = logDate;
	}
	public String getLogTime() {
		return LogTime;
	}
	public void setLogTime(String logTime) {
		LogTime = logTime;
	}
	public String getDirection() {
		return Direction;
	}
	public void setDirection(String direction) {
		Direction = direction;
	}
	public String getWorkCode() {
		return WorkCode;
	}
	public void setWorkCode(String workCode) {
		WorkCode = workCode;
	}
	public String getDeviceName() {
		return DeviceName;
	}
	public void setDeviceName(String deviceName) {
		DeviceName = deviceName;
	}
	public String getSerialNo() {
		return SerialNo;
	}
	public void setSerialNo(String serialNo) {
		SerialNo = serialNo;
	}
	public String getVerMode() {
		return VerMode;
	}
	public void setVerMode(String verMode) {
		VerMode = verMode;
	}
	@Override
	public String toString() {
		return "SaveLiveThumbData [id=" + id + ", EmpCode=" + EmpCode + ", PunchDateTime=" + PunchDateTime
				+ ", LogDate=" + LogDate + ", LogTime=" + LogTime + ", Direction=" + Direction + ", WorkCode="
				+ WorkCode + ", DeviceName=" + DeviceName + ", SerialNo=" + SerialNo + ", VerMode=" + VerMode + "]";
	}
	
	

}
