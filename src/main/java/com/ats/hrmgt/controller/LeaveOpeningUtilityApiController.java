package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.OpeningPendingLeaveEmployeeList;
import com.ats.hrmgt.repo.HolidayListCatWiseRepo;
import com.ats.hrmgt.repository.OpeningPendingLeaveEmployeeListRepo;

@RestController
public class LeaveOpeningUtilityApiController {
	
	@Autowired
	OpeningPendingLeaveEmployeeListRepo openingPendingLeaveEmployeeListRepo;
	
	@RequestMapping(value = { "/getEmplistForOpeningLeave" }, method = RequestMethod.POST)
	public @ResponseBody List<OpeningPendingLeaveEmployeeList> getEmplistForOpeningLeave(@RequestParam("locId") int locId) {

		List<OpeningPendingLeaveEmployeeList> list = new ArrayList<>();
		try {

			list = openingPendingLeaveEmployeeListRepo.getEmplistForOpeningLeave(locId);
 

		} catch (Exception e) {
			 
			e.printStackTrace();
		}

		return list;

	}

}
