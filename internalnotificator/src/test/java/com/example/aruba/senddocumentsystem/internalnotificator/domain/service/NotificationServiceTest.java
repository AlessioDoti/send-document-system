package com.example.aruba.senddocumentsystem.internalnotificator.domain.service;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.enumeration.Status;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.port.persistence.NotificationPersistenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {NotificationService.class, ValidationAutoConfiguration.class})
public class NotificationServiceTest {

    @MockitoBean
    private NotificationPersistenceService persistenceService;

    @Autowired
    private NotificationService service;

    @Test
    public void whenInvokePersistNotificationWithAValidDTO_ShouldPersistWithoutException(){
        doNothing().when(persistenceService).persistNotification(any());

        var validDTO = NotificationDTO.builder()
                .status(Status.COMPLETED)
                .traceParent(UUID.randomUUID().toString())
                .build();

        assertDoesNotThrow(() -> service.persistNotification(validDTO));
    }

    @Test
    public void whenInvokePersistNotificationWithAnInvalidDTO_ShouldThrowIllegalArgumentException(){
        doNothing().when(persistenceService).persistNotification(any());

        var invalidDTO = NotificationDTO.builder()
                .status(Status.COMPLETED)
                .build();

        assertThrows(IllegalArgumentException.class, () -> service.persistNotification(invalidDTO));
    }

    @Test
    public void whenInvokePersistNotificationWithAValidDTOAndAnExceptionOccurs_ShouldThrowTheException(){
        doThrow(RuntimeException.class).when(persistenceService).persistNotification(any());

        var invalidDTO = NotificationDTO.builder()
                .status(Status.COMPLETED)
                .traceParent(UUID.randomUUID().toString())
                .build();

        assertThrows(RuntimeException.class, () -> service.persistNotification(invalidDTO));
    }

}
