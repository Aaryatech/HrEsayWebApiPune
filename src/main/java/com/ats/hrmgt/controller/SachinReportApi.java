package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.InfoForUploadAttendance;
import com.ats.hrmgt.model.report.HodDashboard;
import com.ats.hrmgt.repo.report.HodDashboardRepo;

@RestController
public class SachinReportApi {

	@Autowired HodDashboardRepo hodDashbRepo;
	//Sachin  04-09-2020
	@RequestMapping(value = { "/getHodDashboard" }, method = RequestMethod.POST)
	public @ResponseBody List<HodDashboard> getHodDashboard(
		 	@RequestParam("deptIdList")  List<Integer> deptIdList, @RequestParam("locIdList") List<Integer> locIdList,
			@RequestParam("cmFromDate") String cmFromDate, @RequestParam("cmToDate") String cmToDate,
			@RequestParam("pmFromDate") String pmFromDate, @RequestParam("pmToDate") String pmToDate) {

		List<HodDashboard> dashBList = new ArrayList<>();
		
		try {

			dashBList = hodDashbRepo.getHodDashboardByDeptLocIds(deptIdList, locIdList, cmFromDate, cmToDate, pmFromDate, pmToDate);

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return dashBList;
	}
	
}
