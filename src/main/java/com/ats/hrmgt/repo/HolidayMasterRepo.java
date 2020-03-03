package com.ats.hrmgt.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.HolidayMaster;

public interface HolidayMasterRepo extends JpaRepository<HolidayMaster, Integer> {

	@Query(value = "select\n" + 
			"        *\n" + 
			"    from\n" + 
			"        holiday_master\n" + 
			"    where\n" + 
			"        holiday_master.del_status=:i order by holiday_date asc", nativeQuery = true)
	List<HolidayMaster> findByDelStatus(int i);

	HolidayMaster findByHolidayIdAndDelStatus(int holidayId, int i);

	@Transactional
	@Modifying
	@Query("update HolidayMaster set del_status=0  WHERE holiday_id=:holidayId")
	int deleteHoliday(int holidayId);

	
	
	@Query(value = "SELECT\n" + 
			"    *\n" + 
			"FROM\n" + 
			"    holiday_master\n" + 
			"WHERE\n" + 
			"    holiday_master.holiday_date BETWEEN :currDate AND DATE_ADD(:currDate, INTERVAL 30 DAY) AND holiday_master.del_status = 1\n" + 
			"ORDER BY\n" + 
			"    holiday_master.holiday_date ASC", nativeQuery = true)
	List<HolidayMaster> getHolidaysForDash(@Param("currDate") String currDate);

}
