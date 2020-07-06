package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.RoutePlanDetailWithName;

public interface RoutePlanDetailWithNameRepo extends JpaRepository<RoutePlanDetailWithName, Integer> {

	@Query(value = "select pd.*,emp.first_name,emp.surname from t_route_plan_detail pd,m_employees emp,t_route_plan_header ph where ph.plan_date=:date "
			+ "and pd.plan_head_id=ph.plan_head_id and emp.emp_id=pd.driver_id and emp.del_status=1", nativeQuery = true)
	List<RoutePlanDetailWithName> getDriverPlanList(@Param("date") String date);

	@Query(value = "select\n" + 
			"        pd.*,\n" + 
			"        concat(emp.first_name,' ',emp.surname) as first_name,\n" + 
			"        ph.plan_date as surname \n" + 
			"    from\n" + 
			"        t_route_plan_detail pd,\n" + 
			"        m_employees emp,\n" + 
			"        t_route_plan_header ph \n" + 
			"    where\n" + 
			"        ph.plan_date between :fromDate and :toDate \n" + 
			"        and pd.plan_head_id=ph.plan_head_id \n" + 
			"        and emp.emp_id=:empId and emp.emp_id=pd.driver_id \n" + 
			"        and emp.del_status=1", nativeQuery = true)
	List<RoutePlanDetailWithName> getDriverPlanListByEmpId(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("empId") int empId);

}
