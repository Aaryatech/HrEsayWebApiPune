package com.ats.hrmgt.controller;

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

import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.EmpInfo;
import com.ats.hrmgt.model.EmpJsonData;
import com.ats.hrmgt.model.EmpShiftDetails;
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
import com.ats.hrmgt.repository.EmpShiftDetailsRepo;
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

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date fmdt = df.parse(fromDate);
			Date todt = df.parse(toDate);

			System.out.println(fmdt + " " + todt);

			Calendar temp = Calendar.getInstance();
			temp.setTime(fmdt);
			int year = temp.get(Calendar.YEAR);
			int month = temp.get(Calendar.MONTH) + 1;

			List<EmpInfo> empList = empInfoRepository.getEmpListForAssignShift(fromDate, toDate);

			// List<DailyAttendance> dailyAttendanceList = new ArrayList<>();

			String dailyDailyQuery = "INSERT INTO t_shift_assign_daily (id, emp_id, emp_code, shift_id, shift_date, month,  year, extra1, extra2, var1, var2) VALUES  ";

			for (int i = 0; i < empList.size(); i++) {

				// String empName = empList.get(i).getFirstName() + " " +
				// empList.get(i).getSurname();

				fmdt = df.parse(fromDate);

				for (Date j = fmdt; j.compareTo(todt) <= 0;) {

					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					temp = Calendar.getInstance();
					temp.setTime(j);
					String attdate = sf.format(j);

					dailyDailyQuery = dailyDailyQuery + "('0', '" + empList.get(i).getEmpId() + "','"
							+ empList.get(i).getEmpCode() + "','" + 0 + "','" + attdate + "','" + month + "', '" + year
							+ "', '0', '0', NULL, NULL),";

					j.setTime(j.getTime() + 1000 * 60 * 60 * 24);

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
			@RequestParam("assignDate") String assignDate, @RequestParam("shiftId") int shiftId) {

		Info info = new Info();
		try {

			int update = shiftAssignDailyRepository.updateAssignShiftByDate(empIdList, assignDate, shiftId);
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

	@RequestMapping(value = { "/getshiftProject" }, method = RequestMethod.POST)
	public List<EmpShiftDetails> getshiftProject(@RequestParam String nextMonthDay, @RequestParam String today,
			@RequestParam int companyId, @RequestParam String empRes, @RequestParam int daysNext,
			@RequestParam int daysToday) {
		List<EmpShiftDetails> empShiftList = new ArrayList<EmpShiftDetails>();
		try {

			String[] a = today.split("-");
			String year = a[0];
			String month = a[1];
			if (month.length() == 1) {
				month = "0".concat(month);
			}

			String[] b = nextMonthDay.split("-");
			String year1 = b[0];
			String month1 = b[1];
			if (month1.length() == 1) {
				month1 = "0".concat(month1);
			}

			String fromDate = String.valueOf(year).concat("-").concat(String.valueOf(month)).concat("-").concat("01");

			String toDate1 = String.valueOf(year1).concat("-").concat(String.valueOf(month1)).concat("-")
					.concat(String.valueOf(daysNext));

			List<ShiftMaster> shiftmList = shiftMasterRepository.findByStatus(1);

			List<EmployeeMaster> emplist = new ArrayList<EmployeeMaster>();

			if (empRes.equals("-1")) {
				try {
					emplist = empRepo.findByDelStatusAndCmpCodeOrderByEmpIdDesc(1, companyId);
				} catch (Exception e) {
					System.err.println("Excep in getAllEmployee : " + e.getMessage());
					e.printStackTrace();
				}
			} else {

				try {
					emplist = empRepo.findByDelStatusAndEmpId(1, Integer.parseInt(empRes));
				} catch (Exception e) {
					System.err.println("Excep in getAllEmployee : " + e.getMessage());
					e.printStackTrace();
				}

			}

			// to get week_start_day
			Setting settingList = new Setting();
			settingList = settingRepo.findByKey("week_start_day");
			String startDay = settingList.getValue();

			for (int i = 0; i < emplist.size(); i++) {

				int empId = emplist.get(i).getEmpId();
				int locId = emplist.get(i).getLocationId();

				int currShiftId = 0;
				String currShiftName = null;

				DailyAttendance dailyRec = dailyAttendanceRepository.findLastMonthRecordOfEmp(empId);
				if (dailyRec != null) {
					currShiftId = dailyRec.getCurrentShiftid();
					currShiftName = dailyRec.getCurrentShiftname();
				} else {
					currShiftId = emplist.get(i).getCurrentShiftid();
					ShiftMaster shiftm = new ShiftMaster();
					for (int s = 0; s < shiftmList.size(); s++) {
						if (shiftmList.get(s).getId() == currShiftId) {
							shiftm = shiftmList.get(s);
							break;
						}
					}
					currShiftName = shiftm.getShiftname();

				}

				for (int j = 1; j <= daysToday; j++) {

					// System.out.println("emp "+empId+" day " + j);
					EmpShiftDetails empDet = new EmpShiftDetails();
					String day = null;
					if (String.valueOf(j).length() == 1) {
						day = "0".concat(String.valueOf(j));
					} else {
						day = String.valueOf(j);
					}
					String dateOfMonth = String.valueOf(year).concat("-").concat(String.valueOf(month)).concat("-")
							.concat(day);

					Date date2 = new SimpleDateFormat("yyyy-M-d").parse(dateOfMonth);
					String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date2);

					if (startDay.equals(dayOfWeek)) {

						ShiftMaster shiftm = new ShiftMaster();
						for (int s = 0; s < shiftmList.size(); s++) {
							if (shiftmList.get(s).getId() == currShiftId) {
								shiftm = shiftmList.get(s);
								break;
							}
						}
						if (shiftm.getChangeable() == 1) {

							currShiftId = shiftm.getChangewith();
							for (int s = 0; s < shiftmList.size(); s++) {
								if (shiftmList.get(s).getId() == currShiftId) {
									shiftm = shiftmList.get(s);
									break;
								}
							}
							currShiftName = shiftm.getShiftname();
						}

					}

					empDet.setEmpId(empId);
					empDet.setShiftId(currShiftId);
					empDet.setShiftName(currShiftName);
					empDet.setDay(j);
					empDet.setDateOfMonth(dateOfMonth);
					empDet.setLocationId(locId);
					empDet.setMonth(Integer.parseInt(month));
					empDet.setYear(Integer.parseInt(year));

					empShiftList.add(empDet);

				}

				for (int j = 1; j <= daysNext; j++) {

					// System.out.println("emp "+empId+" day " + j);
					EmpShiftDetails empDet = new EmpShiftDetails();
					String day = null;
					if (String.valueOf(j).length() == 1) {
						day = "0".concat(String.valueOf(j));
					} else {
						day = String.valueOf(j);
					}
					String dateOfMonth = String.valueOf(year1).concat("-").concat(String.valueOf(month1)).concat("-")
							.concat(day);

					Date date2 = new SimpleDateFormat("yyyy-M-d").parse(dateOfMonth);
					String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date2);

					if (startDay.equals(dayOfWeek)) {

						ShiftMaster shiftm = new ShiftMaster();
						for (int s = 0; s < shiftmList.size(); s++) {
							if (shiftmList.get(s).getId() == currShiftId) {
								shiftm = shiftmList.get(s);
								break;
							}
						}
						if (shiftm.getChangeable() == 1) {

							currShiftId = shiftm.getChangewith();
							for (int s = 0; s < shiftmList.size(); s++) {
								if (shiftmList.get(s).getId() == currShiftId) {
									shiftm = shiftmList.get(s);
									break;
								}
							}
							currShiftName = shiftm.getShiftname();
						}

					}

					empDet.setEmpId(empId);
					empDet.setShiftId(currShiftId);
					empDet.setShiftName(currShiftName);
					empDet.setDay(j);
					empDet.setDateOfMonth(dateOfMonth);
					empDet.setLocationId(locId);
					empDet.setMonth(Integer.parseInt(month1));
					empDet.setYear(Integer.parseInt(year1));

					empShiftList.add(empDet);

				}

			}

			// same ************************

			// to get weekoff of that month
			List<WeeklyOff> weeklyOfflist = weeklyOffRepo.getWeeklyOffList();
			// to get leaves of that month
			List<LeaveApply> leavetList = leaveApplyRepository.getleavetList(fromDate, toDate1);
			// System.err.println("leavetList" + leavetList.toString());
			// to get holidey of that month
			List<Holiday> holidayList = holidayRepo.getholidaybetweendate(fromDate, toDate1);
			List<WeeklyOffShit> weeklyOffShitList = weeklyOffShitRepository.getWeeklyOffShitList(fromDate, toDate1);
			List<WeeklyOffShit> weeklyOffShitFromList = weeklyOffShitRepository.weeklyOffShitFromList(fromDate,
					toDate1);

			for (int m = 0; m < empShiftList.size(); m++) {
				int holidayCat = 0;

				int WeekoffCat = 0;

				int empIdNew = empShiftList.get(m).getEmpId();
				String calcDate = empShiftList.get(m).getDateOfMonth();
				int location = empShiftList.get(m).getLocationId();
				List<LeaveApply> leavetListMain = new ArrayList<LeaveApply>();

				for (int l = 0; l < emplist.size(); l++) {

					if (emplist.get(l).getEmpId() == empIdNew) {
						holidayCat = emplist.get(l).getHolidayCategory();
						WeekoffCat = emplist.get(l).getWeekendCategory();
						break;

					}

				}

				if (leavetList != null) {
					for (int p = 0; p < leavetList.size(); p++) {
						if (leavetList.get(p).getEmpId() == empIdNew) {
							leavetListMain.add(leavetList.get(p));
						}

					}
				}

				int weekEndStatus = commonFunctionService.findDateInWeekEnd(calcDate, calcDate, weeklyOfflist,
						weeklyOffShitList, empShiftList.get(m).getLocationId(), WeekoffCat, empIdNew,
						weeklyOffShitFromList);

				int holidayStatus = commonFunctionService.findDateInHoliday(calcDate, calcDate, holidayList, location,
						holidayCat);

				//LeaveStsAndLeaveId stsInfo = commonFunctionService.findDateInLeave(calcDate, leavetListMain, empIdNew);
				
				

				if (holidayStatus == 3) {

					// System.err.println("holday"+calcDate);
					empShiftList.get(m).setShiftName("Holiday");
					empShiftList.get(m).setShiftId(-1);

				} else if (weekEndStatus == 1) {
					// System.err.println("holday"+calcDate);
					empShiftList.get(m).setShiftName("Weeklyoff");
					empShiftList.get(m).setShiftId(-2);
				}/* else if (stsInfo.getSts() == 5) {
					// System.err.println("Leave"+calcDate);
					empShiftList.get(m).setShiftName("Leave");
					empShiftList.get(m).setShiftId(-3);
				}*/

			}
			// System.err.println("daysList"+empShiftList.toString());

		} catch (Exception e) {
			System.err.println("Excep in getDesignationById : " + e.getMessage());
			e.printStackTrace();
		}

		return empShiftList;

	}

}
