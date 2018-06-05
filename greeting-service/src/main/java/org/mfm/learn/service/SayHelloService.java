package org.mfm.learn.service;

import org.mfm.learn.service.fallback.SayHelloServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "say-hello-service", fallback = SayHelloServiceFallback.class)
public interface SayHelloService {

	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
	public String sayHello(@RequestParam(value = "name") String name);

}
