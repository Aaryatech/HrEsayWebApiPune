package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "m_assets")
public class Assets {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int assetId;
	private String assetCode;	
	private String assetName;
	private String assetDesc;
	private int assetCatId;
	private String assetMake;
	private String assetModel;	
	private String assetSrno;
	private String assetPurDate	;
	private int vendorId;
	private String assetRemark;
	private int makerUserId;
	private String updateDatetime;
	private int delStatus;
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
	public int getAssetCatId() {
		return assetCatId;
	}
	public void setAssetCatId(int assetCatId) {
		this.assetCatId = assetCatId;
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
	public String getAssetPurDate() {
		return assetPurDate;
	}
	public void setAssetPurDate(String assetPurDate) {
		this.assetPurDate = assetPurDate;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
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
	@Override
	public String toString() {
		return "Assets [assetId=" + assetId + ", assetCode=" + assetCode + ", assetName=" + assetName + ", assetDesc="
				+ assetDesc + ", assetCatId=" + assetCatId + ", assetMake=" + assetMake + ", assetModel=" + assetModel
				+ ", assetSrno=" + assetSrno + ", assetPurDate=" + assetPurDate + ", vendorId=" + vendorId
				+ ", assetRemark=" + assetRemark + ", makerUserId=" + makerUserId + ", updateDatetime=" + updateDatetime
				+ ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + "]";
	}
	
}
