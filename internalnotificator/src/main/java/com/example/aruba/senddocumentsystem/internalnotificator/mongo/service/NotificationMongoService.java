package com.example.aruba.senddocumentsystem.internalnotificator.mongo.service;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.port.nosql.NotificationNoSqlService;
import com.example.aruba.senddocumentsystem.internalnotificator.mongo.document.NotificationEvent;
import com.example.aruba.senddocumentsystem.internalnotificator.mongo.repository.NotificationMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationMongoService implements NotificationNoSqlService {

    @Autowired
    private NotificationMongoRepository repository;

    @Override
    public void persistEvent(NotificationDTO dto, String error){
        var event = new NotificationEvent();
        event.setError(error);
        event.setDto(dto);
        repository.save(event);
    }

}
