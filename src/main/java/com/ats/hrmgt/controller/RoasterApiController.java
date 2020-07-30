package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
import com.ats.hrmgt.model.DataForUpdateAttendance;
import com.ats.hrmgt.model.DateAndDay;
import com.ats.hrmgt.model.EmpInfo;
import com.ats.hrmgt.model.EmpInfoWithDateInfoList;
import com.ats.hrmgt.model.EmpInfoWithDateInfoListForRaster;
import com.ats.hrmgt.model.FileUploadedData;
import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.PlanHistoryDetail;
import com.ats.hrmgt.model.PlanHistoryRouteWise;
import com.ats.hrmgt.model.PlanHistoryTypeWise;
import com.ats.hrmgt.model.RoasterSheetData;
import com.ats.hrmgt.model.RoasterSummeryDetail;
import com.ats.hrmgt.model.RoasterSummeryDetailRepository;
import com.ats.hrmgt.model.RouteList;
import com.ats.hrmgt.model.RouteListRepo;
import com.ats.hrmgt.model.RoutePlanDetail;
import com.ats.hrmgt.model.RoutePlanDetailWithName;
import com.ats.hrmgt.model.RoutePlanHeader;
import com.ats.hrmgt.model.RouteType;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.TypeWiseRoasterList;
import com.ats.hrmgt.model.report.PendingLoanReport;
import com.ats.hrmgt.repo.EmpDetailForLettersRepo;
import com.ats.hrmgt.repository.EmpInfoRepository;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.PlanHistoryDetailRepo;
import com.ats.hrmgt.repository.PlanHistoryRouteWiseRepo;
import com.ats.hrmgt.repository.PlanHistoryTypeWiseRepo;
import com.ats.hrmgt.repository.RoutePlanDetailRepo;
import com.ats.hrmgt.repository.RoutePlanDetailWithNameRepo;
import com.ats.hrmgt.repository.RoutePlanHeaderRepo;
import com.ats.hrmgt.repository.RouteTypeRepo;
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.repository.TypeWiseRoasterListRepository;

@RestController
public class RoasterApiController {

	@Autowired
	SettingRepo settingRepo;

	@Autowired
	GetEmployeeDetailsRepo getEmployeeDetailsRepo;

	@Autowired
	RoutePlanHeaderRepo routePlanHeaderRepo;

	@Autowired
	RoutePlanDetailRepo routePlanDetailRepo;

	@Autowired
	RoutePlanDetailWithNameRepo routePlanDetailWithNameRepo;

	@Autowired
	RouteTypeRepo routeTypeRepo;

	@Autowired
	RouteListRepo routeListRepo;

	@Autowired
	PlanHistoryDetailRepo planHistoryDetailRepo;

	@Autowired
	PlanHistoryTypeWiseRepo planHistoryTypeWiseRepo;

	@Autowired
	PlanHistoryRouteWiseRepo planHistoryRouteWiseRepo;

	@Autowired
	EmpInfoRepository empInfoRepository;

	@Autowired
	RoasterSummeryDetailRepository roasterSummeryDetailRepository;

	@Autowired
	TypeWiseRoasterListRepository typeWiseRoasterListRepository;

	@RequestMapping(value = { "/insertInitiallydriverInPlanRoute" }, method = RequestMethod.POST)
	public @ResponseBody Info insertInitiallydriverInPlanRoute(@RequestParam("date") String date) {

		Info info = new Info();

		Setting setting = settingRepo.findByKey("designation_id");

		try {

			RoutePlanHeader routePlanHeader = routePlanHeaderRepo.getdateexitrecord(date);

			if (routePlanHeader == null) {

				routePlanHeader = new RoutePlanHeader();
				routePlanHeader.setDelStatus(1);
				routePlanHeader.setPlanDate(date);
				routePlanHeader = routePlanHeaderRepo.save(routePlanHeader);
			}

			if (routePlanHeader.getIsConfirm() == 0) {
				List<GetEmployeeDetails> list = getEmployeeDetailsRepo
						.getlistforinsertInitiallydriverInPlanRoute(Integer.parseInt(setting.getValue()), date);

				List<RoutePlanDetail> routePlanDetaillist = new ArrayList<>();

				for (int i = 0; i < list.size(); i++) {
					RoutePlanDetail routePlanDetail = new RoutePlanDetail();
					routePlanDetail.setPlanHeadId(routePlanHeader.getPlanHeadId());
					routePlanDetail.setDriverId(list.get(i).getEmpId());
					routePlanDetail.setDelStatus(1);
					routePlanDetaillist.add(routePlanDetail);
				}

				List<RoutePlanDetail> ressavedetail = routePlanDetailRepo.saveAll(routePlanDetaillist);

				info.setError(false);
				info.setMsg("insert updated");
			} else {
				info.setError(true);
				info.setMsg("Date is confirmed");
			}

		} catch (Exception e) {

			info.setError(true);
			info.setMsg("error");
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/checkRouteDateIsConfirm" }, method = RequestMethod.POST)
	public @ResponseBody Info checkRouteDateIsConfirm(@RequestParam("date") String date) {

		Info info = new Info();

		try {

			RoutePlanHeader routePlanHeader = routePlanHeaderRepo.getdateexitrecord(date);

			if (routePlanHeader.getIsConfirm() == 0) {
				info.setError(true);
				info.setMsg("Date is not Confirm");
			} else {
				info.setError(false);
				info.setMsg("get Data");
			}

		} catch (Exception e) {

			info.setError(true);
			info.setMsg("Date is not Confirm");
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getRoasterPlanListByDate" }, method = RequestMethod.POST)
	public @ResponseBody List<RoutePlanDetailWithName> getRoasterPlanListByDate(@RequestParam("date") String date) {

		List<RoutePlanDetailWithName> list = new ArrayList<>();

		try {

			list = routePlanDetailWithNameRepo.getDriverPlanList(date);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/updateAndSubmitConfirmDateRoster" }, method = RequestMethod.POST)
	public @ResponseBody Info updateAndSubmitConfirmDateRoster(@RequestParam("date") String date) {

		Info info = new Info();

		try {

			RoutePlanHeader routePlanHeader = routePlanHeaderRepo.getdateexitrecord(date);
			routePlanHeader.setIsConfirm(1);
			routePlanHeader = routePlanHeaderRepo.save(routePlanHeader);

			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {

			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/updateRouteId" }, method = RequestMethod.POST)
	public @ResponseBody Info updateRouteId(@RequestParam("planDetailId") int planDetailId,
			@RequestParam("isFF") int isFF, @RequestParam("routeId") int routeId,
			@RequestParam("rountName") String rountName, @RequestParam("frName") String frName,
			@RequestParam("frId") String frId, @RequestParam("typeId") int typeId, @RequestParam("km") int km,
			@RequestParam("incentive") float incentive) {

		Info info = new Info();

		try {

			int update = routePlanDetailRepo.updateRouteId(planDetailId, isFF, routeId, rountName, frName, frId, typeId,
					km, incentive);

			info.setMsg("updated");
			info.setError(false);
		} catch (Exception e) {
			info.setMsg("error");
			info.setError(true);
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/updateRouteName" }, method = RequestMethod.POST)
	public @ResponseBody Info updateRouteName(@RequestParam("planDetailId") int planDetailId,
			@RequestParam("rountName") String rountName, @RequestParam("incentive") float incentive) {

		Info info = new Info();

		try {

			int update = routePlanDetailRepo.updateRouteName(planDetailId, rountName, incentive);

			info.setMsg("updated");
			info.setError(false);
		} catch (Exception e) {
			info.setMsg("error");
			info.setError(true);
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/changeLateMarkInRoaster" }, method = RequestMethod.POST)
	public @ResponseBody Info changeLateMarkInRoaster(@RequestParam("planDetailId") int planDetailId,
			@RequestParam("lateMark") int lateMark, @RequestParam("lateMin") int lateMin,
			@RequestParam("startTime") String startTime) {

		Info info = new Info();

		try {

			int update = routePlanDetailRepo.changeLateMarkInRoaster(planDetailId, lateMark, lateMin, startTime);

			info.setMsg("updated");
			info.setError(false);
		} catch (Exception e) {
			info.setMsg("error");
			info.setError(true);
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getDriverPlanList" }, method = RequestMethod.POST)
	public @ResponseBody List<RoutePlanDetailWithName> getDriverPlanList(@RequestParam("date") String date) {

		List<RoutePlanDetailWithName> list = new ArrayList<>();

		try {

			list = routePlanDetailWithNameRepo.getDriverPlanList(date);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getRouteTypeList" }, method = RequestMethod.GET)
	public @ResponseBody List<RouteType> getRouteTypeList() {

		List<RouteType> list = new ArrayList<>();

		try {

			list = routeTypeRepo.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/saveRouteType" }, method = RequestMethod.POST)
	public @ResponseBody RouteType saveRouteType(@RequestBody RouteType routeType) {

		RouteType save = new RouteType();

		try {

			save = routeTypeRepo.save(routeType);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getRouteTypeById" }, method = RequestMethod.POST)
	public @ResponseBody RouteType getRouteTypeById(@RequestParam("typeId") int typeId) {

		RouteType save = new RouteType();

		try {

			save = routeTypeRepo.findByTypeId(typeId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/deleteRouteType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteRouteType(@RequestParam("typeId") int typeId) {

		Info info = new Info();

		try {

			int delete = routeTypeRepo.deleteRouteType(typeId);
			info.setMsg("update");
			info.setError(false);
		} catch (Exception e) {
			info.setMsg("error");
			info.setError(true);
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getRouteList" }, method = RequestMethod.GET)
	public @ResponseBody List<RouteList> getRouteList() {

		List<RouteList> list = new ArrayList<>();

		try {

			list = routeListRepo.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getRouteById" }, method = RequestMethod.POST)
	public @ResponseBody RouteList getRouteById(@RequestParam("id") int id) {

		RouteList save = new RouteList();

		try {

			save = routeListRepo.findByRouteId(id);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/saveRouteList" }, method = RequestMethod.POST)
	public @ResponseBody List<RouteList> saveRouteList(@RequestBody List<RouteList> routeList) {

		List<RouteList> list = new ArrayList<>();

		try {

			list = routeListRepo.saveAll(routeList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getPlanHistoryByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody PlanHistoryDetail getPlanHistoryByEmpId(@RequestParam("empId") int empId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		PlanHistoryDetail planHistoryDetail = new PlanHistoryDetail();

		try {

			planHistoryDetail = planHistoryDetailRepo.getPlanHistoryByEmpId(empId, fromDate, toDate);

			List<PlanHistoryTypeWise> list = planHistoryTypeWiseRepo.getPlanHistoryByEmpId(empId, fromDate, toDate);
			List<PlanHistoryRouteWise> routewisePlanHistory = planHistoryRouteWiseRepo.getPlanHistoryByEmpId(empId,
					fromDate, toDate);

			planHistoryDetail.setPlanwisehistoryList(list);
			planHistoryDetail.setRoutewisePlanHistory(routewisePlanHistory);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return planHistoryDetail;

	}

	@RequestMapping(value = { "/getMonthlyRoasterSheet" }, method = RequestMethod.POST)
	public @ResponseBody RoasterSheetData getMonthlyRoasterSheet(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {

		RoasterSheetData info = new RoasterSheetData();

		try {

			Setting setting = settingRepo.findByKey("designation_id");

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date fmdt = df.parse(fromDate);
			Date todt = df.parse(toDate);

			/*
			 * System.out.println(fmdt + " " + todt);
			 * 
			 * Calendar temp = Calendar.getInstance(); temp.setTime(fmdt); int year =
			 * temp.get(Calendar.YEAR); int month = temp.get(Calendar.MONTH) + 1;
			 */

			List<EmpInfo> empList = empInfoRepository.getEmpListAllForRoaster(fromDate,
					Integer.parseInt(setting.getValue()));

			List<RoutePlanDetail> ressavedetailList = routePlanDetailRepo.getListForMonthlySheet(fromDate, toDate);
			List<String> dates = new ArrayList<>();
			List<DateAndDay> dateAndDayList = new ArrayList<>();

			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("EEE");

			for (Date j = fmdt; j.compareTo(todt) <= 0;) {

				dates.add(sf.format(j));

				DateAndDay dateAndDay = new DateAndDay();
				String stringDate = sdf.format(j);
				dateAndDay.setDate(sf.format(j));
				dateAndDay.setDay(stringDate);
				dateAndDayList.add(dateAndDay);

				/* System.out.println(sf.parse(sf.format(j))); */
				j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
			}

			List<EmpInfoWithDateInfoListForRaster> infomationList = new ArrayList<>();

			for (int i = 0; i < empList.size(); i++) {

				EmpInfoWithDateInfoListForRaster infomation = new EmpInfoWithDateInfoListForRaster();
				infomation.setEmpCode(empList.get(i).getEmpCode());
				infomation.setEmpId(empList.get(i).getEmpId());
				infomation.setEmpName(empList.get(i).getFirstName() + " " + empList.get(i).getSurname());
				List<RoutePlanDetail> dateInfo = new ArrayList<>();

				for (int j = 0; j < dates.size(); j++) {

					Date dt = sf.parse(dates.get(j));
					int find = 0;

					for (int k = 0; k < ressavedetailList.size(); k++) {

						Date attsdt = df.parse(ressavedetailList.get(k).getExtraVar1());
						if (ressavedetailList.get(k).getDriverId() == empList.get(i).getEmpId()
								&& attsdt.compareTo(dt) == 0) {

							dateInfo.add(ressavedetailList.get(k));

							find = 1;
							break;
						}

					}

					if (find == 0) {

						RoutePlanDetail dilydly = new RoutePlanDetail();
						dilydly.setExtraVar1(df.format(dt));
						dateInfo.add(dilydly);
					}

				}
				infomation.setSttsList(dateInfo);
				infomationList.add(infomation);
			}

			info.setDates(dates);
			info.setInfomationList(infomationList);
			info.setDateAndDayList(dateAndDayList);

			List<RoasterSummeryDetail> roasterSummeryDetailList = roasterSummeryDetailRepository
					.getRoasterSummeryDetail(fromDate, toDate, Integer.parseInt(setting.getValue()));

			List<TypeWiseRoasterList> typeWiseRoasterList = typeWiseRoasterListRepository
					.getRoasterSummeryDetail(fromDate, toDate);

			info.setRoasterSummeryDetailList(roasterSummeryDetailList);
			info.setTypeWiseRoasterList(typeWiseRoasterList);

			List<RouteType> routeTypelist = routeTypeRepo.findByDelStatus(1);
			info.setRouteTypelist(routeTypelist);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getMonthlyRoasterSheetByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody RoasterSheetData getMonthlyRoasterSheetByEmpId(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("empId") int empId) {

		RoasterSheetData info = new RoasterSheetData();

		try {

			List<RoasterSummeryDetail> roasterSummeryDetailList = roasterSummeryDetailRepository
					.getRoasterSummeryDetailByEmpId(fromDate, toDate, empId);

			List<TypeWiseRoasterList> typeWiseRoasterList = typeWiseRoasterListRepository
					.getRoasterSummeryDetailByEmpId(fromDate, toDate, empId);

			List<RoutePlanDetailWithName> routePlanDetailWithNamelist = routePlanDetailWithNameRepo
					.getDriverPlanListByEmpId(fromDate, toDate, empId);
			List<RouteType> routeTypelist = routeTypeRepo.findByDelStatus(1);
			info.setRouteTypelist(routeTypelist);
			info.setTypeWiseRoasterList(typeWiseRoasterList);
			info.setRoasterSummeryDetailList(roasterSummeryDetailList);
			info.setRoutePlanDetailWithNamelist(routePlanDetailWithNamelist);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	/*
	 * @RequestMapping(value = { "/getRoasterSummeryDetail" }, method =
	 * RequestMethod.POST) public @ResponseBody RoasterSheetData
	 * getRoasterSummeryDetail(@RequestParam("fromDate") String fromDate,
	 * 
	 * @RequestParam("toDate") String toDate) {
	 * 
	 * RoasterSheetData info = new RoasterSheetData();
	 * 
	 * try {
	 * 
	 * Setting setting = settingRepo.findByKey("designation_id");
	 * 
	 * List<RoasterSummeryDetail> roasterSummeryDetailList =
	 * roasterSummeryDetailRepository.getRoasterSummeryDetail(fromDate,toDate,
	 * Integer.parseInt(setting.getValue()));
	 * 
	 * List<TypeWiseRoasterList> typeWiseRoasterList = typeWiseRoasterListRepository
	 * .getRoasterSummeryDetail(fromDate,toDate);
	 * 
	 * info.setRoasterSummeryDetailList(roasterSummeryDetailList);
	 * info.setTypeWiseRoasterList(typeWiseRoasterList);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return info;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/sendNotificatinBetweenDateForPlanRoute" }, method = RequestMethod.POST)
	public @ResponseBody Info sendNotificatinBetweenDateForPlanRoute(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {

		Info info = new Info();

		try {

			Setting setting = settingRepo.findByKey("designation_id");

			List<EmpInfo> empList = empInfoRepository.getEmpListAllForRoaster(fromDate,
					Integer.parseInt(setting.getValue()));

			List<RoutePlanDetail> ressavedetailList = routePlanDetailRepo.getListForNotification(fromDate, toDate);

			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");

			for (int i = 0; i < empList.size(); i++) {

				String msg = "Hi " + empList.get(i).getFirstName() + " " + empList.get(i).getSurname()
						+ ",\n your duties \n";

				for (int k = 0; k < ressavedetailList.size(); k++) {

					if (ressavedetailList.get(k).getDriverId() == empList.get(i).getEmpId()) {

						msg = msg + " " + DateConvertor.convertToDMY(ressavedetailList.get(k).getExtraVar1()) + " - ";

						if (ressavedetailList.get(k).getRouteId() != 0) {
							msg = msg + "" + ressavedetailList.get(k).getRouteName() + "\n";
						} else if (ressavedetailList.get(k).getIsoffdayIsff() == 1) {
							msg = msg + "FF \n";

						} else if (ressavedetailList.get(k).getIsoffdayIsff() == 2) {
							msg = msg + "Off Day \n";

						} else {
							msg = msg + "NA \n";
						}

					}

				}

				System.out.println(msg);

			}

			info.setError(false);
			info.setMsg("successfully Send Notification");
		} catch (Exception e) {

			info.setError(true);
			info.setMsg("error to Send Notification");
			e.printStackTrace();
		}

		return info;

	}

	@Scheduled(cron = "0 30 18 * * ? ")
	/* @Scheduled(cron = "0/2 * * * * ? ") */
	public void callRouteFuntion() {

		try {

			Date dt = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
			dt.setTime(dt.getTime() + 1000 * 60 * 60 * 24);
			String dateyy = sf.format(dt);
			String datedd = dd.format(dt);

			List<RoutePlanDetailWithName> routePlanDetailWithNamelist = routePlanDetailWithNameRepo
					.getDriverListForNextDaySchedule(dateyy);

			for (int i = 0; i < routePlanDetailWithNamelist.size(); i++) {

				String msg = "Tommorow your duty on ";

				if (routePlanDetailWithNamelist.get(i).getRouteId() != 0) {
					msg = msg + "" + routePlanDetailWithNamelist.get(i).getRouteName();
				} else if (routePlanDetailWithNamelist.get(i).getIsoffdayIsff() == 1) {
					msg = msg + "FF";

				} else if (routePlanDetailWithNamelist.get(i).getIsoffdayIsff() == 2) {
					msg = "Tommorow is your Off Day";

				}

				System.out.println(msg + " " + routePlanDetailWithNamelist.get(i).getExtraVar1());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
