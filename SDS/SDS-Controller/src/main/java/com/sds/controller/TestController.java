package com.sds.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/testWeb")
	public String TestWebModule() {
		return "Hello World";
	}
	
}
