package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.OptionalHoliday;

public interface OptionalHolidayRepo extends JpaRepository<OptionalHoliday, Integer>{

	
	@Transactional
	@Modifying
	@Query("update OptionalHoliday set status=:sts,approved_by=:userId,approved_daetime=:date  WHERE id in (:ids)")
	int updateStsOfOptionalHoliday(String date, int sts, int userId, List<Integer> ids);

}
