package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.HodDeptDashb;

public interface HodDeptDashbRepo extends JpaRepository<HodDeptDashb, Integer>{
	
	
	@Query(value=" "
			+ " SELECT	 " + 
			"        dept.*, " + 
			"        IFNULL(sum(Present_Days .cnt),0) as present_days , " + 
			"       IFNULL(sum(WIAB.cnt),0) as ab_days, " + 
			"        IFNULL(sum(OT_last_month.ot_minutes),0) as ot_last_month, " + 
			"       IFNULL( COUNT(b.emp_id),0) as actual_emp_count " + 
			"         " + 
			"    FROM " + 
			"        (SELECT " + 
			"           m_department.depart_id,m_department.name,m_department.name_sd,m_department.ex_int1 as req_emp_count "
			+ "FROM m_department WHERE m_department.depart_id in (:deptIdList) " + 
			"             ) dept " + 
			"               LEFT JOIN( SELECT " + 
			"            m_employees.emp_id, " + 
			"m_employees.depart_id " + 
			"        FROM " + 
			"            m_employees   " + 
			"        where " + 
			"            m_employees.depart_id IN ( " + 
			"               :deptIdList) and  m_employees.location_id IN (  " + 
			"			:locIdList) and m_employees.del_status=1 " + 
			"            ) b on dept.depart_id=b.depart_id " + 
			"    LEFT JOIN " + 
			"        ( " + 
			"            SELECT " + 
			"                tbl_lvm_sumup.name_sd , " + 
			"                COUNT(tbl_attt_daily_daily.lv_sumup_id) as cnt, " + 
			"                tbl_attt_daily_daily.emp_id                   " + 
			"            FROM " + 
			"                tbl_attt_daily_daily, " + 
			"                tbl_lvm_sumup  " + 
			"            WHERE " + 
			"                tbl_attt_daily_daily.att_date=date(CURRENT_DATE) " + 
			"                AND  tbl_attt_daily_daily.lv_sumup_id=tbl_lvm_sumup.id  " + 
			"                and tbl_lvm_sumup.id=5  " + 
			"           GROUP by tbl_attt_daily_daily.emp_id " + 
			"                " + 
			"        ) Present_Days  " + 
			"            on b.emp_id=Present_Days.emp_id        " + 
			"    " + 
			"    LEFT JOIN " + 
			"        ( " + 
			"            SELECT " + 
			"                tbl_lvm_sumup.name_sd , " + 
			"                COUNT(tbl_attt_daily_daily.lv_sumup_id) as cnt, " + 
			"                tbl_attt_daily_daily.emp_id                   " + 
			"            FROM " + 
			"                tbl_attt_daily_daily, " + 
			"                tbl_lvm_sumup  " + 
			"            WHERE " + 
			"                tbl_attt_daily_daily.att_date=date(CURRENT_DATE) " + 
			"                AND  tbl_attt_daily_daily.lv_sumup_id=tbl_lvm_sumup.id  " + 
			"                and tbl_lvm_sumup.id=22  " + 
			"                       GROUP by tbl_attt_daily_daily.emp_id " + 
			" " + 
			"            " + 
			"        ) WIAB  " + 
			"            on b.emp_id=WIAB.emp_id         " + 
			"   " + 
			"    LEFT JOIN " + 
			"        ( " + 
			"            SELECT " + 
			"                COALESCE( (CONCAT(FLOOR(SUM(tbl_attt_daily_daily.ot_hr) / 60), '.',  " + 
			"			 	 LPAD(  MOD(SUM(tbl_attt_daily_daily.ot_hr), " + 
			"		 	 60), 2, '0'))), " + 
			"		  0) AS ot_minutes "+
			"                , " + 
			"                tbl_attt_daily_daily.emp_id                   " + 
			"            FROM " + 
			"                tbl_attt_daily_daily  " + 
			"            WHERE " + 
			"                tbl_attt_daily_daily.att_date BETWEEN :pmFromDate and :pmToDate " + 
			"                and tbl_attt_daily_daily.freeze_by_supervisor=2   " + 
			"                      GROUP by tbl_attt_daily_daily.emp_id " + 
			" " + 
			"            " + 
			"        ) OT_last_month  " + 
			"            on b.emp_id=OT_last_month.emp_id " + 
			"            GROUP by dept.depart_id ",nativeQuery=true)
	
	List<HodDeptDashb> getHodDeptDashb(@Param("deptIdList") List<Integer> deptIdList, @Param("locIdList") List<Integer> locIdList
	,@Param("pmFromDate") String pmFromDate,@Param("pmToDate") String pmToDate );
	

}
