package com.connect.weasport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WeasportApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeasportApplication.class, args);
	}

}
