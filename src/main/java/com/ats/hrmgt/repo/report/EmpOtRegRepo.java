package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.EmpOtReg;

public interface EmpOtRegRepo extends JpaRepository<EmpOtReg, Integer> {
	
@Query(value=" SELECT\n" + 
		"        UUID() AS id,\n" + 
		"        atd.emp_id,\n" + 
		"        atd.emp_code,\n" + 
		"        atd.emp_name AS emp_name,\n" + 
		"        desg.name AS designation,\n" + 
		"        CONCAT(FLOOR(SUM(atd.tot_othr)/60),\n" + 
		"        ':',\n" + 
		"        LPAD(MOD(SUM(atd.tot_othr),\n" + 
		"        60),\n" + 
		"        2,\n" + 
		"        '0'))\n" + 
		"         AS ot_hr,\n" + 
		"        MONTHNAME(STR_TO_DATE(atd.month,\n" + 
		"        '%m')) AS MONTH,\n" + 
		"        0001-01-01 AS date,"
		+ "SUM(atd.tot_othr) as ot_min \n" + 
		"    FROM\n" + 
		"        tbl_attt_summary_daily atd      \n" + 
		"    inner join\n" + 
		"        m_employees emp \n" + 
		"            on atd.emp_id  = emp.emp_id and emp.location_id=:locId  and emp.depart_id in (:deptIds)    \n" + 
		"    inner join\n" + 
		"        m_designation desg \n" + 
		"            on emp.designation_id  = desg.desig_id       \n" + 
		"    inner join\n" + 
		"        tbl_mst_emp_types  emptype \n" + 
		"            on emp.emp_type  = emptype.emp_type_id " + 
		"    WHERE\n" + 
		"        atd.year BETWEEN :year AND :toyear \n" + 
		"        AND atd.month BETWEEN :month AND :tomonth " + 
		"    GROUP BY\n" + 
		"        atd.emp_id ,\n" + 
		"        atd.month \n" + 
		"    ORDER BY\n" + 
		"        atd.emp_id,\n" + 
		"        atd.month",nativeQuery=true)
List<EmpOtReg> getEmpOtSummary(@Param("locId") int locId, @Param("month")String month,
		@Param("year") String year, @Param("tomonth") String tomonth, @Param("toyear") String toyear, @Param("deptIds") List<Integer> deptIds);

@Query(value="SELECT\n" + 
		"        UUID() AS id,\n" + 
		"        atd.emp_id,\n" + 
		"        atd.emp_code,\n" + 
		"        atd.emp_name AS emp_name,\n" + 
		"        desg.name AS designation,\n" + 
		"        CONCAT(FLOOR(atd.ot_hr/60),\n" + 
		"        ':',\n" + 
		"        LPAD(MOD(atd.ot_hr,\n" + 
		"        60),\n" + 
		"        2,\n" + 
		"        '0'))   AS ot_hr ,\n" + 
		"        atd.att_date AS date,\n" + 
		"        'NA' AS MONTH,\n" + 
		"        atd.ot_hr as ot_min\n" + 
		"    FROM\n" + 
		"        tbl_attt_daily_daily  atd           \n" + 
		"    inner join\n" + 
		"        m_employees emp              \n" + 
		"            on atd.emp_id  = emp.emp_id and emp.location_id=:locId  and emp.depart_id in (:deptIds)        \n" + 
		"    inner join\n" + 
		"        m_designation desg              \n" + 
		"            on emp.designation_id  = desg.desig_id            \n" + 
		"    inner join\n" + 
		"        tbl_mst_emp_types  emptype              \n" + 
		"            on emp.emp_type  = emptype.emp_type_id      \n" + 
		"    WHERE\n" + 
		"        atd.att_date BETWEEN :fromDate  AND :toDate         \n" + 
		"        AND   atd.ot_hr>0   and atd.freeze_by_supervisor=2       \n" + 
		"    GROUP BY\n" + 
		"        atd.emp_id ,\n" + 
		"        atd.att_date      \n" + 
		"    ORDER BY\n" + 
		"        atd.emp_id,\n" + 
		"        atd.att_date",nativeQuery=true)
List<EmpOtReg> getEmpOtDetails(@Param("locId") int locId, @Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("deptIds") List<Integer> deptIds);


@Query(value="SELECT a.*,c.emp_name as MONTH from (\n" + 
		"        SELECT UUID() AS id,\n" + 
		"        atd.emp_id,\n" + 
		"        atd.emp_code,\n" + 
		"        atd.emp_name AS emp_name,\n" + 
		"        desg.name AS designation,\n" + 
		"        CONCAT(FLOOR(atd.ot_hr/60),\n" + 
		"        ':',\n" + 
		"        LPAD(MOD(atd.ot_hr,\n" + 
		"        60),\n" + 
		"        2,\n" + 
		"        '0'))   AS ot_hr ,\n" + 
		"        atd.att_date AS date, \n" + 
		"        atd.ot_hr as ot_min     \n" + 
		"    FROM\n" + 
		"        tbl_attt_daily_daily  atd                \n" + 
		"    inner join\n" + 
		"        m_employees emp                           \n" + 
		"            on atd.emp_id  = emp.emp_id \n" + 
		"            and emp.location_id=:locId  \n" + 
		"            and emp.depart_id in (:deptIds)             \n" + 
		"        inner join\n" + 
		"            m_designation desg                           \n" + 
		"                on emp.designation_id  = desg.desig_id                 \n" + 
		"        inner join\n" + 
		"            tbl_mst_emp_types  emptype                           \n" + 
		"                on emp.emp_type  = emptype.emp_type_id           \n" + 
		"        WHERE\n" + 
		"            atd.att_date BETWEEN :fromDate AND :toDate                 \n" + 
		"            AND   atd.ot_hr>0   \n" + 
		"            and atd.freeze_by_supervisor=:sts  \n" + 
		"            and ot_applicable='Yes'          \n" + 
		"        GROUP BY\n" + 
		"            atd.emp_id ,\n" + 
		"            atd.att_date           \n" + 
		"        ORDER BY\n" + 
		"            atd.emp_id,\n" + 
		"            atd.att_date) a\n" + 
		"    left join (\n" + 
		"    select emp_id,ini_auth_emp_id,fin_auth_emp_id from leave_authority ) b on b.emp_id=a.emp_id\n" + 
		"    left join (\n" + 
		"    select emp.emp_id, CONCAT(emp.first_name,' ',emp.surname) as emp_name from m_employees emp where emp.del_status=1) c on c.emp_id=b.ini_auth_emp_id",nativeQuery=true)
List<EmpOtReg> getEmpOtApprovalPending(int locId, String fromDate, String toDate, List<Integer> deptIds, int sts);

@Query(value="SELECT a.*,c.emp_name as MONTH from (\n" + 
		"        SELECT UUID() AS id,\n" + 
		"        atd.emp_id,\n" + 
		"        atd.emp_code,\n" + 
		"        atd.emp_name AS emp_name,\n" + 
		"        desg.name AS designation,\n" + 
		"        CONCAT(FLOOR(atd.ot_hr/60),\n" + 
		"        ':',\n" + 
		"        LPAD(MOD(atd.ot_hr,\n" + 
		"        60),\n" + 
		"        2,\n" + 
		"        '0'))   AS ot_hr ,\n" + 
		"        atd.att_date AS date, \n" + 
		"        atd.ot_hr as ot_min     \n" + 
		"    FROM\n" + 
		"        tbl_attt_daily_daily  atd                \n" + 
		"    inner join\n" + 
		"        m_employees emp                           \n" + 
		"            on atd.emp_id  = emp.emp_id \n" + 
		"            and emp.location_id=:locId  \n" + 
		"            and emp.depart_id in (:deptIds)             \n" + 
		"        inner join\n" + 
		"            m_designation desg                           \n" + 
		"                on emp.designation_id  = desg.desig_id                 \n" + 
		"        inner join\n" + 
		"            tbl_mst_emp_types  emptype                           \n" + 
		"                on emp.emp_type  = emptype.emp_type_id           \n" + 
		"        WHERE\n" + 
		"            atd.att_date BETWEEN :fromDate AND :toDate                 \n" + 
		"            AND   atd.ot_hr>0   \n" + 
		"            and atd.freeze_by_supervisor=:sts  \n" + 
		"            and ot_applicable='Yes'          \n" + 
		"        GROUP BY\n" + 
		"            atd.emp_id ,\n" + 
		"            atd.att_date           \n" + 
		"        ORDER BY\n" + 
		"            atd.emp_id,\n" + 
		"            atd.att_date) a\n" + 
		"    left join (\n" + 
		"    select emp_id,ini_auth_emp_id,fin_auth_emp_id from leave_authority ) b on b.emp_id=a.emp_id\n" + 
		"    left join (\n" + 
		"    select emp.emp_id, CONCAT(emp.first_name,' ',emp.surname) as emp_name from m_employees emp where emp.del_status=1) c on c.emp_id=b.fin_auth_emp_id",nativeQuery=true)
List<EmpOtReg> getEmpOtApprovalFinalPending(int locId, String fromDate, String toDate, List<Integer> deptIds, int sts);

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
