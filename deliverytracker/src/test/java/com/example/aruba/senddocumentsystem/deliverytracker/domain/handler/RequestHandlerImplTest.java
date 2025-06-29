package com.example.aruba.senddocumentsystem.deliverytracker.domain.handler;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.service.DeliveryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestHandlerImplTest {

    @Mock
    private DeliveryService service;

    @InjectMocks
    private RequestHandlerImpl requestHandler;

    @Test
    public void whenInvokeFindByUsernameAndTraceParent_ShouldReturnADTO(){
        when(service.findByUsernameAndTraceParent(anyString(), anyString())).thenReturn(new DeliveryDTO());

        var ret = requestHandler.findByUsernameAndTraceParent("user", "traceParent");

        assertNotNull(ret);
    }

    @Test
    public void whenInvokeFindByUsernameAndTraceParentAndExceptionIsThrown_ShouldThrowTheSameException(){
        when(service.findByUsernameAndTraceParent(anyString(), anyString())).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> requestHandler.findByUsernameAndTraceParent("user", "traceParent"));
    }
}
