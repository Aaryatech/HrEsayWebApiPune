package com.ats.hrmgt.model.repo.dash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.dashboard.LoanAdvDashDet;
 
public interface LoanAdvDashDetDashRepo extends JpaRepository<LoanAdvDashDet, String> {
	
	
	@Query(value = "SELECT\n" + 
			"        UUID() AS uni_key,\n" + 
			"        COUNT(DISTINCT adv.emp_id) AS emp, 0 AS skip_id, 0 AS skip_tott, ROUND(ifnull(SUM(adv.adv_amount),0),2) AS adv_tot \n" + 
			"    FROM\n" + 
			"        tbl_advance adv \n" + 
			"    WHERE\n" + 
			"        adv.ded_month = :month \n" + 
			"        AND adv.ded_year = :year \n" + 
			"        AND adv.del_status = 1 ", nativeQuery = true)
	LoanAdvDashDet getAdvnceDetails(@Param("year") String  year,@Param("month") String  month);
	
	
	
	
	@Query(value = "SELECT\n" + 
			"        UUID() AS uni_key,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(DISTINCT tbl_loan_main.emp_id)     \n" + 
			"        FROM\n" + 
			"            tbl_loan_main     \n" + 
			"        WHERE\n" + 
			"            :currDate BETWEEN tbl_loan_main.loan_repay_start AND tbl_loan_main.loan_repay_end \n" + 
			"            AND tbl_loan_main.del_status = 1 \n" + 
			"            AND tbl_loan_main.skip_id = 0 \n" + 
			"            AND tbl_loan_main.current_outstanding > 0 ) AS emp,\n" + 
			"        0 AS skip_id,\n" + 
			"        (     SELECT\n" + 
			"            SUM(             CASE \n" + 
			"                WHEN advance.current_outstanding < advance.loan_emi_intrest THEN advance.current_outstanding \n" + 
			"                ELSE advance.loan_emi_intrest         \n" + 
			"            END ) \n" + 
			"        FROM\n" + 
			"            tbl_loan_main advance \n" + 
			"        WHERE\n" + 
			"            :currDate BETWEEN advance.loan_repay_start AND advance.loan_repay_end \n" + 
			"            AND advance.del_status = 1 \n" + 
			"            AND advance.skip_id = 0 \n" + 
			"            AND advance.current_outstanding > 0     ) AS adv_tot,\n" + 
			"        0 AS skip_tott", nativeQuery = true)
	LoanAdvDashDet getLoanDetails(@Param("currDate") String  currDate);
	
	

}
