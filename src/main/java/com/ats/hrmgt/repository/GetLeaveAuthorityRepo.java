package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetLeaveAuthority;
 

public interface GetLeaveAuthorityRepo extends JpaRepository<GetLeaveAuthority, Integer> {

	@Query(value = "SELECT\n" + 
			"        auth.*  ,\n" + 
			"        coalesce((SELECT\n" + 
			"            group_concat(DISTINCT e.first_name) \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"             \n" + 
			"              find_in_set(e.emp_id,auth.rep_to_emp_ids)),\n" + 
			"        null) as re_porting_name ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.first_name  \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.emp_id),\n" + 
			"        null) as emp_fname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.middle_name \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.emp_id),\n" + 
			"        null) as emp_mname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.surname \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.emp_id),\n" + 
			"        null) as emp_sname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.emp_code \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.emp_id),\n" + 
			"        null) as emp_code  ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.first_name \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.ini_auth_emp_id),\n" + 
			"        null) as ini_emp_fname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.middle_name \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.ini_auth_emp_id),\n" + 
			"        null) as ini_emp_mname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.surname \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.ini_auth_emp_id),\n" + 
			"        null) as ini_emp_sname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.emp_code \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.ini_auth_emp_id),\n" + 
			"        null) as ini_emp_code    ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.first_name \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.fin_auth_emp_id),\n" + 
			"        null) as fini_emp_fname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.middle_name \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.fin_auth_emp_id),\n" + 
			"        null) as fini_emp_mname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.surname \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.fin_auth_emp_id),\n" + 
			"        null) as fini_emp_sname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.surname \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.fin_auth_emp_id),\n" + 
			"        null) as fini_emp_code  \n" + 
			"    FROM\n" + 
			"        leave_authority auth ,\n" + 
			"        m_employees ei \n" + 
			"    WHERE\n" + 
			"        auth.del_status=1 \n" + 
			"        AND auth.company_id=1\n" + 
			"        AND  ei.emp_id=auth.emp_id ", nativeQuery = true)

	List<GetLeaveAuthority> getLeaveAuth();

	@Query(value = " SELECT concat (surname,' ',first_name) as name FROM m_employees WHERE m_employees.emp_id in (:reportIds) ", nativeQuery = true)
	List<String> getEmpReportingName(@Param("reportIds") String[] reportIds);

	/*
	 * SELECT auth.* ,coalesce((SELECT e.emp_fname FROM emp_info e WHERE
	 * auth.del_status=1 AND e.emp_id=auth.emp_id),null) as emp_fname
	 * ,coalesce((SELECT e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.emp_id),null) as emp_mname ,coalesce((SELECT e.emp_sname FROM
	 * emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.emp_id),null) as
	 * emp_sname ,coalesce((SELECT e.emp_code FROM emp_info e WHERE
	 * auth.del_status=1 AND e.emp_id=auth.emp_id),null) as emp_code
	 * 
	 * ,coalesce((SELECT e.emp_fname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.ini_auth_emp_id),null) as ini_emp_fname ,coalesce((SELECT
	 * e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.ini_auth_emp_id),null) as ini_emp_mname ,coalesce((SELECT
	 * e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.ini_auth_emp_id),null) as ini_emp_sname ,coalesce((SELECT
	 * e.emp_code FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.ini_auth_emp_id),null) as ini_emp_code
	 * 
	 * 
	 * 
	 * ,coalesce((SELECT e.emp_fname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.fin_auth_emp_id),null) as fini_emp_fname ,coalesce((SELECT
	 * e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.fin_auth_emp_id),null) as fini_emp_mname ,coalesce((SELECT
	 * e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.fin_auth_emp_id),null) as fini_emp_sname ,coalesce((SELECT
	 * e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.fin_auth_emp_id),null) as fini_emp_code
	 * 
	 * FROM leave_authority auth WHERE auth.del_status=1 AND auth.company_id=1
	 * 
	 */

}