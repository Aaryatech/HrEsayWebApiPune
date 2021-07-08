package com.ats.hrmgt.claim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.ClaimReport;

public interface ClaimReportRepository extends JpaRepository<ClaimReport, Integer>{

	
	@Query(value="SELECT\n" + 
			"        uuid() as id,claim_data.*,\n" + 
			"        b.initial_auth_name,\n" + 
			"        c.final_auth_name,\n" + 
			"        ifnull(d.emp_remarks,'-') as first_approval_remark,\n" + 
			"        ifnull(e.emp_remarks,'-') as second_approval_remark\n" + 
			"    FROM\n" + 
			"        ( SELECT\n" + 
			"            la.ca_head_id as claim_id,e.emp_code,\n" + 
			"            la.emp_id, \n" + 
			"            la.ca_from_dt  ,\n" + 
			"            la.ca_to_dt , \n" + 
			"            CONCAT(e.surname,\n" + 
			"            \" \",\n" + 
			"            e.first_name) AS emp_name,\n" + 
			"            la.claim_amount,\n" + 
			"            la.claim_title,\n" + 
			"            '-' as claim_type,\n" + 
			"            0 as detail_amt,\n" + 
			"            d.name as dept_name\n" + 
			"        FROM\n" + 
			"            claim_apply_header la,\n" + 
			"            m_employees e,\n" + 
			"            m_department d\n" + 
			"        WHERE\n" + 
			"            e.emp_id=la.emp_id                       \n" + 
			"            and la.claim_status =:sts and e.location_id=:locId \n" + 
			"            and date_format(ca_from_dt,'%m') = :month\n" + 
			"            and date_format(ca_from_dt,'%Y') = :year\n" + 
			"            and e.depart_id=d.depart_id and e.depart_id in (:deptIds)) claim_data\n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                claim_trail_pkey,\n" + 
			"                claim_id,\n" + 
			"                emp_remarks                          \n" + 
			"            FROM\n" + 
			"               claim_trail                     \n" + 
			"            where\n" + 
			"                claim_status=2                                          \n" + 
			"        ) d                                       \n" + 
			"            on claim_data.claim_id=d.claim_id\n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                claim_trail_pkey,\n" + 
			"                claim_id,\n" + 
			"                emp_remarks                          \n" + 
			"            FROM\n" + 
			"               claim_trail                     \n" + 
			"            where\n" + 
			"                claim_status=3                                          \n" + 
			"        ) e                                       \n" + 
			"            on claim_data.claim_id=e.claim_id\n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                la.emp_id,\n" + 
			"                la.ca_ini_auth_emp_id,\n" + 
			"                CONCAT(e.surname,\n" + 
			"                \" \",\n" + 
			"                e.first_name) AS initial_auth_name                           \n" + 
			"            FROM\n" + 
			"                claim_authority la,\n" + 
			"                m_employees e                          \n" + 
			"            where\n" + 
			"                e.emp_id=la.ca_ini_auth_emp_id                   \n" + 
			"        ) b                                        \n" + 
			"            on claim_data.emp_id=b.emp_id           \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                la.emp_id,\n" + 
			"                la.ca_fin_auth_emp_id,\n" + 
			"                CONCAT(e.surname,\n" + 
			"                \" \",\n" + 
			"                e.first_name) AS final_auth_name                           \n" + 
			"            FROM\n" + 
			"                claim_authority la,\n" + 
			"                m_employees e                          \n" + 
			"            where\n" + 
			"                e.emp_id=la.ca_fin_auth_emp_id                                           \n" + 
			"        ) c                                        \n" + 
			"            on claim_data.emp_id=c.emp_id    \n" + 
			"            \n" + 
			"   SELECT\n" + 
			"        uuid() as id,claim_data.*,\n" + 
			"        b.initial_auth_name,\n" + 
			"        c.final_auth_name,\n" + 
			"        ifnull(d.emp_remarks,'-') as first_approval_remark,\n" + 
			"        ifnull(e.emp_remarks,'-') as second_approval_remark\n" + 
			"    FROM\n" + 
			"        ( SELECT\n" + 
			"            la.ca_head_id as claim_id,\n" + 
			"            la.emp_id, \n" + 
			"            la.ca_from_dt  ,\n" + 
			"            la.ca_to_dt , \n" + 
			"            CONCAT(e.surname,\n" + 
			"            \" \",\n" + 
			"            e.first_name) AS emp_name,\n" + 
			"            la.claim_amount,\n" + 
			"            la.claim_title,\n" + 
			"            ct.claim_type_title as claim_type,\n" + 
			"            ch.claim_amount as detail_amt,\n" + 
			"            d.name as dept_name\n" + 
			"        FROM\n" + 
			"            claim_apply_header la,\n" + 
			"            m_employees e,\n" + 
			"            m_department d,\n" + 
			"            claim_apply ch,\n" + 
			"            claim_type ct\n" + 
			"        WHERE\n" + 
			"            e.emp_id=la.emp_id                       \n" + 
			"            and la.claim_status = 3 and e.location_id=2 \n" + 
			"            and date_format(ca_from_dt,'%m') = 06\n" + 
			"            and date_format(ca_from_dt,'%Y') = 2021\n" + 
			"            and e.depart_id=d.depart_id \n" + 
			"            and ch.ex_int2 = la.ca_head_id \n" + 
			"            and ct.claim_type_id = ch.claim_type_id) claim_data",nativeQuery=true)
	List<ClaimReport> claimHeaderReport(String month, String year, int locId, List<Integer> deptIds, int sts);

	
	@Query(value="SELECT\n" + 
			"        uuid() as id,claim_data.*,\n" + 
			"        b.initial_auth_name,\n" + 
			"        c.final_auth_name,\n" + 
			"        ifnull(d.emp_remarks,'-') as first_approval_remark,\n" + 
			"        ifnull(e.emp_remarks,'-') as second_approval_remark\n" + 
			"    FROM\n" + 
			"        ( SELECT\n" + 
			"            la.ca_head_id as claim_id,e.emp_code,\n" + 
			"            la.emp_id, \n" + 
			"            la.ca_from_dt  ,\n" + 
			"            la.ca_to_dt , \n" + 
			"            CONCAT(e.surname,\n" + 
			"            \" \",\n" + 
			"            e.first_name) AS emp_name,\n" + 
			"            la.claim_amount,\n" + 
			"            la.claim_title,\n" + 
			"            ct.claim_type_title as claim_type,\n" + 
			"            ch.claim_amount as detail_amt,\n" + 
			"            d.name as dept_name\n" + 
			"        FROM\n" + 
			"            claim_apply_header la,\n" + 
			"            m_employees e,\n" + 
			"            m_department d,\n" + 
			"            claim_apply ch,\n" + 
			"            claim_type ct\n" + 
			"        WHERE\n" + 
			"            e.emp_id=la.emp_id                       \n" + 
			"            and la.claim_status =:sts and e.location_id=:locId \n" + 
			"            and date_format(ca_from_dt,'%m') = :month\n" + 
			"            and date_format(ca_from_dt,'%Y') = :year\n" + 
			"            and e.depart_id=d.depart_id \n" + 
			"            and ch.ex_int2 = la.ca_head_id \n" + 
			"            and ct.claim_type_id = ch.claim_type_id and e.depart_id in (:deptIds)) claim_data\n" + 
			"            \n" + 
			"            \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                claim_trail_pkey,\n" + 
			"                claim_id,\n" + 
			"                emp_remarks                          \n" + 
			"            FROM\n" + 
			"               claim_trail                     \n" + 
			"            where\n" + 
			"                claim_status=2                                          \n" + 
			"        ) d                                       \n" + 
			"            on claim_data.claim_id=d.claim_id\n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                claim_trail_pkey,\n" + 
			"                claim_id,\n" + 
			"                emp_remarks                          \n" + 
			"            FROM\n" + 
			"               claim_trail                     \n" + 
			"            where\n" + 
			"                claim_status=3                                          \n" + 
			"        ) e                                       \n" + 
			"            on claim_data.claim_id=e.claim_id\n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                la.emp_id,\n" + 
			"                la.ca_ini_auth_emp_id,\n" + 
			"                CONCAT(e.surname,\n" + 
			"                \" \",\n" + 
			"                e.first_name) AS initial_auth_name                           \n" + 
			"            FROM\n" + 
			"                claim_authority la,\n" + 
			"                m_employees e                          \n" + 
			"            where\n" + 
			"                e.emp_id=la.ca_ini_auth_emp_id                   \n" + 
			"        ) b                                        \n" + 
			"            on claim_data.emp_id=b.emp_id           \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                la.emp_id,\n" + 
			"                la.ca_fin_auth_emp_id,\n" + 
			"                CONCAT(e.surname,\n" + 
			"                \" \",\n" + 
			"                e.first_name) AS final_auth_name                           \n" + 
			"            FROM\n" + 
			"                claim_authority la,\n" + 
			"                m_employees e                          \n" + 
			"            where\n" + 
			"                e.emp_id=la.ca_fin_auth_emp_id                                           \n" + 
			"        ) c                                        \n" + 
			"            on claim_data.emp_id=c.emp_id",nativeQuery=true)
	List<ClaimReport> claimDetailReport(String month, String year, int locId, List<Integer> deptIds, int sts);

}
