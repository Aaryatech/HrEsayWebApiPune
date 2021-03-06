package com.ats.hrmgt.advance.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.advance.Advance;

public interface AdvanceRepo extends JpaRepository<Advance, Integer> {

	List<Advance> findByDelStatusAndIsDedAndCmpId(int i, int j, int companyId);
	
	@Transactional
	@Modifying
	@Query("update Advance set del_status=0  WHERE id=:advId")
	int deleteAdvance(int advId);
	
	 Advance findById(int i);

	 @Query(value=" select\n" + 
	 		"        a.*\n" + 
	 		"    from\n" + 
	 		"        tbl_advance a,\n" + 
	 		"        m_employees e\n" + 
	 		"    where\n" + 
	 		"        a.voucher_no=:voucherNo \n" + 
	 		"        and a.del_status=:delStatus\n" + 
	 		"        and e.location_id=:locationId\n" + 
	 		"        and e.emp_id=a.emp_id",nativeQuery=true)
	List<Advance> findByVoucherNoAndDelStatus(String voucherNo, int delStatus,int locationId);
	
	
	List<Advance> findByEmpIdAndDelStatus(int empId,int i);
	
	@Transactional
	@Modifying
	@Query("update Advance set  ded_month=:dedMonth,ded_year=:dedYear,skip_login_name =:userId, skip_login_time=:dateTimeUpdate,skip_id=:count ,skip_remarks=:skipStr WHERE id=:advId")
	int skipAdvance(@Param("advId") int advId,@Param("dedYear") int dedYear,@Param("dedMonth") int dedMonth,@Param("dateTimeUpdate") String dateTimeUpdate,@Param("userId") int userId,@Param("count") int count,@Param("skipStr") String skipStr);

	@Transactional
	@Modifying
	@Query("update Advance set is_ded=1 where ded_month=:month and ded_year=:year and del_status=1 and is_ded=0 and emp_id in (:empIds)")
	int updateAdv(@Param("month")int month, @Param("year") int year, @Param("empIds") List<Integer> empIds);

	@Transactional
	@Modifying
	@Query("delete from Advance where ded_month=:month and ded_year=:year and ex_int1=1 and del_status=1 and is_ded=0 and emp_id in (:empIds)")
	int deleteAdvanceBydefault(int month, int year, List<Integer> empIds);

	@Transactional
	@Modifying
	@Query("update Advance set is_ded=1 where del_status=1 and is_ded=0 and emp_id=:empIds")
	int updateAdv(int empIds);
	
	
	
	 

}
