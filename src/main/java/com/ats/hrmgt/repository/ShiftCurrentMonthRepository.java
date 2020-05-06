package com.ats.hrmgt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.ShiftCurrentMonth;

public interface ShiftCurrentMonthRepository extends JpaRepository<ShiftCurrentMonth, Integer>{

	ShiftCurrentMonth findByIsCurrentAndLocId(int i, int locId);

	@Transactional
	@Modifying
	@Query("update ShiftCurrentMonth set is_current=0  WHERE id=:id")
	int updateIsCurrent(int id);

	/*@Transactional
	@Modifying
	@Query("delete from ShiftCurrentMonth where loc_id=:locId and date=:date")
	void deleteByLocId(int locId, String date);*/

}
