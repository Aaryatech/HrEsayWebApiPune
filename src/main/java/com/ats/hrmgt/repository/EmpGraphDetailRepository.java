package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.EmpGraphDetail;

public interface EmpGraphDetailRepository extends JpaRepository<EmpGraphDetail, Integer> {

	@Query(value = "select ds.id,ds.emp_id,concat(ds.month,'-',ds.year) as month,ds.totlate_mins as late_min,tot_late as late_mark from tbl_attt_summary_daily ds "
			+ "where date_format(concat(ds.year,'-',ds.month,'-01'),'%Y-%m')>=date_format(date_sub(now(),Interval 6 month),'%Y-%m') and ds.emp_id=:empId", nativeQuery = true)
	List<EmpGraphDetail> getLateMarkGraph(int empId);

}
