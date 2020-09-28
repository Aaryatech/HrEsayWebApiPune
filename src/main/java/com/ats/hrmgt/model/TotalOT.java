package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class TotalOT {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private String id;
	
	@Column(name="depart_id")
	private int departId;
	
	@Column(name="month")
	private String month;
	
	@Column(name="ot")
	private float ot;

	@Column(name="date_mo")
	private String dateMo;
	
	@Column(name="name")
	private String name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
 
	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public float getOt() {
		return ot;
	}

	public void setOt(float ot) {
		this.ot = ot;
	}
	//@JsonFormat(locale = "English",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDateMo() {
		return dateMo;
	}

	public void setDateMo(String dateMo) {
		this.dateMo = dateMo;
	}

	@Override
	public String toString() {
		return "TotalOT [id=" + id + ", departId=" + departId + ", month=" + month + ", ot=" + ot + ", dateMo=" + dateMo
				+ ", name=" + name + "]";
	} 
	
	

}
