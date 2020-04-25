package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.ats.hrmgt.model.MailByUsername;

public interface MailByUsernameRepo extends JpaRepository<MailByUsername, Integer>{

	@Query(value = "select e.emp_id,e.email from tbl_emp_info e,m_user u where u.user_name=:inputValue and u.emp_id=e.emp_id", nativeQuery = true)
	MailByUsername getUserByEmailId(@Param("inputValue") String inputValue);
	
}
