package com.example.aruba.senddocumentsystem.requestmanager.mongo.service;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanager.domain.port.nosql.RequestNoSqlService;
import com.example.aruba.senddocumentsystem.requestmanager.mongo.document.RequestEvent;
import com.example.aruba.senddocumentsystem.requestmanager.mongo.repository.RequestMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestMongoService implements RequestNoSqlService {

    @Autowired
    private RequestMongoRepository repository;

    @Override
    public void persistEvent(RequestDTO dto, String error){
        var event = new RequestEvent();
        event.setError(error);
        event.setDto(dto);
        repository.save(event);
    }

}
