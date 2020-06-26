package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetEmpDetailForFullPayslip;

public interface GetEmpDetailForFullPayslipRepo extends JpaRepository<GetEmpDetailForFullPayslip, Integer>{

	@Query(value = "SELECT\n" + 
			"        emp.emp_id,\n" + 
			"        emp.pf_no,\n" + 
			"        emp.esic_no,\n" + 
			"        emp.aadhar_no,\n" + 
			"        emp.uan,\n" + 
			"        emp.pan_card_no,\n" + 
			"        salinfo.cmp_joining_date,\n" + 
			"        ifnull((select sum(current_outstanding) from tbl_loan_main lm where lm.emp_id=emp.emp_id and lm.loan_status='active' and lm.del_status=1),0) as rem_loan_amt,\n" + 
			"        binfo.acc_no, \n" + 
			"        ifnull((bank.name),'-') as bank_name,\n" + 
			"        ifnull((bank.branch_name),'-') as branch_name,\n" + 
			"        loc.loc_name\n" + 
			"    FROM\n" + 
			"        m_employees emp\n" + 
			"         \n" + 
			"    INNER JOIN\n" + 
			"        tbl_emp_salary_info salinfo \n" + 
			"            ON     emp.emp_id = salinfo.emp_id  \n" + 
			"    INNER JOIN\n" + 
			"        tbl_emp_bank_info binfo \n" + 
			"            ON     binfo.emp_id = emp.emp_id   \n" + 
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
			"            ON     succomp.company_id = emp.sub_cmp_id \n" + 
			"    LEFT JOIN\n" + 
			"        weekoff_category wocat \n" + 
			"            ON     wocat.wo_cat_id = emp.weekend_category \n" + 
			"    LEFT JOIN\n" + 
			"        holiday_category holidaycat \n" + 
			"            ON     holidaycat.ho_cat_id = emp.holiday_category  \n" + 
			"     LEFT JOIN\n" + 
			"        m_bank bank \n" + 
			"            ON     bank.bank_id = binfo.bank_id  \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1 \n" + 
			"        AND(\n" + 
			"            salinfo.cmp_leaving_date IS NULL \n" + 
			"            OR salinfo.cmp_leaving_date = '' \n" + 
			"            OR salinfo.cmp_leaving_date = 1970 -00 -00 \n" + 
			"            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m')\n" + 
			"        )  and emp.emp_id in (:empIds)", nativeQuery = true)
	List<GetEmpDetailForFullPayslip> getEmpDetailForFullSalarySlip(@Param("empIds") List<Integer> empIds);

}
