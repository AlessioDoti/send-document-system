package com.example.aruba.senddocumentsystem.internalnotificator.domain.handler;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.exception.SkipMessageException;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.port.messagebroker.producer.MessageProducer;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.port.nosql.NotificationNoSqlService;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.service.NotificationService;
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
    private NotificationService service;

    @Mock
    private NotificationNoSqlService noSqlService;

    @Mock
    private MessageProducer producer;

    @InjectMocks
    private EventHandlerImpl handler;

    @Test
    public void whenInvokeHandleEventAndNoExceptionIsThrown_ShouldntThrowExceptions(){
        doNothing().when(service).persistNotification(any());
        doNothing().when(producer).produceMessage(any());

        assertDoesNotThrow(() -> handler.handleEvent(new NotificationDTO()));
    }

    @Test
    public void whenInvokeHandleEventAndSkipMessageExceptionIsThrown_ShouldntThrowExceptions(){
        doThrow(SkipMessageException.class).when(service).persistNotification(any());

        assertDoesNotThrow(() -> handler.handleEvent(new NotificationDTO()));
    }

    @Test
    public void whenInvokeHandleEventAndExceptionIsThrown_ShouldPersistOnNosqlAndThrowRuntimeException(){
        doThrow(RuntimeException.class).when(service).persistNotification(any());
        doNothing().when(noSqlService).persistEvent(any(), any());

        assertThrows(RuntimeException.class, () -> handler.handleEvent(new NotificationDTO()));
        verify(noSqlService, times(1)).persistEvent(any(), any());
    }
}
