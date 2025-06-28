package com.example.aruba.senddocumentsystem.internalnotificator.domain.service;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.port.persistence.NotificationPersistenceService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends ValidatingService<NotificationDTO> {

    @Autowired
    private final NotificationPersistenceService persistenceService;

    public NotificationService(Validator validator, NotificationPersistenceService persistenceService) {
        super(validator);
        this.persistenceService = persistenceService;
    }

    public void persistNotification(NotificationDTO dto){
        validate(dto);
        persistenceService.persistNotification(dto);
    }
}
