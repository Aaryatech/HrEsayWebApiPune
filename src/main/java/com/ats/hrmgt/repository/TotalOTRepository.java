package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.TotalOT;

public interface TotalOTRepository extends JpaRepository<TotalOT, Integer>{

	@Query(value="select\n" + 
			"        UUID() AS id,\n" + 
			"        ds.emp_id,\n" + 
			"        concat(ds.month,\n" + 
			"        '-',\n" + 
			"        ds.year) as month,\n" + 
			"        sum(tot_othr)/60 as ot\n" + 
			"    from\n" + 
			"        tbl_attt_summary_daily ds,m_employees e \n" + 
			"    where\n" + 
			"        date_format(concat(ds.year,'-',ds.month,'-01'),'%Y-%m')>=date_format(date_sub(now(),Interval 6 month),'%Y-%m') \n" + 
			"        and e.emp_id=ds.emp_id\n" + 
			"        and e.location_id=:locId\n" + 
			"    group by month", nativeQuery=true)
	List<TotalOT> totalOtPrevioussixMonth(int locId);

}
