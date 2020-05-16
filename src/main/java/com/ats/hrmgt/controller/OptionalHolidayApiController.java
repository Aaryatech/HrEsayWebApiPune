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

import com.ats.hrmgt.model.EmpDetailForOptionalHoliday;
import com.ats.hrmgt.model.EmpListForHolidayApprove;
import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.OptionalHoliday;
import com.ats.hrmgt.repository.EmpDetailForOptionalHolidayRepository;
import com.ats.hrmgt.repository.EmpListForHolidayApproveRepo;
import com.ats.hrmgt.repository.HolidayRepo;
import com.ats.hrmgt.repository.OptionalHolidayRepo;

@RestController
public class OptionalHolidayApiController {

	@Autowired
	EmpDetailForOptionalHolidayRepository empDetailForOptionalHolidayRepository;

	@Autowired
	HolidayRepo holidayRepo;

	@Autowired
	OptionalHolidayRepo optionalHolidayRepo;

	@Autowired
	EmpListForHolidayApproveRepo empListForHolidayApproveRepo;

	@RequestMapping(value = { "/getEmpInfoForOptionalHoliday" }, method = RequestMethod.POST)
	public @ResponseBody EmpDetailForOptionalHoliday getEmpInfoForOptionalHoliday(@RequestParam("empId") int empId,
			@RequestParam("yearId") int yearId) {

		EmpDetailForOptionalHoliday empDetailForOptionalHoliday = new EmpDetailForOptionalHoliday();
		try {

			empDetailForOptionalHoliday = empDetailForOptionalHolidayRepository.getEmpInfoForOptionalHoliday(empId,
					yearId);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return empDetailForOptionalHoliday;

	}

	@RequestMapping(value = { "/getHolidayListforoptionalHoliday" }, method = RequestMethod.POST)
	public @ResponseBody List<Holiday> getHolidayListforoptionalHoliday(@RequestParam("date") String date,
			@RequestParam("yearId") int yearId, @RequestParam("catId") int catId, @RequestParam("empId") int empId) {

		List<Holiday> list = new ArrayList<>();

		try {

			list = holidayRepo.getHolidayListforoptionalHoliday(date, yearId, catId, empId);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;

	}

	@RequestMapping(value = { "/saveApplyHoliday" }, method = RequestMethod.POST)
	public @ResponseBody OptionalHoliday saveApplyHoliday(@RequestBody OptionalHoliday optionalHoliday) {

		OptionalHoliday save = new OptionalHoliday();

		try {

			save = optionalHolidayRepo.save(optionalHoliday);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return save;

	}

	@RequestMapping(value = { "/getOptionalHolidayApprovalList" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpListForHolidayApprove> getOptionalHolidayApprovalList(@RequestParam("locId") int locId,
			@RequestParam("sts") List<Integer> sts) {

		List<EmpListForHolidayApprove> list = new ArrayList<>();

		try {

			list = empListForHolidayApproveRepo.getOptionalHolidayApprovalList(locId, sts);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;

	}

	@RequestMapping(value = { "/updateStsOfOptionalHoliday" }, method = RequestMethod.POST)
	public @ResponseBody Info updateStsOfOptionalHoliday(@RequestParam("date") String date,
			@RequestParam("sts") int sts, @RequestParam("userId") int userId ,@RequestParam("ids")  List<Integer> ids) {

		Info info = new Info();

		try {

			int update = optionalHolidayRepo.updateStsOfOptionalHoliday(date, sts, userId,ids);
			info.setError(false);
			info.setMsg("suucess");

		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");

		}

		return info;

	}
	
	@RequestMapping(value = { "/getHistoryOptionalHoliday" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpListForHolidayApprove> getHistoryOptionalHoliday(@RequestParam("empId") int empId,
			@RequestParam("yearId") int yearId,@RequestParam("sts") List<Integer> sts) {

		List<EmpListForHolidayApprove> list = new ArrayList<>();

		try {

			list = empListForHolidayApproveRepo.getHistoryOptionalHoliday(empId,yearId, sts);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;

	}

}
