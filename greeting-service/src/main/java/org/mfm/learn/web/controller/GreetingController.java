package org.mfm.learn.web.controller;

import org.mfm.learn.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

//	@Autowired
//	private GreetingService greetingService;

	@Autowired
	private SayHelloService sayHelloService;

	@Value("${server.port}")
	private String port;

	@Value("${defaultName}")
	private String defaultName;

	@RequestMapping("/greeting")
	public String greeting(@RequestParam String name) {
		String result = null;

		if(name != null && !"".equals(name)) {
			result = this.sayHelloService.sayHello(name);
		} else {
			result = "hello, this is default name: " + this.defaultName;
		}

		result += " , through greeting service from port: " + this.port;

		return result;
	}

}
