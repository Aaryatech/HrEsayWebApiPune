package com.ats.hrmgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.AdvanceAndLoanInfo;
import com.ats.hrmgt.model.Designation;
import com.ats.hrmgt.model.FullAndFinal;
import com.ats.hrmgt.model.GetAdvanceList;
import com.ats.hrmgt.model.GetDetailForBonus;
import com.ats.hrmgt.model.GetPayDedList;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.repo.FullAndFinalRepo;
import com.ats.hrmgt.repo.GetDetailForBonusRepo;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.GetAdvanceListRepo;
import com.ats.hrmgt.repository.GetClaimListRepo;
import com.ats.hrmgt.repository.GetPayDedListRepo;

@RestController
public class FullAndFinalApiController {

	@Autowired
	GetAdvanceListRepo getAdvanceListRepo;

	@Autowired
	GetClaimListRepo getClaimListRepo;

	@Autowired
	GetPayDedListRepo getPayDedListRepo;

	@Autowired
	GetDetailForBonusRepo getDetailForBonusRepo;

	@Autowired
	FullAndFinalRepo fullAndFinalRepo;
	
	@Autowired
	EmpSalaryInfoRepo empSalRepo;


	@RequestMapping(value = { "/getAllAmountDeductionSectionListForFullnFinal" }, method = RequestMethod.POST)
	public AdvanceAndLoanInfo getAllAmountDeductionSectionListForFullnFinal(
			@RequestParam("empId") List<Integer> empIds) {

		AdvanceAndLoanInfo info = new AdvanceAndLoanInfo();

		try {

			GetAdvanceList getAdvanceList = getAdvanceListRepo.getAdvanceListForFullFinal(empIds);
			GetPayDedList getLoanList = getPayDedListRepo.getLoanListForFullFinal(empIds);

			if (getAdvanceList != null) {
				info.setAdvanceAmt(getAdvanceList.getAdvAmount());
			} else {
				info.setAdvanceAmt(0);
			}

			if (getLoanList != null) {
				info.setLoanAmt(getLoanList.getAmt());
			} else {
				info.setLoanAmt(0);
			}

			// List<GetClaimList> getClaimList = getClaimListRepo.getClaimList(empIds);
			// List<GetPayDedList> getPayDedList = getPayDedListRepo.getPayDedList(empIds);
			// List<GetPayDedList> getRewardList = getPayDedListRepo.getBonusList(empIds);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/getbonuscalDetails" }, method = RequestMethod.POST)
	public GetDetailForBonus getbonuscalDetails(@RequestParam("empId") int empId,
			@RequestParam("fromMonth") int fromMonth, @RequestParam("toMonth") int toMonth,
			@RequestParam("fromYear") int fromYear, @RequestParam("toYear") int toYear) {

		GetDetailForBonus info = new GetDetailForBonus();

		try {

			info = getDetailForBonusRepo.getbonuscalDetails(empId, fromMonth, toMonth, fromYear, toYear);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/insertfullandfinalrecord" }, method = RequestMethod.POST)
	public FullAndFinal insertfullandfinalrecord(@RequestBody FullAndFinal fullAndFinal) {

		FullAndFinal save = new FullAndFinal();

		try {

			save = fullAndFinalRepo.save(fullAndFinal);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;
	}

	@RequestMapping(value = { "/updateLeaveDatainemployee" }, method = RequestMethod.POST)
	public Info updateLeaveDatainemployee(@RequestParam int empId, @RequestParam String leaveDate,
			@RequestParam String leaveReason, @RequestParam String lrEsic, @RequestParam String lrForPF) {

		Info info = new Info();
		try {

			int res = empSalRepo.updateLeaveDatainemployee(empId,leaveDate,leaveReason,lrEsic,lrForPF);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

}
