package com.example.aruba.senddocumentsystem.internalnotificator.kafka.consumer.factory;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.enumeration.Status;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NotificationDTOFactory {

    @SneakyThrows
    public NotificationDTO getNotificationDTO(String message){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var dto = mapper.readValue(message, NotificationDTO.class);
        dto.setStatus(Objects.isNull(dto.getStatus()) ? Status.WAITING : dto.getStatus());
        return dto;
    }
}
