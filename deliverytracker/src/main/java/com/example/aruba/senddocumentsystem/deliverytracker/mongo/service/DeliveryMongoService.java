package com.example.aruba.senddocumentsystem.deliverytracker.mongo.service;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.port.nosql.DeliveryNoSqlService;
import com.example.aruba.senddocumentsystem.deliverytracker.mongo.document.DeliveryEvent;
import com.example.aruba.senddocumentsystem.deliverytracker.mongo.repository.DeliveryMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryMongoService implements DeliveryNoSqlService {

    @Autowired
    private DeliveryMongoRepository repository;

    @Override
    public void persistEvent(DeliveryDTO dto, String error){
        var event = new DeliveryEvent();
        event.setError(error);
        event.setDto(dto);
        repository.save(event);
    }

}
