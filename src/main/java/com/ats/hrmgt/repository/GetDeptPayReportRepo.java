package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.GetDeptPayReport;

public interface GetDeptPayReportRepo extends JpaRepository<GetDeptPayReport, Integer>{

	@Query(value = "select\n" + 
			"        sc.id,\n" + 
			"        d.name as depart_name,\n" + 
			"        sc.depart_id,\n" + 
			"        sc.calc_month,\n" + 
			"        sc.calc_year,\n" + 
			"        sc.sal_type_id,\n" + 
			"        sum(sc.basic_cal) as basic_cal,\n" + 
			"        sum(sc.performance_bonus) as performance_bonus,\n" + 
			"        sum(sc.ot_wages) as ot_wages,\n" + 
			"        sum(sc.misc_exp_add) as misc_exp_add,\n" + 
			"        sum(sc.bonus_cal) as bonus_cal,\n" + 
			"        sum(sc.exgretia_cal) as exgretia_cal,\n" + 
			"        sum(sc.da_arreas_cal) as da_arreas_cal,\n" + 
			"        sum(sc.increment_arreas_cal) as increment_arreas_cal,\n" + 
			"        sum(sc.epf_wages) as epf_wages,\n" + 
			"        sum(sc.epf_wages_employer) as epf_wages_employer,\n" + 
			"        sum(sc.esic_wages_cal) as esic_wages_cal,\n" + 
			"        sum(sc.gross_salary) as gross_salary,\n" + 
			"        sum(sc.eps_wages) as eps_wages,\n" + 
			"        sum(sc.esic_wages_dec) as esic_wages_dec,\n" + 
			"        sum(sc.employee_pf) as employee_pf,\n" + 
			"        sum(sc.employer_eps) as employer_eps,\n" + 
			"        sum(sc.eps_default) as eps_default,\n" + 
			"        sum(sc.epmloyer_epf_default) as epmloyer_epf_default,\n" + 
			"        sum(sc.epmloyer_epf_extra) as epmloyer_epf_extra,\n" + 
			"        sum(sc.employer_pf) as employer_pf,\n" + 
			"        sum(sc.esic) as esic,\n" + 
			"        sum(sc.employer_esic) as employer_esic,\n" + 
			"        sum(sc.mlwf) as mlwf,\n" + 
			"        sum(sc.tds) as tds,\n" + 
			"        sum(sc.itded) as itded,\n" + 
			"        sum(sc.fund) as fund,\n" + 
			"        sum(sc.tot_pf_admin_ch) as tot_pf_admin_ch,\n" + 
			"        sum(sc.tot_edli_ch) as tot_edli_ch,\n" + 
			"        sum(sc.tot_edli_admin_ch) as tot_edli_admin_ch,\n" + 
			"        sum(sc.ncp_days) as ncp_days,\n" + 
			"        sum( sc.pt_ded) as pt_ded,\n" + 
			"        sum(sc.advance_ded) as advance_ded,\n" + 
			"        sum(sc.loan_ded) as loan_ded,\n" + 
			"        sum(sc.misc_exp_ded) as misc_exp_ded,\n" + 
			"        sum(sc.misc_exp_ded_deduct) as misc_exp_ded_deduct,\n" + 
			"        sum(sc.net_salary) as net_salary,\n" + 
			"        sum(sc.pay_ded) as pay_ded,\n" + 
			"        sum(sc.society_contribution) as society_contribution,\n" + 
			"        sum(sc.basic_default) as basic_default,\n" + 
			"        sum(sc.ab_deduction) as ab_deduction,\n" + 
			"        sum(sc.production_insentive) as production_insentive,\n" + 
			"        sum(sc.present_insentive) as present_insentive,\n" + 
			"        sum(sc.night_allow) as night_allow,\n" + 
			"        sum(sc.gross_sal_default) as gross_sal_default,\n" + 
			"        sum(sc.employer_mlwf) as employer_mlwf,\n" + 
			"        sum(sc.adjust_minus) as adjust_minus,\n" + 
			"        sum(sc.adjust_plus) as adjust_plus,\n" + 
			"        sum(sc.reward) as reward,\n" + 
			"        sum(sc.bhatta) as bhatta,\n" + 
			"        sum(sc.other1)   as other1, sum(sc.leave_encash_amt) as leave_encash_amt    \n" + 
			"    from\n" + 
			"        tbl_salary_calc sc,\n" + 
			"        m_employees e,\n" + 
			"        m_department d      \n" + 
			"    where\n" + 
			"        sc.calc_month=:month                            \n" + 
			"        and calc_year=:year                            \n" + 
			"        and e.emp_id=sc.emp_id                            \n" + 
			"        and d.depart_id=sc.depart_id              \n" + 
			"        and e.location_id in (:locId)       \n" + 
			"    group  by\n" + 
			"        sc.depart_id", nativeQuery = true)
	List<GetDeptPayReport> departmentwisePayrollReport(int month, int year, List<Integer> locId);

}
