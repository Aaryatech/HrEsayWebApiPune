package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ProductionIncentiveList;

public interface ProductionIncentiveListRepo extends JpaRepository<ProductionIncentiveList, Integer> {

	@Query(value = "select  UUID() as id,da.att_date,da.emp_id,1 as hrs,0 as tot_othr from tbl_attt_daily_daily da where month(att_date)=:month and "
			+ "year(att_date)=:year and emp_id in (:empIds) and lv_sumup_id in(13,14)", nativeQuery = true)
	List<ProductionIncentiveList> getPerformanceIncentiveList(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

	
	@Query(value = "select UUID() as id,da.att_date,da.emp_id,da.ot_hr as hrs,ds.tot_othr from tbl_attt_daily_daily da,tbl_attt_summary_daily ds "
			+ "where month(da.att_date)=ds.month and year(da.att_date)=ds.year and da.emp_id in (:empIds) and da.freeze_by_supervisor=2 and da.ot_hr>0  "
			+ "and ds.emp_id=da.emp_id and ds.month=:month and ds.year=:year", nativeQuery = true)
	List<ProductionIncentiveList> getproductionIncentiveList(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

}
