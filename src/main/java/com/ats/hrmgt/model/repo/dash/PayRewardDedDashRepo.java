package com.ats.hrmgt.model.repo.dash;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.model.dashboard.PayRewardDedDash;

public interface PayRewardDedDashRepo extends JpaRepository<PayRewardDedDash, String> {
	
	
	

	@Query(value = "SELECT\n" + 
			"        UUId() as uni_key,\n" + 
			"        COUNT(DISTINCT emp_id) as emp_count,\n" + 
			"        ROUND(ifnull((SUM(tpd.ded_rate)),0),2) as tot     \n" + 
			"    FROM\n" + 
			"        tblm_pay_deduction_details tpd     \n" + 
			"    WHERE\n" + 
			"        tpd.month=:month \n" + 
			"        AND tpd.year=:year \n" + 
			"        AND tpd.del_status=1\n" + 
			"        AND tpd.del_status = 1", nativeQuery = true)
	PayRewardDedDash getDedDetails(@Param("year") String  year,@Param("month") String  month);
	
	
	
	@Query(value = "SELECT\n" + 
			"        UUId() as uni_key,\n" + 
			"       COUNT(DISTINCT emp_id) AS emp_count,\n" + 
			"        ROUND(ifnull((SUM(tpd.pay_rate)),0),2) as tot       \n" + 
			"    FROM\n" + 
			"        tblm_pay_bonus_details tpd     \n" + 
			"    WHERE\n" + 
			"        tpd.month=:month \n" + 
			"        AND tpd.year=:year \n" + 
			"        AND tpd.del_status=1", nativeQuery = true)
	PayRewardDedDash getRewardDetails(@Param("year") String  year,@Param("month") String  month);

}
