package com.ats.hrmgt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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
import com.ats.hrmgt.model.AdvaceAmtGraph;
import com.ats.hrmgt.model.AttendaceLiveData;
import com.ats.hrmgt.model.CountData;
import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.DashboardLeavePending;
import com.ats.hrmgt.model.EmpDeptWise;
import com.ats.hrmgt.model.EmpGraphDetail;
import com.ats.hrmgt.model.EmpInfoForDashBoard;
import com.ats.hrmgt.model.HolidayMaster;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveAuthority;
import com.ats.hrmgt.model.LeaveHistory;
import com.ats.hrmgt.model.LineGraphData;
import com.ats.hrmgt.model.MonthWiseDisbusedAmt;
import com.ats.hrmgt.model.MonthWithOT;
import com.ats.hrmgt.model.PresentAttendaceList;
import com.ats.hrmgt.model.SelfAttendanceDetail;
import com.ats.hrmgt.model.SummaryDailyAttendance;
import com.ats.hrmgt.model.TblEmpInfo;
import com.ats.hrmgt.model.TotalOT;
import com.ats.hrmgt.model.bonus.BonusMaster;
import com.ats.hrmgt.model.dashboard.AgeDiversityDash;
import com.ats.hrmgt.model.dashboard.BirthHoliDash;
import com.ats.hrmgt.model.dashboard.CommonDash;
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
import com.ats.hrmgt.repository.AdvaceAmtGraphRepository;
import com.ats.hrmgt.repository.CountDataRepository;
import com.ats.hrmgt.repository.DailyAttendanceRepository;
import com.ats.hrmgt.repository.DashboardLeavePendingRepo;
import com.ats.hrmgt.repository.EmpDeptWiseRepository;
import com.ats.hrmgt.repository.EmpGraphDetailRepository;
import com.ats.hrmgt.repository.EmpInfoForDashBoardRepository;
import com.ats.hrmgt.repository.LeaveAuthorityRepository;
import com.ats.hrmgt.repository.PresentAttendaceListRepository;
import com.ats.hrmgt.repository.SelfAttendanceDetailRepository;
import com.ats.hrmgt.repository.SummaryDailyAttendanceRepository;
import com.ats.hrmgt.repository.TblEmpInfoRepo;
import com.ats.hrmgt.repository.TotalOTRepository;

import ch.qos.logback.classic.pattern.DateConverter;

@RestController
public class DashboardApiController {

	@Autowired
	HolidayMasterRepo holidayMasterRepo;

	@Autowired
	GetBirthDaysForDashRepo getBirthDaysForDashRepo;

	@Autowired
	PerformanceProdDashRepo performanceProdDashRepo;

	@Autowired
	AgeDiversityDashRepo ageDiversityDashRepo;

	@Autowired
	IncentivesAmtDashRepo incentivesAmtDashRepo;

	@Autowired
	SummaryDailyAttendanceRepository summaryDailyAttendanceRepository;

	@Autowired
	EmpDeptWiseRepository empDeptWiseRepository;

	@Autowired
	SelfAttendanceDetailRepository selfAttendanceDetailRepository;

	@RequestMapping(value = { "/getCommonDash" }, method = RequestMethod.POST)
	public @ResponseBody CommonDash getCommonDash(@RequestParam("fiterdate") String fiterdate,
			@RequestParam("empId") int empId, @RequestParam("userType") int userType,
			@RequestParam("isAuth") int isAuth, @RequestParam("locId") int locId) throws ParseException {

		CommonDash comDash = new CommonDash();
		BirthHoliDash birthHoliDash = new BirthHoliDash();
		List<HolidayMaster> holilist = new ArrayList<HolidayMaster>();
		List<GetBirthDaysForDash> birthListUpcoming = new ArrayList<GetBirthDaysForDash>();
		List<GetBirthDaysForDash> birthListToday = new ArrayList<GetBirthDaysForDash>();
		String temp[] = fiterdate.split("-");

		// 1 getBirthDayAndHolidayDash

		// for all
		try {
			GetBirthDaysForDash getBirthDaysForDash = getBirthDaysForDashRepo.loginUserBirthDay(fiterdate, empId);
			Date dt = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(getBirthDaysForDash.getDob());
			int month2 = cal2.get(Calendar.MONTH) + 1; // 9 - October!!!
			int day2 = cal2.get(Calendar.DAY_OF_MONTH);

			if (month == month2 && day == day2) {
				birthHoliDash.setLoginUserBirthDay(1);
			} else {
				birthHoliDash.setLoginUserBirthDay(0);
			}
			if (userType == 2) {
				holilist = holidayMasterRepo.getHolidaysForDash(fiterdate);
				birthListToday = getBirthDaysForDashRepo.getTodaysBirth(fiterdate, locId);
				birthListUpcoming = getBirthDaysForDashRepo.getWeekBirth(fiterdate, locId);
			}
			birthHoliDash.setBirthListToday(birthListToday);
			birthHoliDash.setBirthListUpcoming(birthListUpcoming);
			birthHoliDash.setHoliList(holilist);

		} catch (Exception e) {

			e.printStackTrace();
		}

		comDash.setBirth(birthHoliDash);

		// 12 getLeaveHistForDash
		List<GetLeaveHistForDash> leaveHistForDashlist = new ArrayList<GetLeaveHistForDash>();
		try {

			// leaveHistForDashlist = getLeaveHistForDashRepo.getLeaveCnt(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}
		comDash.setLvApplList(leaveHistForDashlist);

		// getDashDeptWiseEmpCount
		if (userType == 2) {
			List<DeptWiseWeekoffDash> listEmpDiv = new ArrayList<DeptWiseWeekoffDash>();
			try {

				//listEmpDiv = deptWiseWeekoffDashRepo.getDeptWiseEmpDiversity(locId);

			} catch (Exception e) {

				e.printStackTrace();
			}

			comDash.setDeptWiseEmpCntList(listEmpDiv);
		}
		// getDedTypewiseDeduction

		List<DeptWiseWeekoffDash> listDedTypewiseAm = new ArrayList<DeptWiseWeekoffDash>();
		try {

			// listDedTypewiseAm = deptWiseWeekoffDashRepo.getDedTypewiseAmt(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}
		comDash.setDedWiseDedList(listDedTypewiseAm);
		// /getRewardWisewiseDeduction

		List<DeptWiseWeekoffDash> rewardwiseAmtlist = new ArrayList<DeptWiseWeekoffDash>();
		try {

			rewardwiseAmtlist = deptWiseWeekoffDashRepo.getRewardwiseAmt(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}
		comDash.setRewardWiseDedList(rewardwiseAmtlist);

		IncentivesAmtDash incent = new IncentivesAmtDash();
		try {

			// incent = incentivesAmtDashRepo.getWeekBirth(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		comDash.setIcent(incent);

		// getPerfProd

		PerformanceProdDash prod = new PerformanceProdDash();

		try {

			/*
			 * prod = performanceProdDashRepo.getPerformanceDetails(temp[0], temp[1],
			 * empId);
			 * 
			 * comDash.setPerfList(prod); prod =
			 * performanceProdDashRepo.getProdDetails(temp[0], temp[1], empId);
			 * 
			 * comDash.setProdList(prod);
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}

		// for HR only

		if (userType == 2) {
			// 2 getNewHireDash
			GetNewHiresDash hireDash = new GetNewHiresDash();

			try {

				hireDash = getNewHiresDashRepo.getTodaysHire(fiterdate, locId);

			} catch (Exception e) {

				e.printStackTrace();
			}

			comDash.setNewHire(hireDash);
			// 3 getLeaveCountDash

			LeavePenDash leavePenDash = new LeavePenDash();

			try {

				leavePenDash = leavePenDashRepo.getLeaveCnt(locId);

			} catch (Exception e) {

				e.printStackTrace();
			}
			comDash.setLvDet(leavePenDash);

			// 5 getAttnDash
			/*
			 * PreDayAttnDash preDayAttn = new PreDayAttnDash(); DailyAttendance dailyAttn =
			 * new DailyAttendance(); List<DailyAttendance> dailyAttnList = new
			 * ArrayList<DailyAttendance>(); try {
			 * 
			 * String fiterdateNew = new String(); dailyAttnList =
			 * dailyAttendanceRepository.dailyAttendanceListRec(fiterdate); if
			 * (dailyAttnList.size() == 0) { dailyAttn =
			 * dailyAttendanceRepository.dailyAttendanceListLastRec(); fiterdateNew =
			 * dailyAttn.getAttDate(); } else { fiterdateNew = fiterdate; }
			 * 
			 * preDayAttn = preDayAttnDashRepo.getAttendance(fiterdateNew);
			 * 
			 * preDayAttn.setAttnDate(DateConvertor.convertToDMY(fiterdateNew)); } catch
			 * (Exception e) {
			 * 
			 * e.printStackTrace(); } comDash.setAttnDet(preDayAttn);
			 */

			// 7 getAllPendingMasterDet

			GetAllPendingMasterDet pendingMast = new GetAllPendingMasterDet();

			try {

				pendingMast = getAllPendingMasterDetRepo.getDet(locId);

			} catch (Exception e) {

				e.printStackTrace();
			}
			comDash.setMasterDet(pendingMast);

			// 9 getAdvLoanDash

			LoanAdvDashDet advDash = new LoanAdvDashDet();

			LoanAdvDashDet loanDash = new LoanAdvDashDet();

			try {

				advDash = loanAdvDashDetDashRepo.getAdvnceDetails(temp[0], temp[1], locId);
				comDash.setAdvDet(advDash);
				loanDash = loanAdvDashDetDashRepo.getLoanDetails(fiterdate, locId);
				comDash.setLoanDet(loanDash);
			} catch (Exception e) {

				e.printStackTrace();
			}

			// 11 getDeptWisePerformanceBonus

			List<DeptWiseWeekoffDash> deptWisePerformanceBonusLidt = new ArrayList<DeptWiseWeekoffDash>();
			try {

				// deptWisePerformanceBonusLidt =
				// deptWiseWeekoffDashRepo.getDeptWisePerformanceBonus(temp[1], temp[0]);

			} catch (Exception e) {

				e.printStackTrace();
			}

			comDash.setPerfListDept(deptWisePerformanceBonusLidt);

			// getAgeDiversity

			GetNewHiresDash ageDiv = new GetNewHiresDash();

			try {

				ageDiv = getNewHiresDashRepo.getAgeDiversity(locId);

			} catch (Exception e) {

				e.printStackTrace();
			}
			comDash.setAgeDiv(ageDiv);
			// getAgeDiversityDetail

			AgeDiversityDash tempList = new AgeDiversityDash();
			try {

				tempList = ageDiversityDashRepo.getAttendance(fiterdate,locId);
				comDash.setAgeDiversity(tempList);
				tempList = ageDiversityDashRepo.getExperienceDiversity(fiterdate,locId);
				comDash.setExpDiversity(tempList);
				tempList = ageDiversityDashRepo.getSalaryDiversity(locId);
				comDash.setSalDiversity(tempList);

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		// for HR and Authority

		if (userType == 2) {

			// 6 getDashDeptWiseWeekoff
			List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>();
			try {

				// list = deptWiseWeekoffDashRepo.getAttendance(fiterdate);

			} catch (Exception e) {

				e.printStackTrace();
			}
			comDash.setDeptwiseWkoff(list);

			// 8 getRewardedDet

			PayRewardDedDash dedDet = new PayRewardDedDash();
			PayRewardDedDash rewardDet = new PayRewardDedDash();
			try {
				dedDet = payRewardDedDashRepo.getDedDetails(temp[0], temp[1], locId);
				// rewardDet = payRewardDedDashRepo.getRewardDetails(temp[0], temp[1], locId);

			} catch (Exception e) {

				e.printStackTrace();
			}

			comDash.setDedDet(dedDet);
			comDash.setRewardDet(rewardDet);

			// 10 getEmpAbsentLv
			List<DeptWiseWeekoffDash> list1 = new ArrayList<DeptWiseWeekoffDash>();
			try {

				// list1 = deptWiseWeekoffDashRepo.getLeavesAndAbsent(fiterdate);

			} catch (Exception e) {

				e.printStackTrace();
			}
			comDash.setDeptWiseLvAbLList(list1);

			// getEmpLastMonthAttn

			SummaryDailyAttendance summlist = new SummaryDailyAttendance();

			try {

				// summlist =
				// summaryDailyAttendanceRepository.summaryDailyAttendanceList1(temp[1],
				// temp[0], empId);

			} catch (Exception e) {

				e.printStackTrace();
			}
			comDash.setAttnLastMon(summlist);

		}

		return comDash;

	}

	/*
	 * @RequestMapping(value = { "/getBirthDayAndHolidayDash" }, method =
	 * RequestMethod.POST) public @ResponseBody BirthHoliDash
	 * getBirthDayAndHolidayDash(@RequestParam("fiterdate") String fiterdate) throws
	 * ParseException {
	 * 
	 * BirthHoliDash birthHoliDash = new BirthHoliDash(); List<HolidayMaster>
	 * holilist = new ArrayList<HolidayMaster>();
	 * 
	 * List<GetBirthDaysForDash> birthListUpcoming = new
	 * ArrayList<GetBirthDaysForDash>();
	 * 
	 * List<GetBirthDaysForDash> birthListToday = new
	 * ArrayList<GetBirthDaysForDash>();
	 * 
	 * try {
	 * 
	 * holilist = holidayMasterRepo.getHolidaysForDash(fiterdate); birthListToday =
	 * getBirthDaysForDashRepo.getTodaysBirth(fiterdate); birthListUpcoming =
	 * getBirthDaysForDashRepo.getWeekBirth(fiterdate);
	 * 
	 * birthHoliDash.setBirthListToday(birthListToday);
	 * birthHoliDash.setBirthListUpcoming(birthListUpcoming);
	 * birthHoliDash.setHoliList(holilist);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return birthHoliDash;
	 * 
	 * }
	 */
	@RequestMapping(value = { "/getUserApplicableHoliday" }, method = RequestMethod.POST)
	public @ResponseBody List<HolidayMaster> getUserApplicableHoliday(@RequestParam("empId") int empId,
			@RequestParam("date") String date) {

		List<HolidayMaster> list = new ArrayList<HolidayMaster>();

		try {

			list = holidayMasterRepo.getUserApplicableHoliday(date, empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	DashboardLeavePendingRepo dashboardLeavePendingRepo;

	@RequestMapping(value = { "/getLeaveApprovalListForDashBoard" }, method = RequestMethod.POST)
	public @ResponseBody List<DashboardLeavePending> getLeaveApprovalListForDashBoard(@RequestParam("type") int type,
			@RequestParam("locId") int locId) {

		List<DashboardLeavePending> list = new ArrayList<DashboardLeavePending>();

		try {

			if (type == 1) {
				list = dashboardLeavePendingRepo.getLeaveIntialApprovalListForDashBoard(locId);
			} else if (type == 2) {
				list = dashboardLeavePendingRepo.getLeaveFinalApprovalListForDashBoard(locId);
			} else if (type == 3) {
				list = dashboardLeavePendingRepo.getOptionalHolidatApprovalListForDashBoard(locId);
			} else if (type == 4) {
				list = dashboardLeavePendingRepo.getClaimIntialApprovalListForDashBoard(locId);
			} else if (type == 5) {
				list = dashboardLeavePendingRepo.getClaimFinalApprovalListForDashBoard(locId);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	GetNewHiresDashRepo getNewHiresDashRepo;

	/*
	 * @RequestMapping(value = { "/getNewHireDash" }, method = RequestMethod.POST)
	 * public @ResponseBody GetNewHiresDash
	 * getNewHireDash(@RequestParam("fiterdate") String fiterdate) throws
	 * ParseException {
	 * 
	 * GetNewHiresDash birthHoliDash = new GetNewHiresDash();
	 * 
	 * try {
	 * 
	 * birthHoliDash = getNewHiresDashRepo.getTodaysHire(fiterdate);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return birthHoliDash;
	 * 
	 * }
	 */

	@Autowired
	LeavePenDashRepo leavePenDashRepo;

	/*
	 * @RequestMapping(value = { "/getLeaveCountDash" }, method = RequestMethod.GET)
	 * public @ResponseBody LeavePenDash getLeaveCountDash() throws ParseException {
	 * 
	 * LeavePenDash birthHoliDash = new LeavePenDash();
	 * 
	 * try {
	 * 
	 * birthHoliDash = leavePenDashRepo.getLeaveCnt();
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return birthHoliDash;
	 * 
	 * }
	 */

	/*
	 * @RequestMapping(value = { "/getPerformanceBonus" }, method =
	 * RequestMethod.GET) public @ResponseBody LeavePenDash getPerformanceBonus()
	 * throws ParseException {
	 * 
	 * LeavePenDash birthHoliDash = new LeavePenDash();
	 * 
	 * try {
	 * 
	 * birthHoliDash = leavePenDashRepo.getLeaveCnt();
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return birthHoliDash;
	 * 
	 * }
	 */

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

	/*
	 * @RequestMapping(value = { "/getAllPendingMasterDet" }, method =
	 * RequestMethod.GET) public @ResponseBody GetAllPendingMasterDet
	 * getAllPendingMasterDet() throws ParseException {
	 * 
	 * GetAllPendingMasterDet birthHoliDash = new GetAllPendingMasterDet();
	 * 
	 * try {
	 * 
	 * birthHoliDash = getAllPendingMasterDetRepo.getDet();
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return birthHoliDash;
	 * 
	 * }
	 */

	@Autowired
	PayRewardDedDashRepo payRewardDedDashRepo;

	/*
	 * @RequestMapping(value = { "/getRewardedDet" }, method = RequestMethod.POST)
	 * public @ResponseBody PayRewardDedDash getRewardedDet(@RequestParam("type")
	 * int type,
	 * 
	 * @RequestParam("fiterdate") String fiterdate) throws ParseException {
	 * 
	 * PayRewardDedDash birthHoliDash = new PayRewardDedDash(); String temp[] =
	 * fiterdate.split("-"); try {
	 * 
	 * if (type == 1) { birthHoliDash = payRewardDedDashRepo.getDedDetails(temp[0],
	 * temp[1]);
	 * 
	 * } else { birthHoliDash = payRewardDedDashRepo.getRewardDetails(temp[0],
	 * temp[1]); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return birthHoliDash;
	 * 
	 * }
	 */

	@Autowired
	LoanAdvDashDetDashRepo loanAdvDashDetDashRepo;

	/*
	 * @RequestMapping(value = { "/getAdvLoanDash" }, method = RequestMethod.POST)
	 * public @ResponseBody LoanAdvDashDet getAdvLoanDash(@RequestParam("type") int
	 * type,
	 * 
	 * @RequestParam("fiterdate") String fiterdate) throws ParseException {
	 * 
	 * LoanAdvDashDet birthHoliDash = new LoanAdvDashDet();
	 * 
	 * String temp[] = fiterdate.split("-"); try {
	 * 
	 * if (type == 1) { birthHoliDash =
	 * loanAdvDashDetDashRepo.getAdvnceDetails(temp[0], temp[1]);
	 * 
	 * } else { birthHoliDash = loanAdvDashDetDashRepo.getLoanDetails(fiterdate); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return birthHoliDash;
	 * 
	 * }
	 */

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

	/*
	 * @RequestMapping(value = { "/getDashDeptWiseEmpCount" }, method =
	 * RequestMethod.GET) public @ResponseBody List<DeptWiseWeekoffDash>
	 * getDashDeptWiseEmpCount() throws ParseException {
	 * 
	 * List<DeptWiseWeekoffDash> list = new ArrayList<DeptWiseWeekoffDash>(); try {
	 * 
	 * list = deptWiseWeekoffDashRepo.getDeptWiseEmpDiversity();
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 */

	/*@RequestMapping(value = { "/getAgeDiversity" }, method = RequestMethod.GET)
	public @ResponseBody GetNewHiresDash getAgeDiversity() throws ParseException {

		GetNewHiresDash birthHoliDash = new GetNewHiresDash();

		try {

			birthHoliDash = getNewHiresDashRepo.getAgeDiversity();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return birthHoliDash;

	}*/

	/*@RequestMapping(value = { "/getAgeDiversityDetail" }, method = RequestMethod.POST)
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

	}*/

	@RequestMapping(value = { "/getSelfAttendaceForDashboard" }, method = RequestMethod.POST)
	public @ResponseBody List<SelfAttendanceDetail> getSelfAttendaceForDashboard(
			@RequestParam("fromDate") String fromDate, @RequestParam("empId") int empId,
			@RequestParam("toDate") String toDate) {

		List<SelfAttendanceDetail> list = new ArrayList<>();

		try {

			list = selfAttendanceDetailRepository.getSelfAttendaceForDashboard(fromDate, empId, toDate);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	// ends

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

	@Autowired
	LeaveAuthorityRepository leaveAuthorityRepository;

	@RequestMapping(value = { "/chkIsAuth" }, method = RequestMethod.POST)
	public @ResponseBody Integer chkIsAuth(@RequestParam("empId") int empId) throws ParseException {
		int n = 0;

		List<LeaveAuthority> list = new ArrayList<LeaveAuthority>();
		try {

			list = leaveAuthorityRepository.chkAuth(empId);
			n = list.size();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return n;

	}

	@Autowired
	EmpInfoForDashBoardRepository empInfoForDashBoardRepository;

	@Autowired
	EmpGraphDetailRepository empGraphDetailRepository;

	@RequestMapping(value = { "/getEmpInfoForModelGraph" }, method = RequestMethod.POST)
	public @ResponseBody EmpInfoForDashBoard getEmpInfoForModelGraph(@RequestParam("empId") int empId) {

		EmpInfoForDashBoard emp = new EmpInfoForDashBoard();
		try {

			emp = empInfoForDashBoardRepository.getEmpInfoForModelGraph(empId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return emp;

	}

	@RequestMapping(value = { "/getLateMarkGraph" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpGraphDetail> getLateMarkGraph(@RequestParam("empId") int empId) {

		List<EmpGraphDetail> emp = new ArrayList<EmpGraphDetail>();

		try {

			emp = empGraphDetailRepository.getLateMarkGraph(empId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return emp;

	}

	@RequestMapping(value = { "/getDeparmentWiseEmpCount" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpDeptWise> getDeparmentWiseEmpCount(@RequestParam("locId") int locId) {

		List<EmpDeptWise> emp = new ArrayList<EmpDeptWise>();

		try {

			emp = empDeptWiseRepository.getDeparmentWiseEmpCount(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return emp;

	}

	@Autowired
	TotalOTRepository totalOTRepository;

	@RequestMapping(value = { "/totalOtPrevioussixMonth" }, method = RequestMethod.POST)
	public @ResponseBody LineGraphData totalOtPrevioussixMonth(@RequestParam("locId") int locId,
			@RequestParam("month") int month, @RequestParam("year") int year) {

		LineGraphData lineGraphData = new LineGraphData();

		List<MonthWithOT> list = new ArrayList<>();

		try {

			YearMonth thisMonth = YearMonth.of(year, month);
			DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM-yyyy");
			SimpleDateFormat sf = new SimpleDateFormat("MM-yyyy");

			for (int i = 5; i >= 0; i--) {

				YearMonth lastMonth = thisMonth.minusMonths(i);
				MonthWithOT monthWithOT = new MonthWithOT();
				monthWithOT.setMonth(lastMonth.format(monthYearFormatter));
				list.add(monthWithOT);

			}

			String defaltdt = year + "-" + month + "-01";
			List<TotalOT> emp = totalOTRepository.totalOtPrevioussixMonth(locId, defaltdt);
			List<TotalOT> deptList = totalOTRepository.deptList(locId, defaltdt);

			for (int i = 0; i < deptList.size(); i++) {
				deptList.get(i).setId("0");
				deptList.get(i).setDateMo("-");
				deptList.get(i).setMonth("-");
				deptList.get(i).setOt(0);
			}

			SimpleDateFormat yy = new SimpleDateFormat("yyyy-MM-dd");
			for (int j = 0; j < list.size(); j++) {

				List<TotalOT> otlist = new ArrayList<>();
				int flag = 0;
				Date dt = sf.parse(list.get(j).getMonth());
				for (int i = 0; i < emp.size(); i++) {

					Date date = yy.parse(emp.get(i).getDateMo());

					if (dt.compareTo(date) == 0) {
						otlist.add(emp.get(i));
						flag = 1;
					}

				}
				if (flag == 0) {
					for (int i = 0; i < deptList.size(); i++) {

						otlist.add(deptList.get(i));
					}
				}
				list.get(j).setOtlist(otlist);
			}
			lineGraphData.setList(list);
			lineGraphData.setDeptList(deptList);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return lineGraphData;

	}

	@Autowired
	AdvaceAmtGraphRepository advaceAmtGraphRepository;

	@RequestMapping(value = { "/disbusedAmtMonthWise" }, method = RequestMethod.POST)
	public @ResponseBody List<MonthWiseDisbusedAmt> disbusedAmtMonthWise(@RequestParam("locId") int locId,
			@RequestParam("month") int month, @RequestParam("year") int year) {

		// LineGraphData lineGraphData = new LineGraphData();

		List<MonthWiseDisbusedAmt> list = new ArrayList<>();

		try {

			YearMonth thisMonth = YearMonth.of(year, month);
			DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM-yyyy");
			SimpleDateFormat sf = new SimpleDateFormat("MM-yyyy");

			for (int i = 5; i >= 0; i--) {

				YearMonth lastMonth = thisMonth.minusMonths(i);
				MonthWiseDisbusedAmt monthWithOT = new MonthWiseDisbusedAmt();
				monthWithOT.setMonth(lastMonth.format(monthYearFormatter));
				list.add(monthWithOT);

			}

			String defaltdt = year + "-" + month + "-01";
			List<AdvaceAmtGraph> advanceList = advaceAmtGraphRepository.getAdvanceMonthAmt(locId, defaltdt);
			List<AdvaceAmtGraph> loanList = advaceAmtGraphRepository.getLoanMonthAmt(locId, defaltdt);

			// System.out.println(advanceList);
			// SimpleDateFormat yy = new SimpleDateFormat("yyyy-MM-dd");
			for (int j = 0; j < list.size(); j++) {

				Date dt = sf.parse(list.get(j).getMonth());

				for (int i = 0; i < advanceList.size(); i++) {

					Date date = sf.parse(advanceList.get(i).getMonth());

					if (dt.compareTo(date) == 0) {
						list.get(j).setAdvAmt(advanceList.get(i).getAdvTot());
						break;
					}

				}

				for (int i = 0; i < loanList.size(); i++) {

					Date date = sf.parse(loanList.get(i).getMonth());

					if (dt.compareTo(date) == 0) {
						list.get(j).setLoanAmt(loanList.get(i).getAdvTot());
						break;
					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getAttendaceByMonth" }, method = RequestMethod.POST)
	public @ResponseBody SummaryDailyAttendance getAttendaceByMonth(@RequestParam("empId") int empId,
			@RequestParam("month") String month, @RequestParam("year") String year) {

		SummaryDailyAttendance summlist = new SummaryDailyAttendance();

		try {

			summlist = summaryDailyAttendanceRepository.summaryDailyAttendanceList1(month, year, empId);

			if (summlist == null) {
				summlist = new SummaryDailyAttendance();
			}
		} catch (Exception e) {
			summlist = new SummaryDailyAttendance();
			e.printStackTrace();
		}

		return summlist;

	}

	@RequestMapping(value = { "/rewardAndClaimAmtMonthWise" }, method = RequestMethod.POST)
	public @ResponseBody List<MonthWiseDisbusedAmt> rewardAndClaimAmtMonthWise(@RequestParam("locId") int locId,
			@RequestParam("month") int month, @RequestParam("year") int year) {

		// LineGraphData lineGraphData = new LineGraphData();

		List<MonthWiseDisbusedAmt> list = new ArrayList<>();

		try {

			YearMonth thisMonth = YearMonth.of(year, month);
			DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM-yyyy");
			SimpleDateFormat sf = new SimpleDateFormat("MM-yyyy");

			for (int i = 5; i >= 0; i--) {

				YearMonth lastMonth = thisMonth.minusMonths(i);
				MonthWiseDisbusedAmt monthWithOT = new MonthWiseDisbusedAmt();
				monthWithOT.setMonth(lastMonth.format(monthYearFormatter));
				list.add(monthWithOT);

			}

			String defaltdt = year + "-" + month + "-01";
			List<AdvaceAmtGraph> rewardList = advaceAmtGraphRepository.getrewardMonthAmt(locId, defaltdt);
			List<AdvaceAmtGraph> claimList = advaceAmtGraphRepository.getClaimMonthAmt(locId, defaltdt);

			// System.out.println(advanceList);
			// SimpleDateFormat yy = new SimpleDateFormat("yyyy-MM-dd");
			for (int j = 0; j < list.size(); j++) {

				Date dt = sf.parse(list.get(j).getMonth());

				for (int i = 0; i < rewardList.size(); i++) {

					Date date = sf.parse(rewardList.get(i).getMonth());

					if (dt.compareTo(date) == 0) {
						list.get(j).setAdvAmt(rewardList.get(i).getAdvTot());
						break;
					}

				}

				for (int i = 0; i < claimList.size(); i++) {

					Date date = sf.parse(claimList.get(i).getMonth());

					if (dt.compareTo(date) == 0) {
						list.get(j).setLoanAmt(claimList.get(i).getAdvTot());
						break;
					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	CountDataRepository countDataRepository;

	@Autowired
	PresentAttendaceListRepository presentAttendaceListRepository;

	@RequestMapping(value = { "/liveAttendaceDashboardData" }, method = RequestMethod.POST)
	public @ResponseBody AttendaceLiveData liveAttendaceDashboardData(@RequestParam("locId") int locId,
			@RequestParam("date") String date) {

		AttendaceLiveData attendaceLiveData = new AttendaceLiveData();

		try {
			CountData countData = countDataRepository.countData(locId, date);
			attendaceLiveData.setCountData(countData);

			List<PresentAttendaceList> presentList = presentAttendaceListRepository.presentList(locId, date);
			attendaceLiveData.setPresentList(presentList);

			List<PresentAttendaceList> lateList = presentAttendaceListRepository.lateListList(locId, date);
			attendaceLiveData.setLateList(lateList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return attendaceLiveData;

	}

}
