package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpWithShiftDetail;

public interface EmpWithShiftDetailRepository extends JpaRepository<EmpWithShiftDetail, Integer> {

	
	@Query(value = "SELECT\n" + 
			"        emp.emp_id, \n" + 
			"        emp.emp_code,\n" + 
			"        concat(emp.first_name,' ',emp.surname) as name,\n" + 
			"        next_shiftid as group_id,\n" + 
			"        sg.ex_int1 as group_type,\n" + 
			"        IFNULL((select shift_id from t_shift_assign_daily where shift_date=:previousDate and emp_id=emp.emp_id),(select current_shiftid from m_employees where emp_id=emp.emp_id)) as shift_id,"
			+ " emp.location_id,\n" + 
			"        emp.holiday_category,\n" + 
			"        emp.weekend_category\n" + 
			"    FROM\n" + 
			"        m_employees emp \n" + 
			"    INNER JOIN\n" + 
			"        tbl_emp_salary_info salinfo \n" + 
			"            ON     emp.emp_id = salinfo.emp_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_designation dg \n" + 
			"            ON     emp.designation_id = dg.desig_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_department dep \n" + 
			"            ON     emp.depart_id = dep.depart_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_contractor con \n" + 
			"            ON     emp.contractor_id = con.contractor_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_location loc \n" + 
			"            ON     emp.location_id = loc.loc_id \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_emp_types emptyp \n" + 
			"            ON     emp.emp_type = emptyp.emp_type_id \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_shift_timming sht \n" + 
			"            ON     emp.current_shiftid = sht.id \n" + 
			"    LEFT JOIN\n" + 
			"        mst_salary_types saltype \n" + 
			"            ON     salinfo.salary_type_id = saltype.sal_type_id \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_sub_company succomp \n" + 
			"            ON     succomp.company_id = emp.sub_cmp_id \n" + 
			"    LEFT JOIN\n" + 
			"        weekoff_category wocat \n" + 
			"            ON     wocat.wo_cat_id = emp.weekend_category \n" + 
			"    LEFT JOIN\n" + 
			"        holiday_category holidaycat \n" + 
			"            ON     holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"    LEFT JOIN\n" + 
			"        m_self_grup sg \n" + 
			"            ON     sg.selft_group_id = emp.next_shiftid\n" + 
			"             \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1 and emp.emp_id not in ( select emp_id from t_shift_assign_daily where shift_date between :fromDate and :toDate group by emp_id ) AND "
			+ "( salinfo.cmp_leaving_date IS NULL \n" + 
			"            OR salinfo.cmp_leaving_date = '' \n" + 
			"            OR salinfo.cmp_leaving_date = 1970 -00 -00 \n" + 
			"            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m')\n" + 
			"        )", nativeQuery = true)
	List<EmpWithShiftDetail> updateAssignShiftByDate(@Param("previousDate") String previousDate,@Param("fromDate") String fromDate, @Param("toDate")String toDate);

	
	@Query(value = "SELECT\n" + 
			"        emp.emp_id, \n" + 
			"        emp.emp_code,\n" + 
			"        concat(emp.first_name,' ',emp.surname) as name,\n" + 
			"        next_shiftid as group_id,\n" + 
			"        sg.ex_int1 as group_type,\n" + 
			"        0 as shift_id,"
			+ " emp.location_id,\n" + 
			"        emp.holiday_category,\n" + 
			"        emp.weekend_category\n" + 
			"    FROM\n" + 
			"        m_employees emp \n" + 
			"    INNER JOIN\n" + 
			"        tbl_emp_salary_info salinfo \n" + 
			"            ON     emp.emp_id = salinfo.emp_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_designation dg \n" + 
			"            ON     emp.designation_id = dg.desig_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_department dep \n" + 
			"            ON     emp.depart_id = dep.depart_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_contractor con \n" + 
			"            ON     emp.contractor_id = con.contractor_id \n" + 
			"    LEFT JOIN\n" + 
			"        m_location loc \n" + 
			"            ON     emp.location_id = loc.loc_id \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_emp_types emptyp \n" + 
			"            ON     emp.emp_type = emptyp.emp_type_id \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_shift_timming sht \n" + 
			"            ON     emp.current_shiftid = sht.id \n" + 
			"    LEFT JOIN\n" + 
			"        mst_salary_types saltype \n" + 
			"            ON     salinfo.salary_type_id = saltype.sal_type_id \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_sub_company succomp \n" + 
			"            ON     succomp.company_id = emp.sub_cmp_id \n" + 
			"    LEFT JOIN\n" + 
			"        weekoff_category wocat \n" + 
			"            ON     wocat.wo_cat_id = emp.weekend_category \n" + 
			"    LEFT JOIN\n" + 
			"        holiday_category holidaycat \n" + 
			"            ON     holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"    LEFT JOIN\n" + 
			"        m_self_grup sg \n" + 
			"            ON     sg.selft_group_id = emp.next_shiftid\n" + 
			"             \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1 AND "
			+ "( salinfo.cmp_leaving_date IS NULL \n" + 
			"            OR salinfo.cmp_leaving_date = '' \n" + 
			"            OR salinfo.cmp_leaving_date = 1970 -00 -00 \n" + 
			"            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m')\n" + 
			"        ) and emp.location_id=:locId", nativeQuery = true) 
	List<EmpWithShiftDetail> getEmpListAll(@Param("locId") int locId);

}
