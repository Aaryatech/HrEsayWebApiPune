package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EcrFileData;

public interface EcrFileDataRepo extends JpaRepository<EcrFileData, Integer>{

	@Query(value="select UUID() as id,e.uan,e.first_name,e.middle_name,e.surname,sal.gross_salary,sal.epf_wages,sal.epf_wages_employer,sal.eps_wages,sal.employee_pf,"
			+ "sal.employer_eps,sal.employer_pf,ds.ncp_days as ncp_days,0 as adv,sal.gross_salary+sal.performance_bonus+sal.reward+sal.ot_wages+sal.production_insentive+sal.night_allow+sal.bhatta+sal.other1+sal.leave_encash_amt as gross_earning "
			+ " from tbl_salary_calc sal,m_employees e,tbl_attt_summary_daily ds where  "
			+ "e.emp_id=sal.emp_id and sal.pf_status=1 and ds.emp_id=e.emp_id and ds.month=sal.calc_month and sal.calc_year=ds.year and "
			+ "sal.calc_month=:month and sal.calc_year=:year and e.location_id=:locId and sal.cmp_id=:cmpId and e.depart_id in (:deptIds)",nativeQuery=true)
	List<EcrFileData> getEcrFileData(@Param("cmpId")int cmpId,@Param("month") String month,@Param("year") String year,@Param("locId") int locId, @Param("deptIds") List<Integer> deptIds);

	@Query(value="select UUID() as id,e.uan,e.first_name,e.middle_name,e.surname,sal.gross_salary,sal.epf_wages,sal.epf_wages_employer,sal.eps_wages,sal.employee_pf,"
			+ "sal.employer_eps,sal.employer_pf,ds.ncp_days as ncp_days,0 as adv  from t_arear_header sal,m_employees e,tbl_attt_summary_daily ds where  "
			+ "e.emp_id=sal.emp_id and sal.pf_status=1 and ds.emp_id=e.emp_id and ds.month=sal.calc_month and sal.calc_year=ds.year and "
			+ "sal.calc_month=:month and sal.calc_year=:year and e.location_id=:locId and sal.cmp_id=:cmpId",nativeQuery=true)
	List<EcrFileData> getArrearsEcrFileData(@Param("cmpId")int cmpId,@Param("month") String month,@Param("year") String year,@Param("locId") int locId);

}
