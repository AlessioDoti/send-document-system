package com.example.aruba.senddocumentsystem.receivermanager.mongo.service;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.domain.ports.nosql.ReceiverNoSqlService;
import com.example.aruba.senddocumentsystem.receivermanager.mongo.document.ReceiverEvent;
import com.example.aruba.senddocumentsystem.receivermanager.mongo.repository.ReceiverMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiverMongoService implements ReceiverNoSqlService {

    @Autowired
    private ReceiverMongoRepository repository;

    @Override
    public void persistEvent(ReceiverDTO dto){
        var event = new ReceiverEvent();
        event.setDto(dto);
        repository.save(event);
    }

}
