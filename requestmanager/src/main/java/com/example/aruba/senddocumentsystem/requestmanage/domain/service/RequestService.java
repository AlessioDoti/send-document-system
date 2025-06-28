package com.example.aruba.senddocumentsystem.requestmanage.domain.service;

import com.example.aruba.senddocumentsystem.requestmanage.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanage.domain.port.feign.ReceiverRestService;
import com.example.aruba.senddocumentsystem.requestmanage.domain.port.persistence.RequestPersistenceService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService extends ValidatingService<RequestDTO>{

    @Autowired
    private final RequestPersistenceService persistenceService;

    @Autowired
    private final ReceiverRestService receiverRestService;

    public RequestService(Validator validator, RequestPersistenceService persistenceService, ReceiverRestService receiverRestService) {
        super(validator);
        this.persistenceService = persistenceService;
        this.receiverRestService = receiverRestService;
    }

    public RequestDTO insertRequest(RequestDTO dto){
        validate(dto);
        var addresses = receiverRestService.getAddresses(dto.getReceivers(), dto.getUsername());
        validateAddress(addresses.size(), dto.getReceivers().size());
        dto.setAddresses(addresses);
        return persistenceService.insertRequest(dto);
    }

    private void validateAddress(int addressesLength, int receiversLength){
        if(addressesLength != receiversLength){
            throw new IllegalArgumentException("One or more addresses are not valid");
        }
    }
}
