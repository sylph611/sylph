package com.sylph.bobmukja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BobmukjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BobmukjaApplication.class, args);
	}

}
