package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.EmpOtReg;

public interface EmpOtRegRepo extends JpaRepository<EmpOtReg, Integer> {
	
@Query(value="SELECT\n" + 
		"    UUID() AS id, atd.emp_id, atd.emp_code, atd.emp_name AS emp_name, desg.name AS designation, SUM(atd.tot_othr) AS ot_hr,\n" + 
		"    MONTHNAME(STR_TO_DATE(atd.month, '%m')) AS MONTH\n" + 
		"FROM\n" + 
		"    tbl_attt_summary_daily atd \n" + 
		"    inner join     m_employees emp on atd.emp_id  = emp.emp_id \n" + 
		"    inner join   m_designation desg on emp.designation_id  = desg.desig_id \n" + 
		"     inner join   tbl_mst_emp_types  emptype on emp.emp_type  = emptype.emp_type_id  and ot_applicable='Yes'\n" + 
		"WHERE\n" + 
		"         atd.year BETWEEN :year AND :toyear AND atd.month BETWEEN :month AND :tomonth AND emp.del_status = 1 AND atd.company_id=:companyId\n" + 
		"GROUP BY\n" + 
		"    atd.emp_id , atd.month\n" + 
		"ORDER BY\n" + 
		" atd.emp_id, atd.month",nativeQuery=true)
List<EmpOtReg> getEmpOtDetails(@Param("companyId") int companyId, @Param("month")String month,
		@Param("year") String year, @Param("tomonth") String tomonth, @Param("toyear") String toyear);
}

	/*	SELECT \n" + 
		"UUID() as id,\n" +
		"	 atd.emp_id,\n" + 
		"    atd.emp_code,\n" + 
		"    atd.emp_name AS emp_name,\n" + 
		"    desg.name AS designation,\n" + 
		"    SUM(atd.tot_othr) AS ot_hr,\n" + 
		"    MONTHNAME(STR_TO_DATE(atd.month, '%m')) AS month\n" + 
		"FROM \n" + 
		"	tbl_attt_summary_daily atd,\n" + 
		"    m_employees emp,\n" + 
		"    m_designation desg    \n" + 
		"WHERE \n" + 
		"	emp.emp_id=atd.emp_id AND\n" + 
		"    atd.company_id=:companyId AND\n" + 
		"    emp.designation_id=desg.desig_id AND\n" + 
		"      DATE_FORMAT(\n" + 
		"        CONCAT(atd.year, '-', atd.month, '-01'),\n" + 
		"        '%Y-%m-%d'\n" + 
		"    ) BETWEEN DATE_FORMAT(\n" + 
		"        CONCAT(:year, '-', :month, '-01'),\n" + 
		"        '%Y-%m-%d'\n" + 
		"    ) AND DATE_FORMAT(\n" + 
		"        CONCAT(:toyear, '-', :tomonth, '-31'),\n" + 
		"        '%Y-%m-%d'\n" + 
		"    ) AND emp.del_status=1 \n" + 
		" GROUP BY atd.emp_id\n" + 
		" ORDER BY atd.month*/
