package com.ats.hrmgt.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model.GetHoliday;
import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.HolidayListCatWise;
import com.ats.hrmgt.model.HolidayMaster;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveAuthority;
import com.ats.hrmgt.model.Location;
import com.ats.hrmgt.repo.HolidayListCatWiseRepo;
import com.ats.hrmgt.repo.HolidayMasterRepo;
import com.ats.hrmgt.repository.CalculateYearRepository;
import com.ats.hrmgt.repository.GetHolidayRepo;
import com.ats.hrmgt.repository.HolidayCategoryRepo;
import com.ats.hrmgt.repository.HolidayRepo;
import com.ats.hrmgt.repository.LeaveAuthorityRepository;
import com.ats.hrmgt.repository.LocationRepository;

@RestController
public class LeaveHolidayApiCon {

	@Autowired
	HolidayRepo holidayRepo;

	@Autowired
	GetHolidayRepo getHolidayRepo;

	@Autowired
	LeaveAuthorityRepository leaveAuthorityRepository;

	@Autowired
	CalculateYearRepository calculateYearRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	HolidayMasterRepo holidayMasterRepo;

	@Autowired
	HolidayListCatWiseRepo holidayListCatWiseRepo;

	// -----------Holiday-----------------------
	@RequestMapping(value = { "/saveHoliday" }, method = RequestMethod.POST)
	public @ResponseBody Holiday saveHoliday(@RequestBody Holiday holiday) {

		Holiday save = new Holiday();
		try {

			save = holidayRepo.saveAndFlush(holiday);

			if (save != null) {
				save.setError(false);
			} else {

				save = new Holiday();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new Holiday();
			save.setError(true);
			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/saveHolidayList" }, method = RequestMethod.POST)
	public @ResponseBody Info saveHolidayList(@RequestBody List<Holiday> holiday) {

		Info info = new Info();
		try {

			List<Holiday> save = holidayRepo.saveAll(holiday);

			if (save != null) {
				info.setError(false);
			} else {

				info.setError(true);
			}

		} catch (Exception e) {
			info.setError(true);
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getHolidayList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetHoliday> getHolidayList(@RequestParam("companyId") int companyId) {

		List<GetHoliday> list = new ArrayList<GetHoliday>();
		try {

			list = getHolidayRepo.getHolidayListByCompany(companyId);

			for (int i = 0; i < list.size(); i++) {

				List<Integer> locIds = Stream.of(list.get(i).getLocId().split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());
				List<Location> locList = new ArrayList<>();

				locList = locationRepository.findByDelStatusAndIsActiveAndLocIdIn(1, 1, locIds);

				String locName = "";
				int x = 0;
				for (int j = 0; j < locList.size(); j++) {

					locName = locList.get(j).getLocName() + "," + locName;
					if (locList.size() > 1)
						x = 1;

				}
				if (x == 1)
					list.get(i).setLocName(locName.substring(0, locName.length() - 1));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getHolidayCategoryListGroupBy" }, method = RequestMethod.GET)
	public @ResponseBody List<HolidayListCatWise> getHolidayCategoryListGroupBy() {

		List<HolidayListCatWise> list = new ArrayList<HolidayListCatWise>();
		try {

			List<GetHoliday> getHolidaylist = getHolidayRepo.getHolidayListByCompany(1);

			list = holidayListCatWiseRepo.getHolidayCategoryListGroupBy();

			for (int i = 0; i < list.size(); i++) {

				List<GetHoliday> hollist = new ArrayList<>();

				for (int j = 0; j < getHolidaylist.size(); j++) {

					if (getHolidaylist.get(j).getCalYrId() == list.get(i).getCalYrId()
							&& list.get(i).getCatId() == getHolidaylist.get(j).getHoCatId()) {
						hollist.add(getHolidaylist.get(j));
					}

				}
				list.get(i).setHolidaylist(hollist);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getHolidayListByDates" }, method = RequestMethod.POST)
	public @ResponseBody List<Holiday> getHolidayListByDates(@RequestParam("dates") List<String> dates,
			@RequestParam("holcatId") int holcatId) {

		List<Holiday> list = new ArrayList<Holiday>();
		try {

			list = holidayRepo.getHolidayListByDates(dates, holcatId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getHolidayById" }, method = RequestMethod.POST)
	public @ResponseBody Holiday getHolidayById(@RequestParam("holidayId") int holidayId) {

		Holiday holiday = new Holiday();
		try {

			holiday = holidayRepo.findByHolidayIdAndDelStatus(holidayId, 1);
			/*
			 * holiday.setHolidayFromdt(DateConvertor.convertToDMY(holiday.getHolidayFromdt(
			 * )));
			 * holiday.setHolidayTodt(DateConvertor.convertToDMY(holiday.getHolidayTodt()));
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}

		return holiday;

	}

	
	@RequestMapping(value = { "/getHolidayByYearIdAndCateId" }, method = RequestMethod.POST)
	public @ResponseBody List<Holiday> getHolidayByYearIdAndCateId(@RequestParam("yearId") int yearId,
			@RequestParam("catId") int catId) {

		List<Holiday> holiday = new ArrayList<>();
		try {

			holiday = holidayRepo.getHolidayByYearIdAndCateId(yearId, catId);
			/*
			 * holiday.setHolidayFromdt(DateConvertor.convertToDMY(holiday.getHolidayFromdt(
			 * )));
			 * holiday.setHolidayTodt(DateConvertor.convertToDMY(holiday.getHolidayTodt()));
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}

		return holiday;

	}

	@RequestMapping(value = { "/deleteHoliday" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteHoliday(@RequestParam("holidayId") int holidayId) {

		Info info = new Info();

		try {

			int delete = holidayRepo.deleteHoliday(holidayId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}

	@RequestMapping(value = { "/deleteHolidayByGroup" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteHolidayByGroup(@RequestParam("catid") int catid,
			@RequestParam("yearId") int yearId) {

		Info info = new Info();

		try {

			int delete = holidayRepo.deleteHolidayByGroup(yearId, catid);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}

	// -----------Leave Authority-----------------------

	@RequestMapping(value = { "/saveLeaveAuthority" }, method = RequestMethod.POST)
	public @ResponseBody LeaveAuthority saveLeaveAuthority(@RequestBody LeaveAuthority leavesAllotment) {

		LeaveAuthority save = new LeaveAuthority();
		try {

			save = leaveAuthorityRepository.saveAndFlush(leavesAllotment);

			if (save != null) {
				save.setError(false);
			} else {

				save = new LeaveAuthority();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new LeaveAuthority();
			save.setError(true);

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLeaveAuthorityListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody LeaveAuthority getLeaveAuthorityListByEmpId(@RequestParam("empId") int empId) {

		LeaveAuthority list = new LeaveAuthority();
		try {

			list = leaveAuthorityRepository.findByDelStatusAndEmpId(1, empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLeaveAuthorityList" }, method = RequestMethod.GET)
	public @ResponseBody List<LeaveAuthority> getLeaveAuthorityList() {

		List<LeaveAuthority> list = new ArrayList<LeaveAuthority>();
		try {

			list = leaveAuthorityRepository.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteLeaveAuthority" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveAuthority(@RequestParam("laPkey") int laPkey) {

		Info info = new Info();

		try {

			int delete = leaveAuthorityRepository.deleteLeaveAuthority(laPkey);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}

	@RequestMapping(value = { "getLeaveAuthorityById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveAuthority getLeaveAuthorityById(@RequestParam("laPkey") int laPkey) {

		LeaveAuthority leaveAuthority = new LeaveAuthority();
		try {

			leaveAuthority = leaveAuthorityRepository.findByLaPkeyAndDelStatus(laPkey, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveAuthority;

	}

	@RequestMapping(value = { "/saveHolidayMaster" }, method = RequestMethod.POST)
	public @ResponseBody HolidayMaster saveHolidayMaster(@RequestBody HolidayMaster holiday) {

		HolidayMaster save = new HolidayMaster();
		try {

			save = holidayMasterRepo.saveAndFlush(holiday);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getHolidayMaster" }, method = RequestMethod.GET)
	public @ResponseBody List<HolidayMaster> getHolidayMaster() {

		List<HolidayMaster> list = new ArrayList<>();
		try {

			list = holidayMasterRepo.findByDelStatusOrder(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getcountofholidaybyyear" }, method = RequestMethod.POST)
	public @ResponseBody Info getcountofholidaybyyear(@RequestParam("yearId") int yearId,
			@RequestParam("catId") int catId) {

		Info info = new Info();
		try {

			String count = holidayMasterRepo.getcountofholidaybyyear(catId, yearId);
			info.setMsg(count);
			if (count.equals("0")) {
				info.setError(false);
			} else {
				info.setError(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getHolidayMasterById" }, method = RequestMethod.POST)
	public @ResponseBody HolidayMaster getHolidayMasterById(@RequestParam("holidayId") int holidayId) {

		HolidayMaster holiday = new HolidayMaster();
		try {

			holiday = holidayMasterRepo.findByHolidayIdAndDelStatus(holidayId, 1);
			/*
			 * holiday.setHolidayFromdt(DateConvertor.convertToDMY(holiday.getHolidayFromdt(
			 * )));
			 * holiday.setHolidayTodt(DateConvertor.convertToDMY(holiday.getHolidayTodt()));
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}

		return holiday;

	}

	@RequestMapping(value = { "/deleteHolidayMaster" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteHolidayMaster(@RequestParam("holidayId") int holidayId) {

		Info info = new Info();

		try {

			int delete = holidayMasterRepo.deleteHoliday(holidayId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}

	// Sachin 30-04-2020
	@RequestMapping(value = { "/getHolidayCountsByDate" }, method = RequestMethod.POST)
	public @ResponseBody Integer getHolidayCountsByDate(@RequestParam int holidayId,
			@RequestParam String holidaytDate) {
		int holidayCount = 0;
		try {
			if (holidayId < 1) {
				holidayCount = holidayMasterRepo.getCountOfHolidayByDate(holidaytDate);
			} else {
				holidayCount = holidayMasterRepo.getCountOfHolidayByDateForEdit(holidaytDate, holidayId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			holidayCount = 1;
		}
		System.err.println("holidayCount " + holidayCount);
		return holidayCount;
	}

}
