package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.DailyDailyInformation;

public interface DailyDailyInformationRepository extends JpaRepository<DailyDailyInformation, Integer>{

	/*@Query(value = "select\n" + 
			"        uuid() as uuid,\n" + 
			"        dl.emp_id,\n" + 
			"        count(*) as daycount,\n" + 
			"        dl.lv_sumup_id,\n" + 
			"        sp.name_sd,\n" + 
			"        sum(dl.working_hrs) as working_min,\n" + 
			"        sum(ot_hr) as ot_min,\n" + 
			"        sum(late_mark) as late_mark,\n" + 
			"        sum(late_min) as  late_min,sum(full_night) as  full_night,sum(atsumm_uid) as  mark_compoff,\n" + 
			"        es.sal_basis\n" + 
			"    from\n" + 
			"        tbl_attt_daily_daily dl,\n" + 
			"        tbl_lvm_sumup sp,\n" + 
			"        tbl_emp_salary_info es\n" + 
			"    where\n" + 
			"        dl.att_date between :fromDate and :toDate \n" + 
			"        and dl.lv_sumup_id=sp.id\n" + 
			"        and es.emp_id=dl.emp_id\n" + 
			"    group by\n" + 
			"        dl.emp_id,\n" + 
			"        dl.lv_sumup_id ", nativeQuery = true)*/
	/*
	 * @Query(value = "select\n" + "        uuid() as uuid,\n" +
	 * "        dl.emp_id,\n" + "        count(*) as daycount,\n" +
	 * "        dl.lv_sumup_id,\n" + "        sp.name_sd,\n" +
	 * "        sum(dl.working_hrs) as working_min,\n" +
	 * "        ifnull((select sum(tbl_attt_daily_daily.ot_hr) from tbl_attt_daily_daily where tbl_attt_daily_daily.emp_id=dl.emp_id and "
	 * +
	 * "tbl_attt_daily_daily.freeze_by_supervisor=2 and dl.lv_sumup_id=tbl_attt_daily_daily.lv_sumup_id and tbl_attt_daily_daily.att_date between :fromDate and :toDate),0)  as ot_min,\n"
	 * + "        sum(late_mark) as late_mark,\n" +
	 * "        sum(late_min) as  late_min,sum(full_night) as  full_night,sum(atsumm_uid) as  mark_compoff,\n"
	 * + "        es.sal_basis\n" + "    from\n" +
	 * "        tbl_attt_daily_daily dl,\n" + "        tbl_lvm_sumup sp,\n" +
	 * "        tbl_emp_salary_info es\n" + "    where\n" +
	 * "        dl.att_date between :fromDate and :toDate \n" +
	 * "        and dl.lv_sumup_id=sp.id\n" +
	 * "        and es.emp_id=dl.emp_id and dl.is_fixed=0 and dl.rec_status='o'\n" +
	 * "    group by\n" + "        dl.emp_id,\n" + "        dl.lv_sumup_id ",
	 * nativeQuery = true)
	 */
	
	@Query(value = "select a.*,ifnull(b.ot_min,0)  as ot_min from (\n"
			+ "        select uuid() as uuid,\n"
			+ "        dl.emp_id,\n"
			+ "        count(*) as daycount,\n"
			+ "        dl.lv_sumup_id,\n"
			+ "        sp.name_sd,\n"
			+ "        sum(dl.working_hrs) as working_min, \n"
			+ "        sum(late_mark) as late_mark,\n"
			+ "        sum(late_min) as  late_min,\n"
			+ "        sum(full_night) as  full_night,\n"
			+ "        sum(atsumm_uid) as  mark_compoff,\n"
			+ "        es.sal_basis     \n"
			+ "    from\n"
			+ "        tbl_attt_daily_daily dl,\n"
			+ "        tbl_lvm_sumup sp,\n"
			+ "        tbl_emp_salary_info es     \n"
			+ "    where\n"
			+ "        dl.att_date between :fromDate and :toDate          \n"
			+ "        and dl.lv_sumup_id=sp.id         \n"
			+ "        and es.emp_id=dl.emp_id \n"
			+ "        and dl.is_fixed=0 \n"
			+ "        and dl.rec_status='o'     \n"
			+ "    group by\n"
			+ "        dl.emp_id,\n"
			+ "        dl.lv_sumup_id) a\n"
			+ "    left join (\n"
			+ "    \n"
			+ "    select\n"
			+ "            sum(tbl_attt_daily_daily.ot_hr) as ot_min,\n"
			+ "            tbl_attt_daily_daily.emp_id,\n"
			+ "            tbl_attt_daily_daily.lv_sumup_id\n"
			+ "        from\n"
			+ "            tbl_attt_daily_daily \n"
			+ "        where \n"
			+ "             tbl_attt_daily_daily.freeze_by_supervisor=2  \n"
			+ "            and tbl_attt_daily_daily.att_date between :fromDate and :toDate\n"
			+ "        group by tbl_attt_daily_daily.emp_id,tbl_attt_daily_daily.lv_sumup_id\n"
			+ "    ) b on b.emp_id=a.emp_id and b.lv_sumup_id=a.lv_sumup_id", nativeQuery = true)
	List<DailyDailyInformation> dailyDailyInformationList(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	/*@Query(value = "select\n" + 
			"        uuid() as uuid,\n" + 
			"        dl.emp_id,\n" + 
			"        count(*) as daycount,\n" + 
			"        dl.lv_sumup_id,\n" + 
			"        sp.name_sd,\n" + 
			"        sum(dl.working_hrs) as working_min,\n" + 
			"        sum(ot_hr) as ot_min,\n" + 
			"        sum(late_mark) as late_mark,\n" + 
			"        sum(late_min) as  late_min,sum(full_night) as  full_night,sum(atsumm_uid) as  mark_compoff,\n" + 
			"        es.sal_basis\n" + 
			"    from\n" + 
			"        tbl_attt_daily_daily dl,\n" + 
			"        tbl_lvm_sumup sp,\n" + 
			"        tbl_emp_salary_info es\n" + 
			"    where\n" + 
			"        dl.att_date between :fromDate and :toDate \n" + 
			"        and dl.lv_sumup_id=sp.id\n" + 
			"        and es.emp_id=dl.emp_id and dl.emp_id=:empId\n" + 
			"    group by\n" + 
			"        dl.emp_id,\n" + 
			"        dl.lv_sumup_id ", nativeQuery = true)*/
	
	@Query(value = "select\n" + 
			"        uuid() as uuid,\n" + 
			"        dl.emp_id,\n" + 
			"        count(*) as daycount,\n" + 
			"        dl.lv_sumup_id,\n" + 
			"        sp.name_sd,\n" + 
			"        sum(dl.working_hrs) as working_min,\n" + 
			"        ifnull((select sum(tbl_attt_daily_daily.ot_hr) from tbl_attt_daily_daily where tbl_attt_daily_daily.emp_id=dl.emp_id and "
			+ "tbl_attt_daily_daily.freeze_by_supervisor=2 and dl.lv_sumup_id=tbl_attt_daily_daily.lv_sumup_id and tbl_attt_daily_daily.att_date between :fromDate and :toDate),0)  as ot_min,\n" + 
			"        sum(late_mark) as late_mark,\n" + 
			"        sum(late_min) as  late_min,sum(full_night) as  full_night,sum(atsumm_uid) as  mark_compoff,\n" + 
			"        es.sal_basis\n" + 
			"    from\n" + 
			"        tbl_attt_daily_daily dl,\n" + 
			"        tbl_lvm_sumup sp,\n" + 
			"        tbl_emp_salary_info es\n" + 
			"    where\n" + 
			"        dl.att_date between :fromDate and :toDate \n" + 
			"        and dl.lv_sumup_id=sp.id\n" + 
			"        and es.emp_id=dl.emp_id and dl.emp_id=:empId and dl.is_fixed=0 and dl.rec_status='o'\n" + 
			"    group by\n" + 
			"        dl.emp_id,\n" + 
			"        dl.lv_sumup_id ", nativeQuery = true)
	List<DailyDailyInformation> dailyDailyInformationList(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("empId") int empId);

}
