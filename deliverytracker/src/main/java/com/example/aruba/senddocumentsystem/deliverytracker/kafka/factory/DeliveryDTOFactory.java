package com.example.aruba.senddocumentsystem.deliverytracker.kafka.factory;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.enumeration.Status;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Component
public class DeliveryDTOFactory {

    @SneakyThrows
    public DeliveryDTO getDeliveryDTO(String message){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var dto = mapper.readValue(message, DeliveryDTO.class);
        dto.setStatus(Objects.isNull(dto.getStatus()) ? Status.WAITING : dto.getStatus());
        dto.setTimestamp(Objects.isNull(dto.getTimestamp()) ? Timestamp.from(Instant.now()) : dto.getTimestamp());
        return dto;
    }
}
