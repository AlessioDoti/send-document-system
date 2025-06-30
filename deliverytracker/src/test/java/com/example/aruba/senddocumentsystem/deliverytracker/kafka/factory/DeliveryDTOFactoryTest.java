package com.example.aruba.senddocumentsystem.deliverytracker.kafka.factory;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DeliveryDTOFactoryTest {

    @InjectMocks
    public DeliveryDTOFactory factory;

    @Test
    public void whenInvokeGetDeliveryDTOWithAValidMessage_ShouldReturnADTO(){
        var message = "{\n" +
                "\t\"username\": \"user2\",\n" +
                "\t\"documents\": [\n" +
                "\t\t\"document reference\"\n" +
                "\t],\n" +
                "\t\"addresses\": [\n" +
                "\t\t\"ALESSIO11\"\n" +
                "\t],\n" +
                "\t\"deliveryType\": \"STANDARD\",\n" +
                "\t\"traceParent\": \"ba7cf543-c1d9-4d3c-9984-badd5362e8a4\"\n" +
                "}";

        assertNotNull(factory.getDeliveryDTO(message));
    }

    @Test
    public void whenInvokeGetDeliveryDTOWithAnInvalidMessage_ShouldThrowException(){
        var message = "invalid";

        assertThrows(JsonParseException.class, () ->factory.getDeliveryDTO(message));
    }
}
