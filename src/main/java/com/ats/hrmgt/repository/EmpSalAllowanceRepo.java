package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpSalAllowance;

public interface EmpSalAllowanceRepo extends JpaRepository<EmpSalAllowance, Integer> {

	List<EmpSalAllowance> findByEmpIdAndDelStatus(int empId, int del);

	@Transactional
	@Modifying
	@Query(value="UPDATE `emp_sal_allowance` SET del_status=0 WHERE emp_id=:empId",nativeQuery=true)
	int deleteEmpAllowances(@Param("empId") int empId);

	List<EmpSalAllowance> findByDelStatus(int i);

 
	EmpSalAllowance findByAllowanceIdAndEmpId(int allowanceId, int empId);

	@Query(value = "select * from emp_sal_allowance where del_status=:delStatus and emp_id in (:empIds)", nativeQuery = true)
	List<EmpSalAllowance> findByDelStatusAndEmpId(@Param("delStatus") int delStatus, @Param("empIds")  List<Integer> empIds);

	@Query(value = "SELECT\n" + 
			"        ea.emp_sal_allowance_id,\n" + 
			"        ea.emp_id,\n" + 
			"        ea.allowance_id,\n" + 
			"        ea.allowance_value,\n" + 
			"        ea.maker_enter_datetime,\n" + 
			"        ea.del_status,\n" + 
			"        ea.ex_int1,\n" + 
			"        ea.ex_int2,\n" + 
			"        a.name as ex_var1,\n" + 
			"        ea.ex_var2   \n" + 
			"    from\n" + 
			"        emp_sal_allowance ea,\n" + 
			"        m_allowances a \n" + 
			"    where\n" + 
			"        ea.emp_id=:empId\n" + 
			"        and ea.allowance_id = a.allowance_id \n" + 
			"        and a.is_active=1 \n" + 
			"        and ea.del_status=1\n" + 
			"    group by ea.allowance_id \n" + 
			"    order by\n" + 
			"        a.short_no", nativeQuery = true)
	List<EmpSalAllowance> getEmployeeSalAllowancesInfo(int empId);

	@Query(value = "SELECT\n" + 
			"        ea.emp_sal_allowance_id,\n" + 
			"        ea.emp_id,\n" + 
			"        ea.allowance_id,\n" + 
			"        ea.allowance_value,\n" + 
			"        ea.maker_enter_datetime,\n" + 
			"        ea.del_status,\n" + 
			"        ea.ex_int1,\n" + 
			"        ea.ex_int2,\n" + 
			"        a.name as ex_var1,\n" + 
			"        ea.ex_var2   \n" + 
			"    from\n" + 
			"        emp_sal_allowance ea,\n" + 
			"        m_allowances a,\n" + 
			"        m_employees emp\n" + 
			"    where\n" + 
			"         ea.allowance_id = a.allowance_id \n" + 
			"        and a.is_active=1 \n" + 
			"        and ea.del_status=1\n" + 
			"        and emp.emp_id=ea.emp_id and emp.location_id in (:locId)\n" + 
			"    group by ea.emp_id,ea.allowance_id \n" + 
			"    order by\n" + 
			"        ea.emp_id,a.short_no", nativeQuery = true)
	List<EmpSalAllowance> getEmployeeSalAllowancesInfoAll(List<Integer> locId);
 

	

}
