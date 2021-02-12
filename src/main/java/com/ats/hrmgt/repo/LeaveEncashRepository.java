package com.ats.hrmgt.repo;

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

}
