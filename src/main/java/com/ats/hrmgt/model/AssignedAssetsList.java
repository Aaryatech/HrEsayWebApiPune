package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class AssignedAssetsList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int assetTransId;
	private int assetId;
	private String assetCode;
	private String assetName;
	private String catName;
	private Date useFromDate;
	private Date useToDate;
	private String assignRemark;
	private String assignImgFile;
	
	public int getAssetTransId() {
		return assetTransId;
	}
	public void setAssetTransId(int assetTransId) {
		this.assetTransId = assetTransId;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getUseFromDate() {
		return useFromDate;
	}
	public void setUseFromDate(Date useFromDate) {
		this.useFromDate = useFromDate;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getUseToDate() {
		return useToDate;
	}
	public void setUseToDate(Date useToDate) {
		this.useToDate = useToDate;
	}
	public String getAssignRemark() {
		return assignRemark;
	}
	public void setAssignRemark(String assignRemark) {
		this.assignRemark = assignRemark;
	}
	
	public String getAssignImgFile() {
		return assignImgFile;
	}
	public void setAssignImgFile(String assignImgFile) {
		this.assignImgFile = assignImgFile;
	}
	@Override
	public String toString() {
		return "AssignedAssetsList [assetTransId=" + assetTransId + ", assetId=" + assetId + ", assetCode=" + assetCode
				+ ", assetName=" + assetName + ", catName=" + catName + ", useFromDate=" + useFromDate + ", useToDate="
				+ useToDate + ", assignRemark=" + assignRemark + ", assignImgFile=" + assignImgFile + "]";
	}
	
}
