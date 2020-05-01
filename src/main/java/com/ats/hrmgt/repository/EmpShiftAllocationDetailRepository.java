package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpShiftAllocationDetail;

public interface EmpShiftAllocationDetailRepository extends JpaRepository<EmpShiftAllocationDetail, Integer>{

	@Query(value = "select sd.id,e.emp_id,sd.shift_id,sd.shift_date,st.shiftname  from t_shift_assign_daily sd,tbl_shift_timming st,m_employees e where shift_date"
			+ " between :fromDate and  :toDate and st.id=sd.shift_id and e.emp_id=sd.emp_id and e.del_status=1 order by e.emp_id,sd.id", nativeQuery = true)
	List<EmpShiftAllocationDetail> getEmpShiftAllocationDetail(@Param("fromDate")String fromDate,@Param("toDate") String toDate);

}
