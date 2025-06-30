package com.example.aruba.senddocumentsystem.receivermanager.rest.factory;

import com.example.aruba.senddocumentsystem.receivermanager.rest.factory.utils.InvalidRequest;
import com.example.aruba.senddocumentsystem.receivermanager.rest.request.impl.InsertReceiverRequest;
import com.example.aruba.senddocumentsystem.receivermanager.rest.request.impl.UpdateReceiverRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ReceiverDTOFactoryTest {

    @InjectMocks
    public ReceiverDTOFactory factory;

    @Test
    public void whenInvokeGetDTOWithInsertReceiverRequest_ShouldReturnADTO(){
        var req = new InsertReceiverRequest();

        assertNotNull(factory.getDTO(req));
    }

    @Test
    public void whenInvokeGetDTOWithUpdateReceiverRequest_ShouldReturnADTO(){
        var req = new UpdateReceiverRequest();

        assertNotNull(factory.getDTO(req));
    }

    @Test
    public void whenInvokeGetDeliveryDTOWithAnInvalidMessage_ShouldThrowException(){
        var invalidReq = new InvalidRequest();

        assertThrows(RuntimeException.class, () -> factory.getDTO(invalidReq));
    }
}
