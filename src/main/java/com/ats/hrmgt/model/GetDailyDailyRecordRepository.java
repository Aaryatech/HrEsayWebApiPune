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
			+ "casetype ,  is_fixed ,  by_file_updated ,  location_id ,  emp_type ,  emp_json ,  atsumm_uid ,  file_name ,  row_id,atts_sd_show  FROM  tbl_attt_daily_daily  "
			+ "WHERE att_date between :fromDate and :toDate and emp_id=:empId", nativeQuery = true)
	List<GetDailyDailyRecord> summaryDailyAttendanceListAll(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("empId") int empId);

	@Query(value = "SELECT  id ,  company_id ,  emp_code ,  emp_name ,  att_date ,  att_status ,  lv_sumup_id ,  CONCAT(FLOOR(working_hrs/60),':',LPAD(MOD(working_hrs,60), 2, '0')) as "
			+ "working_hrs ,  in_time ,  rec_status ,  login_name ,  login_time ,  import_date ,  cmp_code ,  emp_id ,  CONCAT(FLOOR(ot_hr/60),':',LPAD(MOD(ot_hr,60), 2, '0')) as ot_hr ,"
			+ "current_shiftid ,  late_mark ,  late_min ,  reason ,  current_shiftname ,  freeze_by_supervisor ,  comments_supervisor ,  get_pass_used_count ,  get_pass_used_hour ,"
			+ "get_pass_used_hour_reason ,  raw_data_inout ,  manual_ot_hr ,  full_night ,  half_night ,  out_time ,  early_going_mark ,  early_going_min ,  multiple_entries ,"
			+ "casetype ,  is_fixed ,  by_file_updated ,  location_id ,  emp_type ,  emp_json ,  atsumm_uid ,  file_name ,  row_id,atts_sd_show  FROM  tbl_attt_daily_daily  "
			+ "WHERE  id=:dailyId", nativeQuery = true)
	GetDailyDailyRecord getDailyDailyRecordByDailyId(@Param("dailyId") int dailyId);
	
	

	@Query(value = "SELECT\n" + 
			"        d.id ,\n" + 
			"        d.company_id ,\n" + 
			"        d.emp_code ,\n" + 
			"        d.emp_name ,\n" + 
			"        d.att_date ,\n" + 
			"        d.att_status ,\n" + 
			"        d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR(d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"        d.in_time ,\n" + 
			"        d.rec_status ,\n" + 
			"        d.login_name ,\n" + 
			"        d.login_time ,\n" + 
			"        d.import_date ,\n" + 
			"        Day(d.att_date) as  cmp_code ,\n" + 
			"        d.emp_id ,\n" + 
			"        CONCAT(FLOOR(d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.ot_hr,\n" + 
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
			"        Day(d.att_date) as emp_type ,\n" + 
			"        d.emp_json ,\n" + 
			"        d.atsumm_uid ,\n" + 
			"        d.file_name ,\n" + 
			"        d.row_id,\n" + 
			"        d.atts_sd_show  \n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily d,\n" + 
			"        m_employees e\n" + 
			"    WHERE\n" + 
			"        d.att_date between :fromDate and :toDate \n" + 
			"        and d.emp_id=e.emp_id\n" + 
			"        and e.location_id=:locId", nativeQuery = true)
	List<GetDailyDailyRecord> summaryDailyAttendanceListAll1(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("locId")  int locId);

	
	@Query(value = "SELECT\n" + 
			"        dl.id ,\n" + 
			"        dl.company_id ,\n" + 
			"        dl.emp_code ,\n" + 
			"        dl.emp_name ,\n" + 
			"        dl.att_date ,\n" + 
			"        dl.att_status ,\n" + 
			"        dl.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR(dl.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(dl.working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"        dl.in_time ,\n" + 
			"        dl.rec_status ,\n" + 
			"        dl.login_name ,\n" + 
			"        dl.login_time ,\n" + 
			"        dl.import_date ,\n" + 
			"        dl.cmp_code ,\n" + 
			"        dl.emp_id ,\n" + 
			"        CONCAT(FLOOR(dl.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(dl.ot_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as ot_hr ,\n" + 
			"        dl.current_shiftid ,\n" + 
			"        dl.late_mark ,\n" + 
			"        dl.late_min ,\n" + 
			"        dl.reason ,\n" + 
			"        dl.current_shiftname ,\n" + 
			"        dl.freeze_by_supervisor ,\n" + 
			"        dl.comments_supervisor ,\n" + 
			"        dl.get_pass_used_count ,\n" + 
			"        dl.get_pass_used_hour ,\n" + 
			"        dl.get_pass_used_hour_reason ,\n" + 
			"        dl.raw_data_inout ,\n" + 
			"        dl.manual_ot_hr ,\n" + 
			"        dl.full_night ,\n" + 
			"        dl.half_night ,\n" + 
			"        dl.out_time ,\n" + 
			"        dl.early_going_mark ,\n" + 
			"        dl.early_going_min ,\n" + 
			"        dl.multiple_entries ,\n" + 
			"        dl.casetype ,\n" + 
			"        dl.is_fixed ,\n" + 
			"        dl.by_file_updated ,\n" + 
			"        dl.location_id ,\n" + 
			"        dl.emp_type ,\n" + 
			"        dl.emp_json ,\n" + 
			"        dl.atsumm_uid ,\n" + 
			"        dl.file_name ,\n" + 
			"        dl.row_id,dl.atts_sd_show            \n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily dl,m_employees e,tbl_mst_emp_types et            \n" + 
			"    WHERE\n" + 
			"        dl.is_fixed=0                   \n" + 
			"        and dl.rec_status='o'                  \n" + 
			"        and dl.freeze_by_supervisor=0                  \n" + 
			"        and dl.by_file_updated=1 \n" + 
			"        and dl.att_date=:date \n" + 
			"        and dl.emp_id in (\n" + 
			"            select\n" + 
			"                emp_id \n" + 
			"            from\n" + 
			"                leave_authority \n" + 
			"            where\n" + 
			"                ini_auth_emp_id =:empId\n" + 
			"        ) \n" + 
			"        and ot_hr>0 and e.emp_id=dl.emp_id and e.emp_type=et.emp_type_id and et.ot_applicable='Yes'", nativeQuery = true)
	List<GetDailyDailyRecord> getDailyDailyRecordForOtApproval(@Param("date") String date,@Param("empId") int empId);

	
	@Query(value = "SELECT\n" + 
			"        dl.id ,\n" + 
			"        dl.company_id ,\n" + 
			"        dl.emp_code ,\n" + 
			"        dl.emp_name ,\n" + 
			"        dl.att_date ,\n" + 
			"        dl.att_status ,\n" + 
			"        dl.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR(dl.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(dl.working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"        dl.in_time ,\n" + 
			"        dl.rec_status ,\n" + 
			"        dl.login_name ,\n" + 
			"        dl.login_time ,\n" + 
			"        dl.import_date ,\n" + 
			"        dl.cmp_code ,\n" + 
			"        dl.emp_id ,\n" + 
			"        CONCAT(FLOOR(dl.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(dl.ot_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as ot_hr ,\n" + 
			"        dl.current_shiftid ,\n" + 
			"        dl.late_mark ,\n" + 
			"        dl.late_min ,\n" + 
			"        dl.reason ,\n" + 
			"        dl.current_shiftname ,\n" + 
			"        dl.freeze_by_supervisor ,\n" + 
			"        dl.comments_supervisor ,\n" + 
			"        dl.get_pass_used_count ,\n" + 
			"        dl.get_pass_used_hour ,\n" + 
			"        dl.get_pass_used_hour_reason ,\n" + 
			"        dl.raw_data_inout ,\n" + 
			"        dl.manual_ot_hr ,\n" + 
			"        dl.full_night ,\n" + 
			"        dl.half_night ,\n" + 
			"        dl.out_time ,\n" + 
			"        dl.early_going_mark ,\n" + 
			"        dl.early_going_min ,\n" + 
			"        dl.multiple_entries ,\n" + 
			"        dl.casetype ,\n" + 
			"        dl.is_fixed ,\n" + 
			"        dl.by_file_updated ,\n" + 
			"        dl.location_id ,\n" + 
			"        dl.emp_type ,\n" + 
			"        dl.emp_json ,\n" + 
			"        dl.atsumm_uid ,\n" + 
			"        dl.file_name ,\n" + 
			"        dl.row_id,dl.atts_sd_show            \n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily dl,m_employees e,tbl_mst_emp_types et            \n" + 
			"    WHERE\n" + 
			"        dl.is_fixed=0                   \n" + 
			"        and dl.rec_status='o'                  \n" + 
			"        and dl.freeze_by_supervisor=1                  \n" + 
			"        and dl.by_file_updated=1 \n" + 
			"        and dl.att_date=:date \n" + 
			"        and dl.emp_id in (\n" + 
			"            select\n" + 
			"                emp_id \n" + 
			"            from\n" + 
			"                leave_authority \n" + 
			"            where\n" + 
			"                fin_auth_emp_id =:empId\n" + 
			"        ) \n" + 
			"        and ot_hr>0 and e.emp_id=dl.emp_id and e.emp_type=et.emp_type_id and et.ot_applicable='Yes'", nativeQuery = true)
	List<GetDailyDailyRecord> getDailyDailyRecordForFinalOtApproval(@Param("date") String date,@Param("empId") int empId);

	/*@Query(value = " SELECT\n" + 
			"        d.id ,\n" + 
			"        d.company_id ,\n" + 
			"        d.emp_code ,\n" + 
			"        d.emp_name ,\n" + 
			"        d.att_date ,\n" + 
			"        d.att_status ,\n" + 
			"        d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR(d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.working_hrs,\n" + 
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
			"        CONCAT(FLOOR(d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.ot_hr,\n" + 
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
			"        et.wh_work as emp_json ,\n" + 
			"        d.atsumm_uid ,\n" + 
			"        d.file_name ,\n" + 
			"        d.row_id,\n" + 
			"        d.atts_sd_show  \n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily d,\n" + 
			"        m_employees e,\n" + 
			"        m_department dep,\n" + 
			"        tbl_mst_emp_types et\n" + 
			"    WHERE\n" + 
			"        d.att_date between :date and :date \n" + 
			"        and d.emp_id=e.emp_id         \n" + 
			"        and e.depart_id=dep.depart_id\n" + 
			"        and e.emp_type=et.emp_type_id", nativeQuery = true)*/
	
	@Query(value = "SELECT\n" + 
			"        d.id ,\n" + 
			"        d.company_id ,\n" + 
			"        d.emp_code ,\n" + 
			"        d.emp_name ,\n" + 
			"        d.att_date ,\n" + 
			"        d.att_status ,\n" + 
			"        d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR(d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.working_hrs,\n" + 
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
			"        CONCAT(FLOOR(d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.ot_hr,\n" + 
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
			"        et.wh_work as emp_json ,\n" + 
			"        d.atsumm_uid ,\n" + 
			"        ifnull((select ifnull(( select CONCAT(m_employees.first_name, ' ', m_employees.surname) from m_employees where la.ini_auth_emp_id=m_employees.emp_id),'-') as name from \n" + 
			"        leave_authority la where la.emp_id=e.emp_id  ), '-') as file_name ,\n" + 
			"        d.row_id,\n" + 
			"        d.atts_sd_show      \n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily d,\n" + 
			"        m_employees e,\n" + 
			"        m_department dep,\n" + 
			"        tbl_mst_emp_types et      \n" + 
			"    WHERE\n" + 
			"        d.att_date between :date and :date                   \n" + 
			"        and d.emp_id=e.emp_id                           \n" + 
			"        and e.depart_id=dep.depart_id                  \n" + 
			"        and e.emp_type=et.emp_type_id", nativeQuery = true)
	List<GetDailyDailyRecord> getDailyDailyRecordForHrByDate(@Param("date") String date);

	@Query(value = " SELECT\n" + 
			"        d.id ,\n" + 
			"        d.company_id ,\n" + 
			"        d.emp_code ,\n" + 
			"        d.emp_name ,\n" + 
			"        d.att_date ,\n" + 
			"        d.att_status ,\n" + 
			"        d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR(d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.working_hrs,\n" + 
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
			"        CONCAT(FLOOR(d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.ot_hr,\n" + 
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
			"        et.wh_work as emp_json ,\n" + 
			"        d.atsumm_uid ,\n" + 
			"        d.file_name ,\n" + 
			"        d.row_id,\n" + 
			"        d.atts_sd_show  \n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily d,\n" + 
			"        m_employees e,\n" + 
			"        m_department dep,\n" + 
			"        tbl_mst_emp_types et\n" + 
			"    WHERE\n" + 
			"        d.att_date between :date and :date \n" + 
			"        and d.emp_id=e.emp_id         \n" + 
			"        and e.depart_id=dep.depart_id\n" + 
			"        and e.emp_type=et.emp_type_id and e.emp_id in (:empIds)", nativeQuery = true)
	List<GetDailyDailyRecord> getDailyDailyRecordForRoaster(@Param("date")String date, @Param("empIds")List<Integer> empIds);

	@Query(value = "SELECT\n" + 
			"        d.id ,\n" + 
			"        d.company_id ,\n" + 
			"        d.emp_code ,\n" + 
			"        d.emp_name ,\n" + 
			"        d.att_date ,\n" + 
			"        d.att_status ,\n" + 
			"        d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR(d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.working_hrs,\n" + 
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
			"        CONCAT(FLOOR(d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.ot_hr,\n" + 
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
			"        et.wh_work as emp_json ,\n" + 
			"        d.atsumm_uid ,\n" + 
			"        ifnull((select ifnull(( select CONCAT(m_employees.first_name, ' ', m_employees.surname) from m_employees where la.ini_auth_emp_id=m_employees.emp_id),'-') as name from \n" + 
			"        leave_authority la where la.emp_id=e.emp_id  ), '-') as file_name ,\n" + 
			"        e.notice_pay_amount as row_id,\n" + 
			"        d.atts_sd_show      \n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily d,\n" + 
			"        m_employees e,\n" + 
			"        m_department dep,\n" + 
			"        tbl_mst_emp_types et      \n" + 
			"    WHERE\n" + 
			"        d.att_date between :date and :date                   \n" + 
			"        and d.emp_id=e.emp_id                           \n" + 
			"        and e.depart_id=dep.depart_id                  \n" + 
			"        and e.emp_type=et.emp_type_id and e.location_id in (:locId) order by e.notice_pay_amount asc,d.emp_name asc", nativeQuery = true)
	List<GetDailyDailyRecord> getDailyDailyRecordForHrByDateLocId(@Param("date")String date,@Param("locId") List<Integer> locId);

	@Query(value = "SELECT\n" + 
			"        d.id ,\n" + 
			"        d.company_id ,\n" + 
			"        d.emp_code ,\n" + 
			"        d.emp_name ,\n" + 
			"        d.att_date ,\n" + 
			"        d.att_status ,\n" + 
			"        d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR(d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"        d.in_time ,\n" + 
			"        d.rec_status ,\n" + 
			"        d.login_name ,\n" + 
			"        d.login_time ,\n" + 
			"        d.import_date ,\n" + 
			"        Day(d.att_date) as  cmp_code ,\n" + 
			"        d.emp_id ,\n" + 
			"        CONCAT(FLOOR(d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(d.ot_hr,\n" + 
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
			"        Day(d.att_date) as emp_type ,\n" + 
			"        d.emp_json ,\n" + 
			"        d.atsumm_uid ,\n" + 
			"        d.file_name ,\n" + 
			"        d.row_id,\n" + 
			"        d.atts_sd_show  \n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily d,\n" + 
			"        m_employees e\n" + 
			"    WHERE\n" + 
			"        d.att_date between :fromDate and :toDate \n" + 
			"        and d.emp_id=e.emp_id\n" + 
			"        and e.location_id=:locId and e.depart_id in (:deptIds)", nativeQuery = true)
	List<GetDailyDailyRecord> summaryDailyAttendanceListAlldept(String fromDate, String toDate, int locId,
			List<Integer> deptIds); 

}
