package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.FreezeLogs;

public interface FreezeLogsRepo extends JpaRepository<FreezeLogs, Integer> {

}
