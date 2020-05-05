package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.InfoForUploadAttendance;

public interface InfoForUploadAttendanceRepository extends JpaRepository<InfoForUploadAttendance, Integer>{

	@Query(value="select\n" + 
			"        COALESCE(( select\n" + 
			"            count(*) \n" + 
			"        from\n" + 
			"            m_employees,\n" + 
			"            tbl_emp_salary_info \n" + 
			"        where\n" + 
			"            m_employees.emp_id=tbl_emp_salary_info.emp_id \n" + 
			"            and m_employees.del_status=1 and (tbl_emp_salary_info.cmp_leaving_date IS NULL or tbl_emp_salary_info.cmp_leaving_date='' or tbl_emp_salary_info.cmp_leaving_date=1970-00-00 or  date_format(tbl_emp_salary_info.cmp_leaving_date,'%Y-%m')>=date_format(:fromDate,'%Y-%m')) ),\n" + 
			"        0)  as total_emp,\n" + 
			"        DATEDIFF(:toDate,\n" + 
			"        :fromDate) as date_diff,\n" + 
			"        COALESCE(( select\n" + 
			"            count(*) \n" + 
			"        from\n" + 
			"            tbl_attt_daily_daily,m_employees \n" + 
			"        where\n" + 
			"            att_date between :fromDate and :toDate and m_employees.emp_id=tbl_attt_daily_daily.emp_id and m_employees.del_status=1),\n" + 
			"        0)  as updated_by_step1,\n" + 
			"        COALESCE(( select\n" + 
			"            count(*) \n" + 
			"        from\n" + 
			"            tbl_attt_daily_daily,m_employees \n" + 
			"        where\n" + 
			"            att_date between :fromDate and :toDate \n" + 
			"            and by_file_updated=1 and m_employees.emp_id=tbl_attt_daily_daily.emp_id and m_employees.del_status=1),\n" + 
			"        0)  as updated_by_file",nativeQuery=true)
	InfoForUploadAttendance getInformationOfUploadedAttendance(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate);

	@Query(value="select\n" + 
			"        COALESCE(( select\n" + 
			"            count('')          \n" + 
			"        from\n" + 
			"            m_employees,\n" + 
			"            tbl_emp_salary_info          \n" + 
			"        where\n" + 
			"            m_employees.emp_id=tbl_emp_salary_info.emp_id              \n" + 
			"            and m_employees.del_status=1 ),\n" + 
			"        0)  as total_emp,\n" + 
			"         DATEDIFF(:toDate,\n" + 
			"        :fromDate) as date_diff,\n" + 
			"        COALESCE(( select\n" + 
			"            count('')          \n" + 
			"        from\n" + 
			"            t_shift_assign_daily,\n" + 
			"            m_employees          \n" + 
			"        where\n" + 
			"            shift_date between :fromDate and :toDate \n" + 
			"            and m_employees.emp_id=t_shift_assign_daily.emp_id \n" + 
			"            and m_employees.del_status=1),\n" + 
			"        0)  as updated_by_step1,\n" + 
			"        0  as updated_by_file",nativeQuery=true) 
	InfoForUploadAttendance getInformationOfUploadedShiftAssign(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate);

}
