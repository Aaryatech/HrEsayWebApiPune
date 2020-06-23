package com.ats.hrmgt.model.assets;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ScrappedAssetsReport {

	@Id
	private int assetId;
	private String assetCode;
	private String assetName;
	private Date assetPurDate;
	private String catName;
	private Date scrapDate;
	private String scrapRemark;
	private String scrapAuthoriyDetails;
	private String scrapDatetime;
	private String empCode;
	private String firstName;
	private String surname;
	private String deptName;
	private String empDesgn;
	private String locName;
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
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getAssetPurDate() {
		return assetPurDate;
	}
	public void setAssetPurDate(Date assetPurDate) {
		this.assetPurDate = assetPurDate;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getScrapDate() {
		return scrapDate;
	}
	public void setScrapDate(Date scrapDate) {
		this.scrapDate = scrapDate;
	}
	public String getScrapRemark() {
		return scrapRemark;
	}
	public void setScrapRemark(String scrapRemark) {
		this.scrapRemark = scrapRemark;
	}
	public String getScrapAuthoriyDetails() {
		return scrapAuthoriyDetails;
	}
	public void setScrapAuthoriyDetails(String scrapAuthoriyDetails) {
		this.scrapAuthoriyDetails = scrapAuthoriyDetails;
	}
	public String getScrapDatetime() {
		return scrapDatetime;
	}
	public void setScrapDatetime(String scrapDatetime) {
		this.scrapDatetime = scrapDatetime;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getEmpDesgn() {
		return empDesgn;
	}
	public void setEmpDesgn(String empDesgn) {
		this.empDesgn = empDesgn;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	@Override
	public String toString() {
		return "ScrappedAssetsReport [assetId=" + assetId + ", assetCode=" + assetCode + ", assetName=" + assetName
				+ ", assetPurDate=" + assetPurDate + ", catName=" + catName + ", scrapDate=" + scrapDate
				+ ", scrapRemark=" + scrapRemark + ", scrapAuthoriyDetails=" + scrapAuthoriyDetails + ", scrapDatetime="
				+ scrapDatetime + ", empCode=" + empCode + ", firstName=" + firstName + ", surname=" + surname
				+ ", deptName=" + deptName + ", empDesgn=" + empDesgn + ", locName=" + locName + "]";
	}
	
}
