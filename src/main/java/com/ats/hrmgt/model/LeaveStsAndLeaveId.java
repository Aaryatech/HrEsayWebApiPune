package com.ats.hrmgt.model;

public class LeaveStsAndLeaveId {
	
	private int sts;
	private int duration;
	private int leaveId;
	private String msg;
	private int leaveTyId;
	private float noOfLeave;
	private int lvTypeId;
	private String stsshortname;
	
	public int getSts() {
		return sts;
	}
	public void setSts(int sts) {
		this.sts = sts;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getLeaveTyId() {
		return leaveTyId;
	}
	public void setLeaveTyId(int leaveTyId) {
		this.leaveTyId = leaveTyId;
	}
	public float getNoOfLeave() {
		return noOfLeave;
	}
	public void setNoOfLeave(float noOfLeave) {
		this.noOfLeave = noOfLeave;
	}
	public int getLvTypeId() {
		return lvTypeId;
	}
	public void setLvTypeId(int lvTypeId) {
		this.lvTypeId = lvTypeId;
	}
	public String getStsshortname() {
		return stsshortname;
	}
	public void setStsshortname(String stsshortname) {
		this.stsshortname = stsshortname;
	}
	@Override
	public String toString() {
		return "LeaveStsAndLeaveId [sts=" + sts + ", duration=" + duration + ", leaveId=" + leaveId + ", msg=" + msg
				+ ", leaveTyId=" + leaveTyId + ", noOfLeave=" + noOfLeave + ", lvTypeId=" + lvTypeId + ", stsshortname="
				+ stsshortname + "]";
	}
	
	
	

}
