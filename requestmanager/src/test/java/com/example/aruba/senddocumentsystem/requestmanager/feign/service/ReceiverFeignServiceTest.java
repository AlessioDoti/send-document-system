package com.example.aruba.senddocumentsystem.requestmanager.feign.service;

import com.example.aruba.senddocumentsystem.requestmanager.feign.client.ReceiverFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceiverFeignServiceTest {

    @Mock
    private ReceiverFeignClient client;

    @InjectMocks
    private ReceiverFeignService service;


    @Test
    public void whenInvokeGetAddressesAndNoExceptionsOccurs_shouldReturnAList(){
        doReturn(List.of()).when(client).getReceiversFromCodes(any(), any());

        assertDoesNotThrow(() -> service.getAddresses(List.of(), ""));
    }

    @Test
    public void whenInvokeGetAddressesAndAnExceptionsOccurs_shouldFallbackThrowRuntimeException(){
        doThrow(RuntimeException.class).when(client).getReceiversFromCodes(any(), any());

        assertThrows(RuntimeException.class, () -> service.getAddresses(List.of(), ""));
    }
}
