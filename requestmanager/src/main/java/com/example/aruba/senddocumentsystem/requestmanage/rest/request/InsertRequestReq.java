package com.example.aruba.senddocumentsystem.requestmanage.rest.request;

import lombok.Getter;

import java.util.List;

@Getter
public class InsertRequestReq extends BaseRequest{

    private List<String> receivers;
    private List<String> documents;
    private String deliveryType;
}
