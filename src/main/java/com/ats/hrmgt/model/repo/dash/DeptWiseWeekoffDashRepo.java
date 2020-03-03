package com.ats.hrmgt.model.repo.dash;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.dashboard.DeptWiseWeekoffDash;
import com.ats.hrmgt.model.dashboard.PreDayAttnDash;

public interface DeptWiseWeekoffDashRepo extends JpaRepository<DeptWiseWeekoffDash, Integer>{
	
	

	@Query(value = "SELECT\n" + 
			"    dep.depart_id,\n" + 
			"    dep.name_sd,\n" + 
			"    COUNT('') AS emp_count\n" + 
			"FROM\n" + 
			"    tbl_attt_daily_daily tad\n" + 
			"INNER JOIN m_employees emp ON\n" + 
			"    tad.emp_id = emp.emp_id\n" + 
			"INNER JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"WHERE\n" + 
			"    tad.att_date = :currDate AND tad.lv_sumup_id IN(12, 14, 16, 17, 18, 19)\n" + 
			"GROUP BY\n" + 
			"    dep.depart_id", nativeQuery = true)
	List<DeptWiseWeekoffDash> getAttendance(@Param("currDate") String currDate);

}
