package uk.co.engagetech.backend.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.co.engagetech.backend.controller.HelloController;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(HelloController.class, args);
	}

}
