package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpShiftAllocationDetail;

public interface EmpShiftAllocationDetailRepository extends JpaRepository<EmpShiftAllocationDetail, Integer>{

	/*
	 * @Query(value =
	 * "select sd.id,e.emp_id,sd.shift_id,sd.shift_date,st.shiftname  from t_shift_assign_daily sd,tbl_shift_timming st,m_employees e where shift_date"
	 * +
	 * " between :fromDate and  :toDate and st.id=sd.shift_id and e.emp_id=sd.emp_id and e.del_status=1 order by e.emp_id,sd.id"
	 * , nativeQuery = true)
	 */
	@Query(value = "select a.*,b.shiftname from\n" + 
			"        (select sd.id,\n" + 
			"        e.emp_id,\n" + 
			"        CASE WHEN sd.shift_id != 0 THEN sd.shift_id \n" + 
			"                else 3 END AS shift_id,\n" + 
			"        sd.shift_date  \n" + 
			"    from\n" + 
			"        t_shift_assign_daily sd, \n" + 
			"        m_employees e \n" + 
			"    where\n" + 
			"        shift_date between :fromDate and  :toDate  \n" + 
			"        and e.emp_id=sd.emp_id \n" + 
			"        and e.del_status=1 \n" + 
			"        and e.location_id=:locId and e.depart_id in (:deptId)\n" + 
			"    order by\n" + 
			"        e.emp_id,\n" + 
			"        sd.id) a\n" + 
			"    left join(\n" + 
			"    select st.id,st.shiftname \n" + 
			"    from tbl_shift_timming st\n" + 
			"    )b on  b.id=a.shift_id", nativeQuery = true)
	List<EmpShiftAllocationDetail> getEmpShiftAllocationDetail(@Param("fromDate")String fromDate,@Param("toDate") String toDate,@Param("locId") int locId,@Param("deptId")  List<Integer> deptId);

	@Query(value = "select\n" + 
			"        a.*,\n" + 
			"        b.shiftname \n" + 
			"    from\n" + 
			"        (select\n" + 
			"            sd.id,\n" + 
			"            e.emp_id,\n" + 
			"            CASE \n" + 
			"                WHEN sd.shift_id != 0 THEN sd.shift_id                  \n" + 
			"                else 3 \n" + 
			"            END AS shift_id,\n" + 
			"            sd.shift_date       \n" + 
			"        from\n" + 
			"            t_shift_assign_daily sd,\n" + 
			"            m_employees e,\n" + 
			"            leave_authority la\n" + 
			"        where\n" + 
			"            shift_date between :fromDate and  :toDate          \n" + 
			"            and e.emp_id=sd.emp_id          \n" + 
			"            and e.del_status=1          \n" + 
			"            and la.emp_id=e.emp_id and e.depart_id in (:deptId)\n" + 
			"            and (ini_auth_emp_id=:userId or fin_auth_emp_id=:userId)\n" + 
			"        order by\n" + 
			"            e.emp_id,\n" + 
			"            sd.id) a     \n" + 
			"    left join\n" + 
			"        (\n" + 
			"            select\n" + 
			"                st.id,\n" + 
			"                st.shiftname      \n" + 
			"            from\n" + 
			"                tbl_shift_timming st     \n" + 
			"        )b \n" + 
			"            on  b.id=a.shift_id", nativeQuery = true)
	List<EmpShiftAllocationDetail> getEmpShiftAllocationDetailAuthorityWise(String fromDate, String toDate, int userId, List<Integer> deptId);

}
