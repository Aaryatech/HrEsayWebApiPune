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
	List<HolidayMaster> findByDelStatusOrder(int i);

	HolidayMaster findByHolidayIdAndDelStatus(int holidayId, int i);

	@Transactional
	@Modifying
	@Query("update HolidayMaster set del_status=0  WHERE holiday_id=:holidayId")
	int deleteHoliday(int holidayId);

	
	
	@Query(value = "SELECT\n" + 
			"        holiday_id,\n" + 
			"        holiday_fromdt as holiday_date,\n" + 
			"        ex_var2 as holiday_name,\n" + 
			"        0 as del_status\n" + 
			"    FROM\n" + 
			"        m_holiday \n" + 
			"    WHERE\n" + 
			"        m_holiday.holiday_fromdt BETWEEN '2020-09-11' AND DATE_ADD('2020-09-11', INTERVAL 30 DAY) \n" + 
			"        AND m_holiday.del_status = 1\n" + 
			"    group by \n" + 
			"        m_holiday.ex_int2\n" + 
			"    ORDER BY\n" + 
			"        m_holiday.holiday_fromdt ASC", nativeQuery = true)
	List<HolidayMaster> getHolidaysForDash(@Param("currDate") String currDate);

	@Query(value = "select count('') as count from m_holiday where del_status=1 and ex_int1=:catId and cal_yr_id=:yearId", nativeQuery = true)
	String getcountofholidaybyyear(@Param("catId")int catId,@Param("yearId") int yearId);

	@Query(value = "	SELECT count(*) FROM holiday_master WHERE DAY(holiday_master.holiday_date)=DAY(:holidayDate) AND MONTH(holiday_master.holiday_date)=MONTH(:holidayDate) AND  del_status=1\n" + 
			"", nativeQuery = true)
	Integer getCountOfHolidayByDate(@Param("holidayDate") String holidayDate);

		
	@Query(value = "	SELECT count(*) FROM holiday_master WHERE DAY(holiday_master.holiday_date)=DAY(:holidayDate) AND MONTH(holiday_master.holiday_date)=MONTH(:holidayDate) and holiday_id!=:holidayId and del_status=1\n" + 
			"", nativeQuery = true)
	Integer getCountOfHolidayByDateForEdit(@Param("holidayDate") String holidayDate,@Param("holidayId") int holidayId);

}
