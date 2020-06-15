package com.ats.hrmgt.scheduler;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ats.hrmgt.controller.AttendanceApiControllerchange;
import com.ats.hrmgt.model.DataForUpdateAttendance;

@Component
public class Schedular {

	/* @Scheduled(cron = "0/5 * * * * ?") */
	/*@Scheduled(cron = "0 35 12 * * ?  ")
	public void callAttendancFuntion() {

		try {

			Date dt = new Date();
			System.out.println(dt);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
	
	/*DataForUpdateAttendance dataForUpdateAttendance = new DataForUpdateAttendance();
	AttendanceApiControllerchange attendanceApiControllerchange = new AttendanceApiControllerchange();
	attendanceApiControllerchange.getVariousListForUploadAttendace(dataForUpdateAttendance);*/

}
