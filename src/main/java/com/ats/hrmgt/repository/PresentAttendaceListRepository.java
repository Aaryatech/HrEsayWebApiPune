package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.PresentAttendaceList;

public interface PresentAttendaceListRepository extends JpaRepository<PresentAttendaceList, Integer>{
	@Query(value=" select UUID() as id,e.emp_id,concat(e.first_name,' ',e.surname) as emp_name,d.att_status,late_min from tbl_attt_daily_daily d, "
			+ "m_employees e where d.att_date = :date and d.lv_sumup_id in (5,13,14,15,20,21,18) and e.emp_id=d.emp_id and e.location_id in (:locId) ", nativeQuery=true)
	 List<PresentAttendaceList> presentList(int locId, String date);

}
