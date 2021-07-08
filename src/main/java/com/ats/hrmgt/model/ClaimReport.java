package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ClaimReport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;

	@Column(name = "claim_id")
	private int claimId;

	@Column(name = "emp_id")
	private int empId;

	@Column(name = "ca_from_dt")
	private Date caFromDt;

	@Column(name = "ca_to_dt")
	private Date caToDt;
	
	@Column(name = "emp_code")
	private String empCode;
	
	@Column(name = "emp_name")
	private String empName;

	@Column(name = "claim_amount")
	private float claimAmount;

	@Column(name = "claim_title")
	private String claimTitle;

	@Column(name = "claim_type")
	private String claimType;

	@Column(name = "detail_amt")
	private float detailAmt;

	@Column(name = "dept_name")
	private String deptName;

	@Column(name = "initial_auth_name")
	private String initialAuthName;

	@Column(name = "final_auth_name")
	private String finalAuthName;

	@Column(name = "first_approval_remark")
	private String firstApprovalRemark;

	@Column(name = "second_approval_remark")
	private String secondApprovalRemark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getCaFromDt() {
		return caFromDt;
	}

	public void setCaFromDt(Date caFromDt) {
		this.caFromDt = caFromDt;
	}

	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getCaToDt() {
		return caToDt;
	}

	public void setCaToDt(Date caToDt) {
		this.caToDt = caToDt;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public float getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getClaimTitle() {
		return claimTitle;
	}

	public void setClaimTitle(String claimTitle) {
		this.claimTitle = claimTitle;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public float getDetailAmt() {
		return detailAmt;
	}

	public void setDetailAmt(float detailAmt) {
		this.detailAmt = detailAmt;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getInitialAuthName() {
		return initialAuthName;
	}

	public void setInitialAuthName(String initialAuthName) {
		this.initialAuthName = initialAuthName;
	}

	public String getFinalAuthName() {
		return finalAuthName;
	}

	public void setFinalAuthName(String finalAuthName) {
		this.finalAuthName = finalAuthName;
	}

	public String getFirstApprovalRemark() {
		return firstApprovalRemark;
	}

	public void setFirstApprovalRemark(String firstApprovalRemark) {
		this.firstApprovalRemark = firstApprovalRemark;
	}

	public String getSecondApprovalRemark() {
		return secondApprovalRemark;
	}

	public void setSecondApprovalRemark(String secondApprovalRemark) {
		this.secondApprovalRemark = secondApprovalRemark;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	@Override
	public String toString() {
		return "ClaimReport [id=" + id + ", claimId=" + claimId + ", empId=" + empId + ", caFromDt=" + caFromDt
				+ ", caToDt=" + caToDt + ", empCode=" + empCode + ", empName=" + empName + ", claimAmount="
				+ claimAmount + ", claimTitle=" + claimTitle + ", claimType=" + claimType + ", detailAmt=" + detailAmt
				+ ", deptName=" + deptName + ", initialAuthName=" + initialAuthName + ", finalAuthName=" + finalAuthName
				+ ", firstApprovalRemark=" + firstApprovalRemark + ", secondApprovalRemark=" + secondApprovalRemark
				+ "]";
	}

}
