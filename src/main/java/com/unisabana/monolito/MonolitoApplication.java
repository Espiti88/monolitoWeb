package com.unisabana.monolito;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MonolitoApplication {

	private static final Logger log = LoggerFactory.getLogger(MonolitoApplication.class);

	public static void main(String[] args) {
		
		SpringApplication.run(MonolitoApplication.class, args);

	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			log.info("=================================================================================");
		}; 
	}

}
