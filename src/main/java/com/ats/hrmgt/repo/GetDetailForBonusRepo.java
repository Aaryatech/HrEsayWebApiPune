package com.ats.hrmgt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetDetailForBonus;

public interface GetDetailForBonusRepo extends JpaRepository<GetDetailForBonus, Integer> {

	@Query(value = "select id,emp_id,sum(present_days) as presentdays ,sum(weekly_off) as weeklyoff ,sum(paid_holiday) as holiday "
			+ "from tbl_attt_summary_daily where emp_id=:empId and month between :fromMonth and :toMonth and year between :fromYear and :toYear", nativeQuery = true)
	GetDetailForBonus getbonuscalDetails(@Param("empId") int empId, @Param("fromMonth") int fromMonth,
			@Param("toMonth") int toMonth, @Param("fromYear") int fromYear, @Param("toYear") int toYear);

}
