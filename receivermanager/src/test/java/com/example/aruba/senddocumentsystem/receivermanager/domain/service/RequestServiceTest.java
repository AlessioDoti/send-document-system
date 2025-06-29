package com.example.aruba.senddocumentsystem.receivermanager.domain.service;


import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.domain.port.persistence.ReceiverPersistenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ReceiverService.class, ValidationAutoConfiguration.class})
public class RequestServiceTest {

    @MockitoBean
    private ReceiverPersistenceService persistenceService;

    @Autowired
    private ReceiverService service;

    @Test
    public void whenInvokeInsertReceiverWithAValidDTO_ShouldPersistWithoutException(){

        var validDTO = ReceiverDTO.builder()
                .username("user")
                .valid(true)
                .receiverFiscalCode("fiscalcode")
                .address("aaa@aaa.com")
                .build();

        assertDoesNotThrow(() -> service.insertReceiver(validDTO));
    }

    @Test
    public void whenInvokeInsertReceiverWithAnInvalidDTO_ShouldThrowIllegalArgumentException(){

        var invalidDTO = ReceiverDTO.builder()
                .valid(true)
                .receiverFiscalCode("fiscalcode")
                .address("aaa@aaa.com")
                .build();

        assertThrows(IllegalArgumentException.class, () -> service.insertReceiver(invalidDTO));
    }

    @Test
    public void whenInvokeInsertReceiverWithAValidDTOAndAnExceptionOccurs_ShouldThrowTheException(){
        doThrow(RuntimeException.class).when(persistenceService).insertReceiver(any());

        var validDTO = ReceiverDTO.builder()
                .username("user")
                .valid(true)
                .receiverFiscalCode("fiscalcode")
                .address("aaa@aaa.com")
                .build();

        assertThrows(RuntimeException.class, () -> service.insertReceiver(validDTO));
    }

    @Test
    public void whenInvokeUpdateReceiverWithAValidDTO_ShouldPersistWithoutException(){

        var validDTO = ReceiverDTO.builder()
                .username("user")
                .valid(true)
                .receiverFiscalCode("fiscalcode")
                .address("aaa@aaa.com")
                .build();

        assertDoesNotThrow(() -> service.updateReceiver(validDTO));
    }

    @Test
    public void whenInvokeUpdateReceiverWithAnInvalidDTO_ShouldThrowIllegalArgumentException(){

        var invalidDTO = ReceiverDTO.builder()
                .valid(true)
                .build();

        assertThrows(IllegalArgumentException.class, () -> service.updateReceiver(invalidDTO));
    }

    @Test
    public void whenInvokeUpdateReceiverWithAValidDTOAndAnExceptionOccurs_ShouldThrowTheException(){
        doThrow(RuntimeException.class).when(persistenceService).updateReceiver(any());

        var validDTO = ReceiverDTO.builder()
                .username("user")
                .valid(true)
                .receiverFiscalCode("fiscalcode")
                .address("aaa@aaa.com")
                .build();

        assertThrows(RuntimeException.class, () -> service.updateReceiver(validDTO));
    }

    @Test
    public void whenInvokeFindReceiversFromCodesAndNoExceptionOccurs_ShouldReturnADTO(){
        doReturn(List.of()).when(persistenceService).findReceiversFromCodes(any(), any());

        assertNotNull(persistenceService.findReceiversFromCodes(List.of(), "user"));
    }

    @Test
    public void whenInvokeFindReceiversFromCodesAndAnExceptionOccurs_ShouldThrowTheException(){
        doThrow(RuntimeException.class).when(persistenceService).findReceiversFromCodes(any(), any());

        assertThrows(RuntimeException.class,
                () -> persistenceService.findReceiversFromCodes(List.of(), "user"));
    }

    @Test
    public void whenFindUserReceiversAndNoExceptionOccurs_ShouldReturnADTO(){
        doReturn(Page.empty()).when(persistenceService).findUserReceivers(any(), any());

        assertNotNull(persistenceService.findUserReceivers("user", Pageable.ofSize(20)));
    }

    @Test
    public void whenInvokeFindUserReceiversAndAnExceptionOccurs_ShouldThrowTheException(){
        doThrow(RuntimeException.class).when(persistenceService).findUserReceivers(any(), any());

        assertThrows(RuntimeException.class,
                () -> persistenceService.findUserReceivers("user", Pageable.ofSize(20)));
    }
}
