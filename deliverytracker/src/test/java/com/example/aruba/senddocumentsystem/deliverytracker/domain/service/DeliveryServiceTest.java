package com.example.aruba.senddocumentsystem.deliverytracker.domain.service;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.enumeration.Status;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.port.persistence.DeliveryPersistenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {DeliveryService.class, ValidationAutoConfiguration.class})
public class DeliveryServiceTest {

    @MockitoBean
    private DeliveryPersistenceService persistenceService;

    @Autowired
    private DeliveryService service;

    @Test
    public void whenInvokePersistDeliveryWithAValidDTO_ShouldPersistWithoutException(){
        doNothing().when(persistenceService).persistDelivery(any());

        var validDTO = DeliveryDTO.builder()
                .username("user")
                .status(Status.COMPLETED)
                .traceParent(UUID.randomUUID().toString())
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        assertDoesNotThrow(() -> service.persistDelivery(validDTO));
    }

    @Test
    public void whenInvokePersistDeliveryWithAnInvalidDTO_ShouldThrowIllegalArgumentException(){
        doNothing().when(persistenceService).persistDelivery(any());

        var invalidDTO = DeliveryDTO.builder()
                .username("user")
                .status(Status.COMPLETED)
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        assertThrows(IllegalArgumentException.class, () -> service.persistDelivery(invalidDTO));
    }

    @Test
    public void whenInvokePersistDeliveryWithAValidDTOAndAnExceptionOccurs_ShouldThrowTheException(){
        doThrow(RuntimeException.class).when(persistenceService).persistDelivery(any());

        var invalidDTO = DeliveryDTO.builder()
                .username("user")
                .status(Status.COMPLETED)
                .traceParent(UUID.randomUUID().toString())
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        assertThrows(RuntimeException.class, () -> service.persistDelivery(invalidDTO));
    }

    @Test
    public void whenInvokeFindByUsernameAndTraceParentAndNoExceptionOccurs_ShouldReturnADTO(){
        doReturn(new DeliveryDTO()).when(persistenceService).findByUsernameAndTraceParent(anyString(), anyString());

        assertNotNull(persistenceService.findByUsernameAndTraceParent("user", "traceParent"));
    }

    @Test
    public void whenInvokeFindByUsernameAndTraceParentAndAnExceptionOccurs_ShouldThrowTheException(){
        doThrow(RuntimeException.class).when(persistenceService).findByUsernameAndTraceParent(anyString(), anyString());

        assertThrows(RuntimeException.class,
                () -> persistenceService.findByUsernameAndTraceParent("user", "traceParent"));
    }

}
