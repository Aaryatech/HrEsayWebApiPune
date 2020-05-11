package com.ats.hrmgt.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.DateWiseProjection;
import com.ats.hrmgt.model.EmpInfo;
import com.ats.hrmgt.model.EmpJsonData;
import com.ats.hrmgt.model.EmpShiftAllocationDetail;
import com.ats.hrmgt.model.EmpShiftDetails;
import com.ats.hrmgt.model.EmpWithShiftDetail;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.InfoForUploadAttendance;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveStsAndLeaveId;
import com.ats.hrmgt.model.RemainingEmpForAllocation;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.ShiftAssignDaily;
import com.ats.hrmgt.model.ShiftCurrentMonth;
import com.ats.hrmgt.model.ShiftMaster;
import com.ats.hrmgt.model.SummaryDailyAttendance;
import com.ats.hrmgt.model.TempFistDayAssignList;
import com.ats.hrmgt.model.TempFistTimeAssign;
import com.ats.hrmgt.model.WeeklyOff;
import com.ats.hrmgt.model.WeeklyOffShit;
import com.ats.hrmgt.repo.ShiftAssignDailyRepository;
import com.ats.hrmgt.repository.DailyAttendanceRepository;
import com.ats.hrmgt.repository.EmpInfoRepository;
import com.ats.hrmgt.repository.EmpShiftAllocationDetailRepository;
import com.ats.hrmgt.repository.EmpShiftDetailsRepo;
import com.ats.hrmgt.repository.EmpWithShiftDetailRepository;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.HolidayRepo;
import com.ats.hrmgt.repository.InfoForUploadAttendanceRepository;
import com.ats.hrmgt.repository.LeaveApplyRepository;
import com.ats.hrmgt.repository.RemainingEmpForAllocationRepository;
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.repository.ShiftCurrentMonthRepository;
import com.ats.hrmgt.repository.ShiftMasterRepository;
import com.ats.hrmgt.repository.TempFistDayAssignListRepository;
import com.ats.hrmgt.repository.TempFistTimeAssignRepository;
import com.ats.hrmgt.repository.WeeklyOffRepo;
import com.ats.hrmgt.repository.WeeklyOffShitRepository;
import com.ats.hrmgt.service.CommonFunctionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import ch.qos.logback.classic.pattern.DateConverter;

@RestController
public class ShiftAssignApiController {

	@Autowired
	InfoForUploadAttendanceRepository infoForUploadAttendanceRepository;

	@Autowired
	EmpInfoRepository empInfoRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	ShiftAssignDailyRepository shiftAssignDailyRepository;

	@Autowired
	TempFistTimeAssignRepository tempFistTimeAssignRepository;

	@RequestMapping(value = { "/getInformationOfUploadedShift" }, method = RequestMethod.POST)
	public @ResponseBody InfoForUploadAttendance getInformationOfUploadedShift(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		InfoForUploadAttendance infoForUploadAttendance = new InfoForUploadAttendance();
		try {

			infoForUploadAttendance = infoForUploadAttendanceRepository.getInformationOfUploadedShiftAssign(fromDate,
					toDate);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return infoForUploadAttendance;

	}

	@RequestMapping(value = { "/initiallyInsertDailyShiftAssignRecord" }, method = RequestMethod.POST)
	public @ResponseBody Info initiallyInsertDailyShiftAssignRecord(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("userId") int userId,
			@RequestParam("locId") int locId) {

		Info info = new Info();
		try {

			/*
			 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); Date fmdt =
			 * df.parse(fromDate); Date todt = df.parse(toDate);
			 * 
			 * System.out.println(fmdt + " " + todt);
			 * 
			 * Calendar temp = Calendar.getInstance(); temp.setTime(fmdt); int year =
			 * temp.get(Calendar.YEAR); int month = temp.get(Calendar.MONTH) + 1;
			 * 
			 * List<EmpInfo> empList = empInfoRepository.getEmpListForAssignShift(fromDate,
			 * toDate);
			 * 
			 * String dailyDailyQuery =
			 * "INSERT INTO t_shift_assign_daily (id, emp_id, emp_code, shift_id, shift_date, month,  year, extra1, extra2, var1, var2) VALUES  "
			 * ;
			 * 
			 * for (int i = 0; i < empList.size(); i++) {
			 * 
			 * fmdt = df.parse(fromDate);
			 * 
			 * for (Date j = fmdt; j.compareTo(todt) <= 0;) {
			 * 
			 * SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd"); temp =
			 * Calendar.getInstance(); temp.setTime(j); String attdate = sf.format(j);
			 * 
			 * dailyDailyQuery = dailyDailyQuery + "('0', '" + empList.get(i).getEmpId() +
			 * "','" + empList.get(i).getEmpCode() + "','" + 0 + "','" + attdate + "','" +
			 * month + "', '" + year + "', '0', '0', NULL, NULL),";
			 * 
			 * j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
			 * 
			 * }
			 * 
			 * } dailyDailyQuery = dailyDailyQuery.substring(0, dailyDailyQuery.length() -
			 * 1);
			 */

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");

			Date fmdt = df.parse(fromDate);
			Date todt = df.parse(toDate);

			System.out.println(fmdt + " " + todt);

			Calendar temp = Calendar.getInstance();
			temp.setTime(fmdt);
			int year = temp.get(Calendar.YEAR);
			int month = temp.get(Calendar.MONTH) + 1;

			List<EmpWithShiftDetail> empList = getshiftProject(fromDate, toDate, locId);

			String dailyDailyQuery = "INSERT INTO t_shift_assign_daily (id, emp_id, emp_code, shift_id, shift_date, month,  year, extra1, extra2, var1, var2) VALUES  ";

			for (int i = 0; i < empList.size(); i++) {

				fmdt = df.parse(fromDate);

				for (int j = 0; j < empList.get(i).getDateprojectlist().size(); j++) {

					String attdate = DateConvertor.convertToYMD(empList.get(i).getDateprojectlist().get(j).getDate());

					dailyDailyQuery = dailyDailyQuery + "('0', '" + empList.get(i).getEmpId() + "','"
							+ empList.get(i).getEmpCode() + "','"
							+ empList.get(i).getDateprojectlist().get(j).getShiftId() + "','" + attdate + "','" + month
							+ "', '" + year + "', '0', '0','"
							+ empList.get(i).getDateprojectlist().get(j).getShiftName() + "', NULL),";

				}

			}
			dailyDailyQuery = dailyDailyQuery.substring(0, dailyDailyQuery.length() - 1);

			jdbcTemplate.batchUpdate(dailyDailyQuery);

			info.setError(false);
			info.setMsg("success");
		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/updateAssignShiftByDate" }, method = RequestMethod.POST)
	public @ResponseBody Info updateAssignShiftByDate(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("shiftId") int shiftId) {

		Info info = new Info();
		try {

			int update = shiftAssignDailyRepository.updateAssignShiftByDate(empIdList, fromDate, toDate, shiftId);
			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;

	}

	@Autowired
	WeeklyOffRepo weeklyOffRepo;

	@Autowired
	LeaveApplyRepository leaveApplyRepository;

	@Autowired
	ShiftMasterRepository shiftMasterRepository;

	@Autowired
	EmployeeMasterRepository empRepo;

	@Autowired
	SettingRepo settingRepo;

	@Autowired
	DailyAttendanceRepository dailyAttendanceRepository;

	@Autowired
	HolidayRepo holidayRepo;

	@Autowired
	WeeklyOffShitRepository weeklyOffShitRepository;
	@Autowired
	EmpShiftDetailsRepo empShiftDetailsRepo;

	@Autowired
	CommonFunctionService commonFunctionService;

	@Autowired
	EmpWithShiftDetailRepository empWithShiftDetailRepository;

	@Autowired
	TempFistDayAssignListRepository tempFistDayAssignListRepository;

	@Autowired
	ShiftCurrentMonthRepository shiftCurrentMonthRepository;

	@Autowired
	RemainingEmpForAllocationRepository remainingEmpForAllocationRepository;

	@RequestMapping(value = { "/getshiftProject" }, method = RequestMethod.POST)
	public List<EmpWithShiftDetail> getshiftProject(@RequestParam String fromDate, @RequestParam String toDate,
			int locId) {

		List<EmpWithShiftDetail> empShiftList = new ArrayList<EmpWithShiftDetail>();

		try {

			List<ShiftMaster> shiftmList = shiftMasterRepository.findByStatus(1);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat ddfrmt = new SimpleDateFormat("dd-MM-yyyy");

			Date myDate = dateFormat.parse(fromDate);
			Date oneDayBefore = new Date(myDate.getTime() - 2);
			String previousDate = dateFormat.format(oneDayBefore);
			empShiftList = empWithShiftDetailRepository.updateAssignShiftByDate(previousDate, fromDate, toDate, locId);

			List<WeeklyOff> weeklyOfflist = weeklyOffRepo.getWeeklyOffList();
			/*
			 * List<LeaveApply> leavetList = leaveApplyRepository.getleavetList(fromDate,
			 * toDate); List<Holiday> holidayList =
			 * holidayRepo.getholidaybetweendate(fromDate, toDate);
			 */
			List<WeeklyOffShit> weeklyOffShitList = weeklyOffShitRepository.getWeeklyOffShitList(fromDate, toDate);
			List<WeeklyOffShit> weeklyOffShitFromList = weeklyOffShitRepository.weeklyOffShitFromList(fromDate, toDate);

			Date todt = dateFormat.parse(toDate);

			for (int i = 0; i < empShiftList.size(); i++) {

				List<DateWiseProjection> dateProjectlist = new ArrayList<>();
				Date fmdt = dateFormat.parse(fromDate);

				int findweklyoff = 0;
				int shiftId = empShiftList.get(i).getShiftId();

				for (Date j = fmdt; j.compareTo(todt) <= 0;) {

					DateWiseProjection dateWiseProjection = new DateWiseProjection();

					String dt = dateFormat.format(j);

					int weekEndStatus = commonFunctionService.findDateInWeekEnd(dt, dt, weeklyOfflist,
							weeklyOffShitList, empShiftList.get(i).getLocationId(),
							empShiftList.get(i).getWeekendCategory(), empShiftList.get(i).getEmpId(),
							weeklyOffShitFromList);
					/*
					 * int holidayStatus = commonFunctionService.findDateInHoliday(dt, dt,
					 * holidayList, empShiftList.get(i).getLocationId(),
					 * empShiftList.get(i).getHolidayCategory());
					 */

					if (weekEndStatus == 1) {

						/*
						 * dateWiseProjection.setShiftName("Weeklyoff");
						 * dateWiseProjection.setShiftId(0);
						 */
						findweklyoff = 1;

						for (int m = 0; m < shiftmList.size(); m++) {
							if (shiftmList.get(m).getId() == shiftId) {
								dateWiseProjection.setShiftName(shiftmList.get(m).getShiftname());
								dateWiseProjection.setShiftId(shiftId);
								break;
							}
						}

					} /*
						 * else if (holidayStatus == 3) {
						 * 
						 * 
						 * dateWiseProjection.setShiftName("Holiday"); dateWiseProjection.setShiftId(0);
						 * 
						 * for (int m = 0; m < shiftmList.size(); m++) { if (shiftmList.get(m).getId()
						 * == shiftId) {
						 * dateWiseProjection.setShiftName(shiftmList.get(m).getShiftname());
						 * dateWiseProjection.setShiftId(shiftId); break; } } }
						 */ else {

						if (findweklyoff == 1 && empShiftList.get(i).getGroupType() == 0) {

							ShiftMaster shiftm = new ShiftMaster();
							for (int s = 0; s < shiftmList.size(); s++) {
								if (shiftmList.get(s).getId() == shiftId) {
									dateWiseProjection.setShiftName(shiftmList.get(s).getShiftname());
									dateWiseProjection.setShiftId(shiftId);
									shiftm = shiftmList.get(s);
									break;
								}
							}
							if (shiftm.getChangeable() == 1) {

								shiftId = shiftm.getChangewith();
								for (int s = 0; s < shiftmList.size(); s++) {
									if (shiftmList.get(s).getId() == shiftId) {
										dateWiseProjection.setShiftName(shiftmList.get(s).getShiftname());
										dateWiseProjection.setShiftId(shiftId);
										break;
									}
								}

							}
							findweklyoff = 0;
						} else {

							for (int m = 0; m < shiftmList.size(); m++) {
								if (shiftmList.get(m).getId() == shiftId) {
									dateWiseProjection.setShiftName(shiftmList.get(m).getShiftname());
									dateWiseProjection.setShiftId(shiftId);
									break;
								}
							}
						}

					}
					dateWiseProjection.setDate(ddfrmt.format(j));
					dateProjectlist.add(dateWiseProjection);

					j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
				}

				empShiftList.get(i).setDateprojectlist(dateProjectlist);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return empShiftList;

	}

	@Autowired
	EmpShiftAllocationDetailRepository empShiftAllocationDetailRepository;

	@RequestMapping(value = { "/getEmpProjectionMatrix" }, method = RequestMethod.POST)
	public List<EmpWithShiftDetail> getEmpProjectionMatrix(@RequestParam String fromDate, @RequestParam String toDate,
			@RequestParam int locId) {

		List<EmpWithShiftDetail> empShiftList = new ArrayList<EmpWithShiftDetail>();

		try {

			// List<ShiftMaster> shiftmList = shiftMasterRepository.findByStatus(1);

			/*
			 * DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); DateFormat ddfrmt
			 * = new SimpleDateFormat("dd-MM-yyyy");
			 */

			// Date myDate = dateFormat.parse(fromDate);
			// Date oneDayBefore = new Date(myDate.getTime() - 2);
			// String previousDate = dateFormat.format(oneDayBefore);
			empShiftList = empWithShiftDetailRepository.getEmpListAll(locId);
			List<EmpShiftAllocationDetail> shiftallocationDetailList = empShiftAllocationDetailRepository
					.getEmpShiftAllocationDetail(fromDate, toDate);

			List<WeeklyOff> weeklyOfflist = weeklyOffRepo.getWeeklyOffList();
			List<LeaveApply> leavetList = leaveApplyRepository.getleavetList(fromDate, toDate);
			List<Holiday> holidayList = holidayRepo.getholidaybetweendate(fromDate, toDate);
			List<WeeklyOffShit> weeklyOffShitList = weeklyOffShitRepository.getWeeklyOffShitList(fromDate, toDate);
			List<WeeklyOffShit> weeklyOffShitFromList = weeklyOffShitRepository.weeklyOffShitFromList(fromDate, toDate);

			// Date todt = dateFormat.parse(toDate);

			for (int i = 0; i < empShiftList.size(); i++) {

				List<EmpShiftAllocationDetail> shiftlist = new ArrayList<>();

				for (int j = 0; j < shiftallocationDetailList.size(); j++) {

					String dt = shiftallocationDetailList.get(j).getShiftDate();

					if (shiftallocationDetailList.get(j).getEmpId() == empShiftList.get(i).getEmpId()) {
						int weekEndStatus = commonFunctionService.findDateInWeekEnd(dt, dt, weeklyOfflist,
								weeklyOffShitList, empShiftList.get(i).getLocationId(),
								empShiftList.get(i).getWeekendCategory(), empShiftList.get(i).getEmpId(),
								weeklyOffShitFromList);
						int holidayStatus = commonFunctionService.findDateInHoliday(dt, dt, holidayList,
								empShiftList.get(i).getLocationId(), empShiftList.get(i).getHolidayCategory());
						LeaveStsAndLeaveId stsInfo = commonFunctionService.findDateInLeave(dt, leavetList,
								empShiftList.get(i).getEmpId());
						if (stsInfo.getSts() == 5) {
							shiftallocationDetailList.get(j).setExtra("PL");
							shiftallocationDetailList.get(j).setExtraType(3);
						} else if (weekEndStatus == 1) {

							shiftallocationDetailList.get(j).setExtra("WO");
							shiftallocationDetailList.get(j).setExtraType(2);

						} else if (holidayStatus == 3) {

							shiftallocationDetailList.get(j).setExtra("HO");
							shiftallocationDetailList.get(j).setExtraType(1);

						} else {
							shiftallocationDetailList.get(j).setExtra("-");
							shiftallocationDetailList.get(j).setExtraType(0);
						}

						shiftlist.add(shiftallocationDetailList.get(j));
					}

				}

				empShiftList.get(i).setShiftallocationDetailList(shiftlist);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return empShiftList;

	}

	@RequestMapping(value = { "/getFistDayAssignShiftFromTemp" }, method = RequestMethod.POST)
	public List<TempFistDayAssignList> getFistDayAssignShiftFromTemp(@RequestParam String date,
			@RequestParam int locId) {

		List<TempFistDayAssignList> empShiftList = new ArrayList<TempFistDayAssignList>();

		try {

			Info info = initiallyInsertTempAssign(date, locId);
			empShiftList = tempFistDayAssignListRepository.getFistDayAssignShiftFromTemp(date, locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return empShiftList;

	}

	@RequestMapping(value = { "/initiallyInsertTempAssign" }, method = RequestMethod.POST)
	public @ResponseBody Info initiallyInsertTempAssign(@RequestParam("fromDate") String fromDate,
			@RequestParam int locId) {

		Info info = new Info();
		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date myDate = dateFormat.parse(fromDate);
			Date oneDayBefore = new Date(myDate.getTime() - 2);
			String previousDate = dateFormat.format(oneDayBefore);

			List<EmpWithShiftDetail> empShiftList = empWithShiftDetailRepository.getempList(previousDate, fromDate,
					locId);

			if (empShiftList.size() > 0) {

				String dailyDailyQuery = "INSERT INTO t_temp_assign_first_day_shift (id, emp_id, date, extra1, extra2, shift_id) VALUES  ";

				for (int i = 0; i < empShiftList.size(); i++) {

					dailyDailyQuery = dailyDailyQuery + "('0', '" + empShiftList.get(i).getEmpId() + "','" + fromDate
							+ "','" + locId + "','" + 0 + "','" + empShiftList.get(i).getShiftId() + "'),";

				}
				dailyDailyQuery = dailyDailyQuery.substring(0, dailyDailyQuery.length() - 1);

				jdbcTemplate.batchUpdate(dailyDailyQuery);
			}
			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getDateFromIsCurrentMonth" }, method = RequestMethod.POST)
	public @ResponseBody ShiftCurrentMonth getDateFromIsCurrentMonth(@RequestParam int locId,
			@RequestParam int userId) {

		ShiftCurrentMonth shiftCurrentMonth = new ShiftCurrentMonth();
		try {

			shiftCurrentMonth = shiftCurrentMonthRepository.findByIsCurrentAndLocId(1, locId);

			if (shiftCurrentMonth == null) {

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dt = new Date();
				Calendar temp = Calendar.getInstance();
				temp.setTime(dt);
				int year = temp.get(Calendar.YEAR);
				int month1 = temp.get(Calendar.MONTH) + 1;

				shiftCurrentMonth = new ShiftCurrentMonth();
				shiftCurrentMonth.setCompanyId(1);
				shiftCurrentMonth.setDate(year + "-" + month1 + "-" + "01");
				shiftCurrentMonth.setIsCurrent(1);
				shiftCurrentMonth.setLocId(locId);
				shiftCurrentMonth.setLastUpdatedBy(userId);
				shiftCurrentMonth.setLastUpdateDatetime(df.format(dt));
				shiftCurrentMonthRepository.save(shiftCurrentMonth);
				shiftCurrentMonth = shiftCurrentMonthRepository.findByIsCurrentAndLocId(1, locId);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return shiftCurrentMonth;

	}

	@RequestMapping(value = { "/updateShiftIdInTempAllocation" }, method = RequestMethod.POST)
	public Info updateShiftIdInTempAllocation(@RequestParam int id, @RequestParam int shiftId) {

		Info info = new Info();

		try {

			int update = tempFistTimeAssignRepository.updateshift(id, shiftId);

			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/autoshiftAllocation" }, method = RequestMethod.POST)
	public @ResponseBody Info autoshiftAllocation(@RequestParam("locId") int locId,
			@RequestParam("userId") int userId) {

		Info info = new Info();
		try {

			ShiftCurrentMonth shiftCurrentMonth = shiftCurrentMonthRepository.findByIsCurrentAndLocId(1, locId);

			List<TempFistDayAssignList> empShiftList = new ArrayList<TempFistDayAssignList>();

			empShiftList = tempFistDayAssignListRepository.getFistDayAssignShiftFromTemp(shiftCurrentMonth.getDate(),
					locId);

			String dailyDailyQuery = "INSERT INTO t_shift_assign_daily (id, emp_id, emp_code, shift_id, shift_date, month,  year, extra1, extra2, var1, var2) VALUES  ";

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			Date dt = df.parse(shiftCurrentMonth.getDate());
			Calendar temp = Calendar.getInstance();
			temp.setTime(dt);
			int year = temp.get(Calendar.YEAR);
			int month1 = temp.get(Calendar.MONTH) + 1;

			if (empShiftList.size() > 0) {

				for (int i = 0; i < empShiftList.size(); i++) {

					dailyDailyQuery = dailyDailyQuery + "('0', '" + empShiftList.get(i).getEmpId() + "','"
							+ empShiftList.get(i).getEmpCode() + "','" + empShiftList.get(i).getShiftId() + "','"
							+ shiftCurrentMonth.getDate() + "','" + month1 + "', '" + year + "', '0', '0','"
							+ empShiftList.get(i).getShiftName() + "', NULL),";

				}

				dailyDailyQuery = dailyDailyQuery.substring(0, dailyDailyQuery.length() - 1);
				jdbcTemplate.batchUpdate(dailyDailyQuery);

				Date firstDay = new GregorianCalendar(year, month1 - 1, 2).getTime();
				Date lastDay = new GregorianCalendar(year, month1, 0).getTime();

				Info info1 = initiallyInsertDailyShiftAssignRecord(df.format(firstDay), df.format(lastDay), userId,
						locId);

				if (info1.isError() == false) {

					int update = shiftCurrentMonthRepository.updateIsCurrent(shiftCurrentMonth.getId());
					// tempFistTimeAssignRepository.deleterecord(locId,
					// df.parse(shiftCurrentMonth.getDate()));

					df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					dt = lastDay;
					Calendar c = Calendar.getInstance();
					c.setTime(dt);
					c.add(Calendar.DATE, 1);
					dt = c.getTime();
					temp = Calendar.getInstance();
					temp.setTime(dt);
					year = temp.get(Calendar.YEAR);
					month1 = temp.get(Calendar.MONTH) + 1;

					Date newdat = new Date();

					shiftCurrentMonth = new ShiftCurrentMonth();
					shiftCurrentMonth.setCompanyId(1);
					shiftCurrentMonth.setDate(year + "-" + month1 + "-" + "01");
					shiftCurrentMonth.setIsCurrent(1);
					shiftCurrentMonth.setLocId(locId);
					shiftCurrentMonth.setLastUpdatedBy(userId);
					shiftCurrentMonth.setLastUpdateDatetime(df.format(newdat));
					shiftCurrentMonthRepository.save(shiftCurrentMonth);
				}
			}

			info.setError(false);
			info.setMsg("success");
		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/checkRemainingEmployeeForProjection" }, method = RequestMethod.POST)
	public Info checkRemainingEmployeeForProjection(@RequestParam String fromDate, @RequestParam String toDate,
			@RequestParam int locId, @RequestParam int userId) {

		Info info = new Info();

		try {

			/*
			 * DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 * 
			 * Date myDate = dateFormat.parse(fromDate); Date oneDayBefore = new
			 * Date(myDate.getTime() - 2); String previousDate =
			 * dateFormat.format(oneDayBefore); List<EmpWithShiftDetail> empShiftList =
			 * empWithShiftDetailRepository.updateAssignShiftByDate(previousDate, fromDate,
			 * toDate, locId);
			 */

			/*
			 * if (empShiftList.size() > 0) { info =
			 * initiallyInsertDailyShiftAssignRecord(fromDate, toDate, userId, locId); }
			 */

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			/*
			 * Date fmdt = dateFormat.parse(toDate); Date todt = dateFormat.parse(toDate);
			 */
			List<RemainingEmpForAllocation> empShiftList = remainingEmpForAllocationRepository
					.checkRemainingEmployeeForProjection(locId);

			String dailyDailyQuery = "INSERT INTO t_shift_assign_daily (id, emp_id, emp_code, shift_id, shift_date, month,  year, extra1, extra2, var1, var2) VALUES  ";

			int flag = 0;

			for (int i = 0; i < empShiftList.size(); i++) {

				Date fmdt = dateFormat.parse(fromDate);
				Date todt = dateFormat.parse(toDate);

				if (empShiftList.get(i).getMaxDate().equals("0")) {
					flag = 1;

					for (Date j = fmdt; j.compareTo(todt) <= 0;) {

						Calendar temp = Calendar.getInstance();
						temp.setTime(j);
						int year = temp.get(Calendar.YEAR);
						int month = temp.get(Calendar.MONTH) + 1;

						String attdate = dateFormat.format(j);

						dailyDailyQuery = dailyDailyQuery + "('0', '" + empShiftList.get(i).getEmpId() + "','"
								+ empShiftList.get(i).getEmpCode() + "','" + empShiftList.get(i).getShiftId() + "','"
								+ attdate + "','" + month + "', '" + year + "', '0', '0',NULL, NULL),";
						j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
					}
				} else {

					Date maxDate = dateFormat.parse(empShiftList.get(i).getMaxDate());
					
					if (maxDate.compareTo(todt) < 0) {
						
						flag = 1;
						
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(maxDate);
						calendar.add(Calendar.DAY_OF_YEAR, 1); 
						String stdt = dateFormat.format(calendar.getTime());  
						fmdt = dateFormat.parse(stdt);
						
						for (Date j = fmdt; j.compareTo(todt) <= 0;) {

							Calendar temp = Calendar.getInstance();
							temp.setTime(j);
							int year = temp.get(Calendar.YEAR);
							int month = temp.get(Calendar.MONTH) + 1;

							String attdate = dateFormat.format(j);

							dailyDailyQuery = dailyDailyQuery + "('0', '" + empShiftList.get(i).getEmpId() + "','"
									+ empShiftList.get(i).getEmpCode() + "','" + empShiftList.get(i).getShiftId() + "','"
									+ attdate + "','" + month + "', '" + year + "', '0', '0',NULL, NULL),";
							j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
						}
						
					}
				}

			}

			if(flag==1) {
				
				dailyDailyQuery = dailyDailyQuery.substring(0, dailyDailyQuery.length() - 1); 
				jdbcTemplate.batchUpdate(dailyDailyQuery);
			}
			

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}

}
