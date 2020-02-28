package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model.AttendanceSheetData;
import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.DailyDailyInfomationForChart;
import com.ats.hrmgt.model.DailyDailyInformation;
import com.ats.hrmgt.model.DataForUpdateAttendance;
import com.ats.hrmgt.model.EmpInfo;
import com.ats.hrmgt.model.EmpInfoWithDateInfoList;
import com.ats.hrmgt.model.EmpJsonData;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.FileUploadedData;
import com.ats.hrmgt.model.GetDailyDailyRecord;
import com.ats.hrmgt.model.GetDailyDailyRecordRepository;
import com.ats.hrmgt.model.GetWeeklyOff;
import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.InfoForUploadAttendance;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveStsAndLeaveId;
import com.ats.hrmgt.model.LeaveTrail;
import com.ats.hrmgt.model.LoginResponse;
import com.ats.hrmgt.model.LvType;
import com.ats.hrmgt.model.LvmSumUp;
import com.ats.hrmgt.model.MstEmpType;
import com.ats.hrmgt.model.ShiftMaster;
import com.ats.hrmgt.model.SummaryAttendance;
import com.ats.hrmgt.model.SummaryDailyAttendance;
import com.ats.hrmgt.model.VariousList;
import com.ats.hrmgt.model.WeeklyOff;
import com.ats.hrmgt.model.WeeklyOffShit;
import com.ats.hrmgt.repository.AccessRightModuleRepository;
import com.ats.hrmgt.repository.DailyAttendanceRepository;
import com.ats.hrmgt.repository.DailyDailyInformationRepository;
import com.ats.hrmgt.repository.EmpInfoRepository;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.GetWeeklyOffRepo;
import com.ats.hrmgt.repository.HolidayRepo;
import com.ats.hrmgt.repository.InfoForUploadAttendanceRepository;
import com.ats.hrmgt.repository.LeaveApplyRepository;
import com.ats.hrmgt.repository.LeaveTrailRepository;
import com.ats.hrmgt.repository.LvTypeRepository;
import com.ats.hrmgt.repository.LvmSumUpRepository;
import com.ats.hrmgt.repository.MstEmpTypeRepository;
import com.ats.hrmgt.repository.ShiftMasterRepository;
import com.ats.hrmgt.repository.SummaryAttendanceRepository;
import com.ats.hrmgt.repository.SummaryDailyAttendanceRepository;
import com.ats.hrmgt.repository.WeeklyOffRepo;
import com.ats.hrmgt.repository.WeeklyOffShitRepository;
import com.ats.hrmgt.service.CommonFunctionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class AttendanceApiController {

	@Autowired
	AccessRightModuleRepository accessRightModuleRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	DailyAttendanceRepository dailyAttendanceRepository;

	@Autowired
	SummaryDailyAttendanceRepository summaryDailyAttendanceRepository;

	@Autowired
	InfoForUploadAttendanceRepository infoForUploadAttendanceRepository;

	@Autowired
	EmpInfoRepository empInfoRepository;

	@Autowired
	HolidayRepo holidayRepo;

	@Autowired
	ShiftMasterRepository shiftMasterRepository;

	@Autowired
	WeeklyOffShitRepository weeklyOffShitRepository;

	@Autowired
	LvTypeRepository lvTypeRepository;

	@Autowired
	LvmSumUpRepository lvmSumUpRepository;

	@Autowired
	MstEmpTypeRepository mstEmpTypeRepository;

	@Autowired
	CommonFunctionService commonFunctionService;

	@Autowired
	WeeklyOffRepo weeklyOffRepo;

	@Autowired
	LeaveApplyRepository leaveApplyRepository;

	@Autowired
	LeaveTrailRepository leaveTrailRepository;

	@Autowired
	DailyDailyInformationRepository dailyDailyInformationRepository;

	@Autowired
	SummaryAttendanceRepository summaryAttendanceRepository;

	@Autowired
	GetDailyDailyRecordRepository getDailyDailyRecordRepository;

	int PL_CL_HD_leave_insert_automatic = 0;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = { "/initiallyInsertDailyRecord" }, method = RequestMethod.POST)
	public @ResponseBody Info initiallyInsertDailyRecord(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("userId") int userId) {

		Info info = new Info();
		try {

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date fmdt = df.parse(fromDate);
			Date todt = df.parse(toDate);

			System.out.println(fmdt + " " + todt);

			Calendar temp = Calendar.getInstance();
			temp.setTime(fmdt);
			int year = temp.get(Calendar.YEAR);
			int month = temp.get(Calendar.MONTH) + 1;

			List<EmpInfo> empList = empInfoRepository.getEmpList(fromDate, toDate);

			List<SummaryDailyAttendance> summaryDailyAttendanceList = new ArrayList<>();

			// SummaryDailyAttendance summaryDailyAttendance = new SummaryDailyAttendance();
			List<DailyAttendance> dailyAttendanceList = new ArrayList<>();

			String dailyDailyQuery = "INSERT INTO tbl_attt_daily_daily (id, company_id, emp_code, emp_name, att_date, att_status,  lv_sumup_id, working_hrs, in_time, rec_status, login_name, login_time, import_date, cmp_code, emp_id, ot_hr,  current_shiftid, late_mark, late_min, reason, current_shiftname, freeze_by_supervisor, comments_supervisor, get_pass_used_count, get_pass_used_hour, get_pass_used_hour_reason, raw_data_inout, manual_ot_hr, full_night, half_night, out_time, early_going_mark, early_going_min, multiple_entries, casetype, is_fixed, by_file_updated, location_id, emp_type, emp_json, atsumm_uid, file_name, row_id) VALUES  ";
			String dailyDailySummryQuery = "INSERT INTO tbl_attt_summary_daily (id, company_id, emp_id, emp_code, emp_name, month, year, working_days, present_days, weekly_off, paid_holiday, paid_leave, legal_strike, lay_off, unpaid_holiday, unpaid_leave, absent_days, payable_days, ncp_days, totlate_mins, totlate_days, totout_mins, totworking_hrs, totot_hrs, tot_othr, tot_late, rec_status, login_name, login_time, status, import_date, cmp_code, rec_status_paid, total_days_inmonth, late_ded_leave_paid, holiday_present, weekly_off_present, full_night, half_night, holiday_present_half, weekly_off_present_half, weekly_off_holiday_off, weekly_off_holiday_off_present, weekly_off_holiday_off_present_halfday, hdpresent_hdleave, tot_early_going, atsumm_uid, calculation_done) VALUES ";

			for (int i = 0; i < empList.size(); i++) {

				String empName = empList.get(i).getFirstName() + " " + empList.get(i).getSurname();
				/*
				 * summaryDailyAttendance = new SummaryDailyAttendance();
				 * summaryDailyAttendance.setEmpCode(empList.get(i).getEmpCode());
				 * summaryDailyAttendance.setEmpId(empList.get(i).getEmpId());
				 * summaryDailyAttendance.setEmpName(empList.get(i).getFirstName() + " " +
				 * empList.get(i).getSurname()); summaryDailyAttendance.setMonth(month);
				 * summaryDailyAttendance.setYear(year); summaryDailyAttendance.setCompanyId(1);
				 * summaryDailyAttendance.setLoginName(String.valueOf(userId));
				 * summaryDailyAttendance.setRecStatus("O");
				 * summaryDailyAttendanceList.add(summaryDailyAttendance);
				 */

				dailyDailySummryQuery = dailyDailySummryQuery + "('0', '1', '" + empList.get(i).getEmpId() + "', '"
						+ empList.get(i).getEmpCode() + "', '" + empName + "', '" + month + "', '" + year
						+ "', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'O', '"
						+ userId
						+ "', NULL, '0', NULL, NULL, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', NULL, '0'),";

				EmpJsonData empJsonData = new EmpJsonData();
				empJsonData.setCmpCode(empList.get(i).getCmpCode());
				empJsonData.setCmpJoiningDate(empList.get(i).getCmpJoiningDate());
				empJsonData.setCurrentShiftid(empList.get(i).getCurrentShiftid());
				empJsonData.setDepartId(empList.get(i).getDepartId());
				empJsonData.setDesignationId(empList.get(i).getDepartId());
				empJsonData.setEmpCategory(empList.get(i).getEmpCategory());
				empJsonData.setEmpCode(empList.get(i).getEmpCode());
				empJsonData.setEmpId(empList.get(i).getEmpId());
				empJsonData.setEmpType(empList.get(i).getEmpType());
				empJsonData.setFirstName(empList.get(i).getFirstName());
				empJsonData.setIsEmp(empList.get(i).getIsEmp());
				empJsonData.setLocationId(empList.get(i).getLocationId());
				empJsonData.setMiddleName(empList.get(i).getMiddleName());
				empJsonData.setSalaryTypeId(empList.get(i).getSalaryTypeId());
				empJsonData.setSalBasis(empList.get(i).getSalBasis());
				empJsonData.setSurname(empList.get(i).getSurname());
				empJsonData.setHolidayCatId(empList.get(i).getHolidayCategory());
				empJsonData.setWeekEndCatId(empList.get(i).getWeekendCategory());
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(empJsonData);

				fmdt = df.parse(fromDate);
				// System.out.println(empList.get(i).getEmpId() + " - " + fmdt);

				for (Date j = fmdt; j.compareTo(todt) <= 0;) {

					// System.out.println(j);

					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					temp = Calendar.getInstance();
					temp.setTime(j);
					String attdate = sf.format(j);
					/*
					 * DailyAttendance dailyAttendance = new DailyAttendance();
					 * dailyAttendance.setEmpCode(summaryDailyAttendance.getEmpCode());
					 * dailyAttendance.setEmpId(summaryDailyAttendance.getEmpId());
					 * dailyAttendance.setCompanyId(1);
					 * dailyAttendance.setLocationId(empList.get(i).getLocationId());
					 * dailyAttendance.setAttDate(attdate);
					 * dailyAttendance.setEmpName(empList.get(i).getFirstName() + " " +
					 * empList.get(i).getSurname());
					 * dailyAttendance.setLoginName(String.valueOf(userId));
					 * dailyAttendance.setEmpJson(json); dailyAttendance.setRecStatus("O");
					 * dailyAttendance.setAttStatus("NA"); dailyAttendance.setLateMark("0");
					 * dailyAttendanceList.add(dailyAttendance);
					 */

					dailyDailyQuery = dailyDailyQuery + "('0', '1','" + empList.get(i).getEmpCode() + "','" + empName
							+ "','" + attdate + "', 'NA', '0', '0', NULL, 'O', \n '" + userId + "', NULL, NULL, NULL, '"
							+ empList.get(i).getEmpId() + "', NULL, '0', '0', '0', NULL, NULL, '0', NULL, '0', '0' \n "
							+ ", NULL, NULL, '0', '0', '0', NULL, '0', '0.00', NULL, NULL, '0', '0', '"
							+ empList.get(i).getLocationId() + "', '0', \n '" + json + "', NULL, NULL, '0'),";

					j.setTime(j.getTime() + 1000 * 60 * 60 * 24);

				}

			}
			dailyDailyQuery = dailyDailyQuery.substring(0, dailyDailyQuery.length() - 1);
			dailyDailySummryQuery = dailyDailySummryQuery.substring(0, dailyDailySummryQuery.length() - 1);

			/*
			 * Calendar cal1 = Calendar.getInstance(); SimpleDateFormat date_format = new
			 * SimpleDateFormat("HH:mm:ss");
			 * System.out.println(date_format.format(cal1.getTime()));
			 */
			jdbcTemplate.batchUpdate(dailyDailyQuery);
			jdbcTemplate.batchUpdate(dailyDailySummryQuery);

			// query=query.substring(0, query.length()-1);
			// dailyAttendanceRepository.insert(query);
			// System.out.println(query);

			// List<DailyAttendance> dailyAttendanceSaveRes =
			// dailyAttendanceRepository.saveAll(dailyAttendanceList);
			/*
			 * cal1 = Calendar.getInstance();
			 * System.out.println(date_format.format(cal1.getTime()));
			 */
			List<SummaryDailyAttendance> summaryDailyAttendanceSaveRes = summaryDailyAttendanceRepository
					.saveAll(summaryDailyAttendanceList);
			info.setError(false);
			info.setMsg("success");
		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getInformationOfUploadedAttendance" }, method = RequestMethod.POST)
	public @ResponseBody InfoForUploadAttendance getInformationOfUploadedAttendance(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		InfoForUploadAttendance infoForUploadAttendance = new InfoForUploadAttendance();
		try {

			infoForUploadAttendance = infoForUploadAttendanceRepository.getInformationOfUploadedAttendance(fromDate,
					toDate);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return infoForUploadAttendance;

	}

	@RequestMapping(value = { "/importAttendanceByFileAndUpdate" }, method = RequestMethod.POST)
	public @ResponseBody Info getVariousListForUploadAttendace(
			@RequestBody DataForUpdateAttendance dataForUpdateAttendance) {

		Info info = new Info();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");

		SimpleDateFormat yyDtTm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {

			String fromDate = dataForUpdateAttendance.getFromDate();
			String toDate = dataForUpdateAttendance.getToDate();
			int month = dataForUpdateAttendance.getMonth();
			int year = dataForUpdateAttendance.getYear();

			List<LeaveApply> leaveListAddeBySystem = new ArrayList<>();
			List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
			List<LeaveApply> leavetList = new ArrayList<>();

			if (dataForUpdateAttendance.getEmpId() != 0) {

				leaveListAddeBySystem = leaveApplyRepository.leaveListAddeBySystem(fromDate, toDate,
						dataForUpdateAttendance.getEmpId());
				dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceList(fromDate, toDate,
						dataForUpdateAttendance.getEmpId());
				leavetList = leaveApplyRepository.getleavetList(fromDate, toDate, dataForUpdateAttendance.getEmpId());

			} else {
				leaveListAddeBySystem = leaveApplyRepository.leaveListAddeBySystem(fromDate, toDate);
				dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceList(fromDate, toDate);
				leavetList = leaveApplyRepository.getleavetList(fromDate, toDate);
			}

			for (int j = 0; j < leaveListAddeBySystem.size(); j++) {

				float updateNoOfDays = 0;

				if (Integer.parseInt(leaveListAddeBySystem.get(j).getLeaveDuration()) != 1) {
					// cancel halfDay leave
					updateNoOfDays = (float) 0.5;

				} else {
					// cancel full leave
					updateNoOfDays = 1;
				}

				int updateNoOfDaysInLeave = leaveApplyRepository.reverseupdateNoOfDaysInLeave(
						leaveListAddeBySystem.get(j).getLvtApplicationIdParent(), updateNoOfDays, "");
				int deleteRocord = leaveApplyRepository.deleteByLeaveId(leaveListAddeBySystem.get(j).getLeaveId());
				int deleteRocordTrail = leaveTrailRepository.deleteByLeaveId(leaveListAddeBySystem.get(j).getLeaveId());
			}

			List<FileUploadedData> fileUploadedDataList = dataForUpdateAttendance.getFileUploadedDataList();
			List<MstEmpType> mstEmpTypeList = mstEmpTypeRepository.findAll();
			List<ShiftMaster> shiftList = shiftMasterRepository.findAll();
			List<Holiday> holidayList = holidayRepo.getholidaybetweendate(fromDate, toDate);
			List<WeeklyOff> weeklyOfflist = weeklyOffRepo.getWeeklyOffList();
			List<WeeklyOffShit> weeklyOffShitList = weeklyOffShitRepository.getWeeklyOffShitList(fromDate, toDate);
			List<LvType> lvTypeList = lvTypeRepository.findAll();

			// System.out.println(fileUploadedDataList);
			/*
			 * List<SummaryDailyAttendance> summaryDailyAttendanceList =
			 * summaryDailyAttendanceRepository .summaryDailyAttendanceList(month, year);
			 */

			//

			// List<MstWeeklyOff> mstWeeklyOffList = mstWeeklyOffRepository.findAll();
			/*
			 * List<LvType> lvTypeList = lvTypeRepository.findAll(); List<Holiday>
			 * 
			 * List<LvmSumUp> lvmSumUpList = lvmSumUpRepository.findAll();
			 * 
			 */

			MstEmpType mstEmpType = new MstEmpType();
			ShiftMaster shiftMaster = new ShiftMaster();
			List<ShiftMaster> possibleShiftList = new ArrayList<>();
			String inDttime = new String();
			String outDttime = new String();
			String shiftTime = new String();

			StringBuilder querysb = new StringBuilder();

			for (int i = 0; i < dailyAttendanceList.size(); i++) {

				possibleShiftList = new ArrayList<>();

				Date defaultDate = sf.parse(dailyAttendanceList.get(i).getAttDate());
				ObjectMapper mapper = new ObjectMapper();
				EmpJsonData employee = mapper.readValue(dailyAttendanceList.get(i).getEmpJson(), EmpJsonData.class);
				dailyAttendanceList.get(i).setEmpType(employee.getEmpType());

				// delete leave Added By System in this month

				// get emptype record and break;

				for (int j = 0; j < mstEmpTypeList.size(); j++) {

					if (mstEmpTypeList.get(j).getEmpTypeId() == employee.getEmpType()) {
						mstEmpType = mstEmpTypeList.get(j);
						break;
					}

				}

				// get timeShifting record by shiftid
				for (int j = 0; j < shiftList.size(); j++) {

					if (shiftList.get(j).getId() == employee.getCurrentShiftid()
							&& employee.getLocationId() == shiftList.get(j).getLocationId()) {
						shiftMaster = shiftList.get(j);
						dailyAttendanceList.get(i).setCurrentShiftid(shiftList.get(j).getId());
						break;
					}

				}

				// get possible timeShifting records List by same deptId of employee
				for (int j = 0; j < shiftList.size(); j++) {

					if (shiftList.get(j).getSelfGroupId() == shiftMaster.getSelfGroupId()
							&& employee.getLocationId() == shiftList.get(j).getLocationId()) {
						possibleShiftList.add(shiftList.get(j));
					}

				}

				// assign in time and out time from uploaded csv to record
				for (int j = 0; j < fileUploadedDataList.size(); j++) {

					try {
						Date uploadedDate = dd.parse(fileUploadedDataList.get(j).getLogDate());

						if (dailyAttendanceList.get(i).getEmpCode().equals(fileUploadedDataList.get(j).getEmpCode())
								&& defaultDate.compareTo(uploadedDate) == 0) {

							dailyAttendanceList.get(i).setInTime(fileUploadedDataList.get(j).getInTime());
							dailyAttendanceList.get(i).setOutTime(fileUploadedDataList.get(j).getOutTime());
							dailyAttendanceList.get(i).setByFileUpdated(1);

							if (dataForUpdateAttendance.getEmpId() == 0) {
								dailyAttendanceList.get(i).setRowId(j + 1);
							}
							break;

						}
					} catch (Exception e) {
						// e.printStackTrace();
					}

				}

				// create default date and time
				inDttime = dailyAttendanceList.get(i).getAttDate() + " " + dailyAttendanceList.get(i).getInTime()
						+ ":00";
				outDttime = dailyAttendanceList.get(i).getAttDate() + " " + dailyAttendanceList.get(i).getOutTime()
						+ ":00";

				long minimumMin = 0;

				// find current shift by thumb
				for (int j = 0; j < possibleShiftList.size(); j++) {

					try {

						long x = 0;
						shiftTime = dailyAttendanceList.get(i).getAttDate() + " "
								+ possibleShiftList.get(j).getFromtime();

						Date startDate = yyDtTm.parse(inDttime);
						Date endDate = yyDtTm.parse(shiftTime);// Set end date

						if (startDate.compareTo(endDate) > 0) {
							long duration = startDate.getTime() - endDate.getTime();
							long diffHours = duration / (60 * 60 * 1000);
							long diffMinutes = (duration / (60 * 1000) % 60) + (diffHours * 60);
							x = diffMinutes;

						} else {
							long duration = endDate.getTime() - startDate.getTime();
							long diffHours = duration / (60 * 60 * 1000);
							long diffMinutes = (duration / (60 * 1000) % 60) + (diffHours * 60);

							x = diffMinutes;

						}

						if (j == 0) {
							minimumMin = x;
							shiftMaster = possibleShiftList.get(j);
							dailyAttendanceList.get(i).setCurrentShiftid(possibleShiftList.get(j).getId());
							dailyAttendanceList.get(i).setCurrentShiftname(possibleShiftList.get(j).getShiftname());
						}

						if (minimumMin > x) {
							minimumMin = x;
							shiftMaster = possibleShiftList.get(j);
							dailyAttendanceList.get(i).setCurrentShiftid(possibleShiftList.get(j).getId());
							dailyAttendanceList.get(i).setCurrentShiftname(possibleShiftList.get(j).getShiftname());
						}

					} catch (Exception e) {
						// e.printStackTrace();
					}

				}

				// calculate working hours
				try {
					Date inDt = yyDtTm.parse(inDttime);// Set start date
					Date outDt = yyDtTm.parse(outDttime);

					if (inDt.compareTo(outDt) > 0) {
						outDt.setTime(outDt.getTime() + 1000 * 60 * 60 * 24);
					}

					long durationBetweenInOut = outDt.getTime() - inDt.getTime();
					long diffHoursBetweenInOut = durationBetweenInOut / (60 * 60 * 1000);
					long diffMinutesBetweenInOut = (durationBetweenInOut / (60 * 1000) % 60)
							+ (diffHoursBetweenInOut * 60);

					dailyAttendanceList.get(i).setWorkingHrs(diffMinutesBetweenInOut);

					if (diffMinutesBetweenInOut > shiftMaster.getShiftOtHour()) {
						dailyAttendanceList.get(i)
								.setOtHr(String.valueOf(diffMinutesBetweenInOut - shiftMaster.getShiftOtHour()));
					} else {
						dailyAttendanceList.get(i).setOtHr("0");
					}

				} catch (Exception e) {
					// e.printStackTrace();
				}

				// calculate late time
				try {

					int allowdLateTime = 0;
					shiftTime = dailyAttendanceList.get(i).getAttDate() + " " + shiftMaster.getFromtime();
					if (mstEmpType.getMaxLateTimeAllowed() > shiftMaster.getMaxLateTimeAllowed()) {
						allowdLateTime = mstEmpType.getMaxLateTimeAllowed();
					} else {
						allowdLateTime = shiftMaster.getMaxLateTimeAllowed();
					}

					Date inDateTime = yyDtTm.parse(inDttime);
					Date shiftDatetime = yyDtTm.parse(shiftTime);// Set end date

					if (inDateTime.compareTo(shiftDatetime) > 0) {
						long durationBetweenInOut = inDateTime.getTime() - shiftDatetime.getTime();
						long diffHoursBetweenInOut = durationBetweenInOut / (60 * 60 * 1000);
						long diffMinutesBetweenInOut = (durationBetweenInOut / (60 * 1000) % 60)
								+ (diffHoursBetweenInOut * 60);
						dailyAttendanceList.get(i).setLateMin((int) diffMinutesBetweenInOut);

						if (diffMinutesBetweenInOut > allowdLateTime) {
							dailyAttendanceList.get(i).setLateMark("1");
						} else {
							dailyAttendanceList.get(i).setLateMark("0");
						}
					} else {
						dailyAttendanceList.get(i).setLateMin(0);
						dailyAttendanceList.get(i).setLateMark("0");
					}

					if (Float.parseFloat(shiftMaster.getShiftHr()) > dailyAttendanceList.get(i).getWorkingHrs()) {
						dailyAttendanceList.get(i).setLateMark("0");
					}

				} catch (Exception e) {
					// e.printStackTrace();
				}
				try {

					// weekEnnd : 1=Weekly off,2: no weekly off
					// holiday : 3=holiday ,4: holiday NO
					// leave : 5=leave ,6: no leave
					// presentStatus : 7=present ,8: absent

					int weekEndStatus = commonFunctionService.findDateInWeekEnd(sf.format(defaultDate),
							sf.format(defaultDate), weeklyOfflist, weeklyOffShitList,
							dailyAttendanceList.get(i).getLocationId(), employee.getWeekEndCatId(),
							dailyAttendanceList.get(i).getEmpId());

					int holidayStatus = commonFunctionService.findDateInHoliday(sf.format(defaultDate),
							sf.format(defaultDate), holidayList, dailyAttendanceList.get(i).getLocationId(),
							employee.getHolidayCatId());

					LeaveStsAndLeaveId stsInfo = commonFunctionService.findDateInLeave(sf.format(defaultDate),
							leavetList, dailyAttendanceList.get(i).getEmpId());

					int leaveStatus = stsInfo.getSts();
					int presentStatus = 7;

					if (dailyAttendanceList.get(i).getInTime().equals("0:00")
							|| dailyAttendanceList.get(i).getOutTime().equals("0:00")
							|| dailyAttendanceList.get(i).getInTime().equals("00:00")
							|| dailyAttendanceList.get(i).getOutTime().equals("00:00")) {
						presentStatus = 8;
					}

					if ((presentStatus == 7 && weekEndStatus == 1) || (presentStatus == 7 && holidayStatus == 1)) {
						dailyAttendanceList.get(i).setOtHr(String.valueOf(dailyAttendanceList.get(i).getWorkingHrs()));
					}

					String atteanceCase = weekEndStatus + "" + holidayStatus + "" + leaveStatus + "" + presentStatus;

					dailyAttendanceList.get(i).setCasetype(atteanceCase);

					try {
						/// start cases implementation
						if (atteanceCase.equals("1357") || atteanceCase.equals("1367") || atteanceCase.equals("2357")
								|| atteanceCase.equals("2367") || atteanceCase.equals("1358")
								|| atteanceCase.equals("1368") || atteanceCase.equals("2358")
								|| atteanceCase.equals("2368")) {
							if (atteanceCase.equals("1358") || atteanceCase.equals("1368")
									|| atteanceCase.equals("2358") || atteanceCase.equals("2368")) {
								String newStts = AttendanceStatus(employee, dailyAttendanceList.get(i),
										dailyAttendanceList.get(i).getEmpId(), dailyAttendanceList.get(i).getAttDate(),
										atteanceCase, dailyAttendanceList.get(i).getWorkingHrs(), shiftMaster,
										mstEmpType);

								dailyAttendanceList.get(i).setAttStatus(newStts);
								for (int j = 0; j < lvTypeList.size(); j++) {
									if (lvTypeList.get(j).getNameSd().equals(newStts)) {
										dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
										break;
									}
								}

							} // $case == "1358" || $case == "1368" || $case == "2358" || $case == "2368"
							else if (atteanceCase.equals("1357") || atteanceCase.equals("1367")
									|| atteanceCase.equals("2357") || atteanceCase.equals("2367")) {
								// echo "<stat>".$rs_emp_type->wh_work;

								String newStts = AttendanceStatus(employee, dailyAttendanceList.get(i),
										dailyAttendanceList.get(i).getEmpId(), dailyAttendanceList.get(i).getAttDate(),
										atteanceCase, dailyAttendanceList.get(i).getWorkingHrs(), shiftMaster,
										mstEmpType);

								dailyAttendanceList.get(i).setAttStatus(newStts);
								for (int j = 0; j < lvTypeList.size(); j++) {
									if (lvTypeList.get(j).getNameSd().equals(newStts)) {
										dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
										break;
									}
								}

								// $rs_emp_type->wh_work == 'Comp Off'
							} // $case == "1357" || $case == "1367" || $case == "2357" || $case == "2367"
						} // $case == "1357" || $case == "1367" || $case == "2357" || $case == "2367" ||
							// $case == "1358" || $case == "1368" || $case == "2358" || $case == "2368"
						else if (atteanceCase.equals("1457") || atteanceCase.equals("1458")
								|| atteanceCase.equals("1467") || atteanceCase.equals("1468")) {

							String newStts = AttendanceStatus(employee, dailyAttendanceList.get(i),
									dailyAttendanceList.get(i).getEmpId(), dailyAttendanceList.get(i).getAttDate(),
									atteanceCase, dailyAttendanceList.get(i).getWorkingHrs(), shiftMaster, mstEmpType);

							dailyAttendanceList.get(i).setAttStatus(newStts);
							for (int j = 0; j < lvTypeList.size(); j++) {
								if (lvTypeList.get(j).getNameSd().equals(newStts)) {
									dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
									break;
								}
							}
						} // $case == "1457" || $case == "1458" || $case == "1467" || $case == "1468"
						else if (atteanceCase.equals("2457") || atteanceCase.equals("2458")) {
							if (atteanceCase.equals("2457")) {

								if (dailyAttendanceList.get(i).getWorkingHrs() >= Float
										.parseFloat(shiftMaster.getShiftHr())) {
									// echo
									if (dailyAttendanceList.get(i).getWorkingHrs() > shiftMaster.getShiftOtHour()) {
										if (mstEmpType.getOtApplicable().contains("Yes")) {
											dailyAttendanceList.get(i).setAttStatus("OT");
											for (int j = 0; j < lvTypeList.size(); j++) {
												if (lvTypeList.get(j).getNameSd().equals("OT")) {
													dailyAttendanceList.get(i)
															.setLvSumupId(lvTypeList.get(j).getLvSumupId());
													break;
												}
											}
										} // $rs_emp_type->ot_applicable == 'Yes'
										else {
											dailyAttendanceList.get(i).setAttStatus("P");
											for (int j = 0; j < lvTypeList.size(); j++) {
												if (lvTypeList.get(j).getNameSd().equals("P")) {
													dailyAttendanceList.get(i)
															.setLvSumupId(lvTypeList.get(j).getLvSumupId());
													break;
												}
											}
										}
									} // $working_hrs > $rs_shift_timming->shift_hr
									else {
										dailyAttendanceList.get(i).setAttStatus("P");
										for (int j = 0; j < lvTypeList.size(); j++) {
											if (lvTypeList.get(j).getNameSd().equals("P")) {
												dailyAttendanceList.get(i)
														.setLvSumupId(lvTypeList.get(j).getLvSumupId());
												break;
											}
										}
									}
									// start Cancle leave
									try {

										float updateNoOfDays = 0;

										if (stsInfo.getDuration() != 1) {
											// cancel halfDay leave
											updateNoOfDays = (float) 0.5;

										} else {
											// cancel full leave
											updateNoOfDays = 1;
										}

										Date date = new Date();
										SimpleDateFormat dtformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

										int updateNoOfDaysInLeave = leaveApplyRepository.updateNoOfDaysInLeave(
												stsInfo.getLeaveId(), updateNoOfDays, "Cancel By System");

										LeaveApply leaveSummary = new LeaveApply();
										leaveSummary.setCalYrId(2);
										leaveSummary.setEmpId(dailyAttendanceList.get(i).getEmpId());
										leaveSummary.setFinalStatus(1);
										leaveSummary.setLeaveDuration(String.valueOf(stsInfo.getDuration()));
										leaveSummary.setCirculatedTo("1");
										leaveSummary.setLeaveEmpReason("Leave Add And Cancel By System");
										leaveSummary.setLeaveCancleRemark("Leave Add And Cancel By System");
										leaveSummary.setLvtApplicationIdParent(stsInfo.getLeaveId());
										leaveSummary.setLvTypeId(stsInfo.getLeaveTyId());
										leaveSummary.setLeaveFromdt(sf.format(defaultDate));
										leaveSummary.setLeaveTodt(sf.format(defaultDate));
										leaveSummary.setExInt1(7);
										leaveSummary.setExInt2(1);
										leaveSummary.setExInt3(1);
										leaveSummary.setExVar1("NA");
										leaveSummary.setExVar2("NA");
										leaveSummary.setExVar3("NA");
										leaveSummary.setIsActive(1);
										leaveSummary.setDelStatus(1);
										leaveSummary.setMakerUserId(dataForUpdateAttendance.getUserId());
										leaveSummary.setMakerEnterDatetime(dtformat.format(date));

										if (stsInfo.getDuration() != 1) {
											// cancel halfDay leave
											leaveSummary.setLeaveNumDays((float) 0.5);

										} else {
											// cancel full leave
											leaveSummary.setLeaveNumDays(1);
										}

										LeaveApply saveLeave = leaveApplyRepository.saveAndFlush(leaveSummary);

										LeaveTrail lt = new LeaveTrail();
										lt.setEmpRemarks(leaveSummary.getLeaveEmpReason());
										lt.setLeaveId(saveLeave.getLeaveId());
										lt.setLeaveStatus(7);
										lt.setEmpId(dailyAttendanceList.get(i).getEmpId());
										lt.setExInt1(1);
										lt.setExInt2(1);
										lt.setExInt3(1);
										lt.setExVar1("NA");
										lt.setExVar2("NA");
										lt.setExVar3("NA");
										lt.setMakerUserId(dataForUpdateAttendance.getUserId());
										lt.setMakerEnterDatetime(dtformat.format(date));
										LeaveTrail saveTr = leaveTrailRepository.saveAndFlush(lt);

										int updateTrailId = leaveApplyRepository
												.updateLeaveApply(saveLeave.getLeaveId(), saveTr.getTrailPkey());

									} catch (Exception e) {
										e.printStackTrace();
									}

									// end Cancle leave

								} // $working_hrs >= $resultp->working_hrs
								else if (dailyAttendanceList.get(i).getWorkingHrs() >= Float
										.parseFloat(shiftMaster.getShiftHalfdayHr())
										&& dailyAttendanceList.get(i).getWorkingHrs() < Float
												.parseFloat(shiftMaster.getShiftHr())
										&& stsInfo.getDuration() != 1) {
									// HD
									if (mstEmpType.getHalfDay().equals("Yes")) {

										dailyAttendanceList.get(i).setAttStatus("HDPHDL");
										for (int j = 0; j < lvTypeList.size(); j++) {
											if (lvTypeList.get(j).getNameSd().equals("HDPHDL")) {
												dailyAttendanceList.get(i)
														.setLvSumupId(lvTypeList.get(j).getLvSumupId());
												break;
											}
										}
									} // $rs_emp_type->half_day == 'Yes'
									else {
										dailyAttendanceList.get(i).setAttStatus("P");
										for (int j = 0; j < lvTypeList.size(); j++) {
											if (lvTypeList.get(j).getNameSd().equals("P")) {
												dailyAttendanceList.get(i)
														.setLvSumupId(lvTypeList.get(j).getLvSumupId());
												break;
											}
										}
									}

								} else {

									if (stsInfo.getLeaveTyId() == 2) {
										dailyAttendanceList.get(i).setAttStatus("LWP");
										for (int j = 0; j < lvTypeList.size(); j++) {
											if (lvTypeList.get(j).getNameSd().equals("LWP")) {
												dailyAttendanceList.get(i)
														.setLvSumupId(lvTypeList.get(j).getLvSumupId());
												break;
											}
										}
									} else {
										dailyAttendanceList.get(i).setAttStatus("PL");
										for (int j = 0; j < lvTypeList.size(); j++) {
											if (lvTypeList.get(j).getNameSd().equals("PL")) {
												dailyAttendanceList.get(i)
														.setLvSumupId(lvTypeList.get(j).getLvSumupId());
												break;
											}
										}
									}

								} // $working_hrs >= $resulthd->working_hrs && $working_hrs <
									// $resultp->working_hrs

							} // $case == '2457'
							else if (atteanceCase.equals("2458")) {

								if (stsInfo.getLeaveTyId() == 2) {

									dailyAttendanceList.get(i).setAttStatus("LWP");
									for (int j = 0; j < lvTypeList.size(); j++) {
										if (lvTypeList.get(j).getNameSd().equals("LWP")) {
											dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
											break;
										}
									}
								} else {
									dailyAttendanceList.get(i).setAttStatus("PL");
									for (int j = 0; j < lvTypeList.size(); j++) {
										if (lvTypeList.get(j).getNameSd().equals("PL")) {
											dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
											break;
										}
									}
								}

							} // $case == '2458'
						} // $case == "2457" || $case == "2458"
						else if (atteanceCase.equals("2467") || atteanceCase.equals("2468")) {
							if (atteanceCase.equals("2467")) {

								if (mstEmpType.getMinworkApplicable().equals("Yes")) {

									if (dailyAttendanceList.get(i).getWorkingHrs() >= Float
											.parseFloat(shiftMaster.getShiftHr())) {
										if (dailyAttendanceList.get(i).getWorkingHrs() > shiftMaster.getShiftOtHour()) {

											if (mstEmpType.getOtApplicable().contains("Yes")) {
												dailyAttendanceList.get(i).setAttStatus("OT");
												for (int j = 0; j < lvTypeList.size(); j++) {
													if (lvTypeList.get(j).getNameSd().equals("OT")) {
														dailyAttendanceList.get(i)
																.setLvSumupId(lvTypeList.get(j).getLvSumupId());
														break;
													}
												}
											} // $rs_emp_type->ot_applicable == 'Yes'
											else {
												dailyAttendanceList.get(i).setAttStatus("P");
												for (int j = 0; j < lvTypeList.size(); j++) {
													if (lvTypeList.get(j).getNameSd().equals("P")) {
														dailyAttendanceList.get(i)
																.setLvSumupId(lvTypeList.get(j).getLvSumupId());
														break;
													}
												}
											}
										} // $working_hrs > $rs_shift_timming->shift_hr
										else {
											dailyAttendanceList.get(i).setAttStatus("P");
											for (int j = 0; j < lvTypeList.size(); j++) {
												if (lvTypeList.get(j).getNameSd().equals("P")) {
													dailyAttendanceList.get(i)
															.setLvSumupId(lvTypeList.get(j).getLvSumupId());
													break;
												}
											}
										}
									} // $working_hrs >= $resultp->working_hrs
									else if (dailyAttendanceList.get(i).getWorkingHrs() >= Float
											.parseFloat(shiftMaster.getShiftHalfdayHr())
											&& dailyAttendanceList.get(i).getWorkingHrs() < Float
													.parseFloat(shiftMaster.getShiftHr())) {
										// echo "<present and >hd111";
										// HD
										if (mstEmpType.getHalfDay().equals("Yes")) {
											// echo
											if (PL_CL_HD_leave_insert_automatic == 1) {

											} // $PL_CL_HD_leave_insert_automatic == "1"
											else {
												dailyAttendanceList.get(i).setAttStatus("HD");
												for (int j = 0; j < lvTypeList.size(); j++) {
													if (lvTypeList.get(j).getNameSd().equals("HD")) {
														dailyAttendanceList.get(i)
																.setLvSumupId(lvTypeList.get(j).getLvSumupId());
														break;
													}
												}
											}
										} // $rs_emp_type->half_day == 'Yes'
										else {
											dailyAttendanceList.get(i).setAttStatus("P");
											for (int j = 0; j < lvTypeList.size(); j++) {
												if (lvTypeList.get(j).getNameSd().equals("P")) {
													dailyAttendanceList.get(i)
															.setLvSumupId(lvTypeList.get(j).getLvSumupId());
													break;
												}
											}
										}
									} // $working_hrs >= $resulthd->working_hrs && $working_hrs <
										// $resultp->working_hrs
									else if (dailyAttendanceList.get(i).getWorkingHrs() < Float
											.parseFloat(shiftMaster.getShiftHalfdayHr())) {
										dailyAttendanceList.get(i).setEarlyGoingMark(0);
										dailyAttendanceList.get(i).setLateMark("0");
										dailyAttendanceList.get(i).setAttStatus("AB");
										for (int j = 0; j < lvTypeList.size(); j++) {
											if (lvTypeList.get(j).getNameSd().equals("AB")) {
												dailyAttendanceList.get(i)
														.setLvSumupId(lvTypeList.get(j).getLvSumupId());
												break;
											}
										}
									} // $working_hrs < $resulthd->working_hrs
								} // $rs_emp_type->minwork_applicable == 'Yes'
								else {
									if (dailyAttendanceList.get(i).getWorkingHrs() > shiftMaster.getShiftOtHour()) {
										dailyAttendanceList.get(i).setAttStatus("OT");
										for (int j = 0; j < lvTypeList.size(); j++) {
											if (lvTypeList.get(j).getNameSd().equals("OT")) {
												dailyAttendanceList.get(i)
														.setLvSumupId(lvTypeList.get(j).getLvSumupId());
												break;
											}
										}
									} // $working_hrs > $rs_shift_timming->shift_hr
									else {
										dailyAttendanceList.get(i).setAttStatus("P");
										for (int j = 0; j < lvTypeList.size(); j++) {
											if (lvTypeList.get(j).getNameSd().equals("P")) {
												dailyAttendanceList.get(i)
														.setLvSumupId(lvTypeList.get(j).getLvSumupId());
												break;
											}
										}
									}
								}
							} // $case == '2467'
							else if (atteanceCase.equals("2468")) {

								if (defaultDate.compareTo(sf.parse(employee.getCmpJoiningDate())) >= 0) {
									dailyAttendanceList.get(i).setAttStatus("AB");
									for (int j = 0; j < lvTypeList.size(); j++) {
										if (lvTypeList.get(j).getNameSd().equals("AB")) {
											dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
											break;
										}
									}

								} // $att_date >= $forsaterday->cmp_joining_date
								else {
									dailyAttendanceList.get(i).setAttStatus("NA");
									for (int j = 0; j < lvTypeList.size(); j++) {
										if (lvTypeList.get(j).getNameSd().equals("NA")) {
											dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
											break;
										}
									}

								}
							} // $case == '2468'
						} // $case == '2467' || $case == '2468'
							////////////////////

						// end cases implementation
					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					// e.printStackTrace();
				}

				querysb = new StringBuilder();

				// System.out.println("case type
				// -----------------"+dailyAttendanceList.get(i).getCasetype());
				if (dailyAttendanceList.get(i).getByFileUpdated() == 1) {
					querysb.append("update\n" + "        tbl_attt_daily_daily \n" + "    set\n"
							+ "        atsumm_uid=NULL," + "        att_date='"
							+ dailyAttendanceList.get(i).getAttDate() + "'," + "        att_status='"
							+ dailyAttendanceList.get(i).getAttStatus() + "'," + "        by_file_updated='"
							+ dailyAttendanceList.get(i).getByFileUpdated() + "'," + "        casetype='"
							+ dailyAttendanceList.get(i).getCasetype() + "'," + "        comments_supervisor=NULL,"
							+ "        company_id='" + dailyAttendanceList.get(i).getCompanyId() + "',"
							+ "        current_shiftid='" + dailyAttendanceList.get(i).getCurrentShiftid() + "',"
							+ "        current_shiftname='" + dailyAttendanceList.get(i).getCurrentShiftname() + "',"
							+ "        early_going_mark='" + dailyAttendanceList.get(i).getEarlyGoingMark() + "',"
							+ "        early_going_min='" + dailyAttendanceList.get(i).getEarlyGoingMin() + "',"
							+ "        emp_code='" + dailyAttendanceList.get(i).getEmpCode() + "'," + "        emp_id='"
							+ dailyAttendanceList.get(i).getEmpId() + "'," + "        emp_json='"
							+ dailyAttendanceList.get(i).getEmpJson() + "'," + "        emp_name='"
							+ dailyAttendanceList.get(i).getEmpName() + "'," + "        emp_type='"
							+ dailyAttendanceList.get(i).getEmpType() + "'," + "        file_name=NULL,"
							+ "        freeze_by_supervisor='" + dailyAttendanceList.get(i).getFreezeBySupervisor()
							+ "'," + "        full_night='" + dailyAttendanceList.get(i).getFullNight() + "',"
							+ "        get_pass_used_count='" + dailyAttendanceList.get(i).getGetPassUsedCount() + "',"
							+ "        get_pass_used_hour='" + dailyAttendanceList.get(i).getGetPassUsedHour() + "',"
							+ "        get_pass_used_hour_reason=NULL," + "        half_night='"
							+ dailyAttendanceList.get(i).getHalfNight() + "'," + "        import_date=NULL,"
							+ "        in_time='" + dailyAttendanceList.get(i).getInTime() + "'," + "        is_fixed='"
							+ dailyAttendanceList.get(i).getIsFixed() + "'," + "        late_mark='"
							+ dailyAttendanceList.get(i).getLateMark() + "'," + "        late_min='"
							+ dailyAttendanceList.get(i).getLateMin() + "'," + "        location_id='"
							+ dailyAttendanceList.get(i).getLocationId() + "'," + "        login_name='"
							+ dailyAttendanceList.get(i).getLoginName() + "'," + "        login_time=NULL,"
							+ "        lv_sumup_id='" + dailyAttendanceList.get(i).getLvSumupId() + "',"
							+ "        manual_ot_hr='" + dailyAttendanceList.get(i).getManualOtHr() + "',"
							+ "        multiple_entries=NULL," + "        ot_hr='"
							+ dailyAttendanceList.get(i).getOtHr() + "'," + "        out_time='"
							+ dailyAttendanceList.get(i).getOutTime() + "'," + "        raw_data_inout=NULL,"
							+ "        reason=NULL," + "        rec_status='"
							+ dailyAttendanceList.get(i).getRecStatus() + "'," + "        row_id='"
							+ dailyAttendanceList.get(i).getRowId() + "'," + "        working_hrs='"
							+ dailyAttendanceList.get(i).getWorkingHrs() + "'" + "    where\n" + "        id="
							+ dailyAttendanceList.get(i).getId() + ";");
					try {
						String quiry = querysb.toString();
						jdbcTemplate.batchUpdate(quiry);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

			// System.out.println("dailyAttendanceList " + quiry);

			// List<DailyAttendance> dailyAttendanceSaveRes =
			// dailyAttendanceRepository.saveAll(dailyAttendanceList);
			info.setError(false);
			info.setMsg("success");

		} catch (

		Exception e) {

			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/finalUpdateDailySumaryRecord" }, method = RequestMethod.POST)
	public @ResponseBody Info finalUpdateDailySumaryRecord(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("userId") int userId, @RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("empId") int empId) {

		Info info = new Info();

		try {

			List<DailyDailyInformation> dailyDailyInformationList = new ArrayList<>();

			List<SummaryDailyAttendance> summaryDailyAttendanceList = new ArrayList<>();

			if (empId != 0) {

				dailyDailyInformationList = dailyDailyInformationRepository.dailyDailyInformationList(fromDate, toDate,
						empId);
				summaryDailyAttendanceList = summaryDailyAttendanceRepository.summaryDailyAttendanceList(month, year,
						empId);

			} else {

				dailyDailyInformationList = dailyDailyInformationRepository.dailyDailyInformationList(fromDate, toDate);
				summaryDailyAttendanceList = summaryDailyAttendanceRepository.summaryDailyAttendanceListAll(month,
						year);
			}

			for (int i = 0; i < summaryDailyAttendanceList.size(); i++) {

				int totalDaysInmonth = difffun(fromDate, toDate);
				float workingDays = 0;
				float presentDays = 0;
				float holidayPresentHalf = 0;
				float paidLeave = 0;
				float unPaidLeave = 0;
				float weeklyOff = 0;
				float paidHoliday = 0;
				float layOff = 0;
				float legalStrike = 0;
				float wot = 0;
				int absentLeave = 0;
				int lateMin = 0;
				int lateMark = 0;
				int totalWorkingHr = 0;
				int totalOtHr = 0;

				String isDaily = "daily";

				for (int j = 0; j < dailyDailyInformationList.size(); j++) {

					if (dailyDailyInformationList.get(j).getEmpId() == summaryDailyAttendanceList.get(i).getEmpId()) {

						isDaily = dailyDailyInformationList.get(j).getSalBasis();

						lateMin = lateMin + dailyDailyInformationList.get(j).getLateMin();
						lateMark = lateMark + dailyDailyInformationList.get(j).getLateMark();
						totalWorkingHr = totalWorkingHr + dailyDailyInformationList.get(j).getWorkingMin();
						totalOtHr = totalOtHr + dailyDailyInformationList.get(j).getOtMin();

						if (dailyDailyInformationList.get(j).getLvSumupId() == 5) { // 5=p,ot

							presentDays = presentDays + dailyDailyInformationList.get(j).getDaycount();
						}
						if (dailyDailyInformationList.get(j).getLvSumupId() == 20) { // 20=HDPHDL,

							presentDays = (float) (presentDays
									+ (dailyDailyInformationList.get(j).getDaycount() * 0.5));
							/*
							 * holidayPresentHalf = (float) (holidayPresentHalf +
							 * (dailyDailyInformationList.get(j).getDaycount() * 0.5));
							 */
							paidLeave = (float) (paidLeave + (dailyDailyInformationList.get(j).getDaycount() * 0.5));
						}
						if (dailyDailyInformationList.get(j).getLvSumupId() == 21) { // 21=HD

							presentDays = (float) (presentDays
									+ (dailyDailyInformationList.get(j).getDaycount() * 0.5));
							unPaidLeave = (float) (unPaidLeave
									+ (dailyDailyInformationList.get(j).getDaycount() * 0.5));

						}

						if (dailyDailyInformationList.get(j).getLvSumupId() == 22) { // 21=HD

							absentLeave = (int) (absentLeave + (dailyDailyInformationList.get(j).getDaycount()));

						}

						if (dailyDailyInformationList.get(j).getLvSumupId() == 14) { // 14=WO-OT

							wot = wot + dailyDailyInformationList.get(j).getDaycount();

						}

						if (dailyDailyInformationList.get(j).getSalBasis().equals("daily")) {
							if (dailyDailyInformationList.get(j).getLvSumupId() == 12
									|| dailyDailyInformationList.get(j).getLvSumupId() == 14
									|| dailyDailyInformationList.get(j).getLvSumupId() == 16
									|| dailyDailyInformationList.get(j).getLvSumupId() == 18
									|| dailyDailyInformationList.get(j).getLvSumupId() == 19) {

								weeklyOff = weeklyOff + dailyDailyInformationList.get(j).getDaycount();

							}
							if (dailyDailyInformationList.get(j).getLvSumupId() == 6
									|| dailyDailyInformationList.get(j).getLvSumupId() == 13
									|| dailyDailyInformationList.get(j).getLvSumupId() == 15
									|| dailyDailyInformationList.get(j).getLvSumupId() == 17
									|| dailyDailyInformationList.get(j).getLvSumupId() == 18
									|| dailyDailyInformationList.get(j).getLvSumupId() == 19) {

								paidHoliday = paidHoliday + dailyDailyInformationList.get(j).getDaycount();

							}
						} else {
							if (dailyDailyInformationList.get(j).getLvSumupId() == 12
									|| dailyDailyInformationList.get(j).getLvSumupId() == 14
									|| dailyDailyInformationList.get(j).getLvSumupId() == 16
									|| dailyDailyInformationList.get(j).getLvSumupId() == 17
									|| dailyDailyInformationList.get(j).getLvSumupId() == 18
									|| dailyDailyInformationList.get(j).getLvSumupId() == 19) {
								weeklyOff = weeklyOff + dailyDailyInformationList.get(j).getDaycount();

							}
							if (dailyDailyInformationList.get(j).getLvSumupId() == 6
									|| dailyDailyInformationList.get(j).getLvSumupId() == 13
									|| dailyDailyInformationList.get(j).getLvSumupId() == 15) {

								paidHoliday = paidHoliday + dailyDailyInformationList.get(j).getDaycount();

							}
						}

						if (dailyDailyInformationList.get(j).getLvSumupId() == 7) {

							paidLeave = paidLeave + dailyDailyInformationList.get(j).getDaycount();
						}
						if (dailyDailyInformationList.get(j).getLvSumupId() == 11) {
							unPaidLeave = unPaidLeave + dailyDailyInformationList.get(j).getDaycount();

						}
						if (dailyDailyInformationList.get(j).getLvSumupId() == 9) {
							layOff = layOff + dailyDailyInformationList.get(j).getDaycount();

						}
						if (dailyDailyInformationList.get(j).getLvSumupId() == 8) {
							legalStrike = legalStrike + dailyDailyInformationList.get(j).getDaycount();

						}
					}
				}
				summaryDailyAttendanceList.get(i).setTotlateMins(lateMin);
				summaryDailyAttendanceList.get(i).setTotlateDays(lateMark);
				summaryDailyAttendanceList.get(i).setTotworkingHrs(totalWorkingHr);
				summaryDailyAttendanceList.get(i).setTotOthr(totalOtHr);
				summaryDailyAttendanceList.get(i).setPresentDays(presentDays);
				summaryDailyAttendanceList.get(i).setHdpresentHdleave(holidayPresentHalf);
				summaryDailyAttendanceList.get(i).setPaidLeave(paidLeave);
				summaryDailyAttendanceList.get(i).setWeeklyOff(weeklyOff);
				summaryDailyAttendanceList.get(i).setPaidHoliday(paidHoliday);
				summaryDailyAttendanceList.get(i).setUnpaidLeave(unPaidLeave);
				summaryDailyAttendanceList.get(i).setLayOff(layOff);
				summaryDailyAttendanceList.get(i).setLegalStrike(legalStrike);
				summaryDailyAttendanceList.get(i).setNcpDays(layOff + legalStrike);
				summaryDailyAttendanceList.get(i).setAbsentDays(absentLeave);
				summaryDailyAttendanceList.get(i).setTotalDaysInmonth(totalDaysInmonth);
				summaryDailyAttendanceList.get(i).setWeeklyOffPresent(wot);
				workingDays = totalDaysInmonth - summaryDailyAttendanceList.get(i).getWeeklyOff()
						- summaryDailyAttendanceList.get(i).getPaidHoliday();
				summaryDailyAttendanceList.get(i).setWorkingDays(workingDays);

				if (isDaily.equals("daily")) {
					summaryDailyAttendanceList.get(i)
							.setPayableDays(summaryDailyAttendanceList.get(i).getPresentDays()
									+ summaryDailyAttendanceList.get(i).getPaidHoliday()
									+ summaryDailyAttendanceList.get(i).getPaidLeave());
				} else {
					summaryDailyAttendanceList.get(i)
							.setPayableDays(summaryDailyAttendanceList.get(i).getPresentDays()
									+ summaryDailyAttendanceList.get(i).getPaidHoliday()
									+ summaryDailyAttendanceList.get(i).getPaidLeave()
									+ summaryDailyAttendanceList.get(i).getWeeklyOff());
				}
				summaryDailyAttendanceList.get(i).setCalculationDone(1);
			}

			// System.out.println("summaryDailyAttendanceList " +
			// summaryDailyAttendanceList);

			List<SummaryDailyAttendance> summaryDailyAttendanceSaveRes = summaryDailyAttendanceRepository
					.saveAll(summaryDailyAttendanceList);

			info.setError(false);
			info.setMsg("success");

		} catch (

		Exception e) {

			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}
		return info;
	}

	public String AttendanceStatus(EmpJsonData employee, DailyAttendance dailyAttendance, int empId, String date,
			String atteanceCase, float hour, ShiftMaster shiftMaster, MstEmpType mstEmpType) {

		String ret = new String();

		try {

			if (atteanceCase.equals("1357") || atteanceCase.equals("1367") || atteanceCase.equals("2357")
					|| atteanceCase.equals("2367")) {

				if (mstEmpType.getWhWork().equals("OT")) {
					if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHr())) {
						if (atteanceCase.equals("1357") || atteanceCase.equals("1367")) {
							ret = "PH-WO-P";
						} // $case == "1357" || $case == "1367"
						else {
							ret = "PH-OT";
						}
					} // $working_hrs >= $resultp['working_hrs']
					else if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHalfdayHr())
							&& dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHr())) {
						if (atteanceCase.equals("1357") || atteanceCase.equals("1367")) {
							ret = "PH-WO-HD";
						} // $case == "1357" || $case == "1367"
						else {
							ret = "PH-HD";
						}
					} // $working_hrs >= $resulthd->working_hrs && $working_hrs <
						// $resultp['working_hrs']
					else if (dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHalfdayHr())) {
						ret = "PH";
					} // $working_hrs < $resulthd['working_hrs']
					else {
						ret = "PH-OT";
					}
				} // $res_getemptype->wh_work == 'OT'
				else if (mstEmpType.getWhWork().equals("Comp Off")) {
					if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHr())) {
						ret = "PH-CO";
					} // $working_hrs >= $resultp['working_hrs']
					else if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHalfdayHr())
							&& dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHr())) {
						ret = "PH-COHD";
					} // $working_hrs >= $resulthd['working_hrs'] && $working_hrs <
						// $resultp['working_hrs']
					else if (dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHalfdayHr())) {
						ret = "PH";
					} // $working_hrs < $resulthd['working_hrs']
					else {
						ret = "PH-CO";
					}
				} // $res_getemptype->wh_work == 'Comp Off'
				else {
					ret = "PH";
				}
			} // $case == '1357' || $case == '1367' || $case == '2357' || $case == '2367'
			else if (atteanceCase.equals("1358") || atteanceCase.equals("1368") || atteanceCase.equals("2358")
					|| atteanceCase.equals("2368")) {
				if (atteanceCase.equals("1358") || atteanceCase.equals("1368")) {
					ret = "PH-WO";
				} // $case == "1358" || $case == "1368"
				else {
					ret = "PH";
				}
			} // $case == '1358' || $case == '1368' || $case == '2358' || $case == '2368'
			else if (atteanceCase.equals("1457") || atteanceCase.equals("1467")) {
				// $query_getemptype = "select te.emp_type,tm.wh_work from tbl_employees te
				// inner join tbl_mst_emp_types tm on te.emp_type=tm.emp_type_id where emp_id='"
				// . $emp_id . "'";
				// $res_getemptype = $this->db->query($query_getemptype)->row();
				if (mstEmpType.getWhWork().equals("OT")) {
					if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHr())) {
						ret = "WO-OT";
					} // $working_hrs >= $resultp['working_hrs']
					else if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHalfdayHr())
							&& dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHr())) {
						ret = "WO-HD";
					} // $working_hrs >= $resulthd['working_hrs'] && $working_hrs <
						// $resultp['working_hrs']
					else if (dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHalfdayHr())) {
						ret = "WO";
					} // $working_hrs < $resulthd['working_hrs']
					else {
						ret = "WO-OT";
					}
				} // $res_getemptype->wh_work == 'OT'
				else if (mstEmpType.getWhWork().equals("Comp Off")) {
					if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHr())) {
						ret = "WO-CO";
					} // $working_hrs >= $resultp['working_hrs']
					else if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHalfdayHr())
							&& dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHr())) {
						ret = "WO-COHD";
					} // $working_hrs >= $resulthd['working_hrs'] && $working_hrs <
						// $resultp['working_hrs']
					else if (dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHalfdayHr())) {
						ret = "WO";
					} // $working_hrs < $resulthd['working_hrs']
					else {
						ret = "WO";
					}
				} // $res_getemptype->wh_work == 'Comp Off'
				else {
					ret = "WO";
				}
			} // $case == '1457' || $case == '1467'
			else if (atteanceCase.equals("1458") || atteanceCase.equals("1468")) {
				ret = "WO";
			} // $case == '1458' || $case == '1468'
			else if (atteanceCase.equals("2468")) {
				ret = "AB";
			} // $case == '2468'
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public int difffun(String date1, String date2) {

		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

		int result = 0;

		try {
			Date date3 = myFormat.parse(date1);
			Date date4 = myFormat.parse(date2);
			long diff = date4.getTime() - date3.getTime();
			result = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (Exception e) {

		}

		return result + 1;
	}

	@RequestMapping(value = { "/getAttendanceSheet" }, method = RequestMethod.POST)
	public @ResponseBody AttendanceSheetData getAttendanceSheet(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {

		AttendanceSheetData info = new AttendanceSheetData();
		try {

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date fmdt = df.parse(fromDate);
			Date todt = df.parse(toDate);

			/*
			 * System.out.println(fmdt + " " + todt);
			 * 
			 * Calendar temp = Calendar.getInstance(); temp.setTime(fmdt); int year =
			 * temp.get(Calendar.YEAR); int month = temp.get(Calendar.MONTH) + 1;
			 */

			List<EmpInfo> empList = empInfoRepository.getEmpListAll(fromDate);

			List<DailyAttendance> dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceListAll(fromDate,
					toDate);

			List<String> dates = new ArrayList<>();
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");

			for (Date j = fmdt; j.compareTo(todt) <= 0;) {
				dates.add(sf.format(j));

				/* System.out.println(sf.parse(sf.format(j))); */
				j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
			}

			List<EmpInfoWithDateInfoList> infomationList = new ArrayList<>();

			for (int i = 0; i < empList.size(); i++) {

				EmpInfoWithDateInfoList infomation = new EmpInfoWithDateInfoList();
				infomation.setEmpCode(empList.get(i).getEmpCode());
				infomation.setEmpId(empList.get(i).getEmpId());
				infomation.setEmpName(empList.get(i).getFirstName() + " " + empList.get(i).getSurname());
				List<DailyDailyInfomationForChart> dateInfo = new ArrayList<>();

				for (int j = 0; j < dates.size(); j++) {

					Date dt = sf.parse(dates.get(j));
					int find = 0;

					for (int k = 0; k < dailyAttendanceList.size(); k++) {

						Date attsdt = df.parse(dailyAttendanceList.get(k).getAttDate());
						if (dailyAttendanceList.get(k).getEmpId() == empList.get(i).getEmpId()
								&& attsdt.compareTo(dt) == 0) {

							DailyDailyInfomationForChart dilydly = new DailyDailyInfomationForChart();
							dilydly.setStatus(dailyAttendanceList.get(k).getAttStatus());
							dilydly.setDate(dates.get(j));
							dilydly.setInTime(dailyAttendanceList.get(k).getInTime());
							dilydly.setOutTime(dailyAttendanceList.get(k).getOutTime());
							dilydly.setOtMin(dailyAttendanceList.get(k).getOtHr());
							dilydly.setLateMin(String.valueOf(dailyAttendanceList.get(k).getLateMin()));
							int hrs = (int) (dailyAttendanceList.get(k).getWorkingHrs() / 60);
							int min = (int) (dailyAttendanceList.get(k).getWorkingHrs() % 60);
							dilydly.setWorkingMin(hrs + "." + min);
							dateInfo.add(dilydly);

							find = 1;
							break;
						}

					}

					if (find == 0) {

						DailyDailyInfomationForChart dilydly = new DailyDailyInfomationForChart();
						dilydly.setStatus("NA");
						dateInfo.add(dilydly);
					}

				}
				infomation.setSttsList(dateInfo);
				infomationList.add(infomation);
			}

			info.setDates(dates);
			info.setInfomationList(infomationList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getMonthlySummryAttendace" }, method = RequestMethod.POST)
	public @ResponseBody List<SummaryAttendance> getMonthlySummryAttendace(@RequestParam("month") int month,
			@RequestParam("year") int year) {

		List<SummaryAttendance> summaryDailyAttendanceList = new ArrayList<>();
		try {

			summaryDailyAttendanceList = summaryAttendanceRepository.summaryDailyAttendanceListAll(month, year);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return summaryDailyAttendanceList;

	}

	@RequestMapping(value = { "/getDailyDailyRecord" }, method = RequestMethod.POST)
	public @ResponseBody List<GetDailyDailyRecord> getDailyDailyRecord(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("empId") int empId) {

		List<GetDailyDailyRecord> summaryDailyAttendanceList = new ArrayList<>();
		try {

			summaryDailyAttendanceList = getDailyDailyRecordRepository.summaryDailyAttendanceListAll(fromDate, toDate,
					empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return summaryDailyAttendanceList;

	}

	@RequestMapping(value = { "/getDailyDailyRecordByDailyId" }, method = RequestMethod.POST)
	public @ResponseBody GetDailyDailyRecord getDailyDailyRecordByDailyId(@RequestParam("dailyId") int dailyId) {

		GetDailyDailyRecord getDailyDailyRecord = new GetDailyDailyRecord();
		try {

			getDailyDailyRecord = getDailyDailyRecordRepository.getDailyDailyRecordByDailyId(dailyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return getDailyDailyRecord;

	}

	@RequestMapping(value = { "/getMonthlySummryAttendaceByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody SummaryAttendance getMonthlySummryAttendaceByEmpId(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("empId") int empId) {

		SummaryAttendance summaryAttendance = new SummaryAttendance();
		try {

			summaryAttendance = summaryAttendanceRepository.summaryDailyAttendanceListAll(month, year, empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return summaryAttendance;

	}

	@RequestMapping(value = { "/updateAttendaceRecordSingle" }, method = RequestMethod.POST)
	public @ResponseBody Info updateAttendaceRecordSingle(@RequestParam("dailyId") int dailyId,
			@RequestParam("selectStatus") int selectStatus, @RequestParam("byStatus") int byStatus,
			@RequestParam("lateMark") String lateMark, @RequestParam("otHours") String otHours,
			@RequestParam("outTime") String outTime, @RequestParam("inTime") String inTime,
			@RequestParam("selectStatusText") String selectStatusText, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("userId") int userId, @RequestParam("month") int month,
			@RequestParam("year") int year) {

		Info info = new Info();
		try {

			DailyAttendance dailyRecordById = dailyAttendanceRepository.getdailyRecordById(dailyId);

			if (dailyRecordById.getIsFixed() == 0) {

				if (byStatus == 1) {

					if (selectStatus != 0) {
						dailyRecordById.setLvSumupId(selectStatus);
						dailyRecordById.setAttStatus(selectStatusText);
					}
					dailyRecordById.setLateMark(lateMark);
					String[] othrsarry = otHours.split(":");
					int othrs = (Integer.parseInt(othrsarry[0]) * 60) + Integer.parseInt(othrsarry[1]);
					dailyRecordById.setOtHr(String.valueOf(othrs));
					DailyAttendance updateRes = dailyAttendanceRepository.save(dailyRecordById);

					info = finalUpdateDailySumaryRecord(fromDate, toDate, userId, month, year,
							dailyRecordById.getEmpId());
					// System.out.println(othrsarry[0] + " " + othrsarry[1]);
				} else {

					List<FileUploadedData> fileUploadedDataList = new ArrayList<>();
					FileUploadedData fileUploadedData = new FileUploadedData();
					fileUploadedData.setEmpCode(dailyRecordById.getEmpCode());
					fileUploadedData.setEmpName(dailyRecordById.getEmpName());
					fileUploadedData.setLogDate(DateConvertor.convertToDMY(dailyRecordById.getAttDate()));
					fileUploadedData.setInTime(inTime);
					fileUploadedData.setOutTime(outTime);
					fileUploadedDataList.add(fileUploadedData);

					DataForUpdateAttendance dataForUpdateAttendance = new DataForUpdateAttendance();
					dataForUpdateAttendance.setFromDate(dailyRecordById.getAttDate());
					dataForUpdateAttendance.setToDate(dailyRecordById.getAttDate());
					dataForUpdateAttendance.setMonth(month);
					dataForUpdateAttendance.setYear(year);
					dataForUpdateAttendance.setUserId(userId);
					dataForUpdateAttendance.setFileUploadedDataList(fileUploadedDataList);
					dataForUpdateAttendance.setEmpId(dailyRecordById.getEmpId());
					info = getVariousListForUploadAttendace(dataForUpdateAttendance);
					info = finalUpdateDailySumaryRecord(fromDate, toDate, userId, month, year,
							dailyRecordById.getEmpId());

				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/updateAttendaceOfWeeklyOffInDailyDaily" }, method = RequestMethod.POST)
	public @ResponseBody Info updateAttendaceOfWeeklyOffInDailyDaily(@RequestParam("id") int id,
			@RequestParam("userId") int userId) {

		Info info = new Info();
		try {

			WeeklyOffShit shiftWeeklyofById = weeklyOffShitRepository.shiftWeeklyofById(id);

			if (shiftWeeklyofById != null) {
				List<DailyAttendance> dailyRecordByfromDate = dailyAttendanceRepository.dailyAttendanceList(
						shiftWeeklyofById.getWeekofffromdate(), shiftWeeklyofById.getWeekofffromdate(),
						shiftWeeklyofById.getEmpId());
				List<DailyAttendance> dailyRecordByonDate = dailyAttendanceRepository.dailyAttendanceList(
						shiftWeeklyofById.getWeekoffshiftdate(), shiftWeeklyofById.getWeekoffshiftdate(),
						shiftWeeklyofById.getEmpId());
				Info fistres = changeStatusOfDailyDailyDate(dailyRecordByfromDate, shiftWeeklyofById, userId);
				Info setcondres = changeStatusOfDailyDailyDate(dailyRecordByonDate, shiftWeeklyofById, userId);
				info.setError(false);
				info.setMsg("success");
				// System.out.println(dailyRecordByDate);

			}
		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;

	}

	public Info changeStatusOfDailyDailyDate(List<DailyAttendance> dailyRecordByDate, WeeklyOffShit shiftWeeklyofById,
			int userId) {

		Info info = new Info();
		try {
			System.out.println(dailyRecordByDate.size());
			if (dailyRecordByDate.size() > 0) {

				if (dailyRecordByDate.get(0).getByFileUpdated() == 1) {

					List<FileUploadedData> fileUploadedDataList = new ArrayList<>();
					FileUploadedData fileUploadedData = new FileUploadedData();
					fileUploadedData.setEmpCode(dailyRecordByDate.get(0).getEmpCode());
					fileUploadedData.setEmpName(dailyRecordByDate.get(0).getEmpName());
					fileUploadedData.setLogDate(DateConvertor.convertToDMY(dailyRecordByDate.get(0).getAttDate()));
					fileUploadedData.setInTime(dailyRecordByDate.get(0).getInTime().substring(0,
							dailyRecordByDate.get(0).getInTime().length() - 3));
					fileUploadedData.setOutTime(dailyRecordByDate.get(0).getOutTime().substring(0,
							dailyRecordByDate.get(0).getOutTime().length() - 3));
					fileUploadedDataList.add(fileUploadedData);

					DataForUpdateAttendance dataForUpdateAttendance = new DataForUpdateAttendance();
					dataForUpdateAttendance.setFromDate(dailyRecordByDate.get(0).getAttDate());
					dataForUpdateAttendance.setToDate(dailyRecordByDate.get(0).getAttDate());
					dataForUpdateAttendance.setMonth(shiftWeeklyofById.getMonth());
					dataForUpdateAttendance.setYear(shiftWeeklyofById.getYear());
					dataForUpdateAttendance.setUserId(userId);
					dataForUpdateAttendance.setFileUploadedDataList(fileUploadedDataList);
					dataForUpdateAttendance.setEmpId(shiftWeeklyofById.getEmpId());

					// System.out.println(fileUploadedData);

					info = getVariousListForUploadAttendace(dataForUpdateAttendance);
					/*
					 * info = finalUpdateDailySumaryRecord(dailyRecordByDate.get(0).getAttDate(),
					 * dailyRecordByDate.get(0).getAttDate(), userId, shiftWeeklyofById.getMonth(),
					 * shiftWeeklyofById.getYear(), shiftWeeklyofById.getEmpId());
					 */

				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getLvTypeList" }, method = RequestMethod.GET)
	public @ResponseBody List<LvType> getLvTypeList() {

		List<LvType> lvTypeList = new ArrayList<>();
		try {

			lvTypeList = lvTypeRepository.getLvTypeList();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return lvTypeList;

	}

	@RequestMapping(value = { "/getDailyDailyRecordBetweenDateAndByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<DailyAttendance> getDailyDailyRecordBetweenDateAndByEmpId(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("empId") int empId) {

		List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
		try {

			dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceList(fromDate, toDate, empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return dailyAttendanceList;

	}

	@RequestMapping(value = { "/getDatesOfWeeklyOfForShiftingDate" }, method = RequestMethod.POST)
	public @ResponseBody List<String> getDatesOfWeeklyOfForShiftingDate(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("locationId") int locationId,
			@RequestParam("weekCatId") int weekCatId) {

		List<String> datesList = new ArrayList<>();
		try {
			List<WeeklyOff> weeklyOfflist = weeklyOffRepo.getWeeklyOffList();
			datesList = commonFunctionService.getDatesOfWeeklyOfForShiftingDate(fromDate, toDate, weeklyOfflist,
					locationId, weekCatId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return datesList;

	}

	@RequestMapping(value = { "/getEmpListForFixAttendace" }, method = RequestMethod.GET)
	public @ResponseBody List<EmpInfo> getEmpListForFixAttendace() {

		List<EmpInfo> empList = new ArrayList<>();
		try {
			empList = empInfoRepository.getEmpListAll();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return empList;

	}

	@RequestMapping(value = { "/fixAttendanceByDateOfEmpLoyee" }, method = RequestMethod.POST)
	public @ResponseBody Info fixAttendanceByDateOfEmpLoyee(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("empIds") List<Integer> empIds) {

		Info info = new Info();

		try {

			/*
			 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); Date fmdt =
			 * df.parse(fromDate); Date todt = df.parse(toDate);
			 * 
			 * for (Date j = fmdt; j.compareTo(todt) <= 0;) {
			 * 
			 * int fixDailyDailyRecord =
			 * dailyAttendanceRepository.fixDailyDailyRecord(df.format(j), empIds);
			 * j.setTime(j.getTime() + 1000 * 60 * 60 * 24); }
			 */
			int fixDailyDailyRecord = dailyAttendanceRepository.fixDailyDailyRecordBetweenDate(fromDate, toDate,
					empIds);
			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getEmployyeDailyDailyListByDeptIds" }, method = RequestMethod.POST)
	public @ResponseBody List<DailyAttendance> getEmployyeDailyDailyListByDeptIds(@RequestParam("date") String date,
			@RequestParam("desgType") int desgType, @RequestParam("departIds") List<Integer> departIds) {

		List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
		try {

			if (desgType == 1) {
				dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceByDeptId(date, departIds);
			} else if (desgType == 2) {
				dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceListAll(date, date);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return dailyAttendanceList;

	}

	@RequestMapping(value = { "/getEmployyeDailyDailyListforHr" }, method = RequestMethod.POST)
	public @ResponseBody List<DailyAttendance> getEmployyeDailyDailyListforHr(@RequestParam("date") String date,
			@RequestParam("desgType") int desgType, @RequestParam("departIds") List<Integer> departIds) {

		List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
		try {

			dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceListAll(date, date);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return dailyAttendanceList;

	}

	@RequestMapping(value = { "/updateAttendaceRecordSingleByHod" }, method = RequestMethod.POST)
	public @ResponseBody Info updateAttendaceRecordSingleByHod(@RequestParam("dailyId") int dailyId,
			@RequestParam("selectStatus") int selectStatus, @RequestParam("lateMark") String lateMark,
			@RequestParam("selectStatusText") String selectStatusText, @RequestParam("userId") int userId,
			@RequestParam("flag") int flag, @RequestParam("otHours") String otHours) {

		Info info = new Info();
		try {

			DailyAttendance dailyRecordById = dailyAttendanceRepository.getdailyRecordById(dailyId);
			String[] dts = dailyRecordById.getAttDate().split("-");

			Date firstDay = new GregorianCalendar(Integer.parseInt(dts[0]), Integer.parseInt(dts[1]) - 1, 1).getTime();
			Date lastDay = new GregorianCalendar(Integer.parseInt(dts[0]), Integer.parseInt(dts[1]), 0).getTime();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			int month = Integer.parseInt(dts[1]);
			int year = Integer.parseInt(dts[0]);

			if (dailyRecordById.getIsFixed() == 0) {

				if (flag == 0) {
					if (selectStatus != 0) {
						dailyRecordById.setLvSumupId(selectStatus);
						dailyRecordById.setAttStatus(selectStatusText);
					}
					dailyRecordById.setLateMark(lateMark);
					// System.out.println("In if");
				} else {
					// System.out.println("In else");
					String[] othrsarry = otHours.split(":");
					int othrs = (Integer.parseInt(othrsarry[0]) * 60) + Integer.parseInt(othrsarry[1]);
					dailyRecordById.setOtHr(String.valueOf(othrs));
				}

				DailyAttendance updateRes = dailyAttendanceRepository.save(dailyRecordById);
				info = finalUpdateDailySumaryRecord(sf.format(firstDay), sf.format(lastDay), userId, month, year,
						dailyRecordById.getEmpId());
				// System.out.println(othrsarry[0] + " " + othrsarry[1]);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

}
