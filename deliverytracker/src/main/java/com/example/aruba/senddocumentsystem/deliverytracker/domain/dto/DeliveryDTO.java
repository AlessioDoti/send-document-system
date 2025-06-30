package com.example.aruba.senddocumentsystem.deliverytracker.domain.dto;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.enumeration.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

    @NotNull(message = "Timestamp cannot be Null")
    Timestamp timestamp;

    @NotNull(message = "Status cannot be Null")
    Status status;

    @UUID(message = "Request TraceParent must be an uuid")
    @NotBlank(message = "Request TraceParent cannot be Empty")
    String traceParent;

    @NotBlank(message = "Username cannot be Empty")
    String username;
}
