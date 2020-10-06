package com.ats.hrmgt.model.repo.dash;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.dashboard.AgeDiversityDash;
import com.ats.hrmgt.model.dashboard.DeptWiseWeekoffDash;

public interface AgeDiversityDashRepo extends JpaRepository<AgeDiversityDash, Integer> {
	 
	@Query(value = "SELECT\n" + 
			"        UUID() AS uni_key,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(DISTINCT tbl_emp_info.emp_id)     \n" + 
			"        FROM\n" + 
			"            tbl_emp_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_info.dob,:currDate) BETWEEN 18 AND 20 \n" + 
			"            AND tbl_emp_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_info.emp_id and e.location_id=:locId) AS age_range1,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(DISTINCT tbl_emp_info.emp_id)     \n" + 
			"        FROM\n" + 
			"            tbl_emp_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_info.dob,    :currDate       ) BETWEEN 21 AND 25 \n" + 
			"            AND tbl_emp_info.del_status = 1  and e.del_status=1 and e.emp_id=tbl_emp_info.emp_id and e.location_id=:locId) AS age_range2,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(DISTINCT tbl_emp_info.emp_id)     \n" + 
			"        FROM\n" + 
			"            tbl_emp_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_info.dob,     :currDate     ) BETWEEN 26 AND 30 \n" + 
			"            AND tbl_emp_info.del_status = 1  and e.del_status=1 and e.emp_id=tbl_emp_info.emp_id and e.location_id=:locId) AS age_range3,\n" + 
			"        (0) AS age_range4,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(DISTINCT tbl_emp_info.emp_id)     \n" + 
			"        FROM\n" + 
			"            tbl_emp_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_info.dob,     :currDate    ) BETWEEN 31 AND 35 \n" + 
			"            AND tbl_emp_info.del_status = 1  and e.del_status=1 and e.emp_id=tbl_emp_info.emp_id and e.location_id=:locId) AS age_range5,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(DISTINCT tbl_emp_info.emp_id)     \n" + 
			"        FROM\n" + 
			"            tbl_emp_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_info.dob,   :currDate   ) BETWEEN 36 AND 40 \n" + 
			"            AND tbl_emp_info.del_status = 1  and e.del_status=1 and e.emp_id=tbl_emp_info.emp_id and e.location_id=:locId) AS age_range6,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(DISTINCT tbl_emp_info.emp_id)     \n" + 
			"        FROM\n" + 
			"            tbl_emp_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_info.dob,  :currDate     ) BETWEEN 41 AND 45 \n" + 
			"            AND tbl_emp_info.del_status = 1  and e.del_status=1 and e.emp_id=tbl_emp_info.emp_id and e.location_id=:locId) AS age_range7,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(DISTINCT tbl_emp_info.emp_id)     \n" + 
			"        FROM\n" + 
			"            tbl_emp_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_info.dob, :currDate  ) BETWEEN 46 AND 50 \n" + 
			"            AND tbl_emp_info.del_status = 1  and e.del_status=1 and e.emp_id=tbl_emp_info.emp_id and e.location_id=:locId) AS age_range8,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(DISTINCT tbl_emp_info.emp_id)     \n" + 
			"        FROM\n" + 
			"            tbl_emp_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_info.dob,:currDate   ) BETWEEN 51 AND 55 \n" + 
			"            AND tbl_emp_info.del_status = 1  and e.del_status=1 and e.emp_id=tbl_emp_info.emp_id and e.location_id=:locId) AS age_range9,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(DISTINCT tbl_emp_info.emp_id)     \n" + 
			"        FROM\n" + 
			"            tbl_emp_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_info.dob,:currDate   ) BETWEEN 56 AND 60 \n" + 
			"            AND tbl_emp_info.del_status = 1  and e.del_status=1 and e.emp_id=tbl_emp_info.emp_id and e.location_id=:locId) AS age_range10,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(DISTINCT tbl_emp_info.emp_id)     \n" + 
			"        FROM\n" + 
			"            tbl_emp_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_info.dob, :currDate   ) BETWEEN 61 AND 65 \n" + 
			"            AND tbl_emp_info.del_status = 1  and e.del_status=1 and e.emp_id=tbl_emp_info.emp_id and e.location_id=:locId) AS age_range11", nativeQuery = true)
	AgeDiversityDash getAttendance(@Param("currDate") String currDate,@Param("locId") int locId);
	
	
	@Query(value = "SELECT\n" + 
			"        UUID() as uni_key ,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_salary_info.cmp_joining_date,             :currDate         ) = 0 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range1,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_salary_info.cmp_joining_date,             :currDate         ) = 1 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range2,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_salary_info.cmp_joining_date,             :currDate         ) = 2 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range3,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_salary_info.cmp_joining_date,             :currDate         ) = 5 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range4,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_salary_info.cmp_joining_date,             :currDate         ) = 10 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range5,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            TIMESTAMPDIFF(             YEAR,             tbl_emp_salary_info.cmp_joining_date,             :currDate         ) > 10 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range6,\n" + 
			"        0 as age_range7 ,\n" + 
			"        0 as age_range8,\n" + 
			"        0 as age_range9,\n" + 
			"        0 as age_range10 ,\n" + 
			"        0 as age_range11 ", nativeQuery = true)
	AgeDiversityDash getExperienceDiversity(@Param("currDate") String currDate,@Param("locId")  int locId);
	
	
	

	@Query(value = "SELECT\n" + 
			"        UUID() as uni_key ,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            tbl_emp_salary_info.gross_salary < 10000 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range1,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info , m_employees e    \n" + 
			"        WHERE\n" + 
			"            tbl_emp_salary_info.gross_salary  BETWEEN 10000 AND 20000 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range2,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            tbl_emp_salary_info.gross_salary  BETWEEN 20000 AND 30000 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range3,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            tbl_emp_salary_info.gross_salary  BETWEEN 30000 AND 40000 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range4,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info , m_employees e    \n" + 
			"        WHERE\n" + 
			"            tbl_emp_salary_info.gross_salary  BETWEEN 40000 AND 50000 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range5,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            tbl_emp_salary_info.gross_salary  BETWEEN 50000 AND 60000 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range6,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            tbl_emp_salary_info.gross_salary  BETWEEN 60000 AND 70000 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range7,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            tbl_emp_salary_info.gross_salary  BETWEEN 70000 AND 80000 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range8,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            tbl_emp_salary_info.gross_salary  BETWEEN 80000 AND 90000 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range9,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(             DISTINCT tbl_emp_salary_info.emp_id         )     \n" + 
			"        FROM\n" + 
			"            tbl_emp_salary_info, m_employees e     \n" + 
			"        WHERE\n" + 
			"            tbl_emp_salary_info.gross_salary  BETWEEN 90000 AND 100000 \n" + 
			"            AND tbl_emp_salary_info.del_status = 1 and e.del_status=1 and e.emp_id=tbl_emp_salary_info.emp_id and e.location_id=:locId) AS age_range10,\n" + 
			"        0 as age_range11", nativeQuery = true)
	AgeDiversityDash getSalaryDiversity(@Param("locId") int locId);


}
