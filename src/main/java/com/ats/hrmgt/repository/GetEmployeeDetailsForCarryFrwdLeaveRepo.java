package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.ats.hrmgt.model.GetEmployeeDetailsForCarryFrwdLeave;

public interface GetEmployeeDetailsForCarryFrwdLeaveRepo extends JpaRepository<GetEmployeeDetailsForCarryFrwdLeave, Integer>{

	
	@Query(value = " SELECT\n" + 
			"        emp.emp_id,\n" + 
			"        emp.emp_code,\n" + 
			"        concat(emp.first_name,' ',emp.surname) as emp_name,\n" + 
			"        dep.name_sd AS dept_name,\n" + 
			"        dg.name_sd AS emp_desgn,\n" + 
			"        loc.loc_name_short as  loc_name, \n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        saltype.sal_type_name,\n" + 
			"        succomp.name_sd  AS sub_comp_name, \n" + 
			"        salinfo.gross_salary,\n" + 
			"        salinfo.basic,\n" + 
			"        strctall.lvs_id,\n" + 
			"        lvsh.lvs_name,\n" + 
			"        IFNULL((select sum(es.allowance_value) from emp_sal_allowance es where es.emp_id=emp.emp_id and find_in_set ( es.allowance_id , (select\n" + 
			"            ex_var1          \n" + 
			"        from\n" + 
			"            leave_structure_header          \n" + 
			"        where\n" + 
			"            lvs_id=strctall.lvs_id))),0) as allow_sum\n" + 
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
			"            ON     emp.depart_id = dep.depart_id  \n" + 
			"    LEFT JOIN\n" + 
			"        m_location loc \n" + 
			"            ON     emp.location_id = loc.loc_id \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_emp_types emptyp \n" + 
			"            ON     emp.emp_type = emptyp.emp_type_id \n" + 
			"    LEFT JOIN\n" + 
			"        mst_salary_types saltype \n" + 
			"            ON     salinfo.salary_type_id = saltype.sal_type_id \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_sub_company succomp \n" + 
			"            ON     succomp.company_id = emp.sub_cmp_id  \n" + 
			"     LEFT JOIN\n" + 
			"        leave_structure_allotment strctall \n" + 
			"            ON     strctall.emp_id = emp.emp_id AND strctall.cal_yr_id=(select\n" + 
			"                max(cal_yr_id-1) as cal_yr_id              \n" + 
			"            from\n" + 
			"                dm_cal_year               \n" + 
			"            where\n" + 
			"                is_current=1)\n" + 
			"    LEFT JOIN\n" + 
			"        leave_structure_header lvsh \n" + 
			"            ON     lvsh.lvs_id = strctall.lvs_id\n" + 
			"            \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1 \n" + 
			"        AND(\n" + 
			"            salinfo.cmp_leaving_date IS NULL \n" + 
			"            OR salinfo.cmp_leaving_date = '' \n" + 
			"            OR salinfo.cmp_leaving_date = 1970 -00 -00 \n" + 
			"            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m')\n" + 
			"        )  \n" + 
			"        and emp.emp_id not in (\n" + 
			"            select\n" + 
			"                emp_id                           \n" + 
			"            from\n" + 
			"                leave_balance_cal                           \n" + 
			"            where\n" + 
			"                cal_yr_id=(\n" + 
			"                    select\n" + 
			"                        cal_yr_id                                           \n" + 
			"                    from\n" + 
			"                        dm_cal_year                                           \n" + 
			"                    where\n" + 
			"                        is_current=1                                  \n" + 
			"                )                          \n" + 
			"            )              \n" + 
			"            and  emp.location_id=:locId", nativeQuery = true)
	List<GetEmployeeDetailsForCarryFrwdLeave> getEmpDetailListForCarryForwardLeave(@Param("locId") int locId);

	@Query(value = " SELECT\n"
			+ "        emp.emp_id,\n"
			+ "        emp.emp_code,\n"
			+ "        concat(emp.first_name,\n"
			+ "        ' ',\n"
			+ "        emp.surname) as emp_name,\n"
			+ "        dep.name_sd AS dept_name,\n"
			+ "        dg.name_sd AS emp_desgn,\n"
			+ "        loc.loc_name_short as  loc_name,\n"
			+ "        emptyp.name AS emp_type_name,\n"
			+ "        saltype.sal_type_name,\n"
			+ "        succomp.name_sd  AS sub_comp_name,\n"
			+ "        salinfo.gross_salary,\n"
			+ "        salinfo.basic,\n"
			+ "        strctall.lvs_id,\n"
			+ "        lvsh.lvs_name,\n"
			+ "        IFNULL((select\n"
			+ "            sum(es.allowance_value) \n"
			+ "        from\n"
			+ "            emp_sal_allowance es \n"
			+ "        where\n"
			+ "            es.emp_id=emp.emp_id \n"
			+ "            and find_in_set ( es.allowance_id , (select\n"
			+ "                ex_var1                   \n"
			+ "            from\n"
			+ "                leave_structure_header                   \n"
			+ "            where\n"
			+ "                lvs_id=strctall.lvs_id))),0) as allow_sum     \n"
			+ "    FROM\n"
			+ "        m_employees emp      \n"
			+ "    INNER JOIN\n"
			+ "        tbl_emp_salary_info salinfo              \n"
			+ "            ON     emp.emp_id = salinfo.emp_id      \n"
			+ "    LEFT JOIN\n"
			+ "        m_designation dg              \n"
			+ "            ON     emp.designation_id = dg.desig_id      \n"
			+ "    LEFT JOIN\n"
			+ "        m_department dep              \n"
			+ "            ON     emp.depart_id = dep.depart_id       \n"
			+ "    LEFT JOIN\n"
			+ "        m_location loc              \n"
			+ "            ON     emp.location_id = loc.loc_id      \n"
			+ "    LEFT JOIN\n"
			+ "        tbl_mst_emp_types emptyp              \n"
			+ "            ON     emp.emp_type = emptyp.emp_type_id      \n"
			+ "    LEFT JOIN\n"
			+ "        mst_salary_types saltype              \n"
			+ "            ON     salinfo.salary_type_id = saltype.sal_type_id      \n"
			+ "    LEFT JOIN\n"
			+ "        tbl_mst_sub_company succomp              \n"
			+ "            ON     succomp.company_id = emp.sub_cmp_id        \n"
			+ "    LEFT JOIN\n"
			+ "        leave_structure_allotment strctall              \n"
			+ "            ON     strctall.emp_id = emp.emp_id \n"
			+ "            AND strctall.cal_yr_id=:currYrId    \n"
			+ "    LEFT JOIN\n"
			+ "        leave_structure_header lvsh              \n"
			+ "            ON     lvsh.lvs_id = strctall.lvs_id                  \n"
			+ "    WHERE\n"
			+ "        emp.emp_id=:empId", nativeQuery = true)
	GetEmployeeDetailsForCarryFrwdLeave getDetailForCarryForwordLeaveByEmpId(int empId, int currYrId);

}
