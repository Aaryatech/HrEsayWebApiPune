package com.ats.hrmgt.model.repo.dash;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
import com.ats.hrmgt.model.dashboard.GetAllPendingMasterDet;
 
public interface GetAllPendingMasterDetRepo  extends JpaRepository<GetAllPendingMasterDet,String>{
 
	@Query(value = "SELECT  UUID() as uni_key, \n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        tbl_mst_sub_company\n" + 
			"    WHERE\n" + 
			"        tbl_mst_sub_company.del_status = 0\n" + 
			") AS company_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        tbl_mst_emp_types\n" + 
			"    WHERE\n" + 
			"        tbl_mst_emp_types.del_status = 0\n" + 
			") AS emptype_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_location\n" + 
			"    WHERE\n" + 
			"        m_location.del_status = 0\n" + 
			") AS loc_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_designation\n" + 
			"    WHERE\n" + 
			"        m_designation.del_status = 0\n" + 
			") AS desn_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_department\n" + 
			"    WHERE\n" + 
			"        m_department.del_status = 0\n" + 
			") AS dept_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        holiday_master\n" + 
			"    WHERE\n" + 
			"        holiday_master.del_status = 0\n" + 
			") AS ho_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        holiday_category\n" + 
			"    WHERE\n" + 
			"        holiday_category.del_status = 0\n" + 
			") AS hocat_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        weekoff_category\n" + 
			"    WHERE\n" + 
			"        weekoff_category.del_status = 0\n" + 
			") AS wocat_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        tbl_shift_timming\n" + 
			"    WHERE\n" + 
			"        tbl_shift_timming.status = 0\n" + 
			") AS shift_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        leave_type\n" + 
			"    WHERE\n" + 
			"        leave_type.del_status = 0\n" + 
			") AS lvtype_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        leave_structure_header\n" + 
			"    WHERE\n" + 
			"        leave_structure_header.del_status = 0\n" + 
			") AS lvstruct_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_employees\n" + 
			"    WHERE\n" + 
			"        m_employees.sub_cmp_id = 0 AND m_employees.del_status=1\n" + 
			") AS comp_pending,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_employees\n" + 
			"    WHERE\n" + 
			"        m_employees.emp_type = 0  AND m_employees.del_status=1\n" + 
			") AS type_pending,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_employees\n" + 
			"    WHERE\n" + 
			"        m_employees.depart_id = 0  AND m_employees.del_status=1\n" + 
			") AS dept_pending,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_employees\n" + 
			"    WHERE\n" + 
			"        m_employees.designation_id = 0  AND m_employees.del_status=1\n" + 
			") AS desn_pending,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_employees\n" + 
			"    WHERE\n" + 
			"        m_employees.current_shiftid = 0  AND m_employees.del_status=1\n" + 
			") AS shift_pending,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_employees\n" + 
			"    WHERE\n" + 
			"        m_employees.location_id = 0  AND m_employees.del_status=1\n" + 
			") AS loc_pending,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_employees\n" + 
			"    WHERE\n" + 
			"        m_employees.holiday_category = 0  AND m_employees.del_status=1\n" + 
			") AS hocat_pending,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_employees\n" + 
			"    WHERE\n" + 
			"        m_employees.weekend_category = 0  AND m_employees.del_status=1\n" + 
			") AS wocat_pending,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_employees,\n" + 
			"        leave_structure_allotment\n" + 
			"    WHERE\n" + 
			"        m_employees.emp_id != leave_structure_allotment.emp_id AND leave_structure_allotment.del_status=1 \n" + 
			") AS lv_struvt_pending,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT('')\n" + 
			"    FROM\n" + 
			"        m_employees,\n" + 
			"        leave_authority\n" + 
			"    WHERE\n" + 
			"        m_employees.emp_id != leave_authority.emp_id AND leave_authority.del_status=1\n" + 
			") AS lv_auth_pending", nativeQuery = true)
	 GetAllPendingMasterDet  getDet();
	
}
