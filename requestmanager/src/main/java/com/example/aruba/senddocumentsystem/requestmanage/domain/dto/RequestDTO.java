package com.example.aruba.senddocumentsystem.requestmanage.domain.dto;

import com.example.aruba.senddocumentsystem.requestmanage.domain.enumeration.DeliveryType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDTO {

    @NotEmpty
    private String username;

    @NotEmpty(message = "Document list must not be empty")
    private List<String> documents;

    @NotEmpty(message = "Receiver list must not be empty")
    private List<String> receivers;

    private List<String> addresses;

    private DeliveryType deliveryType;

    private String traceParent;

}
