package com.ats.hrmgt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.RoutePlanDetail; 

public interface RoutePlanDetailRepo extends JpaRepository<RoutePlanDetail, Integer>{

	@Transactional
	@Modifying
	@Query("update RoutePlanDetail set route_id=:routeId,isoffday_isff=:isFF  WHERE plan_detail_id=:planDetailId")
	int updateRouteId(@Param("planDetailId") int planDetailId,@Param("isFF") int isFF,@Param("routeId") int routeId); 

}
