package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.Holiday;

public interface HolidayRepo extends JpaRepository<Holiday, Integer> {

	Holiday findByHolidayIdAndDelStatus(int holidayId, int i);

	@Transactional
	@Modifying
	@Query("update Holiday set del_status=0  WHERE holiday_id=:holidayId")
	int deleteHoliday(@Param("holidayId") int holidayId);

	@Query(value = "select w.* from m_holiday w,m_employees e where  FIND_IN_SET(e.location_id,w.loc_id)  and e.emp_id=:empId and w.del_status=1 and "
			+ "w.cal_yr_id=(select cal_yr_id from dm_cal_year where is_current=1) "
			+ "and holiday_fromdt>=:fromDate and holiday_todt<=:toDate", nativeQuery = true)
	List<Holiday> getHolidayByEmpIdAndFromDateTodate(@Param("empId") int empId, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate);

	@Query(value = "select * from m_holiday where holiday_fromdt between :fromDate and :toDate and m_holiday.cal_yr_id=(select cal_yr_id from dm_cal_year where is_current=1) "
			+ "and del_status=1 and ex_int3=1", nativeQuery = true)
	List<Holiday> getholidaybetweendate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	List<Holiday> findByExInt1AndDelStatus(int hoCatId, int i);

	@Query(value = "select * from m_holiday where holiday_fromdt in (:dates) and ex_int1=:catId and del_status=1", nativeQuery = true)
	List<Holiday> getHolidayListByDates(List<String> dates, int catId);

	@Transactional
	@Modifying
	@Query("update Holiday set del_status=0  WHERE cal_yr_id=:yearId and ex_int1=:catid")
	int deleteHolidayByGroup(@Param("yearId")int yearId, @Param("catid") int catid);

	@Query(value = "select * from m_holiday where holiday_fromdt>=:date and del_status=1 and ex_int1=:catId and cal_yr_id=:yearId and ex_int3=2 "
			+ "and holiday_fromdt not in (select holidate from t_optional_holiday where emp_id=:empId and (status=0 or status=1) and year_id=cal_yr_id)", nativeQuery = true)
	List<Holiday> getHolidayListforoptionalHoliday(@Param("date") String date,@Param("yearId") int yearId, @Param("catId") int catId,@Param("empId") int empId);

	@Query(value = "select * from m_holiday where cal_yr_id=:yearId and ex_int1=:catId and del_status=1", nativeQuery = true)
	List<Holiday> getHolidayByYearIdAndCateId(int yearId, int catId);

}
