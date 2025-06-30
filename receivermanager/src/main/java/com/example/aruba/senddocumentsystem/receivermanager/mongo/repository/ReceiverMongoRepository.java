package com.example.aruba.senddocumentsystem.receivermanager.mongo.repository;

import com.example.aruba.senddocumentsystem.receivermanager.mongo.document.ReceiverEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverMongoRepository extends MongoRepository<ReceiverEvent, String> {
}
