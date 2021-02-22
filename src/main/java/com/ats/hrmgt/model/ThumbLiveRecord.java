package com.ats.hrmgt.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ThumbLiveRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private String id;
	  
	@Column(name="emp_code")
	private String empCode;
	
	@Column(name="count_thumb")
	private int countThumb;
	
	@Column(name="thumb_record")
	private String thumbRecord;
	
	@Column(name="emp_name")
	private String empName;
	
	@Column(name="dept_name")
	private String deptName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public int getCountThumb() {
		return countThumb;
	}

	public void setCountThumb(int countThumb) {
		this.countThumb = countThumb;
	}

	public String getThumbRecord() {
		return thumbRecord;
	}

	public void setThumbRecord(String thumbRecord) {
		this.thumbRecord = thumbRecord;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "ThumbLiveRecord [id=" + id + ", empCode=" + empCode + ", countThumb=" + countThumb + ", thumbRecord="
				+ thumbRecord + ", empName=" + empName + ", deptName=" + deptName + "]";
	}
	
	

}
