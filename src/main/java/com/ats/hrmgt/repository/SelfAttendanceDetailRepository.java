package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.SelfAttendanceDetail;

public interface SelfAttendanceDetailRepository extends JpaRepository<SelfAttendanceDetail, Integer>{

	@Query(value = "SELECT\n" + 
			"        UUID() as id ,\n" + 
			"        emp_code ,\n" + 
			"        emp_name ,\n" + 
			"        att_date ,\n" + 
			"        atts_sd_show as att_status ,\n" + 
			"        lv_sumup_id,\n" + 
			"        CONCAT(FLOOR(working_hrs/60),\n" + 
			"        ':',\n" + 
			"        LPAD(MOD(working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs,\n" + 
			"        out_time,\n" + 
			"        in_time\n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily       \n" + 
			"    WHERE\n" + 
			"        att_date between :fromDate and :toDate          \n" + 
			"        and emp_id=:empId", nativeQuery = true)
	List<SelfAttendanceDetail> getSelfAttendaceForDashboard(String fromDate, int empId, String toDate);

}
