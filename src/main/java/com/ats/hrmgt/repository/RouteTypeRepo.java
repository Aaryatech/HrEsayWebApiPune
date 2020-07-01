package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.RouteType;

public interface RouteTypeRepo extends JpaRepository<RouteType, Integer>{

	List<RouteType> findByDelStatus(int i);

	RouteType findByTypeId(int routeId);

	@Transactional
	@Modifying
	@Query("update RouteType set del_status=0  WHERE type_id=:typeId")
	int deleteRouteType(@Param("typeId") int typeId);

}
