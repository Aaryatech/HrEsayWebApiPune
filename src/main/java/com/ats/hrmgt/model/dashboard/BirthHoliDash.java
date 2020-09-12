package com.ats.hrmgt.model.dashboard;

import java.util.List;

import com.ats.hrmgt.model.HolidayMaster;
import com.ats.hrmgt.model.TblEmpInfo;

public class BirthHoliDash {
	
	private int loginUserBirthDay;
	
	private List<HolidayMaster> holiList;
	
	
	private List<GetBirthDaysForDash> birthListUpcoming;
	
	
	private List<GetBirthDaysForDash> birthListToday;


	public int getLoginUserBirthDay() {
		return loginUserBirthDay;
	}
 
	public void setLoginUserBirthDay(int loginUserBirthDay) {
		this.loginUserBirthDay = loginUserBirthDay;
	}
 
	public List<HolidayMaster> getHoliList() {
		return holiList;
	}
 
	public void setHoliList(List<HolidayMaster> holiList) {
		this.holiList = holiList;
	}
 
	public List<GetBirthDaysForDash> getBirthListUpcoming() {
		return birthListUpcoming;
	}


	public void setBirthListUpcoming(List<GetBirthDaysForDash> birthListUpcoming) {
		this.birthListUpcoming = birthListUpcoming;
	}


	public List<GetBirthDaysForDash> getBirthListToday() {
		return birthListToday;
	}


	public void setBirthListToday(List<GetBirthDaysForDash> birthListToday) {
		this.birthListToday = birthListToday;
	}
 
	@Override
	public String toString() {
		return "BirthHoliDash [loginUserBirthDay=" + loginUserBirthDay + ", holiList=" + holiList
				+ ", birthListUpcoming=" + birthListUpcoming + ", birthListToday=" + birthListToday + "]";
	}
	
	



}
