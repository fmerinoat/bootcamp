package com.cost.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
@EnableSwagger2
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

/*
 * Controller/Resource
 * 
 * Service
 * 
 * Repository
 * 
 */