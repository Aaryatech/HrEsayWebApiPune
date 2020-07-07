package com.ats.hrmgt.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoasterSummeryDetailRepository extends JpaRepository<RoasterSummeryDetail, Integer>{

	
	@Query(value = "SELECT\n" + 
			"        e.emp_id,\n" + 
			"        e.emp_code,\n" + 
			"        e.first_name,\n" + 
			"        e.middle_name,\n" + 
			"        e.surname,\n" + 
			"        ifnull((select count('') from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and pd.isoffday_isff=1 and ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as off_day_count,\n" + 
			"        ifnull((select count('') from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and pd.isoffday_isff=2 and ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as ff_count,\n" + 
			"        ifnull((select count('') from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and pd.late_mark=1 and ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as late_mark,\n" + 
			"        ifnull((select sum(pd.late_min) from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and  ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as late_min,\n" + 
			"        ifnull((select sum(pd.incentive) from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and  ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as incentive,\n" + 
			"        ifnull((select sum(pd.km) from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and  ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as km\n" + 
			"    FROM\n" + 
			"        m_employees e,\n" + 
			"        tbl_emp_salary_info emp_sal \n" + 
			"    where\n" + 
			"        e.emp_id=emp_sal.emp_id \n" + 
			"        and e.del_status=1 \n" + 
			"        and e.designation_id=:design \n" + 
			"        and (\n" + 
			"            emp_sal.cmp_leaving_date IS NULL \n" + 
			"            or emp_sal.cmp_leaving_date='' \n" + 
			"            or emp_sal.cmp_leaving_date=1970-00-00 \n" + 
			"            or  date_format(emp_sal.cmp_leaving_date,'%Y-%m')>=date_format(:fromDate,'%Y-%m')\n" + 
			"        )", nativeQuery = true)
	List<RoasterSummeryDetail> getRoasterSummeryDetail(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("design") int design);

	@Query(value = "SELECT\n" + 
			"        e.emp_id,\n" + 
			"        e.emp_code,\n" + 
			"        e.first_name,\n" + 
			"        e.middle_name,\n" + 
			"        e.surname,\n" + 
			"        ifnull((select count('') from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and pd.isoffday_isff=1 and ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as off_day_count,\n" + 
			"        ifnull((select count('') from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and pd.isoffday_isff=2 and ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as ff_count,\n" + 
			"        ifnull((select count('') from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and pd.late_mark=1 and ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as late_mark,\n" + 
			"        ifnull((select sum(pd.late_min) from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and  ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as late_min,\n" + 
			"        ifnull((select sum(pd.incentive) from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and  ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as incentive,\n" + 
			"        ifnull((select sum(pd.km) from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and  ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as km\n" + 
			"    FROM\n" + 
			"        m_employees e,\n" + 
			"        tbl_emp_salary_info emp_sal \n" + 
			"    where\n" + 
			"        e.emp_id=emp_sal.emp_id \n" + 
			"        and e.del_status=1 \n" + 
			"        and e.emp_id=:empId", nativeQuery = true)
	List<RoasterSummeryDetail> getRoasterSummeryDetailByEmpId(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("empId") int empId);

	@Query(value = "SELECT\n" + 
			"        e.emp_id,\n" + 
			"        e.emp_code,\n" + 
			"        e.first_name,\n" + 
			"        e.middle_name,\n" + 
			"        e.surname,\n" + 
			"        ifnull((select count('') from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and pd.isoffday_isff=1 and ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as off_day_count,\n" + 
			"        ifnull((select count('') from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and pd.isoffday_isff=2 and ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as ff_count,\n" + 
			"        ifnull((select count('') from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and pd.late_mark=1 and ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as late_mark,\n" + 
			"        ifnull((select sum(pd.late_min) from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and  ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as late_min,\n" + 
			"        ifnull((select sum(pd.incentive) from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and  ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as incentive,\n" + 
			"        ifnull((select sum(pd.km) from t_route_plan_detail pd, t_route_plan_header ph where ph.plan_date between :fromDate and :toDate and  ph.plan_head_id=pd.plan_head_id and pd.driver_id=e.emp_id),0) as km\n" + 
			"    FROM\n" + 
			"        m_employees e,\n" + 
			"        tbl_emp_salary_info emp_sal \n" + 
			"    where\n" + 
			"        e.emp_id=emp_sal.emp_id \n" + 
			"        and e.del_status=1 \n" + 
			"        and e.designation_id=:design", nativeQuery = true)
	List<RoasterSummeryDetail> getRoasterSummeryDetailForPayRoll(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("design") int design);

}
