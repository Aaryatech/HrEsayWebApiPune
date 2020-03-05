package com.ats.hrmgt.model.repo.dash;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.dashboard.GetBirthDaysForDash;
import com.ats.hrmgt.model.dashboard.GetNewHiresDash;

public interface GetNewHiresDashRepo extends JpaRepository<GetNewHiresDash, String>{
	
	

	@Query(value = "SELECT\n" + 
			" UUID() as uni_key ,  (\n" + 
			"    SELECT\n" + 
			"        COUNT(tei.salary_info_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info tei,\n" + 
			"        tbl_emp_info einfo\n" + 
			"    WHERE\n" + 
			"        einfo.gender = 'MALE' AND tei.emp_id = einfo.emp_id AND tei.cmp_joining_date BETWEEN DATE_ADD(:currDate, INTERVAL -30 DAY) AND :currDate AND tei.del_status = 1\n" + 
			") AS male_emp,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(teiNew.salary_info_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info teiNew,\n" + 
			"        tbl_emp_info einfoNew\n" + 
			"    WHERE\n" + 
			"        einfoNew.gender = 'FEMALE' AND teiNew.emp_id = einfoNew.emp_id AND teiNew.cmp_joining_date BETWEEN DATE_ADD(:currDate, INTERVAL -30 DAY) AND :currDate AND teiNew.del_status = 1\n" + 
			") AS female_emp,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(teiNew.salary_info_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info teiNew,\n" + 
			"        tbl_emp_info einfoNew\n" + 
			"    WHERE\n" + 
			"        einfoNew.gender = ! 'FEMALE' AND einfoNew.gender = ! 'MALE' AND teiNew.emp_id = einfoNew.emp_id AND teiNew.cmp_joining_date BETWEEN DATE_ADD(:currDate, INTERVAL -30 DAY) AND :currDate AND teiNew.del_status = 1\n" + 
			") AS oth_emp", nativeQuery = true)
	GetNewHiresDash getTodaysHire(@Param("currDate") String currDate);
	
	
	@Query(value = "SELECT UUID() as uni_key ,  \n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_info.gender = 'MALE' AND tbl_emp_info.del_status = 1\n" + 
			") AS male_emp,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_info.gender = 'FEMALE' AND tbl_emp_info.del_status = 1\n" + 
			") AS female_emp,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_info.gender = ! 'MALE' AND tbl_emp_info.gender != 'FEMALE' AND tbl_emp_info.del_status = 1\n" + 
			") AS oth_emp", nativeQuery = true)
	GetNewHiresDash getAgeDiversity();

}
