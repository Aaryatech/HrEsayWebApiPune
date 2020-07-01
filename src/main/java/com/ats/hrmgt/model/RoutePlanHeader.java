package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_route_plan_header")
public class RoutePlanHeader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "plan_head_id")
	private int planHeadId;

	@Column(name = "plan_date")
	private String planDate;

	@Column(name = "is_confirm")
	private int isConfirm;

	@Column(name = "del_status")
	private int delStatus;

	@Column(name = "extra_int1 ")
	private int extraInt1;
	
	@Column(name = "extra_int2")
	private int extraInt2;
	
	@Column(name = "extra_var2")
	private String extraVar2;
	
	@Column(name = "extra_var1")
	private String extraVar1;

	 

	public int getPlanHeadId() {
		return planHeadId;
	}

	public void setPlanHeadId(int planHeadId) {
		this.planHeadId = planHeadId;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public int getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(int isConfirm) {
		this.isConfirm = isConfirm;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getExtraInt1() {
		return extraInt1;
	}

	public void setExtraInt1(int extraInt1) {
		this.extraInt1 = extraInt1;
	}

	public int getExtraInt2() {
		return extraInt2;
	}

	public void setExtraInt2(int extraInt2) {
		this.extraInt2 = extraInt2;
	}

	public String getExtraVar2() {
		return extraVar2;
	}

	public void setExtraVar2(String extraVar2) {
		this.extraVar2 = extraVar2;
	}

	public String getExtraVar1() {
		return extraVar1;
	}

	public void setExtraVar1(String extraVar1) {
		this.extraVar1 = extraVar1;
	}

	@Override
	public String toString() {
		return "RoutePlanHeader [planHeadId=" + planHeadId + ", planDate=" + planDate + ", isConfirm=" + isConfirm
				+ ", delStatus=" + delStatus + ", extraInt1=" + extraInt1 + ", extraInt2=" + extraInt2 + ", extraVar2="
				+ extraVar2 + ", extraVar1=" + extraVar1 + "]";
	}
	  
	
	

}
