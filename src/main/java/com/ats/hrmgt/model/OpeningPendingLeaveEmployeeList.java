package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OpeningPendingLeaveEmployeeList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private String id ;
	
	@Column(name="emp_id")
	private int empId ;
	  
	@Column(name="emp_code")
	private String empCode ;
	
	@Column(name="emp_name")
	private String empName;
	
	@Column(name="lvs_id")
	private int lvsId;
	
	@Column(name="lv_title")
	private String lvTitle;
	  
	@Column(name="lv_type_id")
	private int lvTypeId; 
	
	@Column(name="lvs_alloted_leaves")
	private float lvs_alloted_leaves;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getLvsId() {
		return lvsId;
	}

	public void setLvsId(int lvsId) {
		this.lvsId = lvsId;
	}

	public String getLvTitle() {
		return lvTitle;
	}

	public void setLvTitle(String lvTitle) {
		this.lvTitle = lvTitle;
	}

	public int getLvTypeId() {
		return lvTypeId;
	}

	public void setLvTypeId(int lvTypeId) {
		this.lvTypeId = lvTypeId;
	}

	public float getLvs_alloted_leaves() {
		return lvs_alloted_leaves;
	}

	public void setLvs_alloted_leaves(float lvs_alloted_leaves) {
		this.lvs_alloted_leaves = lvs_alloted_leaves;
	}

	@Override
	public String toString() {
		return "OpeningPendingLeaveEmployeeList [id=" + id + ", empId=" + empId + ", empCode=" + empCode + ", empName="
				+ empName + ", lvsId=" + lvsId + ", lvTitle=" + lvTitle + ", lvTypeId=" + lvTypeId
				+ ", lvs_alloted_leaves=" + lvs_alloted_leaves + "]";
	} 
	 
	
	

}
