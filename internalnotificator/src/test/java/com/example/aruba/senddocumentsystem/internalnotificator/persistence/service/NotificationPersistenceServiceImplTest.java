package com.example.aruba.senddocumentsystem.internalnotificator.persistence.service;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.enumeration.Status;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.exception.SkipMessageException;
import com.example.aruba.senddocumentsystem.internalnotificator.mongo.repository.NotificationMongoRepository;
import com.example.aruba.senddocumentsystem.internalnotificator.persistence.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@ComponentScan(basePackages = "com.example.aruba.senddocumentsystem.internalnotificator.persistence")
public class NotificationPersistenceServiceImplTest {

    @MockitoBean
    private NotificationMongoRepository mongoRepository;

    @Autowired
    private NotificationPersistenceServiceImpl service;

    @Autowired
    private NotificationRepository repository;


    @Test
    public void whenInvokePersistNotificationWithAValidDTO_ShoulPersistTheNotification(){
        var dto = NotificationDTO.builder()
                .status(Status.WAITING)
                .traceParent(UUID.randomUUID().toString())
                .build();

        service.persistNotification(dto);

        var persisted = repository.findAll().stream().findFirst().orElse(null);

        assertNotNull(persisted);
        assertEquals(dto.getTraceParent(), persisted.getTraceParent());
        assertEquals(dto.getStatus().toString(), persisted.getStatus());

    }

    @Test
    public void whenInvokePersistNotificationWithAValidDTOAndExists_ShoulThrowSkipMessageException(){
        var dto = NotificationDTO.builder()
                .status(Status.COMPLETED)
                .traceParent(UUID.randomUUID().toString())
                .build();

        service.persistNotification(dto);

        assertThrows(SkipMessageException.class, () -> service.persistNotification(dto));
    }

    @Test
    public void whenInvokePersistNotificationWithAnInvalidDTO_ShoulThrowException(){
        var dto = NotificationDTO.builder()
                .traceParent(UUID.randomUUID().toString())
                .build();

        assertThrows(Exception.class, () -> service.persistNotification(dto));

    }

}
