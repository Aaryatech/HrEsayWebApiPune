package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ats.hrmgt.model.Allowances;
import com.ats.hrmgt.model.EmpSalAllowance;
import com.ats.hrmgt.model.EmpSalaryInfoForPayroll;
import com.ats.hrmgt.model.GetAdvanceList;
import com.ats.hrmgt.model.GetClaimList;
import com.ats.hrmgt.model.GetPayDedList;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.SalAllownceTemp;
import com.ats.hrmgt.model.SalaryCalcTemp;
import com.ats.hrmgt.repository.GetAdvanceListRepo;
import com.ats.hrmgt.repository.GetClaimListRepo;
import com.ats.hrmgt.repository.GetPayDedListRepo;

public class FullAndFinalApiController {

	@Autowired
	GetAdvanceListRepo getAdvanceListRepo;

	@Autowired
	GetClaimListRepo getClaimListRepo;

	@Autowired
	GetPayDedListRepo getPayDedListRepo;

	@RequestMapping(value = { "/getAllAmountDeductionSectionListForFullnFinal" }, method = RequestMethod.POST)
	public Info getAllAmountDeductionSectionListForFullnFinal(@RequestParam("empIds") List<Integer> empIds) {

		Info info = new Info();

		try {

			/*List<GetAdvanceList> getAdvanceList = getAdvanceListRepo.getAdvanceList(empIds);
			List<GetClaimList> getClaimList = getClaimListRepo.getClaimList(empIds);
			List<GetPayDedList> getPayDedList = getPayDedListRepo.getPayDedList(empIds);
			List<GetPayDedList> getRewardList = getPayDedListRepo.getBonusList(empIds);
			List<GetPayDedList> getLoanList = getPayDedListRepo.getLoanList(empIds);*/
			  
		} catch (Exception e) {
			 
			e.printStackTrace();
		}

		return info;
	}

}
