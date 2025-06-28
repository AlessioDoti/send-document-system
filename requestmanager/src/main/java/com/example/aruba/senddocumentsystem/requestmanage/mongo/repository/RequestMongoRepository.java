package com.example.aruba.senddocumentsystem.requestmanage.mongo.repository;

import com.example.aruba.senddocumentsystem.requestmanage.mongo.document.RequestEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestMongoRepository extends MongoRepository<RequestEvent, String> {
}
