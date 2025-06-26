package com.example.aruba.senddocumentsystem.receivermanager.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiverDTO {

    private String username;

    @NotBlank(message = "Receiver fiscal code must not be blank")
    private String receiverFiscalCode;

    @Email(message = "Address must be a valid email")
    private String address;

    boolean valid;
}
