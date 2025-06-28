package com.example.aruba.senddocumentsystem.deliverytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class DeliveryTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryTrackerApplication.class, args);
	}

}
