package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SalaryCalc;
import com.ats.hrmgt.model.bonus.BonusParam;

public interface SalaryCalcRepo extends JpaRepository<SalaryCalc, Integer> {

	SalaryCalc findByEmpId(int empId);
	
	
	List<SalaryCalc> findAllByEmpId(int empId);

	@Query(value = " SELECT\n" + 
			"    *\n" + 
			"FROM\n" + 
			"    tbl_salary_calc\n" + 
			"\n" + 
			"WHERE\n" + 
			"   tbl_salary_calc.emp_id =:empId AND(\n" + 
			"        (\n" + 
			"            tbl_salary_calc.calc_month >= :monthFrom AND tbl_salary_calc.calc_year = :yearFrom\n" + 
			"        ) OR(\n" + 
			"            tbl_salary_calc.calc_month <= :monthTo AND tbl_salary_calc.calc_year = :yearTo)\n" + 
			"        )\n" + 
			"        order by calc_year , calc_month ASC\n" + 
			"    \n" + 
			"    limit 0,1", nativeQuery = true)
	SalaryCalc getBonusParametersGS(@Param("empId") int empId,@Param("monthFrom") int monthFrom,@Param("monthTo") int monthTo,
			@Param("yearFrom") String yearFrom,@Param("yearTo") String yearTo
			);


	

 	
	
	

}
