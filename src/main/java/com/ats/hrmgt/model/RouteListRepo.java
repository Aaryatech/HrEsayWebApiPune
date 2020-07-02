package com.ats.hrmgt.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RouteListRepo extends JpaRepository<RouteList, Integer> {

	List<RouteList> findByDelStatus(int i);

	@Query(value = "select * from m_route where id=:id", nativeQuery = true)
	RouteList findByRouteId(@Param("id")int id);

}
