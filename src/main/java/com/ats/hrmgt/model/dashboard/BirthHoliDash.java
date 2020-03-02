package com.ats.hrmgt.model.dashboard;

import java.util.List;

import com.ats.hrmgt.model.HolidayMaster;
import com.ats.hrmgt.model.TblEmpInfo;

public class BirthHoliDash {
	
	
	private List<HolidayMaster> holiList;
	
	
	private List<TblEmpInfo> birthListUpcoming;
	
	
	private List<TblEmpInfo> birthListToday;


	public List<HolidayMaster> getHoliList() {
		return holiList;
	}


	public void setHoliList(List<HolidayMaster> holiList) {
		this.holiList = holiList;
	}


	public List<TblEmpInfo> getBirthListUpcoming() {
		return birthListUpcoming;
	}


	public void setBirthListUpcoming(List<TblEmpInfo> birthListUpcoming) {
		this.birthListUpcoming = birthListUpcoming;
	}


	public List<TblEmpInfo> getBirthListToday() {
		return birthListToday;
	}


	public void setBirthListToday(List<TblEmpInfo> birthListToday) {
		this.birthListToday = birthListToday;
	}


	@Override
	public String toString() {
		return "BirthHoliDash [holiList=" + holiList + ", birthListUpcoming=" + birthListUpcoming + ", birthListToday="
				+ birthListToday + "]";
	}
	
	



}
