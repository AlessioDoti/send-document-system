package com.example.aruba.senddocumentsystem.requestmanager.domain.port.persistence;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;

public interface RequestPersistenceService {
    RequestDTO insertRequest(RequestDTO dto);
}
