package com.example.aruba.senddocumentsystem.internalnotificator.kafka.consumer.factory;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class NotificationDTOFactory {

    @SneakyThrows
    public NotificationDTO getDeliveryDTO(String message){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(message, NotificationDTO.class);
    }
}
