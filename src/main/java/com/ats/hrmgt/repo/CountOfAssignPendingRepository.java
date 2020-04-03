package com.ats.hrmgt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.CountOfAssignPending;

public interface CountOfAssignPendingRepository extends JpaRepository<CountOfAssignPending, Integer>{

	@Query(value = "select uuid() as id,coalesce((select count('') from m_employees where emp_type=0 and del_status=1),0) as emp_type_count,coalesce((select count('') from m_employees where current_shiftid=0 and del_status=1),0) as shift_count,coalesce((select count('') from m_employees where location_id=0 and del_status=1),0) as location_count,coalesce((select count('') from m_employees where holiday_category=0 and del_status=1),0) as holiday_count,coalesce((select count('') from m_employees where weekend_category=0 and del_status=1),0) as weekend_count", nativeQuery = true)
	CountOfAssignPending getCountOfAssignForAttendance();

}
