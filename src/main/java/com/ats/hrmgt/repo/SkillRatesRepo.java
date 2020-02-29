package com.ats.hrmgt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.SkillRates;

public interface SkillRatesRepo extends JpaRepository<SkillRates, Integer> {

	List<SkillRates> findByDelStatus(int i);
	
	
	
	
	
	

}
