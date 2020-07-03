package com.ats.hrmgt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.RoutePlanDetail;

public interface RoutePlanDetailRepo extends JpaRepository<RoutePlanDetail, Integer> {

	@Transactional
	@Modifying
	@Query("update RoutePlanDetail set route_id=:routeId,isoffday_isff=:isFF,route_name=:rountName,fr_name=:frName,fr_ids=:frId,type_id=:typeId,km=:km,incentive=:incentive"
			+ "  WHERE plan_detail_id=:planDetailId")
	int updateRouteId(@Param("planDetailId") int planDetailId, @Param("isFF") int isFF, @Param("routeId") int routeId,
			@Param("rountName") String rountName, @Param("frName") String frName, @Param("frId") String frId,
			@Param("typeId") int typeId, @Param("km") int km, @Param("incentive") float incentive);

	@Transactional
	@Modifying
	@Query("update RoutePlanDetail set late_mark=:lateMark,late_min=:lateMin,start_time=:startTime  WHERE plan_detail_id=:planDetailId")
	int changeLateMarkInRoaster(@Param("planDetailId") int planDetailId, @Param("lateMark") int lateMark,
			@Param("lateMin") int lateMin, @Param("startTime") String startTime);

}
