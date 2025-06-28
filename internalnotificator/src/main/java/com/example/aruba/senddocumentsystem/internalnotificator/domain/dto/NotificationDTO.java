package com.example.aruba.senddocumentsystem.internalnotificator.domain.dto;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.enumeration.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    @NotNull(message = "Status cannot be Null")
    Status status;

    @UUID(message = "Request TraceParent must be an uuid")
    @NotBlank(message = "Request TraceParent cannot be Empty")
    String traceParent;
}
