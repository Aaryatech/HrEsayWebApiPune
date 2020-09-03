package com.ats.hrmgt.claim.repository;

 import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.claim.ClaimApplyHeader;
 
 
public interface ClaimHeaderRepo extends JpaRepository<ClaimApplyHeader, Integer> {
 
	
	@Transactional
	@Modifying
	@Query("update ClaimApplyHeader set ex_int1=:trailId  WHERE ca_head_id=:claimId")
	int updateTrailApply(int claimId,int trailId);

	ClaimApplyHeader findByCaHeadIdAndDelStatus(int claimId, int i);


	@Transactional
	@Modifying
	@Query("update ClaimApplyHeader set claim_status=:status  WHERE ca_head_id=:claimId")
	int updateClaimStatus(int claimId,int status);
	
	
	@Transactional
	@Modifying
	@Query("update ClaimApplyHeader set claim_status=:status,year=:year,month=:month  WHERE ca_head_id=:claimId")
	int updateClaimStatusWithDate(int claimId,int status,int month,int year);

	
	
	@Query(value = " SELECT\n" + 
			"    claim_apply_header.ca_head_id,\n" + 
			"     claim_apply_header.ca_from_dt,\n" + 
			"    claim_apply_header.ca_to_dt,\n" + 
			"    claim_apply_header.claim_title,\n" + 
			"    claim_apply_header.proj_id,\n" + 
			"    claim_apply_header.emp_id,\n" + 
			"    claim_apply_header.claim_status,\n" + 
			"    claim_apply_header.claim_amount,\n" + 
			"    claim_apply_header.maker_user_id,\n" + 
			"    claim_apply_header.maker_enter_datetime,\n" + 
			"    claim_apply_header.circulated_to,\n" + 
			"    claim_apply_header.month,\n" + 
			"    claim_apply_header.year,\n" + 
			"    claim_apply_header.is_paid,\n" + 
			"    claim_apply_header.del_status,\n" + 
			"    claim_apply_header.is_active,\n" + 
			"    claim_apply_header.ex_int1,\n" + 
			"    claim_apply_header.ex_int2,\n" + 
			"    claim_apply_header.ex_int3,\n" + 
			"    CONCAT(\n" + 
			"        m_employees.surname,\n" + 
			"        \" \",\n" + 
			"        m_employees.first_name\n" + 
			"    ) AS ex_var1,\n" + 
			"    claim_apply_header.ex_var2,\n" + 
			"    m_employees.emp_code as ex_var3\n" + 
			"FROM\n" + 
			"    claim_apply_header,\n" + 
			"    m_employees\n" + 
			"WHERE\n" + 
			"    claim_apply_header.claim_status = 3 AND claim_apply_header.is_paid = 0 AND claim_apply_header.emp_id = m_employees.emp_id AND claim_apply_header.del_status=1", nativeQuery = true)

	List<ClaimApplyHeader> getClaimHeaderListByCompanyId();

	
	@Transactional
	@Modifying
	@Query("update ClaimApplyHeader set year=:year,month=:month,maker_enter_datetime=:dateTimeUpdate,maker_user_id=:userId WHERE ca_head_id=:clmHeadId")
	int updatePaid(@Param("dateTimeUpdate") String dateTimeUpdate,@Param("userId")  int userId,@Param("clmHeadId")  int clmHeadId,@Param("month")  int month,@Param("year")  int year);

	@Transactional
	@Modifying
	@Query("update ClaimApplyHeader set is_paid=1 where month=:month  and year=:year and claim_status=3 and is_paid=0 and emp_id in (:empIds) ")
	int updateClaim(@Param("month")int month, @Param("year") int year, @Param("empIds") List<Integer> empIds);

	@Query(value = " SELECT\n" + 
			"    claim_apply_header.ca_head_id,\n" + 
			"     claim_apply_header.ca_from_dt,\n" + 
			"    claim_apply_header.ca_to_dt,\n" + 
			"    claim_apply_header.claim_title,\n" + 
			"    claim_apply_header.proj_id,\n" + 
			"    claim_apply_header.emp_id,\n" + 
			"    claim_apply_header.claim_status,\n" + 
			"    claim_apply_header.claim_amount,\n" + 
			"    claim_apply_header.maker_user_id,\n" + 
			"    claim_apply_header.maker_enter_datetime,\n" + 
			"    claim_apply_header.circulated_to,\n" + 
			"    claim_apply_header.month,\n" + 
			"    claim_apply_header.year,\n" + 
			"    claim_apply_header.is_paid,\n" + 
			"    claim_apply_header.del_status,\n" + 
			"    claim_apply_header.is_active,\n" + 
			"    claim_apply_header.ex_int1,\n" + 
			"    claim_apply_header.ex_int2,\n" + 
			"    claim_apply_header.ex_int3,\n" + 
			"    CONCAT(\n" + 
			"        m_employees.surname,\n" + 
			"        \" \",\n" + 
			"        m_employees.first_name\n" + 
			"    ) AS ex_var1,\n" + 
			"    claim_apply_header.ex_var2,\n" + 
			"    m_employees.emp_code as ex_var3\n" + 
			"FROM\n" + 
			"    claim_apply_header,\n" + 
			"    m_employees\n" + 
			"WHERE\n" + 
			"    claim_apply_header.claim_status = 3 AND claim_apply_header.is_paid = 0 AND claim_apply_header.emp_id = m_employees.emp_id AND claim_apply_header.del_status=1 and m_employees.location_id in (:locId)", nativeQuery = true)
 List<ClaimApplyHeader> getClaimHeaderListByCompanyIdlocId(List<Integer> locId);
	 
}
