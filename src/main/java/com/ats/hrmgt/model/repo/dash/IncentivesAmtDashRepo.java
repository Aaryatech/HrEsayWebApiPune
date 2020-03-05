package com.ats.hrmgt.model.repo.dash;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.model.dashboard.IncentivesAmtDash;

public interface IncentivesAmtDashRepo extends JpaRepository<IncentivesAmtDash,String> {
	
	
	@Query(value = "SELECT\n" + 
			"    UUID() AS uni_key, SUM(tbl_advance.adv_amount) AS perf_incentive,\n" + 
			"    SUM(tbl_loan_main.loan_amt) AS prod_incentive\n" + 
			"FROM\n" + 
			"    tbl_advance,\n" + 
			"    tbl_loan_main\n" + 
			"WHERE\n" + 
			"    tbl_loan_main.emp_id = :empId AND tbl_loan_main.del_status = 1 AND tbl_advance.del_status = 1 AND tbl_advance.emp_id =:empId", nativeQuery = true)
	IncentivesAmtDash getWeekBirth(@Param("empId") int empId);

}
