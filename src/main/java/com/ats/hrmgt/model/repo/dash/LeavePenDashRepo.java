package com.ats.hrmgt.model.repo.dash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.dashboard.GetNewHiresDash;
import com.ats.hrmgt.model.dashboard.LeavePenDash;

public interface LeavePenDashRepo extends JpaRepository<LeavePenDash,String>{
	
	

	@Query(value = "SELECT UUID() as uni_key,\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(lv.leave_id)\n" + 
			"    FROM\n" + 
			"        leave_apply lv\n" + 
			"    WHERE\n" + 
			"        lv.ex_int1 = 1 AND lv.del_status = 1\n" + 
			") AS new_app,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(lv1.leave_id)\n" + 
			"    FROM\n" + 
			"        leave_apply lv1\n" + 
			"    WHERE\n" + 
			"        lv1.ex_int1 = 2 AND lv1.del_status = 1\n" + 
			") AS final_pending", nativeQuery = true)
	LeavePenDash getLeaveCnt();
	
	
	
	/*
	 * @Query(value = "SELECT\n" + "    (\n" + "    SELECT\n" + "        COUNT(\n" +
	 * "            DISTINCT tbl_attt_summary_daily.emp_id\n" + "        )\n" +
	 * "    FROM\n" + "        tbl_attt_summary_daily,\n" + "        m_employees,\n"
	 * + "        tbl_mst_emp_types\n" + "    WHERE\n" +
	 * "        tbl_attt_summary_daily.month = :month AND tbl_attt_summary_daily.year = :year AND tbl_attt_summary_daily.tot_othr > 0 AND m_employees.emp_id = tbl_attt_summary_daily.emp_id AND m_employees.emp_type = tbl_mst_emp_types.emp_type_id AND tbl_mst_emp_types.ot_applicable = 'Yes'\n"
	 * + ") AS new_app,\n" + "UUID() AS uni_key, 0 AS finalPending", nativeQuery =
	 * true) LeavePenDash getPerformabonus(@Param("year") String
	 * year,@Param("month") String month);
	 */
}
