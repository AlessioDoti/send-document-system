package com.example.aruba.senddocumentsystem.deliverytracker.persistence.mapper;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.enumeration.Status;
import com.example.aruba.senddocumentsystem.deliverytracker.persistence.entity.Delivery;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DeliveryMapper {

    public Delivery fromDTO(DeliveryDTO dto){
        var delivery = new Delivery();
        delivery.setStatus(dto.getStatus().toString());
        delivery.setUsername(dto.getUsername());
        delivery.setTraceparent(dto.getTraceParent());
        delivery.setInsertTs(dto.getTimestamp());

        return delivery;
    }

    public DeliveryDTO toDTO(Delivery delivery){
        return DeliveryDTO.builder()
                .status(Status.valueOf(delivery.getStatus().toUpperCase()))
                .traceParent(delivery.getTraceparent())
                .timestamp(Objects.isNull(delivery.getUpdateTs()) ?
                        delivery.getInsertTs() :
                        delivery.getUpdateTs())
                .username(delivery.getUsername())
                .build();
    }
}
