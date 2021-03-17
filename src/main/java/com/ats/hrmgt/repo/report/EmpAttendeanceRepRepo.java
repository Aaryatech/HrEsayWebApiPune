package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.EmpAttendeanceRep;
 
public interface EmpAttendeanceRepRepo extends JpaRepository<EmpAttendeanceRep, String> {
	
	@Query(value="SELECT\n" + 
			"        UUID() as unid,\n" + 
			"        (     SELECT\n" + 
			"            COUNT('')              \n" + 
			"        FROM\n" + 
			"            tbl_attt_daily_daily, m_employees e\n" + 
			"        WHERE\n" + 
			"            tbl_attt_daily_daily.lv_sumup_id in (5,13,14,15,20,21,18)     \n" + 
			"            AND tbl_attt_daily_daily.att_date BETWEEN :fromDate and :toDate and e.emp_id=tbl_attt_daily_daily.emp_id and e.location_id=:locId and depart_id in (:deptIds)) AS emp_present,\n" + 
			"        (     SELECT\n" + 
			"            COUNT('')             \n" + 
			"        FROM\n" + 
			"            tbl_attt_daily_daily,m_employees e              \n" + 
			"        WHERE\n" + 
			"            tbl_attt_daily_daily.lv_sumup_id = 12     \n" + 
			"            AND tbl_attt_daily_daily.att_date BETWEEN :fromDate and :toDate and e.emp_id=tbl_attt_daily_daily.emp_id and e.location_id=:locId and depart_id in (:deptIds)) AS week_off,\n" + 
			"        (     SELECT\n" + 
			"            COUNT('')         \n" + 
			"        FROM\n" + 
			"            tbl_attt_daily_daily,m_employees e               \n" + 
			"        WHERE\n" + 
			"            tbl_attt_daily_daily.lv_sumup_id in ( 7,11)    \n" + 
			"            AND  tbl_attt_daily_daily.att_date BETWEEN :fromDate and :toDate and e.emp_id=tbl_attt_daily_daily.emp_id and e.location_id=:locId and depart_id in (:deptIds)) AS paid_leave,\n" + 
			"        (     SELECT\n" + 
			"           COUNT('')        \n" + 
			"        FROM\n" + 
			"            tbl_attt_daily_daily,m_employees e               \n" + 
			"        WHERE\n" + 
			"            tbl_attt_daily_daily.lv_sumup_id IN(\n" + 
			"                22\n" + 
			"            )     \n" + 
			"            AND tbl_attt_daily_daily.att_date BETWEEN :fromDate and :toDate and e.emp_id=tbl_attt_daily_daily.emp_id and e.location_id=:locId and depart_id in (:deptIds)) AS unpaid_leave",nativeQuery=true)
	List<EmpAttendeanceRep> getSpecEmpAdvForReport(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("locId") int locId, @Param("deptIds") List<Integer> deptIds);
	

}


//SELECT\n" + 
//"UUID() as unid,\n" + 
//"    (\n" + 
//"    SELECT\n" + 
//"        COUNT(tbl_attt_daily_daily.id)\n" + 
//"    FROM\n" + 
//"        tbl_attt_daily_daily\n" + 
//"    WHERE\n" + 
//"        tbl_attt_daily_daily.lv_sumup_id = 5 AND tbl_attt_daily_daily.att_date BETWEEN :fromDate AND :toDate AND tbl_attt_daily_daily.company_id=:companyId\n" + 
//") AS emp_present,\n" + 
//"(\n" + 
//"    SELECT\n" + 
//"        COUNT(tbl_attt_daily_daily.id)\n" + 
//"    FROM\n" + 
//"        tbl_attt_daily_daily\n" + 
//"    WHERE\n" + 
//"        tbl_attt_daily_daily.lv_sumup_id = 12 AND tbl_attt_daily_daily.att_date BETWEEN :fromDate AND :toDate AND tbl_attt_daily_daily.company_id=:companyId \n" + 
//") AS week_off,\n" + 
//"(\n" + 
//"    SELECT\n" + 
//"        COUNT(tbl_attt_daily_daily.id)\n" + 
//"    FROM\n" + 
//"        tbl_attt_daily_daily\n" + 
//"    WHERE\n" + 
//"        tbl_attt_daily_daily.lv_sumup_id = 7 AND  tbl_attt_daily_daily.att_date BETWEEN :fromDate AND :toDate AND tbl_attt_daily_daily.company_id=:companyId \n" + 
//") AS paid_leave,\n" + 
//"(\n" + 
//"    SELECT\n" + 
//"        COUNT(tbl_attt_daily_daily.id)\n" + 
//"    FROM\n" + 
//"        tbl_attt_daily_daily\n" + 
//"    WHERE\n" + 
//"        tbl_attt_daily_daily.lv_sumup_id IN(11,22) AND tbl_attt_daily_daily.att_date BETWEEN :fromDate AND :toDate AND tbl_attt_daily_daily.company_id=:companyId \n" + 
//") AS unpaid_leave