package com.movingtoweb.service;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movingtoweb.test.ApplicationTests;

@RestController
@RequestMapping(value = "/testCase")
public class TestService {

	@RequestMapping(value = "/run", method = RequestMethod.GET)
	@ResponseBody
	private int runtTestCase() {

		Result result = JUnitCore.runClasses(ApplicationTests.class);

		System.out.println("Result ::" + result.getRunCount());
		return result.getRunCount();
	}
}
