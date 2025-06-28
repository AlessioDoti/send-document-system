package com.example.aruba.senddocumentsystem.requestmanage.feign.dto;

import lombok.Data;

@Data
public class ReceiverDTO {

    private String username;

    private String receiverFiscalCode;

    private String address;

    boolean valid;
}
