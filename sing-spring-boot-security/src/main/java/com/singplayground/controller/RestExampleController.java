package com.singplayground.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestExampleController {

	@RequestMapping("example1")
	String home() {
		return "Hello World!";
	}

	@RequestMapping(value = "example2", method = RequestMethod.GET)
	public @ResponseBody HashMap example2Controller() throws Exception {
		HashMap hm = new HashMap();
		hm.put("name", "john");
		return hm;
	}

}
