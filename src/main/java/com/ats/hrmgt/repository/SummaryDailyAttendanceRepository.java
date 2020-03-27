package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SummaryDailyAttendance;

public interface SummaryDailyAttendanceRepository extends JpaRepository<SummaryDailyAttendance, Integer> {

	@Query(value = "select * from tbl_attt_summary_daily where month=:month and year=:year and rec_status='o'", nativeQuery = true)
	List<SummaryDailyAttendance> summaryDailyAttendanceList(@Param("month") int month, @Param("year") int year);

	@Query(value = "select * from tbl_attt_summary_daily where month=:month and year=:year ", nativeQuery = true)
	List<SummaryDailyAttendance> summaryDailyAttendanceListAll(@Param("month") int month, @Param("year") int year);

	@Query(value = "select * from tbl_attt_summary_daily where month=:month and year=:year and emp_id=:empId", nativeQuery = true)
	List<SummaryDailyAttendance> summaryDailyAttendanceList(@Param("month") int month, @Param("year") int year,  @Param("empId") int empId);

	
	
	
	@Query(value = "select * from tbl_attt_summary_daily where month>=:monthFrom  and year=:yearFrom AND month<=:monthTo  and year=:yearTo  and emp_id=:empId", nativeQuery = true)
	List<SummaryDailyAttendance> summaryDailyAttendanceList(@Param("empId") int empId,@Param("monthFrom") int monthFrom,@Param("monthTo") int monthTo,@Param("yearFrom") String yearFrom,@Param("yearTo") String yearTo);

	SummaryDailyAttendance findByCompanyIdAndEmpId(int companyId, int empId);
	
	
	List<SummaryDailyAttendance> findAllByCompanyIdAndEmpId(int companyId, int empId);
	
	
	@Query(value = "select * from tbl_attt_summary_daily where month=:month and year=:year and emp_id=:empId", nativeQuery = true)
	 SummaryDailyAttendance summaryDailyAttendanceList1(@Param("month") String month, @Param("year") String year,  @Param("empId") int empId);

	@Query(value = "SELECT * FROM `tbl_attt_summary_daily` WHERE emp_id=:empId AND company_id=:companyId AND month BETWEEN :fmonth AND :lmonth AND year BETWEEN :fyear AND :lyear", nativeQuery = true)
	List<SummaryDailyAttendance> findAllByCompanyIdAndEmpId(@Param("companyId") int companyId, @Param("empId") int empId, @Param("fmonth") String fmonth, @Param("fyear") String fyear,
			@Param("lmonth") String lmonth, @Param("lyear") String lyear);
	
	@Query(value = "SELECT * FROM `tbl_attt_summary_daily` WHERE company_id=:companyId AND month BETWEEN :fmonth AND :lmonth AND year BETWEEN :fyear AND :lyear", nativeQuery = true)
	List<SummaryDailyAttendance> findAllByCompanyId(@Param("companyId") int companyId, @Param("fmonth") String fmonth, @Param("fyear") String fyear,
			@Param("lmonth") String lmonth, @Param("lyear") String lyear);
	
}
