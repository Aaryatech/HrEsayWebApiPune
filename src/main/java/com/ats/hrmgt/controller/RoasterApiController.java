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

import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.PlanHistoryDetail;
import com.ats.hrmgt.model.PlanHistoryRouteWise;
import com.ats.hrmgt.model.PlanHistoryTypeWise;
import com.ats.hrmgt.model.RouteList;
import com.ats.hrmgt.model.RouteListRepo;
import com.ats.hrmgt.model.RoutePlanDetail;
import com.ats.hrmgt.model.RoutePlanDetailWithName;
import com.ats.hrmgt.model.RoutePlanHeader;
import com.ats.hrmgt.model.RouteType;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.report.PendingLoanReport;
import com.ats.hrmgt.repo.EmpDetailForLettersRepo;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.PlanHistoryDetailRepo;
import com.ats.hrmgt.repository.PlanHistoryRouteWiseRepo;
import com.ats.hrmgt.repository.PlanHistoryTypeWiseRepo;
import com.ats.hrmgt.repository.RoutePlanDetailRepo;
import com.ats.hrmgt.repository.RoutePlanDetailWithNameRepo;
import com.ats.hrmgt.repository.RoutePlanHeaderRepo;
import com.ats.hrmgt.repository.RouteTypeRepo;
import com.ats.hrmgt.repository.SettingRepo;

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

}
