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

	@Query(value = "select id ,\n" + 
			"         d.company_id ,\n" + 
			"         d.emp_code ,\n" + 
			"         d.emp_name ,\n" + 
			"         d.att_date ,\n" + 
			"         d.att_status ,\n" + 
			"         d.lv_sumup_id ,\n" + 
			"        CONCAT(FLOOR( d.working_hrs/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.working_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as working_hrs ,\n" + 
			"         d.in_time ,\n" + 
			"         d.rec_status ,\n" + 
			"         d.login_name ,\n" + 
			"         d.login_time ,\n" + 
			"         d.import_date ,\n" + 
			"         d.cmp_code ,\n" + 
			"         d.emp_id ,\n" + 
			"        CONCAT(FLOOR( d.ot_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD( d.ot_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as ot_hr ,\n" + 
			"         d.current_shiftid ,\n" + 
			"         d.late_mark ,\n" + 
			"         d.late_min ,\n" + 
			"         d.reason ,\n" + 
			"         d.current_shiftname ,\n" + 
			"         d.freeze_by_supervisor ,\n" + 
			"         d.comments_supervisor ,\n" + 
			"         d.get_pass_used_count ,\n" + 
			"         d.get_pass_used_hour ,\n" + 
			"         d.get_pass_used_hour_reason ,\n" + 
			"         d.raw_data_inout ,\n" + 
			"         d.manual_ot_hr ,\n" + 
			"         d.full_night ,\n" + 
			"         d.half_night ,\n" + 
			"         d.out_time ,\n" + 
			"         d.early_going_mark ,\n" + 
			"         d.early_going_min ,\n" + 
			"         d.multiple_entries ,\n" + 
			"         d.casetype ,\n" + 
			"         d.is_fixed ,\n" + 
			"         d.by_file_updated ,\n" + 
			"         d.location_id ,\n" + 
			"         d.emp_type ,\n" + 
			"         d.emp_json ,\n" + 
			"         d.atsumm_uid ,\n" + 
			"         d.file_name ,\n" + 
			"        d.row_id from tbl_attt_daily_daily d,m_employees e where d.att_date = :date and d.emp_id=e.emp_id and e.depart_id in (:departIds) and comments_supervisor=8", nativeQuery = true)
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
	
	@Query(value = "select id ,\n" + 
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
			"        row_id from tbl_attt_daily_daily where att_date between :fromDate and :toDate  and comments_supervisor=0", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceListForSecurityApprove(String fromDate, String toDate);

}
