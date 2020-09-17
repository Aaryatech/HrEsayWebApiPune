package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.OpeningPendingLeaveEmployeeList;

public interface OpeningPendingLeaveEmployeeListRepo extends JpaRepository<OpeningPendingLeaveEmployeeList, Integer>{

	@Query(value = "SELECT\n" + 
			"        data.*,ifnull(lv.leave_id,0) as leave_id,ifnull(lv.leave_num_days,0) as leave_num_days \n" + 
			"    from ( SELECT UUID() as id,\n" + 
			"        e.emp_id,\n" + 
			"        e.emp_code,\n" + 
			"        concat(e.first_name,\n" + 
			"        ' ',\n" + 
			"        e.surname) AS emp_name,\n" + 
			"        l.lvs_id,\n" + 
			"        lt.lv_title,\n" + 
			"        lsd.lv_type_id,\n" + 
			"        lsd.lvs_alloted_leaves     \n" + 
			"    FROM\n" + 
			"        m_employees e,\n" + 
			"        leave_structure_allotment lsa,\n" + 
			"        leave_structure_header l,\n" + 
			"        leave_structure_details lsd,\n" + 
			"        leave_type lt     \n" + 
			"    where\n" + 
			"        e.emp_id = lsa.emp_id              \n" + 
			"        AND lsa.cal_yr_id = :yearId             \n" + 
			"        AND lsa.lvs_id = l.lvs_id              \n" + 
			"        AND l.del_status = 1             \n" + 
			"        and lsd.lvs_id=l.lvs_id              \n" + 
			"        and lt.lv_type_id =lsd.lv_type_id             \n" + 
			"        and lsd.lv_type_id!=1             \n" + 
			"        and lsd.lv_type_id!=2             \n" + 
			"        AND e.del_status=1              \n" + 
			"        and e.location_id in (:locationId)     \n" + 
			"    order by\n" + 
			"        emp_id asc,\n" + 
			"        lv_type_id asc) data\n" + 
			"    left join (select leave_id,emp_id,lv_type_id,leave_num_days from leave_apply where leave_emp_reason='FOR OPENING' and cal_yr_id=:yearId) lv\n" + 
			"    on lv.emp_id=data.emp_id and lv.lv_type_id=data.lv_type_id", nativeQuery = true)
	List<OpeningPendingLeaveEmployeeList> getEmplistForOpeningLeave(int locationId, int yearId);

}
