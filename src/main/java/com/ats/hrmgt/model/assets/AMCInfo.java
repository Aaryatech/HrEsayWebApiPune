package com.ats.hrmgt.model.assets;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class AMCInfo {

	@Id
	private int amcId;
	private int assetId;
	
	private Date amcFrDate;
	private Date amcToDate;
	private String amcAmt;
	
	private String positiveRemark;
	private String negativeRemark;
	
	private int vendorId;
	private String compName;
	
	private int amcStatus; //Integer Value of text 11-Live 12-Renewed 13-Terminated 10-Pending
	private String statusText; //Status Text to be displayed.
	private int assetStatus;	
	
	private String exVar1;
	private String exVar2;
	
	public int getAmcId() {
		return amcId;
	}
	public void setAmcId(int amcId) {
		this.amcId = amcId;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getAmcFrDate() {
		return amcFrDate;
	}
	public void setAmcFrDate(Date amcFrDate) {
		this.amcFrDate = amcFrDate;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getAmcToDate() {
		return amcToDate;
	}
	public void setAmcToDate(Date amcToDate) {
		this.amcToDate = amcToDate;
	}
	public String getAmcAmt() {
		return amcAmt;
	}
	public void setAmcAmt(String amcAmt) {
		this.amcAmt = amcAmt;
	}
	public String getPositiveRemark() {
		return positiveRemark;
	}
	public void setPositiveRemark(String positiveRemark) {
		this.positiveRemark = positiveRemark;
	}
	public String getNegativeRemark() {
		return negativeRemark;
	}
	public void setNegativeRemark(String negativeRemark) {
		this.negativeRemark = negativeRemark;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public int getAmcStatus() {
		return amcStatus;
	}
	public void setAmcStatus(int amcStatus) {
		this.amcStatus = amcStatus;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public String getExVar2() {
		return exVar2;
	}
	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}
	public int getAssetStatus() {
		return assetStatus;
	}
	public void setAssetStatus(int assetStatus) {
		this.assetStatus = assetStatus;
	}
	@Override
	public String toString() {
		return "AMCInfo [amcId=" + amcId + ", assetId=" + assetId + ", amcFrDate=" + amcFrDate + ", amcToDate="
				+ amcToDate + ", amcAmt=" + amcAmt + ", positiveRemark=" + positiveRemark + ", negativeRemark="
				+ negativeRemark + ", vendorId=" + vendorId + ", compName=" + compName + ", amcStatus=" + amcStatus
				+ ", statusText=" + statusText + ", assetStatus=" + assetStatus + ", exVar1=" + exVar1 + ", exVar2="
				+ exVar2 + "]";
	}
	
}
