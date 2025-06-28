package com.example.aruba.senddocumentsystem.internalnotificator.mongo.repository;

import com.example.aruba.senddocumentsystem.internalnotificator.mongo.document.NotificationEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationMongoRepository extends MongoRepository<NotificationEvent, String> {
}
