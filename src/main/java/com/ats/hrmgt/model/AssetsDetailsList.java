package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class AssetsDetailsList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int assetId;
	private String assetCode;	
	private String assetName;
	private String assetDesc;
	private String catName;
	private String assetMake;
	private String assetModel;	
	private String assetSrno;
	private String assetStatus;
	private Date assetPurDate;
	private String assetPurImage;
	private String vendor;
	private String statusText;
	private String assetRemark;
	private int makerUserId;
	private String updateDatetime;
	private int delStatus;
	private String location;
	private int exInt1;
	private int exInt2;	
	private String exVar1;
	private String exVar2;
	
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
	public String getAssetDesc() {
		return assetDesc;
	}
	public void setAssetDesc(String assetDesc) {
		this.assetDesc = assetDesc;
	}
	public String getAssetMake() {
		return assetMake;
	}
	public void setAssetMake(String assetMake) {
		this.assetMake = assetMake;
	}
	public String getAssetModel() {
		return assetModel;
	}
	public void setAssetModel(String assetModel) {
		this.assetModel = assetModel;
	}
	public String getAssetSrno() {
		return assetSrno;
	}
	public void setAssetSrno(String assetSrno) {
		this.assetSrno = assetSrno;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getAssetPurDate() {
		return assetPurDate;
	}
	public void setAssetPurDate(Date assetPurDate) {
		this.assetPurDate = assetPurDate;
	}
	public String getAssetRemark() {
		return assetRemark;
	}
	public void setAssetRemark(String assetRemark) {
		this.assetRemark = assetRemark;
	}
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}
	public String getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
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
	
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAssetStatus() {
		return assetStatus;
	}
	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}
	public String getAssetPurImage() {
		return assetPurImage;
	}
	public void setAssetPurImage(String assetPurImage) {
		this.assetPurImage = assetPurImage;
	}
	@Override
	public String toString() {
		return "AssetsDetailsList [assetId=" + assetId + ", assetCode=" + assetCode + ", assetName=" + assetName
				+ ", assetDesc=" + assetDesc + ", catName=" + catName + ", assetMake=" + assetMake + ", assetModel="
				+ assetModel + ", assetSrno=" + assetSrno + ", assetStatus=" + assetStatus + ", assetPurDate="
				+ assetPurDate + ", assetPurImage=" + assetPurImage + ", vendor=" + vendor + ", statusText="
				+ statusText + ", assetRemark=" + assetRemark + ", makerUserId=" + makerUserId + ", updateDatetime="
				+ updateDatetime + ", delStatus=" + delStatus + ", location=" + location + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
}
