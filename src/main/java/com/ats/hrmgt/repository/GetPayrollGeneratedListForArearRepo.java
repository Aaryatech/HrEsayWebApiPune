package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.GetPayrollGeneratedListForArear;

public interface GetPayrollGeneratedListForArearRepo extends JpaRepository<GetPayrollGeneratedListForArear, Integer> {

	@Query(value = "select\n" + 
			"        concat(e.first_name,\n" + 
			"        ' ',\n" + 
			"        e.surname) as name,\n" + 
			"        et.name as emp_type_name,\n" + 
			"        e.sub_cmp_id,\n" + 
			"        d.name as depart_name,\n" + 
			"        dg.name as design_name,\n" + 
			"        sc.*,\n" + 
			"        dd.payable_days,\n" + 
			"        dd.present_days,\n" + 
			"        dd.weekly_off,\n" + 
			"        dd.paid_holiday,\n" + 
			"        dd.paid_leave,\n" + 
			"        dd.unpaid_leave,\n" + 
			"        dd.absent_days,\n" + 
			"        MONTHNAME(concat(sc.calc_year,'-',sc.calc_month,'-01')) as email,dd.working_days,dd.total_days_inmonth,si.monthly_hr_target,si.monthly_minimum_target,si.monthly_ot_hr,si.daily_hr,dd.totworking_hrs\n" + 
			"    from\n" + 
			"        tbl_salary_calc sc,\n" + 
			"        m_employees e,\n" + 
			"        tbl_mst_emp_types et,\n" + 
			"        m_department d,\n" + 
			"        m_designation dg,\n" + 
			"        tbl_attt_summary_daily dd,\n" + 
			"        tbl_emp_info ei,tbl_emp_salary_info si     \n" + 
			"    where\n" + 
			"                 \n" + 
			"        date_format(concat(sc.calc_year,'-',sc.calc_month,'-01'),'%Y-%m-%d') between :fromDate and :toDate\n" + 
			"        and e.emp_id=sc.emp_id          \n" + 
			"        and et.emp_type_id=sc.emp_type          \n" + 
			"        and d.depart_id=sc.depart_id          \n" + 
			"        and dg.desig_id=e.designation_id \n" + 
			"        and ei.emp_id=e.emp_id         \n" + 
			"        and dd.id=sc.att_sum_id \n" + 
			"        and e.emp_id in (:empIds) and e.emp_id=si.emp_id \n" + 
			"    order by\n" + 
			"        e.emp_id asc", nativeQuery = true) 
	List<GetPayrollGeneratedListForArear> generatedPayrollList(List<Integer> empIds, String fromDate, String toDate);

}
