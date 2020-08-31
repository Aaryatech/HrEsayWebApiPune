package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.LeavesAllotment;

public interface LeaveAllotmentRepository extends JpaRepository<LeavesAllotment, Integer> {

	@Transactional
	@Modifying
	@Query("update LeavesAllotment set del_status=0  WHERE lvsa_pkey=:lvsaPkey")
	int deleteLeaveAllotment(int lvsaPkey);

	List<LeavesAllotment> findByDelStatus(int i);

	List<LeavesAllotment> findByCalYrId(int calYrId);

	LeavesAllotment findByLvsaPkeyAndDelStatus(int lvsaPkey, int i);

	LeavesAllotment findByEmpIdAndLvsIdAndDelStatus(int empId, int lvsId, int i);

	LeavesAllotment findByEmpIdAndDelStatus(int empId, int i);

	@Transactional
	@Modifying
	@Query("update LeavesAllotment set lvs_id=:lvsId  WHERE emp_id=:empId AND cal_yr_id=:calYear ")
	int updateLeaveStructure(int lvsId, int empId, int calYear);
	
	@Transactional
	@Modifying
	@Query("update LeavesAllotment set lvs_id=:lvsId  WHERE lvsa_pkey=:lvsaPkey")
	int updateLeaveStructureBylvsaPkey(int lvsId, int lvsaPkey);

	@Query(value = "SELECT\n" + 
			"        lsa.lvsa_pkey,\n" + 
			"        lsa.cal_yr_id,\n" + 
			"        lsa.emp_id,\n" + 
			"        lsa.lvs_id,\n" + 
			"        lsa.del_status,\n" + 
			"        lsa.is_active,\n" + 
			"        lsa.maker_user_id,\n" + 
			"        lsa.maker_enter_datetime,\n" + 
			"        lsa.ex_int1,\n" + 
			"        lsa.ex_int2,\n" + 
			"        lsa.ex_int3,\n" + 
			"        lsa.ex_var1,\n" + 
			"        lsa.ex_var2,\n" + 
			"        concat( e.surname,\n" + 
			"        ' ',\n" + 
			"        e.first_name) as ex_var3     \n" + 
			"    FROM\n" + 
			"        leave_structure_allotment lsa,\n" + 
			"        m_employees e     \n" + 
			"    WHERE\n" + 
			"        lvsa_pkey=:lvsaPkey         \n" + 
			"        and lsa.emp_id=e.emp_id", nativeQuery = true)
	LeavesAllotment getLeaveAllotmentByKey(@Param("lvsaPkey") int lvsaPkey);

}
