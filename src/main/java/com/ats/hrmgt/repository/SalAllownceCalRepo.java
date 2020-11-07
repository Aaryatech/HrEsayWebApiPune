package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SalAllownceCal;

public interface SalAllownceCalRepo extends JpaRepository<SalAllownceCal, Integer> {

	@Query(value = "select sa.* from tbl_salary_calc_allowance_cal sa,tbl_salary_calc sc where sa.salary_calc_id=sc.id and sc.calc_month=:month and calc_year=:year", nativeQuery = true)
	List<SalAllownceCal> getPayrollAllownceList(@Param("month") int month, @Param("year") int year);

	@Query(value = "select sa.* from tbl_salary_calc_allowance_cal sa,tbl_salary_calc sc where sa.salary_calc_id=sc.id and "
			+ "sc.calc_month=:month and calc_year=:year and sc.emp_id in(:empIds)", nativeQuery = true)
	List<SalAllownceCal> getPayrollAllownceList(@Param("month") int month, @Param("year") int year,@Param("empIds") List<Integer> empIds);

	@Query(value = "select sa.* from tbl_salary_calc_allowance_cal sa,tbl_salary_calc sc,m_employees e where sa.salary_calc_id=sc.id and "
			+ "sc.calc_month=:month and calc_year=:year and sc.emp_id=e.emp_id and e.location_id in (:locId) ", nativeQuery = true)
	List<SalAllownceCal> getPayrollAllownceListlocId(@Param("month") int month, @Param("year") int year,@Param("locId") List<Integer> locId);
	
	List<SalAllownceCal> findByEmpId(int i);

	@Query(value = "select\n" + 
			"        sa.emp_sal_allowance_id,\n" + 
			"        sa.salary_calc_id,\n" + 
			"        sc.depart_id as emp_id,\n" + 
			"        sa.allowance_id,\n" + 
			"        sum(sa.allowance_value) as allowance_value,\n" + 
			"        sum(sa.allowance_value_cal) as allowance_value_cal,\n" + 
			"        sa.maker_enter_datetime,\n" + 
			"        sa.del_status,\n" + 
			"        sa.ex_int1,\n" + 
			"        sa.ex_int2,\n" + 
			"        sa.ex_var1,\n" + 
			"        sa.ex_var2,\n" + 
			"        sa.short_name  \n" + 
			"    from\n" + 
			"        tbl_salary_calc_allowance_cal sa,\n" + 
			"        tbl_salary_calc sc,\n" + 
			"        m_employees e \n" + 
			"    where\n" + 
			"        sa.salary_calc_id=sc.id \n" + 
			"        and  sc.calc_month=:month \n" + 
			"        and calc_year=:year\n" + 
			"        and sc.emp_id=e.emp_id \n" + 
			"        and e.location_id in (:locId) \n" + 
			"    group by\n" + 
			"        sc.depart_id,\n" + 
			"        sa.allowance_id \n" + 
			"    order by\n" + 
			"        sc.depart_id,\n" + 
			"        sa.allowance_id", nativeQuery = true)
	List<SalAllownceCal> getPayrollAllownceListlocIdDept(int month, int year, List<Integer> locId);

}
