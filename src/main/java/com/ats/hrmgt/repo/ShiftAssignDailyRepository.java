package com.ats.hrmgt.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.ShiftAssignDaily;

public interface ShiftAssignDailyRepository extends JpaRepository<ShiftAssignDaily, Integer> {

	@Query(value = "select * from t_shift_assign_daily where shift_date between :fromDate and :toDate", nativeQuery = true)
	List<ShiftAssignDaily> shiftAssignDailyList(String fromDate, String toDate);

	@Query(value = "select * from t_shift_assign_daily where shift_date between :fromDate and :toDate and emp_id=:empId", nativeQuery = true)
	List<ShiftAssignDaily> shiftAssignDailyListById(String fromDate, String toDate, int empId);

	
	@Transactional
	@Modifying
	@Query(value="UPDATE t_shift_assign_daily SET shift_id=:shiftId WHERE shift_date between :fromDate and :toDate and emp_id in (:empIdList)",nativeQuery=true)
	int updateAssignShiftByDate(List<Integer> empIdList, String fromDate, String toDate, int shiftId);

}
