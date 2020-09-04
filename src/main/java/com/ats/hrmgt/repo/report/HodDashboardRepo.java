package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.HodDashboard;

public interface HodDashboardRepo extends JpaRepository<HodDashboard, Integer> {
	
	
	@Query(value=" "
			+ " SELECT b.*,  coalesce((Present_Days	.cnt),\n" + 
			"        0) as present_days ,coalesce((WO_Actually_Covered.cnt),\n" + 
			"        0) as week_off_covered,\n" + 
			"        \n" + 
			"        coalesce((WIAB.cnt),\n" + 
			"        0) as ab_days,\n" + 
			"          coalesce((Last_month_pending_WO.cnt),\n" + 
			"        0) as last_month_pend_woff,\n" + 
			"         coalesce((OT_last_month.ot_minutes),\n" + 
			"        0) as ot_last_month,\n" + 
			"         coalesce((Working_Days.working_days),\n" + 
			"        0) as working_days " + 
			"        \n" + 
			" \n" + 
			" FROM (SELECT m_employees.emp_id,m_employees.emp_code,m_employees.first_name,m_employees.surname FROM m_employees "
			+ " where m_employees.depart_id IN (:deptIdList) and m_employees.location_id IN (:locIdList) and m_employees.del_status=1  ) b \n" + 
			"      \n" + 
			"      LEFT JOIN (SELECT tbl_lvm_sumup.name_sd , COUNT(tbl_attt_daily_daily.lv_sumup_id) as cnt,tbl_attt_daily_daily.emp_id\n" + 
			"                 FROM tbl_attt_daily_daily, tbl_lvm_sumup WHERE tbl_attt_daily_daily.att_date BETWEEN :cmFromDate AND :cmToDate AND  tbl_attt_daily_daily.lv_sumup_id=tbl_lvm_sumup.id and tbl_lvm_sumup.id=5 GROUP by  \n" + 
			" tbl_attt_daily_daily.lv_sumup_id,tbl_attt_daily_daily.emp_id ) Present_Days on b.emp_id=Present_Days.emp_id\n" + 
			" \n" + 
			" \n" + 
			"  LEFT JOIN (SELECT tbl_lvm_sumup.name_sd , COUNT(tbl_attt_daily_daily.lv_sumup_id) as cnt,tbl_attt_daily_daily.emp_id\n" + 
			"                 FROM tbl_attt_daily_daily, tbl_lvm_sumup WHERE tbl_attt_daily_daily.att_date BETWEEN :cmFromDate AND :cmToDate AND  tbl_attt_daily_daily.lv_sumup_id=tbl_lvm_sumup.id and tbl_lvm_sumup.id=12 GROUP by  \n" + 
			" tbl_attt_daily_daily.lv_sumup_id,tbl_attt_daily_daily.emp_id ) WO_Actually_Covered	on b.emp_id=WO_Actually_Covered.emp_id\n" + 
			"  \n" + 
			"  LEFT JOIN (SELECT tbl_lvm_sumup.name_sd , COUNT(tbl_attt_daily_daily.lv_sumup_id) as cnt,tbl_attt_daily_daily.emp_id\n" + 
			"                 FROM tbl_attt_daily_daily, tbl_lvm_sumup WHERE tbl_attt_daily_daily.att_date BETWEEN :cmFromDate AND :cmToDate AND  tbl_attt_daily_daily.lv_sumup_id=tbl_lvm_sumup.id and tbl_lvm_sumup.id=22 GROUP by  \n" + 
			" tbl_attt_daily_daily.lv_sumup_id,tbl_attt_daily_daily.emp_id ) WIAB on b.emp_id=WIAB.emp_id\n" + 
			" \n" + 
			" \n" + 
			"   LEFT JOIN (SELECT tbl_lvm_sumup.name_sd , COUNT(tbl_attt_daily_daily.lv_sumup_id) as cnt,tbl_attt_daily_daily.emp_id\n" + 
			"                 FROM tbl_attt_daily_daily, tbl_lvm_sumup WHERE tbl_attt_daily_daily.att_date BETWEEN :pmFromDate AND :pmToDate AND  tbl_attt_daily_daily.lv_sumup_id=tbl_lvm_sumup.id and tbl_lvm_sumup.id=12 GROUP by  \n" + 
			" tbl_attt_daily_daily.lv_sumup_id,tbl_attt_daily_daily.emp_id ) Last_month_pending_WO	on b.emp_id=Last_month_pending_WO.emp_id\n" + 
			" \n" + 
			" \n" + 
			" LEFT JOIN (SELECT sum(tbl_attt_daily_daily.ot_hr) as ot_minutes1 ,"
			+ " COALESCE( (FLOOR(SUM(tbl_attt_daily_daily.ot_hr)/60  )   + round(  MOD(SUM(tbl_attt_daily_daily.ot_hr), 60)*0.0166  , \n" + 
			"			 2)), 0) AS ot_minutes2,"
			+ ""
			+ " COALESCE( (CONCAT(FLOOR(SUM(tbl_attt_daily_daily.ot_hr) / 60), '.', " + 
			"			 LPAD(  MOD(SUM(tbl_attt_daily_daily.ot_hr), " + 
			"			 60), 2, '0'))), " + 
			"			 0) AS ot_minutes, "
			+ " tbl_attt_daily_daily.emp_id\n" + 
			"                 FROM tbl_attt_daily_daily WHERE tbl_attt_daily_daily.att_date BETWEEN :pmFromDate AND :pmToDate and tbl_attt_daily_daily.freeze_by_supervisor=2  GROUP by\n" + 
			" tbl_attt_daily_daily.emp_id ) OT_last_month	on b.emp_id=OT_last_month.emp_id\n" + 
			" \n" + 
			"LEFT JOIN ( SELECT tbl_attt_summary_daily.working_days,tbl_attt_summary_daily.emp_id FROM tbl_attt_summary_daily WHERE tbl_attt_summary_daily.month=month(:cmFromDate) and \n" + 
			"tbl_attt_summary_daily.year=year(:cmFromDate) ) Working_Days on b.emp_id=Working_Days.emp_id "
			+ ""
			+ "",nativeQuery=true)
	
	List<HodDashboard> getHodDashboardByDeptLocIds(@Param("deptIdList") List<Integer> deptIdList, @Param("locIdList")
	List<Integer> locIdList,@Param("cmFromDate") String cmFromDate,@Param("cmToDate") String cmToDate,@Param("pmFromDate") String pmFromDate,@Param("pmToDate") String pmToDate );
	

}
