package com.ats.hrmgt.model.repo.dash;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.model.dashboard.PayRewardDedDash;

public interface PayRewardDedDashRepo extends JpaRepository<PayRewardDedDash, String> {
	
	
	

	@Query(value = "\n" + 
			"SELECT UUId() as uni_key,\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tblm_pay_deduction_details.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tblm_pay_deduction_details\n" + 
			"    WHERE\n" + 
			"        tblm_pay_deduction_details.month = :month AND tblm_pay_deduction_details.year = :year AND tblm_pay_deduction_details.del_status = 1\n" + 
			") AS emp_count,\n" + 
			"        SUM(tpd.ded_rate) as tot\n" + 
			"    FROM\n" + 
			"        tblm_pay_deduction_details tpd\n" + 
			"    WHERE\n" + 
			"        tpd.month=:month AND tpd.year=:year AND tpd.del_status=1", nativeQuery = true)
	PayRewardDedDash getDedDetails(@Param("year") String  year,@Param("month") String  month);
	
	
	
	@Query(value = "SELECT UUId() as uni_key,\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tblm_pay_bonus_details.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tblm_pay_bonus_details\n" + 
			"    WHERE\n" + 
			"        tblm_pay_bonus_details.month =:month AND tblm_pay_bonus_details.year =:year AND tblm_pay_bonus_details.del_status = 1\n" + 
			") AS emp_count,\n" + 
			"        SUM(tpd.pay_rate) as tot\n" + 
			"    FROM\n" + 
			"        tblm_pay_bonus_details tpd\n" + 
			"    WHERE\n" + 
			"        tpd.month=:month AND tpd.year=:year AND tpd.del_status=1", nativeQuery = true)
	PayRewardDedDash getRewardDetails(@Param("year") String  year,@Param("month") String  month);

}
