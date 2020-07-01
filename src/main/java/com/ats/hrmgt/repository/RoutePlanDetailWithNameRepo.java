package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.RoutePlanDetailWithName;

public interface RoutePlanDetailWithNameRepo extends JpaRepository<RoutePlanDetailWithName, Integer> {

	@Query(value = "select pd.*,emp.first_name,emp.surname from t_route_plan_detail pd,m_employees emp,t_route_plan_header ph where ph.plan_date=:date "
			+ "and pd.plan_head_id=ph.plan_head_id and emp.emp_id=pd.driver_id", nativeQuery = true)
	List<RoutePlanDetailWithName> getDriverPlanList(@Param("date") String date);

}
