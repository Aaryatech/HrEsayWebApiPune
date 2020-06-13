package com.ats.hrmgt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dm_status_mst")
public class DmStatusMst {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int statusId;
	private int statusValue;
	private String statusText;
	private String statusDesc;
	private int delStatus	;
	private String updateDatetime;
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(int statusValue) {
		this.statusValue = statusValue;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public String getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	@Override
	public String toString() {
		return "DmStatusMst [statusId=" + statusId + ", statusValue=" + statusValue + ", statusText=" + statusText
				+ ", statusDesc=" + statusDesc + ", delStatus=" + delStatus + ", updateDatetime=" + updateDatetime
				+ "]";
	}
	
	
}
