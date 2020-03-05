package com.ats.hrmgt.model.repo.dash;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.dashboard.AgeDiversityDash;
import com.ats.hrmgt.model.dashboard.DeptWiseWeekoffDash;

public interface AgeDiversityDashRepo extends JpaRepository<AgeDiversityDash, Integer> {
	 
	@Query(value = "SELECT  UUID() AS uni_key,\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_info.dob,\n" + 
			"            :currDate\n" + 
			"        ) BETWEEN 18 AND 20 AND tbl_emp_info.del_status = 1\n" + 
			") AS age_range1,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_info.dob,\n" + 
			"            :currDate\n" + 
			"        ) BETWEEN 21 AND 25 AND tbl_emp_info.del_status = 1\n" + 
			") AS age_range2,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_info.dob,\n" + 
			"            :currDate\n" + 
			"        ) BETWEEN 26 AND 30 AND tbl_emp_info.del_status = 1\n" + 
			") AS age_range3,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_info.dob,\n" + 
			"            :currDate\n" + 
			"        ) BETWEEN 26 AND 30 AND tbl_emp_info.del_status = 1\n" + 
			") AS age_range4,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_info.dob,\n" + 
			"            :currDate\n" + 
			"        ) BETWEEN 31 AND 35 AND tbl_emp_info.del_status = 1\n" + 
			") AS age_range5,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_info.dob,\n" + 
			"            :currDate\n" + 
			"        ) BETWEEN 36 AND 40 AND tbl_emp_info.del_status = 1\n" + 
			") AS age_range6,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_info.dob,\n" + 
			"            :currDate\n" + 
			"        ) BETWEEN 41 AND 45 AND tbl_emp_info.del_status = 1\n" + 
			") AS age_range7,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_info.dob,\n" + 
			"            :currDate\n" + 
			"        ) BETWEEN 46 AND 50 AND tbl_emp_info.del_status = 1\n" + 
			") AS age_range8,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_info.dob,\n" + 
			"            :currDate\n" + 
			"        ) BETWEEN 51 AND 55 AND tbl_emp_info.del_status = 1\n" + 
			") AS age_range9,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_info.dob,\n" + 
			"            :currDate\n" + 
			"        ) BETWEEN 56 AND 60 AND tbl_emp_info.del_status = 1\n" + 
			") AS age_range10,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT tbl_emp_info.emp_id)\n" + 
			"    FROM\n" + 
			"        tbl_emp_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_info.dob,\n" + 
			"            :currDate\n" + 
			"        ) BETWEEN 61 AND 65 AND tbl_emp_info.del_status = 1\n" + 
			") AS age_range11", nativeQuery = true)
	AgeDiversityDash getAttendance(@Param("currDate") String currDate);
	
	
	@Query(value = "\n" + 
			"SELECT UUID() as uni_key ,  \n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_salary_info.cmp_joining_date,\n" + 
			"            '2020-03-05'\n" + 
			"        ) = 0 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range1,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_salary_info.cmp_joining_date,\n" + 
			"            '2020-03-05'\n" + 
			"        ) = 1 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range2,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_salary_info.cmp_joining_date,\n" + 
			"            '2020-03-05'\n" + 
			"        ) = 2 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range3,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_salary_info.cmp_joining_date,\n" + 
			"            '2020-03-05'\n" + 
			"        ) = 5 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range4,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_salary_info.cmp_joining_date,\n" + 
			"            '2020-03-05'\n" + 
			"        ) = 10 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range5,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        TIMESTAMPDIFF(\n" + 
			"            YEAR,\n" + 
			"            tbl_emp_salary_info.cmp_joining_date,\n" + 
			"            '2020-03-05'\n" + 
			"        ) > 10 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range6, 0 as age_range7 ,0 as age_range8,0 as age_range9,0 as age_range10 ,0 as age_range11 \n" + 
			"", nativeQuery = true)
	AgeDiversityDash getExperienceDiversity(@Param("currDate") String currDate);
	
	
	

	@Query(value = "SELECT UUID() as uni_key , \n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_salary_info.gross_salary < 10000 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range1,  (\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_salary_info.gross_salary  BETWEEN 10000 AND 20000 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range2,(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_salary_info.gross_salary  BETWEEN 21000 AND 30000 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range3,(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_salary_info.gross_salary  BETWEEN 31000 AND 40000 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range4,(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_salary_info.gross_salary  BETWEEN 41000 AND 50000 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range5,(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_salary_info.gross_salary  BETWEEN 51000 AND 60000 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range6,(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_salary_info.gross_salary  BETWEEN 61000 AND 70000 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range7,(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_salary_info.gross_salary  BETWEEN 71000 AND 80000 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range8,(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_salary_info.gross_salary  BETWEEN 81000 AND 90000 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range9,(\n" + 
			"    SELECT\n" + 
			"        COUNT(\n" + 
			"            DISTINCT tbl_emp_salary_info.emp_id\n" + 
			"        )\n" + 
			"    FROM\n" + 
			"        tbl_emp_salary_info\n" + 
			"    WHERE\n" + 
			"        tbl_emp_salary_info.gross_salary  BETWEEN 91000 AND 100000 AND tbl_emp_salary_info.del_status = 1\n" + 
			") AS age_range10, 0 as age_range11", nativeQuery = true)
	AgeDiversityDash getSalaryDiversity(@Param("currDate") String currDate);


}
