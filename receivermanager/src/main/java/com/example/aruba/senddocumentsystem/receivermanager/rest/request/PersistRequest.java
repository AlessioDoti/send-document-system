package com.example.aruba.senddocumentsystem.receivermanager.rest.request;

import lombok.Getter;

@Getter
public abstract class PersistRequest extends BaseRequest{

    protected String receiverKey;
    protected String address;
}
