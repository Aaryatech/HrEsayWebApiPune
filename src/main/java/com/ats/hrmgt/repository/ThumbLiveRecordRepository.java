package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.ThumbLiveRecord;

public interface ThumbLiveRecordRepository extends JpaRepository<ThumbLiveRecord, Integer>{

	@Query(value="select a.* from (\n"
			+ "                select uuid() as id,\n"
			+ "                HRRAWINOUTDATA.EmpCode as emp_code,\n"
			+ "                count('') as count_thumb,\n"
			+ "                GROUP_CONCAT(DISTINCT PunchDateTime SEPARATOR ', ') as thumb_record,\n"
			+ "                CONCAT(emp.first_name,' ',emp.surname) as emp_name,\n"
			+ "                dept.name as dept_name\n"
			+ "            from\n"
			+ "                HRRAWINOUTDATA,\n"
			+ "                m_employees emp,\n"
			+ "                m_department dept\n"
			+ "            where\n"
			+ "                DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:date              \n"
			+ "                and VerMode=0 and emp.emp_code= HRRAWINOUTDATA.EmpCode  and  emp.del_status=1 \n"
			+ "                and dept.depart_id = emp.depart_id\n"
			+ "                and emp.location_id=:locId\n"
			+ "            group by\n"
			+ "                EmpCode order by dept.name,emp_name) a  \n"
			+ "            where   a.count_thumb>1    ", nativeQuery=true)
	List<ThumbLiveRecord> getPresentAttendaceLiveRecordFromThumb(int locId, String date);

	@Query(value="select a.* from (\n"
			+ "                select uuid() as id,\n"
			+ "                HRRAWINOUTDATA.EmpCode as emp_code,\n"
			+ "                count('') as count_thumb,\n"
			+ "                GROUP_CONCAT(DISTINCT PunchDateTime SEPARATOR ', ') as thumb_record,\n"
			+ "                CONCAT(emp.first_name,' ',emp.surname) as emp_name,\n"
			+ "                dept.name as dept_name\n"
			+ "            from\n"
			+ "                HRRAWINOUTDATA,\n"
			+ "                m_employees emp,\n"
			+ "                m_department dept\n"
			+ "            where\n"
			+ "                DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:date              \n"
			+ "                and VerMode=0 and emp.emp_code= HRRAWINOUTDATA.EmpCode  and  emp.del_status=1 \n"
			+ "                and dept.depart_id = emp.depart_id\n"
			+ "                and emp.location_id=:locId\n"
			+ "            group by\n"
			+ "                EmpCode order by dept.name,emp_name) a  \n"
			+ "            where   a.count_thumb=1    ", nativeQuery=true)
	List<ThumbLiveRecord> getAvailableAttendaceLiveRecordFromThumb(int locId, String date);

}
