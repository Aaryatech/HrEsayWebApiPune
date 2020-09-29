package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.CountData;

public interface CountDataRepository extends JpaRepository<CountData, Integer>{

	@Query(value="select UUID() as id,\n" + 
			"(select count('') from tbl_attt_daily_daily d, m_employees e where d.att_date = :date and d.lv_sumup_id in (5,13,14,15,20,21,18) and e.emp_id=d.emp_id and e.location_id in (:locId)) as present,\n" + 
			"(select count('') from tbl_attt_daily_daily d, m_employees e where d.att_date = :date and d.lv_sumup_id in (22) and e.emp_id=d.emp_id and e.location_id in (:locId)) as absent,\n" + 
			"(select count('') from tbl_attt_daily_daily d, m_employees e where d.att_date = :date and d.lv_sumup_id in (7,11) and e.emp_id=d.emp_id and e.location_id in (:locId)) as leavecount,\n" + 
			"(select count('') from tbl_attt_daily_daily d, m_employees e where d.att_date = :date and late_mark=1 and e.emp_id=d.emp_id and e.location_id in (:locId)) as latemark", nativeQuery=true)
	CountData countData(int locId, String date);

}
