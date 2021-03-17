package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.ats.hrmgt.model.report.StatutoryEsicRep;

public interface StatutoryEsicRepRepo extends JpaRepository<StatutoryEsicRep, Integer> {
	
	
	
	@Query(value="SELECT\n" + 
			"    UUID() AS key_new, a.*\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT DISTINCT\n" + 
			"        te.emp_code,te.emp_id,\n" + 
			"        CONCAT(\n" + 
			"            te.first_name,\n" + 
			"            ' ',\n" + 
			"            te.middle_name,\n" + 
			"            ' ',\n" + 
			"            te.surname\n" + 
			"        ) AS emp_name,\n" + 
			"        te.esic_no,\n" + 
			"        tsc.net_salary,\n" + 
			"        tsc.esic_wages_cal,\n" + 
			"        tsc.employer_esic,\n" + 
			"        tasd.payable_days as present_days,\n" + 
			"        tasd.year,\n" + 
			"        tasd.month,tsc.esic,\n" + 
			"        '' as   reason, \n" + 
			"        '' as   esic_leave_date\n" + 
			"    FROM\n" + 
			"        m_employees AS te\n" + 
			"    INNER JOIN tbl_attt_summary_daily AS tasd\n" + 
			"    ON\n" + 
			"        tasd.emp_id = te.emp_id AND(\n" + 
			"            DATE_FORMAT(\n" + 
			"                CONCAT(tasd.year, '-', tasd.month, '-01'),\n" + 
			"                '%Y-%m-%d'\n" + 
			"            ) >=:fromDate AND DATE_FORMAT(\n" + 
			"                CONCAT(tasd.year, '-', tasd.month, '-01'),\n" + 
			"                '%Y-%m-%d'\n" + 
			"            ) <=:toDate)\n" + 
			"        INNER JOIN tbl_salary_calc AS tsc\n" + 
			"        ON\n" + 
			"            tsc.emp_id = te.emp_id AND(\n" + 
			"                DATE_FORMAT(\n" + 
			"                    CONCAT(\n" + 
			"                        tsc.calc_year,\n" + 
			"                        '-',\n" + 
			"                        tsc.calc_month,\n" + 
			"                        '-01'\n" + 
			"                    ),\n" + 
			"                    '%Y-%m-%d'\n" + 
			"                ) >=:fromDate AND DATE_FORMAT(\n" + 
			"                    CONCAT(\n" + 
			"                        tsc.calc_year,\n" + 
			"                        '-',\n" + 
			"                        tsc.calc_month,\n" + 
			"                        '-01'\n" + 
			"                    ),\n" + 
			"                    '%Y-%m-%d'\n" + 
			"                ) <=:toDate) and tsc.esic_status=1\n" + 
			"            WHERE\n" + 
			"                te.is_emp = '1' AND tsc.cmp_id =:companyId AND te.del_status=1 and te.location_id=:locId and te.depart_id in (:deptIds)\n" + 
			"            ) AS a",nativeQuery=true)
	List<StatutoryEsicRep> getStatutoryEsic(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("companyId") int companyId,@Param("locId") int locId, List<Integer> deptIds);

	
	

	@Query(value="SELECT\n" + 
			"    UUID() AS key_new, a.*\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT DISTINCT\n" + 
			"        te.emp_code,te.emp_id,\n" + 
			"        CONCAT(\n" + 
			"            te.first_name,\n" + 
			"            ' ',\n" + 
			"            te.middle_name,\n" + 
			"            ' ',\n" + 
			"            te.surname\n" + 
			"        ) AS emp_name,\n" + 
			"        te.esic_no,\n" + 
			"        tsc.net_salary,\n" + 
			"        tsc.esic_wages_cal,\n" + 
			"        tsc.employer_esic,\n" + 
			"        tasd.payable_days as present_days,\n" + 
			"        tasd.year,\n" + 
			"        tasd.month,tsc.esic,\n" + 
			"        '' as   reason, \n" + 
			"        '' as   esic_leave_date\n" + 
			"    FROM\n" + 
			"        m_employees AS te \n" + 
			"    INNER JOIN tbl_attt_summary_daily AS tasd\n" + 
			"    ON\n" + 
			"        tasd.emp_id = te.emp_id AND(\n" + 
			"            DATE_FORMAT(\n" + 
			"                CONCAT(tasd.year, '-', tasd.month, '-01'),\n" + 
			"                '%Y-%m-%d'\n" + 
			"            ) >=:fromDate AND DATE_FORMAT(\n" + 
			"                CONCAT(tasd.year, '-', tasd.month, '-01'),\n" + 
			"                '%Y-%m-%d'\n" + 
			"            ) <=:toDate )\n" + 
			"        INNER JOIN tbl_salary_calc AS tsc\n" + 
			"        ON\n" + 
			"            tsc.emp_id = te.emp_id AND(\n" + 
			"                DATE_FORMAT(\n" + 
			"                    CONCAT(\n" + 
			"                        tsc.calc_year,\n" + 
			"                        '-',\n" + 
			"                        tsc.calc_month,\n" + 
			"                        '-01'\n" + 
			"                    ),\n" + 
			"                    '%Y-%m-%d'\n" + 
			"                ) >=:fromDate AND DATE_FORMAT(\n" + 
			"                    CONCAT(\n" + 
			"                        tsc.calc_year,\n" + 
			"                        '-',\n" + 
			"                        tsc.calc_month,\n" + 
			"                        '-01'\n" + 
			"                    ),\n" + 
			"                    '%Y-%m-%d'\n" + 
			"                ) <=:toDate ) and tsc.esic_status=1\n" + 
			"            WHERE\n" + 
			"                te.is_emp = '1' AND te.del_status=1 and te.location_id=:locId and te.depart_id in (:deptIds)\n" + 
			"            ) AS a",nativeQuery=true)
	List<StatutoryEsicRep> getStatutoryEsicAll(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("locId")  int locId, List<Integer> deptIds);


	@Query(value="SELECT\n" + 
			"        UUID() AS key_new,\n" + 
			"        a.* \n" + 
			"    FROM\n" + 
			"        (     SELECT\n" + 
			"            DISTINCT         te.emp_code,\n" + 
			"            te.emp_id,\n" + 
			"            CONCAT(             te.first_name,\n" + 
			"            ' ',\n" + 
			"            te.middle_name,\n" + 
			"            ' ',\n" + 
			"            te.surname         ) AS emp_name,\n" + 
			"            te.esic_no,\n" + 
			"            tsc.net_salary,\n" + 
			"            tsc.esic_wages_cal,\n" + 
			"            tsc.employer_esic,\n" + 
			"            tasd.payable_days as present_days,\n" + 
			"            tasd.year,\n" + 
			"            tasd.month,\n" + 
			"            tsc.esic, case when si.leaving_reason_esic>-1 then si.leaving_reason_esic else '' end as reason, \n" + 
			"            case when si.leaving_reason_esic>-1 then si.ex_var1   else '' end as esic_leave_date    \n" + 
			"        FROM\n" + 
			"            m_employees AS te     \n" + 
			"        INNER JOIN\n" + 
			"            tbl_attt_summary_daily AS tasd     \n" + 
			"                ON         tasd.emp_id = te.emp_id \n" + 
			"                AND tasd.month = :month AND tasd.year = :year         \n" + 
			"         INNER JOIN\n" + 
			"                tbl_salary_calc AS tsc         \n" + 
			"                    ON             tsc.emp_id = te.emp_id \n" + 
			"                    AND tsc.calc_month = tasd.month AND tsc.calc_year = tasd.year \n" + 
			"                    and tsc.esic_status=1\n" + 
			"        INNER JOIN\n" + 
			"            tbl_emp_salary_info AS si                              \n" + 
			"                ON             si.emp_id = te.emp_id             \n" + 
			"                WHERE\n" + 
			"                      tsc.cmp_id =:companyId\n" + 
			"                    AND te.del_status=1 \n" + 
			"                    and te.location_id=:locId and te.depart_id in (:deptIds)) AS a",nativeQuery=true)
	List<StatutoryEsicRep> showEsicDataUpload(@Param("month")String month, @Param("year") String year, @Param("locId") int locId, @Param("companyId") int companyId, List<Integer> deptIds);



	@Query(value="SELECT\n" + 
			"    UUID() AS key_new, a.*\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT DISTINCT\n" + 
			"        te.emp_code,te.emp_id,\n" + 
			"        CONCAT(\n" + 
			"            te.first_name,\n" + 
			"            ' ',\n" + 
			"            te.middle_name,\n" + 
			"            ' ',\n" + 
			"            te.surname\n" + 
			"        ) AS emp_name,\n" + 
			"        te.esic_no,\n" + 
			"        tsc.net_salary,\n" + 
			"        tsc.esic_wages_cal,\n" + 
			"        tsc.employer_esic,\n" + 
			"        tasd.payable_days as present_days,\n" + 
			"        tasd.year,\n" + 
			"        tasd.month,tsc.esic,\n" + 
			"        '' as   reason, \n" + 
			"        '' as   esic_leave_date\n" + 
			"    FROM\n" + 
			"        m_employees AS te\n" + 
			"    INNER JOIN tbl_attt_summary_daily AS tasd\n" + 
			"    ON\n" + 
			"        tasd.emp_id = te.emp_id AND(\n" + 
			"            DATE_FORMAT(\n" + 
			"                CONCAT(tasd.year, '-', tasd.month, '-01'),\n" + 
			"                '%Y-%m-%d'\n" + 
			"            ) >=:fromDate AND DATE_FORMAT(\n" + 
			"                CONCAT(tasd.year, '-', tasd.month, '-01'),\n" + 
			"                '%Y-%m-%d'\n" + 
			"            ) <=:toDate)\n" + 
			"        INNER JOIN t_arear_header AS tsc\n" + 
			"        ON\n" + 
			"            tsc.emp_id = te.emp_id AND(\n" + 
			"                DATE_FORMAT(\n" + 
			"                    CONCAT(\n" + 
			"                        tsc.calc_year,\n" + 
			"                        '-',\n" + 
			"                        tsc.calc_month,\n" + 
			"                        '-01'\n" + 
			"                    ),\n" + 
			"                    '%Y-%m-%d'\n" + 
			"                ) >=:fromDate AND DATE_FORMAT(\n" + 
			"                    CONCAT(\n" + 
			"                        tsc.calc_year,\n" + 
			"                        '-',\n" + 
			"                        tsc.calc_month,\n" + 
			"                        '-01'\n" + 
			"                    ),\n" + 
			"                    '%Y-%m-%d'\n" + 
			"                ) <=:toDate) and tsc.esic_status=1\n" + 
			"            WHERE\n" + 
			"                te.is_emp = '1' AND tsc.cmp_id =:companyId AND te.del_status=1 and te.location_id=:locId\n" + 
			"            ) AS a",nativeQuery=true)
	List<StatutoryEsicRep> getArearsStatutoryEsic(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("companyId") int companyId,@Param("locId") int locId);

}
