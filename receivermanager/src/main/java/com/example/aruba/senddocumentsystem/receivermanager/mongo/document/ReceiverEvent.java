package com.example.aruba.senddocumentsystem.receivermanager.mongo.document;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "receiver_insert_update_kafka_errors")
@Data
public class ReceiverEvent {

    @Id
    private String id;

    private String error;

    private ReceiverDTO dto;
}
