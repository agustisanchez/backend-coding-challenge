package uk.co.engagetech.backend.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
// TODO Move app context to common configuration
@RequestMapping("/hello")
@EnableAutoConfiguration
public class HelloController {

	// TODO consumes, produces
	@RequestMapping(path = "", method = RequestMethod.GET)
	public String hello() {
		return "Hello from Spring Boot";
	}

}
