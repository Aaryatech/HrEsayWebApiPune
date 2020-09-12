package com.ats.hrmgt.model.repo.dash;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.model.dashboard.GetBirthDaysForDash;

public interface GetBirthDaysForDashRepo extends JpaRepository<GetBirthDaysForDash, Integer>{
	
	
	
	@Query(value = "SELECT\n" + 
			"   UUID() as uuid,emp_code,  dob,te.emp_id,\n" + 
			"    CONCAT(first_name, ' ', surname) AS name,  TIMESTAMPDIFF(YEAR, dob, :currDate) AS age \n" + 
			"FROM\n" + 
			"    tbl_emp_info tei\n" + 
			"INNER JOIN m_employees te ON\n" + 
			"    tei.emp_id = te.emp_id\n" + 
			"WHERE\n" + 
			"    DATE_FORMAT(dob, '%m-%d') = DATE_FORMAT( :currDate, '%m-%d') AND tei.del_status=1", nativeQuery = true)
	List<GetBirthDaysForDash> getTodaysBirth(@Param("currDate") String currDate);
	
	
	
	@Query(value = "SELECT\n" + 
			"    UUID() as uuid,emp_code,te.emp_id,\n" + 
			"    dob,\n" + 
			"    CONCAT(first_name, ' ', surname) AS NAME,\n" + 
			"    TIMESTAMPDIFF(YEAR, dob, :currDate) AS age\n" + 
			"FROM\n" + 
			"    tbl_emp_info tei\n" + 
			"INNER JOIN m_employees te ON\n" + 
			"    tei.emp_id = te.emp_id\n" + 
			"WHERE\n" + 
			"    DATE_FORMAT(dob, '%m-%d') BETWEEN DATE_FORMAT(DATE_ADD(:currDate, INTERVAL 1 DAY), '%m-%d') AND DATE_FORMAT(\n" + 
			"        DATE_ADD(:currDate, INTERVAL 6 DAY),\n" + 
			"        '%m-%d'\n" + 
			"    )  AND tei.del_status=1", nativeQuery = true)
	List<GetBirthDaysForDash> getWeekBirth(@Param("currDate") String currDate);


	@Query(value = "SELECT\n" + 
			"        UUID() as uuid,emp_code,\n" + 
			"        dob,\n" + 
			"        te.emp_id,\n" + 
			"        CONCAT(first_name,\n" + 
			"        ' ',\n" + 
			"        surname) AS name,\n" + 
			"        TIMESTAMPDIFF(YEAR,\n" + 
			"        dob,\n" + 
			"        :fiterdate) AS age  \n" + 
			"    FROM\n" + 
			"        tbl_emp_info tei \n" + 
			"    INNER JOIN\n" + 
			"        m_employees te \n" + 
			"            ON     tei.emp_id = te.emp_id \n" + 
			"    WHERE\n" + 
			"        te.emp_id=:empId and\n" + 
			"          tei.del_status=1", nativeQuery = true)
	GetBirthDaysForDash loginUserBirthDay(String fiterdate, int empId);

}
