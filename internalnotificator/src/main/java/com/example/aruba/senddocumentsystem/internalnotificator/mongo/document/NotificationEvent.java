package com.example.aruba.senddocumentsystem.internalnotificator.mongo.document;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notification_kafka_errors")
@Data
public class NotificationEvent {

    @Id
    private String id;

    private String error;

    private NotificationDTO dto;
}
