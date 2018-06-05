package org.mfm.learn.service.fallback;

import org.mfm.learn.service.SayHelloService;
import org.springframework.stereotype.Component;

@Component
public class SayHelloServiceFallback implements SayHelloService {

	public String sayHello(String name) {
		return "error, " + name;
	}

}
