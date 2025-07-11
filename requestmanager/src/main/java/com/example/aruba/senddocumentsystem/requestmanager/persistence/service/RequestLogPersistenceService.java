package com.example.aruba.senddocumentsystem.requestmanager.persistence.service;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanager.domain.port.persistence.RequestPersistenceService;
import com.example.aruba.senddocumentsystem.requestmanager.persistence.mapper.RequestLogMapper;
import com.example.aruba.senddocumentsystem.requestmanager.persistence.repository.RequestLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestLogPersistenceService implements RequestPersistenceService {

    @Autowired
    private final RequestLogRepository repository;

    @Autowired
    private final RequestLogMapper mapper;

    @Override
    public RequestDTO insertRequest(RequestDTO dto) {
        return mapper.toDTO(repository.save(mapper.fromDTO(dto)));
    }
}
