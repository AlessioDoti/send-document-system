package com.example.aruba.senddocumentsystem.receivermanager.domain.handler;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.MessageDeliveryException;
import com.example.aruba.senddocumentsystem.receivermanager.domain.port.messagebroker.MessageProducer;
import com.example.aruba.senddocumentsystem.receivermanager.domain.port.nosql.ReceiverNoSqlService;
import com.example.aruba.senddocumentsystem.receivermanager.domain.service.ReceiverService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class RequestHandlerImplTest {

    @Mock
    private ReceiverService service;

    @Mock
    private MessageProducer messageProducer;

    @Mock
    private ReceiverNoSqlService receiverNoSqlService;

    @InjectMocks
    private RequestHandlerImpl requestHandler;

    @Test
    public void whenInvokeInsertReceiverAndNoExceptionIsThrown_ShouldReturnADtoAndProduceAMessage(){
        doReturn(ReceiverDTO.builder().build()).when(service).insertReceiver(any());
        doNothing().when(messageProducer).produceMessage(any());

        assertDoesNotThrow(() -> requestHandler.insertReceiver(ReceiverDTO.builder().build()));
        verify(messageProducer, times(1)).produceMessage(any());
    }

    @Test
    public void whenInvokeInsertReceiverAndExceptionIsThrown_ShouldThrowException(){
        doThrow(RuntimeException.class).when(service).insertReceiver(any());

        assertThrows(RuntimeException.class, () -> requestHandler.insertReceiver(ReceiverDTO.builder().build()));
    }

    @Test
    public void whenInvokeInsertReceiverAndMessageDeliveryExceptionIsThrown_ShouldPersistOnNosql(){
        doReturn(ReceiverDTO.builder().build()).when(service).insertReceiver(any());
        doThrow(MessageDeliveryException.class).when(messageProducer).produceMessage(any());
        doNothing().when(receiverNoSqlService).persistEvent(any(), any());

        requestHandler.insertReceiver(ReceiverDTO.builder().build());

        verify(receiverNoSqlService, times(1)).persistEvent(any(), any());
    }

    @Test
    public void whenInvokeUpdateReceiverAndNoExceptionIsThrown_ShouldReturnADtoAndProduceAMessage(){
        doReturn(ReceiverDTO.builder().build()).when(service).updateReceiver(any());
        doNothing().when(messageProducer).produceMessage(any());

        assertDoesNotThrow(() -> requestHandler.updateReceiver(ReceiverDTO.builder().build()));
        verify(messageProducer, times(1)).produceMessage(any());
    }

    @Test
    public void whenInvokeUpdateReceiverAndExceptionIsThrown_ShouldThrowException(){
        doThrow(RuntimeException.class).when(service).updateReceiver(any());

        assertThrows(RuntimeException.class, () -> requestHandler.updateReceiver(ReceiverDTO.builder().build()));
    }

    @Test
    public void whenInvokeUpdateReceiverAndMessageDeliveryExceptionIsThrown_ShouldPersistOnNosql(){
        doReturn(ReceiverDTO.builder().build()).when(service).updateReceiver(any());
        doThrow(MessageDeliveryException.class).when(messageProducer).produceMessage(any());
        doNothing().when(receiverNoSqlService).persistEvent(any(), any());

        requestHandler.updateReceiver(ReceiverDTO.builder().build());

        verify(receiverNoSqlService, times(1)).persistEvent(any(), any());
    }

    @Test
    public void whenInvokeGetUserReceivers_ShouldReturnADTO(){
        doReturn(Page.empty()).when(service).findUserReceivers(any(), any());

        var ret = requestHandler.getUserReceivers("user", Pageable.ofSize(20));

        assertNotNull(ret);
    }

    @Test
    public void whenInvokeGetUserReceivers_ShouldThrowTheSameException(){
        doThrow(RuntimeException.class).when(service).findUserReceivers(any(), any());

        assertThrows(RuntimeException.class, () -> requestHandler.getUserReceivers("user", Pageable.ofSize(20)));
    }

    @Test
    public void whenInvokegetReceiversFromCodes_ShouldReturnADTO(){
        doReturn(List.of()).when(service).findReceiversFromCodes(any(), any());

        var ret = requestHandler.getReceiversFromCodes(List.of(), "user");

        assertNotNull(ret);
    }

    @Test
    public void whenInvokegetReceiversFromCodes_ShouldThrowTheSameException(){
        doThrow(RuntimeException.class).when(service).findReceiversFromCodes(any(), any());

        assertThrows(RuntimeException.class, () -> requestHandler.getReceiversFromCodes(List.of(), "user"));
    }
}
