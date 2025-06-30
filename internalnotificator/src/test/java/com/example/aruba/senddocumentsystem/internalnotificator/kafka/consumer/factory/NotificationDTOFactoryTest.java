package com.example.aruba.senddocumentsystem.internalnotificator.kafka.consumer.factory;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class NotificationDTOFactoryTest {

    @InjectMocks
    public NotificationDTOFactory factory;

    @Test
    public void whenInvokeGetNotificationDTOWithAValidMessage_ShouldReturnADTO(){
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

        assertNotNull(factory.getNotificationDTO(message));
    }

    @Test
    public void whenInvokeGetNotificationDTOWithAnInvalidMessage_ShouldThrowException(){
        var message = "invalid";

        assertThrows(JsonParseException.class, () ->factory.getNotificationDTO(message));
    }
}
