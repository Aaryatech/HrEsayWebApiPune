package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetDetailForGraduaty;

public interface GetDetailForGraduatyRepo extends JpaRepository<GetDetailForGraduaty, Integer>{

	@Query(value = "select\r\n" + 
			"        uuid() as uuid,\r\n" + 
			"        coalesce((select\r\n" + 
			"            sf.basic \r\n" + 
			"        from\r\n" + 
			"            tbl_emp_salary_info sf \r\n" + 
			"        where\r\n" + 
			"            sf.emp_id=e.emp_id ),\r\n" + 
			"        0) as basic,\r\n" + 
			"        coalesce(sum(es.allowance_value),\r\n" + 
			"        0) as allowance_value,\r\n" + 
			"        ei.cmp_joining_date,\r\n" + 
			"        TIMESTAMPDIFF(YEAR,cmp_joining_date,CURDATE()) AS service\r\n" + 
			"    from\r\n" + 
			"        m_employees e,\r\n" + 
			"        emp_sal_allowance es,\r\n" + 
			"        tbl_emp_salary_info ei\r\n" + 
			"    where\r\n" + 
			"        e.emp_id=:empId           \r\n" + 
			"        and es.emp_id=e.emp_id\r\n" + 
			"        and es.allowance_id=1 \r\n" + 
			"        and ei.emp_id=e.emp_id ", nativeQuery = true)
	GetDetailForGraduaty getdetailforgraduaty(@Param("empId") int empId);

}