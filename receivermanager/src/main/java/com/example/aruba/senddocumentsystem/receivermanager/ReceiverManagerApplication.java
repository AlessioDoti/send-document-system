package com.example.aruba.senddocumentsystem.receivermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class ReceiverManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiverManagerApplication.class, args);
	}

}
