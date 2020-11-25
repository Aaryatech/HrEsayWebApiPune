package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.advance.repository.GetAdvanceRepo;
import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.DeductionAndLoanAMT;
import com.ats.hrmgt.model.EcrFileData;
import com.ats.hrmgt.model.EmpOpningLoanList;
import com.ats.hrmgt.model.EmpShiftDetails;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.GetDailyDailyRecord;
import com.ats.hrmgt.model.GetDailyDailyRecordRepository;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LedgerDetailList;
import com.ats.hrmgt.model.LwfChallanData;
import com.ats.hrmgt.model.MonthWithOT;
import com.ats.hrmgt.model.PayDeductionDetails;
import com.ats.hrmgt.model.SlabMaster;
import com.ats.hrmgt.model.advance.GetAdvance;
import com.ats.hrmgt.model.bonus.BonusCalc;
import com.ats.hrmgt.model.report.EmpAttendeanceRep;
import com.ats.hrmgt.model.report.EsiSumaryRep;
import com.ats.hrmgt.model.report.GetLoanReport;
import com.ats.hrmgt.model.report.GetPtChallan;
import com.ats.hrmgt.model.report.GetSalaryCalcReport;
import com.ats.hrmgt.model.report.GetYearlyAdvance;
import com.ats.hrmgt.model.report.GetYearlyAdvanceNew;
import com.ats.hrmgt.model.report.GetYearlyLoan;
import com.ats.hrmgt.model.report.LoanDedReport;
import com.ats.hrmgt.model.report.StatutoryEsicRep;
import com.ats.hrmgt.repo.bonus.BonusCalcRepo;
import com.ats.hrmgt.repo.report.EmpAttendeanceRepRepo;
import com.ats.hrmgt.repo.report.EsiSumaryRepRepo;
import com.ats.hrmgt.repo.report.GetLoanReportRepo;
import com.ats.hrmgt.repo.report.GetPtChallanRepo;
import com.ats.hrmgt.repo.report.GetSalaryCalcReportRepo;
import com.ats.hrmgt.repo.report.GetYearlyAdvanceRepo;
import com.ats.hrmgt.repo.report.GetYearlyLoanRepo;
import com.ats.hrmgt.repo.report.LoanDedReportRepo;
import com.ats.hrmgt.repo.report.StatutoryEsicRepRepo;
import com.ats.hrmgt.repository.DailyAttendanceRepository;
import com.ats.hrmgt.repository.DeductionAndLoanAmtRepo;
import com.ats.hrmgt.repository.EcrFileDataRepo;
import com.ats.hrmgt.repository.EmpOpningLoanListRepo;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.LeaveApplyRepository;
import com.ats.hrmgt.repository.LwfChallanDataRepo;
import com.ats.hrmgt.repository.PayDeductionDetailsRepo;
import com.ats.hrmgt.repository.SlabMasterRepository;

@RestController

public class LeaveReportApiController {

	@Autowired
	LeaveApplyRepository leaveApplyRepository;

	@Autowired
	GetAdvanceRepo getAdvanceRepo;

	@Autowired
	EmployeeMasterRepository empRepo;

	@RequestMapping(value = { "/getAdvanceReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GetAdvance> getAdvanceReport(@RequestParam("companyId") int companyId,
			@RequestParam("month") int month, @RequestParam("year") int year, @RequestParam("locId") int locId) {

		List<GetAdvance> list = new ArrayList<GetAdvance>();
		try {

			list = getAdvanceRepo.getSpecEmpAdvForReport(companyId, month, year, locId);

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getIsDed() == 1) {
					list.get(i).setExVar1("Yes");
				} else {
					list.get(i).setExVar1("No");
				}
				list.get(i).setAdvDate(DateConvertor.convertToDMY(list.get(i).getAdvDate()));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	GetYearlyAdvanceRepo getYearlyAdvanceRepo;

	@RequestMapping(value = { "/getAdvanceYearlyReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GetYearlyAdvanceNew> getAdvanceYearlyReport(@RequestParam("companyId") int companyId,
			@RequestParam("year") int year, @RequestParam("locId") int locId) {

		List<GetYearlyAdvance> advYearList = new ArrayList<GetYearlyAdvance>();
		List<GetYearlyAdvanceNew> newList = new ArrayList<GetYearlyAdvanceNew>();

		try {
			advYearList = getYearlyAdvanceRepo.getSpecEmpAdvForReport(companyId, year, locId);

			List<EmployeeMaster> emplist = empRepo.findByLocationIdAndDelStatus(locId, companyId);
			// System.err.println("advYearList" + advYearList.toString());

			for (int i = 0; i < emplist.size(); i++) {
				GetYearlyAdvanceNew temp = new GetYearlyAdvanceNew();

				String janVal = "0";
				String febVal = "0";
				String marchVal = "0";
				String aprVal = "0";
				String mayVal = "0";
				String junVal = "0";
				String julVal = "0";
				String augVal = "0";
				String sepVal = "0";
				String octVal = "0";
				String novVal = "0";
				String decVal = "0";

				for (int k = 0; k < advYearList.size(); k++) {

					if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 1) {
						janVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 2) {
						febVal = advYearList.get(k).getAdvAmount();
						System.err.println("feb" + febVal);
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 3) {
						marchVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 4) {
						aprVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 5) {
						mayVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 6) {
						junVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 7) {
						julVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 8) {
						augVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 9) {
						sepVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 10) {
						octVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 11) {
						novVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 12) {
						decVal = advYearList.get(k).getAdvAmount();
					}
				}

				temp.setAprCount(aprVal);
				temp.setAugCount(augVal);
				temp.setDecCount(decVal);
				temp.setEmpCode(emplist.get(i).getEmpCode());
				temp.setEmpId(emplist.get(i).getEmpId());
				temp.setEmpName(emplist.get(i).getFirstName().concat(" ").concat(emplist.get(i).getMiddleName())
						.concat(" ").concat(emplist.get(i).getSurname()));
				temp.setFebCount(febVal);
				temp.setJanCount(janVal);
				temp.setJulCount(julVal);
				temp.setJunCount(junVal);
				temp.setMarchCount(marchVal);
				temp.setMayCount(mayVal);
				temp.setNovCount(novVal);
				temp.setOctCount(octVal);
				temp.setSepCount(sepVal);

				double tot = Double.parseDouble(janVal) + Double.parseDouble(febVal) + Double.parseDouble(marchVal)
						+ Double.parseDouble(aprVal) + Double.parseDouble(mayVal) + Double.parseDouble(junVal)
						+ Double.parseDouble(julVal) + Double.parseDouble(augVal) + Double.parseDouble(sepVal)
						+ Double.parseDouble(octVal) + Double.parseDouble(novVal) + Double.parseDouble(decVal);
				temp.setTotal(tot);

				newList.add(temp);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return newList;

	}

	@Autowired
	GetDailyDailyRecordRepository getDailyDailyRecordRepository;

	@RequestMapping(value = { "/getAttendenceRegReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GetDailyDailyRecord> getAttendenceRegReport(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("locId") int locId) {

		List<GetDailyDailyRecord> list = new ArrayList<GetDailyDailyRecord>();

		try {

			list = getDailyDailyRecordRepository.summaryDailyAttendanceListAll1(fromDate, toDate, locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	EmpAttendeanceRepRepo empAttendeanceRepRepo;

	@RequestMapping(value = { "/getDailyAttendenceReport" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpAttendeanceRep> getDailyAttendenceReport(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("companyId") int companyId,
			@RequestParam("locId") int locId) {

		List<EmpAttendeanceRep> list = new ArrayList<EmpAttendeanceRep>();

		try {

			list = empAttendeanceRepRepo.getSpecEmpAdvForReport(fromDate, toDate, locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	DailyAttendanceRepository dailyAttendanceRepository;

	@RequestMapping(value = { "/getEmpLateMarkReport" }, method = RequestMethod.POST)
	public @ResponseBody List<DailyAttendance> getEmpLateMarkReport(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<DailyAttendance> list = new ArrayList<DailyAttendance>();

		try {

			list = dailyAttendanceRepository.dailyAttendanceListAll1(companyId, fromDate, toDate);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

//*********************LoanReport*****************************
	@Autowired
	GetLoanReportRepo getLoanReportRepo;

	@RequestMapping(value = { "/getLoanReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLoanReport> getLoanReport(@RequestParam("month") int month,
			@RequestParam("year") int year) {

		List<GetLoanReport> loanList = new ArrayList<GetLoanReport>();

		try {
			loanList = getLoanReportRepo.getSpecEmpAdvForReport(year, month);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loanList;

	}

	@Autowired
	GetYearlyLoanRepo getYearlyLoanRepo;

	@RequestMapping(value = { "/getLoanYearlyReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GetYearlyLoan> getLoanYearlyReport(@RequestParam("companyId") int companyId,
			@RequestParam("year") int year) {

		List<GetYearlyLoan> advYearList = new ArrayList<GetYearlyLoan>();

		try {
			advYearList = getYearlyLoanRepo.getSpecEmpAdvForReport(year);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	// *************PF Statement*************************
	@Autowired
	GetSalaryCalcReportRepo getSalaryCalcReportRepo;

	@RequestMapping(value = { "/getPfStatement" }, method = RequestMethod.POST)
	public @ResponseBody List<GetSalaryCalcReport> getPfStatement(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("locId") int locId) {

		List<GetSalaryCalcReport> advYearList = new ArrayList<GetSalaryCalcReport>();

		String from[] = fromDate.split("-");
		String to[] = toDate.split("-");

		try {

			if (companyId == 0) {
				advYearList = getSalaryCalcReportRepo.getSpecEmpPfForReport(from[2].trim(), from[1].trim(),
						to[2].trim(), to[1].trim(), locId);

			} else {
				advYearList = getSalaryCalcReportRepo.getSpecEmpPfForReportComp(companyId, from[2].trim(),
						from[1].trim(), to[2], to[1], locId);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@RequestMapping(value = { "/getArrearsPfStatement" }, method = RequestMethod.POST)
	public @ResponseBody List<GetSalaryCalcReport> getArrearsPfStatement(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("locId") int locId) {

		List<GetSalaryCalcReport> advYearList = new ArrayList<GetSalaryCalcReport>();

		String from[] = fromDate.split("-");
		String to[] = toDate.split("-");

		try {

			if (companyId == 0) {
				advYearList = getSalaryCalcReportRepo.getArrearsSpecEmpPfForReport(from[2].trim(), from[1].trim(),
						to[2].trim(), to[1].trim(), locId);

			} else {
				advYearList = getSalaryCalcReportRepo.getArrearsSpecEmpPfForReportComp(companyId, from[2].trim(),
						from[1].trim(), to[2], to[1], locId);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@RequestMapping(value = { "/getPtStatement" }, method = RequestMethod.POST)
	public @ResponseBody List<GetSalaryCalcReport> getPtStatement(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("locId") int locId) {

		List<GetSalaryCalcReport> advYearList = new ArrayList<GetSalaryCalcReport>();

		String from[] = fromDate.split("-");
		String to[] = toDate.split("-");

		try {

			advYearList = getSalaryCalcReportRepo.getSpecEmpPTForReportComp(companyId, from[2], from[1], to[2], to[1],
					locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@RequestMapping(value = { "/getEmpPfStatement" }, method = RequestMethod.POST)
	public @ResponseBody List<GetSalaryCalcReport> getEmpPfStatement(@RequestParam("empId") int empId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<GetSalaryCalcReport> advYearList = new ArrayList<GetSalaryCalcReport>();

		String from[] = fromDate.split("-");
		String to[] = toDate.split("-");

		try {
			advYearList = getSalaryCalcReportRepo.getSpecEmpPfStat(from[1], from[0], to[1], to[0], empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@Autowired
	LwfChallanDataRepo lwfChallanDataRepo;

	@RequestMapping(value = { "/getLwfDataForChallan" }, method = RequestMethod.POST)
	public @ResponseBody LwfChallanData getLwfDataForChallan(@RequestParam("locId") int locId,
			@RequestParam("month") String month, @RequestParam("year") String year) {

		LwfChallanData lwfChallanData = new LwfChallanData();

		try {
			lwfChallanData = lwfChallanDataRepo.getLwfDataForChallan(locId, month, year);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return lwfChallanData;

	}

	@Autowired
	GetPtChallanRepo getPtChallanRepo;

	@Autowired
	SlabMasterRepository slabMasterRepository;

	@RequestMapping(value = { "/getPtChallanRep" }, method = RequestMethod.POST)
	public @ResponseBody List<GetPtChallan> getPtChallanRep(@RequestParam("companyId") int companyId,
			@RequestParam("month") String month, @RequestParam("year") String year, @RequestParam("locId") int locId) {

		List<GetPtChallan> advYearList = new ArrayList<GetPtChallan>();

		try {

			advYearList = getPtChallanRepo.getPtChallan(month, year, companyId, locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@RequestMapping(value = { "/getMLWFStatement" }, method = RequestMethod.POST)
	public @ResponseBody List<GetSalaryCalcReport> getMLWFStatement(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("locId") int locId) {

		List<GetSalaryCalcReport> advYearList = new ArrayList<GetSalaryCalcReport>();

		String from[] = fromDate.split("-");
		String to[] = toDate.split("-");

		try {

			if (companyId == 0) {
				advYearList = getSalaryCalcReportRepo.getMlwfRepAllCmp(from[2], from[1], to[2], to[1], locId);

			} else {
				advYearList = getSalaryCalcReportRepo.getMlwfRep(from[2].trim(), from[1].trim(), to[2].trim(),
						to[1].trim(), companyId, locId);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@Autowired
	PayDeductionDetailsRepo payDeductionDetailsRepo;

	@RequestMapping(value = { "/getEmpPayDed" }, method = RequestMethod.POST)
	public @ResponseBody List<PayDeductionDetails> getEmpPayDed(@RequestParam("empId") int empId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, int flag) {

		List<PayDeductionDetails> advYearList = new ArrayList<PayDeductionDetails>();

		try {

			if (flag == 0) {

				String from[] = fromDate.split("-");
				String to[] = toDate.split("-");
				advYearList = payDeductionDetailsRepo.getEmpPayDed(from[1].trim(), from[0].trim(), to[1].trim(),
						to[0].trim(), empId);

			} else {
				String from[] = fromDate.split("-");
				String to[] = toDate.split("-");
				advYearList = payDeductionDetailsRepo.getEmpPayDedAllEmp(from[2].trim(), from[1].trim(), to[2].trim(),
						to[1].trim());
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@Autowired
	StatutoryEsicRepRepo statutoryEsicRepRepo;

	@RequestMapping(value = { "/getStatutoryEsic" }, method = RequestMethod.POST)
	public @ResponseBody List<StatutoryEsicRep> getStatutoryEsic(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("locId") int locId) {

		List<StatutoryEsicRep> advYearList = new ArrayList<StatutoryEsicRep>();

		String from[] = fromDate.split("-");
		String to[] = toDate.split("-");

		System.err.println("fromDate" + fromDate);
		System.err.println("toDate" + toDate);

		fromDate = from[2].trim().concat("-").concat(from[1].trim()).concat("-").concat("01");
		toDate = to[2].trim().concat("-").concat(to[1].trim()).concat("-").concat("01");

		try {

			if (companyId == 0) {
				advYearList = statutoryEsicRepRepo.getStatutoryEsicAll(fromDate, toDate, locId);

			} else {
				advYearList = statutoryEsicRepRepo.getStatutoryEsic(fromDate, toDate, companyId, locId);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@RequestMapping(value = { "/getArearsStatutoryEsic" }, method = RequestMethod.POST)
	public @ResponseBody List<StatutoryEsicRep> getArearsStatutoryEsic(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("locId") int locId) {

		List<StatutoryEsicRep> advYearList = new ArrayList<StatutoryEsicRep>();

		String from[] = fromDate.split("-");
		String to[] = toDate.split("-");

		fromDate = from[2].trim().concat("-").concat(from[1].trim()).concat("-").concat("01");
		toDate = to[2].trim().concat("-").concat(to[1].trim()).concat("-").concat("01");

		try {

			advYearList = statutoryEsicRepRepo.getArearsStatutoryEsic(fromDate, toDate, companyId, locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	// loan Reports

	@Autowired
	LoanDedReportRepo loanDedReportRepo;

	@RequestMapping(value = { "/getLoanDedReport" }, method = RequestMethod.POST)
	public @ResponseBody List<LoanDedReport> getLoanReport(@RequestParam("month") String month,
			@RequestParam("year") String year, @RequestParam("locId") int locId) {

		List<LoanDedReport> advYearList = new ArrayList<LoanDedReport>();

		try {

			advYearList = loanDedReportRepo.getSpecEmpDedLoanReport(month, year, locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@Autowired
	EcrFileDataRepo ecrFileDataRepo;

	@RequestMapping(value = { "/getEcrFileData" }, method = RequestMethod.POST)
	public @ResponseBody List<EcrFileData> getEcrFileData(@RequestParam("cmpId") int cmpId,
			@RequestParam("month") String month, @RequestParam("year") String year, @RequestParam("locId") int locId) {

		List<EcrFileData> ecrFileDataList = new ArrayList<EcrFileData>();

		try {

			ecrFileDataList = ecrFileDataRepo.getEcrFileData(cmpId, month, year, locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return ecrFileDataList;

	}

	@Autowired
	EmpOpningLoanListRepo empOpningLoanListRepo;

	@Autowired
	DeductionAndLoanAmtRepo deductionAndLoanAmtRepo;

	@RequestMapping(value = { "/payLoanLedgerReport" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpOpningLoanList> payLoanLedgerReport(@RequestParam("fromMonth") String fromMonth,
			@RequestParam("toMonth") String toMonth, @RequestParam("fromYear") String fromYear,
			@RequestParam("toYear") String toYear, @RequestParam("locId") int locId) {

		List<EmpOpningLoanList> list = new ArrayList<EmpOpningLoanList>();

		try {

			list = empOpningLoanListRepo.payLoanLedgerReport(fromYear + "-" + fromMonth + "-01",
					toYear + "-" + toMonth + "-01", locId);

			List<DeductionAndLoanAMT> deductionList = deductionAndLoanAmtRepo
					.getDeductoinAmtList(fromYear + "-" + fromMonth + "-01", toYear + "-" + toMonth + "-01", locId);

			List<DeductionAndLoanAMT> loanList = deductionAndLoanAmtRepo
					.getLoanAmtList(fromYear + "-" + fromMonth + "-01", toYear + "-" + toMonth + "-01", locId);

			SimpleDateFormat yy = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
			Calendar startCalendar = new GregorianCalendar();
			startCalendar.setTime(yy.parse(fromYear + "-" + fromMonth + "-01"));
			Calendar endCalendar = new GregorianCalendar();
			endCalendar.setTime(yy.parse(toYear + "-" + toMonth + "-01"));

			int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
			int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH) + 1;

			YearMonth thisMonth = YearMonth.of(Integer.parseInt(fromYear), Integer.parseInt(fromMonth));
			DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM-yyyy");
			SimpleDateFormat sf = new SimpleDateFormat("MM-yyyy");
			SimpleDateFormat monthNameF = new SimpleDateFormat("MMM-yyyy");
			List<MonthWithOT> monthList = new ArrayList<>();

			for (int i = 0; i < diffMonth; i++) {

				YearMonth lastMonth = thisMonth.plusMonths(i);
				MonthWithOT monthWithOT = new MonthWithOT();
				monthWithOT.setMonth(lastMonth.format(monthYearFormatter));
				monthList.add(monthWithOT);

			}

			for (int i = 0; i < list.size(); i++) {

				List<LedgerDetailList> ledgerList = new ArrayList<>();

				if (list.get(i).getOpAmt() > 0) {

					LedgerDetailList ledgerDetailList = new LedgerDetailList();
					ledgerDetailList.setMonthYear(list.get(i).getMonthYear());
					ledgerDetailList.setMonthName(monthNameF.format(dd.parse("01-" + list.get(i).getMonthYear())));
					ledgerDetailList.setPaid(list.get(i).getOpAmt());
					ledgerDetailList.setCaptionName("OPENING BALANCE");
					ledgerList.add(ledgerDetailList);
				}
				for (int k = 0; k < monthList.size(); k++) {

					Date dt = sf.parse(monthList.get(k).getMonth());

					for (int j = 0; j < loanList.size(); j++) {

						Date date = dd.parse("01-" + loanList.get(j).getMonthYear());

						if (loanList.get(j).getEmpId() == list.get(i).getEmpId() && dt.compareTo(date) == 0) {
							LedgerDetailList ledgerDetailList = new LedgerDetailList();
							ledgerDetailList.setMonthYear(loanList.get(j).getMonthYear());
							ledgerDetailList
									.setMonthName(monthNameF.format(dd.parse("01-" + loanList.get(j).getMonthYear())));
							ledgerDetailList.setPaid(loanList.get(j).getAmt());
							ledgerDetailList.setCaptionName("LOAN");
							ledgerList.add(ledgerDetailList);
							break;
						}

					}

					for (int j = 0; j < deductionList.size(); j++) {

						Date date = dd.parse("01-" + deductionList.get(j).getMonthYear());

						if (deductionList.get(j).getEmpId() == list.get(i).getEmpId() && dt.compareTo(date) == 0) {
							LedgerDetailList ledgerDetailList = new LedgerDetailList();
							ledgerDetailList.setMonthYear(deductionList.get(j).getMonthYear());
							ledgerDetailList.setMonthName(
									monthNameF.format(dd.parse("01-" + deductionList.get(j).getMonthYear())));
							ledgerDetailList.setReturnAmt(deductionList.get(j).getAmt());
							ledgerDetailList.setCaptionName("DEDUCTION");
							ledgerList.add(ledgerDetailList);
							break;
						}

					}
				}
				list.get(i).setLedgerList(ledgerList);
				// System.out.println(ledgerList);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getAdvanceDeductionReport" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpOpningLoanList> getAdvanceDeductionReport(@RequestParam("month") String month,
			@RequestParam("year") String year, @RequestParam("locId") int locId) {

		List<EmpOpningLoanList> list = new ArrayList<EmpOpningLoanList>();

		try {

			list = empOpningLoanListRepo.getAdvanceDeductionReport(month, year, locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	/// bonus list

	@Autowired
	BonusCalcRepo bonusCalcRepo;

	@RequestMapping(value = { "/getBonusReportByBonusId" }, method = RequestMethod.POST)
	public @ResponseBody List<BonusCalc> getBonusReportByRep(@RequestParam("bonusId") int bonusId) {

		List<BonusCalc> advYearList = new ArrayList<BonusCalc>();

		try {

			advYearList = bonusCalcRepo.findByDelStatusAndBonusId(1, bonusId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@Autowired
	EsiSumaryRepRepo esiSumaryRepRepo;

	@RequestMapping(value = { "/getEsiSummaryReport" }, method = RequestMethod.POST)
	public @ResponseBody List<EsiSumaryRep> getEsiSummaryReport(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("locId") int locId) {

		List<EsiSumaryRep> advYearList = new ArrayList<EsiSumaryRep>();

		String from[] = fromDate.split("-");
		String to[] = toDate.split("-");

		System.err.println("fromDate" + fromDate);
		System.err.println("toDate" + toDate);

		fromDate = from[2].concat("-").concat(from[1]).concat("-").concat("01");
		toDate = to[2].concat("-").concat(to[1]).concat("-").concat("01");

		try {
			if (companyId == 0) {
				advYearList = esiSumaryRepRepo.getEsiSummAll(from[2], from[1], to[2], to[1], locId);

			} else {
				advYearList = esiSumaryRepRepo.getEsiSumm(from[2].trim(), from[1].trim(), to[2].trim(), to[1].trim(),
						companyId, locId);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@RequestMapping(value = { "/showEsicDataUpload" }, method = RequestMethod.POST)
	public @ResponseBody List<StatutoryEsicRep> showEsicDataUpload(@RequestParam("companyId") int companyId,
			@RequestParam("year") String year, @RequestParam("month") String month, @RequestParam("locId") int locId) {

		List<StatutoryEsicRep> advYearList = new ArrayList<StatutoryEsicRep>();

		try {

			advYearList = statutoryEsicRepRepo.showEsicDataUpload(month, year, locId, companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return advYearList;

	}

	@RequestMapping(value = { "/getLeaveApplicationEmpReport" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveApply> getLeaveApplicationEmpReport(@RequestParam("calYrId") int calYrId,
			@RequestParam("empId") int empId) {

		List<LeaveApply> list = new ArrayList<LeaveApply>();

		try {

			list = leaveApplyRepository.findByCalYrIdAndDelStatusAndEmpId(calYrId, 1, empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

}
