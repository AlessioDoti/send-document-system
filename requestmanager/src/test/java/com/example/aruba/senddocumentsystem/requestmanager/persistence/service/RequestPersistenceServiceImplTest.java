package com.example.aruba.senddocumentsystem.requestmanager.persistence.service;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanager.domain.enumeration.DeliveryType;
import com.example.aruba.senddocumentsystem.requestmanager.domain.port.persistence.RequestPersistenceService;
import com.example.aruba.senddocumentsystem.requestmanager.mongo.repository.RequestMongoRepository;
import com.example.aruba.senddocumentsystem.requestmanager.persistence.repository.RequestLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@ComponentScan(basePackages = "com.example.aruba.senddocumentsystem.requestmanager.persistence")
public class RequestPersistenceServiceImplTest {

    @MockitoBean
    private RequestMongoRepository mongoRepository;

    @Autowired
    private RequestPersistenceService service;

    @Autowired
    private RequestLogRepository repository;


    @Test
    public void whenInvokeInsertRequestWithAValidDTO_ShouldInsertRequest(){
        var dto = RequestDTO.builder()
                .addresses(List.of("address"))
                .receivers(List.of("receiver"))
                .documents(List.of("document"))
                .traceParent(UUID.randomUUID().toString())
                .username("user")
                .deliveryType(DeliveryType.CERTIFIED)
                .build();

        service.insertRequest(dto);

        var persisted = repository.findAll().stream().findFirst().orElse(null);

        assertNotNull(persisted);
        assertEquals(dto.getTraceParent(), persisted.getTraceParent());

    }

    @Test
    public void whenInvokePersistNotificationWithAnInvalidDTO_ShoulThrowException(){
        var dto = RequestDTO.builder()
                .traceParent(UUID.randomUUID().toString())
                .build();

        assertThrows(Exception.class, () -> service.insertRequest(dto));

    }

}
