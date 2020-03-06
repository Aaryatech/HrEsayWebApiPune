package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.LeaveAuthority;
import com.ats.hrmgt.model.LeaveHistory;
 

public interface LeaveAuthorityRepository extends JpaRepository<LeaveAuthority, Integer> {

	List<LeaveAuthority> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update LeaveAuthority set del_status=0  WHERE la_pkey=:laPkey")
	int deleteLeaveAuthority(@Param("laPkey") int laPkey);

	LeaveAuthority findByLaPkeyAndDelStatus(int laPkey, int i);

	LeaveAuthority findByDelStatusAndEmpId(int i, int empId);
	
	

	 
	
	
	@Query(value = " SELECT\n" + 
			"    *\n" + 
			"FROM\n" + 
			"    leave_authority\n" + 
			"WHERE\n" + 
			"    :empId IN(\n" + 
			"        leave_authority.ini_auth_emp_id\n" + 
			"    ) OR :empId IN(\n" + 
			"        leave_authority.fin_auth_emp_id AND  leave_authority.del_status=1 \n" + 
			"    )", nativeQuery = true)
  	List<LeaveAuthority> chkAuth(@Param("empId") int empId);


}
