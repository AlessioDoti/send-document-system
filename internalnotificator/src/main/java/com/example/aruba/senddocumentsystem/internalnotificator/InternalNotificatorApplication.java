package com.example.aruba.senddocumentsystem.internalnotificator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class InternalNotificatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternalNotificatorApplication.class, args);
	}

}
