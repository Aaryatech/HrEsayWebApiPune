package com.ats.hrmgt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.HolidayMaster;
import com.ats.hrmgt.model.TblEmpInfo;
import com.ats.hrmgt.model.bonus.BonusMaster;
import com.ats.hrmgt.model.dashboard.BirthHoliDash;
import com.ats.hrmgt.repo.HolidayMasterRepo;
import com.ats.hrmgt.repository.TblEmpInfoRepo;

@RestController
public class DashboardApiController {

	@Autowired
	HolidayMasterRepo holidayMasterRepo;

	@Autowired
	TblEmpInfoRepo tblEmpInfoRepo;

	@RequestMapping(value = { "/getBirthDayAndHolidayDash" }, method = RequestMethod.POST)
	public @ResponseBody BirthHoliDash getBirthDayAndHolidayDash(@RequestParam("fiterdate") String fiterdate)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
		Date date = new Date();
		Calendar c = Calendar.getInstance();

		System.err.println(date);
		try {
			// Setting the date to the given date
			c.setTime(sdf.parse(String.valueOf(date)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Date before Addition: " + sdf.parse(String.valueOf(date)));

		c.add(Calendar.DAY_OF_MONTH, 30);
		String newDate = sdf.format(c.getTime());
		System.out.println("Date after Addition: " + newDate);

		BirthHoliDash birthHoliDash = new BirthHoliDash();
		List<HolidayMaster> holilist = new ArrayList<HolidayMaster>();

		List<TblEmpInfo> birthListUpcoming = new ArrayList<TblEmpInfo>();
		;

		List<TblEmpInfo> birthListToday = new ArrayList<TblEmpInfo>();
		;
		try {

			holilist = holidayMasterRepo.getHolidaysForDash(String.valueOf(sdf.parse(String.valueOf(date))), newDate);
			System.err.println("holilist" + holilist.toString());
			
			//birthListUpcoming.getTodaysBirthDays(date,);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return birthHoliDash;

	}

}
