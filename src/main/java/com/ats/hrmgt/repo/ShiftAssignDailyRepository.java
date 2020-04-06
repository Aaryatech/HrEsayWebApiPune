package com.ats.hrmgt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.ShiftAssignDaily;

public interface ShiftAssignDailyRepository extends JpaRepository<ShiftAssignDaily, Integer>{

	@Query(value = "select * from t_shift_assign_daily where shift_date between :fromDate and :toDate", nativeQuery = true)
	List<ShiftAssignDaily> shiftAssignDailyList(String fromDate, String toDate);

	@Query(value = "select * from t_shift_assign_daily where shift_date between :fromDate and :toDate and emp_id=:empId", nativeQuery = true)
	List<ShiftAssignDaily> shiftAssignDailyListById(String fromDate, String toDate, int empId);

}
