package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.DailyAttendance;

public interface DailyAttendanceRepository extends JpaRepository<DailyAttendance, Integer> {

	@Query(value = "select * from tbl_attt_daily_daily where att_date between :fromDate and :toDate and is_fixed=0 and rec_status='o'", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceList(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "select * from tbl_attt_daily_daily where att_date between :fromDate and :toDate  ", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceListAll(String fromDate, String toDate);

	@Query(value = "select d.* from tbl_attt_daily_daily d,leave_authority la,m_employees e where d.att_date between :fromDate and :toDate and la.emp_id=d.emp_id and ( la.ini_auth_emp_id=:userId or la.fin_auth_emp_id=:userId ) and d.emp_id=e.emp_id and e.depart_id in (:deptIds) and e.del_status=1", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceListForHod(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("userId") int userId, List<Integer> deptIds);
	
	@Query(value = "select * from tbl_attt_daily_daily where att_date between :fromDate and :toDate  AND  company_id=:companyId AND late_mark=1 ORDER BY emp_id ASC ", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceListAll1(int companyId, String fromDate, String toDate);

	@Query(value = "select * from tbl_attt_daily_daily where id=:dailyId", nativeQuery = true)
	DailyAttendance getdailyRecordById(@Param("dailyId") int dailyId);

	@Query(value = "select * from tbl_attt_daily_daily where att_date between :fromDate and :toDate and  emp_id=:empId and is_fixed=0 and rec_status='o'", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceList(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("empId") int empId);

	@Transactional
	@Modifying
	@Query("update DailyAttendance set rec_status='F',is_fixed=1 WHERE emp_id in (:empIds) and att_date=:date")
	int fixDailyDailyRecord(@Param("date") String date, @Param("empIds") List<Integer> empIds);

	@Transactional
	@Modifying
	@Query("update DailyAttendance set rec_status=:sts,is_fixed=:isFixed WHERE att_date between :fromDate and :toDate and emp_id in (:empIds) ")
	int fixDailyDailyRecordBetweenDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("empIds") List<Integer> empIds, @Param("isFixed") int isFixed, @Param("sts") String sts);

	@Query(value = "SELECT\n" + "    tbl_attt_daily_daily.*\n" + "FROM\n" + "    tbl_attt_daily_daily\n" + "WHERE\n"
			+ "    tbl_attt_daily_daily.emp_id =:empId AND YEAR(tbl_attt_daily_daily.att_date) =:year  AND MONTH(tbl_attt_daily_daily.att_date) =:monthValue \n"
			+ "ORDER BY\n" + "    tbl_attt_daily_daily.id\n" + "DESC\n" + "LIMIT 1", nativeQuery = true)
	DailyAttendance findLastMonthRecordOfEmp(int empId, int monthValue, int year);

	@Query(value = "SELECT\n" + "    tbl_attt_daily_daily.*\n" + "FROM\n" + "    tbl_attt_daily_daily\n" + "WHERE\n"
			+ "    tbl_attt_daily_daily.emp_id =:empId AND tbl_attt_daily_daily.in_time != '00:00:00' \n" + "ORDER BY\n"
			+ "    tbl_attt_daily_daily.id\n" + "DESC\n" + "LIMIT 1", nativeQuery = true)
	DailyAttendance findLastMonthRecordOfEmp(int empId);

	@Query(value = "select\n" + 
			"        id ,\n" + 
			"        d.company_id ,\n" + 
			"        d.emp_code ,\n" + 
			"        d.emp_name ,\n" + 
			"        d.att_date ,\n" + 
			"        d.att_status ,\n" + 
			"        d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR( d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"        d.in_time ,\n" + 
			"        d.rec_status ,\n" + 
			"        d.login_name ,\n" + 
			"        d.login_time ,\n" + 
			"        d.import_date ,\n" + 
			"        d.cmp_code ,\n" + 
			"        d.emp_id ,\n" + 
			"        CONCAT(FLOOR( d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.ot_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as ot_hr ,\n" + 
			"        d.current_shiftid ,\n" + 
			"        d.late_mark ,\n" + 
			"        d.late_min ,\n" + 
			"        d.reason ,\n" + 
			"        d.current_shiftname ,\n" + 
			"        d.freeze_by_supervisor ,\n" + 
			"        d.comments_supervisor ,\n" + 
			"        d.get_pass_used_count ,\n" + 
			"        d.get_pass_used_hour ,\n" + 
			"        d.get_pass_used_hour_reason ,\n" + 
			"        d.raw_data_inout ,\n" + 
			"        d.manual_ot_hr ,\n" + 
			"        d.full_night ,\n" + 
			"        d.half_night ,\n" + 
			"        d.out_time ,\n" + 
			"        d.early_going_mark ,\n" + 
			"        d.early_going_min ,\n" + 
			"        d.multiple_entries ,\n" + 
			"        d.casetype ,\n" + 
			"        d.is_fixed ,\n" + 
			"        d.by_file_updated ,\n" + 
			"        d.location_id ,\n" + 
			"        d.emp_type ,\n" + 
			"        dep.name as emp_json ,\n" + 
			"        d.atsumm_uid ,\n" + 
			"        d.file_name ,\n" + 
			"        d.row_id,d.atts_sd_show \n" + 
			"    from\n" + 
			"        tbl_attt_daily_daily d,\n" + 
			"        m_employees e,m_department dep \n" + 
			"    where\n" + 
			"        d.att_date = :date \n" + 
			"        and d.emp_id=e.emp_id\n" + 
			"        and e.depart_id=dep.depart_id\n" + 
			"        and e.depart_id in (:departIds) \n" + 
			"        and d.comments_supervisor=8", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceByDeptId(@Param("date") String date,
			@Param("departIds") List<Integer> departIds);

	@Modifying
	@Query(value = "INSERT INTO tbl_attt_daily_daily (id, company_id, emp_code, emp_name, att_date, att_status,  lv_sumup_id, working_hrs, in_time, rec_status, login_name, login_time, import_date, cmp_code, emp_id, ot_hr,  current_shiftid, late_mark, late_min, reason, current_shiftname, freeze_by_supervisor, comments_supervisor, get_pass_used_count, get_pass_used_hour, get_pass_used_hour_reason, raw_data_inout, manual_ot_hr, full_night, half_night, out_time, early_going_mark, early_going_min, multiple_entries, casetype, is_fixed, by_file_updated, location_id, emp_type, emp_json, atsumm_uid, file_name, row_id) VALUES :string ", nativeQuery = true)
	@Transactional
	void insert(String string);

	@Transactional
	@Modifying
	@Query("update DailyAttendance set multipleEntries=:status  WHERE id in (:dailydaillyIds) ")
	int updateweeklyoffotStatutoused(List<Integer> dailydaillyIds, String status);

	@Query(value = "select * from tbl_attt_daily_daily where att_date =:filterDate", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceListRec(@Param("filterDate") String filterDate);

	@Query(value = "select * from tbl_attt_daily_daily   ORDER BY tbl_attt_daily_daily.id DESC LIMIT 1", nativeQuery = true)
	DailyAttendance dailyAttendanceListLastRec();

	@Query(value = "select  IFNULL((select shift_id from t_shift_assign_daily where shift_date=:fromDate and emp_id=:empId),(select current_shiftid from m_employees where emp_id=:empId)) as shift_id", nativeQuery = true)
	String getShiftIdByEmpId(@Param("empId")  int empId, @Param("fromDate") String fromDate);

	@Transactional
	@Modifying
	@Query("update DailyAttendance set freeze_by_supervisor=:status  WHERE id in (:ids) ")
	int updateOtApproveStatus(@Param("ids") List<Integer> ids,@Param("status") int status);

	@Transactional
	@Modifying
	@Query("update DailyAttendance set comments_supervisor=:status  WHERE id in (:ids) ")
	int updateAttendaceApproveStatus(@Param("ids") List<Integer> ids,@Param("status") int status);
	
	@Query(value = "select\n" + 
			"        id ,\n" + 
			"        d.company_id ,\n" + 
			"        d.emp_code ,\n" + 
			"        d.emp_name ,\n" + 
			"        d.att_date ,\n" + 
			"        d.att_status ,\n" + 
			"        d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR( d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"        d.in_time ,\n" + 
			"        d.rec_status ,\n" + 
			"        d.login_name ,\n" + 
			"        d.login_time ,\n" + 
			"        d.import_date ,\n" + 
			"        d.cmp_code ,\n" + 
			"        d.emp_id ,\n" + 
			"        CONCAT(FLOOR( d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.ot_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as ot_hr ,\n" + 
			"        d.current_shiftid ,\n" + 
			"        d.late_mark ,\n" + 
			"        d.late_min ,\n" + 
			"        d.reason ,\n" + 
			"        d.current_shiftname ,\n" + 
			"        d.freeze_by_supervisor ,\n" + 
			"        d.comments_supervisor ,\n" + 
			"        d.get_pass_used_count ,\n" + 
			"        d.get_pass_used_hour ,\n" + 
			"        d.get_pass_used_hour_reason ,\n" + 
			"        d.raw_data_inout ,\n" + 
			"        d.manual_ot_hr ,\n" + 
			"        d.full_night ,\n" + 
			"        d.half_night ,\n" + 
			"        d.out_time ,\n" + 
			"        d.early_going_mark ,\n" + 
			"        d.early_going_min ,\n" + 
			"        d.multiple_entries ,\n" + 
			"        d.casetype ,\n" + 
			"        d.is_fixed ,\n" + 
			"        d.by_file_updated ,\n" + 
			"        d.location_id ,\n" + 
			"        d.emp_type ,\n" + 
			"        dep.name as emp_json ,\n" + 
			"        d.atsumm_uid ,\n" + 
			"        d.file_name ,\n" + 
			"        d.row_id,d.atts_sd_show \n" + 
			"    from\n" + 
			"        tbl_attt_daily_daily d,\n" + 
			"        m_employees e,m_department dep \n" + 
			"    where\n" + 
			"        d.att_date = :fromDate \n" + 
			"        and d.emp_id=e.emp_id\n" + 
			"        and e.depart_id=dep.depart_id\n" + 
			"        and comments_supervisor=0", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceListForSecurityApprove(@Param("fromDate") String fromDate);

	@Query(value = "select\n" + 
			"        id ,\n" + 
			"        d.company_id ,\n" + 
			"        d.emp_code ,\n" + 
			"        d.emp_name ,\n" + 
			"        d.att_date ,\n" + 
			"        d.att_status ,\n" + 
			"        d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR( d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"        d.in_time ,\n" + 
			"        d.rec_status ,\n" + 
			"        d.login_name ,\n" + 
			"        d.login_time ,\n" + 
			"        d.import_date ,\n" + 
			"        d.cmp_code ,\n" + 
			"        d.emp_id ,\n" + 
			"        CONCAT(FLOOR( d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.ot_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as ot_hr ,\n" + 
			"        d.current_shiftid ,\n" + 
			"        d.late_mark ,\n" + 
			"        d.late_min ,\n" + 
			"        d.reason ,\n" + 
			"        d.current_shiftname ,\n" + 
			"        d.freeze_by_supervisor ,\n" + 
			"        d.comments_supervisor ,\n" + 
			"        d.get_pass_used_count ,\n" + 
			"        d.get_pass_used_hour ,\n" + 
			"        d.get_pass_used_hour_reason ,\n" + 
			"        d.raw_data_inout ,\n" + 
			"        d.manual_ot_hr ,\n" + 
			"        d.full_night ,\n" + 
			"        d.half_night ,\n" + 
			"        d.out_time ,\n" + 
			"        d.early_going_mark ,\n" + 
			"        d.early_going_min ,\n" + 
			"        d.multiple_entries ,\n" + 
			"        d.casetype ,\n" + 
			"        d.is_fixed ,\n" + 
			"        d.by_file_updated ,\n" + 
			"        d.location_id ,\n" + 
			"        d.emp_type ,\n" + 
			"        dep.name as emp_json ,\n" + 
			"        d.atsumm_uid ,\n" + 
			"        d.file_name ,\n" + 
			"        d.row_id,\n" + 
			"        d.atts_sd_show      \n" + 
			"    from\n" + 
			"        tbl_attt_daily_daily d,\n" + 
			"        m_employees e,\n" + 
			"        m_department dep,\n" + 
			"        leave_authority la\n" + 
			"    where\n" + 
			"        d.att_date = :date          \n" + 
			"        and d.emp_id=e.emp_id         \n" + 
			"        and e.depart_id=dep.depart_id         \n" + 
			"        and la.emp_id=e.emp_id  \n" + 
			"        and la.ini_auth_emp_id=:empId\n" + 
			"        and d.comments_supervisor=8", nativeQuery = true)
	List<DailyAttendance> getEmployyeDailyDailyListByAuthority(@Param("date")String date,@Param("empId") int empId);

	
	@Query(value = "select\n" + 
			"        id ,\n" + 
			"        d.company_id ,\n" + 
			"        d.emp_code ,\n" + 
			"        d.emp_name ,\n" + 
			"        d.att_date ,\n" + 
			"        d.att_status ,\n" + 
			"        d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR( d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"        d.in_time ,\n" + 
			"        d.rec_status ,\n" + 
			"        d.login_name ,\n" + 
			"        d.login_time ,\n" + 
			"        d.import_date ,\n" + 
			"        d.cmp_code ,\n" + 
			"        d.emp_id ,\n" + 
			"        CONCAT(FLOOR( d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.ot_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as ot_hr ,\n" + 
			"        d.current_shiftid ,\n" + 
			"        d.late_mark ,\n" + 
			"        d.late_min ,\n" + 
			"        d.reason ,\n" + 
			"        d.current_shiftname ,\n" + 
			"        d.freeze_by_supervisor ,\n" + 
			"        d.comments_supervisor ,\n" + 
			"        d.get_pass_used_count ,\n" + 
			"        d.get_pass_used_hour ,\n" + 
			"        d.get_pass_used_hour_reason ,\n" + 
			"        d.raw_data_inout ,\n" + 
			"        d.manual_ot_hr ,\n" + 
			"        d.full_night ,\n" + 
			"        d.half_night ,\n" + 
			"        d.out_time ,\n" + 
			"        d.early_going_mark ,\n" + 
			"        d.early_going_min ,\n" + 
			"        d.multiple_entries ,\n" + 
			"        d.casetype ,\n" + 
			"        d.is_fixed ,\n" + 
			"        d.by_file_updated ,\n" + 
			"        d.location_id ,\n" + 
			"        d.emp_type ,\n" + 
			"        dep.name as emp_json ,\n" + 
			"        d.atsumm_uid ,\n" + 
			"        d.file_name ,\n" + 
			"        d.row_id,d.atts_sd_show \n" + 
			"    from\n" + 
			"        tbl_attt_daily_daily d,\n" + 
			"        m_employees e,m_department dep \n" + 
			"    where\n" + 
			"        d.att_date = :date \n" + 
			"        and d.emp_id=e.emp_id\n" + 
			"        and e.depart_id=dep.depart_id\n" + 
			"        and comments_supervisor=0 and e.location_id in (:locId)", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceListForSecurityApproveLocId(@Param("date")String date, @Param("locId")List<Integer> locId);

	
	@Query(value = "select\n" + 
			"        id ,\n" + 
			"        d.company_id ,\n" + 
			"        d.emp_code ,\n" + 
			"        d.emp_name ,\n" + 
			"        d.att_date ,\n" + 
			"        d.att_status ,\n" + 
			"        d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR( d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"        d.in_time ,\n" + 
			"        d.rec_status ,\n" + 
			"        d.login_name ,\n" + 
			"        d.login_time ,\n" + 
			"        d.import_date ,\n" + 
			"        d.cmp_code ,\n" + 
			"        d.emp_id ,\n" + 
			"        CONCAT(FLOOR( d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.ot_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as ot_hr ,\n" + 
			"        d.current_shiftid ,\n" + 
			"        d.late_mark ,\n" + 
			"        d.late_min ,\n" + 
			"        case when e.contractor_id>0 then (select m.org_name from m_contractor m where m.contractor_id=e.contractor_id) else (select m.company_name from tbl_mst_sub_company m where m.company_id=e.sub_cmp_id)  end as reason ,\n" + 
			"        d.current_shiftname ,\n" + 
			"        d.freeze_by_supervisor ,\n" + 
			"        d.comments_supervisor ,\n" + 
			"        d.get_pass_used_count ,\n" + 
			"        d.get_pass_used_hour ,\n" + 
			"        d.get_pass_used_hour_reason ,\n" + 
			"        d.raw_data_inout ,\n" + 
			"        d.manual_ot_hr ,\n" + 
			"        d.full_night ,\n" + 
			"        d.half_night ,\n" + 
			"        d.out_time ,\n" + 
			"        d.early_going_mark ,\n" + 
			"        d.early_going_min ,\n" + 
			"        d.multiple_entries ,\n" + 
			"        d.casetype ,\n" + 
			"        d.is_fixed ,\n" + 
			"        d.by_file_updated ,\n" + 
			"        d.location_id ,\n" + 
			"        d.emp_type ,\n" + 
			"        dep.name as emp_json ,\n" + 
			"        d.atsumm_uid ,\n" + 
			"        d.file_name ,\n" + 
			"        e.notice_pay_amount as row_id,\n" + 
			"        d.atts_sd_show      \n" + 
			"    from\n" + 
			"        tbl_attt_daily_daily d,\n" + 
			"        m_employees e,\n" + 
			"        m_department dep,\n" + 
			"        leave_authority la\n" + 
			"    where\n" + 
			"        d.att_date = :date          \n" + 
			"        and d.emp_id=e.emp_id         \n" + 
			"        and e.depart_id=dep.depart_id         \n" + 
			"        and la.emp_id=e.emp_id  \n" + 
			"        and la.ini_auth_emp_id=:empId\n" + 
			"        and d.comments_supervisor=8 and e.location_id in (:locId) order by e.notice_pay_amount asc,d.emp_name asc", nativeQuery = true)
	List<DailyAttendance> getEmployyeDailyDailyListByAuthorityLocId(@Param("date")String date, @Param("empId")int empId, @Param("locId")List<Integer> locId);

	@Query(value = "select ad.* from tbl_attt_daily_daily ad ,m_employees e where ad.att_date between :fromDate and :toDate  and ad.emp_id=e.emp_id and e.location_id in (:locId) and e.depart_id in (:deptIds) and e.del_status=1", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceListAlllocId(@Param("fromDate")String fromDate,@Param("toDate") String toDate,@Param("locId") List<Integer> locId, List<Integer> deptIds);

	
	
	//Sachin 05-09-2020
	@Transactional
	@Modifying
	@Query("UPDATE DailyAttendance set ot_hr=:otHr  WHERE id=:id")
	int updateOTById(@Param("otHr") String otHr,@Param("id") int id );

	//akshay 17-09-2020
	@Transactional
	@Modifying
	@Query("UPDATE DailyAttendance set ot_hr=:otHr,freeze_by_supervisor=2  WHERE id=:id")
	int updateOTByIdAndApprove(String otHr, int id);

	@Query(value = "select * from tbl_attt_daily_daily where att_date between :fromDate and :toDate and is_fixed=0 and rec_status='o' and emp_code in (:empCodes);", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceList(String fromDate, String toDate, List<String> empCodes);

}
