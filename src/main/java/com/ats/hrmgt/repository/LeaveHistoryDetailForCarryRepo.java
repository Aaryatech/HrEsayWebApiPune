package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.LeaveHistoryDetailForCarry;

public interface LeaveHistoryDetailForCarryRepo extends JpaRepository<LeaveHistoryDetailForCarry, Integer>{

	
	
	@Query(value = "SELECT\n" + 
			"        uuid() as id,m_employees.emp_id,leave_type.lv_type_id,\n" + 
			"        leave_type.lv_title_short,\n" + 
			"        leave_type.lv_title,\n" + 
			"        leave_structure_details.lvs_alloted_leaves,\n" + 
			"        leave_structure_header.lvs_id, \n" + 
			"        leave_structure_details.max_accumulate_carryforward,\n" + 
			"        leave_structure_details.is_carryforward,\n" + 
			"        leave_structure_details.max_carryforward,\n" + 
			"        leave_structure_details.ex_int1 as is_in_cash,\n" + 
			"        coalesce((select\n" + 
			"            b.op_bal                   \n" + 
			"        from\n" + 
			"            leave_balance_cal b                   \n" + 
			"        where\n" + 
			"            b.emp_id=m_employees.emp_id                           \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id                           \n" + 
			"            and b.cal_yr_id=leave_structure_allotment.cal_yr_id),\n" + 
			"        0) as bal_leave,\n" + 
			"        coalesce((select\n" + 
			"            sum(b.leave_num_days)                   \n" + 
			"        from\n" + 
			"            leave_apply b                   \n" + 
			"        where\n" + 
			"            b.emp_id=m_employees.emp_id                           \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id                           \n" + 
			"            and b.cal_yr_id=leave_structure_allotment.cal_yr_id                           \n" + 
			"            and b.ex_int1=3),\n" + 
			"        0) as saction_leave,\n" + 
			"        coalesce((select\n" + 
			"            sum(b.leave_num_days)                   \n" + 
			"        from\n" + 
			"            leave_apply b                    \n" + 
			"        where\n" + 
			"            b.emp_id=m_employees.emp_id                           \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id                           \n" + 
			"            and b.cal_yr_id=leave_structure_allotment.cal_yr_id                           \n" + 
			"            and b.ex_int1 in (1,2)),\n" + 
			"        0) as apllied_leaeve,\n" + 
			"        leave_structure_header.lvs_name          \n" + 
			"    FROM\n" + 
			"        leave_type,\n" + 
			"        m_employees,\n" + 
			"        leave_structure_header,\n" + 
			"        leave_structure_details,\n" + 
			"        leave_structure_allotment           \n" + 
			"    WHERE\n" + 
			"        m_employees.emp_id = leave_structure_allotment.emp_id                   \n" + 
			"        AND leave_structure_allotment.lvs_id = leave_structure_header.lvs_id                   \n" + 
			"        AND leave_structure_header.lvs_id = leave_structure_details.lvs_id                   \n" + 
			"        AND leave_structure_details.lv_type_id = leave_type.lv_type_id \n" + 
			"        and leave_structure_allotment.cal_yr_id=(\n" + 
			"            select\n" + 
			"                max(cal_yr_id-1) as cal_yr_id              \n" + 
			"            from\n" + 
			"                dm_cal_year               \n" + 
			"            where\n" + 
			"                is_current=1         \n" + 
			"        ) and m_employees.emp_id not in (\n" + 
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
			"            and  m_employees.location_id=:locId order by emp_id,lv_type_id ", nativeQuery = true)
	List<LeaveHistoryDetailForCarry> getPreviousleaveHistoryForCarryFrwd(@Param("locId") int locId);

}
