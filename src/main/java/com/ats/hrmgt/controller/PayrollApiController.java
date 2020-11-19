package com.ats.hrmgt.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.advance.repository.AdvanceRepo;
import com.ats.hrmgt.claim.repository.ClaimHeaderRepo;
import com.ats.hrmgt.common.EmailUtility;
import com.ats.hrmgt.common.EnglishNumberToWords;
import com.ats.hrmgt.model.Allowances;
import com.ats.hrmgt.model.EmpAllowanceList;
import com.ats.hrmgt.model.EmpSalAllowance;
import com.ats.hrmgt.model.EmpSalInfoDaiyInfoTempInfo;
import com.ats.hrmgt.model.EmpSalaryInfo;
import com.ats.hrmgt.model.EmpSalaryInfoForPayroll;
import com.ats.hrmgt.model.GetAdvanceDetails;
import com.ats.hrmgt.model.GetAdvanceList;
import com.ats.hrmgt.model.GetClaimList;
import com.ats.hrmgt.model.GetDeptPayReport;
import com.ats.hrmgt.model.GetEmpDetail;
import com.ats.hrmgt.model.GetEmpDetailForFullPayslip;
import com.ats.hrmgt.model.GetOnelineReport;
import com.ats.hrmgt.model.GetPayDedList;
import com.ats.hrmgt.model.GetPayrollGeneratedList;
import com.ats.hrmgt.model.GetSalDynamicTempRecord;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LateMarkListForInsertAdvance;
import com.ats.hrmgt.model.MlwfMaster;
import com.ats.hrmgt.model.MstEmpType;
import com.ats.hrmgt.model.PayRollDataForProcessing;
import com.ats.hrmgt.model.ProductionIncentiveList;
import com.ats.hrmgt.model.RoasterSummeryDetail;
import com.ats.hrmgt.model.RoasterSummeryDetailRepository;
import com.ats.hrmgt.model.SalAllownceCal;
import com.ats.hrmgt.model.SalAllownceTemp;
import com.ats.hrmgt.model.SalaryCalc;
import com.ats.hrmgt.model.SalaryCalcTemp;
import com.ats.hrmgt.model.SalaryTerm;
import com.ats.hrmgt.model.SalaryTypesMaster;
import com.ats.hrmgt.model.SampleClass;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.SlabMaster;
import com.ats.hrmgt.model.SummaryAttendance;
import com.ats.hrmgt.model.advance.Advance;
import com.ats.hrmgt.model.loan.LoanDetails;
import com.ats.hrmgt.model.loan.LoanMain;
import com.ats.hrmgt.repo.GetEmpDetailRepo;
import com.ats.hrmgt.repo.bonus.PayBonusDetailsRepo;
import com.ats.hrmgt.repo.loan.LoanDetailsRepo;
import com.ats.hrmgt.repo.loan.LoanMainRepo;
import com.ats.hrmgt.repository.AllowancesRepo;
import com.ats.hrmgt.repository.EmpSalAllowanceRepo;
import com.ats.hrmgt.repository.EmpSalInfoDaiyInfoTempInfoRepo;
import com.ats.hrmgt.repository.EmpSalaryInfoForPayrollRepository;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.GetAdvanceDetailsRepo;
import com.ats.hrmgt.repository.GetAdvanceListRepo;
import com.ats.hrmgt.repository.GetClaimListRepo;
import com.ats.hrmgt.repository.GetDeptPayReportRepo;
import com.ats.hrmgt.repository.GetEmpDetailForFullPayslipRepo;
import com.ats.hrmgt.repository.GetOnelineReportRepo;
import com.ats.hrmgt.repository.GetPayDedListRepo;
import com.ats.hrmgt.repository.GetPayrollGeneratedListRepo;
import com.ats.hrmgt.repository.GetSalDynamicTempRecordRepository;
import com.ats.hrmgt.repository.LateMarkListForInsertAdvanceRepository;
import com.ats.hrmgt.repository.MlwfMasterRepository;
import com.ats.hrmgt.repository.MstEmpTypeRepository;
import com.ats.hrmgt.repository.PayDeductionDetailsRepo;
import com.ats.hrmgt.repository.ProductionIncentiveListRepo;
import com.ats.hrmgt.repository.SalAllownceCalRepo;
import com.ats.hrmgt.repository.SalAllownceTempRepo;
import com.ats.hrmgt.repository.SalaryCalcRepo;
import com.ats.hrmgt.repository.SalaryCalcTempRepo;
import com.ats.hrmgt.repository.SalaryTermRepository;
import com.ats.hrmgt.repository.SalaryTypesMasterRepo;
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.repository.SlabMasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class PayrollApiController {

	@Autowired
	EmpSalaryInfoForPayrollRepository empSalaryInfoForPayrollRepository;

	@Autowired
	EmpSalAllowanceRepo empSalAllowanceRepo;

	@Autowired
	AllowancesRepo allowanceRepo;

	@Autowired
	SalaryCalcTempRepo salaryCalcTempRepo;

	@Autowired
	SalAllownceTempRepo salAllownceTempRepo;

	@Autowired
	GetSalDynamicTempRecordRepository getSalDynamicTempRecordRepository;

	@Autowired
	GetAdvanceListRepo getAdvanceListRepo;

	@Autowired
	GetClaimListRepo getClaimListRepo;

	@Autowired
	GetPayDedListRepo getPayDedListRepo;

	@Autowired
	MstEmpTypeRepository mstEmpTypeRepository;

	@Autowired
	SalaryTypesMasterRepo salaryTypesMasterRepo;

	@Autowired
	SlabMasterRepository slabMasterRepository;

	@Autowired
	SalaryTermRepository salaryTermRepository;

	@Autowired
	SettingRepo settingRepo;

	@Autowired
	EmpSalaryInfoRepo empSalaryInfoRepo;

	@Autowired
	EmpSalInfoDaiyInfoTempInfoRepo empSalInfoDaiyInfoTempInfoRepo;

	@Autowired
	RoasterSummeryDetailRepository roasterSummeryDetailRepository;

	@Autowired
	SalaryCalcRepo salaryCalcRepo;

	@Autowired
	SalAllownceCalRepo salAllownceCalRepo;

	@Autowired
	GetPayrollGeneratedListRepo getPayrollGeneratedListRepo;

	@Autowired
	AdvanceRepo advanceRepo;

	@Autowired
	ClaimHeaderRepo claimHeaderRepo;

	@Autowired
	PayDeductionDetailsRepo payDeductionDetailsRepo;

	@Autowired
	LoanMainRepo loanMainRepo;

	@Autowired
	LoanDetailsRepo loanDetailsRepo;

	@Autowired
	PayBonusDetailsRepo payBonusDetailsRepo;

	@Autowired
	GetEmpDetailRepo getEmpDetailRepo;

	@Autowired
	LateMarkListForInsertAdvanceRepository lateMarkListForInsertAdvanceRepository;

	@Autowired
	ProductionIncentiveListRepo productionIncentiveListRepo;

	@Autowired
	GetAdvanceDetailsRepo getAdvanceDetailsRepo;

	@Autowired
	GetEmpDetailForFullPayslipRepo getEmpDetailForFullPayslipRepo;

	@Autowired
	MlwfMasterRepository mlwfMasterRepository;

	@Autowired
	GetOnelineReportRepo getOnelineReportRepo;

	@Autowired
	GetDeptPayReportRepo getDeptPayReportRepo;

	@RequestMapping(value = { "/getEmployeeListWithEmpSalEnfoForPayRoll" }, method = RequestMethod.POST)
	public PayRollDataForProcessing getEmployeeListWithEmpSalEnfoForPayRoll(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("locId") List<Integer> locId) {

		PayRollDataForProcessing payRollDataForProcessing = new PayRollDataForProcessing();

		try {
			List<EmpSalaryInfoForPayroll> list = empSalaryInfoForPayrollRepository
					.getEmployeeListWithEmpSalEnfoForPayRoll(month, year, locId, (year + "-" + month + "-01"));
			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<EmpSalAllowance> empAllowanceList = empSalAllowanceRepo.findByDelStatus(1);

			for (int i = 0; i < list.size(); i++) {

				List<EmpAllowanceList> allowlist = new ArrayList<>();

				for (int j = 0; j < allowancelist.size(); j++) {

					int flag = 0;

					for (int k = 0; k < empAllowanceList.size(); k++) {

						if (empAllowanceList.get(k).getEmpId() == list.get(i).getEmpId()
								&& empAllowanceList.get(k).getAllowanceId() == allowancelist.get(j).getAllowanceId()) {

							EmpAllowanceList empAllowance = new EmpAllowanceList();
							empAllowance.setAllowanceId(allowancelist.get(j).getAllowanceId());
							empAllowance.setAllowanceName(allowancelist.get(j).getName());
							empAllowance.setValue(empAllowanceList.get(k).getAllowanceValue());
							allowlist.add(empAllowance);
							flag = 1;
							break;

						}
					}
					if (flag == 0) {
						EmpAllowanceList empAllowance = new EmpAllowanceList();
						empAllowance.setAllowanceId(allowancelist.get(j).getAllowanceId());
						empAllowance.setAllowanceName(allowancelist.get(j).getName());
						empAllowance.setValue(0);
						allowlist.add(empAllowance);
					}
				}
				list.get(i).setEmpAllowanceList(allowlist);
			}

			payRollDataForProcessing.setAllowancelist(allowancelist);
			payRollDataForProcessing.setList(list);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return payRollDataForProcessing;
	}

	@RequestMapping(value = { "/updateAllowAmtInSalTemp" }, method = RequestMethod.POST)
	public Info updateAllowAmtInSalTemp(@RequestParam("month") int month, @RequestParam("year") int year,
			@RequestParam("empIds") List<Integer> empIds, @RequestParam("userId") int userId) {

		Info info = new Info();

		try {
			List<SalAllownceTemp> salAllowTempList = salAllownceTempRepo.findByDelStatusAndEmpIdIn(empIds, month, year);
			// List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0,
			// 1);
			List<EmpSalAllowance> empAllowanceList = empSalAllowanceRepo.findByDelStatusAndEmpId(1, empIds);

			for (int k = 0; k < salAllowTempList.size(); k++) {

				for (int j = 0; j < empAllowanceList.size(); j++) {
					if (salAllowTempList.get(k).getEmpId() == empAllowanceList.get(j).getEmpId()
							&& salAllowTempList.get(k).getAllowanceId() == empAllowanceList.get(j).getAllowanceId()) {
						salAllowTempList.get(k).setAllowanceValue(empAllowanceList.get(j).getAllowanceValue());

					}
				}

			}
			List<SalAllownceTemp> update = salAllownceTempRepo.saveAll(salAllowTempList);
			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/getSettingListByGroup" }, method = RequestMethod.POST)
	public List<Setting> getSettingListByGroup(@RequestParam("group") String group) {

		List<Setting> settingList = new ArrayList<>();

		try {
			settingList = settingRepo.findByGroup("PAYROLLHIDESHOW");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return settingList;
	}

	@RequestMapping(value = { "/insertPayrollIntempTable" }, method = RequestMethod.POST)
	public Info insertPayrollIntempTable(@RequestParam("month") int month, @RequestParam("year") int year,
			@RequestParam("empIds") List<Integer> empIds, @RequestParam("userId") int userId) {

		Info info = new Info();

		try {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");

			List<Setting> settingList = settingRepo.findByGroup("PAYROLLHIDESHOW");

			int payroll_claim_show = 0;
			int payroll_advance_show = 0;
			int payroll_loan_show = 0;
			int payroll_payded_show = 0;
			int payroll_reward_show = 0;
			int payroll_tds_show = 0;
			int payroll_performancebonus_show = 0;

			for (int k = 0; k < settingList.size(); k++) {
				if (settingList.get(k).getKey().equalsIgnoreCase("payroll_claim_show")) {
					payroll_claim_show = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("payroll_advance_show")) {
					payroll_advance_show = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("payroll_loan_show")) {
					payroll_loan_show = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("payroll_payded_show")) {
					payroll_payded_show = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("payroll_reward_show")) {
					payroll_reward_show = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("payroll_tds_show")) {
					payroll_tds_show = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("payroll_performancebonus_show")) {
					payroll_performancebonus_show = Integer.parseInt(settingList.get(k).getValue());
				}
			}

			// insertadvance for late mark deduct

			int deleteAdvanceBydefault = advanceRepo.deleteAdvanceBydefault(month, year, empIds);
			List<LateMarkListForInsertAdvance> latemarkList = lateMarkListForInsertAdvanceRepository
					.getlatemarkList(month, year, empIds);
			List<Advance> advlist = new ArrayList<>();

			for (int i = 0; i < latemarkList.size(); i++) {

				double advanceAmt = latemarkList.get(i).getTotlateDays()
						* (latemarkList.get(i).getGrSal() / latemarkList.get(i).getTotalDaysInmonth());

				Advance adv = new Advance();
				adv.setAdvAmount(advanceAmt);
				adv.setAdvDate(sf2.format(date));
				adv.setAdvRemainingAmount(advanceAmt);
				adv.setAdvRemarks("LATEMARKDEDUCT");
				adv.setCmpId(1);
				adv.setEmpId(latemarkList.get(i).getEmpId());
				adv.setDedMonth(month);
				adv.setDedYear(year);
				adv.setExInt1(1);
				adv.setExVar1("NA");
				adv.setExVar2("NA");
				adv.setVoucherNo("LATEMARKDEDUCT");
				adv.setIsDed(0);
				adv.setIsUsed(0);
				adv.setLoginName(String.valueOf(userId));
				adv.setLoginTime(sf.format(date));
				adv.setSkipId(0);
				adv.setDelStatus(1);
				advlist.add(adv);
				// Advance save = advanceRepo.saveAndFlush(adv);

			}
			List<Advance> save = advanceRepo.saveAll(advlist);

			// start process
			List<EmpSalaryInfoForPayroll> list = empSalaryInfoForPayrollRepository
					.getEmployeeListWithEmpSalEnfoForPayRollForTempInsert(month, year, empIds);
			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<EmpSalAllowance> empAllowanceList = empSalAllowanceRepo.findByDelStatusAndEmpId(1, empIds);

			// insert code if record not inserted in temp table
			for (int i = 0; i < list.size(); i++) {

				SalaryCalcTemp salaryCalcTempsave = new SalaryCalcTemp();
				salaryCalcTempsave.setCalcMonth(month);
				salaryCalcTempsave.setCalcYear(year);
				salaryCalcTempsave.setEmpId(list.get(i).getEmpId());
				salaryCalcTempsave.setEmpCode(list.get(i).getEmpCode());
				salaryCalcTempsave.setContractorId(list.get(i).getContractorId());
				salaryCalcTempsave.setDesignationId(list.get(i).getDesigId());
				salaryCalcTempsave.setLocationId(list.get(i).getLocId());
				salaryCalcTempsave.setEmpType(list.get(i).getEmpTypeId());
				salaryCalcTempsave.setDepartId(list.get(i).getDepartId());
				salaryCalcTempsave.setAttSumId(list.get(i).getSumId());
				salaryCalcTempsave.setSalTypeId(list.get(i).getSalaryTypeId());
				salaryCalcTempsave.setLoginName(String.valueOf(userId));
				salaryCalcTempsave.setLoginTime(sf.format(date));
				salaryCalcTempsave.setCmpId(list.get(i).getSubCmpId());
				SalaryCalcTemp saveres = salaryCalcTempRepo.save(salaryCalcTempsave);

				List<SalAllownceTemp> allowlist = new ArrayList<>();

				for (int j = 0; j < allowancelist.size(); j++) {

					int flag = 0;

					for (int k = 0; k < empAllowanceList.size(); k++) {

						if (empAllowanceList.get(k).getEmpId() == list.get(i).getEmpId()
								&& empAllowanceList.get(k).getAllowanceId() == allowancelist.get(j).getAllowanceId()) {

							SalAllownceTemp empAllowance = new SalAllownceTemp();
							empAllowance.setAllowanceId(allowancelist.get(j).getAllowanceId());
							empAllowance.setAllowanceValue(empAllowanceList.get(k).getAllowanceValue());
							empAllowance.setTblSalaryDynamicTempId(saveres.getId());
							empAllowance.setEmpId(saveres.getEmpId());
							empAllowance.setDelStatus(1);
							empAllowance.setShortName(allowancelist.get(j).getShortName());
							allowlist.add(empAllowance);
							flag = 1;
							break;

						}
					}
					if (flag == 0) {
						SalAllownceTemp empAllowance = new SalAllownceTemp();
						empAllowance.setAllowanceId(allowancelist.get(j).getAllowanceId());
						empAllowance.setAllowanceValue(0);
						empAllowance.setTblSalaryDynamicTempId(saveres.getId());
						empAllowance.setEmpId(saveres.getEmpId());
						empAllowance.setDelStatus(1);
						empAllowance.setShortName(allowancelist.get(j).getShortName());
						allowlist.add(empAllowance);
					}
				}
				List<SalAllownceTemp> saveAllores = salAllownceTempRepo.saveAll(allowlist);
			}

			// update record if some ammount insert after insert temp record
			List<SalaryCalcTemp> listForUpdatedValue = salaryCalcTempRepo.listForUpdatedValue(month, year, empIds);

			List<GetAdvanceList> getAdvanceList = new ArrayList<>();
			List<GetClaimList> getClaimList = new ArrayList<>();
			List<GetPayDedList> getPayDedList = new ArrayList<>();
			List<GetPayDedList> getRewardList = new ArrayList<>();
			List<GetPayDedList> getLoanList = new ArrayList<>();
			List<GetPayDedList> getPartialLoanList = new ArrayList<>();
			if (payroll_advance_show == 1) {
				getAdvanceList = getAdvanceListRepo.getAdvanceList(month, year, empIds);
			}

			if (payroll_claim_show == 1) {
				getClaimList = getClaimListRepo.getClaimList(month, year, empIds);
			}
			if (payroll_payded_show == 1) {
				getPayDedList = getPayDedListRepo.getPayDedList(month, year, empIds);
			}

			if (payroll_reward_show == 1) {
				getRewardList = getPayDedListRepo.getBonusList(month, year, empIds);
			}

			if (payroll_loan_show == 1) {
				getLoanList = getPayDedListRepo.getLoanList(year + "-" + month + "-01", empIds);
				getPartialLoanList = getPayDedListRepo.getPartialLoanList(month, year, empIds);
			}

			/*
			 * List<GetPayDedList> getBonusList = getPayDedListRepo.getBonusList(year + "-"
			 * + month + "-01", empIds); List<GetPayDedList> getExgretiaList =
			 * getPayDedListRepo.getExgretiaList(year + "-" + month + "-01", empIds);
			 */

			for (int i = 0; i < listForUpdatedValue.size(); i++) {

				int flag = 0;
				for (int j = 0; j < getAdvanceList.size(); j++) {

					if (getAdvanceList.get(j).getEmpId() == listForUpdatedValue.get(i).getEmpId()) {
						listForUpdatedValue.get(i).setAdvanceDed(getAdvanceList.get(j).getAdvAmount());
						flag = 1;
						break;
					}

				}
				if (flag == 0) {
					listForUpdatedValue.get(i).setAdvanceDed(0);
				}

				flag = 0;
				for (int j = 0; j < getClaimList.size(); j++) {

					if (getClaimList.get(j).getEmpId() == listForUpdatedValue.get(i).getEmpId()) {
						listForUpdatedValue.get(i).setMiscExpAdd(getClaimList.get(j).getClaimAmount());
						flag = 1;
						break;
					}

				}
				if (flag == 0) {
					listForUpdatedValue.get(i).setMiscExpAdd(0);
				}

				flag = 0;
				for (int j = 0; j < getPayDedList.size(); j++) {

					if (getPayDedList.get(j).getEmpId() == listForUpdatedValue.get(i).getEmpId()) {
						listForUpdatedValue.get(i).setPayDed(getPayDedList.get(j).getAmt());
						flag = 1;
						break;
					}

				}
				if (flag == 0) {
					listForUpdatedValue.get(i).setPayDed(0);
				}

				flag = 0;
				for (int j = 0; j < getRewardList.size(); j++) {

					if (getRewardList.get(j).getEmpId() == listForUpdatedValue.get(i).getEmpId()) {
						listForUpdatedValue.get(i).setReward(getRewardList.get(j).getAmt());
						flag = 1;
						break;
					}

				}
				if (flag == 0) {
					listForUpdatedValue.get(i).setReward(0);
				}

				double loanamt = 0;
				flag = 0;
				for (int j = 0; j < getLoanList.size(); j++) {

					if (getLoanList.get(j).getEmpId() == listForUpdatedValue.get(i).getEmpId()) {
						loanamt = loanamt + getLoanList.get(j).getAmt();
						listForUpdatedValue.get(i).setLoanDed(loanamt);
						flag = 1;
						break;
					}

				}

				for (int j = 0; j < getPartialLoanList.size(); j++) {

					if (getPartialLoanList.get(j).getEmpId() == listForUpdatedValue.get(i).getEmpId()) {
						loanamt = loanamt + getPartialLoanList.get(j).getAmt();
						listForUpdatedValue.get(i).setLoanDed(loanamt);
						flag = 1;
						break;
					}

				}

				if (flag == 0) {
					listForUpdatedValue.get(i).setLoanDed(0);
				}

				/*
				 * flag = 0; for (int j = 0; j < getBonusList.size(); j++) {
				 * 
				 * if (getBonusList.get(j).getEmpId() == listForUpdatedValue.get(i).getEmpId())
				 * { listForUpdatedValue.get(i).setBonusCal(getBonusList.get(j).getAmt()); flag
				 * = 1; break; }
				 * 
				 * } if (flag == 0) { listForUpdatedValue.get(i).setBonusCal(0); }
				 * 
				 * flag = 0; for (int j = 0; j < getExgretiaList.size(); j++) {
				 * 
				 * if (getExgretiaList.get(j).getEmpId() ==
				 * listForUpdatedValue.get(i).getEmpId()) {
				 * listForUpdatedValue.get(i).setExgretiaCal(getExgretiaList.get(j).getAmt());
				 * flag = 1; break; }
				 * 
				 * } if (flag == 0) { listForUpdatedValue.get(i).setExgretiaCal(0); }
				 */
			}

			List<SalaryCalcTemp> savereslist = salaryCalcTempRepo.saveAll(listForUpdatedValue);

			info.setError(false);
			info.setMsg("success");
		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/getSalDynamicTempRecord" }, method = RequestMethod.POST)
	public List<GetSalDynamicTempRecord> getSalDynamicTempRecord(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("empIds") List<Integer> empIds) {

		List<GetSalDynamicTempRecord> list = new ArrayList<>();

		try {
			list = getSalDynamicTempRecordRepository.getSalDynamicTempRecord(month, year, empIds);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getSalDynamicTempRecordById" }, method = RequestMethod.POST)
	public GetSalDynamicTempRecord getSalDynamicTempRecordById(
			@RequestParam("tempSalDaynamicId") int tempSalDaynamicId) {

		GetSalDynamicTempRecord getSalDynamicTempRecordById = new GetSalDynamicTempRecord();

		try {
			getSalDynamicTempRecordById = getSalDynamicTempRecordRepository
					.getSalDynamicTempRecordById(tempSalDaynamicId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return getSalDynamicTempRecordById;
	}

	@RequestMapping(value = { "/updateBonusAmt" }, method = RequestMethod.POST)
	public Info updateBonusAmt(@RequestParam("tempSalDaynamicId") int tempSalDaynamicId,
			@RequestParam("itAmt") float itAmt, @RequestParam("perBonus") float perBonus,
			@RequestParam("other1") float other1) {

		Info info = new Info();

		try {
			int update = salaryCalcTempRepo.updateBonusAmt(tempSalDaynamicId, itAmt, perBonus, other1);
			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/sampleWebservice" }, method = RequestMethod.GET)
	public List<SampleClass> sampleWebservice() {

		List<SampleClass> list = new ArrayList<>();

		try {
			SampleClass sampleClass = new SampleClass();
			sampleClass.setValue(10);
			sampleClass.setName("akshay");
			list.add(sampleClass);

			sampleClass = new SampleClass();
			sampleClass.setValue(12);
			sampleClass.setName("kishore");
			list.add(sampleClass);

			for (int i = 0; i < list.size(); i++) {

				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(list.get(i));
				String[] splt = json.substring(1, json.length() - 1).split(",");

				for (int j = 0; j < splt.length; j++) {
					if (splt[j].contains("value")) {
						String[] value = splt[j].split(":");
						// System.out.println(value[1].trim());
						break;
					}
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/calculateSalary" }, method = RequestMethod.POST)
	public List<EmpSalInfoDaiyInfoTempInfo> calculateSalary(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("empIds") List<Integer> empIds,
			@RequestParam("userId") int userId) {

		List<EmpSalInfoDaiyInfoTempInfo> getSalaryTempList = new ArrayList<>();
		try {

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Setting setting = settingRepo.findByKey("designation_id");

			Date firstDay = new GregorianCalendar(year, month - 1, 1).getTime();
			Date lastDay = new GregorianCalendar(year, month, 0).getTime();

			String fromDate = sf.format(firstDay);
			String toDate = sf.format(lastDay);

			List<RoasterSummeryDetail> roasterSummeryDetailList = new ArrayList<>();

			try {
				roasterSummeryDetailList = roasterSummeryDetailRepository.getRoasterSummeryDetailForPayRoll(fromDate,
						toDate, Integer.parseInt(setting.getValue()));
			} catch (Exception e) {

			}
			Date dt = new Date();
			String date = sf.format(dt);
			String[] dates = date.split("-");

			getSalaryTempList = empSalInfoDaiyInfoTempInfoRepo.getSalaryTempList(month, year, empIds);
			List<SalaryTypesMaster> salaryTypeList = salaryTypesMasterRepo.findAllByDelStatus(1);
			List<MstEmpType> mstEmpTypeList = mstEmpTypeRepository.findAll();
			List<SlabMaster> slabMasterList = slabMasterRepository.findAll();// slab
			List<MlwfMaster> mlwfMasterList = mlwfMasterRepository.findAll();
			List<SalaryTerm> salaryTermList = salaryTermRepository.getSalaryTermList();// salary tem
			List<Setting> settingList = settingRepo.findByGroup("PAYROLL");
			List<SalAllownceTemp> getAllowanceTempList = salAllownceTempRepo.getAllowanceTempList(month, year, empIds);

			for (int i = 0; i < getSalaryTempList.size(); i++) {
				List<SalAllownceTemp> list = new ArrayList<>();

				for (int j = 0; j < getAllowanceTempList.size(); j++) {

					if (getAllowanceTempList.get(j).getTblSalaryDynamicTempId() == getSalaryTempList.get(i).getId()) {
						list.add(getAllowanceTempList.get(j));
					}

				}
				getSalaryTempList.get(i).setGetAllowanceTempList(list);
			}

			double epf_wages_employee = 0;
			double epf_wages_employeR = 0;
			double employeePfOnAmt = 0;
			double epf_percentage = 0;
			double cealing_limit_eps_wages = 0;
			double employer_eps_percentage = 0;
			double employer_eps_ceiling_limit = 0;
			double eps_age_limit = 0;
			double esic_limit = 0;
			double employer_epf_percentage = 0;
			double employer_esic_percentage = 0;
			double employee_esic_percentage = 0;
			double tot_pf_admin_ch_percentage = 0;
			double tot_edli_ch_percentage = 0;
			double tot_edli_admin_ch_percentage = 0;
			double employee_ceiling_limit = 0;
			float ab_deduction = 0;
			int febmonthptamount_condtioncheck = 0;
			int add_pf_other = 0;
			double febmonthptamount = 0;
			int amount_round = 0;
			double employee_mlwf = 0;
			double employer_mlwf = 0;
			double night_allo_rate = 0;
			double ot_per_min = 0;
			int ot_rate = 0;
			for (int k = 0; k < settingList.size(); k++) {
				if (settingList.get(k).getKey().equalsIgnoreCase("ceiling_limit_eps_wages")) {
					cealing_limit_eps_wages = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employer_eps_percentage")) {
					employer_eps_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("epf_percentage")) {
					epf_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employer_eps_ceiling_limit")) {
					employer_eps_ceiling_limit = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("eps_age_limit")) {
					eps_age_limit = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("esic_limit")) {
					esic_limit = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employer_esic_percentage")) {
					employer_esic_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employee_esic_percentage")) {
					employee_esic_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("tot_pf_admin_ch")) {
					tot_pf_admin_ch_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("tot_edli_ch")) {
					tot_edli_ch_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("tot_edli_admin_ch")) {
					tot_edli_admin_ch_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("febmonthptamount_condtioncheck")) {
					febmonthptamount_condtioncheck = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("febmonthptamount")) {
					febmonthptamount = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("ammount_format_Insert")) {
					amount_round = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("ab_deduction")) {
					ab_deduction = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employer_epf_percentage")) {
					employer_epf_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employer_mlwf_value_ded")) {
					employer_mlwf = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("mlwf_value_ded")) {
					employee_mlwf = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("night_allo_rate")) {
					night_allo_rate = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("ot_rate")) {
					ot_rate = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("ot_per_min")) {
					ot_per_min = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employee_eps_ceiling_limit")) {
					employee_ceiling_limit = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("add_pf_other")) {
					add_pf_other = Integer.parseInt(settingList.get(k).getValue());
				}
			}

			MstEmpType mstEmpType = new MstEmpType();
			SalaryTypesMaster salaryType = new SalaryTypesMaster();

			for (int i = 0; i < getSalaryTempList.size(); i++) {

				if (getSalaryTempList.get(i).getPfEmpPer() > 0) {
					epf_percentage = getSalaryTempList.get(i).getPfEmpPer();
				}

				for (int j = 0; j < salaryTermList.size(); j++) {
					salaryTermList.get(j).setValue(0);
				}

				for (int j = 0; j < mstEmpTypeList.size(); j++) {

					if (mstEmpTypeList.get(j).getEmpTypeId() == getSalaryTempList.get(i).getEmpType()) {
						mstEmpType = mstEmpTypeList.get(j);
						break;
					}

				}

				for (int j = 0; j < salaryTypeList.size(); j++) {

					if (salaryTypeList.get(j).getSalTypeId() == getSalaryTempList.get(i).getSalTypeId()) {
						salaryType = salaryTypeList.get(j);
						break;
					}

				}

				for (int j = 0; j < salaryTermList.size(); j++) {

					if (salaryTermList.get(j).getSalTypeId() == salaryType.getSalTypeId()) {

						getSalaryTempList.get(i).setNightRate(night_allo_rate);
						getSalaryTempList.get(i)
								.setNightAllow(getSalaryTempList.get(i).getFullNight() * night_allo_rate);

						double ammt = 0;
						int index = 0;
						int findAll = 0;
						if (!salaryTermList.get(j).getFromColumn().equals("")) {
							if (salaryTermList.get(j).getFromColumn().equals("basic")) {
								ammt = getSalaryTempList.get(i).getBasic();
								// System.out.println(ammt);
							} else if (salaryTermList.get(j).getFromColumn().equals("performance_bouns_cal")) {
								ammt = getSalaryTempList.get(i).getPerformanceBonus();
							} else if (salaryTermList.get(j).getFromColumn().equals("society_contribution")) {
								ammt = getSalaryTempList.get(i).getSocietyContribution();
							} else {

								for (int k = 0; k < getSalaryTempList.get(i).getGetAllowanceTempList().size(); k++) {

									if (getSalaryTempList.get(i).getGetAllowanceTempList().get(k).getShortName()
											.equals(salaryTermList.get(j).getFromColumn())) {
										findAll = 1;
										index = k;
										ammt = getSalaryTempList.get(i).getGetAllowanceTempList().get(k)
												.getAllowanceValue();
									}

								}

							}
						}
						switch (salaryTermList.get(j).getFormulaType()) {
						case "F":
							double calculatedValue = calculateFdata(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getPayableDays(),
									getSalaryTempList.get(i).getWorkingDays(), ammt,
									getSalaryTempList.get(i).getPresentDays(),
									getSalaryTempList.get(i).getMonthlyMinimumTarget(),
									getSalaryTempList.get(i).getMonthlyHrTarget(),
									getSalaryTempList.get(i).getTotworkingHrs(), amount_round);
							getSalaryTempList.get(i).setBasicCal(calculatedValue);
							salaryTermList.get(j).setValue(calculatedValue);

							break;
						case "A":
							double tempVal = 0;

							if (findAll == 1) {
								tempVal = calculateAllowancedata(salaryTermList.get(j).getPercentage(),
										getSalaryTempList.get(i).getSalBasis(),
										getSalaryTempList.get(i).getTotalDaysInmonth(),
										getSalaryTempList.get(i).getPayableDays(),
										getSalaryTempList.get(i).getWorkingDays(), ammt,
										getSalaryTempList.get(i).getPresentDays(),
										getSalaryTempList.get(i).getMonthlyMinimumTarget(),
										getSalaryTempList.get(i).getMonthlyHrTarget(),
										getSalaryTempList.get(i).getTotworkingHrs(), amount_round);
								getSalaryTempList.get(i).getGetAllowanceTempList().get(index)
										.setAllowanceValueCal(tempVal);
								salaryTermList.get(j).setValue(tempVal);

								/*
								 * System.out.println(salaryTermList.get(j).getSalTermId() + " " + tempVal + ""
								 * +
								 * getSalaryTempList.get(i).getGetAllowanceTempList().get(index).getShortName())
								 * ;
								 */

							}

							break;
						case "S":
							double tempValNew = 0;
							tempVal = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
									amount_round);

							if (getSalaryTempList.get(i).getPtApplicable().equalsIgnoreCase("yes")) {

								/*
								 * tempVal = tempVal + getSalaryTempList.get(i).getOtWages() +
								 * getSalaryTempList.get(i).getProductionInsentive() +
								 * getSalaryTempList.get(i).getNightAllow();
								 */

								for (int k = 0; k < slabMasterList.size(); k++) {

									if (slabMasterList.get(k).getSalTermId() == salaryTermList.get(j).getSalTermId()
											&& slabMasterList.get(k).getExInt1() == getSalaryTempList.get(i)
													.getCurrentLoc()) {

										if (tempVal >= slabMasterList.get(k).getMinVal()
												&& tempVal <= slabMasterList.get(k).getMaxVal()
												&& getSalaryTempList.get(i).getGender().equalsIgnoreCase("male")
												&& slabMasterList.get(k).getGender() == 1) {

											if (getSalaryTempList.get(i).getMonth() == 2 && slabMasterList.get(k)
													.getAmount() == febmonthptamount_condtioncheck) {
												tempValNew = febmonthptamount;
											} // $calc_month == "2" && $amt == "200"
											else {
												tempValNew = slabMasterList.get(k).getAmount();
											}
											break;
										} else if (tempVal >= slabMasterList.get(k).getMinVal()
												&& tempVal <= slabMasterList.get(k).getMaxVal()
												&& getSalaryTempList.get(i).getGender().equalsIgnoreCase("female")
												&& slabMasterList.get(k).getGender() == 2) {

											if (getSalaryTempList.get(i).getMonth() == 2 && slabMasterList.get(k)
													.getAmount() == febmonthptamount_condtioncheck) {
												tempValNew = febmonthptamount;
											} // $calc_month == "2" && $amt == "200"
											else {
												tempValNew = slabMasterList.get(k).getAmount();
											}
											break;
										}
									}

								}
							}

							getSalaryTempList.get(i).setPtDed(tempValNew);
							salaryTermList.get(j).setValue(tempValNew);

							break;
						case "X":
							ammt = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
									amount_round);

							tempVal = calculateXdata(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getPayableDays(),
									getSalaryTempList.get(i).getWorkingDays(), ammt,
									getSalaryTempList.get(i).getPresentDays(), amount_round);
							// getSalaryTempList.get(i).setBasicDefault(tempVal);
							salaryTermList.get(j).setValue(tempVal);

							/*
							 * System.out.println(getSalaryTempList.get(i).getEmpId() + " " + ammt +
							 * " termid " + salaryTermList.get(j).getSalTermId() + " value " +
							 * salaryTermList.get(j).getValue() + " basic" +
							 * getSalaryTempList.get(i).getBasic());
							 */
							/* System.out.println(salaryTermList); */
							break;
						case "XA":

							if (findAll == 1) {
								salaryTermList.get(j).setValue(getSalaryTempList.get(i).getGetAllowanceTempList()
										.get(index).getAllowanceValue());

								// System.out.println(salaryTermList.get(j));

							}
							break;
						case "P":
							double temp = calculatePdata(salaryTermList.get(j), salaryTermList,
									getSalaryTempList.get(i), amount_round);

							if (salaryTermList.get(j).getFieldName().equals("gross_salary")) {
								getSalaryTempList.get(i).setGrossSalaryDytemp(temp);

								salaryTermList.get(j).setValue(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("epf_wages")) {
								// System.out.println(temp);
								getSalaryTempList.get(i).setEpfWages(temp);
								salaryTermList.get(j).setValue(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("esic_wages_cal")) {
								getSalaryTempList.get(i).setEsicWagesCal(temp);
								salaryTermList.get(j).setValue(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("eps_wages_cal")) {
								getSalaryTempList.get(i).setEpsWages(temp);
								salaryTermList.get(j).setValue(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("esic_wages_dec_cal")) {
								getSalaryTempList.get(i).setEsicWagesDec(temp);
								salaryTermList.get(j).setValue(temp);
							} else {
								salaryTermList.get(j).setValue(temp);
							}

							break;
						case "NET":
							temp = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
									amount_round);
							getSalaryTempList.get(i).setNetSalary(temp);
							salaryTermList.get(j).setValue(temp);
							break;
						case "Y":
							// tempVal = castNumber(ammt);

							if (salaryTermList.get(j).getFieldName().equals("performance_bouns_cal")) {
								getSalaryTempList.get(i).setPerformanceBonus(ammt);
							} else if (salaryTermList.get(j).getFieldName().equals("basic_default")) {
								getSalaryTempList.get(i).setBasicDefault(ammt);
							} else if (salaryTermList.get(j).getFieldName().equals("society_contribution")) {
								getSalaryTempList.get(i).setSocietyContributionDytemp(ammt);
							}
							salaryTermList.get(j).setValue(ammt);

							// System.out.println(salaryTermList.get(j).getFieldName()+ " " +
							// getSalaryTempList.get(i).getBasicDefault());

							/* System.out.println(salaryTermList); */
							break;

						case "C1":

							break;
						case "OT":

							if (mstEmpType.getOtApplicable().equalsIgnoreCase("yes")) {

								ammt = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
										amount_round);

								tempVal = otwages(salaryTermList.get(j).getPercentage(),
										getSalaryTempList.get(i).getSalBasis(),
										getSalaryTempList.get(i).getTotalDaysInmonth(),
										getSalaryTempList.get(i).getPayableDays(),
										getSalaryTempList.get(i).getWorkingDays(),
										getSalaryTempList.get(i).getOtPerHr(), getSalaryTempList.get(i).getTotOthr(),
										ammt, mstEmpType, getSalaryTempList.get(i).getRate(), ot_rate,
										getSalaryTempList.get(i).getMonthlyHrTarget(), amount_round);
								getSalaryTempList.get(i).setOtWages(tempVal);
								salaryTermList.get(j).setValue(tempVal);

								getSalaryTempList.get(i).setOtRate(getSalaryTempList.get(i).getRate());
								// System.out.println("tempVal " + tempVal);
							}

							break;
						case "OTFD":

							break;
						case "WOP":

							ammt = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
									amount_round);

							tempVal = otwageswo(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getWeeklyOffPresent(),
									getSalaryTempList.get(i).getHolidayPresent(),
									getSalaryTempList.get(i).getWeeklyOffHolidayOffPresent(),
									getSalaryTempList.get(i).getAtsummUid(), getSalaryTempList.get(i).getWorkingDays(),
									ammt, mstEmpType, amount_round);
							getSalaryTempList.get(i).setProductionInsentive(tempVal);
							salaryTermList.get(j).setValue(tempVal);
							// System.out.println(ammt + "oTTTTT" + tempVal);

							/*
							 * System.out.println(getSalaryTempList.get(i).getEmpId() + " " + ammt +
							 * " termid " + salaryTermList.get(j).getSalTermId() + " value " +
							 * salaryTermList.get(j).getValue() + " basic" +
							 * getSalaryTempList.get(i).getBasic());
							 */

							break;
						case "AB":

							ammt = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
									amount_round);

							if (getSalaryTempList.get(i).getSalBasis().equalsIgnoreCase("monthly")) {
								tempVal = ammt / getSalaryTempList.get(i).getTotalDaysInmonth();
								double deductValue = castNumber(
										(tempVal * getSalaryTempList.get(i).getAbsentDays() * ab_deduction * 2),
										amount_round);
								getSalaryTempList.get(i).setAbDeduction(deductValue);
								salaryTermList.get(j).setValue(deductValue);
							} else if (getSalaryTempList.get(i).getSalBasis().equalsIgnoreCase("daily")) {
								tempVal = ammt / getSalaryTempList.get(i).getWorkingDays();
								double deductValue = castNumber(
										(tempVal * getSalaryTempList.get(i).getAbsentDays() * ab_deduction * 2),
										amount_round);
								getSalaryTempList.get(i).setAbDeduction(deductValue);
								salaryTermList.get(j).setValue(deductValue);
							} else {
								getSalaryTempList.get(i).setAbDeduction(0);
								salaryTermList.get(j).setValue(0);
							}

							break;
						case "FD":
							tempVal = fundwages(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(), ammt, amount_round);
							salaryTermList.get(j).setValue(tempVal);
							break;
						default:

							break;

						}

					}

				}

				if (getSalaryTempList.get(i).getMlwfApplicable().equalsIgnoreCase("yes")) {

					// System.out.println("mlwf applicable");
					for (int k = 0; k < mlwfMasterList.size(); k++) {

						// System.out.println(mlwfMasterList.get(k).getLocationId() + " location " +
						// getSalaryTempList.get(i).getCurrentLoc());
						// System.out.println(mlwfMasterList.get(k).getMonth() + " month " + month);
						if (mlwfMasterList.get(k).getLocationId() == getSalaryTempList.get(i).getCurrentLoc()
								&& mlwfMasterList.get(k).getMonth() == month) {
							// System.out.println("in if");
							getSalaryTempList.get(i).setMlwf(mlwfMasterList.get(k).getEmployeeValue());
							getSalaryTempList.get(i).setEmployerMlwf(mlwfMasterList.get(k).getEmployerValue());
							break;
						}

					}
					/*
					 * if (month == 6 || month == 12) {
					 * getSalaryTempList.get(i).setMlwf(employee_mlwf);
					 * getSalaryTempList.get(i).setEmployerMlwf(employer_mlwf); }
					 */
				} else {
					getSalaryTempList.get(i).setMlwf(0);
					getSalaryTempList.get(i).setEmployerMlwf(0);
				}

				try {
					// System.out.println("PF ==== " +
					// getSalaryTempList.get(i).getPfApplicable()+"#"+getSalaryTempList.get(i).getEpfWages());
					if (getSalaryTempList.get(i).getPfApplicable().equalsIgnoreCase("yes")) {

						getSalaryTempList.get(i).setPfStatus(1);
						epf_wages_employee = getSalaryTempList.get(i).getEpfWages();

						/*
						 * try { if (getSalaryTempList.get(i).getPfType().equalsIgnoreCase("voluntary"))
						 * { getSalaryTempList.get(i).setEmployeePf(castNumber( (epf_wages_employee *
						 * getSalaryTempList.get(i).getPfEmpPer()), amount_round));
						 * getSalaryTempList.get(i).setEpfPercentage(getSalaryTempList.get(i).
						 * getPfEmpPer());
						 * 
						 * } else {
						 * 
						 * getSalaryTempList.get(i) .setEmployeePf(castNumber((epf_wages_employee *
						 * epf_percentage), amount_round));
						 * getSalaryTempList.get(i).setEpfPercentage(epf_percentage); }
						 * 
						 * } catch (Exception e) { e.printStackTrace(); }
						 */

						String[] dob = getSalaryTempList.get(i).getDob().split("-");

						LocalDate birthDate = LocalDate.of(Integer.parseInt(dob[0]), Integer.parseInt(dob[1]),
								Integer.parseInt(dob[2]));
						int age = calculateAge(birthDate, LocalDate.of(Integer.parseInt(dates[0]),
								Integer.parseInt(dates[1]), Integer.parseInt(dates[2])));

						if (age <= eps_age_limit) {
							try {

								System.out.println(" ** employee "
										+ getSalaryTempList.get(i).getCeilingLimitEmpApplicable() + " ** employer "
										+ getSalaryTempList.get(i).getCeilingLimitEmployerApplicable());

								if (getSalaryTempList.get(i).getCeilingLimitEmpApplicable().equalsIgnoreCase("yes")
										&& getSalaryTempList.get(i).getCeilingLimitEmployerApplicable()
												.equalsIgnoreCase("yes")) {
									System.out.println("innnn if");
									int isAddOther = 0;

									// employer pf
									if (getSalaryTempList.get(i).getEpfWages() > employer_eps_ceiling_limit) {
										epf_wages_employeR = employer_eps_ceiling_limit;

									} else {
										epf_wages_employeR = getSalaryTempList.get(i).getEpfWages();
									}

									// employee pf
									if (getSalaryTempList.get(i).getEpfWages() > employee_ceiling_limit) {
										employeePfOnAmt = employee_ceiling_limit;
										isAddOther = 1;

									} else {
										employeePfOnAmt = getSalaryTempList.get(i).getEpfWages();

									}
									// System.out.println("isAddOther" + isAddOther + " add_pf_other " +
									// add_pf_other);
									double pfAmt = employeePfOnAmt * epf_percentage;
									getSalaryTempList.get(i).setEmployeePf(castNumber(pfAmt, amount_round));
									getSalaryTempList.get(i).setEpfPercentage(epf_percentage);

									/*
									 * if (isAddOther == 1 && add_pf_other == 1) { double pfAmtDefault =
									 * getSalaryTempList.get(i).getEpfWages() * epf_percentage;
									 * 
									 * if (pfAmt < pfAmtDefault) {
									 * 
									 * for (int k = 0; k < getSalaryTempList.get(i).getGetAllowanceTempList()
									 * .size(); k++) {
									 * 
									 * if (getSalaryTempList.get(i).getGetAllowanceTempList().get(k)
									 * .getShortName().equals("OTH")) { System.out.println("innnn");
									 * getSalaryTempList.get(i).getGetAllowanceTempList().get(k)
									 * .setAllowanceValueCal(pfAmtDefault - pfAmt);
									 * getSalaryTempList.get(i).setGrossSalaryDytemp(
									 * getSalaryTempList.get(i).getGrossSalaryDytemp() + (pfAmtDefault - pfAmt));
									 * break; }
									 * 
									 * }
									 * 
									 * } }
									 */

								} else if (getSalaryTempList.get(i).getCeilingLimitEmpApplicable()
										.equalsIgnoreCase("no")
										&& getSalaryTempList.get(i).getCeilingLimitEmployerApplicable()
												.equalsIgnoreCase("yes")) {
									// employer pf
									if (getSalaryTempList.get(i).getEpfWages() > employer_eps_ceiling_limit) {
										epf_wages_employeR = employer_eps_ceiling_limit;

									} else {
										epf_wages_employeR = getSalaryTempList.get(i).getEpfWages();
									}

									// employee pf

									employeePfOnAmt = getSalaryTempList.get(i).getEpfWages();
									getSalaryTempList.get(i).setEmployeePf(
											castNumber((employeePfOnAmt * epf_percentage), amount_round));
									getSalaryTempList.get(i).setEpfPercentage(epf_percentage);

								} else if (getSalaryTempList.get(i).getCeilingLimitEmpApplicable()
										.equalsIgnoreCase("yes")
										&& getSalaryTempList.get(i).getCeilingLimitEmployerApplicable()
												.equalsIgnoreCase("no")) {

									int isAddOther = 0;

									// employer pf
									epf_wages_employeR = getSalaryTempList.get(i).getEpfWages();
									// employee pf
									if (getSalaryTempList.get(i).getEpfWages() > employee_ceiling_limit) {
										employeePfOnAmt = employee_ceiling_limit;
										isAddOther = 1;

									} else {
										employeePfOnAmt = getSalaryTempList.get(i).getEpfWages();
									}

									double pfAmt = employeePfOnAmt * epf_percentage;
									getSalaryTempList.get(i).setEmployeePf(castNumber(pfAmt, amount_round));
									getSalaryTempList.get(i).setEpfPercentage(epf_percentage);

									/*
									 * if (isAddOther == 1 && add_pf_other == 1) { double pfAmtDefault =
									 * getSalaryTempList.get(i).getEpfWages() * epf_percentage;
									 * 
									 * if (pfAmt < pfAmtDefault) {
									 * 
									 * for (int k = 0; k < getSalaryTempList.get(i).getGetAllowanceTempList()
									 * .size(); k++) {
									 * 
									 * if (getSalaryTempList.get(i).getGetAllowanceTempList().get(k)
									 * .getShortName().equals("OTH")) {
									 * getSalaryTempList.get(i).getGetAllowanceTempList().get(k)
									 * .setAllowanceValueCal(pfAmtDefault - pfAmt);
									 * getSalaryTempList.get(i).setGrossSalaryDytemp(
									 * getSalaryTempList.get(i).getGrossSalaryDytemp() + (pfAmtDefault - pfAmt));
									 * break; }
									 * 
									 * }
									 * 
									 * } }
									 */
								} else {
									epf_wages_employeR = getSalaryTempList.get(i).getEpfWages();
									employeePfOnAmt = getSalaryTempList.get(i).getEpfWages();

									getSalaryTempList.get(i).setEmployeePf(
											castNumber((employeePfOnAmt * epf_percentage), amount_round));
									getSalaryTempList.get(i).setEpfPercentage(epf_percentage);
								}
							} catch (Exception e) {
								e.printStackTrace();

								// employer pf
								if (getSalaryTempList.get(i).getEpfWages() > employer_eps_ceiling_limit) {
									epf_wages_employeR = employer_eps_ceiling_limit;

								} else {
									epf_wages_employeR = getSalaryTempList.get(i).getEpfWages();
								}

								// employee pf

								employeePfOnAmt = getSalaryTempList.get(i).getEpfWages();
								getSalaryTempList.get(i)
										.setEmployeePf(castNumber((employeePfOnAmt * epf_percentage), amount_round));
								getSalaryTempList.get(i).setEpfPercentage(epf_percentage);
							}

							/*
							 * if (getSalaryTempList.get(i).getEpfWages() > employer_eps_ceiling_limit) {
							 * epf_wages_employeR = employer_eps_ceiling_limit;
							 * 
							 * } else { epf_wages_employeR = getSalaryTempList.get(i).getEpfWages();
							 * 
							 * }
							 */

							double employer_eps_default = getSalaryTempList.get(i).getEpfWages()
									* employer_eps_percentage;
							double employer_eps = epf_wages_employeR * employer_eps_percentage;

							// employer_eps
							getSalaryTempList.get(i).setEpsWages(castNumber(epf_wages_employeR, amount_round));
							getSalaryTempList.get(i).setEpsDefault(castNumber(employer_eps_default, amount_round));
							getSalaryTempList.get(i)
									.setEmployerEps(castNumber(castNumber(employer_eps, amount_round), amount_round));
							getSalaryTempList.get(i).setEpsEmployerPercentage(employer_eps_percentage);

							// employer_epf
							double employer_epf_default = getSalaryTempList.get(i).getEpfWages()
									* employer_epf_percentage;
							getSalaryTempList.get(i).setEpfWagesEmployer(
									castNumber(getSalaryTempList.get(i).getEpfWages(), amount_round));
							getSalaryTempList.get(i)
									.setEpmloyerEpfDefault(castNumber(employer_epf_default, amount_round));
							getSalaryTempList.get(i).setEpfEmployerPercentage(employer_epf_percentage);

							if (employer_eps < employer_eps_default) {
								getSalaryTempList.get(i).setEpmloyerEpfExtra(
										castNumber((employer_eps_default - employer_eps), amount_round));
							}
							getSalaryTempList.get(i)
									.setEmployerPf(castNumber(getSalaryTempList.get(i).getEpmloyerEpfDefault()
											+ getSalaryTempList.get(i).getEpmloyerEpfExtra(), amount_round));

							/*
							 * System.out.println(getSalaryTempList.get(i).getEmpName() + " " +
							 * getSalaryTempList.get(i).getEmployeePf() + " " +
							 * getSalaryTempList.get(i).getPfApplicable());
							 */

						} else {
							getSalaryTempList.get(i).setEpsWages(0);
							getSalaryTempList.get(i).setEpsDefault(0);
							getSalaryTempList.get(i).setEmployerEps(0);
							getSalaryTempList.get(i).setEpsEmployerPercentage(0);

							getSalaryTempList.get(i).setEpfWagesEmployer(0);
							getSalaryTempList.get(i).setEpmloyerEpfDefault(0);
							getSalaryTempList.get(i).setEpfEmployerPercentage(0);
							getSalaryTempList.get(i).setEpmloyerEpfExtra(0);
							getSalaryTempList.get(i).setEmployerPf(0);
						}
						// employer_pf calculation

					} // strtolower($value->pf_applicable) == 'yes'
					else // $emp->pf_applicable)=='no'
					{
						getSalaryTempList.get(i).setEmployeePf(0);
						getSalaryTempList.get(i).setEpfPercentage(0);
						getSalaryTempList.get(i).setPfStatus(0);
						getSalaryTempList.get(i).setEpfWages(0);
						getSalaryTempList.get(i).setEpsEmployeePercentage(0);

						getSalaryTempList.get(i).setEpsWages(0);
						getSalaryTempList.get(i).setEpsDefault(0);
						getSalaryTempList.get(i).setEmployerEps(0);
						getSalaryTempList.get(i).setEpsEmployerPercentage(0);

						getSalaryTempList.get(i).setEpfWagesEmployer(0);
						getSalaryTempList.get(i).setEpmloyerEpfDefault(0);
						getSalaryTempList.get(i).setEpfEmployerPercentage(0);
						getSalaryTempList.get(i).setEpmloyerEpfExtra(0);
						getSalaryTempList.get(i).setEmployerPf(0);

					}
				} catch (Exception e) {
					// e.printStackTrace();
				}
				try {
					// esic cal start
					if (getSalaryTempList.get(i).getEsicApplicable().equalsIgnoreCase("no")) {
						// $records['esic_wages_cal'] = 0;

						getSalaryTempList.get(i).setEmployerEsic(0);
						getSalaryTempList.get(i).setEsic(0);
						getSalaryTempList.get(i).setEsicWagesCal(0);

						getSalaryTempList.get(i).setEsicStatus(0);
					} // strtolower($value->esic_applicable) == 'no'
					else {

						getSalaryTempList.get(i).setEsicStatus(1);

						// we have not add any code to avoid esci deduction. as it mendatory to deduct
						// esic till some month, but kishore has changed the code to cut the esic
						if (getSalaryTempList.get(i).getEsicWagesDec() >= esic_limit) {
							getSalaryTempList.get(i).setEmployerEsic(0);
							getSalaryTempList.get(i).setEsic(0);
						} else {

							getSalaryTempList.get(i)
									.setEmployerEsic(castNumber(
											((getSalaryTempList.get(i).getEsicWagesCal()) * employer_esic_percentage),
											amount_round));
							getSalaryTempList.get(i).setEsic(castNumber(
									((getSalaryTempList.get(i).getEsicWagesCal()) * employee_esic_percentage), 3));
						}
						/*
						 * System.out.println("ESIC ==== " + getSalaryTempList.get(i).getEsicWagesCal()
						 * + "##" + employee_esic_percentage);
						 */

					}
				} catch (Exception e) {
					// e.printStackTrace();
				}

				for (int k = 0; k < roasterSummeryDetailList.size(); k++) {

					if (roasterSummeryDetailList.get(k).getEmpId() == getSalaryTempList.get(i).getEmpId()) {
						getSalaryTempList.get(i).setBhatta(roasterSummeryDetailList.get(k).getIncentive());
						break;
					}

				}

				getSalaryTempList.get(i).setTotPfAdminCh(castNumber(
						(getSalaryTempList.get(i).getEpsWages() * tot_pf_admin_ch_percentage), amount_round));
				getSalaryTempList.get(i).setTotEdliCh(
						castNumber((getSalaryTempList.get(i).getEpsWages() * tot_edli_ch_percentage), amount_round));
				getSalaryTempList.get(i).setTotEdliAdminCh(castNumber(
						(getSalaryTempList.get(i).getEpsWages() * tot_edli_admin_ch_percentage), amount_round));
				getSalaryTempList.get(i).setPfAdminChPercentage(tot_pf_admin_ch_percentage);
				getSalaryTempList.get(i).setEdliPercentage(tot_edli_ch_percentage);
				getSalaryTempList.get(i).setEdliAdminPercentage(tot_edli_admin_ch_percentage);
				/*
				 * getSalaryTempList.get(i).setGrossSalaryDytemp(castNumber(getSalaryTempList.
				 * get(i).getGrossSalaryDytemp() + getSalaryTempList.get(i).getOtWages() +
				 * getSalaryTempList.get(i).getProductionInsentive() +
				 * getSalaryTempList.get(i).getNightAllow(), amount_round));
				 */
				getSalaryTempList.get(i).setStatusDytemp(1);
				getSalaryTempList.get(i).setNetSalary(castNumber((getSalaryTempList.get(i).getGrossSalaryDytemp()
						+ getSalaryTempList.get(i).getPerformanceBonus() + getSalaryTempList.get(i).getMiscExpAdd()
						+ getSalaryTempList.get(i).getReward() + getSalaryTempList.get(i).getOtWages()
						+ getSalaryTempList.get(i).getProductionInsentive() + getSalaryTempList.get(i).getNightAllow()
						+ getSalaryTempList.get(i).getBhatta() + getSalaryTempList.get(i).getOther1())
						- (getSalaryTempList.get(i).getAdvanceDed() + getSalaryTempList.get(i).getLoanDed()
								+ getSalaryTempList.get(i).getPayDed() + getSalaryTempList.get(i).getEsic()
								+ getSalaryTempList.get(i).getEmployeePf() + getSalaryTempList.get(i).getPtDed()
								+ getSalaryTempList.get(i).getItded() + getSalaryTempList.get(i).getTds()
								+ getSalaryTempList.get(i).getSocietyContributionDytemp()),
						amount_round));

				getSalaryTempList.get(i).setLoginName(String.valueOf(userId));
				// System.out.println(salaryTermList);
			}

			// System.out.println(getSalaryTempList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return getSalaryTempList;
	}

	public double calculateFdata(float percentage, String salBasis, int totalDaysInMonth, float payableDays,
			float workingDays, double ammt, float presentDays, String monthlyMinimumHr, String monthlyTargetHr,
			float workingHrs, int amount_round) {

		double val = 0;

		String[] monthlyMinimumHrarr = monthlyMinimumHr.split("\\.");
		int monthlyMiniMin = 0;
		try {
			if (monthlyMinimumHrarr.length > 1) {

				monthlyMiniMin = (Integer.parseInt(monthlyMinimumHrarr[0]) * 60)
						+ Integer.parseInt(monthlyMinimumHrarr[1]);

			} else {
				monthlyMiniMin = (Integer.parseInt(monthlyMinimumHrarr[0]) * 60);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] monthlyTargetHrarr = monthlyTargetHr.split("\\.");
		int monthlyTargetMin = 0;
		try {
			if (monthlyTargetHrarr.length > 1) {

				monthlyTargetMin = (Integer.parseInt(monthlyTargetHrarr[0]) * 60)
						+ Integer.parseInt(monthlyTargetHrarr[1]);

			} else {
				monthlyTargetMin = (Integer.parseInt(monthlyTargetHrarr[0]) * 60);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (percentage == 1) {
			float totalPayableDaysTemp = Math.min(payableDays, totalDaysInMonth);
			// System.out.println(ammt);
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (ammt / totalDaysInMonth) * totalPayableDaysTemp;
			} // $sal_basis == "monthly"
			else if (salBasis.equalsIgnoreCase("hour")) {
				if (workingHrs >= monthlyMiniMin) {
					val = ammt;
				} else {
					ammt = ammt / monthlyTargetMin;
					val = ammt * workingHrs;
				}
			} else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (ammt) * totalPayableDaysTemp;
			}
		} // $percentage == 1
		else if (percentage == 2) {
			float totalPayableDaysTemp = Math.min(payableDays, totalDaysInMonth);
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (ammt / totalDaysInMonth) * totalPayableDaysTemp;
			} // $sal_basis == "monthly"
			else if (salBasis.equalsIgnoreCase("hour")) {
				if (workingHrs >= monthlyMiniMin) {
					val = ammt;
				} else {
					ammt = ammt / monthlyTargetMin;
					val = ammt * workingHrs;
				}
			} else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (ammt / workingDays) * totalPayableDaysTemp;
			}
		} // $percentage == 2
		else if (percentage == 3) {
			float totalPayableDaysTemp = Math.min(payableDays, totalDaysInMonth);

			if (salBasis.equalsIgnoreCase("hour")) {
				if (workingHrs >= monthlyMiniMin) {
					val = ammt;
				} else {
					ammt = ammt / monthlyTargetMin;
					val = ammt * workingHrs;
				}
			} else {
				val = (ammt / totalDaysInMonth) * totalPayableDaysTemp;
			}

		} // $percentage == 2
		val = castNumber(val, amount_round);
		return val;
	}

	public double calculateAllowancedata(float percentage, String salBasis, int totalDays, float totalPayableDays,
			float workingDays, double amount, float presentDays, String monthlyMinimumHr, String monthlyTargetHr,
			float workingHrs, int amount_round) {

		double val = 0;

		String[] monthlyMinimumHrarr = monthlyMinimumHr.split("\\.");
		int monthlyMiniMin = 0;
		try {
			if (monthlyMinimumHrarr.length > 1) {

				monthlyMiniMin = (Integer.parseInt(monthlyMinimumHrarr[0]) * 60)
						+ Integer.parseInt(monthlyMinimumHrarr[1]);

			} else {
				monthlyMiniMin = (Integer.parseInt(monthlyMinimumHrarr[0]) * 60);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] monthlyTargetHrarr = monthlyTargetHr.split("\\.");
		int monthlyTargetMin = 0;
		try {
			if (monthlyTargetHrarr.length > 1) {

				monthlyTargetMin = (Integer.parseInt(monthlyTargetHrarr[0]) * 60)
						+ Integer.parseInt(monthlyTargetHrarr[1]);

			} else {
				monthlyTargetMin = (Integer.parseInt(monthlyTargetHrarr[0]) * 60);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (percentage == 0) {
			val = castNumber(amount, amount_round);
			// val = castNumber(amount);
		} // $percentage == "0"
		else if (percentage == 1) {
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (amount / totalDays) * totalPayableDays;
			} // $sal_basis == "monthly"
			else if (salBasis.equalsIgnoreCase("hour")) {
				if (workingHrs >= monthlyMiniMin) {
					val = amount;
				} else {
					amount = amount / monthlyTargetMin;
					val = amount * workingHrs;
				}
			} else {
				val = amount * totalPayableDays;
			}

			// val = castNumber(val);
		} // $percentage == 1
		else if (percentage == 2) {
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (amount / totalDays) * presentDays;
			} // $sal_basis == "monthly"
			else if (salBasis.equalsIgnoreCase("hour")) {
				if (workingHrs >= monthlyMiniMin) {
					val = amount;
				} else {
					amount = amount / monthlyTargetMin;
					val = amount * workingHrs;
				}
			} else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = amount * totalPayableDays;
			}
			// val = castNumber(val);
		} // $percentage == 2
		else if (percentage == 3) {
			float totalPayableDaysTemp = Math.min(totalPayableDays, totalDays);
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (amount / totalDays) * totalPayableDaysTemp;
			} // $sal_basis == "monthly"
			else if (salBasis.equalsIgnoreCase("hour")) {
				if (workingHrs >= monthlyMiniMin) {
					val = amount;
				} else {
					amount = amount / monthlyTargetMin;
					val = amount * workingHrs;
				}
			} else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (amount / totalDays) * totalPayableDaysTemp;
			}
			// val = castNumber(val);
		} // $percentage == 3
		else if (percentage == 4) {
			float totalPayableDaysTemp = Math.min(totalPayableDays, totalDays);
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (amount / totalDays) * totalPayableDaysTemp;
			} // $sal_basis == "monthly"
			else if (salBasis.equalsIgnoreCase("hour")) {
				if (workingHrs >= monthlyMiniMin) {
					val = amount;
				} else {
					amount = amount / monthlyTargetMin;
					val = amount * workingHrs;
				}
			} else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (amount / workingDays) * totalPayableDaysTemp;
			}
			// val = castNumber(val);
		} // $percentage == 4

		val = castNumber(val, amount_round);
		return val;
	}

	public double calculateXdata(float percentage, String salBasis, int totalDays, float totalPayableDays,
			float workingDays, double amount, float presentDays, int amount_round) {

		double val = 0;
		if (salBasis.equalsIgnoreCase("monthly")) {
			val = amount;
		} // $sal_basis == "monthly"
		else {
			// $val = ($amount / $working_days ) * $total_payable_days;
			val = (amount / totalDays);
		}
		val = castNumber(val, amount_round);
		return val;

	}

	public double calculatePdata(SalaryTerm salaryTerm, List<SalaryTerm> salaryTermList,
			EmpSalInfoDaiyInfoTempInfo empSalInfoDaiyInfoTempInfo, int amount_round) {

		String formula = salaryTerm.getFormula();
		String[] plus = formula.split("\\+");
		String[] minus = formula.split("\\-");

		double value = 0;

		if (plus.length > 1) {
			// System.out.println("add" + formula);
			for (int i = 0; i < plus.length; i++) {
				for (int j = 0; j < salaryTermList.size(); j++) {
					if (Integer.parseInt(plus[i]) == salaryTermList.get(j).getSalTermId()
							&& salaryTermList.get(j).getSalTypeId() == salaryTerm.getSalTypeId()) {
						value = value + salaryTermList.get(j).getValue();
						/*
						 * System.out .println(salaryTermList.get(j).getSalTermId() + " " +
						 * salaryTermList.get(j).getValue() + " " +
						 * empSalInfoDaiyInfoTempInfo.getEmpId());
						 */
						break;
					}
				}

			}
		} else if (minus.length > 1) {
			for (int i = 0; i < minus.length; i++) {
				for (int j = 0; j < salaryTermList.size(); j++) {
					if (Integer.parseInt(minus[i]) == salaryTermList.get(j).getSalTermId()
							&& salaryTermList.get(j).getSalTypeId() == salaryTerm.getSalTypeId()) {
						if (i == 0) {
							value = salaryTermList.get(j).getValue();
						} else {
							value = value - salaryTermList.get(j).getValue();
						}
						break;
					}
				}

			}
		} else if (minus.length == 1 || plus.length == 1) {
			for (int j = 0; j < salaryTermList.size(); j++) {
				if (Integer.parseInt(minus[0]) == salaryTermList.get(j).getSalTermId()) {
					value = salaryTermList.get(j).getValue();
					break;
				}
			}
		}
		value = castNumber(value, amount_round);
		return value;
	}

	public double otwages(float percentage, String salBasis, int totalDays, float totalPayableDays, float workingDays,
			double workingHour, float otHr, double ammt, MstEmpType mstEmpType, double rate, int ot_rate,
			String monthlyTargetHr, int amount_round) {

		int monthlyTargetMin = 0;
		try {
			String[] monthlyTargetHrarr = monthlyTargetHr.split("\\.");
			if (monthlyTargetHrarr.length > 1) {

				monthlyTargetMin = (Integer.parseInt(monthlyTargetHrarr[0]) * 60)
						+ Integer.parseInt(monthlyTargetHrarr[1]);

			} else {
				monthlyTargetMin = (Integer.parseInt(monthlyTargetHrarr[0]) * 60);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		double val = 0;

		if (mstEmpType.getOtApplicable().equalsIgnoreCase("yes")) {

			if (ot_rate == 0) {

				val = ((ammt / workingHour) * otHr) * Integer.parseInt(mstEmpType.getOtType());

				if (salBasis.equalsIgnoreCase("monthly")) {
					val = (((ammt / totalDays) / workingHour) * otHr) * Integer.parseInt(mstEmpType.getOtType());
				} else if (salBasis.equalsIgnoreCase("hour")) {

					val = (((ammt / monthlyTargetMin)) * otHr) * Integer.parseInt(mstEmpType.getOtType());

				} else {
					val = (((ammt / workingDays) / workingHour) * otHr) * Integer.parseInt(mstEmpType.getOtType());
				}

			} else {

				double rateofmin = (rate / 60);
				val = otHr * rateofmin;
			}
		}
		System.out.println(val + "***" + mstEmpType.getOtType());
		val = castNumber(val, amount_round);
		return val;
	}

	public double otwageswo(float percentage, String salBasis, int totalDays, float woPresent, float phPresent,
			float woHoPresent, String markCompoffCount, float workingDays, double ammt, MstEmpType mstEmpType,
			int amount_round) {

		double perDayGrossSal = 0;

		if (salBasis.equalsIgnoreCase("monthly")) {

			perDayGrossSal = (ammt / totalDays)
					* (woPresent + phPresent + woHoPresent - Integer.parseInt(markCompoffCount));
		} else {

			perDayGrossSal = (ammt / workingDays)
					* (woPresent + phPresent + woHoPresent - Integer.parseInt(markCompoffCount));
		}

		perDayGrossSal = castNumber(perDayGrossSal, amount_round);

		return perDayGrossSal;
	}

	public double fundwages(float percentage, String salBasis, double ammt, int amount_round) {
		double val = 0;
		float per = percentage / 100;
		val = ammt * per;
		val = castNumber(val, amount_round);
		return val;
	}

	public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		} else {
			return 0;
		}
	}

	public double castNumber(double val, int amount_round) {
		switch (amount_round) {
		case 1:
			val = Math.round(val);
			break;
		case 2:
			DecimalFormat df = new DecimalFormat("#.00");
			val = Double.parseDouble(df.format(val));
			break;
		case 3:
			val = Math.ceil(val);
			break;
		case 4:
			val = Math.floor(val);
			break;
		}
		return val;
	}

	@RequestMapping(value = { "/insertFinalPayRollAndDeleteFroTemp" }, method = RequestMethod.POST)
	@ResponseBody
	public Info insertFinalPayRollAndDeleteFroTemp(@RequestBody List<EmpSalInfoDaiyInfoTempInfo> salList) {

		Info info = new Info();

		try {

			List<Integer> empIds = new ArrayList<>();
			List<Integer> detailIds = new ArrayList<>();
			int month = salList.get(0).getMonth();
			int year = salList.get(0).getYear();
			// System.out.println(salList);
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < salList.size(); i++) {

				empIds.add(salList.get(i).getEmpId());

				SalaryCalc SalaryCalc = new SalaryCalc();
				SalaryCalc.setEmpId(salList.get(i).getEmpId());
				SalaryCalc.setEmpCode(salList.get(i).getEmpCode());
				SalaryCalc.setEmpType(salList.get(i).getEmpType());
				SalaryCalc.setContractorId(salList.get(i).getContractorId());
				SalaryCalc.setDepartId(salList.get(i).getDepartId());
				SalaryCalc.setDesignationId(salList.get(i).getDesignationId());
				SalaryCalc.setLocationId(salList.get(i).getLocationId());
				SalaryCalc.setCalcMonth(salList.get(i).getMonth());
				SalaryCalc.setCalcYear(salList.get(i).getYear());
				SalaryCalc.setAttSumId(salList.get(i).getAttSumId());
				SalaryCalc.setSalTypeId(salList.get(i).getSalaryTypeId());
				SalaryCalc.setBasicCal(salList.get(i).getBasicCal());
				SalaryCalc.setPerformanceBonus(salList.get(i).getPerformanceBonus());
				SalaryCalc.setOtWages(salList.get(i).getOtWages());
				SalaryCalc.setMiscExpAdd(salList.get(i).getMiscExpAdd());
				SalaryCalc.setBonusCal(salList.get(i).getBonusCal());
				SalaryCalc.setExgretiaCal(salList.get(i).getExgretiaCal());
				SalaryCalc.setDaArreasCal(salList.get(i).getDaArreasCal());
				SalaryCalc.setIncrementArreasCal(salList.get(i).getIncrementArreasCal());
				SalaryCalc.setEpfWages(salList.get(i).getEpfWages());
				SalaryCalc.setEpfWagesEmployer(salList.get(i).getEpfWagesEmployer());
				SalaryCalc.setEsicWagesCal(salList.get(i).getEsicWagesCal());
				SalaryCalc.setGrossSalary(salList.get(i).getGrossSalaryDytemp());
				SalaryCalc.setEpsWages(salList.get(i).getEpsWages());
				SalaryCalc.setEsicWagesDec(salList.get(i).getEsicWagesDec());
				SalaryCalc.setEmployeePf(salList.get(i).getEmployeePf());
				SalaryCalc.setEmployerPf(salList.get(i).getEmployerPf());
				SalaryCalc.setEmployerEps(salList.get(i).getEmployerEps());
				SalaryCalc.setEsic(salList.get(i).getEsic());
				SalaryCalc.setEmployerEsic(salList.get(i).getEmployerEsic());
				SalaryCalc.setEsicStatus(salList.get(i).getEsicStatus());
				SalaryCalc.setPfStatus(salList.get(i).getPfStatus());
				SalaryCalc.setMlwf(salList.get(i).getMlwf());
				SalaryCalc.setTds(salList.get(i).getTds());
				SalaryCalc.setItded(salList.get(i).getItded());
				SalaryCalc.setFund(salList.get(i).getFund());
				SalaryCalc.setTotPfAdminCh(salList.get(i).getTotPfAdminCh());
				SalaryCalc.setTotEdliCh(salList.get(i).getTotEdliCh());
				SalaryCalc.setTotEdliAdminCh(salList.get(i).getTotEdliAdminCh());
				SalaryCalc.setNcpDays(salList.get(i).getNcpDaysDytemp());
				SalaryCalc.setStatus(salList.get(i).getStatusDytemp());
				SalaryCalc.setPtDed(salList.get(i).getPtDed());
				SalaryCalc.setAdvanceDed(salList.get(i).getAdvanceDed());
				SalaryCalc.setLoanDed(salList.get(i).getLoanDed());
				SalaryCalc.setMiscExpDed(salList.get(i).getMiscExpDed());
				SalaryCalc.setMiscExpDedDeduct(salList.get(i).getMiscExpDedDeduct());
				SalaryCalc.setNetSalary(salList.get(i).getNetSalary());
				SalaryCalc.setIsLocked(salList.get(i).getIsLocked());
				SalaryCalc.setLoginName(salList.get(i).getLoginName());
				SalaryCalc.setLoginTime(sf.format(date));
				SalaryCalc.setMlwfApplicable(salList.get(i).getMlwfApplicable());
				SalaryCalc.setPtApplicable(salList.get(i).getPtApplicable());
				SalaryCalc.setPayDed(salList.get(i).getPayDed());
				SalaryCalc.setCommentsForItBonus(salList.get(i).getCommentsForItBonus());
				SalaryCalc.setSocietyContribution(salList.get(i).getSocietyContributionDytemp());
				SalaryCalc.setEmpCategory(salList.get(i).getSalBasis());
				SalaryCalc.setBasicDefault(salList.get(i).getBasicDefault());
				SalaryCalc.setCmpId(salList.get(i).getCmpId());
				SalaryCalc.setAbDeduction(salList.get(i).getAbDeduction());
				SalaryCalc.setProductionInsentive(salList.get(i).getProductionInsentive());
				SalaryCalc.setPresentInsentive(salList.get(i).getPresentInsentive());
				SalaryCalc.setNightAllow(salList.get(i).getNightAllow());
				SalaryCalc.setEpfPercentage(salList.get(i).getEpfPercentage());
				SalaryCalc.setEpsEmployeePercentage(salList.get(i).getEpsEmployeePercentage());
				SalaryCalc.setEpfEmployerPercentage(salList.get(i).getEpfEmployerPercentage());
				SalaryCalc.setEpsEmployerPercentage(salList.get(i).getEpsEmployerPercentage());
				SalaryCalc.setEpsDefault(salList.get(i).getEpsDefault());
				SalaryCalc.setEpmloyerEpfDefault(salList.get(i).getEpmloyerEpfDefault());
				SalaryCalc.setEpmloyerEpfExtra(salList.get(i).getEpmloyerEpfExtra());
				SalaryCalc.setPfAdminChPercentage(salList.get(i).getPfAdminChPercentage());
				SalaryCalc.setEdliPercentage(salList.get(i).getEdliPercentage());
				SalaryCalc.setEdliAdminPercentage(salList.get(i).getEdliAdminPercentage());
				SalaryCalc.setEmployeeEsicPercentage(salList.get(i).getEmployeeEsicPercentage());
				SalaryCalc.setEmployerEsicPercentage(salList.get(i).getEmployerEsicPercentage());
				SalaryCalc.setEmployerMlwf(salList.get(i).getEmployerMlwf());
				SalaryCalc.setGrossSalDefault(salList.get(i).getGrossSalary());
				SalaryCalc.setAdjustMinus(salList.get(i).getAdjustMinus());
				SalaryCalc.setAdjustPlus(salList.get(i).getAdjustPlus());
				SalaryCalc.setReward(salList.get(i).getReward());
				SalaryCalc.setNightRate(salList.get(i).getNightRate());
				SalaryCalc.setOtRate(salList.get(i).getOtRate());
				SalaryCalc.setBhatta(salList.get(i).getBhatta());
				SalaryCalc.setOther1(salList.get(i).getOther1());
				SalaryCalc saveres = salaryCalcRepo.save(SalaryCalc);

				List<SalAllownceCal> allowlist = new ArrayList<>();

				for (int j = 0; j < salList.get(i).getGetAllowanceTempList().size(); j++) {

					SalAllownceCal empAllowance = new SalAllownceCal();
					empAllowance.setSalaryCalcId(saveres.getId());
					empAllowance.setAllowanceId(salList.get(i).getGetAllowanceTempList().get(j).getAllowanceId());
					empAllowance.setAllowanceValue(salList.get(i).getGetAllowanceTempList().get(j).getAllowanceValue());
					empAllowance.setEmpId(salList.get(i).getGetAllowanceTempList().get(j).getEmpId());
					empAllowance.setAllowanceValueCal(
							salList.get(i).getGetAllowanceTempList().get(j).getAllowanceValueCal());
					empAllowance.setShortName(salList.get(i).getGetAllowanceTempList().get(j).getShortName());
					empAllowance.setDelStatus(1);
					detailIds.add(salList.get(i).getGetAllowanceTempList().get(j).getEmpSalAllowanceId());
					allowlist.add(empAllowance);

				}

				List<SalAllownceCal> saveAllores = salAllownceCalRepo.saveAll(allowlist);
			}

			int deleteFromTemp = salaryCalcTempRepo.deleteFromTemp(month, year, empIds);
			int deleteFromTempAll = salAllownceTempRepo.deleteFromTempAll(detailIds);

			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/updateIsPaidInPaydeClaimAdvLoan" }, method = RequestMethod.POST)
	@ResponseBody
	public Info updateIsPaidInPaydeClaimAdvLoan(@RequestParam("month") int month, @RequestParam("year") int year,
			@RequestParam("userId") int userId, @RequestParam("empIds") List<Integer> empIds) {

		Info info = new Info();

		try {

			List<Setting> settingList = settingRepo.findByGroup("PAYROLLHIDESHOW");

			int payroll_claim_show = 0;
			int payroll_advance_show = 0;
			int payroll_loan_show = 0;
			int payroll_payded_show = 0;
			int payroll_reward_show = 0;

			for (int k = 0; k < settingList.size(); k++) {
				if (settingList.get(k).getKey().equalsIgnoreCase("payroll_claim_show")) {
					payroll_claim_show = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("payroll_advance_show")) {
					payroll_advance_show = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("payroll_loan_show")) {
					payroll_loan_show = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("payroll_payded_show")) {
					payroll_payded_show = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("payroll_reward_show")) {
					payroll_reward_show = Integer.parseInt(settingList.get(k).getValue());
				}
			}

			if (payroll_advance_show == 1) {
				int updateAdv = advanceRepo.updateAdv(month, year, empIds);
			}

			if (payroll_claim_show == 1) {
				int updateClaim = claimHeaderRepo.updateClaim(month, year, empIds);
			}

			if (payroll_payded_show == 1) {
				int updatePayde = payDeductionDetailsRepo.updatePayde(month, year, empIds);
			}

			if (payroll_reward_show == 1) {
				int updateBonus = payBonusDetailsRepo.updateBonus(month, year, empIds);
			}

			// List<GetPayDedList> getLoanList = getPayDedListRepo.getLoanList(year + "-" +
			// month + "-01", empIds);

			if (payroll_loan_show == 1) {

				List<LoanMain> getLoanList = loanMainRepo.getLoanList(year + "-" + month + "-01", empIds);
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				List<LoanDetails> loandetaillist = new ArrayList<>();
				for (int i = 0; i < getLoanList.size(); i++) {
					LoanDetails loanDetails = new LoanDetails();
					loanDetails.setLoanMainId(getLoanList.get(i).getId());
					loanDetails.setMonths(month);
					loanDetails.setYears(year);
					loanDetails.setPayType("SP");
					loanDetails.setLoginName(String.valueOf(userId));
					loanDetails.setLoginTime(sf.format(date));
					loanDetails.setDelStatus(1);
					int amt = 0;

					if (getLoanList.get(i).getCurrentOutstanding() < getLoanList.get(i).getLoanEmiIntrest()) {
						amt = getLoanList.get(i).getCurrentOutstanding();
					} else {
						amt = getLoanList.get(i).getLoanEmiIntrest();
					}

					if (getLoanList.get(i).getSkipId() == 0) {
						loanDetails.setAmountEmi(amt);
						loanDetails.setRemarks("Auto Deducted :Salary Deduction");
						getLoanList.get(i).setCurrentOutstanding(getLoanList.get(i).getCurrentOutstanding() - amt);
						getLoanList.get(i).setCurrentTotpaid(getLoanList.get(i).getCurrentTotpaid() + amt);
					} else {
						getLoanList.get(i).setSkipId(0);
						loanDetails.setAmountEmi(0);
						loanDetails.setSkippAmoount(amt);
						loanDetails.setSkippMonthYear(month + "-" + year);
					}

					if (getLoanList.get(i).getCurrentOutstanding() == 0) {
						getLoanList.get(i).setLoanStatus("Paid");
					}
					loandetaillist.add(loanDetails);
				}

				if (loandetaillist.size() > 0) {
					List<LoanDetails> res = loanDetailsRepo.saveAll(loandetaillist);
					List<LoanMain> updateRes = loanMainRepo.saveAll(getLoanList);
				}
			}
			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/sendMailTest" }, method = RequestMethod.GET)
	@ResponseBody
	public Info sendMailTest() {

		Info info = new Info();

		try {
			EmailUtility.sendEmail("task.management@kppmca.com", "De@8380077223", "akshaykasar72@gmail.com",
					"Test Mail", "", "Mail Testing");
			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/getPayrollGenratedList" }, method = RequestMethod.POST)
	@ResponseBody
	public PayRollDataForProcessing getPayrollGenratedList(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("companyId") int companyId,
			@RequestParam("locId") List<Integer> locId) {

		PayRollDataForProcessing payRollDataForProcessing = new PayRollDataForProcessing();

		try {

			List<GetPayrollGeneratedList> list = new ArrayList<>();

			if (companyId == 0) {
				list = getPayrollGeneratedListRepo.getPayrollGenratedListLocId(month, year, locId);
			} else {
				list = getPayrollGeneratedListRepo.getPayrollGenratedListLocId(month, year, companyId, locId);
			}

			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<SalAllownceCal> getPayrollAllownceList = salAllownceCalRepo.getPayrollAllownceListlocId(month, year,
					locId);

			for (int i = 0; i < list.size(); i++) {

				List<SalAllownceCal> assignAllownceList = new ArrayList<>();

				for (int k = 0; k < getPayrollAllownceList.size(); k++) {

					if (list.get(i).getId() == getPayrollAllownceList.get(k).getSalaryCalcId()) {
						assignAllownceList.add(getPayrollAllownceList.get(k));

					}
				}

				list.get(i).setPayrollAllownceList(assignAllownceList);
			}

			payRollDataForProcessing.setPayrollGeneratedList(list);
			payRollDataForProcessing.setAllowancelist(allowancelist);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payRollDataForProcessing;
	}

	@RequestMapping(value = { "/getPayOneLineReport" }, method = RequestMethod.POST)
	@ResponseBody
	public PayRollDataForProcessing getPayOneLineReport(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("locId") List<Integer> locId) {

		PayRollDataForProcessing payRollDataForProcessing = new PayRollDataForProcessing();

		try {

			List<GetOnelineReport> onelinereportlist = new ArrayList<>();

			onelinereportlist = getOnelineReportRepo.getPayOneLineReport(month, year, locId);

			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<SalAllownceCal> getPayrollAllownceList = salAllownceCalRepo.getPayrollAllownceListlocId(month, year,
					locId);

			for (int i = 0; i < onelinereportlist.size(); i++) {

				List<SalAllownceCal> assignAllownceList = new ArrayList<>();

				for (int k = 0; k < getPayrollAllownceList.size(); k++) {

					if (onelinereportlist.get(i).getId() == getPayrollAllownceList.get(k).getSalaryCalcId()) {
						assignAllownceList.add(getPayrollAllownceList.get(k));

					}
				}

				onelinereportlist.get(i).setPayrollAllownceList(assignAllownceList);
			}

			payRollDataForProcessing.setOnelinereportlist(onelinereportlist);
			payRollDataForProcessing.setAllowancelist(allowancelist);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payRollDataForProcessing;
	}

	@RequestMapping(value = { "/departmentwisePayrollReport" }, method = RequestMethod.POST)
	@ResponseBody
	public PayRollDataForProcessing departmentwisePayrollReport(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("locId") List<Integer> locId) {

		PayRollDataForProcessing payRollDataForProcessing = new PayRollDataForProcessing();

		try {

			List<GetDeptPayReport> onelinereportlist = new ArrayList<>();

			onelinereportlist = getDeptPayReportRepo.departmentwisePayrollReport(month, year, locId);

			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<SalAllownceCal> getPayrollAllownceList = salAllownceCalRepo.getPayrollAllownceListlocIdDept(month,
					year, locId);

			for (int i = 0; i < onelinereportlist.size(); i++) {

				List<SalAllownceCal> assignAllownceList = new ArrayList<>();

				for (int k = 0; k < getPayrollAllownceList.size(); k++) {

					if (onelinereportlist.get(i).getDepartId() == getPayrollAllownceList.get(k).getEmpId()) {
						assignAllownceList.add(getPayrollAllownceList.get(k));

					}
				}

				onelinereportlist.get(i).setPayrollAllownceList(assignAllownceList);
			}

			payRollDataForProcessing.setDeptreportlist(onelinereportlist);
			payRollDataForProcessing.setAllowancelist(allowancelist);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payRollDataForProcessing;
	}

	@RequestMapping(value = { "/getPayrollGenratedListByAuthority" }, method = RequestMethod.POST)
	@ResponseBody
	public PayRollDataForProcessing getPayrollGenratedListByAuthority(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("empId") int empId) {

		PayRollDataForProcessing payRollDataForProcessing = new PayRollDataForProcessing();

		try {

			List<GetPayrollGeneratedList> list = new ArrayList<>();

			list = getPayrollGeneratedListRepo.getPayrollGenratedListByAuthority(month, year, empId);

			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<SalAllownceCal> getPayrollAllownceList = salAllownceCalRepo.getPayrollAllownceList(month, year);

			for (int i = 0; i < list.size(); i++) {

				List<SalAllownceCal> assignAllownceList = new ArrayList<>();

				for (int k = 0; k < getPayrollAllownceList.size(); k++) {

					if (list.get(i).getId() == getPayrollAllownceList.get(k).getSalaryCalcId()) {
						assignAllownceList.add(getPayrollAllownceList.get(k));

					}
				}

				list.get(i).setPayrollAllownceList(assignAllownceList);
			}

			payRollDataForProcessing.setPayrollGeneratedList(list);
			payRollDataForProcessing.setAllowancelist(allowancelist);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payRollDataForProcessing;
	}

	@RequestMapping(value = { "/getPayrollGenratedListByEmpIds" }, method = RequestMethod.POST)
	@ResponseBody
	public PayRollDataForProcessing getPayrollGenratedListByEmpIds(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("empIds") List<Integer> empIds) {

		PayRollDataForProcessing payRollDataForProcessing = new PayRollDataForProcessing();

		try {

			List<GetPayrollGeneratedList> list = getPayrollGeneratedListRepo.getPayrollGenratedList(month, year,
					empIds);
			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<SalAllownceCal> getPayrollAllownceList = salAllownceCalRepo.getPayrollAllownceList(month, year,
					empIds);
			Setting setting = settingRepo.findByKey("ammount_format_show");
			int amount_round = Integer.parseInt(setting.getValue());
			for (int i = 0; i < list.size(); i++) {

				List<SalAllownceCal> assignAllownceList = new ArrayList<>();

				for (int k = 0; k < getPayrollAllownceList.size(); k++) {

					if (list.get(i).getId() == getPayrollAllownceList.get(k).getSalaryCalcId()) {

						getPayrollAllownceList.get(k).setAllowanceValueCal(
								castNumber(getPayrollAllownceList.get(k).getAllowanceValueCal(), amount_round));
						assignAllownceList.add(getPayrollAllownceList.get(k));

					}
				}
				list.get(i).setBasicCal(castNumber(list.get(i).getBasicCal(), amount_round));
				list.get(i).setOtWages(castNumber(list.get(i).getOtWages(), amount_round));
				list.get(i).setProductionInsentive(castNumber(list.get(i).getProductionInsentive(), amount_round));
				list.get(i).setReward(castNumber(list.get(i).getReward(), amount_round));
				list.get(i).setNightAllow(castNumber(list.get(i).getNightAllow(), amount_round));
				list.get(i).setPerformanceBonus(castNumber(list.get(i).getPerformanceBonus(), amount_round));
				list.get(i).setMiscExpAdd(castNumber(list.get(i).getMiscExpAdd(), amount_round));
				list.get(i).setPtDed(castNumber(list.get(i).getPtDed(), amount_round));
				list.get(i).setEmployeePf((float) castNumber(list.get(i).getEmployeePf(), amount_round));
				list.get(i).setEsic((float) castNumber(list.get(i).getEsic(), amount_round));
				list.get(i).setAdvanceDed(castNumber(list.get(i).getAdvanceDed(), amount_round));
				list.get(i).setTds(castNumber(list.get(i).getTds(), amount_round));
				list.get(i).setSocietyContribution(castNumber(list.get(i).getSocietyContribution(), amount_round));
				list.get(i).setLoanDed(castNumber(list.get(i).getLoanDed(), amount_round));
				list.get(i).setMlwf(castNumber(list.get(i).getMlwf(), amount_round));
				list.get(i).setPayDed(castNumber(list.get(i).getPayDed(), amount_round));
				list.get(i).setItded(castNumber(list.get(i).getItded(), amount_round));
				list.get(i).setBhatta(castNumber(list.get(i).getBhatta(), amount_round));
				list.get(i).setNetSalary(castNumber(list.get(i).getNetSalary(), amount_round));

				long sal = (long) list.get(i).getNetSalary();
				list.get(i).setMoneyInword(EnglishNumberToWords.convert(sal));
				list.get(i).setPayrollAllownceList(assignAllownceList);
			}

			payRollDataForProcessing.setPayrollGeneratedList(list);
			payRollDataForProcessing.setAllowancelist(allowancelist);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payRollDataForProcessing;
	}

	@RequestMapping(value = { "/getEmpDetailForPayRoll" }, method = RequestMethod.POST)
	@ResponseBody
	public List<GetEmpDetail> getEmpDetailForPayRoll(@RequestParam("empIds") List<Integer> empIds) {

		List<GetEmpDetail> list = new ArrayList<>();

		try {

			list = getEmpDetailRepo.getPayrollGenratedList(empIds);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/updateIsPaidInPaydeClaimAdvLoanInFullFinal" }, method = RequestMethod.POST)
	@ResponseBody
	public Info updateIsPaidInPaydeClaimAdvLoanInFullFinal(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("userId") int userId, @RequestParam("empIds") int empIds) {

		Info info = new Info();

		try {

			int updateAdv = advanceRepo.updateAdv(empIds);
			List<LoanMain> getLoanList = loanMainRepo.getLoanList(empIds);
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			List<LoanDetails> loandetaillist = new ArrayList<>();
			for (int i = 0; i < getLoanList.size(); i++) {
				LoanDetails loanDetails = new LoanDetails();
				loanDetails.setLoanMainId(getLoanList.get(i).getId());
				loanDetails.setMonths(month);
				loanDetails.setYears(year);
				loanDetails.setPayType("SP");
				loanDetails.setLoginName(String.valueOf(userId));
				loanDetails.setLoginTime(sf.format(date));

				int amt = 0;

				amt = getLoanList.get(i).getCurrentOutstanding();

				loanDetails.setAmountEmi(amt);
				loanDetails.setRemarks("Auto Deducted :Salary Deduction");
				getLoanList.get(i).setCurrentOutstanding(getLoanList.get(i).getCurrentOutstanding() - amt);
				getLoanList.get(i).setCurrentTotpaid(getLoanList.get(i).getCurrentTotpaid() + amt);
				getLoanList.get(i).setLoanStatus("Paid");

				loandetaillist.add(loanDetails);
			}

			if (loandetaillist.size() > 0) {
				List<LoanDetails> res = loanDetailsRepo.saveAll(loandetaillist);
				List<LoanMain> updateRes = loanMainRepo.saveAll(getLoanList);
			}

			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/checkPayslipisgenerated" }, method = RequestMethod.POST)
	@ResponseBody
	public Info checkPayslipisgenerated(@RequestParam("month") int month, @RequestParam("year") int year,
			@RequestParam("empId") int empId) {

		Info info = new Info();

		try {
			List<Integer> empIds = new ArrayList<>();
			empIds.add(empId);

			List<GetPayrollGeneratedList> list = getPayrollGeneratedListRepo.getPayrollGenratedList(month, year,
					empIds);

			if (list.size() > 0) {
				info.setError(false);
				info.setMsg("Payslip is generated");
			} else {
				info.setError(true);
				info.setMsg("Payslip is not generated of selected month");
			}
		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Payslip is not generated of selected month");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/getSalaryDetailByEmpIds" }, method = RequestMethod.POST)
	@ResponseBody
	public PayRollDataForProcessing getSalaryDetailByEmpIds(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("empIds") List<Integer> empIds) {

		PayRollDataForProcessing payRollDataForProcessing = new PayRollDataForProcessing();

		try {

			List<Integer> wootsts = new ArrayList<>();
			wootsts.add(13);
			wootsts.add(14);

			List<Integer> absts = new ArrayList<>();
			absts.add(22);

			List<Setting> attendance = settingRepo.findByGroup("Attendance");
			List<Setting> payrollhideshow = settingRepo.findByGroup("PAYROLLHIDESHOW");

			int payroll_claim_show = 0;
			int payroll_advance_show = 0;
			int payroll_loan_show = 0;
			int payroll_payded_show = 0;
			int payroll_reward_show = 0;
			int max_late_day_allowed = 0;
			int payroll_bhatta_show = 0;

			for (int k = 0; k < payrollhideshow.size(); k++) {
				if (payrollhideshow.get(k).getKey().equalsIgnoreCase("payroll_claim_show")) {
					payroll_claim_show = Integer.parseInt(payrollhideshow.get(k).getValue());
				} else if (payrollhideshow.get(k).getKey().equalsIgnoreCase("payroll_advance_show")) {
					payroll_advance_show = Integer.parseInt(payrollhideshow.get(k).getValue());
				} else if (payrollhideshow.get(k).getKey().equalsIgnoreCase("payroll_loan_show")) {
					payroll_loan_show = Integer.parseInt(payrollhideshow.get(k).getValue());
				} else if (payrollhideshow.get(k).getKey().equalsIgnoreCase("payroll_payded_show")) {
					payroll_payded_show = Integer.parseInt(payrollhideshow.get(k).getValue());
				} else if (payrollhideshow.get(k).getKey().equalsIgnoreCase("payroll_reward_show")) {
					payroll_reward_show = Integer.parseInt(payrollhideshow.get(k).getValue());
				} else if (payrollhideshow.get(k).getKey().equalsIgnoreCase("payroll_bhatta_show")) {
					payroll_bhatta_show = Integer.parseInt(payrollhideshow.get(k).getValue());
				}
			}

			for (int k = 0; k < attendance.size(); k++) {
				if (attendance.get(k).getKey().equalsIgnoreCase("max_late_day_allowed")) {
					max_late_day_allowed = Integer.parseInt(attendance.get(k).getValue());
					break;
				}
			}

			List<GetAdvanceDetails> getLateMarkAdvanceList = new ArrayList<>();
			List<GetAdvanceDetails> getAdvanceList = new ArrayList<>();
			List<GetAdvanceDetails> getClaimList = new ArrayList<>();
			List<GetAdvanceDetails> getBhattaList = new ArrayList<>();
			List<GetAdvanceDetails> getPayDedList = new ArrayList<>();
			List<GetAdvanceDetails> getRewardList = new ArrayList<>();
			List<GetAdvanceDetails> getLoanList = new ArrayList<>();

			getLateMarkAdvanceList = getAdvanceDetailsRepo.getLateMarkAdvanceListSaparate(month, year, empIds);

			if (payroll_advance_show == 1) {
				getAdvanceList = getAdvanceDetailsRepo.getAdvanceListSaparate(month, year, empIds);
			}

			if (payroll_claim_show == 1) {
				getClaimList = getAdvanceDetailsRepo.getClaimListSaparate(month, year, empIds);
			}
			if (payroll_payded_show == 1) {
				getPayDedList = getAdvanceDetailsRepo.getPayDedListSaparate(month, year, empIds);
			}

			if (payroll_reward_show == 1) {
				getRewardList = getAdvanceDetailsRepo.getBonusListSaparate(month, year, empIds);
			}

			if (payroll_bhatta_show == 1) {
				try {
					getBhattaList = getAdvanceDetailsRepo.getBhattaListSaparate(month, year, empIds);
				} catch (Exception e) {
					getBhattaList = new ArrayList<>();
				}

			}

			if (payroll_loan_show == 1) {
				getLoanList = getAdvanceDetailsRepo.getLoanListSaparate(month, year, empIds);
			}

			List<ProductionIncentiveList> performanceIncentiveList = productionIncentiveListRepo
					.getPerformanceIncentiveList(month, year, empIds, wootsts);

			List<ProductionIncentiveList> productionIncentiveList = productionIncentiveListRepo
					.getproductionIncentiveList(month, year, empIds);

			List<ProductionIncentiveList> getAbDedList = productionIncentiveListRepo.getPerformanceIncentiveList(month,
					year, empIds, absts);

			List<ProductionIncentiveList> getLateMarkDedList = productionIncentiveListRepo
					.getLateMarkDedListSaparate(month, year, empIds);

			List<GetPayrollGeneratedList> list = getPayrollGeneratedListRepo.getPayrollGenratedList(month, year,
					empIds);

			Setting setting = settingRepo.findByKey("ammount_format_show");
			int amount_round = Integer.parseInt(setting.getValue());

			for (int i = 0; i < list.size(); i++) {

				List<GetAdvanceDetails> advance = new ArrayList<>();
				List<GetAdvanceDetails> calim = new ArrayList<>();
				List<GetAdvanceDetails> payded = new ArrayList<>();
				List<GetAdvanceDetails> reward = new ArrayList<>();
				List<GetAdvanceDetails> loan = new ArrayList<>();
				List<GetAdvanceDetails> bhatta = new ArrayList<>();

				List<ProductionIncentiveList> latemark = new ArrayList<>();
				List<ProductionIncentiveList> absent = new ArrayList<>();
				List<ProductionIncentiveList> performance = new ArrayList<>();
				List<ProductionIncentiveList> production = new ArrayList<>();

				for (int j = 0; j < getAdvanceList.size(); j++) {

					if (getAdvanceList.get(j).getEmpId() == list.get(i).getEmpId()) {
						advance.add(getAdvanceList.get(j));
					}

				}

				for (int j = 0; j < getClaimList.size(); j++) {

					if (getClaimList.get(j).getEmpId() == list.get(i).getEmpId()) {
						calim.add(getClaimList.get(j));
					}

				}

				for (int j = 0; j < getBhattaList.size(); j++) {

					if (getBhattaList.get(j).getEmpId() == list.get(i).getEmpId()) {
						bhatta.add(getBhattaList.get(j));
					}

				}

				for (int j = 0; j < getPayDedList.size(); j++) {

					if (getPayDedList.get(j).getEmpId() == list.get(i).getEmpId()) {
						payded.add(getPayDedList.get(j));
					}

				}

				for (int j = 0; j < getRewardList.size(); j++) {

					if (getRewardList.get(j).getEmpId() == list.get(i).getEmpId()) {
						reward.add(getRewardList.get(j));
					}

				}

				for (int j = 0; j < getLoanList.size(); j++) {

					if (getLoanList.get(j).getEmpId() == list.get(i).getEmpId()) {
						loan.add(getLoanList.get(j));
					}

				}

				GetAdvanceDetails advancelatemarkdeductemprecord = new GetAdvanceDetails();

				for (int j = 0; j < getLateMarkAdvanceList.size(); j++) {

					if (getLateMarkAdvanceList.get(j).getEmpId() == list.get(i).getEmpId()) {
						advancelatemarkdeductemprecord = getLateMarkAdvanceList.get(j);
						break;
					}

				}

				for (int j = 0; j < getLateMarkDedList.size(); j++) {

					if (getLateMarkDedList.get(j).getEmpId() == list.get(i).getEmpId()) {

						latemark.add(getLateMarkDedList.get(j));
					}

				}

				double latemarkamt = advancelatemarkdeductemprecord.getAmt()
						/ ((latemark.size() - max_late_day_allowed));

				for (int j = 0; j < getAbDedList.size(); j++) {

					if (getAbDedList.get(j).getEmpId() == list.get(i).getEmpId()) {
						absent.add(getAbDedList.get(j));
					}

				}
				for (int j = 0; j < performanceIncentiveList.size(); j++) {

					if (performanceIncentiveList.get(j).getEmpId() == list.get(i).getEmpId()) {
						performance.add(performanceIncentiveList.get(j));
					}

				}
				for (int j = 0; j < productionIncentiveList.size(); j++) {

					if (productionIncentiveList.get(j).getEmpId() == list.get(i).getEmpId()) {

						double amt = (list.get(i).getOtWages() / productionIncentiveList.get(j).getTotOthr())
								* productionIncentiveList.get(j).getHrs();
						productionIncentiveList.get(j).setAmt(castNumber(amt, amount_round));
						production.add(productionIncentiveList.get(j));
					}

				}

				list.get(i).setBasicCal(castNumber(list.get(i).getBasicCal(), amount_round));
				list.get(i).setOtWages(castNumber(list.get(i).getOtWages(), amount_round));
				list.get(i).setProductionInsentive(
						castNumber((list.get(i).getProductionInsentive() / performance.size()), amount_round));
				list.get(i).setReward(castNumber(list.get(i).getReward(), amount_round));
				list.get(i).setNightAllow(castNumber(list.get(i).getNightAllow(), amount_round));
				list.get(i).setPerformanceBonus(castNumber(list.get(i).getPerformanceBonus(), amount_round));
				list.get(i).setMiscExpAdd(castNumber(list.get(i).getMiscExpAdd(), amount_round));
				list.get(i).setPtDed(castNumber(list.get(i).getPtDed(), amount_round));
				list.get(i).setEmployeePf((float) castNumber(list.get(i).getEmployeePf(), amount_round));
				list.get(i).setEsic((float) castNumber(list.get(i).getEsic(), amount_round));
				list.get(i).setAdvanceDed(castNumber(list.get(i).getAdvanceDed(), amount_round));
				list.get(i).setTds(castNumber(list.get(i).getTds(), amount_round));
				list.get(i).setSocietyContribution(castNumber(list.get(i).getSocietyContribution(), amount_round));
				list.get(i).setLoanDed(castNumber(list.get(i).getLoanDed(), amount_round));
				list.get(i).setMlwf(castNumber(list.get(i).getMlwf(), amount_round));
				list.get(i).setPayDed(castNumber(list.get(i).getPayDed(), amount_round));
				list.get(i).setItded(castNumber(list.get(i).getItded(), amount_round));
				list.get(i).setAbDeduction(castNumber((list.get(i).getAbDeduction() / absent.size()), amount_round));
				list.get(i).setNetSalary(castNumber(list.get(i).getNetSalary(), amount_round));
				list.get(i).setAdjustPlus(castNumber(latemarkamt, amount_round));

				long sal = (long) list.get(i).getNetSalary();
				list.get(i).setMoneyInword(EnglishNumberToWords.convert(sal));

				list.get(i).setGetAdvanceList(advance);
				list.get(i).setGetClaimList(calim);
				list.get(i).setGetPayDedList(payded);
				list.get(i).setGetRewardList(reward);
				list.get(i).setGetLoanList(loan);
				list.get(i).setGetAbsentDedList(absent);
				list.get(i).setGetLateMarkDedList(latemark);
				list.get(i).setProduction(production);
				list.get(i).setPerformance(performance);
				list.get(i).setGetBhattaList(bhatta);

			}

			payRollDataForProcessing.setPayrollGeneratedList(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payRollDataForProcessing;
	}

	@RequestMapping(value = { "/getEmpDetailForFullSalarySlip" }, method = RequestMethod.POST)
	@ResponseBody
	public List<GetEmpDetailForFullPayslip> getEmpDetailForFullSalarySlip(
			@RequestParam("empIds") List<Integer> empIds) {

		List<GetEmpDetailForFullPayslip> list = new ArrayList<>();

		try {

			list = getEmpDetailForFullPayslipRepo.getEmpDetailForFullSalarySlip(empIds);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
