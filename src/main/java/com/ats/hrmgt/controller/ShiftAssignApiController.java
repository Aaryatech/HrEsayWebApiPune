package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.InfoForUploadAttendance;
import com.ats.hrmgt.model.ShiftAssignDaily;
import com.ats.hrmgt.model.SummaryDailyAttendance;
import com.ats.hrmgt.repo.ShiftAssignDailyRepository;
import com.ats.hrmgt.repository.EmpInfoRepository;
import com.ats.hrmgt.repository.InfoForUploadAttendanceRepository;
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

}
