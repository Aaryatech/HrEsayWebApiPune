package com.ats.hrmgt.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.ShiftAssignDaily;
import com.ats.hrmgt.model.ShiftMaster;
import com.ats.hrmgt.model.SummaryDailyAttendance;
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
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.repository.ShiftMasterRepository;
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
			@RequestParam("toDate") String toDate, @RequestParam("userId") int userId) {

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

			List<EmpWithShiftDetail> empList = getshiftProject(fromDate, toDate);

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

			int update = shiftAssignDailyRepository.updateAssignShiftByDate(empIdList, fromDate,toDate, shiftId);
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

	@RequestMapping(value = { "/getshiftProject" }, method = RequestMethod.POST)
	public List<EmpWithShiftDetail> getshiftProject(@RequestParam String fromDate, @RequestParam String toDate) {

		List<EmpWithShiftDetail> empShiftList = new ArrayList<EmpWithShiftDetail>();

		try {

			List<ShiftMaster> shiftmList = shiftMasterRepository.findByStatus(1);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat ddfrmt = new SimpleDateFormat("dd-MM-yyyy");

			Date myDate = dateFormat.parse(fromDate);
			Date oneDayBefore = new Date(myDate.getTime() - 2);
			String previousDate = dateFormat.format(oneDayBefore);
			empShiftList = empWithShiftDetailRepository.updateAssignShiftByDate(previousDate, fromDate, toDate);

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

}
