package com.ats.hrmgt.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LeaveEncash;

public interface LeaveEncashRepository extends JpaRepository<LeaveEncash, Integer> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE t_encash SET del_status = 0 WHERE id=:id",nativeQuery=true)
	int deleteEncashLeave(int id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE t_encash SET is_freeze = 1 WHERE month=:month and year=:year and emp_id in (:empIds) and del_status=1",nativeQuery=true)
	int updateEncashAmt(int month, int year, List<Integer> empIds);

}
