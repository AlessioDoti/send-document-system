package com.example.aruba.senddocumentsystem.internalnotificator.mongo.service;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.mongo.repository.NotificationMongoRepository;
import com.example.aruba.senddocumentsystem.internalnotificator.mongo.service.config.FakeMongoConfig;
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
@ComponentScan(basePackages = "com.example.aruba.senddocumentsystem.internalnotificator.mongo")
public class NotificationMongoServiceTest {

    @Autowired
    private NotificationMongoService service;

    @Autowired
    private NotificationMongoRepository repository;

    @Test
    public void whenInvokePersistEvent_shouldPersistTheEvent(){
        var dto = NotificationDTO.builder().build();
        service.persistEvent(dto, "error");

        var persisted = repository.findAll();

        assertNotNull(persisted);
    }
}
