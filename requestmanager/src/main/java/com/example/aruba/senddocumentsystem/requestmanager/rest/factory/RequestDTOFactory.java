package com.example.aruba.senddocumentsystem.requestmanager.rest.factory;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanager.domain.enumeration.DeliveryType;
import com.example.aruba.senddocumentsystem.requestmanager.rest.request.BaseRequest;
import com.example.aruba.senddocumentsystem.requestmanager.rest.request.InsertRequestReq;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestDTOFactory {

    public RequestDTO getDTO(BaseRequest request){
        if(request instanceof InsertRequestReq){
            return getInsertDTO((InsertRequestReq) request);
        } else{
            throw new RuntimeException("Invalid Request, please map a new type of request");
        }
    }

    private RequestDTO getInsertDTO(InsertRequestReq req){
        return RequestDTO.builder()
                .username(req.getUsername())
                .deliveryType(DeliveryType.valueOf(req.getDeliveryType().toUpperCase()))
                .receivers(req.getReceivers())
                .documents(req.getDocuments())
                .traceParent(UUID.randomUUID().toString())
                .build();
    }
}
