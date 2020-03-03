package com.ats.hrmgt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.EmpJsonData;

public interface EmpJsonDataRepository extends JpaRepository<EmpJsonData,Integer>{

	@Query(value = "  select \n" + 
			"  e.emp_id,\n" + 
			"  e.emp_code,\n" + 
			"  e.cmp_code,\n" + 
			"  e.emp_type,\n" + 
			"  e.depart_id,\n" + 
			"  e.designation_id,\n" + 
			"  e.location_id,\n" + 
			"  e.first_name,\n" + 
			"  e.middle_name,\n" + 
			"  e.surname,\n" + 
			"  e.is_emp,\n" + 
			"  e.current_shiftid,\n" + 
			"  e.emp_category,\n" + 
			"  si.salary_type_id,\n" + 
			"  si.sal_basis,\n" + 
			"  si.cmp_joining_date,\n" + 
			"  e.holiday_category as holiday_cat_id,\n" + 
			"  e.weekend_category as week_end_cat_id from m_employees e,tbl_emp_salary_info si where e.emp_id=si.emp_id and e.del_status=1 and e.emp_id=:empId", nativeQuery = true)
	List<EmpJsonData> jsonDataList(int empId);
	
	@Query(value = "  select \n" + 
			"  e.emp_id,\n" + 
			"  e.emp_code,\n" + 
			"  e.cmp_code,\n" + 
			"  e.emp_type,\n" + 
			"  e.depart_id,\n" + 
			"  e.designation_id,\n" + 
			"  e.location_id,\n" + 
			"  e.first_name,\n" + 
			"  e.middle_name,\n" + 
			"  e.surname,\n" + 
			"  e.is_emp,\n" + 
			"  e.current_shiftid,\n" + 
			"  e.emp_category,\n" + 
			"  si.salary_type_id,\n" + 
			"  si.sal_basis,\n" + 
			"  si.cmp_joining_date,\n" + 
			"  e.holiday_category as holiday_cat_id,\n" + 
			"  e.weekend_category as week_end_cat_id from m_employees e,tbl_emp_salary_info si where e.emp_id=si.emp_id and e.del_status=1", nativeQuery = true)
	List<EmpJsonData> jsonDataList();

}
