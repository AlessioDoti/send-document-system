package com.example.aruba.senddocumentsystem.deliverytracker.persistence.service;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.enumeration.Status;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.exception.DeliveryNotFoundException;
import com.example.aruba.senddocumentsystem.deliverytracker.mongo.repository.DeliveryMongoRepository;
import com.example.aruba.senddocumentsystem.deliverytracker.persistence.repository.DeliveryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@ComponentScan(basePackages = "com.example.aruba.senddocumentsystem.deliverytracker.persistence")
public class DeliveryPersistenceServiceImplTest {

    @MockitoBean
    private DeliveryMongoRepository mongoRepository;

    @Autowired
    private DeliveryPersistenceServiceImpl service;

    @Autowired
    private DeliveryRepository repository;


    @Test
    public void whenInvokePersistDeliveryWithAValidDTO_ShoulPersistTheDelivery(){
        var dto = DeliveryDTO.builder()
                .timestamp(Timestamp.from(Instant.now()))
                .status(Status.COMPLETED)
                .traceParent(UUID.randomUUID().toString())
                .username("user")
                .build();

        service.persistDelivery(dto);

        var persisted = repository.findByTraceparentAndUsername(dto.getTraceParent(), dto.getUsername()).orElse(null);

        assertNotNull(persisted);
        assertEquals(dto.getTraceParent(), persisted.getTraceparent());
        assertEquals(dto.getTimestamp(), persisted.getInsertTs());
        assertEquals(dto.getUsername(), persisted.getUsername());
        assertEquals(dto.getStatus().toString(), persisted.getStatus());

    }

    @Test
    public void whenInvokePersistDeliveryWithAValidDTOAndExists_ShoulPersistTheDeliveryChanges(){
        var dto = DeliveryDTO.builder()
                .timestamp(Timestamp.from(Instant.now()))
                .status(Status.COMPLETED)
                .traceParent(UUID.randomUUID().toString())
                .username("user2")
                .build();

        service.persistDelivery(dto);

        var persistedId = Objects.requireNonNull(repository.findByTraceparentAndUsername(dto.getTraceParent(), dto.getUsername()).orElse(null)).getId();

        dto.setStatus(Status.FAILED);
        dto.setTimestamp(Timestamp.from(Instant.now().plusSeconds(30)));
        service.persistDelivery(dto);

        var persisted = repository.findByTraceparentAndUsername(dto.getTraceParent(), dto.getUsername()).orElse(null);

        assertNotNull(persisted);
        assertNotNull(persisted.getUpdateTs());
        assertEquals(persistedId, persisted.getId());
    }

    @Test
    public void whenInvokePersistDeliveryWithAnInvalidDTO_ShoulThrowException(){
        var dto = DeliveryDTO.builder()
                .timestamp(Timestamp.from(Instant.now()))
                .traceParent(UUID.randomUUID().toString())
                .username("user2")
                .build();

        assertThrows(Exception.class, () -> service.persistDelivery(dto));

    }

    @Test
    public void whenInvokeFindByUsernameAndTraceParentAndTheRecordExists_ShoulReturnTheRecord(){
        var dto = DeliveryDTO.builder()
                .timestamp(Timestamp.from(Instant.now()))
                .traceParent(UUID.randomUUID().toString())
                .status(Status.COMPLETED)
                .username("user2")
                .build();

        service.persistDelivery(dto);
        assertNotNull(service.findByUsernameAndTraceParent(dto.getUsername(), dto.getTraceParent()));

    }

    @Test
    public void whenInvokeFindByUsernameAndTraceParentAndTheRecordDoesNotExist_ShoulThrowDeliveryNotFoundException(){

        assertThrows(DeliveryNotFoundException.class, () -> service.findByUsernameAndTraceParent("user", "traceparent"));

    }
}
