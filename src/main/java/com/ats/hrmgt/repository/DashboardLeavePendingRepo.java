package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.DashboardLeavePending;

public interface DashboardLeavePendingRepo extends JpaRepository<DashboardLeavePending, Integer>{

	@Query(value = "SELECT\n" + 
			"         leave_data.*,b.initial_auth_name,c.final_auth_name,d.leave_title          \n" + 
			"    FROM\n" + 
			"        ( SELECT\n" + 
			"        la.leave_id, \n" + 
			"        la.emp_id,\n" + 
			"        la.lv_type_id, \n" + 
			"        la.leave_fromdt,\n" + 
			"        la.leave_todt, \n" + 
			"        la.leave_num_days,\n" + 
			"        CONCAT(e.surname,\n" + 
			"        \" \",\n" + 
			"        e.first_name) AS emp_name \n" + 
			"    FROM \n" + 
			"        leave_apply la,\n" + 
			"        m_employees e,\n" + 
			"        dm_cal_year cy\n" + 
			"    WHERE\n" + 
			"         e.emp_id=la.emp_id \n" + 
			"        and la.ex_int1 = 1  \n" + 
			"        and cy.cal_yr_id=la.cal_yr_id \n" + 
			"        AND cy.is_current =1\n" + 
			"         ) leave_data \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                la.emp_id,\n" + 
			"                la.ini_auth_emp_id,\n" + 
			"                CONCAT(e.surname,\n" + 
			"        \" \",\n" + 
			"        e.first_name) AS initial_auth_name \n" + 
			"            FROM\n" + 
			"                leave_authority la,\n" + 
			"                m_employees e\n" + 
			"            where \n" + 
			"                e.emp_id=la.ini_auth_emp_id \n" + 
			"        ) b              \n" + 
			"            on leave_data.emp_id=b.emp_id \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                la.emp_id,\n" + 
			"                la.fin_auth_emp_id,\n" + 
			"                CONCAT(e.surname,\n" + 
			"        \" \",\n" + 
			"        e.first_name) AS final_auth_name \n" + 
			"            FROM\n" + 
			"                leave_authority la,\n" + 
			"                m_employees e\n" + 
			"            where \n" + 
			"                e.emp_id=la.fin_auth_emp_id                         \n" + 
			"        ) c              \n" + 
			"            on leave_data.emp_id=c.emp_id\n" + 
			"        LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                lt.lv_type_id,\n" + 
			"                lt.lv_title AS leave_title \n" + 
			"            FROM\n" + 
			"                leave_type lt \n" + 
			"        ) d              \n" + 
			"            on d.lv_type_id=leave_data.lv_type_id", nativeQuery = true)
	List<DashboardLeavePending> getLeaveIntialApprovalListForDashBoard();

	@Query(value = "SELECT\n" + 
			"         leave_data.*,b.initial_auth_name,c.final_auth_name,d.leave_title          \n" + 
			"    FROM\n" + 
			"        ( SELECT\n" + 
			"        la.leave_id, \n" + 
			"        la.emp_id,\n" + 
			"        la.lv_type_id, \n" + 
			"        la.leave_fromdt,\n" + 
			"        la.leave_todt, \n" + 
			"        la.leave_num_days,\n" + 
			"        CONCAT(e.surname,\n" + 
			"        \" \",\n" + 
			"        e.first_name) AS emp_name \n" + 
			"    FROM \n" + 
			"        leave_apply la,\n" + 
			"        m_employees e,\n" + 
			"        dm_cal_year cy\n" + 
			"    WHERE\n" + 
			"         e.emp_id=la.emp_id \n" + 
			"        and la.ex_int1 = 2  \n" + 
			"        and cy.cal_yr_id=la.cal_yr_id \n" + 
			"        AND cy.is_current =1\n" + 
			"         ) leave_data \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                la.emp_id,\n" + 
			"                la.ini_auth_emp_id,\n" + 
			"                CONCAT(e.surname,\n" + 
			"        \" \",\n" + 
			"        e.first_name) AS initial_auth_name \n" + 
			"            FROM\n" + 
			"                leave_authority la,\n" + 
			"                m_employees e\n" + 
			"            where \n" + 
			"                e.emp_id=la.ini_auth_emp_id \n" + 
			"        ) b              \n" + 
			"            on leave_data.emp_id=b.emp_id \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                la.emp_id,\n" + 
			"                la.fin_auth_emp_id,\n" + 
			"                CONCAT(e.surname,\n" + 
			"        \" \",\n" + 
			"        e.first_name) AS final_auth_name \n" + 
			"            FROM\n" + 
			"                leave_authority la,\n" + 
			"                m_employees e\n" + 
			"            where \n" + 
			"                e.emp_id=la.fin_auth_emp_id                         \n" + 
			"        ) c              \n" + 
			"            on leave_data.emp_id=c.emp_id\n" + 
			"        LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                lt.lv_type_id,\n" + 
			"                lt.lv_title AS leave_title \n" + 
			"            FROM\n" + 
			"                leave_type lt \n" + 
			"        ) d              \n" + 
			"            on d.lv_type_id=leave_data.lv_type_id", nativeQuery = true)
	List<DashboardLeavePending> getLeaveFinalApprovalListForDashBoard();

}