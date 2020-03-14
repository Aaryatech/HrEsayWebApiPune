package com.ats.hrmgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.FreezeLogs; 
import com.ats.hrmgt.repository.FreezeLogsRepo;

@RestController
public class LogsApiController {

	@Autowired
	FreezeLogsRepo freezeLogsRepoRepo;

	@RequestMapping(value = { "/freezeUnfreezeLogs" }, method = RequestMethod.POST)
	public @ResponseBody FreezeLogs freezeUnfreezeLogs(@RequestBody FreezeLogs freezeLogs) {

		FreezeLogs save = new FreezeLogs();
		try {

			save = freezeLogsRepoRepo.saveAndFlush(freezeLogs);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

}
