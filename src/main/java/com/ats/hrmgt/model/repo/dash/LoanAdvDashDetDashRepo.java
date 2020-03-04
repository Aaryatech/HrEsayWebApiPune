package com.ats.hrmgt.model.repo.dash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.dashboard.LoanAdvDashDet;
 
public interface LoanAdvDashDetDashRepo extends JpaRepository<LoanAdvDashDet, String> {
	
	
	@Query(value = "SELECT\n" + 
			"    UUID() AS uni_key,(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_advance.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_advance\n" + 
			"    WHERE\n" + 
			"        tbl_advance.ded_month =:month AND tbl_advance.ded_year = :year AND tbl_advance.del_status = 1 AND tbl_advance.id NOT IN(\n" + 
			"        SELECT\n" + 
			"            tbl_advance_details.adv_id\n" + 
			"        FROM\n" + 
			"            tbl_advance_details\n" + 
			"        WHERE\n" + 
			"            MONTH(\n" + 
			"                tbl_advance_details.skip_login_time\n" + 
			"            ) = :month AND YEAR(\n" + 
			"                tbl_advance_details.skip_login_time\n" + 
			"            ) = :year AND tbl_advance_details.del_status = 1\n" + 
			"    )\n" + 
			") AS emp,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_advance_details.skip_login_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_advance_details\n" + 
			"    WHERE\n" + 
			"        MONTH(\n" + 
			"            tbl_advance_details.skip_login_time\n" + 
			"        ) = :month AND YEAR(\n" + 
			"            tbl_advance_details.skip_login_time\n" + 
			"        ) = :year AND tbl_advance_details.del_status = 1\n" + 
			") AS skip_id,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        SUM(advance.adv_amount)\n" + 
			"    FROM\n" + 
			"        tbl_advance advance,\n" + 
			"        tbl_advance_details detAdv\n" + 
			"    WHERE\n" + 
			"        detAdv.adv_id = advance.id AND MONTH(detAdv.skip_login_time) = :month AND YEAR(detAdv.skip_login_time) = :year AND detAdv.del_status = 1\n" + 
			") AS skip_tott,\n" + 
			"SUM(adv.adv_amount) AS adv_tot\n" + 
			"FROM\n" + 
			"    tbl_advance adv\n" + 
			"WHERE\n" + 
			"    adv.ded_month = :month AND adv.ded_year = :year AND adv.del_status = 1 AND adv.id NOT IN(\n" + 
			"    SELECT\n" + 
			"        tbl_advance_details.adv_id\n" + 
			"    FROM\n" + 
			"        tbl_advance_details\n" + 
			"    WHERE\n" + 
			"        MONTH(\n" + 
			"            tbl_advance_details.skip_login_time\n" + 
			"        ) = :month AND YEAR(\n" + 
			"            tbl_advance_details.skip_login_time\n" + 
			"        ) = :year AND tbl_advance_details.del_status = 1\n" + 
			")", nativeQuery = true)
	LoanAdvDashDet getAdvnceDetails(@Param("year") String  year,@Param("month") String  month);
	
	
	
	
	@Query(value = "SELECT\n" + 
			"    UUID() AS uni_key,(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_loan_main.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_loan_main\n" + 
			"    WHERE\n" + 
			"        :currDate BETWEEN tbl_loan_main.loan_repay_start AND tbl_loan_main.loan_repay_end AND tbl_loan_main.del_status = 1 AND tbl_loan_main.skip_id = 0 AND tbl_loan_main.current_outstanding > 0\n" + 
			") AS emp,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_loan_main.skip_login_name\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_loan_main\n" + 
			"    WHERE\n" + 
			"        MONTH(tbl_loan_main.skip_login_time) = :month AND YEAR(tbl_loan_main.skip_login_time) = :year AND tbl_loan_main.del_status = 1 AND tbl_loan_main.skip_id > 0\n" + 
			") AS skip_id,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        SUM(\n" + 
			"            CASE WHEN advance.current_outstanding < advance.loan_emi_intrest THEN advance.current_outstanding ELSE advance.loan_emi_intrest\n" + 
			"        END\n" + 
			")\n" + 
			"FROM\n" + 
			"    tbl_loan_main advance\n" + 
			"WHERE\n" + 
			"      :currDate BETWEEN advance.loan_repay_start AND advance.loan_repay_end AND advance.del_status = 1 AND advance.skip_id = 0 AND advance.current_outstanding > 0\n" + 
			"    ) AS adv_tot,\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        SUM(\n" + 
			"            CASE WHEN advance.current_outstanding < advance.loan_emi_intrest THEN advance.current_outstanding ELSE advance.loan_emi_intrest\n" + 
			"        END\n" + 
			")\n" + 
			"FROM\n" + 
			"    tbl_loan_main advance\n" + 
			"WHERE\n" + 
			"    advance.del_status = 1 AND advance.skip_id > 0 AND advance.current_outstanding > 0 AND MONTH(advance.skip_login_time) =:month AND YEAR(advance.skip_login_time) = :year \n" + 
			") AS skip_tott", nativeQuery = true)
	LoanAdvDashDet getLoanDetails(@Param("year") String  year,@Param("month") String  month,@Param("currDate") String  currDate);
	
	

}
