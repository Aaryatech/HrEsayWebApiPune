package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.TotalOT;

public interface TotalOTRepository extends JpaRepository<TotalOT, Integer>{

	@Query(value="select\n" + 
			"        UUID() AS id,\n" + 
			"        e.depart_id,\n" + 
			"        concat(ds.month,\n" + 
			"        '-',\n" + 
			"        ds.year) as month,\n" + 
			"        sum(tot_othr)/60 as ot,\n" + 
			"        concat(ds.year,'-',ds.month,\n" + 
			"        '-01') as date_mo,\n" + 
			"        dept.name\n" + 
			"    from\n" + 
			"        tbl_attt_summary_daily ds,m_employees e,m_department dept \n" + 
			"    where\n" + 
			"        date_format(concat(ds.year,'-',ds.month,'-01'),'%Y-%m')>=date_format(date_sub(now(),Interval 6 month),'%Y-%m') \n" + 
			"        and e.emp_id=ds.emp_id\n" + 
			"        and e.location_id=:locId\n" + 
			"        and e.del_status=1\n" + 
			"        and dept.depart_id=e.depart_id\n" + 
			"        and dept.del_status=1\n" + 
			"    group by month,e.depart_id \n" + 
			"    order by month asc,e.depart_id asc", nativeQuery=true)
	List<TotalOT> totalOtPrevioussixMonth(int locId);

}
