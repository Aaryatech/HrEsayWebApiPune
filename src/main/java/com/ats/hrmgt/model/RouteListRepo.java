package com.ats.hrmgt.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteListRepo extends JpaRepository<RouteList, Integer> {

	List<RouteList> findByDelStatus(int i);

}
