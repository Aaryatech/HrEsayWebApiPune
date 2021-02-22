package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LiveThumbData;

public interface LiveThumbDataRepository extends JpaRepository<LiveThumbData, Integer> {

	
	/*
	 * @Query(value="select\n" + "        a.*,\n" + "        b.in_id,\n" +
	 * "        b.in_punch_time,\n" + "        b.in_date,\n" +
	 * "        b.in_time,\n" + "        c.out_id,\n" +
	 * "        c.out_punch_time,\n" + "        c.out_date,\n" +
	 * "        c.out_time\n" + "    from\n" + "        (select\n" +
	 * "            uuid() as id,\n" +
	 * "            HRRAWINOUTDATA.EmpCode as emp_code,count('') as count_thumb  \n"
	 * + "        from\n" + "            HRRAWINOUTDATA \n" + "        where\n" +
	 * "            DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:date and VerMode=0\n" +
	 * "        group by\n" + "            EmpCode) a  \n" + "    left join\n" +
	 * "        (\n" + "            SELECT\n" +
	 * "                dt.PunchDateTime as in_punch_time,\n" +
	 * "                dt.id as in_id,\n" +
	 * "                DATE_FORMAT(dt.PunchDateTime,\n" +
	 * "                '%Y-%m-%d') as in_date,\n" +
	 * "                DATE_FORMAT(dt.PunchDateTime,\n" +
	 * "                '%H:%i:%s') as in_time,\n" +
	 * "                dt.EmpCode as emp_code \n" + "            FROM\n" +
	 * "                HRRAWINOUTDATA dt \n" + "            JOIN\n" +
	 * "                (\n" + "                    Select\n" +
	 * "                        EmpCode as emp_code ,\n" +
	 * "                        min(id) id        \n" + "                    from\n"
	 * + "                        HRRAWINOUTDATA \n" + "                    where\n"
	 * +
	 * "                        DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:date  and VerMode=0      \n"
	 * + "                    group by\n" + "                        EmpCode\n" +
	 * "                ) T     \n" +
	 * "                    on dt.EmpCode = T.emp_code    \n" +
	 * "                    and dt.id = T.id \n" + "                ) b \n" +
	 * "                    on a.emp_code=b.emp_code \n" + "            left join\n"
	 * + "                (\n" + "                    SELECT\n" +
	 * "                        dt.PunchDateTime as out_punch_time,\n" +
	 * "                        dt.id as out_id,\n" +
	 * "                        DATE_FORMAT(dt.PunchDateTime,\n" +
	 * "                        '%Y-%m-%d') as out_date,\n" +
	 * "                        DATE_FORMAT(dt.PunchDateTime,\n" +
	 * "                        '%H:%i:%s') as out_time,\n" +
	 * "                        dt.EmpCode as emp_code \n" +
	 * "                    FROM\n" + "                        HRRAWINOUTDATA dt \n"
	 * + "                    JOIN\n" + "                        (\n" +
	 * "                            Select\n" +
	 * "                                EmpCode as emp_code ,\n" +
	 * "                                max(id) id        \n" +
	 * "                            from\n" +
	 * "                                HRRAWINOUTDATA \n" +
	 * "                            where\n" +
	 * "                                DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:date and VerMode=0      \n"
	 * + "                            group by\n" +
	 * "                                EmpCode \n" +
	 * "                        ) T     \n" +
	 * "                            on dt.EmpCode = T.emp_code    \n" +
	 * "                            and dt.id = T.id \n" +
	 * "                        ) c \n" +
	 * "                            on a.emp_code=c.emp_code\n" +
	 * "            where \n" + "            a.count_thumb>1",nativeQuery=true)
	 */
	
	@Query(value="select\n"
			+ "        a.*,ifnull(d.count_thumb,0) as count_thumb,\n"
			+ "        ifnull(b.in_id,0) as in_id,\n"
			+ "        ifnull(b.in_punch_time,0) as in_punch_time,\n"
			+ "        ifnull(b.in_date,0) as in_date,\n"
			+ "        ifnull(b.in_time,0) as in_time,\n"
			+ "        ifnull(c.out_id,0) as out_id,\n"
			+ "        ifnull(c.out_punch_time,0) as out_punch_time,\n"
			+ "        ifnull(c.out_date,0) as out_date, \n"
			+ "        ifnull(c.out_time,0) as out_time    \n"
			+ "    from\n"
			+ "        (SELECT uuid() as id,emp.emp_code FROM m_employees emp where emp.del_status=1) a \n"
			+ "    left join(\n"
			+ "    select\n"
			+ "            uuid() as id,\n"
			+ "            HRRAWINOUTDATA.EmpCode as emp_code,\n"
			+ "            count('') as count_thumb           \n"
			+ "        from\n"
			+ "            HRRAWINOUTDATA          \n"
			+ "        where\n"
			+ "            DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:date \n"
			+ "            and VerMode=0         \n"
			+ "        group by\n"
			+ "            EmpCode\n"
			+ "    ) d on d.emp_code=a.emp_code and d.count_thumb>1\n"
			+ "    left join\n"
			+ "        (\n"
			+ "            SELECT\n"
			+ "                dt.PunchDateTime as in_punch_time,\n"
			+ "                dt.id as in_id,\n"
			+ "                DATE_FORMAT(dt.PunchDateTime,\n"
			+ "                '%Y-%m-%d') as in_date,\n"
			+ "                DATE_FORMAT(dt.PunchDateTime,\n"
			+ "                '%H:%i:%s') as in_time,\n"
			+ "                dt.EmpCode as emp_code              \n"
			+ "            FROM\n"
			+ "                HRRAWINOUTDATA dt              \n"
			+ "            JOIN\n"
			+ "                (\n"
			+ "                    Select\n"
			+ "                        EmpCode as emp_code ,\n"
			+ "                        min(id) id                             \n"
			+ "                    from\n"
			+ "                        HRRAWINOUTDATA                      \n"
			+ "                    where\n"
			+ "                        DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:date  \n"
			+ "                        and VerMode=0                           \n"
			+ "                    group by\n"
			+ "                        EmpCode                 \n"
			+ "                ) T                          \n"
			+ "                    on dt.EmpCode = T.emp_code                         \n"
			+ "                    and dt.id = T.id                  \n"
			+ "                ) b                      \n"
			+ "                    on a.emp_code=b.emp_code              \n"
			+ "            left join\n"
			+ "                (\n"
			+ "                    SELECT\n"
			+ "                        dt.PunchDateTime as out_punch_time,\n"
			+ "                        dt.id as out_id,\n"
			+ "                        DATE_FORMAT(dt.PunchDateTime,\n"
			+ "                        '%Y-%m-%d') as out_date,\n"
			+ "                        DATE_FORMAT(dt.PunchDateTime,\n"
			+ "                        '%H:%i:%s') as out_time,\n"
			+ "                        dt.EmpCode as emp_code                      \n"
			+ "                    FROM\n"
			+ "                        HRRAWINOUTDATA dt                      \n"
			+ "                    JOIN\n"
			+ "                        (\n"
			+ "                            Select\n"
			+ "                                EmpCode as emp_code ,\n"
			+ "                                max(id) id                                     \n"
			+ "                            from\n"
			+ "                                HRRAWINOUTDATA                              \n"
			+ "                            where\n"
			+ "                                DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:date \n"
			+ "                                and VerMode=0                                   \n"
			+ "                            group by\n"
			+ "                                EmpCode                          \n"
			+ "                        ) T                                  \n"
			+ "                            on dt.EmpCode = T.emp_code                                 \n"
			+ "                            and dt.id = T.id                          \n"
			+ "                        ) c                              \n"
			+ "                            on a.emp_code=c.emp_code  ",nativeQuery=true)
	List<LiveThumbData> getThumbData(String date);

	@Query(value="select\n"
			+ "        a.*,\n"
			+ "        ifnull(b.in_id,0) as in_id,\n"
			+ "        b.in_punch_time,\n"
			+ "        b.in_date,\n"
			+ "        b.in_time,\n"
			+ "        ifnull(c.out_id,0) as out_id,\n"
			+ "        c.out_punch_time,\n"
			+ "        c.out_date,\n"
			+ "        c.out_time     \n"
			+ "    from\n"
			+ "        (select\n"
			+ "            uuid() as id,\n"
			+ "            HRRAWINOUTDATA.EmpCode as emp_code,\n"
			+ "            count('') as count_thumb           \n"
			+ "        from\n"
			+ "            HRRAWINOUTDATA          \n"
			+ "        where\n"
			+ "            DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:yesterday \n"
			+ "        group by\n"
			+ "            EmpCode) a       \n"
			+ "    left join\n"
			+ "        (\n"
			+ "            SELECT\n"
			+ "                dt.PunchDateTime as in_punch_time,\n"
			+ "                dt.id as in_id,\n"
			+ "                DATE_FORMAT(dt.PunchDateTime,\n"
			+ "                '%Y-%m-%d') as in_date,\n"
			+ "                DATE_FORMAT(dt.PunchDateTime,\n"
			+ "                '%H:%i:%s') as in_time,\n"
			+ "                dt.EmpCode as emp_code              \n"
			+ "            FROM\n"
			+ "                HRRAWINOUTDATA dt              \n"
			+ "            JOIN\n"
			+ "                (\n"
			+ "                    Select\n"
			+ "                        EmpCode as emp_code ,\n"
			+ "                        min(id) id                             \n"
			+ "                    from\n"
			+ "                        HRRAWINOUTDATA                      \n"
			+ "                    where\n"
			+ "                        DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:yesterday \n"
			+ "                        and VerMode=0                           \n"
			+ "                    group by\n"
			+ "                        EmpCode                 \n"
			+ "                ) T                          \n"
			+ "                    on dt.EmpCode = T.emp_code                         \n"
			+ "                    and dt.id = T.id                  \n"
			+ "                ) b                      \n"
			+ "                    on a.emp_code=b.emp_code              \n"
			+ "            left join\n"
			+ "                (\n"
			+ "                    SELECT\n"
			+ "                        dt.PunchDateTime as out_punch_time,\n"
			+ "                        dt.id as out_id,\n"
			+ "                        DATE_FORMAT(dt.PunchDateTime,\n"
			+ "                        '%Y-%m-%d') as out_date,\n"
			+ "                        DATE_FORMAT(dt.PunchDateTime,\n"
			+ "                        '%H:%i:%s') as out_time,\n"
			+ "                        dt.EmpCode as emp_code                      \n"
			+ "                    FROM\n"
			+ "                        HRRAWINOUTDATA dt                      \n"
			+ "                    JOIN\n"
			+ "                        (\n"
			+ "                            Select\n"
			+ "                                EmpCode as emp_code ,\n"
			+ "                                min(id) id                                     \n"
			+ "                            from\n"
			+ "                                HRRAWINOUTDATA                              \n"
			+ "                            where\n"
			+ "                                DATE_FORMAT(PunchDateTime, '%Y-%m-%d')=:today \n"
			+ "                                and VerMode=0                                   \n"
			+ "                            group by\n"
			+ "                                EmpCode                          \n"
			+ "                        ) T                                  \n"
			+ "                            on dt.EmpCode = T.emp_code                                 \n"
			+ "                            and dt.id = T.id                          \n"
			+ "                        ) c                              \n"
			+ "                            on a.emp_code=c.emp_code             \n"
			+ "                    where\n"
			+ "                        a.count_thumb=1",nativeQuery=true)
	List<LiveThumbData> getThumbDataYesterday(String today, String yesterday);

}
