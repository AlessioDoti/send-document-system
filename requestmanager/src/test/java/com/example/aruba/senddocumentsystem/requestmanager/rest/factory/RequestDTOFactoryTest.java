package com.example.aruba.senddocumentsystem.requestmanager.rest.factory;

import com.example.aruba.senddocumentsystem.requestmanager.rest.factory.utils.InvalidRequest;
import com.example.aruba.senddocumentsystem.requestmanager.rest.request.InsertRequestReq;
import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestDTOFactoryTest {

    @InjectMocks
    public RequestDTOFactory factory;

    @Test
    public void whenInvokeGetDTOWithInsertRequestReq_ShouldReturnADTO(){
        var req = new InsertRequestReq();
        req.setReceivers(List.of("receiver"));
        req.setDocuments(List.of("doc"));
        req.setDeliveryType("CERTIFIED");

        assertNotNull(factory.getDTO(req));
    }

    @Test
    public void whenInvokeGetDeliveryDTOWithAnInvalidMessage_ShouldThrowException(){
        var invalidReq = new InvalidRequest();

        assertThrows(RuntimeException.class, () -> factory.getDTO(invalidReq));
    }
}
