package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpSalaryInfoForPayroll;

public interface EmpSalaryInfoForPayrollRepository extends JpaRepository<EmpSalaryInfoForPayroll, Integer> {

	/*@Query(value = "select\n" + 
			"        e.emp_code,e.sub_cmp_id,\n" + 
			"        et.name as emp_type_name,\n" + 
			"        et.emp_type_id,\n" + 
			"        l.loc_name,\n" + 
			"        st.sal_type_name,\n" + 
			"        de.name as designation,\n" + 
			"        dp.name as dept_name,\n" + 
			"        de.desig_id,\n" + 
			"        l.loc_id,\n" + 
			"        dp.depart_id,\n" + 
			"        e.contractor_id,\n" + 
			"        concat(e.first_name,\n" + 
			"        ' ',\n" + 
			"        e.surname) as emp_name,\n" + 
			"        si.*,\n" + 
			"        sd.id as sum_id, 0 as count_leave\n" + 
			"    from\n" + 
			"        tbl_emp_salary_info si,\n" + 
			"        m_employees e,\n" + 
			"        tbl_mst_emp_types et,\n" + 
			"        m_location l,\n" + 
			"        mst_salary_types st,\n" + 
			"        m_designation de,\n" + 
			"        m_department dp,\n" + 
			"        tbl_attt_summary_daily sd\n" + 
			"    where\n" + 
			"        e.emp_id=si.emp_id \n" + 
			"        and e.del_status=1 \n" + 
			"        and et.emp_type_id=e.emp_type \n" + 
			"        and l.loc_id = e.location_id \n" + 
			"        and st.sal_type_id=si.salary_type_id \n" + 
			"        and de.desig_id=e.designation_id \n" + 
			"        and dp.depart_id=e.depart_id \n" + 
			"        and sd.emp_id=e.emp_id\n" + 
			"        and sd.month=:month and sd.year=:year\n" + 
			"        and e.emp_id not in (\n" + 
			"            select\n" + 
			"                emp_id \n" + 
			"            from\n" + 
			"                tbl_salary_calc \n" + 
			"            where\n" + 
			"                calc_month=:month \n" + 
			"                and calc_year=:year\n" + 
			"        ) and sd.total_days_inmonth=(SELECT count(*) FROM tbl_attt_daily_daily where YEAR(att_date) =sd.year "
			+ "and MONTH(att_date) =sd.month and e.emp_id=tbl_attt_daily_daily.emp_id and is_fixed=1 and rec_status='F') and e.location_id=:locId\n" + 
			"    order by\n" + 
			"        e.emp_id", nativeQuery = true)*/
	//change code on 15-10-2020 akshay
	
	@Query(value = "select data.*,\n" + 
			"CASE\n" + 
			"    WHEN a.sal_count>0 THEN ifnull(b.sal_count,0)\n" + 
			"    ELSE 1\n" + 
			"END as can_generate_sal\n" + 
			"\n" + 
			"from\n" + 
			"        (select e.emp_code,\n" + 
			"        e.sub_cmp_id,\n" + 
			"        et.name as emp_type_name,\n" + 
			"        et.emp_type_id,\n" + 
			"        l.loc_name,\n" + 
			"        st.sal_type_name,\n" + 
			"        de.name as designation,\n" + 
			"        dp.name as dept_name,\n" + 
			"        de.desig_id,\n" + 
			"        l.loc_id,\n" + 
			"        dp.depart_id,\n" + 
			"        e.contractor_id,\n" + 
			"        concat(e.first_name,\n" + 
			"        ' ',\n" + 
			"        e.surname) as emp_name,\n" + 
			"        si.*,\n" + 
			"        sd.id as sum_id,\n" + 
			"        0 as count_leave     \n" + 
			"    from\n" + 
			"        tbl_emp_salary_info si,\n" + 
			"        m_employees e,\n" + 
			"        tbl_mst_emp_types et,\n" + 
			"        m_location l,\n" + 
			"        mst_salary_types st,\n" + 
			"        m_designation de,\n" + 
			"        m_department dp,\n" + 
			"        tbl_attt_summary_daily sd     \n" + 
			"    where\n" + 
			"        e.emp_id=si.emp_id          \n" + 
			"        and e.del_status=1          \n" + 
			"        and et.emp_type_id=e.emp_type          \n" + 
			"        and l.loc_id = e.location_id          \n" + 
			"        and st.sal_type_id=si.salary_type_id          \n" + 
			"        and de.desig_id=e.designation_id          \n" + 
			"        and dp.depart_id=e.depart_id          \n" + 
			"        and sd.emp_id=e.emp_id         \n" + 
			"        and sd.month=:month\n" + 
			"        and sd.year=:year        \n" + 
			"        and e.emp_id not in (\n" + 
			"            select\n" + 
			"                emp_id              \n" + 
			"            from\n" + 
			"                tbl_salary_calc              \n" + 
			"            where\n" + 
			"                calc_month=sd.month                \n" + 
			"                and calc_year=sd.year         \n" + 
			"        ) \n" + 
			"        and sd.total_days_inmonth=(\n" + 
			"            SELECT\n" + 
			"                count(*) \n" + 
			"            FROM\n" + 
			"                tbl_attt_daily_daily \n" + 
			"            where\n" + 
			"                YEAR(att_date) =sd.year \n" + 
			"                and MONTH(att_date) =sd.month \n" + 
			"                and e.emp_id=tbl_attt_daily_daily.emp_id \n" + 
			"                and is_fixed=1 \n" + 
			"                and rec_status='F'\n" + 
			"        ) \n" + 
			"        and e.location_id=(:locId)     \n" + 
			"    order by\n" + 
			"        e.emp_id) data\n" + 
			"left join\n" + 
			"(\n" + 
			"select\n" + 
			"        count('') as sal_count,emp_id             \n" + 
			"            from\n" + 
			"                tbl_salary_calc  \n" + 
			"            group by emp_id) a on data.emp_id=a.emp_id\n" + 
			"left join(\n" + 
			"select\n" + 
			"        ifnull(count(''),0) as sal_count,emp_id             \n" + 
			"            from\n" + 
			"                tbl_salary_calc              \n" + 
			"            where\n" + 
			"            date_format(concat(calc_year,'-',calc_month,'-01'),'%Y-%m')=date_format(date_sub(:date,Interval 1 month),'%Y-%m') \n" + 
			"            group by emp_id) b on a.emp_id=b.emp_id ", nativeQuery = true)
	List<EmpSalaryInfoForPayroll> getEmployeeListWithEmpSalEnfoForPayRoll(@Param("month") int month,
			@Param("year") int year, @Param("locId") List<Integer> locId,  @Param("date") String date);

	@Query(value = "select\n" + 
			"        e.emp_code,e.sub_cmp_id,\n" + 
			"        et.name as emp_type_name,\n" + 
			"        et.emp_type_id,\n" + 
			"        l.loc_name,\n" + 
			"        st.sal_type_name,\n" + 
			"        de.name as designation,\n" + 
			"        dp.name as dept_name,\n" + 
			"        de.desig_id,\n" + 
			"        l.loc_id,\n" + 
			"        dp.depart_id,\n" + 
			"        e.contractor_id,\n" + 
			"        concat(e.first_name,\n" + 
			"        ' ',\n" + 
			"        e.surname) as emp_name,\n" + 
			"        si.*,\n" + 
			"        sd.id as sum_id, 0 as count_leave, 0 as can_generate_sal     \n" + 
			"    from\n" + 
			"        tbl_emp_salary_info si,\n" + 
			"        m_employees e,\n" + 
			"        tbl_mst_emp_types et,\n" + 
			"        m_location l,\n" + 
			"        mst_salary_types st,\n" + 
			"        m_designation de,\n" + 
			"        m_department dp,\n" + 
			"        tbl_attt_summary_daily sd     \n" + 
			"    where\n" + 
			"        e.emp_id=si.emp_id          \n" + 
			"        and e.del_status=1          \n" + 
			"        and et.emp_type_id=e.emp_type          \n" + 
			"        and l.loc_id = e.location_id          \n" + 
			"        and st.sal_type_id=si.salary_type_id          \n" + 
			"        and de.desig_id=e.designation_id          \n" + 
			"        and dp.depart_id=e.depart_id          \n" + 
			"        and sd.emp_id=e.emp_id         \n" + 
			"        and sd.month=:month \n" + 
			"        and sd.year=:year       \n" + 
			"        and e.emp_id not in (\n" + 
			"            select\n" + 
			"                emp_id              \n" + 
			"            from\n" + 
			"                tbl_salary_dynamic_temp              \n" + 
			"            where\n" + 
			"                calc_month=sd.month                  \n" + 
			"                and calc_year=sd.year         \n" + 
			"        ) \n" + 
			"        and e.emp_id in (:empIds)\n" + 
			"        and e.emp_id not in (\n" + 
			"            select\n" + 
			"                emp_id              \n" + 
			"            from\n" + 
			"                tbl_salary_calc              \n" + 
			"            where\n" + 
			"                calc_month=sd.month                 \n" + 
			"                and calc_year=sd.year         \n" + 
			"        ) \n" + 
			"    order by\n" + 
			"        e.emp_id", nativeQuery = true)
	List<EmpSalaryInfoForPayroll> getEmployeeListWithEmpSalEnfoForPayRollForTempInsert(@Param("month") int month,
			@Param("year") int year,@Param("empIds") List<Integer> empIds);

	
	@Query(value = "select a.*,ifnull(b.count_leave,0) as count_leave, 0 as can_generate_sal from ( select \n" + 
			"        e.emp_code,\n" + 
			"        e.sub_cmp_id,\n" + 
			"        et.name as emp_type_name,\n" + 
			"        et.emp_type_id,\n" + 
			"        l.loc_name,\n" + 
			"        st.sal_type_name,\n" + 
			"        de.name as designation,\n" + 
			"        dp.name as dept_name,\n" + 
			"        de.desig_id,\n" + 
			"        l.loc_id,\n" + 
			"        dp.depart_id,\n" + 
			"        e.contractor_id,\n" + 
			"        concat(e.first_name,\n" + 
			"        ' ',\n" + 
			"        e.surname) as emp_name,\n" + 
			"        si.*,\n" + 
			"        sd.id as sum_id     \n" + 
			"    from\n" + 
			"        tbl_emp_salary_info si,\n" + 
			"        m_employees e,\n" + 
			"        tbl_mst_emp_types et,\n" + 
			"        m_location l,\n" + 
			"        mst_salary_types st,\n" + 
			"        m_designation de,\n" + 
			"        m_department dp,\n" + 
			"        tbl_attt_summary_daily sd     \n" + 
			"    where\n" + 
			"        e.emp_id=si.emp_id          \n" + 
			"        and e.del_status=1          \n" + 
			"        and et.emp_type_id=e.emp_type          \n" + 
			"        and l.loc_id = e.location_id          \n" + 
			"        and st.sal_type_id=si.salary_type_id          \n" + 
			"        and de.desig_id=e.designation_id          \n" + 
			"        and dp.depart_id=e.depart_id          \n" + 
			"        and sd.emp_id=e.emp_id         \n" + 
			"        and sd.month=:month \n" + 
			"        and sd.year=:year         \n" + 
			"        and e.emp_id not in (\n" + 
			"            select\n" + 
			"                emp_id              \n" + 
			"            from\n" + 
			"                tbl_salary_calc              \n" + 
			"            where\n" + 
			"                calc_month=:month                  \n" + 
			"                and calc_year=:year         \n" + 
			"        ) \n" + 
			"        and sd.total_days_inmonth=(\n" + 
			"            SELECT\n" + 
			"                count(*) \n" + 
			"            FROM\n" + 
			"                tbl_attt_daily_daily \n" + 
			"            where\n" + 
			"                YEAR(att_date) =sd.year \n" + 
			"                and MONTH(att_date) =sd.month \n" + 
			"                and e.emp_id=tbl_attt_daily_daily.emp_id \n" + 
			"                and is_fixed=:isFixed\n" + 
			"                and rec_status=:sts\n" + 
			"        ) \n" + 
			"        and e.location_id=(:locId)     \n" + 
			"    order by\n" + 
			"        e.emp_id) a\n" + 
			"left join (\n" + 
			"  select count('') as count_leave,e.emp_id from m_employees e,leave_apply la where la.emp_id=e.emp_id and la.ex_int1 in(1,2) group by e.emp_id\n" + 
			") b on b.emp_id=a.emp_id", nativeQuery = true)
	List<EmpSalaryInfoForPayroll> getListForfixunfixAttendance(int month, int year, int isFixed, String sts, List<Integer> locId);

}
