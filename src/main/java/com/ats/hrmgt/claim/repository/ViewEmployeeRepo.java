package com.ats.hrmgt.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ViewEmployee;

public interface ViewEmployeeRepo extends JpaRepository<ViewEmployee, Integer> {

	@Query(value="SELECT\n" + 
			"    t1.emp_id,\n" + 
			"    t1.emp_code,\n" + 
			"    t1.first_name,\n" + 
			"    t1.middle_name,\n" + 
			"    t1.surname,\n" + 
			"    t1.mobile_no_1,\n" + 
			"    t1.mobile_no_2,\n" + 
			"    t2.company_name,\n" + 
			"    t1.uan,\n" + 
			"    t1.esic_no,\n" + 
			"    t1.aadhar_no,\n" + 
			"    t1.pan_card_no,\n" + 
			"    t1.pf_no,\n" + 
			"    t3.org_name AS contractor_name,\n" + 
			"    t4.name AS skill,\n" + 
			"    t5.name AS desingnation,\n" + 
			"    t6.name depart_name,\n" + 
			"    t7.loc_name AS location,\n" + 
			"    t7.ex_int1 AS authority_desig_type,\n" + 
			"    t8.name AS emp_type,\n" + 
			"    t9.emp_type_name AS emp_category,\n" + 
			"    t10.middle_name,\n" + 
			"    t10.middle_name_relation,\n" + 
			"    t10.dob,\n" + 
			"    t10.gender,\n" + 
			"    t10.marital_status,\n" + 
			"    t10.email,\n" + 
			"    t10.address,\n" + 
			"    t10.permanent_address,\n" + 
			"    t10.emp_qualification,\n" + 
			"    t10.emer_name,\n" + 
			"    t10.emer_contact_no_1,\n" + 
			"    t10.emer_contact_no_2,\n" + 
			"    t10.emer_contact_addr,\n" + 
			"    t10.blood_group,\n" + 
			"    t10.uniform_size,\n" + 
			"    t11.name AS name1,\n" + 
			"    t11.relation as relation1,\n" + 
			"    t11.dob AS dob1,\n" + 
			"    t11.occupation1,\n" + 
			"    t11.name2,\n" + 
			"    t11.relation2,\n" + 
			"    t11.dob2,\n" + 
			"    t11.occupation2,\n" + 
			"    t11.name3,\n" + 
			"    t11.relation3,\n" + 
			"    t11.dob3,\n" + 
			"    t11.occupation3,\n" + 
			"    t11.name4,\n" + 
			"    t11.relation4,\n" + 
			"    t11.dob4,\n" + 
			"    t11.occupation4,\n" + 
			"    t11.name5,\n" + 
			"    t11.relation5,\n" + 
			"    t11.dob5,\n" + 
			"    t11.occupation5,\n" + 
			"    t11.name6,\n" + 
			"    t11.relation6,\n" + 
			"    t11.dob6,\n" + 
			"    t11.occupation6,\n" + 
			"    t12.acc_no,\n" + 
			"    t12.ex_var1 as bank_name,\n" + 
			"    t13.gross_salary,\n" + 
			"    t13.basic,\n" + 
			"    t13.society_contribution,\n" + 
			"    t13.pf_applicable,\n" + 
			"    t13.pf_type,\n" + 
			"    t13.pf_emp_per,\n" + 
			"    t13.esic_applicable,\n" + 
			"    t13.mlwf_applicable,\n" + 
			"    t13.pt_applicable,\n" + 
			"    t13.sal_basis,\n" + 
			"    t13.epf_joining_date,\n" + 
			"    t13.cmp_joining_date,\n" + 
			"    t13.cmp_leaving_date,\n" + 
			"    t13.leaving_reason,\n" + 
			"    t13.leaving_reason_esic,\n" + 
			"    t13.leaving_reason_pf\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        emp.*\n" + 
			"    FROM\n" + 
			"        m_employees emp\n" + 
			"    WHERE\n" + 
			"        emp.emp_id = :empId\n" + 
			") t1\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_company.*\n" + 
			"    FROM\n" + 
			"        m_company\n" + 
			") t2\n" + 
			"ON\n" + 
			"    t1.cmp_code = t2.company_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_contractor.*\n" + 
			"    FROM\n" + 
			"        m_contractor\n" + 
			") t3\n" + 
			"ON\n" + 
			"    t1.contractor_id = t3.contractor_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_skill_rates.*\n" + 
			"    FROM\n" + 
			"        m_skill_rates\n" + 
			") t4\n" + 
			"ON\n" + 
			"    t1.ex_int2 = t4.skill_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_designation.*\n" + 
			"    FROM\n" + 
			"        m_designation\n" + 
			") t5\n" + 
			"ON\n" + 
			"    t1.designation_id = t5.desig_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_department.*\n" + 
			"    FROM\n" + 
			"        m_department\n" + 
			") t6\n" + 
			"ON\n" + 
			"    t1.depart_id = t6.depart_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_location.*\n" + 
			"    FROM\n" + 
			"        m_location\n" + 
			") t7\n" + 
			"ON\n" + 
			"    t1.location_id = t7.loc_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        tbl_mst_emp_types.*\n" + 
			"    FROM\n" + 
			"        tbl_mst_emp_types\n" + 
			") t8\n" + 
			"ON\n" + 
			"    t1.emp_type = t8.emp_type_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_access_role_name.*\n" + 
			"    FROM\n" + 
			"        m_access_role_name\n" + 
			") t9\n" + 
			"ON\n" + 
			"    t1.emp_category = t9.emp_type_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        tbl_emp_info.*\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			") t10\n" + 
			"ON\n" + 
			"    t1.emp_id = t10.emp_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        tbl_emp_nominees.*\n" + 
			"    FROM\n" + 
			"        tbl_emp_nominees\n" + 
			") t11\n" + 
			"ON\n" + 
			"    t1.emp_id = t11.emp_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        tbl_emp_bank_info.*\n" + 
			"    FROM\n" + 
			"        tbl_emp_bank_info\n" + 
			") t12\n" + 
			"ON\n" + 
			"    t1.emp_id = t12.emp_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        tbl_emp_salary_info.*\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			") t13\n" + 
			"ON\n" + 
			"    t1.emp_id = t13.emp_id", nativeQuery=true)
	public ViewEmployee getEmployeeDetails(@Param("empId") int empId);
}
