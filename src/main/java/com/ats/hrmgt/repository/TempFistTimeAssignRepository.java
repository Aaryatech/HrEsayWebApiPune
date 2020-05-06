package com.ats.hrmgt.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.TempFistTimeAssign;

public interface TempFistTimeAssignRepository extends JpaRepository<TempFistTimeAssign, Integer>{

	@Transactional
	@Modifying
	@Query("update TempFistTimeAssign set shift_id=:shiftId  WHERE id=:id")
	int updateshift(@Param("id")int id, @Param("shiftId") int shiftId);

	
	@Transactional
	@Modifying
	@Query("delete from TempFistTimeAssign where date=:date and extra1=:locId")
	void deleterecord(int locId, Date date); 

}
