package com.example.aruba.senddocumentsystem.internalnotificator.persistence.mapper;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.enumeration.Status;
import com.example.aruba.senddocumentsystem.internalnotificator.persistence.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public Notification fromDTO(NotificationDTO dto){
        var notification = new Notification();
        notification.setStatus(dto.getStatus().toString());
        notification.setTraceParent(dto.getTraceParent());
        return notification;
    }

    public NotificationDTO toDTO(Notification notification){
        return NotificationDTO.builder()
                .status(Status.valueOf(notification.getStatus().toUpperCase()))
                .traceParent(notification.getTraceParent())
                .build();
    }
}
