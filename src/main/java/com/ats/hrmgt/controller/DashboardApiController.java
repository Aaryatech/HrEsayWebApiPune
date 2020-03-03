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

import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.HolidayMaster;
import com.ats.hrmgt.model.TblEmpInfo;
import com.ats.hrmgt.model.bonus.BonusMaster;
import com.ats.hrmgt.model.dashboard.BirthHoliDash;
import com.ats.hrmgt.model.dashboard.DeptWiseWeekoffDash;
import com.ats.hrmgt.model.dashboard.GetAllPendingMasterDet;
import com.ats.hrmgt.model.dashboard.GetBirthDaysForDash;
import com.ats.hrmgt.model.dashboard.GetNewHiresDash;
import com.ats.hrmgt.model.dashboard.LeavePenDash;
import com.ats.hrmgt.model.dashboard.PreDayAttnDash;
import com.ats.hrmgt.model.repo.dash.DeptWiseWeekoffDashRepo;
import com.ats.hrmgt.model.repo.dash.GetAllPendingMasterDetRepo;
import com.ats.hrmgt.model.repo.dash.GetBirthDaysForDashRepo;
import com.ats.hrmgt.model.repo.dash.GetNewHiresDashRepo;
import com.ats.hrmgt.model.repo.dash.LeavePenDashRepo;
import com.ats.hrmgt.model.repo.dash.PreDayAttnDashRepo;
import com.ats.hrmgt.repo.HolidayMasterRepo;
import com.ats.hrmgt.repository.DailyAttendanceRepository;
import com.ats.hrmgt.repository.TblEmpInfoRepo;

@RestController
public class DashboardApiController {

	@Autowired
	HolidayMasterRepo holidayMasterRepo;

	@Autowired
	GetBirthDaysForDashRepo getBirthDaysForDashRepo;

	@RequestMapping(value = { "/getBirthDayAndHolidayDash" }, method = RequestMethod.POST)
	public @ResponseBody BirthHoliDash getBirthDayAndHolidayDash(@RequestParam("fiterdate") String fiterdate)
			throws ParseException {

		BirthHoliDash birthHoliDash = new BirthHoliDash();
		List<HolidayMaster> holilist = new ArrayList<HolidayMaster>();

		List<GetBirthDaysForDash> birthListUpcoming = new ArrayList<GetBirthDaysForDash>();

		List<GetBirthDaysForDash> birthListToday = new ArrayList<GetBirthDaysForDash>();

		try {

			holilist = holidayMasterRepo.getHolidaysForDash(fiterdate);
			birthListToday = getBirthDaysForDashRepo.getTodaysBirth(fiterdate);
			birthListUpcoming = getBirthDaysForDashRepo.getWeekBirth(fiterdate);

			birthHoliDash.setBirthListToday(birthListToday);
			birthHoliDash.setBirthListUpcoming(birthListUpcoming);
			birthHoliDash.setHoliList(holilist);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return birthHoliDash;

	}

	@Autowired
	GetNewHiresDashRepo getNewHiresDashRepo;

	@RequestMapping(value = { "/getNewHireDash" }, method = RequestMethod.POST)
	public @ResponseBody GetNewHiresDash getNewHireDash(@RequestParam("fiterdate") String fiterdate)
			throws ParseException {

		GetNewHiresDash birthHoliDash = new GetNewHiresDash();

		try {

			birthHoliDash = getNewHiresDashRepo.getTodaysHire(fiterdate);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return birthHoliDash;

	}

	@Autowired
	LeavePenDashRepo leavePenDashRepo;

	@RequestMapping(value = { "/getLeaveCountDash" }, method = RequestMethod.GET)
	public @ResponseBody LeavePenDash getLeaveCountDash() throws ParseException {

		LeavePenDash birthHoliDash = new LeavePenDash();

		try {

			birthHoliDash = leavePenDashRepo.getLeaveCnt();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return birthHoliDash;

	}

	@Autowired
	PreDayAttnDashRepo preDayAttnDashRepo;

	@Autowired
	DailyAttendanceRepository dailyAttendanceRepository;

	@RequestMapping(value = { "/getAttnDash" }, method = RequestMethod.POST)
	public @ResponseBody PreDayAttnDash getAttnDash(@RequestParam("fiterdate") String fiterdate) throws ParseException {

		PreDayAttnDash birthHoliDash = new PreDayAttnDash();
		DailyAttendance dailyAttn = new DailyAttendance();
		List<DailyAttendance> dailyAttnList = new ArrayList<DailyAttendance>();
		try {

			String fiterdateNew = new String();
			dailyAttnList = dailyAttendanceRepository.dailyAttendanceListRec(fiterdate);
			if (dailyAttnList.size() == 0) {
				dailyAttn = dailyAttendanceRepository.dailyAttendanceListLastRec();
				fiterdateNew = dailyAttn.getAttDate();
			} else {
				fiterdateNew = fiterdate;
			}

			birthHoliDash = preDayAttnDashRepo.getAttendance(fiterdateNew);

			birthHoliDash.setAttnDate(fiterdateNew);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return birthHoliDash;

	}
	@Autowired
 	DeptWiseWeekoffDashRepo deptWiseWeekoffDashRepo;
	
	
	@RequestMapping(value = { "/getDashDeptWiseWeekoff" }, method = RequestMethod.POST)
	public @ResponseBody 	List<DeptWiseWeekoffDash> getDashDeptWiseWeekoff(@RequestParam("fiterdate") String fiterdate)
			throws ParseException {

		List<DeptWiseWeekoffDash> list =new ArrayList<DeptWiseWeekoffDash>();
		try {

			list = deptWiseWeekoffDashRepo.getAttendance(fiterdate);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@Autowired
	GetAllPendingMasterDetRepo getAllPendingMasterDetRepo;
	
	
	@RequestMapping(value = { "/getAllPendingMasterDet" }, method = RequestMethod.GET)
	public @ResponseBody GetAllPendingMasterDet getAllPendingMasterDet() throws ParseException {

		GetAllPendingMasterDet birthHoliDash = new GetAllPendingMasterDet();

		try {

			birthHoliDash = getAllPendingMasterDetRepo.getDet();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return birthHoliDash;

	}
	


}
