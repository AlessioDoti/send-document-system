package com.example.aruba.senddocumentsystem.deliverytracker.domain.dto;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.enumeration.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

import java.sql.Timestamp;

@Data
@Builder
public class DeliveryDTO {

    @NotNull(message = "Timestamp cannot be Null")
    Timestamp timestamp;

    Status status;

    @UUID(message = "Request TraceParent must be an uuid")
    @NotBlank(message = "Request TraceParent cannot be Empty")
    String requestTraceParent;

    @NotBlank(message = "Username cannot be Empty")
    String username;
}
