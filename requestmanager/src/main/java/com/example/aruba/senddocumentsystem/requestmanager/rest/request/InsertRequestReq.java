package com.example.aruba.senddocumentsystem.requestmanager.rest.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InsertRequestReq extends BaseRequest{

    private List<String> receivers;
    private List<String> documents;
    private String deliveryType;
}
