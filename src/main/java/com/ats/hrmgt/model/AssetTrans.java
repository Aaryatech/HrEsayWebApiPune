package com.ats.hrmgt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_asset_trans")
public class AssetTrans {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int assetTransId;
	private int ahId;
	private int empId;
	private int assetId;
	private String useFromDate;
	private String useToDate;
	private int transRemark;
	private int assetTransStatus;
	private int makerUserId;
	private String updateDtetime;
	private int delStatus;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getAssetTransId() {
		return assetTransId;
	}
	public void setAssetTransId(int assetTransId) {
		this.assetTransId = assetTransId;
	}
	public int getAhId() {
		return ahId;
	}
	public void setAhId(int ahId) {
		this.ahId = ahId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public String getUseFromDate() {
		return useFromDate;
	}
	public void setUseFromDate(String useFromDate) {
		this.useFromDate = useFromDate;
	}
	public String getUseToDate() {
		return useToDate;
	}
	public void setUseToDate(String useToDate) {
		this.useToDate = useToDate;
	}
	public int getTransRemark() {
		return transRemark;
	}
	public void setTransRemark(int transRemark) {
		this.transRemark = transRemark;
	}
	public int getAssetTransStatus() {
		return assetTransStatus;
	}
	public void setAssetTransStatus(int assetTransStatus) {
		this.assetTransStatus = assetTransStatus;
	}
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}
	public String getUpdateDtetime() {
		return updateDtetime;
	}
	public void setUpdateDtetime(String updateDtetime) {
		this.updateDtetime = updateDtetime;
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
		return "AssetTrans [assetTransId=" + assetTransId + ", ahId=" + ahId + ", empId=" + empId + ", assetId="
				+ assetId + ", useFromDate=" + useFromDate + ", useToDate=" + useToDate + ", transRemark=" + transRemark
				+ ", assetTransStatus=" + assetTransStatus + ", makerUserId=" + makerUserId + ", updateDtetime="
				+ updateDtetime + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	
}
