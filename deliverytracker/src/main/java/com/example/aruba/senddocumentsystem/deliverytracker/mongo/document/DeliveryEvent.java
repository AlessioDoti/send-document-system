package com.example.aruba.senddocumentsystem.deliverytracker.mongo.document;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "delivery_update_errors")
@Data
public class DeliveryEvent {

    @Id
    private String id;

    private String error;

    private DeliveryDTO dto;
}
