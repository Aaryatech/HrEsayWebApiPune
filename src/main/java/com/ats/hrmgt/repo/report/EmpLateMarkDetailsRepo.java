package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.EmpLateMarkDetails;

public interface EmpLateMarkDetailsRepo extends JpaRepository<EmpLateMarkDetails, Integer> {
	

	@Query(value="SELECT\n" + 
			"    UUID() AS id, atd.emp_id, atd.emp_code, atd.emp_name AS emp_name, desg.name AS designation, atd.totlate_mins AS late_hr, MONTHNAME(STR_TO_DATE(atd.month, '%m')) AS MONTH\n" + 
			"FROM\n" + 
			"    tbl_attt_summary_daily atd,\n" + 
			"    m_employees emp,\n" + 
			"    m_designation desg\n" + 
			"WHERE\n" + 
			"    emp.emp_id = atd.emp_id AND atd.company_id = :companyId AND emp.designation_id = desg.desig_id AND atd.year BETWEEN :year AND :toyear AND atd.month BETWEEN :month AND :tomonth AND emp.del_status = 1\n" + 
			"ORDER BY\n" + 
			"    atd.month",nativeQuery=true)
	List<EmpLateMarkDetails> getEmpLateMarkSummaryReport(@Param("companyId")int companyId, @Param("month")String month,
			@Param("year") String year, @Param("tomonth") String tomonth, @Param("toyear") String toyear);
	
	@Query(value="SELECT\n" + 
			"        UUID() AS id,\n" + 
			"        atd.emp_id,\n" + 
			"        atd.emp_code,\n" + 
			"        atd.emp_name AS emp_name,\n" + 
			"        desg.name AS designation,\n" + 
			"        atd.late_min AS late_hr,\n" + 
			"        DATE_FORMAT(atd.att_date,'%d-%m-%Y') AS MONTH\n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily atd,\n" + 
			"        m_employees emp,\n" + 
			"        m_designation desg \n" + 
			"    WHERE\n" + 
			"        emp.emp_id = atd.emp_id \n" + 
			"        AND atd.company_id = :companyId\n" + 
			"        AND emp.designation_id = desg.desig_id \n" + 
			"        AND atd.att_date BETWEEN :fromDate AND :toDate AND emp.del_status = 1  and atd.late_mark=1\n" + 
			"    ORDER BY\n" + 
			"        atd.emp_id,atd.att_date",nativeQuery=true)
	List<EmpLateMarkDetails> getEmpLateMarkDetailReport(int companyId, String fromDate, String toDate);

	
	@Query(value="SELECT\n" + 
			"UUID() as id,\n" + 
			"    atd.emp_id,\n" + 
			"    atd.emp_code,\n" + 
			"    atd.emp_name AS emp_name,\n" + 
			"    desg.name AS designation,\n" + 
			"    atd.totlate_mins AS late_hr,\n" + 
			"    MONTHNAME(STR_TO_DATE(atd.month, '%m')) AS MONTH\n" + 
			"FROM\n" + 
			"    tbl_attt_summary_daily atd,\n" + 
			"    m_employees emp,\n" + 
			"    m_designation desg\n" + 
			"WHERE\n" + 
			"    emp.emp_id = atd.emp_id AND atd.emp_id=:empId AND emp.designation_id = desg.desig_id AND\n" + 
			"    DATE_FORMAT(\n" + 
			"        CONCAT(atd.year, '-', atd.month, '-01'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) BETWEEN DATE_FORMAT(\n" + 
			"        CONCAT(:year, '-', :month, '-01'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) AND DATE_FORMAT(\n" + 
			"        CONCAT(:toyear, '-', :tomonth, '-31'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) AND atd.totlate_days > 0 AND emp.del_status = 1\n" + 
			"ORDER BY\n" + 
			"    atd.month",nativeQuery=true)	

	List<EmpLateMarkDetails> getEmpLateMarkDetailReportByEmpId(@Param("month")String month,
			@Param("year") String year, @Param("tomonth") String tomonth,
			@Param("toyear") String toyear, @Param("empId") int empId);

	
}
