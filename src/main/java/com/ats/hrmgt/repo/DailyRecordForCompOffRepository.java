package com.ats.hrmgt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.DailyDailyInformation;
import com.ats.hrmgt.model.DailyRecordForCompOff;

public interface DailyRecordForCompOffRepository extends JpaRepository<DailyRecordForCompOff, Integer>{

	@Query(value = "SELECT\n" + 
			"         id,att_date,emp_id,att_status,lv_sumup_id\n" + 
			"    from\n" + 
			"        tbl_attt_daily_daily dl \n" + 
			"     where \n" + 
			"        emp_id = :empId and lv_sumup_id in (13,14,18) and att_date between :fromDate and :toDate and multiple_entries=0 ", nativeQuery = true)
	List<DailyRecordForCompOff> dailyrecordlistforcompoff(String fromDate, String toDate, int empId);

}
