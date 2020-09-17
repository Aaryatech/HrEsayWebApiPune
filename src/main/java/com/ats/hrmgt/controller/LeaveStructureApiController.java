package com.ats.hrmgt.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.CalenderYear;
import com.ats.hrmgt.model.EmployeeLeaveDetail;
import com.ats.hrmgt.model.GetLeaveApplyAuthwise;
import com.ats.hrmgt.model.GetLeaveAuthority;
import com.ats.hrmgt.model.GetLeaveStatus;
import com.ats.hrmgt.model.GetStructureAllotment;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveBalanceCal;
import com.ats.hrmgt.model.LeaveCashReport;
import com.ats.hrmgt.model.LeaveStructureDetails;
import com.ats.hrmgt.model.LeaveStructureHeader;
import com.ats.hrmgt.model.LeavesAllotment;
import com.ats.hrmgt.repo.LeaveCashReportRepository;
import com.ats.hrmgt.repository.CalculateYearRepository;
import com.ats.hrmgt.repository.EmployeeLeaveDetailRepo;
import com.ats.hrmgt.repository.GetLeaveApplyAuthwiseRepo;
import com.ats.hrmgt.repository.GetLeaveAuthorityRepo;
import com.ats.hrmgt.repository.GetLeaveStatusRepo;
import com.ats.hrmgt.repository.GetStructureAllotmentRepo;
import com.ats.hrmgt.repository.LeaveAllotmentRepository;
import com.ats.hrmgt.repository.LeaveBalanceCalRepo;
import com.ats.hrmgt.repository.LeaveStructureDetailsRepo;
import com.ats.hrmgt.repository.LeaveStructureHeaderRepo;
import com.ats.hrmgt.repository.LeavesAllotmentRepo;

@RestController
public class LeaveStructureApiController {

	@Autowired
	LeaveStructureDetailsRepo leaveStructureDetailsRepo;

	@Autowired
	LeaveStructureHeaderRepo leaveStructureHeaderRepo;

	@Autowired
	GetStructureAllotmentRepo getStructureAllotmentRepo;

	@Autowired
	GetLeaveAuthorityRepo getLeaveAuthorityRepo;

	@Autowired
	CalculateYearRepository calculateYearRepository;

	@Autowired
	LeaveBalanceCalRepo leaveBalanceCalRepo;

	@Autowired
	LeaveAllotmentRepository leaveAllotmentRepository;

	@Autowired
	EmployeeLeaveDetailRepo employeeLeaveDetailRepo;

	@Autowired
	GetLeaveApplyAuthwiseRepo getLeaveApplyAuthwiseRepo;

	@Autowired
	GetLeaveStatusRepo getLeaveStatusRepo;

	@Autowired
	LeaveCashReportRepository leaveCashReportRepository;

	// ----------------------Leave balance Cal---------------------

	@RequestMapping(value = { "/saveLeaveBalanceCal" }, method = RequestMethod.POST)
	public @ResponseBody LeaveBalanceCal saveLeaveBalanceCal(@RequestBody LeaveBalanceCal leaveBalanceCal) {

		LeaveBalanceCal save = new LeaveBalanceCal();
		try {

			save = leaveBalanceCalRepo.saveAndFlush(leaveBalanceCal);

			if (save != null) {
				save.setError(false);
			} else {

				save = new LeaveBalanceCal();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new LeaveBalanceCal();
			save.setError(true);
			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/saveNewBalRecord" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveBalanceCal> saveNewLeaveAllotmentWith(
			@RequestBody List<LeaveBalanceCal> leavesAllotment) {

		List<LeaveBalanceCal> leaveBalanccRes = new ArrayList<>();

		try {

			leaveBalanccRes = leaveBalanceCalRepo.saveAll(leavesAllotment);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return leaveBalanccRes;

	}
	/*
	 * @RequestMapping(value = { "/getLeavebalByEmpIdList" }, method =
	 * RequestMethod.POST) public @ResponseBody List<LeaveBalanceCal>
	 * getLeavebalByEmpIdList(@RequestParam("empId") int empId) {
	 * 
	 * CalenderYear calYear = new CalenderYear(); calYear =
	 * calculateYearRepository.findByIsCurrent(1); List<LeaveBalanceCal> list = new
	 * ArrayList<LeaveBalanceCal>(); try {
	 * 
	 * list =
	 * leaveBalanceCalRepo.findByCalYrIdAndEmpIdAndDelStatusAndIsActive(calYear.
	 * getCalYrId() ,empId,1,1);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 */

	/*
	 * @RequestMapping(value = { "/updateLeaveBalCal" }, method =
	 * RequestMethod.POST) public @ResponseBody Info
	 * updateLeaveBalCal(@RequestParam("lvTypeId") int
	 * lvTypeId,@RequestParam("empId") int empId,@RequestParam("noDays") int noDays)
	 * {
	 * 
	 * Info info = new Info(); CalenderYear calYear = new CalenderYear(); calYear =
	 * calculateYearRepository.findByIsCurrent(1); try {
	 * 
	 * int delete =
	 * leaveBalanceCalRepo.updateLeaveBalCal(lvTypeId,empId,calYear.getCalYrId(),
	 * noDays);
	 * 
	 * if (delete > 0) { info.setError(false); info.setMsg("deleted"); } else {
	 * info.setError(true); info.setMsg("failed"); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); info.setError(true); info.setMsg("failed"); }
	 * 
	 * return info;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/getLeaveAuthorityList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveAuthority> getLeaveAuthorityList(@RequestParam("companyId") int companyId,
			@RequestParam("locIdList") List<Integer> locIdList) {

		List<GetLeaveAuthority> list = new ArrayList<GetLeaveAuthority>();
		try {

			list = getLeaveAuthorityRepo.getLeaveAuth(locIdList);

			/*
			 * for (int i = 0; i < list.size(); i++) {
			 * 
			 * String[] reportIds = list.get(i).getRepToEmpIds().split(",");
			 * 
			 * List<String> name = getLeaveAuthorityRepo.getEmpReportingName(reportIds);
			 * list.get(i).setRePortingName(name); }
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	/*
	 * @Autowired SettingRepo settingRepo;
	 */

	@RequestMapping(value = { "/getStructureAllotmentList" }, method = RequestMethod.GET)
	public @ResponseBody List<GetStructureAllotment> getStructureAllotmentList() {

		List<GetStructureAllotment> list = new ArrayList<GetStructureAllotment>();
		try {

			CalenderYear calYear = new CalenderYear();
			calYear = calculateYearRepository.findByIsCurrent(1);

			list = getStructureAllotmentRepo.getStructureAllotment(calYear.getCalYrId());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getStructureAllotmentListLocId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetStructureAllotment> getStructureAllotmentListLocId(
			@RequestParam("locId") List<Integer> locId) {

		List<GetStructureAllotment> list = new ArrayList<GetStructureAllotment>();
		try {

			CalenderYear calYear = new CalenderYear();
			calYear = calculateYearRepository.findByIsCurrent(1);

			list = getStructureAllotmentRepo.getStructureAllotmentListLocId(calYear.getCalYrId(), locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "getLeaveAllotmentByCurrentCalender" }, method = RequestMethod.GET)
	public @ResponseBody List<LeavesAllotment> getLeaveAllotmentByCurrentCalender() {

		List<LeavesAllotment> leavesAllotment = new ArrayList<>();
		try {

			CalenderYear calYear = new CalenderYear();
			calYear = calculateYearRepository.findByIsCurrent(1);
			leavesAllotment = leaveAllotmentRepository.findByCalYrId(calYear.getCalYrId());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leavesAllotment;

	}

	@RequestMapping(value = { "/saveLeaveAllotment" }, method = RequestMethod.POST)
	public @ResponseBody LeavesAllotment saveLeaveAllotment(@RequestBody LeavesAllotment leavesAllotment) {

		LeavesAllotment save = new LeavesAllotment();

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			String dateTime = dateFormat.format(now);

			save = leaveAllotmentRepository.saveAndFlush(leavesAllotment);
			if (save != null) {

				save.setError(false);

				List<LeaveStructureDetails> leaveStructureDetailsList = leaveStructureDetailsRepo
						.findByLvsIdAndDelStatus(save.getLvsId(), 1);

				for (int j = 0; j < leaveStructureDetailsList.size(); j++) {

					LeaveBalanceCal leaveBalanceCal = new LeaveBalanceCal();

					leaveBalanceCal.setCalYrId(save.getCalYrId());
					leaveBalanceCal.setDelStatus(1);
					leaveBalanceCal.setEmpId(save.getEmpId());
					leaveBalanceCal.setIsActive(1);
					leaveBalanceCal.setLvAlloted(0);
					leaveBalanceCal.setLvbalId(0);
					leaveBalanceCal.setLvCarryFwd(0);
					leaveBalanceCal.setLvCarryFwdRemarks("Null");
					leaveBalanceCal.setLvEncash(0);
					leaveBalanceCal.setOpBal(0);
					leaveBalanceCal.setMakerUserId(1);
					leaveBalanceCal.setMakerEnterDatetime(dateTime);
					leaveBalanceCal.setLvEncashRemarks("Null");

					leaveBalanceCal.setLvTypeId(leaveStructureDetailsList.get(j).getLvTypeId());

					LeaveBalanceCal leaveBalanccRes = leaveBalanceCalRepo.saveAndFlush(leaveBalanceCal);
				}

			} else {

				save = new LeavesAllotment();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new LeavesAllotment();
			save.setError(true);
			e.printStackTrace();
		}
		return save;

	}

	@RequestMapping(value = { "/getLeaveAllotmentByKey" }, method = RequestMethod.POST)
	public @ResponseBody LeavesAllotment getLeaveAllotmentByKey(@RequestParam("lvsaPkey") int lvsaPkey) {

		LeavesAllotment list = new LeavesAllotment();
		try {

			list = leaveAllotmentRepository.getLeaveAllotmentByKey(lvsaPkey);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/updateLeaveStructure" }, method = RequestMethod.POST)
	public @ResponseBody Info updateLeaveStructure(@RequestParam("lvsaPkey") int lvsaPkey,
			@RequestParam("lvsId") int lvsId, @RequestParam("empId") int empId, @RequestParam("yearId") int yearId) {

		Info info = new Info();
		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			String dateTime = dateFormat.format(now);

			int update = leaveAllotmentRepository.updateLeaveStructureBylvsaPkey(lvsId, lvsaPkey);
			List<LeaveStructureDetails> leaveStructureDetailsList = leaveStructureDetailsRepo
					.findByLvsIdAndDelStatus(lvsId, 1);

			List<LeaveBalanceCal> balList = leaveBalanceCalRepo.findByCalYrIdAndEmpId(yearId, empId);

			for (int i = 0; i < leaveStructureDetailsList.size(); i++) {

				int flag = 0;

				for (int j = 0; j < balList.size(); j++) {

					if (balList.get(j).getLvTypeId() == leaveStructureDetailsList.get(i).getLvTypeId()) {

						flag = 1;
						break;
					}

				}

				if (flag == 0) {
					LeaveBalanceCal leaveBalanceCal = new LeaveBalanceCal();

					leaveBalanceCal.setCalYrId(yearId);
					leaveBalanceCal.setDelStatus(1);
					leaveBalanceCal.setEmpId(empId);
					leaveBalanceCal.setIsActive(1);
					leaveBalanceCal.setLvAlloted(0);
					leaveBalanceCal.setLvbalId(0);
					leaveBalanceCal.setLvCarryFwd(0);
					leaveBalanceCal.setLvCarryFwdRemarks("Null");
					leaveBalanceCal.setLvEncash(0);
					leaveBalanceCal.setOpBal(0);
					leaveBalanceCal.setMakerUserId(1);
					leaveBalanceCal.setMakerEnterDatetime(dateTime);
					leaveBalanceCal.setLvEncashRemarks("Null");
					leaveBalanceCal.setLvTypeId(leaveStructureDetailsList.get(i).getLvTypeId());
					balList.add(leaveBalanceCal);

				}

			}

			List<LeaveBalanceCal> leaveBalanccRes = leaveBalanceCalRepo.saveAll(balList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	// ----------------------Leave balance Structure--------------------

	@RequestMapping(value = { "/saveLeaveStruture" }, method = RequestMethod.POST)
	public @ResponseBody LeaveStructureHeader saveLeaveStruture(
			@RequestBody LeaveStructureHeader leaveStructureHeader) {

		LeaveStructureHeader leaveHeader = new LeaveStructureHeader();
		try {

			leaveHeader = leaveStructureHeaderRepo.save(leaveStructureHeader);

			for (int i = 0; i < leaveStructureHeader.getDetailList().size(); i++) {

				leaveStructureHeader.getDetailList().get(i).setLvsId(leaveStructureHeader.getLvsId());

			}

			List<LeaveStructureDetails> docTermDetailsList = leaveStructureDetailsRepo
					.saveAll(leaveStructureHeader.getDetailList());
			leaveHeader.setDetailList(docTermDetailsList);

			if (leaveHeader != null) {
				leaveHeader.setError(false);
			} else {

				leaveHeader = new LeaveStructureHeader();
				leaveHeader.setError(true);
			}

		} catch (Exception e) {
			leaveHeader = new LeaveStructureHeader();
			leaveHeader.setError(true);
			e.printStackTrace();
		}

		return leaveHeader;

	}

	@RequestMapping(value = { "/getStructureList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveStructureHeader> getStructureList(@RequestParam("companyId") int companyId) {

		List<LeaveStructureHeader> list = new ArrayList<LeaveStructureHeader>();
		try {

			list = leaveStructureHeaderRepo.findByDelStatusAndCompanyIdOrderByLvsIdDesc(1, companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	/*
	 * @RequestMapping(value = { "/getStructureDetailsList" }, method =
	 * RequestMethod.POST) public @ResponseBody List<LeaveStructureDetails>
	 * getStructureDetailsList(@RequestParam("lvsId") int lvsId) { Setting setting =
	 * new Setting(); List<LeaveStructureDetails> list = new
	 * ArrayList<LeaveStructureDetails>(); try {
	 * 
	 * setting = settingRepo.findByKey("probLeaveStructure"); int lvsId1 =
	 * Integer.parseInt(setting.getValue()); System.out.println(lvsId);
	 * 
	 * 
	 * list = leaveStructureDetailsRepo.findByLvsIdAndDelStatus(lvsId1, 1);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 */

	@Autowired
	LeavesAllotmentRepo leavesAllotmentRepo;

	@RequestMapping(value = { "/getStructureByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody LeavesAllotment getStructureByEmpId(@RequestParam("empId") int empId,
			@RequestParam("currYrId") int currYrId) {

		LeavesAllotment list = new LeavesAllotment();
		try {

			list = leavesAllotmentRepo.findByDelStatusAndEmpIdAndCalYrId(1, empId, currYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getStructureById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveStructureHeader getStructureById(@RequestParam("lvsId") int lvsId) {

		LeaveStructureHeader lvs = new LeaveStructureHeader();
		try {

			lvs = leaveStructureHeaderRepo.findByLvsIdAndDelStatus(lvsId, 1);

			List<LeaveStructureDetails> detailList = leaveStructureDetailsRepo.findByLvsIdAndDelStatus(lvsId, 1);
			lvs.setDetailList(detailList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return lvs;

	}

	@RequestMapping(value = { "/deleteLeaveStructure" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveStructure(@RequestParam("lvsId") int lvsId) {

		Info info = new Info();

		try {
			List<LeavesAllotment> lvsList = leavesAllotmentRepo.findByLvsIdAndDelStatus(lvsId, 1);
			if (lvsList.size() <= 0) {

				int delete = leaveStructureHeaderRepo.deleteLeaveStructure(lvsId);

				if (delete > 0) {
					info.setError(false);
					info.setMsg("Leave Structure Deleted Successfully");
				} else {
					info.setError(true);
					info.setMsg("Failed To Delete Leave Structure");
				}
			}

			else {
				info.setError(true);
				info.setMsg("Leave Structure Can't be Deleted as it is Asigned to Employee");
			}

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Failed To Delete Leave Structure");
			System.err.println("Excep in deleteBank : " + e.getMessage());
			e.printStackTrace();
		}
		return info;

	}

	@RequestMapping(value = { "getLeaveListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeLeaveDetail> getLeaveListByEmpId(@RequestParam("empId") int empId) {

		List<EmployeeLeaveDetail> employeeInfo = new ArrayList<EmployeeLeaveDetail>();

		try {

			employeeInfo = employeeLeaveDetailRepo.getLeaveListByEmp(empId);

			System.out.println("info" + employeeInfo);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return employeeInfo;

	}

	@RequestMapping(value = { "/getLeaveApplyDetailsByLeaveId" }, method = RequestMethod.POST)
	public @ResponseBody GetLeaveApplyAuthwise getLeaveApplyDetailsByLeaveId(@RequestParam("leaveId") int leaveId) {
		GetLeaveApplyAuthwise list = new GetLeaveApplyAuthwise();

		try {

			list = getLeaveApplyAuthwiseRepo.getLeaveApplyDetails(leaveId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getStatusForFreezeMonth" }, method = RequestMethod.POST)
	public @ResponseBody Info getStatusForFreezeMonth(@RequestParam("empId") int empId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		Info info = new Info();

		try {

			int count = getLeaveApplyAuthwiseRepo.getStatusForFreezeMonth(empId, fromDate, toDate);
			info.setMsg(String.valueOf(count));
			info.setError(false);
		} catch (Exception e) {
			info.setMsg("0");
			info.setError(true);
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getEmpInfoListByTrailEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveStatus> getEmpInfoListByTrailEmpId(@RequestParam("leaveId") int leaveId) {

		List<GetLeaveStatus> leaveStatus = new ArrayList<GetLeaveStatus>();
		try {
			leaveStatus = getLeaveStatusRepo.getEmpInfoByLeaveId(leaveId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveStatus;

	}

	@RequestMapping(value = { "/saveNewLeaveAllotment" }, method = RequestMethod.POST)
	public @ResponseBody LeavesAllotment saveNewLeaveAllotmentWith(@RequestBody LeavesAllotment leavesAllotment) {

		LeavesAllotment save = new LeavesAllotment();

		try {

			save = leaveAllotmentRepository.saveAndFlush(leavesAllotment);
			if (save != null) {

				save.setError(false);

			} else {

				save = new LeavesAllotment();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new LeavesAllotment();
			save.setError(true);
			e.printStackTrace();
		}
		return save;
	}

	@RequestMapping(value = { "/saveNewLeaveAllotmentAll" }, method = RequestMethod.POST)
	public @ResponseBody List<LeavesAllotment> saveNewLeaveAllotmentAll(
			@RequestBody List<LeavesAllotment> leavesAllotment) {

		List<LeavesAllotment> save = new ArrayList<>();

		try {

			save = leaveAllotmentRepository.saveAll(leavesAllotment);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return save;
	}

	@RequestMapping(value = { "/getPendingListOfleaveCash" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveCashReport> getPendingListOfleaveCash(@RequestParam("yearId") int yearId) {

		List<LeaveCashReport> list = new ArrayList<>();

		try {

			list = leaveCashReportRepository.getPendingListOfleaveCash(yearId);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = { "/getPendingListOfleaveCashLocId" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveCashReport> getPendingListOfleaveCashLocId(@RequestParam("yearId") int yearId,
			@RequestParam("locId") List<Integer> locId) {

		List<LeaveCashReport> list = new ArrayList<>();

		try {

			list = leaveCashReportRepository.getPendingListOfleaveCashLocId(yearId, locId);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = { "/getPaidListOfleaveCash" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveCashReport> getPaidListOfleaveCash(@RequestParam("yearId") int yearId) {

		List<LeaveCashReport> list = new ArrayList<>();

		try {

			list = leaveCashReportRepository.getPaidListOfleaveCash(yearId);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = { "/getPaidListOfleaveCashLocId" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveCashReport> getPaidListOfleaveCashLocId(@RequestParam("yearId") int yearId,
			@RequestParam("locId") List<Integer> locId) {

		List<LeaveCashReport> list = new ArrayList<>();

		try {

			list = leaveCashReportRepository.getPaidListOfleaveCashLocId(yearId, locId);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = { "/updateIsPaidIncash" }, method = RequestMethod.POST)
	public @ResponseBody Info updateIsPaidIncash(@RequestParam("yearId") int yearId,
			@RequestParam("empId") List<Integer> empId, @RequestParam("date") String date) {

		Info info = new Info();

		try {

			int update = leaveBalanceCalRepo.updateIsPaidIncash(yearId, empId, date);
			info.setError(false);
			info.setMsg("update");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return info;
	}

}
