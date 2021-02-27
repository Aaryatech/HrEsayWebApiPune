package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpWithShiftDetail;

public interface EmpWithShiftDetailRepository extends JpaRepository<EmpWithShiftDetail, Integer> {

	
	/*@Query(value = "SELECT\n" + 
			"        emp.emp_id, \n" + 
			"        emp.emp_code,\n" + 
			"        concat(emp.first_name,' ',emp.surname) as name,\n" + 
			"        IFNULL(next_shiftid,0) as group_id,\n" + 
			"        IFNULL(sg.ex_int1,0) as group_type,\n" + 
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
			"        emp.del_status = 1 and emp.emp_id not in ( select emp_id from t_shift_assign_daily where shift_date between :fromDate and :toDate group by emp_id ) and emp.location_id=:locId ", nativeQuery = true)*/
	
	@Query(value = "SELECT\n" + 
			"        emp.emp_id,\n" + 
			"        emp.emp_code,\n" + 
			"        concat(emp.first_name,\n" + 
			"        ' ',\n" + 
			"        emp.surname) as name, \n" + 
			"        IFNULL((select\n" + 
			"            g.ex_int1 \n" + 
			"        from\n" + 
			"            t_shift_assign_daily sda,tbl_shift_timming st,m_self_grup g \n" + 
			"        where\n" + 
			"            sda.shift_date=:previousDate \n" + 
			"            and sda.emp_id=emp.emp_id and st.id=sda.shift_id and st.self_group_id=g.selft_group_id),\n" + 
			"        (0)) as group_type,\n" + 
			"        IFNULL((select\n" + 
			"            st.self_group_id \n" + 
			"        from\n" + 
			"            t_shift_assign_daily sda,tbl_shift_timming st \n" + 
			"        where\n" + 
			"            sda.shift_date=:previousDate \n" + 
			"            and sda.emp_id=emp.emp_id and st.id=sda.shift_id),\n" + 
			"        (0)) as group_id,\n" + 
			"        IFNULL((select\n" + 
			"            shift_id \n" + 
			"        from\n" + 
			"            t_shift_assign_daily \n" + 
			"        where\n" + 
			"            shift_date=:previousDate \n" + 
			"            and emp_id=emp.emp_id),\n" + 
			"        (select\n" + 
			"            current_shiftid \n" + 
			"        from\n" + 
			"            m_employees \n" + 
			"        where\n" + 
			"            emp_id=emp.emp_id)) as shift_id,\n" + 
			"        emp.location_id,\n" + 
			"        emp.holiday_category,\n" + 
			"        emp.weekend_category     \n" + 
			"    FROM\n" + 
			"        m_employees emp      \n" + 
			"    INNER JOIN\n" + 
			"        tbl_emp_salary_info salinfo              \n" + 
			"            ON     emp.emp_id = salinfo.emp_id      \n" + 
			"    LEFT JOIN\n" + 
			"        m_designation dg              \n" + 
			"            ON     emp.designation_id = dg.desig_id      \n" + 
			"    LEFT JOIN\n" + 
			"        m_department dep              \n" + 
			"            ON     emp.depart_id = dep.depart_id      \n" + 
			"    LEFT JOIN\n" + 
			"        m_contractor con              \n" + 
			"            ON     emp.contractor_id = con.contractor_id      \n" + 
			"    LEFT JOIN\n" + 
			"        m_location loc              \n" + 
			"            ON     emp.location_id = loc.loc_id      \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_emp_types emptyp              \n" + 
			"            ON     emp.emp_type = emptyp.emp_type_id      \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_shift_timming sht              \n" + 
			"            ON     emp.current_shiftid = sht.id      \n" + 
			"    LEFT JOIN\n" + 
			"        mst_salary_types saltype              \n" + 
			"            ON     salinfo.salary_type_id = saltype.sal_type_id      \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_sub_company succomp              \n" + 
			"            ON     succomp.company_id = emp.sub_cmp_id      \n" + 
			"    LEFT JOIN\n" + 
			"        weekoff_category wocat              \n" + 
			"            ON     wocat.wo_cat_id = emp.weekend_category      \n" + 
			"    LEFT JOIN\n" + 
			"        holiday_category holidaycat              \n" + 
			"            ON     holidaycat.ho_cat_id = emp.holiday_category     \n" + 
			"    LEFT JOIN\n" + 
			"        m_self_grup sg              \n" + 
			"            ON     sg.selft_group_id = emp.next_shiftid                   \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1 \n" + 
			"        and emp.emp_id not in (\n" + 
			"            select\n" + 
			"                emp_id \n" + 
			"            from\n" + 
			"                t_shift_assign_daily \n" + 
			"            where\n" + 
			"                shift_date between :fromDate and :toDate \n" + 
			"            group by\n" + 
			"                emp_id \n" + 
			"        ) \n" + 
			"        and emp.location_id=:locId", nativeQuery = true)
	List<EmpWithShiftDetail> updateAssignShiftByDate(@Param("previousDate") String previousDate,@Param("fromDate") String fromDate, @Param("toDate")String toDate,@Param("locId") int locId);

	
	
	
	@Query(value = "SELECT\n" + 
			"        emp.emp_id, \n" + 
			"        emp.emp_code,\n" + 
			"        concat(emp.first_name,' ',emp.surname) as name,\n" + 
			"        IFNULL(next_shiftid,0) as  group_id,\n" + 
			"        IFNULL(sg.ex_int1,0) as group_type,\n" + 
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
			"        ) and emp.location_id=:locId and emp.depart_id in (:deptId)", nativeQuery = true) 
	List<EmpWithShiftDetail> getEmpListAll(@Param("locId") int locId, @Param("deptId") List<Integer> deptId);


	@Query(value = "SELECT\n" + 
			"        emp.emp_id, \n" + 
			"        emp.emp_code,\n" + 
			"        concat(emp.first_name,' ',emp.surname) as name,\n" + 
			"        IFNULL(next_shiftid,0) as group_id,\n" + 
			"        IFNULL(sg.ex_int1,0) as group_type,\n" + 
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
			"        emp.del_status = 1 and emp.emp_id not in ( select tas.emp_id from t_temp_assign_first_day_shift tas  where tas.date=:fromDate  ) and emp.location_id=:locId", nativeQuery = true)
	List<EmpWithShiftDetail> getempList(String previousDate,String fromDate, int locId);



	@Query(value = "SELECT\n" + 
			"        emp.emp_id,\n" + 
			"        emp.emp_code,\n" + 
			"        concat(emp.first_name,\n" + 
			"        ' ',\n" + 
			"        emp.surname) as name,\n" + 
			"        IFNULL(next_shiftid,\n" + 
			"        0) as  group_id,\n" + 
			"        IFNULL(sg.ex_int1,\n" + 
			"        0) as group_type,\n" + 
			"        0 as shift_id,\n" + 
			"        emp.location_id,\n" + 
			"        emp.holiday_category,\n" + 
			"        emp.weekend_category     \n" + 
			"    FROM\n" + 
			"        m_employees emp      \n" + 
			"    INNER JOIN\n" + 
			"        tbl_emp_salary_info salinfo              \n" + 
			"            ON     emp.emp_id = salinfo.emp_id \n" + 
			"    INNER JOIN\n" + 
			"        leave_authority la  ON     emp.emp_id = la.emp_id and (ini_auth_emp_id=:userId or fin_auth_emp_id=:userId)\n" + 
			"    LEFT JOIN\n" + 
			"        m_designation dg              \n" + 
			"            ON     emp.designation_id = dg.desig_id      \n" + 
			"    LEFT JOIN\n" + 
			"        m_department dep              \n" + 
			"            ON     emp.depart_id = dep.depart_id      \n" + 
			"    LEFT JOIN\n" + 
			"        m_contractor con              \n" + 
			"            ON     emp.contractor_id = con.contractor_id      \n" + 
			"    LEFT JOIN\n" + 
			"        m_location loc              \n" + 
			"            ON     emp.location_id = loc.loc_id      \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_emp_types emptyp              \n" + 
			"            ON     emp.emp_type = emptyp.emp_type_id      \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_shift_timming sht              \n" + 
			"            ON     emp.current_shiftid = sht.id      \n" + 
			"    LEFT JOIN\n" + 
			"        mst_salary_types saltype              \n" + 
			"            ON     salinfo.salary_type_id = saltype.sal_type_id      \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_mst_sub_company succomp              \n" + 
			"            ON     succomp.company_id = emp.sub_cmp_id      \n" + 
			"    LEFT JOIN\n" + 
			"        weekoff_category wocat              \n" + 
			"            ON     wocat.wo_cat_id = emp.weekend_category      \n" + 
			"    LEFT JOIN\n" + 
			"        holiday_category holidaycat              \n" + 
			"            ON     holidaycat.ho_cat_id = emp.holiday_category     \n" + 
			"    LEFT JOIN\n" + 
			"        m_self_grup sg              \n" + 
			"            ON     sg.selft_group_id = emp.next_shiftid                   \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1 \n" + 
			"        AND (\n" + 
			"            salinfo.cmp_leaving_date IS NULL              \n" + 
			"            OR salinfo.cmp_leaving_date = ''              \n" + 
			"            OR salinfo.cmp_leaving_date = 1970 -00 -00              \n" + 
			"            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m')         \n" + 
			"        )  and emp.depart_id in (:deptId)", nativeQuery = true)
	List<EmpWithShiftDetail> getEmpListAuthorityWise(int userId, List<Integer> deptId);

}
