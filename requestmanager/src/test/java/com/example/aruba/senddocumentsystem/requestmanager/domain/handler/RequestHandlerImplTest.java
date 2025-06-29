package com.example.aruba.senddocumentsystem.requestmanager.domain.handler;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanager.domain.exception.MessageDeliveryException;
import com.example.aruba.senddocumentsystem.requestmanager.domain.port.messagebroker.MessageProducer;
import com.example.aruba.senddocumentsystem.requestmanager.domain.port.nosql.RequestNoSqlService;
import com.example.aruba.senddocumentsystem.requestmanager.domain.service.RequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestHandlerImplTest {

    @Mock
    private RequestService service;

    @Mock
    private MessageProducer messageProducer;

    @Mock
    private RequestNoSqlService noSqlService;

    @InjectMocks
    private RequestHandlerImpl requestHandler;

    @Test
    public void whenInvokeInsertRequestAndNoExceptionIsThrown_ShouldReturnADtoAndProduceAMessage(){
        doReturn(RequestDTO.builder().build()).when(service).insertRequest(any());
        doNothing().when(messageProducer).produceMessage(any());

        assertDoesNotThrow(() -> requestHandler.insertRequest(RequestDTO.builder().build()));
        verify(messageProducer, times(1)).produceMessage(any());
    }

    @Test
    public void whenInvokeInsertRequestAndExceptionIsThrown_ShouldThrowException(){
        doThrow(RuntimeException.class).when(service).insertRequest(any());

        assertThrows(RuntimeException.class, () -> requestHandler.insertRequest(RequestDTO.builder().build()));
    }

    @Test
    public void whenInvokeInsertRequestAndMessageDeliveryExceptionIsThrown_ShouldPersistOnNosql(){
        doReturn(RequestDTO.builder().build()).when(service).insertRequest(any());
        doThrow(MessageDeliveryException.class).when(messageProducer).produceMessage(any());
        doNothing().when(noSqlService).persistEvent(any(), any());

        requestHandler.insertRequest(RequestDTO.builder().build());

        verify(noSqlService, times(1)).persistEvent(any(), any());
    }
}
