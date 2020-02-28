package com.ats.hrmgt.claim.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.claim.GetClaimAuthority;

 
public interface GetClaimAuthorityRepo extends JpaRepository<GetClaimAuthority, Integer> {

	@Query(value = " SELECT\n" + 
			"        auth.*  ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.first_name      \n" + 
			"        FROM\n" + 
			"            m_employees  e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.emp_id),\n" + 
			"        null) as emp_fname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.middle_name     \n" + 
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
			"            AND e.emp_id=auth.ca_ini_auth_emp_id),\n" + 
			"        null) as ini_emp_fname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.middle_name \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.ca_ini_auth_emp_id),\n" + 
			"        null) as ini_emp_mname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.surname \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.ca_ini_auth_emp_id),\n" + 
			"        null) as ini_emp_sname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.emp_code \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.ca_ini_auth_emp_id),\n" + 
			"        null) as ini_emp_code    ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.first_name \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.ca_fin_auth_emp_id),\n" + 
			"        null) as fini_emp_fname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.middle_name \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.ca_fin_auth_emp_id),\n" + 
			"        null) as fini_emp_mname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.surname \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.ca_fin_auth_emp_id),\n" + 
			"        null) as fini_emp_sname ,\n" + 
			"        coalesce((SELECT\n" + 
			"            e.surname \n" + 
			"        FROM\n" + 
			"            m_employees e \n" + 
			"        WHERE\n" + 
			"            auth.del_status=1 \n" + 
			"            AND e.emp_id=auth.ca_fin_auth_emp_id),\n" + 
			"        null) as fini_emp_code  \n" + 
			"    FROM\n" + 
			"        claim_authority auth ,\n" + 
			"        m_employees ei \n" + 
			"    WHERE\n" + 
			"        auth.del_status=1 \n" + 
			"        AND auth.company_id=:companyId \n" + 
			"        AND  ei.emp_id=auth.emp_id \n" + 
			"        AND ei.location_id IN(\n" + 
			"           :locIdList \n" + 
			"        ) \n" + 
			"        AND ei.del_status=1"
			+ " ", nativeQuery = true)

	List<GetClaimAuthority> getClaimAuth(@Param("companyId") int companyId,
			@Param("locIdList") List<Integer> locIdList);

}
