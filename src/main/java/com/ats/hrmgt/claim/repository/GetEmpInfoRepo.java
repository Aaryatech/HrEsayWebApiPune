package com.ats.hrmgt.claim.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.claim.GetEmployeeAuthorityWise;
import com.ats.hrmgt.model.claim.GetEmployeeInfo;
 
public interface GetEmpInfoRepo extends JpaRepository<GetEmployeeInfo, Integer> {

	// List<GetEmployeeInfo> getEmpListByCompanyId(int companyId, List<Integer>
	// locIdList);

	@Query(value = " SELECT\n" + 
			"        emp_info.emp_code ,\n" + 
			"        emp_info.emp_id,\n" + 
			"        emp_info.middle_name as emp_mname,\n" + 
			"        emp_info.first_name as emp_fname      ,\n" + 
			"        emp_info.surname as emp_sname ,\n" + 
			"        emp_info.mobile_no_1 as emp_mobile1 ,\n" + 
			"        emp_info.email_id as emp_email      ,\n" + 
			"        m_department.name as emp_dept_short_name,\n" + 
			"        emp_info.new_basic_rate as emp_rate_perhr  ,\n" + 
			"       '0' as emp_prev_exp_yrs ,\n" + 
			"        m_department.name as emp_dept ,\n" + 
			"        tbl_mst_emp_types.name as  emp_type,\n" + 
			"       m_designation.name as emp_category,\n" + 
			"        emp_info.cmp_code as company_id,\n" + 
			"        emp_info.ex_var1,\n" + 
			"        m_company.company_name   ,\n" + 
			"        '-' as emp_cat_short_name,\n" + 
			"        '-' as emp_type_short_name,\n" + 
			"       '0' as emp_cat_id,\n" + 
			"        emp_info.emp_type as emp_type_id,\n" + 
			"        emp_info.depart_id as emp_dept_id  \n" + 
			"    from\n" + 
			"      m_employees emp_info,\n" + 
			"        m_department,\n" + 
			"        tbl_mst_emp_types,\n" + 
			"         m_company ,m_designation\n" + 
			"    where\n" + 
			"        emp_info.depart_id=m_department.depart_id\n" + 
			"     \n" + 
			"        and emp_info.emp_type=tbl_mst_emp_types.emp_type_id \n" + 
			"        and emp_info.del_status=1    \n" + 
			"         and emp_info.cmp_code=:companyId \n" + 
			"        and emp_info.location_id IN(\n" + 
			"        :locIdList \n" + 
			"        ) \n" + 
			"        and emp_info.cmp_code=m_company.company_id   AND m_designation.desig_id=  emp_info.designation_id\n" + 
			"     order by\n" + 
			"        emp_info.emp_id desc ", nativeQuery = true)

	List<GetEmployeeInfo> getEmpListByCompanyId(@Param("companyId") int companyId,
			@Param("locIdList") List<Integer> locIdList);
	
	
	

	@Query(value = " SELECT\n" + 
			"        emp_info.emp_code ,\n" + 
			"        emp_info.emp_id,\n" + 
			"        emp_info.middle_name as emp_mname,\n" + 
			"        emp_info.first_name as emp_fname      ,\n" + 
			"        emp_info.surname as emp_sname ,\n" + 
			"        emp_info.mobile_no_1 as emp_mobile1 ,\n" + 
			"        emp_info.email_id as emp_email      ,\n" + 
			"        m_department.name as emp_dept_short_name,\n" + 
			"        emp_info.new_basic_rate as emp_rate_perhr  ,\n" + 
			"       '0' as emp_prev_exp_yrs ,\n" + 
			"        m_department.name as emp_dept ,\n" + 
			"        tbl_mst_emp_types.name as  emp_type,\n" + 
			"       m_designation.name as emp_category,\n" + 
			"        emp_info.cmp_code as company_id,\n" + 
			"        emp_info.ex_var1,\n" + 
			"        m_company.company_name   ,\n" + 
			"        '-' as emp_cat_short_name,\n" + 
			"        '-' as emp_type_short_name,\n" + 
			"       '0' as emp_cat_id,\n" + 
			"        emp_info.emp_type as emp_type_id,\n" + 
			"        emp_info.depart_id as emp_dept_id  \n" + 
			"    from\n" + 
			"      m_employees emp_info,\n" + 
			"        m_department,\n" + 
			"        tbl_mst_emp_types,\n" + 
			"         m_company ,m_designation\n" + 
			"    where\n" + 
			"        emp_info.depart_id=m_department.depart_id\n" + 
			"     \n" + 
			"        and emp_info.emp_type=tbl_mst_emp_types.emp_type_id \n" + 
			"        and emp_info.del_status=1    \n" + 
			"         and emp_info.cmp_code=:companyId \n" + 
			"        and emp_info.emp_id IN(\n" + 
			"        :empIdList \n" + 
			"        ) \n" + 
			"        and emp_info.cmp_code=m_company.company_id   AND m_designation.desig_id=  emp_info.designation_id\n" + 
			"     order by\n" + 
			"        emp_info.emp_id desc ", nativeQuery = true)

	List<GetEmployeeInfo> getEmpListByCompanyIdByEmp(@Param("companyId") int companyId,
			@Param("empIdList") List<Integer> empIdList);


	@Query(value = "SELECT\n" + 
			"        emp_info.emp_code,\n" + 
			"        emp_info.emp_id,\n" + 
			"        emp_info.middle_name AS emp_mname,\n" + 
			"        emp_info.first_name AS emp_fname,\n" + 
			"        emp_info.surname AS emp_sname,\n" + 
			"        emp_info.mobile_no_1 AS emp_mobile1,\n" + 
			"        emp_info.email_id AS emp_email,\n" + 
			"        m_department.name AS emp_dept_short_name,\n" + 
			"        emp_info.new_basic_rate AS emp_rate_perhr,\n" + 
			"        '0' AS emp_prev_exp_yrs,\n" + 
			"        m_department.name AS emp_dept,\n" + 
			"        tbl_mst_emp_types.name AS emp_type,\n" + 
			"        m_designation.name AS emp_category,\n" + 
			"        emp_info.cmp_code AS company_id,\n" + 
			"        emp_info.ex_var1,\n" + 
			"        m_company.company_name,\n" + 
			"        '-' AS emp_cat_short_name,\n" + 
			"        '-' AS emp_type_short_name,\n" + 
			"        '0' AS emp_cat_id,\n" + 
			"        emp_info.emp_type AS emp_type_id,\n" + 
			"        emp_info.depart_id AS emp_dept_id \n" + 
			"    FROM\n" + 
			"        m_employees emp_info,\n" + 
			"        m_department,\n" + 
			"        tbl_mst_emp_types,\n" + 
			"        m_company,\n" + 
			"        m_designation \n" + 
			"    WHERE\n" + 
			"        emp_info.depart_id = m_department.depart_id \n" + 
			"        AND emp_info.designation_id = m_designation.desig_id \n" + 
			"        AND emp_info.emp_id = tbl_mst_emp_types.emp_type_id \n" + 
			"        AND emp_info.del_status = 1  \n" + 
			"        \n" + 
			"        AND emp_info.cmp_code = m_company.company_id \n" + 
			"        AND     emp_info.emp_id=:empId", nativeQuery = true)

	GetEmployeeInfo getEmpByEmpId(@Param("empId") int empId);

	@Query(value = " SELECT	  emp_info.emp_code ,emp_info.emp_id,emp_info.emp_fname,emp_info.emp_mname,m_emp_department.emp_dept_short_name  \n"
			+ "		,emp_info.emp_sname ,emp_info.emp_mobile1 " + ",emp_info.emp_email  \n"
			+ "		 ,emp_info.emp_rate_perhr  ,emp_info.emp_prev_exp_yrs , \n"
			+ "		m_emp_department.emp_dept_name as emp_dept ,m_emp_type.emp_type_name emp_type,m_emp_category.emp_cat_name as emp_category,\n"
			+ "		emp_info.company_id,emp_info.ex_var1,m_company.company_name   ,m_emp_category.emp_cat_short_name,m_emp_type.emp_type_short_name, \n"
			+ "		emp_info.emp_cat_id,emp_info.emp_type_id,emp_info.emp_dept_id  from emp_info,m_emp_department,m_emp_type, \n"
			+ "		m_emp_category,m_company where emp_info.emp_dept_id=m_emp_department.emp_dept_id and emp_info.emp_cat_id= \n"
			+ "	    m_emp_category.emp_cat_id  and emp_info.emp_type_id=m_emp_type.emp_type_id and emp_info.del_status=1 \n"
			+ "		and emp_info.is_active=1 and emp_info.company_id=:companyId and emp_info.company_id=m_company.company_id "
			+ "    and emp_info.company_id=m_company.company_id  and emp_info.emp_id IN(:empIdList) ", nativeQuery = true)

	List<GetEmployeeInfo> getEmpIdListByCompanyId(@Param("companyId") int companyId,
			  @Param("empIdList") List<GetEmployeeAuthorityWise> empIdList);

	@Query(value = "  SELECT\n" + "        emp_info.emp_code ,\n" + "        emp_info.emp_id,\n"
			+ "        emp_info.emp_fname,\n" + "        emp_info.emp_mname     ,\n" + "        emp_info.emp_sname ,\n"
			+ "        emp_info.emp_mobile1 ,\n" + "        emp_info.emp_email      ,\n"
			+ "        emp_info.emp_rate_perhr  ,\n" + "        emp_info.emp_prev_exp_yrs ,\n"
			+ "        m_emp_department.emp_dept_name as emp_dept ,m_emp_department.emp_dept_short_name, m_emp_category.emp_cat_short_name,m_emp_type.emp_type_short_name,\n" + "        m_emp_type.emp_type_name emp_type,\n"
			+ "        m_emp_category.emp_cat_name as emp_category,\n" + "        emp_info.company_id,\n"
			+ "        emp_info.ex_var1,\n" + "        m_company.company_name   ,\n" + "        emp_info.emp_cat_id,\n"
			+ "        emp_info.emp_type_id,\n" + "        emp_info.emp_dept_id  \n" + "    from\n"
			+ "        emp_info,\n" + "        m_emp_department,\n" + "        m_emp_type,\n"
			+ "        m_emp_category,\n" + "        m_company  \n" + "    where\n"
			+ "        emp_info.emp_dept_id=m_emp_department.emp_dept_id \n"
			+ "        and emp_info.emp_cat_id= m_emp_category.emp_cat_id  \n"
			+ "        and emp_info.emp_type_id=m_emp_type.emp_type_id \n" + "        and emp_info.del_status=1    \n"
			+ "        and emp_info.is_active=1 and emp_info.loc_id IN(:locIdList) \n" + "        and emp_info.company_id=:companyId\n" + "       \n"
			+ "        and emp_info.company_id=m_company.company_id\n"
			+ "         AND emp_info.emp_id NOT IN(SELECT auth.emp_id FROM leave_authority auth  WHERE auth.del_status=1 ) order by emp_info.emp_id desc", nativeQuery = true)

	List<GetEmployeeInfo> getEmpListByCompanyIdForAuth(@Param("companyId") int companyId,@Param("locIdList") List<Integer> locIdList);

	@Query(value = "  SELECT\n" + "        emp_info.emp_code ,\n" + "        emp_info.emp_id,\n"
			+ "        emp_info.emp_fname,\n" + "        emp_info.emp_mname     ,m_emp_department.emp_dept_short_name, m_emp_category.emp_cat_short_name,m_emp_type.emp_type_short_name,\n" + "        emp_info.emp_sname ,\n"
			+ "        emp_info.emp_mobile1 ,\n" + "        emp_info.emp_email      ,\n"
			+ "        emp_info.emp_rate_perhr  ,\n" + "        emp_info.emp_prev_exp_yrs ,\n"
			+ "        m_emp_department.emp_dept_name as emp_dept ,\n" + "        m_emp_type.emp_type_name emp_type,\n"
			+ "        m_emp_category.emp_cat_name as emp_category,\n" + "        emp_info.company_id,\n"
			+ "        emp_info.ex_var1,\n" + "        m_company.company_name   ,\n" + "        emp_info.emp_cat_id,\n"
			+ "        emp_info.emp_type_id,\n" + "        emp_info.emp_dept_id       \n" + "    from\n"
			+ "        emp_info,\n" + "        m_emp_department,\n" + "        m_emp_type,\n"
			+ "        m_emp_category,\n" + "        m_company       \n" + "    where\n"
			+ "        emp_info.emp_dept_id=m_emp_department.emp_dept_id          \n"
			+ "        and emp_info.emp_cat_id= m_emp_category.emp_cat_id           \n"
			+ "        and emp_info.emp_type_id=m_emp_type.emp_type_id          \n"
			+ "        and emp_info.del_status=1             \n" + "        and emp_info.is_active=1          \n"
			+ "        and emp_info.company_id=:companyId               \n"
			+ "        and emp_info.company_id=m_company.company_id          \n"
			+ "        AND emp_info.emp_id AND emp_info.emp_id IN(:empIdList) ", nativeQuery = true)

	List<GetEmployeeInfo> getEmpListByCompanyIdAndEmpIdList(@Param("companyId") int companyId,
			@Param("empIdList") List<Integer> empIdList);

	@Query(value = " SELECT\n" + 
			"    emp_info.emp_code,\n" + 
			"    emp_info.emp_id,\n" + 
			"    emp_info.middle_name AS emp_mname,\n" + 
			"    emp_info.first_name AS emp_fname,\n" + 
			"    emp_info.surname AS emp_sname,\n" + 
			"    emp_info.mobile_no_1 AS emp_mobile1,\n" + 
			"    emp_info.email_id AS emp_email,\n" + 
			"    m_department.name AS emp_dept_short_name,\n" + 
			"    emp_info.new_basic_rate AS emp_rate_perhr,\n" + 
			"    '0' AS emp_prev_exp_yrs,\n" + 
			"    m_department.name AS emp_dept,\n" + 
			"    tbl_mst_emp_types.name AS emp_type,\n" + 
			"    m_designation.name AS emp_category,\n" + 
			"    emp_info.cmp_code AS company_id,\n" + 
			"    emp_info.ex_var1,\n" + 
			"    m_company.company_name,\n" + 
			"    '-' AS emp_cat_short_name,\n" + 
			"    '-' AS emp_type_short_name,\n" + 
			"    '0' AS emp_cat_id,\n" + 
			"    emp_info.emp_type AS emp_type_id,\n" + 
			"    emp_info.depart_id AS emp_dept_id\n" + 
			"FROM\n" + 
			"    m_employees emp_info,\n" + 
			"    m_department,\n" + 
			"    tbl_mst_emp_types,\n" + 
			"    m_company,\n" + 
			"    m_designation\n" + 
			"where\n" + 
			"        emp_info.depart_id=m_department.depart_id          \n" + 
			"        and emp_info.designation_id= m_designation.desig_id           \n" + 
			"        and emp_info.emp_type=tbl_mst_emp_types.emp_type_id          \n" + 
			"        and emp_info.del_status=1             \n" + 
			"         and emp_info.location_id IN(\n" + 
			"         :locIdList \n" + 
			"        )          \n" + 
			"        and emp_info.cmp_code=:companyId               \n" + 
			"        and emp_info.cmp_code=m_company.company_id          \n" + 
			"        AND emp_info.emp_id NOT IN(\n" + 
			"            SELECT\n" + 
			"                claim.emp_id \n" + 
			"            FROM\n" + 
			"                claim_authority claim  \n" + 
			"            WHERE\n" + 
			"                claim.del_status=1 \n" + 
			"        ) ", nativeQuery = true)

	List<GetEmployeeInfo> getEmpListByCompanyIdForAuthClaim(@Param("companyId") int companyId,@Param("locIdList") List<Integer> locIdList);
	 
	@Query(value = " SELECT\n" + 
			"    emp_info.emp_code,\n" + 
			"    emp_info.emp_id,\n" + 
			"    emp_info.middle_name AS emp_mname,\n" + 
			"    emp_info.first_name AS emp_fname,\n" + 
			"    emp_info.surname AS emp_sname,\n" + 
			"    emp_info.mobile_no_1 AS emp_mobile1,\n" + 
			"    emp_info.email_id AS emp_email,\n" + 
			"    m_department.name AS emp_dept_short_name,\n" + 
			"    emp_info.new_basic_rate AS emp_rate_perhr,\n" + 
			"    '0' AS emp_prev_exp_yrs,\n" + 
			"    m_department.name AS emp_dept,\n" + 
			"    tbl_mst_emp_types.name AS emp_type,\n" + 
			"    m_designation.name AS emp_category,\n" + 
			"    emp_info.cmp_code AS company_id,\n" + 
			"    emp_info.ex_var1,\n" + 
			"    m_company.company_name,\n" + 
			"    '-' AS emp_cat_short_name,\n" + 
			"    '-' AS emp_type_short_name,\n" + 
			"    '0' AS emp_cat_id,\n" + 
			"    emp_info.emp_type AS emp_type_id,\n" + 
			"    emp_info.depart_id AS emp_dept_id\n" + 
			"FROM\n" + 
			"    m_employees emp_info,\n" + 
			"    m_department,\n" + 
			"    tbl_mst_emp_types,\n" + 
			"    m_company,\n" + 
			"    m_designation\n" + 
			"WHERE\n" + 
			"    emp_info.depart_id = m_department.depart_id AND emp_info.designation_id = m_designation.desig_id AND emp_info.emp_id = tbl_mst_emp_types.emp_type_id AND emp_info.del_status = 1  AND emp_info.cmp_code =:companyId AND emp_info.cmp_code = m_company.company_id AND   emp_info.emp_id IN(:empIdList)", nativeQuery = true)

	List<GetEmployeeInfo> getEmpIdListByCompanyIdForClaim(@Param("companyId") int companyId,@Param("empIdList") List<GetEmployeeAuthorityWise> empIdList);

}
