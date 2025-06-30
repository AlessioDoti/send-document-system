package com.example.aruba.senddocumentsystem.deliverytracker.mongo.repository;

import com.example.aruba.senddocumentsystem.deliverytracker.mongo.document.DeliveryEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryMongoRepository extends MongoRepository<DeliveryEvent, String> {
}
