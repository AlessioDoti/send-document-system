package com.example.aruba.senddocumentsystem.requestmanager.domain.service;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanager.domain.enumeration.DeliveryType;
import com.example.aruba.senddocumentsystem.requestmanager.domain.port.feign.ReceiverRestService;
import com.example.aruba.senddocumentsystem.requestmanager.domain.port.persistence.RequestPersistenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {RequestService.class, ValidationAutoConfiguration.class})
public class RequestServiceTest {

    @MockitoBean
    private RequestPersistenceService persistenceService;

    @MockitoBean
    private ReceiverRestService receiverRestService;

    @Autowired
    private RequestService service;

    @Test
    public void whenInvokeInsertRequestWithAValidDTO_ShouldPersistWithoutException(){
        doReturn(RequestDTO.builder().build()).when(persistenceService).insertRequest(any());
        doReturn(List.of("address")).when(receiverRestService).getAddresses(any(), anyString());

        var validDTO = RequestDTO.builder()
                .username("user")
                .addresses(List.of("address"))
                .documents(List.of("documents"))
                .receivers(List.of("receiver"))
                .traceParent(UUID.randomUUID().toString())
                .deliveryType(DeliveryType.CERTIFIED)
                .build();

        assertDoesNotThrow(() -> service.insertRequest(validDTO));
    }

    @Test
    public void whenInvokeInsertRequestWithAnInvalidDTO_ShouldThrowIllegalArgumentException(){
        doReturn(RequestDTO.builder().build()).when(persistenceService).insertRequest(any());

        var invalidDTO = RequestDTO.builder()
                .addresses(List.of("address"))
                .documents(List.of("documents"))
                .traceParent(UUID.randomUUID().toString())
                .deliveryType(DeliveryType.CERTIFIED)
                .build();

        assertThrows(IllegalArgumentException.class, () -> service.insertRequest(invalidDTO));
    }

    @Test
    public void whenInvokeInsertRequestWithAValidDTOAndAnExceptionOccurs_ShouldThrowTheException(){
        doThrow(RuntimeException.class).when(persistenceService).insertRequest(any());
        doReturn(List.of("address")).when(receiverRestService).getAddresses(any(), anyString());

        var invalidDTO = RequestDTO.builder()
                .username("user")
                .documents(List.of("documents"))
                .addresses(List.of("address"))
                .receivers(List.of("receiver"))
                .traceParent(UUID.randomUUID().toString())
                .deliveryType(DeliveryType.CERTIFIED)
                .build();

        assertThrows(RuntimeException.class, () -> service.insertRequest(invalidDTO));
    }

    @Test
    public void whenInvokeInsertRequestWithNoAddresses_ShouldThrowIllegalArgumentException(){
        doThrow(RuntimeException.class).when(persistenceService).insertRequest(any());
        doReturn(List.of()).when(receiverRestService).getAddresses(any(), anyString());

        var invalidDTO = RequestDTO.builder()
                .addresses(List.of("address"))
                .documents(List.of("documents"))
                .username("user")
                .receivers(List.of("receiver"))
                .traceParent(UUID.randomUUID().toString())
                .deliveryType(DeliveryType.CERTIFIED)
                .build();

        assertThrows(IllegalArgumentException.class, () -> service.insertRequest(invalidDTO));
    }


}
