package com.ats.hrmgt.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.AdvanceAndLoanInfo; 
import com.ats.hrmgt.model.GetAdvanceList; 
import com.ats.hrmgt.model.GetPayDedList; 
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

	@RequestMapping(value = { "/getAllAmountDeductionSectionListForFullnFinal" }, method = RequestMethod.POST)
	public AdvanceAndLoanInfo getAllAmountDeductionSectionListForFullnFinal(
			@RequestParam("empId") List<Integer> empIds) {

		AdvanceAndLoanInfo info = new AdvanceAndLoanInfo();

		try {

			GetAdvanceList getAdvanceList = getAdvanceListRepo.getAdvanceListForFullFinal(empIds);
			GetPayDedList getLoanList = getPayDedListRepo.getLoanListForFullFinal(empIds);

			if(getAdvanceList!=null) {
				info.setAdvanceAmt(getAdvanceList.getAdvAmount());
			}else {
				info.setAdvanceAmt(0);
			}
			
			if(getLoanList!=null) {
				info.setLoanAmt(getLoanList.getAmt());
			}else {
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

}
