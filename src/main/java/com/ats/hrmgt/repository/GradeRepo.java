package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.Grade; 

public interface GradeRepo extends JpaRepository<Grade, Integer> {

	Grade findByGradeIdAndDelStatus(int gradeId, int i);

	List<Grade> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query(value="UPDATE m_grade SET del_status=0 WHERE grade_id=:gradeId",nativeQuery=true)
	int deleteGreade(int gradeId);

}
