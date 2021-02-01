package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LiveThumbData;

public interface LiveThumbDataRepository extends JpaRepository<LiveThumbData, Integer> {

	
	@Query(value="select a.*,b.in_id,b.in_punch_time,b.in_date,b.in_time,c.out_id,c.out_punch_time,c.out_date,c.out_time from (select uuid() as id,HRRAWINOUTDATA.EmpCode as emp_code from HRRAWINOUTDATA where DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:date group by EmpCode) a \n"
			+ "left join (SELECT  dt.PunchDateTime as in_punch_time,dt.id as in_id,DATE_FORMAT(dt.PunchDateTime, '%Y-%m-%d') as in_date,DATE_FORMAT(dt.PunchDateTime, '%H:%i:%s') as in_time,  dt.EmpCode as emp_code\n"
			+ "FROM HRRAWINOUTDATA dt\n"
			+ "JOIN (\n"
			+ "       Select EmpCode as emp_code , min(id) id\n"
			+ "       from HRRAWINOUTDATA where DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:date \n"
			+ "       group by EmpCode) T\n"
			+ "    on dt.EmpCode = T.emp_code\n"
			+ "   and dt.id = T.id ) b on a.emp_code=b.emp_code\n"
			+ "left join (SELECT  dt.PunchDateTime as out_punch_time,dt.id as out_id,DATE_FORMAT(dt.PunchDateTime, '%Y-%m-%d') as out_date,DATE_FORMAT(dt.PunchDateTime, '%H:%i:%s') as out_time,  dt.EmpCode as emp_code\n"
			+ "FROM HRRAWINOUTDATA dt\n"
			+ "JOIN (\n"
			+ "       Select EmpCode as emp_code , max(id) id\n"
			+ "       from HRRAWINOUTDATA where DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:date \n"
			+ "       group by EmpCode) T\n"
			+ "    on dt.EmpCode = T.emp_code\n"
			+ "   and dt.id = T.id ) c on a.emp_code=c.emp_code",nativeQuery=true)
	List<LiveThumbData> getThumbData(String date);

}
