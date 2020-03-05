package com.ats.hrmgt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.HolidayMaster;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.SummaryDailyAttendance;
import com.ats.hrmgt.model.TblEmpInfo;
import com.ats.hrmgt.model.bonus.BonusMaster;
import com.ats.hrmgt.model.dashboard.AgeDiversityDash;
import com.ats.hrmgt.model.dashboard.BirthHoliDash;
import com.ats.hrmgt.model.dashboard.DeptWiseWeekoffDash;
import com.ats.hrmgt.model.dashboard.GetAllPendingMasterDet;
import com.ats.hrmgt.model.dashboard.GetBirthDaysForDash;
import com.ats.hrmgt.model.dashboard.GetLeaveHistForDash;
import com.ats.hrmgt.model.dashboard.GetNewHiresDash;
import com.ats.hrmgt.model.dashboard.IncentivesAmtDash;
import com.ats.hrmgt.model.dashboard.LeavePenDash;
import com.ats.hrmgt.model.dashboard.LoanAdvDashDet;
import com.ats.hrmgt.model.dashboard.PayRewardDedDash;
import com.ats.hrmgt.model.dashboard.PerformanceProdDash;
import com.ats.hrmgt.model.dashboard.PreDayAttnDash;
import com.ats.hrmgt.model.repo.dash.AgeDiversityDashRepo;
import com.ats.hrmgt.model.repo.dash.DeptWiseWeekoffDashRepo;
import com.ats.hrmgt.model.repo.dash.GetAllPendingMasterDetRepo;
import com.ats.hrmgt.model.repo.dash.GetBirthDaysForDashRepo;
import com.ats.hrmgt.model.repo.dash.GetLeaveHistForDashRepo;
import com.ats.hrmgt.model.repo.dash.GetNewHiresDashRepo;
import com.ats.hrmgt.model.repo.dash.IncentivesAmtDashRepo;
import com.ats.hrmgt.model.repo.dash.LeavePenDashRepo;
import com.ats.hrmgt.model.repo.dash.LoanAdvDashDetDashRepo;
import com.ats.hrmgt.model.repo.dash.PayRewardDedDashRepo;
import com.ats.hrmgt.model.repo.dash.PerformanceProdDashRepo;
import com.ats.hrmgt.model.repo.dash.PreDayAttnDashRepo;
import com.ats.hrmgt.repo.HolidayMasterRepo;
import com.ats.hrmgt.repository.DailyAttendanceRepository;
import com.ats.hrmgt.repository.SummaryDailyAttendanceRepository;
import com.ats.hrmgt.repository.TblEmpInfoRepo;

import ch.qos.logback.classic.pattern.DateConverter;

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

	@RequestMapping(value = { "/getPerformanceBonus" }, method = RequestMethod.GET)
	public @ResponseBody LeavePenDash getPerformanceBonus() throws ParseException {

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

			birthHoliDash.setAttnDate(DateConvertor.convertToDMY(fiterdateNew));
		} catch (Exception e) {

			e.printStackTrace();
		}

		return birthHoliDash;

	}

	@Autowired
	DeptWiseWeekoffDashRepo deptWiseWeekoffDashRepo;

	@RequestMapping(value = { "/getDashDeptWiseWeekoff" }, method = RequestMethod.POST)
	public @ResponseBody List<DeptWiseWeekoffDash> getDashDeptWiseWeekoff(@RequestParam("fiterdate") String fiterdate)
			throws ParseException {

		List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
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

	@Autowired
	PayRewardDedDashRepo payRewardDedDashRepo;

	@RequestMapping(value = { "/getRewardedDet" }, method = RequestMethod.POST)
	public @ResponseBody PayRewardDedDash getRewardedDet(@RequestParam("type") int type,
			@RequestParam("fiterdate") String fiterdate) throws ParseException {

		PayRewardDedDash birthHoliDash = new PayRewardDedDash();
		String temp[] = fiterdate.split("-");
		try {

			if (type == 1) {
				birthHoliDash = payRewardDedDashRepo.getDedDetails(temp[0], temp[1]);

			} else {
				birthHoliDash = payRewardDedDashRepo.getRewardDetails(temp[0], temp[1]);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return birthHoliDash;

	}

	@Autowired
	LoanAdvDashDetDashRepo loanAdvDashDetDashRepo;

	@RequestMapping(value = { "/getAdvLoanDash" }, method = RequestMethod.POST)
	public @ResponseBody LoanAdvDashDet getAdvLoanDash(@RequestParam("type") int type,
			@RequestParam("fiterdate") String fiterdate) throws ParseException {

		LoanAdvDashDet birthHoliDash = new LoanAdvDashDet();

		String temp[] = fiterdate.split("-");
		try {

			if (type == 1) {
				birthHoliDash = loanAdvDashDetDashRepo.getAdvnceDetails(temp[0], temp[1]);

			} else {
				birthHoliDash = loanAdvDashDetDashRepo.getLoanDetails(temp[0], temp[1], fiterdate);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return birthHoliDash;

	}

	@RequestMapping(value = { "/getEmpAbsentLv" }, method = RequestMethod.POST)
	public @ResponseBody List<DeptWiseWeekoffDash> getEmpAbsentLv(@RequestParam("fiterdate") String fiterdate)
			throws ParseException {

		List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
		try {

			list = deptWiseWeekoffDashRepo.getLeavesAndAbsent(fiterdate);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getDeptWisePerformanceBonus" }, method = RequestMethod.POST)
	public @ResponseBody List<DeptWiseWeekoffDash> getDeptWisePerformanceBonus(
			@RequestParam("fiterdate") String fiterdate) throws ParseException {

		List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
		try {

			String temp[] = fiterdate.split("-");
			list = deptWiseWeekoffDashRepo.getDeptWisePerformanceBonus(temp[1], temp[0]);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	GetLeaveHistForDashRepo getLeaveHistForDashRepo;

	@RequestMapping(value = { "/getLeaveHistForDash" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveHistForDash> getLeaveHistForDash(@RequestParam("empId") int empId)
			throws ParseException {

		List<GetLeaveHistForDash> list = new ArrayList<GetLeaveHistForDash>();
		try {

			list = getLeaveHistForDashRepo.getLeaveCnt(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	// **************************Diversity rep*********************************

	@RequestMapping(value = { "/getDashDeptWiseEmpCount" }, method = RequestMethod.GET)
	public @ResponseBody List<DeptWiseWeekoffDash> getDashDeptWiseEmpCount() throws ParseException {

		List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
		try {

			list = deptWiseWeekoffDashRepo.getDeptWiseEmpDiversity();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getAgeDiversity" }, method = RequestMethod.GET)
	public @ResponseBody GetNewHiresDash getAgeDiversity() throws ParseException {

		GetNewHiresDash birthHoliDash = new GetNewHiresDash();

		try {

			birthHoliDash = getNewHiresDashRepo.getAgeDiversity();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return birthHoliDash;

	}

	@Autowired
	AgeDiversityDashRepo ageDiversityDashRepo;

	@RequestMapping(value = { "/getAgeDiversityDetail" }, method = RequestMethod.POST)
	public @ResponseBody List<AgeDiversityDash> getAgeDiversityDetail(@RequestParam("fiterdate") String fiterdate)
			throws ParseException {

		AgeDiversityDash birthHoliDash = new AgeDiversityDash();
		List<AgeDiversityDash> list = new ArrayList<AgeDiversityDash>();
		try {

			birthHoliDash = ageDiversityDashRepo.getAttendance(fiterdate);
			list.add(birthHoliDash);
			birthHoliDash = ageDiversityDashRepo.getExperienceDiversity(fiterdate);
			list.add(birthHoliDash);
			birthHoliDash = ageDiversityDashRepo.getSalaryDiversity(fiterdate);
			list.add(birthHoliDash);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	// ends

	@Autowired
	SummaryDailyAttendanceRepository summaryDailyAttendanceRepository;

	@RequestMapping(value = { "/getEmpLastMonthAttn" }, method = RequestMethod.POST)
	public @ResponseBody SummaryDailyAttendance getEmpLastMonthRep(@RequestParam("fiterdate") String fiterdate,
			@RequestParam("empId") int empId) {

		SummaryDailyAttendance list = new SummaryDailyAttendance();

		try {
			String temp[] = fiterdate.split("-");

			list = summaryDailyAttendanceRepository.summaryDailyAttendanceList1(temp[1], temp[0], empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getDedTypewiseDeduction" }, method = RequestMethod.POST)
	public @ResponseBody List<DeptWiseWeekoffDash> getDedTypewiseDeduction(@RequestParam("empId") int empId)
			throws ParseException {

		List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
		try {

			list = deptWiseWeekoffDashRepo.getDedTypewiseAmt(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getRewardWisewiseDeduction" }, method = RequestMethod.POST)
	public @ResponseBody List<DeptWiseWeekoffDash> getRewardWisewiseDeduction(@RequestParam("empId") int empId)
			throws ParseException {

		List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
		try {

			list = deptWiseWeekoffDashRepo.getRewardwiseAmt(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	IncentivesAmtDashRepo incentivesAmtDashRepo;

	@RequestMapping(value = { "/getEmpTotAmtsDash" }, method = RequestMethod.POST)
	public @ResponseBody IncentivesAmtDash getEmpTotAmtsDash(@RequestParam("empId") int empId) throws ParseException {

		IncentivesAmtDash list = new IncentivesAmtDash();
		try {

			list = incentivesAmtDashRepo.getWeekBirth(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	PerformanceProdDashRepo performanceProdDashRepo;

	@RequestMapping(value = { "/getPerfProd" }, method = RequestMethod.POST)
	public @ResponseBody List<PerformanceProdDash> getPerfProd(@RequestParam("fiterdate") String fiterdate,
			@RequestParam("empId") int empId) {

		List<PerformanceProdDash> list = new ArrayList<PerformanceProdDash>();
		PerformanceProdDash prod = new PerformanceProdDash();

		try {
			String temp[] = fiterdate.split("-");

			prod = performanceProdDashRepo.getPerformanceDetails(temp[0], temp[1], empId);
			list.add(prod);

			prod = performanceProdDashRepo.getProdDetails(temp[0], temp[1], empId);
			list.add(prod);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

}
