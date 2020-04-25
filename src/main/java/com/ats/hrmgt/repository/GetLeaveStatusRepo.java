package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetLeaveStatus;
 


public interface GetLeaveStatusRepo extends JpaRepository<GetLeaveStatus, Integer> {

	
	@Query(value = "SELECT\n" + 
			"  \n" + 
			"    e.first_name as emp_fname,\n" + 
			"    e.middle_name as emp_mname, e.surname as emp_sname,\n" + 
			"    '' as emp_photo,   \n" + 
			"    t.emp_remarks,\n" + 
			"    t.trail_pkey,\n" + 
			"    t.emp_id,\n" + 
			"    t.leave_id,\n" + 
			"    t.leave_status,t.maker_user_id,\n" + 
			"    t.maker_enter_datetime , COALESCE(         (         SELECT\n" + 
			"            DISTINCT             CONCAT(                 e.first_name,\n" + 
			"            \" \",\n" + 
			"            e.middle_name,\n" + 
			"            \" \",\n" + 
			"            e.surname             ) AS user_name                  \n" + 
			"        FROM\n" + 
			"            m_employees AS e,\n" + 
			"            m_user u,\n" + 
			"            leave_apply l                  \n" + 
			"        WHERE\n" + 
			"            u.user_id = t.maker_user_id              \n" + 
			"            AND e.emp_id = u.emp_id     ),\n" + 
			"        NULL     ) AS user_name FROM\n" + 
			"    m_employees AS e,\n" + 
			"    leave_trail AS t \n" + 
			"WHERE\n" + 
			"    e.emp_id = t.emp_id  and t.leave_id=:leaveId ORDER BY t.trail_pkey DESC", nativeQuery = true)
	List<GetLeaveStatus> getLeaveTrailByLeaveId(@Param("leaveId") int leaveId);


	@Query(value = "SELECT\n" + 
			"        e.first_name as emp_fname,\n" + 
			"        e.middle_name as emp_mname,\n" + 
			"        e.surname as emp_sname,\n" + 
			"        \" \" as emp_photo,\n" + 
			"        t.emp_remarks,\n" + 
			"        t.trail_pkey,\n" + 
			"        t.emp_id,\n" + 
			"        t.leave_id,\n" + 
			"        t.leave_status,\n" + 
			"        t.maker_user_id,\n" + 
			"        t.maker_enter_datetime,\n" + 
			"        COALESCE(         (         SELECT\n" + 
			"            DISTINCT             CONCAT(                 e.first_name,\n" + 
			"            \" \",\n" + 
			"            e.middle_name,\n" + 
			"            \" \",\n" + 
			"            e.surname             ) AS user_name         \n" + 
			"        FROM\n" + 
			"            m_employees AS e,\n" + 
			"            m_user u,\n" + 
			"            leave_apply l         \n" + 
			"        WHERE\n" + 
			"            u.user_id = t.maker_user_id \n" + 
			"            AND e.emp_id = u.emp_id     ),\n" + 
			"        NULL     ) AS user_name \n" + 
			"    FROM\n" + 
			"        m_employees AS e,\n" + 
			"        leave_trail AS t \n" + 
			"    WHERE\n" + 
			"        e.emp_id = t.emp_id \n" + 
			"        AND t.leave_id =:leaveId", nativeQuery = true)
	List<GetLeaveStatus> getEmpInfoByLeaveId(@Param("leaveId") int leaveId);
	


}
