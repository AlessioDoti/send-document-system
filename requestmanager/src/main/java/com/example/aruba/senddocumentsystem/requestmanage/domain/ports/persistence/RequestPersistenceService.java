package com.example.aruba.senddocumentsystem.requestmanage.domain.ports.persistence;

import com.example.aruba.senddocumentsystem.requestmanage.domain.dto.RequestDTO;

public interface RequestPersistenceService {
    RequestDTO insertRequest(RequestDTO dto);
}
