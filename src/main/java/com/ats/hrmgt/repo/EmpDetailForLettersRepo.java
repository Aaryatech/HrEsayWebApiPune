package com.ats.hrmgt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpDetailForLetters;

public interface EmpDetailForLettersRepo extends JpaRepository<EmpDetailForLetters, Integer> {

	@Query(value = "SELECT\n" + 
			"        emp.emp_id,\n" + 
			"        emp.emp_code,\n" + 
			"        emp.first_name,\n" + 
			"        emp.middle_name,\n" + 
			"        emp.surname,emp.mobile_no_1 as mobile,\n" + 
			"        dep.name AS dept_name,\n" + 
			"        dg.name AS emp_desgn,\n" + 
			"        loc.loc_name as  loc_name,\n" + 
			"        con.org_name,con.owner, \n" + 
			"        succomp.company_name  AS sub_comp_name,\n" + 
			"        salinfo.cmp_joining_date,\n" + 
			"        salinfo.cmp_leaving_date\n" + 
			"    FROM\n" + 
			"        m_employees emp \n" + 
			"    INNER JOIN\n" + 
			"        tbl_emp_salary_info salinfo \n" + 
			"            ON     emp.emp_id = salinfo.emp_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_designation dg \n" + 
			"            ON     emp.designation_id = dg.desig_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_department dep \n" + 
			"            ON     emp.depart_id = dep.depart_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_contractor con \n" + 
			"            ON     emp.contractor_id = con.contractor_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_location loc \n" + 
			"            ON     emp.location_id = loc.loc_id \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_emp_types emptyp \n" + 
			"            ON     emp.emp_type = emptyp.emp_type_id \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_shift_timming sht \n" + 
			"            ON     emp.current_shiftid = sht.id \n" + 
			"    LEFT JOIN\n" + 
			"        mst_salary_types saltype \n" + 
			"            ON     salinfo.salary_type_id = saltype.sal_type_id \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_sub_company succomp \n" + 
			"            ON     succomp.company_id = emp.sub_cmp_id  \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1  and emp.emp_id=:empId", nativeQuery = true)
	EmpDetailForLetters getEmpPendingLoanDetails(@Param("empId") int empId);

}
