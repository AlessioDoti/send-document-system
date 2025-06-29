package com.example.aruba.senddocumentsystem.deliverytracker.domain.handler;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.port.nosql.DeliveryNoSqlService;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.service.DeliveryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventHandlerImplTest {

    @Mock
    private DeliveryService deliveryService;

    @Mock
    private DeliveryNoSqlService noSqlService;

    @InjectMocks
    private EventHandlerImpl handler;

    @Test
    public void whenInvokeHandleEventAndNoExceptionIsThrown_ShouldntThrowExceptions(){
        doNothing().when(deliveryService).persistDelivery(any());

        assertDoesNotThrow(() -> handler.handleEvent(new DeliveryDTO()));
    }

    @Test
    public void whenInvokeHandleEventAndExceptionIsThrown_ShouldPersistOnNosqlAndThrowRuntimeException(){
        doThrow(RuntimeException.class).when(deliveryService).persistDelivery(any());
        doNothing().when(noSqlService).persistEvent(any(), any());

        assertThrows(RuntimeException.class, () -> handler.handleEvent(new DeliveryDTO()));
        verify(noSqlService, times(1)).persistEvent(any(), any());
    }
}
