package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.common.Firebase;
import com.ats.hrmgt.model.AuthorityInformation;
import com.ats.hrmgt.model.CalenderYear;
import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.DailyRecordForCompOff;
import com.ats.hrmgt.model.DataForUpdateAttendance;
import com.ats.hrmgt.model.EmpBasicAllownceForLeaveInCash;
import com.ats.hrmgt.model.EmpLeaveHistoryRep;
import com.ats.hrmgt.model.EmployeeListForInActive;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.FileUploadedData;
import com.ats.hrmgt.model.GetAuthorityIds;
import com.ats.hrmgt.model.GetDetailForGraduaty;
import com.ats.hrmgt.model.GetLeaveApplyAuthwise;
import com.ats.hrmgt.model.GetLeaveEncashDetail;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.InfoForCompOffList;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveEncash;
import com.ats.hrmgt.model.LeaveHistory;
import com.ats.hrmgt.model.LeaveHistoryDetailForCarry;
import com.ats.hrmgt.model.LeaveStsAndLeaveId;
import com.ats.hrmgt.model.LeaveTrail;
import com.ats.hrmgt.model.LeaveTypeWithLimit;
import com.ats.hrmgt.model.MstEmpType;
import com.ats.hrmgt.model.PayableDayAndPresentDays;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.repo.DailyRecordForCompOffRepository;
import com.ats.hrmgt.repo.LeaveEncashRepository;
import com.ats.hrmgt.repository.AuthorityInformationRepository;
import com.ats.hrmgt.repository.CalculateYearRepository;
import com.ats.hrmgt.repository.EmpBasicAllownceForLeaveInCashRepo;
import com.ats.hrmgt.repository.EmpLeaveHistoryRepRepo;
import com.ats.hrmgt.repository.EmployeeListForInActiveRepository;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.GetAuthorityIdsRepo;
import com.ats.hrmgt.repository.GetDetailForGraduatyRepo;
import com.ats.hrmgt.repository.GetLeaveApplyAuthwiseRepo;
import com.ats.hrmgt.repository.GetLeaveEncashDetailRepository;
import com.ats.hrmgt.repository.LeaveApplyRepository;
import com.ats.hrmgt.repository.LeaveHistoryDetailForCarryRepo;
import com.ats.hrmgt.repository.LeaveHistoryRepo;
import com.ats.hrmgt.repository.LeaveTrailRepository;
import com.ats.hrmgt.repository.LeaveTypeWithLimitRepository;
import com.ats.hrmgt.repository.MstEmpTypeRepository;
import com.ats.hrmgt.repository.PayableDayAndPresentDaysRepo;
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.service.CommonFunctionService;

@RestController
public class LeaveActionApiController {

	@Autowired
	LeaveApplyRepository leaveApplyRepository;

	@Autowired
	LeaveTrailRepository leaveTrailRepository;

	@Autowired
	LeaveHistoryRepo leaveHistoryRepo;

	@Autowired
	AuthorityInformationRepository authorityInformationRepository;

	@Autowired
	GetAuthorityIdsRepo getAuthorityIdsRepo;

	@Autowired
	GetLeaveApplyAuthwiseRepo getLeaveApplyAuthwiseRepo;

	@Autowired
	CalculateYearRepository calculateYearRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	SettingRepo settingRepo;

	@Autowired
	PayableDayAndPresentDaysRepo payableDayAndPresentDaysRepo;

	@Autowired
	LeaveTypeWithLimitRepository leaveTypeWithLimitRepository;

	@Autowired
	EmpBasicAllownceForLeaveInCashRepo empBasicAllownceForLeaveInCashRepo;

	@Autowired
	MstEmpTypeRepository mstEmpTypeRepository;

	@Autowired
	DailyRecordForCompOffRepository dailyRecordForCompOffRepository;

	@Autowired
	GetDetailForGraduatyRepo getDetailForGraduatyRepo;

	@Autowired
	LeaveHistoryDetailForCarryRepo leaveHistoryDetailForCarryRepo;

	@Autowired
	LeaveEncashRepository leaveEncashRepository;

	@Autowired
	GetLeaveEncashDetailRepository getLeaveEncashDetailRepository;

	@RequestMapping(value = { "/updateLeaveStatus" }, method = RequestMethod.POST)
	public @ResponseBody Info updateLeaveStatus(@RequestParam("leaveId") int leaveId,
			@RequestParam("status") int status) {

		Info info = new Info();
		// System.err.println("in updateLeaveStatus" + status + leaveId);
		try {

			int update = leaveApplyRepository.updateLeaveStatus(leaveId, status);

			if (update > 0) {
				info.setError(false);
				info.setMsg("updated");
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

	@RequestMapping(value = { "/saveLeaveTrail" }, method = RequestMethod.POST)
	public @ResponseBody LeaveTrail saveLeaveTrail(@RequestBody LeaveTrail leaveTrail) {

		LeaveTrail save = new LeaveTrail();
		try {

			save = leaveTrailRepository.saveAndFlush(leaveTrail);

			if (save == null) {

				save = new LeaveTrail();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/updateTrailId" }, method = RequestMethod.POST)
	public @ResponseBody Info updateTrailId(@RequestParam("leaveId") int leaveId,
			@RequestParam("trailId") int trailId) {

		Info info = new Info();

		try {

			int delete = leaveApplyRepository.updateLeaveApply(leaveId, trailId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("updated status");
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

	@RequestMapping(value = { "/getLeaveHistoryList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveHistory> getLeaveHistoryList(@RequestParam("empId") int empId,
			@RequestParam("currYrId") int currYrId) {

		List<LeaveHistory> list = new ArrayList<LeaveHistory>();
		try {

			list = leaveHistoryRepo.getLeaveHistoryByEmpId(empId, currYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getPayableDayandPresentDayByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody PayableDayAndPresentDays getPayableDayandPresentDayByEmpId(@RequestParam("empId") int empId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		PayableDayAndPresentDays payableDayAndPresentDays = new PayableDayAndPresentDays();
		try {

			payableDayAndPresentDays = payableDayAndPresentDaysRepo.getPayableDayandPresentDayByEmpId(empId,
					DateConvertor.convertToYMD(fromDate), DateConvertor.convertToYMD(toDate));

			if (payableDayAndPresentDays == null) {
				payableDayAndPresentDays = new PayableDayAndPresentDays();
				payableDayAndPresentDays.setError(true);
			} else {
				payableDayAndPresentDays.setError(false);
			}

		} catch (Exception e) {
			payableDayAndPresentDays = new PayableDayAndPresentDays();
			payableDayAndPresentDays.setError(true);
			e.printStackTrace();
		}

		return payableDayAndPresentDays;

	}

	@RequestMapping(value = { "/getAuthorityInfoByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody AuthorityInformation getAuthorityInfoByEmpId(@RequestParam("empId") int empId) {

		AuthorityInformation authorityInformation = new AuthorityInformation();
		try {

			authorityInformation = authorityInformationRepository.getAuthorityInfoByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return authorityInformation;

	}

	@RequestMapping(value = { "getAuthIdByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody GetAuthorityIds getAuthIdByEmpId(@RequestParam("empId") int empId) {

		System.out.println("emp id is " + empId);
		GetAuthorityIds leaveApply = new GetAuthorityIds();
		try {

			leaveApply = getAuthorityIdsRepo.getAuthIds(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveApply;

	}

	@RequestMapping(value = { "/saveLeaveApplyList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveApply> saveLeaveApplyList(@RequestBody List<LeaveApply> leave) {

		List<LeaveApply> save = new ArrayList<>();
		try {

			save = leaveApplyRepository.saveAll(leave);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/saveLeaveApply" }, method = RequestMethod.POST)
	public @ResponseBody LeaveApply saveLeaveApply(@RequestBody LeaveApply leave) {

		LeaveApply save = new LeaveApply();
		try {

			save = leaveApplyRepository.saveAndFlush(leave);
			if (save == null) {

				save = new LeaveApply();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {
			save = new LeaveApply();
			save.setError(true);
			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLeaveApplyListForPending" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveApplyAuthwise> getLeaveApplyAuthwiseList(@RequestParam("empId") int empId,
			@RequestParam("currYrId") int currYrId) {
		List<GetLeaveApplyAuthwise> list = new ArrayList<GetLeaveApplyAuthwise>();

		try {

			list = getLeaveApplyAuthwiseRepo.getLeaveApplyList(empId, currYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLeaveApplyListForInformation" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveApplyAuthwise> getLeaveApplyListForInformation(@RequestParam("empId") int empId,
			@RequestParam("currYrId") int currYrId) {
		List<GetLeaveApplyAuthwise> list = new ArrayList<GetLeaveApplyAuthwise>();

		try {

			list = getLeaveApplyAuthwiseRepo.getLeaveApplyList2(empId, currYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getCalculateYearList" }, method = RequestMethod.GET)
	public @ResponseBody List<CalenderYear> getCalculateYearList() {

		List<CalenderYear> list = new ArrayList<CalenderYear>();
		try {

			list = calculateYearRepository.getAllCalYearOrderByDesc();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCalYrFromDate(DateConvertor.convertToDMY(list.get(i).getCalYrFromDate()));
				list.get(i).setCalYrToDate(DateConvertor.convertToDMY(list.get(i).getCalYrToDate()));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/checkUniqueCalDates" }, method = RequestMethod.POST)
	public @ResponseBody Info checkUniqueCalDates(@RequestParam("inputValue") String inputValue,
			@RequestParam("valueType") int valueType, @RequestParam("isEdit") int isEdit,
			@RequestParam("primaryKey") int primaryKey) {
		Info info = new Info();
		List<CalenderYear> list = new ArrayList<CalenderYear>();
		try {
			System.out.println("isEdit-------------" + isEdit);
			if (valueType == 1) {
				if (isEdit == 0) {
					list = calculateYearRepository.findByCalYrFromDate(inputValue);
				} else {
					list = calculateYearRepository.findByCalYrFromDateAndCalYrIdNot(inputValue, primaryKey);
				}
			} else if (valueType == 2) {
				if (isEdit == 0) {
					list = calculateYearRepository.findByCalYrToDate(inputValue);
				} else {
					list = calculateYearRepository.findByCalYrToDateAndCalYrIdNot(inputValue, primaryKey);
				}
			}

			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCalYrFromDate(DateConvertor.convertToDMY(list.get(i).getCalYrFromDate()));
				list.get(i).setCalYrToDate(DateConvertor.convertToDMY(list.get(i).getCalYrToDate()));
			}

			if (list.size() <= 0) {
				info.setError(false);
				info.setMsg("0");
			} else {
				info.setError(true);
				info.setMsg("1");

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/saveCalYear" }, method = RequestMethod.POST)
	public @ResponseBody CalenderYear checkUniqueCalDates(@RequestBody CalenderYear year) {
		CalenderYear calYear = new CalenderYear();
		try {
			calYear = calculateYearRepository.save(year);
			if (calYear.getIsCurrent() == 1) {
				int a = calculateYearRepository.updateOtherIds(calYear.getCalYrId());

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return calYear;

	}

	@RequestMapping(value = { "/getCalYearById" }, method = RequestMethod.POST)
	public @ResponseBody CalenderYear getCalYearById(@RequestParam int calYearId) {
		CalenderYear calYear = new CalenderYear();
		try {
			calYear = calculateYearRepository.findByCalYrId(calYearId);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return calYear;

	}

	@RequestMapping(value = { "/getCountCalYear" }, method = RequestMethod.GET)
	public @ResponseBody Integer getCountCalYear() {
		int cntCalYear = 0;
		try {
			cntCalYear = calculateYearRepository.countCalyear();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return cntCalYear;

	}

	@Autowired
	EmpLeaveHistoryRepRepo empLeaveHistoryRepRepo;

	@RequestMapping(value = { "/getLeaveHistoryRep" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpLeaveHistoryRep> getLeaveHistoryRep(@RequestParam("empId") int empId,
			@RequestParam("locId") int locId, @RequestParam("calYrId") int calYrId) {
		List<EmpLeaveHistoryRep> list = new ArrayList<EmpLeaveHistoryRep>();

		try {
			if (empId == -1) {
				list = empLeaveHistoryRepRepo.getEmpLeaveHistoryRepAll(calYrId, locId);
			} else {
				list = empLeaveHistoryRepRepo.getEmpLeaveHistoryRep(empId, calYrId, locId);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "getemplistwhichisnotyearend" }, method = RequestMethod.GET)
	public @ResponseBody List<EmployeeMaster> getemplistwhichisnotyearend() {

		List<EmployeeMaster> employeeInfo = new ArrayList<EmployeeMaster>();

		try {

			employeeInfo = employeeMasterRepository.getemplistwhichisnotyearend();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return employeeInfo;

	}

	@RequestMapping(value = { "getemplistwhichisnotyearendByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeMaster> getemplistwhichisnotyearendByEmpId(@RequestParam("locId") int locId) {

		List<EmployeeMaster> employeeInfo = new ArrayList<EmployeeMaster>();

		try {

			employeeInfo = employeeMasterRepository.getemplistwhichisnotyearendByEmpId(locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return employeeInfo;

	}

	@RequestMapping(value = { "/getPreviousleaveHistory" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveHistory> getPreviousleaveHistory(@RequestParam("empId") int empId) {

		List<LeaveHistory> list = new ArrayList<LeaveHistory>();
		try {

			list = leaveHistoryRepo.getPreviousleaveHistory(empId);

			// System.err.println("LeaveHistory" + list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getEmployeeBasicAndAllownceValueByEmpIdAndStructId" }, method = RequestMethod.POST)
	public @ResponseBody EmpBasicAllownceForLeaveInCash getEmployeeBasicAndAllownceValueByEmpIdAndStructId(
			@RequestParam("empId") int empId, @RequestParam("lvsId") int lvsId) {

		EmpBasicAllownceForLeaveInCash empBasicAllownceForLeaveInCash = new EmpBasicAllownceForLeaveInCash();
		try {

			empBasicAllownceForLeaveInCash = empBasicAllownceForLeaveInCashRepo
					.getEmployeeBasicAndAllownceValueByEmpIdAndStructId(empId, lvsId);

			// System.err.println("LeaveHistory" + list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return empBasicAllownceForLeaveInCash;

	}

	@RequestMapping(value = { "/getdetailforgraduaty" }, method = RequestMethod.POST)
	public @ResponseBody GetDetailForGraduaty getdetailforgraduaty(@RequestParam("empId") int empId) {

		GetDetailForGraduaty getDetailForGraduaty = new GetDetailForGraduaty();
		try {

			getDetailForGraduaty = getDetailForGraduatyRepo.getdetailforgraduaty(empId);

			// System.err.println("LeaveHistory" + list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return getDetailForGraduaty;

	}

	@RequestMapping(value = { "/getValidationOfFreezeMonth" }, method = RequestMethod.POST)
	public @ResponseBody Info getValidationOfFreezeMonth(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("empId") int empId) {

		Info info = new Info();
		try {

			int count = getDetailForGraduatyRepo.getValidationOfFreezeMonth(fromDate, toDate, empId);

			if (count > 0) {
				info.setError(true);
			} else {
				info.setError(false);
			}
			info.setMsg(String.valueOf(count));

			// System.err.println("LeaveHistory" + list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getValidationOfFreezeMonthSalary" }, method = RequestMethod.POST)
	public @ResponseBody Info getValidationOfFreezeMonthSalary(@RequestParam("month") String month,
			@RequestParam("year") String year, @RequestParam("empId") int empId) {

		Info info = new Info();
		try {

			int count = getDetailForGraduatyRepo.getValidationOfFreezeMonthSalary(month, year, empId);

			if (count > 0) {
				info.setError(true);
			} else {
				info.setError(false);
			}
			info.setMsg(String.valueOf(count));

			// System.err.println("LeaveHistory" + list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/checkDateForRepetedLeaveValidation" }, method = RequestMethod.POST)
	public @ResponseBody InfoForCompOffList checkDateForRepetedLeaveValidation(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("empId") int empId, @RequestParam("leaveTypeId") int leaveTypeId,
			@RequestParam("shortName") String shortName, @RequestParam("noOfDays") float noOfDays) {

		InfoForCompOffList info = new InfoForCompOffList();
		List<DailyRecordForCompOff> dailyrecordlistforcompoff = new ArrayList<>();
		info.setDailyrecordlistforcompoff(dailyrecordlistforcompoff);

		try {

			Setting setting = settingRepo.findByKey("CONTILEAVE");
			Setting CL_ID = settingRepo.findByKey("CL_ID");
			Setting PL_ID = settingRepo.findByKey("PL_ID");

			// Setting COMPOFFCONDITION = settingRepo.findByKey("COMPOFFCONDITION");

			List<LeaveApply> list = leaveApplyRepository.checkDateForRepetedLeaveValidation(fromDate, toDate, empId);

			if (list.size() > 0) {

				info.setError(true);
				info.setMsg("You Have Already Apply Leave on this Date.");

			} else {

				if (Integer.parseInt(setting.getValue()) == 1) {

					/*
					 * if (shortName.equalsIgnoreCase("LWP") || shortName.equalsIgnoreCase("SL")) {
					 * 
					 * info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays);
					 * 
					 * } else if (shortName.equalsIgnoreCase("Compoff")) {
					 * 
					 * info = checkCompOff(empId, fromDate, toDate);
					 * 
					 * } else {
					 * 
					 * list = leaveApplyRepository.checkContinueDateLeave(fromDate, toDate, empId,
					 * leaveTypeId); if (list.size() > 0) {
					 * 
					 * info.setError(true);
					 * info.setMsg("You Cannot Apply Continue Leave As Diffrent Type. ");
					 * 
					 * } else {
					 * 
					 * info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays); } }
					 */

					if (shortName.equalsIgnoreCase("CL") || shortName.equalsIgnoreCase("PL")) {

						if (shortName.equalsIgnoreCase("CL")) {
							list = leaveApplyRepository.checkContinueDateLeave(fromDate, toDate, empId,
									Integer.parseInt(PL_ID.getValue()));
						} else {
							list = leaveApplyRepository.checkContinueDateLeave(fromDate, toDate, empId,
									Integer.parseInt(CL_ID.getValue()));
						}

						if (list.size() > 0) {

							info.setError(true);
							info.setMsg("You Cannot Apply Continue Leave CL and PL");

						} else {

							info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays);
						}

					} else if (shortName.equalsIgnoreCase("Compoff")) {
						// match compoffApplicabale in emptype

						info = checkCompOff(empId, fromDate, toDate);

					} else {

						info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays);
					}

				} else {

					if (shortName.equalsIgnoreCase("Compoff")) {

						info = checkCompOff(empId, fromDate, toDate);
					} else {
						info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays);
					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	public InfoForCompOffList LeaveTypeValidation(int empId, int leaveTypeId, String shortName, float noOfDays) {
		InfoForCompOffList info = new InfoForCompOffList();

		try {
			List<DailyRecordForCompOff> dailyrecordlistforcompoff = new ArrayList<>();
			info.setDailyrecordlistforcompoff(dailyrecordlistforcompoff);

			Setting TYPEVALIDATION = settingRepo.findByKey("TYPEVALIDATION");

			if (Integer.parseInt(TYPEVALIDATION.getValue()) == 1) {
				CalenderYear calendearYear = calculateYearRepository.findByIsCurrent(1);
				LeaveTypeWithLimit leaveTypeWithLimit = leaveTypeWithLimitRepository.LeaveTypeWithLimit(empId,
						leaveTypeId, calendearYear.getCalYrId());

				// System.out.println(leaveTypeWithLimit.getMaxNoDays() + " " + noOfDays);
				if (leaveTypeWithLimit.getMaxNoDays() != 0 && leaveTypeWithLimit.getMaxNoDays() < noOfDays) {

					info.setError(true);
					info.setMsg("Your Leave Limit is " + leaveTypeWithLimit.getMaxNoDays());

				} else {
					info.setError(false);
					info.setMsg("you can apply");
				}

			} else {
				info.setError(false);
				info.setMsg("you can apply");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	public InfoForCompOffList checkCompOff(int empId, String fromDate, String toDate) {
		InfoForCompOffList info = new InfoForCompOffList();

		try {
			MstEmpType mstEmpType = mstEmpTypeRepository.getTypeByempId(empId);

			if (mstEmpType.getWhWork().equals("Compoff")) {

				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dt = sf.parse(fromDate);

				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(dt);
				cal.add(Calendar.DATE, -60);
				dt = cal.getTime();
				toDate = sf.format(dt);

				List<DailyRecordForCompOff> dailyrecordlistforcompoff = dailyRecordForCompOffRepository
						.dailyrecordlistforcompoff(toDate, fromDate, empId);

				if (dailyrecordlistforcompoff.size() > 0) {

					info.setError(false);
					info.setMsg("you can apply");
				} else {
					info.setError(true);
					info.setMsg("Insufficient compoff");
				}
				info.setDailyrecordlistforcompoff(dailyrecordlistforcompoff);
			} else {
				info.setError(true);
				info.setMsg("You cant take compoff");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/getEmpTypeByempId" }, method = RequestMethod.POST)
	public @ResponseBody MstEmpType getEmpTypeByempId(@RequestParam("empId") int empId) {

		MstEmpType mstEmpType = new MstEmpType();
		try {

			mstEmpType = mstEmpTypeRepository.getTypeByempId(empId);

			// System.err.println("LeaveHistory" + list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mstEmpType;

	}

	@RequestMapping(value = { "/getPreviousleaveHistoryForCarryFrwd" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveHistoryDetailForCarry> getPreviousleaveHistoryForCarryFrwd(
			@RequestParam("locId") int locId) {

		List<LeaveHistoryDetailForCarry> list = new ArrayList<LeaveHistoryDetailForCarry>();
		try {

			list = leaveHistoryDetailForCarryRepo.getPreviousleaveHistoryForCarryFrwd(locId);

			// System.err.println("LeaveHistory" + list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	CommonFunctionService commonFunctionService;

	@Autowired
	EmployeeListForInActiveRepository employeeListForInActiveRepository;

	@Autowired
	AttendanceApiControllerchange attendanceApiControllerchange;

	/* @Scheduled(cron = "0/20 * * * * ? ") */
	@Scheduled(cron = "0 0 23 * * ? ")
	public void applyLWPofInactiveEmployee() {

		try {
			Date dt = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			List<LeaveApply> leavetList = leaveApplyRepository.getleavetListForAttndace(sf.format(dt), sf.format(dt));
			List<EmployeeListForInActive> empList = employeeListForInActiveRepository.getEmpInactiveList();
			CalenderYear calendearYear = calculateYearRepository.findByIsCurrent(1);

			for (int i = 0; i < empList.size(); i++) {

				int findLeave = 0;

				for (int j = 0; j < leavetList.size(); j++) {

					if (empList.get(i).getEmpId() == leavetList.get(j).getEmpId()) {
						findLeave = 1;
						break;
					}

				}

				if (findLeave == 0) {
					LeaveApply leaveSummary = new LeaveApply();

					leaveSummary.setCalYrId(calendearYear.getCalYrId());
					leaveSummary.setEmpId(empList.get(i).getEmpId());
					leaveSummary.setFinalStatus(1);
					leaveSummary.setLeaveNumDays(1);
					leaveSummary.setCirculatedTo("1");
					leaveSummary.setLeaveDuration("1");
					leaveSummary.setLeaveEmpReason("BY SYSTEM");
					leaveSummary.setLvTypeId(2);
					leaveSummary.setLeaveFromdt(sf.format(dt));
					leaveSummary.setLeaveTodt(sf.format(dt));

					leaveSummary.setExInt1(3);
					leaveSummary.setExInt2(1);
					leaveSummary.setExInt3(1);
					leaveSummary.setExVar1("NA");

					leaveSummary.setExVar2("0");/*
												 * leaveSummary.setExVar3("");
												 */
					leaveSummary.setIsActive(1);
					leaveSummary.setDelStatus(1);
					leaveSummary.setMakerUserId(1);
					leaveSummary.setMakerEnterDatetime(df.format(dt));

					LeaveApply save = leaveApplyRepository.saveAndFlush(leaveSummary);

					if (save != null) {

						List<FileUploadedData> fileUploadedDataList = new ArrayList<>();

						FileUploadedData fileUploadedData = new FileUploadedData();
						fileUploadedData.setEmpCode(empList.get(i).getEmpCode());
						fileUploadedData.setEmpName("XYZ");
						fileUploadedData.setLogDate(DateConvertor.convertToDMY(sf.format(dt)));
						fileUploadedData.setInTime("00:00:00");
						fileUploadedData.setOutTime("00:00:00");

						fileUploadedDataList.add(fileUploadedData);
						DataForUpdateAttendance dataForUpdateAttendance = new DataForUpdateAttendance();
						dataForUpdateAttendance.setFromDate(sf.format(dt));
						dataForUpdateAttendance.setToDate(sf.format(dt));
						dataForUpdateAttendance.setMonth(0);
						dataForUpdateAttendance.setYear(0);
						dataForUpdateAttendance.setUserId(1);
						dataForUpdateAttendance.setFileUploadedDataList(fileUploadedDataList);
						dataForUpdateAttendance.setEmpId(empList.get(i).getEmpId());
						// System.out.println(dataForUpdateAttendance);
						Info dailydailyinfo = attendanceApiControllerchange
								.getVariousListForUploadAttendace(dataForUpdateAttendance);

					}
				}
			}

			/*
			 * LeaveStsAndLeaveId stsInfo = commonFunctionService.findDateInLeave(
			 * sf.format(dt), leavetList, dailyAttendanceList.get(i).getEmpId());
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = { "/newLeaveEncash" }, method = RequestMethod.POST)
	public @ResponseBody Info newLeaveEncash(@RequestBody LeaveEncash leaveEncash) {

		Info info = new Info();

		try {

			LeaveEncash res = leaveEncashRepository.save(leaveEncash);

			if (res != null) {
				info.setError(false);
				info.setMsg("save successfullyy");
			} else {
				info.setError(true);
				info.setMsg("error while encash leave");
			}

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("error while encash leave");
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/deleteEncashLeave" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteEncashLeave(@RequestParam("id") int id) {

		Info info = new Info();

		try {

			int delete = leaveEncashRepository.deleteEncashLeave(id);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("delete successfullyy");
			} else {
				info.setError(true);
				info.setMsg("error while delete");
			}

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("error delete");
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getLeaveEncashDetailByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveEncashDetail> getLeaveEncashDetailByEmpId(@RequestParam("empId") int empId,
			@RequestParam("currYrId") int yearId) {

		List<GetLeaveEncashDetail> list = new ArrayList<>();

		try {

			list = getLeaveEncashDetailRepository.getLeaveEncashDetailByEmpId(empId, yearId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
}
