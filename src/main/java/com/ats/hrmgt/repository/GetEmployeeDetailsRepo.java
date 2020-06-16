package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.GetEmployeeDetails;

public interface GetEmployeeDetailsRepo extends JpaRepository<GetEmployeeDetails, Integer>{
	


	 
	
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name AS dept_name,\n" + 
			"    dg.name AS emp_desgn,\n" + 
			"    loc.loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"    'NA' AS  sub_comp_name,'NA' AS  wo_cat_name, 'NA'  as ho_cat_name,salinfo.gross_salary \n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1   \n" + 
			" AND (salinfo.cmp_leaving_date IS NULL or salinfo.cmp_leaving_date='' or salinfo.cmp_leaving_date=1970-00-00 or  date_format(salinfo.cmp_leaving_date,'%Y-%m')>=date_format(CURDATE(),'%Y-%m')) AND emp.emp_id=:empId", nativeQuery = true)

 GetEmployeeDetails  getEmpDetailList(@Param("empId") int empId);
	
	
	
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name AS dept_name,\n" + 
			"    dg.name AS emp_desgn,\n" + 
			"    loc.loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"    'NA' AS  sub_comp_name,'NA' AS  wo_cat_name, 'NA'  as ho_cat_name,salinfo.gross_salary \n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1  \n" + 
			" AND (salinfo.cmp_leaving_date IS NULL or salinfo.cmp_leaving_date='' or salinfo.cmp_leaving_date=1970-00-00 or  date_format(salinfo.cmp_leaving_date,'%Y-%m')>=date_format(CURDATE(),'%Y-%m')) AND emp.location_id IN(:locId)", nativeQuery = true) 
	List<GetEmployeeDetails> getEmpDetailListByLocId(@Param("locId") List<Integer> locId);
	
	
	@Query(value = "SELECT\n" + 
			"        emp.*,\n" + 
			"        dep.name AS dept_name,\n" + 
			"        dg.name AS emp_desgn,\n" + 
			"        loc.loc_name,\n" + 
			"        con.org_name,\n" + 
			"        sht.shiftname,\n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        saltype.sal_type_name,\n" + 
			"        'NA' AS  sub_comp_name,'NA' AS  wo_cat_name, 'NA'  as ho_cat_name ,salinfo.gross_salary \n" + 
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
			"    WHERE\n" + 
			"        emp.del_status = 1 \n" + 
			"          \n" + 
			" AND (salinfo.cmp_leaving_date IS NULL or salinfo.cmp_leaving_date='' or salinfo.cmp_leaving_date=1970-00-00 or  date_format(salinfo.cmp_leaving_date,'%Y-%m')>=date_format(CURDATE(),'%Y-%m')) \n" + 
			"        AND emp.emp_id IN(\n" + 
			"            SELECT\n" + 
			"                t_bonus_calc.emp_id \n" + 
			"            FROM\n" + 
			"                t_bonus_calc     \n" + 
			"            WHERE\n" + 
			"                t_bonus_calc.bonus_id =:bonusId \n" + 
			"                AND t_bonus_calc.del_status = 1 \n" + 
			"                AND t_bonus_calc.is_exgretia_finalized!='Yes'  \n" + 
			"        )", nativeQuery = true)

 List<GetEmployeeDetails>  getEmpDetailListByBonusId(@Param("bonusId") int bonusId);
	
	
	
	@Query(value = "SELECT\n" + 
			"        emp.*,\n" + 
			"        dep.name AS dept_name,\n" + 
			"        dg.name AS emp_desgn,\n" + 
			"        loc.loc_name,\n" + 
			"        con.org_name,\n" + 
			"        sht.shiftname,\n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        saltype.sal_type_name,\n" + 
			"        'NA' AS  sub_comp_name,'NA' AS  wo_cat_name, 'NA'  as ho_cat_name ,salinfo.gross_salary \n" + 
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
			"    WHERE\n" + 
			"        emp.del_status = 1 \n" + 
			"          \n" + 
			" AND (salinfo.cmp_leaving_date IS NULL or salinfo.cmp_leaving_date='' or salinfo.cmp_leaving_date=1970-00-00 or  date_format(salinfo.cmp_leaving_date,'%Y-%m')>=date_format(CURDATE(),'%Y-%m')) \n" + 
			"        AND emp.emp_id NOT IN(\n" + 
			"            SELECT\n" + 
			"                t_bonus_calc.emp_id \n" + 
			"            FROM\n" + 
			"                t_bonus_calc     \n" + 
			"            WHERE\n" + 
			"                t_bonus_calc.bonus_id =:bonusId \n" + 
			"                AND t_bonus_calc.del_status = 1 \n" + 
			"                \n" + 
			"        )", nativeQuery = true)

 List<GetEmployeeDetails>  getEmpDetailListByBonusIdAssignedBonus(@Param("bonusId") int bonusId);



	
	
	@Query(value = " SELECT\n" + 
			"        emp.*,\n" + 
			"        dep.name AS dept_name,\n" + 
			"        dg.name AS emp_desgn,\n" + 
			"        loc.loc_name,\n" + 
			"        con.org_name,\n" + 
			"        sht.shiftname,\n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        empd.licence_no as sal_type_name,\n" + 
			"       'NA' AS  sub_comp_name,'NA' AS  wo_cat_name, 'NA'  as ho_cat_name ,salinfo.gross_salary \n" + 
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
			"        m_emp_driver empd \n" + 
			"            ON     empd.emp_id = emp.emp_id \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1 \n" + 
			"        \n" + 
			"        AND emp.designation_id=:degnId  AND (salinfo.cmp_leaving_date IS NULL or salinfo.cmp_leaving_date='' or salinfo.cmp_leaving_date=1970-00-00 or  date_format(salinfo.cmp_leaving_date,'%Y-%m')>=date_format(CURDATE(),'%Y-%m'))  ", nativeQuery = true)

	List<GetEmployeeDetails> getDriverEmpDetailList(@Param("degnId") int degnId);
	
	
	
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m'))  ", nativeQuery = true)

	List<GetEmployeeDetails> getEmpDetailList();
	//getAllEmployeeDetailassignSubCompany
	
	
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"    succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_short_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_short_name as ho_cat_name,salinfo.gross_salary \n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1  AND emp.ex_int1 in(1,2) AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m'))", nativeQuery = true)
  	List<GetEmployeeDetails> getEmplistForAssignAuthority();
	
	
	
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"    succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_short_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_short_name as ho_cat_name,salinfo.gross_salary \n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 	
			"        emp.emp_id NOT IN(\n" + 
			"            SELECT\n" + 
			"                auth.emp_id \n" + 
			"            FROM\n" + 
			"                leave_authority auth  \n" + 
			"            WHERE\n" + 
			"                auth.del_status=1 \n" + 
			"        )  AND \n" + 
			"    emp.del_status = 1  AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m'))", nativeQuery = true)
  	List<GetEmployeeDetails>  getEmpListByCompanyIdForAuth();

	 


	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"    succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_short_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_short_name as ho_cat_name,salinfo.gross_salary \n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 	
			"       emp.emp_id IN(:empIdList) AND \n"+ 
			"    emp.del_status = 1  AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m'))", nativeQuery = true)
  	List<GetEmployeeDetails>  getEmpListByCompanyIdAndEmpIdList(@Param("empIdList") List<Integer> empIdList);
	
	
	
	
	@Query(value = "SELECT\n" + 
			"        emp.*,\n" + 
			"        dep.name_sd AS dept_name,\n" + 
			"        dg.name_sd AS emp_desgn,\n" + 
			"        loc.loc_name_short as  loc_name,\n" + 
			"        con.org_name,\n" + 
			"        empinfo.email as shiftname,\n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        saltype.sal_type_name,\n" + 
			"        succomp.name_sd  AS sub_comp_name,\n" + 
			"        wocat.wo_cat_short_name as wo_cat_name,\n" + 
			"        holidaycat.ho_cat_short_name as ho_cat_name,\n" + 
			"        salinfo.gross_salary \n" + 
			"    FROM\n" + 
			"        leave_authority au,\n" + 
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
			"            ON     holidaycat.ho_cat_id = emp.holiday_category \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_emp_info empinfo \n" + 
			"            ON     empinfo.emp_id = emp.emp_id\n" + 
			"    WHERE\n" + 
			"        au.emp_id = emp.emp_id \n" + 
			"        AND(\n" + 
			"            au.ini_auth_emp_id = :empId \n" + 
			"            OR au.fin_auth_emp_id = :empId\n" + 
			"            OR emp.emp_id = :empId     \n" + 
			"        ) \n" + 
			"        AND      emp.del_status = 1  \n" + 
			"        AND(\n" + 
			"            salinfo.cmp_leaving_date IS NULL \n" + 
			"            OR salinfo.cmp_leaving_date = '' \n" + 
			"            OR salinfo.cmp_leaving_date = 1970 -00 -00 \n" + 
			"            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m')\n" + 
			"        )", nativeQuery = true)
  	List<GetEmployeeDetails>   getAuthorityWiseEmpListByEmpId(@Param("empId")int empId);
	
	
	
	
	
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        GROUP_CONCAT(loc.loc_name)\n" + 
			"    FROM\n" + 
			"        m_location loc,\n" + 
			"        m_user u,\n" + 
			"        m_employees empnew\n" + 
			"    WHERE\n" + 
			"        FIND_IN_SET(loc.loc_id, u.loc_id) AND empnew.emp_id = u.emp_id AND emp.emp_id = empnew.emp_id\n" + 
			") AS loc_name,\n" + 
			"con.org_name,\n" + 
			"sht.shiftname,\n" + 
			"emptyp.name AS emp_type_name,\n" + 
			"saltype.sal_type_name,\n" + 
			"succomp.name_sd AS sub_comp_name,\n" + 
			"wocat.wo_cat_short_name AS wo_cat_name,\n" + 
			"holidaycat.ho_cat_short_name AS ho_cat_name,\n" + 
			"salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m')) 	order by loc_name ", nativeQuery = true)

	List<GetEmployeeDetails> getEmpDetailListForAccessibleLoc();
	
	
	@Query(value = "SELECT\n" + 
			"        emp.*,\n" + 
			"        dep.name_sd AS dept_name,\n" + 
			"        dg.name_sd AS emp_desgn,\n" + 
			"      \n" + 
			"         sk.name   AS loc_name,\n" + 
			"        con.org_name,\n" + 
			"        sht.shiftname,\n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        saltype.sal_type_name,\n" + 
			"        succomp.name_sd AS sub_comp_name,\n" + 
			"        wocat.wo_cat_short_name AS wo_cat_name,\n" + 
			"        holidaycat.ho_cat_short_name AS ho_cat_name,\n" + 
			"        salinfo.gross_salary \n" + 
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
			"            ON     holidaycat.ho_cat_id = emp.holiday_category LEFT JOIN\n" + 
			"        m_skill_rates sk \n" + 
			"            ON     sk.skill_id = emp.ex_int2 \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1 \n" + 
			"        AND(\n" + 
			"            salinfo.cmp_leaving_date IS NULL \n" + 
			"            OR salinfo.cmp_leaving_date = '' \n" + 
			"            OR salinfo.cmp_leaving_date = 1970 -00 -00 \n" + 
			"            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m'))"
			+ "ORDER BY emp.ex_int2 ", nativeQuery = true)

	List<GetEmployeeDetails> getEmpDetailListForSkillRate();



	@Query(value = " SELECT\n" + 
			"        emp.*,\n" + 
			"        dep.name_sd AS dept_name,\n" + 
			"        dg.name_sd AS emp_desgn,\n" + 
			"        role.emp_type_name   AS loc_name,\n" + 
			"        con.org_name,\n" + 
			"        sht.shiftname,\n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        saltype.sal_type_name,\n" + 
			"        succomp.name_sd AS sub_comp_name,\n" + 
			"        wocat.wo_cat_short_name AS wo_cat_name,\n" + 
			"        holidaycat.ho_cat_short_name AS ho_cat_name,\n" + 
			"        salinfo.gross_salary      \n" + 
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
			"            ON     holidaycat.ho_cat_id = emp.holiday_category \n" + 
			"    LEFT JOIN\n" + 
			"        m_access_role_name role              \n" + 
			"            ON     role.emp_type_id = emp.emp_category      \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1          \n" + 
			"        AND(\n" + 
			"            salinfo.cmp_leaving_date IS NULL              \n" + 
			"            OR salinfo.cmp_leaving_date = ''              \n" + 
			"            OR salinfo.cmp_leaving_date = 1970 -00 -00              \n" + 
			"            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m')\n" + 
			"        ) ORDER BY emp.emp_category ", nativeQuery = true) 
	List<GetEmployeeDetails> getAllEmployeeDetailAccesssRole();


	@Query(value = "SELECT\n" + 
			"        emp.*,\n" + 
			"        dep.name_sd AS dept_name,\n" + 
			"        dg.name_sd AS emp_desgn,\n" + 
			"        loc.loc_name_short as  loc_name,\n" + 
			"        con.org_name,\n" + 
			"        sht.shiftname,\n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        saltype.sal_type_name,\n" + 
			"        succomp.name_sd  AS sub_comp_name,\n" + 
			"        wocat.wo_cat_short_name as wo_cat_name,\n" + 
			"        holidaycat.ho_cat_short_name as ho_cat_name,\n" + 
			"        salinfo.gross_salary \n" + 
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
			"            ON     holidaycat.ho_cat_id = emp.holiday_category \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1 \n" + 
			"        and emp.emp_id not in ( select f.emp_id from t_fulnfinal f)", nativeQuery = true)
	List<GetEmployeeDetails> getAllEmployeeDetailForFullnFinal();


	@Query(value = "SELECT\n" + 
			"        emp.*,\n" + 
			"        dep.name AS dept_name,\n" + 
			"        dg.name AS emp_desgn,\n" + 
			"        loc.loc_name,\n" + 
			"        con.org_name,\n" + 
			"        sht.shiftname,\n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        saltype.sal_type_name,\n" + 
			"        'NA' AS  sub_comp_name,\n" + 
			"        'NA' AS  wo_cat_name,\n" + 
			"        'NA'  as ho_cat_name,\n" + 
			"        salinfo.gross_salary  \n" + 
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
			"        t_shift_assign_daily shift \n" + 
			"            ON     shift.emp_id = emp.emp_id\n" + 
			"    LEFT JOIN\n" + 
			"        tbl_shift_timming sht              \n" + 
			"            ON     shift.shift_id = sht.id \n" + 
			"    LEFT JOIN\n" + 
			"        mst_salary_types saltype \n" + 
			"            ON     salinfo.salary_type_id = saltype.sal_type_id \n" + 
			"    WHERE\n" + 
			"        emp.del_status = 1    \n" + 
			"        AND (\n" + 
			"            salinfo.cmp_leaving_date IS NULL \n" + 
			"            or salinfo.cmp_leaving_date='' \n" + 
			"            or salinfo.cmp_leaving_date=1970-00-00 \n" + 
			"            or  date_format(salinfo.cmp_leaving_date,'%Y-%m')>=date_format(CURDATE(),'%Y-%m')\n" + 
			"        ) and shift.shift_date=:date order by shift.shift_id asc", nativeQuery = true)
	List<GetEmployeeDetails> getEmpDetailListforassignshiftbulk(@Param("date")String date);



	@Query(value = "SELECT\n" + 
			"        emp.*,\n" + 
			"        dep.name_sd AS dept_name,\n" + 
			"        dg.name_sd AS emp_desgn,\n" + 
			"        loc.loc_name_short as  loc_name,\n" + 
			"        con.org_name,\n" + 
			"        empinfo.ex_var1 as shiftname,\n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        saltype.sal_type_name,\n" + 
			"        succomp.name_sd  AS sub_comp_name,\n" + 
			"        wocat.wo_cat_short_name as wo_cat_name,\n" + 
			"        holidaycat.ho_cat_short_name as ho_cat_name,\n" + 
			"        salinfo.gross_salary \n" + 
			"    FROM\n" + 
			"        leave_authority au,\n" + 
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
			"            ON     holidaycat.ho_cat_id = emp.holiday_category \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_emp_info empinfo \n" + 
			"            ON     empinfo.emp_id = emp.emp_id\n" + 
			"    WHERE\n" + 
			"        au.emp_id = emp.emp_id \n" + 
			"        AND(\n" + 
			"            au.ini_auth_emp_id = :empId \n" + 
			"            OR au.fin_auth_emp_id = :empId\n" + 
			"            OR emp.emp_id = :empId     \n" + 
			"        ) \n" + 
			"        AND      emp.del_status = 1  \n" + 
			"        AND(\n" + 
			"            salinfo.cmp_leaving_date IS NULL \n" + 
			"            OR salinfo.cmp_leaving_date = '' \n" + 
			"            OR salinfo.cmp_leaving_date = 1970 -00 -00 \n" + 
			"            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m')\n" + 
			"        )", nativeQuery = true)
	List<GetEmployeeDetails> getAuthorityWiseEmpListByEmpIdForApp(@Param("empId") int empId);


	@Query(value = "SELECT\n" + 
			"        emp.*,\n" + 
			"        dep.name_sd AS dept_name,\n" + 
			"        dg.name_sd AS emp_desgn,\n" + 
			"        loc.loc_name_short as  loc_name,\n" + 
			"        con.org_name,\n" + 
			"        sg.name as shiftname,\n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        saltype.sal_type_name,\n" + 
			"        succomp.name_sd  AS sub_comp_name,\n" + 
			"        wocat.wo_cat_name as wo_cat_name,\n" + 
			"        holidaycat.ho_cat_name as ho_cat_name,\n" + 
			"        salinfo.gross_salary \n" + 
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
			"    WHERE\n" + 
			"        emp.del_status = 1 \n" + 
			"        AND(\n" + 
			"            salinfo.cmp_leaving_date IS NULL \n" + 
			"            OR salinfo.cmp_leaving_date = '' \n" + 
			"            OR salinfo.cmp_leaving_date = 1970 -00 -00 \n" + 
			"            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m')\n" + 
			"        ) ORDER BY emp.next_shiftid ", nativeQuery = true)
	List<GetEmployeeDetails> getAllEmployeeDetailShiftGroup();


	
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    usr.ex_var1 as shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"    succomp.name_sd  AS sub_comp_name,\n" + 
			"    e_info.email as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN  m_user usr   ON\n" + 
			"    emp.emp_id = usr.emp_id \n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			

"LEFT JOIN tbl_emp_info e_info ON\n" + 
"    e_info.emp_id = emp.emp_id \n" + 

			
			
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m')) ORDER BY usr.ex_var1 ", nativeQuery = true)

	List<GetEmployeeDetails> getEmpDetailForLoginType();
	
	//Sachin 05-05-2020
	
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m')) ORDER BY emp.sub_cmp_id  ", nativeQuery = true)

	List<GetEmployeeDetails> getAllEmployeeDetailassignSubCompany();


	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m')) ORDER BY emp.emp_type  ", nativeQuery = true)

	List<GetEmployeeDetails> getAllEmployeeDetailshowAssignEmpType();

	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m')) ORDER BY emp.designation_id  ", nativeQuery = true)


	List<GetEmployeeDetails> getAllEmployeeDetailshowAssignDesignation();


	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m')) ORDER BY emp.current_shiftid  ", nativeQuery = true)

	List<GetEmployeeDetails> getAllEmployeeDetailshowEmpListToAssignShift();


	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m')) ORDER BY emp.depart_id  ", nativeQuery = true)


	List<GetEmployeeDetails> getAllEmployeeDetailassignDept();

	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m')) ORDER BY emp.location_id  ", nativeQuery = true)


	List<GetEmployeeDetails> getAllEmployeeDetailshowAssignLocation();


	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m')) ORDER BY emp.holiday_category  ", nativeQuery = true)


	List<GetEmployeeDetails> getAllEmployeeDetailassignHolidayCategory();


	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m')) ORDER BY emp.weekend_category  ", nativeQuery = true)


	List<GetEmployeeDetails> getAllEmployeeDetailassignWeekoffCategory();

	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m')) ORDER BY salinfo.salary_type_id  ", nativeQuery = true)



	List<GetEmployeeDetails> getAllEmployeeDetailshowEmpListToAssignSalStruct();


	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m'))  and emp.emp_id not in (\n" + 
			"            select\n" + 
			"                emp_id              \n" + 
			"            from\n" + 
			"                leave_balance_cal              \n" + 
			"            where\n" + 
			"                cal_yr_id=(\n" + 
			"                    select\n" + 
			"                        cal_yr_id                      \n" + 
			"                    from\n" + 
			"                        dm_cal_year                      \n" + 
			"                    where\n" + 
			"                        is_current=1                 \n" + 
			"                )             \n" + 
			"            ) \n" + 
			"            and  emp.location_id=:locId", nativeQuery = true)
	List<GetEmployeeDetails> getEmpDetailListForCarryForwardLeave(@Param("locId") int locId);


	
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m'))"
			+ " and emp.emp_id not in (SELECT tbl_loan_main.emp_id FROM tbl_loan_main WHERE tbl_loan_main.current_outstanding!=0) " + 
			"                and emp.emp_id not in (SELECT tbl_loan_main.ex_int1 FROM tbl_loan_main WHERE tbl_loan_main.current_outstanding!=0) " + 
			"                and emp.emp_id not in (SELECT tbl_loan_main.ex_int2 FROM tbl_loan_main WHERE tbl_loan_main.current_outstanding!=0)  ", nativeQuery = true)

	List<GetEmployeeDetails> getEmpsForLoanOrGuarantor();


	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name AS dept_name,\n" + 
			"    dg.name AS emp_desgn,\n" + 
			"    loc.loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"    'NA' AS  sub_comp_name,'NA' AS  wo_cat_name, 'NA'  as ho_cat_name,salinfo.gross_salary \n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1   \n" + 
			" AND (salinfo.cmp_leaving_date IS NULL or salinfo.cmp_leaving_date='' or salinfo.cmp_leaving_date=1970-00-00 or  date_format(salinfo.cmp_leaving_date,'%Y-%m')>=date_format(CURDATE(),'%Y-%m')) AND emp.emp_id IN (:empIds) ", nativeQuery = true)

	List<GetEmployeeDetails> getEmpDetailListByCsEmpIds(@Param("empIds")List<String> empIds);


	@Query(value = "SELECT\n" + 
			"        emp.*,\n" + 
			"        dep.name_sd AS dept_name,\n" + 
			"        dg.name_sd AS emp_desgn,\n" + 
			"        loc.loc_name_short as  loc_name,\n" + 
			"        con.org_name,\n" + 
			"        empinfo.email as shiftname,\n" + 
			"        emptyp.name AS emp_type_name,\n" + 
			"        saltype.sal_type_name,\n" + 
			"        succomp.name_sd  AS sub_comp_name,\n" + 
			"        wocat.wo_cat_short_name as wo_cat_name,\n" + 
			"        holidaycat.ho_cat_short_name as ho_cat_name,\n" + 
			"        salinfo.gross_salary      \n" + 
			"    FROM\n" + 
			"        claim_authority au,\n" + 
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
			"            ON     holidaycat.ho_cat_id = emp.holiday_category      \n" + 
			"    LEFT JOIN\n" + 
			"        tbl_emp_info empinfo              \n" + 
			"            ON     empinfo.emp_id = emp.emp_id     \n" + 
			"    WHERE\n" + 
			"        au.emp_id = emp.emp_id          \n" + 
			"        AND(\n" + 
			"            au.ca_ini_auth_emp_id =:empId              \n" + 
			"            OR au.ca_fin_auth_emp_id =:empId            \n" + 
			"            OR emp.emp_id =:empId              \n" + 
			"        )          \n" + 
			"        AND      emp.del_status = 1           \n" + 
			"        AND(\n" + 
			"            salinfo.cmp_leaving_date IS NULL              \n" + 
			"            OR salinfo.cmp_leaving_date = ''              \n" + 
			"            OR salinfo.cmp_leaving_date = 1970 -00 -00              \n" + 
			"            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m')         \n" + 
			"        )", nativeQuery = true)
	List<GetEmployeeDetails> getEmpListForClaimAuthByEmpId(@Param("empId") int empId);
	
	
	//Sachin 13-06-2020
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name_sd AS dept_name,\n" + 
			"    dg.name_sd AS emp_desgn,\n" + 
			"    loc.loc_name_short as  loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"     succomp.name_sd  AS sub_comp_name,\n" + 
			"    wocat.wo_cat_name as wo_cat_name,\n" + 
			"    holidaycat.ho_cat_name as ho_cat_name, salinfo.gross_salary\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN tbl_mst_sub_company succomp ON\n" + 
			"    succomp.company_id = emp.sub_cmp_id\n" + 
			"LEFT JOIN weekoff_category wocat ON\n" + 
			"    wocat.wo_cat_id = emp.weekend_category\n" + 
			"LEFT JOIN holiday_category holidaycat ON\n" + 
			"    holidaycat.ho_cat_id = emp.holiday_category\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 and emp.location_id=:locId AND(\n" + 
			"        salinfo.cmp_leaving_date IS NULL OR salinfo.cmp_leaving_date = '' OR salinfo.cmp_leaving_date = 1970 -00 -00 OR DATE_FORMAT(\n" + 
			"            salinfo.cmp_leaving_date,\n" + 
			"            '%Y-%m'\n" + 
			"        ) >= DATE_FORMAT(CURDATE(), '%Y-%m'))  ", nativeQuery = true)

	List<GetEmployeeDetails> getEmpListByLocId(@Param("locId") int locId);
   
}
