package uk.co.engagetech.backend.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "uk.co.engagetech.backend.controller", "uk.co.engagetech.backend.service" })
@EnableJpaRepositories(basePackages = "uk.co.engagetech.backend.service")
@EntityScan(basePackages = "uk.co.engagetech.backend.domain")
// @ImportResource(locations = "classpath:applicationContext.xml")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
