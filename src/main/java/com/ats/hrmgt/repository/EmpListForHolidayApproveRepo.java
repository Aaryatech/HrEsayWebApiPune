package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.EmpListForHolidayApprove;

public interface EmpListForHolidayApproveRepo extends JpaRepository<EmpListForHolidayApprove, Integer>{

	
	@Query(value = "select oh.id,oh.emp_id,oh.holidate,oh.del_status,oh.holiday_id,oh.status,oh.remark,e.emp_code,concat(e.first_name,' ',e.surname) as "
			+ "emp_name,h.ex_var2 as holiday_name from t_optional_holiday oh,m_employees e,m_holiday h where oh.status in (:sts) and e.emp_id=oh.emp_id and "
			+ "e.location_id=:locId and h.holiday_id=oh.holiday_id", nativeQuery = true)
	List<EmpListForHolidayApprove> getOptionalHolidayApprovalList(int locId, List<Integer> sts);

	
	@Query(value = " select\n" + 
			"        oh.id,\n" + 
			"        oh.emp_id,\n" + 
			"        oh.holidate,\n" + 
			"        oh.del_status,\n" + 
			"        oh.holiday_id,\n" + 
			"        oh.status,\n" + 
			"        oh.remark,\n" + 
			"        e.emp_code,\n" + 
			"        concat(e.first_name,\n" + 
			"        ' ',\n" + 
			"        e.surname) as emp_name,\n" + 
			"        h.ex_var2 as holiday_name \n" + 
			"    from\n" + 
			"        t_optional_holiday oh,\n" + 
			"        m_employees e,\n" + 
			"        m_holiday h \n" + 
			"    where\n" + 
			"        oh.status in (:sts) \n" + 
			"        and e.emp_id=oh.emp_id \n" + 
			"        and e.emp_id=:empId \n" + 
			"        and h.holiday_id=oh.holiday_id\n" + 
			"        and oh.year_id=:yearId", nativeQuery = true)
	List<EmpListForHolidayApprove> getHistoryOptionalHoliday(int empId, int yearId, List<Integer> sts);


	@Query(value = " select\n" + 
			"        oh.id,\n" + 
			"        oh.emp_id,\n" + 
			"        oh.holidate,\n" + 
			"        oh.del_status,\n" + 
			"        oh.holiday_id,\n" + 
			"        oh.status,\n" + 
			"        oh.remark,\n" + 
			"        e.emp_code,\n" + 
			"        concat(e.first_name,\n" + 
			"        ' ',\n" + 
			"        e.surname) as emp_name,\n" + 
			"        h.ex_var2 as holiday_name \n" + 
			"    from\n" + 
			"        t_optional_holiday oh,\n" + 
			"        m_employees e,\n" + 
			"        m_holiday h \n" + 
			"    where\n" + 
			"        oh.status in (:sts) \n" + 
			"        and e.emp_id=oh.emp_id \n" + 
			"        and e.emp_id=:empId \n" + 
			"        and h.holiday_id=oh.holiday_id", nativeQuery = true)
	List<EmpListForHolidayApprove> getOptionalHolidayApprovalList(int empId, int sts);


	@Query(value = " select\n" + 
			"        oh.id,\n" + 
			"        oh.emp_id,\n" + 
			"        oh.holidate,\n" + 
			"        oh.del_status,\n" + 
			"        oh.holiday_id,\n" + 
			"        oh.status,\n" + 
			"        oh.remark,\n" + 
			"        e.emp_code,\n" + 
			"        concat(e.first_name,\n" + 
			"        ' ',\n" + 
			"        e.surname) as emp_name,\n" + 
			"        h.ex_var2 as holiday_name \n" + 
			"    from\n" + 
			"        t_optional_holiday oh,\n" + 
			"        m_employees e,\n" + 
			"        m_holiday h \n" + 
			"    where\n" + 
			"        oh.status in (:sts) \n" + 
			"        and e.emp_id=oh.emp_id and h.holiday_id=oh.holiday_id", nativeQuery = true)
	List<EmpListForHolidayApprove> getOptionalHolidayApprovalList(int sts);

}
