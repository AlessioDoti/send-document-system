package com.example.aruba.senddocumentsystem.receivermanager.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PersistRequest extends BaseRequest{

    protected String receiverKey;
    protected String address;
}
