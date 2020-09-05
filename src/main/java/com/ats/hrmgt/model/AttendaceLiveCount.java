package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AttendaceLiveCount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="lv_sumup_id")
	private int lvSumupId;
	  
	@Column(name="atts_sd_show")
	private String attsSdShow;
	
	@Column(name="cnt")
	private int cnt;

	public int getLvSumupId() {
		return lvSumupId;
	}

	public void setLvSumupId(int lvSumupId) {
		this.lvSumupId = lvSumupId;
	}

	public String getAttsSdShow() {
		return attsSdShow;
	}

	public void setAttsSdShow(String attsSdShow) {
		this.attsSdShow = attsSdShow;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "AttendaceLiveCount [lvSumupId=" + lvSumupId + ", attsSdShow=" + attsSdShow + ", cnt=" + cnt + "]";
	} 
	
	
}
