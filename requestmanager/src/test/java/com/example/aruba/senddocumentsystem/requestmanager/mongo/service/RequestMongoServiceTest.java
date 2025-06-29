package com.example.aruba.senddocumentsystem.requestmanager.mongo.service;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanager.mongo.repository.RequestMongoRepository;
import com.example.aruba.senddocumentsystem.requestmanager.mongo.service.config.FakeMongoConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@Import(FakeMongoConfig.class)
@DataMongoTest
@ComponentScan(basePackages = "com.example.aruba.senddocumentsystem.requestmanager.mongo")
public class RequestMongoServiceTest {

    @Autowired
    private RequestMongoService service;

    @Autowired
    private RequestMongoRepository repository;

    @Test
    public void whenInvokePersistEvent_shouldPersistTheEvent(){
        var dto = RequestDTO.builder().build();
        service.persistEvent(dto, "error");

        var persisted = repository.findAll();

        assertNotNull(persisted);
    }
}
