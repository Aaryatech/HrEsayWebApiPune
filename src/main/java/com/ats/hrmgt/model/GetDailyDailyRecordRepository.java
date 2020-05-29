package com.ats.hrmgt.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GetDailyDailyRecordRepository extends JpaRepository<GetDailyDailyRecord, Integer> {

	@Query(value = "SELECT  id ,  company_id ,  emp_code ,  emp_name ,  att_date ,  att_status ,  lv_sumup_id ,  CONCAT(FLOOR(working_hrs/60),'.',LPAD(MOD(working_hrs,60), 2, '0')) as "
			+ "working_hrs ,  in_time ,  rec_status ,  login_name ,  login_time ,  import_date ,  cmp_code ,  emp_id ,  CONCAT(FLOOR(ot_hr/60),'.',LPAD(MOD(ot_hr,60), 2, '0')) as ot_hr ,"
			+ "current_shiftid ,  late_mark ,  late_min ,  reason ,  current_shiftname ,  freeze_by_supervisor ,  comments_supervisor ,  get_pass_used_count ,  get_pass_used_hour ,"
			+ "get_pass_used_hour_reason ,  raw_data_inout ,  manual_ot_hr ,  full_night ,  half_night ,  out_time ,  early_going_mark ,  early_going_min ,  multiple_entries ,"
			+ "casetype ,  is_fixed ,  by_file_updated ,  location_id ,  emp_type ,  emp_json ,  atsumm_uid ,  file_name ,  row_id  FROM  tbl_attt_daily_daily  "
			+ "WHERE att_date between :fromDate and :toDate and emp_id=:empId", nativeQuery = true)
	List<GetDailyDailyRecord> summaryDailyAttendanceListAll(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("empId") int empId);

	@Query(value = "SELECT  id ,  company_id ,  emp_code ,  emp_name ,  att_date ,  att_status ,  lv_sumup_id ,  CONCAT(FLOOR(working_hrs/60),':',LPAD(MOD(working_hrs,60), 2, '0')) as "
			+ "working_hrs ,  in_time ,  rec_status ,  login_name ,  login_time ,  import_date ,  cmp_code ,  emp_id ,  CONCAT(FLOOR(ot_hr/60),':',LPAD(MOD(ot_hr,60), 2, '0')) as ot_hr ,"
			+ "current_shiftid ,  late_mark ,  late_min ,  reason ,  current_shiftname ,  freeze_by_supervisor ,  comments_supervisor ,  get_pass_used_count ,  get_pass_used_hour ,"
			+ "get_pass_used_hour_reason ,  raw_data_inout ,  manual_ot_hr ,  full_night ,  half_night ,  out_time ,  early_going_mark ,  early_going_min ,  multiple_entries ,"
			+ "casetype ,  is_fixed ,  by_file_updated ,  location_id ,  emp_type ,  emp_json ,  atsumm_uid ,  file_name ,  row_id  FROM  tbl_attt_daily_daily  "
			+ "WHERE  id=:dailyId", nativeQuery = true)
	GetDailyDailyRecord getDailyDailyRecordByDailyId(@Param("dailyId") int dailyId);
	
	

	@Query(value = "SELECT  id ,  company_id ,  emp_code ,  emp_name ,  att_date ,  att_status ,  lv_sumup_id ,  CONCAT(FLOOR(working_hrs/60),'.',LPAD(MOD(working_hrs,60), 2, '0')) as "
			+ "working_hrs ,  in_time ,  rec_status ,  login_name ,  login_time ,  import_date , Day(att_date) as  cmp_code ,  emp_id ,  CONCAT(FLOOR(ot_hr/60),'.',LPAD(MOD(ot_hr,60), 2, '0')) as ot_hr ,"
			+ "current_shiftid ,  late_mark ,  late_min ,  reason ,  current_shiftname ,  freeze_by_supervisor ,  comments_supervisor ,  get_pass_used_count ,  get_pass_used_hour ,"
			+ "get_pass_used_hour_reason ,  raw_data_inout ,  manual_ot_hr ,  full_night ,  half_night ,  out_time ,  early_going_mark ,  early_going_min ,  multiple_entries ,"
			+ "casetype ,  is_fixed ,  by_file_updated ,  location_id ,   Day(att_date) as emp_type ,  emp_json ,  atsumm_uid ,  file_name ,  row_id  FROM  tbl_attt_daily_daily  "
			+ "WHERE att_date between :fromDate and :toDate and company_id=:companyId", nativeQuery = true)
	List<GetDailyDailyRecord> summaryDailyAttendanceListAll1(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("companyId") int companyId);

	
	@Query(value = "SELECT\n" + 
			"        id ,\n" + 
			"        company_id ,\n" + 
			"        emp_code ,\n" + 
			"        emp_name ,\n" + 
			"        att_date ,\n" + 
			"        att_status ,\n" + 
			"        lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR(working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"        in_time ,\n" + 
			"        rec_status ,\n" + 
			"        login_name ,\n" + 
			"        login_time ,\n" + 
			"        import_date ,\n" + 
			"        cmp_code ,\n" + 
			"        emp_id ,\n" + 
			"        CONCAT(FLOOR(ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(ot_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as ot_hr ,\n" + 
			"        current_shiftid ,\n" + 
			"        late_mark ,\n" + 
			"        late_min ,\n" + 
			"        reason ,\n" + 
			"        current_shiftname ,\n" + 
			"        freeze_by_supervisor ,\n" + 
			"        comments_supervisor ,\n" + 
			"        get_pass_used_count ,\n" + 
			"        get_pass_used_hour ,\n" + 
			"        get_pass_used_hour_reason ,\n" + 
			"        raw_data_inout ,\n" + 
			"        manual_ot_hr ,\n" + 
			"        full_night ,\n" + 
			"        half_night ,\n" + 
			"        out_time ,\n" + 
			"        early_going_mark ,\n" + 
			"        early_going_min ,\n" + 
			"        multiple_entries ,\n" + 
			"        casetype ,\n" + 
			"        is_fixed ,\n" + 
			"        by_file_updated ,\n" + 
			"        location_id ,\n" + 
			"        emp_type ,\n" + 
			"        emp_json ,\n" + 
			"        atsumm_uid ,\n" + 
			"        file_name ,\n" + 
			"        row_id       \n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily       \n" + 
			"    WHERE\n" + 
			"        is_fixed=0          \n" + 
			"        and rec_status='o'         \n" + 
			"        and freeze_by_supervisor=0         \n" + 
			"        and by_file_updated=1 and att_date=:date and emp_id in (select emp_id from leave_authority where ini_auth_emp_id =:empId) and ot_hr>0", nativeQuery = true)
	List<GetDailyDailyRecord> getDailyDailyRecordForOtApproval(@Param("date") String date,@Param("empId") int empId);

	
	@Query(value = "SELECT\n" + 
			"        id ,\n" + 
			"        company_id ,\n" + 
			"        emp_code ,\n" + 
			"        emp_name ,\n" + 
			"        att_date ,\n" + 
			"        att_status ,\n" + 
			"        lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR(working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"        in_time ,\n" + 
			"        rec_status ,\n" + 
			"        login_name ,\n" + 
			"        login_time ,\n" + 
			"        import_date ,\n" + 
			"        cmp_code ,\n" + 
			"        emp_id ,\n" + 
			"        CONCAT(FLOOR(ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(ot_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as ot_hr ,\n" + 
			"        current_shiftid ,\n" + 
			"        late_mark ,\n" + 
			"        late_min ,\n" + 
			"        reason ,\n" + 
			"        current_shiftname ,\n" + 
			"        freeze_by_supervisor ,\n" + 
			"        comments_supervisor ,\n" + 
			"        get_pass_used_count ,\n" + 
			"        get_pass_used_hour ,\n" + 
			"        get_pass_used_hour_reason ,\n" + 
			"        raw_data_inout ,\n" + 
			"        manual_ot_hr ,\n" + 
			"        full_night ,\n" + 
			"        half_night ,\n" + 
			"        out_time ,\n" + 
			"        early_going_mark ,\n" + 
			"        early_going_min ,\n" + 
			"        multiple_entries ,\n" + 
			"        casetype ,\n" + 
			"        is_fixed ,\n" + 
			"        by_file_updated ,\n" + 
			"        location_id ,\n" + 
			"        emp_type ,\n" + 
			"        emp_json ,\n" + 
			"        atsumm_uid ,\n" + 
			"        file_name ,\n" + 
			"        row_id       \n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily       \n" + 
			"    WHERE\n" + 
			"        is_fixed=0          \n" + 
			"        and rec_status='o'         \n" + 
			"        and freeze_by_supervisor=1         \n" + 
			"        and by_file_updated=1 and att_date=:date and emp_id in (select emp_id from leave_authority where fin_auth_emp_id =:empId) and ot_hr>0", nativeQuery = true)
	List<GetDailyDailyRecord> getDailyDailyRecordForFinalOtApproval(@Param("date") String date,@Param("empId") int empId);

}
