package com.example.aruba.senddocumentsystem.deliverytracker.mongo.service;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.mongo.repository.DeliveryMongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@DataMongoTest
@ComponentScan(basePackages = "com.example.aruba.senddocumentsystem.deliverytracker.mongo")
public class DeliveryMongoServiceTest {

    @Autowired
    private DeliveryMongoService service;

    @Autowired
    private DeliveryMongoRepository repository;

    @Test
    public void whenInvokePersistEvent_shouldPersistTheEvent(){
        var dto = DeliveryDTO.builder().build();
        service.persistEvent(dto, "error");

        var persisted = repository.findAll();

        assertNotNull(persisted);
    }
}
