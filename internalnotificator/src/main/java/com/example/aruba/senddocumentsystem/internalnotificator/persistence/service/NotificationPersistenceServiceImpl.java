package com.example.aruba.senddocumentsystem.internalnotificator.persistence.service;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.exception.SkipMessageException;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.port.persistence.NotificationPersistenceService;
import com.example.aruba.senddocumentsystem.internalnotificator.persistence.mapper.NotificationMapper;
import com.example.aruba.senddocumentsystem.internalnotificator.persistence.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationPersistenceServiceImpl implements NotificationPersistenceService {

    @Autowired
    private final NotificationRepository repository;

    @Autowired
    private final NotificationMapper mapper;

    @Override
    public void persistNotification(NotificationDTO dto) {
        try{
            mapper.toDTO(repository.saveAndFlush(mapper.fromDTO(dto)));
        } catch (DataIntegrityViolationException e){
            log.info("Notification Already Exists, will be skipped");
            throw new SkipMessageException("Notification Already Exists, will be skipped");
        }
    }
}
