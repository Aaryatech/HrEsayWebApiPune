package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.InoutTable;

public interface InoutTableRepo extends JpaRepository<InoutTable, Integer> {

	@Transactional
	@Modifying
	@Query(value = "update HRRAWINOUTDATA set VerMode=1  WHERE id in (:ids)", nativeQuery = true)
	int updatethumbflag(List<Integer> ids);
}
