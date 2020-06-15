package com.ats.hrmgt.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ats.hrmgt.controller.AttendanceApiControllerchange;
import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.DataForUpdateAttendance;
import com.ats.hrmgt.model.FileUploadedData;
import com.ats.hrmgt.repository.DailyAttendanceRepository;

@Component
public class Schedular {

	/* @Scheduled(cron = "0/5 * * * * ?") */

	@Autowired
	DailyAttendanceRepository dailyAttendanceRepository;

	/*@Scheduled(cron = "0/10 * * * * ?  ")
	public void callAttendancFuntion() {

		try {

			Date dt = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");

			List<DailyAttendance> dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceListAll(sf.format(dt),
					sf.format(dt));

			Calendar a = Calendar.getInstance();
			a.setTime(dt);
			int year = a.get(Calendar.YEAR);
			int month = a.get(Calendar.MONTH) + 1;

			DataForUpdateAttendance dataForUpdateAttendance = new DataForUpdateAttendance();
			dataForUpdateAttendance.setFromDate(sf.format(dt));
			dataForUpdateAttendance.setToDate(sf.format(dt));
			dataForUpdateAttendance.setEmpId(0);
			dataForUpdateAttendance.setUserId(1);
			dataForUpdateAttendance.setMonth(month);
			dataForUpdateAttendance.setYear(year);
			List<FileUploadedData> fileUploadedDataList = new ArrayList<>();

			for (int i = 0; i < dailyAttendanceList.size(); i++) {
				FileUploadedData fileUploadedData = new FileUploadedData();
				fileUploadedData.setEmpCode(dailyAttendanceList.get(i).getEmpCode());
				fileUploadedData.setEmpName(dailyAttendanceList.get(i).getEmpName());
				fileUploadedData.setLogDate(dd.format(dt));
				fileUploadedData.setInTime("00:00:00");
				fileUploadedData.setOutTime("00:00:00");
				fileUploadedDataList.add(fileUploadedData);
			}
			dataForUpdateAttendance.setFileUploadedDataList(fileUploadedDataList);

			AttendanceApiControllerchange attendanceApiControllerchange = new AttendanceApiControllerchange();
			attendanceApiControllerchange.getVariousListForUploadAttendace(dataForUpdateAttendance);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

}
