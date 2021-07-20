package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SummaryDailyAttendance;

public interface SummaryDailyAttendanceRepository extends JpaRepository<SummaryDailyAttendance, Integer> {

	@Query(value = "select * from tbl_attt_summary_daily where month=:month and year=:year and rec_status='o'", nativeQuery = true)
	List<SummaryDailyAttendance> summaryDailyAttendanceList(@Param("month") int month, @Param("year") int year);

	/*@Query(value = "select * from tbl_attt_summary_daily where month=:month and year=:year ", nativeQuery = true)*/
	/*
	 * @Query(value = "select\n" + "        sd.* \n" + "    from\n" +
	 * "         tbl_attt_summary_daily sd \n" + "    where\n" +
	 * "        sd.month=:month\n" + "        and sd.year=:year\n" +
	 * "        and   sd.total_days_inmonth=(\n" + "            SELECT\n" +
	 * "                count(*) \n" + "            FROM\n" +
	 * "                tbl_attt_daily_daily \n" + "            where\n" +
	 * "                YEAR(att_date) =sd.year \n" +
	 * "                and MONTH(att_date) =sd.month \n" +
	 * "                and sd.emp_id=tbl_attt_daily_daily.emp_id \n" +
	 * "                and is_fixed=0 \n" + "                and rec_status='o'\n"
	 * + "        )", nativeQuery = true)
	 */
	@Query(value = "select a.* from (select sd.* from tbl_attt_summary_daily sd, m_employees e where sd.month=:month and sd.year=:year and "
			+ "sd.emp_id=e.emp_id and location_id!=8 group by emp_id) a left join ( SELECT count('') as count_att, emp_id FROM "
			+ "tbl_attt_daily_daily where YEAR(att_date) =:year and MONTH(att_date) =:month and is_fixed=0 and rec_status='o' group by emp_id ) "
			+ "b on b.emp_id=a.emp_id where b.count_att>0", nativeQuery = true)
	List<SummaryDailyAttendance> summaryDailyAttendanceListAll(@Param("month") int month, @Param("year") int year);

	/*@Query(value = "select * from tbl_attt_summary_daily where month=:month and year=:year and emp_id=:empId", nativeQuery = true)*/
	@Query(value = "select\n" + 
			"        sd.* \n" + 
			"    from\n" + 
			"         tbl_attt_summary_daily sd \n" + 
			"    where\n" + 
			"        sd.month=:month\n" + 
			"        and sd.year=:year\n" + 
			"        and   sd.total_days_inmonth=(\n" + 
			"            SELECT\n" + 
			"                count(*) \n" + 
			"            FROM\n" + 
			"                tbl_attt_daily_daily \n" + 
			"            where\n" + 
			"                YEAR(att_date) =sd.year \n" + 
			"                and MONTH(att_date) =sd.month \n" + 
			"                and sd.emp_id=tbl_attt_daily_daily.emp_id \n" + 
			"                and is_fixed=0 \n" + 
			"                and rec_status='o'\n" + 
			"        ) and sd.emp_id=:empId", nativeQuery = true)
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
	
	@Query(value = "SELECT\n" + 
			"        d.* \n" + 
			"    FROM\n" + 
			"        `tbl_attt_summary_daily` d,\n" + 
			"        m_employees e\n" + 
			"    WHERE\n" + 
			"          d.month BETWEEN :fmonth AND :lmonth \n" + 
			"        AND d.year BETWEEN :fyear AND :lyear\n" + 
			"        and e.emp_id=d.emp_id\n" + 
			"        and e.location_id=:locId and e.depart_id in (:deptIds)", nativeQuery = true)
	List<SummaryDailyAttendance> findAllByCompanyId(@Param("locId") int locId, @Param("fmonth") String fmonth, @Param("fyear") String fyear,
			@Param("lmonth") String lmonth, @Param("lyear") String lyear,@Param("deptIds") List<Integer> deptIds);

	@Query(value = " select a.* from\n"
			+ "        (select sd.*      \n"
			+ "    from\n"
			+ "        tbl_attt_summary_daily sd      \n"
			+ "    where\n"
			+ "        sd.month=:month        \n"
			+ "        and sd.year=:year and emp_code in (:empCodes)  \n"
			+ "        group by emp_id) a\n"
			+ "        left join \n"
			+ "        (SELECT\n"
			+ "                count('') as count_att,\n"
			+ "                emp_id\n"
			+ "            FROM\n"
			+ "                tbl_attt_daily_daily              \n"
			+ "            where\n"
			+ "                YEAR(att_date) =:year                 \n"
			+ "                and MONTH(att_date) =:month                  \n"
			+ "                and is_fixed=0                  \n"
			+ "                and rec_status='o'\n"
			+ "        group by emp_id) b on b.emp_id=a.emp_id where b.count_att>0", nativeQuery = true)
	List<SummaryDailyAttendance> summaryDailyAttendanceListByEmpIds(int month, int year, List<String> empCodes);
	
}
