package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.GetPtChallan;
import com.ats.hrmgt.model.report.GetSalaryCalcReport;
import com.ats.hrmgt.model.report.GetYearlyLoan;

public interface GetPtChallanRepo extends JpaRepository<GetPtChallan, Integer> {

	
	@Query(value="select sb.slab_id,sb.sal_term_id,sb.min_val,sb.max_val,sb.amount,sb.gender,sb.ex_int1 as loc_id, \n" + 
			"CASE\n" + 
			"    WHEN sb.gender=1 \n" + 
			"        THEN (select \n" + 
			"                count('')  from tbl_salary_calc s,m_employees e,tbl_emp_info ef where s.pt_applicable='yes' and  s.calc_month=:month and  \n" + 
			"                s.calc_year=:year and e.emp_id=s.emp_id and ef.emp_id=e.emp_id and ef.gender='male' and e.location_id=sb.ex_int1 and e.depart_id in (:deptIds) and \n" + 
			"                (s.gross_salary+s.production_insentive+s.ot_wages) >= sb.min_val and (s.gross_salary+s.production_insentive+s.ot_wages) <=sb.max_val and s.cmp_id=:companyId)\n" + 
			"    WHEN sb.gender = 2 \n" + 
			"        THEN (select \n" + 
			"                count('')  from tbl_salary_calc s,m_employees e,tbl_emp_info ef where s.pt_applicable='yes' and  s.calc_month=:month and  \n" + 
			"                s.calc_year=:year and e.emp_id=s.emp_id and ef.emp_id=e.emp_id and ef.gender='female' and e.location_id=sb.ex_int1 and e.depart_id in (:deptIds) and \n" + 
			"                (s.gross_salary+s.production_insentive+s.ot_wages) >= sb.min_val and (s.gross_salary+s.production_insentive+s.ot_wages) <=sb.max_val and s.cmp_id=:companyId) \n" + 
			"            \n" + 
			"    ELSE 0\n" + 
			"END as count,CASE\n" + 
			"    WHEN sb.gender=1 then 'MALE' \n" + 
			"    WHEN sb.gender=2 then 'FEMALE' \n" + 
			"    ELSE '-' \n" + 
			"END AS gender_name from tbl_slabs  sb where sb.ex_int1=:locId",nativeQuery=true)
	List<GetPtChallan> getPtChallan(@Param("month") String month, @Param("year") String year,@Param("companyId") int companyId,@Param("locId") int locId, List<Integer> deptIds);
	
	 
}
