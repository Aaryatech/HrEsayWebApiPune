package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetAdvanceDetails; 

public interface GetAdvanceDetailsRepo extends JpaRepository<GetAdvanceDetails, Integer>{

	
	 @Query(value = "select\n" + 
	 		"        uuid() as id,\n" + 
	 		"        emp_id,\n" + 
	 		"        round(adv_amount) as amt,\n" + 
	 		"        adv_date as date,\n" + 
	 		"        adv_remarks as remark\n" + 
	 		"    from\n" + 
	 		"        tbl_advance      \n" + 
	 		"    where\n" + 
	 		"        ded_month=:month        \n" + 
	 		"        and ded_year=:year         \n" + 
	 		"        and del_status=1          \n" + 
	 		"        and is_ded=1          \n" + 
	 		"        and emp_id in (:empIds) \n" + 
	 		"        and ex_int1=0", nativeQuery = true)
	List<GetAdvanceDetails> getAdvanceListSaparate(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

	 @Query(value = "select\n" + 
	 		"        uuid() as id,\n" + 
	 		"        emp_id,\n" + 
	 		"        round(claim_amount) as amt,\n" + 
	 		"        ca_from_dt as date,\n" + 
	 		"        claim_title as remark\n" + 
	 		"    from\n" + 
	 		"        claim_apply_header \n" + 
	 		"    where\n" + 
	 		"        month=:month \n" + 
	 		"        and year=:year \n" + 
	 		"        and claim_status=3 \n" + 
	 		"        and is_paid=1 \n" + 
	 		"        and emp_id in (:empIds)", nativeQuery = true)
	List<GetAdvanceDetails> getClaimListSaparate(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

	 @Query(value = "select\n" + 
	 		"        uuid() as id,\n" + 
	 		"        pd.emp_id,\n" + 
	 		"        round(pd.ded_rate)  as amt,\n" + 
	 		"        pd.ded_approval_datetime as date,\n" + 
	 		"        pt.type_name as remark\n" + 
	 		"    from\n" + 
	 		"        tblm_pay_deduction_details pd,\n" + 
	 		"        tbl_pay_deduction pt\n" + 
	 		"    where\n" + 
	 		"        pd.month=:month \n" + 
	 		"        and pd.year=:year \n" + 
	 		"        and pd.del_status=1 \n" + 
	 		"        and pd.is_deducted=1 \n" + 
	 		"        and pd.emp_id in (:empIds)\n" + 
	 		"        and pt.ded_type_id=pd.ded_type_id", nativeQuery = true)
	List<GetAdvanceDetails> getPayDedListSaparate(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

	 @Query(value = "select\n" + 
	 		"        uuid() as id,\n" + 
	 		"        rd.emp_id,\n" + 
	 		"        round(rd.pay_rate) as amt,\n" + 
	 		"        rd.pay_approval_datetime as date,\n" + 
	 		"        rt.type_name as remark\n" + 
	 		"    from\n" + 
	 		"        tblm_pay_bonus_details rd,\n" + 
	 		"        tbl_pay_bonus rt\n" + 
	 		"    where\n" + 
	 		"        rd.month=:month          \n" + 
	 		"        and rd.year=:year          \n" + 
	 		"        and rd.del_status=1          \n" + 
	 		"        and rd.is_paid=1          \n" + 
	 		"        and rd.emp_id in (:empIds)\n" + 
	 		"        and rt.pay_type_id=rd.pay_type_id", nativeQuery = true)
	List<GetAdvanceDetails> getBonusListSaparate(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

	 @Query(value = "select\n" + 
	 		"        uuid() as id,\n" + 
	 		"        lm.emp_id,\n" + 
	 		"        round(ld.amount_emi) as amt,\n" + 
	 		"        ld.login_time as date, \n" + 
	 		"        concat('Loan Date - ',DATE_FORMAT(lm.loan_repay_start, '%d-%m-%Y'),', Loan AMT -',lm.loan_amt,', Rem AMT - ',lm.current_outstanding) as remark\n" + 
	 		"    from\n" + 
	 		"        tbl_loan_main lm,\n" + 
	 		"        tbl_loan_details ld     \n" + 
	 		"    where\n" + 
	 		"        ld.months=:month         \n" + 
	 		"        and ld.years=:year         \n" + 
	 		"        and lm.del_status=1          \n" + 
	 		"        and lm.emp_id in (:empIds) \n" + 
	 		"        and ld.loan_main_id=lm.id         \n" + 
	 		"        and ld.pay_type='SP'", nativeQuery = true)
	List<GetAdvanceDetails> getLoanListSaparate(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

	 @Query(value = "select\n" + 
		 		"        uuid() as id,\n" + 
		 		"        emp_id,\n" + 
		 		"        adv_amount as amt,\n" + 
		 		"        adv_date as date,\n" + 
		 		"        adv_remarks as remark\n" + 
		 		"    from\n" + 
		 		"        tbl_advance      \n" + 
		 		"    where\n" + 
		 		"        ded_month=:month        \n" + 
		 		"        and ded_year=:year         \n" + 
		 		"        and del_status=1          \n" + 
		 		"        and is_ded=1          \n" + 
		 		"        and emp_id in (:empIds) \n" + 
		 		"        and ex_int1=1", nativeQuery = true) 
	List<GetAdvanceDetails> getLateMarkAdvanceListSaparate(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

}
