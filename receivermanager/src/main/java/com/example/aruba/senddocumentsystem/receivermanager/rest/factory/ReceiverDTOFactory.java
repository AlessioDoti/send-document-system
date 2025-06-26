package com.example.aruba.senddocumentsystem.receivermanager.rest.factory;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.rest.request.PersistRequest;
import com.example.aruba.senddocumentsystem.receivermanager.rest.request.impl.InsertReceiverRequest;
import com.example.aruba.senddocumentsystem.receivermanager.rest.request.impl.UpdateReceiverRequest;
import org.springframework.stereotype.Component;

@Component
public class ReceiverDTOFactory {

    public ReceiverDTO getDTO(PersistRequest request){
        if(request instanceof InsertReceiverRequest){
            return getInsertDTO((InsertReceiverRequest) request);

        } else if (request instanceof UpdateReceiverRequest) {
            return getUpdateDTO((UpdateReceiverRequest) request);
        } else {
            throw new RuntimeException("Invalid Request, please map a new type of request");
        }
    }

    private ReceiverDTO getUpdateDTO(UpdateReceiverRequest request){
        return ReceiverDTO.builder()
                .username(null)
                .receiverFiscalCode(request.getReceiverKey())
                .address(request.getAddress())
                .valid(request.isValid())
                .build();
    }

    private ReceiverDTO getInsertDTO(InsertReceiverRequest request){
        return ReceiverDTO.builder()
                .username(request.getUsername())
                .receiverFiscalCode(request.getReceiverKey())
                .address(request.getAddress())
                .valid(true)
                .build();
    }
}
