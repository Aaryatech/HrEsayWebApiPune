package com.ats.hrmgt.repository;

import java.util.List;

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

	@Query(value = "select pd.plan_detail_id,pd.plan_head_id,pd.route_id,pd.driver_id,pd.isoffday_isff,pd.type_id,pd.route_name,pd.fr_name,pd.fr_ids,pd.late_mark,pd.late_min,"
			+ "pd.start_time,pd.km,pd.incentive,pd.del_status,pd.extra_int1,ph.is_confirm as extra_int2,ph.plan_date as extra_var1,pd.extra_var2 from t_route_plan_header ph,"
			+ "t_route_plan_detail pd where plan_date between :fromDate and :toDate and pd.plan_head_id=ph.plan_head_id   ", nativeQuery = true)
	List<RoutePlanDetail> getListForMonthlySheet(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "select pd.plan_detail_id,pd.plan_head_id,pd.route_id,pd.driver_id,pd.isoffday_isff,pd.type_id,pd.route_name,pd.fr_name,pd.fr_ids,pd.late_mark,pd.late_min,"
			+ "pd.start_time,pd.km,pd.incentive,pd.del_status,pd.extra_int1,ph.is_confirm as extra_int2,ph.plan_date as extra_var1,pd.extra_var2 from t_route_plan_header ph,"
			+ "t_route_plan_detail pd where plan_date between :fromDate and :toDate and pd.plan_head_id=ph.plan_head_id  order by pd.driver_id asc,extra_var1 asc  ", nativeQuery = true)
	List<RoutePlanDetail> getListForNotification(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Transactional
	@Modifying
	@Query("update RoutePlanDetail set route_name=:rountName,incentive=:incentive WHERE plan_detail_id=:planDetailId")
	int updateRouteName(@Param("planDetailId") int planDetailId, @Param("rountName") String rountName,
			@Param("incentive") float incentive);

}
