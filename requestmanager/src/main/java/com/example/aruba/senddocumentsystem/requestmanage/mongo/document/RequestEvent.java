package com.example.aruba.senddocumentsystem.requestmanage.mongo.document;

import com.example.aruba.senddocumentsystem.requestmanage.domain.dto.RequestDTO;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "request_insert_kafka_errors")
@Data
public class RequestEvent {

    @Id
    private String id;

    private String error;

    private RequestDTO dto;
}
