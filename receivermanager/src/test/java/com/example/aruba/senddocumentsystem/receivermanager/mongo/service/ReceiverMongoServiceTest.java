package com.example.aruba.senddocumentsystem.receivermanager.mongo.service;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.mongo.repository.ReceiverMongoRepository;
import com.example.aruba.senddocumentsystem.receivermanager.mongo.service.config.FakeMongoConfig;
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
@ComponentScan(basePackages = "com.example.aruba.senddocumentsystem.receivermanager.mongo")
public class ReceiverMongoServiceTest {

    @Autowired
    private ReceiverMongoService service;

    @Autowired
    private ReceiverMongoRepository repository;

    @Test
    public void whenInvokePersistEvent_shouldPersistTheEvent(){
        var dto = ReceiverDTO.builder().build();
        service.persistEvent(dto, "error");

        var persisted = repository.findAll();

        assertNotNull(persisted);
    }
}
